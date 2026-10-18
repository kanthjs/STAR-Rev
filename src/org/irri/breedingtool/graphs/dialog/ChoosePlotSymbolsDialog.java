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

public class ChoosePlotSymbolsDialog
extends Dialog {
    private String chosenSymbol = "";
    private Shell parentShell;
    private Button[] symbolButtons = new Button[19];
    private int defaultSelected = 0;

    public ChoosePlotSymbolsDialog(Shell parentShell) {
        super(parentShell);
        this.parentShell = parentShell;
    }

    public ChoosePlotSymbolsDialog(Shell parentShell, int defaultSelected) {
        super(parentShell);
        this.parentShell = parentShell;
        this.defaultSelected = defaultSelected;
    }

    public String getChosenSymbol() {
        return this.chosenSymbol;
    }

    public void setDefaultSelected(int sel) {
        this.defaultSelected = sel;
    }

    protected Control createDialogArea(Composite parent) {
        parent.getShell().setText("Specify plot symbol to be used.");
        Composite container = (Composite)super.createDialogArea(parent);
        Group grpDelimiterUsedIn = new Group(container, 0);
        grpDelimiterUsedIn.setText("Plot Symbols:");
        grpDelimiterUsedIn.setLayout((Layout)new GridLayout(9, false));
        GridData gd_grpDelimiterUsedIn = new GridData(4, 4, true, true, 1, 1);
        gd_grpDelimiterUsedIn.heightHint = 139;
        gd_grpDelimiterUsedIn.widthHint = 235;
        grpDelimiterUsedIn.setLayoutData((Object)gd_grpDelimiterUsedIn);
        Button button = new Button((Composite)grpDelimiterUsedIn, 16);
        button.setImage(ResourceManager.getPluginImage("Star", "icons/plotsymbol0.png"));
        new Label((Composite)grpDelimiterUsedIn, 0);
        this.symbolButtons[0] = button;
        Button button_0 = new Button((Composite)grpDelimiterUsedIn, 16);
        button_0.setSelection(true);
        button_0.setImage(ResourceManager.getPluginImage("Star", "icons/plotsymbol1.png"));
        new Label((Composite)grpDelimiterUsedIn, 0);
        this.symbolButtons[1] = button_0;
        Button button_1 = new Button((Composite)grpDelimiterUsedIn, 16);
        button_1.setImage(ResourceManager.getPluginImage("Star", "icons/plotsymbol2.png"));
        new Label((Composite)grpDelimiterUsedIn, 0);
        this.symbolButtons[2] = button_1;
        Button button_2 = new Button((Composite)grpDelimiterUsedIn, 16);
        button_2.setImage(ResourceManager.getPluginImage("Star", "icons/plotsymbol3.png"));
        new Label((Composite)grpDelimiterUsedIn, 0);
        this.symbolButtons[3] = button_2;
        Button button_3 = new Button((Composite)grpDelimiterUsedIn, 16);
        button_3.setImage(ResourceManager.getPluginImage("Star", "icons/plotsymbol4.png"));
        this.symbolButtons[4] = button_3;
        new Label((Composite)grpDelimiterUsedIn, 0);
        new Label((Composite)grpDelimiterUsedIn, 0);
        new Label((Composite)grpDelimiterUsedIn, 0);
        new Label((Composite)grpDelimiterUsedIn, 0);
        new Label((Composite)grpDelimiterUsedIn, 0);
        new Label((Composite)grpDelimiterUsedIn, 0);
        new Label((Composite)grpDelimiterUsedIn, 0);
        new Label((Composite)grpDelimiterUsedIn, 0);
        new Label((Composite)grpDelimiterUsedIn, 0);
        Button button_4 = new Button((Composite)grpDelimiterUsedIn, 16);
        button_4.setImage(ResourceManager.getPluginImage("Star", "icons/plotsymbol5.png"));
        new Label((Composite)grpDelimiterUsedIn, 0);
        this.symbolButtons[5] = button_4;
        Button button_5 = new Button((Composite)grpDelimiterUsedIn, 16);
        button_5.setImage(ResourceManager.getPluginImage("Star", "icons/plotsymbol6.png"));
        new Label((Composite)grpDelimiterUsedIn, 0);
        this.symbolButtons[6] = button_5;
        Button button_6 = new Button((Composite)grpDelimiterUsedIn, 16);
        button_6.setImage(ResourceManager.getPluginImage("Star", "icons/plotsymbol7.png"));
        new Label((Composite)grpDelimiterUsedIn, 0);
        this.symbolButtons[7] = button_6;
        Button button_7 = new Button((Composite)grpDelimiterUsedIn, 16);
        button_7.setImage(ResourceManager.getPluginImage("Star", "icons/plotsymbol8.png"));
        new Label((Composite)grpDelimiterUsedIn, 0);
        this.symbolButtons[8] = button_7;
        Button button_8 = new Button((Composite)grpDelimiterUsedIn, 16);
        button_8.setImage(ResourceManager.getPluginImage("Star", "icons/plotsymbol9.png"));
        this.symbolButtons[9] = button_8;
        new Label((Composite)grpDelimiterUsedIn, 0);
        new Label((Composite)grpDelimiterUsedIn, 0);
        new Label((Composite)grpDelimiterUsedIn, 0);
        new Label((Composite)grpDelimiterUsedIn, 0);
        new Label((Composite)grpDelimiterUsedIn, 0);
        new Label((Composite)grpDelimiterUsedIn, 0);
        new Label((Composite)grpDelimiterUsedIn, 0);
        new Label((Composite)grpDelimiterUsedIn, 0);
        new Label((Composite)grpDelimiterUsedIn, 0);
        Button button_9 = new Button((Composite)grpDelimiterUsedIn, 16);
        button_9.setImage(ResourceManager.getPluginImage("Star", "icons/plotsymbol10.png"));
        new Label((Composite)grpDelimiterUsedIn, 0);
        this.symbolButtons[10] = button_9;
        Button button_10 = new Button((Composite)grpDelimiterUsedIn, 16);
        button_10.setImage(ResourceManager.getPluginImage("Star", "icons/plotsymbol11.png"));
        new Label((Composite)grpDelimiterUsedIn, 0);
        this.symbolButtons[11] = button_10;
        Button button_11 = new Button((Composite)grpDelimiterUsedIn, 16);
        button_11.setImage(ResourceManager.getPluginImage("Star", "icons/plotsymbol12.png"));
        new Label((Composite)grpDelimiterUsedIn, 0);
        this.symbolButtons[12] = button_11;
        Button button_12 = new Button((Composite)grpDelimiterUsedIn, 16);
        button_12.setImage(ResourceManager.getPluginImage("Star", "icons/plotsymbol13.png"));
        new Label((Composite)grpDelimiterUsedIn, 0);
        this.symbolButtons[13] = button_12;
        Button button_13 = new Button((Composite)grpDelimiterUsedIn, 16);
        button_13.setImage(ResourceManager.getPluginImage("Star", "icons/plotsymbol14.png"));
        this.symbolButtons[14] = button_13;
        new Label((Composite)grpDelimiterUsedIn, 0);
        new Label((Composite)grpDelimiterUsedIn, 0);
        new Label((Composite)grpDelimiterUsedIn, 0);
        new Label((Composite)grpDelimiterUsedIn, 0);
        new Label((Composite)grpDelimiterUsedIn, 0);
        new Label((Composite)grpDelimiterUsedIn, 0);
        new Label((Composite)grpDelimiterUsedIn, 0);
        new Label((Composite)grpDelimiterUsedIn, 0);
        new Label((Composite)grpDelimiterUsedIn, 0);
        Button button_14 = new Button((Composite)grpDelimiterUsedIn, 16);
        button_14.setImage(ResourceManager.getPluginImage("Star", "icons/plotsymbol15.png"));
        new Label((Composite)grpDelimiterUsedIn, 0);
        this.symbolButtons[15] = button_14;
        Button button_15 = new Button((Composite)grpDelimiterUsedIn, 16);
        button_15.setImage(ResourceManager.getPluginImage("Star", "icons/plotsymbol16.png"));
        new Label((Composite)grpDelimiterUsedIn, 0);
        this.symbolButtons[16] = button_15;
        Button button_16 = new Button((Composite)grpDelimiterUsedIn, 16);
        button_16.setImage(ResourceManager.getPluginImage("Star", "icons/plotsymbol17.png"));
        new Label((Composite)grpDelimiterUsedIn, 0);
        this.symbolButtons[17] = button_16;
        Button button_17 = new Button((Composite)grpDelimiterUsedIn, 16);
        button_17.setImage(ResourceManager.getPluginImage("Star", "icons/plotsymbol18.png"));
        this.symbolButtons[18] = button_17;
        new Label((Composite)grpDelimiterUsedIn, 0);
        new Label((Composite)grpDelimiterUsedIn, 0);
        this.setSelected(this.defaultSelected);
        return container;
    }

    private void setSelected(int selected) {
        int i = 0;
        while (i < this.symbolButtons.length) {
            if (selected == i) {
                this.symbolButtons[i].setSelection(true);
            } else {
                this.symbolButtons[i].setSelection(false);
            }
            ++i;
        }
    }

    protected void okPressed() {
        int i = 0;
        while (i < 20) {
            if (this.symbolButtons[i].getSelection()) {
                this.chosenSymbol = Integer.toString(i);
                break;
            }
            ++i;
        }
        this.close();
    }

    protected void createButtonsForButtonBar(Composite parent) {
        this.createButton(parent, 0, IDialogConstants.OK_LABEL, true);
        this.createButton(parent, 1, IDialogConstants.CANCEL_LABEL, false);
    }

    protected Point getInitialSize() {
        return new Point(455, 321);
    }

    protected boolean isResizable() {
        return true;
    }
}

