/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.sun.jna.LastErrorException
 *  com.sun.jna.platform.FileUtils
 *  org.eclipse.jface.dialogs.IInputValidator
 *  org.eclipse.jface.dialogs.InputDialog
 *  org.eclipse.jface.dialogs.MessageDialog
 *  org.eclipse.swt.dnd.Clipboard
 *  org.eclipse.swt.dnd.FileTransfer
 *  org.eclipse.swt.dnd.Transfer
 *  org.eclipse.swt.widgets.Display
 *  org.eclipse.swt.widgets.Shell
 *  org.eclipse.swt.widgets.TreeItem
 */
package org.irri.breedingtool.manager.impl;

import com.sun.jna.LastErrorException;
import com.sun.jna.platform.FileUtils;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Properties;
import javax.annotation.PostConstruct;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.FileTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.wb.swt.ResourceManager;
import org.irri.breedingtool.application.handler.PartStackHandler;
import org.irri.breedingtool.application.model.ProjectExplorerTreeNodeModel;
import org.irri.breedingtool.manager.api.IProjectExplorerManager;
import org.irri.breedingtool.projectexplorer.dialog.OpenProjectDialog;
import org.irri.breedingtool.projectexplorer.view.ProjectExplorerView;
import org.irri.breedingtool.projectexplorer.view.ProjectTreeComponent;
import org.irri.breedingtool.projectexplorer.view.RJavaManagerInitializer;
import org.irri.breedingtool.utility.CheckFileExistValidator;
import org.irri.breedingtool.utility.StarAnalysisUtilities;

