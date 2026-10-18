/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.swt.graphics.Image
 *  org.eclipse.swt.widgets.Canvas
 *  org.eclipse.swt.widgets.Composite
 */
package org.irri.breedingtool.manager.api;

import java.io.File;
import java.util.List;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

public interface IImageManager {
    public void drawThumbnails(List var1, Canvas var2, Composite var3);

    public Image resizeImage(Image var1, int var2, int var3);

    public void openImage(String var1, Canvas var2);

    public List listAllImage(File var1);

    public boolean exportImageTo(File var1, File var2);
}

