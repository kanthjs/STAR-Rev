/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.inject.Inject
 *  org.eclipse.core.runtime.ILogListener
 *  org.eclipse.core.runtime.IStatus
 *  org.eclipse.core.runtime.Platform
 *  org.eclipse.jface.dialogs.IInputValidator
 *  org.eclipse.jface.dialogs.InputDialog
 *  org.eclipse.jface.dialogs.MessageDialog
 *  org.eclipse.swt.SWTException
 *  org.eclipse.swt.dnd.Clipboard
 *  org.eclipse.swt.dnd.DragSource
 *  org.eclipse.swt.dnd.DragSourceAdapter
 *  org.eclipse.swt.dnd.DragSourceEvent
 *  org.eclipse.swt.dnd.DragSourceListener
 *  org.eclipse.swt.dnd.DropTarget
 *  org.eclipse.swt.dnd.DropTargetAdapter
 *  org.eclipse.swt.dnd.DropTargetEvent
 *  org.eclipse.swt.dnd.DropTargetListener
 *  org.eclipse.swt.dnd.FileTransfer
 *  org.eclipse.swt.dnd.Transfer
 *  org.eclipse.swt.layout.FillLayout
 *  org.eclipse.swt.widgets.Composite
 *  org.eclipse.swt.widgets.Control
 *  org.eclipse.swt.widgets.DirectoryDialog
 *  org.eclipse.swt.widgets.Display
 *  org.eclipse.swt.widgets.Event
 *  org.eclipse.swt.widgets.FileDialog
 *  org.eclipse.swt.widgets.Layout
 *  org.eclipse.swt.widgets.Listener
 *  org.eclipse.swt.widgets.Menu
 *  org.eclipse.swt.widgets.MenuItem
 *  org.eclipse.swt.widgets.Shell
 *  org.eclipse.swt.widgets.Tree
 *  org.eclipse.swt.widgets.TreeItem
 *  org.irri.breedingtool.rjava.manager.RJavaManager
 */
package org.irri.breedingtool.projectexplorer.view;

import java.io.File;
import java.util.ArrayList;
import java.util.regex.Pattern;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.eclipse.core.runtime.ILogListener;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.DragSource;
import org.eclipse.swt.dnd.DragSourceAdapter;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.DragSourceListener;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.dnd.DropTargetAdapter;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.DropTargetListener;
import org.eclipse.swt.dnd.FileTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.wb.swt.ResourceManager;
import org.eclipse.wb.swt.SWTResourceManager;
import org.irri.breedingtool.application.handler.MenuHandler;
import org.irri.breedingtool.application.handler.PartStackHandler;
import org.irri.breedingtool.application.model.ProjectExplorerTreeNodeModel;
import org.irri.breedingtool.datamanipulation.dialog.ChooseDelimiterDialog;
import org.irri.breedingtool.manager.impl.DataManipulationManager;
import org.irri.breedingtool.manager.impl.ProjectExplorerManager;
import org.irri.breedingtool.projectexplorer.dialog.ImportDataFromRDialog;
import org.irri.breedingtool.projectexplorer.dialog.ImportFromOtherProjectsDialog;
import org.irri.breedingtool.projectexplorer.dialog.MoveToDialog;
import org.irri.breedingtool.projectexplorer.dialog.NewProjectDialog;
import org.irri.breedingtool.projectexplorer.dialog.OpenProjectDialog;
import org.irri.breedingtool.projectexplorer.handler.DeleteProjectDialogHandler;
import org.irri.breedingtool.projectexplorer.view.ProjectTreeComponent;
import org.irri.breedingtool.projectexplorer.view.RJavaManagerInitializer;
import org.irri.breedingtool.rjava.manager.RJavaManager;
import org.irri.breedingtool.utility.CheckFileExistValidator;
import org.irri.breedingtool.utility.GeneralUtility;

public class ProjectExplorerView {
    ProjectTreeComponent projectTreeComponent;
    public static Tree rootTree;
    String projectLocation = "";
    public static TreeItem projectTree;
    public static RJavaManager rJavaManager;
    public static boolean ACTIVATED;
    ProjectExplorerManager projectExplorerManager = new ProjectExplorerManager();
    public static Shell parentShell;

    static {
        rJavaManager = new RJavaManager();
        ACTIVATED = false;
    }

    @Inject
    public ProjectExplorerView(Composite parent) {
        RJavaManagerInitializer.Initialize();
        Platform.addLogListener((ILogListener)new ILogListener(){

            public void logging(IStatus status, String plugin) {
            }
        });
        parent.getShell().setMaximized(true);
        Composite composite = new Composite(parent, 0);
        composite.setBackground(SWTResourceManager.getColor(1));
        composite.setLayout((Layout)new FillLayout(256));
        Display display = Display.getCurrent();
        Clipboard clipboard = new Clipboard(display);
        clipboard.clearContents();
        rootTree = new Tree(composite, 2050);
        projectTree = new TreeItem(rootTree, 0);
        if (!new File(String.valueOf(ProjectTreeComponent.getCurrentWorkspacePath()) + "//" + this.projectExplorerManager.getLastOpenedProject()).exists()) {
            projectTree.setText("No active project selected.");
            return;
        }
        this.projectTreeComponent = new ProjectTreeComponent(projectTree, this.projectExplorerManager.getLastOpenedProject());
        parent.getShell().setText(String.valueOf(RJavaManagerInitializer.APPLICATION_TITLE) + this.projectExplorerManager.getProjectPath());
        if (!new File(this.projectExplorerManager.getProjectPath()).exists()) {
            parent.getShell().setText(String.valueOf(RJavaManagerInitializer.APPLICATION_TITLE) + this.projectExplorerManager.getProjectPath());
        }
        parentShell = parent.getShell();
    }

