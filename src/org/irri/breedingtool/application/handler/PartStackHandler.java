/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.inject.Inject
 *  org.eclipse.e4.core.di.annotations.Execute
 *  org.eclipse.e4.ui.model.application.MApplication
 *  org.eclipse.e4.ui.model.application.ui.MUIElement
 *  org.eclipse.e4.ui.model.application.ui.advanced.MPerspective
 *  org.eclipse.e4.ui.model.application.ui.basic.MBasicFactory
 *  org.eclipse.e4.ui.model.application.ui.basic.MPart
 *  org.eclipse.e4.ui.model.application.ui.basic.MPartSashContainer
 *  org.eclipse.e4.ui.model.application.ui.basic.MPartStack
 *  org.eclipse.e4.ui.model.application.ui.basic.MStackElement
 *  org.eclipse.e4.ui.model.application.ui.basic.MWindow
 *  org.eclipse.e4.ui.model.application.ui.menu.MMenu
 *  org.eclipse.e4.ui.workbench.modeling.EModelService
 *  org.eclipse.e4.ui.workbench.modeling.EPartService
 *  org.eclipse.e4.ui.workbench.modeling.EPartService$PartState
 *  org.eclipse.e4.ui.workbench.modeling.ISaveHandler
 *  org.eclipse.e4.ui.workbench.modeling.ISaveHandler$Save
 *  org.eclipse.jface.dialogs.MessageDialog
 *  org.eclipse.swt.SWTException
 *  org.eclipse.swt.widgets.Display
 *  org.eclipse.swt.widgets.FileDialog
 *  org.eclipse.swt.widgets.MessageBox
 *  org.eclipse.swt.widgets.Shell
 */
package org.irri.breedingtool.application.handler;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import javax.inject.Inject;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.MUIElement;
import org.eclipse.e4.ui.model.application.ui.advanced.MPerspective;
import org.eclipse.e4.ui.model.application.ui.basic.MBasicFactory;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.model.application.ui.basic.MPartSashContainer;
import org.eclipse.e4.ui.model.application.ui.basic.MPartStack;
import org.eclipse.e4.ui.model.application.ui.basic.MStackElement;
import org.eclipse.e4.ui.model.application.ui.basic.MWindow;
import org.eclipse.e4.ui.model.application.ui.menu.MMenu;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.e4.ui.workbench.modeling.ISaveHandler;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.irri.breedingtool.application.model.ProjectExplorerTreeNodeModel;
import org.irri.breedingtool.datamanipulation.data.view.FileTableViewer;
import org.irri.breedingtool.datamanipulation.data.view.OutputFolderViewer;
import org.irri.breedingtool.datamanipulation.data.view.PdfViewer;
import org.irri.breedingtool.datamanipulation.data.view.TxtFileViewer;
import org.irri.breedingtool.datamanipulation.dialog.ChooseDelimiterDialog;
import org.irri.breedingtool.datamanipulation.dialog.SaveablePartPromptDialog;
import org.irri.breedingtool.manager.impl.DataManipulationManager;
import org.irri.breedingtool.manager.impl.ProjectExplorerManager;
import org.irri.breedingtool.projectexplorer.view.ProjectExplorerView;
import org.irri.breedingtool.utility.MPartContributionURI;

public class PartStackHandler {
    @Inject
    private static EPartService service;
    @Inject
    private static MApplication application;
    @Inject
    private static EModelService modelService;
    private static int triggerRetries;
    public static String PackageName;

    static {
        triggerRetries = 0;
        PackageName = "Star";
    }

    public static void reOpen(MPart part) {
        MPartStack stack = (MPartStack)modelService.find("ViewerStack", (MUIElement)application);
        stack.getChildren().add(part);
    }

    public static void setStackNoCollapse() {
        MPartStack stack = (MPartStack)modelService.find("ViewerStack", (MUIElement)application);
        stack.getTags().add("NoAutoCollapse");
    }

