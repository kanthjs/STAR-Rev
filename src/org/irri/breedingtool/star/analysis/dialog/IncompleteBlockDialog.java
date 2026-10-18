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
 *  org.eclipse.swt.widgets.Text
 */
package org.irri.breedingtool.star.analysis.dialog;

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
import org.eclipse.swt.widgets.Text;
import org.irri.breedingtool.application.handler.PartStackHandler;
import org.irri.breedingtool.application.model.ProjectExplorerTreeNodeModel;
import org.irri.breedingtool.datamanipulation.dialog.OperationProgressDialog;
import org.irri.breedingtool.manager.impl.DataManipulationManager;
import org.irri.breedingtool.pbtools.analysis.environment.dialog.SpecifyRowColDialog;
import org.irri.breedingtool.projectexplorer.view.ProjectExplorerView;
import org.irri.breedingtool.utility.StarAnalysisUtilities;
import org.irri.breedingtool.utility.WindowDialogControlUtil;

public class IncompleteBlockDialog
extends Dialog {
    private ProjectExplorerTreeNodeModel fileModel;
    private ArrayList<String> varInfo;
    private Combo designType;
    private List numVarList;
    private List factorVarList;
    private Button addEnvBtn;
    private Button addBlockBtn;
    private Button addRepBtn;
    private Button addRowBtn;
    private Button addColBtn;
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
    private List envVarList;
    private List genVarList;
    private List blockVarList;
    private List replicateVarList;
    private List rowVarList;
    private List columnVarList;
    private String[] numericVariables;
    private String[] factorVariables;
    private File file;
    private DataManipulationManager dataManipulationManager = new DataManipulationManager();
    private Button addGenBtn;
    private TabItem tbtmOptions;
    private Label lblLevelOfSignificance;
    private Label lblSpecifyControls;
    private Label levelsOfGenotypeLbl;
    private Label controlsLbl;
    private Label lblBlock;
    private Label lblReplicate;
    private Group variableGroup;
    private Label lblColumn;
    private Label lblRow;
    private int design = 0;
    private String[] respvars = new String[]{"Y1", "Y2"};
    private String environment = "Site";
    private String[] environmentLevels = new String[]{"Env1", "Env2"};
    private String genotype = "Gen";
    private String block = "Blk";
    private String rep = "NULL";
    private String row = "NULL";
    private String column = "NULL";
    private boolean descriptiveStat = true;
    private boolean varianceComponents = true;
    private boolean boxplotRawData = true;
    private boolean histogramRawData = true;
    private boolean heatmapResiduals = true;
    private String heatmapRow = null;
    private String heatmapColumn = null;
    private boolean diagnosticPlot = true;
    private boolean genotypeFixed = true;
    private String performPairwise;
    private String pairwiseAlpha = "0.05";
    private String[] genotypeLevels = new String[]{"1", "2", "3", "4", "5", "6", "7", "8"};
    private String[] controlLevels = new String[]{"1", "2", "3"};
    private boolean compareControl = true;
    private boolean performAllPairwise = false;
    private boolean genotypeRandom = false;
    private boolean excludeControls = true;
    private String[] controlLevels2 = new String[]{"1", "2", "3"};
    private boolean genoPhenoCorrelation = false;
    private Button checkBoxPlotBtn;
    private Button checkHistogramBtn;
    private Button checkHeatmapBtn;
    private Button checkDiagnosticPlotsBtn;
    private boolean satisfiedAllConditions = true;
    private boolean selectedHeatMapVars = false;
    private String analysisType;
    private Button specifyHeatMapVarsBtn;
    private String filePath = PartStackHandler.getActiveElementID();

    public IncompleteBlockDialog(Shell parentShell, String analysisType, File file) {
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
                WindowDialogControlUtil.removeWindowDialogToList(IncompleteBlockDialog.this.getShell());
            }
        });
        parent.getShell().setText("Incomplete Block Analysis: " + this.file.getName());
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
        this.designType.setItems(new String[]{"Balanced Incomplete Block Design", "Augmented RCB", "Augmented Latin Square", "Alpha-Lattice", "Row-Column"});
        this.designType.setLayoutData((Object)new GridData(4, 0x1000000, true, false, 3, 1));
        this.designType.select(0);
        this.designType.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                IncompleteBlockDialog.this.enableFactorButtons(false);
                if (IncompleteBlockDialog.this.designType.getSelectionIndex() == 0) {
                    IncompleteBlockDialog.this.lblBlock.setEnabled(true);
                    IncompleteBlockDialog.this.disable("replicate");
                    IncompleteBlockDialog.this.disable("row");
                    IncompleteBlockDialog.this.disable("column");
                } else if (IncompleteBlockDialog.this.designType.getSelectionIndex() == 1) {
                    IncompleteBlockDialog.this.lblBlock.setEnabled(true);
                    IncompleteBlockDialog.this.disable("replicate");
                    IncompleteBlockDialog.this.disable("row");
                    IncompleteBlockDialog.this.disable("column");
                } else if (IncompleteBlockDialog.this.designType.getSelectionIndex() == 2) {
                    IncompleteBlockDialog.this.lblRow.setEnabled(true);
                    IncompleteBlockDialog.this.lblColumn.setEnabled(true);
                    IncompleteBlockDialog.this.disable("replicate");
                    IncompleteBlockDialog.this.disable("block");
                } else if (IncompleteBlockDialog.this.designType.getSelectionIndex() == 3) {
                    IncompleteBlockDialog.this.lblReplicate.setEnabled(true);
                    IncompleteBlockDialog.this.lblBlock.setEnabled(true);
                    IncompleteBlockDialog.this.disable("row");
                    IncompleteBlockDialog.this.disable("column");
                } else if (IncompleteBlockDialog.this.designType.getSelectionIndex() == 4) {
                    IncompleteBlockDialog.this.lblRow.setEnabled(true);
                    IncompleteBlockDialog.this.lblColumn.setEnabled(true);
                    IncompleteBlockDialog.this.lblReplicate.setEnabled(true);
                    IncompleteBlockDialog.this.disable("block");
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
                IncompleteBlockDialog.this.numVarList.getSelection();
                IncompleteBlockDialog.this.dataManipulationManager.moveSelectedStringFromTo(IncompleteBlockDialog.this.numVarList, IncompleteBlockDialog.this.responseVarList);
                IncompleteBlockDialog.this.numVarList.remove(IncompleteBlockDialog.this.numVarList.getSelectionIndices());
            }

            public void mouseDown(MouseEvent e) {
                if (IncompleteBlockDialog.this.numVarList.getSelectionCount() > 0) {
                    IncompleteBlockDialog.this.factorVarList.setSelection(-1);
                    IncompleteBlockDialog.this.responseVarList.setSelection(-1);
                    IncompleteBlockDialog.this.addBtn.setText("Add");
                    IncompleteBlockDialog.this.moveBtn.setText("Set to Factor");
                    IncompleteBlockDialog.this.enableNumericButtons(true);
                    IncompleteBlockDialog.this.enableFactorButtons(false);
                }
            }

            public void mouseUp(MouseEvent e) {
            }
        });
        this.addBtn = new Button(modelComposite, 0);
        this.addBtn.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (IncompleteBlockDialog.this.numVarList.getSelectionCount() > 0) {
                    String[] selectedNumVars = IncompleteBlockDialog.this.numVarList.getSelection();
                    int i = 0;
                    while (i < selectedNumVars.length) {
                        IncompleteBlockDialog.this.responseVarList.add(selectedNumVars[i]);
                        ++i;
                    }
                    IncompleteBlockDialog.this.numVarList.remove(IncompleteBlockDialog.this.numVarList.getSelectionIndices());
                } else {
                    String[] selectedNumVars = IncompleteBlockDialog.this.responseVarList.getSelection();
                    int i = 0;
                    while (i < selectedNumVars.length) {
                        IncompleteBlockDialog.this.numVarList.add(selectedNumVars[i]);
                        ++i;
                    }
                    IncompleteBlockDialog.this.responseVarList.remove(IncompleteBlockDialog.this.responseVarList.getSelectionIndices());
                    IncompleteBlockDialog.this.resetHeatMap();
                }
                IncompleteBlockDialog.this.enableNumericButtons(false);
            }
        });
        this.addBtn.setEnabled(false);
        GridData gd_addBtn = new GridData(16384, 0x1000000, false, false, 2, 1);
        gd_addBtn.widthHint = 52;
        this.addBtn.setLayoutData((Object)gd_addBtn);
        this.addBtn.setText("Add");
        this.responseVarList = new List(modelComposite, 2562);
        GridData gd_responseVarList = new GridData(4, 4, true, false, 1, 1);
        gd_responseVarList.widthHint = 111;
        this.responseVarList.setLayoutData((Object)gd_responseVarList);
        this.responseVarList.addMouseListener(new MouseListener(){

            public void mouseDoubleClick(MouseEvent e) {
                IncompleteBlockDialog.this.dataManipulationManager.moveSelectedStringFromTo(IncompleteBlockDialog.this.responseVarList, IncompleteBlockDialog.this.numVarList);
                IncompleteBlockDialog.this.responseVarList.remove(IncompleteBlockDialog.this.responseVarList.getSelectionIndices());
            }

            public void mouseDown(MouseEvent e) {
                if (IncompleteBlockDialog.this.responseVarList.getSelectionCount() > 0) {
                    IncompleteBlockDialog.this.numVarList.setSelection(-1);
                    IncompleteBlockDialog.this.addBtn.setText("Remove");
                    IncompleteBlockDialog.this.addBtn.setEnabled(true);
                    IncompleteBlockDialog.this.moveBtn.setEnabled(false);
                    IncompleteBlockDialog.this.enableFactorButtons(false);
                }
            }

            public void mouseUp(MouseEvent e) {
            }
        });
        Label lblFactors = new Label(modelComposite, 0);
        lblFactors.setText("Factors:");
        this.moveBtn = new Button(modelComposite, 0);
        this.moveBtn.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (IncompleteBlockDialog.this.numVarList.getSelectionCount() > 0) {
                    String[] selectedNumVars = IncompleteBlockDialog.this.numVarList.getSelection();
                    int i = 0;
                    while (i < selectedNumVars.length) {
                        IncompleteBlockDialog.this.factorVarList.add(selectedNumVars[i]);
                        IncompleteBlockDialog.this.dataManipulationManager.editVariableType(IncompleteBlockDialog.this.file.getAbsolutePath(), IncompleteBlockDialog.this.varInfo, selectedNumVars[i], "Factor");
                        ++i;
                    }
                    IncompleteBlockDialog.this.numVarList.remove(IncompleteBlockDialog.this.numVarList.getSelectionIndices());
                    IncompleteBlockDialog.this.enableNumericButtons(false);
                } else {
                    String[] selectedNumVars = IncompleteBlockDialog.this.factorVarList.getSelection();
                    int i = 0;
                    while (i < selectedNumVars.length) {
                        IncompleteBlockDialog.this.numVarList.add(selectedNumVars[i]);
                        IncompleteBlockDialog.this.dataManipulationManager.editVariableType(IncompleteBlockDialog.this.file.getAbsolutePath(), IncompleteBlockDialog.this.varInfo, selectedNumVars[i], "Numeric");
                        ++i;
                    }
                    IncompleteBlockDialog.this.factorVarList.remove(IncompleteBlockDialog.this.factorVarList.getSelectionIndices());
                    IncompleteBlockDialog.this.enableFactorButtons(false);
                }
            }
        });
        this.moveBtn.setEnabled(false);
        GridData gd_moveBtn = new GridData(131072, 0x1000000, false, false, 1, 1);
        gd_moveBtn.heightHint = 24;
        gd_moveBtn.widthHint = 80;
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
        this.factorVarList.addListener(3, new Listener(){

            public void handleEvent(Event event) {
                if (IncompleteBlockDialog.this.factorVarList.getSelectionIndex() > -1) {
                    IncompleteBlockDialog.this.enableNumericButtons(false);
                    IncompleteBlockDialog.this.enableFactorButtons(false);
                    IncompleteBlockDialog.this.numVarList.setSelection(-1);
                    String[] s = IncompleteBlockDialog.this.factorVarList.getSelection();
                    String isNumeric = IncompleteBlockDialog.this.dataManipulationManager.isNumeric(IncompleteBlockDialog.this.file.getAbsolutePath().replaceAll(File.separator, "/"), s[0]);
                    if (isNumeric.equals("TRUE")) {
                        IncompleteBlockDialog.this.moveBtn.setText("Set to Numeric");
                        IncompleteBlockDialog.this.moveBtn.setEnabled(true);
                    } else {
                        IncompleteBlockDialog.this.moveBtn.setEnabled(false);
                    }
                    IncompleteBlockDialog.this.enableFactorButtons(true);
                }
            }
        });
        Group group = new Group(modelComposite, 0);
        group.setLayout((Layout)new GridLayout(3, false));
        GridData gd_group = new GridData(4, 4, true, false, 3, 1);
        gd_group.widthHint = 255;
        group.setLayoutData((Object)gd_group);
        this.addEnvBtn = new Button((Composite)group, 0);
        this.addEnvBtn.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (IncompleteBlockDialog.this.factorVarList.getSelectionCount() > 0) {
                    String[] selectedStrings = IncompleteBlockDialog.this.factorVarList.getSelection();
                    IncompleteBlockDialog.this.environmentLevels = DataManipulationManager.getEnvtLevels(selectedStrings[0], IncompleteBlockDialog.this.file);
                    IncompleteBlockDialog.this.dataManipulationManager.moveSelectedStringFromTo(IncompleteBlockDialog.this.factorVarList, IncompleteBlockDialog.this.envVarList);
                } else {
                    IncompleteBlockDialog.this.dataManipulationManager.moveSelectedStringFromTo(IncompleteBlockDialog.this.envVarList, IncompleteBlockDialog.this.factorVarList);
                }
                IncompleteBlockDialog.this.enableFactorButtons(false);
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
                IncompleteBlockDialog.this.dataManipulationManager.moveSelectedStringFromTo(IncompleteBlockDialog.this.envVarList, IncompleteBlockDialog.this.factorVarList);
                IncompleteBlockDialog.this.addEnvBtn.setEnabled(false);
            }

            public void mouseDown(MouseEvent e) {
                if (IncompleteBlockDialog.this.envVarList.getSelectionCount() > 0) {
                    IncompleteBlockDialog.this.factorVarList.setSelection(-1);
                    IncompleteBlockDialog.this.addEnvBtn.setText("Remove");
                    IncompleteBlockDialog.this.addEnvBtn.setEnabled(true);
                }
            }

            public void mouseUp(MouseEvent e) {
            }
        });
        this.addGenBtn = new Button((Composite)group, 0);
        this.addGenBtn.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (IncompleteBlockDialog.this.factorVarList.getSelectionCount() > 0) {
                    String[] selectedStrings = IncompleteBlockDialog.this.factorVarList.getSelection();
                    IncompleteBlockDialog.this.genotypeLevels = DataManipulationManager.getEnvtLevels(selectedStrings[0], IncompleteBlockDialog.this.file);
                    IncompleteBlockDialog.this.dataManipulationManager.moveSelectedStringFromTo(IncompleteBlockDialog.this.factorVarList, IncompleteBlockDialog.this.genVarList);
                    IncompleteBlockDialog.this.genLevelsList.setItems(IncompleteBlockDialog.this.genotypeLevels);
                    IncompleteBlockDialog.this.activatePerformPairwiseOptions(IncompleteBlockDialog.this.checkPerformPairwiseBtn.getSelection());
                } else {
                    IncompleteBlockDialog.this.dataManipulationManager.moveSelectedStringFromTo(IncompleteBlockDialog.this.genVarList, IncompleteBlockDialog.this.factorVarList);
                    IncompleteBlockDialog.this.genLevelsList.removeAll();
                    IncompleteBlockDialog.this.controlsList.removeAll();
                    IncompleteBlockDialog.this.resetGenotypeControls();
                }
                IncompleteBlockDialog.this.enableFactorButtons(false);
            }
        });
        this.addGenBtn.setText("Add");
        GridData gd_addGenBtn = new GridData(16384, 0x1000000, false, false, 1, 1);
        gd_addGenBtn.widthHint = 52;
        this.addGenBtn.setLayoutData((Object)gd_addGenBtn);
        Label lblNewLabel_2 = new Label((Composite)group, 0);
        lblNewLabel_2.setText("Factor:");
        this.genVarList = new List((Composite)group, 2048);
        GridData gd_genVarList = new GridData(4, 0x1000000, true, false, 1, 1);
        gd_genVarList.heightHint = 19;
        this.genVarList.setLayoutData((Object)gd_genVarList);
        this.genVarList.addMouseListener(new MouseListener(){

            public void mouseDoubleClick(MouseEvent e) {
                IncompleteBlockDialog.this.dataManipulationManager.moveSelectedStringFromTo(IncompleteBlockDialog.this.genVarList, IncompleteBlockDialog.this.factorVarList);
                IncompleteBlockDialog.this.genLevelsList.removeAll();
                IncompleteBlockDialog.this.controlsList.removeAll();
                IncompleteBlockDialog.this.resetGenotypeControls();
                IncompleteBlockDialog.this.addGenBtn.setEnabled(false);
            }

            public void mouseDown(MouseEvent e) {
                if (IncompleteBlockDialog.this.genVarList.getSelectionCount() > 0) {
                    IncompleteBlockDialog.this.factorVarList.setSelection(-1);
                    IncompleteBlockDialog.this.addGenBtn.setText("Remove");
                    IncompleteBlockDialog.this.addGenBtn.setEnabled(true);
                }
            }

            public void mouseUp(MouseEvent e) {
            }
        });
        this.variableGroup = new Group(modelComposite, 0);
        this.variableGroup.setLayout((Layout)new GridLayout(3, false));
        GridData gd_group_1 = new GridData(4, 4, true, true, 3, 1);
        gd_group_1.heightHint = 135;
        this.variableGroup.setLayoutData((Object)gd_group_1);
        this.addBlockBtn = new Button((Composite)this.variableGroup, 0);
        this.addBlockBtn.setEnabled(false);
        this.addBlockBtn.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (IncompleteBlockDialog.this.factorVarList.getSelectionCount() > 0) {
                    IncompleteBlockDialog.this.dataManipulationManager.moveSelectedStringFromTo(IncompleteBlockDialog.this.factorVarList, IncompleteBlockDialog.this.blockVarList);
                } else {
                    IncompleteBlockDialog.this.dataManipulationManager.moveSelectedStringFromTo(IncompleteBlockDialog.this.blockVarList, IncompleteBlockDialog.this.factorVarList);
                }
                IncompleteBlockDialog.this.enableFactorButtons(false);
            }
        });
        this.addBlockBtn.setText("Add");
        GridData gd_addBlockBtn = new GridData(16384, 0x1000000, false, false, 1, 1);
        gd_addBlockBtn.widthHint = 52;
        this.addBlockBtn.setLayoutData((Object)gd_addBlockBtn);
        this.lblBlock = new Label((Composite)this.variableGroup, 0);
        this.lblBlock.setText("Block:");
        this.blockVarList = new List((Composite)this.variableGroup, 2048);
        GridData gd_blockVarList = new GridData(4, 0x1000000, true, false, 1, 1);
        gd_blockVarList.heightHint = 25;
        this.blockVarList.setLayoutData((Object)gd_blockVarList);
        this.blockVarList.addMouseListener(new MouseListener(){

            public void mouseDoubleClick(MouseEvent e) {
                IncompleteBlockDialog.this.dataManipulationManager.moveSelectedStringFromTo(IncompleteBlockDialog.this.blockVarList, IncompleteBlockDialog.this.factorVarList);
                IncompleteBlockDialog.this.addBlockBtn.setEnabled(false);
            }

            public void mouseDown(MouseEvent e) {
                if (IncompleteBlockDialog.this.blockVarList.getSelectionCount() > 0) {
                    IncompleteBlockDialog.this.factorVarList.setSelection(-1);
                    IncompleteBlockDialog.this.addBlockBtn.setText("Remove");
                    IncompleteBlockDialog.this.addBlockBtn.setEnabled(true);
                }
            }

            public void mouseUp(MouseEvent e) {
            }
        });
        this.addRepBtn = new Button((Composite)this.variableGroup, 0);
        this.addRepBtn.setEnabled(false);
        this.addRepBtn.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (IncompleteBlockDialog.this.factorVarList.getSelectionCount() > 0) {
                    IncompleteBlockDialog.this.dataManipulationManager.moveSelectedStringFromTo(IncompleteBlockDialog.this.factorVarList, IncompleteBlockDialog.this.replicateVarList);
                } else {
                    IncompleteBlockDialog.this.dataManipulationManager.moveSelectedStringFromTo(IncompleteBlockDialog.this.replicateVarList, IncompleteBlockDialog.this.factorVarList);
                }
                IncompleteBlockDialog.this.enableFactorButtons(false);
            }
        });
        this.addRepBtn.setText("Add");
        GridData gd_addRepBtn = new GridData(16384, 0x1000000, false, false, 1, 1);
        gd_addRepBtn.widthHint = 52;
        this.addRepBtn.setLayoutData((Object)gd_addRepBtn);
        this.lblReplicate = new Label((Composite)this.variableGroup, 0);
        this.lblReplicate.setEnabled(false);
        this.lblReplicate.setText("Replicate:");
        this.replicateVarList = new List((Composite)this.variableGroup, 2048);
        GridData gd_replicateVarList = new GridData(4, 0x1000000, true, false, 1, 1);
        gd_replicateVarList.heightHint = 25;
        this.replicateVarList.setLayoutData((Object)gd_replicateVarList);
        this.replicateVarList.addMouseListener(new MouseListener(){

            public void mouseDoubleClick(MouseEvent e) {
                IncompleteBlockDialog.this.dataManipulationManager.moveSelectedStringFromTo(IncompleteBlockDialog.this.replicateVarList, IncompleteBlockDialog.this.factorVarList);
                IncompleteBlockDialog.this.addRepBtn.setEnabled(false);
            }

            public void mouseDown(MouseEvent e) {
                if (IncompleteBlockDialog.this.replicateVarList.getSelectionCount() > 0) {
                    IncompleteBlockDialog.this.factorVarList.setSelection(-1);
                    IncompleteBlockDialog.this.addRepBtn.setText("Remove");
                    IncompleteBlockDialog.this.addRepBtn.setEnabled(true);
                }
            }

            public void mouseUp(MouseEvent e) {
            }
        });
        this.addRowBtn = new Button((Composite)this.variableGroup, 0);
        this.addRowBtn.setEnabled(false);
        this.addRowBtn.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (IncompleteBlockDialog.this.factorVarList.getSelectionCount() > 0) {
                    IncompleteBlockDialog.this.dataManipulationManager.moveSelectedStringFromTo(IncompleteBlockDialog.this.factorVarList, IncompleteBlockDialog.this.rowVarList);
                } else {
                    IncompleteBlockDialog.this.dataManipulationManager.moveSelectedStringFromTo(IncompleteBlockDialog.this.rowVarList, IncompleteBlockDialog.this.factorVarList);
                }
                IncompleteBlockDialog.this.enableFactorButtons(false);
            }
        });
        this.addRowBtn.setText("Add");
        GridData gd_addRowBtn = new GridData(16384, 0x1000000, false, false, 1, 1);
        gd_addRowBtn.widthHint = 52;
        this.addRowBtn.setLayoutData((Object)gd_addRowBtn);
        this.lblRow = new Label((Composite)this.variableGroup, 0);
        this.lblRow.setEnabled(false);
        this.lblRow.setText("Row:");
        this.rowVarList = new List((Composite)this.variableGroup, 2048);
        GridData gd_rowVarList = new GridData(4, 0x1000000, true, false, 1, 1);
        gd_rowVarList.heightHint = 25;
        this.rowVarList.setLayoutData((Object)gd_rowVarList);
        this.rowVarList.addMouseListener(new MouseListener(){

            public void mouseDoubleClick(MouseEvent e) {
                IncompleteBlockDialog.this.dataManipulationManager.moveSelectedStringFromTo(IncompleteBlockDialog.this.rowVarList, IncompleteBlockDialog.this.factorVarList);
                IncompleteBlockDialog.this.addRowBtn.setEnabled(false);
            }

            public void mouseDown(MouseEvent e) {
                if (IncompleteBlockDialog.this.rowVarList.getSelectionCount() > 0) {
                    IncompleteBlockDialog.this.factorVarList.setSelection(-1);
                    IncompleteBlockDialog.this.addRowBtn.setText("Remove");
                    IncompleteBlockDialog.this.addRowBtn.setEnabled(true);
                }
            }

            public void mouseUp(MouseEvent e) {
            }
        });
        this.addColBtn = new Button((Composite)this.variableGroup, 0);
        this.addColBtn.setEnabled(false);
        this.addColBtn.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (IncompleteBlockDialog.this.factorVarList.getSelectionCount() > 0) {
                    IncompleteBlockDialog.this.dataManipulationManager.moveSelectedStringFromTo(IncompleteBlockDialog.this.factorVarList, IncompleteBlockDialog.this.columnVarList);
                } else {
                    IncompleteBlockDialog.this.dataManipulationManager.moveSelectedStringFromTo(IncompleteBlockDialog.this.columnVarList, IncompleteBlockDialog.this.factorVarList);
                }
                IncompleteBlockDialog.this.enableFactorButtons(false);
            }
        });
        this.addColBtn.setText("Add");
        GridData gd_addColBtn = new GridData(16384, 0x1000000, false, false, 1, 1);
        gd_addColBtn.widthHint = 52;
        this.addColBtn.setLayoutData((Object)gd_addColBtn);
        this.lblColumn = new Label((Composite)this.variableGroup, 0);
        this.lblColumn.setEnabled(false);
        this.lblColumn.setText("Column:");
        this.columnVarList = new List((Composite)this.variableGroup, 2048);
        GridData gd_columnVarList = new GridData(4, 0x1000000, true, false, 1, 1);
        gd_columnVarList.heightHint = 25;
        this.columnVarList.setLayoutData((Object)gd_columnVarList);
        this.columnVarList.addMouseListener(new MouseListener(){

            public void mouseDoubleClick(MouseEvent e) {
                IncompleteBlockDialog.this.dataManipulationManager.moveSelectedStringFromTo(IncompleteBlockDialog.this.columnVarList, IncompleteBlockDialog.this.factorVarList);
                IncompleteBlockDialog.this.addColBtn.setEnabled(false);
            }

            public void mouseDown(MouseEvent e) {
                if (IncompleteBlockDialog.this.columnVarList.getSelectionCount() > 0) {
                    IncompleteBlockDialog.this.factorVarList.setSelection(-1);
                    IncompleteBlockDialog.this.addColBtn.setText("Remove");
                    IncompleteBlockDialog.this.addColBtn.setEnabled(true);
                }
            }

            public void mouseUp(MouseEvent e) {
            }
        });
        this.tbtmOptions = new TabItem(tabFolder, 0);
        this.tbtmOptions.setText("Options");
        Composite composite_1 = new Composite((Composite)tabFolder, 0);
        this.tbtmOptions.setControl((Control)composite_1);
        composite_1.setBackground(this.getShell().getBackground());
        composite_1.setLayout((Layout)new GridLayout(9, false));
        new Label(composite_1, 0);
        this.checkPerformPairwiseBtn = new Button(composite_1, 32);
        this.checkPerformPairwiseBtn.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (IncompleteBlockDialog.this.checkPerformPairwiseBtn.getSelection()) {
                    IncompleteBlockDialog.this.activatePerformPairwiseOptions(true);
                    if (IncompleteBlockDialog.this.compareWithControlsBtn.getSelection()) {
                        IncompleteBlockDialog.this.activateLevelOfConrolsOptions(true);
                    }
                } else {
                    IncompleteBlockDialog.this.activatePerformPairwiseOptions(false);
                }
            }
        });
        this.checkPerformPairwiseBtn.setLayoutData((Object)new GridData(16384, 0x1000000, false, false, 8, 1));
        this.checkPerformPairwiseBtn.setText("Perform pairwise mean comparisons");
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        this.compareWithControlsBtn = new Button(composite_1, 16);
        this.compareWithControlsBtn.setEnabled(false);
        this.compareWithControlsBtn.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (IncompleteBlockDialog.this.compareWithControlsBtn.getSelection()) {
                    IncompleteBlockDialog.this.activateLevelOfConrolsOptions(true);
                }
            }
        });
        this.compareWithControlsBtn.setLayoutData((Object)new GridData(16384, 0x1000000, false, false, 7, 1));
        this.compareWithControlsBtn.setText("Compare with control(s)");
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        this.performAllComparisonsBtn = new Button(composite_1, 16);
        this.performAllComparisonsBtn.setEnabled(false);
        this.performAllComparisonsBtn.setLayoutData((Object)new GridData(16384, 0x1000000, false, false, 7, 1));
        this.performAllComparisonsBtn.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                IncompleteBlockDialog.this.activateLevelOfConrolsOptions(false);
            }
        });
        this.performAllComparisonsBtn.setText("Perform all comparisons");
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        this.lblLevelOfSignificance = new Label(composite_1, 0);
        this.lblLevelOfSignificance.setEnabled(false);
        this.lblLevelOfSignificance.setLayoutData((Object)new GridData(131072, 0x1000000, false, false, 3, 1));
        this.lblLevelOfSignificance.setText("Level of Significance:");
        this.significanceLevel = new Text(composite_1, 2048);
        this.significanceLevel.setEnabled(false);
        this.significanceLevel.setText("0.05");
        GridData gd_significanceLevel = new GridData(4, 0x1000000, false, false, 1, 1);
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
        this.lblSpecifyControls.setEnabled(false);
        this.lblSpecifyControls.setLayoutData((Object)new GridData(16384, 0x1000000, false, false, 9, 1));
        this.lblSpecifyControls.setText("Specify Control(s)");
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        this.levelsOfGenotypeLbl = new Label(composite_1, 0);
        this.levelsOfGenotypeLbl.setEnabled(false);
        this.levelsOfGenotypeLbl.setLayoutData((Object)new GridData(16384, 0x1000000, false, false, 3, 1));
        this.levelsOfGenotypeLbl.setText("Levels of Genotype:");
        this.controlsLbl = new Label(composite_1, 0);
        this.controlsLbl.setEnabled(false);
        this.controlsLbl.setText("Control(s):");
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        this.genLevelsList = new List(composite_1, 2562);
        this.genLevelsList.setEnabled(false);
        GridData gd_genLevelsList = new GridData(4, 4, false, true, 1, 3);
        gd_genLevelsList.widthHint = 128;
        this.genLevelsList.setLayoutData((Object)gd_genLevelsList);
        this.genLevelsList.addMouseListener(new MouseListener(){

            public void mouseDoubleClick(MouseEvent e) {
                IncompleteBlockDialog.this.dataManipulationManager.moveSelectedStringFromTo(IncompleteBlockDialog.this.genLevelsList, IncompleteBlockDialog.this.controlsList);
                IncompleteBlockDialog.this.addControlsBtn.setEnabled(false);
            }

            public void mouseDown(MouseEvent e) {
                if (IncompleteBlockDialog.this.genLevelsList.getSelectionIndex() > -1) {
                    IncompleteBlockDialog.this.addControlsBtn.setEnabled(true);
                    IncompleteBlockDialog.this.controlsList.setSelection(-1);
                    IncompleteBlockDialog.this.genLevelsList.getSelection();
                    IncompleteBlockDialog.this.addControlsBtn.setText("Add");
                    IncompleteBlockDialog.this.addControlsBtn.setEnabled(true);
                }
            }

            public void mouseUp(MouseEvent e) {
            }
        });
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        this.controlsList = new List(composite_1, 2050);
        this.controlsList.setEnabled(false);
        GridData gd_controlsList = new GridData(4, 4, false, true, 2, 3);
        gd_controlsList.widthHint = 125;
        this.controlsList.setLayoutData((Object)gd_controlsList);
        this.controlsList.addMouseListener(new MouseListener(){

            public void mouseDoubleClick(MouseEvent e) {
                IncompleteBlockDialog.this.dataManipulationManager.moveSelectedStringFromTo(IncompleteBlockDialog.this.controlsList, IncompleteBlockDialog.this.genLevelsList);
                IncompleteBlockDialog.this.addControlsBtn.setEnabled(false);
            }

            public void mouseDown(MouseEvent e) {
                if (IncompleteBlockDialog.this.controlsList.getSelectionIndex() > -1) {
                    IncompleteBlockDialog.this.addControlsBtn.setEnabled(true);
                    IncompleteBlockDialog.this.genLevelsList.setSelection(-1);
                    IncompleteBlockDialog.this.controlsList.getSelection();
                    IncompleteBlockDialog.this.addControlsBtn.setText("Remove");
                    IncompleteBlockDialog.this.addControlsBtn.setEnabled(true);
                }
            }

            public void mouseUp(MouseEvent e) {
            }
        });
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        this.addControlsBtn = new Button(composite_1, 0);
        this.addControlsBtn.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (IncompleteBlockDialog.this.genLevelsList.getSelectionCount() > 0) {
                    String[] selectedNumVars = IncompleteBlockDialog.this.genLevelsList.getSelection();
                    int i = 0;
                    while (i < selectedNumVars.length) {
                        IncompleteBlockDialog.this.controlsList.add(selectedNumVars[i]);
                        ++i;
                    }
                    IncompleteBlockDialog.this.genLevelsList.remove(IncompleteBlockDialog.this.genLevelsList.getSelectionIndices());
                    IncompleteBlockDialog.this.addControlsBtn.setEnabled(false);
                } else {
                    String[] selectedNumVars = IncompleteBlockDialog.this.controlsList.getSelection();
                    int i = 0;
                    while (i < selectedNumVars.length) {
                        IncompleteBlockDialog.this.genLevelsList.add(selectedNumVars[i]);
                        ++i;
                    }
                    IncompleteBlockDialog.this.controlsList.remove(IncompleteBlockDialog.this.controlsList.getSelectionIndices());
                }
                IncompleteBlockDialog.this.addControlsBtn.setEnabled(false);
            }
        });
        this.addControlsBtn.setEnabled(false);
        GridData gd_addControlsBtn = new GridData(0x1000000, 0x1000000, false, false, 2, 1);
        gd_addControlsBtn.widthHint = 71;
        this.addControlsBtn.setLayoutData((Object)gd_addControlsBtn);
        this.addControlsBtn.setText("Add");
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        Label lblNewLabel_3 = new Label(composite_1, 0);
        lblNewLabel_3.setLayoutData((Object)new GridData(16384, 0x1000000, false, false, 9, 1));
        lblNewLabel_3.setText("Display:");
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        this.checkDescriptiveStatistics = new Button(composite_1, 32);
        this.checkDescriptiveStatistics.setLayoutData((Object)new GridData(16384, 0x1000000, false, false, 7, 1));
        this.checkDescriptiveStatistics.setText("Descriptive Statistics");
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        this.checkVarianceComponents = new Button(composite_1, 32);
        this.checkVarianceComponents.setLayoutData((Object)new GridData(16384, 0x1000000, false, false, 7, 1));
        this.checkVarianceComponents.setText("Variance Components");
        TabItem tbtmGraph_1 = new TabItem(tabFolder, 0);
        tbtmGraph_1.setText("Graph");
        Composite composite = new Composite((Composite)tabFolder, 0);
        tbtmGraph_1.setControl((Control)composite);
        composite.setBackground(this.getShell().getBackground());
        composite.setLayout((Layout)new GridLayout(2, false));
        this.checkBoxPlotBtn = new Button(composite, 32);
        this.checkBoxPlotBtn.setLayoutData((Object)new GridData(16384, 0x1000000, true, false, 1, 2));
        this.checkBoxPlotBtn.setText("Boxplot of the raw data");
        new Label(composite, 0);
        new Label(composite, 0);
        this.checkHistogramBtn = new Button(composite, 32);
        GridData gd_checkHistogramBtn = new GridData(16384, 0x1000000, true, false, 1, 1);
        gd_checkHistogramBtn.widthHint = 156;
        this.checkHistogramBtn.setLayoutData((Object)gd_checkHistogramBtn);
        this.checkHistogramBtn.setText("Histogram of the raw data");
        new Label(composite, 0);
        this.checkHeatmapBtn = new Button(composite, 32);
        this.checkHeatmapBtn.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (!IncompleteBlockDialog.this.checkHeatmapBtn.getSelection()) {
                    IncompleteBlockDialog.this.specifyHeatMapVarsBtn.setEnabled(false);
                    IncompleteBlockDialog.this.resetHeatMap();
                } else {
                    IncompleteBlockDialog.this.specifyHeatMapVarsBtn.setEnabled(true);
                }
            }
        });
        this.checkHeatmapBtn.setLayoutData((Object)new GridData(16384, 0x1000000, true, false, 1, 2));
        this.checkHeatmapBtn.setText("Heatmap of the residuals");
        this.specifyHeatMapVarsBtn = new Button(composite, 0);
        this.specifyHeatMapVarsBtn.setEnabled(false);
        this.specifyHeatMapVarsBtn.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                SpecifyRowColDialog rowColDialog = new SpecifyRowColDialog(IncompleteBlockDialog.this.getShell(), IncompleteBlockDialog.this.dataManipulationManager.getListItems(IncompleteBlockDialog.this.varInfo));
                rowColDialog.open();
                if (rowColDialog.getReturnCode() == 0) {
                    IncompleteBlockDialog.this.heatmapRow = rowColDialog.getHeatRow();
                    IncompleteBlockDialog.this.heatmapColumn = rowColDialog.getHeatColumn();
                    IncompleteBlockDialog.this.selectedHeatMapVars = true;
                } else {
                    IncompleteBlockDialog.this.selectedHeatMapVars = false;
                }
            }
        });
        GridData gd_specifyHeatMapVarsBtn = new GridData(16384, 0x1000000, true, false, 1, 2);
        gd_specifyHeatMapVarsBtn.widthHint = 157;
        this.specifyHeatMapVarsBtn.setLayoutData((Object)gd_specifyHeatMapVarsBtn);
        this.specifyHeatMapVarsBtn.setText("Specify Row/Column");
        this.checkDiagnosticPlotsBtn = new Button(composite, 32);
        this.checkDiagnosticPlotsBtn.setLayoutData((Object)new GridData(16384, 0x1000000, true, false, 1, 1));
        this.checkDiagnosticPlotsBtn.setText("Diagnostic plots");
        new Label(composite, 0);
        return container;
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
            this.lblReplicate.setEnabled(false);
            if (this.replicateVarList.getItemCount() > 0) {
                this.factorVarList.add(this.replicateVarList.getItem(0));
            }
            this.replicateVarList.removeAll();
            this.addRepBtn.setEnabled(false);
        } else if (string.equals("block")) {
            this.lblBlock.setEnabled(false);
            if (this.blockVarList.getItemCount() > 0) {
                this.factorVarList.add(this.blockVarList.getItem(0));
            }
            this.blockVarList.removeAll();
            this.addBlockBtn.setEnabled(false);
        } else if (string.equals("row")) {
            this.lblRow.setEnabled(false);
            if (this.rowVarList.getItemCount() > 0) {
                this.factorVarList.add(this.rowVarList.getItem(0));
            }
            this.rowVarList.removeAll();
            this.addRowBtn.setEnabled(false);
        } else if (string.equals("column")) {
            this.lblColumn.setEnabled(false);
            if (this.columnVarList.getItemCount() > 0) {
                this.factorVarList.add(this.columnVarList.getItem(0));
            }
            this.columnVarList.removeAll();
            this.addColBtn.setEnabled(false);
        }
    }

    protected void activateFixedOptions(boolean state) {
        this.checkPerformPairwiseBtn.setEnabled(state);
        this.activatePerformPairwiseOptions(state);
        if (state) {
            this.checkPerformPairwiseBtn.setSelection(state);
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
            }
        }
    }

    protected void activateRandomOptions(boolean state) {
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
        this.replicateVarList.setSelection(-1);
        this.blockVarList.setSelection(-1);
        this.envVarList.setSelection(-1);
        this.genVarList.setSelection(-1);
        this.rowVarList.setSelection(-1);
        this.columnVarList.setSelection(-1);
        if (state) {
            this.addBlockBtn.setText("Add");
            this.addRepBtn.setText("Add");
            this.addGenBtn.setText("Add");
            this.addRowBtn.setText("Add");
            this.addColBtn.setText("Add");
            this.addEnvBtn.setText("Add");
            if (this.envVarList.getItemCount() < 1) {
                this.addEnvBtn.setEnabled(state);
            }
            if (this.blockVarList.getItemCount() < 1 && this.lblBlock.getEnabled()) {
                this.addBlockBtn.setEnabled(state);
            }
            if (this.genVarList.getItemCount() < 1) {
                this.addGenBtn.setEnabled(state);
            }
            if (this.replicateVarList.getItemCount() < 1 && this.lblReplicate.getEnabled()) {
                this.addRepBtn.setEnabled(state);
            }
            if (this.rowVarList.getItemCount() < 1 && this.lblRow.getEnabled()) {
                this.addRowBtn.setEnabled(state);
            }
            if (this.columnVarList.getItemCount() < 1 && this.lblColumn.getEnabled()) {
                this.addColBtn.setEnabled(state);
            }
        } else {
            this.addBlockBtn.setEnabled(state);
            this.addRepBtn.setEnabled(state);
            this.addGenBtn.setEnabled(state);
            this.addRowBtn.setEnabled(state);
            this.addColBtn.setEnabled(state);
            this.addEnvBtn.setEnabled(state);
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
        this.design = this.designType.getSelectionIndex();
        String designType = null;
        switch (this.design) {
            case 0: {
                designType = "BIBD";
                break;
            }
            case 1: {
                designType = "AugRCB";
                break;
            }
            case 2: {
                designType = "AugLS";
                break;
            }
            case 3: {
                designType = "Alpha";
                break;
            }
            case 4: {
                designType = "RowCol";
            }
        }
        this.descriptiveStat = this.checkDescriptiveStatistics.getSelection();
        this.varianceComponents = this.checkVarianceComponents.getSelection();
        this.boxplotRawData = this.checkBoxPlotBtn.getSelection();
        this.histogramRawData = this.checkHistogramBtn.getSelection();
        this.heatmapResiduals = this.checkHeatmapBtn.getSelection();
        this.diagnosticPlot = this.checkDiagnosticPlotsBtn.getSelection();
        this.satisfiedAllConditions = true;
        if (this.responseVarList.getItemCount() < 1) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"Please enter a response variable.");
        } else if (this.genVarList.getItemCount() < 1) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"Please enter a genotype variable.");
        } else if (!this.genotypeFixed && !this.genotypeRandom) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"Please specify whether the genotype variable is fixed or random.");
        } else if (this.lblBlock.getEnabled() && this.blockVarList.getItemCount() < 1) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"Please enter a block variable.");
        } else if (this.lblReplicate.getEnabled() && this.replicateVarList.getItemCount() < 1) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"Please enter a replicate variable.");
        } else if (this.lblRow.getEnabled() && this.rowVarList.getItemCount() < 1) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"Please enter a row variable.");
        } else if (this.lblColumn.getEnabled() && this.columnVarList.getItemCount() < 1) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"Please enter a column variable.");
        } else if (this.heatmapResiduals && !this.selectedHeatMapVars) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"Please specify the heatmap rows/colums.");
        } else {
            this.performPairwise = this.checkPerformPairwiseBtn.getSelection() ? (this.compareWithControlsBtn.getSelection() ? "control" : "all") : "null";
            if (this.checkPerformPairwiseBtn.getSelection()) {
                this.pairwiseAlpha = this.significanceLevel.getText();
                if (this.pairwiseAlpha.equals("")) {
                    MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"Please enter a value for the pairwise level of significance.");
                    this.satisfiedAllConditions = false;
                } else {
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
            }
            if (this.satisfiedAllConditions) {
                if (this.lblBlock.getEnabled()) {
                    this.block = this.blockVarList.getItem(0);
                }
                if (this.lblReplicate.getEnabled()) {
                    this.rep = this.replicateVarList.getItem(0);
                }
                if (this.lblRow.getEnabled()) {
                    this.row = this.rowVarList.getItem(0);
                }
                if (this.lblColumn.getEnabled()) {
                    this.column = this.columnVarList.getItem(0);
                }
                this.respvars = this.responseVarList.getItems();
                this.environment = this.envVarList.getItemCount() < 1 ? "" : this.envVarList.getItem(0);
                this.genotype = this.genVarList.getItem(0);
                String dataFileName = this.file.getAbsolutePath().toString().replaceAll(File.separator, "/");
                File outputFolder = new File(StarAnalysisUtilities.createOutputFolder(new File(this.filePath).getName(), "Incomplete Block"));
                OperationProgressDialog rInfo = new OperationProgressDialog(this.getShell(), "Incomplete Block Analysis");
                rInfo.open();
                try {
                    if (this.environment.equals("")) {
                        this.environment = null;
                    }
                    if (this.genotype.equals("")) {
                        this.genotype = null;
                    }
                    if (this.block.equals("")) {
                        this.block = null;
                    }
                    if (this.rep.equals("")) {
                        this.rep = null;
                    }
                    if (this.row.equals("")) {
                        this.row = null;
                    }
                    if (this.column.equals("")) {
                        this.column = null;
                    }
                    ProjectExplorerView.rJavaManager.getSTARAnalysisManager().doUnbalancedAnalysis(dataFileName, String.valueOf(outputFolder.getAbsolutePath().replace(File.separator, "/")) + "/", designType, this.respvars, this.environment, this.genotype, this.block, this.rep, this.row, this.column, this.descriptiveStat, this.varianceComponents, this.performPairwise, Double.parseDouble(this.significanceLevel.getText()), this.controlLevels, this.boxplotRawData, this.histogramRawData, this.heatmapResiduals, this.heatmapRow, this.heatmapColumn, this.diagnosticPlot);
                    StarAnalysisUtilities.testVarArgs(dataFileName, outputFolder.getAbsolutePath().replace(File.separator, "/"), designType, this.respvars, this.environment, this.genotype, this.block, this.rep, this.row, this.column, this.descriptiveStat, this.varianceComponents, this.performPairwise, Double.parseDouble(this.significanceLevel.getText()), this.controlLevels, this.boxplotRawData, this.histogramRawData, this.heatmapResiduals, this.heatmapRow, this.heatmapColumn, this.diagnosticPlot);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                rInfo.close();
                WindowDialogControlUtil.hideAllWindowDialog();
                StarAnalysisUtilities.openAndRefreshFolder(outputFolder.getAbsolutePath());
            }
        }
    }

    protected Point getInitialSize() {
        return new Point(643, 695);
    }

    protected boolean isResizable() {
        return true;
    }
}

