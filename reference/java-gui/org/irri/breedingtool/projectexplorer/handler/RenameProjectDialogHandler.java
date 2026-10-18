/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.inject.Named
 *  org.eclipse.e4.core.di.annotations.Execute
 *  org.eclipse.e4.ui.model.application.ui.basic.MWindow
 *  org.eclipse.e4.ui.workbench.modeling.EModelService
 *  org.eclipse.jface.dialogs.IInputValidator
 *  org.eclipse.jface.dialogs.InputDialog
 *  org.eclipse.swt.widgets.Display
 *  org.eclipse.swt.widgets.Shell
 */
package org.irri.breedingtool.projectexplorer.handler;

import java.io.File;
import javax.inject.Named;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.ui.basic.MWindow;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.irri.breedingtool.application.handler.PartStackHandler;
import org.irri.breedingtool.manager.impl.ProjectExplorerManager;
import org.irri.breedingtool.projectexplorer.view.ProjectExplorerView;
import org.irri.breedingtool.projectexplorer.view.ProjectTreeComponent;
import org.irri.breedingtool.utility.CheckFileExistValidator;

public class RenameProjectDialogHandler {
    @Named(value="activeShell")
    @Execute
    public void launchImportProject(Shell parent, EModelService service, MWindow window) {
        if (ProjectExplorerView.rootTree.getItemCount() == 0) {
            return;
        }
        if (ProjectExplorerView.projectTree.getText().length() <= 3) {
            return;
        }
        ProjectExplorerManager projectMan = new ProjectExplorerManager();
        InputDialog dlgRenameFile = new InputDialog(Display.getCurrent().getActiveShell(), "Rename Project", "Enter the new Project Name", ProjectExplorerView.projectTree.getText(), (IInputValidator)new CheckFileExistValidator(ProjectExplorerView.projectTree.getText().toString(), ProjectTreeComponent.getCurrentWorkspacePath(), false));
        if (dlgRenameFile.open() == 0) {
            PartStackHandler.removeAll();
            if (this.renameDirectory(projectMan.getProjectPath(), String.valueOf(new File(projectMan.getProjectPath()).getParent()) + File.separator + dlgRenameFile.getValue())) {
                new ProjectTreeComponent(ProjectExplorerView.projectTree, dlgRenameFile.getValue());
            }
        }
    }

    public boolean renameDirectory(String fromDir, String toDir) {
        File from = new File(fromDir);
        if (!from.exists() || !from.isDirectory()) {
            System.out.println("Directory does not exist: " + fromDir);
            return false;
        }
        File to = new File(toDir);
        return from.renameTo(to);
    }
}

