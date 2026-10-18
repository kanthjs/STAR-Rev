/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.inject.Inject
 *  org.eclipse.e4.ui.di.Focus
 *  org.eclipse.e4.ui.di.Persist
 *  org.eclipse.e4.ui.di.PersistState
 *  org.eclipse.e4.ui.model.application.ui.basic.MPart
 *  org.eclipse.e4.ui.workbench.modeling.EPartService
 *  org.eclipse.jface.dialogs.IInputValidator
 *  org.eclipse.jface.dialogs.InputDialog
 *  org.eclipse.jface.dialogs.MessageDialog
 *  org.eclipse.swt.SWTException
 *  org.eclipse.swt.custom.TableEditor
 *  org.eclipse.swt.dnd.Clipboard
 *  org.eclipse.swt.dnd.TextTransfer
 *  org.eclipse.swt.dnd.Transfer
 *  org.eclipse.swt.events.SelectionAdapter
 *  org.eclipse.swt.events.SelectionEvent
 *  org.eclipse.swt.events.SelectionListener
 *  org.eclipse.swt.events.ShellAdapter
 *  org.eclipse.swt.events.ShellEvent
 *  org.eclipse.swt.events.ShellListener
 *  org.eclipse.swt.graphics.Device
 *  org.eclipse.swt.graphics.Image
 *  org.eclipse.swt.graphics.Point
 *  org.eclipse.swt.graphics.Rectangle
 *  org.eclipse.swt.widgets.Composite
 *  org.eclipse.swt.widgets.Control
 *  org.eclipse.swt.widgets.Display
 *  org.eclipse.swt.widgets.Event
 *  org.eclipse.swt.widgets.Label
 *  org.eclipse.swt.widgets.Layout
 *  org.eclipse.swt.widgets.Listener
 *  org.eclipse.swt.widgets.Menu
 *  org.eclipse.swt.widgets.MenuItem
 *  org.eclipse.swt.widgets.Shell
 *  org.eclipse.swt.widgets.Table
 *  org.eclipse.swt.widgets.TableColumn
 *  org.eclipse.swt.widgets.TableItem
 *  org.eclipse.swt.widgets.Text
 *  org.eclipse.swt.widgets.ToolBar
 *  org.eclipse.swt.widgets.ToolItem
 *  swing2swt.layout.BorderLayout
 */
package org.irri.breedingtool.datamanipulation.data.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.di.Persist;
import org.eclipse.e4.ui.di.PersistState;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.events.ShellAdapter;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.events.ShellListener;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.wb.swt.ResourceManager;
import org.irri.breedingtool.application.handler.DialogHandler;
import org.irri.breedingtool.application.handler.PartStackHandler;
import org.irri.breedingtool.application.model.ProjectExplorerTreeNodeModel;
import org.irri.breedingtool.datamanipulation.data.view.TxtFileViewer;
import org.irri.breedingtool.datamanipulation.dialog.EditVariableInfoDialog;
import org.irri.breedingtool.datamanipulation.handler.AggregateHandler;
import org.irri.breedingtool.datamanipulation.handler.AppendHandler;
import org.irri.breedingtool.datamanipulation.handler.CreateNewVariableHandler;
import org.irri.breedingtool.datamanipulation.handler.DeleteColumnMenuHandler;
import org.irri.breedingtool.datamanipulation.handler.DeleteRowMenuHandler;
import org.irri.breedingtool.datamanipulation.handler.InsertColumnMenuHandler;
import org.irri.breedingtool.datamanipulation.handler.InsertRowMenuHandler;
import org.irri.breedingtool.datamanipulation.handler.MergeHandler;
import org.irri.breedingtool.datamanipulation.handler.ReshapeToLongHandler;
import org.irri.breedingtool.datamanipulation.handler.ReshapeToWideHandler;
import org.irri.breedingtool.datamanipulation.handler.SaveAsFileHandler;
import org.irri.breedingtool.datamanipulation.handler.SortHandler;
import org.irri.breedingtool.datamanipulation.handler.SubsetHandler;
import org.irri.breedingtool.manager.impl.DataManipulationManager;
import org.irri.breedingtool.manager.impl.FileNameValidator;
import org.irri.breedingtool.manager.impl.ProjectExplorerManager;
import org.irri.breedingtool.projectexplorer.view.ProjectExplorerView;
import org.irri.breedingtool.projectexplorer.view.RJavaManagerInitializer;
import org.irri.breedingtool.utility.WindowDialogControlUtil;
import swing2swt.layout.BorderLayout;

