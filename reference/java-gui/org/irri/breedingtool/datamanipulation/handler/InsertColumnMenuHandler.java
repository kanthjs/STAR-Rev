/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.inject.Inject
 *  org.eclipse.e4.core.di.annotations.Execute
 *  org.eclipse.e4.ui.model.application.MApplication
 *  org.eclipse.e4.ui.model.application.ui.MUIElement
 *  org.eclipse.e4.ui.model.application.ui.basic.MPartStack
 *  org.eclipse.e4.ui.model.application.ui.basic.MStackElement
 *  org.eclipse.e4.ui.workbench.modeling.EModelService
 *  org.eclipse.e4.ui.workbench.modeling.EPartService
 *  org.eclipse.jface.dialogs.InputDialog
 *  org.eclipse.jface.dialogs.MessageDialog
 *  org.eclipse.swt.SWTException
 *  org.eclipse.swt.widgets.Display
 *  org.eclipse.swt.widgets.Listener
 *  org.eclipse.swt.widgets.Shell
 *  org.eclipse.swt.widgets.Table
 *  org.eclipse.swt.widgets.TableColumn
 *  org.eclipse.swt.widgets.ToolItem
 */
package org.irri.breedingtool.datamanipulation.handler;

import java.io.File;
import javax.inject.Inject;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.MUIElement;
import org.eclipse.e4.ui.model.application.ui.basic.MPartStack;
import org.eclipse.e4.ui.model.application.ui.basic.MStackElement;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.ToolItem;
import org.irri.breedingtool.application.handler.PartStackHandler;
import org.irri.breedingtool.manager.impl.DataManipulationManager;
import org.irri.breedingtool.utility.WindowDialogControlUtil;

public class InsertColumnMenuHandler {
    @Inject
    private static EPartService service;
    @Inject
    private static MApplication application;
    @Inject
    private static EModelService modelService;
    static DataManipulationManager dataManipulationManager;

    static {
        dataManipulationManager = new DataManipulationManager();
    }

    @Execute
    public static void execute() {
        try {
            System.out.println("InsertColumnManuHandler");
            MPartStack stack = (MPartStack)modelService.find("ViewerStack", (MUIElement)application);
            MStackElement selected = (MStackElement)stack.getSelectedElement();
            String absoluteFilePath = selected.getElementId();
            File file = new File(absoluteFilePath);
            Table table = DataManipulationManager.getActiveTable(selected.getElementId());
            boolean selectedColumnExists = false;
            boolean colNameUsed = true;
            String displayMessage = "There's no selected column.\nDo you want to insert a new column after the last column?";
            int index = table.getColumnCount();
            String ifColUsed = "";
            TableColumn selectedColumn = (TableColumn)table.getData("selectedColumn");
            String[] columnHeaderNames = (String[])table.getData("columnHeaderNames");
            Listener columnHeaderListener = (Listener)table.getData("columnHeaderListener");
            try {
                if (selectedColumn.getImage() != null) {
                    selectedColumnExists = true;
                }
            }
            catch (NullPointerException nullPointerException) {
            }
            catch (SWTException sWTException) {}
            if (selectedColumnExists) {
                index = (Integer)selectedColumn.getData("index");
                displayMessage = "Insert Column before " + selectedColumn.getText() + "?";
            }
            if (MessageDialog.openQuestion((Shell)Display.getCurrent().getActiveShell(), (String)"Insert Column", (String)displayMessage)) {
                while (colNameUsed) {
                    InputDialog getcolNameDlg = new InputDialog(Display.getCurrent().getActiveShell(), "Insert Column", String.valueOf(ifColUsed) + "\nInsert New Column Name:", "NewColumn", null);
                    getcolNameDlg.open();
                    if (getcolNameDlg.getReturnCode() == 0) {
                        if (!dataManipulationManager.checkColumnNameIfUsed(getcolNameDlg.getValue(), columnHeaderNames)) {
                            ifColUsed = dataManipulationManager.variableNameInputValidation(getcolNameDlg.getValue());
                            if (!ifColUsed.equals("true")) continue;
                            colNameUsed = false;
                            dataManipulationManager.insertTableColumn(table, index, getcolNameDlg.getValue());
                            TableColumn col = table.getColumn(index);
                            col.addListener(13, columnHeaderListener);
                            dataManipulationManager.addNewVariableNameToTmpVarInfo(file.getAbsolutePath(), getcolNameDlg.getValue());
                            dataManipulationManager.saveFileChanges(file, table, DataManipulationManager.getActiveDataDelimiter(file.getAbsolutePath()));
                            columnHeaderNames = dataManipulationManager.updateColHeaderNames(table, (String[])table.getData("columnHeaderNames"));
                            PartStackHandler.setPartModified(absoluteFilePath);
                            WindowDialogControlUtil.closeAllWindowDialogOfFile(file.getAbsolutePath());
                            ((ToolItem)table.getData("saveBtn")).setEnabled(true);
                            continue;
                        }
                        ifColUsed = "Sorry, the column name \"" + getcolNameDlg.getValue() + "\" already exist.\n";
                        continue;
                    }
                    colNameUsed = false;
                }
            }
        }
        catch (NullPointerException nullPointerException) {
            MessageDialog.openError((Shell)Display.getCurrent().getActiveShell(), (String)"Error", (String)"The active tab in the Editor panel should contain a data file.");
        }
    }

