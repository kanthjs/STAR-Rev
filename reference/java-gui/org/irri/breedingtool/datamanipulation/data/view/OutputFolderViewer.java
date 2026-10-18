/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.inject.Inject
 *  javax.inject.Named
 *  org.eclipse.e4.ui.di.Focus
 *  org.eclipse.e4.ui.model.application.ui.basic.MPart
 *  org.eclipse.jface.dialogs.MessageDialog
 *  org.eclipse.swt.custom.CLabel
 *  org.eclipse.swt.custom.CTabFolder
 *  org.eclipse.swt.custom.CTabItem
 *  org.eclipse.swt.custom.ScrolledComposite
 *  org.eclipse.swt.custom.StyledText
 *  org.eclipse.swt.dnd.Clipboard
 *  org.eclipse.swt.dnd.TextTransfer
 *  org.eclipse.swt.dnd.Transfer
 *  org.eclipse.swt.events.KeyEvent
 *  org.eclipse.swt.events.KeyListener
 *  org.eclipse.swt.events.SelectionAdapter
 *  org.eclipse.swt.events.SelectionEvent
 *  org.eclipse.swt.events.SelectionListener
 *  org.eclipse.swt.graphics.Device
 *  org.eclipse.swt.graphics.Image
 *  org.eclipse.swt.layout.FillLayout
 *  org.eclipse.swt.layout.GridData
 *  org.eclipse.swt.layout.GridLayout
 *  org.eclipse.swt.widgets.Composite
 *  org.eclipse.swt.widgets.Control
 *  org.eclipse.swt.widgets.DirectoryDialog
 *  org.eclipse.swt.widgets.Display
 *  org.eclipse.swt.widgets.Event
 *  org.eclipse.swt.widgets.Label
 *  org.eclipse.swt.widgets.Layout
 *  org.eclipse.swt.widgets.Listener
 *  org.eclipse.swt.widgets.Menu
 *  org.eclipse.swt.widgets.MenuItem
 *  org.eclipse.swt.widgets.Shell
 *  org.eclipse.swt.widgets.TabFolder
 *  org.eclipse.swt.widgets.TabItem
 *  org.eclipse.swt.widgets.ToolBar
 *  org.eclipse.swt.widgets.ToolItem
 */
package org.irri.breedingtool.datamanipulation.data.view;

import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.wb.swt.ResourceManager;
import org.eclipse.wb.swt.SWTResourceManager;
import org.irri.breedingtool.application.handler.DialogHandler;
import org.irri.breedingtool.application.handler.PartStackHandler;
import org.irri.breedingtool.application.model.ProjectExplorerTreeNodeModel;
import org.irri.breedingtool.datamanipulation.data.view.TxtFileViewer;
import org.irri.breedingtool.manager.impl.ImageManager;
import org.irri.breedingtool.manager.impl.ProjectExplorerManager;

public class OutputFolderViewer {
    private static String filePath;
    static ProjectExplorerTreeNodeModel fileModel;
    String[] imageList;
    Composite compositeImageView;
    ImageManager imageManager;
    Composite parentComposite;
    private TabFolder tabTxtFiles;
    private Label lblImagePlaceHolder;
    private CLabel activeImageThumbnail;
    private Composite compositeThumbnailHolder;

    @Inject
    public void setFilePath(@Named(value="folderPath") String fileP) {
        filePath = fileP;
    }

