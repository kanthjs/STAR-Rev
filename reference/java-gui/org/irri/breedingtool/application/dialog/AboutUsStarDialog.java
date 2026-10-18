/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.jface.dialogs.Dialog
 *  org.eclipse.jface.dialogs.IDialogConstants
 *  org.eclipse.swt.graphics.Point
 *  org.eclipse.swt.layout.GridData
 *  org.eclipse.swt.layout.GridLayout
 *  org.eclipse.swt.widgets.Composite
 *  org.eclipse.swt.widgets.Control
 *  org.eclipse.swt.widgets.Label
 *  org.eclipse.swt.widgets.Layout
 *  org.eclipse.swt.widgets.Shell
 *  org.eclipse.swt.widgets.Text
 */
package org.irri.breedingtool.application.dialog;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

public class AboutUsStarDialog
extends Dialog {
    private Text txtStatisticalToolFor;

    public AboutUsStarDialog(Shell parentShell) {
        super(parentShell);
        this.setShellStyle(133200);
    }

    protected Control createDialogArea(Composite parent) {
        Composite container = (Composite)super.createDialogArea(parent);
        container.setLayout((Layout)new GridLayout(1, false));
        Label lblIrri = new Label(container, 0);
        lblIrri.setLayoutData((Object)new GridData(16384, 0x1000000, true, false, 1, 1));
        lblIrri.setForeground(SWTResourceManager.getColor(6));
        lblIrri.setFont(SWTResourceManager.getFont("Times New Roman", 15, 1));
        lblIrri.setText("IRRI");
        Label lblInternationalRiceResearch = new Label(container, 0);
        GridData gd_lblInternationalRiceResearch = new GridData(16384, 0x1000000, true, false, 1, 1);
        gd_lblInternationalRiceResearch.heightHint = 27;
        lblInternationalRiceResearch.setLayoutData((Object)gd_lblInternationalRiceResearch);
        lblInternationalRiceResearch.setText("International Rice Research Institute");
        this.txtStatisticalToolFor = new Text(container, 2826);
        GridData gd_txtStatisticalToolFor = new GridData(4, 4, true, true, 1, 1);
        gd_txtStatisticalToolFor.heightHint = 184;
        this.txtStatisticalToolFor.setLayoutData((Object)gd_txtStatisticalToolFor);
        this.txtStatisticalToolFor.setText("Statistical Tool for Agricultural Research (STAR)\r\n\r\nVersion: 2.0.1\r\n\r\n(c) Copyright International Rice Research Institute (IRRI) 2013 - 2020 All rights reserved.\r\n\r\nFor updates, please visit http://bbi.irri.org\r\n\r\nFor any concerns, contact us through email at star.bbi@irri.org. \r\n");
        this.txtStatisticalToolFor.setBackground(SWTResourceManager.getColor(255, 255, 255));
        return container;
    }

    protected void createButtonsForButtonBar(Composite parent) {
        this.createButton(parent, 0, IDialogConstants.OK_LABEL, true);
    }

    protected void configureShell(Shell shell) {
        super.configureShell(shell);
        shell.setText("About STAR");
    }

    protected Point getInitialSize() {
        return new Point(605, 393);
    }
}

