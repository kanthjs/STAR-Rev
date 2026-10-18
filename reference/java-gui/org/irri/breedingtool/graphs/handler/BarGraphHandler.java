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
 */
package org.irri.breedingtool.graphs.handler;

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
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.irri.breedingtool.application.model.ProjectExplorerTreeNodeModel;
import org.irri.breedingtool.graphs.dialog.BarGraphDialog;
import org.irri.breedingtool.manager.impl.DataManipulationManager;
import org.irri.breedingtool.manager.impl.ProjectExplorerManager;
import org.irri.breedingtool.utility.WindowDialogControlUtil;

public class BarGraphHandler {
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
        block5: {
            try {
                MPartStack stack = (MPartStack)modelService.find("ViewerStack", (MUIElement)application);
                MUIElement selected = stack.getSelectedElement();
                String absoluteFilePath = selected.getElementId();
                dataManipulationManager.saveFileChanges(new File(absoluteFilePath), DataManipulationManager.getActiveTable(absoluteFilePath), DataManipulationManager.getActiveDataDelimiter(absoluteFilePath));
                ProjectExplorerManager projExpMan = new ProjectExplorerManager();
                ProjectExplorerTreeNodeModel newFileModel = projExpMan.getTreeNodeModelbyFilePath(absoluteFilePath);
                dataManipulationManager.getVarInfoFromFile(absoluteFilePath);
                System.out.println(newFileModel.getProjectFile().getName());
                dataManipulationManager.saveFileChanges(newFileModel.getProjectFile(), DataManipulationManager.getActiveTable(absoluteFilePath), DataManipulationManager.getActiveDataDelimiter(absoluteFilePath));
                File activeFile = new File(((MStackElement)stack.getSelectedElement()).getElementId());
                try {
                    if (WindowDialogControlUtil.doesWindowDialogForFileExist("BarGraph", activeFile.getAbsolutePath())) {
                        WindowDialogControlUtil.showWindowDialog("BarGraph", activeFile.getAbsolutePath());
                        break block5;
                    }
                    BarGraphDialog newDialog = new BarGraphDialog(Display.getCurrent().getActiveShell(), "BarGraph", newFileModel.getProjectFile());
                    newDialog.open();
                }
                catch (NullPointerException nullPointerException) {
                    BarGraphDialog newDialog = new BarGraphDialog(Display.getCurrent().getActiveShell(), "BarGraph", newFileModel.getProjectFile());
                    newDialog.open();
                }
            }
            catch (NullPointerException nullPointerException) {
                MessageDialog.openError((Shell)Display.getCurrent().getActiveShell(), (String)"Error", (String)"The active tab in the Editor panel should contain a data file.");
            }
        }
    }
}

