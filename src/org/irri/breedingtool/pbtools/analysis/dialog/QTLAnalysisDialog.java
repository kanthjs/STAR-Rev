/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.jface.dialogs.Dialog
 *  org.eclipse.jface.dialogs.IDialogConstants
 *  org.eclipse.jface.dialogs.MessageDialog
 *  org.eclipse.swt.custom.CCombo
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
 *  org.eclipse.swt.widgets.FileDialog
 *  org.eclipse.swt.widgets.Group
 *  org.eclipse.swt.widgets.Label
 *  org.eclipse.swt.widgets.Layout
 *  org.eclipse.swt.widgets.List
 *  org.eclipse.swt.widgets.Shell
 *  org.eclipse.swt.widgets.Spinner
 *  org.eclipse.swt.widgets.TabFolder
 *  org.eclipse.swt.widgets.TabItem
 *  org.eclipse.swt.widgets.Text
 */
package org.irri.breedingtool.pbtools.analysis.dialog;

import java.io.File;
import java.util.ArrayList;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.custom.CCombo;
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
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;
import org.irri.breedingtool.application.model.ProjectExplorerTreeNodeModel;
import org.irri.breedingtool.datamanipulation.dialog.OperationProgressDialog;
import org.irri.breedingtool.manager.impl.DataManipulationManager;
import org.irri.breedingtool.manager.impl.ProjectExplorerManager;
import org.irri.breedingtool.projectexplorer.view.ProjectExplorerView;
import org.irri.breedingtool.utility.DialogFormUtility;
import org.irri.breedingtool.utility.DialogListValidator;
import org.irri.breedingtool.utility.PBToolAnalysisUtilities;
import org.irri.breedingtool.utility.TextVarValidator;
import org.irri.breedingtool.utility.WindowDialogControlUtil;