    public void resetMenu() {
        this.setMenu();
    }

    private void setMenu() {
        TreeItem[] fileExeceptions;
        MenuItem menuItem;
        Menu menu = new Menu((Control)rootTree);
        rootTree.setMenu(menu);
        if (rootTree.getItemCount() == 0) {
            return;
        }
        if (projectTree == null || rootTree.getSelection()[0] == null) {
            return;
        }
        if (rootTree.getSelection()[0].getText().toString().length() > 3) {
            if (rootTree.getSelection()[0] == projectTree) {
                menuItem = new MenuItem(menu, 0);
                menuItem.setText("Rename Project");
                menuItem.addListener(13, this.listenerRenameProject());
                menuItem.setImage(ResourceManager.getPluginImage(this.projectExplorerManager.getPackageName(), "icons/renameFile.png"));
                menuItem = new MenuItem(menu, 0);
                menuItem.setText("Delete Project");
                menuItem.addListener(13, this.listenerDeleteProject());
                menuItem.setImage(ResourceManager.getPluginImage(this.projectExplorerManager.getPackageName(), "icons/deleteProject.png"));
                menuItem = new MenuItem(menu, 0);
                menuItem.setText("Export Project");
                menuItem.addListener(13, this.listenerExportProject());
            }
        } else {
            return;
        }
        File selectedFile = ((ProjectExplorerTreeNodeModel)rootTree.getSelection()[0].getData()).getProjectFile();
        ProjectExplorerManager projectExplorerManager = new ProjectExplorerManager();
        if ((projectExplorerManager.getDataFolder(projectTree).equals(rootTree.getSelection()[0]) || selectedFile.getAbsolutePath().contains(((ProjectExplorerTreeNodeModel)projectExplorerManager.getDataFolder(projectTree).getData()).getProjectFile().getPath())) && selectedFile.isDirectory()) {
            menuItem = new MenuItem(menu, 0);
            menuItem.setText("Import Data");
            menuItem.addListener(13, this.listenerImportData(rootTree.getSelection()[0]));
            menuItem.setImage(ResourceManager.getPluginImage(projectExplorerManager.getPackageName(), "icons/importData.png"));
            menuItem = new MenuItem(menu, 0);
            menuItem.setText("Import Data from Other Project");
            menuItem.addListener(13, this.listenerImportDataFromOtherProject(rootTree.getSelection()[0]));
            menuItem.setImage(ResourceManager.getPluginImage(projectExplorerManager.getPackageName(), "icons/importFromOtherProjects.png"));
            menuItem = new MenuItem(menu, 0);
            menuItem.setText("Import Data from Package");
            menuItem.addListener(13, this.listenerImportDataFromPackage(rootTree.getSelection()[0]));
            new MenuItem(menu, 2);
        }
        if (!this.compareTreeItemArray(fileExeceptions = new TreeItem[]{projectExplorerManager.getDataFolder(projectTree), projectExplorerManager.getOutputFolder(projectTree), projectTree}, rootTree.getSelection())) {
            if (rootTree.getSelectionCount() == 1) {
                menuItem = new MenuItem(menu, 0);
                menuItem.setText("Rename");
                menuItem.addListener(13, this.listenerRenameFile());
                menuItem.setImage(ResourceManager.getPluginImage(projectExplorerManager.getPackageName(), "icons/renameFile.png"));
            }
            menuItem = new MenuItem(menu, 0);
            menuItem.setText("Delete");
            menuItem.addListener(13, this.listenerDeleteFile());
            menuItem.setImage(ResourceManager.getPluginImage(projectExplorerManager.getPackageName(), "icons/deleteFile.png"));
            menuItem = new MenuItem(menu, 0);
            menuItem.setText("Copy");
            menuItem.addListener(13, this.listenerCopyFile());
            menuItem.setImage(ResourceManager.getPluginImage(projectExplorerManager.getPackageName(), "icons/copyFile.png"));
            menuItem = new MenuItem(menu, 0);
            menuItem.setText("Move to");
            menuItem.addListener(13, this.listenerMoveTo());
            menuItem.setImage(ResourceManager.getPluginImage(projectExplorerManager.getPackageName(), "icons/moveTo.png"));
        }
        if (rootTree.getSelection()[0] != projectTree) {
            if (!GeneralUtility.isClipboardEmpty()) {
                menuItem = new MenuItem(menu, 0);
                menuItem.setText("Paste");
                menuItem.addListener(13, this.listenerPasteFile());
                menuItem.setImage(ResourceManager.getPluginImage(projectExplorerManager.getPackageName(), "icons/pasteFile.png"));
            }
            menuItem = new MenuItem(menu, 0);
            menuItem.setText("Create New Folder");
            menuItem.addListener(13, this.listenerCreateNewFolder());
            menuItem.setImage(ResourceManager.getPluginImage(projectExplorerManager.getPackageName(), "icons/newFolder.png"));
            menuItem = new MenuItem(menu, 0);
            menuItem.setText("Refresh");
            menuItem.addListener(13, this.listenerRefreshFolder());
            menuItem.setImage(ResourceManager.getPluginImage(projectExplorerManager.getPackageName(), "icons/refresh.png"));
        }
    }