public class FileTableViewer {
    public static boolean invalidDataDetected = false;
    static File openedFile;
    static ArrayList<String> s;
    private boolean needToSave = false;
    static ProjectExplorerTreeNodeModel openedFileModel;
    private ProjectExplorerTreeNodeModel fileModel;
    private int columnSize;
    private int selColumn;
    private int selRow;
    private String[] columnHeaderNames;
    private String[] columnType;
    private Text text;
    private String delimiter;
    private String fileType;
    private static String newFileType;
    private static String newDelimiter;
    private static int newfileFormat;
    private File tableFile;
    private Table table;
    private TableItem selectedItem;
    private TableColumn selectedColumn;
    private Listener columnHeaderListener;
    private DataManipulationManager dataManipulationManager;
    private Shell parentShell;
    private ToolBar toolBarDataManipulation;
    private ToolItem toolItemDeleteColumn;
    private ToolItem toolItemSave;
    private ToolItem toolItemSaveAs;
    private ToolItem toolItemInsertColumn;
    private ToolItem toolItemInsertRow;
    private ToolItem toolItemDeleteRow;
    private ToolItem toolItemSort;
    private ToolItem toolItemMerge;
    private ToolItem toolItemAppend;
    private ToolItem toolItemSubset;
    private ToolItem toolItemAggregate;
    private ToolItem toolItemCreateNewVariable;
    private ArrayList<String> varInfo;
    private ToolItem tltmEditvarinfo;
    private ToolItem tltmTolong;
    private ToolItem tltmTowide;
    protected int fileFormat;
    private ArrayList<String> tableData = new ArrayList();
    private ProjectExplorerManager projectExplorerManager;
    private ToolItem tltmConvertToCsv;
    private ProjectExplorerManager projExpMan = new ProjectExplorerManager();
    private Clipboard clipboard;
    private MenuItem mntmCopy;
    private MenuItem mntmPaste;
    private MenuItem mntmSelectAll;
    private long lastModified;
    @Inject
    private EPartService partService;
    private Label tableInfoLabel;

