/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.inject.Inject
 *  org.eclipse.e4.ui.di.Focus
 *  org.eclipse.e4.ui.model.application.ui.basic.MPart
 *  org.eclipse.swt.browser.Browser
 *  org.eclipse.swt.layout.FillLayout
 *  org.eclipse.swt.widgets.Composite
 *  org.eclipse.swt.widgets.Layout
 */
package org.irri.breedingtool.datamanipulation.data.view;

import java.io.File;
import java.net.MalformedURLException;
import javax.inject.Inject;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Layout;
import org.irri.breedingtool.application.handler.DialogHandler;
import org.irri.breedingtool.application.handler.PartStackHandler;
import org.irri.breedingtool.application.model.ProjectExplorerTreeNodeModel;

public class PdfViewer {
    static ProjectExplorerTreeNodeModel treeNodeModel;
    static String pdfPath;

    static {
        pdfPath = "";
    }

    @Inject
    public PdfViewer(Composite parent) {
        Composite composite = new Composite(parent, 0);
        composite.setLayout((Layout)new FillLayout(256));
        Browser browser = new Browser(composite, 0);
        if (pdfPath.length() > 3) {
            try {
                browser.setUrl(new File(pdfPath).toURI().toURL().toString());
            }
            catch (MalformedURLException e) {
                e.printStackTrace();
            }
            return;
        }
        try {
            browser.setUrl(treeNodeModel.getProjectFile().toURI().toURL().toString());
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Focus
    public void onFocus(MPart part) {
        DialogHandler.setPartDialogMaximized(PartStackHandler.getActiveElementID());
    }

    public static void openPdfFile(ProjectExplorerTreeNodeModel fileM) {
        treeNodeModel = fileM;
    }

    public static void openPdfFile(String fileM) {
        pdfPath = fileM;
    }
}