    private Listener listenerCreateNewFolder() {
        Listener menuListener = new Listener(){

            public void handleEvent(Event event) {
                ProjectExplorerManager projectMan = new ProjectExplorerManager();
                if (!((ProjectExplorerTreeNodeModel)rootTree.getSelection()[0].getData()).getProjectFile().isDirectory()) {
                    InputDialog dlgNewFolderFile = new InputDialog(Display.getCurrent().getActiveShell(), "New Folder", "Enter the new folder name", rootTree.getSelection()[0].getText(), (IInputValidator)new CheckFileExistValidator("New Folder", ((ProjectExplorerTreeNodeModel)rootTree.getSelection()[0].getParentItem().getData()).getProjectFile().getPath(), false));
                    dlgNewFolderFile.open();
                    projectMan.createNewFolder(((ProjectExplorerTreeNodeModel)rootTree.getSelection()[0].getParentItem().getData()).getProjectFile().getPath(), dlgNewFolderFile.getValue());
                    projectMan.refreshFolder((ProjectExplorerTreeNodeModel)rootTree.getSelection()[0].getParentItem().getData());
                } else {
                    InputDialog dlgNewFolderFile = new InputDialog(Display.getCurrent().getActiveShell(), "New Folder", "Enter the new folder name", rootTree.getSelection()[0].getText(), (IInputValidator)new CheckFileExistValidator("New Folder", ((ProjectExplorerTreeNodeModel)rootTree.getSelection()[0].getData()).getProjectFile().getPath(), false));
                    dlgNewFolderFile.open();
                    projectMan.createNewFolder(((ProjectExplorerTreeNodeModel)rootTree.getSelection()[0].getData()).getProjectFile().getPath(), dlgNewFolderFile.getValue());
                    projectMan.refreshFolder((ProjectExplorerTreeNodeModel)rootTree.getSelection()[0].getData());
                }
            }
        };
        return menuListener;
    }

    private Listener listenerRenameProject() {
        Listener menuListener = new Listener(){

            public void handleEvent(Event event) {
                ProjectExplorerManager projectMan = new ProjectExplorerManager();
                InputDialog dlgRenameFile = new InputDialog(Display.getCurrent().getActiveShell(), "Rename Project", "Enter the new Project Name", projectTree.getText(), (IInputValidator)new CheckFileExistValidator(projectTree.getText().toString(), ProjectTreeComponent.getCurrentWorkspacePath(), false));
                if (dlgRenameFile.open() == 0 && projectMan.renameProject(dlgRenameFile.getValue())) {
                    new ProjectTreeComponent(projectTree, dlgRenameFile.getValue());
                    PartStackHandler.removeAll();
                }
            }
        };
        return menuListener;
    }

    private Listener listenerExportProject() {
        Listener menuListener = new Listener(){

            public void handleEvent(Event event) {
                DirectoryDialog dlg = new DirectoryDialog(Display.getCurrent().getActiveShell());
                dlg.setText("Export Project");
                dlg.setMessage("Select a Directory");
                String dir = dlg.open();
                if (dir != null) {
                    boolean isOverwrite = true;
                    String message = "The Project: '" + new File(String.valueOf(dir) + File.separator + projectTree.getText().toString()).getName().toString() + "' already exist in the chosen folder. Do you want to overwrite the Project? \n\n\n WARNING: This action is permanent";
                    if (new File(String.valueOf(dir) + File.separator + projectTree.getText().toString()).exists()) {
                        isOverwrite = MessageDialog.openQuestion((Shell)Display.getCurrent().getActiveShell(), (String)"Confirm", (String)message);
                    }
                    if (isOverwrite) {
                        ProjectExplorerManager projectMan = new ProjectExplorerManager();
                        if (new File(String.valueOf(dir) + File.separator + projectTree.getText().toString()).exists()) {
                            projectMan.deleteFolder(new File(String.valueOf(dir) + File.separator + projectTree.getText().toString()));
                        }
                        projectMan.copyDirectory(new File(projectMan.getProjectPath()), new File(String.valueOf(dir) + File.separator + projectTree.getText().toString()));
                    }
                }
            }
        };
        return menuListener;
    }

    private Listener listenerMoveTo() {
        Listener menuListener = new Listener(){

            public void handleEvent(Event event) {
                MoveToDialog moveToDialog = new MoveToDialog(Display.getCurrent().getActiveShell());
                File destinationFolder = moveToDialog.open();
                ProjectExplorerManager projectMan = new ProjectExplorerManager();
                String errMessage = null;
                if (destinationFolder == null) {
                    return;
                }
                int i = 0;
                while (i < rootTree.getSelectionCount()) {
                    if (!((ProjectExplorerTreeNodeModel)rootTree.getSelection()[i].getData()).getProjectFile().getPath().equals(destinationFolder.getPath())) {
                        if (PartStackHandler.isOpen(((ProjectExplorerTreeNodeModel)rootTree.getSelection()[i].getData()).getProjectFile().getAbsolutePath()) || PartStackHandler.isOpen(String.valueOf(destinationFolder.getPath()) + File.separator + rootTree.getSelection()[i].getText())) {
                            errMessage = errMessage == null ? ((ProjectExplorerTreeNodeModel)rootTree.getSelection()[i].getData()).getProjectFile().getName() : String.valueOf(errMessage) + ", " + ((ProjectExplorerTreeNodeModel)rootTree.getSelection()[i].getData()).getProjectFile().getName();
                        } else if (((ProjectExplorerTreeNodeModel)rootTree.getSelection()[i].getData()).getProjectFile().isDirectory()) {
                            boolean toDelete = projectMan.copyDirectory(((ProjectExplorerTreeNodeModel)rootTree.getSelection()[i].getData()).getProjectFile(), new File(String.valueOf(destinationFolder.getAbsolutePath()) + File.separator + rootTree.getSelection()[i].getText()));
                            if (toDelete) {
                                projectMan.deleteFolder(((ProjectExplorerTreeNodeModel)rootTree.getSelection()[i].getData()).getProjectFile());
                            }
                        } else {
                            Boolean toDelete = projectMan.copyFile(((ProjectExplorerTreeNodeModel)rootTree.getSelection()[i].getData()).getProjectFile(), new File(String.valueOf(destinationFolder.getPath().toString()) + File.separator + ((ProjectExplorerTreeNodeModel)rootTree.getSelection()[i].getData()).getProjectFile().getName()));
                            if (toDelete.booleanValue()) {
                                ((ProjectExplorerTreeNodeModel)rootTree.getSelection()[i].getData()).getProjectFile().delete();
                            }
                        }
                        if (errMessage != null) {
                            MessageDialog.openError((Shell)Display.getCurrent().getActiveShell(), (String)"Error File Operation", (String)("The file(s) [" + errMessage + "]" + " is still active in the application. Close the file(s) first."));
                            return;
                        }
                    }
                    ++i;
                }
                projectMan.refreshFolder((ProjectExplorerTreeNodeModel)projectMan.getDataFolder(projectTree).getData());
                projectMan.refreshFolder((ProjectExplorerTreeNodeModel)projectMan.getOutputFolder(projectTree).getData());
            }
        };
        return menuListener;
    }

