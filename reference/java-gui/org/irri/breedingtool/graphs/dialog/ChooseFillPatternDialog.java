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

public class ChooseFillPatternDialog
extends Dialog {
    private String chosenPattern = "";
    private Button btnForwardDiagonal;
    private Button btnHorizontal;
    private Button btnBackwardDiagonal;
    private Button btnVertical;
    private Shell parentShell;
    private Button btnPlainTxt;
    private boolean importCalled;
    private String varName;
    private Button btnSolid;
    private Label label;
    private Label label_1;
    private Label label_2;
    private Label label_3;
    private Label label_4;
    private Label label_5;
    private int angle;
    private int lineDensity;

    public ChooseFillPatternDialog(Shell parentShell, String varName) {
        super(parentShell);
        this.parentShell = parentShell;
        this.importCalled = this.importCalled;
        this.varName = varName;
    }

    public String getChosenPattern() {
        return this.chosenPattern;
    }

    public int getPatternAngle() {
        return this.angle;
    }

    public int getLineDensity() {
        return this.lineDensity;
    }

    protected Control createDialogArea(Composite parent) {
        parent.getShell().setText("Specify fill pattern to be used.");
        Composite container = (Composite)super.createDialogArea(parent);
        Group grpDelimiterUsedIn = new Group(container, 0);
        grpDelimiterUsedIn.setText("Fill Paterns");
        grpDelimiterUsedIn.setLayout((Layout)new GridLayout(2, false));
        GridData gd_grpDelimiterUsedIn = new GridData(4, 4, true, true, 1, 1);
        gd_grpDelimiterUsedIn.heightHint = 139;
        gd_grpDelimiterUsedIn.widthHint = 235;
        grpDelimiterUsedIn.setLayoutData((Object)gd_grpDelimiterUsedIn);
        this.btnSolid = new Button((Composite)grpDelimiterUsedIn, 16);
        this.btnSolid.setSelection(true);
        this.btnSolid.setText("Solid");
        this.label = new Label((Composite)grpDelimiterUsedIn, 0);
        this.label.setImage(ResourceManager.getPluginImage("Star", "icons/solid.png"));
        new Label((Composite)grpDelimiterUsedIn, 0);
        new Label((Composite)grpDelimiterUsedIn, 0);
        this.btnVertical = new Button((Composite)grpDelimiterUsedIn, 16);
        this.btnVertical.setLayoutData((Object)new GridData(4, 4, false, false, 1, 1));
        this.btnVertical.setText("Vertical");
        this.label_1 = new Label((Composite)grpDelimiterUsedIn, 0);
        this.label_1.setImage(ResourceManager.getPluginImage("Star", "icons/vertical.png"));
        new Label((Composite)grpDelimiterUsedIn, 0);
        this.label_2 = new Label((Composite)grpDelimiterUsedIn, 0);
        this.btnHorizontal = new Button((Composite)grpDelimiterUsedIn, 16);
        this.btnHorizontal.setLayoutData((Object)new GridData(4, 4, false, false, 1, 1));
        this.btnHorizontal.setText("Horizontal");
        this.label_5 = new Label((Composite)grpDelimiterUsedIn, 0);
        this.label_5.setImage(ResourceManager.getPluginImage("Star", "icons/horizontal.png"));
        new Label((Composite)grpDelimiterUsedIn, 0);
        new Label((Composite)grpDelimiterUsedIn, 0);
        this.btnForwardDiagonal = new Button((Composite)grpDelimiterUsedIn, 16);
        this.btnForwardDiagonal.setLayoutData((Object)new GridData(4, 4, false, false, 1, 1));
        this.btnForwardDiagonal.setText("Forward Diagonal");
        this.label_3 = new Label((Composite)grpDelimiterUsedIn, 0);
        this.label_3.setImage(ResourceManager.getPluginImage("Star", "icons/forward_diagonal.png"));
        new Label((Composite)grpDelimiterUsedIn, 0);
        new Label((Composite)grpDelimiterUsedIn, 0);
        this.btnBackwardDiagonal = new Button((Composite)grpDelimiterUsedIn, 16);
        this.btnBackwardDiagonal.setLayoutData((Object)new GridData(4, 4, false, false, 1, 1));
        this.btnBackwardDiagonal.setText("Backward Diagonal");
        this.label_4 = new Label((Composite)grpDelimiterUsedIn, 0);
        this.label_4.setImage(ResourceManager.getPluginImage("Star", "icons/backward_diagonal.png"));
        return container;
    }

    protected void okPressed() {
        this.lineDensity = 10;
        if (this.btnForwardDiagonal.getSelection()) {
            this.chosenPattern = "forward_diagonal";
            this.angle = 135;
        } else if (this.btnHorizontal.getSelection()) {
            this.angle = 0;
            this.chosenPattern = "horizontal";
        } else if (this.btnVertical.getSelection()) {
            this.angle = 90;
            this.chosenPattern = "vertical";
        } else if (this.btnBackwardDiagonal.getSelection()) {
            this.angle = 45;
            this.chosenPattern = "backward_diagonal";
        } else {
            this.angle = 0;
            this.lineDensity = 100;
            this.chosenPattern = "solid";
        }
        this.close();
    }

    protected void createButtonsForButtonBar(Composite parent) {
        this.createButton(parent, 0, IDialogConstants.OK_LABEL, true);
        this.createButton(parent, 1, IDialogConstants.CANCEL_LABEL, false);
    }

    protected Point getInitialSize() {
        return new Point(458, 334);
    }

    protected boolean isResizable() {
        return true;
    }
}

