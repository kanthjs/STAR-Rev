/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.jface.dialogs.Dialog
 *  org.eclipse.jface.dialogs.IDialogConstants
 *  org.eclipse.jface.dialogs.MessageDialog
 *  org.eclipse.swt.events.SelectionAdapter
 *  org.eclipse.swt.events.SelectionEvent
 *  org.eclipse.swt.events.SelectionListener
 *  org.eclipse.swt.graphics.Point
 *  org.eclipse.swt.layout.FillLayout
 *  org.eclipse.swt.layout.GridData
 *  org.eclipse.swt.layout.GridLayout
 *  org.eclipse.swt.widgets.Button
 *  org.eclipse.swt.widgets.Composite
 *  org.eclipse.swt.widgets.Control
 *  org.eclipse.swt.widgets.FileDialog
 *  org.eclipse.swt.widgets.Group
 *  org.eclipse.swt.widgets.Label
 *  org.eclipse.swt.widgets.Layout
 *  org.eclipse.swt.widgets.List
 *  org.eclipse.swt.widgets.Shell
 *  org.eclipse.swt.widgets.TabFolder
 *  org.eclipse.swt.widgets.TabItem
 *  org.eclipse.swt.widgets.Table
 *  org.eclipse.swt.widgets.TableColumn
 *  org.eclipse.swt.widgets.Text
 */
package org.irri.breedingtool.star.analysis.dialog;

import java.io.File;
import java.util.ArrayList;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;
import org.irri.breedingtool.application.handler.PartStackHandler;
import org.irri.breedingtool.datamanipulation.dialog.OperationProgressDialog;
import org.irri.breedingtool.graphs.managers.GraphTableManager;
import org.irri.breedingtool.manager.impl.DataManipulationManager;
import org.irri.breedingtool.projectexplorer.view.ProjectExplorerView;
import org.irri.breedingtool.utility.DialogFormUtility;
import org.irri.breedingtool.utility.GeneralUtility;
import org.irri.breedingtool.utility.StarAnalysisUtilities;