    @Inject
    public FileTableViewer(Composite parent) {
        this.parentShell = parent.getShell();
        this.fileType = newFileType;
        this.varInfo = s;
        invalidDataDetected = false;
        this.delimiter = newDelimiter;
        this.fileFormat = newfileFormat;
        this.fileModel = openedFileModel;
        this.dataManipulationManager = new DataManipulationManager();
        Composite composite = new Composite(parent, 0);
        composite.setLayout((Layout)new BorderLayout(0, 10));
        parent.getShell().addShellListener((ShellListener)new ShellAdapter(){

            public void shellActivated(ShellEvent e) {
                WindowDialogControlUtil.hideAllWindowDialog();
            }
        });
        this.table = new Table(composite, 268503042);
        this.table.setHeaderVisible(true);
        this.table.setLinesVisible(true);
        this.columnHeaderListener = new Listener(){

            public void handleEvent(Event e) {
                FileTableViewer.this.table.deselectAll();
                try {
                    FileTableViewer.this.selectedColumn.setImage(null);
                }
                catch (NullPointerException nullPointerException) {
                }
                catch (SWTException sWTException) {}
                FileTableViewer.this.selectedColumn = (TableColumn)e.widget;
                FileTableViewer.this.selectedColumn.setImage(new Image((Device)Display.getDefault(), String.valueOf(System.getProperty("user.dir")) + File.separator + "icons" + File.separator + "selected.png"));
                FileTableViewer.this.columnHeaderNames = FileTableViewer.this.dataManipulationManager.updateColHeaderNames(FileTableViewer.this.table, FileTableViewer.this.columnHeaderNames);
                int i = 1;
                while (i < FileTableViewer.this.table.getColumnCount()) {
                    System.out.println(String.valueOf(FileTableViewer.this.columnHeaderNames[i - 1]) + "index" + Integer.toString(i));
                    if (FileTableViewer.this.columnHeaderNames[i - 1].equals(FileTableViewer.this.selectedColumn.getText())) {
                        FileTableViewer.this.selectedColumn.setData("index", (Object)i);
                    }
                    ++i;
                }
                FileTableViewer.this.table.setData("selectedColumn", (Object)FileTableViewer.this.selectedColumn);
            }
        };
        try {
            this.tableData.addAll(Arrays.asList(TxtFileViewer.readFileAsString(openedFileModel.getProjectFile().getAbsolutePath(), null).split(System.getProperty("line.separator"))));
        }
        catch (IOException e1) {
            e1.printStackTrace();
        }
        this.table.setData("columnHeaderListener", (Object)this.columnHeaderListener);
        this.table.setItemCount(this.tableData.size() - 1);
        this.table.setData("ID", (Object)this.fileModel.getProjectFile().getAbsolutePath());
        this.table.setData("fileFormat", (Object)this.fileFormat);
        this.table.setData("selectedColumn", (Object)this.selectedColumn);
        this.table.setData("delimiter", (Object)this.delimiter);
        this.table.setData("tableData", this.tableData);
        DataManipulationManager.addTable(this.table);
        String[] columnString = this.tableData.get(0).replaceAll("\"", "").split(this.delimiter);
        TableColumn tblColumn = new TableColumn(this.table, 131072);
        tblColumn.setResizable(false);
        tblColumn.setWidth(50);
        boolean columnNameHadSpaces = false;
        if (!this.dataManipulationManager.isMatchColumnHeaders(columnString, this.varInfo)) {
            columnString = this.dataManipulationManager.getListItems(this.varInfo);
            columnNameHadSpaces = true;
            this.dataManipulationManager.saveFileChanges(this.fileModel.getProjectFile(), this.table, this.delimiter);
        }
        this.columnHeaderNames = columnString;
        this.table.setData("columnHeaderNames", (Object)this.columnHeaderNames);
        int i = 0;
        while (i < columnString.length) {
            tblColumn = new TableColumn(this.table, 0);
            tblColumn.setWidth(100);
            tblColumn.setData("index", (Object)(i + 1));
            tblColumn.setText(columnString[i]);
            tblColumn.addListener(13, this.columnHeaderListener);
            ++i;
        }
        if (columnNameHadSpaces) {
            MessageDialog.openInformation((Shell)Display.getCurrent().getActiveShell(), (String)"Invalid column names detected.", (String)"The column names were modified.\n\nYou can change the column names using: Data -> Edit Variable Information. ");
        }
        this.tableData.remove(0);
        this.table.addListener(36, new Listener(){

            public void handleEvent(Event event) {
                TableItem item = (TableItem)event.item;
                int index = FileTableViewer.this.table.indexOf(item);
                int start = index / 64 * 64;
                int end = Math.min(start + 64, FileTableViewer.this.table.getItemCount());
                int i = start;
                while (i < end) {
                    item = FileTableViewer.this.table.getItem(i);
                    item.setBackground(0, FileTableViewer.this.table.getShell().getBackground());
                    ArrayList<String> tableRow = new ArrayList<String>();
                    tableRow.add(String.valueOf(i + 1));
                    List<String> parseRowData = Arrays.asList(this.parseString(((String)FileTableViewer.this.tableData.get(i)).split(FileTableViewer.this.delimiter)));
                    tableRow.addAll(parseRowData);
                    item.setText(tableRow.toArray(new String[tableRow.size()]));
                    ++i;
                }
            }

            private String[] parseString(String[] split) {
                ArrayList<String> parsedRowData = new ArrayList<String>();
                String accumulatedString = "";
                String[] stringArray = split;
                int n = split.length;
                int n2 = 0;
                while (n2 < n) {
                    String s = stringArray[n2];
                    if (s.contains("\"")) {
                        accumulatedString = String.valueOf(accumulatedString) + s;
                        if (s.charAt(0) == '\"' && !s.endsWith("\"")) {
                            System.out.println("s: " + s);
                            accumulatedString = String.valueOf(accumulatedString) + ",";
                        } else {
                            parsedRowData.add(accumulatedString.replaceAll("\"", ""));
                            accumulatedString = "";
                        }
                    } else if (accumulatedString.equals("")) {
                        parsedRowData.add(s);
                    } else {
                        accumulatedString = String.valueOf(accumulatedString) + s + ",";
                    }
                    ++n2;
                }
                return parsedRowData.toArray(new String[parsedRowData.size()]);
            }
        });
        this.table.getColumn(0).setAlignment(131072);
        TableEditor editor = new TableEditor(this.table);
        this.dataManipulationManager.saveFileChanges(this.fileModel.getProjectFile(), this.table, this.delimiter);
        this.dataManipulationManager.saveOriginal(this.fileModel.getProjectFile(), this.table, this.delimiter);
        Menu copyPasteMenu = new Menu((Control)this.table);
        this.table.setMenu(copyPasteMenu);
        this.mntmCopy = new MenuItem(copyPasteMenu, 0);
        this.mntmCopy.setText("Copy");
        this.mntmCopy.addListener(13, new Listener(){

            public void handleEvent(Event event) {
                if (FileTableViewer.this.table.getSelectionCount() > 0) {
                    String selectedText = FileTableViewer.this.getSelectedDataFrom(FileTableViewer.this.table);
                    FileTableViewer.this.copySelectedTableDataToClipboard(selectedText);
                } else {
                    MessageDialog.openWarning((Shell)parent.getShell(), (String)"No selected Items to copy.", (String)"Please select the rows from the table that you want to copy!");
                }
            }
        });
        this.mntmPaste = new MenuItem(copyPasteMenu, 0);
        this.mntmPaste.setText("paste");
        this.mntmPaste.addListener(13, new Listener(){

            public void handleEvent(Event event) {
                if (FileTableViewer.this.table.getSelectionCount() < 1) {
                    FileTableViewer.this.table.select(0);
                }
                String clipboardText = FileTableViewer.this.getTextFromClipBoard();
                FileTableViewer.this.pasteClipBoardTextTo(FileTableViewer.this.table, clipboardText);
            }
        });
        this.mntmSelectAll = new MenuItem(copyPasteMenu, 0);
        this.mntmSelectAll.setText("Select All");
        this.mntmSelectAll.addListener(13, new Listener(){

            public void handleEvent(Event event) {
                FileTableViewer.this.table.setSelection(FileTableViewer.this.table.getItems());
            }
        });
        this.toolBarDataManipulation = new ToolBar(composite, 131080);
        this.toolBarDataManipulation.setLayoutData((Object)"North");
        this.tableInfoLabel = new Label(composite, 0);
        this.tableInfoLabel.setLayoutData((Object)"South");
        this.tableInfoLabel.setText("Column(s): " + Integer.toString(this.table.getColumnCount() - 1) + "   Row(s): " + Integer.toString(this.table.getItemCount()));
        this.table.setData("tableInfoLabel", (Object)this.tableInfoLabel);
        if (this.fileModel.getProjectFile().getAbsolutePath().toString().endsWith("csv")) {
            this.toolItemSave = new ToolItem(this.toolBarDataManipulation, 0);
            this.toolItemSave.setSelection(true);
            this.toolItemSave.setEnabled(false);
            this.toolItemSave.setImage(ResourceManager.getPluginImage("Star", "icons/save_edit.gif"));
            this.toolItemSave.setToolTipText("Save Changes");
            this.toolItemSave.addSelectionListener((SelectionListener)new SelectionAdapter(){

                public void widgetSelected(SelectionEvent e) {
                    FileTableViewer.this.dataManipulationManager.saveFileChanges(FileTableViewer.this.fileModel.getProjectFile(), FileTableViewer.this.table, FileTableViewer.this.delimiter);
                    FileTableViewer.this.dataManipulationManager.saveOriginal(FileTableViewer.this.fileModel.getProjectFile(), FileTableViewer.this.table, FileTableViewer.this.delimiter);
                    PartStackHandler.setPartSaved(FileTableViewer.this.fileModel.getProjectFile().getAbsolutePath());
                    FileTableViewer.this.toolItemSave.setEnabled(false);
                }
            });
            this.table.setData("saveBtn", (Object)this.toolItemSave);
            this.toolItemSaveAs = new ToolItem(this.toolBarDataManipulation, 0);
            this.toolItemSaveAs.setImage(ResourceManager.getPluginImage("Star", "icons/saveas.png"));
            this.toolItemSaveAs.setToolTipText("Save File as...");
            this.toolItemSaveAs.addSelectionListener((SelectionListener)new SelectionAdapter(){

                public void widgetSelected(SelectionEvent e) {
                    SaveAsFileHandler.execute(FileTableViewer.this.fileModel, FileTableViewer.this.table);
                }
            });
            this.toolItemCreateNewVariable = new ToolItem(this.toolBarDataManipulation, 0);
            this.toolItemCreateNewVariable.setImage(ResourceManager.getPluginImage("Star", "icons/new_var.png"));
            this.toolItemCreateNewVariable.setToolTipText("Create New Variable");
            this.toolItemCreateNewVariable.addSelectionListener((SelectionListener)new SelectionAdapter(){

                public void widgetSelected(SelectionEvent e) {
                    FileTableViewer.this.dataManipulationManager.saveFileChanges(FileTableViewer.this.fileModel.getProjectFile(), FileTableViewer.this.table, FileTableViewer.this.delimiter);
                    CreateNewVariableHandler.execute(FileTableViewer.this.fileModel, FileTableViewer.this.fileFormat, FileTableViewer.this.delimiter);
                }
            });
            this.tltmEditvarinfo = new ToolItem(this.toolBarDataManipulation, 0);
            this.tltmEditvarinfo.setImage(ResourceManager.getPluginImage("Star", "icons/edit_var.png"));
            this.tltmEditvarinfo.setToolTipText("Edit Variable Info");
            this.tltmEditvarinfo.addSelectionListener((SelectionListener)new SelectionAdapter(){

                public void widgetSelected(SelectionEvent e) {
                    FileTableViewer.this.dataManipulationManager.saveFileChanges(FileTableViewer.this.fileModel.getProjectFile(), FileTableViewer.this.table, FileTableViewer.this.delimiter);
                    EditVariableInfoDialog newVarDlg = new EditVariableInfoDialog(parent.getShell(), FileTableViewer.this.varInfo, FileTableViewer.this.fileModel);
                    newVarDlg.open();
                    FileTableViewer.this.varInfo = newVarDlg.getVarInfo();
                }
            });
            this.toolItemInsertColumn = new ToolItem(this.toolBarDataManipulation, 0);
            this.toolItemInsertColumn.setToolTipText("Insert Column");
            this.toolItemInsertColumn.setImage(ResourceManager.getPluginImage("Star", "icons/table-insert-column-icon.png"));
            this.toolItemInsertColumn.addSelectionListener((SelectionListener)new SelectionAdapter(){

                public void widgetSelected(SelectionEvent e) {
                    InsertColumnMenuHandler.execute(FileTableViewer.this.fileModel.getProjectFile(), FileTableViewer.this.table);
                }
            });
            this.toolItemDeleteColumn = new ToolItem(this.toolBarDataManipulation, 0);
            this.toolItemDeleteColumn.setToolTipText("Delete Column");
            this.toolItemDeleteColumn.setImage(ResourceManager.getPluginImage("Star", "icons/table-delete-column-icon.png"));
            this.toolItemDeleteColumn.addSelectionListener((SelectionListener)new SelectionAdapter(){

                public void widgetSelected(SelectionEvent e) {
                    DeleteColumnMenuHandler.execute(FileTableViewer.this.fileModel.getProjectFile(), FileTableViewer.this.table);
                    FileTableViewer.this.dataManipulationManager.saveFileChanges(FileTableViewer.this.fileModel.getProjectFile(), FileTableViewer.this.table, FileTableViewer.this.delimiter);
                }
            });
            this.toolItemInsertRow = new ToolItem(this.toolBarDataManipulation, 0);
            this.toolItemInsertRow.setImage(ResourceManager.getPluginImage("Star", "icons/table-insert-row-icon.png"));
            this.toolItemInsertRow.setToolTipText("Insert Row");
            this.toolItemInsertRow.addSelectionListener((SelectionListener)new SelectionAdapter(){

                public void widgetSelected(SelectionEvent e) {
                    InsertRowMenuHandler.execute(FileTableViewer.this.table);
                    FileTableViewer.this.dataManipulationManager.saveFileChanges(FileTableViewer.this.fileModel.getProjectFile(), FileTableViewer.this.table, FileTableViewer.this.delimiter);
                }
            });
            this.toolItemDeleteRow = new ToolItem(this.toolBarDataManipulation, 0);
            this.toolItemDeleteRow.setImage(ResourceManager.getPluginImage("Star", "icons/table-delete-row-icon.png"));
            this.toolItemDeleteRow.setToolTipText("Delete Row");
            this.toolItemDeleteRow.addSelectionListener((SelectionListener)new SelectionAdapter(){

                public void widgetSelected(SelectionEvent e) {
                    DeleteRowMenuHandler.execute(FileTableViewer.this.table);
                    FileTableViewer.this.dataManipulationManager.saveFileChanges(FileTableViewer.this.fileModel.getProjectFile(), FileTableViewer.this.table, FileTableViewer.this.delimiter);
                }
            });
            this.toolItemSort = new ToolItem(this.toolBarDataManipulation, 0);
            this.toolItemSort.setToolTipText("Sort Data");
            this.toolItemSort.setImage(ResourceManager.getPluginImage("Star", "icons/sort.png"));
            this.toolItemSort.addSelectionListener((SelectionListener)new SelectionAdapter(){

                public void widgetSelected(SelectionEvent e) {
                    FileTableViewer.this.dataManipulationManager.saveFileChanges(FileTableViewer.this.fileModel.getProjectFile(), FileTableViewer.this.table, FileTableViewer.this.delimiter);
                    SortHandler.execute(FileTableViewer.this.fileModel, FileTableViewer.this.fileFormat, FileTableViewer.this.delimiter);
                }
            });
            this.toolItemMerge = new ToolItem(this.toolBarDataManipulation, 0);
            this.toolItemMerge.setToolTipText("Merge Data");
            this.toolItemMerge.setImage(ResourceManager.getPluginImage("Star", "icons/merge.png"));
            this.toolItemMerge.addSelectionListener((SelectionListener)new SelectionAdapter(){

                public void widgetSelected(SelectionEvent e) {
                    FileTableViewer.this.dataManipulationManager.saveFileChanges(FileTableViewer.this.fileModel.getProjectFile(), FileTableViewer.this.table, FileTableViewer.this.delimiter);
                    MergeHandler.execute(FileTableViewer.this.fileModel, FileTableViewer.this.fileFormat, FileTableViewer.this.delimiter);
                }
            });
            this.toolItemAppend = new ToolItem(this.toolBarDataManipulation, 0);
            this.toolItemAppend.setImage(ResourceManager.getPluginImage("Star", "icons/append.png"));
            this.toolItemAppend.setToolTipText("Append Data");
            this.toolItemAppend.addSelectionListener((SelectionListener)new SelectionAdapter(){

                public void widgetSelected(SelectionEvent e) {
                    FileTableViewer.this.dataManipulationManager.saveFileChanges(FileTableViewer.this.fileModel.getProjectFile(), FileTableViewer.this.table, FileTableViewer.this.delimiter);
                    AppendHandler.execute(FileTableViewer.this.fileModel, FileTableViewer.this.fileFormat, FileTableViewer.this.delimiter);
                }
            });
            this.toolItemSubset = new ToolItem(this.toolBarDataManipulation, 0);
            this.toolItemSubset.setToolTipText("Create Data Subset");
            this.toolItemSubset.setImage(ResourceManager.getPluginImage("Star", "icons/subset.png"));
            this.toolItemSubset.addSelectionListener((SelectionListener)new SelectionAdapter(){

                public void widgetSelected(SelectionEvent e) {
                    FileTableViewer.this.dataManipulationManager.saveFileChanges(FileTableViewer.this.fileModel.getProjectFile(), FileTableViewer.this.table, FileTableViewer.this.delimiter);
                    SubsetHandler.execute(FileTableViewer.this.fileModel, FileTableViewer.this.fileFormat, FileTableViewer.this.delimiter);
                }
            });
            this.toolItemAggregate = new ToolItem(this.toolBarDataManipulation, 0);
            this.toolItemAggregate.setImage(ResourceManager.getPluginImage("Star", "icons/aggreagate.png"));
            this.toolItemAggregate.setToolTipText("Aggregate Data");
            this.toolItemAggregate.addSelectionListener((SelectionListener)new SelectionAdapter(){

                public void widgetSelected(SelectionEvent e) {
                    FileTableViewer.this.dataManipulationManager.saveFileChanges(FileTableViewer.this.fileModel.getProjectFile(), FileTableViewer.this.table, FileTableViewer.this.delimiter);
                    AggregateHandler.execute(FileTableViewer.this.fileModel, FileTableViewer.this.fileFormat, FileTableViewer.this.delimiter);
                }
            });
            this.tltmTolong = new ToolItem(this.toolBarDataManipulation, 0);
            this.tltmTolong.setToolTipText("Reshape: to Long");
            this.tltmTolong.setImage(ResourceManager.getPluginImage("Star", "icons/to_long.png"));
            this.tltmTolong.addSelectionListener((SelectionListener)new SelectionAdapter(){

                public void widgetSelected(SelectionEvent e) {
                    FileTableViewer.this.dataManipulationManager.saveFileChanges(FileTableViewer.this.fileModel.getProjectFile(), FileTableViewer.this.table, FileTableViewer.this.delimiter);
                    ReshapeToLongHandler.execute(FileTableViewer.this.fileModel, FileTableViewer.this.fileFormat, FileTableViewer.this.delimiter);
                }
            });
            this.tltmTowide = new ToolItem(this.toolBarDataManipulation, 0);
            this.tltmTowide.setImage(ResourceManager.getPluginImage("Star", "icons/to_wide.png"));
            this.tltmTowide.setToolTipText("Reshape: to Wide");
            this.tltmTowide.addSelectionListener((SelectionListener)new SelectionAdapter(){

                public void widgetSelected(SelectionEvent e) {
                    FileTableViewer.this.dataManipulationManager.saveFileChanges(FileTableViewer.this.fileModel.getProjectFile(), FileTableViewer.this.table, FileTableViewer.this.delimiter);
                    ReshapeToWideHandler.execute(FileTableViewer.this.fileModel, FileTableViewer.this.fileFormat, FileTableViewer.this.delimiter);
                }
            });
            editor.horizontalAlignment = 16384;
            editor.grabHorizontal = true;
            editor.minimumWidth = 50;
            this.table.addListener(3, new Listener(){

                public void handleEvent(Event event) {
                    try {
                        FileTableViewer.this.selectedColumn.setImage(null);
                    }
                    catch (NullPointerException nullPointerException) {
                    }
                    catch (SWTException sWTException) {}
                    Rectangle clientArea = FileTableViewer.this.table.getClientArea();
                    Point pt = new Point(event.x, event.y);
                    int index = FileTableViewer.this.table.getTopIndex();
                    while (index < FileTableViewer.this.table.getItemCount()) {
                        boolean visible = false;
                        FileTableViewer.this.selectedItem = FileTableViewer.this.table.getItem(index);
                        int i = 1;
                        while (i < FileTableViewer.this.table.getColumnCount()) {
                            Rectangle rect = FileTableViewer.this.selectedItem.getBounds(i);
                            if (rect.contains(pt)) {
                                final int column = i;
                                FileTableViewer.this.selRow = index;
                                FileTableViewer.this.selColumn = i;
                                FileTableViewer.this.text = new Text((Composite)FileTableViewer.this.table, 0);
                                Listener textListener = new Listener(){

                                    public void handleEvent(Event e) {
                                        switch (e.type) {
                                            case 16: {
                                                FileTableViewer.this.selectedItem.setText(column, FileTableViewer.this.text.getText());
                                                ArrayList<String> newData = new ArrayList<String>();
                                                int i = 1;
                                                while (i < FileTableViewer.this.table.getColumnCount()) {
                                                    newData.add(FileTableViewer.this.table.getItem(FileTableViewer.this.table.getSelectionIndex()).getText(i));
                                                    ++i;
                                                }
                                                FileTableViewer.this.tableData.set(FileTableViewer.this.table.getSelectionIndex(), FileTableViewer.implode(newData.toArray(new String[newData.size()]), FileTableViewer.this.delimiter));
                                                FileTableViewer.this.text.dispose();
                                                break;
                                            }
                                            case 31: {
                                                switch (e.detail) {
                                                    case 2: 
                                                    case 4: {
                                                        FileTableViewer.this.selectedItem.setText(column, FileTableViewer.this.text.getText());
                                                        FileTableViewer.this.text.dispose();
                                                        e.doit = false;
                                                    }
                                                }
                                            }
                                            case 1: {
                                                if (!FileTableViewer.this.fileModel.getProjectFile().getAbsolutePath().toString().endsWith(".csv")) break;
                                                FileTableViewer.this.toolItemSave.setEnabled(true);
                                                break;
                                            }
                                            case 2: {
                                                PartStackHandler.setPartModified(FileTableViewer.this.fileModel.getProjectFile().getAbsolutePath());
                                                System.out.println(FileTableViewer.this.text.getText());
                                                try {
                                                    Double.parseDouble(FileTableViewer.this.text.getText());
                                                }
                                                catch (NumberFormatException numberFormatException) {
                                                    FileTableViewer.this.dataManipulationManager.editVariableType(FileTableViewer.this.fileModel.getProjectFile().getAbsolutePath(), FileTableViewer.this.dataManipulationManager.getVarInfoFromFile(FileTableViewer.this.fileModel.getProjectFile().getAbsolutePath()), FileTableViewer.this.table.getColumn(column).getText(), "Factor");
                                                }
                                                FileTableViewer.this.toolItemSave.setEnabled(true);
                                                FileTableViewer.this.selectedItem.setText(column, FileTableViewer.this.text.getText());
                                                FileTableViewer.this.dataManipulationManager.saveFileChanges(FileTableViewer.this.fileModel.getProjectFile(), FileTableViewer.this.table, FileTableViewer.this.delimiter);
                                            }
                                        }
                                    }
                                };
                                FileTableViewer.this.text.addListener(16, textListener);
                                FileTableViewer.this.text.addListener(31, textListener);
                                FileTableViewer.this.text.addListener(1, textListener);
                                FileTableViewer.this.text.addListener(2, textListener);
                                editor.setEditor((Control)FileTableViewer.this.text, FileTableViewer.this.selectedItem, i);
                                FileTableViewer.this.text.setText(FileTableViewer.this.selectedItem.getText(i));
                                FileTableViewer.this.text.selectAll();
                                FileTableViewer.this.text.setFocus();
                                return;
                            }
                            if (!visible && rect.intersects(clientArea)) {
                                visible = true;
                            }
                            ++i;
                        }
                        if (!visible) {
                            return;
                        }
                        ++index;
                    }
                }
            });
        } else {
            this.tltmConvertToCsv = new ToolItem(this.toolBarDataManipulation, 0);
            this.tltmConvertToCsv.setImage(ResourceManager.getPluginImage("Star", "icons/convert.png"));
            this.tltmConvertToCsv.setHotImage(null);
            this.tltmConvertToCsv.setToolTipText("Convert to CSV");
            this.tltmConvertToCsv.addSelectionListener((SelectionListener)new SelectionAdapter(){

                public void widgetSelected(SelectionEvent e) {
                    String selectedFile = FileTableViewer.this.fileModel.getProjectFile().getAbsolutePath();
                    File currFile = new File(selectedFile);
                    String newFileName = selectedFile.replaceAll(".txt", ".csv");
                    if (!new File(newFileName).exists()) {
                        FileTableViewer.this.dataManipulationManager.convertTxtToCsv(selectedFile, newFileName, FileTableViewer.this.delimiter);
                        FileTableViewer.refreshExplorer();
                        ProjectExplorerTreeNodeModel newFileModel = FileTableViewer.this.projExpMan.getTreeNodeModelbyFilePath(newFileName);
                        PartStackHandler.execute(newFileModel);
                        MessageDialog.openInformation((Shell)parent.getShell(), (String)"Converted .txt to .csv", (String)("Successfully created '" + new File(newFileName).getName() + "'!"));
                    } else {
                        InputDialog id = new InputDialog(Display.getCurrent().getActiveShell(), "'" + new File(newFileName).getName() + "' already exist.", "Enter a different file name", "copyOf" + new File(newFileName).getName(), (IInputValidator)new FileNameValidator(currFile));
                        id.open();
                        String selectedNewFileName = String.valueOf(currFile.getParentFile().getAbsolutePath()) + File.separator + id.getValue();
                        if (id.getReturnCode() == 0) {
                            FileTableViewer.this.dataManipulationManager.convertTxtToCsv(selectedFile, selectedNewFileName, FileTableViewer.this.delimiter);
                            FileTableViewer.refreshExplorer();
                            ProjectExplorerTreeNodeModel newFileModel = FileTableViewer.this.projExpMan.getTreeNodeModelbyFilePath(selectedNewFileName);
                            MessageDialog.openInformation((Shell)parent.getShell(), (String)"Converted .txt to .csv", (String)("Successfully created '" + new File(selectedNewFileName).getName() + "'!"));
                            PartStackHandler.execute(newFileModel);
                        }
                    }
                }
            });
        }
    }

