/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.jface.dialogs.Dialog
 *  org.eclipse.jface.dialogs.IDialogConstants
 *  org.eclipse.jface.dialogs.MessageDialog
 *  org.eclipse.swt.custom.CCombo
 *  org.eclipse.swt.custom.TableEditor
 *  org.eclipse.swt.events.ModifyEvent
 *  org.eclipse.swt.events.ModifyListener
 *  org.eclipse.swt.events.SelectionAdapter
 *  org.eclipse.swt.events.SelectionEvent
 *  org.eclipse.swt.events.SelectionListener
 *  org.eclipse.swt.graphics.Point
 *  org.eclipse.swt.layout.GridData
 *  org.eclipse.swt.layout.GridLayout
 *  org.eclipse.swt.widgets.Composite
 *  org.eclipse.swt.widgets.Control
 *  org.eclipse.swt.widgets.Display
 *  org.eclipse.swt.widgets.Event
 *  org.eclipse.swt.widgets.Listener
 *  org.eclipse.swt.widgets.Shell
 *  org.eclipse.swt.widgets.Table
 *  org.eclipse.swt.widgets.TableColumn
 *  org.eclipse.swt.widgets.TableItem
 *  org.eclipse.swt.widgets.Text
 *  org.eclipse.swt.widgets.ToolItem
 */
package org.irri.breedingtool.datamanipulation.dialog;

import java.util.ArrayList;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.wb.swt.SWTResourceManager;
import org.irri.breedingtool.application.handler.PartStackHandler;
import org.irri.breedingtool.application.model.ProjectExplorerTreeNodeModel;
import org.irri.breedingtool.manager.impl.DataManipulationManager;
import org.irri.breedingtool.utility.WindowDialogControlUtil;