public class QTLAnalysisDialog
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
    private Button moveBtn;
    private String[] numericVariables;
    private String[] factorVariables;
    private File file;
    private DataManipulationManager dataManipulationManager = new DataManipulationManager();
    private DialogFormUtility dlgManager = new DialogFormUtility();
    private Button btnAddGen;
    private Label lblBlock;
    private Label lblRep;
    private Group variableGroup;
    private Label lblCol;
    private Label lblRow;
    private FileDialog fd;
    private int design = 0;
    private String[] respvars = new String[]{"Y1", "Y2"};
    private String environment = "NULL";
    private String[] environmentLevels = new String[]{"Env1", "Env2"};
    private String genotype = "Gen";
    private String block = "Blk";
    private String rep = "NULL";
    private String row = "NULL";
    private String column = "NULL";
    private String[] controlLevels = new String[]{"1", "2", "3"};
    boolean isInputRawData = false;
    int designIndex = 0;
    String[] traits = new String[]{"pred"};
    String selectedEnvironmentLevel = "NULL";
    boolean heterozygousPresent = true;
    String crossType = "riself";
    String step = "10";
    String windowSize = "20";
    String minDistance = "10";
    String qtlMethod = "CIM";
    boolean thresholdLiJi = true;
    String thresholdNumericValue = "NULL";
    boolean estimatePlotMarkerMap = true;
    double allelicDiffThreshold = 0.1;
    double cutOffMissingData = 0.1;
    double significanceLevelChiSquare = 0.05;
    private boolean satisfiedAllConditions = true;
    private Label lblLevels;
    private TabItem tbtmDataInput;
    private TabItem tbtmOptions;
    private Composite dataInputComposite;
    private Composite composite_1;
    private Composite optionsComposite;
    private Composite composite_2;
    private Label lblPhenotypicDataUsed;
    private Button btnPredicted;
    private Button btnRaw;
    private Button btnBrowse;
    private Label lblFilename;
    private Label lblGenotypicDataFile;
    private Label lblGenotypicMapFile;
    private Label label_1;
    private Button button;
    private Label label;
    private Button heterozygotesPresentBtn;
    private Label lblChiSquare;
    private Combo envLevelsCombo;
    private Composite composite;
    private Button btnLiAndJi;
    private Button btnNumerical;
    private Composite composite_3;
    private Button btnSim;
    private Button btnCim;
    private Label label_3;
    private Spinner chiSquareSignificanceSpinner;
    private Spinner allelicProportionsSpinner;
    private Spinner cutOffSpinner;
    private Spinner windowSizeSpinner;
    private Spinner stepSpinner;
    private Spinner minDistanceSpinner;
    private Label lblCutoffForReporting;
    private Spinner numericThresholdSpinner;
    private Combo crossTypeSelection;
    private Button btnEstimateAndPlot;
    private String genotypicDataFileName;
    private String mapFileName;
    private String analysisType;
    private Label lblTypeOfDesignLabel;
    private CCombo genDataFileText;
    private CCombo genMapFileText;
    private Label lblGen;
    private Label lblEnv;
    private Text txtGenVar;
    private Text txtEnvVar;
    private Text txtBlockVar;
    private Text txtRepVar;
    private Text txtRowVar;
    private Text txtColVar;

    public QTLAnalysisDialog(Shell parentShell, String analysisType, File file) {
        super(parentShell);
        this.setBlockOnOpen(false);
        this.analysisType = analysisType;
        this.setShellStyle(2256);
        this.file = file;
        this.setFactors();
    }

    protected Control createDialogArea(Composite parent) {
        this.getShell().setData("analysis", (Object)this.analysisType);
        this.getShell().setData("filePathID", (Object)this.file.getAbsolutePath());
        WindowDialogControlUtil.addWindowDialogToList(this.getShell());
        parent.addDisposeListener(new DisposeListener(){

            public void widgetDisposed(DisposeEvent e) {
                WindowDialogControlUtil.removeWindowDialogToList(QTLAnalysisDialog.this.getShell());
            }
        });
        this.fd = new FileDialog(this.getShell(), 4096);
        this.fd.setText("Choose File Input");
        ProjectExplorerManager projectExplorerManger = new ProjectExplorerManager();
        this.fd.setFilterPath(((ProjectExplorerTreeNodeModel)projectExplorerManger.getDataFolder(ProjectExplorerView.projectTree).getData()).getProjectFile().getAbsolutePath());
        String[] filterExt = new String[]{"*.txt"};
        this.fd.setFilterExtensions(filterExt);
        parent.getShell().setText("QTL Analysis: " + this.dataManipulationManager.getDisplayFileName(this.file.getAbsolutePath()));
        Composite container = (Composite)super.createDialogArea(parent);
        TabFolder tabFolder = new TabFolder(container, 0);
        GridData gd_tabFolder = new GridData(4, 4, true, true, 1, 1);
        gd_tabFolder.heightHint = 489;
        tabFolder.setLayoutData((Object)gd_tabFolder);
        this.tbtmDataInput = new TabItem(tabFolder, 0);
        this.tbtmDataInput.setText("Data Input");
        this.dataInputComposite = new Composite((Composite)tabFolder, 0);
        this.tbtmDataInput.setControl((Control)this.dataInputComposite);
        this.dataInputComposite.setBackground(this.getShell().getBackground());
        this.dataInputComposite.setLayout((Layout)new GridLayout(5, false));
        new Label(this.dataInputComposite, 0);
        this.label = new Label(this.dataInputComposite, 0);
        new Label(this.dataInputComposite, 0);
        new Label(this.dataInputComposite, 0);
        new Label(this.dataInputComposite, 0);
        this.lblPhenotypicDataUsed = new Label(this.dataInputComposite, 0);
        this.lblPhenotypicDataUsed.setLayoutData((Object)new GridData(16384, 0x1000000, false, false, 2, 1));
        this.lblPhenotypicDataUsed.setText("Phenotypic Data Used:");
        this.btnPredicted = new Button(this.dataInputComposite, 16);
        this.btnPredicted.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                QTLAnalysisDialog.this.isInputRawData = false;
                QTLAnalysisDialog.this.activatePredictedDataOptions(false);
            }
        });
        this.btnPredicted.setSelection(true);
        this.btnPredicted.setText("Estimated Means");
        this.btnRaw = new Button(this.dataInputComposite, 16);
        this.btnRaw.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                QTLAnalysisDialog.this.isInputRawData = true;
                QTLAnalysisDialog.this.activatePredictedDataOptions(true);
            }
        });
        this.btnRaw.setText("Raw");
        new Label(this.dataInputComposite, 0);
        new Label(this.dataInputComposite, 0);
        new Label(this.dataInputComposite, 0);
        new Label(this.dataInputComposite, 0);
        new Label(this.dataInputComposite, 0);
        new Label(this.dataInputComposite, 0);
        this.lblGenotypicDataFile = new Label(this.dataInputComposite, 0);
        this.lblGenotypicDataFile.setLayoutData((Object)new GridData(16384, 0x1000000, false, false, 2, 1));
        this.lblGenotypicDataFile.setText("Genotypic data file (.txt):");
        new Label(this.dataInputComposite, 0);
        new Label(this.dataInputComposite, 0);
        new Label(this.dataInputComposite, 0);
        new Label(this.dataInputComposite, 0);
        this.lblFilename = new Label(this.dataInputComposite, 0);
        this.lblFilename.setLayoutData((Object)new GridData(131072, 0x1000000, false, false, 1, 1));
        this.lblFilename.setText("Filename:");
        this.genDataFileText = new CCombo(this.dataInputComposite, 2048);
        this.genDataFileText.setBackground(SWTResourceManager.getColor(1));
        this.genDataFileText.setEditable(false);
        this.genDataFileText.setLayoutData((Object)new GridData(4, 0x1000000, true, false, 2, 1));
        this.genDataFileText.setItems(this.dataManipulationManager.getTxtFilesFromDir(this.file));
        this.genDataFileText.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                QTLAnalysisDialog.this.genotypicDataFileName = String.valueOf(QTLAnalysisDialog.this.file.getParentFile().getAbsolutePath()) + "\\" + QTLAnalysisDialog.this.genDataFileText.getText();
            }
        });
        this.btnBrowse = new Button(this.dataInputComposite, 0);
        GridData gd_btnBrowse = new GridData(4, 0x1000000, false, false, 1, 1);
        gd_btnBrowse.widthHint = 73;
        this.btnBrowse.setLayoutData((Object)gd_btnBrowse);
        this.btnBrowse.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                try {
                    String selected = QTLAnalysisDialog.this.fd.open();
                    QTLAnalysisDialog.this.genotypicDataFileName = selected;
                    QTLAnalysisDialog.this.genDataFileText.setText(new File(selected).getName());
                }
                catch (NullPointerException nullPointerException) {}
            }
        });
        this.btnBrowse.setText("Browse");
        new Label(this.dataInputComposite, 0);
        new Label(this.dataInputComposite, 0);
        new Label(this.dataInputComposite, 0);
        new Label(this.dataInputComposite, 0);
        new Label(this.dataInputComposite, 0);
        this.lblGenotypicMapFile = new Label(this.dataInputComposite, 0);
        this.lblGenotypicMapFile.setLayoutData((Object)new GridData(16384, 0x1000000, false, false, 2, 1));
        this.lblGenotypicMapFile.setText("Genetic map file (.txt):");
        new Label(this.dataInputComposite, 0);
        new Label(this.dataInputComposite, 0);
        new Label(this.dataInputComposite, 0);
        new Label(this.dataInputComposite, 0);
        this.label_1 = new Label(this.dataInputComposite, 0);
        this.label_1.setLayoutData((Object)new GridData(131072, 0x1000000, false, false, 1, 1));
        this.label_1.setText("Filename:");
        this.genMapFileText = new CCombo(this.dataInputComposite, 2048);
        this.genMapFileText.setBackground(SWTResourceManager.getColor(1));
        this.genMapFileText.setEditable(false);
        this.genMapFileText.setLayoutData((Object)new GridData(4, 0x1000000, true, false, 2, 1));
        this.genMapFileText.setItems(this.dataManipulationManager.getTxtFilesFromDir(this.file));
        this.genMapFileText.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                QTLAnalysisDialog.this.mapFileName = String.valueOf(QTLAnalysisDialog.this.file.getParentFile().getAbsolutePath()) + "\\" + QTLAnalysisDialog.this.genMapFileText.getText();
            }
        });
        this.button = new Button(this.dataInputComposite, 0);
        GridData gd_button = new GridData(4, 0x1000000, false, false, 1, 1);
        gd_button.widthHint = 79;
        this.button.setLayoutData((Object)gd_button);
        this.button.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                try {
                    String selected = QTLAnalysisDialog.this.fd.open();
                    QTLAnalysisDialog.this.mapFileName = selected;
                    QTLAnalysisDialog.this.genMapFileText.setText(new File(selected).getName());
                }
                catch (NullPointerException nullPointerException) {}
            }
        });
        this.button.setText("Browse");
        TabItem tbtmModelSpecifications_1 = new TabItem(tabFolder, 0);
        tbtmModelSpecifications_1.setText("Model Specifications");
        Composite modelComposite = new Composite((Composite)tabFolder, 0);
        tbtmModelSpecifications_1.setControl((Control)modelComposite);
        modelComposite.setLayout((Layout)new GridLayout(5, false));
        modelComposite.setBackground(this.getShell().getBackground());
        this.lblTypeOfDesignLabel = new Label(modelComposite, 0);
        this.lblTypeOfDesignLabel.setEnabled(false);
        this.lblTypeOfDesignLabel.setLayoutData((Object)new GridData(16384, 0x1000000, false, false, 2, 1));
        this.lblTypeOfDesignLabel.setText("Type of Design:");
        this.designType = new Combo(modelComposite, 8);
        this.designType.setEnabled(false);
        this.designType.setItems(new String[]{"Randomized Complete Block (RCB)", "Augmented RCB", "Augmented Latin Square", "Alpha-Lattice", "Row-Column"});
        this.designType.setLayoutData((Object)new GridData(4, 0x1000000, true, false, 3, 1));
        this.designType.select(0);
        this.designType.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                QTLAnalysisDialog.this.enableFactorButtons(false);
                if (QTLAnalysisDialog.this.designType.getSelectionIndex() == 0) {
                    QTLAnalysisDialog.this.lblBlock.setEnabled(true);
                    QTLAnalysisDialog.this.disable("replicate");
                    QTLAnalysisDialog.this.disable("row");
                    QTLAnalysisDialog.this.disable("column");
                } else if (QTLAnalysisDialog.this.designType.getSelectionIndex() == 1) {
                    QTLAnalysisDialog.this.lblBlock.setEnabled(true);
                    QTLAnalysisDialog.this.disable("replicate");
                    QTLAnalysisDialog.this.disable("row");
                    QTLAnalysisDialog.this.disable("column");
                } else if (QTLAnalysisDialog.this.designType.getSelectionIndex() == 2) {
                    QTLAnalysisDialog.this.lblRow.setEnabled(true);
                    QTLAnalysisDialog.this.lblCol.setEnabled(true);
                    QTLAnalysisDialog.this.lblRep.setEnabled(true);
                    QTLAnalysisDialog.this.disable("block");
                } else if (QTLAnalysisDialog.this.designType.getSelectionIndex() == 3) {
                    QTLAnalysisDialog.this.lblRep.setEnabled(true);
                    QTLAnalysisDialog.this.lblBlock.setEnabled(true);
                    QTLAnalysisDialog.this.disable("row");
                    QTLAnalysisDialog.this.disable("column");
                } else if (QTLAnalysisDialog.this.designType.getSelectionIndex() == 4) {
                    QTLAnalysisDialog.this.lblRow.setEnabled(true);
                    QTLAnalysisDialog.this.lblCol.setEnabled(true);
                    QTLAnalysisDialog.this.lblRep.setEnabled(true);
                    QTLAnalysisDialog.this.disable("block");
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
        GridData gd_numVarList = new GridData(4, 4, true, false, 2, 1);
        gd_numVarList.heightHint = 95;
        gd_numVarList.widthHint = 171;
        this.numVarList.setLayoutData((Object)gd_numVarList);
        this.numVarList.setItems(this.numericVariables);
        this.numVarList.addMouseListener(new MouseListener(){

            public void mouseDoubleClick(MouseEvent e) {
                QTLAnalysisDialog.this.numVarList.getSelection();
                QTLAnalysisDialog.this.dataManipulationManager.moveSelectedStringFromTo(QTLAnalysisDialog.this.numVarList, QTLAnalysisDialog.this.responseVarList);
                QTLAnalysisDialog.this.numVarList.remove(QTLAnalysisDialog.this.numVarList.getSelectionIndices());
            }

            public void mouseDown(MouseEvent e) {
                QTLAnalysisDialog.this.moveBtn.setEnabled(true);
                if (QTLAnalysisDialog.this.numVarList.getSelectionCount() > 0) {
                    QTLAnalysisDialog.this.factorVarList.setSelection(-1);
                    QTLAnalysisDialog.this.responseVarList.setSelection(-1);
                    QTLAnalysisDialog.this.addBtn.setText("Add");
                    QTLAnalysisDialog.this.moveBtn.setText("Set to Factor");
                    QTLAnalysisDialog.this.enableFactorButtons(false);
                    QTLAnalysisDialog.this.enableNumericButtons(true);
                }
            }

            public void mouseUp(MouseEvent e) {
            }
        });
        new Label(modelComposite, 0);
        this.addBtn = new Button(modelComposite, 0);
        this.addBtn.setEnabled(false);
        GridData gd_addBtn = new GridData(16384, 0x1000000, false, false, 1, 1);
        gd_addBtn.widthHint = 52;
        this.addBtn.setLayoutData((Object)gd_addBtn);
        this.addBtn.setText("Add");
        this.responseVarList = new List(modelComposite, 2818);
        GridData gd_responseVarList = new GridData(4, 4, true, false, 1, 1);
        gd_responseVarList.widthHint = 111;
        this.responseVarList.setLayoutData((Object)gd_responseVarList);
        Label lblFactors = new Label(modelComposite, 0);
        lblFactors.setText("Factors:");
        this.moveBtn = new Button(modelComposite, 0);
        this.moveBtn.setEnabled(false);
        GridData gd_moveBtn = new GridData(131072, 0x1000000, true, false, 1, 1);
        gd_moveBtn.heightHint = 24;
        gd_moveBtn.widthHint = 90;
        this.moveBtn.setLayoutData((Object)gd_moveBtn);
        this.moveBtn.setText("Set to Factor");
        new Label(modelComposite, 0);
        new Label(modelComposite, 0);
        new Label(modelComposite, 0);
        this.factorVarList = new List(modelComposite, 2816);
        GridData gd_factorVarList = new GridData(4, 4, true, true, 2, 2);
        gd_factorVarList.widthHint = 65;
        this.factorVarList.setLayoutData((Object)gd_factorVarList);
        this.factorVarList.setItems(this.factorVariables);
        Group group = new Group(modelComposite, 0);
        group.setLayout((Layout)new GridLayout(5, false));
        GridData gd_group = new GridData(4, 4, true, false, 3, 1);
        gd_group.heightHint = 133;
        gd_group.widthHint = 255;
        group.setLayoutData((Object)gd_group);
        this.btnAddGen = new Button((Composite)group, 0);
        this.btnAddGen.setText("Add");
        GridData gd_btnAddGen = new GridData(16384, 0x1000000, false, false, 1, 1);
        gd_btnAddGen.widthHint = 52;
        this.btnAddGen.setLayoutData((Object)gd_btnAddGen);
        this.lblGen = new Label((Composite)group, 0);
        this.lblGen.setLayoutData((Object)new GridData(16384, 0x1000000, false, false, 2, 1));
        this.lblGen.setText("Genotype:");
        this.txtGenVar = new Text((Composite)group, 2048);
        this.txtGenVar.setBackground(SWTResourceManager.getColor(1));
        this.txtGenVar.setEditable(false);
        this.txtGenVar.setLayoutData((Object)new GridData(4, 4, true, false, 2, 1));
        this.btnAddEnv = new Button((Composite)group, 0);
        this.btnAddEnv.setText("Add");
        GridData gd_btnAddEnv = new GridData(16384, 0x1000000, false, false, 1, 1);
        gd_btnAddEnv.widthHint = 52;
        this.btnAddEnv.setLayoutData((Object)gd_btnAddEnv);
        this.lblEnv = new Label((Composite)group, 0);
        this.lblEnv.setLayoutData((Object)new GridData(16384, 0x1000000, false, false, 2, 1));
        this.lblEnv.setText("Environment:");
        this.txtEnvVar = new Text((Composite)group, 2048);
        this.txtEnvVar.setEditable(false);
        this.txtEnvVar.setBackground(SWTResourceManager.getColor(1));
        this.txtEnvVar.setLayoutData((Object)new GridData(4, 4, true, false, 2, 1));
        this.txtEnvVar.addMouseListener(new MouseListener(){

            public void mouseDoubleClick(MouseEvent e) {
                QTLAnalysisDialog.this.envLevelsCombo.setEnabled(false);
                QTLAnalysisDialog.this.envLevelsCombo.removeAll();
            }

            public void mouseDown(MouseEvent e) {
            }

            public void mouseUp(MouseEvent e) {
            }
        });
        new Label((Composite)group, 0);
        new Label((Composite)group, 0);
        this.lblLevels = new Label((Composite)group, 0);
        this.lblLevels.setLayoutData((Object)new GridData(131072, 0x1000000, false, false, 1, 1));
        this.lblLevels.setText("Level(s):");
        this.envLevelsCombo = new Combo((Composite)group, 8);
        this.envLevelsCombo.setEnabled(false);
        this.envLevelsCombo.setLayoutData((Object)new GridData(4, 0x1000000, true, false, 2, 1));
        this.variableGroup = new Group(modelComposite, 0);
        this.variableGroup.setEnabled(false);
        this.variableGroup.setLayout((Layout)new GridLayout(3, false));
        GridData gd_variableGroup = new GridData(4, 4, false, false, 3, 1);
        gd_variableGroup.heightHint = 135;
        this.variableGroup.setLayoutData((Object)gd_variableGroup);
        this.btnAddBlock = new Button((Composite)this.variableGroup, 0);
        this.btnAddBlock.setEnabled(false);
        this.btnAddBlock.setText("Add");
        GridData gd_btnAddBlock = new GridData(16384, 0x1000000, false, false, 1, 1);
        gd_btnAddBlock.widthHint = 52;
        this.btnAddBlock.setLayoutData((Object)gd_btnAddBlock);
        this.lblBlock = new Label((Composite)this.variableGroup, 0);
        this.lblBlock.setEnabled(false);
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
        this.lblCol.setEnabled(false);
        this.lblCol.setText("Column:");
        this.txtColVar = new Text((Composite)this.variableGroup, 2048);
        this.txtColVar.setEditable(false);
        this.txtColVar.setBackground(SWTResourceManager.getColor(1));
        this.txtColVar.setLayoutData((Object)new GridData(4, 4, true, false, 1, 1));
        this.tbtmOptions = new TabItem(tabFolder, 0);
        this.tbtmOptions.setText("Options");
        this.optionsComposite = new Composite((Composite)tabFolder, 0);
        this.tbtmOptions.setControl((Control)this.optionsComposite);
        this.optionsComposite.setBackground(this.getShell().getBackground());
        this.optionsComposite.setLayout((Layout)new GridLayout(5, false));
        this.label_3 = new Label(this.optionsComposite, 0);
        this.label_3.setLayoutData((Object)new GridData(16384, 0x1000000, false, false, 5, 1));
        this.heterozygotesPresentBtn = new Button(this.optionsComposite, 32);
        this.heterozygotesPresentBtn.setLayoutData((Object)new GridData(16384, 0x1000000, false, false, 2, 1));
        this.heterozygotesPresentBtn.setText("Heterozygotes present");
        new Label(this.optionsComposite, 0);
        new Label(this.optionsComposite, 0);
        new Label(this.optionsComposite, 0);
        Label lblTypeOfCross = new Label(this.optionsComposite, 0);
        lblTypeOfCross.setText("Type of Cross:");
        this.crossTypeSelection = new Combo(this.optionsComposite, 8);
        this.crossTypeSelection.setItems(new String[]{"f2", "bc", "dh", "am", "riself", "ri4self", "ri8self", "risib", "ri4sib", "ri8sib"});
        this.crossTypeSelection.setLayoutData((Object)new GridData(4, 0x1000000, true, false, 1, 1));
        this.crossTypeSelection.select(0);
        new Label(this.optionsComposite, 0);
        Label lblStep = new Label(this.optionsComposite, 0);
        lblStep.setText("Step:");
        this.stepSpinner = new Spinner(this.optionsComposite, 2048);
        GridData gd_stepSpinner = new GridData(16384, 0x1000000, false, false, 1, 1);
        gd_stepSpinner.widthHint = 10;
        this.stepSpinner.setLayoutData((Object)gd_stepSpinner);
        this.stepSpinner.setSelection(10);
        Label lblWindowSize = new Label(this.optionsComposite, 0);
        lblWindowSize.setText("Window size:");
        this.windowSizeSpinner = new Spinner(this.optionsComposite, 2048);
        this.windowSizeSpinner.setSelection(20);
        GridData gd_windowSizeSpinner = new GridData(4, 0x1000000, false, false, 1, 1);
        gd_windowSizeSpinner.widthHint = 10;
        this.windowSizeSpinner.setLayoutData((Object)gd_windowSizeSpinner);
        new Label(this.optionsComposite, 0);
        Label lblMinimumDistance = new Label(this.optionsComposite, 0);
        lblMinimumDistance.setLayoutData((Object)new GridData(131072, 0x1000000, false, false, 1, 1));
        lblMinimumDistance.setText("Minimum distance:");
        this.minDistanceSpinner = new Spinner(this.optionsComposite, 2048);
        this.minDistanceSpinner.setSelection(10);
        GridData gd_minDistanceSpinner = new GridData(16384, 0x1000000, false, false, 1, 1);
        gd_minDistanceSpinner.widthHint = 10;
        this.minDistanceSpinner.setLayoutData((Object)gd_minDistanceSpinner);
        Label lblMethodForDetecting = new Label(this.optionsComposite, 0);
        lblMethodForDetecting.setText("Method for detecting QTL:");
        this.composite_3 = new Composite(this.optionsComposite, 0);
        this.composite_3.setLayout((Layout)new GridLayout(3, false));
        this.composite_3.setLayoutData((Object)new GridData(4, 0x1000000, false, false, 3, 1));
        this.btnSim = new Button(this.composite_3, 16);
        this.btnSim.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                QTLAnalysisDialog.this.qtlMethod = "SIM";
            }
        });
        GridData gd_btnSim = new GridData(16384, 0x1000000, false, false, 1, 1);
        gd_btnSim.widthHint = 59;
        this.btnSim.setLayoutData((Object)gd_btnSim);
        this.btnSim.setText("SIM");
        new Label(this.composite_3, 0);
        this.btnCim = new Button(this.composite_3, 16);
        this.btnCim.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                QTLAnalysisDialog.this.qtlMethod = "CIM";
            }
        });
        this.btnCim.setSelection(true);
        this.btnCim.setText("CIM");
        new Label(this.optionsComposite, 0);
        Label lblThresholdForPvalue = new Label(this.optionsComposite, 0);
        lblThresholdForPvalue.setText("Threshold for p-value:");
        this.composite = new Composite(this.optionsComposite, 0);
        this.composite.setLayout((Layout)new GridLayout(3, false));
        GridData gd_composite = new GridData(4, 4, false, false, 3, 1);
        gd_composite.widthHint = 166;
        this.composite.setLayoutData((Object)gd_composite);
        this.btnLiAndJi = new Button(this.composite, 16);
        this.btnLiAndJi.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                QTLAnalysisDialog.this.thresholdLiJi = true;
                QTLAnalysisDialog.this.numericThresholdSpinner.setEnabled(false);
            }
        });
        this.btnLiAndJi.setSelection(true);
        GridData gd_btnLiAndJi = new GridData(16384, 0x1000000, false, false, 1, 1);
        gd_btnLiAndJi.widthHint = 59;
        this.btnLiAndJi.setLayoutData((Object)gd_btnLiAndJi);
        this.btnLiAndJi.setText("Li and Ji");
        new Label(this.composite, 0);
        this.btnNumerical = new Button(this.composite, 16);
        this.btnNumerical.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                QTLAnalysisDialog.this.thresholdLiJi = false;
                QTLAnalysisDialog.this.numericThresholdSpinner.setEnabled(true);
            }
        });
        this.btnNumerical.setText("Numerical");
        this.numericThresholdSpinner = new Spinner(this.optionsComposite, 2048);
        this.numericThresholdSpinner.setEnabled(false);
        this.numericThresholdSpinner.setPageIncrement(0);
        this.numericThresholdSpinner.setMaximum(95);
        this.numericThresholdSpinner.setMinimum(5);
        this.numericThresholdSpinner.setSelection(10);
        this.numericThresholdSpinner.setIncrement(5);
        this.numericThresholdSpinner.setDigits(2);
        new Label(this.optionsComposite, 0);
        new Label(this.optionsComposite, 0);
        new Label(this.optionsComposite, 0);
        new Label(this.optionsComposite, 0);
        new Label(this.optionsComposite, 0);
        Group grpMarkerQualitOptions = new Group(this.optionsComposite, 0);
        grpMarkerQualitOptions.setText("Marker Quality Options:");
        grpMarkerQualitOptions.setLayout((Layout)new GridLayout(2, false));
        grpMarkerQualitOptions.setLayoutData((Object)new GridData(4, 4, true, false, 5, 1));
        this.btnEstimateAndPlot = new Button((Composite)grpMarkerQualitOptions, 32);
        this.btnEstimateAndPlot.setText("Estimate and plot new marker map");
        new Label((Composite)grpMarkerQualitOptions, 0);
        Label lblAllelicDifferencesThreshold = new Label((Composite)grpMarkerQualitOptions, 0);
        lblAllelicDifferencesThreshold.setText("Allelic differences threshold (proportion):");
        this.allelicProportionsSpinner = new Spinner((Composite)grpMarkerQualitOptions, 2048);
        this.allelicProportionsSpinner.setMaximum(95);
        this.allelicProportionsSpinner.setMinimum(5);
        this.allelicProportionsSpinner.setSelection(10);
        this.allelicProportionsSpinner.setIncrement(5);
        this.allelicProportionsSpinner.setPageIncrement(0);
        this.allelicProportionsSpinner.setDigits(2);
        GridData gd_allelicProportionsSpinner = new GridData(4, 0x1000000, false, false, 1, 1);
        gd_allelicProportionsSpinner.widthHint = 41;
        this.allelicProportionsSpinner.setLayoutData((Object)gd_allelicProportionsSpinner);
        this.lblCutoffForReporting = new Label((Composite)grpMarkerQualitOptions, 0);
        this.lblCutoffForReporting.setText("Cut-off for reporting missing data:");
        this.cutOffSpinner = new Spinner((Composite)grpMarkerQualitOptions, 2048);
        this.cutOffSpinner.setMaximum(95);
        this.cutOffSpinner.setMinimum(5);
        this.cutOffSpinner.setSelection(10);
        this.cutOffSpinner.setDigits(2);
        this.cutOffSpinner.setPageIncrement(0);
        this.cutOffSpinner.setIncrement(5);
        GridData gd_cutOffSpinner = new GridData(4, 0x1000000, false, false, 1, 1);
        gd_cutOffSpinner.widthHint = 46;
        this.cutOffSpinner.setLayoutData((Object)gd_cutOffSpinner);
        this.lblChiSquare = new Label((Composite)grpMarkerQualitOptions, 64);
        GridData gd_lblChiSquare = new GridData(16384, 0x1000000, false, false, 1, 1);
        gd_lblChiSquare.widthHint = 199;
        this.lblChiSquare.setLayoutData((Object)gd_lblChiSquare);
        this.lblChiSquare.setText("Level of significance for Chi-square test for Segregation Distortion:");
        this.chiSquareSignificanceSpinner = new Spinner((Composite)grpMarkerQualitOptions, 2048);
        this.chiSquareSignificanceSpinner.setMaximum(95);
        this.chiSquareSignificanceSpinner.setMinimum(5);
        this.chiSquareSignificanceSpinner.setSelection(5);
        this.chiSquareSignificanceSpinner.setPageIncrement(0);
        this.chiSquareSignificanceSpinner.setIncrement(5);
        this.chiSquareSignificanceSpinner.setDigits(2);
        GridData gd_chiSquareSignificanceSpinner = new GridData(4, 0x1000000, false, false, 1, 1);
        gd_chiSquareSignificanceSpinner.widthHint = 20;
        this.chiSquareSignificanceSpinner.setLayoutData((Object)gd_chiSquareSignificanceSpinner);
        this.initializeForm();
        return container;
    }

    void initializeForm() {
        this.dlgManager.initializeSingleSelectionList(this.factorVarList, this.txtEnvVar, this.moveBtn, this.btnAddEnv, new envVarValidator(this.factorVarList, true, this.file, this.txtEnvVar, this.lblEnv));
        this.dlgManager.initializeSingleSelectionList(this.factorVarList, this.txtGenVar, this.moveBtn, this.btnAddGen, new TextVarValidator(this.txtGenVar, this.lblGen));
        this.dlgManager.initializeSingleSelectionList(this.factorVarList, this.txtBlockVar, this.moveBtn, this.btnAddBlock, new TextVarValidator(this.txtBlockVar, this.lblBlock));
        this.dlgManager.initializeSingleSelectionList(this.factorVarList, this.txtRepVar, this.moveBtn, this.btnAddRep, new TextVarValidator(this.txtRepVar, this.lblRep));
        this.dlgManager.initializeSingleSelectionList(this.factorVarList, this.txtRowVar, this.moveBtn, this.btnAddRow, new TextVarValidator(this.txtRowVar, this.lblRow));
        this.dlgManager.initializeSingleSelectionList(this.factorVarList, this.txtColVar, this.moveBtn, this.btnAddCol, new TextVarValidator(this.txtColVar, this.lblCol));
        this.dlgManager.initializeVariableMoveList(this.numVarList, this.factorVarList, this.moveBtn, this.file.getAbsolutePath());
        this.dlgManager.initializeSingleButtonList(this.numVarList, this.responseVarList, this.moveBtn, this.addBtn);
    }

    protected void activatePredictedDataOptions(boolean state) {
        this.designType.setEnabled(state);
        this.variableGroup.setEnabled(state);
        this.lblTypeOfDesignLabel.setEnabled(state);
        if (!state) {
            this.design = 0;
            this.block = "NULL";
            this.rep = "NULL";
            this.row = "NULL";
            this.column = "NULL";
            this.lblRep.setEnabled(false);
            this.lblBlock.setEnabled(false);
            this.lblRow.setEnabled(false);
            this.lblCol.setEnabled(false);
            if (!this.txtRepVar.getText().isEmpty()) {
                this.dataManipulationManager.removeItemOfThenAddTo(this.txtRepVar, this.factorVarList);
            }
            if (!this.txtBlockVar.getText().isEmpty()) {
                this.dataManipulationManager.removeItemOfThenAddTo(this.txtBlockVar, this.factorVarList);
            }
            if (!this.txtRowVar.getText().isEmpty()) {
                this.dataManipulationManager.removeItemOfThenAddTo(this.txtRowVar, this.factorVarList);
            }
            if (!this.txtColVar.getText().isEmpty()) {
                this.dataManipulationManager.removeItemOfThenAddTo(this.txtColVar, this.factorVarList);
            }
        } else {
            switch (this.designType.getSelectionIndex()) {
                case 0: 
                case 1: {
                    this.lblBlock.setEnabled(state);
                    break;
                }
                case 4: {
                    this.lblBlock.setEnabled(state);
                    this.lblRep.setEnabled(state);
                    break;
                }
                case 2: 
                case 3: {
                    this.lblRep.setEnabled(state);
                    this.lblRow.setEnabled(state);
                    this.lblCol.setEnabled(state);
                }
            }
        }
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
        System.out.println("MAP: " + this.mapFileName);
        System.out.println("DATA: " + this.genotypicDataFileName);
        this.design = this.designType.getSelectionIndex();
        this.satisfiedAllConditions = true;
        if (this.responseVarList.getItemCount() < 1) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"Please add a response variable.");
        } else if (this.txtGenVar.getText().isEmpty()) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"Please add a genotype factor.");
        } else {
            if (this.btnRaw.getSelection()) {
                if (this.lblBlock.getEnabled() && this.txtBlockVar.getText().isEmpty()) {
                    MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"Please add a block factor.");
                    this.satisfiedAllConditions = false;
                } else if (this.lblRep.getEnabled() && this.txtRepVar.getText().isEmpty()) {
                    MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"Please add a replicate factor.");
                    this.satisfiedAllConditions = false;
                } else if (this.lblRow.getEnabled() && this.txtRowVar.getText().isEmpty()) {
                    MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"Please add a row factor.");
                    this.satisfiedAllConditions = false;
                } else if (this.lblCol.getEnabled() && this.txtColVar.getText().isEmpty()) {
                    MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"Please add a column factor.");
                    this.satisfiedAllConditions = false;
                }
            }
            if (this.satisfiedAllConditions) {
                if (this.lblBlock.getEnabled() && !this.txtBlockVar.getText().isEmpty()) {
                    this.block = this.txtBlockVar.getText();
                }
                if (this.lblRep.getEnabled() && !this.txtRepVar.getText().isEmpty()) {
                    this.rep = this.txtRepVar.getText();
                }
                if (this.lblRow.getEnabled() && !this.txtRowVar.getText().isEmpty()) {
                    this.row = this.txtRowVar.getText();
                }
                if (this.lblCol.getEnabled() && !this.txtColVar.getText().isEmpty()) {
                    this.column = this.txtColVar.getText();
                }
                this.heterozygousPresent = this.heterozygotesPresentBtn.getSelection();
                this.crossType = this.crossTypeSelection.getText();
                this.step = this.stepSpinner.getText();
                this.windowSize = this.windowSizeSpinner.getText();
                this.minDistance = this.minDistanceSpinner.getText();
                this.thresholdNumericValue = this.btnNumerical.getSelection() ? Double.toString(this.dataManipulationManager.convertInttoDouble(this.numericThresholdSpinner.getSelection(), this.numericThresholdSpinner.getDigits())) : "NULL";
                this.estimatePlotMarkerMap = this.btnEstimateAndPlot.getSelection();
                this.allelicDiffThreshold = this.dataManipulationManager.convertInttoDouble(this.allelicProportionsSpinner.getSelection(), this.allelicProportionsSpinner.getDigits());
                this.cutOffMissingData = this.dataManipulationManager.convertInttoDouble(this.cutOffSpinner.getSelection(), this.cutOffSpinner.getDigits());
                this.significanceLevelChiSquare = this.dataManipulationManager.convertInttoDouble(this.chiSquareSignificanceSpinner.getSelection(), this.chiSquareSignificanceSpinner.getDigits());
                this.traits = this.responseVarList.getItems();
                if (!this.txtEnvVar.getText().isEmpty()) {
                    this.environment = this.txtEnvVar.getText();
                    this.selectedEnvironmentLevel = this.envLevelsCombo.getText();
                }
                this.genotype = this.txtGenVar.getText();
                this.genotypicDataFileName = this.genotypicDataFileName.replaceAll("\\\\+", "/");
                this.mapFileName = this.mapFileName.replaceAll("\\\\+", "/");
                String dataFileName = this.file.getAbsolutePath().toString().replaceAll("\\\\+", "/");
                File outputFolder = PBToolAnalysisUtilities.createOutputFolder(this.file.getName(), this.analysisType);
                String newOutputFileName = String.valueOf(outputFolder.getAbsolutePath()) + "/output.txt";
                System.out.println("is unput raw data?" + this.isInputRawData);
                OperationProgressDialog rInfo = new OperationProgressDialog(this.getShell(), "QTL Analysis");
                rInfo.open();
                ProjectExplorerView.rJavaManager.getPbToolAnalysisManager().doQtl(dataFileName, this.isInputRawData, newOutputFileName.replaceAll("\\\\+", "/"), String.valueOf(outputFolder.getAbsolutePath().replaceAll("\\\\+", "/")) + "/", this.genotypicDataFileName, this.mapFileName, this.designIndex, this.traits, this.block, this.rep, this.row, this.column, this.genotype, this.environment, this.environmentLevels, this.selectedEnvironmentLevel, this.heterozygousPresent, this.crossType, this.step, this.windowSize, this.minDistance, this.qtlMethod, this.thresholdLiJi, this.thresholdNumericValue, this.estimatePlotMarkerMap, this.allelicDiffThreshold, this.cutOffMissingData, this.significanceLevelChiSquare);
                rInfo.close();
                WindowDialogControlUtil.hideAllWindowDialog();
                PBToolAnalysisUtilities.openFolder(outputFolder);
            }
        }
    }

    protected Point getInitialSize() {
        return new Point(658, 686);
    }

    protected boolean isResizable() {
        return true;
    }

    public class envVarValidator
    extends DialogListValidator {
        DataManipulationManager dataManipulationManager = new DataManipulationManager();
        List factorVarList;
        Boolean performMultiEnv;
        File file;
        Text txtEnvVar;
        Label lblEnvironment;

        public envVarValidator(List factorVarList, Boolean performMultiEnv, File file, Text envVarText, Label lblEnvironment) {
            this.factorVarList = factorVarList;
            this.performMultiEnv = performMultiEnv;
            this.file = file;
            this.txtEnvVar = envVarText;
            this.lblEnvironment = lblEnvironment;
        }

        @Override
        public boolean validate() {
            return this.txtEnvVar.getText().isEmpty();
        }

        @Override
        public boolean performAddProcess() {
            String[] selectedNumVars = this.factorVarList.getSelection();
            String[] selectedStrings = this.factorVarList.getSelection();
            DataManipulationManager.getEnvtLevels(selectedNumVars[0], this.file);
            QTLAnalysisDialog.this.environmentLevels = DataManipulationManager.getEnvtLevels(selectedStrings[0], this.file);
            QTLAnalysisDialog.this.envLevelsCombo.setEnabled(true);
            QTLAnalysisDialog.this.envLevelsCombo.setItems(QTLAnalysisDialog.this.environmentLevels);
            QTLAnalysisDialog.this.envLevelsCombo.add("[ALL]", 0);
            QTLAnalysisDialog.this.envLevelsCombo.select(0);
            return true;
        }

        @Override
        public boolean performRemoveProcess() {
            QTLAnalysisDialog.this.envLevelsCombo.setEnabled(false);
            QTLAnalysisDialog.this.envLevelsCombo.removeAll();
            return true;
        }
    }
}

