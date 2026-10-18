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
 *  org.eclipse.swt.widgets.Event
 *  org.eclipse.swt.widgets.Group
 *  org.eclipse.swt.widgets.Label
 *  org.eclipse.swt.widgets.Layout
 *  org.eclipse.swt.widgets.List
 *  org.eclipse.swt.widgets.Listener
 *  org.eclipse.swt.widgets.Shell
 *  org.eclipse.swt.widgets.TabFolder
 *  org.eclipse.swt.widgets.TabItem
 *  org.eclipse.swt.widgets.Table
 *  org.eclipse.swt.widgets.TableColumn
 *  org.eclipse.swt.widgets.TableItem
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
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;
import org.irri.breedingtool.application.model.ProjectExplorerTreeNodeModel;
import org.irri.breedingtool.datamanipulation.dialog.OperationProgressDialog;
import org.irri.breedingtool.manager.impl.DataManipulationManager;
import org.irri.breedingtool.pbtools.analysis.environment.dialog.TwoStageMseRepsDialog;
import org.irri.breedingtool.projectexplorer.view.ProjectExplorerView;
import org.irri.breedingtool.utility.PBToolAnalysisUtilities;
import org.irri.breedingtool.utility.WindowDialogControlUtil;

public class TwoStageMultiEnvironmentDialog
extends Dialog {
    private ProjectExplorerTreeNodeModel fileModel;
    private ArrayList<String> varInfo;
    private List numVarList;
    private List factorVarList;
    private Button addEnvBtn;
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
    private List envVarList;
    private List genVarList;
    private String[] numericVariables;
    private String[] factorVariables;
    private File file;
    private DataManipulationManager dataManipulationManager = new DataManipulationManager();
    private Button addGenBtn;
    private TabItem tbtmOptions;
    private Button checkFixedBtn;
    private Label lblLevelOfSignificance;
    private Label lblOptionsForGenotype;
    private Label lblSpecifyControls;
    private Label levelsOfGenotypeLbl;
    private Label controlsLbl;
    private Button checkRandomBtn;
    private Button checkBoxPlotBtn;
    private Button checkHistogramBtn;
    private Button checkDiagnosticPlotsBtn;
    private Label lblWeightOption;
    private Combo combo;
    private Group group_1;
    private Label lblSummary;
    private Table table;
    private Button btnEditButton;
    private Button addRespBtn;
    private Label lblResponseVariable;
    private Button addStdErrBtn;
    private Button addResidVarBtn;
    private Button addReplicateBtn;
    private Label lblStandardError;
    private Label lblResidualVariance;
    private Label lblNumberOfReplicates;
    private Label lblProvideTheFollowing;
    private Button cancelTableBtn;
    private Button addToTableBtn;
    private List respVarList;
    private List stdErrVarList;
    private List residVarList;
    private List replicateVarList;
    private TableColumn tblclmnResponseVar;
    private TableColumn tblclmnStdError;
    private TableColumn tblclmnVariance;
    private TableColumn tblclmnNoOfReps;
    private Button btnDeleteRow;
    private boolean stabilityFinlay;
    private boolean stabilityShukla;
    private boolean ammi;
    private boolean gge;
    ArrayList<Integer> incompleteRequirements = new ArrayList();
    String weightOption = "stderr";
    String[] respvar = new String[0];
    String[] standardErrors = new String[0];
    String[] residualVariances = new String[0];
    String[] numberOfReps = new String[0];
    String environment = "Env";
    String[] environmentLevels = new String[0];
    String[] genotypeLevels = new String[0];
    String genotype = "Geno";
    boolean descriptiveStat = true;
    boolean varianceComponents = true;
    boolean boxplotRawData = true;
    boolean histogramRawData = true;
    boolean diagnosticPlot = true;
    boolean genotypeFixed = true;
    boolean performPairwise = true;
    String pairwiseAlpha = "0.05";
    String[] controlLevels = new String[0];
    boolean compareControl = true;
    boolean performAllPairwise = false;
    boolean genotypeRandom = true;
    private boolean satisfiedAllConditions;
    private String analysisType;
    private Button stabilityFinlaybtn;
    private Button stabilityShuklabtn;
    private Button btnAmmiAnalysis;
    private Label lblNewLabel;
    private Label lblNewLabel_4;
    private Button btnGgeAnalysis;
    private Label lblNewLabel_5;

    public TwoStageMultiEnvironmentDialog(Shell parentShell, String analysisType, File file) {
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
                WindowDialogControlUtil.removeWindowDialogToList(TwoStageMultiEnvironmentDialog.this.getShell());
            }
        });
        parent.getShell().setText("Two-Stage Multi-Environment Analysis: " + this.dataManipulationManager.getDisplayFileName(this.file.getAbsolutePath()));
        Composite container = (Composite)super.createDialogArea(parent);
        TabFolder tabFolder = new TabFolder(container, 0);
        tabFolder.setLayoutData((Object)new GridData(4, 4, true, true, 1, 1));
        TabItem tbtmModelSpecifications_1 = new TabItem(tabFolder, 0);
        tbtmModelSpecifications_1.setText("Model Specifications");
        Composite modelComposite = new Composite((Composite)tabFolder, 0);
        tbtmModelSpecifications_1.setControl((Control)modelComposite);
        modelComposite.setLayout((Layout)new GridLayout(6, false));
        modelComposite.setBackground(this.getShell().getBackground());
        Label lblNumericVariables = new Label(modelComposite, 0);
        lblNumericVariables.setLayoutData((Object)new GridData(16384, 0x1000000, false, false, 2, 1));
        lblNumericVariables.setText("Numeric Variables:");
        this.lblWeightOption = new Label(modelComposite, 0);
        this.lblWeightOption.setLayoutData((Object)new GridData(131072, 0x1000000, false, false, 1, 1));
        this.lblWeightOption.setText("Weight option:");
        this.combo = new Combo(modelComposite, 8);
        this.combo.setItems(new String[]{"No weight", "1/(sem^2)"});
        this.combo.setLayoutData((Object)new GridData(4, 0x1000000, true, false, 2, 1));
        this.combo.select(0);
        this.combo.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (TwoStageMultiEnvironmentDialog.this.combo.getSelectionIndex() == 0) {
                    TwoStageMultiEnvironmentDialog.this.addStdErrBtn.setEnabled(false);
                    TwoStageMultiEnvironmentDialog.this.stdErrVarList.setEnabled(false);
                    TwoStageMultiEnvironmentDialog.this.lblStandardError.setEnabled(false);
                    if (TwoStageMultiEnvironmentDialog.this.stdErrVarList.getItemCount() > 0) {
                        TwoStageMultiEnvironmentDialog.this.numVarList.add(TwoStageMultiEnvironmentDialog.this.stdErrVarList.getItems()[0]);
                    }
                    TwoStageMultiEnvironmentDialog.this.stdErrVarList.removeAll();
                } else {
                    TwoStageMultiEnvironmentDialog.this.addStdErrBtn.setEnabled(true);
                    TwoStageMultiEnvironmentDialog.this.stdErrVarList.setEnabled(true);
                    TwoStageMultiEnvironmentDialog.this.lblStandardError.setEnabled(true);
                }
            }
        });
        new Label(modelComposite, 0);
        this.numVarList = new List(modelComposite, 2816);
        GridData gd_numVarList = new GridData(4, 4, false, false, 2, 3);
        gd_numVarList.heightHint = 95;
        gd_numVarList.widthHint = 181;
        this.numVarList.setLayoutData((Object)gd_numVarList);
        this.numVarList.setItems(this.numericVariables);
        this.numVarList.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                TwoStageMultiEnvironmentDialog.this.enableNumericButtons(false);
                if (TwoStageMultiEnvironmentDialog.this.numVarList.getSelectionCount() > 0) {
                    TwoStageMultiEnvironmentDialog.this.moveBtn.setEnabled(true);
                    TwoStageMultiEnvironmentDialog.this.factorVarList.setSelection(-1);
                    TwoStageMultiEnvironmentDialog.this.moveBtn.setText("Set to Factor");
                    TwoStageMultiEnvironmentDialog.this.moveBtn.setEnabled(true);
                    TwoStageMultiEnvironmentDialog.this.addEnvBtn.setEnabled(false);
                    TwoStageMultiEnvironmentDialog.this.addGenBtn.setEnabled(false);
                    TwoStageMultiEnvironmentDialog.this.enableNumericButtons(true);
                }
            }
        });
        this.group_1 = new Group(modelComposite, 0);
        this.group_1.setLayout((Layout)new GridLayout(5, false));
        GridData gd_group_1 = new GridData(4, 4, true, true, 4, 1);
        gd_group_1.widthHint = 203;
        gd_group_1.heightHint = 237;
        this.group_1.setLayoutData((Object)gd_group_1);
        this.addRespBtn = new Button((Composite)this.group_1, 0);
        this.addRespBtn.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (TwoStageMultiEnvironmentDialog.this.numVarList.getSelectionCount() > 0) {
                    String[] selectedNumVars = TwoStageMultiEnvironmentDialog.this.numVarList.getSelection();
                    int i = 0;
                    while (i < selectedNumVars.length) {
                        TwoStageMultiEnvironmentDialog.this.respVarList.add(selectedNumVars[i]);
                        ++i;
                    }
                    TwoStageMultiEnvironmentDialog.this.numVarList.remove(TwoStageMultiEnvironmentDialog.this.numVarList.getSelectionIndices());
                    TwoStageMultiEnvironmentDialog.this.addEnvBtn.setEnabled(false);
                } else {
                    String[] selectedNumVars = TwoStageMultiEnvironmentDialog.this.respVarList.getSelection();
                    int i = 0;
                    while (i < selectedNumVars.length) {
                        TwoStageMultiEnvironmentDialog.this.numVarList.add(selectedNumVars[i]);
                        ++i;
                    }
                    TwoStageMultiEnvironmentDialog.this.respVarList.remove(TwoStageMultiEnvironmentDialog.this.respVarList.getSelectionIndices());
                }
                TwoStageMultiEnvironmentDialog.this.enableNumericButtons(false);
            }
        });
        GridData gd_addRespBtn = new GridData(16384, 0x1000000, false, false, 1, 1);
        gd_addRespBtn.widthHint = 52;
        this.addRespBtn.setLayoutData((Object)gd_addRespBtn);
        this.addRespBtn.setText("Add");
        this.lblResponseVariable = new Label((Composite)this.group_1, 0);
        this.lblResponseVariable.setText("Response Variable:");
        this.respVarList = new List((Composite)this.group_1, 2048);
        GridData gd_respVarList = new GridData(4, 0x1000000, true, true, 3, 1);
        gd_respVarList.heightHint = 20;
        this.respVarList.setLayoutData((Object)gd_respVarList);
        this.respVarList.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (TwoStageMultiEnvironmentDialog.this.respVarList.getSelectionCount() > 0) {
                    TwoStageMultiEnvironmentDialog.this.numVarList.setSelection(-1);
                    TwoStageMultiEnvironmentDialog.this.addRespBtn.setText("Remove");
                    TwoStageMultiEnvironmentDialog.this.addRespBtn.setEnabled(true);
                }
            }
        });
        this.lblProvideTheFollowing = new Label((Composite)this.group_1, 64);
        GridData gd_lblProvideTheFollowing = new GridData(4, 1024, false, false, 5, 1);
        gd_lblProvideTheFollowing.widthHint = 199;
        gd_lblProvideTheFollowing.heightHint = 27;
        this.lblProvideTheFollowing.setLayoutData((Object)gd_lblProvideTheFollowing);
        this.lblProvideTheFollowing.setText("Provide the following for the response variable specified above:");
        this.addStdErrBtn = new Button((Composite)this.group_1, 0);
        this.addStdErrBtn.setEnabled(false);
        this.addStdErrBtn.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (TwoStageMultiEnvironmentDialog.this.numVarList.getSelectionCount() > 0) {
                    String[] selectedNumVars = TwoStageMultiEnvironmentDialog.this.numVarList.getSelection();
                    int i = 0;
                    while (i < selectedNumVars.length) {
                        TwoStageMultiEnvironmentDialog.this.stdErrVarList.add(selectedNumVars[i]);
                        ++i;
                    }
                    TwoStageMultiEnvironmentDialog.this.numVarList.remove(TwoStageMultiEnvironmentDialog.this.numVarList.getSelectionIndices());
                    TwoStageMultiEnvironmentDialog.this.addStdErrBtn.setEnabled(false);
                } else {
                    String[] selectedNumVars = TwoStageMultiEnvironmentDialog.this.stdErrVarList.getSelection();
                    int i = 0;
                    while (i < selectedNumVars.length) {
                        TwoStageMultiEnvironmentDialog.this.numVarList.add(selectedNumVars[i]);
                        ++i;
                    }
                    TwoStageMultiEnvironmentDialog.this.stdErrVarList.remove(TwoStageMultiEnvironmentDialog.this.stdErrVarList.getSelectionIndices());
                }
                TwoStageMultiEnvironmentDialog.this.enableNumericButtons(false);
            }
        });
        GridData gd_addStdErrBtn = new GridData(16384, 0x1000000, false, false, 1, 1);
        gd_addStdErrBtn.widthHint = 52;
        this.addStdErrBtn.setLayoutData((Object)gd_addStdErrBtn);
        this.addStdErrBtn.setText("Add");
        this.lblStandardError = new Label((Composite)this.group_1, 0);
        this.lblStandardError.setEnabled(false);
        this.lblStandardError.setText("Standard Error:");
        this.stdErrVarList = new List((Composite)this.group_1, 2048);
        this.stdErrVarList.setEnabled(false);
        GridData gd_stdErrVarList = new GridData(4, 0x1000000, true, true, 3, 1);
        gd_stdErrVarList.heightHint = 20;
        this.stdErrVarList.setLayoutData((Object)gd_stdErrVarList);
        this.stdErrVarList.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (TwoStageMultiEnvironmentDialog.this.stdErrVarList.getSelectionCount() > 0) {
                    TwoStageMultiEnvironmentDialog.this.numVarList.setSelection(-1);
                    TwoStageMultiEnvironmentDialog.this.addStdErrBtn.setText("Remove");
                    TwoStageMultiEnvironmentDialog.this.addStdErrBtn.setEnabled(true);
                }
            }
        });
        this.addResidVarBtn = new Button((Composite)this.group_1, 0);
        GridData gd_addResidVarBtn = new GridData(16384, 0x1000000, false, false, 1, 1);
        gd_addResidVarBtn.widthHint = 52;
        this.addResidVarBtn.setLayoutData((Object)gd_addResidVarBtn);
        this.addResidVarBtn.setText("Add");
        this.addResidVarBtn.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (TwoStageMultiEnvironmentDialog.this.numVarList.getSelectionCount() > 0) {
                    String[] selectedNumVars = TwoStageMultiEnvironmentDialog.this.numVarList.getSelection();
                    int i = 0;
                    while (i < selectedNumVars.length) {
                        TwoStageMultiEnvironmentDialog.this.residVarList.add(selectedNumVars[i]);
                        ++i;
                    }
                    TwoStageMultiEnvironmentDialog.this.numVarList.remove(TwoStageMultiEnvironmentDialog.this.numVarList.getSelectionIndices());
                    TwoStageMultiEnvironmentDialog.this.addResidVarBtn.setEnabled(false);
                } else {
                    String[] selectedNumVars = TwoStageMultiEnvironmentDialog.this.residVarList.getSelection();
                    int i = 0;
                    while (i < selectedNumVars.length) {
                        TwoStageMultiEnvironmentDialog.this.numVarList.add(selectedNumVars[i]);
                        ++i;
                    }
                    TwoStageMultiEnvironmentDialog.this.residVarList.remove(TwoStageMultiEnvironmentDialog.this.residVarList.getSelectionIndices());
                }
                TwoStageMultiEnvironmentDialog.this.enableNumericButtons(false);
            }
        });
        this.lblResidualVariance = new Label((Composite)this.group_1, 0);
        this.lblResidualVariance.setText("Residual Variance:");
        this.residVarList = new List((Composite)this.group_1, 2048);
        GridData gd_residVarList = new GridData(4, 0x1000000, true, true, 3, 1);
        gd_residVarList.heightHint = 20;
        this.residVarList.setLayoutData((Object)gd_residVarList);
        this.residVarList.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (TwoStageMultiEnvironmentDialog.this.residVarList.getSelectionCount() > 0) {
                    TwoStageMultiEnvironmentDialog.this.numVarList.setSelection(-1);
                    TwoStageMultiEnvironmentDialog.this.addResidVarBtn.setText("Remove");
                    TwoStageMultiEnvironmentDialog.this.addResidVarBtn.setEnabled(true);
                }
            }
        });
        this.addReplicateBtn = new Button((Composite)this.group_1, 0);
        GridData gd_addReplicateBtn = new GridData(16384, 0x1000000, false, false, 1, 1);
        gd_addReplicateBtn.widthHint = 52;
        this.addReplicateBtn.setLayoutData((Object)gd_addReplicateBtn);
        this.addReplicateBtn.setText("Add");
        this.addReplicateBtn.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (TwoStageMultiEnvironmentDialog.this.numVarList.getSelectionCount() > 0) {
                    String[] selectedNumVars = TwoStageMultiEnvironmentDialog.this.numVarList.getSelection();
                    int i = 0;
                    while (i < selectedNumVars.length) {
                        TwoStageMultiEnvironmentDialog.this.replicateVarList.add(selectedNumVars[i]);
                        ++i;
                    }
                    TwoStageMultiEnvironmentDialog.this.numVarList.remove(TwoStageMultiEnvironmentDialog.this.numVarList.getSelectionIndices());
                    TwoStageMultiEnvironmentDialog.this.addReplicateBtn.setEnabled(false);
                } else {
                    String[] selectedNumVars = TwoStageMultiEnvironmentDialog.this.replicateVarList.getSelection();
                    int i = 0;
                    while (i < selectedNumVars.length) {
                        TwoStageMultiEnvironmentDialog.this.numVarList.add(selectedNumVars[i]);
                        ++i;
                    }
                    TwoStageMultiEnvironmentDialog.this.replicateVarList.remove(TwoStageMultiEnvironmentDialog.this.replicateVarList.getSelectionIndices());
                }
                TwoStageMultiEnvironmentDialog.this.enableNumericButtons(false);
            }
        });
        this.lblNumberOfReplicates = new Label((Composite)this.group_1, 0);
        this.lblNumberOfReplicates.setText("Number of Replicates:");
        this.replicateVarList = new List((Composite)this.group_1, 2048);
        GridData gd_replicateVarList = new GridData(4, 0x1000000, true, true, 3, 1);
        gd_replicateVarList.heightHint = 20;
        this.replicateVarList.setLayoutData((Object)gd_replicateVarList);
        new Label((Composite)this.group_1, 0);
        this.replicateVarList.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (TwoStageMultiEnvironmentDialog.this.replicateVarList.getSelectionCount() > 0) {
                    TwoStageMultiEnvironmentDialog.this.numVarList.setSelection(-1);
                    TwoStageMultiEnvironmentDialog.this.addReplicateBtn.setText("Remove");
                    TwoStageMultiEnvironmentDialog.this.addReplicateBtn.setEnabled(true);
                }
            }
        });
        this.cancelTableBtn = new Button((Composite)this.group_1, 0);
        this.cancelTableBtn.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                TwoStageMultiEnvironmentDialog.this.cancelResponseValues();
            }
        });
        GridData gd_cancelTableBtn = new GridData(131072, 0x1000000, false, false, 1, 1);
        gd_cancelTableBtn.widthHint = 90;
        this.cancelTableBtn.setLayoutData((Object)gd_cancelTableBtn);
        this.cancelTableBtn.setText("Cancel");
        this.addToTableBtn = new Button((Composite)this.group_1, 0);
        this.addToTableBtn.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (TwoStageMultiEnvironmentDialog.this.respVarList.getItemCount() > 0) {
                    if (TwoStageMultiEnvironmentDialog.this.residVarList.getItemCount() == 0 || TwoStageMultiEnvironmentDialog.this.replicateVarList.getItemCount() == 0) {
                        TwoStageMultiEnvironmentDialog.this.incompleteRequirements.add(1);
                    } else if (TwoStageMultiEnvironmentDialog.this.stdErrVarList.getItemCount() == 0 && TwoStageMultiEnvironmentDialog.this.lblStandardError.getEnabled() && TwoStageMultiEnvironmentDialog.this.combo.getSelectionIndex() == 1) {
                        TwoStageMultiEnvironmentDialog.this.incompleteRequirements.add(2);
                    } else {
                        TwoStageMultiEnvironmentDialog.this.incompleteRequirements.add(0);
                    }
                    TwoStageMultiEnvironmentDialog.this.addVariablesToTable();
                } else {
                    MessageDialog.openError((Shell)TwoStageMultiEnvironmentDialog.this.getShell(), (String)"Error", (String)"Please specify a response variable.");
                }
            }
        });
        GridData gd_addToTableBtn = new GridData(16384, 0x1000000, false, false, 3, 1);
        gd_addToTableBtn.widthHint = 90;
        this.addToTableBtn.setLayoutData((Object)gd_addToTableBtn);
        this.addToTableBtn.setText("Add to table");
        this.lblSummary = new Label(modelComposite, 0);
        this.lblSummary.setLayoutData((Object)new GridData(16384, 0x1000000, false, false, 4, 1));
        this.lblSummary.setText("Summary:");
        this.table = new Table(modelComposite, 67584);
        GridData gd_table = new GridData(4, 4, true, true, 4, 1);
        gd_table.widthHint = 258;
        gd_table.heightHint = 58;
        this.table.setLayoutData((Object)gd_table);
        this.table.setHeaderVisible(true);
        this.table.setLinesVisible(true);
        this.tblclmnResponseVar = new TableColumn(this.table, 0);
        this.tblclmnResponseVar.setWidth(109);
        this.tblclmnResponseVar.setText("Response Var");
        this.tblclmnStdError = new TableColumn(this.table, 0);
        this.tblclmnStdError.setWidth(81);
        this.tblclmnStdError.setText("Std Error");
        this.tblclmnVariance = new TableColumn(this.table, 0);
        this.tblclmnVariance.setWidth(103);
        this.tblclmnVariance.setText("Variance");
        this.tblclmnNoOfReps = new TableColumn(this.table, 0);
        this.tblclmnNoOfReps.setWidth(87);
        this.tblclmnNoOfReps.setText("No. of Reps");
        Label lblFactors = new Label(modelComposite, 0);
        lblFactors.setText("Factors:");
        this.moveBtn = new Button(modelComposite, 0);
        this.moveBtn.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (TwoStageMultiEnvironmentDialog.this.numVarList.getSelectionCount() > 0) {
                    String[] selectedNumVars = TwoStageMultiEnvironmentDialog.this.numVarList.getSelection();
                    int i = 0;
                    while (i < selectedNumVars.length) {
                        TwoStageMultiEnvironmentDialog.this.factorVarList.add(selectedNumVars[i]);
                        TwoStageMultiEnvironmentDialog.this.dataManipulationManager.editVariableType(TwoStageMultiEnvironmentDialog.this.file.getAbsolutePath(), TwoStageMultiEnvironmentDialog.this.varInfo, selectedNumVars[i], "Factor");
                        ++i;
                    }
                    TwoStageMultiEnvironmentDialog.this.numVarList.remove(TwoStageMultiEnvironmentDialog.this.numVarList.getSelectionIndices());
                    TwoStageMultiEnvironmentDialog.this.enableNumericButtons(false);
                } else {
                    String[] selectedNumVars = TwoStageMultiEnvironmentDialog.this.factorVarList.getSelection();
                    int i = 0;
                    while (i < selectedNumVars.length) {
                        TwoStageMultiEnvironmentDialog.this.numVarList.add(selectedNumVars[i]);
                        TwoStageMultiEnvironmentDialog.this.dataManipulationManager.editVariableType(TwoStageMultiEnvironmentDialog.this.file.getAbsolutePath(), TwoStageMultiEnvironmentDialog.this.varInfo, selectedNumVars[i], "Numeric");
                        ++i;
                    }
                    TwoStageMultiEnvironmentDialog.this.factorVarList.remove(TwoStageMultiEnvironmentDialog.this.factorVarList.getSelectionIndices());
                }
            }
        });
        this.moveBtn.setEnabled(false);
        GridData gd_moveBtn = new GridData(131072, 0x1000000, true, false, 1, 1);
        gd_moveBtn.heightHint = 24;
        gd_moveBtn.widthHint = 90;
        this.moveBtn.setLayoutData((Object)gd_moveBtn);
        this.moveBtn.setText("Set to Factor");
        new Label(modelComposite, 0);
        new Label(modelComposite, 0);
        this.btnEditButton = new Button(modelComposite, 0);
        this.btnEditButton.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (TwoStageMultiEnvironmentDialog.this.table.getItemCount() > 0) {
                    if (TwoStageMultiEnvironmentDialog.this.table.getItemCount() == 1 || TwoStageMultiEnvironmentDialog.this.table.getSelectionCount() > 0) {
                        int index = TwoStageMultiEnvironmentDialog.this.table.getSelectionIndex();
                        if (TwoStageMultiEnvironmentDialog.this.table.getSelectionCount() == 0) {
                            index = 0;
                        }
                        TwoStageMultiEnvironmentDialog.this.editRowValues(index);
                        TwoStageMultiEnvironmentDialog.this.incompleteRequirements.remove(index);
                    } else {
                        MessageDialog.openWarning((Shell)TwoStageMultiEnvironmentDialog.this.getShell(), (String)"No selected row", (String)"Please select the row you want to delete.");
                    }
                } else {
                    MessageDialog.openWarning((Shell)TwoStageMultiEnvironmentDialog.this.getShell(), (String)"Invalid input", (String)"Table is empty. There is nothing to edit.");
                }
            }
        });
        GridData gd_btnEditButton = new GridData(131072, 0x1000000, false, false, 1, 1);
        gd_btnEditButton.widthHint = 90;
        this.btnEditButton.setLayoutData((Object)gd_btnEditButton);
        this.btnEditButton.setText("Edit Row");
        this.btnDeleteRow = new Button(modelComposite, 0);
        this.btnDeleteRow.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (TwoStageMultiEnvironmentDialog.this.table.getItemCount() > 0) {
                    if (TwoStageMultiEnvironmentDialog.this.table.getItemCount() == 1 || TwoStageMultiEnvironmentDialog.this.table.getSelectionCount() > 0) {
                        int index = TwoStageMultiEnvironmentDialog.this.table.getSelectionIndex();
                        if (TwoStageMultiEnvironmentDialog.this.table.getSelectionCount() == 0) {
                            index = 0;
                        }
                        String[] values = TwoStageMultiEnvironmentDialog.this.getVariableValuesFromTableAtRow(index);
                        int i = 0;
                        while (i < 4) {
                            if (!values[i].equals("")) {
                                TwoStageMultiEnvironmentDialog.this.numVarList.add(values[i]);
                            }
                            ++i;
                        }
                        TwoStageMultiEnvironmentDialog.this.dataManipulationManager.deleteTableRow(TwoStageMultiEnvironmentDialog.this.table, index);
                        TwoStageMultiEnvironmentDialog.this.incompleteRequirements.remove(index);
                    } else {
                        MessageDialog.openWarning((Shell)TwoStageMultiEnvironmentDialog.this.getShell(), (String)"No selected row", (String)"Please select the row you want to delete.");
                    }
                } else {
                    MessageDialog.openWarning((Shell)TwoStageMultiEnvironmentDialog.this.getShell(), (String)"Invalid input", (String)"Table is empty. There is nothing to delete.");
                }
            }
        });
        GridData gd_btnDeleteRow = new GridData(16384, 0x1000000, false, false, 1, 1);
        gd_btnDeleteRow.widthHint = 90;
        this.btnDeleteRow.setLayoutData((Object)gd_btnDeleteRow);
        this.btnDeleteRow.setText("Delete Row");
        this.factorVarList = new List(modelComposite, 2816);
        GridData gd_factorVarList = new GridData(4, 4, false, true, 2, 1);
        gd_factorVarList.heightHint = 95;
        gd_factorVarList.widthHint = 60;
        this.factorVarList.setLayoutData((Object)gd_factorVarList);
        this.factorVarList.setItems(this.factorVariables);
        this.factorVarList.addListener(3, new Listener(){

            public void handleEvent(Event event) {
                if (TwoStageMultiEnvironmentDialog.this.factorVarList.getSelectionIndex() > -1) {
                    TwoStageMultiEnvironmentDialog.this.enableFactorButtons(false);
                    TwoStageMultiEnvironmentDialog.this.enableNumericButtons(false);
                    TwoStageMultiEnvironmentDialog.this.enableFactorButtons(true);
                    TwoStageMultiEnvironmentDialog.this.numVarList.setSelection(-1);
                    String[] s = TwoStageMultiEnvironmentDialog.this.factorVarList.getSelection();
                    String isNumeric = TwoStageMultiEnvironmentDialog.this.dataManipulationManager.isNumeric(TwoStageMultiEnvironmentDialog.this.file.getAbsolutePath().replaceAll("\\\\", "/"), s[0]);
                    if (isNumeric.equals("TRUE")) {
                        TwoStageMultiEnvironmentDialog.this.moveBtn.setText("Set to Numeric");
                        TwoStageMultiEnvironmentDialog.this.moveBtn.setEnabled(true);
                    } else {
                        TwoStageMultiEnvironmentDialog.this.moveBtn.setEnabled(false);
                    }
                }
            }
        });
        Group group = new Group(modelComposite, 0);
        group.setLayout((Layout)new GridLayout(3, false));
        GridData gd_group = new GridData(4, 4, true, false, 4, 1);
        gd_group.widthHint = 206;
        group.setLayoutData((Object)gd_group);
        this.addEnvBtn = new Button((Composite)group, 0);
        this.addEnvBtn.setEnabled(false);
        this.addEnvBtn.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (TwoStageMultiEnvironmentDialog.this.factorVarList.getSelectionCount() > 0) {
                    String[] selectedStrings = TwoStageMultiEnvironmentDialog.this.factorVarList.getSelection();
                    TwoStageMultiEnvironmentDialog.this.environmentLevels = DataManipulationManager.getEnvtLevels(selectedStrings[0], TwoStageMultiEnvironmentDialog.this.file);
                    TwoStageMultiEnvironmentDialog.this.dataManipulationManager.moveSelectedStringFromTo(TwoStageMultiEnvironmentDialog.this.factorVarList, TwoStageMultiEnvironmentDialog.this.envVarList);
                } else {
                    TwoStageMultiEnvironmentDialog.this.dataManipulationManager.moveSelectedStringFromTo(TwoStageMultiEnvironmentDialog.this.envVarList, TwoStageMultiEnvironmentDialog.this.factorVarList);
                }
                TwoStageMultiEnvironmentDialog.this.addEnvBtn.setEnabled(false);
            }
        });
        this.addEnvBtn.setText("Add");
        GridData gd_addEnvBtn = new GridData(16384, 0x1000000, false, false, 1, 1);
        gd_addEnvBtn.widthHint = 52;
        this.addEnvBtn.setLayoutData((Object)gd_addEnvBtn);
        Label lblNewLabel_1 = new Label((Composite)group, 0);
        lblNewLabel_1.setText("Environment:");
        this.envVarList = new List((Composite)group, 2048);
        GridData gd_envVarList = new GridData(4, 0x1000000, true, false, 1, 1);
        gd_envVarList.widthHint = 101;
        gd_envVarList.heightHint = 20;
        this.envVarList.setLayoutData((Object)gd_envVarList);
        this.envVarList.addMouseListener(new MouseListener(){

            public void mouseDoubleClick(MouseEvent e) {
                TwoStageMultiEnvironmentDialog.this.dataManipulationManager.moveSelectedStringFromTo(TwoStageMultiEnvironmentDialog.this.envVarList, TwoStageMultiEnvironmentDialog.this.factorVarList);
                TwoStageMultiEnvironmentDialog.this.addEnvBtn.setEnabled(false);
            }

            public void mouseDown(MouseEvent e) {
                if (TwoStageMultiEnvironmentDialog.this.envVarList.getSelectionCount() > 0) {
                    TwoStageMultiEnvironmentDialog.this.factorVarList.setSelection(-1);
                    TwoStageMultiEnvironmentDialog.this.addEnvBtn.setText("Remove");
                    TwoStageMultiEnvironmentDialog.this.addEnvBtn.setEnabled(true);
                }
            }

            public void mouseUp(MouseEvent e) {
            }
        });
        this.addGenBtn = new Button((Composite)group, 0);
        this.addGenBtn.setEnabled(false);
        this.addGenBtn.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (TwoStageMultiEnvironmentDialog.this.factorVarList.getSelectionCount() > 0) {
                    String[] selectedStrings = TwoStageMultiEnvironmentDialog.this.factorVarList.getSelection();
                    TwoStageMultiEnvironmentDialog.this.genotypeLevels = DataManipulationManager.getEnvtLevels(selectedStrings[0], TwoStageMultiEnvironmentDialog.this.file);
                    TwoStageMultiEnvironmentDialog.this.dataManipulationManager.moveSelectedStringFromTo(TwoStageMultiEnvironmentDialog.this.factorVarList, TwoStageMultiEnvironmentDialog.this.genVarList);
                    TwoStageMultiEnvironmentDialog.this.genLevelsList.setItems(TwoStageMultiEnvironmentDialog.this.genotypeLevels);
                    if (TwoStageMultiEnvironmentDialog.this.checkFixedBtn.getSelection() && TwoStageMultiEnvironmentDialog.this.checkPerformPairwiseBtn.getSelection()) {
                        TwoStageMultiEnvironmentDialog.this.activatePerformPairwiseOptions(true);
                    }
                } else {
                    TwoStageMultiEnvironmentDialog.this.dataManipulationManager.moveSelectedStringFromTo(TwoStageMultiEnvironmentDialog.this.genVarList, TwoStageMultiEnvironmentDialog.this.factorVarList);
                    TwoStageMultiEnvironmentDialog.this.genLevelsList.removeAll();
                    TwoStageMultiEnvironmentDialog.this.controlsList.removeAll();
                    TwoStageMultiEnvironmentDialog.this.resetGenotypeControls();
                }
                TwoStageMultiEnvironmentDialog.this.addGenBtn.setEnabled(false);
            }
        });
        this.addGenBtn.setText("Add");
        GridData gd_addGenBtn = new GridData(16384, 0x1000000, false, false, 1, 1);
        gd_addGenBtn.widthHint = 52;
        this.addGenBtn.setLayoutData((Object)gd_addGenBtn);
        Label lblNewLabel_2 = new Label((Composite)group, 0);
        lblNewLabel_2.setText("Genotype:");
        this.genVarList = new List((Composite)group, 2048);
        GridData gd_genVarList = new GridData(4, 0x1000000, true, false, 1, 1);
        gd_genVarList.heightHint = 19;
        this.genVarList.setLayoutData((Object)gd_genVarList);
        this.genVarList.addMouseListener(new MouseListener(){

            public void mouseDoubleClick(MouseEvent e) {
                TwoStageMultiEnvironmentDialog.this.dataManipulationManager.moveSelectedStringFromTo(TwoStageMultiEnvironmentDialog.this.genVarList, TwoStageMultiEnvironmentDialog.this.factorVarList);
                TwoStageMultiEnvironmentDialog.this.genLevelsList.removeAll();
                TwoStageMultiEnvironmentDialog.this.controlsList.removeAll();
                TwoStageMultiEnvironmentDialog.this.resetGenotypeControls();
                TwoStageMultiEnvironmentDialog.this.addGenBtn.setEnabled(false);
            }

            public void mouseDown(MouseEvent e) {
                if (TwoStageMultiEnvironmentDialog.this.genVarList.getSelectionCount() > 0) {
                    TwoStageMultiEnvironmentDialog.this.factorVarList.setSelection(-1);
                    TwoStageMultiEnvironmentDialog.this.addGenBtn.setText("Remove");
                    TwoStageMultiEnvironmentDialog.this.addGenBtn.setEnabled(true);
                }
            }

            public void mouseUp(MouseEvent e) {
            }
        });
        new Label((Composite)group, 0);
        this.checkFixedBtn = new Button((Composite)group, 32);
        this.checkFixedBtn.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (TwoStageMultiEnvironmentDialog.this.checkFixedBtn.getSelection()) {
                    TwoStageMultiEnvironmentDialog.this.activateFixedOptions(true);
                    if (TwoStageMultiEnvironmentDialog.this.checkPerformPairwiseBtn.getSelection()) {
                        TwoStageMultiEnvironmentDialog.this.activatePerformPairwiseOptions(true);
                        if (TwoStageMultiEnvironmentDialog.this.compareWithControlsBtn.getSelection()) {
                            TwoStageMultiEnvironmentDialog.this.activateLevelOfConrolsOptions(true);
                        }
                    }
                } else {
                    TwoStageMultiEnvironmentDialog.this.activateFixedOptions(false);
                    TwoStageMultiEnvironmentDialog.this.activatePerformPairwiseOptions(false);
                    TwoStageMultiEnvironmentDialog.this.activateLevelOfConrolsOptions(false);
                }
            }
        });
        this.checkFixedBtn.setSelection(true);
        this.checkFixedBtn.setText("Fixed");
        this.checkRandomBtn = new Button((Composite)group, 32);
        this.checkRandomBtn.setLayoutData((Object)new GridData(16384, 0x1000000, true, false, 1, 1));
        this.checkRandomBtn.setText("Random");
        this.tbtmOptions = new TabItem(tabFolder, 0);
        this.tbtmOptions.setText("Options");
        Composite composite_1 = new Composite((Composite)tabFolder, 0);
        this.tbtmOptions.setControl((Control)composite_1);
        composite_1.setBackground(this.getShell().getBackground());
        composite_1.setLayout((Layout)new GridLayout(7, false));
        this.lblOptionsForGenotype = new Label(composite_1, 0);
        GridData gd_lblOptionsForGenotype = new GridData(16384, 0x1000000, false, false, 4, 1);
        gd_lblOptionsForGenotype.heightHint = 21;
        gd_lblOptionsForGenotype.widthHint = 217;
        this.lblOptionsForGenotype.setLayoutData((Object)gd_lblOptionsForGenotype);
        this.lblOptionsForGenotype.setText("Options for Genotype as Fixed:");
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        this.checkPerformPairwiseBtn = new Button(composite_1, 32);
        this.checkPerformPairwiseBtn.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (TwoStageMultiEnvironmentDialog.this.checkPerformPairwiseBtn.getSelection()) {
                    TwoStageMultiEnvironmentDialog.this.activatePerformPairwiseOptions(true);
                    if (TwoStageMultiEnvironmentDialog.this.compareWithControlsBtn.getSelection()) {
                        TwoStageMultiEnvironmentDialog.this.activateLevelOfConrolsOptions(true);
                    }
                } else {
                    TwoStageMultiEnvironmentDialog.this.activatePerformPairwiseOptions(false);
                    TwoStageMultiEnvironmentDialog.this.activateLevelOfConrolsOptions(false);
                }
            }
        });
        this.checkPerformPairwiseBtn.setLayoutData((Object)new GridData(16384, 0x1000000, false, false, 6, 1));
        this.checkPerformPairwiseBtn.setText("Perform pairwise mean comparisons");
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        this.compareWithControlsBtn = new Button(composite_1, 16);
        this.compareWithControlsBtn.setEnabled(false);
        this.compareWithControlsBtn.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (TwoStageMultiEnvironmentDialog.this.compareWithControlsBtn.getSelection()) {
                    TwoStageMultiEnvironmentDialog.this.activateLevelOfConrolsOptions(true);
                } else {
                    TwoStageMultiEnvironmentDialog.this.activateLevelOfConrolsOptions(false);
                }
            }
        });
        this.compareWithControlsBtn.setLayoutData((Object)new GridData(16384, 0x1000000, true, false, 5, 1));
        this.compareWithControlsBtn.setText("Compare with control(s)");
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        this.performAllComparisonsBtn = new Button(composite_1, 16);
        this.performAllComparisonsBtn.setEnabled(false);
        this.performAllComparisonsBtn.setLayoutData((Object)new GridData(16384, 0x1000000, true, false, 5, 1));
        this.performAllComparisonsBtn.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
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
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        this.lblSpecifyControls = new Label(composite_1, 0);
        this.lblSpecifyControls.setLayoutData((Object)new GridData(16384, 0x1000000, false, false, 7, 1));
        this.lblSpecifyControls.setEnabled(false);
        this.lblSpecifyControls.setText("Specify Control(s)");
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        this.levelsOfGenotypeLbl = new Label(composite_1, 0);
        this.levelsOfGenotypeLbl.setLayoutData((Object)new GridData(16384, 0x1000000, true, false, 1, 1));
        this.levelsOfGenotypeLbl.setEnabled(false);
        this.levelsOfGenotypeLbl.setText("Levels of Genotype:");
        new Label(composite_1, 0);
        this.controlsLbl = new Label(composite_1, 0);
        this.controlsLbl.setLayoutData((Object)new GridData(16384, 0x1000000, true, false, 1, 1));
        this.controlsLbl.setEnabled(false);
        this.controlsLbl.setText("Control(s):");
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        this.genLevelsList = new List(composite_1, 2818);
        this.genLevelsList.setEnabled(false);
        GridData gd_genLevelsList = new GridData(4, 4, true, true, 1, 3);
        gd_genLevelsList.widthHint = 120;
        this.genLevelsList.setLayoutData((Object)gd_genLevelsList);
        this.genLevelsList.addMouseListener(new MouseListener(){

            public void mouseDoubleClick(MouseEvent e) {
                TwoStageMultiEnvironmentDialog.this.dataManipulationManager.moveSelectedStringFromTo(TwoStageMultiEnvironmentDialog.this.genLevelsList, TwoStageMultiEnvironmentDialog.this.controlsList);
                TwoStageMultiEnvironmentDialog.this.addControlsBtn.setEnabled(false);
            }

            public void mouseDown(MouseEvent e) {
                if (TwoStageMultiEnvironmentDialog.this.genLevelsList.getSelectionIndex() > -1) {
                    TwoStageMultiEnvironmentDialog.this.addControlsBtn.setEnabled(true);
                    TwoStageMultiEnvironmentDialog.this.controlsList.setSelection(-1);
                    TwoStageMultiEnvironmentDialog.this.genLevelsList.getSelection();
                    TwoStageMultiEnvironmentDialog.this.addControlsBtn.setText("Add");
                    TwoStageMultiEnvironmentDialog.this.addControlsBtn.setEnabled(true);
                }
            }

            public void mouseUp(MouseEvent e) {
            }
        });
        new Label(composite_1, 0);
        this.controlsList = new List(composite_1, 2818);
        this.controlsList.setEnabled(false);
        GridData gd_controlsList = new GridData(4, 4, true, true, 1, 3);
        gd_controlsList.widthHint = 120;
        this.controlsList.setLayoutData((Object)gd_controlsList);
        this.controlsList.addMouseListener(new MouseListener(){

            public void mouseDoubleClick(MouseEvent e) {
                TwoStageMultiEnvironmentDialog.this.dataManipulationManager.moveSelectedStringFromTo(TwoStageMultiEnvironmentDialog.this.controlsList, TwoStageMultiEnvironmentDialog.this.genLevelsList);
                TwoStageMultiEnvironmentDialog.this.addControlsBtn.setEnabled(false);
            }

            public void mouseDown(MouseEvent e) {
                if (TwoStageMultiEnvironmentDialog.this.controlsList.getSelectionIndex() > -1) {
                    TwoStageMultiEnvironmentDialog.this.addControlsBtn.setEnabled(true);
                    TwoStageMultiEnvironmentDialog.this.genLevelsList.setSelection(-1);
                    TwoStageMultiEnvironmentDialog.this.controlsList.getSelection();
                    TwoStageMultiEnvironmentDialog.this.addControlsBtn.setText("Remove");
                    TwoStageMultiEnvironmentDialog.this.addControlsBtn.setEnabled(true);
                }
            }

            public void mouseUp(MouseEvent e) {
            }
        });
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        this.addControlsBtn = new Button(composite_1, 0);
        this.addControlsBtn.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (TwoStageMultiEnvironmentDialog.this.genLevelsList.getSelectionCount() > 0) {
                    String[] selectedNumVars = TwoStageMultiEnvironmentDialog.this.genLevelsList.getSelection();
                    int i = 0;
                    while (i < selectedNumVars.length) {
                        TwoStageMultiEnvironmentDialog.this.controlsList.add(selectedNumVars[i]);
                        ++i;
                    }
                    TwoStageMultiEnvironmentDialog.this.genLevelsList.remove(TwoStageMultiEnvironmentDialog.this.genLevelsList.getSelectionIndices());
                    TwoStageMultiEnvironmentDialog.this.addControlsBtn.setEnabled(false);
                } else {
                    String[] selectedNumVars = TwoStageMultiEnvironmentDialog.this.controlsList.getSelection();
                    int i = 0;
                    while (i < selectedNumVars.length) {
                        TwoStageMultiEnvironmentDialog.this.genLevelsList.add(selectedNumVars[i]);
                        ++i;
                    }
                    TwoStageMultiEnvironmentDialog.this.controlsList.remove(TwoStageMultiEnvironmentDialog.this.controlsList.getSelectionIndices());
                }
                TwoStageMultiEnvironmentDialog.this.addControlsBtn.setEnabled(false);
            }
        });
        this.addControlsBtn.setEnabled(false);
        GridData gd_addControlsBtn = new GridData(0x1000000, 0x1000000, false, false, 1, 1);
        gd_addControlsBtn.widthHint = 78;
        this.addControlsBtn.setLayoutData((Object)gd_addControlsBtn);
        this.addControlsBtn.setText("Add");
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        this.lblNewLabel = new Label(composite_1, 0);
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        this.stabilityFinlaybtn = new Button(composite_1, 32);
        this.stabilityFinlaybtn.setLayoutData((Object)new GridData(16384, 0x1000000, false, false, 3, 1));
        this.stabilityFinlaybtn.setText("Stability Analysis Using Finlay-Wilkinson Model");
        this.stabilityFinlaybtn.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        this.stabilityShuklabtn = new Button(composite_1, 32);
        this.stabilityShuklabtn.setLayoutData((Object)new GridData(16384, 0x1000000, false, false, 3, 1));
        this.stabilityShuklabtn.setText("Stability Analysis Using Shukla's Model");
        this.stabilityShuklabtn.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        this.btnAmmiAnalysis = new Button(composite_1, 32);
        this.btnAmmiAnalysis.setLayoutData((Object)new GridData(16384, 0x1000000, false, false, 3, 1));
        this.btnAmmiAnalysis.setText("AMMI Analysis");
        this.btnAmmiAnalysis.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        this.btnGgeAnalysis = new Button(composite_1, 32);
        this.btnGgeAnalysis.setLayoutData((Object)new GridData(16384, 0x1000000, false, false, 3, 1));
        this.btnGgeAnalysis.setText("GGE Analysis");
        this.btnGgeAnalysis.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        this.lblNewLabel_4 = new Label(composite_1, 0);
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        this.lblNewLabel_5 = new Label(composite_1, 0);
        new Label(composite_1, 0);
        Label lblNewLabel_3 = new Label(composite_1, 0);
        lblNewLabel_3.setLayoutData((Object)new GridData(16384, 0x1000000, false, false, 7, 1));
        lblNewLabel_3.setText("Display:");
        new Label(composite_1, 0);
        this.checkDescriptiveStatistics = new Button(composite_1, 32);
        this.checkDescriptiveStatistics.setLayoutData((Object)new GridData(16384, 0x1000000, false, false, 6, 1));
        this.checkDescriptiveStatistics.setText("Descriptive Statistics");
        new Label(composite_1, 0);
        this.checkVarianceComponents = new Button(composite_1, 32);
        this.checkVarianceComponents.setLayoutData((Object)new GridData(16384, 0x1000000, false, false, 6, 1));
        this.checkVarianceComponents.setText("Variance Components");
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
        return container;
    }

    protected void editRowValues(int index) {
        this.cancelResponseValues();
        TableItem tableItem = this.table.getItem(index);
        if (!tableItem.getText(0).equals("")) {
            this.respVarList.add(tableItem.getText(0));
        }
        if (!tableItem.getText(1).equals("")) {
            this.stdErrVarList.add(tableItem.getText(1));
        }
        if (!tableItem.getText(2).equals("")) {
            this.residVarList.add(tableItem.getText(2));
        }
        if (!tableItem.getText(3).equals("")) {
            this.replicateVarList.add(tableItem.getText(3));
        }
        this.dataManipulationManager.deleteTableRow(this.table, index);
    }

    protected String[] getVariableValuesFromTableAtRow(int index) {
        TableItem tableItem = this.table.getItem(index);
        String[] values = new String[]{tableItem.getText(0), tableItem.getText(1), tableItem.getText(2), tableItem.getText(3)};
        return values;
    }

    protected void cancelResponseValues() {
        if (this.respVarList.getItemCount() > 0) {
            this.numVarList.add(this.respVarList.getItem(0));
        }
        if (this.stdErrVarList.getItemCount() > 0) {
            this.numVarList.add(this.stdErrVarList.getItem(0));
        }
        if (this.residVarList.getItemCount() > 0) {
            this.numVarList.add(this.residVarList.getItem(0));
        }
        if (this.replicateVarList.getItemCount() > 0) {
            this.numVarList.add(this.replicateVarList.getItem(0));
        }
        this.removeVarValues();
    }

    protected void addVariablesToTable() {
        String[] values = this.getVariableValues();
        TableItem tableItem = new TableItem(this.table, 0x1000000);
        tableItem.setText(0, values[0]);
        tableItem.setText(1, values[1]);
        tableItem.setText(2, values[2]);
        tableItem.setText(3, values[3]);
    }

    private String[] getVariableValues() {
        String[] values = new String[]{this.respVarList.getItemCount() > 0 ? this.respVarList.getItem(0) : "", this.stdErrVarList.getItemCount() > 0 ? this.stdErrVarList.getItem(0) : "", this.residVarList.getItemCount() > 0 ? this.residVarList.getItem(0) : "", this.replicateVarList.getItemCount() > 0 ? this.replicateVarList.getItem(0) : ""};
        this.removeVarValues();
        return values;
    }

    private void removeVarValues() {
        this.stdErrVarList.removeAll();
        this.respVarList.removeAll();
        this.residVarList.removeAll();
        this.replicateVarList.removeAll();
    }

    protected void resetGenotypeControls() {
        this.genLevelsList.removeAll();
        this.controlsList.removeAll();
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
                this.performAllComparisonsBtn.setEnabled(state);
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

    public void enableNumericButtons(boolean state) {
        this.moveBtn.setEnabled(state);
        this.replicateVarList.setSelection(-1);
        this.respVarList.setSelection(-1);
        this.residVarList.setSelection(-1);
        this.stdErrVarList.setSelection(-1);
        if (state) {
            this.addReplicateBtn.setText("Add");
            this.addRespBtn.setText("Add");
            this.addResidVarBtn.setText("Add");
            this.addStdErrBtn.setText("Add");
            if (this.replicateVarList.getItemCount() < 1) {
                this.addReplicateBtn.setEnabled(state);
            }
            if (this.respVarList.getItemCount() < 1) {
                this.addRespBtn.setEnabled(state);
            }
            if (this.residVarList.getItemCount() < 1) {
                this.addResidVarBtn.setEnabled(state);
            }
            if (this.stdErrVarList.getItemCount() < 1 && this.stdErrVarList.getEnabled()) {
                this.addStdErrBtn.setEnabled(state);
            }
        } else {
            this.moveBtn.setEnabled(state);
            this.addReplicateBtn.setEnabled(state);
            this.addRespBtn.setEnabled(state);
            this.addResidVarBtn.setEnabled(state);
            this.addStdErrBtn.setEnabled(state);
        }
    }

    public void enableFactorButtons(boolean state) {
        this.genVarList.setSelection(-1);
        this.envVarList.setSelection(-1);
        if (state) {
            this.addGenBtn.setText("Add");
            this.addEnvBtn.setText("Add");
            if (this.genVarList.getItemCount() < 1) {
                this.addGenBtn.setEnabled(state);
            }
            if (this.envVarList.getItemCount() < 1) {
                this.addEnvBtn.setEnabled(state);
            }
        } else {
            this.moveBtn.setEnabled(state);
            this.addGenBtn.setEnabled(state);
            this.addEnvBtn.setEnabled(state);
        }
    }

    protected void createButtonsForButtonBar(Composite parent) {
        this.createButton(parent, 0, IDialogConstants.OK_LABEL, true);
        this.createButton(parent, 1, IDialogConstants.CANCEL_LABEL, false);
    }

    protected void okPressed() {
        this.satisfiedAllConditions = true;
        this.genotypeFixed = this.checkFixedBtn.getSelection();
        this.genotypeRandom = this.checkRandomBtn.getSelection();
        if (this.table.getItemCount() < 1) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"Please add values to the summary table.");
        } else if (this.envVarList.getItemCount() < 1) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"Please add an environment factor.");
        } else if (this.genVarList.getItemCount() < 1) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"Please add a genotype factor.");
        } else if (!this.genotypeFixed && !this.genotypeRandom) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"Please specify whether the genotype variable is fixed or random.");
        } else if (this.incompleteRequirements.contains(1) || this.incompleteRequirements.contains(2)) {
            if (this.genotypeFixed) {
                String[] respvar = new String[this.table.getItemCount()];
                int i = 0;
                while (i < this.table.getItemCount()) {
                    respvar[i] = this.table.getItem(i).getText(0);
                    ++i;
                }
                TwoStageMseRepsDialog mseRepsDlg = new TwoStageMseRepsDialog(this.getShell(), respvar);
                mseRepsDlg.open();
                if (mseRepsDlg.getReturnCode() == 0) {
                    String[] mseValue = mseRepsDlg.getMseValues();
                    String[] repValue = mseRepsDlg.getRepsValues();
                    String environment = this.envVarList.getItem(0);
                    String[] environmentLevels = DataManipulationManager.getEnvtLevels(environment, this.file);
                    String genotype = this.genVarList.getItem(0);
                    String dataFileName = this.file.getAbsolutePath().toString().replaceAll("\\\\+", "/");
                    File outputFolder = PBToolAnalysisUtilities.createOutputFolder(this.file.getName(), this.analysisType);
                    String newOutputFileName = String.valueOf(outputFolder.getAbsolutePath()) + "/output.txt";
                    OperationProgressDialog rInfo = new OperationProgressDialog(this.getShell(), "Two-stage Multi-Environment Analysis");
                    rInfo.open();
                    ProjectExplorerView.rJavaManager.getPbToolAnalysisManager().doMultiEnvironmentSecondStageVersion2(dataFileName, newOutputFileName.replaceAll("\\\\+", "/"), String.valueOf(outputFolder.getAbsolutePath().replaceAll("\\\\", "/")) + "/", respvar, environment, environmentLevels, genotype, mseValue, repValue);
                    rInfo.close();
                    WindowDialogControlUtil.hideAllWindowDialog();
                    PBToolAnalysisUtilities.openFolder(outputFolder);
                }
            } else {
                MessageDialog.openWarning((Shell)this.getShell(), (String)"Invalid Table Input", (String)"The variables you specified were incomplete.");
            }
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
                this.respvar = this.getResponseVariables();
                this.standardErrors = this.getStandardErrors();
                this.residualVariances = this.getResidVars();
                this.numberOfReps = this.getNumberOfReps();
                this.environment = this.envVarList.getItem(0);
                this.genotype = this.genVarList.getItem(0);
                this.pairwiseAlpha = this.significanceLevel.getText();
                this.stabilityFinlay = this.stabilityFinlaybtn.getSelection();
                this.stabilityShukla = this.stabilityShuklabtn.getSelection();
                this.ammi = this.btnAmmiAnalysis.getSelection();
                this.gge = this.btnGgeAnalysis.getSelection();
                this.descriptiveStat = this.checkDescriptiveStatistics.getSelection();
                this.varianceComponents = this.checkVarianceComponents.getSelection();
                this.boxplotRawData = this.checkBoxPlotBtn.getSelection();
                this.histogramRawData = this.checkHistogramBtn.getSelection();
                this.diagnosticPlot = this.checkDiagnosticPlotsBtn.getSelection();
                this.weightOption = this.combo.getText().equals("No weight") ? "none" : "stderr";
                String dataFileName = this.file.getAbsolutePath().toString().replaceAll("\\\\+", "/");
                File outputFolder = PBToolAnalysisUtilities.createOutputFolder(this.file.getName(), this.analysisType);
                String newOutputFileName = String.valueOf(outputFolder.getAbsolutePath()) + "/output.txt";
                OperationProgressDialog rInfo = new OperationProgressDialog(this.getShell(), "Two-stage Multi-Environment Analysis");
                rInfo.open();
                ProjectExplorerView.rJavaManager.getPbToolAnalysisManager().doMultiEnvironmentSecondStage(dataFileName, newOutputFileName.replaceAll("\\\\+", "/"), String.valueOf(outputFolder.getAbsolutePath().replaceAll("\\\\", "/")) + "/", this.weightOption, this.respvar, this.standardErrors, this.residualVariances, this.numberOfReps, this.environment, this.environmentLevels, this.genotype, this.descriptiveStat, this.varianceComponents, this.stabilityFinlay, this.stabilityShukla, this.ammi, this.gge, this.boxplotRawData, this.histogramRawData, this.diagnosticPlot, this.genotypeFixed, this.performPairwise, this.pairwiseAlpha, this.genotypeLevels, this.controlLevels, this.compareControl, this.performAllPairwise, this.genotypeRandom);
                rInfo.close();
                WindowDialogControlUtil.hideAllWindowDialog();
                PBToolAnalysisUtilities.openFolder(outputFolder);
            }
        }
    }

    private String[] getNumberOfReps() {
        int itemCount = this.table.getItemCount();
        String[] RepVars = new String[itemCount];
        int i = 0;
        while (i < itemCount) {
            TableItem item = this.table.getItem(i);
            RepVars[i] = item.getText(3);
            ++i;
        }
        return RepVars;
    }

    private String[] getResidVars() {
        int itemCount = this.table.getItemCount();
        String[] ResidVars = new String[itemCount];
        int i = 0;
        while (i < itemCount) {
            TableItem item = this.table.getItem(i);
            ResidVars[i] = item.getText(2);
            ++i;
        }
        return ResidVars;
    }

    private String[] getStandardErrors() {
        int itemCount = this.table.getItemCount();
        String[] stdErrVars = new String[itemCount];
        int i = 0;
        while (i < itemCount) {
            TableItem item = this.table.getItem(i);
            stdErrVars[i] = item.getText(1);
            ++i;
        }
        return stdErrVars;
    }

    private String[] getResponseVariables() {
        int itemCount = this.table.getItemCount();
        String[] respVars = new String[itemCount];
        int i = 0;
        while (i < itemCount) {
            TableItem item = this.table.getItem(i);
            respVars[i] = item.getText(0);
            ++i;
        }
        return respVars;
    }

    protected Point getInitialSize() {
        return new Point(624, 732);
    }

    protected boolean isResizable() {
        return true;
    }
}

