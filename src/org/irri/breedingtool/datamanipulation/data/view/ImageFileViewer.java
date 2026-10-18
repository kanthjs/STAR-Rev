/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.inject.Inject
 *  javax.inject.Named
 *  org.eclipse.e4.ui.di.Focus
 *  org.eclipse.e4.ui.model.application.ui.basic.MPart
 *  org.eclipse.swt.events.PaintEvent
 *  org.eclipse.swt.events.PaintListener
 *  org.eclipse.swt.graphics.Device
 *  org.eclipse.swt.graphics.GC
 *  org.eclipse.swt.graphics.Image
 *  org.eclipse.swt.graphics.ImageData
 *  org.eclipse.swt.layout.FillLayout
 *  org.eclipse.swt.widgets.Composite
 *  org.eclipse.swt.widgets.Layout
 */
package org.irri.breedingtool.datamanipulation.data.view;

import javax.inject.Inject;
import javax.inject.Named;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Layout;
import org.irri.breedingtool.application.handler.DialogHandler;
import org.irri.breedingtool.application.handler.PartStackHandler;

public class ImageFileViewer {
    private String filePath;

    @Inject
    public void setFilePath(@Named(value="filePath") String fileP) {
        this.filePath = fileP;
    }

    @Inject
    public ImageFileViewer(Composite parent) {
        Composite composite = new Composite(parent, 0);
        composite.setLayout((Layout)new FillLayout());
        System.out.println(String.valueOf(this.filePath) + " <");
        composite.addPaintListener(new PaintListener(){

            public void paintControl(PaintEvent e) {
                try {
                    Image image = new Image((Device)e.display, new ImageData(ImageFileViewer.this.filePath));
                    GC gc = e.gc;
                    gc.drawImage(image, 0, 0);
                }
                catch (Exception exception) {}
            }
        });
    }

    @Focus
    public void onFocus(MPart part) {
        DialogHandler.setPartDialogMaximized(PartStackHandler.getActiveElementID());
    }
}

