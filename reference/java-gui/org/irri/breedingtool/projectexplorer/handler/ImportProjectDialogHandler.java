/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.inject.Named
 *  org.eclipse.e4.core.di.annotations.Execute
 *  org.eclipse.jface.dialogs.MessageDialog
 *  org.eclipse.swt.widgets.DirectoryDialog
 *  org.eclipse.swt.widgets.Display
 *  org.eclipse.swt.widgets.Shell
 */
package org.irri.breedingtool.projectexplorer.handler;

import java.io.File;
import javax.inject.Named;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.irri.breedingtool.datamanipulation.dialog.OperationProgressDialog;
import org.irri.breedingtool.manager.impl.ProjectExplorerManager;
import org.irri.breedingtool.projectexplorer.view.ProjectExplorerView;
import org.irri.breedingtool.projectexplorer.view.ProjectTreeComponent;

public class ImportProjectDialogHandler {
    ProjectTreeComponent projectTreeComponent;

    @Named(value="activeShell")
    @Execute
    public void launchImportProject(Shell parent) {
        DirectoryDialog dlg = new DirectoryDialog(Display.getCurrent().getActiveShell());
        dlg.setText("Import Project");
        dlg.setMessage("Select a directory");
        String dir = dlg.open();
        if (dir != null) {
            File newProj = new File(String.valueOf(dir) + File.separator + "project.ini");
            if (!newProj.exists()) {
                MessageDialog.openError((Shell)Display.getCurrent().getActiveShell(), (String)"Import Project Error", (String)"The folder selected was not a valid project");
                return;
            }
            boolean isOverwrite = true;
            String message = "The Project: '" + new File(dir).getName().toString() + "' already exist in the Project folder. Do you want to overwrite the Project? \n\n\n WARNING: This action is permanent";
            if (new File(String.valueOf(ProjectTreeComponent.getCurrentWorkspacePath()) + new File(dir).getName()).exists()) {
                if (new File(String.valueOf(ProjectTreeComponent.getCurrentWorkspacePath()) + new File(dir).getName()).getAbsolutePath().equals(dir)) {
                    MessageDialog.openError((Shell)parent, (String)"Importing Project", (String)"Error: Could not import the same project that already exist in the workspace.");
                    return;
                }
                isOverwrite = MessageDialog.openQuestion((Shell)Display.getCurrent().getActiveShell(), (String)"Confirm", (String)message);
            }
            if (isOverwrite) {
                OperationProgressDialog rInfo = new OperationProgressDialog(Display.getCurrent().getActiveShell(), "Importing Project...");
                rInfo.open();
                ProjectExplorerManager projectMan = new ProjectExplorerManager();
                projectMan.deleteFolder(new File(String.valueOf(ProjectTreeComponent.getCurrentWorkspacePath()) + new File(dir).getName()));
                projectMan.copyDirectory(new File(dir), new File(String.valueOf(ProjectTreeComponent.getCurrentWorkspacePath()) + new File(dir).getName()));
                this.projectTreeComponent = new ProjectTreeComponent(ProjectExplorerView.projectTree, new File(String.valueOf(ProjectTreeComponent.getCurrentWorkspacePath()) + new File(dir).getName()).getName().toString());
                rInfo.close();
            }
        }
    }
}