    @Inject
    public OutputFolderViewer(Composite parent) {
        this.parentComposite = new Composite(parent, 2048);
        this.parentComposite.setLayout((Layout)new FillLayout(256));
        CTabFolder tabFolder = new CTabFolder(this.parentComposite, 2048);
        tabFolder.setSelectionBackground(Display.getCurrent().getSystemColor(35));
        ArrayList<File> txtFiles = new ArrayList<File>();
        String[] txtExclude = new String[]{"ContrastAnalysisOutput.txt", "PairwiseOutput.txt"};
        int i = 0;
        while (i < fileModel.getProjectFile().listFiles().length) {
            if (!(!fileModel.getProjectFile().listFiles()[i].getName().endsWith(".txt") || fileModel.getProjectFile().listFiles()[i].getName().toLowerCase().endsWith("_varinfo.txt") && fileModel.getProjectFile().listFiles()[i].getName().toLowerCase().endsWith("_original.txt"))) {
                txtFiles.add(fileModel.getProjectFile().listFiles()[i]);
            }
            ++i;
        }
        if (!txtFiles.isEmpty()) {
            System.out.println(String.valueOf(fileModel.getProjectFile().getPath()) + "\\Output.txt");
            tabFolder.setSelection(0);
            CTabItem tbiOutput = new CTabItem(tabFolder, 0);
            tbiOutput.setText("Output");
            this.tabTxtFiles = new TabFolder((Composite)tabFolder, 0);
            tbiOutput.setControl((Control)this.tabTxtFiles);
            int i2 = 0;
            while (i2 < txtFiles.size()) {
                TabItem tbtmTest = new TabItem(this.tabTxtFiles, 0);
                tbtmTest.setText(((File)txtFiles.get(i2)).getName());
                StyledText stOutput = new StyledText((Composite)this.tabTxtFiles, 2818);
                tbtmTest.setControl((Control)stOutput);
                stOutput.setIndent(5);
                stOutput.setEditable(false);
                stOutput.setFont(SWTResourceManager.getFont("Courier New", 10, 0));
                try {
                    stOutput.setText(TxtFileViewer.readFileAsString(((File)txtFiles.get(i2)).getAbsolutePath(), null));
                }
                catch (IOException e1) {
                    e1.printStackTrace();
                }
                Menu menu = new Menu((Control)stOutput);
                stOutput.setMenu(menu);
                MenuItem mntmCopy = new MenuItem(menu, 0);
                mntmCopy.addSelectionListener((SelectionListener)new SelectionAdapter(){

                    public void widgetSelected(SelectionEvent e) {
                        Clipboard clipboard = new Clipboard(parent.getDisplay());
                        String plainText = stOutput.getSelectionText();
                        TextTransfer textTransfer = TextTransfer.getInstance();
                        try {
                            clipboard.setContents((Object[])new String[]{plainText}, new Transfer[]{textTransfer});
                            clipboard.dispose();
                        }
                        catch (Exception exception) {
                            System.out.println("No text selected.");
                        }
                    }
                });
                mntmCopy.setText("Copy");
                ++i2;
            }
            i2 = 0;
            while (i2 < this.tabTxtFiles.getItemCount()) {
                if (!Arrays.asList(txtExclude).contains(this.tabTxtFiles.getItem(i2).getText())) {
                    this.tabTxtFiles.setSelection(this.tabTxtFiles.getItem(i2));
                }
                ++i2;
            }
        }
        tabFolder.setSelection(0);
        this.imageManager = new ImageManager();
        List imageList = this.imageManager.listAllImage(fileModel.getProjectFile());
        if (imageList.size() == 0) {
            return;
        }
        CTabItem tbtmImageViewer = new CTabItem(tabFolder, 0);
        tbtmImageViewer.setText("Graph");
        Composite compositeImageViewer = new Composite((Composite)tabFolder, 0);
        tbtmImageViewer.setControl((Control)compositeImageViewer);
        compositeImageViewer.setLayout((Layout)new GridLayout(1, false));
        ToolBar toolBar = new ToolBar(compositeImageViewer, 0x820000);
        ToolItem zoomIn = new ToolItem(toolBar, 0);
        zoomIn.setToolTipText("Zoom in");
        zoomIn.setImage(ResourceManager.getPluginImage("Star", "icons/zoom_in.png"));
        ToolItem zoomOut = new ToolItem(toolBar, 0);
        zoomOut.setToolTipText("Zoom out");
        zoomOut.setImage(ResourceManager.getPluginImage("Star", "icons/zoom_out.png"));
        ToolItem zoomNormal = new ToolItem(toolBar, 0);
        zoomNormal.setToolTipText("Zoom to normal size");
        zoomNormal.setImage(ResourceManager.getPluginImage("Star", "icons/zoom.png"));
        ToolItem exportFile = new ToolItem(toolBar, 0);
        exportFile.setToolTipText("Export Image");
        exportFile.setImage(ResourceManager.getPluginImage("Star", "icons/picture_go.png"));
        ScrolledComposite compositeImagePlaceHolder = new ScrolledComposite(compositeImageViewer, 2816);
        compositeImagePlaceHolder.setLayoutData((Object)new GridData(4, 4, true, true, 1, 1));
        compositeImagePlaceHolder.setExpandHorizontal(true);
        compositeImagePlaceHolder.setExpandVertical(true);
        this.lblImagePlaceHolder = new Label((Composite)compositeImagePlaceHolder, 16779332);
        this.lblImagePlaceHolder.setImage(SWTResourceManager.getImage((String)imageList.get(0)));
        this.lblImagePlaceHolder.setData("imagePath", imageList.get(0));
        this.lblImagePlaceHolder.setData("sizeInt", (Object)1);
        Menu menu_1 = new Menu((Control)this.lblImagePlaceHolder);
        this.lblImagePlaceHolder.setMenu(menu_1);
        MenuItem mntmCopyToClipboard = new MenuItem(menu_1, 0);
        mntmCopyToClipboard.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                ProjectExplorerManager projectExplorerManager = new ProjectExplorerManager();
                String[] imageString = new String[]{OutputFolderViewer.this.lblImagePlaceHolder.getData("imagePath").toString()};
                projectExplorerManager.copyToClipboard(imageString);
            }
        });
        mntmCopyToClipboard.setText("Copy to Clipboard");
        compositeImagePlaceHolder.setContent((Control)this.lblImagePlaceHolder);
        compositeImagePlaceHolder.setMinSize(this.lblImagePlaceHolder.computeSize(-1, -1));
        ScrolledComposite scrolledCompositeThumbnailHolder = new ScrolledComposite(compositeImageViewer, 2816);
        GridData gd_scrolledCompositeThumbnailHolder = new GridData(4, 4, true, false, 1, 1);
        gd_scrolledCompositeThumbnailHolder.widthHint = 137;
        scrolledCompositeThumbnailHolder.setLayoutData((Object)gd_scrolledCompositeThumbnailHolder);
        scrolledCompositeThumbnailHolder.setExpandHorizontal(true);
        scrolledCompositeThumbnailHolder.setExpandVertical(true);
        this.compositeThumbnailHolder = new Composite((Composite)scrolledCompositeThumbnailHolder, 0);
        this.compositeThumbnailHolder.setLayout((Layout)new GridLayout(60, false));
        this.lblImagePlaceHolder.addListener(4, new Listener(){

            public void handleEvent(Event event) {
                compositeImagePlaceHolder.setFocus();
            }
        });
        int i3 = 0;
        while (i3 < imageList.size()) {
            CLabel lblImageThumbnail = new CLabel(this.compositeThumbnailHolder, 1);
            try {
                Image imgThumb = new Image((Device)Display.getCurrent(), (String)imageList.get(i3));
                lblImageThumbnail.setImage(this.imageManager.resizeImage(imgThumb, 120, 120));
                lblImageThumbnail.setData("imagePath", imageList.get(i3));
                lblImageThumbnail.setData("imageIndex", (Object)i3);
                lblImageThumbnail.setToolTipText(new File((String)imageList.get(i3)).getName());
                if (i3 == 0) {
                    this.activeImageThumbnail = lblImageThumbnail;
                }
                lblImageThumbnail.addListener(3, new Listener(){

                    public void handleEvent(Event event) {
                        compositeImagePlaceHolder.setFocus();
                        if (new File(lblImageThumbnail.getData("imagePath").toString()).exists()) {
                            OutputFolderViewer.this.activeImageThumbnail = lblImageThumbnail;
                            int y = 0;
                            while (y < OutputFolderViewer.this.compositeThumbnailHolder.getChildren().length) {
                                OutputFolderViewer.this.compositeThumbnailHolder.getChildren()[y].setBackground(SWTResourceManager.getColor(22));
                                ++y;
                            }
                            Image image = new Image((Device)Display.getCurrent(), lblImageThumbnail.getData("imagePath").toString());
                            OutputFolderViewer.this.lblImagePlaceHolder.setImage(image);
                            OutputFolderViewer.this.lblImagePlaceHolder.setToolTipText(lblImageThumbnail.getData("imagePath").toString());
                            OutputFolderViewer.this.lblImagePlaceHolder.setData("imagePath", (Object)lblImageThumbnail.getData("imagePath").toString());
                            OutputFolderViewer.this.lblImagePlaceHolder.setData("sizeInt", (Object)1);
                            lblImageThumbnail.setBackground(SWTResourceManager.getColor(35));
                            compositeImagePlaceHolder.setMinSize(OutputFolderViewer.this.lblImagePlaceHolder.computeSize(-1, -1));
                            compositeImagePlaceHolder.setBackground(Display.getCurrent().getSystemColor(9));
                        } else {
                            OutputFolderViewer.this.lblImagePlaceHolder.setText("File" + lblImageThumbnail.getData("imagePath").toString() + " does not exit.");
                            OutputFolderViewer.this.lblImagePlaceHolder.getImage().dispose();
                            lblImageThumbnail.getImage().dispose();
                            lblImageThumbnail.dispose();
                        }
                    }
                });
            }
            catch (Exception exception) {
                System.out.println("output_folder_viewer catch exception, corrupted image.");
            }
            ++i3;
        }
        tabFolder.setSelection(0);
        this.parentComposite.addListener(11, new Listener(){

            public void handleEvent(Event event) {
                compositeImagePlaceHolder.getBounds().height = OutputFolderViewer.this.getPercentage(OutputFolderViewer.this.parentComposite.getBounds().height, 79);
            }
        });
        scrolledCompositeThumbnailHolder.setContent((Control)this.compositeThumbnailHolder);
        scrolledCompositeThumbnailHolder.setMinSize(this.compositeThumbnailHolder.computeSize(-1, -1));
        compositeImagePlaceHolder.addKeyListener(new KeyListener(){

            public void keyPressed(org.eclipse.swt.events.KeyEvent event) {
                System.out.println("KeyPressed: " + KeyEvent.getKeyText(event.keyCode) + " vs " + Integer.toString(37) + "and" + Integer.toString(39));
                int keyCode = event.keyCode;
                String direction = "none";
                if (keyCode == 37 || keyCode == 226 || keyCode == 0x1000003 || keyCode == 16777268) {
                    direction = "prev";
                } else if (keyCode == 39 || keyCode == 227 || keyCode == 0x1000004 || keyCode == 16777270) {
                    direction = "next";
                }
                int index = (Integer)OutputFolderViewer.this.activeImageThumbnail.getData("imageIndex");
                if (direction.equals("prev") && index != 0) {
                    --index;
                    scrolledCompositeThumbnailHolder.traverse(32);
                } else if (direction.equals("next") && index != imageList.size() - 1) {
                    ++index;
                    scrolledCompositeThumbnailHolder.traverse(64);
                } else {
                    direction = "none";
                }
                if (!direction.equals("none")) {
                    System.out.println(Integer.toString(index));
                    CLabel lblImageThumbnail = (CLabel)OutputFolderViewer.this.compositeThumbnailHolder.getChildren()[index];
                    if (index != imageList.size() - 1 || index != 0) {
                        if (new File(lblImageThumbnail.getData("imagePath").toString()).exists()) {
                            OutputFolderViewer.this.activeImageThumbnail = lblImageThumbnail;
                            int y = 0;
                            while (y < OutputFolderViewer.this.compositeThumbnailHolder.getChildren().length) {
                                OutputFolderViewer.this.compositeThumbnailHolder.getChildren()[y].setBackground(SWTResourceManager.getColor(22));
                                ++y;
                            }
                            Image image = new Image((Device)Display.getCurrent(), lblImageThumbnail.getData("imagePath").toString());
                            OutputFolderViewer.this.lblImagePlaceHolder.setImage(image);
                            OutputFolderViewer.this.lblImagePlaceHolder.setToolTipText(lblImageThumbnail.getData("imagePath").toString());
                            OutputFolderViewer.this.lblImagePlaceHolder.setData("imagePath", (Object)lblImageThumbnail.getData("imagePath").toString());
                            OutputFolderViewer.this.lblImagePlaceHolder.setData("sizeInt", (Object)1);
                            lblImageThumbnail.setBackground(SWTResourceManager.getColor(35));
                            compositeImagePlaceHolder.setMinSize(OutputFolderViewer.this.lblImagePlaceHolder.computeSize(-1, -1));
                            compositeImagePlaceHolder.setBackground(Display.getCurrent().getSystemColor(9));
                        } else {
                            OutputFolderViewer.this.lblImagePlaceHolder.setText("File" + lblImageThumbnail.getData("imagePath").toString() + " does not exit.");
                            OutputFolderViewer.this.lblImagePlaceHolder.getImage().dispose();
                            lblImageThumbnail.getImage().dispose();
                            lblImageThumbnail.dispose();
                        }
                    }
                }
            }

            public void keyReleased(org.eclipse.swt.events.KeyEvent e) {
            }
        });
        zoomIn.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if ((Integer)OutputFolderViewer.this.lblImagePlaceHolder.getData("sizeInt") <= 3 && !OutputFolderViewer.this.lblImagePlaceHolder.getImage().isDisposed()) {
                    Image image = new Image((Device)Display.getCurrent(), OutputFolderViewer.this.lblImagePlaceHolder.getData("imagePath").toString());
                    int zoomInt = 1 + (Integer)OutputFolderViewer.this.lblImagePlaceHolder.getData("sizeInt");
                    OutputFolderViewer.this.lblImagePlaceHolder.setImage(OutputFolderViewer.this.imageManager.resizeImage(image, image.getBounds().width * zoomInt, image.getBounds().height * zoomInt));
                    OutputFolderViewer.this.lblImagePlaceHolder.setData("sizeInt", (Object)zoomInt);
                    image.dispose();
                    compositeImagePlaceHolder.setMinSize(OutputFolderViewer.this.lblImagePlaceHolder.computeSize(-1, -1));
                }
            }
        });
        zoomOut.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if ((Integer)OutputFolderViewer.this.lblImagePlaceHolder.getData("sizeInt") > 1 && !OutputFolderViewer.this.lblImagePlaceHolder.getImage().isDisposed()) {
                    Image image = new Image((Device)Display.getCurrent(), OutputFolderViewer.this.lblImagePlaceHolder.getData("imagePath").toString());
                    int zoomInt = (Integer)OutputFolderViewer.this.lblImagePlaceHolder.getData("sizeInt") - 1;
                    OutputFolderViewer.this.lblImagePlaceHolder.setImage(OutputFolderViewer.this.imageManager.resizeImage(image, image.getBounds().width * zoomInt, image.getBounds().height * zoomInt));
                    OutputFolderViewer.this.lblImagePlaceHolder.setData("sizeInt", (Object)zoomInt);
                    image.dispose();
                    compositeImagePlaceHolder.setMinSize(OutputFolderViewer.this.lblImagePlaceHolder.computeSize(-1, -1));
                }
            }
        });
        zoomNormal.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                OutputFolderViewer.this.lblImagePlaceHolder.setImage(new Image((Device)Display.getCurrent(), OutputFolderViewer.this.lblImagePlaceHolder.getData("imagePath").toString()));
                OutputFolderViewer.this.lblImagePlaceHolder.setData("sizeInt", (Object)1);
                compositeImagePlaceHolder.setMinSize(OutputFolderViewer.this.lblImagePlaceHolder.computeSize(-1, -1));
            }
        });
        exportFile.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                DirectoryDialog dlgExport = new DirectoryDialog(Display.getCurrent().getActiveShell());
                dlgExport.setText("Export Image to");
                dlgExport.setMessage("Select a Directory");
                String dirPath = dlgExport.open();
                if (dirPath != null) {
                    if (new File(String.valueOf(dirPath) + "\\" + new File(OutputFolderViewer.this.lblImagePlaceHolder.getData("imagePath").toString()).getName()).exists() && !MessageDialog.openQuestion((Shell)Display.getCurrent().getActiveShell(), (String)"Confirm", (String)("The file '" + new File(OutputFolderViewer.this.lblImagePlaceHolder.getData("imagePath").toString()).getName().toString() + "' already exist in the folder. Do you want to overwrite the file? \n\n\n WARNING: This action is permanent"))) {
                        return;
                    }
                    OutputFolderViewer.this.imageManager.exportImageTo(new File(OutputFolderViewer.this.lblImagePlaceHolder.getData("imagePath").toString()), new File(dirPath));
                }
            }
        });
    }

    @Focus
    public void onFocus(MPart part) {
        DialogHandler.setPartDialogMaximized(PartStackHandler.getActiveElementID());
    }

    private int getPercentage(int total, int percentage) {
        return (int)((float)percentage / 100.0f * (float)total);
    }

    public static void setFileModel(ProjectExplorerTreeNodeModel fModel) {
        fileModel = fModel;
    }
}

