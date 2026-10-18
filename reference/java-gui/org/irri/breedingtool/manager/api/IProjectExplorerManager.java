/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.swt.widgets.TreeItem
 */
package org.irri.breedingtool.manager.api;

import java.io.File;
import java.io.IOException;
import org.eclipse.swt.widgets.TreeItem;
import org.irri.breedingtool.application.model.ProjectExplorerTreeNodeModel;

public interface IProjectExplorerManager {
    public ProjectExplorerTreeNodeModel createNewProjectExplorerModel(ProjectExplorerTreeNodeModel var1, String var2);

    public boolean createNewProject(String var1, String var2);

    public String getProjectDescription(String var1);

    public String getProjectDateCreated(String var1);

    public TreeItem getTreeNodeByName(TreeItem var1, String var2);

    public ProjectExplorerTreeNodeModel getTreeNodeModelbyFilePath(String var1);

    public TreeItem getDataFolder(TreeItem var1);

    public TreeItem getOutputFolder(TreeItem var1);

    public String getLastOpenedProject();

    public String getApplicationINI();

    public String getPackageName();

    public String getProjectPath();

    public boolean deleteFile(String var1);

    public boolean deleteFolder(File var1);

    public boolean deleteProject(String var1);

    public void listFolder(File var1, TreeItem var2);

    public void listFiles(File var1, TreeItem var2);

    public boolean copyFile(File var1, File var2);

    public boolean copyBatchFile(File[] var1, File var2);

    public boolean copyDirectory(File var1, File var2);

    public boolean renameFile(String var1, String var2) throws IOException;

    public void copyToClipboard(String[] var1);

    public void pasteFromClipboard(String var1, String var2);

    public void refreshFolder(ProjectExplorerTreeNodeModel var1);

    public boolean renameProject(String var1);

    public boolean saveLastOpenedProject(String var1);

    public boolean toImport(String var1, String var2, String var3);

    public boolean redraw();

    public boolean removeAllTreeItems(TreeItem var1);

    public void createNewFolder(String var1, String var2);

    public String getWorkspacePath();

    public boolean saveWorkspacePath(String var1);

    public boolean copySampleProjectTo(String var1) throws IOException;
}

