/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.jface.dialogs.Dialog
 *  org.eclipse.jface.dialogs.IDialogConstants
 *  org.eclipse.jface.dialogs.MessageDialog
 *  org.eclipse.jface.viewers.ListViewer
 *  org.eclipse.swt.custom.CCombo
 *  org.eclipse.swt.events.ModifyEvent
 *  org.eclipse.swt.events.ModifyListener
 *  org.eclipse.swt.events.SelectionAdapter
 *  org.eclipse.swt.events.SelectionEvent
 *  org.eclipse.swt.events.SelectionListener
 *  org.eclipse.swt.graphics.Point
 *  org.eclipse.swt.layout.FillLayout
 *  org.eclipse.swt.layout.GridData
 *  org.eclipse.swt.layout.GridLayout
 *  org.eclipse.swt.widgets.Button
 *  org.eclipse.swt.widgets.Combo
 *  org.eclipse.swt.widgets.Composite
 *  org.eclipse.swt.widgets.Control
 *  org.eclipse.swt.widgets.Group
 *  org.eclipse.swt.widgets.Label
 *  org.eclipse.swt.widgets.Layout
 *  org.eclipse.swt.widgets.List
 *  org.eclipse.swt.widgets.Shell
 *  org.eclipse.swt.widgets.Spinner
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
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;
import org.irri.breedingtool.application.handler.PartStackHandler;
import org.irri.breedingtool.datamanipulation.dialog.OperationProgressDialog;
import org.irri.breedingtool.graphs.managers.GraphTableManager;
import org.irri.breedingtool.graphs.managers.RowEntityModel;
import org.irri.breedingtool.manager.impl.DataManipulationManager;
import org.irri.breedingtool.projectexplorer.view.ProjectExplorerView;
import org.irri.breedingtool.utility.DialogFormUtility;
import org.irri.breedingtool.utility.GraphsUtilities;
import org.irri.breedingtool.utility.StarAnalysisUtilities;