public class ProjectExplorerManager
implements IProjectExplorerManager {
    @Override
    public ProjectExplorerTreeNodeModel createNewProjectExplorerModel(ProjectExplorerTreeNodeModel parentModel, String fileName) {
        this.refreshFolder(parentModel);
        int i = 0;
        while (i < parentModel.getTreeItem().getItemCount()) {
            if (parentModel.getTreeItem().getItem(i).getText().toString().equals(fileName)) {
                return (ProjectExplorerTreeNodeModel)parentModel.getTreeItem().getItem(i).getData();
            }
            ++i;
        }
        return null;
    }

    @Override
    public boolean copySampleProjectTo(String workspacePath) throws IOException {
        String applicationPath = String.valueOf(new File(".").getCanonicalPath()) + File.separator + "Projects" + File.separator + "SampleProject";
        this.copyDirectory(new File(applicationPath), new File(workspacePath));
        return false;
    }

    @Override
    public boolean createNewProject(String projectName, String projectDescription) {
        File newProjectFolder = new File(String.valueOf(ProjectTreeComponent.getCurrentWorkspacePath()) + projectName);
        newProjectFolder.mkdir();
        newProjectFolder = new File(String.valueOf(ProjectTreeComponent.getCurrentWorkspacePath()) + projectName + "\\Data\\");
        newProjectFolder.mkdir();
        newProjectFolder = new File(String.valueOf(ProjectTreeComponent.getCurrentWorkspacePath()) + projectName + "\\Output\\");
        newProjectFolder.mkdir();
        File setINIFile = new File(String.valueOf(ProjectTreeComponent.getCurrentWorkspacePath()) + projectName + "\\project.ini");
        try {
            FileWriter fileWriter = new FileWriter(setINIFile.getAbsoluteFile());
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            Date dateCreated = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss Z");
            String iniContent = "projectName=" + projectName + "\nprojectDescription=" + projectDescription + "\ndateCreated=" + formatter.format(dateCreated).toString();
            bufferedWriter.write(iniContent);
            bufferedWriter.close();
        }
        catch (IOException e1) {
            e1.printStackTrace();
        }
        return true;
    }

    @Override
    public String getProjectDescription(String projectName) {
        String retValue = "";
        Properties projectINI = new Properties();
        try {
            projectINI.load(new FileInputStream(String.valueOf(ProjectTreeComponent.getCurrentWorkspacePath()) + projectName + "\\project.ini"));
            retValue = projectINI.getProperty("projectDescription");
        }
        catch (FileNotFoundException fileNotFoundException) {
        }
        catch (IOException iOException) {}
        return retValue;
    }

    @Override
    public String getProjectDateCreated(String projectName) {
        String retValue = "";
        Properties projectINI = new Properties();
        try {
            projectINI.load(new FileInputStream(String.valueOf(ProjectTreeComponent.getCurrentWorkspacePath()) + projectName + "\\project.ini"));
            retValue = projectINI.getProperty("dateCreated");
        }
        catch (FileNotFoundException fileNotFoundException) {
        }
        catch (IOException iOException) {}
        return retValue;
    }

    @Override
    public TreeItem getTreeNodeByName(TreeItem leaf, String searchTreeNode) {
        int i = 0;
        while (i < leaf.getItemCount()) {
            if (searchTreeNode.equals(leaf.getItem(i).getText().toString())) {
                return leaf.getItem(i);
            }
            ++i;
        }
        return leaf;
    }

    @Override
    public TreeItem getDataFolder(TreeItem projectTree) {
        TreeItem[] treeNodes;
        TreeItem[] treeItemArray = treeNodes = projectTree.getItems();
        int n = treeNodes.length;
        int n2 = 0;
        while (n2 < n) {
            TreeItem node = treeItemArray[n2];
            if (node.getText().toString().equals("Data")) {
                return node;
            }
            ++n2;
        }
        return null;
    }

    @Override
    public ProjectExplorerTreeNodeModel getTreeNodeModelbyFilePath(String filePath) {
        ProjectExplorerTreeNodeModel fileModel = null;
        filePath = filePath.replace(String.valueOf(this.getProjectPath()) + "\\", "");
        String[] folderPaths = filePath.split("\\\\");
        TreeItem searchItem = ProjectExplorerView.projectTree;
        int i = 0;
        while (i < folderPaths.length) {
            if (folderPaths[i].equals((searchItem = this.getTreeNodeByName(searchItem, folderPaths[i])).getText().toString())) {
                fileModel = (ProjectExplorerTreeNodeModel)searchItem.getData();
            }
            ++i;
        }
        return fileModel;
    }

    @Override
    public TreeItem getOutputFolder(TreeItem projectTree) {
        TreeItem[] treeNodes;
        TreeItem[] treeItemArray = treeNodes = projectTree.getItems();
        int n = treeNodes.length;
        int n2 = 0;
        while (n2 < n) {
            TreeItem node = treeItemArray[n2];
            if (node.getText().toString().equals("Output")) {
                return node;
            }
            ++n2;
        }
        return null;
    }

    @Override
    public String getLastOpenedProject() {
        Properties starINI = new Properties();
        String retValue = "";
        try {
            starINI.load(new FileInputStream(this.getApplicationINI()));
            retValue = starINI.getProperty("lastOpenedProject");
            if (retValue == null) {
                return String.valueOf(Math.random());
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return retValue;
    }

    @Override
    public String getWorkspacePath() {
        Properties starINI = new Properties();
        String retValue = "";
        try {
            starINI.load(new FileInputStream(this.getApplicationINI()));
            retValue = starINI.getProperty("workspacePath");
            if (retValue == null) {
                return "NULL";
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        if (!retValue.endsWith("\\")) {
            return String.valueOf(retValue) + "\\";
        }
        return retValue;
    }

    @Override
    public String getApplicationINI() {
        String iniPath = String.valueOf(System.getProperty("user.dir")) + "\\system.ini";
        if (!new File(iniPath).exists()) {
            try {
                new File(iniPath).createNewFile();
            }
            catch (IOException e1) {
                e1.printStackTrace();
            }
            PrintStream out = null;
            try {
                try {
                    out = new PrintStream(new FileOutputStream(iniPath));
                }
                catch (FileNotFoundException fileNotFoundException) {}
                if (new File(String.valueOf(System.getProperty("user.dir")) + "\\Projects\\SampleProject").exists()) {
                    out.print("lastOpenedProject=SampleProject");
                } else {
                    OpenProjectDialog openProject = new OpenProjectDialog(Display.getCurrent().getActiveShell());
                    if (openProject.open() == 0) {
                        new ProjectTreeComponent(ProjectExplorerView.projectTree, openProject.getGetProjectName());
                        out.print("lastOpenedProject=" + openProject.getGetProjectName());
                    }
                }
            }
            finally {
                if (out != null) {
                    out.close();
                }
            }
        }
        return String.valueOf(System.getProperty("user.dir")) + "\\system.ini";
    }

    @Override
    public String getPackageName() {
        return "Star";
    }

    @Override
    public String getProjectPath() {
        return new File(String.valueOf(this.getWorkspacePath()) + File.separator + this.getLastOpenedProject()).getAbsolutePath();
    }

    @Override
    public boolean deleteFile(String filePath) {
        File projectFile = new File(filePath);
        FileUtils fileUtil = FileUtils.getInstance();
        File[] delFile = new File[]{projectFile};
        Object[] fileExeceptions = new String[]{String.valueOf(this.getProjectPath()) + "\\Data", String.valueOf(this.getProjectPath()) + "\\Output"};
        if (Arrays.binarySearch(fileExeceptions, projectFile.getAbsolutePath().toString()) > -1) {
            return false;
        }
        if (projectFile.isDirectory()) {
            try {
                fileUtil.moveToTrash(delFile);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        }
        try {
            File tempFile;
            if (delFile[0].getAbsolutePath().endsWith(".csv") && (tempFile = new File(delFile[0].getAbsolutePath().replace(".csv", RJavaManagerInitializer.VARINFO_TMPFILE_EXTENSION))).exists()) {
                tempFile.delete();
            }
            fileUtil.moveToTrash(delFile);
        }
        catch (IOException iOException) {
            return false;
        }
        catch (LastErrorException lastErrorException) {
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteFolder(File directory) {
        if (directory == null) {
            return false;
        }
        if (!directory.exists()) {
            return true;
        }
        if (!directory.isDirectory()) {
            return false;
        }
        String[] list = directory.list();
        if (list != null) {
            int i = 0;
            while (i < list.length) {
                File entry = new File(directory, list[i]);
                if (entry.isDirectory() ? !this.deleteFolder(entry) : !entry.delete()) {
                    return false;
                }
                ++i;
            }
        }
        return directory.delete();
    }

    @Override
    public boolean deleteProject(String projectName) {
        this.deleteFile(this.getProjectPath());
        return true;
    }

    @Override
    public void listFolder(File folder, TreeItem projectTree) {
        Object[] getDir = folder.listFiles();
        Arrays.sort(getDir);
        Object[] objectArray = getDir;
        int n = getDir.length;
        int n2 = 0;
        while (n2 < n) {
            Object fileEntry = objectArray[n2];
            if (((File)fileEntry).isDirectory()) {
                TreeItem folderLeaf = new TreeItem(projectTree, 0);
                folderLeaf.setText(((File)fileEntry).getName().toString());
                folderLeaf.setImage(ResourceManager.getPluginImage(this.getPackageName(), "icons/folder.png"));
                if (((File)fileEntry).list().length == 0) {
                    folderLeaf.setImage(ResourceManager.getPluginImage(this.getPackageName(), "icons/folder_empty.png"));
                }
                ProjectExplorerTreeNodeModel fileModel = new ProjectExplorerTreeNodeModel((File)fileEntry, folderLeaf);
                folderLeaf.setData((Object)fileModel);
                this.listFolder((File)fileEntry, folderLeaf);
            }
            ++n2;
        }
    }

    @Override
    public void listFiles(File folder, TreeItem projectTree) {
        FilenameFilter fileFilter = new FilenameFilter(){

            @Override
            public boolean accept(File arg0, String arg1) {
                String[] filterFileTypes;
                String[] stringArray = filterFileTypes = new String[]{"ini", "tmp", "RData", "_varInfo.txt", "_original.txt", "_varinfo.txt"};
                int n = filterFileTypes.length;
                int n2 = 0;
                while (n2 < n) {
                    String filter = stringArray[n2];
                    if (arg1.toUpperCase().endsWith(filter.toUpperCase())) {
                        return false;
                    }
                    ++n2;
                }
                return true;
            }
        };
        Object[] getDir = folder.listFiles(fileFilter);
        Arrays.sort(getDir);
        String[] filterFileTypes = new String[]{"ini", "tmp", "RData", "_varInfo.txt", "_original.txt", "_varinfo.txt"};
        Object[] objectArray = getDir;
        int n = getDir.length;
        int n2 = 0;
        while (n2 < n) {
            Object fileEntry = objectArray[n2];
            if (((File)fileEntry).isDirectory()) {
                this.listFiles((File)fileEntry, this.getTreeNodeByName(projectTree, ((File)fileEntry).getName().toString()));
            } else {
                String fileType = ((File)fileEntry).getName().toString().split("\\.")[((File)fileEntry).getName().toString().split("\\.").length - 1];
                if (!Arrays.asList(filterFileTypes).contains(fileType.toString())) {
                    TreeItem fileLeaf = new TreeItem(projectTree, 0);
                    fileLeaf.setText(((File)fileEntry).getName().toString());
                    ProjectExplorerTreeNodeModel fileModel = new ProjectExplorerTreeNodeModel((File)fileEntry, fileLeaf);
                    fileLeaf.setData((Object)fileModel);
                    if (((File)fileEntry).getName().endsWith(".txt")) {
                        fileLeaf.setImage(ResourceManager.getPluginImage(this.getPackageName(), "icons/page_white_text.png"));
                    } else if (((File)fileEntry).getName().endsWith(".png") || ((File)fileEntry).getName().endsWith(".jpg")) {
                        fileLeaf.setImage(ResourceManager.getPluginImage(this.getPackageName(), "icons/picture.png"));
                    } else if (((File)fileEntry).getName().endsWith(".csv")) {
                        fileLeaf.setImage(ResourceManager.getPluginImage(this.getPackageName(), "icons/table.png"));
                    } else if (((File)fileEntry).getName().endsWith(".pdf")) {
                        fileLeaf.setImage(ResourceManager.getPluginImage(this.getPackageName(), "icons/pdf_icon.png"));
                    } else {
                        fileLeaf.setImage(ResourceManager.getPluginImage(this.getPackageName(), "icons/unknown.png"));
                    }
                }
            }
            ++n2;
        }
    }

    @Override
    public boolean copyFile(File sourceFile, File destinationFile) {
        ProjectExplorerManager projMan;
        block6: {
            projMan = new ProjectExplorerManager();
            if (sourceFile.getAbsolutePath().equals(destinationFile.getAbsolutePath())) {
                return true;
            }
            System.out.println("Source: " + sourceFile.getAbsolutePath());
            System.out.println("Destination: " + destinationFile.getAbsolutePath());
            if (!destinationFile.exists()) break block6;
            String message = "The file '" + destinationFile.getName().toString() + "' already exist in the  folder. Do you want to overwrite the file? \n\n\n WARNING: This action is permanent";
            boolean isOverwrite = MessageDialog.openQuestion((Shell)Display.getCurrent().getActiveShell(), (String)"Confirm", (String)message);
            if (isOverwrite) break block6;
            return false;
        }
        try {
            int len;
            FileInputStream is = new FileInputStream(sourceFile);
            FileOutputStream os = new FileOutputStream(destinationFile);
            byte[] buf = new byte[1024];
            while ((len = ((InputStream)is).read(buf)) > 0) {
                ((OutputStream)os).write(buf, 0, len);
            }
            ((InputStream)is).close();
            ((OutputStream)os).close();
            projMan.deleteFile(destinationFile.getAbsolutePath().replace(".csv", RJavaManagerInitializer.VARINFO_ORIGINALFILE_EXTENSION));
            projMan.deleteFile(destinationFile.getAbsolutePath().replace(".csv", RJavaManagerInitializer.VARINFO_TMPFILE_EXTENSION));
            destinationFile.renameTo(destinationFile);
            return true;
        }
        catch (FileNotFoundException fileNotFoundException) {
            System.out.println("File not found");
            return false;
        }
        catch (IOException iOException) {
            return false;
        }
    }

    /*
     * Enabled aggressive exception aggregation
     */
    @Override
    public boolean copyBatchFile(File[] sourceFiles, File destinationFolder) {
        StarAnalysisUtilities.testVarArgs(sourceFiles);
        boolean overwriteYesToAll = false;
        System.out.println("called copy batch file.");
        String errMessage = null;
        int i = sourceFiles.length - 1;
        while (i >= 0) {
            File destinationFile = new File(String.valueOf(destinationFolder.getPath()) + "\\" + sourceFiles[i].getName());
            if (sourceFiles[i].isDirectory()) {
                if (!sourceFiles[i].getAbsolutePath().equals(destinationFolder.getAbsolutePath())) {
                    this.copyDirectory(sourceFiles[i], destinationFile);
                }
            } else if (sourceFiles[i].isFile()) {
                try {
                    int dialogResult = -1;
                    if (destinationFile.exists() && !overwriteYesToAll) {
                        String message = "The file '" + destinationFile.getName().toString() + "' already exist in the destination folder. Do you want to overwrite the file? \n\n\n WARNING: This action is permanent";
                        MessageDialog dialogOverwrite = new MessageDialog(Display.getCurrent().getActiveShell(), "Confirm Overwrite", null, message, 5, new String[]{"Yes", "Yes To All", "No", "No to All"}, 0);
                        dialogResult = dialogOverwrite.open();
                        if (dialogResult == 3) {
                            return false;
                        }
                        if (dialogResult == 1) {
                            overwriteYesToAll = true;
                        }
                    }
                    if (overwriteYesToAll || dialogResult == 0 || !destinationFile.exists()) {
                        FileInputStream is = new FileInputStream(sourceFiles[i]);
                        FileOutputStream os = new FileOutputStream(destinationFile);
                        byte[] buf = new byte[1024];
                        if (!PartStackHandler.isOpen(destinationFile.getAbsolutePath())) {
                            int len;
                            while ((len = ((InputStream)is).read(buf)) > 0) {
                                ((OutputStream)os).write(buf, 0, len);
                            }
                        } else {
                            errMessage = errMessage == null ? destinationFile.getName() : String.valueOf(errMessage) + ", " + destinationFile.getName();
                        }
                        ((InputStream)is).close();
                        ((OutputStream)os).close();
                        destinationFile.renameTo(destinationFile);
                    }
                }
                catch (FileNotFoundException fileNotFoundException) {
                    System.out.println("File not found");
                    return false;
                }
                catch (IOException iOException) {
                    return false;
                }
            }
            --i;
        }
        if (errMessage != null) {
            MessageDialog.openError((Shell)Display.getCurrent().getActiveShell(), (String)"Error File Operation", (String)("The file(s) [" + errMessage + "]" + " is still active in the application. Close the files first."));
        }
        return true;
    }

    @Override
    public boolean copyDirectory(File sourceDir, File destDir) {
        if (!destDir.exists()) {
            destDir.mkdir();
        }
        StarAnalysisUtilities.testVarArgs(sourceDir.listFiles(), sourceDir.getPath(), destDir);
        return this.copyBatchFile(sourceDir.listFiles(), destDir);
    }

    @Override
    public boolean renameFile(String filePath, String newFileName) {
        File projectFile = new File(filePath);
        Object[] fileExeceptions = new String[]{String.valueOf(this.getProjectPath()) + "\\Data", String.valueOf(this.getProjectPath()) + "\\Output"};
        if (Arrays.binarySearch(fileExeceptions, projectFile.getAbsolutePath().toString()) > -1) {
            return false;
        }
        projectFile.getName();
        int i = 0;
        while (i < 20) {
            if (projectFile.renameTo(new File(String.valueOf(projectFile.getParentFile().getAbsolutePath()) + "\\" + newFileName))) {
                File tmpFile;
                if (filePath.endsWith(".csv") && (tmpFile = new File(filePath.replace(".csv", RJavaManagerInitializer.VARINFO_TMPFILE_EXTENSION))).exists()) {
                    tmpFile.renameTo(new File(String.valueOf(tmpFile.getParentFile().getPath()) + "\\" + newFileName.replace(".csv", RJavaManagerInitializer.VARINFO_TMPFILE_EXTENSION)));
                }
                return true;
            }
            try {
                Thread.sleep(50L);
            }
            catch (InterruptedException interruptedException) {}
            ++i;
        }
        return false;
    }

    @Override
    public void copyToClipboard(String[] fileName) {
        Display display = Display.getCurrent();
        Clipboard clipboard = new Clipboard(display);
        clipboard.setContents(new Object[]{fileName}, new Transfer[]{FileTransfer.getInstance()});
        clipboard.dispose();
    }

    @Override
    public void pasteFromClipboard(String folderPath, String projectPath) {
        Display display = Display.getCurrent();
        Clipboard clipboard = new Clipboard(display);
        String[] data = (String[])clipboard.getContents((Transfer)FileTransfer.getInstance());
        if (data == null) {
            return;
        }
        String errMessage = null;
        int i = 0;
        while (i < data.length) {
            File clipboardFile = new File(data[i]);
            if (new File(String.valueOf(folderPath) + "\\" + clipboardFile.getName()).exists()) {
                if (!PartStackHandler.isOpen(new File(String.valueOf(folderPath) + "\\" + clipboardFile.getName()).getAbsolutePath())) {
                    InputDialog dlgRenameFile = new InputDialog(Display.getCurrent().getActiveShell(), "STAR - File Conflicts", "Enter the new file name", "Copy of " + clipboardFile.getName(), (IInputValidator)new CheckFileExistValidator(clipboardFile.getName(), folderPath, true));
                    if (dlgRenameFile.open() == 0) {
                        this.toImport(data[i], String.valueOf(folderPath) + "\\" + dlgRenameFile.getValue(), projectPath);
                    }
                } else {
                    errMessage = errMessage == null ? clipboardFile.getName() : String.valueOf(errMessage) + ", " + clipboardFile.getName();
                }
            } else {
                this.toImport(data[i], String.valueOf(folderPath) + "\\" + clipboardFile.getName(), projectPath);
            }
            ++i;
        }
        if (errMessage != null) {
            MessageDialog.openError((Shell)Display.getCurrent().getActiveShell(), (String)"File Operation Error", (String)("Error overwriting the file(s) [" + errMessage + "]. Make sure all the files are closed. "));
        }
    }

    @Override
    public void refreshFolder(ProjectExplorerTreeNodeModel folderNode) {
        folderNode.getTreeItem().removeAll();
        this.listFolder(new File(folderNode.getProjectFile().getPath()), folderNode.getTreeItem());
        this.listFiles(new File(folderNode.getProjectFile().getPath()), folderNode.getTreeItem());
        PartStackHandler.getStackChildrens();
    }

    @Override
    public boolean renameProject(String projectName) {
        String fromDir = this.getProjectPath();
        String toDir = String.valueOf(this.getWorkspacePath()) + "\\" + projectName;
        File from = new File(fromDir);
        if (!from.exists() || !from.isDirectory()) {
            System.out.println("Directory does not exist: " + fromDir);
            return false;
        }
        File to = new File(toDir);
        return from.renameTo(to);
    }

    @Override
    public boolean saveLastOpenedProject(String projectName) {
        Properties starINI = new Properties();
        try {
            starINI.load(new FileInputStream(this.getApplicationINI()));
            starINI.setProperty("lastOpenedProject", projectName);
            starINI.store(new FileOutputStream(this.getApplicationINI()), "                            ");
            return true;
        }
        catch (FileNotFoundException fileNotFoundException) {
            return false;
        }
        catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean saveWorkspacePath(String workspacePath) {
        Properties starINI = new Properties();
        try {
            starINI.load(new FileInputStream(this.getApplicationINI()));
            starINI.setProperty("workspacePath", workspacePath);
            starINI.store(new FileOutputStream(this.getApplicationINI()), "                            ");
            return true;
        }
        catch (FileNotFoundException fileNotFoundException) {
            return false;
        }
        catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean toImport(String importDestination, String optionalOutputPath, String projectPath) {
        boolean retValue = true;
        System.out.println(String.valueOf(importDestination) + "<<<< Import Destination");
        System.out.println(String.valueOf(optionalOutputPath) + "<<<< optionalOutputPath");
        File ImportFiletoSelectedFolder = new File(importDestination);
        if (optionalOutputPath == null) {
            optionalOutputPath = String.valueOf(projectPath) + "\\Data\\" + ImportFiletoSelectedFolder.getName();
        }
        File OutputFile = new File(optionalOutputPath);
        if (ImportFiletoSelectedFolder.isDirectory()) {
            this.copyDirectory(ImportFiletoSelectedFolder, OutputFile);
        } else {
            this.copyFile(ImportFiletoSelectedFolder, OutputFile);
        }
        return retValue;
    }

    @Override
    public boolean redraw() {
        return false;
    }

    @Override
    public boolean removeAllTreeItems(TreeItem projectTree) {
        projectTree.removeAll();
        projectTree.setText("");
        return true;
    }

    @Override
    public void createNewFolder(String parentDestinationPath, String folderName) {
        File newFolder = new File(String.valueOf(parentDestinationPath) + "\\" + folderName);
        newFolder.mkdir();
    }

    @PostConstruct
    public void postCreate() {
    }
}

