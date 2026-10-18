/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.swt.events.PaintEvent
 *  org.eclipse.swt.events.PaintListener
 *  org.eclipse.swt.graphics.Device
 *  org.eclipse.swt.graphics.Drawable
 *  org.eclipse.swt.graphics.GC
 *  org.eclipse.swt.graphics.Image
 *  org.eclipse.swt.graphics.ImageData
 *  org.eclipse.swt.layout.FillLayout
 *  org.eclipse.swt.widgets.Canvas
 *  org.eclipse.swt.widgets.Composite
 *  org.eclipse.swt.widgets.Display
 *  org.eclipse.swt.widgets.Layout
 */
package org.irri.breedingtool.manager.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Drawable;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Layout;
import org.irri.breedingtool.manager.api.IImageManager;

public class ImageManager
implements IImageManager {
    @Override
    public void drawThumbnails(List imageFiles, Canvas canvas, Composite container) {
    }

    @Override
    public Image resizeImage(Image image, int width, int height) {
        Image scaled = new Image((Device)Display.getDefault(), width, height);
        GC gc = new GC((Drawable)scaled);
        gc.setAntialias(1);
        gc.setInterpolation(2);
        gc.drawImage(image, 0, 0, image.getBounds().width, image.getBounds().height, 0, 0, width, height);
        gc.dispose();
        image.dispose();
        return scaled;
    }

    public static void openImage(String imPath, Composite composite) {
        composite.setLayout((Layout)new FillLayout());
        composite.addPaintListener(new PaintListener(){

            public void paintControl(PaintEvent e) {
                System.out.println("imPath: " + imPath);
                Image image = new Image((Device)e.display, new ImageData(imPath));
                GC gc = e.gc;
                image.getBounds().height = 10;
                image.getBounds().width = 10;
                gc.drawImage(image, 0, 0);
            }
        });
    }

    @Override
    public void openImage(String imPath, Canvas imagePlaceHolder) {
        imagePlaceHolder.addPaintListener(new PaintListener(){

            public void paintControl(PaintEvent e) {
                Image image = new Image((Device)Display.getCurrent(), imPath);
                e.gc.drawImage(image, 0, 0);
            }
        });
    }

    @Override
    public boolean exportImageTo(File source, File destinationFolder) {
        boolean retValue = false;
        try {
            int len;
            FileInputStream is = new FileInputStream(source);
            FileOutputStream os = new FileOutputStream(new File(String.valueOf(destinationFolder.getPath()) + "\\" + source.getName()));
            byte[] buf = new byte[1024];
            while ((len = ((InputStream)is).read(buf)) > 0) {
                ((OutputStream)os).write(buf, 0, len);
            }
            ((InputStream)is).close();
            ((OutputStream)os).close();
            boolean ko = source.renameTo(source);
            System.out.println(ko);
            System.out.println("Exported: " + source.getName());
            retValue = true;
        }
        catch (FileNotFoundException fileNotFoundException) {
            retValue = false;
        }
        catch (IOException iOException) {
            retValue = false;
        }
        return retValue;
    }

    @Override
    public List listAllImage(File folder) {
        ArrayList<String> returnFiles = new ArrayList<String>();
        File[] fileArray = folder.listFiles();
        int n = fileArray.length;
        int n2 = 0;
        while (n2 < n) {
            File fileEntry = fileArray[n2];
            if (fileEntry.getName().toString().endsWith(".jpg") || fileEntry.getName().toString().endsWith(".png")) {
                System.out.println(fileEntry);
                returnFiles.add(fileEntry.getPath());
            }
            ++n2;
        }
        return returnFiles;
    }
}