    @Execute
    public static void execute(File file, Table table) {
        try {
            boolean selectedColumnExists = false;
            boolean colNameUsed = true;
            String displayMessage = "There's no selected column.\nDo you want to insert a new column after the last column?";
            int index = table.getColumnCount();
            String ifColUsed = "";
            TableColumn selectedColumn = (TableColumn)table.getData("selectedColumn");
            String[] columnHeaderNames = (String[])table.getData("columnHeaderNames");
            Listener columnHeaderListener = (Listener)table.getData("columnHeaderListener");
            try {
                if (selectedColumn.getImage() != null) {
                    selectedColumnExists = true;
                }
            }
            catch (NullPointerException nullPointerException) {
            }
            catch (SWTException sWTException) {}
            if (selectedColumnExists) {
                index = (Integer)selectedColumn.getData("index");
                displayMessage = "Insert Column before " + selectedColumn.getText() + "?";
            }
            if (MessageDialog.openQuestion((Shell)Display.getCurrent().getActiveShell(), (String)"Insert Column", (String)displayMessage)) {
                while (colNameUsed) {
                    InputDialog getcolNameDlg = new InputDialog(Display.getCurrent().getActiveShell(), "Insert Column", String.valueOf(ifColUsed) + "\nInsert New Column Name:", "NewColumn", null);
                    getcolNameDlg.open();
                    if (getcolNameDlg.getReturnCode() == 0) {
                        if (!dataManipulationManager.checkColumnNameIfUsed(getcolNameDlg.getValue(), columnHeaderNames)) {
                            ifColUsed = dataManipulationManager.variableNameInputValidation(getcolNameDlg.getValue());
                            if (!ifColUsed.equals("true")) continue;
                            colNameUsed = false;
                            dataManipulationManager.insertTableColumn(table, index, getcolNameDlg.getValue());
                            TableColumn col = table.getColumn(index);
                            col.addListener(13, columnHeaderListener);
                            dataManipulationManager.addNewVariableNameToTmpVarInfo(file.getAbsolutePath(), getcolNameDlg.getValue());
                            dataManipulationManager.saveFileChanges(file, table, DataManipulationManager.getActiveDataDelimiter(file.getAbsolutePath()));
                            columnHeaderNames = dataManipulationManager.updateColHeaderNames(table, (String[])table.getData("columnHeaderNames"));
                            PartStackHandler.setPartModified((String)table.getData("ID"));
                            WindowDialogControlUtil.closeAllWindowDialogOfFile(file.getAbsolutePath());
                            ((ToolItem)table.getData("saveBtn")).setEnabled(true);
                            continue;
                        }
                        ifColUsed = "Sorry, the column name \"" + getcolNameDlg.getValue() + "\" already exist.\n";
                        continue;
                    }
                    colNameUsed = false;
                }
            }
        }
        catch (NullPointerException nullPointerException) {
            MessageDialog.openError((Shell)Display.getCurrent().getActiveShell(), (String)"Error", (String)"The active tab in the Editor panel should contain a data file.");
        }
    }
}