    @Execute
    public static void execute(ProjectExplorerTreeNodeModel treeNodeModel) {
        MPart cfr_ignored_0 = (MPart)modelService.find("retainer", (MUIElement)application);
        MPartStack stack = (MPartStack)modelService.find("ViewerStack", (MUIElement)application);
        stack.getTags().add("NoAutoCollapse");
        if (modelService.find(treeNodeModel.getProjectFile().getAbsolutePath(), (MUIElement)application) != null) {
            service.showPart((MPart)modelService.find(treeNodeModel.getProjectFile().getAbsolutePath(), (MUIElement)application), EPartService.PartState.ACTIVATE);
            service.activate((MPart)modelService.find(treeNodeModel.getProjectFile().getAbsolutePath(), (MUIElement)application));
            service.showPart((MPart)modelService.find(treeNodeModel.getProjectFile().getAbsolutePath(), (MUIElement)application), EPartService.PartState.ACTIVATE);
            System.out.println("Show");
            return;
        }
        System.out.println("Continue");
        ProjectExplorerManager projectMan = new ProjectExplorerManager();
        boolean toOpen = true;
        treeNodeModel.getParentTreeItem().setExpanded(true);
        if (treeNodeModel.getParentTreeItem().getParentItem() != null) {
            treeNodeModel.getParentTreeItem().getParentItem().setExpanded(true);
        }
        if (treeNodeModel.getProjectFile().getAbsolutePath().contains(((ProjectExplorerTreeNodeModel)projectMan.getOutputFolder(ProjectExplorerView.projectTree).getData()).getProjectFile().getPath())) {
            projectMan.getOutputFolder(ProjectExplorerView.projectTree).setExpanded(true);
        }
        MPart part = MBasicFactory.INSTANCE.createPart();
        DataManipulationManager dataManipulationManager = new DataManipulationManager();
        part.setLabel(dataManipulationManager.getDisplayFileName(treeNodeModel.getProjectFile().getAbsolutePath()));
        part.setElementId(treeNodeModel.getProjectFile().getPath());
        if (treeNodeModel.getProjectFile().getName().endsWith(".txt")) {
            part.setAccessibilityPhrase("Table");
            part.setIconURI("platform:/plugin/" + PackageName + "/icons/page_white_text.png");
            String projectPath = new File(projectMan.getProjectPath()).getAbsolutePath();
            if (treeNodeModel.getProjectFile().getPath().toString().contains(String.valueOf(projectPath) + File.separator + "Output")) {
                part.setAccessibilityPhrase("TxtFile");
                TxtFileViewer.openTxtFile(treeNodeModel);
                part.setContributionURI("bundleclass://" + PackageName + "/org.irri.breedingtool.datamanipulation.data.view.TxtFileViewer");
            } else {
                System.out.println(String.valueOf(projectMan.getProjectPath()) + File.separator + "Output" + " " + treeNodeModel.getProjectFile().getPath().toString());
                ChooseDelimiterDialog chooseDelimiter = new ChooseDelimiterDialog(Display.getCurrent().getActiveShell(), false, dataManipulationManager.getDisplayFileName(treeNodeModel.getProjectFile().getAbsolutePath()));
                chooseDelimiter.open();
                String delimiter = chooseDelimiter.getDelimiter();
                if (chooseDelimiter.getReturnCode() == 0) {
                    if (!delimiter.equals("")) {
                        part.setContributionURI(MPartContributionURI.FILE_TABLE_VIEWER_URI);
                        FileTableViewer.openFile(treeNodeModel, "txt", delimiter);
                    } else {
                        part.setContributionURI(MPartContributionURI.PLAIN_TXT_VIEWER_URI);
                        TxtFileViewer.openTxtFile(treeNodeModel);
                    }
                } else {
                    toOpen = false;
                }
            }
        } else if (treeNodeModel.getProjectFile().getName().endsWith(".png") || treeNodeModel.getProjectFile().getName().endsWith(".jpg")) {
            part.setIconURI("platform:/plugin/" + PackageName + "/icons/picture.png");
            part.setContributionURI(MPartContributionURI.IMAGE_FILE_VIEWER_URI);
            part.getProperties().put("filePath", treeNodeModel.getProjectFile().getPath());
            part.setAccessibilityPhrase("Image");
        } else if (treeNodeModel.getProjectFile().getName().endsWith(".pdf")) {
            part.setIconURI("platform:/plugin/" + PackageName + "/icons/pdf_icon.png");
            PdfViewer.openPdfFile(treeNodeModel);
            part.getProperties().put("filePath", treeNodeModel.getProjectFile().getPath());
            part.setContributionURI(MPartContributionURI.PDF_VIEWER_URI);
            part.setAccessibilityPhrase("PDF");
        } else if (treeNodeModel.getProjectFile().getName().endsWith(".csv")) {
            part.setAccessibilityPhrase("Table");
            part.setIconURI(MPartContributionURI.CSV_TABLE_VIEWER_ICON);
            System.out.println("opening csv");
            FileTableViewer.openFile(treeNodeModel, "csv", ",");
            part.setContributionURI(MPartContributionURI.FILE_TABLE_VIEWER_URI);
        } else if (treeNodeModel.getProjectFile().getName().endsWith(".rda")) {
            if (MessageDialog.openConfirm((Shell)Display.getCurrent().getActiveShell(), (String)"Convert RDA to CSV", (String)"You have opened an R Data File.\n Do you want to convert it to CSV?")) {
                String dataFilePath = treeNodeModel.getProjectFile().getAbsolutePath();
                FileDialog fd = new FileDialog(Display.getCurrent().getActiveShell(), 8192);
                fd.setText("Save as CSV File");
                fd.setFilterPath(dataFilePath);
                String[] filterExt = new String[]{"*.csv"};
                fd.setFilterExtensions(filterExt);
                String newFileName = fd.open();
                try {
                    part.setLabel(fd.getFileName());
                    part.setElementId(newFileName);
                    String fileName = dataFilePath.replace(File.separator, "/");
                    newFileName = newFileName.replace(File.separator, "/");
                    ProjectExplorerView.rJavaManager.getRJavaDataManipulationManager().convertRdaToCsv(fileName, newFileName);
                    part.setAccessibilityPhrase("Table");
                    part.setIconURI(MPartContributionURI.CSV_TABLE_VIEWER_ICON);
                    ProjectExplorerTreeNodeModel convertedFile = new ProjectExplorerTreeNodeModel(new File(newFileName), treeNodeModel.getTreeItem());
                    FileTableViewer.openFile(convertedFile, "csv", ",");
                    part.setContributionURI(MPartContributionURI.FILE_TABLE_VIEWER_URI);
                    projectMan.refreshFolder((ProjectExplorerTreeNodeModel)projectMan.getDataFolder(ProjectExplorerView.projectTree).getData());
                }
                catch (NullPointerException nullPointerException) {}
            } else {
                MessageDialog.openError((Shell)Display.getCurrent().getActiveShell(), (String)"Invalid File Type", (String)("Sorry, can't open file " + treeNodeModel.getProjectFile().getName()));
            }
        } else if (treeNodeModel.getProjectFile().getPath().contains(((ProjectExplorerTreeNodeModel)projectMan.getOutputFolder(ProjectExplorerView.projectTree).getData()).getProjectFile().getPath())) {
            if (treeNodeModel.getProjectFile().getAbsolutePath().equals(((ProjectExplorerTreeNodeModel)projectMan.getOutputFolder(ProjectExplorerView.projectTree).getData()).getProjectFile().getAbsolutePath())) {
                return;
            }
            part.setAccessibilityPhrase("OutputFolder");
            part.setIconURI(MPartContributionURI.CSV_TABLE_VIEWER_ICON);
            part.getProperties().put("folderPath", treeNodeModel.getProjectFile().getPath());
            OutputFolderViewer.setFileModel(treeNodeModel);
            part.setContributionURI(MPartContributionURI.OUTPUT_FOLDER_VIEWER_URI);
        } else {
            MessageDialog.openError((Shell)Display.getCurrent().getActiveShell(), (String)"Unknown File Type", (String)("Sorry, can't open file " + treeNodeModel.getProjectFile().getName()));
            return;
        }
        if (toOpen) {
            part.setCloseable(true);
            stack.getChildren().add(part);
            treeNodeModel.setElementInStack(part);
            part.setObject((Object)treeNodeModel);
            treeNodeModel.setElementInStack(part);
            try {
                try {
                    service.showPart(part, EPartService.PartState.ACTIVATE);
                }
                catch (Exception exception) {
                    stack.setSelectedElement((MUIElement)part);
                }
            }
            catch (SWTException sWTException) {
            }
            catch (NullPointerException nullPointerException) {
                stack.setSelectedElement((MUIElement)part);
            }
            part.getTags().add("removeOnHide");
            if (part.getLabel().endsWith(".csv") && FileTableViewer.invalidDataDetected) {
                treeNodeModel.getElementInStack().getParent().getChildren().remove(treeNodeModel.getElementInStack());
            }
        }
    }

