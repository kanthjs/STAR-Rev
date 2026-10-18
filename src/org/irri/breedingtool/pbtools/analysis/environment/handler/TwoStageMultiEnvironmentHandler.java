/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.inject.Inject
 *  javax.inject.Named
 *  org.eclipse.e4.core.di.annotations.Execute
 *  org.eclipse.e4.ui.model.application.MApplication
 *  org.eclipse.e4.ui.model.application.ui.MUIElement
 *  org.eclipse.e4.ui.model.application.ui.basic.MPartStack
 *  org.eclipse.e4.ui.model.application.ui.basic.MStackElement
 *  org.eclipse.e4.ui.model.application.ui.basic.MWindow
 *  org.eclipse.e4.ui.workbench.modeling.EModelService
 *  org.eclipse.jface.dialogs.MessageDialog
 *  org.eclipse.swt.widgets.Display
 *  org.eclipse.swt.widgets.Shell
 */
package org.irri.breedingtool.pbtools.analysis.environment.handler;

import java.io.File;
import javax.inject.Inject;
import javax.inject.Named;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.MUIElement;
import org.eclipse.e4.ui.model.application.ui.basic.MPartStack;
import org.eclipse.e4.ui.model.application.ui.basic.MStackElement;
import org.eclipse.e4.ui.model.application.ui.basic.MWindow;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.irri.breedingtool.manager.impl.DataManipulationManager;
import org.irri.breedingtool.pbtools.analysis.environment.dialog.TwoStageMultiEnvironmentDialog;
import org.irri.breedingtool.projectexplorer.view.ProjectTreeComponent;
import org.irri.breedingtool.utility.WindowDialogControlUtil;

public class TwoStageMultiEnvironmentHandler {
    ProjectTreeComponent projectTreeComponent;
    TwoStageMultiEnvironmentDialog twoStageMultiEnvironmentDialog;
    @Inject
    private static EModelService modelService;
    @Inject
    private static MApplication application;

    @Named(value="activeShell")
    @Execute
    public void execute(Shell parent, EModelService service, MWindow window) {
        block5: {
            DataManipulationManager dataManipulationManager = new DataManipulationManager();
            try {
                MPartStack stack = (MPartStack)modelService.find("ViewerStack", (MUIElement)application);
                MUIElement selected = stack.getSelectedElement();
                String absoluteFilePath = selected.getElementId();
                dataManipulationManager.saveFileChanges(new File(absoluteFilePath), DataManipulationManager.getActiveTable(absoluteFilePath), DataManipulationManager.getActiveDataDelimiter(absoluteFilePath));
                File activeFile = new File(((MStackElement)stack.getSelectedElement()).getElementId());
                try {
                    if (WindowDialogControlUtil.doesWindowDialogForFileExist("twoStageMultiEnv", activeFile.getAbsolutePath())) {
                        WindowDialogControlUtil.showWindowDialog("twoStageMultiEnv", activeFile.getAbsolutePath());
                        break block5;
                    }
                    this.twoStageMultiEnvironmentDialog = new TwoStageMultiEnvironmentDialog(parent, "twoStageMultiEnv", activeFile);
                    this.twoStageMultiEnvironmentDialog.open();
                }
                catch (NullPointerException nullPointerException) {
                    this.twoStageMultiEnvironmentDialog = new TwoStageMultiEnvironmentDialog(parent, "twoStageMultiEnv", activeFile);
                    this.twoStageMultiEnvironmentDialog.open();
                }
            }
            catch (NullPointerException nullPointerException) {
                MessageDialog.openError((Shell)Display.getCurrent().getActiveShell(), (String)"Error", (String)"The active tab in the Editor panel should contain a data file.");
            }
        }
    }
}

