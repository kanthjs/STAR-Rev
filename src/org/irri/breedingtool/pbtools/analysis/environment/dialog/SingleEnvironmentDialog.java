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
import org.irri.breedingtool.pbtools.analysis.environment.dialog.SpecifyRowColDialog;
import org.irri.breedingtool.projectexplorer.view.ProjectExplorerView;
import org.irri.breedingtool.utility.DialogFormUtility;
import org.irri.breedingtool.utility.DialogListValidator;
import org.irri.breedingtool.utility.EnvVarValidator;
import org.irri.breedingtool.utility.PBToolAnalysisUtilities;
import org.irri.breedingtool.utility.TextVarValidator;
import org.irri.breedingtool.utility.WindowDialogControlUtil;

public class SingleEnvironmentDialog
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
    private Button excludeControlsBtn;
    private Button estimateGenotypeBtn;
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
    private Label lblOptionsForGenotype_1;
    private Button checkRandomBtn;
    private Label lblBlock;
    private Label lblRep;
    private Group variableGroup;
    private Label lblCol;
    private Label lblRow;
    private int design = 0;
    private String[] respvars = new String[0];
    private String environment = "NULL";
    private String[] environmentLevels = new String[0];
    private String genotype = "NULL";
    private String block = "NULL";
    private String rep = "NULL";
    private String row = "NULL";
    private String column = "NULL";
    private boolean descriptiveStat = false;
    private boolean varianceComponents = false;
    private boolean boxplotRawData = false;
    private boolean histogramRawData = false;
    private boolean heatmapResiduals = false;
    private String heatmapRow = null;
    private String heatmapColumn = null;
    private boolean diagnosticPlot = false;
    private boolean genotypeFixed = false;
    private boolean performPairwise = false;
    private String pairwiseAlpha = "0.05";
    private String[] genotypeLevels = new String[0];
    private String[] controlLevels = new String[0];
    private boolean compareControl = false;
    private boolean performAllPairwise = false;
    private boolean genotypeRandom = false;
    private boolean excludeControls = false;
    private boolean genoPhenoCorrelation = false;
    private Button checkBoxPlotBtn;
    private Button checkHistogramBtn;
    private Button checkHeatmapBtn;
    private Button checkDiagnosticPlotsBtn;
    private boolean satisfiedAllConditions = true;
    private boolean selectedHeatMapVars = false;
    private String analysisType;
    private Button specifyHeatMapVarsBtn;
    private Label lblEnv;
    private Label lblGen;
    private Text txtEnvVar;
    private Text txtGenVar;
    private Text txtBlockVar;
    private Text txtRepVar;
    private Text txtRowVar;
    private Text txtColVar;

    public SingleEnvironmentDialog(Shell parentShell, String analysisType, File file) {
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
                WindowDialogControlUtil.removeWindowDialogToList(SingleEnvironmentDialog.this.getShell());
            }
        });
        parent.getShell().setText("Single-Environment Analysis: " + this.dataManipulationManager.getDisplayFileName(this.file.getAbsolutePath()));
        Composite container = (Composite)super.createDialogArea(parent);
        TabFolder tabFolder = new TabFolder(container, 0);
        tabFolder.setBackground(SWTResourceManager.getColor(22));
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
        this.designType.setItems(new String[]{"Randomized Complete Block (RCB)", "Augmented RCB", "Augmented Latin Square", "Alpha-Lattice", "Row-Column", "Latinized Alpha-Lattice", "Latinized Row-Column"});
        this.designType.setLayoutData((Object)new GridData(4, 0x1000000, true, false, 3, 1));
        this.designType.select(0);
        this.designType.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                SingleEnvironmentDialog.this.enableFactorButtons(false);
                if (SingleEnvironmentDialog.this.designType.getSelectionIndex() == 0) {
                    SingleEnvironmentDialog.this.enableAugmentedOptions(false);
                    if (SingleEnvironmentDialog.this.checkPerformPairwiseBtn.getSelection()) {
                        SingleEnvironmentDialog.this.activateLevelOfConrolsOptions(true);
                    } else {
                        SingleEnvironmentDialog.this.activateLevelOfConrolsOptions(false);
                    }
                    SingleEnvironmentDialog.this.lblBlock.setEnabled(true);
                    SingleEnvironmentDialog.this.disable("replicate");
                    SingleEnvironmentDialog.this.disable("row");
                    SingleEnvironmentDialog.this.disable("column");
                } else if (SingleEnvironmentDialog.this.designType.getSelectionIndex() == 1) {
                    SingleEnvironmentDialog.this.enableAugmentedOptions(true);
                    if (SingleEnvironmentDialog.this.checkPerformPairwiseBtn.getSelection()) {
                        SingleEnvironmentDialog.this.activateLevelOfConrolsOptions(true);
                    } else {
                        SingleEnvironmentDialog.this.activateLevelOfConrolsOptions(false);
                    }
                    SingleEnvironmentDialog.this.lblBlock.setEnabled(true);
                    SingleEnvironmentDialog.this.disable("replicate");
                    SingleEnvironmentDialog.this.disable("row");
                    SingleEnvironmentDialog.this.disable("column");
                } else if (SingleEnvironmentDialog.this.designType.getSelectionIndex() == 2) {
                    SingleEnvironmentDialog.this.enableAugmentedOptions(true);
                    if (SingleEnvironmentDialog.this.checkPerformPairwiseBtn.getSelection()) {
                        SingleEnvironmentDialog.this.activateLevelOfConrolsOptions(true);
                    } else {
                        SingleEnvironmentDialog.this.activateLevelOfConrolsOptions(false);
                    }
                    SingleEnvironmentDialog.this.lblRow.setEnabled(true);
                    SingleEnvironmentDialog.this.lblCol.setEnabled(true);
                    SingleEnvironmentDialog.this.disable("replicate");
                    SingleEnvironmentDialog.this.disable("block");
                } else if (SingleEnvironmentDialog.this.designType.getSelectionIndex() == 3) {
                    SingleEnvironmentDialog.this.enableAugmentedOptions(false);
                    if (SingleEnvironmentDialog.this.checkPerformPairwiseBtn.getSelection()) {
                        SingleEnvironmentDialog.this.activateLevelOfConrolsOptions(true);
                    } else {
                        SingleEnvironmentDialog.this.activateLevelOfConrolsOptions(false);
                    }
                    SingleEnvironmentDialog.this.lblRep.setEnabled(true);
                    SingleEnvironmentDialog.this.lblBlock.setEnabled(true);
                    SingleEnvironmentDialog.this.disable("row");
                    SingleEnvironmentDialog.this.disable("column");
                } else if (SingleEnvironmentDialog.this.designType.getSelectionIndex() == 4) {
                    SingleEnvironmentDialog.this.enableAugmentedOptions(false);
                    if (SingleEnvironmentDialog.this.checkPerformPairwiseBtn.getSelection()) {
                        SingleEnvironmentDialog.this.activateLevelOfConrolsOptions(true);
                    } else {
                        SingleEnvironmentDialog.this.activateLevelOfConrolsOptions(false);
                    }
                    SingleEnvironmentDialog.this.lblRow.setEnabled(true);
                    SingleEnvironmentDialog.this.lblCol.setEnabled(true);
                    SingleEnvironmentDialog.this.lblRep.setEnabled(true);
                    SingleEnvironmentDialog.this.disable("block");
                } else if (SingleEnvironmentDialog.this.designType.getSelectionIndex() == 5) {
                    SingleEnvironmentDialog.this.enableAugmentedOptions(false);
                    if (SingleEnvironmentDialog.this.checkPerformPairwiseBtn.getSelection()) {
                        SingleEnvironmentDialog.this.activateLevelOfConrolsOptions(true);
                    } else {
                        SingleEnvironmentDialog.this.activateLevelOfConrolsOptions(false);
                    }
                    SingleEnvironmentDialog.this.lblRep.setEnabled(true);
                    SingleEnvironmentDialog.this.lblBlock.setEnabled(true);
                    SingleEnvironmentDialog.this.disable("row");
                    SingleEnvironmentDialog.this.disable("column");
                } else if (SingleEnvironmentDialog.this.designType.getSelectionIndex() == 6) {
                    SingleEnvironmentDialog.this.enableAugmentedOptions(false);
                    if (SingleEnvironmentDialog.this.checkPerformPairwiseBtn.getSelection()) {
                        SingleEnvironmentDialog.this.activateLevelOfConrolsOptions(true);
                    } else {
                        SingleEnvironmentDialog.this.activateLevelOfConrolsOptions(false);
                    }
                    SingleEnvironmentDialog.this.lblRow.setEnabled(true);
                    SingleEnvironmentDialog.this.lblCol.setEnabled(true);
                    SingleEnvironmentDialog.this.lblRep.setEnabled(true);
                    SingleEnvironmentDialog.this.disable("block");
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
        gd_numVarList.widthHint = 126;
        this.numVarList.setLayoutData((Object)gd_numVarList);
        this.numVarList.setItems(this.numericVariables);
        this.numVarList.addMouseListener(new MouseListener(){

            public void mouseDoubleClick(MouseEvent e) {
                SingleEnvironmentDialog.this.numVarList.getSelection();
                SingleEnvironmentDialog.this.dataManipulationManager.moveSelectedStringFromTo(SingleEnvironmentDialog.this.numVarList, SingleEnvironmentDialog.this.responseVarList);
                SingleEnvironmentDialog.this.numVarList.remove(SingleEnvironmentDialog.this.numVarList.getSelectionIndices());
                if (SingleEnvironmentDialog.this.responseVarList.getItemCount() < 2) {
                    SingleEnvironmentDialog.this.estimateGenotypeBtn.setEnabled(false);
                }
                SingleEnvironmentDialog.this.estimateGenotypeBtn.setSelection(false);
                if (SingleEnvironmentDialog.this.checkRandomBtn.getSelection()) {
                    SingleEnvironmentDialog.this.activateRandomOptions(true);
                }
                if (SingleEnvironmentDialog.this.designType.getSelectionIndex() == 1 || SingleEnvironmentDialog.this.designType.getSelectionIndex() == 2) {
                    SingleEnvironmentDialog.this.lblOptionsForGenotype_1.setEnabled(false);
                    SingleEnvironmentDialog.this.excludeControlsBtn.setEnabled(false);
                    if (SingleEnvironmentDialog.this.responseVarList.getItemCount() > 1) {
                        SingleEnvironmentDialog.this.lblOptionsForGenotype_1.setEnabled(true);
                        SingleEnvironmentDialog.this.estimateGenotypeBtn.setEnabled(true);
                    }
                }
            }

            public void mouseDown(MouseEvent e) {
                if (SingleEnvironmentDialog.this.numVarList.getSelectionCount() > 0) {
                    SingleEnvironmentDialog.this.moveBtn.setEnabled(true);
                    SingleEnvironmentDialog.this.factorVarList.setSelection(-1);
                    SingleEnvironmentDialog.this.responseVarList.setSelection(-1);
                    SingleEnvironmentDialog.this.addBtn.setText("Add");
                    SingleEnvironmentDialog.this.moveBtn.setText("Set to Factor");
                    SingleEnvironmentDialog.this.enableFactorButtons(false);
                    SingleEnvironmentDialog.this.enableNumericButtons(true);
                }
            }

            public void mouseUp(MouseEvent e) {
            }
        });
        this.addBtn = new Button(modelComposite, 0);
        this.addBtn.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (SingleEnvironmentDialog.this.numVarList.getSelectionCount() > 0) {
                    String[] selectedNumVars = SingleEnvironmentDialog.this.numVarList.getSelection();
                    int i = 0;
                    while (i < selectedNumVars.length) {
                        SingleEnvironmentDialog.this.responseVarList.add(selectedNumVars[i]);
                        ++i;
                    }
                    SingleEnvironmentDialog.this.numVarList.remove(SingleEnvironmentDialog.this.numVarList.getSelectionIndices());
                } else {
                    String[] selectedNumVars = SingleEnvironmentDialog.this.responseVarList.getSelection();
                    int i = 0;
                    while (i < selectedNumVars.length) {
                        SingleEnvironmentDialog.this.numVarList.add(selectedNumVars[i]);
                        ++i;
                    }
                    SingleEnvironmentDialog.this.responseVarList.remove(SingleEnvironmentDialog.this.responseVarList.getSelectionIndices());
                    SingleEnvironmentDialog.this.resetHeatMap();
                }
                if (SingleEnvironmentDialog.this.responseVarList.getItemCount() < 2) {
                    SingleEnvironmentDialog.this.estimateGenotypeBtn.setEnabled(false);
                }
                SingleEnvironmentDialog.this.estimateGenotypeBtn.setSelection(false);
                SingleEnvironmentDialog.this.enableNumericButtons(false);
                if (SingleEnvironmentDialog.this.checkRandomBtn.getSelection()) {
                    SingleEnvironmentDialog.this.activateRandomOptions(true);
                }
                if (SingleEnvironmentDialog.this.designType.getSelectionIndex() == 1 || SingleEnvironmentDialog.this.designType.getSelectionIndex() == 2) {
                    SingleEnvironmentDialog.this.lblOptionsForGenotype_1.setEnabled(false);
                    SingleEnvironmentDialog.this.excludeControlsBtn.setEnabled(false);
                    if (SingleEnvironmentDialog.this.responseVarList.getItemCount() > 1) {
                        SingleEnvironmentDialog.this.lblOptionsForGenotype_1.setEnabled(true);
                        SingleEnvironmentDialog.this.estimateGenotypeBtn.setEnabled(true);
                    }
                }
                SingleEnvironmentDialog.this.moveBtn.setEnabled(false);
            }
        });
        this.addBtn.setEnabled(false);
        GridData gd_addBtn = new GridData(16384, 0x1000000, false, false, 2, 1);
        gd_addBtn.widthHint = 52;
        this.addBtn.setLayoutData((Object)gd_addBtn);
        this.addBtn.setText("Add");
        this.responseVarList = new List(modelComposite, 2818);
        GridData gd_responseVarList = new GridData(4, 4, true, false, 1, 1);
        gd_responseVarList.widthHint = 111;
        this.responseVarList.setLayoutData((Object)gd_responseVarList);
        this.responseVarList.addMouseListener(new MouseListener(){

            public void mouseDoubleClick(MouseEvent e) {
                SingleEnvironmentDialog.this.moveBtn.setEnabled(false);
                SingleEnvironmentDialog.this.dataManipulationManager.moveSelectedStringFromTo(SingleEnvironmentDialog.this.responseVarList, SingleEnvironmentDialog.this.numVarList);
                SingleEnvironmentDialog.this.responseVarList.remove(SingleEnvironmentDialog.this.responseVarList.getSelectionIndices());
                if (SingleEnvironmentDialog.this.responseVarList.getItemCount() < 2) {
                    SingleEnvironmentDialog.this.estimateGenotypeBtn.setEnabled(false);
                }
                SingleEnvironmentDialog.this.estimateGenotypeBtn.setSelection(false);
                if (SingleEnvironmentDialog.this.checkRandomBtn.getSelection()) {
                    SingleEnvironmentDialog.this.activateRandomOptions(true);
                }
                if (SingleEnvironmentDialog.this.designType.getSelectionIndex() == 1 || SingleEnvironmentDialog.this.designType.getSelectionIndex() == 2) {
                    SingleEnvironmentDialog.this.lblOptionsForGenotype_1.setEnabled(false);
                    SingleEnvironmentDialog.this.excludeControlsBtn.setEnabled(false);
                    if (SingleEnvironmentDialog.this.responseVarList.getItemCount() > 1) {
                        SingleEnvironmentDialog.this.lblOptionsForGenotype_1.setEnabled(true);
                        SingleEnvironmentDialog.this.estimateGenotypeBtn.setEnabled(true);
                    }
                }
                SingleEnvironmentDialog.this.moveBtn.setEnabled(false);
            }

            public void mouseDown(MouseEvent e) {
                if (SingleEnvironmentDialog.this.responseVarList.getSelectionCount() > 0) {
                    SingleEnvironmentDialog.this.numVarList.setSelection(-1);
                    SingleEnvironmentDialog.this.addBtn.setText("Remove");
                    SingleEnvironmentDialog.this.addBtn.setEnabled(true);
                    SingleEnvironmentDialog.this.moveBtn.setEnabled(false);
                    SingleEnvironmentDialog.this.enableFactorButtons(false);
                }
            }

            public void mouseUp(MouseEvent e) {
            }
        });
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
        this.factorVarList.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (SingleEnvironmentDialog.this.factorVarList.getSelectionCount() > 0) {
                    SingleEnvironmentDialog.this.enableFactorButtons(true);
                    SingleEnvironmentDialog.this.enableNumericButtons(false);
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
        this.lblGen.setText("Genotype:");
        this.txtGenVar = new Text((Composite)group, 2048);
        this.txtGenVar.setEditable(false);
        this.txtGenVar.setBackground(SWTResourceManager.getColor(1));
        this.txtGenVar.setLayoutData((Object)new GridData(4, 4, true, false, 1, 1));
        this.txtGenVar.addMouseListener(new MouseListener(){

            public void mouseDoubleClick(MouseEvent e) {
                SingleEnvironmentDialog.this.genLevelsList.removeAll();
                SingleEnvironmentDialog.this.controlsList.removeAll();
                SingleEnvironmentDialog.this.resetGenotypeControls();
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
                if (SingleEnvironmentDialog.this.checkFixedBtn.getSelection()) {
                    SingleEnvironmentDialog.this.activateFixedOptions(true);
                    if (SingleEnvironmentDialog.this.compareWithControlsBtn.getSelection() && SingleEnvironmentDialog.this.checkPerformPairwiseBtn.getSelection()) {
                        SingleEnvironmentDialog.this.activateLevelOfConrolsOptions(true);
                    }
                } else {
                    SingleEnvironmentDialog.this.activateFixedOptions(false);
                    if (!SingleEnvironmentDialog.this.excludeControlsBtn.getSelection() || !SingleEnvironmentDialog.this.excludeControlsBtn.getEnabled()) {
                        SingleEnvironmentDialog.this.activateLevelOfConrolsOptions(false);
                    } else if (SingleEnvironmentDialog.this.designType.getSelectionIndex() == 1 || SingleEnvironmentDialog.this.designType.getSelectionIndex() == 2) {
                        SingleEnvironmentDialog.this.activateLevelOfConrolsOptions(false);
                    }
                }
            }
        });
        this.checkFixedBtn.setSelection(true);
        this.checkFixedBtn.setLayoutData((Object)new GridData(16384, 0x1000000, true, false, 1, 1));
        this.checkFixedBtn.setText("Fixed");
        this.checkRandomBtn = new Button((Composite)group, 32);
        this.checkRandomBtn.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (SingleEnvironmentDialog.this.checkRandomBtn.getSelection()) {
                    SingleEnvironmentDialog.this.activateRandomOptions(true);
                    SingleEnvironmentDialog.this.activateLevelOfConrolsOptions(false);
                    if (SingleEnvironmentDialog.this.excludeControlsBtn.getSelection()) {
                        SingleEnvironmentDialog.this.activateLevelOfConrolsOptions(true);
                    }
                    if (SingleEnvironmentDialog.this.designType.getSelectionIndex() == 1 || SingleEnvironmentDialog.this.designType.getSelectionIndex() == 2) {
                        SingleEnvironmentDialog.this.enableAugmentedOptions(true);
                    }
                } else {
                    SingleEnvironmentDialog.this.activateRandomOptions(false);
                    if (!SingleEnvironmentDialog.this.compareWithControlsBtn.getSelection() || !SingleEnvironmentDialog.this.compareWithControlsBtn.getEnabled()) {
                        SingleEnvironmentDialog.this.activateLevelOfConrolsOptions(false);
                    }
                }
            }
        });
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
        Composite composite_1 = new Composite((Composite)tabFolder, 0);
        this.tbtmOptions.setControl((Control)composite_1);
        composite_1.setBackground(this.getShell().getBackground());
        composite_1.setLayout((Layout)new GridLayout(6, false));
        this.lblOptionsForGenotype = new Label(composite_1, 0);
        GridData gd_lblOptionsForGenotype = new GridData(16384, 0x1000000, false, false, 4, 1);
        gd_lblOptionsForGenotype.heightHint = 21;
        gd_lblOptionsForGenotype.widthHint = 223;
        this.lblOptionsForGenotype.setLayoutData((Object)gd_lblOptionsForGenotype);
        this.lblOptionsForGenotype.setText("Options for Genotype as Fixed:");
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        this.checkPerformPairwiseBtn = new Button(composite_1, 32);
        this.checkPerformPairwiseBtn.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (SingleEnvironmentDialog.this.checkPerformPairwiseBtn.getSelection()) {
                    SingleEnvironmentDialog.this.activatePerformPairwiseOptions(true);
                    if (SingleEnvironmentDialog.this.compareWithControlsBtn.getSelection()) {
                        SingleEnvironmentDialog.this.activateLevelOfConrolsOptions(true);
                    }
                } else {
                    SingleEnvironmentDialog.this.activatePerformPairwiseOptions(false);
                    if (!SingleEnvironmentDialog.this.excludeControlsBtn.getSelection() || !SingleEnvironmentDialog.this.excludeControlsBtn.getEnabled()) {
                        SingleEnvironmentDialog.this.activateLevelOfConrolsOptions(false);
                    } else if (SingleEnvironmentDialog.this.designType.getSelectionIndex() == 1 || SingleEnvironmentDialog.this.designType.getSelectionIndex() == 2) {
                        SingleEnvironmentDialog.this.activateLevelOfConrolsOptions(false);
                    }
                }
            }
        });
        this.checkPerformPairwiseBtn.setLayoutData((Object)new GridData(16384, 0x1000000, false, false, 5, 1));
        this.checkPerformPairwiseBtn.setText("Perform pairwise mean comparisons");
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        this.compareWithControlsBtn = new Button(composite_1, 16);
        this.compareWithControlsBtn.setEnabled(false);
        this.compareWithControlsBtn.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (SingleEnvironmentDialog.this.compareWithControlsBtn.getSelection()) {
                    SingleEnvironmentDialog.this.activateLevelOfConrolsOptions(true);
                } else if (!SingleEnvironmentDialog.this.excludeControlsBtn.getSelection() || !SingleEnvironmentDialog.this.excludeControlsBtn.getEnabled()) {
                    SingleEnvironmentDialog.this.activateLevelOfConrolsOptions(false);
                } else if (SingleEnvironmentDialog.this.designType.getSelectionIndex() == 1 || SingleEnvironmentDialog.this.designType.getSelectionIndex() == 2) {
                    SingleEnvironmentDialog.this.activateLevelOfConrolsOptions(false);
                }
            }
        });
        this.compareWithControlsBtn.setLayoutData((Object)new GridData(16384, 0x1000000, true, false, 4, 1));
        this.compareWithControlsBtn.setText("Compare with control(s)");
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        this.performAllComparisonsBtn = new Button(composite_1, 16);
        this.performAllComparisonsBtn.setEnabled(false);
        this.performAllComparisonsBtn.setLayoutData((Object)new GridData(16384, 0x1000000, true, false, 4, 1));
        this.performAllComparisonsBtn.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (!(!SingleEnvironmentDialog.this.performAllComparisonsBtn.getSelection() || SingleEnvironmentDialog.this.excludeControlsBtn.getSelection() && SingleEnvironmentDialog.this.excludeControlsBtn.getEnabled())) {
                    SingleEnvironmentDialog.this.activateLevelOfConrolsOptions(false);
                }
            }
        });
        this.performAllComparisonsBtn.setText("Perform all comparisons");
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        this.lblLevelOfSignificance = new Label(composite_1, 0);
        this.lblLevelOfSignificance.setEnabled(false);
        this.lblLevelOfSignificance.setLayoutData((Object)new GridData(131072, 0x1000000, true, false, 2, 1));
        this.lblLevelOfSignificance.setText("Level of Significance:");
        this.significanceLevel = new Text(composite_1, 2048);
        this.significanceLevel.setEnabled(false);
        this.significanceLevel.setText("0.05");
        GridData gd_significanceLevel = new GridData(16384, 0x1000000, true, false, 1, 1);
        gd_significanceLevel.widthHint = 39;
        this.significanceLevel.setLayoutData((Object)gd_significanceLevel);
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        this.lblOptionsForGenotype_1 = new Label(composite_1, 0);
        this.lblOptionsForGenotype_1.setEnabled(false);
        this.lblOptionsForGenotype_1.setLayoutData((Object)new GridData(16384, 0x1000000, false, false, 6, 1));
        this.lblOptionsForGenotype_1.setText("Options for Genotype as Random:");
        new Label(composite_1, 0);
        this.excludeControlsBtn = new Button(composite_1, 32);
        this.excludeControlsBtn.setEnabled(false);
        this.excludeControlsBtn.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (SingleEnvironmentDialog.this.excludeControlsBtn.getSelection()) {
                    if (SingleEnvironmentDialog.this.designType.getSelectionIndex() != 1 && SingleEnvironmentDialog.this.designType.getSelectionIndex() != 2) {
                        SingleEnvironmentDialog.this.activateLevelOfConrolsOptions(true);
                    }
                } else if (SingleEnvironmentDialog.this.checkPerformPairwiseBtn.getSelection() && SingleEnvironmentDialog.this.checkPerformPairwiseBtn.getEnabled()) {
                    if (!SingleEnvironmentDialog.this.compareWithControlsBtn.getSelection() || !SingleEnvironmentDialog.this.compareWithControlsBtn.getEnabled()) {
                        SingleEnvironmentDialog.this.activateLevelOfConrolsOptions(false);
                    }
                } else {
                    SingleEnvironmentDialog.this.activateLevelOfConrolsOptions(false);
                }
            }
        });
        this.excludeControlsBtn.setLayoutData((Object)new GridData(16384, 0x1000000, false, false, 5, 1));
        this.excludeControlsBtn.setText("Exclude controls in the estimation of genotypic variance");
        new Label(composite_1, 0);
        this.estimateGenotypeBtn = new Button(composite_1, 32);
        this.estimateGenotypeBtn.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
            }
        });
        this.estimateGenotypeBtn.setEnabled(false);
        this.estimateGenotypeBtn.setLayoutData((Object)new GridData(16384, 0x1000000, false, false, 5, 1));
        this.estimateGenotypeBtn.setText("Estimate genotypic and phenotypic correlations");
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        this.lblSpecifyControls = new Label(composite_1, 0);
        this.lblSpecifyControls.setEnabled(false);
        this.lblSpecifyControls.setLayoutData((Object)new GridData(16384, 0x1000000, false, false, 6, 1));
        this.lblSpecifyControls.setText("Specify Control(s)");
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        this.levelsOfGenotypeLbl = new Label(composite_1, 0);
        this.levelsOfGenotypeLbl.setEnabled(false);
        this.levelsOfGenotypeLbl.setLayoutData((Object)new GridData(16384, 0x1000000, true, false, 2, 1));
        this.levelsOfGenotypeLbl.setText("Levels of Genotype:");
        this.controlsLbl = new Label(composite_1, 0);
        this.controlsLbl.setLayoutData((Object)new GridData(16384, 0x1000000, true, false, 1, 1));
        this.controlsLbl.setEnabled(false);
        this.controlsLbl.setText("Control(s):");
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        this.genLevelsList = new List(composite_1, 2818);
        this.genLevelsList.setEnabled(false);
        GridData gd_genLevelsList = new GridData(4, 4, true, true, 1, 1);
        gd_genLevelsList.widthHint = 128;
        this.genLevelsList.setLayoutData((Object)gd_genLevelsList);
        this.genLevelsList.addMouseListener(new MouseListener(){

            public void mouseDoubleClick(MouseEvent e) {
                SingleEnvironmentDialog.this.dataManipulationManager.moveSelectedStringFromTo(SingleEnvironmentDialog.this.genLevelsList, SingleEnvironmentDialog.this.controlsList);
                SingleEnvironmentDialog.this.addControlsBtn.setEnabled(false);
            }

            public void mouseDown(MouseEvent e) {
                if (SingleEnvironmentDialog.this.genLevelsList.getSelectionIndex() > -1) {
                    SingleEnvironmentDialog.this.addControlsBtn.setEnabled(true);
                    SingleEnvironmentDialog.this.controlsList.setSelection(-1);
                    SingleEnvironmentDialog.this.genLevelsList.getSelection();
                    SingleEnvironmentDialog.this.addControlsBtn.setText("Add");
                    SingleEnvironmentDialog.this.addControlsBtn.setEnabled(true);
                }
            }

            public void mouseUp(MouseEvent e) {
            }
        });
        this.addControlsBtn = new Button(composite_1, 0);
        this.addControlsBtn.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (SingleEnvironmentDialog.this.genLevelsList.getSelectionCount() > 0) {
                    String[] selectedNumVars = SingleEnvironmentDialog.this.genLevelsList.getSelection();
                    int i = 0;
                    while (i < selectedNumVars.length) {
                        SingleEnvironmentDialog.this.controlsList.add(selectedNumVars[i]);
                        ++i;
                    }
                    SingleEnvironmentDialog.this.genLevelsList.remove(SingleEnvironmentDialog.this.genLevelsList.getSelectionIndices());
                    SingleEnvironmentDialog.this.addControlsBtn.setEnabled(false);
                } else {
                    String[] selectedNumVars = SingleEnvironmentDialog.this.controlsList.getSelection();
                    int i = 0;
                    while (i < selectedNumVars.length) {
                        SingleEnvironmentDialog.this.genLevelsList.add(selectedNumVars[i]);
                        ++i;
                    }
                    SingleEnvironmentDialog.this.controlsList.remove(SingleEnvironmentDialog.this.controlsList.getSelectionIndices());
                }
                SingleEnvironmentDialog.this.addControlsBtn.setEnabled(false);
            }
        });
        this.addControlsBtn.setEnabled(false);
        GridData gd_addControlsBtn = new GridData(4, 0x1000000, false, false, 1, 1);
        gd_addControlsBtn.widthHint = 71;
        this.addControlsBtn.setLayoutData((Object)gd_addControlsBtn);
        this.addControlsBtn.setText("Add");
        this.controlsList = new List(composite_1, 2818);
        this.controlsList.setEnabled(false);
        GridData gd_controlsList = new GridData(4, 4, true, true, 1, 1);
        gd_controlsList.widthHint = 128;
        this.controlsList.setLayoutData((Object)gd_controlsList);
        this.controlsList.addMouseListener(new MouseListener(){

            public void mouseDoubleClick(MouseEvent e) {
                SingleEnvironmentDialog.this.dataManipulationManager.moveSelectedStringFromTo(SingleEnvironmentDialog.this.controlsList, SingleEnvironmentDialog.this.genLevelsList);
                SingleEnvironmentDialog.this.addControlsBtn.setEnabled(false);
            }

            public void mouseDown(MouseEvent e) {
                if (SingleEnvironmentDialog.this.controlsList.getSelectionIndex() > -1) {
                    SingleEnvironmentDialog.this.addControlsBtn.setEnabled(true);
                    SingleEnvironmentDialog.this.genLevelsList.setSelection(-1);
                    SingleEnvironmentDialog.this.controlsList.getSelection();
                    SingleEnvironmentDialog.this.addControlsBtn.setText("Remove");
                    SingleEnvironmentDialog.this.addControlsBtn.setEnabled(true);
                }
            }

            public void mouseUp(MouseEvent e) {
            }
        });
        new Label(composite_1, 0);
        Label lblNewLabel_3 = new Label(composite_1, 0);
        lblNewLabel_3.setLayoutData((Object)new GridData(16384, 0x1000000, false, false, 6, 1));
        lblNewLabel_3.setText("Display:");
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        this.checkDescriptiveStatistics = new Button(composite_1, 32);
        this.checkDescriptiveStatistics.setLayoutData((Object)new GridData(16384, 0x1000000, true, false, 4, 1));
        this.checkDescriptiveStatistics.setText("Descriptive Statistics");
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        this.checkVarianceComponents = new Button(composite_1, 32);
        this.checkVarianceComponents.setLayoutData((Object)new GridData(16384, 0x1000000, true, false, 4, 1));
        this.checkVarianceComponents.setText("Variance Components");
        TabItem tbtmGraph_1 = new TabItem(tabFolder, 0);
        tbtmGraph_1.setText("Graph");
        Composite composite = new Composite((Composite)tabFolder, 0);
        tbtmGraph_1.setControl((Control)composite);
        composite.setBackground(this.getShell().getBackground());
        composite.setLayout((Layout)new GridLayout(3, false));
        Label lblOutputGraph = new Label(composite, 0);
        lblOutputGraph.setLayoutData((Object)new GridData(16384, 0x1000000, true, false, 2, 1));
        lblOutputGraph.setText("Output Graph:");
        new Label(composite, 0);
        new Label(composite, 0);
        this.checkBoxPlotBtn = new Button(composite, 32);
        this.checkBoxPlotBtn.setLayoutData((Object)new GridData(16384, 0x1000000, true, false, 1, 2));
        this.checkBoxPlotBtn.setText("Boxplot of the raw data");
        new Label(composite, 0);
        new Label(composite, 0);
        new Label(composite, 0);
        new Label(composite, 0);
        this.checkHistogramBtn = new Button(composite, 32);
        GridData gd_checkHistogramBtn = new GridData(16384, 0x1000000, true, false, 1, 1);
        gd_checkHistogramBtn.widthHint = 156;
        this.checkHistogramBtn.setLayoutData((Object)gd_checkHistogramBtn);
        this.checkHistogramBtn.setText("Histogram of the raw data");
        new Label(composite, 0);
        new Label(composite, 0);
        this.checkHeatmapBtn = new Button(composite, 32);
        this.checkHeatmapBtn.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (!SingleEnvironmentDialog.this.checkHeatmapBtn.getSelection()) {
                    SingleEnvironmentDialog.this.specifyHeatMapVarsBtn.setEnabled(false);
                    SingleEnvironmentDialog.this.resetHeatMap();
                } else {
                    SingleEnvironmentDialog.this.specifyHeatMapVarsBtn.setEnabled(true);
                }
            }
        });
        this.checkHeatmapBtn.setLayoutData((Object)new GridData(16384, 0x1000000, true, false, 1, 2));
        this.checkHeatmapBtn.setText("Heatmap of the residuals");
        this.specifyHeatMapVarsBtn = new Button(composite, 0);
        this.specifyHeatMapVarsBtn.setEnabled(false);
        this.specifyHeatMapVarsBtn.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                SpecifyRowColDialog rowColDialog = new SpecifyRowColDialog(SingleEnvironmentDialog.this.getShell(), SingleEnvironmentDialog.this.dataManipulationManager.getListItems(SingleEnvironmentDialog.this.varInfo));
                rowColDialog.open();
                if (rowColDialog.getReturnCode() == 0) {
                    SingleEnvironmentDialog.this.heatmapRow = rowColDialog.getHeatRow();
                    SingleEnvironmentDialog.this.heatmapColumn = rowColDialog.getHeatColumn();
                    SingleEnvironmentDialog.this.selectedHeatMapVars = true;
                } else {
                    SingleEnvironmentDialog.this.selectedHeatMapVars = false;
                }
            }
        });
        GridData gd_specifyHeatMapVarsBtn = new GridData(16384, 0x1000000, true, false, 1, 2);
        gd_specifyHeatMapVarsBtn.widthHint = 157;
        this.specifyHeatMapVarsBtn.setLayoutData((Object)gd_specifyHeatMapVarsBtn);
        this.specifyHeatMapVarsBtn.setText("Specify Field Row/Column");
        new Label(composite, 0);
        new Label(composite, 0);
        this.checkDiagnosticPlotsBtn = new Button(composite, 32);
        this.checkDiagnosticPlotsBtn.setLayoutData((Object)new GridData(16384, 0x1000000, true, false, 1, 1));
        this.checkDiagnosticPlotsBtn.setText("Diagnostic plots");
        new Label(composite, 0);
        this.initializeForm();
        return container;
    }

    protected void enableAugmentedOptions(boolean state) {
        if (this.checkRandomBtn.getSelection()) {
            this.lblOptionsForGenotype_1.setEnabled(true);
            this.excludeControlsBtn.setEnabled(true);
            this.excludeControlsBtn.setSelection(true);
            if (this.responseVarList.getItemCount() > 1) {
                this.estimateGenotypeBtn.setEnabled(true);
            }
            this.activateLevelOfConrolsOptions(!state);
            if (!state) {
                this.excludeControlsBtn.setSelection(false);
                this.activateLevelOfConrolsOptions(false);
            }
        }
    }

    void initializeForm() {
        this.dlgManager.initializeVariableMoveList(this.numVarList, this.factorVarList, this.moveBtn, this.file.getAbsolutePath());
        this.dlgManager.initializeSingleSelectionList(this.factorVarList, this.txtEnvVar, this.moveBtn, this.btnAddEnv, new EnvVarValidator(this.factorVarList, true, this.file, this.txtEnvVar, this.lblEnv));
        this.dlgManager.initializeSingleSelectionList(this.factorVarList, this.txtGenVar, this.moveBtn, this.btnAddGen, new genotypeVarValidator());
        this.dlgManager.initializeSingleSelectionList(this.factorVarList, this.txtBlockVar, this.moveBtn, this.btnAddBlock, new TextVarValidator(this.txtBlockVar, this.lblBlock));
        this.dlgManager.initializeSingleSelectionList(this.factorVarList, this.txtRepVar, this.moveBtn, this.btnAddRep, new TextVarValidator(this.txtRepVar, this.lblRep));
        this.dlgManager.initializeSingleSelectionList(this.factorVarList, this.txtRowVar, this.moveBtn, this.btnAddRow, new TextVarValidator(this.txtRowVar, this.lblRow));
        this.dlgManager.initializeSingleSelectionList(this.factorVarList, this.txtColVar, this.moveBtn, this.btnAddCol, new TextVarValidator(this.txtColVar, this.lblCol));
        this.dlgManager.initializeSingleButtonList(this.numVarList, this.responseVarList, this.moveBtn, this.addBtn);
    }

    protected void resetGenotypeControls() {
        this.genLevelsList.removeAll();
        this.controlsList.removeAll();
    }

    protected void resetHeatMap() {
        this.heatmapRow = null;
        this.heatmapColumn = null;
        this.selectedHeatMapVars = false;
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
        this.activatePerformPairwiseOptions(state);
        if (!state && !this.excludeControlsBtn.getSelection()) {
            this.activateLevelOfConrolsOptions(false);
        }
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
                if (this.performAllComparisonsBtn.getSelection()) {
                    this.compareWithControlsBtn.setSelection(false);
                }
            }
        }
    }

    protected void activateRandomOptions(boolean state) {
        this.lblOptionsForGenotype_1.setEnabled(state);
        this.excludeControlsBtn.setEnabled(state);
        if (this.responseVarList.getItemCount() > 1) {
            this.estimateGenotypeBtn.setEnabled(state);
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
    }

    protected void createButtonsForButtonBar(Composite parent) {
        this.createButton(parent, 0, IDialogConstants.OK_LABEL, true);
        this.createButton(parent, 1, IDialogConstants.CANCEL_LABEL, false);
    }

    protected void okPressed() {
        this.genotypeFixed = this.checkFixedBtn.getSelection();
        this.genotypeRandom = this.checkRandomBtn.getSelection();
        this.design = this.designType.getSelectionIndex();
        this.environment = "NULL";
        this.descriptiveStat = this.checkDescriptiveStatistics.getSelection();
        this.varianceComponents = this.checkVarianceComponents.getSelection();
        this.boxplotRawData = this.checkBoxPlotBtn.getSelection();
        this.histogramRawData = this.checkHistogramBtn.getSelection();
        this.heatmapResiduals = this.checkHeatmapBtn.getSelection();
        this.diagnosticPlot = this.checkDiagnosticPlotsBtn.getSelection();
        this.satisfiedAllConditions = true;
        if (this.responseVarList.getItemCount() < 1) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"Please add a response variable.");
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
        } else if (this.heatmapResiduals && !this.selectedHeatMapVars) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"Please specify the heatmap rows/colums.");
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
                            if (this.compareControl) {
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
            if (this.genotypeRandom) {
                this.excludeControls = this.excludeControlsBtn.getSelection();
                this.genoPhenoCorrelation = this.estimateGenotypeBtn.getSelection();
                if (!(!this.excludeControls || this.compareWithControlsBtn.getSelection() && this.compareWithControlsBtn.getEnabled())) {
                    this.controlLevels = this.controlsList.getItems();
                    if (this.controlLevels.length < 1 && this.designType.getSelectionIndex() != 1 && this.designType.getSelectionIndex() != 2) {
                        MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"Please specify control variables from the genotype levels.");
                        this.satisfiedAllConditions = false;
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
                this.environment = this.txtEnvVar.getText().isEmpty() ? "NULL" : this.txtEnvVar.getText();
                this.genotype = this.txtGenVar.getText();
                String selectedStrings = this.txtEnvVar.getText();
                this.environmentLevels = DataManipulationManager.getEnvtLevels(selectedStrings, this.file);
                String selectedStrings2 = this.txtGenVar.getText();
                this.genotypeLevels = DataManipulationManager.getEnvtLevels(selectedStrings2, this.file);
                String dataFileName = this.file.getAbsolutePath().toString().replaceAll("\\\\+", "/");
                File outputFolder = PBToolAnalysisUtilities.createOutputFolder(this.file.getName(), this.analysisType);
                String newOutputFileName = String.valueOf(outputFolder.getAbsolutePath()) + "/output.txt";
                OperationProgressDialog rInfo = new OperationProgressDialog(this.getShell(), "Single Environment Analysis");
                rInfo.open();
                ProjectExplorerView.rJavaManager.getPbToolAnalysisManager().doSingleEnvironmentAnalysis(dataFileName, newOutputFileName.replaceAll("\\\\+", "/"), String.valueOf(outputFolder.getAbsolutePath().replaceAll("\\\\+", "/")) + "/", this.design, this.respvars, this.environment, this.environmentLevels, this.genotype, this.block, this.rep, this.row, this.column, this.descriptiveStat, this.varianceComponents, this.boxplotRawData, this.histogramRawData, this.heatmapResiduals, this.heatmapRow, this.heatmapColumn, this.diagnosticPlot, this.genotypeFixed, this.performPairwise, this.pairwiseAlpha, this.genotypeLevels, this.controlLevels, this.compareControl, this.performAllPairwise, this.genotypeRandom, this.excludeControls, this.genoPhenoCorrelation);
                rInfo.close();
                WindowDialogControlUtil.hideAllWindowDialog();
                PBToolAnalysisUtilities.openFolder(outputFolder);
            }
        }
    }

    protected Point getInitialSize() {
        return new Point(568, 711);
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
            String[] selectedStrings = SingleEnvironmentDialog.this.factorVarList.getSelection();
            SingleEnvironmentDialog.this.genotypeLevels = DataManipulationManager.getEnvtLevels(selectedStrings[0], SingleEnvironmentDialog.this.file);
            SingleEnvironmentDialog.this.genLevelsList.setItems(SingleEnvironmentDialog.this.genotypeLevels);
            if (SingleEnvironmentDialog.this.checkFixedBtn.getSelection() && SingleEnvironmentDialog.this.checkPerformPairwiseBtn.getSelection()) {
                SingleEnvironmentDialog.this.activatePerformPairwiseOptions(true);
            }
            return true;
        }

        @Override
        public boolean performRemoveProcess() {
            SingleEnvironmentDialog.this.genLevelsList.removeAll();
            SingleEnvironmentDialog.this.controlsList.removeAll();
            SingleEnvironmentDialog.this.resetGenotypeControls();
            return true;
        }
    }
}

