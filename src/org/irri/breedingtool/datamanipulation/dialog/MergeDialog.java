/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.jface.dialogs.Dialog
 *  org.eclipse.jface.dialogs.IDialogConstants
 *  org.eclipse.jface.dialogs.MessageDialog
 *  org.eclipse.swt.custom.CCombo
 *  org.eclipse.swt.events.SelectionAdapter
 *  org.eclipse.swt.events.SelectionEvent
 *  org.eclipse.swt.events.SelectionListener
 *  org.eclipse.swt.graphics.Point
 *  org.eclipse.swt.layout.GridData
 *  org.eclipse.swt.layout.GridLayout
 *  org.eclipse.swt.widgets.Button
 *  org.eclipse.swt.widgets.Combo
 *  org.eclipse.swt.widgets.Composite
 *  org.eclipse.swt.widgets.Control
 *  org.eclipse.swt.widgets.Display
 *  org.eclipse.swt.widgets.Event
 *  org.eclipse.swt.widgets.FileDialog
 *  org.eclipse.swt.widgets.Group
 *  org.eclipse.swt.widgets.Label
 *  org.eclipse.swt.widgets.Layout
 *  org.eclipse.swt.widgets.List
 *  org.eclipse.swt.widgets.Listener
 *  org.eclipse.swt.widgets.Shell
 *  org.eclipse.swt.widgets.TreeItem
 */
package org.irri.breedingtool.datamanipulation.dialog;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.wb.swt.SWTResourceManager;
import org.irri.breedingtool.application.handler.PartStackHandler;
import org.irri.breedingtool.application.model.ProjectExplorerTreeNodeModel;
import org.irri.breedingtool.manager.impl.DataManipulationManager;
import org.irri.breedingtool.manager.impl.ProjectExplorerManager;
import org.irri.breedingtool.projectexplorer.view.ProjectExplorerView;
import org.irri.breedingtool.projectexplorer.view.RJavaManagerInitializer;

