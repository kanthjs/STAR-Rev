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
 *  org.eclipse.swt.widgets.Label
 *  org.eclipse.swt.widgets.Layout
 *  org.eclipse.swt.widgets.Shell
 */
package org.irri.breedingtool.graphs.dialog;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.ResourceManager;

public class ChooseLineTypesDialog
extends Dialog {
    private String chosenPattern = "";
    private Button btn4;
    private Button btn3;
    private Button btn5;
    private Button btn2;
    private Shell parentShell;
    private Button btnPlainTxt;
    private Button btn1;
    private Label label;
    private Label label_1;
    private Label label_3;
    private Label label_4;
    private Label label_5;
    private Button btn6;
    private Label label_6;

    public ChooseLineTypesDialog(Shell parentShell) {
        super(parentShell);
        this.parentShell = parentShell;
    }

    public String getChosenPattern() {
        return this.chosenPattern;
    }

    protected Control createDialogArea(Composite parent) {
        parent.getShell().setText("Specify line pattern to be used.");
        Composite container = (Composite)super.createDialogArea(parent);
        Group grpLineUsedIn = new Group(container, 0);
        grpLineUsedIn.setText("Line Patterns");
        grpLineUsedIn.setLayout((Layout)new GridLayout(2, false));
        GridData gd_grpLineUsedIn = new GridData(4, 4, true, true, 1, 1);
        gd_grpLineUsedIn.heightHint = 139;
        gd_grpLineUsedIn.widthHint = 235;
        grpLineUsedIn.setLayoutData((Object)gd_grpLineUsedIn);
        this.btn1 = new Button((Composite)grpLineUsedIn, 16);
        this.btn1.setSelection(true);
        this.label = new Label((Composite)grpLineUsedIn, 0);
        this.label.setImage(ResourceManager.getPluginImage("Star", "icons/line1.png"));
        new Label((Composite)grpLineUsedIn, 0);
        new Label((Composite)grpLineUsedIn, 0);
        this.btn2 = new Button((Composite)grpLineUsedIn, 16);
        this.btn2.setLayoutData((Object)new GridData(4, 4, false, false, 1, 1));
        this.label_1 = new Label((Composite)grpLineUsedIn, 0);
        this.label_1.setImage(ResourceManager.getPluginImage("Star", "icons/line2.png"));
        new Label((Composite)grpLineUsedIn, 0);
        new Label((Composite)grpLineUsedIn, 0);
        this.btn3 = new Button((Composite)grpLineUsedIn, 16);
        this.btn3.setLayoutData((Object)new GridData(4, 4, false, false, 1, 1));
        this.label_5 = new Label((Composite)grpLineUsedIn, 0);
        this.label_5.setImage(ResourceManager.getPluginImage("Star", "icons/line3.png"));
        new Label((Composite)grpLineUsedIn, 0);
        new Label((Composite)grpLineUsedIn, 0);
        this.btn4 = new Button((Composite)grpLineUsedIn, 16);
        this.btn4.setLayoutData((Object)new GridData(4, 4, false, false, 1, 1));
        this.label_3 = new Label((Composite)grpLineUsedIn, 0);
        this.label_3.setImage(ResourceManager.getPluginImage("Star", "icons/line4.png"));
        new Label((Composite)grpLineUsedIn, 0);
        new Label((Composite)grpLineUsedIn, 0);
        this.btn5 = new Button((Composite)grpLineUsedIn, 16);
        this.btn5.setLayoutData((Object)new GridData(4, 4, false, false, 1, 1));
        this.label_4 = new Label((Composite)grpLineUsedIn, 0);
        this.label_4.setImage(ResourceManager.getPluginImage("Star", "icons/line5.png"));
        new Label((Composite)grpLineUsedIn, 0);
        new Label((Composite)grpLineUsedIn, 0);
        this.btn6 = new Button((Composite)grpLineUsedIn, 16);
        this.label_6 = new Label((Composite)grpLineUsedIn, 0);
        this.label_6.setImage(ResourceManager.getPluginImage("Star", "icons/line6.png"));
        return container;
    }

    protected void okPressed() {
        if (this.btn1.getSelection()) {
            this.chosenPattern = "1";
        }
        if (this.btn2.getSelection()) {
            this.chosenPattern = "2";
        }
        if (this.btn3.getSelection()) {
            this.chosenPattern = "3";
        }
        if (this.btn4.getSelection()) {
            this.chosenPattern = "4";
        }
        if (this.btn5.getSelection()) {
            this.chosenPattern = "5";
        }
        if (this.btn6.getSelection()) {
            this.chosenPattern = "6";
        }
        this.close();
    }

    protected void createButtonsForButtonBar(Composite parent) {
        this.createButton(parent, 0, IDialogConstants.OK_LABEL, true);
        this.createButton(parent, 1, IDialogConstants.CANCEL_LABEL, false);
    }

    protected Point getInitialSize() {
        return new Point(513, 408);
    }

    protected boolean isResizable() {
        return true;
    }
}