    public static void initISaveHandler() {
        MWindow window = (MWindow)modelService.find("MainWindow", (MUIElement)application);
        window.getContext().set(ISaveHandler.class, (Object)new ISaveHandler(){

            public ISaveHandler.Save promptToSave(MPart dirtyPart) {
                Shell shell = (Shell)window.getContext().get("activeShell");
                int style = 452;
                MessageBox messageBox = new MessageBox(shell, style);
                messageBox.setMessage(String.valueOf(dirtyPart.getLabel()) + " has been modified. Save Changes?");
                switch (messageBox.open()) {
                    case 256: {
                        return ISaveHandler.Save.CANCEL;
                    }
                    case 64: {
                        return ISaveHandler.Save.YES;
                    }
                    case 128: {
                        return ISaveHandler.Save.NO;
                    }
                }
                return ISaveHandler.Save.CANCEL;
            }

            public ISaveHandler.Save[] promptToSave(Collection<MPart> dirtyParts) {
                ArrayList<MPart> parts = new ArrayList<MPart>(dirtyParts);
                Shell shell = (Shell)window.getContext().get("activeShell");
                Object[] response = new ISaveHandler.Save[dirtyParts.size()];
                Object[] elements = PartStackHandler.promptForSave(shell, parts);
                if (elements == null) {
                    Arrays.fill(response, ISaveHandler.Save.CANCEL);
                } else {
                    Arrays.fill(response, ISaveHandler.Save.NO);
                    int i = 0;
                    while (i < elements.length) {
                        response[parts.indexOf((Object)elements[i])] = ISaveHandler.Save.YES;
                        ++i;
                    }
                }
                return response;
            }
        });
    }