    private Listener listenerRefreshFolder() {
        Listener menuListener = new Listener(){

            public void handleEvent(Event event) {
                ProjectExplorerTreeNodeModel fileModel = (ProjectExplorerTreeNodeModel)rootTree.getSelection()[0].getData();
                if (!fileModel.getProjectFile().isDirectory()) {
                    fileModel = (ProjectExplorerTreeNodeModel)rootTree.getSelection()[0].getParentItem().getData();
                }
                ProjectExplorerManager projMan = new ProjectExplorerManager();
                projMan.refreshFolder(fileModel);
            }
        };
        return menuListener;
    }

    private Listener listenerPasteFile() {
        Listener menuListener = new Listener(){

            public void handleEvent(Event event) {
                ProjectExplorerTreeNodeModel fileModel = (ProjectExplorerTreeNodeModel)rootTree.getSelection()[0].getData();
                if (!fileModel.getProjectFile().isDirectory()) {
                    fileModel = (ProjectExplorerTreeNodeModel)rootTree.getSelection()[0].getParentItem().getData();
                }
                ProjectExplorerManager projMan = new ProjectExplorerManager();
                projMan.pasteFromClipboard(fileModel.getProjectFile().getPath().toString(), ProjectTreeComponent.PROJECT_PATH);
                projMan.refreshFolder(fileModel);
            }
        };
        return menuListener;
    }

    private Listener listenerCopyFile() {
        Listener menuListener = new Listener(){

            public void handleEvent(Event event) {
                TreeItem[] selection = rootTree.getSelection();
                String[] fileNames = new String[selection.length];
                fileNames[0] = null;
                int i = 0;
                while (i < selection.length) {
                    System.out.println(fileNames[i]);
                    ProjectExplorerTreeNodeModel fileModel = (ProjectExplorerTreeNodeModel)selection[i].getData();
                    fileNames[i] = fileModel.getProjectFile().getPath();
                    ++i;
                }
                ProjectExplorerView.this.projectExplorerManager.copyToClipboard(fileNames);
            }
        };
        return menuListener;
    }

    private Listener listenerRenameFile() {
        Listener menuListener = new Listener(){

            public void handleEvent(Event event) {
                String filePath = ((ProjectExplorerTreeNodeModel)rootTree.getSelection()[0].getData()).getProjectFile().getPath();
                if (PartStackHandler.isOpen(filePath)) {
                    MessageDialog.openError((Shell)Display.getCurrent().getActiveShell(), (String)"File Operation Error", (String)("Could not rename file: " + new File(filePath).getName() + " . Close the file first"));
                    return;
                }
                InputDialog dlgRenameFile = new InputDialog(Display.getCurrent().getActiveShell(), "Rename File", "Enter the new file name", rootTree.getSelection()[0].getText(), (IInputValidator)new CheckFileExistValidator(rootTree.getSelection()[0].getText().toString(), ((ProjectExplorerTreeNodeModel)rootTree.getSelection()[0].getParentItem().getData()).getProjectFile().getPath(), false));
                if (dlgRenameFile.open() == 0) {
                    String newFileName;
                    if (new File(filePath).isDirectory()) {
                        newFileName = dlgRenameFile.getValue();
                    } else {
                        String string = newFileName = dlgRenameFile.getValue().contains(GeneralUtility.getFileExtension(filePath)) ? dlgRenameFile.getValue() : String.valueOf(dlgRenameFile.getValue()) + "." + GeneralUtility.getFileExtension(filePath);
                    }
                    if (ProjectExplorerView.this.projectExplorerManager.renameFile(filePath, newFileName)) {
                        ProjectExplorerManager projectMan = new ProjectExplorerManager();
                        projectMan.refreshFolder((ProjectExplorerTreeNodeModel)rootTree.getSelection()[0].getParentItem().getData());
                    }
                }
            }
        };
        return menuListener;
    }