public class DiscriminantAnalysisDialog
extends Dialog {
    private Button btnOk;
    private Button btnAddVars;
    private List lstNumericVars;
    private List lstVars;
    private String filePath = PartStackHandler.getActiveElementID();
    private DialogFormUtility listManager = new DialogFormUtility();
    private DataManipulationManager dataManipulationManager = new DataManipulationManager();
    private ArrayList<String> tableData = new ArrayList();
    private TabItem tbtmNewItem_1;
    private TableColumn tblclmnNewColumn;
    private TableColumn tblclmnNewColumn_2;
    private Composite composite_2;
    private Group grpDisplay;
    private Button btnBox;
    private Button btnDescriptives;
    private Label lblFactors;
    private List lstFactors;
    private Button btnMove;
    private Button btnAddGrp;
    private Text txtGrpVar;
    private Label lblIdVar;
    private Button btnProbabilities;
    private Button btnShapiro;
    private Button btnCases;
    private Button btnBrowse;
    private Label lblFile;
    private Text txtFile;
    private File file;
    private GraphTableManager tableManager;
    private Table table;
    private String dataFileName2 = "NULL";
    private String temp;
    private int dVal;

    public DiscriminantAnalysisDialog(Shell parentShell) {
        super(parentShell);
        this.setShellStyle(2288);
    }

    protected void configureShell(Shell shell) {
        super.configureShell(shell);
        shell.setText("Discriminant Analysis: " + new File(this.filePath).getName());
    }

    protected Control createDialogArea(Composite parent) {
        Composite container = (Composite)super.createDialogArea(parent);
        FillLayout fl_container = new FillLayout(256);
        fl_container.marginHeight = 8;
        fl_container.marginWidth = 8;
        container.setLayout((Layout)fl_container);
        TabFolder tabFolder = new TabFolder(container, 0);
        TabItem tbtmTitleCase = new TabItem(tabFolder, 0);
        tbtmTitleCase.setText("Variable Description");
        Composite composite = new Composite((Composite)tabFolder, 0);
        tbtmTitleCase.setControl((Control)composite);
        composite.setLayout((Layout)new GridLayout(8, false));
        Label lblAvailableData = new Label(composite, 0);
        GridData gd_lblAvailableData = new GridData(4, 0x1000000, false, false, 2, 1);
        gd_lblAvailableData.widthHint = 102;
        lblAvailableData.setLayoutData((Object)gd_lblAvailableData);
        lblAvailableData.setText("Numeric Variables:");
        new Label(composite, 0);
        new Label(composite, 0);
        new Label(composite, 0);
        Label lblDependentVariables = new Label(composite, 0);
        lblDependentVariables.setText("Variables:");
        new Label(composite, 0);
        new Label(composite, 0);
        this.lstNumericVars = new List(composite, 2818);
        GridData gd_lstNumericVars = new GridData(4, 4, false, false, 3, 1);
        gd_lstNumericVars.heightHint = 140;
        gd_lstNumericVars.widthHint = 160;
        this.lstNumericVars.setLayoutData((Object)gd_lstNumericVars);
        this.btnAddVars = new Button(composite, 0);
        GridData gd_btnAddVars = new GridData(4, 0x1000000, false, false, 2, 1);
        gd_btnAddVars.widthHint = 90;
        this.btnAddVars.setLayoutData((Object)gd_btnAddVars);
        this.btnAddVars.setText("Add");
        this.lstVars = new List(composite, 2818);
        GridData gd_lstVars = new GridData(4, 4, false, false, 3, 1);
        gd_lstVars.heightHint = 140;
        gd_lstVars.widthHint = 160;
        this.lstVars.setLayoutData((Object)gd_lstVars);
        this.lblFactors = new Label(composite, 0);
        this.lblFactors.setText("Factors:");
        new Label(composite, 0);
        this.btnMove = new Button(composite, 0);
        GridData gd_btnMove = new GridData(4, 0x1000000, true, false, 1, 1);
        gd_btnMove.widthHint = 90;
        this.btnMove.setLayoutData((Object)gd_btnMove);
        this.btnMove.setText("Set to Factor");
        new Label(composite, 0);
        new Label(composite, 0);
        this.lblIdVar = new Label(composite, 0);
        this.lblIdVar.setLayoutData((Object)new GridData(16384, 0x1000000, false, false, 2, 1));
        this.lblIdVar.setText("Grouping variable:");
        new Label(composite, 0);
        this.lstFactors = new List(composite, 2818);
        GridData gd_lstFactors = new GridData(4, 4, false, false, 3, 1);
        gd_lstFactors.heightHint = 140;
        gd_lstFactors.widthHint = 160;
        this.lstFactors.setLayoutData((Object)gd_lstFactors);
        this.btnAddGrp = new Button(composite, 0);
        this.btnAddGrp.addSelectionListener((SelectionListener)new SelectionAdapter(){
            private double dVal;

            public void widgetSelected(SelectionEvent e) {
                if (DiscriminantAnalysisDialog.this.lstFactors.getSelectionCount() > 0) {
                    String[] group;
                    String[] selectedVar = DiscriminantAnalysisDialog.this.lstFactors.getSelection();
                    DiscriminantAnalysisDialog.this.txtGrpVar.setText(selectedVar[0]);
                    String[] stringArray = group = DataManipulationManager.getEnvtLevels(DiscriminantAnalysisDialog.this.txtGrpVar.getText(), new File(DiscriminantAnalysisDialog.this.filePath));
                    int n = group.length;
                    int n2 = 0;
                    while (n2 < n) {
                        String var = stringArray[n2];
                        String prob = "1/" + group.length;
                        DiscriminantAnalysisDialog.this.addGroup(var, prob);
                        ++n2;
                    }
                } else {
                    String[] group;
                    String[] stringArray = group = DataManipulationManager.getEnvtLevels(DiscriminantAnalysisDialog.this.txtGrpVar.getText(), new File(DiscriminantAnalysisDialog.this.filePath));
                    int n = group.length;
                    int n3 = 0;
                    while (n3 < n) {
                        String var = stringArray[n3];
                        String prob = "1/" + group.length;
                        DiscriminantAnalysisDialog.this.removeGroup(var, prob);
                        ++n3;
                    }
                }
            }
        });
        GridData gd_btnAddGrp = new GridData(4, 128, false, false, 2, 1);
        gd_btnAddGrp.widthHint = 90;
        this.btnAddGrp.setLayoutData((Object)gd_btnAddGrp);
        this.btnAddGrp.setText("Add");
        this.txtGrpVar = new Text(composite, 2048);
        this.txtGrpVar.setBackground(SWTResourceManager.getColor(1));
        GridData gd_txtGrpVar = new GridData(4, 128, true, false, 3, 1);
        gd_txtGrpVar.widthHint = 160;
        this.txtGrpVar.setLayoutData((Object)gd_txtGrpVar);
        this.tbtmNewItem_1 = new TabItem(tabFolder, 0);
        this.tbtmNewItem_1.setText("Options");
        this.composite_2 = new Composite((Composite)tabFolder, 0);
        this.tbtmNewItem_1.setControl((Control)this.composite_2);
        this.composite_2.setLayout((Layout)new GridLayout(3, false));
        this.grpDisplay = new Group(this.composite_2, 0);
        GridData gd_grpDisplay = new GridData(16384, 128, false, false, 3, 2);
        gd_grpDisplay.heightHint = 69;
        gd_grpDisplay.widthHint = 313;
        this.grpDisplay.setLayoutData((Object)gd_grpDisplay);
        this.grpDisplay.setText("Display");
        this.grpDisplay.setLayout((Layout)new GridLayout(1, false));
        this.btnDescriptives = new Button((Composite)this.grpDisplay, 32);
        this.btnDescriptives.setText("Descriptive Statistics");
        this.btnShapiro = new Button((Composite)this.grpDisplay, 32);
        this.btnShapiro.setText("Shapiro-Wilk Multivariate Normality Test");
        this.btnBox = new Button((Composite)this.grpDisplay, 32);
        this.btnBox.setLayoutData((Object)new GridData(16384, 128, false, false, 1, 1));
        this.btnBox.setText("Box's M Test for Homogeneity of Covariance Matrices");
        new Label(this.composite_2, 0);
        new Label(this.composite_2, 0);
        new Label(this.composite_2, 0);
        this.btnProbabilities = new Button(this.composite_2, 32);
        this.btnProbabilities.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (DiscriminantAnalysisDialog.this.btnProbabilities.getSelection()) {
                    DiscriminantAnalysisDialog.this.table.setEnabled(true);
                    DiscriminantAnalysisDialog.this.tableManager.setAllEnabled(true);
                } else {
                    DiscriminantAnalysisDialog.this.table.setEnabled(false);
                }
                DiscriminantAnalysisDialog.this.tableManager.setAllEnabled(false);
                DiscriminantAnalysisDialog.this.tableManager.setAllEnabled(DiscriminantAnalysisDialog.this.btnProbabilities.getSelection());
            }
        });
        this.btnProbabilities.setLayoutData((Object)new GridData(16384, 0x1000000, false, false, 2, 1));
        this.btnProbabilities.setText("Specify prior probabilities");
        new Label(this.composite_2, 0);
        new Label(this.composite_2, 0);
        new Label(this.composite_2, 0);
        new Label(this.composite_2, 0);
        new Label(this.composite_2, 0);
        this.table = new Table(this.composite_2, 67584);
        this.table.setEnabled(false);
        this.table.setBackground(SWTResourceManager.getColor(1));
        GridData gd_table = new GridData(16384, 128, true, false, 2, 1);
        gd_table.heightHint = 156;
        gd_table.widthHint = 135;
        this.table.setLayoutData((Object)gd_table);
        this.table.setHeaderVisible(true);
        this.table.setLinesVisible(true);
        this.tblclmnNewColumn = new TableColumn(this.table, 0);
        this.tblclmnNewColumn.setWidth(71);
        this.tblclmnNewColumn.setText("Group");
        this.tblclmnNewColumn_2 = new TableColumn(this.table, 0);
        this.tblclmnNewColumn_2.setWidth(77);
        this.tblclmnNewColumn_2.setText("Probability");
        this.btnCases = new Button(this.composite_2, 32);
        this.btnCases.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (DiscriminantAnalysisDialog.this.btnCases.getSelection()) {
                    DiscriminantAnalysisDialog.this.lblFile.setEnabled(true);
                    DiscriminantAnalysisDialog.this.txtFile.setEnabled(true);
                    DiscriminantAnalysisDialog.this.btnBrowse.setEnabled(true);
                } else {
                    DiscriminantAnalysisDialog.this.lblFile.setEnabled(false);
                    DiscriminantAnalysisDialog.this.txtFile.setEnabled(false);
                    DiscriminantAnalysisDialog.this.btnBrowse.setEnabled(false);
                }
            }
        });
        this.btnCases.setLayoutData((Object)new GridData(16384, 128, false, false, 2, 1));
        this.btnCases.setText("Classify new cases");
        new Label(this.composite_2, 0);
        this.lblFile = new Label(this.composite_2, 0);
        this.lblFile.setEnabled(false);
        this.lblFile.setText("File:");
        this.txtFile = new Text(this.composite_2, 2048);
        this.txtFile.setBackground(SWTResourceManager.getColor(1));
        this.txtFile.setEnabled(false);
        GridData gd_txtFile = new GridData(4, 0x1000000, false, false, 1, 1);
        gd_txtFile.widthHint = 245;
        this.txtFile.setLayoutData((Object)gd_txtFile);
        this.btnBrowse = new Button(this.composite_2, 0);
        this.btnBrowse.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                FileDialog fd = new FileDialog(DiscriminantAnalysisDialog.this.getShell(), 4096);
                fd.setText("Open");
                String[] filterExt = new String[]{"*.csv"};
                fd.setFilterExtensions(filterExt);
                String filePath = fd.open();
                if (filePath != null) {
                    DiscriminantAnalysisDialog.this.dataFileName2 = filePath;
                    DiscriminantAnalysisDialog.this.txtFile.setText(DiscriminantAnalysisDialog.this.dataFileName2);
                }
            }
        });
        this.btnBrowse.setEnabled(false);
        this.btnBrowse.setLayoutData((Object)new GridData(16384, 4, false, false, 1, 1));
        this.btnBrowse.setText("Browse...");
        this.initializeContent();
        return container;
    }

    void initializeContent() {
        this.listManager.initializeNumericList(this.lstNumericVars, this.filePath);
        this.listManager.initializeFactorList(this.lstFactors, this.filePath);
        this.listManager.initializeSingleButtonList(this.lstNumericVars, this.lstVars, this.btnMove, this.btnAddVars);
        this.listManager.initializeSingleSelectionList(this.lstFactors, this.txtGrpVar, this.btnMove, this.btnAddGrp);
        this.listManager.initializeVariableMoveList(this.lstNumericVars, this.lstFactors, this.btnMove, this.filePath);
        ArrayList<Integer> tableHeaderIdentity = new ArrayList<Integer>();
        tableHeaderIdentity.add(0);
        tableHeaderIdentity.add(1);
        this.tableManager = new GraphTableManager(this.table, tableHeaderIdentity);
        this.tableManager.setAllEnabled(false);
    }

    void addGroup(String group, String prob) {
        this.tableManager.addItem(new Object[]{group, prob});
    }

    void removeGroup(String group, String prob) {
        this.tableManager.removeAll();
    }

    protected void buttonPressed(int buttonID) {
        if (buttonID == 0) {
            this.okPressed();
        } else if (buttonID == 8) {
            this.lstNumericVars.removeAll();
            this.lstVars.removeAll();
            this.lstFactors.removeAll();
            this.txtGrpVar.setText("");
            this.btnAddVars.setEnabled(false);
            this.btnAddVars.setText("Add");
            this.btnAddGrp.setEnabled(false);
            this.btnAddGrp.setText("Add");
            this.btnMove.setEnabled(false);
            this.btnDescriptives.setSelection(false);
            this.btnBox.setSelection(false);
            this.btnProbabilities.setSelection(false);
            this.btnCases.setSelection(false);
            this.btnBrowse.setEnabled(false);
            this.btnShapiro.setSelection(false);
            this.table.setEnabled(false);
            this.tableManager.removeAll();
            this.txtFile.setText("");
            this.listManager.initializeNumericList(this.lstNumericVars, this.filePath);
            this.listManager.initializeFactorList(this.lstFactors, this.filePath);
        } else {
            this.close();
        }
    }

    protected void okPressed() {
        if (this.lstVars.getItemCount() < 2) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"Please add at least two variables.");
            return;
        }
        if (this.txtGrpVar.getText().equals("")) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"Grouping variable list box should not be empty.");
            return;
        }
        String outputFolder = StarAnalysisUtilities.createOutputFolder(this.filePath, "Discriminant");
        String dataFileName = this.filePath.replace(File.separator, "/");
        String outputPath = outputFolder;
        String[] vars = this.lstVars.getItems();
        String grpVar = this.txtGrpVar.getText();
        boolean descriptive = this.btnDescriptives.getSelection();
        boolean doNormalTest = this.btnShapiro.getSelection();
        boolean doBoxM = this.btnBox.getSelection();
        String priorProb = "c(";
        String[] arrC = new String[this.table.getItemCount()];
        int x = 0;
        for (String[] row : this.tableManager.getDataToString()) {
            arrC[x] = row[1];
            ++x;
        }
        String temp = GeneralUtility.combineArrayOfString(arrC, ",");
        priorProb = String.valueOf(priorProb) + temp + ")";
        if (!this.btnProbabilities.getSelection()) {
            priorProb = "NULL";
        }
        boolean classifyNew = this.btnCases.getSelection();
        if (this.btnCases.getSelection()) {
            this.dataFileName2 = this.dataFileName2.replace(File.separator, "/");
        }
        OperationProgressDialog rInfo = new OperationProgressDialog(this.getShell(), "Performing Analysis");
        rInfo.open();
        this.btnOk.setEnabled(false);
        ProjectExplorerView.rJavaManager.getSTARAnalysisManager().doDiscriminant(dataFileName, outputPath.replace(File.separator, "/"), vars, grpVar, descriptive, doNormalTest, doBoxM, priorProb, classifyNew, this.dataFileName2);
        StarAnalysisUtilities.testVarArgs(dataFileName, outputPath.replace(File.separator, "/"), vars, grpVar, descriptive, doNormalTest, doBoxM, priorProb, classifyNew, this.dataFileName2);
        StarAnalysisUtilities.openAndRefreshFolder(outputFolder);
        this.getShell().setMinimized(true);
        this.btnOk.setEnabled(true);
        rInfo.close();
    }

    protected void createButtonsForButtonBar(Composite parent) {
        this.createButton(parent, 8, "Reset", false);
        this.btnOk = this.createButton(parent, 0, IDialogConstants.OK_LABEL, true);
        this.createButton(parent, 1, IDialogConstants.CANCEL_LABEL, false);
    }

    protected Point getInitialSize() {
        return new Point(545, 509);
    }
}

