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
 *  org.eclipse.swt.widgets.Display
 *  org.eclipse.swt.widgets.Shell
 *  org.eclipse.swt.widgets.Table
 *  org.eclipse.swt.widgets.ToolItem
 */
package org.irri.breedingtool.datamanipulation.handler;

import java.io.File;
import java.util.ArrayList;
import javax.inject.Inject;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.MUIElement;
import org.eclipse.e4.ui.model.application.ui.basic.MPartStack;
import org.eclipse.e4.ui.model.application.ui.basic.MStackElement;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.ToolItem;
import org.irri.breedingtool.application.handler.PartStackHandler;
import org.irri.breedingtool.manager.impl.DataManipulationManager;

public class InsertRowMenuHandler {
    static DataManipulationManager dataManipulationManager = new DataManipulationManager();
    @Inject
    private static EPartService service;
    @Inject
    private static MApplication application;
    @Inject
    private static EModelService modelService;
    public static String PackageName;

    static {
        PackageName = "Star";
    }

    @Execute
    public static void execute() {
        try {
            MPartStack stack = (MPartStack)modelService.find("ViewerStack", (MUIElement)application);
            MStackElement selected = (MStackElement)stack.getSelectedElement();
            String absoluteFilePath = selected.getElementId();
            Table table = DataManipulationManager.getActiveTable(selected.getElementId());
            ArrayList tableData = (ArrayList)table.getData("tableData");
            if (table != null) {
                String displayMessage = "There's no selected row.\nDo you want to insert a new row after the last row?";
                if (table.getSelectionIndex() == -1) {
                    if (MessageDialog.openQuestion((Shell)Display.getCurrent().getActiveShell(), (String)"Insert Row", (String)displayMessage)) {
                        dataManipulationManager.insertTableRow(table, table.getItemCount(), tableData);
                    }
                    ((ToolItem)table.getData("saveBtn")).setEnabled(true);
                    dataManipulationManager.updateRowNumberColumn(table);
                } else {
                    int[] selectedRows = table.getSelectionIndices();
                    int i = 0;
                    while (i < selectedRows.length) {
                        dataManipulationManager.insertTableRow(table, selectedRows[i], tableData);
                        ++i;
                    }
                    displayMessage = "Successfully inserted new row(s).";
                    MessageDialog.openInformation((Shell)Display.getCurrent().getActiveShell(), (String)"Insert Row", (String)displayMessage);
                    dataManipulationManager.updateRowNumberColumn(table);
                    ((ToolItem)table.getData("saveBtn")).setEnabled(true);
                }
                PartStackHandler.setPartModified(absoluteFilePath);
                dataManipulationManager.saveFileChanges(new File(absoluteFilePath), DataManipulationManager.getActiveTable(absoluteFilePath), DataManipulationManager.getActiveDataDelimiter(absoluteFilePath));
            }
        }
        catch (NullPointerException nullPointerException) {
            MessageDialog.openError((Shell)Display.getCurrent().getActiveShell(), (String)"Error", (String)"The active tab in the Editor panel should contain a data file.");
        }
    }

    @Execute
    public static void execute(Table table) {
        String displayMessage = "There's no selected row.\nDo you want to insert a new row after the last row?";
        ArrayList tableData = (ArrayList)table.getData("tableData");
        if (table.getSelectionIndex() == -1) {
            if (MessageDialog.openQuestion((Shell)Display.getCurrent().getActiveShell(), (String)"Insert Row", (String)displayMessage)) {
                dataManipulationManager.insertTableRow(table, table.getItemCount(), tableData);
            }
            dataManipulationManager.updateRowNumberColumn(table);
            ((ToolItem)table.getData("saveBtn")).setEnabled(true);
        } else {
            int[] selectedRows = table.getSelectionIndices();
            int i = 0;
            while (i < selectedRows.length) {
                dataManipulationManager.insertTableRow(table, selectedRows[i], tableData);
                ++i;
            }
            displayMessage = "Successfully inserted new row(s).";
            MessageDialog.openInformation((Shell)Display.getCurrent().getActiveShell(), (String)"Insert Row", (String)displayMessage);
            dataManipulationManager.updateRowNumberColumn(table);
            ((ToolItem)table.getData("saveBtn")).setEnabled(true);
        }
        PartStackHandler.setPartModified((String)table.getData("ID"));
    }
}