    protected void pasteClipBoardTextTo(Table table2, String clipboardText) {
        int offsetRow = this.table.getSelectionIndices()[0];
        int offsetColumn = this.selColumn;
        String lineSeparator = System.getProperty("line.separator");
        Boolean canCopy = true;
        String[] textPerRow = clipboardText.split(lineSeparator);
        this.table.getColumnCount();
        int rowSpace = this.table.getItemCount() - offsetRow;
        if (textPerRow.length > rowSpace) {
            MessageDialog.openInformation((Shell)Display.getCurrent().getActiveShell(), (String)"Cannot paste data to table", (String)"Data on the clipboard is larger than the size of the selected area.");
            canCopy = false;
        }
        if (canCopy.booleanValue()) {
            this.table.setSelection(-1);
            int rowNumber = offsetRow;
            ArrayList<TableItem> tableItems = new ArrayList<TableItem>();
            String[] stringArray = textPerRow;
            int n = textPerRow.length;
            int n2 = 0;
            while (n2 < n) {
                String line = stringArray[n2];
                String[] words = line.split("\t");
                int colNumber = 1;
                String[] stringArray2 = words;
                int n3 = words.length;
                int n4 = 0;
                while (n4 < n3) {
                    String word = stringArray2[n4];
                    TableItem tableItem = this.table.getItem(rowNumber);
                    tableItem.setText(colNumber, word);
                    tableItems.add(tableItem);
                    ++colNumber;
                    ++n4;
                }
                ++rowNumber;
                ++n2;
            }
            this.table.setSelection(tableItems.toArray(new TableItem[tableItems.size()]));
            PartStackHandler.setPartModified(this.fileModel.getProjectFile().getAbsolutePath());
            this.toolItemSave.setEnabled(true);
        }
    }

