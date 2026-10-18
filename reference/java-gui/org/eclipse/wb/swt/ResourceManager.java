/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.core.runtime.Platform
 *  org.eclipse.jface.resource.CompositeImageDescriptor
 *  org.eclipse.jface.resource.ImageDescriptor
 *  org.eclipse.swt.graphics.Image
 *  org.eclipse.swt.graphics.Point
 *  org.eclipse.swt.graphics.Rectangle
 *  org.osgi.framework.Bundle
 */
package org.eclipse.wb.swt;

import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.resource.CompositeImageDescriptor;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.wb.swt.SWTResourceManager;
import org.osgi.framework.Bundle;

public class ResourceManager
extends SWTResourceManager {
    private static Map<ImageDescriptor, Image> m_descriptorImageMap = new HashMap<ImageDescriptor, Image>();
    private static Map<Image, Map<Image, Image>>[] m_decoratedImageMap = new Map[5];
    private static Map<String, Image> m_URLImageMap = new HashMap<String, Image>();
    private static PluginResourceProvider m_designTimePluginResourceProvider = null;

    public static ImageDescriptor getImageDescriptor(Class<?> clazz, String path) {
        return ImageDescriptor.createFromFile(clazz, (String)path);
    }

    public static ImageDescriptor getImageDescriptor(String path) {
        try {
            return ImageDescriptor.createFromURL((URL)new File(path).toURI().toURL());
        }
        catch (MalformedURLException malformedURLException) {
            return null;
        }
    }

    public static Image getImage(ImageDescriptor descriptor) {
        if (descriptor == null) {
            return null;
        }
        Image image = m_descriptorImageMap.get(descriptor);
        if (image == null) {
            image = descriptor.createImage();
            m_descriptorImageMap.put(descriptor, image);
        }
        return image;
    }

    public static Image decorateImage(Image baseImage, Image decorator) {
        return ResourceManager.decorateImage(baseImage, decorator, 4);
    }

    public static Image decorateImage(Image baseImage, Image decorator, int corner) {
        Image result;
        Map<Image, Image> decoratedMap;
        if (corner <= 0 || corner >= 5) {
            throw new IllegalArgumentException("Wrong decorate corner");
        }
        Map<Image, Map<Image, Image>> cornerDecoratedImageMap = m_decoratedImageMap[corner];
        if (cornerDecoratedImageMap == null) {
            ResourceManager.m_decoratedImageMap[corner] = cornerDecoratedImageMap = new HashMap<Image, Map<Image, Image>>();
        }
        if ((decoratedMap = cornerDecoratedImageMap.get(baseImage)) == null) {
            decoratedMap = new HashMap<Image, Image>();
            cornerDecoratedImageMap.put(baseImage, decoratedMap);
        }
        if ((result = decoratedMap.get(decorator)) == null) {
            Rectangle bib = baseImage.getBounds();
            Rectangle dib = decorator.getBounds();
            Point baseImageSize = new Point(bib.width, bib.height);
            CompositeImageDescriptor compositImageDesc = new CompositeImageDescriptor(){

                protected void drawCompositeImage(int width, int height) {
                    this.drawImage(baseImage.getImageData(), 0, 0);
                    if (corner == 1) {
                        this.drawImage(decorator.getImageData(), 0, 0);
                    } else if (corner == 2) {
                        this.drawImage(decorator.getImageData(), bib.width - dib.width, 0);
                    } else if (corner == 3) {
                        this.drawImage(decorator.getImageData(), 0, bib.height - dib.height);
                    } else if (corner == 4) {
                        this.drawImage(decorator.getImageData(), bib.width - dib.width, bib.height - dib.height);
                    }
                }

                protected Point getSize() {
                    return baseImageSize;
                }
            };
            result = compositImageDesc.createImage();
            decoratedMap.put(decorator, result);
        }
        return result;
    }

    public static void disposeImages() {
        SWTResourceManager.disposeImages();
        Iterator<Image> I = m_descriptorImageMap.values().iterator();
        while (I.hasNext()) {
            I.next().dispose();
        }
        m_descriptorImageMap.clear();
        int i = 0;
        while (i < m_decoratedImageMap.length) {
            Map<Image, Map<Image, Image>> cornerDecoratedImageMap = m_decoratedImageMap[i];
            if (cornerDecoratedImageMap != null) {
                for (Map<Image, Image> decoratedMap : cornerDecoratedImageMap.values()) {
                    for (Image image : decoratedMap.values()) {
                        image.dispose();
                    }
                    decoratedMap.clear();
                }
                cornerDecoratedImageMap.clear();
            }
            ++i;
        }
        I = m_URLImageMap.values().iterator();
        while (I.hasNext()) {
            I.next().dispose();
        }
        m_URLImageMap.clear();
    }

    @Deprecated
    public static Image getPluginImage(Object plugin, String name) {
        try {
            URL url = ResourceManager.getPluginImageURL(plugin, name);
            if (url != null) {
                return ResourceManager.getPluginImageFromUrl(url);
            }
        }
        catch (Throwable throwable) {}
        return null;
    }

    public static Image getPluginImage(String symbolicName, String path) {
        try {
            URL url = ResourceManager.getPluginImageURL(symbolicName, path);
            if (url != null) {
                return ResourceManager.getPluginImageFromUrl(url);
            }
        }
        catch (Throwable throwable) {}
        return null;
    }

    private static Image getPluginImageFromUrl(URL url) {
        try {
            String key = url.toExternalForm();
            Image image = m_URLImageMap.get(key);
            if (image == null) {
                InputStream stream = url.openStream();
                try {
                    image = ResourceManager.getImage(stream);
                    m_URLImageMap.put(key, image);
                }
                finally {
                    stream.close();
                }
            }
            return image;
        }
        catch (Throwable throwable) {
            try {
            }
            catch (Throwable throwable2) {}
            return null;
        }
    }

    @Deprecated
    public static ImageDescriptor getPluginImageDescriptor(Object plugin, String name) {
        try {
            URL url = ResourceManager.getPluginImageURL(plugin, name);
            return ImageDescriptor.createFromURL((URL)url);
        }
        catch (Throwable throwable) {
            try {
            }
            catch (Throwable throwable2) {}
            return null;
        }
    }

    public static ImageDescriptor getPluginImageDescriptor(String symbolicName, String path) {
        try {
            URL url = ResourceManager.getPluginImageURL(symbolicName, path);
            if (url != null) {
                return ImageDescriptor.createFromURL((URL)url);
            }
        }
        catch (Throwable throwable) {}
        return null;
    }

    private static URL getPluginImageURL(String symbolicName, String path) {
        Bundle bundle = Platform.getBundle((String)symbolicName);
        if (bundle != null) {
            return bundle.getEntry(path);
        }
        if (m_designTimePluginResourceProvider != null) {
            return m_designTimePluginResourceProvider.getEntry(symbolicName, path);
        }
        return null;
    }

    private static URL getPluginImageURL(Object plugin, String name) throws Exception {
        try {
            Class<?> BundleClass = Class.forName("org.osgi.framework.Bundle");
            Class<?> BundleContextClass = Class.forName("org.osgi.framework.BundleContext");
            if (BundleContextClass.isAssignableFrom(plugin.getClass())) {
                Method getBundleMethod = BundleContextClass.getMethod("getBundle", new Class[0]);
                Object bundle = getBundleMethod.invoke(plugin, new Object[0]);
                Class<?> PathClass = Class.forName("org.eclipse.core.runtime.Path");
                Constructor<?> pathConstructor = PathClass.getConstructor(String.class);
                Object path = pathConstructor.newInstance(name);
                Class<?> IPathClass = Class.forName("org.eclipse.core.runtime.IPath");
                Class<?> PlatformClass = Class.forName("org.eclipse.core.runtime.Platform");
                Method findMethod = PlatformClass.getMethod("find", BundleClass, IPathClass);
                return (URL)findMethod.invoke(null, bundle, path);
            }
        }
        catch (Throwable throwable) {}
        Class<?> PluginClass = Class.forName("org.eclipse.core.runtime.Plugin");
        if (PluginClass.isAssignableFrom(plugin.getClass())) {
            Class<?> PathClass = Class.forName("org.eclipse.core.runtime.Path");
            Constructor<?> pathConstructor = PathClass.getConstructor(String.class);
            Object path = pathConstructor.newInstance(name);
            Class<?> IPathClass = Class.forName("org.eclipse.core.runtime.IPath");
            Method findMethod = PluginClass.getMethod("find", IPathClass);
            return (URL)findMethod.invoke(plugin, path);
        }
        return null;
    }

    public static void dispose() {
        ResourceManager.disposeColors();
        ResourceManager.disposeFonts();
        ResourceManager.disposeImages();
    }

    public static interface PluginResourceProvider {
        public URL getEntry(String var1, String var2);
    }
}