public class EditVariableInfoDialog
extends Dialog {
    protected static final int FACTOR = 1;
    private ArrayList<String> varInfo;
    private ArrayList<String> newVarInfo;
    private ProjectExplorerTreeNodeModel newFileModel;
    private Table table;
    private boolean editedVarInfo = false;
    private boolean editedVariableNames = false;
    private DataManipulationManager dataManipulationManager = new DataManipulationManager();

    public EditVariableInfoDialog(Shell parentShell, ArrayList<String> varInfo, ProjectExplorerTreeNodeModel fileModel) {
        super(parentShell);
        this.varInfo = this.dataManipulationManager.getVarInfoFromFile(fileModel.getProjectFile().getAbsolutePath());
        this.newVarInfo = this.varInfo;
        this.newFileModel = fileModel;
    }

    protected Control createDialogArea(Composite parent) {
        parent.getShell().setText("Edit Variable Information: " + this.dataManipulationManager.getDisplayFileName(this.newFileModel.getProjectFile().getAbsolutePath()));
        Composite container = (Composite)super.createDialogArea(parent);
        GridLayout cfr_ignored_0 = (GridLayout)container.getLayout();
        this.table = new Table(container, 67586);
        this.table.setBackground(SWTResourceManager.getColor(1));
        GridData gd_table = new GridData(4, 4, true, true, 1, 1);
        gd_table.widthHint = 204;
        this.table.setLayoutData((Object)gd_table);
        this.table.setHeaderVisible(true);
        this.table.setLinesVisible(true);
        TableColumn tblclmnNewColumn = new TableColumn(this.table, 0);
        tblclmnNewColumn.setWidth(210);
        tblclmnNewColumn.setText("Variable Name");
        TableColumn tblclmnNewColumn_1 = new TableColumn(this.table, 0);
        tblclmnNewColumn_1.setWidth(190);
        tblclmnNewColumn_1.setText("Variable Type");
        int ctr = 0;
        for (String s : this.varInfo) {
            String[] tmp = s.split(":");
            TableItem tableItem = new TableItem(this.table, 0x1000000);
            tableItem.setText(0, tmp[0]);
            TableEditor editor = new TableEditor(this.table);
            CCombo varTypeList = new CCombo((Composite)this.table, 8);
            varTypeList.setBackground(Display.getCurrent().getSystemColor(1));
            editor.grabHorizontal = true;
            editor.setEditor((Control)varTypeList, tableItem, 1);
            varTypeList.setData("varIndex", (Object)Integer.toString(ctr++));
            varTypeList.setData("varType", (Object)tmp[1]);
            varTypeList.setData("varName", (Object)tmp[0]);
            varTypeList.add("Numeric");
            varTypeList.add("Factor");
            if (tmp[1].toLowerCase().equals("numeric")) {
                varTypeList.select(0);
            } else {
                varTypeList.select(1);
            }
            varTypeList.addSelectionListener((SelectionListener)new SelectionAdapter(){

                public void widgetSelected(SelectionEvent e) {
                    CCombo selectedCombo = (CCombo)e.getSource();
                    int varIndex = Integer.parseInt((String)selectedCombo.getData("varIndex"));
                    String varType = selectedCombo.getText();
                    String varName = ((String)EditVariableInfoDialog.this.newVarInfo.get(varIndex)).split(":")[0];
                    if (varType.toLowerCase().equals("numeric")) {
                        String isNumeric = EditVariableInfoDialog.this.dataManipulationManager.isNumeric(EditVariableInfoDialog.this.newFileModel.getProjectFile().getAbsolutePath().replaceAll("\\\\", "/"), varName);
                        if (isNumeric.equals("TRUE")) {
                            EditVariableInfoDialog.this.newVarInfo.set(varIndex, String.valueOf(varName) + ":" + varType);
                            EditVariableInfoDialog.this.editedVarInfo = true;
                        } else {
                            MessageDialog.openWarning((Shell)EditVariableInfoDialog.this.getShell(), (String)"Invalid type Assignment", (String)("Sorry, " + varName + " cannot be assigned as a numeric variable."));
                            selectedCombo.select(1);
                        }
                    } else {
                        EditVariableInfoDialog.this.newVarInfo.set(varIndex, String.valueOf(varName) + ":" + varType);
                        EditVariableInfoDialog.this.editedVarInfo = true;
                    }
                }

                public void widgetDefaultSelected(SelectionEvent e) {
                }
            });
        }
        this.table.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                TableItem item;
                final TableEditor editor = new TableEditor(EditVariableInfoDialog.this.table);
                final int varIndex = EditVariableInfoDialog.this.table.getSelectionIndex();
                editor.horizontalAlignment = 16384;
                editor.grabHorizontal = true;
                editor.minimumWidth = 50;
                Control oldEditor = editor.getEditor();
                if (oldEditor != null) {
                    oldEditor.dispose();
                }
                if ((item = (TableItem)e.item) == null) {
                    return;
                }
                Text newEditor = new Text((Composite)EditVariableInfoDialog.this.table, 0);
                newEditor.setText(item.getText(0));
                final String originalText = item.getText(0);
                newEditor.addModifyListener(new ModifyListener(){

                    public void modifyText(ModifyEvent e) {
                        final Text text = (Text)editor.getEditor();
                        text.addListener(16, new Listener(){

                            public void handleEvent(Event e) {
                                try {
                                    if (!EditVariableInfoDialog.this.dataManipulationManager.variableNameInputValidation(text.getText()).equals("true")) {
                                        MessageDialog.openWarning((Shell)EditVariableInfoDialog.this.getShell(), (String)"Invalid Input", (String)EditVariableInfoDialog.this.dataManipulationManager.variableNameInputValidation(text.getText()));
                                        editor.getItem().setText(0, originalText);
                                        text.dispose();
                                    } else if (text.getText().isEmpty()) {
                                        MessageDialog.openInformation((Shell)EditVariableInfoDialog.this.getShell(), (String)"Invalid Input", (String)"Sorry, you can't leave a variable name empty.");
                                        editor.getItem().setText(0, originalText);
                                        text.dispose();
                                    } else if (!EditVariableInfoDialog.this.dataManipulationManager.checkColumnNameIfUsed(text.getText(), EditVariableInfoDialog.this.dataManipulationManager.getListItems(EditVariableInfoDialog.this.newVarInfo))) {
                                        System.out.println(text.getText());
                                        String varName = text.getText();
                                        String varType = ((String)EditVariableInfoDialog.this.newVarInfo.get(varIndex)).split(":")[1];
                                        System.out.println("varType: " + varType);
                                        EditVariableInfoDialog.this.newVarInfo.set(varIndex, String.valueOf(varName) + ":" + varType);
                                        EditVariableInfoDialog.this.editedVariableNames = true;
                                        editor.getItem().setText(0, text.getText());
                                        System.out.println("SET VAR:" + Integer.toString(varIndex) + " to " + varName + ":" + varType);
                                        text.dispose();
                                    } else {
                                        MessageDialog.openInformation((Shell)EditVariableInfoDialog.this.getShell(), (String)"Invalid Input", (String)("Sorry, the variable '" + text.getText() + "' already exist."));
                                        editor.getItem().setText(0, originalText);
                                        text.dispose();
                                    }
                                }
                                catch (Exception exception) {}
                            }
                        });
                    }
                });
                newEditor.selectAll();
                newEditor.setFocus();
                editor.setEditor((Control)newEditor, item, 0);
            }
        });
        System.out.println(Integer.toString(ctr));
        return container;
    }

    protected void okPressed() {
        if (this.editedVarInfo || this.editedVariableNames) {
            DataManipulationManager.saveVarTempFile(this.newFileModel.getProjectFile().getAbsolutePath(), this.newVarInfo);
            this.varInfo = this.newVarInfo;
            if (this.editedVariableNames) {
                Table table = DataManipulationManager.getActiveTable(this.newFileModel.getProjectFile().getAbsolutePath());
                String[] newColumnName = this.dataManipulationManager.getListItems(this.varInfo);
                int i = 1;
                while (i < table.getColumnCount()) {
                    TableColumn column = table.getColumn(i);
                    column.setText(newColumnName[i - 1]);
                    ++i;
                }
                this.dataManipulationManager.saveFileChanges(this.newFileModel.getProjectFile(), table, ",");
                PartStackHandler.setPartModified((String)table.getData("ID"));
                ((ToolItem)table.getData("saveBtn")).setEnabled(true);
                MessageDialog.openInformation((Shell)this.getShell(), (String)"Edit Variable Information", (String)"Your changes have been saved.");
            }
            WindowDialogControlUtil.closeAllWindowDialogOfFile(this.newFileModel.getProjectFile().getAbsolutePath());
        } else {
            MessageDialog.openInformation((Shell)this.getShell(), (String)"Exit", (String)"No changes were made in the variable information.");
        }
        this.close();
    }

    public ArrayList<String> getVarInfo() {
        return this.varInfo;
    }

    protected void createButtonsForButtonBar(Composite parent) {
        this.createButton(parent, 0, IDialogConstants.OK_LABEL, true);
        this.createButton(parent, 1, IDialogConstants.CANCEL_LABEL, false);
    }

    protected Point getInitialSize() {
        return new Point(496, 296);
    }

    protected boolean isResizable() {
        return true;
    }
}