    protected String getTextFromClipBoard() {
        Clipboard clipboard = new Clipboard(Display.getCurrent());
        clipboard.getAvailableTypes();
        String plainText = (String)clipboard.getContents((Transfer)TextTransfer.getInstance());
        clipboard.dispose();
        return plainText;
    }

    protected String getSelectedDataFrom(Table table2) {
        StringBuilder sb = new StringBuilder();
        int[] SelectionIndices = table2.getSelectionIndices();
        String lineSeparator = System.getProperty("line.separator");
        int numRows = SelectionIndices.length;
        int numCols = this.table.getColumnCount();
        int rowCount = 0;
        while (rowCount < numRows) {
            int colCount = 1;
            while (colCount < numCols) {
                sb.append(this.table.getItem(SelectionIndices[rowCount]).getText(colCount));
                if (colCount != numCols - 1) {
                    sb.append("\t");
                }
                ++colCount;
            }
            if (rowCount != numRows - 1) {
                sb.append(lineSeparator);
            }
            ++rowCount;
        }
        return sb.toString();
    }

    protected void copySelectedTableDataToClipboard(String selectedTableData) {
        Clipboard clipboard = new Clipboard(Display.getCurrent());
        TextTransfer textTransfer = TextTransfer.getInstance();
        clipboard.setContents((Object[])new String[]{selectedTableData}, new Transfer[]{textTransfer});
        clipboard.dispose();
    }

