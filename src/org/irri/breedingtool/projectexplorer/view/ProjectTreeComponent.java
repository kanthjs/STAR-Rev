/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.jface.dialogs.MessageDialog
 *  org.eclipse.swt.widgets.Display
 *  org.eclipse.swt.widgets.Shell
 *  org.eclipse.swt.widgets.TreeItem
 */
package org.irri.breedingtool.projectexplorer.view;

import java.io.File;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.wb.swt.ResourceManager;
import org.irri.breedingtool.application.model.ProjectExplorerTreeNodeModel;
import org.irri.breedingtool.manager.impl.ProjectExplorerManager;
import org.irri.breedingtool.projectexplorer.view.ProjectExplorerView;
import org.irri.breedingtool.projectexplorer.view.RJavaManagerInitializer;

public class ProjectTreeComponent {
    private static ProjectExplorerManager projMan = new ProjectExplorerManager();
    static String PROJECT_PATH;
    private ProjectExplorerManager projectExplorerManager = new ProjectExplorerManager();

    public static String getCurrentWorkspacePath() {
        ProjectExplorerManager projectExplorerManager = new ProjectExplorerManager();
        return projectExplorerManager.getWorkspacePath();
    }

    public ProjectTreeComponent(TreeItem projectTree, String projectName) {
        if (projectName.equals("null")) {
            return;
        }
        projectTree.removeAll();
        System.out.println(System.getProperty("java.library.path"));
        PROJECT_PATH = String.valueOf(this.projectExplorerManager.getWorkspacePath()) + projectName + File.separator;
        if (!new File(PROJECT_PATH).exists()) {
            if (Display.getCurrent().getActiveShell() != null) {
                Display.getCurrent().getActiveShell().setText(RJavaManagerInitializer.APPLICATION_TITLE);
            }
            projectTree.setText("...");
            RJavaManagerInitializer.isProjectActive = false;
            return;
        }
        this.projectExplorerManager.saveLastOpenedProject(projectName);
        File fileData = new File(String.valueOf(PROJECT_PATH) + File.separator + "Data");
        File fileOutput = new File(String.valueOf(PROJECT_PATH) + File.separator + "Output");
        File projectPath = new File(PROJECT_PATH);
        if (!fileOutput.exists()) {
            fileOutput.mkdir();
        }
        if (!fileData.exists()) {
            fileData.mkdir();
        }
        this.projectExplorerManager.listFolder(projectPath, projectTree);
        this.projectExplorerManager.listFiles(projectPath, projectTree);
        this.projectExplorerManager.saveLastOpenedProject(projectName);
        projectTree.setImage(ResourceManager.getPluginImage(this.projectExplorerManager.getPackageName(), "icons/package.png"));
        projectTree.setExpanded(true);
        projectTree.setText(projectName);
        projectTree.setData((Object)new ProjectExplorerTreeNodeModel(projectPath, projectTree));
        if (Display.getCurrent().getActiveShell() != null) {
            Display.getCurrent().getActiveShell().setText(String.valueOf(RJavaManagerInitializer.APPLICATION_TITLE) + projectName);
        }
        boolean invalidFiles = false;
        int i = 0;
        while (i < ProjectExplorerView.projectTree.getItemCount()) {
            if (!ProjectExplorerView.projectTree.getItem(i).getText().equals("Data") && !ProjectExplorerView.projectTree.getItem(i).getText().equals("Output")) {
                ProjectExplorerView.projectTree.getItem(i).dispose();
                invalidFiles = true;
            }
            ++i;
        }
        if (invalidFiles) {
            MessageDialog.openWarning((Shell)Display.getCurrent().getActiveShell(), (String)"Warning: Invalid Files", (String)"Warning! There are some files outside the primary folders. \n Make sure all of the files are inside Data/Output folder.");
        }
        RJavaManagerInitializer.isProjectActive = true;
    }
}

