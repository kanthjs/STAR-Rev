/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.jface.dialogs.Dialog
 *  org.eclipse.jface.dialogs.IDialogConstants
 *  org.eclipse.jface.dialogs.MessageDialog
 *  org.eclipse.swt.events.MouseEvent
 *  org.eclipse.swt.events.MouseListener
 *  org.eclipse.swt.events.SelectionAdapter
 *  org.eclipse.swt.events.SelectionEvent
 *  org.eclipse.swt.events.SelectionListener
 *  org.eclipse.swt.graphics.Point
 *  org.eclipse.swt.layout.GridData
 *  org.eclipse.swt.layout.GridLayout
 *  org.eclipse.swt.widgets.Button
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
package org.irri.breedingtool.star.analysis.dialog;

import java.io.File;
import java.util.ArrayList;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
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
import org.irri.breedingtool.application.handler.PartStackHandler;
import org.irri.breedingtool.datamanipulation.dialog.OperationProgressDialog;
import org.irri.breedingtool.manager.impl.DataManipulationManager;
import org.irri.breedingtool.pbtools.analysis.environment.dialog.SpecifyRowColDialog;
import org.irri.breedingtool.projectexplorer.view.ProjectExplorerView;
import org.irri.breedingtool.utility.DialogFormUtility;
import org.irri.breedingtool.utility.DialogListValidator;
import org.irri.breedingtool.utility.StarAnalysisUtilities;