    private static void write(ArrayList<String> records, Writer writer) throws IOException {
        long start = System.currentTimeMillis();
        for (String record : records) {
            writer.write(String.valueOf(record) + System.getProperty("line.separator"));
        }
        writer.flush();
        writer.close();
        long end = System.currentTimeMillis();
        System.out.println(String.valueOf((float)(end - start) / 1000.0f) + " seconds");
    }

    public static void refreshExplorer() {
        ProjectExplorerTreeNodeModel model = openedFileModel;
        ProjectExplorerManager projectExplorerManager = new ProjectExplorerManager();
        try {
            projectExplorerManager.refreshFolder((ProjectExplorerTreeNodeModel)model.getParentTreeItem().getData());
        }
        catch (SWTException sWTException) {}
    }

    public static String implode(String[] array, String separator) {
        StringBuffer out = new StringBuffer();
        boolean first = true;
        String[] stringArray = array;
        int n = array.length;
        int n2 = 0;
        while (n2 < n) {
            String v = stringArray[n2];
            if (first) {
                first = false;
            } else {
                out.append(separator);
            }
            out.append(v);
            ++n2;
        }
        return out.toString();
    }

    public static void openFile(ProjectExplorerTreeNodeModel fileM, String fileType, String delimiter) {
        openedFile = new File(fileM.getProjectFile().getAbsolutePath());
        openedFileModel = fileM;
        newFileType = fileType;
        newDelimiter = delimiter;
        newfileFormat = 1;
        DataManipulationManager dataManager = new DataManipulationManager();
        String fileName = openedFile.toString().replace("\\", "/");
        if (fileName.endsWith(".txt")) {
            newfileFormat = 2;
        }
        Scanner openedFileScanner = null;
        try {
            openedFileScanner = new Scanner(openedFile);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String[] fileColumnHeaders = openedFileScanner.nextLine().split(delimiter);
        openedFileScanner.close();
        String tmpFile = null;
        tmpFile = fileName.endsWith(".csv") ? fileName.replaceAll(".csv", RJavaManagerInitializer.VARINFO_TMPFILE_EXTENSION) : fileName.replaceAll(".txt", RJavaManagerInitializer.VARINFO_TMPFILE_EXTENSION);
        try {
            Scanner newScanner = new Scanner(new File(tmpFile));
            s = dataManager.getVarInfoFromFile(fileM.getProjectFile().getAbsolutePath());
            if (!dataManager.isMatchColumnHeaders(fileColumnHeaders, s)) {
                s = ProjectExplorerView.rJavaManager.getRJavaDataManipulationManager().getVariableInfo(fileName, tmpFile, newfileFormat, delimiter);
                System.out.println("overwrite column headers");
            } else {
                System.out.println("same column headers");
            }
            newScanner.close();
        }
        catch (FileNotFoundException fileNotFoundException) {
            s = ProjectExplorerView.rJavaManager.getRJavaDataManipulationManager().getVariableInfo(fileName, tmpFile, newfileFormat, delimiter);
            System.out.println("created new tmp file");
        }
    }

    @Persist
    public void saveData(MPart part) {
        System.out.println(String.valueOf(part.getElementId()) + " THIS");
        ProjectExplorerManager projMan = new ProjectExplorerManager();
        String elemId = part.getElementId();
        Table table = DataManipulationManager.getActiveTable(elemId);
        DataManipulationManager.removeTable(table);
        WindowDialogControlUtil.closeAllWindowDialogOfFile(part.getElementId());
        String originalTmpFilepath = part.getElementId().replaceAll(".csv", RJavaManagerInitializer.VARINFO_ORIGINALFILE_EXTENSION);
        projMan.deleteFile(originalTmpFilepath);
        part.setDirty(false);
    }

    @PersistState
    public void disposeData(MPart part) {
        String elemId = part.getElementId();
        if (part.isDirty()) {
            this.dataManipulationManager.keepOriginalFile(new File(elemId));
        }
        WindowDialogControlUtil.closeAllWindowDialogOfFile(part.getElementId());
        try {
            Table table2 = DataManipulationManager.getActiveTable(elemId);
            DataManipulationManager.removeTable(table2);
        }
        catch (Exception exception) {}
    }

    @PreDestroy
    public void predispose(MPart part) {
        DialogHandler.closeAllPartDialogs(part.getElementId());
    }

    @PostConstruct
    void onClassActivation(MPart part) {
        File activeDataFile = new File(part.getElementId());
        this.lastModified = activeDataFile.lastModified();
    }

    @Focus
    public void onFocus(MPart part) {
        DialogHandler.setPartDialogMaximized(PartStackHandler.getActiveElementID());
    }
}

