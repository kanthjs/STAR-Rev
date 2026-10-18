/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.inject.Inject
 *  org.eclipse.e4.core.di.annotations.Execute
 *  org.eclipse.e4.ui.model.application.MApplication
 *  org.eclipse.e4.ui.model.application.ui.MUIElement
 *  org.eclipse.e4.ui.model.application.ui.basic.MPartStack
 *  org.eclipse.e4.ui.workbench.modeling.EModelService
 *  org.eclipse.e4.ui.workbench.modeling.EPartService
 *  org.eclipse.jface.dialogs.MessageDialog
 *  org.eclipse.swt.widgets.Display
 *  org.eclipse.swt.widgets.Shell
 */
package org.irri.breedingtool.datamanipulation.handler;

import java.io.File;
import javax.inject.Inject;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.MUIElement;
import org.eclipse.e4.ui.model.application.ui.basic.MPartStack;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.irri.breedingtool.application.model.ProjectExplorerTreeNodeModel;
import org.irri.breedingtool.datamanipulation.dialog.MergeDialog;
import org.irri.breedingtool.manager.impl.DataManipulationManager;
import org.irri.breedingtool.manager.impl.ProjectExplorerManager;

public class MergeHandler {
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
            MUIElement selected = stack.getSelectedElement();
            String absoluteFilePath = selected.getElementId();
            dataManipulationManager.saveFileChanges(new File(absoluteFilePath), DataManipulationManager.getActiveTable(absoluteFilePath), DataManipulationManager.getActiveDataDelimiter(absoluteFilePath));
            ProjectExplorerManager projExpMan = new ProjectExplorerManager();
            ProjectExplorerTreeNodeModel newFileModel = projExpMan.getTreeNodeModelbyFilePath(absoluteFilePath);
            dataManipulationManager.getVarInfoFromFile(absoluteFilePath);
            System.out.println(newFileModel.getProjectFile().getName());
            MergeDialog newDialog = new MergeDialog(Display.getCurrent().getActiveShell(), newFileModel);
            newDialog.open();
        }
        catch (NullPointerException nullPointerException) {
            MessageDialog.openError((Shell)Display.getCurrent().getActiveShell(), (String)"Error", (String)"The active tab in the Editor panel should contain a data file.");
        }
    }

    public static void execute(ProjectExplorerTreeNodeModel newFileModel, int fileFormat, String delimiter) {
        String absoluteFilePath = newFileModel.getProjectFile().getAbsolutePath();
        dataManipulationManager.getVarInfoFromFile(absoluteFilePath);
        System.out.println(newFileModel.getProjectFile().getName());
        MergeDialog newVarDlg = new MergeDialog(Display.getCurrent().getActiveShell(), newFileModel);
        newVarDlg.open();
    }
}

