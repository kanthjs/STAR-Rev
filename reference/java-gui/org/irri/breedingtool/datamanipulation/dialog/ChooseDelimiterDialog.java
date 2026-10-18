/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.jface.dialogs.Dialog
 *  org.eclipse.jface.dialogs.IDialogConstants
 *  org.eclipse.swt.graphics.Point
 *  org.eclipse.swt.layout.GridData
 *  org.eclipse.swt.layout.GridLayout
 *  org.eclipse.swt.widgets.Button
 *  org.eclipse.swt.widgets.Composite
 *  org.eclipse.swt.widgets.Control
 *  org.eclipse.swt.widgets.Group
 *  org.eclipse.swt.widgets.Layout
 *  org.eclipse.swt.widgets.Shell
 */
package org.irri.breedingtool.datamanipulation.dialog;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.Shell;

public class ChooseDelimiterDialog
extends Dialog {
    private String chosenDelimiter = "";
    private Button btnSemicolon;
    private Button btnComma;
    private Button btnSpace;
    private Button btnTab;
    private Shell parentShell;
    private Button btnPlainTxt;
    private boolean importCalled;
    private String fileName;

    public ChooseDelimiterDialog(Shell parentShell, boolean importCalled, String fileName) {
        super(parentShell);
        this.parentShell = parentShell;
        this.importCalled = importCalled;
        this.fileName = fileName;
    }

    public String getDelimiter() {
        return this.chosenDelimiter;
    }

    protected Control createDialogArea(Composite parent) {
        parent.getShell().setText("Specify Delimiter for: " + this.fileName);
        Composite container = (Composite)super.createDialogArea(parent);
        Group grpDelimiterUsedIn = new Group(container, 0);
        grpDelimiterUsedIn.setText("Delimiter used in the text file: (choose one)");
        grpDelimiterUsedIn.setLayout((Layout)new GridLayout(1, false));
        GridData gd_grpDelimiterUsedIn = new GridData(4, 4, true, true, 1, 1);
        gd_grpDelimiterUsedIn.heightHint = 139;
        gd_grpDelimiterUsedIn.widthHint = 235;
        grpDelimiterUsedIn.setLayoutData((Object)gd_grpDelimiterUsedIn);
        this.btnComma = new Button((Composite)grpDelimiterUsedIn, 16);
        this.btnComma.setLayoutData((Object)new GridData(4, 4, true, false, 1, 1));
        this.btnComma.setText("Comma");
        if (!this.importCalled) {
            this.btnPlainTxt = new Button((Composite)grpDelimiterUsedIn, 16);
            this.btnPlainTxt.setLayoutData((Object)new GridData(4, 4, false, false, 1, 1));
            this.btnPlainTxt.setText("Plain Text");
        }
        this.btnTab = new Button((Composite)grpDelimiterUsedIn, 16);
        this.btnTab.setLayoutData((Object)new GridData(4, 4, false, false, 1, 1));
        this.btnTab.setText("Tab");
        this.btnSemicolon = new Button((Composite)grpDelimiterUsedIn, 16);
        this.btnSemicolon.setLayoutData((Object)new GridData(4, 4, false, false, 1, 1));
        this.btnSemicolon.setText("Semi-colon");
        this.btnSpace = new Button((Composite)grpDelimiterUsedIn, 16);
        this.btnSpace.setLayoutData((Object)new GridData(4, 4, false, false, 1, 1));
        this.btnSpace.setText("Space");
        return container;
    }

    protected void okPressed() {
        if (this.btnSemicolon.getSelection()) {
            this.chosenDelimiter = ";";
        } else if (this.btnComma.getSelection()) {
            this.chosenDelimiter = ",";
        } else if (this.btnTab.getSelection()) {
            this.chosenDelimiter = "\t";
        } else if (this.btnSpace.getSelection()) {
            this.chosenDelimiter = " ";
        } else {
            this.close();
        }
        this.close();
    }

    protected void createButtonsForButtonBar(Composite parent) {
        this.createButton(parent, 0, IDialogConstants.OK_LABEL, true);
        this.createButton(parent, 1, IDialogConstants.CANCEL_LABEL, false);
    }

    protected Point getInitialSize() {
        return new Point(430, 319);
    }

    protected boolean isResizable() {
        return true;
    }
}