public class PrincipalComponentAnalysisDialog
extends Dialog {
    private String filePath = PartStackHandler.getActiveElementID();
    private File file;
    private DialogFormUtility listManager = new DialogFormUtility();
    private DataManipulationManager dataManipulationManager = new DataManipulationManager();
    private Button btnOkay;
    private Button btnScreePlot;
    private Button btnBiplot;
    private Button btnPcaPlot;
    private Button btnCorr;
    private Button btnCov;
    private Combo cmboTransformation;
    private Label lblTransformation;
    private Group grpAnalyze;
    private Group grpGraph;
    private TabItem tbtmVariableDescription_1;
    private Composite composite_2;
    private Label lblNumericVariables;
    private Label lblSelectedVariables;
    private List lstSelectedVariables;
    private ListViewer listViewer_2;
    private List lstNumericVariables;
    private ListViewer listViewer_3;
    private Button btnAddNumeric;
    private Button btnSetToFactor;
    private Label lblFactors;
    private List lstFactors;
    private ListViewer listViewer_4;
    private Button btnAddFactor;
    private Label lblIdvar;
    private Text txtIdvar;
    private Label lblNewLabel;
    private Group group;
    private Button btnSavePcScores;
    private String[] Transformations = new String[]{"zerocenter", "unitvar", "none"};
    private Button btnUseIdVariable;
    private Button btnDescriptiveStatistics;
    private Table table;
    private TableColumn tableColumn;
    private TableColumn tableColumn_1;
    private TableColumn tableColumn_2;
    private TableColumn tableColumn_3;
    private Button btnShowLeg;
    private Label lblPostion;
    private Label lblTitle;
    private Text txtTitle;
    private CCombo cmbPosition;
    private Label lblNoOfColumns;
    private Spinner spinColumns;
    private int CURRENT_COLOR = 0;
    private GraphTableManager tableManager;
    private String legPos = "bottom-right";
    private String legTitle = null;
    private int legNcol = 1;
    private int axesNum = 3;
    private String[] pCol = new String[]{"rgb(255,0,0,max = 255)", "rgb(0,255,0,max = 255)", "rgb(0,0,255,max = 255)", "rgb(0,255,255,max = 255)", "rgb(127,127,127,max = 255)"};
    private int[] pChars = new int[]{1, 2, 3, 4, 5};
    private Double[] pSizes = new Double[]{1.0, 1.0, 1.0, 1.0, 1.0};
    private Label lblNoOfAxes;
    private Spinner spinAxes;

    public PrincipalComponentAnalysisDialog(Shell parentShell) {
        super(parentShell);
        this.setShellStyle(2288);
    }

    protected void configureShell(Shell shell) {
        super.configureShell(shell);
        shell.setText("Principal Component Analysis : " + new File(this.filePath).getName());
    }

    protected Control createDialogArea(Composite parent) {
        Composite container = (Composite)super.createDialogArea(parent);
        FillLayout fl_container = new FillLayout(256);
        fl_container.marginHeight = 8;
        fl_container.marginWidth = 8;
        container.setLayout((Layout)fl_container);
        TabFolder tabFolder = new TabFolder(container, 0);
        tabFolder.setBackground(SWTResourceManager.getColor(22));
        this.tbtmVariableDescription_1 = new TabItem(tabFolder, 0);
        this.tbtmVariableDescription_1.setText("Variable Description");
        this.composite_2 = new Composite((Composite)tabFolder, 0);
        this.tbtmVariableDescription_1.setControl((Control)this.composite_2);
        GridLayout gl_composite_2 = new GridLayout(4, false);
        this.composite_2.setLayout((Layout)gl_composite_2);
        this.lblNumericVariables = new Label(this.composite_2, 0);
        GridData gd_lblNumericVariables = new GridData(16384, 0x1000000, true, false, 1, 1);
        gd_lblNumericVariables.horizontalIndent = 5;
        this.lblNumericVariables.setLayoutData((Object)gd_lblNumericVariables);
        this.lblNumericVariables.setText("Numeric Variable(s):");
        this.lblNumericVariables.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        new Label(this.composite_2, 0);
        this.lblSelectedVariables = new Label(this.composite_2, 0);
        this.lblSelectedVariables.setLayoutData((Object)new GridData(16384, 0x1000000, true, false, 1, 1));
        this.lblSelectedVariables.setText("Selected Variables:");
        this.lblSelectedVariables.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        new Label(this.composite_2, 0);
        this.listViewer_3 = new ListViewer(this.composite_2, 2818);
        this.lstNumericVariables = this.listViewer_3.getList();
        GridData gd_lstNumericVariables = new GridData(4, 4, true, true, 1, 1);
        gd_lstNumericVariables.horizontalIndent = 5;
        gd_lstNumericVariables.heightHint = 130;
        gd_lstNumericVariables.widthHint = 100;
        this.lstNumericVariables.setLayoutData((Object)gd_lstNumericVariables);
        this.lstNumericVariables.setItems(new String[0]);
        this.btnAddNumeric = new Button(this.composite_2, 0);
        GridData gd_btnAddNumeric = new GridData(4, 0x1000000, false, false, 1, 1);
        gd_btnAddNumeric.widthHint = 90;
        this.btnAddNumeric.setLayoutData((Object)gd_btnAddNumeric);
        this.btnAddNumeric.setText("Add");
        this.listViewer_2 = new ListViewer(this.composite_2, 2818);
        this.lstSelectedVariables = this.listViewer_2.getList();
        GridData gd_lstSelectedVariables = new GridData(4, 4, true, true, 1, 1);
        gd_lstSelectedVariables.heightHint = 130;
        gd_lstSelectedVariables.widthHint = 100;
        this.lstSelectedVariables.setLayoutData((Object)gd_lstSelectedVariables);
        this.lstSelectedVariables.setItems(new String[0]);
        this.lblNewLabel = new Label(this.composite_2, 0);
        GridData gd_lblNewLabel = new GridData(16384, 0x1000000, false, false, 1, 1);
        gd_lblNewLabel.widthHint = 2;
        this.lblNewLabel.setLayoutData((Object)gd_lblNewLabel);
        this.btnSetToFactor = new Button(this.composite_2, 0);
        GridData gd_btnSetToFactor = new GridData(131072, 0x1000000, true, false, 1, 1);
        gd_btnSetToFactor.widthHint = 110;
        this.btnSetToFactor.setLayoutData((Object)gd_btnSetToFactor);
        this.btnSetToFactor.setText("Set to Factor");
        new Label(this.composite_2, 0);
        new Label(this.composite_2, 0);
        new Label(this.composite_2, 0);
        this.lblFactors = new Label(this.composite_2, 0);
        GridData gd_lblFactors = new GridData(16384, 0x1000000, true, false, 1, 1);
        gd_lblFactors.horizontalIndent = 5;
        this.lblFactors.setLayoutData((Object)gd_lblFactors);
        this.lblFactors.setText("Factor(s):");
        this.lblFactors.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        new Label(this.composite_2, 0);
        this.lblIdvar = new Label(this.composite_2, 0);
        this.lblIdvar.setLayoutData((Object)new GridData(16384, 0x1000000, true, false, 1, 1));
        this.lblIdvar.setText("ID Variable:");
        this.lblIdvar.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        new Label(this.composite_2, 0);
        this.listViewer_4 = new ListViewer(this.composite_2, 2818);
        this.lstFactors = this.listViewer_4.getList();
        GridData gd_lstFactors = new GridData(4, 4, true, true, 1, 1);
        gd_lstFactors.widthHint = 100;
        gd_lstFactors.heightHint = 90;
        gd_lstFactors.verticalIndent = 2;
        gd_lstFactors.horizontalIndent = 5;
        this.lstFactors.setLayoutData((Object)gd_lstFactors);
        this.lstFactors.setItems(new String[0]);
        this.btnAddFactor = new Button(this.composite_2, 0);
        this.btnAddFactor.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (PrincipalComponentAnalysisDialog.this.lstFactors.getSelectionCount() > 0) {
                    String[] catVar;
                    PrincipalComponentAnalysisDialog.this.btnShowLeg.setSelection(true);
                    PrincipalComponentAnalysisDialog.this.btnShowLeg.setEnabled(true);
                    PrincipalComponentAnalysisDialog.this.legendOptions(true);
                    String[] selectedVar = PrincipalComponentAnalysisDialog.this.lstFactors.getSelection();
                    PrincipalComponentAnalysisDialog.this.txtIdvar.setText(selectedVar[0]);
                    String[] stringArray = catVar = DataManipulationManager.getEnvtLevels(PrincipalComponentAnalysisDialog.this.txtIdvar.getText(), new File(PrincipalComponentAnalysisDialog.this.filePath));
                    int n = catVar.length;
                    int n2 = 0;
                    while (n2 < n) {
                        String var = stringArray[n2];
                        PrincipalComponentAnalysisDialog.this.addDataToTable(var);
                        ++n2;
                    }
                    PrincipalComponentAnalysisDialog.this.btnPcaPlot.setSelection(true);
                } else {
                    PrincipalComponentAnalysisDialog.this.btnShowLeg.setSelection(false);
                    PrincipalComponentAnalysisDialog.this.btnShowLeg.setEnabled(false);
                    PrincipalComponentAnalysisDialog.this.legendOptions(false);
                    PrincipalComponentAnalysisDialog.this.tableManager.removeAll();
                    PrincipalComponentAnalysisDialog.this.btnPcaPlot.setSelection(false);
                }
            }
        });
        GridData gd_btnAddFactor = new GridData(4, 128, false, false, 1, 1);
        gd_btnAddFactor.widthHint = 90;
        this.btnAddFactor.setLayoutData((Object)gd_btnAddFactor);
        this.btnAddFactor.setText("Add");
        this.txtIdvar = new Text(this.composite_2, 2048);
        this.txtIdvar.addModifyListener(new ModifyListener(){

            public void modifyText(ModifyEvent e) {
                if (!PrincipalComponentAnalysisDialog.this.txtIdvar.getText().isEmpty() && PrincipalComponentAnalysisDialog.this.btnPcaPlot.getSelection()) {
                    PrincipalComponentAnalysisDialog.this.btnUseIdVariable.setEnabled(true);
                } else {
                    PrincipalComponentAnalysisDialog.this.btnUseIdVariable.setEnabled(false);
                }
            }
        });
        this.txtIdvar.setBackground(SWTResourceManager.getColor(1));
        this.txtIdvar.setEditable(false);
        GridData gd_txtIdvar = new GridData(4, 128, true, true, 1, 1);
        gd_txtIdvar.widthHint = 100;
        this.txtIdvar.setLayoutData((Object)gd_txtIdvar);
        new Label(this.composite_2, 0);
        TabItem tbtmOptions_1 = new TabItem(tabFolder, 0);
        tbtmOptions_1.setText("Options");
        Composite composite_1 = new Composite((Composite)tabFolder, 0);
        tbtmOptions_1.setControl((Control)composite_1);
        composite_1.setLayout((Layout)new GridLayout(1, false));
        this.grpAnalyze = new Group(composite_1, 0);
        this.grpAnalyze.setLayoutData((Object)new GridData(4, 0x1000000, true, false, 1, 1));
        this.grpAnalyze.setLayout((Layout)new GridLayout(3, false));
        this.grpAnalyze.setText("Analyze");
        this.btnCorr = new Button((Composite)this.grpAnalyze, 16);
        this.btnCorr.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                PrincipalComponentAnalysisDialog.this.lblTransformation.setEnabled(false);
                PrincipalComponentAnalysisDialog.this.cmboTransformation.setEnabled(false);
            }
        });
        this.btnCorr.setSelection(true);
        this.btnCorr.setText("Correlation Matrix");
        new Label((Composite)this.grpAnalyze, 0);
        new Label((Composite)this.grpAnalyze, 0);
        this.btnCov = new Button((Composite)this.grpAnalyze, 16);
        this.btnCov.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                PrincipalComponentAnalysisDialog.this.lblTransformation.setEnabled(true);
                PrincipalComponentAnalysisDialog.this.cmboTransformation.setEnabled(true);
            }
        });
        this.btnCov.setText("Variance Covariance Matrix");
        new Label((Composite)this.grpAnalyze, 0);
        new Label((Composite)this.grpAnalyze, 0);
        this.lblTransformation = new Label((Composite)this.grpAnalyze, 0);
        this.lblTransformation.setLayoutData((Object)new GridData(131072, 0x1000000, false, false, 1, 1));
        this.lblTransformation.setEnabled(false);
        this.lblTransformation.setText("Transformation");
        this.cmboTransformation = new Combo((Composite)this.grpAnalyze, 8);
        this.cmboTransformation.setEnabled(false);
        this.cmboTransformation.setItems(new String[]{"Zero - Centered", "Unit Variance", "No transformation"});
        GridData gd_cmboTransformation = new GridData(16384, 0x1000000, true, false, 2, 1);
        gd_cmboTransformation.widthHint = 100;
        this.cmboTransformation.setLayoutData((Object)gd_cmboTransformation);
        this.cmboTransformation.select(0);
        this.group = new Group(composite_1, 0);
        this.group.setLayout((Layout)new GridLayout(2, false));
        this.group.setLayoutData((Object)new GridData(4, 4, false, false, 1, 1));
        this.btnDescriptiveStatistics = new Button((Composite)this.group, 32);
        this.btnDescriptiveStatistics.setLayoutData((Object)new GridData(16384, 0x1000000, false, false, 2, 1));
        this.btnDescriptiveStatistics.setText("Display Descriptive Statistics");
        this.btnSavePcScores = new Button((Composite)this.group, 32);
        this.btnSavePcScores.setLayoutData((Object)new GridData(16384, 0x1000000, false, false, 2, 1));
        this.btnSavePcScores.setText("Save PC Scores to a file");
        this.btnSavePcScores.setSelection(true);
        this.btnSavePcScores.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        this.lblNoOfAxes = new Label((Composite)this.group, 0);
        this.lblNoOfAxes.setText("Number of PC axes:");
        this.spinAxes = new Spinner((Composite)this.group, 2048);
        this.spinAxes.setMinimum(2);
        this.spinAxes.setSelection(3);
        this.grpGraph = new Group(composite_1, 0);
        this.grpGraph.setLayout((Layout)new GridLayout(4, false));
        GridData gd_grpGraph = new GridData(4, 4, false, true, 1, 1);
        gd_grpGraph.widthHint = 423;
        this.grpGraph.setLayoutData((Object)gd_grpGraph);
        this.grpGraph.setText("Graph");
        this.btnScreePlot = new Button((Composite)this.grpGraph, 32);
        this.btnScreePlot.setText("Scree Plot");
        this.btnScreePlot.setSelection(true);
        this.btnScreePlot.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        new Label((Composite)this.grpGraph, 0);
        new Label((Composite)this.grpGraph, 0);
        new Label((Composite)this.grpGraph, 0);
        this.btnBiplot = new Button((Composite)this.grpGraph, 32);
        this.btnBiplot.setText("Biplot");
        this.btnBiplot.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        new Label((Composite)this.grpGraph, 0);
        new Label((Composite)this.grpGraph, 0);
        new Label((Composite)this.grpGraph, 0);
        this.btnPcaPlot = new Button((Composite)this.grpGraph, 32);
        this.btnPcaPlot.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (PrincipalComponentAnalysisDialog.this.btnPcaPlot.getSelection() && !PrincipalComponentAnalysisDialog.this.txtIdvar.getText().isEmpty()) {
                    PrincipalComponentAnalysisDialog.this.btnUseIdVariable.setEnabled(true);
                } else {
                    PrincipalComponentAnalysisDialog.this.btnUseIdVariable.setEnabled(false);
                }
            }
        });
        this.btnPcaPlot.setText("PCA Plot");
        this.btnPcaPlot.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        new Label((Composite)this.grpGraph, 0);
        new Label((Composite)this.grpGraph, 0);
        new Label((Composite)this.grpGraph, 0);
        this.table = new Table((Composite)this.grpGraph, 67584);
        this.table.setLinesVisible(true);
        this.table.setHeaderVisible(true);
        GridData gd_table = new GridData(4, 4, true, true, 4, 1);
        gd_table.widthHint = 420;
        gd_table.heightHint = 150;
        this.table.setLayoutData((Object)gd_table);
        this.tableColumn = new TableColumn(this.table, 0);
        this.tableColumn.setWidth(109);
        this.tableColumn.setText("Levels");
        this.tableColumn_1 = new TableColumn(this.table, 0);
        this.tableColumn_1.setWidth(78);
        this.tableColumn_1.setText("Color");
        this.tableColumn_1.setResizable(false);
        this.tableColumn_2 = new TableColumn(this.table, 0);
        this.tableColumn_2.setWidth(44);
        this.tableColumn_2.setText("Size");
        this.tableColumn_3 = new TableColumn(this.table, 0);
        this.tableColumn_3.setWidth(75);
        this.tableColumn_3.setText("Symbol");
        this.tableColumn_3.setResizable(false);
        this.btnUseIdVariable = new Button((Composite)this.grpGraph, 32);
        this.btnUseIdVariable.setEnabled(false);
        this.btnUseIdVariable.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                PrincipalComponentAnalysisDialog.this.tableManager.setColumnsDisabled(!PrincipalComponentAnalysisDialog.this.btnUseIdVariable.getSelection(), 3);
                if (PrincipalComponentAnalysisDialog.this.btnUseIdVariable.getSelection()) {
                    PrincipalComponentAnalysisDialog.this.btnShowLeg.setEnabled(false);
                    PrincipalComponentAnalysisDialog.this.btnShowLeg.setSelection(false);
                    PrincipalComponentAnalysisDialog.this.legendOptions(false);
                } else {
                    PrincipalComponentAnalysisDialog.this.btnShowLeg.setEnabled(true);
                    PrincipalComponentAnalysisDialog.this.btnShowLeg.setSelection(true);
                    PrincipalComponentAnalysisDialog.this.legendOptions(true);
                }
            }
        });
        this.btnUseIdVariable.setLayoutData((Object)new GridData(131072, 0x1000000, false, false, 4, 1));
        this.btnUseIdVariable.setText("Use ID Variable ");
        this.btnShowLeg = new Button((Composite)this.grpGraph, 32);
        this.btnShowLeg.setEnabled(false);
        this.btnShowLeg.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (PrincipalComponentAnalysisDialog.this.btnShowLeg.getSelection()) {
                    PrincipalComponentAnalysisDialog.this.legendOptions(true);
                } else {
                    PrincipalComponentAnalysisDialog.this.legendOptions(false);
                }
            }
        });
        this.btnShowLeg.setText("Show legend");
        this.btnShowLeg.setSelection(true);
        new Label((Composite)this.grpGraph, 0);
        new Label((Composite)this.grpGraph, 0);
        new Label((Composite)this.grpGraph, 0);
        this.lblPostion = new Label((Composite)this.grpGraph, 0);
        this.lblPostion.setEnabled(false);
        this.lblPostion.setText("Position:");
        this.cmbPosition = new CCombo((Composite)this.grpGraph, 2048);
        this.cmbPosition.setEnabled(false);
        this.cmbPosition.setItems(new String[]{"bottom-right", "bottom", "bottom-left", "left", "top-left", "top", "top-right", "right", "center"});
        this.cmbPosition.setEditable(false);
        this.cmbPosition.select(0);
        this.cmbPosition.setBackground(SWTResourceManager.getColor(1));
        this.lblNoOfColumns = new Label((Composite)this.grpGraph, 0);
        this.lblNoOfColumns.setEnabled(false);
        this.lblNoOfColumns.setText("No. of Columns:");
        this.spinColumns = new Spinner((Composite)this.grpGraph, 2048);
        this.spinColumns.setEnabled(false);
        this.spinColumns.setMaximum(5);
        this.spinColumns.setMinimum(1);
        this.spinColumns.setSelection(1);
        this.lblTitle = new Label((Composite)this.grpGraph, 0);
        this.lblTitle.setEnabled(false);
        this.lblTitle.setLayoutData((Object)new GridData(16384, 0x1000000, false, true, 1, 1));
        this.lblTitle.setText("Title:");
        this.txtTitle = new Text((Composite)this.grpGraph, 2048);
        this.txtTitle.setEnabled(false);
        this.txtTitle.setText("Legend:");
        this.txtTitle.setBackground(SWTResourceManager.getColor(1));
        this.txtTitle.setLayoutData((Object)new GridData(4, 0x1000000, true, true, 3, 1));
        this.initializeContent();
        return container;
    }

    void initializeContent() {
        this.listManager.initializeSingleButtonList(this.lstNumericVariables, this.lstSelectedVariables, this.btnAddNumeric);
        this.listManager.initializeSingleSelectionList(this.lstFactors, this.txtIdvar, this.btnAddFactor);
        this.listManager.initializeVariableMoveList(this.lstNumericVariables, this.lstFactors, this.btnSetToFactor, this.filePath);
        this.listManager.initializeNumericList(this.lstNumericVariables, this.filePath);
        this.listManager.initializeFactorList(this.lstFactors, this.filePath);
        ArrayList<Integer> headers = new ArrayList<Integer>();
        headers.add(0);
        headers.add(2);
        headers.add(5);
        headers.add(7);
        this.tableManager = new GraphTableManager(this.table, headers);
        this.tableManager.ROW_HEIGHT = 28;
        this.tableManager.SYMBOL_HEIGHT = 25;
    }

    protected void addDataToTable(String selectedNumVars) {
        GraphsUtilities.getGrayShades(this.table.getItemCount() + 1);
        this.tableManager.addItem(new Object[]{selectedNumVars, GraphsUtilities.getRandomColor(), new RowEntityModel(5, (Object)10, 10, -1, 1), "0"});
        this.getShell().setSize(this.getShell().getSize().x - 1, this.getShell().getSize().y);
        this.getShell().setSize(this.getShell().getSize().x + 1, this.getShell().getSize().y);
    }

    protected void legendOptions(boolean state) {
        this.lblPostion.setEnabled(state);
        this.lblTitle.setEnabled(state);
        this.lblNoOfColumns.setEnabled(state);
        this.cmbPosition.setEnabled(state);
        this.txtTitle.setEnabled(state);
        this.spinColumns.setEnabled(state);
    }

    void resetDialog() {
        this.lstNumericVariables.removeAll();
        this.lstSelectedVariables.removeAll();
        this.lstFactors.removeAll();
        this.txtIdvar.setText("");
        this.btnAddNumeric.setText("Add");
        this.btnAddFactor.setText("Add");
        this.btnAddFactor.setEnabled(false);
        this.btnCorr.setSelection(true);
        this.btnCov.setSelection(false);
        this.btnScreePlot.setSelection(true);
        this.btnBiplot.setSelection(false);
        this.btnPcaPlot.setSelection(false);
        this.btnSavePcScores.setSelection(true);
        this.btnUseIdVariable.setEnabled(false);
        this.btnUseIdVariable.setSelection(false);
        this.btnShowLeg.setSelection(true);
        this.spinAxes.setSelection(3);
        this.btnShowLeg.setEnabled(false);
        this.btnShowLeg.setSelection(false);
        this.legendOptions(false);
        this.tableManager.removeAll();
        this.txtTitle.setText("Legend:");
        this.cmbPosition.setText("bottom-right");
        this.spinColumns.setSelection(1);
        this.btnDescriptiveStatistics.setSelection(false);
        this.lblTransformation.setEnabled(false);
        this.cmboTransformation.setEnabled(false);
        this.cmboTransformation.setText("Zero - Centered");
        this.listManager.initializeNumericList(this.lstNumericVariables, this.filePath);
        this.listManager.initializeFactorList(this.lstFactors, this.filePath);
    }

    protected void buttonPressed(int ID) {
        if (ID == 0) {
            this.okPressed();
        } else if (ID == 1) {
            this.cancelPressed();
        } else if (ID == 8) {
            this.resetDialog();
        }
    }

    protected void okPressed() {
        if (this.lstSelectedVariables.getItemCount() < 2) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"Please add at least two Numeric Variables.");
            return;
        }
        if (this.spinAxes.getSelection() > this.lstSelectedVariables.getItemCount()) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"The number of PC axes should be less than or equal to the selected variables");
            this.spinAxes.getSelection();
            return;
        }
        String outputFolder = StarAnalysisUtilities.createOutputFolder(this.filePath, "PrincipalComponentAnalysis");
        OperationProgressDialog rInfo = new OperationProgressDialog(this.getShell(), "Performing Analysis");
        rInfo.open();
        this.btnOkay.setEnabled(false);
        String dataFileName = this.filePath.replace(File.separator, "/");
        String outputPath = outputFolder;
        String[] var = this.lstSelectedVariables.getItems();
        String idVar = this.txtIdvar.getText().isEmpty() ? null : this.txtIdvar.getText();
        boolean descriptiveStat = this.btnDescriptiveStatistics.getSelection();
        boolean saveScore = this.btnSavePcScores.getSelection();
        boolean scree = this.btnScreePlot.getSelection();
        boolean biplot = this.btnBiplot.getSelection();
        boolean pcaplot = this.btnPcaPlot.getSelection();
        String matx = "corr";
        String transform = "zerocenter";
        if (this.btnCov.getSelection()) {
            matx = "cov";
            transform = this.Transformations[this.cmboTransformation.getSelectionIndex()];
        }
        if (this.table.getItemCount() > 0) {
            this.pCol = new String[this.table.getItemCount()];
            this.pSizes = new Double[this.table.getItemCount()];
            this.pChars = new int[this.table.getItemCount()];
        }
        ArrayList<String> arrpCol = new ArrayList<String>();
        ArrayList<Double> arrpSizes = new ArrayList<Double>();
        ArrayList<Integer> arrpChars = new ArrayList<Integer>();
        for (String[] data : this.tableManager.getDataToString()) {
            arrpCol.add(GraphsUtilities.convertToRrgbFormat(data[1]));
            arrpSizes.add(Double.parseDouble(data[2]));
            arrpChars.add(Integer.parseInt(data[3]));
            System.out.println(String.valueOf(data[1]) + " " + data[2] + " " + data[3]);
        }
        if (!arrpCol.isEmpty()) {
            this.pCol = arrpCol.toArray(new String[arrpCol.size()]);
        }
        if (!arrpSizes.isEmpty()) {
            this.pSizes = arrpSizes.toArray(new Double[arrpSizes.size()]);
        }
        if (!arrpChars.isEmpty()) {
            this.pChars = this.convertToPrimitiveInt(arrpChars.toArray(new Integer[arrpChars.size()]));
        }
        boolean useIdVar = this.btnUseIdVariable.getSelection();
        boolean showLeg = false;
        if (this.btnShowLeg.getSelection()) {
            showLeg = true;
            this.legPos = this.cmbPosition.getText().replaceAll("-", "");
            this.legTitle = !this.txtTitle.getText().isEmpty() ? this.txtTitle.getText() : "";
            this.legNcol = this.spinColumns.getSelection();
        }
        this.axesNum = this.spinAxes.getSelection();
        ProjectExplorerView.rJavaManager.getSTARAnalysisManager().doPCA(dataFileName, outputPath.replace(File.separator, "/"), var, idVar, descriptiveStat, matx, transform, saveScore, scree, biplot, pcaplot, useIdVar, this.pChars, this.convertToPrimitiveDouble(this.pSizes), this.pCol, showLeg, this.legTitle, this.legPos, this.legNcol, this.axesNum);
        this.getShell().setMinimized(true);
        StarAnalysisUtilities.openAndRefreshFolder(outputPath);
        this.btnOkay.setEnabled(true);
        rInfo.close();
    }

    int[] convertToPrimitiveInt(Integer[] val) {
        int[] returnVal = new int[val.length];
        int i = 0;
        while (i < val.length) {
            returnVal[i] = val[i];
            ++i;
        }
        return returnVal;
    }

    double[] convertToPrimitiveDouble(Double[] val) {
        double[] returnVal = new double[val.length];
        int i = 0;
        while (i < val.length) {
            returnVal[i] = val[i];
            ++i;
        }
        return returnVal;
    }

    protected void createButtonsForButtonBar(Composite parent) {
        this.createButton(parent, 8, "Reset", false);
        this.btnOkay = this.createButton(parent, 0, IDialogConstants.OK_LABEL, true);
        this.createButton(parent, 1, IDialogConstants.CANCEL_LABEL, false);
    }

    protected Point getInitialSize() {
        return new Point(610, 700);
    }
}

