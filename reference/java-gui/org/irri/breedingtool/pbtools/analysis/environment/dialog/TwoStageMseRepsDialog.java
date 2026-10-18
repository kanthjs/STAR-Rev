/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.jface.dialogs.Dialog
 *  org.eclipse.jface.dialogs.IDialogConstants
 *  org.eclipse.jface.dialogs.MessageDialog
 *  org.eclipse.swt.custom.TableEditor
 *  org.eclipse.swt.graphics.Point
 *  org.eclipse.swt.layout.GridData
 *  org.eclipse.swt.widgets.Composite
 *  org.eclipse.swt.widgets.Control
 *  org.eclipse.swt.widgets.Event
 *  org.eclipse.swt.widgets.Label
 *  org.eclipse.swt.widgets.Listener
 *  org.eclipse.swt.widgets.Shell
 *  org.eclipse.swt.widgets.Table
 *  org.eclipse.swt.widgets.TableColumn
 *  org.eclipse.swt.widgets.TableItem
 *  org.eclipse.swt.widgets.Text
 */
package org.irri.breedingtool.pbtools.analysis.environment.dialog;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.irri.breedingtool.manager.impl.DataManipulationManager;

public class TwoStageMseRepsDialog
extends Dialog {
    private Table table;
    private String indexFactorName;
    private String[] reshapeVariableNames;
    private String targetVariable;
    private String[] variableLevels;
    private DataManipulationManager dataManipulationManager;
    private String[] mseValues;
    private String[] repsValues;

    public TwoStageMseRepsDialog(Shell parentShell, String[] reshapeVariableNames) {
        super(parentShell);
        this.reshapeVariableNames = reshapeVariableNames;
        this.dataManipulationManager = new DataManipulationManager();
    }

    protected Control createDialogArea(Composite parent) {
        parent.getShell().setText("AMMI, GGE and Stability Analyses");
        Composite container = (Composite)super.createDialogArea(parent);
        Label lblTheVariablesYou = new Label(container, 0);
        lblTheVariablesYou.setText("The variables you specified were incomplete.");
        Label lblToPerformAmmi = new Label(container, 64);
        GridData gd_lblToPerformAmmi = new GridData(16384, 0x1000000, true, false, 1, 1);
        gd_lblToPerformAmmi.heightHint = 44;
        lblToPerformAmmi.setLayoutData((Object)gd_lblToPerformAmmi);
        lblToPerformAmmi.setText("To perform AMMI, GGE and Stability Analyses only, please specify the no. of Reps and MSE for each response variable.");
        this.table = new Table(container, 67584);
        this.table.setLayoutData((Object)new GridData(4, 4, true, true, 1, 1));
        this.table.setHeaderVisible(true);
        this.table.setLinesVisible(true);
        TableColumn targetVarNameCol = new TableColumn(this.table, 0);
        targetVarNameCol.setWidth(124);
        targetVarNameCol.setText("Response Variable");
        TableColumn mseCol = new TableColumn(this.table, 0);
        mseCol.setWidth(155);
        mseCol.setText("MSE:");
        TableColumn repsCol = new TableColumn(this.table, 0);
        repsCol.setWidth(155);
        repsCol.setText("No. of Reps:");
        String[] stringArray = this.reshapeVariableNames;
        int n = this.reshapeVariableNames.length;
        int n2 = 0;
        while (n2 < n) {
            String s = stringArray[n2];
            TableItem tableItem = new TableItem(this.table, 0x1000000);
            tableItem.setText(0, s);
            TableEditor editor = new TableEditor(this.table);
            TableEditor editor2 = new TableEditor(this.table);
            Text varLevel = new Text((Composite)this.table, 0);
            varLevel.setText(Integer.toString(0));
            Listener textListener = new Listener(){

                public void handleEvent(Event e) {
                    switch (e.type) {
                        case 16: {
                            tableItem.setText(1, varLevel.getText());
                        }
                    }
                }
            };
            varLevel.addListener(16, textListener);
            varLevel.addListener(31, textListener);
            varLevel.addListener(1, textListener);
            Text varLeve2 = new Text((Composite)this.table, 0);
            varLeve2.setText(Integer.toString(1));
            Listener textListener2 = new Listener(){

                public void handleEvent(Event e) {
                    switch (e.type) {
                        case 16: {
                            tableItem.setText(1, varLeve2.getText());
                        }
                    }
                }
            };
            varLeve2.addListener(16, textListener2);
            varLeve2.addListener(31, textListener2);
            varLeve2.addListener(1, textListener2);
            editor.grabHorizontal = true;
            editor2.grabHorizontal = true;
            editor.setEditor((Control)varLevel, tableItem, 1);
            editor2.setEditor((Control)varLeve2, tableItem, 2);
            tableItem.setData("mse", (Object)varLevel);
            tableItem.setData("reps", (Object)varLeve2);
            ++n2;
        }
        return container;
    }

    protected void okPressed() {
        this.variableLevels = this.getCurrentLevels();
        try {
            if (this.variableLevels.length == this.reshapeVariableNames.length) {
                this.close();
            }
        }
        catch (Exception exception) {}
    }

    public String[] getCurrentLevels() {
        this.mseValues = new String[this.reshapeVariableNames.length];
        this.repsValues = new String[this.reshapeVariableNames.length];
        String[] empty = null;
        int ctr = 0;
        String[] stringArray = this.reshapeVariableNames;
        int n = this.reshapeVariableNames.length;
        int n2 = 0;
        while (n2 < n) {
            String s = stringArray[n2];
            TableItem tableItem = this.table.getItem(ctr);
            Text mseText = (Text)tableItem.getData("mse");
            Text repsText = (Text)tableItem.getData("reps");
            System.out.println(String.valueOf(tableItem.getText(1)) + "is the value of table Item index 1");
            if (mseText.getText().equals("")) {
                MessageDialog.openWarning((Shell)this.getShell(), (String)"Invalid Input", (String)("Please indicate an MSE for " + s + "."));
                return empty;
            }
            if (repsText.getText().equals("")) {
                MessageDialog.openWarning((Shell)this.getShell(), (String)"Invalid Input", (String)("Please indicate the no. of reps for " + s + "."));
                return empty;
            }
            if (this.checkLevelsNameIfNotNumeric(mseText.getText())) {
                MessageDialog.openWarning((Shell)this.getShell(), (String)("Invalid Input at " + s), (String)"MSE should have a positive numeric value.");
                return empty;
            }
            if (this.checkLevelsNameIfNotNumeric(repsText.getText())) {
                MessageDialog.openWarning((Shell)this.getShell(), (String)("Invalid Input at " + s), (String)"The no. of reps should have a positive numeric value.");
                return empty;
            }
            this.mseValues[ctr] = mseText.getText();
            this.repsValues[ctr] = repsText.getText();
            ++ctr;
            ++n2;
        }
        return this.mseValues;
    }

    public String[] getLevels() {
        return this.variableLevels;
    }

    public boolean checkLevelsNameIfNotNumeric(String value) {
        try {
            Double d = Double.parseDouble(value);
            return d < 0.0;
        }
        catch (Exception exception) {
            return true;
        }
    }

    protected void createButtonsForButtonBar(Composite parent) {
        this.createButton(parent, 0, IDialogConstants.OK_LABEL, true);
        this.createButton(parent, 1, "Back", false);
    }

    protected Point getInitialSize() {
        return new Point(470, 298);
    }

    public String[] getMseValues() {
        return this.mseValues;
    }

    public String[] getRepsValues() {
        return this.repsValues;
    }
}