    public static void retainPart(MPart part) {
        service.activate(part);
        service.showPart(part, EPartService.PartState.ACTIVATE);
        part.setVisible(true);
    }

    public static void reCreatePartSashHandler() {
        MPerspective mainPerspective = (MPerspective)modelService.find("MainPerspective", (MUIElement)application);
        mainPerspective.getChildren().remove(0);
        MPartSashContainer mainPartSashContainer = MBasicFactory.INSTANCE.createPartSashContainer();
        mainPartSashContainer.setElementId("MainPartSashContainer");
        mainPerspective.getChildren().add(mainPartSashContainer);
        MPartStack projectExplorerStack = MBasicFactory.INSTANCE.createPartStack();
        MBasicFactory.INSTANCE.createPartStack();
        projectExplorerStack.setElementId("ProjectExplorerStack");
        projectExplorerStack.setContainerData("2");
        MPart projExpPart = MBasicFactory.INSTANCE.createPart();
        projExpPart.setCloseable(false);
        projExpPart.setLabel("Project Explorer");
    }

    public static void setPartModified(String partID) {
        MPart part = (MPart)modelService.find(partID, (MUIElement)application);
        part.setDirty(true);
    }

    public static void setPartSaved(String partID) {
        MPart part = (MPart)modelService.find(partID, (MUIElement)application);
        part.setDirty(false);
    }

    public static List<MStackElement> getStackChildrens() {
        MPartStack stack = (MPartStack)modelService.find("ViewerStack", (MUIElement)application);
        return stack.getChildren();
    }

    public static ProjectExplorerTreeNodeModel getPartTreeModel(String partID) {
        if (!PartStackHandler.isOpen(partID)) {
            return null;
        }
        MPart part = (MPart)modelService.find(partID, (MUIElement)application);
        return (ProjectExplorerTreeNodeModel)part.getObject();
    }

    public static void removeAll() {
        MPartStack stack = (MPartStack)modelService.find("ViewerStack", (MUIElement)application);
        if (stack == null) {
            return;
        }
        List stackChildren = stack.getChildren();
        int listCount = stackChildren.size();
        if (listCount <= 0) {
            return;
        }
        int i = listCount - 1;
        while (i >= 0) {
            MPart part = (MPart)stack.getChildren().get(i);
            if (!part.getElementId().equals("retainer")) {
                stackChildren.remove(i);
            }
            --i;
        }
    }

