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

import java.util.ArrayList;
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
import org.irri.breedingtool.datamanipulation.dialog.EditVariableInfoDialog;
import org.irri.breedingtool.manager.impl.DataManipulationManager;
import org.irri.breedingtool.manager.impl.ProjectExplorerManager;

public class EditVarInfoHandler {
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
            ProjectExplorerManager projExpMan = new ProjectExplorerManager();
            ProjectExplorerTreeNodeModel newFileModel = projExpMan.getTreeNodeModelbyFilePath(absoluteFilePath);
            ArrayList<String> varInfo = dataManipulationManager.getVarInfoFromFile(absoluteFilePath);
            System.out.println(newFileModel.getProjectFile().getName());
            EditVariableInfoDialog newDialog = new EditVariableInfoDialog(Display.getCurrent().getActiveShell(), varInfo, newFileModel);
            newDialog.open();
            varInfo = newDialog.getVarInfo();
        }
        catch (NullPointerException nullPointerException) {
            MessageDialog.openError((Shell)Display.getCurrent().getActiveShell(), (String)"Error", (String)"The active tab in the Editor panel should contain a data file.");
        }
    }

    public static void execute(ProjectExplorerTreeNodeModel newFileModel, int fileFormat, String delimiter) {
        try {
            String absoluteFilePath = newFileModel.getProjectFile().getAbsolutePath();
            ArrayList<String> varInfo = dataManipulationManager.getVarInfoFromFile(absoluteFilePath);
            System.out.println(newFileModel.getProjectFile().getName());
            EditVariableInfoDialog newVarDlg = new EditVariableInfoDialog(Display.getCurrent().getActiveShell(), varInfo, newFileModel);
            newVarDlg.open();
            varInfo = newVarDlg.getVarInfo();
        }
        catch (NullPointerException nullPointerException) {
            MessageDialog.openError((Shell)Display.getCurrent().getActiveShell(), (String)"Error", (String)"The active tab in the Editor panel should contain a data file.");
        }
    }
}

