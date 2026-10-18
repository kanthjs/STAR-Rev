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

public class AppendDialog
extends Dialog {
    private List transactionDataList;
    private List workingDataList;
    private CCombo transactionFileNameCombo;
    private Combo delimiterCombo;
    private String fileName;
    private String[] delimiter = new String[]{",", " ", "\t", ";"};
    private String separator = "NULL";
    private int fileFormat = 1;
    private String dataFileName;
    private ProjectExplorerTreeNodeModel fileModel;
    private DataManipulationManager dataManipulationManager = new DataManipulationManager();
    private List activeDataList;
    protected ArrayList<String> keyVariablesMasterList = new ArrayList();
    protected ArrayList<String> keyVariablesTransactList = new ArrayList();
    protected ArrayList<String> otherVariablesMasterList = new ArrayList();
    protected ArrayList<String> otherVariablesTransactList = new ArrayList();
    private String transactionFileName;
    private String transactionName;
    private ProjectExplorerManager projExpMan;
    private Button pairBtn;
    private Button addBtn;

    public AppendDialog(Shell parentShell, ProjectExplorerTreeNodeModel fileModel) {
        super(parentShell);
        this.fileModel = fileModel;
        this.projExpMan = new ProjectExplorerManager();
    }

    protected Control createDialogArea(Composite parent) {
        parent.getShell().setText("Append Data: " + this.dataManipulationManager.getDisplayFileName(this.fileModel.getProjectFile().getAbsolutePath()));
        Composite container = (Composite)super.createDialogArea(parent);
        this.dataFileName = this.fileModel.getProjectFile().getAbsolutePath();
        Group group = new Group(container, 0);
        group.setLayout((Layout)new GridLayout(3, false));
        GridData gd_group = new GridData(4, 4, true, false, 1, 1);
        gd_group.heightHint = 74;
        group.setLayoutData((Object)gd_group);
        Label lblTransactionDataFile = new Label((Composite)group, 0);
        lblTransactionDataFile.setLayoutData((Object)new GridData(4, 4, false, false, 1, 1));
        lblTransactionDataFile.setText("Transaction Data File Name:");
        lblTransactionDataFile.setFont(SWTResourceManager.getFont("Tahoma", 9, 0));
        this.transactionFileNameCombo = new CCombo((Composite)group, 2048);
        this.transactionFileNameCombo.setBackground(SWTResourceManager.getColor(1));
        this.transactionFileNameCombo.setEditable(false);
        this.transactionFileNameCombo.setLayoutData((Object)new GridData(4, 4, true, false, 1, 1));
        this.transactionFileNameCombo.setItems(this.dataManipulationManager.getFilesFromDir(this.fileModel));
        this.transactionFileNameCombo.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                AppendDialog.this.delimiterCombo.select(-1);
                AppendDialog.this.resetList();
                AppendDialog.this.transactionFileName = String.valueOf(AppendDialog.this.fileModel.getProjectFile().getParentFile().getAbsolutePath()) + System.getProperty("file.separator") + AppendDialog.this.transactionFileNameCombo.getText();
                if (AppendDialog.this.transactionFileName.endsWith(".txt")) {
                    String tmpFile = AppendDialog.this.transactionFileName.replaceAll(".txt", RJavaManagerInitializer.VARINFO_TMPFILE_EXTENSION);
                    AppendDialog.this.projExpMan.deleteFile(tmpFile);
                }
                AppendDialog.this.setTransactionDataFromFile(AppendDialog.this.transactionFileName);
            }

            public void widgetDefaultSelected(SelectionEvent e) {
            }
        });
        Button button = new Button((Composite)group, 0);
        button.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                FileDialog fd = new FileDialog(AppendDialog.this.getShell(), 4096);
                fd.setText("Choose File Input");
                ProjectExplorerManager projectExplorerManger = new ProjectExplorerManager();
                fd.setFilterPath(((ProjectExplorerTreeNodeModel)projectExplorerManger.getDataFolder(ProjectExplorerView.projectTree).getData()).getProjectFile().getAbsolutePath());
                String[] filterExt = new String[]{"*.csv", "*.txt"};
                fd.setFilterExtensions(filterExt);
                try {
                    String selected = fd.open();
                    AppendDialog.this.delimiterCombo.setItems(new String[]{"comma", "space", "tab", "semicolon"});
                    AppendDialog.this.resetList();
                    AppendDialog.this.transactionFileNameCombo.setText(new File(selected).getName().toString());
                    AppendDialog.this.transactionFileName = selected;
                    AppendDialog.this.setTransactionDataFromFile(selected);
                }
                catch (Exception exception) {}
            }
        });
        GridData gd_button = new GridData(16384, 4, true, false, 1, 1);
        gd_button.widthHint = 97;
        button.setLayoutData((Object)gd_button);
        button.setText("Browse");
        Label label_1 = new Label((Composite)group, 0);
        label_1.setLayoutData((Object)new GridData(4, 4, false, false, 1, 1));
        label_1.setText("Text File Delimeter:");
        label_1.setFont(SWTResourceManager.getFont("Tahoma", 9, 0));
        this.delimiterCombo = new Combo((Composite)group, 8);
        this.delimiterCombo.setLayoutData((Object)new GridData(4, 4, true, false, 1, 1));
        this.delimiterCombo.setItems(new String[]{"comma", "space", "tab", "semicolon"});
        this.delimiterCombo.setEnabled(false);
        this.delimiterCombo.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                AppendDialog.this.separator = AppendDialog.this.delimiter[AppendDialog.this.delimiterCombo.getSelectionIndex()];
                AppendDialog.this.resetList();
                String tmpFile = AppendDialog.this.transactionFileName.replaceAll(".txt", RJavaManagerInitializer.VARINFO_TMPFILE_EXTENSION);
                AppendDialog.this.projExpMan.deleteFile(tmpFile);
                AppendDialog.this.setTransactionDataFromFile(AppendDialog.this.transactionFileName);
            }

            public void widgetDefaultSelected(SelectionEvent e) {
            }
        });
        new Label((Composite)group, 0);
        Group group_1 = new Group(container, 0);
        group_1.setLayout((Layout)new GridLayout(5, false));
        GridData gd_group_1 = new GridData(4, 4, true, true, 1, 1);
        gd_group_1.widthHint = 402;
        gd_group_1.heightHint = 275;
        group_1.setLayoutData((Object)gd_group_1);
        group_1.setText("Variables in the...");
        group_1.setFont(SWTResourceManager.getFont("Tahoma", 9, 0));
        Label label_2 = new Label((Composite)group_1, 0);
        label_2.setLayoutData((Object)new GridData(4, 4, true, false, 1, 1));
        label_2.setText("Active Data:");
        label_2.setFont(SWTResourceManager.getFont("Tahoma", 9, 0));
        new Label((Composite)group_1, 0);
        new Label((Composite)group_1, 0);
        Label lblNewWorkingData = new Label((Composite)group_1, 0);
        lblNewWorkingData.setLayoutData((Object)new GridData(4, 4, true, false, 1, 1));
        lblNewWorkingData.setText("New Working Data:");
        lblNewWorkingData.setFont(SWTResourceManager.getFont("Tahoma", 9, 0));
        new Label((Composite)group_1, 0);
        new Label((Composite)group_1, 0);
        new Label((Composite)group_1, 0);
        Group group_2 = new Group((Composite)group_1, 0);
        group_2.setLayoutData((Object)new GridData(4, 4, false, false, 3, 4));
        group_2.setLayout((Layout)new GridLayout(2, false));
        new Label((Composite)group_2, 0);
        this.workingDataList = new List((Composite)group_2, 2816);
        GridData gd_workingDataList = new GridData(4, 4, true, true, 1, 4);
        gd_workingDataList.widthHint = 157;
        this.workingDataList.setLayoutData((Object)gd_workingDataList);
        this.workingDataList.setSize(174, 151);
        this.workingDataList.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (AppendDialog.this.workingDataList.getSelectionCount() > 0) {
                    AppendDialog.this.enableRemove(true);
                }
            }
        });
        new Label((Composite)group_2, 0);
        this.pairBtn = new Button((Composite)group_2, 0);
        GridData gd_pairBtn = new GridData(4, 128, true, true, 1, 1);
        gd_pairBtn.widthHint = 62;
        this.pairBtn.setLayoutData((Object)gd_pairBtn);
        this.pairBtn.setSize(67, 23);
        this.pairBtn.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                String[] selectedActiveVars = AppendDialog.this.activeDataList.getSelection();
                String[] selectedTransactionVars = AppendDialog.this.transactionDataList.getSelection();
                if (selectedActiveVars.length != 1 || selectedTransactionVars.length != 1) {
                    MessageDialog.openWarning((Shell)Display.getCurrent().getActiveShell(), (String)"Wrong Variable Pairing", (String)"Please choose one variable from the active data and one from the transaction data.");
                } else {
                    AppendDialog.this.workingDataList.add(String.valueOf(selectedActiveVars[0]) + " (active) & " + selectedTransactionVars[0] + " (transaction)");
                    AppendDialog.this.keyVariablesMasterList.add(selectedActiveVars[0]);
                    AppendDialog.this.keyVariablesTransactList.add(selectedTransactionVars[0]);
                    AppendDialog.this.activeDataList.remove(AppendDialog.this.activeDataList.getSelectionIndices());
                    AppendDialog.this.transactionDataList.remove(AppendDialog.this.transactionDataList.getSelectionIndices());
                    AppendDialog.this.transactionName = AppendDialog.this.transactionFileNameCombo.getText().replaceAll(".csv", "_");
                    AppendDialog.this.transactionName = AppendDialog.this.transactionName.replaceAll(".txt", "_");
                    AppendDialog.this.transactionFileNameCombo.setEnabled(false);
                }
            }
        });
        this.pairBtn.setText("Pair");
        this.addBtn = new Button((Composite)group_2, 0);
        GridData gd_addBtn = new GridData(4, 128, true, true, 1, 1);
        gd_addBtn.widthHint = 61;
        gd_addBtn.heightHint = 24;
        this.addBtn.setLayoutData((Object)gd_addBtn);
        this.addBtn.setSize(67, 23);
        this.addBtn.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (AppendDialog.this.addBtn.getText().equals("Add")) {
                    String[] selectedNumVars = AppendDialog.this.activeDataList.getSelection();
                    int i = 0;
                    while (i < selectedNumVars.length) {
                        AppendDialog.this.workingDataList.add(String.valueOf(selectedNumVars[i]) + "(Active)");
                        AppendDialog.this.otherVariablesMasterList.add(selectedNumVars[i]);
                        ++i;
                    }
                    AppendDialog.this.activeDataList.remove(AppendDialog.this.activeDataList.getSelectionIndices());
                    String[] selectedTransactionVars = AppendDialog.this.transactionDataList.getSelection();
                    int i2 = 0;
                    while (i2 < selectedTransactionVars.length) {
                        AppendDialog.this.workingDataList.add(String.valueOf(selectedTransactionVars[i2]) + "(Transaction)");
                        AppendDialog.this.otherVariablesTransactList.add(selectedTransactionVars[i2]);
                        ++i2;
                    }
                    AppendDialog.this.transactionDataList.remove(AppendDialog.this.transactionDataList.getSelectionIndices());
                } else {
                    int[] selectedVars = AppendDialog.this.workingDataList.getSelectionIndices();
                    if (selectedVars.length > 0) {
                        int i = 0;
                        while (i < selectedVars.length) {
                            String selectedString = AppendDialog.this.workingDataList.getItem(selectedVars[i]);
                            if (selectedString.contains("&")) {
                                AppendDialog.this.workingDataList.remove(selectedVars[i]);
                                AppendDialog.this.activeDataList.add(AppendDialog.this.keyVariablesMasterList.get(selectedVars[i]));
                                AppendDialog.this.keyVariablesMasterList.remove(selectedVars[i]);
                                AppendDialog.this.transactionDataList.add(AppendDialog.this.keyVariablesTransactList.get(selectedVars[i]));
                                AppendDialog.this.keyVariablesTransactList.remove(selectedVars[i]);
                            } else {
                                if (selectedString.endsWith("(Active)")) {
                                    selectedString = selectedString.replaceAll("(Active)", "");
                                    selectedString = selectedString.substring(0, selectedString.length() - 2);
                                    AppendDialog.this.activeDataList.add(selectedString);
                                    AppendDialog.this.otherVariablesMasterList.remove(selectedString);
                                } else {
                                    selectedString = selectedString.replaceAll("(Transaction)", "");
                                    selectedString = selectedString.substring(0, selectedString.length() - 2);
                                    AppendDialog.this.transactionDataList.add(selectedString);
                                    AppendDialog.this.otherVariablesTransactList.remove(selectedString);
                                }
                                System.out.println("selected String :" + selectedString);
                                AppendDialog.this.workingDataList.remove(selectedVars[i]);
                            }
                            ++i;
                        }
                    }
                }
            }
        });
        this.addBtn.setText("Add");
        this.activeDataList = new List((Composite)group_1, 2818);
        GridData gd_activeDataList = new GridData(4, 4, true, true, 1, 1);
        gd_activeDataList.heightHint = 95;
        this.activeDataList.setLayoutData((Object)gd_activeDataList);
        ArrayList<String> s = this.dataManipulationManager.getVarInfoFromFile(this.dataFileName);
        this.activeDataList.setItems(this.dataManipulationManager.getListItems(s));
        this.activeDataList.addListener(3, new Listener(){

            public void handleEvent(Event event) {
                AppendDialog.this.enableRemove(false);
            }
        });
        new Label((Composite)group_1, 0);
        Label label_3 = new Label((Composite)group_1, 0);
        label_3.setLayoutData((Object)new GridData(4, 4, true, false, 1, 1));
        label_3.setText("Transaction Data:");
        label_3.setFont(SWTResourceManager.getFont("Tahoma", 9, 0));
        new Label((Composite)group_1, 0);
        this.transactionDataList = new List((Composite)group_1, 2818);
        GridData gd_transactionDataList = new GridData(4, 4, true, true, 1, 1);
        gd_transactionDataList.heightHint = 95;
        this.transactionDataList.setLayoutData((Object)gd_transactionDataList);
        new Label((Composite)group_1, 0);
        this.transactionDataList.addListener(3, new Listener(){

            public void handleEvent(Event event) {
                if (AppendDialog.this.transactionDataList.getItemCount() < 1) {
                    if (!AppendDialog.this.transactionFileNameCombo.getText().isEmpty()) {
                        MessageDialog.openWarning((Shell)AppendDialog.this.getShell(), (String)"Empty Transaction File", (String)"Please choose a different transaction file.");
                    } else {
                        MessageDialog.openWarning((Shell)AppendDialog.this.getShell(), (String)"Transaction File", (String)"Please choose a transaction file first.");
                    }
                } else if (AppendDialog.this.transactionDataList.getItemCount() > 0 && AppendDialog.this.delimiterCombo.getText().isEmpty() && AppendDialog.this.delimiterCombo.getEnabled()) {
                    MessageDialog.openWarning((Shell)AppendDialog.this.getShell(), (String)"Transaction File", (String)"Please choose a the proper delimiter for your selected .txt transaction file.");
                } else {
                    AppendDialog.this.enableRemove(false);
                }
            }
        });
        new Label((Composite)group_1, 0);
        Label lblNewLabel = new Label((Composite)group_1, 0);
        lblNewLabel.setLayoutData((Object)new GridData(4, 4, true, false, 3, 1));
        Label lbltransactionData = new Label((Composite)group_1, 0);
        lbltransactionData.setLayoutData((Object)new GridData(4, 4, true, false, 1, 1));
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
            this.workingDataList.setSelection(-1);
        }
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

    protected void resetList() {
        this.transactionDataList.removeAll();
        this.workingDataList.removeAll();
        this.activeDataList.removeAll();
        this.keyVariablesMasterList.clear();
        this.keyVariablesTransactList.clear();
        this.otherVariablesMasterList.clear();
        this.otherVariablesTransactList.clear();
        ArrayList<String> s = this.dataManipulationManager.getVarInfoFromFile(this.fileModel.getProjectFile().getAbsolutePath());
        this.activeDataList.setItems(this.dataManipulationManager.getListItems(s));
    }

    protected String[] getListItems(ArrayList<String> varInfo) {
        ArrayList<String> variableNames = new ArrayList<String>();
        for (String s : varInfo) {
            String[] tmp = s.split(":");
            variableNames.add(tmp[0]);
        }
        return variableNames.toArray(new String[variableNames.size()]);
    }

    protected void okPressed() {
        if (this.keyVariablesTransactList.isEmpty()) {
            MessageDialog.openWarning((Shell)this.getShell(), (String)"Transaction Variables", (String)"Please choose a variable from the transaction data to:\n add to the working data variables\n or pair with an active data variable");
        } else {
            String[] keyVariablesMaster = this.keyVariablesMasterList.toArray(new String[this.keyVariablesMasterList.size()]);
            String[] keyVariablesTransact = this.keyVariablesTransactList.toArray(new String[this.keyVariablesTransactList.size()]);
            String[] otherVariablesMaster = this.otherVariablesMasterList.toArray(new String[this.otherVariablesMasterList.size()]);
            String[] otherVariablesTransact = this.otherVariablesTransactList.toArray(new String[this.otherVariablesTransactList.size()]);
            String tmptransactionFileName = "";
            String masterFile = this.fileModel.getProjectFile().getName().replaceAll(".csv", "");
            String transactionFile = this.transactionName.replaceAll(".csv", "");
            masterFile = masterFile.replaceAll("_", "");
            transactionFile = transactionFile.replaceAll("_", "");
            if (this.transactionFileName.endsWith(".txt")) {
                tmptransactionFileName = this.transactionFileName.replaceAll(".txt", "_tmpFile.csv");
                this.dataManipulationManager.convertTxtToCsv(this.transactionFileName, tmptransactionFileName, this.separator);
                this.transactionFileName = tmptransactionFileName.replaceAll("\\\\+", "/");
            } else {
                tmptransactionFileName = this.transactionFileName;
                this.transactionFileName = tmptransactionFileName.replaceAll("\\\\+", "/");
            }
            this.dataFileName = this.fileModel.getProjectFile().getAbsolutePath().toString().replaceAll("\\\\+", "/");
            String fileNameExtension = this.dataManipulationManager.getManipulatedFileNameExtension("_" + this.transactionName + "_append", this.fileModel.getProjectFile().getAbsolutePath());
            String newFileName = this.dataFileName.replaceAll(".csv", fileNameExtension);
            newFileName = newFileName.replaceAll(".txt", fileNameExtension);
            ProjectExplorerView.rJavaManager.getRJavaDataManipulationManager().appendData(masterFile, transactionFile, this.dataFileName, this.transactionFileName, newFileName, keyVariablesMaster, keyVariablesTransact, otherVariablesMaster, otherVariablesTransact);
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
                MessageDialog.openWarning((Shell)Display.getCurrent().getActiveShell(), (String)"Append was Unsuccessful", (String)"Failed to create appended file.\n\nAre you sure you specified the correct delimiter and/or variables?");
            }
        }
    }

    protected void buttonPressed(int buttonId) {
        if (buttonId == 19) {
            this.transactionFileNameCombo.setEnabled(true);
            this.transactionFileNameCombo.setItems(this.dataManipulationManager.getFilesFromDir(this.fileModel));
            this.delimiterCombo.setItems(new String[]{"comma", "space", "tab", "semicolon"});
            this.delimiterCombo.setEnabled(false);
            this.resetList();
        }
        super.buttonPressed(buttonId);
    }

    protected void createButtonsForButtonBar(Composite parent) {
        this.createButton(parent, 19, "Reset", true);
        this.createButton(parent, 0, IDialogConstants.OK_LABEL, true);
        this.createButton(parent, 1, IDialogConstants.CANCEL_LABEL, false);
    }

    protected Point getInitialSize() {
        return new Point(618, 468);
    }

    protected boolean isResizable() {
        return true;
    }
}

