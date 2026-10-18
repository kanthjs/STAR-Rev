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
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.ToolItem;
import org.irri.breedingtool.application.handler.PartStackHandler;
import org.irri.breedingtool.datamanipulation.dialog.DeleteColumnsDialog;
import org.irri.breedingtool.manager.impl.DataManipulationManager;
import org.irri.breedingtool.utility.WindowDialogControlUtil;

public class DeleteColumnMenuHandler {
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
        block13: {
            try {
                MPartStack stack = (MPartStack)modelService.find("ViewerStack", (MUIElement)application);
                MStackElement selected = (MStackElement)stack.getSelectedElement();
                String absoluteFilePath = selected.getElementId();
                File file = new File(absoluteFilePath);
                Table table = DataManipulationManager.getActiveTable(selected.getElementId());
                String delimiter = DataManipulationManager.getActiveDataDelimiter(selected.getElementId());
                TableColumn selectedColumn = (TableColumn)table.getData("selectedColumn");
                String[] columnHeaderNames = (String[])table.getData("columnHeaderNames");
                Listener cfr_ignored_0 = (Listener)table.getData("columnHeaderListener");
                boolean selectedColumnExists = false;
                try {
                    if (selectedColumn.getImage() != null) {
                        selectedColumnExists = true;
                    }
                }
                catch (NullPointerException nullPointerException) {
                }
                catch (SWTException sWTException) {}
                if (selectedColumnExists) {
                    int index = (Integer)selectedColumn.getData("index");
                    DeleteColumnsDialog getInputFromUserDlg = new DeleteColumnsDialog(Display.getCurrent().getActiveShell(), selectedColumn.getText(), columnHeaderNames);
                    getInputFromUserDlg.open();
                    int[] selectedColumnIndices = getInputFromUserDlg.getSelectedColumnIndices();
                    if (getInputFromUserDlg.getReturnCode() != 0) break block13;
                    if (selectedColumnIndices.length > 1) {
                        int i = 0;
                        while (i < selectedColumnIndices.length) {
                            int selColIndex = selectedColumnIndices[i] + 2 - (i + 1);
                            TableColumn selCol = table.getColumn(selColIndex);
                            dataManipulationManager.deleteVariableFromVarInfoTmp(file.getAbsolutePath(), selCol.getText());
                            dataManipulationManager.deleteTableColumn(table, selColIndex);
                            System.out.println("delete " + Integer.toString(selectedColumnIndices[i] + 2 - (i + 1)));
                            ++i;
                        }
                        WindowDialogControlUtil.closeAllWindowDialogOfFile(file.getAbsolutePath());
                        ((ToolItem)table.getData("saveBtn")).setEnabled(true);
                    } else {
                        dataManipulationManager.deleteVariableFromVarInfoTmp(file.getAbsolutePath(), selectedColumn.getText());
                        dataManipulationManager.deleteTableColumn(table, index);
                        WindowDialogControlUtil.closeAllWindowDialogOfFile(file.getAbsolutePath());
                        ((ToolItem)table.getData("saveBtn")).setEnabled(true);
                    }
                    try {
                        selectedColumn.setImage(null);
                    }
                    catch (SWTException sWTException) {}
                    PartStackHandler.setPartModified(file.getAbsolutePath());
                    columnHeaderNames = dataManipulationManager.updateColHeaderNames(table, columnHeaderNames);
                    dataManipulationManager.saveFileChanges(file, table, delimiter);
                    break block13;
                }
                MessageDialog.openInformation((Shell)Display.getCurrent().getActiveShell(), (String)"Delete Column", (String)"Please select the column you want to delete.");
            }
            catch (NullPointerException nullPointerException) {
                MessageDialog.openError((Shell)Display.getCurrent().getActiveShell(), (String)"Error", (String)"The active tab in the Editor panel should contain a data file.");
            }
        }
    }

    @Execute
    public static void execute(File file, Table table) {
        block13: {
            try {
                boolean selectedColumnExists = false;
                String delimiter = (String)table.getData("delimiter");
                TableColumn selectedColumn = (TableColumn)table.getData("selectedColumn");
                String[] columnHeaderNames = (String[])table.getData("columnHeaderNames");
                Listener cfr_ignored_0 = (Listener)table.getData("columnHeaderListener");
                try {
                    if (selectedColumn.getImage() != null) {
                        selectedColumnExists = true;
                    }
                }
                catch (NullPointerException nullPointerException) {
                }
                catch (SWTException sWTException) {}
                if (selectedColumnExists) {
                    int index = (Integer)selectedColumn.getData("index");
                    DeleteColumnsDialog getInputFromUserDlg = new DeleteColumnsDialog(Display.getCurrent().getActiveShell(), selectedColumn.getText(), columnHeaderNames);
                    getInputFromUserDlg.open();
                    int[] selectedColumnIndices = getInputFromUserDlg.getSelectedColumnIndices();
                    if (getInputFromUserDlg.getReturnCode() != 0) break block13;
                    if (selectedColumnIndices.length > 1) {
                        int i = 0;
                        while (i < selectedColumnIndices.length) {
                            int selColIndex = selectedColumnIndices[i] + 2 - (i + 1);
                            TableColumn selCol = table.getColumn(selColIndex);
                            dataManipulationManager.deleteVariableFromVarInfoTmp(file.getAbsolutePath(), selCol.getText());
                            dataManipulationManager.deleteTableColumn(table, selColIndex);
                            System.out.println("delete " + Integer.toString(selectedColumnIndices[i] + 2 - (i + 1)));
                            ++i;
                        }
                        WindowDialogControlUtil.closeAllWindowDialogOfFile(file.getAbsolutePath());
                        ((ToolItem)table.getData("saveBtn")).setEnabled(true);
                    } else {
                        dataManipulationManager.deleteVariableFromVarInfoTmp(file.getAbsolutePath(), selectedColumn.getText());
                        dataManipulationManager.deleteTableColumn(table, index);
                        WindowDialogControlUtil.closeAllWindowDialogOfFile(file.getAbsolutePath());
                        ((ToolItem)table.getData("saveBtn")).setEnabled(true);
                    }
                    try {
                        selectedColumn.setImage(null);
                    }
                    catch (SWTException sWTException) {}
                    PartStackHandler.setPartModified(file.getAbsolutePath());
                    columnHeaderNames = dataManipulationManager.updateColHeaderNames(table, columnHeaderNames);
                    dataManipulationManager.saveFileChanges(file, table, delimiter);
                    break block13;
                }
                MessageDialog.openInformation((Shell)Display.getCurrent().getActiveShell(), (String)"Delete Column", (String)"Please select the column you want to delete.");
            }
            catch (NullPointerException nullPointerException) {
                MessageDialog.openError((Shell)Display.getCurrent().getActiveShell(), (String)"Error", (String)"The active tab in the Editor panel should contain a data file.");
            }
        }
    }
}