    public static void addRetainer() {
        MPartStack stack = (MPartStack)modelService.find("ViewerStack", (MUIElement)application);
        if (modelService.find("retainer", (MUIElement)application) != null) {
            return;
        }
        MPart retainer = MBasicFactory.INSTANCE.createPart();
        retainer.setVisible(false);
        retainer.setElementId("retainer");
        System.out.println("Retainer Added");
        stack.getChildren().add(retainer);
        service.hidePart(retainer);
    }

    public static void showRetainer() {
        MPartStack cfr_ignored_0 = (MPartStack)modelService.find("ViewerStack", (MUIElement)application);
        if (modelService.find("retainer", (MUIElement)application) == null) {
            PartStackHandler.addRetainer();
            PartStackHandler.showRetainer();
            return;
        }
        service.showPart((MPart)modelService.find("retainer", (MUIElement)application), EPartService.PartState.ACTIVATE);
    }

    public static void hideRetainer() {
        MPartStack cfr_ignored_0 = (MPartStack)modelService.find("ViewerStack", (MUIElement)application);
        if (modelService.find("retainer", (MUIElement)application) == null) {
            PartStackHandler.addRetainer();
            return;
        }
        service.hidePart((MPart)modelService.find("retainer", (MUIElement)application));
        System.out.println("retainer hide");
    }

    public static boolean isOpen(String elementID) {
        if (modelService == null) {
            return false;
        }
        MPart modelActivePart = (MPart)modelService.find(elementID, (MUIElement)application);
        MPartStack cfr_ignored_0 = (MPartStack)modelService.find("ViewerStack", (MUIElement)application);
        return modelActivePart != null && modelActivePart.isVisible();
    }

    public static void reOpenPart(ProjectExplorerTreeNodeModel nodeModel) {
        MPartStack stack = (MPartStack)modelService.find("ViewerStack", (MUIElement)application);
        MPart part = (MPart)modelService.find(nodeModel.getProjectFile().getAbsolutePath(), (MUIElement)application);
        stack.getChildren().remove(part);
        PartStackHandler.execute(nodeModel);
    }

    public static void closePart(ProjectExplorerTreeNodeModel nodeModel) {
        nodeModel.getElementInStack().getParent().getChildren().remove(nodeModel.getElementInStack());
    }

    public static MStackElement getActivePart() {
        MPartStack stack = (MPartStack)modelService.find("ViewerStack", (MUIElement)application);
        return (MStackElement)stack.getSelectedElement();
    }

    public static String getActiveElementID() {
        String ID;
        MPartStack stack = (MPartStack)modelService.find("ViewerStack", (MUIElement)application);
        try {
            ID = ((MStackElement)stack.getSelectedElement()).getElementId();
        }
        catch (Exception exception) {
            return null;
        }
        return ID;
    }

    private static Object[] promptForSave(Shell parentShell, Collection<MPart> saveableParts) {
        SaveablePartPromptDialog dialog = new SaveablePartPromptDialog(parentShell, saveableParts);
        if (dialog.open() == 1) {
            return null;
        }
        return dialog.getCheckedElements();
    }

    public static void disableMenuItems() {
        MMenu stack = (MMenu)modelService.find("mnuAnalysis", (MUIElement)application);
        stack.setEnabled(false);
        stack.setVisible(false);
    }

    public static void launchHelp() {
        MPartStack stack = (MPartStack)modelService.find("ViewerStack", (MUIElement)application);
        MPart part = MBasicFactory.INSTANCE.createPart();
        part.setIconURI("platform:/plugin/" + PackageName + "/icons/pdf_icon.png");
        PdfViewer.openPdfFile(String.valueOf(System.getProperty("user.dir")) + File.separator + "User's Manual.pdf");
        part.setLabel("User's Manual");
        part.setContributionURI(MPartContributionURI.PDF_VIEWER_URI);
        part.setAccessibilityPhrase("PDF");
        part.setCloseable(true);
        stack.getChildren().add(part);
        stack.setSelectedElement((MUIElement)part);
    }
}