public class MergeDialog
extends Dialog {
    private String[] delimiter = new String[]{",", " ", "\t", ";"};
    private String separator = "NULL";
    private int fileFormat = 1;
    private String dataFileName;
    String[] observationsToInclude = new String[]{"common observations", "all observations in the active data", "all observations in the transaction data", "all observations"};
    String[] delimiterList = new String[]{"comma", "space", "tab", "semicolon"};
    private Combo delimiterCombo;
    private CCombo transactionFileNameCombo;
    private List transactionDataList;
    private List activeDataList;
    private List keyVariableList;
    private List othersList;
    private ArrayList<String> keyVariablesMasterList = new ArrayList();
    private ArrayList<String> keyVariablesTransactList = new ArrayList();
    private ArrayList<String> otherVariablesMasterList = new ArrayList();
    private ArrayList<String> otherVariablesTransactList = new ArrayList();
    private DataManipulationManager dataManipulationManager = new DataManipulationManager();
    private Combo observationsCombo;
    private String transactionFileName;
    private String transactionName;
    private ProjectExplorerManager projExpMan;
    private Button addBtn;
    private Button pairBtn;
    private ProjectExplorerTreeNodeModel fileModel;

    public MergeDialog(Shell parentShell, ProjectExplorerTreeNodeModel fileModel) {
        super(parentShell);
        this.fileModel = fileModel;
        this.projExpMan = new ProjectExplorerManager();
    }

    protected Control createDialogArea(Composite parent) {
        parent.getShell().setText("Merge Data: " + this.dataManipulationManager.getDisplayFileName(this.fileModel.getProjectFile().getAbsolutePath()));
        Composite container = (Composite)super.createDialogArea(parent);
        this.dataFileName = this.fileModel.getProjectFile().getAbsolutePath();
        Group group = new Group(container, 0);
        group.setLayout((Layout)new GridLayout(4, false));
        GridData gd_group = new GridData(4, 4, true, false, 1, 1);
        gd_group.heightHint = 97;
        gd_group.widthHint = 425;
        group.setLayoutData((Object)gd_group);
        Label lblNewLabel = new Label((Composite)group, 0);
        lblNewLabel.setLayoutData((Object)new GridData(16384, 4, false, false, 1, 1));
        lblNewLabel.setFont(SWTResourceManager.getFont("Tahoma", 9, 0));
        lblNewLabel.setText("Transaction Data File Name:");
        new Label((Composite)group, 0);
        this.transactionFileNameCombo = new CCombo((Composite)group, 2048);
        this.transactionFileNameCombo.setEditable(false);
        this.transactionFileNameCombo.setBackground(SWTResourceManager.getColor(1));
        this.transactionFileNameCombo.setLayoutData((Object)new GridData(4, 0x1000000, true, true, 1, 1));
        this.transactionFileNameCombo.setItems(this.dataManipulationManager.getFilesFromDir(this.fileModel));
        this.transactionFileNameCombo.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                MergeDialog.this.delimiterCombo.select(-1);
                MergeDialog.this.resetList();
                MergeDialog.this.transactionFileName = String.valueOf(MergeDialog.this.fileModel.getProjectFile().getParentFile().getAbsolutePath()) + System.getProperty("file.separator") + MergeDialog.this.transactionFileNameCombo.getText();
                if (MergeDialog.this.transactionFileName.endsWith(".txt")) {
                    String tmpFile = MergeDialog.this.transactionFileName.replaceAll(".txt", RJavaManagerInitializer.VARINFO_TMPFILE_EXTENSION);
                    MergeDialog.this.projExpMan.deleteFile(tmpFile);
                }
                MergeDialog.this.setTransactionDataFromFile(MergeDialog.this.transactionFileName);
            }

            public void widgetDefaultSelected(SelectionEvent e) {
            }
        });
        Button btnBrowse = new Button((Composite)group, 0);
        btnBrowse.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                FileDialog fd = new FileDialog(MergeDialog.this.getShell(), 4096);
                fd.setText("Choose File Input");
                ProjectExplorerManager projectExplorerManger = new ProjectExplorerManager();
                fd.setFilterPath(((ProjectExplorerTreeNodeModel)projectExplorerManger.getDataFolder(ProjectExplorerView.projectTree).getData()).getProjectFile().getAbsolutePath());
                String[] filterExt = new String[]{"*.csv", "*.txt"};
                fd.setFilterExtensions(filterExt);
                try {
                    String selected = fd.open();
                    MergeDialog.this.resetList();
                    MergeDialog.this.delimiterCombo.setItems(MergeDialog.this.delimiterList);
                    MergeDialog.this.transactionFileNameCombo.setText(new File(selected).getName().toString());
                    MergeDialog.this.transactionFileName = selected;
                    System.out.println(selected);
                    MergeDialog.this.setTransactionDataFromFile(selected);
                }
                catch (Exception exception) {}
            }
        });
        btnBrowse.setLayoutData((Object)new GridData(4, 0x1000000, true, false, 1, 1));
        btnBrowse.setText("Browse");
        Label lblTextFileDelimeter = new Label((Composite)group, 0);
        lblTextFileDelimeter.setLayoutData((Object)new GridData(16384, 4, false, false, 1, 1));
        lblTextFileDelimeter.setText("Text File Delimeter:");
        lblTextFileDelimeter.setFont(SWTResourceManager.getFont("Tahoma", 9, 0));
        new Label((Composite)group, 0);
        this.delimiterCombo = new Combo((Composite)group, 12);
        this.delimiterCombo.setLayoutData((Object)new GridData(4, 4, true, true, 1, 1));
        this.delimiterCombo.setEnabled(false);
        this.delimiterCombo.setItems(this.delimiterList);
        this.delimiterCombo.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                MergeDialog.this.separator = MergeDialog.this.delimiter[MergeDialog.this.delimiterCombo.getSelectionIndex()];
                MergeDialog.this.resetList();
                String tmpFile = MergeDialog.this.transactionFileName.replaceAll(".txt", RJavaManagerInitializer.VARINFO_TMPFILE_EXTENSION);
                MergeDialog.this.projExpMan.deleteFile(tmpFile);
                MergeDialog.this.setTransactionDataFromFile(MergeDialog.this.transactionFileName);
            }

            public void widgetDefaultSelected(SelectionEvent e) {
            }
        });
        new Label((Composite)group, 0);
        Label lblObservationsToInclude = new Label((Composite)group, 0);
        lblObservationsToInclude.setLayoutData((Object)new GridData(16384, 0x1000000, false, false, 2, 1));
        lblObservationsToInclude.setText("Observations to Include:");
        lblObservationsToInclude.setFont(SWTResourceManager.getFont("Tahoma", 9, 0));
        this.observationsCombo = new Combo((Composite)group, 12);
        this.observationsCombo.setLayoutData((Object)new GridData(4, 4, true, true, 1, 1));
        this.observationsCombo.setItems(this.observationsToInclude);
        this.observationsCombo.select(0);
        new Label((Composite)group, 0);
        Group grpVariablesInThe = new Group(container, 0);
        grpVariablesInThe.setFont(SWTResourceManager.getFont("Tahoma", 9, 0));
        grpVariablesInThe.setText("Variables in the...");
        grpVariablesInThe.setLayout((Layout)new GridLayout(3, false));
        GridData gd_grpVariablesInThe = new GridData(4, 4, true, true, 1, 4);
        gd_grpVariablesInThe.widthHint = 429;
        gd_grpVariablesInThe.heightHint = 217;
        grpVariablesInThe.setLayoutData((Object)gd_grpVariablesInThe);
        Label lblNewLabel_1 = new Label((Composite)grpVariablesInThe, 0);
        lblNewLabel_1.setLayoutData((Object)new GridData(4, 4, true, false, 1, 1));
        lblNewLabel_1.setFont(SWTResourceManager.getFont("Tahoma", 9, 0));
        lblNewLabel_1.setText("Active Data:");
        Group grpNewWokingData = new Group((Composite)grpVariablesInThe, 0);
        GridData gd_grpNewWokingData = new GridData(4, 4, true, true, 2, 4);
        gd_grpNewWokingData.heightHint = 214;
        grpNewWokingData.setLayoutData((Object)gd_grpNewWokingData);
        grpNewWokingData.setFont(SWTResourceManager.getFont("Tahoma", 9, 0));
        grpNewWokingData.setText("New Woking Data File:");
        grpNewWokingData.setLayout((Layout)new GridLayout(2, false));
        new Label((Composite)grpNewWokingData, 0);
        Label lblKeyVariables = new Label((Composite)grpNewWokingData, 0);
        lblKeyVariables.setLayoutData((Object)new GridData(4, 4, true, false, 1, 1));
        lblKeyVariables.setText("Key Variables:");
        lblKeyVariables.setFont(SWTResourceManager.getFont("Tahoma", 9, 0));
        this.pairBtn = new Button((Composite)grpNewWokingData, 0);
        GridData gd_pairBtn = new GridData(4, 1024, false, false, 1, 1);
        gd_pairBtn.widthHint = 71;
        this.pairBtn.setLayoutData((Object)gd_pairBtn);
        this.pairBtn.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                String[] selectedActiveVars = MergeDialog.this.activeDataList.getSelection();
                String[] selectedTransactionVars = MergeDialog.this.transactionDataList.getSelection();
                if (selectedActiveVars.length != 1 || selectedTransactionVars.length != 1) {
                    MessageDialog.openWarning((Shell)Display.getCurrent().getActiveShell(), (String)"Wrong Variable Pairing", (String)"Please choose one variable from the active data and one from the transaction data.");
                } else {
                    MergeDialog.this.keyVariableList.add(String.valueOf(selectedActiveVars[0]) + " & " + selectedTransactionVars[0]);
                    MergeDialog.this.keyVariablesMasterList.add(selectedActiveVars[0]);
                    MergeDialog.this.keyVariablesTransactList.add(selectedTransactionVars[0]);
                    MergeDialog.this.activeDataList.remove(MergeDialog.this.activeDataList.getSelectionIndex());
                    MergeDialog.this.transactionDataList.remove(MergeDialog.this.transactionDataList.getSelectionIndex());
                    MergeDialog.this.transactionName = MergeDialog.this.transactionFileNameCombo.getText().replaceAll(".csv", "_");
                    MergeDialog.this.transactionName = MergeDialog.this.transactionName.replaceAll(".txt", "_");
                    MergeDialog.this.transactionFileNameCombo.setEnabled(false);
                }
            }
        });
        this.pairBtn.setText("Pair");
        this.keyVariableList = new List((Composite)grpNewWokingData, 2816);
        this.keyVariableList.setLayoutData((Object)new GridData(4, 4, true, true, 1, 1));
        new Label((Composite)grpNewWokingData, 0);
        this.keyVariableList.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (MergeDialog.this.activeDataList.getSelectionCount() > 0) {
                    MergeDialog.this.enableRemove(true);
                }
            }
        });
        Label lblOther = new Label((Composite)grpNewWokingData, 0);
        lblOther.setLayoutData((Object)new GridData(16384, 4, true, false, 1, 1));
        lblOther.setText("Other:");
        lblOther.setFont(SWTResourceManager.getFont("Tahoma", 9, 0));
        this.addBtn = new Button((Composite)grpNewWokingData, 0);
        GridData gd_addBtn = new GridData(16384, 128, false, false, 1, 1);
        gd_addBtn.widthHint = 75;
        this.addBtn.setLayoutData((Object)gd_addBtn);
        this.addBtn.setText("Add");
        this.addBtn.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (MergeDialog.this.addBtn.getText().equals("Add")) {
                    String[] selectedActiveVars = MergeDialog.this.activeDataList.getSelection();
                    String[] selectedTransactionVars = MergeDialog.this.transactionDataList.getSelection();
                    if (selectedActiveVars.length < 1 && selectedTransactionVars.length < 1) {
                        MessageDialog.openWarning((Shell)Display.getCurrent().getActiveShell(), (String)"Select Variables", (String)"Please select variables from the active data or from the transaction data.");
                    } else {
                        String selectedString;
                        int i = 0;
                        while (i < selectedActiveVars.length) {
                            selectedString = selectedActiveVars[i];
                            selectedString = String.valueOf(selectedString) + "(Active)";
                            MergeDialog.this.othersList.add(selectedString);
                            MergeDialog.this.otherVariablesMasterList.add(selectedActiveVars[i]);
                            ++i;
                        }
                        i = 0;
                        while (i < selectedTransactionVars.length) {
                            selectedString = selectedTransactionVars[i];
                            selectedString = String.valueOf(selectedString) + "(Transaction)";
                            MergeDialog.this.othersList.add(selectedString);
                            MergeDialog.this.otherVariablesTransactList.add(selectedTransactionVars[i]);
                            ++i;
                        }
                        MergeDialog.this.activeDataList.remove(MergeDialog.this.activeDataList.getSelectionIndices());
                        MergeDialog.this.transactionDataList.remove(MergeDialog.this.transactionDataList.getSelectionIndices());
                    }
                } else {
                    int i;
                    int[] selectedKeyVars = MergeDialog.this.keyVariableList.getSelectionIndices();
                    int[] selectedOtherVars = MergeDialog.this.othersList.getSelectionIndices();
                    if (selectedKeyVars.length > 0) {
                        i = 0;
                        while (i < selectedKeyVars.length) {
                            MergeDialog.this.keyVariableList.remove(selectedKeyVars[i]);
                            MergeDialog.this.activeDataList.add((String)MergeDialog.this.keyVariablesMasterList.get(selectedKeyVars[i]));
                            MergeDialog.this.keyVariablesMasterList.remove(selectedKeyVars[i]);
                            MergeDialog.this.transactionDataList.add((String)MergeDialog.this.keyVariablesTransactList.get(selectedKeyVars[i]));
                            MergeDialog.this.keyVariablesTransactList.remove(selectedKeyVars[i]);
                            ++i;
                        }
                    }
                    if (selectedOtherVars.length > 0) {
                        i = 0;
                        while (i < selectedOtherVars.length) {
                            String selectedString = MergeDialog.this.othersList.getItem(selectedOtherVars[i]);
                            if (selectedString.endsWith("(Active)")) {
                                selectedString = selectedString.replaceAll("(Active)", "");
                                selectedString = selectedString.substring(0, selectedString.length() - 2);
                                MergeDialog.this.activeDataList.add(selectedString);
                                MergeDialog.this.otherVariablesMasterList.remove(selectedString);
                            } else {
                                selectedString = selectedString.replaceAll("(Transaction)", "");
                                selectedString = selectedString.substring(0, selectedString.length() - 2);
                                MergeDialog.this.transactionDataList.add(selectedString);
                                MergeDialog.this.otherVariablesTransactList.remove(selectedString);
                            }
                            System.out.println("selected String :" + selectedString);
                            MergeDialog.this.othersList.remove(selectedOtherVars[i]);
                            ++i;
                        }
                    }
                }
            }
        });
        this.othersList = new List((Composite)grpNewWokingData, 2816);
        this.othersList.setLayoutData((Object)new GridData(4, 4, true, true, 1, 1));
        this.othersList.addListener(3, new Listener(){

            public void handleEvent(Event event) {
                if (MergeDialog.this.othersList.getSelectionCount() > 0) {
                    MergeDialog.this.enableRemove(true);
                }
            }
        });
        this.activeDataList = new List((Composite)grpVariablesInThe, 2818);
        GridData gd_activeDataList = new GridData(4, 4, true, true, 1, 1);
        gd_activeDataList.heightHint = 95;
        this.activeDataList.setLayoutData((Object)gd_activeDataList);
        ArrayList<String> s = this.dataManipulationManager.getVarInfoFromFile(this.dataFileName);
        this.activeDataList.setItems(this.dataManipulationManager.getListItems(s));
        this.activeDataList.addListener(3, new Listener(){

            public void handleEvent(Event event) {
                MergeDialog.this.enableRemove(false);
            }
        });
        Label lblTransactionData = new Label((Composite)grpVariablesInThe, 0);
        lblTransactionData.setLayoutData((Object)new GridData(4, 4, true, false, 1, 1));
        lblTransactionData.setText("Transaction Data:");
        lblTransactionData.setFont(SWTResourceManager.getFont("Tahoma", 9, 0));
        this.transactionDataList = new List((Composite)grpVariablesInThe, 2818);
        GridData gd_transactionDataList = new GridData(4, 4, true, true, 1, 1);
        gd_transactionDataList.heightHint = 95;
        this.transactionDataList.setLayoutData((Object)gd_transactionDataList);
        this.transactionDataList.addListener(3, new Listener(){

            public void handleEvent(Event event) {
                if (MergeDialog.this.transactionDataList.getItemCount() < 1) {
                    if (!MergeDialog.this.transactionFileNameCombo.getText().isEmpty()) {
                        MessageDialog.openWarning((Shell)MergeDialog.this.getShell(), (String)"Empty Transaction File", (String)"Please choose a different transaction file.");
                    } else {
                        MessageDialog.openWarning((Shell)MergeDialog.this.getShell(), (String)"Transaction File", (String)"Please choose a transaction file first.");
                    }
                } else if (MergeDialog.this.transactionDataList.getItemCount() > 0 && MergeDialog.this.delimiterCombo.getText().isEmpty() && MergeDialog.this.delimiterCombo.getEnabled()) {
                    MessageDialog.openWarning((Shell)MergeDialog.this.getShell(), (String)"Transaction File", (String)"Please choose a the proper delimiter for your selected .txt transaction file.");
                } else {
                    MergeDialog.this.enableRemove(false);
                }
            }
        });
        return container;
    }

    protected void enableRemove(boolean state) {
        this.pairBtn.setEnabled(!state);
        if (state) {
            this.addBtn.setText("Remove");
            this.activeDataList.setSelection(-1);
            this.transactionDataList.setSelection(-1);
        } else {
            this.addBtn.setText("Add");
            this.keyVariableList.setSelection(-1);
            this.othersList.setSelection(-1);
        }
    }

    protected void resetList() {
        this.transactionDataList.removeAll();
        this.keyVariableList.removeAll();
        this.othersList.removeAll();
        this.activeDataList.removeAll();
        this.keyVariablesMasterList.clear();
        this.keyVariablesTransactList.clear();
        this.otherVariablesMasterList.clear();
        this.otherVariablesTransactList.clear();
        ArrayList<String> s = this.dataManipulationManager.getVarInfoFromFile(this.fileModel.getProjectFile().getAbsolutePath());
        this.activeDataList.setItems(this.dataManipulationManager.getListItems(s));
    }

    protected void setTransactionDataFromFile(String transactionFileName2) {
        ArrayList s = null;
        int format = 1;
        String tmpFile = "";
        this.transactionFileName = transactionFileName2;
        if (transactionFileName2.endsWith(".txt")) {
            this.delimiterCombo.setEnabled(true);
            tmpFile = transactionFileName2.replaceAll(".txt", RJavaManagerInitializer.VARINFO_TMPFILE_EXTENSION);
            format = 2;
        } else {
            this.delimiterCombo.select(0);
            this.delimiterCombo.setEnabled(false);
            tmpFile = transactionFileName2.replaceAll(".csv", RJavaManagerInitializer.VARINFO_TMPFILE_EXTENSION);
        }
        if (this.delimiterCombo.getSelectionIndex() >= 0) {
            try {
                Scanner newScanner = new Scanner(new File(tmpFile));
                s = this.dataManipulationManager.getVarInfoFromFile(transactionFileName2);
                System.out.println("somehow, " + tmpFile + " still exists");
                newScanner.close();
            }
            catch (FileNotFoundException fileNotFoundException) {
                System.out.println("filenotFound");
                String rJavaTmpFile = tmpFile.replaceAll("\\\\", "/");
                String dataFile = transactionFileName2.replaceAll("\\\\", "/");
                s = ProjectExplorerView.rJavaManager.getRJavaDataManipulationManager().getVariableInfo(dataFile, rJavaTmpFile, format, this.separator);
            }
            this.transactionDataList.setItems(this.dataManipulationManager.getListItems(s));
        }
    }

    protected void okPressed() {
        if (this.keyVariableList.getItemCount() < 1) {
            MessageDialog.openWarning((Shell)Display.getCurrent().getActiveShell(), (String)"Select Variables", (String)"Please pair variables from the active data and transaction data.");
        } else {
            String[] keyVariablesMaster = this.keyVariablesMasterList.toArray(new String[this.keyVariablesMasterList.size()]);
            String[] keyVariablesTransact = this.keyVariablesTransactList.toArray(new String[this.keyVariablesTransactList.size()]);
            String[] otherVariablesMaster = this.otherVariablesMasterList.toArray(new String[this.otherVariablesMasterList.size()]);
            String[] otherVariablesTransact = this.otherVariablesTransactList.toArray(new String[this.otherVariablesTransactList.size()]);
            int option = this.observationsCombo.getSelectionIndex();
            String tmptransactionFileName = "";
            if (this.transactionFileName.endsWith(".txt")) {
                tmptransactionFileName = this.transactionFileName.replaceAll(".txt", "_tmpFile.csv");
                this.dataManipulationManager.convertTxtToCsv(this.transactionFileName, tmptransactionFileName, this.separator);
                this.transactionFileName = tmptransactionFileName.replaceAll("\\\\+", "/");
            } else {
                tmptransactionFileName = this.transactionFileName;
                this.transactionFileName = tmptransactionFileName.replaceAll("\\\\+", "/");
            }
            this.dataFileName = this.fileModel.getProjectFile().getAbsolutePath().toString().replaceAll("\\\\+", "/");
            String fileNameExtension = this.dataManipulationManager.getManipulatedFileNameExtension("_" + this.transactionName + "_merge", this.fileModel.getProjectFile().getAbsolutePath());
            String newFileName = this.dataFileName.replaceAll(".csv", fileNameExtension);
            newFileName = newFileName.replaceAll(".txt", "_" + this.transactionName + "_merge.csv");
            ProjectExplorerView.rJavaManager.getRJavaDataManipulationManager().mergeData(this.dataFileName, this.transactionFileName, newFileName, keyVariablesMaster, keyVariablesTransact, otherVariablesMaster, otherVariablesTransact, option);
            if (tmptransactionFileName.endsWith("_tmpFile.csv")) {
                this.projExpMan.deleteFile(tmptransactionFileName);
            }
            this.close();
            String newFile = this.fileModel.getProjectFile().getName().replaceAll(".csv", fileNameExtension);
            TreeItem sortedTree = this.projExpMan.getTreeNodeByName(this.fileModel.getParentTreeItem(), newFile);
            try {
                if (((ProjectExplorerTreeNodeModel)sortedTree.getData()).getElementInStack() == null) {
                    PartStackHandler.execute(this.projExpMan.createNewProjectExplorerModel((ProjectExplorerTreeNodeModel)this.fileModel.getParentTreeItem().getData(), newFile));
                } else {
                    PartStackHandler.reOpenPart((ProjectExplorerTreeNodeModel)sortedTree.getData());
                }
            }
            catch (NullPointerException nullPointerException) {
                MessageDialog.openWarning((Shell)Display.getCurrent().getActiveShell(), (String)"Merge was Unsuccessful", (String)"Failed to create merged file.\n\nAre you sure you specified the correct delimiter and/or variables?");
            }
        }
    }

    protected void buttonPressed(int buttonId) {
        if (buttonId == 19) {
            this.transactionFileNameCombo.setEnabled(true);
            this.transactionFileNameCombo.setItems(this.dataManipulationManager.getFilesFromDir(this.fileModel));
            this.delimiterCombo.setItems(this.delimiterList);
            this.delimiterCombo.setEnabled(false);
            this.resetList();
        }
        super.buttonPressed(buttonId);
    }

    protected void createButtonsForButtonBar(Composite parent) {
        this.createButton(parent, 19, "Reset", true);
        this.createButton(parent, 0, "Ok", true);
        this.createButton(parent, 1, IDialogConstants.CANCEL_LABEL, false);
    }

    protected Point getInitialSize() {
        return new Point(729, 489);
    }

    protected boolean isResizable() {
        return true;
    }
}

