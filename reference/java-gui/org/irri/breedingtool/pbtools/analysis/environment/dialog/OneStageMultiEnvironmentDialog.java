/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.jface.dialogs.Dialog
 *  org.eclipse.jface.dialogs.IDialogConstants
 *  org.eclipse.jface.dialogs.MessageDialog
 *  org.eclipse.swt.events.DisposeEvent
 *  org.eclipse.swt.events.DisposeListener
 *  org.eclipse.swt.events.MouseEvent
 *  org.eclipse.swt.events.MouseListener
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
 *  org.eclipse.swt.widgets.Group
 *  org.eclipse.swt.widgets.Label
 *  org.eclipse.swt.widgets.Layout
 *  org.eclipse.swt.widgets.List
 *  org.eclipse.swt.widgets.Shell
 *  org.eclipse.swt.widgets.TabFolder
 *  org.eclipse.swt.widgets.TabItem
 *  org.eclipse.swt.widgets.Text
 */
package org.irri.breedingtool.pbtools.analysis.environment.dialog;

import java.io.File;
import java.util.ArrayList;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
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
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;
import org.irri.breedingtool.application.model.ProjectExplorerTreeNodeModel;
import org.irri.breedingtool.datamanipulation.dialog.OperationProgressDialog;
import org.irri.breedingtool.manager.impl.DataManipulationManager;
import org.irri.breedingtool.projectexplorer.view.ProjectExplorerView;
import org.irri.breedingtool.utility.DialogFormUtility;
import org.irri.breedingtool.utility.DialogListValidator;
import org.irri.breedingtool.utility.EnvVarValidator;
import org.irri.breedingtool.utility.PBToolAnalysisUtilities;
import org.irri.breedingtool.utility.TextVarValidator;
import org.irri.breedingtool.utility.WindowDialogControlUtil;

