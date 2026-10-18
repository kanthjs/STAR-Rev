/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.sun.jna.platform.FileUtils
 *  javax.inject.Named
 *  org.eclipse.e4.core.di.annotations.Execute
 *  org.eclipse.swt.widgets.Display
 *  org.eclipse.swt.widgets.Shell
 */
package org.irri.breedingtool.projectexplorer.handler;

import com.sun.jna.platform.FileUtils;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.inject.Named;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.irri.breedingtool.application.handler.PartStackHandler;
import org.irri.breedingtool.datamanipulation.dialog.OperationProgressDialog;
import org.irri.breedingtool.manager.impl.ProjectExplorerManager;
import org.irri.breedingtool.projectexplorer.dialog.DeleteProjectsDialog;
import org.irri.breedingtool.projectexplorer.view.ProjectExplorerView;
import org.irri.breedingtool.projectexplorer.view.ProjectTreeComponent;
import org.irri.breedingtool.projectexplorer.view.RJavaManagerInitializer;

public class DeleteProjectDialogHandler {
    ProjectTreeComponent projectTreeComponent;

    @Named(value="activeShell")
    @Execute
    public static void deleteProject(Shell parent) {
        if (ProjectExplorerView.rootTree.getItemCount() == 0) {
            return;
        }
        if (ProjectExplorerView.projectTree.getText().length() <= 3) {
            return;
        }
        ProjectExplorerManager projectMan = new ProjectExplorerManager();
        DeleteProjectsDialog dlgDeleteProjects = new DeleteProjectsDialog(Display.getCurrent().getActiveShell());
        dlgDeleteProjects.open();
        ArrayList<File> projectsSelected = dlgDeleteProjects.getProjectsSelected();
        if (projectsSelected.isEmpty()) {
            return;
        }
        OperationProgressDialog dlgProgress = new OperationProgressDialog(parent, "Sending Project(s) to Recycle/Trash bin...");
        dlgProgress.open();
        FileUtils fileUtil = FileUtils.getInstance();
        int i = 0;
        while (i < projectsSelected.size()) {
            if (projectsSelected.get(i).getName().equals(ProjectExplorerView.projectTree.getText())) {
                ProjectExplorerView.projectTree.clearAll(true);
                ProjectExplorerView.projectTree.removeAll();
                ProjectExplorerView.projectTree.setText("...");
                RJavaManagerInitializer.isProjectActive = false;
                PartStackHandler.removeAll();
                projectMan.saveLastOpenedProject("null");
                Display.getDefault().getActiveShell().setText(RJavaManagerInitializer.APPLICATION_TITLE);
            }
            try {
                fileUtil.moveToTrash(new File[]{projectsSelected.get(i)});
            }
            catch (IOException iOException) {
                try {
                    fileUtil.moveToTrash(new File[]{projectsSelected.get(i)});
                }
                catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            ++i;
        }
        dlgProgress.close();
    }
}