    private Listener listenerDeleteFile() {
        Listener menuListener = new Listener(){

            public void handleEvent(Event event) {
                TreeItem[] selection = rootTree.getSelection();
                String errMessage = null;
                ArrayList<TreeItem> parentSelection = new ArrayList<TreeItem>();
                boolean confirm = false;
                if (selection.length <= 1) {
                    if (MessageDialog.openQuestion((Shell)Display.getCurrent().getActiveShell(), (String)"Confirm Delete", (String)("Are you sure you want to delete '" + selection[0].getText() + "'?  \n\n\n This file will be moved to Trash/Recycle Bin"))) {
                        confirm = true;
                    }
                } else if (MessageDialog.openQuestion((Shell)Display.getCurrent().getActiveShell(), (String)"Confirm Delete", (String)("Are you sure you want to delete these " + selection.length + " elements?  \n\n\n These files will be moved to Trash/Recycle Bin"))) {
                    confirm = true;
                }
                if (confirm) {
                    int i = 0;
                    while (i < selection.length) {
                        ProjectExplorerTreeNodeModel fileModel;
                        if (PartStackHandler.isOpen(((ProjectExplorerTreeNodeModel)selection[i].getData()).getProjectFile().getAbsolutePath())) {
                            errMessage = errMessage == null ? ((ProjectExplorerTreeNodeModel)selection[i].getData()).getProjectFile().getName() : String.valueOf(errMessage) + ", " + ((ProjectExplorerTreeNodeModel)selection[i].getData()).getProjectFile().getName();
                        } else if (!selection[i].isDisposed() && ProjectExplorerView.this.projectExplorerManager.deleteFile((fileModel = (ProjectExplorerTreeNodeModel)selection[i].getData()).getProjectFile().getPath().toString())) {
                            parentSelection.add(selection[i].getParentItem());
                        }
                        ++i;
                    }
                    if (errMessage != null) {
                        MessageDialog.openError((Shell)Display.getCurrent().getActiveShell(), (String)"Error File Operation", (String)("The file(s) [" + errMessage + "]" + " is still active in the application. Close the files first."));
                    } else {
                        i = 0;
                        while (i < parentSelection.size()) {
                            if (!((TreeItem)parentSelection.get(i)).isDisposed()) {
                                ProjectExplorerView.this.projectExplorerManager.refreshFolder((ProjectExplorerTreeNodeModel)((TreeItem)parentSelection.get(i)).getData());
                            }
                            ++i;
                        }
                    }
                }
            }
        };
        return menuListener;
    }

    private Listener listenerNewProject(MenuItem menuItem) {
        Listener menuListener = new Listener(){

            public void handleEvent(Event event) {
                NewProjectDialog newProjectDialog = new NewProjectDialog(Display.getCurrent().getActiveShell());
                if (newProjectDialog.open() == 0) {
                    ProjectExplorerView.this.projectTreeComponent = new ProjectTreeComponent(projectTree, newProjectDialog.getGetProjectName());
                }
            }
        };
        return menuListener;
    }

    private Listener listenerDeleteProject() {
        Listener menuListener = new Listener(){

            public void handleEvent(Event event) {
                DeleteProjectDialogHandler.deleteProject(Display.getCurrent().getActiveShell());
                ProjectExplorerView.this.resetMenu();
            }
        };
        return menuListener;
    }

    private Listener listenerOpenExistingProject(MenuItem menuItem) {
        Listener menuListener = new Listener(){

            public void handleEvent(Event event) {
                OpenProjectDialog openProjectDialog = new OpenProjectDialog(Display.getCurrent().getActiveShell());
                if (openProjectDialog.open() == 0) {
                    ProjectExplorerView.this.projectTreeComponent = new ProjectTreeComponent(projectTree, openProjectDialog.getGetProjectName());
                }
            }
        };
        return menuListener;
    }