public class OneStageMultiEnvironmentDialog
extends Dialog {
    private ProjectExplorerTreeNodeModel fileModel;
    private ArrayList<String> varInfo;
    private Combo designType;
    private List numVarList;
    private List factorVarList;
    private Button btnAddEnv;
    private Button btnAddBlock;
    private Button btnAddRep;
    private Button btnAddRow;
    private Button btnAddCol;
    private List responseVarList;
    private Button addBtn;
    private Button checkPerformPairwiseBtn;
    private Button compareWithControlsBtn;
    private Button performAllComparisonsBtn;
    private Text significanceLevel;
    private List genLevelsList;
    private Button addControlsBtn;
    private List controlsList;
    private Button checkDescriptiveStatistics;
    private Button checkVarianceComponents;
    private Button moveBtn;
    private String[] numericVariables;
    private String[] factorVariables;
    private File file;
    private DataManipulationManager dataManipulationManager = new DataManipulationManager();
    private DialogFormUtility dlgManager = new DialogFormUtility();
    private Button btnAddGen;
    private TabItem tbtmOptions;
    private Button checkFixedBtn;
    private Label lblLevelOfSignificance;
    private Label lblOptionsForGenotype;
    private Label lblSpecifyControls;
    private Label levelsOfGenotypeLbl;
    private Label controlsLbl;
    private Button checkRandomBtn;
    private Label lblBlock;
    private Label lblRep;
    private Group variableGroup;
    private Label lblCol;
    private Label lblRow;
    private int design = 0;
    private String[] respvars = new String[0];
    private String environment = "Site";
    private String[] environmentLevels;
    private String genotype = "Gen";
    private String block = "Blk";
    private String rep = "NULL";
    private String row = "NULL";
    private String column = "NULL";
    private boolean descriptiveStat = true;
    private boolean varianceComponents;
    private boolean stabilityFinlay;
    private boolean stabilityShukla;
    private boolean ammi;
    private boolean gge;
    private boolean boxplotRawData = true;
    private boolean histogramRawData = true;
    private boolean heatmapResiduals = true;
    private String heatmapRow = null;
    private String heatmapColumn = null;
    private boolean diagnosticPlot = true;
    private boolean genotypeFixed = true;
    private boolean performPairwise = true;
    private String pairwiseAlpha = "0.05";
    private String[] genotypeLevels = new String[0];
    private String[] controlLevels = new String[0];
    private boolean compareControl = true;
    private boolean performAllPairwise = false;
    private boolean genotypeRandom = true;
    private boolean excludeControls = true;
    private String[] controlLevels2 = new String[0];
    private boolean genoPhenoCorrelation = true;
    private Button checkBoxPlotBtn;
    private Button checkHistogramBtn;
    private Button checkDiagnosticPlotsBtn;
    private Button stabilityFinlaybtn;
    private Button stabilityShuklabtn;
    private Button btnAmmiAnalysis;
    private boolean satisfiedAllConditions = true;
    private String analysisType;
    private Label lblEnv;
    private Label lblGen;
    private Text txtEnvVar;
    private Text txtGenVar;
    private Text txtBlockVar;
    private Text txtRepVar;
    private Text txtRowVar;
    private Text txtColVar;
    private Label lblNewLabel_2;
    private Label lblNewLabel_4;
    private Label lblNewLabel_1;
    private Label lblNewLabel_5;
    private Button btnGgeAnalysis;
    private Label lblNewLabel_6;

    public OneStageMultiEnvironmentDialog(Shell parentShell, String analysisType, File file) {
        super(parentShell);
        this.setShellStyle(2256);
        this.setBlockOnOpen(false);
        this.analysisType = analysisType;
        this.file = file;
        this.setFactors();
    }

    protected Control createDialogArea(Composite parent) {
        this.getShell().setData("analysis", (Object)this.analysisType);
        this.getShell().setData("filePathID", (Object)this.file.getAbsolutePath());
        WindowDialogControlUtil.addWindowDialogToList(this.getShell());
        parent.addDisposeListener(new DisposeListener(){

            public void widgetDisposed(DisposeEvent e) {
                WindowDialogControlUtil.removeWindowDialogToList(OneStageMultiEnvironmentDialog.this.getShell());
            }
        });
        parent.getShell().setText(" One-Stage Multi-Environment Analysis: " + this.dataManipulationManager.getDisplayFileName(this.file.getAbsolutePath()));
        Composite container = (Composite)super.createDialogArea(parent);
        TabFolder tabFolder = new TabFolder(container, 0);
        tabFolder.setLayoutData((Object)new GridData(4, 4, true, true, 1, 1));
        TabItem tbtmModelSpecifications_1 = new TabItem(tabFolder, 0);
        tbtmModelSpecifications_1.setText("Model Specifications");
        Composite modelComposite = new Composite((Composite)tabFolder, 0);
        tbtmModelSpecifications_1.setControl((Control)modelComposite);
        modelComposite.setLayout((Layout)new GridLayout(5, false));
        modelComposite.setBackground(this.getShell().getBackground());
        Label lblNewLabel = new Label(modelComposite, 0);
        lblNewLabel.setLayoutData((Object)new GridData(16384, 0x1000000, false, false, 2, 1));
        lblNewLabel.setText("Type of Design:");
        this.designType = new Combo(modelComposite, 8);
        this.designType.setItems(new String[]{"Randomized Complete Block (RCB)", "Alpha-Lattice", "Row-Column", "Latinized Alpha-Lattice", "Latinized Row-Column"});
        this.designType.setLayoutData((Object)new GridData(4, 0x1000000, true, false, 3, 1));
        this.designType.select(0);
        this.designType.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                OneStageMultiEnvironmentDialog.this.enableFactorButtons(false);
                if (OneStageMultiEnvironmentDialog.this.designType.getSelectionIndex() == 0) {
                    OneStageMultiEnvironmentDialog.this.lblBlock.setEnabled(true);
                    OneStageMultiEnvironmentDialog.this.disable("replicate");
                    OneStageMultiEnvironmentDialog.this.disable("row");
                    OneStageMultiEnvironmentDialog.this.disable("column");
                } else if (OneStageMultiEnvironmentDialog.this.designType.getSelectionIndex() == 1) {
                    OneStageMultiEnvironmentDialog.this.lblRep.setEnabled(true);
                    OneStageMultiEnvironmentDialog.this.lblBlock.setEnabled(true);
                    OneStageMultiEnvironmentDialog.this.disable("row");
                    OneStageMultiEnvironmentDialog.this.disable("column");
                } else if (OneStageMultiEnvironmentDialog.this.designType.getSelectionIndex() == 2) {
                    OneStageMultiEnvironmentDialog.this.lblRow.setEnabled(true);
                    OneStageMultiEnvironmentDialog.this.lblCol.setEnabled(true);
                    OneStageMultiEnvironmentDialog.this.lblRep.setEnabled(true);
                    OneStageMultiEnvironmentDialog.this.disable("block");
                } else if (OneStageMultiEnvironmentDialog.this.designType.getSelectionIndex() == 3) {
                    OneStageMultiEnvironmentDialog.this.lblRep.setEnabled(true);
                    OneStageMultiEnvironmentDialog.this.lblBlock.setEnabled(true);
                    OneStageMultiEnvironmentDialog.this.disable("row");
                    OneStageMultiEnvironmentDialog.this.disable("column");
                } else if (OneStageMultiEnvironmentDialog.this.designType.getSelectionIndex() == 4) {
                    OneStageMultiEnvironmentDialog.this.lblRow.setEnabled(true);
                    OneStageMultiEnvironmentDialog.this.lblCol.setEnabled(true);
                    OneStageMultiEnvironmentDialog.this.lblRep.setEnabled(true);
                    OneStageMultiEnvironmentDialog.this.disable("block");
                }
            }
        });
        Label lblNumericVariables = new Label(modelComposite, 0);
        lblNumericVariables.setLayoutData((Object)new GridData(16384, 0x1000000, false, false, 2, 1));
        lblNumericVariables.setText("Numeric Variables:");
        new Label(modelComposite, 0);
        new Label(modelComposite, 0);
        Label lblResponseVariables = new Label(modelComposite, 0);
        lblResponseVariables.setText("Response Variable(s):");
        this.numVarList = new List(modelComposite, 2818);
        this.numVarList.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (OneStageMultiEnvironmentDialog.this.numVarList.getSelectionCount() > 0) {
                    OneStageMultiEnvironmentDialog.this.enableFactorButtons(false);
                    OneStageMultiEnvironmentDialog.this.enableNumericButtons(true);
                }
            }
        });
        GridData gd_numVarList = new GridData(4, 4, true, false, 2, 1);
        gd_numVarList.heightHint = 95;
        gd_numVarList.widthHint = 167;
        this.numVarList.setLayoutData((Object)gd_numVarList);
        this.numVarList.setItems(this.numericVariables);
        this.addBtn = new Button(modelComposite, 0);
        this.addBtn.setEnabled(false);
        GridData gd_addBtn = new GridData(0x1000000, 0x1000000, true, false, 2, 1);
        gd_addBtn.widthHint = 66;
        this.addBtn.setLayoutData((Object)gd_addBtn);
        this.addBtn.setText("Add");
        this.responseVarList = new List(modelComposite, 2818);
        GridData gd_responseVarList = new GridData(4, 4, true, false, 1, 1);
        gd_responseVarList.widthHint = 111;
        this.responseVarList.setLayoutData((Object)gd_responseVarList);
        Label lblFactors = new Label(modelComposite, 0);
        lblFactors.setText("Factors:");
        this.moveBtn = new Button(modelComposite, 0);
        GridData gd_moveBtn = new GridData(131072, 0x1000000, true, false, 1, 1);
        gd_moveBtn.heightHint = 24;
        gd_moveBtn.widthHint = 90;
        this.moveBtn.setLayoutData((Object)gd_moveBtn);
        this.moveBtn.setText("Set to Factor");
        new Label(modelComposite, 0);
        new Label(modelComposite, 0);
        new Label(modelComposite, 0);
        this.factorVarList = new List(modelComposite, 2816);
        this.factorVarList.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (OneStageMultiEnvironmentDialog.this.factorVarList.getSelectionCount() > 0) {
                    OneStageMultiEnvironmentDialog.this.enableFactorButtons(true);
                    OneStageMultiEnvironmentDialog.this.enableNumericButtons(false);
                }
            }
        });
        GridData gd_factorVarList = new GridData(4, 4, true, true, 2, 2);
        gd_factorVarList.widthHint = 65;
        this.factorVarList.setLayoutData((Object)gd_factorVarList);
        this.factorVarList.setItems(this.factorVariables);
        Group group = new Group(modelComposite, 0);
        group.setLayout((Layout)new GridLayout(3, false));
        GridData gd_group = new GridData(4, 4, true, false, 3, 1);
        gd_group.widthHint = 255;
        group.setLayoutData((Object)gd_group);
        this.btnAddEnv = new Button((Composite)group, 0);
        this.btnAddEnv.setText("Add");
        GridData gd_btnAddEnv = new GridData(16384, 0x1000000, false, false, 1, 1);
        gd_btnAddEnv.widthHint = 52;
        this.btnAddEnv.setLayoutData((Object)gd_btnAddEnv);
        this.lblEnv = new Label((Composite)group, 0);
        this.lblEnv.setLayoutData((Object)new GridData(131072, 0x1000000, false, false, 1, 1));
        this.lblEnv.setText("Environment:");
        this.txtEnvVar = new Text((Composite)group, 2048);
        this.txtEnvVar.setBackground(SWTResourceManager.getColor(1));
        this.txtEnvVar.setEditable(false);
        this.txtEnvVar.setLayoutData((Object)new GridData(4, 4, true, false, 1, 1));
        this.btnAddGen = new Button((Composite)group, 0);
        this.btnAddGen.setText("Add");
        GridData gd_btnAddGen = new GridData(16384, 0x1000000, false, false, 1, 1);
        gd_btnAddGen.widthHint = 52;
        this.btnAddGen.setLayoutData((Object)gd_btnAddGen);
        this.lblGen = new Label((Composite)group, 0);
        this.lblGen.setLayoutData((Object)new GridData(131072, 0x1000000, false, false, 1, 1));
        this.lblGen.setText("Genotype:");
        this.txtGenVar = new Text((Composite)group, 2048);
        this.txtGenVar.setEditable(false);
        this.txtGenVar.setBackground(SWTResourceManager.getColor(1));
        this.txtGenVar.setLayoutData((Object)new GridData(4, 4, true, false, 1, 1));
        this.txtGenVar.addMouseListener(new MouseListener(){

            public void mouseDoubleClick(MouseEvent e) {
                OneStageMultiEnvironmentDialog.this.genLevelsList.removeAll();
                OneStageMultiEnvironmentDialog.this.controlsList.removeAll();
                OneStageMultiEnvironmentDialog.this.resetGenotypeControls();
            }

            public void mouseDown(MouseEvent e) {
            }

            public void mouseUp(MouseEvent e) {
            }
        });
        new Label((Composite)group, 0);
        this.checkFixedBtn = new Button((Composite)group, 32);
        this.checkFixedBtn.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (OneStageMultiEnvironmentDialog.this.checkFixedBtn.getSelection()) {
                    OneStageMultiEnvironmentDialog.this.activateFixedOptions(true);
                    if (OneStageMultiEnvironmentDialog.this.checkPerformPairwiseBtn.getSelection()) {
                        OneStageMultiEnvironmentDialog.this.activatePerformPairwiseOptions(true);
                        if (OneStageMultiEnvironmentDialog.this.compareWithControlsBtn.getSelection()) {
                            OneStageMultiEnvironmentDialog.this.activateLevelOfConrolsOptions(true);
                        }
                    }
                } else {
                    OneStageMultiEnvironmentDialog.this.activateFixedOptions(false);
                    OneStageMultiEnvironmentDialog.this.activatePerformPairwiseOptions(false);
                    OneStageMultiEnvironmentDialog.this.activateLevelOfConrolsOptions(false);
                }
            }
        });
        this.checkFixedBtn.setSelection(true);
        this.checkFixedBtn.setText("Fixed");
        this.checkRandomBtn = new Button((Composite)group, 32);
        this.checkRandomBtn.setLayoutData((Object)new GridData(16384, 0x1000000, true, false, 1, 1));
        this.checkRandomBtn.setText("Random");
        this.variableGroup = new Group(modelComposite, 0);
        this.variableGroup.setLayout((Layout)new GridLayout(3, false));
        GridData gd_group_1 = new GridData(4, 4, true, true, 3, 1);
        gd_group_1.heightHint = 135;
        this.variableGroup.setLayoutData((Object)gd_group_1);
        this.btnAddBlock = new Button((Composite)this.variableGroup, 0);
        this.btnAddBlock.setEnabled(false);
        this.btnAddBlock.setText("Add");
        GridData gd_btnAddBlock = new GridData(16384, 0x1000000, false, false, 1, 1);
        gd_btnAddBlock.widthHint = 52;
        this.btnAddBlock.setLayoutData((Object)gd_btnAddBlock);
        this.lblBlock = new Label((Composite)this.variableGroup, 0);
        this.lblBlock.setLayoutData((Object)new GridData(131072, 0x1000000, false, false, 1, 1));
        this.lblBlock.setText("Block:");
        this.txtBlockVar = new Text((Composite)this.variableGroup, 2048);
        this.txtBlockVar.setEditable(false);
        this.txtBlockVar.setBackground(SWTResourceManager.getColor(1));
        this.txtBlockVar.setLayoutData((Object)new GridData(4, 4, true, false, 1, 1));
        this.btnAddRep = new Button((Composite)this.variableGroup, 0);
        this.btnAddRep.setEnabled(false);
        this.btnAddRep.setText("Add");
        GridData gd_btnAddRep = new GridData(16384, 0x1000000, false, false, 1, 1);
        gd_btnAddRep.widthHint = 52;
        this.btnAddRep.setLayoutData((Object)gd_btnAddRep);
        this.lblRep = new Label((Composite)this.variableGroup, 0);
        this.lblRep.setLayoutData((Object)new GridData(131072, 0x1000000, false, false, 1, 1));
        this.lblRep.setEnabled(false);
        this.lblRep.setText("Replicate:");
        this.txtRepVar = new Text((Composite)this.variableGroup, 2048);
        this.txtRepVar.setEditable(false);
        this.txtRepVar.setBackground(SWTResourceManager.getColor(1));
        this.txtRepVar.setLayoutData((Object)new GridData(4, 4, true, false, 1, 1));
        this.btnAddRow = new Button((Composite)this.variableGroup, 0);
        this.btnAddRow.setEnabled(false);
        this.btnAddRow.setText("Add");
        GridData gd_btnAddRow = new GridData(16384, 0x1000000, false, false, 1, 1);
        gd_btnAddRow.widthHint = 52;
        this.btnAddRow.setLayoutData((Object)gd_btnAddRow);
        this.lblRow = new Label((Composite)this.variableGroup, 0);
        this.lblRow.setLayoutData((Object)new GridData(131072, 0x1000000, false, false, 1, 1));
        this.lblRow.setEnabled(false);
        this.lblRow.setText("Row:");
        this.txtRowVar = new Text((Composite)this.variableGroup, 2048);
        this.txtRowVar.setEditable(false);
        this.txtRowVar.setBackground(SWTResourceManager.getColor(1));
        this.txtRowVar.setLayoutData((Object)new GridData(4, 4, true, false, 1, 1));
        this.btnAddCol = new Button((Composite)this.variableGroup, 0);
        this.btnAddCol.setEnabled(false);
        this.btnAddCol.setText("Add");
        GridData gd_btnAddCol = new GridData(16384, 0x1000000, false, false, 1, 1);
        gd_btnAddCol.widthHint = 52;
        this.btnAddCol.setLayoutData((Object)gd_btnAddCol);
        this.lblCol = new Label((Composite)this.variableGroup, 0);
        this.lblCol.setLayoutData((Object)new GridData(131072, 0x1000000, false, false, 1, 1));
        this.lblCol.setEnabled(false);
        this.lblCol.setText("Column:");
        this.txtColVar = new Text((Composite)this.variableGroup, 2048);
        this.txtColVar.setEditable(false);
        this.txtColVar.setBackground(SWTResourceManager.getColor(1));
        this.txtColVar.setLayoutData((Object)new GridData(4, 4, true, false, 1, 1));
        this.tbtmOptions = new TabItem(tabFolder, 0);
        this.tbtmOptions.setText("Options");
        Composite composite_1 = new Composite((Composite)tabFolder, 0);
        this.tbtmOptions.setControl((Control)composite_1);
        composite_1.setBackground(this.getShell().getBackground());
        composite_1.setLayout((Layout)new GridLayout(7, false));
        this.lblOptionsForGenotype = new Label(composite_1, 0);
        this.lblOptionsForGenotype.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        GridData gd_lblOptionsForGenotype = new GridData(16384, 0x1000000, false, false, 5, 1);
        gd_lblOptionsForGenotype.heightHint = 21;
        gd_lblOptionsForGenotype.widthHint = 228;
        this.lblOptionsForGenotype.setLayoutData((Object)gd_lblOptionsForGenotype);
        this.lblOptionsForGenotype.setText("Options for Genotype as Fixed:");
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        this.checkPerformPairwiseBtn = new Button(composite_1, 32);
        this.checkPerformPairwiseBtn.setLayoutData((Object)new GridData(16384, 0x1000000, false, false, 4, 1));
        this.checkPerformPairwiseBtn.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        this.checkPerformPairwiseBtn.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (OneStageMultiEnvironmentDialog.this.checkPerformPairwiseBtn.getSelection()) {
                    OneStageMultiEnvironmentDialog.this.activatePerformPairwiseOptions(true);
                    if (OneStageMultiEnvironmentDialog.this.compareWithControlsBtn.getSelection()) {
                        OneStageMultiEnvironmentDialog.this.activateLevelOfConrolsOptions(true);
                    }
                } else {
                    OneStageMultiEnvironmentDialog.this.activatePerformPairwiseOptions(false);
                    OneStageMultiEnvironmentDialog.this.activateLevelOfConrolsOptions(false);
                }
            }
        });
        this.checkPerformPairwiseBtn.setText("Pairwise mean comparisons");
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        this.compareWithControlsBtn = new Button(composite_1, 16);
        this.compareWithControlsBtn.setLayoutData((Object)new GridData(16384, 0x1000000, false, false, 2, 1));
        this.compareWithControlsBtn.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        this.compareWithControlsBtn.setEnabled(false);
        this.compareWithControlsBtn.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (OneStageMultiEnvironmentDialog.this.compareWithControlsBtn.getSelection()) {
                    OneStageMultiEnvironmentDialog.this.activateLevelOfConrolsOptions(true);
                } else {
                    OneStageMultiEnvironmentDialog.this.activateLevelOfConrolsOptions(false);
                }
            }
        });
        this.compareWithControlsBtn.setText("Compare with control(s)");
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        this.performAllComparisonsBtn = new Button(composite_1, 16);
        this.performAllComparisonsBtn.setLayoutData((Object)new GridData(16384, 0x1000000, false, false, 2, 1));
        this.performAllComparisonsBtn.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        this.performAllComparisonsBtn.setEnabled(false);
        this.performAllComparisonsBtn.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
            }
        });
        this.performAllComparisonsBtn.setText("Perform all comparisons");
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        this.lblLevelOfSignificance = new Label(composite_1, 0);
        this.lblLevelOfSignificance.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        this.lblLevelOfSignificance.setEnabled(false);
        this.lblLevelOfSignificance.setLayoutData((Object)new GridData(131072, 0x1000000, true, false, 1, 1));
        this.lblLevelOfSignificance.setText("Level of Significance:");
        this.significanceLevel = new Text(composite_1, 2048);
        this.significanceLevel.setEnabled(false);
        this.significanceLevel.setText("0.05");
        GridData gd_significanceLevel = new GridData(16384, 0x1000000, false, false, 1, 1);
        gd_significanceLevel.widthHint = 39;
        this.significanceLevel.setLayoutData((Object)gd_significanceLevel);
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        this.lblSpecifyControls = new Label(composite_1, 0);
        this.lblSpecifyControls.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        this.lblSpecifyControls.setLayoutData((Object)new GridData(16384, 0x1000000, true, false, 3, 1));
        this.lblSpecifyControls.setEnabled(false);
        this.lblSpecifyControls.setText("Specify Control(s)");
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        this.levelsOfGenotypeLbl = new Label(composite_1, 0);
        this.levelsOfGenotypeLbl.setLayoutData((Object)new GridData(16384, 0x1000000, true, false, 1, 1));
        this.levelsOfGenotypeLbl.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        this.levelsOfGenotypeLbl.setEnabled(false);
        this.levelsOfGenotypeLbl.setText("Levels of Genotype:");
        new Label(composite_1, 0);
        this.controlsLbl = new Label(composite_1, 0);
        this.controlsLbl.setLayoutData((Object)new GridData(16384, 0x1000000, true, false, 1, 1));
        this.controlsLbl.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        this.controlsLbl.setEnabled(false);
        this.controlsLbl.setText("Control(s):");
        this.lblNewLabel_1 = new Label(composite_1, 0);
        GridData gd_lblNewLabel_1 = new GridData(16384, 0x1000000, false, false, 1, 1);
        gd_lblNewLabel_1.widthHint = 5;
        this.lblNewLabel_1.setLayoutData((Object)gd_lblNewLabel_1);
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        this.genLevelsList = new List(composite_1, 2818);
        this.genLevelsList.setEnabled(false);
        GridData gd_genLevelsList = new GridData(4, 4, true, true, 1, 1);
        gd_genLevelsList.widthHint = 120;
        this.genLevelsList.setLayoutData((Object)gd_genLevelsList);
        this.genLevelsList.addMouseListener(new MouseListener(){

            public void mouseDoubleClick(MouseEvent e) {
                OneStageMultiEnvironmentDialog.this.dataManipulationManager.moveSelectedStringFromTo(OneStageMultiEnvironmentDialog.this.genLevelsList, OneStageMultiEnvironmentDialog.this.controlsList);
                OneStageMultiEnvironmentDialog.this.addControlsBtn.setEnabled(false);
            }

            public void mouseDown(MouseEvent e) {
                if (OneStageMultiEnvironmentDialog.this.genLevelsList.getSelectionIndex() > -1) {
                    OneStageMultiEnvironmentDialog.this.addControlsBtn.setEnabled(true);
                    OneStageMultiEnvironmentDialog.this.controlsList.setSelection(-1);
                    OneStageMultiEnvironmentDialog.this.addControlsBtn.setText("Add");
                    OneStageMultiEnvironmentDialog.this.addControlsBtn.setEnabled(true);
                }
            }

            public void mouseUp(MouseEvent e) {
            }
        });
        this.addControlsBtn = new Button(composite_1, 0);
        this.addControlsBtn.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        this.addControlsBtn.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (OneStageMultiEnvironmentDialog.this.genLevelsList.getSelectionCount() > 0) {
                    String[] selectedNumVars = OneStageMultiEnvironmentDialog.this.genLevelsList.getSelection();
                    int i = 0;
                    while (i < selectedNumVars.length) {
                        OneStageMultiEnvironmentDialog.this.controlsList.add(selectedNumVars[i]);
                        ++i;
                    }
                    OneStageMultiEnvironmentDialog.this.genLevelsList.remove(OneStageMultiEnvironmentDialog.this.genLevelsList.getSelectionIndices());
                    OneStageMultiEnvironmentDialog.this.addControlsBtn.setEnabled(false);
                } else {
                    String[] selectedNumVars = OneStageMultiEnvironmentDialog.this.controlsList.getSelection();
                    int i = 0;
                    while (i < selectedNumVars.length) {
                        OneStageMultiEnvironmentDialog.this.genLevelsList.add(selectedNumVars[i]);
                        ++i;
                    }
                    OneStageMultiEnvironmentDialog.this.controlsList.remove(OneStageMultiEnvironmentDialog.this.controlsList.getSelectionIndices());
                }
                OneStageMultiEnvironmentDialog.this.addControlsBtn.setEnabled(false);
            }
        });
        this.addControlsBtn.setEnabled(false);
        GridData gd_addControlsBtn = new GridData(4, 0x1000000, false, false, 1, 1);
        gd_addControlsBtn.widthHint = 68;
        this.addControlsBtn.setLayoutData((Object)gd_addControlsBtn);
        this.addControlsBtn.setText("Add");
        this.controlsList = new List(composite_1, 2818);
        this.controlsList.setEnabled(false);
        GridData gd_controlsList = new GridData(4, 4, true, true, 1, 1);
        gd_controlsList.widthHint = 120;
        this.controlsList.setLayoutData((Object)gd_controlsList);
        this.controlsList.addMouseListener(new MouseListener(){

            public void mouseDoubleClick(MouseEvent e) {
                OneStageMultiEnvironmentDialog.this.dataManipulationManager.moveSelectedStringFromTo(OneStageMultiEnvironmentDialog.this.controlsList, OneStageMultiEnvironmentDialog.this.genLevelsList);
                OneStageMultiEnvironmentDialog.this.addControlsBtn.setEnabled(false);
            }

            public void mouseDown(MouseEvent e) {
                if (OneStageMultiEnvironmentDialog.this.controlsList.getSelectionIndex() > -1) {
                    OneStageMultiEnvironmentDialog.this.addControlsBtn.setEnabled(true);
                    OneStageMultiEnvironmentDialog.this.genLevelsList.setSelection(-1);
                    OneStageMultiEnvironmentDialog.this.addControlsBtn.setText("Remove");
                    OneStageMultiEnvironmentDialog.this.addControlsBtn.setEnabled(true);
                }
            }

            public void mouseUp(MouseEvent e) {
            }
        });
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        this.lblNewLabel_5 = new Label(composite_1, 0);
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        this.stabilityFinlaybtn = new Button(composite_1, 32);
        this.stabilityFinlaybtn.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        GridData gd_stabilityFinlaybtn = new GridData(16384, 0x1000000, false, false, 5, 1);
        gd_stabilityFinlaybtn.heightHint = 21;
        this.stabilityFinlaybtn.setLayoutData((Object)gd_stabilityFinlaybtn);
        this.stabilityFinlaybtn.setText("Stability Analysis Using Finlay-Wilkinson Model");
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        this.stabilityShuklabtn = new Button(composite_1, 32);
        this.stabilityShuklabtn.setLayoutData((Object)new GridData(16384, 0x1000000, false, false, 5, 1));
        this.stabilityShuklabtn.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        this.stabilityShuklabtn.setText("Stability Analysis Using Shukla's Model");
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        this.btnAmmiAnalysis = new Button(composite_1, 32);
        this.btnAmmiAnalysis.setLayoutData((Object)new GridData(16384, 0x1000000, false, false, 5, 1));
        this.btnAmmiAnalysis.setText("AMMI Analysis");
        this.btnAmmiAnalysis.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        this.btnGgeAnalysis = new Button(composite_1, 32);
        this.btnGgeAnalysis.setLayoutData((Object)new GridData(16384, 0x1000000, false, false, 5, 1));
        this.btnGgeAnalysis.setText("GGE Analysis");
        this.btnGgeAnalysis.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        this.lblNewLabel_2 = new Label(composite_1, 0);
        this.lblNewLabel_2.setLayoutData((Object)new GridData(16384, 0x1000000, true, false, 1, 1));
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        this.lblNewLabel_6 = new Label(composite_1, 0);
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        Label lblNewLabel_3 = new Label(composite_1, 0);
        lblNewLabel_3.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        lblNewLabel_3.setLayoutData((Object)new GridData(16384, 0x1000000, false, false, 6, 1));
        lblNewLabel_3.setText("Display:");
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        this.checkDescriptiveStatistics = new Button(composite_1, 32);
        this.checkDescriptiveStatistics.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        this.checkDescriptiveStatistics.setLayoutData((Object)new GridData(16384, 0x1000000, false, false, 5, 1));
        this.checkDescriptiveStatistics.setText("Descriptive Statistics");
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        this.checkVarianceComponents = new Button(composite_1, 32);
        this.checkVarianceComponents.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        this.checkVarianceComponents.setLayoutData((Object)new GridData(16384, 0x1000000, false, false, 5, 1));
        this.checkVarianceComponents.setText("Variance Components");
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        this.lblNewLabel_4 = new Label(composite_1, 0);
        this.lblNewLabel_4.setLayoutData((Object)new GridData(16384, 0x1000000, true, false, 1, 1));
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        TabItem tbtmGraph_1 = new TabItem(tabFolder, 0);
        tbtmGraph_1.setText("Graph");
        Composite composite = new Composite((Composite)tabFolder, 0);
        tbtmGraph_1.setControl((Control)composite);
        composite.setBackground(this.getShell().getBackground());
        composite.setLayout((Layout)new GridLayout(2, false));
        Label lblOutputGraph = new Label(composite, 0);
        lblOutputGraph.setLayoutData((Object)new GridData(16384, 0x1000000, true, false, 2, 1));
        lblOutputGraph.setText("Output Graph:");
        new Label(composite, 0);
        this.checkBoxPlotBtn = new Button(composite, 32);
        this.checkBoxPlotBtn.setLayoutData((Object)new GridData(16384, 0x1000000, true, false, 1, 2));
        this.checkBoxPlotBtn.setText("Boxplot across environments");
        new Label(composite, 0);
        new Label(composite, 0);
        this.checkHistogramBtn = new Button(composite, 32);
        GridData gd_checkHistogramBtn = new GridData(4, 0x1000000, true, false, 1, 1);
        gd_checkHistogramBtn.widthHint = 156;
        this.checkHistogramBtn.setLayoutData((Object)gd_checkHistogramBtn);
        this.checkHistogramBtn.setText("Histograms across environments");
        new Label(composite, 0);
        this.checkDiagnosticPlotsBtn = new Button(composite, 32);
        this.checkDiagnosticPlotsBtn.setLayoutData((Object)new GridData(16384, 0x1000000, true, false, 1, 2));
        this.checkDiagnosticPlotsBtn.setText("Diagnostic plots");
        new Label(composite, 0);
        this.initializeForm();
        return container;
    }

    void initializeForm() {
        this.dlgManager.initializeSingleSelectionList(this.factorVarList, this.txtEnvVar, this.moveBtn, this.btnAddEnv, new EnvVarValidator(this.factorVarList, true, this.file, this.txtEnvVar, this.lblEnv));
        this.dlgManager.initializeSingleSelectionList(this.factorVarList, this.txtGenVar, this.moveBtn, this.btnAddGen, new genotypeVarValidator());
        this.dlgManager.initializeSingleSelectionList(this.factorVarList, this.txtBlockVar, this.moveBtn, this.btnAddBlock, new TextVarValidator(this.txtBlockVar, this.lblBlock));
        this.dlgManager.initializeSingleSelectionList(this.factorVarList, this.txtRepVar, this.moveBtn, this.btnAddRep, new TextVarValidator(this.txtRepVar, this.lblRep));
        this.dlgManager.initializeSingleSelectionList(this.factorVarList, this.txtRowVar, this.moveBtn, this.btnAddRow, new TextVarValidator(this.txtRowVar, this.lblRow));
        this.dlgManager.initializeSingleSelectionList(this.factorVarList, this.txtColVar, this.moveBtn, this.btnAddCol, new TextVarValidator(this.txtColVar, this.lblCol));
        this.dlgManager.initializeVariableMoveList(this.numVarList, this.factorVarList, this.moveBtn, this.file.getAbsolutePath());
        this.dlgManager.initializeSingleButtonList(this.numVarList, this.responseVarList, this.moveBtn, this.addBtn);
    }

    protected void resetGenotypeControls() {
        this.genLevelsList.removeAll();
        this.controlsList.removeAll();
    }

    protected void disable(String string) {
        if (string.equals("replicate")) {
            this.lblRep.setEnabled(false);
            if (!this.txtRepVar.getText().isEmpty()) {
                this.factorVarList.add(this.txtRepVar.getText());
            }
            this.txtRepVar.setText("");
            this.btnAddRep.setEnabled(false);
        } else if (string.equals("block")) {
            this.lblBlock.setEnabled(false);
            if (!this.txtBlockVar.getText().isEmpty()) {
                this.factorVarList.add(this.txtBlockVar.getText());
            }
            this.txtBlockVar.setText("");
            this.btnAddBlock.setEnabled(false);
        } else if (string.equals("row")) {
            this.lblRow.setEnabled(false);
            if (!this.txtRowVar.getText().isEmpty()) {
                this.factorVarList.add(this.txtRowVar.getText());
            }
            this.txtRowVar.setText("");
            this.btnAddRow.setEnabled(false);
        } else if (string.equals("column")) {
            this.lblCol.setEnabled(false);
            if (!this.txtColVar.getText().isEmpty()) {
                this.factorVarList.add(this.txtColVar.getText());
            }
            this.txtColVar.setText("");
            this.btnAddCol.setEnabled(false);
        }
    }

    protected void activateFixedOptions(boolean state) {
        this.lblOptionsForGenotype.setEnabled(state);
        this.checkPerformPairwiseBtn.setEnabled(state);
        this.stabilityFinlaybtn.setEnabled(state);
        this.stabilityShuklabtn.setEnabled(state);
        this.btnAmmiAnalysis.setEnabled(state);
        this.btnGgeAnalysis.setEnabled(state);
    }

    protected void activatePerformPairwiseOptions(boolean state) {
        this.compareWithControlsBtn.setEnabled(state);
        this.significanceLevel.setEnabled(state);
        this.lblLevelOfSignificance.setEnabled(state);
        this.compareWithControlsBtn.setSelection(state);
        this.performAllComparisonsBtn.setEnabled(state);
        if (state) {
            if (this.genotypeLevels.length > 15) {
                this.compareWithControlsBtn.setSelection(true);
                this.activateLevelOfConrolsOptions(true);
                this.performAllComparisonsBtn.setSelection(false);
                this.performAllComparisonsBtn.setEnabled(false);
            } else {
                this.performAllComparisonsBtn.setEnabled(true);
            }
        }
    }

    protected void activateLevelOfConrolsOptions(boolean state) {
        this.lblSpecifyControls.setEnabled(state);
        this.levelsOfGenotypeLbl.setEnabled(state);
        this.controlsLbl.setEnabled(state);
        this.genLevelsList.setEnabled(state);
        this.controlsList.setEnabled(state);
    }

    protected void activateDesign(String design) {
    }

    protected void setFactors() {
        this.varInfo = this.dataManipulationManager.getVarInfoFromFile(this.file.getAbsolutePath());
        this.numericVariables = this.dataManipulationManager.getNumericVars(this.varInfo);
        this.factorVariables = this.dataManipulationManager.getFactorVars(this.varInfo);
    }

    public void enableFactorButtons(boolean state) {
        this.txtRepVar.setSelection(-1);
        this.txtBlockVar.setSelection(-1);
        this.txtEnvVar.setSelection(-1);
        this.txtGenVar.setSelection(-1);
        this.txtRowVar.setSelection(-1);
        this.txtColVar.setSelection(-1);
        if (state) {
            this.btnAddBlock.setText("Add");
            this.btnAddRep.setText("Add");
            this.btnAddGen.setText("Add");
            this.btnAddRow.setText("Add");
            this.btnAddCol.setText("Add");
            this.btnAddEnv.setText("Add");
            if (this.txtEnvVar.getText().isEmpty()) {
                this.btnAddEnv.setEnabled(state);
            }
            if (this.txtBlockVar.getText().isEmpty() && this.lblBlock.getEnabled()) {
                this.btnAddBlock.setEnabled(state);
            }
            if (this.txtGenVar.getText().isEmpty()) {
                this.btnAddGen.setEnabled(state);
            }
            if (this.txtRepVar.getText().isEmpty() && this.lblRep.getEnabled()) {
                this.btnAddRep.setEnabled(state);
            }
            if (this.txtRowVar.getText().isEmpty() && this.lblRow.getEnabled()) {
                this.btnAddRow.setEnabled(state);
            }
            if (this.txtColVar.getText().isEmpty() && this.lblCol.getEnabled()) {
                this.btnAddCol.setEnabled(state);
            }
        } else {
            this.moveBtn.setEnabled(state);
            this.btnAddBlock.setEnabled(state);
            this.btnAddRep.setEnabled(state);
            this.btnAddGen.setEnabled(state);
            this.btnAddRow.setEnabled(state);
            this.btnAddCol.setEnabled(state);
            this.btnAddEnv.setEnabled(state);
        }
    }

    public void enableNumericButtons(boolean state) {
        this.addBtn.setEnabled(state);
        this.moveBtn.setEnabled(state);
    }

    protected void createButtonsForButtonBar(Composite parent) {
        this.createButton(parent, 0, IDialogConstants.OK_LABEL, true);
        this.createButton(parent, 1, IDialogConstants.CANCEL_LABEL, false);
    }

    protected void okPressed() {
        this.genotypeFixed = this.checkFixedBtn.getSelection();
        this.genotypeRandom = this.checkRandomBtn.getSelection();
        this.design = this.designType.getSelectionIndex();
        this.descriptiveStat = this.checkDescriptiveStatistics.getSelection();
        this.varianceComponents = this.checkVarianceComponents.getSelection();
        this.boxplotRawData = this.checkBoxPlotBtn.getSelection();
        this.histogramRawData = this.checkHistogramBtn.getSelection();
        this.diagnosticPlot = this.checkDiagnosticPlotsBtn.getSelection();
        this.satisfiedAllConditions = true;
        if (this.responseVarList.getItemCount() < 1) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"Please add a response variable.");
        } else if (this.txtEnvVar.getText().isEmpty()) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"Please add an environment factor.");
        } else if (this.txtGenVar.getText().isEmpty()) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"Please add a genotype factor.");
        } else if (!this.genotypeFixed && !this.genotypeRandom) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"Please specify whether the genotype variable is fixed or random.");
        } else if (this.lblBlock.getEnabled() && this.txtBlockVar.getText().isEmpty()) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"Please add a block factor.");
        } else if (this.lblRep.getEnabled() && this.txtRepVar.getText().isEmpty()) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"Please add a replicate factor.");
        } else if (this.lblRow.getEnabled() && this.txtRowVar.getText().isEmpty()) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"Please add a row factor.");
        } else if (this.lblCol.getEnabled() && this.txtColVar.getText().isEmpty()) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"Please add a column factor.");
        } else {
            if (this.genotypeFixed) {
                this.performPairwise = this.checkPerformPairwiseBtn.getSelection();
                if (this.performPairwise) {
                    this.pairwiseAlpha = this.significanceLevel.getText();
                    if (this.pairwiseAlpha.equals("")) {
                        MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"Please add a value for the pairwise level of significance.");
                        this.satisfiedAllConditions = false;
                    } else {
                        try {
                            Double.parseDouble(this.pairwiseAlpha);
                            this.compareControl = this.compareWithControlsBtn.getSelection();
                            this.performAllPairwise = this.performAllComparisonsBtn.getSelection();
                            if (!this.compareControl && !this.performAllPairwise) {
                                MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"Please specify which pairwise mean comparison to perform.");
                                this.satisfiedAllConditions = false;
                            } else if (this.compareControl) {
                                this.controlLevels = this.controlsList.getItems();
                                if (this.controlLevels.length < 1) {
                                    MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"Please specify control variables from the genotype levels.");
                                    this.satisfiedAllConditions = false;
                                }
                            }
                        }
                        catch (Exception exception) {
                            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"Please add numeric a value for the pairwise level of significance.");
                            this.satisfiedAllConditions = false;
                        }
                    }
                }
            }
            if (this.satisfiedAllConditions) {
                if (this.lblBlock.getEnabled()) {
                    this.block = this.txtBlockVar.getText();
                }
                if (this.lblRep.getEnabled()) {
                    this.rep = this.txtRepVar.getText();
                }
                if (this.lblRow.getEnabled()) {
                    this.row = this.txtRowVar.getText();
                }
                if (this.lblCol.getEnabled()) {
                    this.column = this.txtColVar.getText();
                }
                this.respvars = this.responseVarList.getItems();
                this.environment = this.txtEnvVar.getText();
                this.genotype = this.txtGenVar.getText();
                this.stabilityFinlay = this.stabilityFinlaybtn.getSelection();
                this.stabilityShukla = this.stabilityShuklabtn.getSelection();
                this.ammi = this.btnAmmiAnalysis.getSelection();
                this.gge = this.btnGgeAnalysis.getSelection();
                String selectedStrings = this.txtEnvVar.getText();
                this.environmentLevels = DataManipulationManager.getEnvtLevels(selectedStrings, this.file);
                String dataFileName = this.file.getAbsolutePath().toString().replaceAll("\\\\+", "/");
                File outputFolder = PBToolAnalysisUtilities.createOutputFolder(this.file.getName(), this.analysisType);
                String newOutputFileName = String.valueOf(outputFolder.getAbsolutePath()) + "/output.txt";
                OperationProgressDialog rInfo = new OperationProgressDialog(this.getShell(), "One-Stage Multi-Environment Analysis");
                rInfo.open();
                ProjectExplorerView.rJavaManager.getPbToolAnalysisManager().doMultiEnvironmentOneStage(dataFileName, newOutputFileName.replaceAll("\\\\+", "/"), String.valueOf(outputFolder.getAbsolutePath().replaceAll("\\\\+", "/")) + "/", this.design, this.respvars, this.environment, this.environmentLevels, this.genotype, this.block, this.rep, this.row, this.column, this.descriptiveStat, this.varianceComponents, this.stabilityFinlay, this.stabilityShukla, this.ammi, this.gge, this.boxplotRawData, this.histogramRawData, this.diagnosticPlot, this.genotypeFixed, this.performPairwise, this.pairwiseAlpha, this.genotypeLevels, this.controlLevels, this.compareControl, this.performAllPairwise, this.genotypeRandom);
                rInfo.close();
                WindowDialogControlUtil.hideAllWindowDialog();
                PBToolAnalysisUtilities.openFolder(outputFolder);
            }
        }
    }

    protected Point getInitialSize() {
        return new Point(538, 664);
    }

    protected boolean isResizable() {
        return true;
    }

    public class genotypeVarValidator
    extends DialogListValidator {
        @Override
        public boolean validate() {
            return true;
        }

        @Override
        public boolean performAddProcess() {
            String[] selectedStrings = OneStageMultiEnvironmentDialog.this.factorVarList.getSelection();
            OneStageMultiEnvironmentDialog.this.genotypeLevels = DataManipulationManager.getEnvtLevels(selectedStrings[0], OneStageMultiEnvironmentDialog.this.file);
            OneStageMultiEnvironmentDialog.this.genLevelsList.setItems(OneStageMultiEnvironmentDialog.this.genotypeLevels);
            if (OneStageMultiEnvironmentDialog.this.checkFixedBtn.getSelection() && OneStageMultiEnvironmentDialog.this.checkPerformPairwiseBtn.getSelection()) {
                OneStageMultiEnvironmentDialog.this.activatePerformPairwiseOptions(true);
            }
            return true;
        }

        @Override
        public boolean performRemoveProcess() {
            OneStageMultiEnvironmentDialog.this.genLevelsList.removeAll();
            OneStageMultiEnvironmentDialog.this.controlsList.removeAll();
            OneStageMultiEnvironmentDialog.this.resetGenotypeControls();
            return true;
        }
    }
}

