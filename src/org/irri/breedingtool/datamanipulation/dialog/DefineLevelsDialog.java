/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.jface.dialogs.Dialog
 *  org.eclipse.jface.dialogs.IDialogConstants
 *  org.eclipse.jface.dialogs.MessageDialog
 *  org.eclipse.swt.custom.TableEditor
 *  org.eclipse.swt.events.SelectionAdapter
 *  org.eclipse.swt.events.SelectionEvent
 *  org.eclipse.swt.events.SelectionListener
 *  org.eclipse.swt.graphics.Point
 *  org.eclipse.swt.layout.GridData
 *  org.eclipse.swt.widgets.Button
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
package org.irri.breedingtool.datamanipulation.dialog;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
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

public class DefineLevelsDialog
extends Dialog {
    private Table table;
    private String indexFactorName;
    private String[] reshapeVariableNames;
    private String targetVariable;
    private String[] variableLevels;
    private DataManipulationManager dataManipulationManager;
    private Button btnUseDefaultLevels;

    public DefineLevelsDialog(Shell parentShell, String indexFactorName, String targetVariable, String[] reshapeVariableNames) {
        super(parentShell);
        this.targetVariable = targetVariable;
        this.indexFactorName = indexFactorName;
        this.reshapeVariableNames = reshapeVariableNames;
        this.dataManipulationManager = new DataManipulationManager();
    }

    protected Control createDialogArea(Composite parent) {
        parent.getShell().setText("Define Levels");
        Composite container = (Composite)super.createDialogArea(parent);
        Label lblDefineLevelsOf = new Label(container, 0);
        lblDefineLevelsOf.setText("Define Levels Of " + this.indexFactorName + ":");
        this.btnUseDefaultLevels = new Button(container, 32);
        this.btnUseDefaultLevels.setSelection(true);
        this.btnUseDefaultLevels.setText("Use Default Levels");
        this.table = new Table(container, 67584);
        this.table.setLayoutData((Object)new GridData(4, 4, true, true, 1, 1));
        this.table.setHeaderVisible(true);
        this.table.setLinesVisible(true);
        TableColumn targetVarNameCol = new TableColumn(this.table, 0);
        targetVarNameCol.setWidth(104);
        targetVarNameCol.setText("Default Levels");
        TableColumn levelsCol = new TableColumn(this.table, 0);
        levelsCol.setWidth(155);
        levelsCol.setText("New Levels");
        String[] stringArray = this.reshapeVariableNames;
        int n = this.reshapeVariableNames.length;
        int n2 = 0;
        while (n2 < n) {
            String s = stringArray[n2];
            TableItem tableItem = new TableItem(this.table, 0x1000000);
            tableItem.setText(0, s);
            TableEditor editor = new TableEditor(this.table);
            Text varLevel = new Text((Composite)this.table, 0);
            if (this.btnUseDefaultLevels.getSelection()) {
                varLevel.setText(s);
            }
            Listener textListener = new Listener(){

                public void handleEvent(Event e) {
                    switch (e.type) {
                        case 16: {
                            tableItem.setText(1, varLevel.getText());
                        }
                    }
                }
            };
            tableItem.setData("editor", (Object)editor);
            varLevel.addListener(16, textListener);
            varLevel.addListener(31, textListener);
            varLevel.addListener(1, textListener);
            editor.grabHorizontal = true;
            editor.setEditor((Control)varLevel, tableItem, 1);
            ++n2;
        }
        this.btnUseDefaultLevels.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                int ctr = 0;
                TableItem[] tableItemArray = DefineLevelsDialog.this.table.getItems();
                int n = tableItemArray.length;
                int n2 = 0;
                while (n2 < n) {
                    TableItem ti = tableItemArray[n2];
                    TableEditor editor = (TableEditor)ti.getData("editor");
                    if (DefineLevelsDialog.this.btnUseDefaultLevels.getSelection()) {
                        ((Text)editor.getEditor()).setText(DefineLevelsDialog.this.reshapeVariableNames[ctr]);
                    } else {
                        ((Text)editor.getEditor()).setText("");
                    }
                    ++ctr;
                    ++n2;
                }
            }
        });
        return container;
    }

    protected void okPressed() {
        this.variableLevels = this.getCurrentLevels();
        if (this.variableLevels.length == this.reshapeVariableNames.length) {
            this.close();
        }
    }

    public String[] getCurrentLevels() {
        String[] varLevels = new String[this.reshapeVariableNames.length];
        String[] empty = new String[]{"null"};
        int ctr = 0;
        String[] stringArray = this.reshapeVariableNames;
        int n = this.reshapeVariableNames.length;
        int n2 = 0;
        while (n2 < n) {
            String s = stringArray[n2];
            TableItem tableItem = this.table.getItem(ctr);
            if (tableItem.getText(1).equals("")) {
                MessageDialog.openWarning((Shell)this.getShell(), (String)"Invalid Input", (String)("Please indicate a level for " + s + "."));
                return empty;
            }
            if (this.checkLevelsNameIfUsed(tableItem.getText(1), varLevels, ctr)) {
                MessageDialog.openWarning((Shell)this.getShell(), (String)"Invalid Input", (String)"The name of the levels should be unique.");
                return empty;
            }
            if (!this.dataManipulationManager.variableNameInputValidation(tableItem.getText(1)).equals("true")) {
                MessageDialog.openWarning((Shell)this.getShell(), (String)"Invalid Input", (String)("'" + tableItem.getText(1) + "' is an invalid level name."));
                return empty;
            }
            varLevels[ctr] = tableItem.getText(1);
            ++ctr;
            ++n2;
        }
        return varLevels;
    }

    public String[] getLevels() {
        return this.variableLevels;
    }

    public boolean checkLevelsNameIfUsed(String value, String[] columnHeaders, int index) {
        int i = 0;
        while (i < columnHeaders.length) {
            if (value.equals(columnHeaders[i]) && index != i) {
                return true;
            }
            ++i;
        }
        return false;
    }

    protected void createButtonsForButtonBar(Composite parent) {
        this.createButton(parent, 0, IDialogConstants.OK_LABEL, true);
        this.createButton(parent, 1, "Back", false);
    }

    protected Point getInitialSize() {
        return new Point(461, 291);
    }
}