    private Listener listenerImportData(TreeItem treeItem) {
        Listener menuListener = new Listener(){
            private boolean displayedWarningMessage = false;

            public void handleEvent(Event event) {
                DataManipulationManager dataManipulationManager = new DataManipulationManager();
                FileDialog fileDialog = new FileDialog(Display.getCurrent().getActiveShell(), 2);
                fileDialog.setText("Import");
                String[] filterExt = new String[]{"*.csv", "*.rda", "*.txt"};
                fileDialog.setFilterPath(((ProjectExplorerTreeNodeModel)ProjectExplorerView.this.projectExplorerManager.getOutputFolder(projectTree).getData()).getProjectFile().getAbsolutePath());
                fileDialog.setFilterExtensions(filterExt);
                String selectedFile = fileDialog.open();
                if (selectedFile == null) {
                    return;
                }
                StringBuffer buf = new StringBuffer();
                String[] files = fileDialog.getFileNames();
                int i = 0;
                int n = files.length;
                while (i < n) {
                    buf.append(fileDialog.getFilterPath());
                    if (buf.charAt(buf.length() - 1) != File.separatorChar) {
                        buf.append(File.separatorChar);
                    }
                    buf.append(files[i]);
                    buf.append(";");
                    ++i;
                }
                String source = buf.toString();
                String refreshFolderPath = null;
                String[] sources = source.split(";");
                if (sources[0].endsWith(".csv")) {
                    File destinationFolder = ((ProjectExplorerTreeNodeModel)treeItem.getData()).getProjectFile();
                    ArrayList<File> sourceFileList = new ArrayList<File>();
                    int i2 = 0;
                    while (i2 < sources.length) {
                        System.out.println("source: " + sources[i2]);
                        sourceFileList.add(new File(sources[i2]));
                        ++i2;
                    }
                    File[] fileSources = sourceFileList.toArray(new File[sourceFileList.size()]);
                    destinationFolder = !destinationFolder.isDirectory() ? new File(String.valueOf(destinationFolder.getParentFile().getAbsolutePath()) + File.separator) : new File(String.valueOf(destinationFolder.getAbsolutePath()) + File.separator);
                    ProjectExplorerView.this.projectExplorerManager.copyBatchFile(fileSources, destinationFolder);
                    ProjectExplorerView.this.projectExplorerManager.refreshFolder(ProjectExplorerView.this.projectExplorerManager.getTreeNodeModelbyFilePath(destinationFolder.getAbsolutePath()));
                } else {
                    String[] stringArray = sources;
                    int n2 = sources.length;
                    int n3 = 0;
                    while (n3 < n2) {
                        String newDestinationFilePath;
                        String s;
                        String sourceFilePath = s = stringArray[n3];
                        System.out.println("Selected File: " + sourceFilePath);
                        String outputFileName = new File(s).getName();
                        File destination = ((ProjectExplorerTreeNodeModel)treeItem.getData()).getProjectFile();
                        destination = !destination.isDirectory() ? new File(String.valueOf(destination.getParentFile().getAbsolutePath()) + File.separator + outputFileName) : new File(String.valueOf(destination.getAbsolutePath()) + File.separator + outputFileName);
                        if (PartStackHandler.isOpen(String.valueOf(ProjectExplorerView.this.projectExplorerManager.getProjectPath()) + File.separator + "Data" + File.separator + sourceFilePath)) {
                            MessageDialog.openError((Shell)Display.getCurrent().getActiveShell(), (String)"Error File Operation", (String)("Could not import file: " + sourceFilePath + ". An active data with same filename was detected. Close the file first."));
                            return;
                        }
                        String destinationFilePath = destination.getAbsolutePath();
                        new File(s).getName();
                        refreshFolderPath = destinationFilePath;
                        if (s.endsWith(".rda")) {
                            destinationFilePath = destinationFilePath.replace(".rda", ".csv");
                            newDestinationFilePath = destinationFilePath.replace(".csv", dataManipulationManager.getManipulatedFileNameExtension("", destinationFilePath));
                            sourceFilePath = s.replace(".rda", ".csv");
                            if (!this.displayedWarningMessage && MessageDialog.openConfirm((Shell)Display.getCurrent().getActiveShell(), (String)"Convert RDA to CSV", (String)"You have imported an R Data File.\n\nIt will automatically be converted to a csv file.")) {
                                this.displayedWarningMessage = true;
                            }
                            if (this.displayedWarningMessage) {
                                if (!destinationFilePath.equals(newDestinationFilePath)) {
                                    MessageDialog.openInformation((Shell)Display.getCurrent().getActiveShell(), (String)("File '" + new File(destinationFilePath).getName() + "' already exist."), (String)("File name will be changed to '" + new File(newDestinationFilePath).getName() + "'."));
                                    rJavaManager.getRJavaDataManipulationManager().convertRdaToCsv(selectedFile.replaceAll(Pattern.quote(File.separator), "/"), newDestinationFilePath.replaceAll(Pattern.quote(File.separator), "/"));
                                } else {
                                    rJavaManager.getRJavaDataManipulationManager().convertRdaToCsv(selectedFile.replaceAll(Pattern.quote(File.separator), "/"), destinationFilePath.replaceAll(Pattern.quote(File.separator), "/"));
                                }
                                ProjectExplorerView.this.projectExplorerManager.refreshFolder(ProjectExplorerView.this.projectExplorerManager.getTreeNodeModelbyFilePath(new File(refreshFolderPath).getParentFile().getAbsolutePath()));
                            }
                        } else if (s.endsWith(".txt")) {
                            destinationFilePath = destinationFilePath.replace(".txt", ".csv");
                            newDestinationFilePath = destinationFilePath.replace(".csv", dataManipulationManager.getManipulatedFileNameExtension("", destinationFilePath));
                            sourceFilePath = s.replace(".txt", ".csv");
                            if (!this.displayedWarningMessage && MessageDialog.openConfirm((Shell)Display.getCurrent().getActiveShell(), (String)"Convert TXT to CSV", (String)"You have imported a txt file.\n\nIt will automatically be converted to a csv file.")) {
                                this.displayedWarningMessage = true;
                            }
                            if (this.displayedWarningMessage) {
                                ChooseDelimiterDialog chooseDelimiter = new ChooseDelimiterDialog(Display.getCurrent().getActiveShell(), true, dataManipulationManager.getDisplayFileName(new File(s).getName()));
                                chooseDelimiter.open();
                                String delimiter = chooseDelimiter.getDelimiter();
                                if (chooseDelimiter.getReturnCode() == 0 && !delimiter.equals("")) {
                                    if (!destinationFilePath.equals(newDestinationFilePath)) {
                                        MessageDialog.openInformation((Shell)Display.getCurrent().getActiveShell(), (String)("File '" + new File(destinationFilePath).getName() + "' already exist."), (String)("File name will be changed to '" + new File(newDestinationFilePath).getName() + "'."));
                                        dataManipulationManager.convertTxtToCsv(s, newDestinationFilePath, delimiter);
                                    } else {
                                        dataManipulationManager.convertTxtToCsv(s, destinationFilePath, delimiter);
                                    }
                                    ProjectExplorerView.this.projectExplorerManager.refreshFolder(ProjectExplorerView.this.projectExplorerManager.getTreeNodeModelbyFilePath(new File(refreshFolderPath).getParentFile().getAbsolutePath()));
                                }
                            }
                        }
                        ++n3;
                    }
                }
            }
        };
        return menuListener;
    }