public class AugmentedRCBIncompleteBlockDialog
extends Dialog {
    private DialogFormUtility listManager = new DialogFormUtility();
    private String filePath = PartStackHandler.getActiveElementID();
    private Button btnOk;
    private List lstVariables;
    private ArrayList<Composite> groups = new ArrayList();
    private Button btnAddTrt;
    private Button btnAddSingleVariable;
    private Button btnSetToFactor;
    private List lstNumericVariables;
    private List lstFactorVariables;
    private Text txtAnalysisPerSet;
    private Text txtTrt;
    private Text txtBlk;
    private Text txtSigLevel;
    private Button btnVarComponents;
    private Button btnStatistics;
    private Button btnBoxplot;
    private Button btnHistogram;
    private Button btnHeatmap;
    private Button btnSpecifyRowCol;
    private Button btnDiagnosticplots;
    private List lstControls;
    private List lstLevelsofFactor;
    private Button btnAddLevelsofFactor;
    private Button btnAddBlk;
    private Button btnAddSet;
    private DataManipulationManager dataManipulationManager = new DataManipulationManager();
    private ArrayList<String> varInfo;
    private String[] numericVariables;
    private String[] factorVariables;
    private File file;
    private String heatmapRow;
    private String heatmapColumn;
    private boolean selectedHeatMapVars;
    private Button btnPerformPairwiseMean;
    private Button btnAllComparisons;
    private Button btnCompareWithControl;
    private Label lblLevelOfSignificance;
    private String pairwiseAlpha = "0.05";
    private boolean satisfiedAllConditions = true;
    private boolean compareControl = true;
    private boolean performAllPairwise = false;
    private String[] factorLevels = new String[]{"1", "2", "3", "4", "5", "6", "7", "8"};
    private String[] controlLevels = new String[]{"1", "2", "3"};
    private Label lblSpecifyControl;
    private Label lblLevelsofFactor;
    private Label lblControls;

    public AugmentedRCBIncompleteBlockDialog(Shell parentShell) {
        super(parentShell);
        this.setShellStyle(2288);
    }

    protected void configureShell(Shell shell) {
        super.configureShell(shell);
        shell.setText("Augmented RCB: " + new File(this.filePath).getName());
    }

    protected Control createDialogArea(Composite parent) {
        Composite container = (Composite)super.createDialogArea(parent);
        GridLayout gridLayout = (GridLayout)container.getLayout();
        gridLayout.marginHeight = 10;
        GridLayout gl_container = new GridLayout(1, false);
        gl_container.marginWidth = 10;
        container.setLayout((Layout)gl_container);
        TabFolder tabFolder = new TabFolder(container, 0);
        tabFolder.setLayoutData((Object)new GridData(4, 4, true, true, 1, 1));
        TabItem tbtmVariableDescription = new TabItem(tabFolder, 0);
        tbtmVariableDescription.setText("Model Specifications");
        Composite composite = new Composite((Composite)tabFolder, 0);
        tbtmVariableDescription.setControl((Control)composite);
        composite.setLayout((Layout)new GridLayout(4, false));
        Label lblNumericVariables = new Label(composite, 0);
        lblNumericVariables.setLayoutData((Object)new GridData(16384, 0x1000000, true, false, 1, 1));
        lblNumericVariables.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        lblNumericVariables.setText("Numeric Variable(s):");
        new Label(composite, 0);
        Label lblResponseVariables = new Label(composite, 0);
        lblResponseVariables.setLayoutData((Object)new GridData(16384, 0x1000000, true, false, 2, 1));
        lblResponseVariables.setText("Response Variable(s):");
        lblResponseVariables.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        this.lstNumericVariables = new List(composite, 2818);
        GridData gd_lstNumericVariables_1 = new GridData(4, 4, true, true, 1, 1);
        gd_lstNumericVariables_1.heightHint = 80;
        gd_lstNumericVariables_1.widthHint = 150;
        this.lstNumericVariables.setLayoutData((Object)gd_lstNumericVariables_1);
        this.btnAddSingleVariable = new Button(composite, 0);
        GridData gd_btnAddSingleVariable = new GridData(4, 0x1000000, false, true, 1, 1);
        gd_btnAddSingleVariable.widthHint = 60;
        this.btnAddSingleVariable.setLayoutData((Object)gd_btnAddSingleVariable);
        this.btnAddSingleVariable.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        this.btnAddSingleVariable.setText("Add");
        this.lstVariables = new List(composite, 2818);
        GridData gd_lstVariables = new GridData(4, 4, true, true, 2, 1);
        gd_lstVariables.widthHint = 150;
        gd_lstVariables.heightHint = 80;
        this.lstVariables.setLayoutData((Object)gd_lstVariables);
        this.btnSetToFactor = new Button(composite, 0);
        GridData gd_btnSetToFactor = new GridData(131072, 0x1000000, true, false, 1, 1);
        gd_btnSetToFactor.widthHint = 90;
        gd_btnSetToFactor.minimumHeight = 25;
        gd_btnSetToFactor.minimumWidth = 30;
        gd_btnSetToFactor.heightHint = 24;
        this.btnSetToFactor.setLayoutData((Object)gd_btnSetToFactor);
        this.btnSetToFactor.setText("Set to Factor");
        new Label(composite, 0);
        new Label(composite, 0);
        new Label(composite, 0);
        Label lblFactors = new Label(composite, 0);
        GridData gd_lblFactors = new GridData(16384, 0x1000000, true, false, 1, 1);
        gd_lblFactors.heightHint = 15;
        lblFactors.setLayoutData((Object)gd_lblFactors);
        lblFactors.setText("Factor(s):");
        lblFactors.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        new Label(composite, 0);
        new Label(composite, 0);
        new Label(composite, 0);
        this.lstFactorVariables = new List(composite, 2818);
        GridData gd_lstFactorVariables = new GridData(4, 4, true, false, 1, 3);
        gd_lstFactorVariables.widthHint = 200;
        gd_lstFactorVariables.heightHint = 100;
        this.lstFactorVariables.setLayoutData((Object)gd_lstFactorVariables);
        Group grpSet = new Group(composite, 0);
        grpSet.setLayout((Layout)new GridLayout(2, false));
        GridData gd_grpSet = new GridData(4, 4, false, false, 3, 1);
        gd_grpSet.widthHint = 267;
        grpSet.setLayoutData((Object)gd_grpSet);
        grpSet.setText("Set");
        Button btnAnalysisPerSet = new Button((Composite)grpSet, 16);
        btnAnalysisPerSet.setSelection(true);
        btnAnalysisPerSet.setLayoutData((Object)new GridData(16384, 0x1000000, false, false, 2, 1));
        btnAnalysisPerSet.setText("Perform Analysis Per Set");
        Button btnCombinedAnalysis = new Button((Composite)grpSet, 16);
        btnCombinedAnalysis.setEnabled(false);
        btnCombinedAnalysis.setLayoutData((Object)new GridData(16384, 0x1000000, false, false, 2, 1));
        btnCombinedAnalysis.setText("Perform Combined Analysis");
        this.btnAddSet = new Button((Composite)grpSet, 0);
        GridData gd_btnAddSet = new GridData(16384, 0x1000000, false, false, 1, 1);
        gd_btnAddSet.widthHint = 60;
        this.btnAddSet.setLayoutData((Object)gd_btnAddSet);
        this.btnAddSet.setText("Add");
        this.txtAnalysisPerSet = new Text((Composite)grpSet, 2048);
        this.txtAnalysisPerSet.setBackground(SWTResourceManager.getColor(1));
        GridData gd_txtAnalysisPerSet = new GridData(131072, 0x1000000, true, false, 1, 1);
        gd_txtAnalysisPerSet.heightHint = 15;
        gd_txtAnalysisPerSet.widthHint = 155;
        this.txtAnalysisPerSet.setLayoutData((Object)gd_txtAnalysisPerSet);
        this.btnAddTrt = new Button(composite, 0);
        GridData gd_btnAddTrt = new GridData(4, 0x1000000, false, false, 1, 1);
        gd_btnAddTrt.widthHint = 60;
        this.btnAddTrt.setLayoutData((Object)gd_btnAddTrt);
        this.btnAddTrt.setText("Add");
        this.btnAddTrt.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        Label lblTreatment = new Label(composite, 0);
        lblTreatment.setText("Treatment:");
        this.txtTrt = new Text(composite, 2048);
        this.txtTrt.setBackground(SWTResourceManager.getColor(1));
        GridData gd_txtTrt = new GridData(4, 0x1000000, true, false, 1, 1);
        gd_txtTrt.heightHint = 15;
        gd_txtTrt.widthHint = 150;
        this.txtTrt.setLayoutData((Object)gd_txtTrt);
        this.btnAddBlk = new Button(composite, 0);
        GridData gd_btnAddBlk = new GridData(16384, 0x1000000, false, false, 1, 1);
        gd_btnAddBlk.widthHint = 60;
        this.btnAddBlk.setLayoutData((Object)gd_btnAddBlk);
        this.btnAddBlk.setText("Add");
        this.btnAddBlk.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        Label lblBlock = new Label(composite, 0);
        lblBlock.setText("Block:");
        this.txtBlk = new Text(composite, 2048);
        this.txtBlk.setBackground(SWTResourceManager.getColor(1));
        GridData gd_txtBlk = new GridData(4, 0x1000000, true, false, 1, 1);
        gd_txtBlk.heightHint = 15;
        gd_txtBlk.widthHint = 150;
        this.txtBlk.setLayoutData((Object)gd_txtBlk);
        TabItem tbtmStatistics = new TabItem(tabFolder, 0);
        tbtmStatistics.setText("Options");
        Composite cmpStatistics = new Composite((Composite)tabFolder, 0);
        tbtmStatistics.setControl((Control)cmpStatistics);
        cmpStatistics.setLayout((Layout)new GridLayout(3, false));
        this.btnPerformPairwiseMean = new Button(cmpStatistics, 32);
        this.btnPerformPairwiseMean.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (AugmentedRCBIncompleteBlockDialog.this.btnPerformPairwiseMean.getSelection()) {
                    AugmentedRCBIncompleteBlockDialog.this.activatePerformPairwiseOptions(true);
                    if (AugmentedRCBIncompleteBlockDialog.this.btnCompareWithControl.getSelection()) {
                        AugmentedRCBIncompleteBlockDialog.this.activateLevelOfConrolsOptions(true);
                    }
                } else {
                    AugmentedRCBIncompleteBlockDialog.this.activatePerformPairwiseOptions(false);
                }
            }
        });
        this.btnPerformPairwiseMean.setLayoutData((Object)new GridData(16384, 0x1000000, true, false, 1, 1));
        this.btnPerformPairwiseMean.setText("Perform Pairwise Mean Comparison");
        this.lblLevelOfSignificance = new Label(cmpStatistics, 0);
        this.lblLevelOfSignificance.setEnabled(false);
        this.lblLevelOfSignificance.setLayoutData((Object)new GridData(131072, 0x1000000, false, false, 1, 1));
        this.lblLevelOfSignificance.setText("Level of Significance:");
        this.txtSigLevel = new Text(cmpStatistics, 2048);
        this.txtSigLevel.setEnabled(false);
        this.txtSigLevel.setText("0.05");
        GridData gd_txtSigLevel = new GridData(16384, 0x1000000, true, false, 1, 1);
        gd_txtSigLevel.widthHint = 33;
        this.txtSigLevel.setLayoutData((Object)gd_txtSigLevel);
        this.btnAllComparisons = new Button(cmpStatistics, 16);
        this.btnAllComparisons.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                AugmentedRCBIncompleteBlockDialog.this.activateLevelOfConrolsOptions(false);
            }
        });
        this.btnAllComparisons.setLayoutData((Object)new GridData(16384, 0x1000000, true, false, 1, 1));
        this.btnAllComparisons.setText("Perform all comparisons");
        this.btnAllComparisons.setEnabled(false);
        new Label(cmpStatistics, 0);
        new Label(cmpStatistics, 0);
        this.btnCompareWithControl = new Button(cmpStatistics, 16);
        this.btnCompareWithControl.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (AugmentedRCBIncompleteBlockDialog.this.btnCompareWithControl.getSelection()) {
                    AugmentedRCBIncompleteBlockDialog.this.activateLevelOfConrolsOptions(true);
                }
            }
        });
        this.btnCompareWithControl.setLayoutData((Object)new GridData(16384, 0x1000000, true, false, 1, 1));
        this.btnCompareWithControl.setText("Compare with control(s)");
        this.btnCompareWithControl.setEnabled(false);
        new Label(cmpStatistics, 0);
        new Label(cmpStatistics, 0);
        this.lblSpecifyControl = new Label(cmpStatistics, 0);
        this.lblSpecifyControl.setLayoutData((Object)new GridData(16384, 0x1000000, true, false, 1, 1));
        this.lblSpecifyControl.setText("Specify Control(s)");
        this.lblSpecifyControl.setEnabled(false);
        new Label(cmpStatistics, 0);
        new Label(cmpStatistics, 0);
        this.lblLevelsofFactor = new Label(cmpStatistics, 0);
        this.lblLevelsofFactor.setLayoutData((Object)new GridData(16384, 0x1000000, true, false, 1, 1));
        this.lblLevelsofFactor.setText("Treatment Levels:");
        this.lblLevelsofFactor.setEnabled(false);
        new Label(cmpStatistics, 0);
        this.lblControls = new Label(cmpStatistics, 0);
        this.lblControls.setLayoutData((Object)new GridData(16384, 0x1000000, true, false, 1, 1));
        this.lblControls.setText("Control(s):");
        this.lblControls.setEnabled(false);
        this.lstLevelsofFactor = new List(cmpStatistics, 2818);
        this.lstLevelsofFactor.setEnabled(false);
        GridData gd_lstLevelsofFactor = new GridData(4, 4, true, true, 1, 1);
        gd_lstLevelsofFactor.heightHint = 80;
        gd_lstLevelsofFactor.widthHint = 190;
        this.lstLevelsofFactor.setLayoutData((Object)gd_lstLevelsofFactor);
        this.lstLevelsofFactor.addMouseListener(new MouseListener(){

            public void mouseDoubleClick(MouseEvent e) {
                AugmentedRCBIncompleteBlockDialog.this.dataManipulationManager.moveSelectedStringFromTo(AugmentedRCBIncompleteBlockDialog.this.lstLevelsofFactor, AugmentedRCBIncompleteBlockDialog.this.lstControls);
                AugmentedRCBIncompleteBlockDialog.this.btnAddLevelsofFactor.setEnabled(false);
            }

            public void mouseDown(MouseEvent e) {
                if (AugmentedRCBIncompleteBlockDialog.this.lstLevelsofFactor.getSelectionIndex() > -1) {
                    AugmentedRCBIncompleteBlockDialog.this.btnAddLevelsofFactor.setEnabled(true);
                    AugmentedRCBIncompleteBlockDialog.this.lstControls.setSelection(-1);
                    AugmentedRCBIncompleteBlockDialog.this.lstLevelsofFactor.getSelection();
                    AugmentedRCBIncompleteBlockDialog.this.btnAddLevelsofFactor.setText("Add");
                    AugmentedRCBIncompleteBlockDialog.this.btnAddLevelsofFactor.setEnabled(true);
                }
            }

            public void mouseUp(MouseEvent e) {
            }
        });
        this.btnAddLevelsofFactor = new Button(cmpStatistics, 0);
        this.btnAddLevelsofFactor.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (AugmentedRCBIncompleteBlockDialog.this.lstLevelsofFactor.getSelectionCount() > 0) {
                    String[] selectedNumVars = AugmentedRCBIncompleteBlockDialog.this.lstLevelsofFactor.getSelection();
                    int i = 0;
                    while (i < selectedNumVars.length) {
                        AugmentedRCBIncompleteBlockDialog.this.lstControls.add(selectedNumVars[i]);
                        ++i;
                    }
                    AugmentedRCBIncompleteBlockDialog.this.lstLevelsofFactor.remove(AugmentedRCBIncompleteBlockDialog.this.lstLevelsofFactor.getSelectionIndices());
                    AugmentedRCBIncompleteBlockDialog.this.btnAddLevelsofFactor.setEnabled(false);
                } else {
                    String[] selectedNumVars = AugmentedRCBIncompleteBlockDialog.this.lstControls.getSelection();
                    int i = 0;
                    while (i < selectedNumVars.length) {
                        AugmentedRCBIncompleteBlockDialog.this.lstLevelsofFactor.add(selectedNumVars[i]);
                        ++i;
                    }
                    AugmentedRCBIncompleteBlockDialog.this.lstControls.remove(AugmentedRCBIncompleteBlockDialog.this.lstControls.getSelectionIndices());
                }
                AugmentedRCBIncompleteBlockDialog.this.btnAddLevelsofFactor.setEnabled(false);
            }
        });
        GridData gd_btnAddLevelsofFactor = new GridData(0x1000000, 0x1000000, false, true, 1, 1);
        gd_btnAddLevelsofFactor.widthHint = 60;
        this.btnAddLevelsofFactor.setLayoutData((Object)gd_btnAddLevelsofFactor);
        this.btnAddLevelsofFactor.setText("Add");
        this.btnAddLevelsofFactor.setEnabled(false);
        this.lstControls = new List(cmpStatistics, 2818);
        this.lstControls.setEnabled(false);
        GridData gd_lstControls = new GridData(4, 4, true, true, 1, 1);
        gd_lstControls.heightHint = 80;
        gd_lstControls.widthHint = 185;
        this.lstControls.setLayoutData((Object)gd_lstControls);
        this.lstControls.addMouseListener(new MouseListener(){

            public void mouseDoubleClick(MouseEvent e) {
                AugmentedRCBIncompleteBlockDialog.this.dataManipulationManager.moveSelectedStringFromTo(AugmentedRCBIncompleteBlockDialog.this.lstControls, AugmentedRCBIncompleteBlockDialog.this.lstLevelsofFactor);
                AugmentedRCBIncompleteBlockDialog.this.btnAddLevelsofFactor.setEnabled(false);
            }

            public void mouseDown(MouseEvent e) {
                if (AugmentedRCBIncompleteBlockDialog.this.lstControls.getSelectionIndex() > -1) {
                    AugmentedRCBIncompleteBlockDialog.this.btnAddLevelsofFactor.setEnabled(true);
                    AugmentedRCBIncompleteBlockDialog.this.lstLevelsofFactor.setSelection(-1);
                    AugmentedRCBIncompleteBlockDialog.this.lstControls.getSelection();
                    AugmentedRCBIncompleteBlockDialog.this.btnAddLevelsofFactor.setText("Remove");
                    AugmentedRCBIncompleteBlockDialog.this.btnAddLevelsofFactor.setEnabled(true);
                }
            }

            public void mouseUp(MouseEvent e) {
            }
        });
        Group grpGraph = new Group(cmpStatistics, 0);
        grpGraph.setLayoutData((Object)new GridData(4, 4, true, true, 2, 1));
        grpGraph.setText("Graph");
        grpGraph.setLayout((Layout)new GridLayout(3, false));
        this.btnBoxplot = new Button((Composite)grpGraph, 32);
        this.btnBoxplot.setText("Boxplot of the raw data");
        new Label((Composite)grpGraph, 0);
        new Label((Composite)grpGraph, 0);
        this.btnHistogram = new Button((Composite)grpGraph, 32);
        this.btnHistogram.setText("Histogram of the raw data");
        new Label((Composite)grpGraph, 0);
        new Label((Composite)grpGraph, 0);
        this.btnHeatmap = new Button((Composite)grpGraph, 32);
        this.btnHeatmap.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (!AugmentedRCBIncompleteBlockDialog.this.btnHeatmap.getSelection()) {
                    AugmentedRCBIncompleteBlockDialog.this.btnSpecifyRowCol.setEnabled(false);
                    AugmentedRCBIncompleteBlockDialog.this.resetHeatMap();
                } else {
                    AugmentedRCBIncompleteBlockDialog.this.btnSpecifyRowCol.setEnabled(true);
                }
            }
        });
        this.btnHeatmap.setText("Heatmap of the residuals");
        new Label((Composite)grpGraph, 0);
        this.btnSpecifyRowCol = new Button((Composite)grpGraph, 0);
        this.btnSpecifyRowCol.setEnabled(false);
        this.btnSpecifyRowCol.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                ArrayList<String> varInfo = AugmentedRCBIncompleteBlockDialog.this.dataManipulationManager.getVarInfoFromFile(AugmentedRCBIncompleteBlockDialog.this.filePath);
                SpecifyRowColDialog rowColDialog = new SpecifyRowColDialog(AugmentedRCBIncompleteBlockDialog.this.getShell(), AugmentedRCBIncompleteBlockDialog.this.dataManipulationManager.getListItems(varInfo));
                rowColDialog.open();
                if (rowColDialog.getReturnCode() == 0) {
                    AugmentedRCBIncompleteBlockDialog.this.heatmapRow = rowColDialog.getHeatRow();
                    AugmentedRCBIncompleteBlockDialog.this.heatmapColumn = rowColDialog.getHeatColumn();
                    AugmentedRCBIncompleteBlockDialog.this.selectedHeatMapVars = true;
                } else {
                    AugmentedRCBIncompleteBlockDialog.this.selectedHeatMapVars = false;
                }
            }
        });
        this.btnSpecifyRowCol.setLayoutData((Object)new GridData(0x1000000, 0x1000000, false, false, 1, 1));
        this.btnSpecifyRowCol.setText("Specify Row/Column");
        this.btnDiagnosticplots = new Button((Composite)grpGraph, 32);
        this.btnDiagnosticplots.setText("Diagnostic plots");
        new Label((Composite)grpGraph, 0);
        new Label((Composite)grpGraph, 0);
        Group grpDisplay = new Group(cmpStatistics, 0);
        grpDisplay.setLayoutData((Object)new GridData(4, 4, true, true, 1, 1));
        grpDisplay.setText("Display");
        grpDisplay.setLayout((Layout)new GridLayout(1, false));
        this.btnStatistics = new Button((Composite)grpDisplay, 32);
        this.btnStatistics.setText("Descriptive Statistics");
        this.btnVarComponents = new Button((Composite)grpDisplay, 32);
        this.btnVarComponents.setText("Variance Components");
        this.initializeList();
        return container;
    }

    protected void resetGenotypeControls() {
        this.lstLevelsofFactor.removeAll();
        this.lstControls.removeAll();
    }

    protected void activateFixedOptions(boolean state) {
        this.btnPerformPairwiseMean.setEnabled(state);
        this.activatePerformPairwiseOptions(state);
        if (state) {
            this.btnPerformPairwiseMean.setSelection(state);
        }
    }

    protected void activatePerformPairwiseOptions(boolean state) {
        this.btnCompareWithControl.setEnabled(state);
        this.txtSigLevel.setEnabled(state);
        this.lblLevelOfSignificance.setEnabled(state);
        this.btnCompareWithControl.setSelection(state);
        this.btnAllComparisons.setEnabled(state);
        if (state) {
            if (this.factorLevels.length > 15) {
                this.btnCompareWithControl.setSelection(true);
                this.activateLevelOfConrolsOptions(true);
                this.btnAllComparisons.setSelection(false);
                this.btnAllComparisons.setEnabled(false);
            } else {
                this.btnAllComparisons.setEnabled(true);
            }
        }
    }

    protected void activateRandomOptions(boolean state) {
    }

    protected void activateLevelOfConrolsOptions(boolean state) {
        this.lblSpecifyControl.setEnabled(state);
        this.lblLevelsofFactor.setEnabled(state);
        this.lblControls.setEnabled(state);
        this.lstLevelsofFactor.setEnabled(state);
        this.lstControls.setEnabled(state);
    }

    protected void resetHeatMap() {
        this.heatmapRow = null;
        this.heatmapColumn = null;
        this.selectedHeatMapVars = false;
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

    void resetDialog() {
        this.lstFactorVariables.removeAll();
        this.lstNumericVariables.removeAll();
        this.lstVariables.removeAll();
        this.txtBlk.setText("");
        this.txtTrt.setText("");
        this.txtAnalysisPerSet.setText("");
        this.btnAddSet.setText("Add");
        this.btnAddBlk.setText("Add");
        this.btnAddTrt.setText("Add");
        this.btnAddSingleVariable.setText("Add");
        this.btnSetToFactor.setText("Set to Factor");
        this.btnSetToFactor.setEnabled(false);
        this.btnAddSet.setEnabled(false);
        this.btnAddBlk.setEnabled(false);
        this.btnAddTrt.setEnabled(false);
        this.btnAddSingleVariable.setEnabled(false);
        this.lstLevelsofFactor.removeAll();
        this.lstLevelsofFactor.setEnabled(false);
        this.lstControls.removeAll();
        this.lstControls.setEnabled(false);
        this.btnPerformPairwiseMean.setSelection(false);
        this.btnAllComparisons.setEnabled(false);
        this.btnAllComparisons.setSelection(false);
        this.btnCompareWithControl.setEnabled(false);
        this.lblSpecifyControl.setEnabled(false);
        this.lblLevelsofFactor.setEnabled(false);
        this.lblControls.setEnabled(false);
        this.lblLevelOfSignificance.setEnabled(false);
        this.txtSigLevel.setEnabled(false);
        this.btnBoxplot.setSelection(false);
        this.btnHistogram.setSelection(false);
        this.btnHeatmap.setSelection(false);
        this.btnDiagnosticplots.setSelection(false);
        this.btnSpecifyRowCol.setEnabled(false);
        this.btnStatistics.setSelection(false);
        this.btnVarComponents.setSelection(false);
        this.listManager.initializeFactorList(this.lstFactorVariables, this.filePath);
        this.listManager.initializeNumericList(this.lstNumericVariables, this.filePath);
    }

    protected void okPressed() {
        String dataFileName = this.filePath.replace(File.separator, "/");
        String resultFolderPath = StarAnalysisUtilities.createOutputFolder(this.filePath, "BalancedIncompleteBlockDesign");
        String design = "AugRCB";
        String environment = this.txtAnalysisPerSet.getText().isEmpty() ? null : this.txtAnalysisPerSet.getText();
        String factor = this.txtTrt.getText();
        String block = this.txtBlk.getText();
        String rep = null;
        String row = null;
        String column = null;
        String performPairwise = "none";
        String heatmapRow = null;
        String heatmapColumn = null;
        String[] control = this.lstControls.getItems();
        String[] respvars = this.lstVariables.getItems();
        boolean descriptiveStat = this.btnStatistics.getSelection();
        boolean varianceComponents = this.btnVarComponents.getSelection();
        boolean boxplotRawData = this.btnBoxplot.getSelection();
        boolean histogramRawData = this.btnHistogram.getSelection();
        boolean heatmapResiduals = this.btnHeatmap.getSelection();
        boolean diagnosticPlot = this.btnDiagnosticplots.getSelection();
        double levelSig = Double.parseDouble(this.txtSigLevel.getText());
        this.satisfiedAllConditions = true;
        if (this.lstVariables.getItemCount() < 1) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"Please enter a response variable.");
        } else if (this.txtTrt.getText().isEmpty()) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"Please enter a treatment variable.");
        } else if (this.txtBlk.getText().isEmpty()) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"Please enter a block variable.");
        } else if (heatmapResiduals && !this.selectedHeatMapVars) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"Please specify the heatmap rows/colums.");
        } else {
            performPairwise = this.btnPerformPairwiseMean.getSelection() ? (this.btnCompareWithControl.getSelection() ? "control" : "all") : "none";
            if (this.btnPerformPairwiseMean.getSelection()) {
                this.pairwiseAlpha = this.txtSigLevel.getText();
                if (this.pairwiseAlpha.equals("")) {
                    MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"Please enter a value for the pairwise level of significance.");
                    this.satisfiedAllConditions = false;
                } else {
                    this.compareControl = this.btnCompareWithControl.getSelection();
                    this.performAllPairwise = this.btnPerformPairwiseMean.getSelection();
                    if (this.compareControl) {
                        this.controlLevels = this.lstControls.getItems();
                        if (this.controlLevels.length < 1) {
                            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"Please specify control variables from the factor levels.");
                            this.satisfiedAllConditions = false;
                        }
                    }
                }
            }
            if (this.satisfiedAllConditions) {
                OperationProgressDialog rInfo = new OperationProgressDialog(this.getShell(), "Performing Analysis");
                rInfo.open();
                this.btnOk.setEnabled(false);
                ProjectExplorerView.rJavaManager.getSTARAnalysisManager().doUnbalancedAnalysis(dataFileName, resultFolderPath.replace(File.separator, "/"), design, respvars, environment, factor, block, rep, row, column, descriptiveStat, varianceComponents, performPairwise, levelSig, control, boxplotRawData, histogramRawData, heatmapResiduals, heatmapRow, heatmapColumn, diagnosticPlot);
                StarAnalysisUtilities.testVarArgs(dataFileName, resultFolderPath.replace(File.separator, "/"), design, respvars, environment, factor, block, rep, row, column, descriptiveStat, varianceComponents, performPairwise, levelSig, control, boxplotRawData, histogramRawData, heatmapResiduals, heatmapRow, heatmapColumn, diagnosticPlot);
                this.getShell().setMinimized(true);
                StarAnalysisUtilities.openAndRefreshFolder(resultFolderPath);
                this.btnOk.setEnabled(true);
                rInfo.close();
            }
        }
    }

    protected void createButtonsForButtonBar(Composite parent) {
        this.createButton(parent, 8, "Reset", false);
        this.btnOk = this.createButton(parent, 0, IDialogConstants.OK_LABEL, true);
        this.createButton(parent, 1, IDialogConstants.CANCEL_LABEL, false);
    }

    private void initializeList() {
        this.listManager.initializeSingleButtonList(this.lstNumericVariables, this.lstVariables, this.btnAddSingleVariable);
        this.listManager.initializeFactorList(this.lstFactorVariables, this.filePath);
        this.listManager.initializeNumericList(this.lstNumericVariables, this.filePath);
        this.listManager.initializeVariableMoveList(this.lstNumericVariables, this.lstFactorVariables, this.btnSetToFactor, this.filePath);
        this.listManager.initializeSingleSelectionList(this.lstFactorVariables, this.txtAnalysisPerSet, this.btnAddSet);
        this.listManager.initializeSingleSelectionList(this.lstFactorVariables, this.txtTrt, this.btnSetToFactor, this.btnAddTrt, new TreatmentListValidator(this.lstFactorVariables));
        this.listManager.initializeSingleSelectionList(this.lstFactorVariables, this.txtBlk, this.btnAddBlk);
    }

    protected Point getInitialSize() {
        return new Point(582, 564);
    }

    public class TreatmentListValidator
    extends DialogListValidator {
        private List factorList;
        private DialogFormUtility dlgHelper = new DialogFormUtility();

        public TreatmentListValidator(List factorList) {
            this.factorList = factorList;
        }

        @Override
        public boolean performAddProcess() {
            String[] selectedStrings = this.factorList.getSelection();
            AugmentedRCBIncompleteBlockDialog.this.factorLevels = DataManipulationManager.getEnvtLevels(selectedStrings[0], new File(AugmentedRCBIncompleteBlockDialog.this.filePath));
            AugmentedRCBIncompleteBlockDialog.this.lstLevelsofFactor.add(this.factorList.getSelection()[0]);
            AugmentedRCBIncompleteBlockDialog.this.lstLevelsofFactor.setItems(AugmentedRCBIncompleteBlockDialog.this.factorLevels);
            AugmentedRCBIncompleteBlockDialog.this.activatePerformPairwiseOptions(AugmentedRCBIncompleteBlockDialog.this.btnPerformPairwiseMean.getSelection());
            return true;
        }

        @Override
        public boolean performRemoveProcess() {
            AugmentedRCBIncompleteBlockDialog.this.lstLevelsofFactor.removeAll();
            AugmentedRCBIncompleteBlockDialog.this.lstControls.removeAll();
            AugmentedRCBIncompleteBlockDialog.this.resetGenotypeControls();
            return true;
        }

        @Override
        public boolean validate() {
            return this.dlgHelper.getVariableLevels(AugmentedRCBIncompleteBlockDialog.this.filePath, this.factorList.getSelection()[0]) > 1;
        }
    }
}

