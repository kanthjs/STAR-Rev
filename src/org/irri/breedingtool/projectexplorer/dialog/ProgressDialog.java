/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.jface.dialogs.Dialog
 *  org.eclipse.jface.dialogs.IDialogConstants
 *  org.eclipse.swt.layout.GridData
 *  org.eclipse.swt.layout.GridLayout
 *  org.eclipse.swt.widgets.Composite
 *  org.eclipse.swt.widgets.Control
 *  org.eclipse.swt.widgets.Label
 *  org.eclipse.swt.widgets.ProgressBar
 *  org.eclipse.swt.widgets.Shell
 */
package org.irri.breedingtool.projectexplorer.dialog;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.widgets.Shell;

public class ProgressDialog
extends Dialog {
    private String getProjectName;
    private ProgressBar progressBar;

    public String getGetProjectName() {
        return this.getProjectName;
    }

    private void setGetProjectName(String getProjectName) {
        this.getProjectName = getProjectName;
    }

    public ProgressDialog(Shell parentShell) {
        super(parentShell);
        this.setBlockOnOpen(true);
    }

    protected void createButtonsForButtonBar(Composite parent) {
        this.createButton(parent, 0, IDialogConstants.OK_LABEL, true);
        this.createButton(parent, 1, IDialogConstants.CANCEL_LABEL, false);
    }

    protected void okPressed() {
    }

    protected Control createDialogArea(Composite parent) {
        parent.getShell().setText("Create new STAR Project");
        Composite container = (Composite)super.createDialogArea(parent);
        GridLayout gridLayout = (GridLayout)container.getLayout();
        gridLayout.numColumns = 2;
        new Label(container, 0);
        new Label(container, 0);
        new Label(container, 0);
        new Label(container, 0);
        this.progressBar = new ProgressBar(container, 0);
        GridData gd_progressBar = new GridData(16384, 0x1000000, false, false, 2, 1);
        gd_progressBar.widthHint = 433;
        this.progressBar.setLayoutData((Object)gd_progressBar);
        return container;
    }
}