    private Listener listenerImportDataFromOtherProject(TreeItem treeItem) {
        Listener menuListener = new Listener(){

            public void handleEvent(Event event) {
                ImportFromOtherProjectsDialog importDialog = new ImportFromOtherProjectsDialog(Display.getCurrent().getActiveShell());
                ArrayList<File> returnData = importDialog.open();
                if (returnData != null && !returnData.isEmpty()) {
                    ProjectExplorerManager projectMan = new ProjectExplorerManager();
                    String errMessage = null;
                    int i = 0;
                    while (i < returnData.size()) {
                        if (PartStackHandler.isOpen(String.valueOf(projectMan.getProjectPath()) + File.separator + "Data" + File.separator + returnData.get(i).getName())) {
                            errMessage = errMessage == null ? returnData.get(i).getName() : String.valueOf(errMessage) + ", " + returnData.get(i).getName();
                            returnData.remove(i);
                        }
                        ++i;
                    }
                    i = 0;
                    while (i < returnData.size()) {
                        if (returnData.get(i).toString().endsWith(".csv") || returnData.get(i).toString().endsWith(".rda") || new File(returnData.get(i).toString()).isDirectory()) {
                            File destination = ((ProjectExplorerTreeNodeModel)treeItem.getData()).getProjectFile();
                            destination = !destination.isDirectory() ? new File(String.valueOf(destination.getParentFile().getAbsolutePath()) + File.separator + returnData.get(i).getName()) : new File(String.valueOf(destination.getAbsolutePath()) + File.separator + returnData.get(i).getName());
                            projectMan.toImport(returnData.get(i).toString(), destination.getPath(), ProjectTreeComponent.PROJECT_PATH);
                        }
                        ++i;
                    }
                    ProjectExplorerView.this.projectExplorerManager.refreshFolder((ProjectExplorerTreeNodeModel)ProjectExplorerView.this.projectExplorerManager.getDataFolder(projectTree).getData());
                    if (errMessage != null) {
                        MessageDialog.openError((Shell)Display.getCurrent().getActiveShell(), (String)"Error File Operation", (String)("The file(s) [" + errMessage + "]" + " is still active in the application. Close the files first."));
                    }
                }
            }
        };
        return menuListener;
    }

    private Listener listenerImportDataFromPackage(TreeItem treeItem) {
        Listener menuListener = new Listener(){

            public void handleEvent(Event event) {
                File destinationFolder = ((ProjectExplorerTreeNodeModel)treeItem.getData()).getProjectFile();
                destinationFolder = !destinationFolder.isDirectory() ? new File(destinationFolder.getParentFile().getAbsolutePath()) : new File(destinationFolder.getAbsolutePath());
                System.out.println("folder: " + destinationFolder.getAbsolutePath());
                ImportDataFromRDialog importFromRDialog = new ImportDataFromRDialog(Display.getCurrent().getActiveShell(), destinationFolder.getAbsolutePath());
                try {
                    importFromRDialog.open();
                    ProjectExplorerView.this.projectExplorerManager.refreshFolder(ProjectExplorerView.this.projectExplorerManager.getTreeNodeModelbyFilePath(destinationFolder.getAbsolutePath()));
                }
                catch (Exception exception) {}
            }
        };
        return menuListener;
    }

    private boolean compareTreeItemArray(TreeItem[] arrayA, TreeItem[] arrayB) {
        boolean foundSwitch = false;
        boolean isFound = false;
        int i = 0;
        while (i < arrayA.length) {
            int j = 0;
            while (j < arrayB.length) {
                if (arrayA[i] == arrayB[j]) {
                    foundSwitch = true;
                    isFound = true;
                }
                ++j;
            }
            foundSwitch = false;
            ++i;
        }
        return isFound;
    }

    @PostConstruct
    public void initProjectExplorerView() {
        rootTree.addListener(26, new Listener(){

            public void handleEvent(Event e) {
                PartStackHandler.initISaveHandler();
                if (PartStackHandler.getActiveElementID() == null) {
                    if (Display.getCurrent().getActiveShell() != null && Display.getCurrent().getActiveShell().getText().length() < 3) {
                        Display.getCurrent().getActiveShell().setText(RJavaManagerInitializer.APPLICATION_TITLE);
                    }
                    MenuHandler.setDataDependentMenuVisible(false);
                }
            }
        });
        rootTree.addListener(3, new Listener(){

            public void handleEvent(Event event) {
                if (rootTree.getSelectionCount() > 0 && rootTree.getItem(0).getItemCount() > 0) {
                    ProjectExplorerView.this.setMenu();
                } else {
                    try {
                        rootTree.getMenu().dispose();
                    }
                    catch (Exception exception) {}
                }
            }
        });
        rootTree.setFocus();
        rootTree.addListener(8, new Listener(){

            public void handleEvent(Event event) {
                TreeItem[] selection = rootTree.getSelection();
                if (rootTree.getSelectionCount() > 0) {
                    if (selection[0].getItemCount() == 0) {
                        ProjectExplorerTreeNodeModel fileModel = (ProjectExplorerTreeNodeModel)selection[0].getData();
                        if (!fileModel.getProjectFile().isDirectory()) {
                            System.out.println(fileModel.getProjectFile().getAbsolutePath().toString());
                            try {
                                PartStackHandler.execute(fileModel);
                            }
                            catch (SWTException sWTException) {
                            }
                            catch (NullPointerException nullPointerException) {}
                        }
                    } else {
                        ProjectExplorerTreeNodeModel fileModel;
                        ProjectExplorerTreeNodeModel selectedNode;
                        File randomizationFile = new File(String.valueOf(((ProjectExplorerTreeNodeModel)ProjectExplorerView.this.projectExplorerManager.getOutputFolder(projectTree).getData()).getProjectFile().getAbsolutePath()) + File.separator + "Randomization");
                        if (selection[0].getParentItem() == ProjectExplorerView.this.projectExplorerManager.getOutputFolder(projectTree) && !(selectedNode = (ProjectExplorerTreeNodeModel)selection[0].getData()).getProjectFile().getAbsolutePath().contains(randomizationFile.getAbsolutePath())) {
                            fileModel = (ProjectExplorerTreeNodeModel)selection[0].getData();
                            PartStackHandler.execute(fileModel);
                        }
                        if (((ProjectExplorerTreeNodeModel)selection[0].getData()).getProjectFile().getPath().contains(((ProjectExplorerTreeNodeModel)ProjectExplorerView.this.projectExplorerManager.getOutputFolder(projectTree).getData()).getProjectFile().getPath()) && !(selectedNode = (ProjectExplorerTreeNodeModel)selection[0].getData()).getProjectFile().getAbsolutePath().contains(randomizationFile.getAbsolutePath())) {
                            fileModel = (ProjectExplorerTreeNodeModel)selection[0].getData();
                            PartStackHandler.execute(fileModel);
                        }
                    }
                }
            }
        });
        DragSource ds = new DragSource((Control)rootTree, 2);
        ds.setTransfer(new Transfer[]{FileTransfer.getInstance()});
        ds.addDragListener((DragSourceListener)new DragSourceAdapter(){

            public void dragStart(DragSourceEvent event) {
                int i = 0;
                while (i < rootTree.getSelectionCount()) {
                    if (rootTree.getSelection()[i] == projectTree || rootTree.getSelection()[i] == ProjectExplorerView.this.projectExplorerManager.getDataFolder(projectTree) || rootTree.getSelection()[i] == ProjectExplorerView.this.projectExplorerManager.getOutputFolder(projectTree)) {
                        event.doit = false;
                    }
                    ++i;
                }
            }

            public void dragSetData(DragSourceEvent event) {
                String[] draggedFiles = new String[rootTree.getSelectionCount()];
                int i = 0;
                while (i < rootTree.getSelectionCount()) {
                    ProjectExplorerTreeNodeModel nodeModel = (ProjectExplorerTreeNodeModel)rootTree.getSelection()[i].getData();
                    draggedFiles[i] = nodeModel.getProjectFile().getPath();
                    ++i;
                }
                event.data = draggedFiles;
            }

            public void dragFinished(DragSourceEvent event) {
                ProjectExplorerManager projectExplorerManager = new ProjectExplorerManager();
                if (rootTree.getSelectionCount() > 0) {
                    projectExplorerManager.refreshFolder((ProjectExplorerTreeNodeModel)rootTree.getSelection()[0].getParentItem().getData());
                }
            }
        });
        DropTarget dt = new DropTarget((Control)rootTree, 2);
        dt.setTransfer(new Transfer[]{FileTransfer.getInstance()});
        dt.addDropListener((DropTargetListener)new DropTargetAdapter(){

            public void drop(DropTargetEvent event) {
                if (event.data == null || event.item == null || event.item == projectTree) {
                    event.detail = 0;
                    return;
                }
                TreeItem item = (TreeItem)event.item;
                String[] draggedFiles = (String[])event.data;
                System.out.println(item.getText());
                ProjectExplorerManager projectExplorerManager = new ProjectExplorerManager();
                ProjectExplorerTreeNodeModel nodeModel = (ProjectExplorerTreeNodeModel)item.getData();
                if (!nodeModel.getProjectFile().isDirectory()) {
                    nodeModel = (ProjectExplorerTreeNodeModel)nodeModel.getParentTreeItem().getData();
                } else {
                    System.out.println("Occurenc");
                }
                ArrayList<String> errorFiles = new ArrayList<String>();
                int i = 0;
                while (i < draggedFiles.length) {
                    if (!PartStackHandler.isOpen(draggedFiles[i])) {
                        System.out.println("Destination: " + nodeModel.getProjectFile().getPath());
                        System.out.println("Source: " + draggedFiles[i]);
                        File sourceFile = new File(draggedFiles[i]);
                        File destinationFolder = new File(String.valueOf(nodeModel.getProjectFile().getPath()) + "//" + sourceFile.getName());
                        if (!destinationFolder.getParentFile().getPath().toString().equals(sourceFile.getParentFile().getPath())) {
                            if (!sourceFile.isDirectory()) {
                                if (projectExplorerManager.copyFile(sourceFile, destinationFolder)) {
                                    sourceFile.delete();
                                }
                            } else if (projectExplorerManager.copyDirectory(sourceFile, destinationFolder)) {
                                projectExplorerManager.deleteFolder(sourceFile);
                            }
                        }
                    } else {
                        errorFiles.add(new File(draggedFiles[i]).getName());
                    }
                    ++i;
                }
                projectExplorerManager.refreshFolder(nodeModel);
                if (!errorFiles.isEmpty()) {
                    MessageDialog.openWarning((Shell)Display.getCurrent().getActiveShell(), (String)"Cannot move files", (String)("Error moving file(s) [" + GeneralUtility.combineArrayOfString(errorFiles.toArray(new String[errorFiles.size()]), ", ") + "]. Make sure all the files are not active before performing this operation"));
                }
            }
        });
    }

    public static void refreshApplicationTitle() {
        RJavaManagerInitializer.refreshApplicationTitle();
        Display.getCurrent().getActiveShell().setText(String.valueOf(RJavaManagerInitializer.APPLICATION_TITLE) + new ProjectExplorerManager().getProjectPath());
        System.out.println(RJavaManagerInitializer.APPLICATION_TITLE);
    }
}

