/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.jface.dialogs.Dialog
 *  org.eclipse.jface.dialogs.IDialogConstants
 *  org.eclipse.jface.dialogs.MessageDialog
 *  org.eclipse.jface.viewers.ListViewer
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
 *  org.eclipse.swt.widgets.Text
 */
package org.irri.breedingtool.star.analysis.dialog;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ListViewer;
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
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;
import org.irri.breedingtool.application.handler.PartStackHandler;
import org.irri.breedingtool.datamanipulation.dialog.OperationProgressDialog;
import org.irri.breedingtool.manager.impl.DataManipulationManager;
import org.irri.breedingtool.projectexplorer.view.ProjectExplorerView;
import org.irri.breedingtool.utility.DialogFormUtility;
import org.irri.breedingtool.utility.DialogListValidator;
import org.irri.breedingtool.utility.StarAnalysisUtilities;

public class ClusterAnalysisDialog
extends Dialog {
    private Button btnOkay;
    private DialogFormUtility listManager = new DialogFormUtility();
    private String filePath = PartStackHandler.getActiveElementID();
    private Label lblNumericVariables;
    private Button btnSetToFactor;
    private TabItem tbtmVariableDescription;
    private Label lblFactors;
    private List lstFactors;
    private ListViewer listViewer_1;
    private List lstSelectedVariables;
    private ListViewer listViewer_2;
    private Button btnAddNumeric;
    private Label lblIdVariable;
    private Label lblSelectedVariables;
    private Text txtIDVariable;
    private Button btnAddIdVar;
    private TabItem tbtmOptions_1;
    private Composite composite_1;
    private Label lblDistanceMeasures;
    private Combo cmboDistanceMeasures;
    private Group grpClusteringMethod;
    private Button btnAgglomerative;
    private Combo cmboClusterMethod;
    private Button btnDivisive;
    private Button btnKmeans;
    private Group grpDisplay;
    private Button btnDataStandardization;
    private Button btnDistanceMatrix;
    private Button btnSummaryStatistics;
    private Button btnHighlightClusters;
    private Button btnSaveClusterMembership;
    private Spinner txtNumCluster;
    private List lstNumericVariables;
    private Label lblNewLabel;
    private Label lblBinaryVariable;
    private Button btnAddBinVar;
    private List lstFactorVars;
    private ListViewer listViewer_3;
    private Label lblSelectedFactors;
    private Button btnAddFactorVar;
    private List lstBinVars;
    private ListViewer listViewer_4;
    private Label lblNewLabel_1;
    private List lstAbinVar;
    private ListViewer listViewer_5;
    private List lstOfactor;
    private ListViewer listViewer_6;
    private Label lblAsymmetricBinaryVariables;
    private Label lblOrderedFactors;
    private Button btnAddAbinVar;
    private Button btnAddOfactor;
    private Group grpSaveToA;
    private String[] ClusterMethods = new String[]{"Single", "Complete", "Average", "Ward", "Centroid"};
    private Button btnCopheneticDistances;

    public ClusterAnalysisDialog(Shell parentShell) {
        super(parentShell);
        this.setShellStyle(2288);
    }

    protected void configureShell(Shell shell) {
        super.configureShell(shell);
        shell.setText("Cluster Analysis : " + new File(this.filePath).getName());
    }

    protected Control createDialogArea(Composite parent) {
        Composite container = (Composite)super.createDialogArea(parent);
        FillLayout fl_container = new FillLayout(256);
        fl_container.marginHeight = 8;
        fl_container.marginWidth = 8;
        container.setLayout((Layout)fl_container);
        TabFolder tabFolder = new TabFolder(container, 0);
        tabFolder.setBackground(SWTResourceManager.getColor(22));
        this.tbtmVariableDescription = new TabItem(tabFolder, 0);
        this.tbtmVariableDescription.setText("Variable Description");
        Composite composite = new Composite((Composite)tabFolder, 0);
        this.tbtmVariableDescription.setControl((Control)composite);
        GridLayout gl_composite = new GridLayout(3, false);
        gl_composite.marginHeight = 11;
        gl_composite.marginWidth = 9;
        composite.setLayout((Layout)gl_composite);
        this.lblNumericVariables = new Label(composite, 0);
        this.lblNumericVariables.setLayoutData((Object)new GridData(16384, 0x1000000, true, false, 1, 1));
        this.lblNumericVariables.setText("Numeric Variable(s):");
        this.lblNumericVariables.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        new Label(composite, 0);
        this.lblSelectedVariables = new Label(composite, 0);
        this.lblSelectedVariables.setLayoutData((Object)new GridData(16384, 0x1000000, true, false, 1, 1));
        this.lblSelectedVariables.setText("Variable(s):");
        this.lblSelectedVariables.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        ListViewer listViewer = new ListViewer(composite, 2818);
        this.lstNumericVariables = listViewer.getList();
        GridData gd_lstNumericVariables = new GridData(4, 4, true, false, 1, 1);
        gd_lstNumericVariables.widthHint = 140;
        gd_lstNumericVariables.heightHint = 100;
        this.lstNumericVariables.setLayoutData((Object)gd_lstNumericVariables);
        this.lstNumericVariables.setItems(new String[0]);
        this.btnAddNumeric = new Button(composite, 0);
        GridData gd_btnAddNumeric = new GridData(0x1000000, 0x1000000, true, false, 1, 1);
        gd_btnAddNumeric.widthHint = 90;
        this.btnAddNumeric.setLayoutData((Object)gd_btnAddNumeric);
        this.btnAddNumeric.setText("Add");
        this.listViewer_2 = new ListViewer(composite, 2818);
        this.lstSelectedVariables = this.listViewer_2.getList();
        GridData gd_lstSelectedVariables = new GridData(4, 4, true, false, 1, 1);
        gd_lstSelectedVariables.heightHint = 100;
        gd_lstSelectedVariables.widthHint = 140;
        this.lstSelectedVariables.setLayoutData((Object)gd_lstSelectedVariables);
        this.lstSelectedVariables.setItems(new String[0]);
        this.btnSetToFactor = new Button(composite, 0);
        GridData gd_btnSetToFactor = new GridData(131072, 0x1000000, true, false, 1, 1);
        gd_btnSetToFactor.widthHint = 110;
        this.btnSetToFactor.setLayoutData((Object)gd_btnSetToFactor);
        this.btnSetToFactor.setText("Set to Factor");
        new Label(composite, 0);
        new Label(composite, 0);
        this.lblFactors = new Label(composite, 0);
        this.lblFactors.setLayoutData((Object)new GridData(16384, 0x1000000, true, false, 1, 1));
        this.lblFactors.setText("Factor(s):");
        this.lblFactors.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        new Label(composite, 0);
        this.lblIdVariable = new Label(composite, 0);
        this.lblIdVariable.setLayoutData((Object)new GridData(16384, 0x1000000, true, false, 1, 1));
        this.lblIdVariable.setText("ID Variable:");
        this.lblIdVariable.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        this.listViewer_1 = new ListViewer(composite, 2818);
        this.lstFactors = this.listViewer_1.getList();
        GridData gd_lstFactors = new GridData(4, 4, true, false, 1, 9);
        gd_lstFactors.widthHint = 140;
        gd_lstFactors.heightHint = 74;
        this.lstFactors.setLayoutData((Object)gd_lstFactors);
        this.lstFactors.setItems(new String[0]);
        this.btnAddIdVar = new Button(composite, 0);
        GridData gd_btnAddIdVar = new GridData(0x1000000, 128, true, false, 1, 1);
        gd_btnAddIdVar.widthHint = 90;
        this.btnAddIdVar.setLayoutData((Object)gd_btnAddIdVar);
        this.btnAddIdVar.setText("Add");
        this.txtIDVariable = new Text(composite, 2048);
        this.txtIDVariable.setBackground(SWTResourceManager.getColor(1));
        this.txtIDVariable.setEditable(false);
        GridData gd_txtIDVariable = new GridData(4, 128, true, false, 1, 1);
        gd_txtIDVariable.widthHint = 140;
        this.txtIDVariable.setLayoutData((Object)gd_txtIDVariable);
        new Label(composite, 0);
        this.lblBinaryVariable = new Label(composite, 0);
        this.lblBinaryVariable.setText("Symmetric Binary Variable(s):");
        this.lblBinaryVariable.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        this.btnAddBinVar = new Button(composite, 0);
        GridData gd_btnAddBinVar = new GridData(0x1000000, 0x1000000, false, false, 1, 1);
        gd_btnAddBinVar.widthHint = 90;
        this.btnAddBinVar.setLayoutData((Object)gd_btnAddBinVar);
        this.btnAddBinVar.setText("Add");
        this.listViewer_4 = new ListViewer(composite, 2818);
        this.lstBinVars = this.listViewer_4.getList();
        GridData gd_lstBinVars = new GridData(4, 4, false, false, 1, 1);
        gd_lstBinVars.heightHint = 20;
        this.lstBinVars.setLayoutData((Object)gd_lstBinVars);
        this.lstBinVars.setItems(new String[0]);
        new Label(composite, 0);
        this.lblAsymmetricBinaryVariables = new Label(composite, 0);
        this.lblAsymmetricBinaryVariables.setText("Asymmetric Binary Variable(s):");
        this.lblAsymmetricBinaryVariables.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        this.btnAddAbinVar = new Button(composite, 0);
        GridData gd_btnAddAbinVar = new GridData(0x1000000, 0x1000000, false, false, 1, 1);
        gd_btnAddAbinVar.widthHint = 90;
        this.btnAddAbinVar.setLayoutData((Object)gd_btnAddAbinVar);
        this.btnAddAbinVar.setText("Add");
        this.listViewer_5 = new ListViewer(composite, 2818);
        this.lstAbinVar = this.listViewer_5.getList();
        GridData gd_lstAbinVar = new GridData(4, 4, false, false, 1, 1);
        gd_lstAbinVar.heightHint = 20;
        this.lstAbinVar.setLayoutData((Object)gd_lstAbinVar);
        this.lstAbinVar.setItems(new String[0]);
        new Label(composite, 0);
        this.lblOrderedFactors = new Label(composite, 0);
        this.lblOrderedFactors.setText("Ordered Factor(s):");
        this.lblOrderedFactors.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        this.btnAddOfactor = new Button(composite, 0);
        GridData gd_btnAddOfactor = new GridData(0x1000000, 0x1000000, false, false, 1, 1);
        gd_btnAddOfactor.widthHint = 90;
        this.btnAddOfactor.setLayoutData((Object)gd_btnAddOfactor);
        this.btnAddOfactor.setText("Add");
        this.listViewer_6 = new ListViewer(composite, 2818);
        this.lstOfactor = this.listViewer_6.getList();
        GridData gd_lstOfactor = new GridData(4, 4, false, false, 1, 1);
        gd_lstOfactor.heightHint = 20;
        this.lstOfactor.setLayoutData((Object)gd_lstOfactor);
        this.lstOfactor.setItems(new String[0]);
        new Label(composite, 0);
        this.lblSelectedFactors = new Label(composite, 0);
        this.lblSelectedFactors.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        this.lblSelectedFactors.setText("Nominal Factor(s):");
        this.btnAddFactorVar = new Button(composite, 0);
        GridData gd_btnAddFactorVar = new GridData(0x1000000, 0x1000000, false, true, 1, 1);
        gd_btnAddFactorVar.widthHint = 90;
        this.btnAddFactorVar.setLayoutData((Object)gd_btnAddFactorVar);
        this.btnAddFactorVar.setText("Add");
        this.listViewer_3 = new ListViewer(composite, 2818);
        this.lstFactorVars = this.listViewer_3.getList();
        GridData gd_lstFactorVars = new GridData(4, 4, false, true, 1, 1);
        gd_lstFactorVars.heightHint = 30;
        this.lstFactorVars.setLayoutData((Object)gd_lstFactorVars);
        this.lstFactorVars.setItems(new String[0]);
        this.tbtmOptions_1 = new TabItem(tabFolder, 0);
        this.tbtmOptions_1.setText("Options");
        this.composite_1 = new Composite((Composite)tabFolder, 0);
        this.tbtmOptions_1.setControl((Control)this.composite_1);
        this.composite_1.setLayout((Layout)new GridLayout(2, false));
        this.lblNewLabel_1 = new Label(this.composite_1, 0);
        new Label(this.composite_1, 0);
        Label lblNumberOfClusters_1 = new Label(this.composite_1, 0);
        lblNumberOfClusters_1.setText("Number of Clusters:");
        this.txtNumCluster = new Spinner(this.composite_1, 2048);
        this.txtNumCluster.setLayoutData((Object)new GridData(16384, 0x1000000, true, false, 1, 1));
        this.txtNumCluster.setMinimum(2);
        this.lblDistanceMeasures = new Label(this.composite_1, 0);
        this.lblDistanceMeasures.setText("Distance Measure:");
        this.cmboDistanceMeasures = new Combo(this.composite_1, 8);
        GridData gd_cmboDistanceMeasures = new GridData(16384, 0x1000000, true, false, 1, 1);
        gd_cmboDistanceMeasures.widthHint = 120;
        this.cmboDistanceMeasures.setLayoutData((Object)gd_cmboDistanceMeasures);
        this.cmboDistanceMeasures.setItems(new String[]{"Euclidean", "Maximum", "Manhattan", "Minkowski", "Canberra", "Simple Matching", "Sokal & Sneath", "Hamann coefficient", "Jaccard", "Dice", "Gower"});
        this.cmboDistanceMeasures.select(0);
        this.lblNewLabel = new Label(this.composite_1, 0);
        new Label(this.composite_1, 0);
        this.grpClusteringMethod = new Group(this.composite_1, 0);
        this.grpClusteringMethod.setLayoutData((Object)new GridData(4, 4, false, false, 2, 1));
        this.grpClusteringMethod.setText("Clustering Method");
        this.grpClusteringMethod.setLayout((Layout)new GridLayout(1, false));
        this.btnAgglomerative = new Button((Composite)this.grpClusteringMethod, 16);
        this.btnAgglomerative.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                ClusterAnalysisDialog.this.cmboDistanceMeasures.setEnabled(true);
                ClusterAnalysisDialog.this.cmboClusterMethod.setEnabled(true);
                ClusterAnalysisDialog.this.btnDataStandardization.setEnabled(true);
                ClusterAnalysisDialog.this.btnDistanceMatrix.setEnabled(true);
                ClusterAnalysisDialog.this.btnCopheneticDistances.setEnabled(true);
                ClusterAnalysisDialog.this.btnHighlightClusters.setEnabled(true);
                ClusterAnalysisDialog.this.btnSaveClusterMembership.setEnabled(true);
                ClusterAnalysisDialog.this.btnSummaryStatistics.setEnabled(true);
            }
        });
        this.btnAgglomerative.setText("Agglomerative");
        this.btnAgglomerative.setSelection(true);
        this.cmboClusterMethod = new Combo((Composite)this.grpClusteringMethod, 8);
        this.cmboClusterMethod.setItems(new String[]{"Single Linkage", "Complete Linkage", "Average Linkage", "Ward's Method", "Centroid Method"});
        GridData gd_cmboClusterMethod = new GridData(16384, 4, true, false, 1, 1);
        gd_cmboClusterMethod.widthHint = 120;
        this.cmboClusterMethod.setLayoutData((Object)gd_cmboClusterMethod);
        this.cmboClusterMethod.select(1);
        this.btnDivisive = new Button((Composite)this.grpClusteringMethod, 16);
        this.btnDivisive.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                ClusterAnalysisDialog.this.cmboDistanceMeasures.setEnabled(true);
                ClusterAnalysisDialog.this.cmboClusterMethod.setEnabled(false);
                ClusterAnalysisDialog.this.btnDataStandardization.setEnabled(true);
                ClusterAnalysisDialog.this.btnDistanceMatrix.setEnabled(true);
                ClusterAnalysisDialog.this.btnCopheneticDistances.setEnabled(true);
                ClusterAnalysisDialog.this.btnHighlightClusters.setEnabled(true);
                ClusterAnalysisDialog.this.btnSaveClusterMembership.setEnabled(true);
                ClusterAnalysisDialog.this.btnSummaryStatistics.setEnabled(true);
            }
        });
        this.btnDivisive.setLayoutData((Object)new GridData(16384, 128, false, false, 1, 1));
        this.btnDivisive.setText("Divisive");
        this.btnKmeans = new Button((Composite)this.grpClusteringMethod, 16);
        this.btnKmeans.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                ClusterAnalysisDialog.this.cmboDistanceMeasures.setEnabled(false);
                ClusterAnalysisDialog.this.cmboClusterMethod.setEnabled(false);
                ClusterAnalysisDialog.this.btnDataStandardization.setEnabled(false);
                ClusterAnalysisDialog.this.btnDistanceMatrix.setEnabled(false);
                ClusterAnalysisDialog.this.btnCopheneticDistances.setEnabled(false);
                ClusterAnalysisDialog.this.btnHighlightClusters.setEnabled(false);
                ClusterAnalysisDialog.this.btnSaveClusterMembership.setEnabled(true);
                ClusterAnalysisDialog.this.btnSummaryStatistics.setEnabled(true);
                ClusterAnalysisDialog.this.btnCopheneticDistances.setEnabled(false);
            }
        });
        this.btnKmeans.setText("K - Means");
        this.grpDisplay = new Group(this.composite_1, 0);
        this.grpDisplay.setLayoutData((Object)new GridData(4, 4, true, false, 2, 1));
        this.grpDisplay.setLayout((Layout)new GridLayout(1, false));
        this.btnDataStandardization = new Button((Composite)this.grpDisplay, 32);
        this.btnDataStandardization.setText("Data Standardization");
        this.btnDataStandardization.setSelection(true);
        this.btnSummaryStatistics = new Button((Composite)this.grpDisplay, 32);
        this.btnSummaryStatistics.setText("Display Descriptive Statistics for each Cluster");
        this.btnHighlightClusters = new Button((Composite)this.grpDisplay, 32);
        this.btnHighlightClusters.setText("Highlight Clusters on Dendrogram");
        this.grpSaveToA = new Group(this.composite_1, 0);
        this.grpSaveToA.setLayout((Layout)new GridLayout(1, false));
        GridData gd_grpSaveToA = new GridData(4, 4, false, false, 2, 1);
        gd_grpSaveToA.widthHint = 551;
        this.grpSaveToA.setLayoutData((Object)gd_grpSaveToA);
        this.grpSaveToA.setText("Save to a File");
        this.btnDistanceMatrix = new Button((Composite)this.grpSaveToA, 32);
        this.btnDistanceMatrix.setText("Distance Matrix");
        this.btnCopheneticDistances = new Button((Composite)this.grpSaveToA, 32);
        this.btnCopheneticDistances.setText("Cophenetic Distances");
        this.btnSaveClusterMembership = new Button((Composite)this.grpSaveToA, 32);
        this.btnSaveClusterMembership.setText("Cluster Membership");
        this.btnSaveClusterMembership.setSelection(true);
        this.initializeContent();
        return container;
    }

    void initializeContent() {
        this.listManager.initializeSingleButtonList(this.lstNumericVariables, this.lstSelectedVariables, this.btnAddNumeric, new ClusterListValidator());
        this.listManager.initializeSingleButtonList(this.lstFactors, this.lstBinVars, this.btnAddBinVar, new ClusterListValidator());
        this.listManager.initializeSingleButtonList(this.lstFactors, this.lstAbinVar, this.btnAddAbinVar, new ClusterListValidator());
        this.listManager.initializeSingleButtonList(this.lstFactors, this.lstOfactor, this.btnAddOfactor, new ClusterListValidator());
        this.listManager.initializeSingleButtonList(this.lstFactors, this.lstFactorVars, this.btnAddFactorVar, new ClusterListValidator());
        this.listManager.initializeSingleSelectionList(this.lstFactors, this.txtIDVariable, this.btnAddIdVar);
        this.listManager.initializeVariableMoveList(this.lstNumericVariables, this.lstFactors, this.btnSetToFactor, this.filePath);
        this.listManager.initializeNumericList(this.lstNumericVariables, this.filePath);
        this.listManager.initializeFactorList(this.lstFactors, this.filePath);
    }

    void resetDialog() {
        this.lstNumericVariables.removeAll();
        this.lstSelectedVariables.removeAll();
        this.lstFactors.removeAll();
        this.lstAbinVar.removeAll();
        this.lstBinVars.removeAll();
        this.lstFactorVars.removeAll();
        this.lstOfactor.removeAll();
        this.txtIDVariable.setText("");
        this.listManager.initializeNumericList(this.lstNumericVariables, this.filePath);
        this.listManager.initializeFactorList(this.lstFactors, this.filePath);
        this.btnAddNumeric.setText("Add");
        this.btnAddNumeric.setEnabled(false);
        this.btnAddIdVar.setText("Add");
        this.btnAddIdVar.setEnabled(false);
        this.btnAddBinVar.setText("Add");
        this.btnAddBinVar.setEnabled(false);
        this.btnAddAbinVar.setText("Add");
        this.btnAddAbinVar.setEnabled(false);
        this.btnAddOfactor.setEnabled(false);
        this.btnAddOfactor.setText("Add");
        this.btnAddFactorVar.setText("Add");
        this.btnAddFactorVar.setEnabled(false);
        this.btnAgglomerative.setSelection(true);
        this.btnDivisive.setSelection(false);
        this.btnKmeans.setSelection(false);
        this.btnKmeans.setEnabled(true);
        this.btnSetToFactor.setEnabled(false);
        this.btnSetToFactor.setText("Set to Factor");
        this.btnDataStandardization.setSelection(true);
        this.btnDistanceMatrix.setSelection(false);
        this.btnDistanceMatrix.setEnabled(true);
        this.btnCopheneticDistances.setSelection(false);
        this.btnCopheneticDistances.setEnabled(true);
        this.btnHighlightClusters.setSelection(false);
        this.btnSaveClusterMembership.setSelection(true);
        this.btnSummaryStatistics.setEnabled(true);
        this.btnSummaryStatistics.setSelection(false);
        this.cmboDistanceMeasures.setItems(new String[]{"Euclidean", "Maximum", "Manhattan", "Minkowski", "Canberra", "Simple Matching", "Jaccard", "Dice", "Gower"});
        this.cmboDistanceMeasures.select(0);
        this.txtNumCluster.setSelection(2);
        this.cmboClusterMethod.setText("Complete Linkage");
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
        if (this.txtIDVariable.getText().equals("")) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"Please add an ID Variable.");
            return;
        }
        String[] idLevels = DataManipulationManager.getEnvtLevels(this.txtIDVariable.getText(), new File(this.filePath));
        try {
            if (idLevels.length != this.countLines(this.filePath) - 1) {
                System.out.println(String.valueOf(idLevels.length) + ":" + (this.countLines(this.filePath) - 1));
                MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"ID Variable is not unique.");
                return;
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        String outputFolder = String.valueOf(StarAnalysisUtilities.createOutputFolder(this.filePath, "ClusterAnalysis")) + File.separator;
        OperationProgressDialog rInfo = new OperationProgressDialog(this.getShell(), "Performing Analysis");
        rInfo.open();
        this.btnOkay.setEnabled(false);
        String dataFileName = this.filePath.replace(File.separator, "/");
        String outputPath = outputFolder;
        String[] var = this.lstSelectedVariables.getItems();
        String idVar = this.txtIDVariable.getText().isEmpty() ? null : this.txtIDVariable.getText();
        String[] sbinVar = this.lstBinVars.getItemCount() <= 0 ? null : this.lstBinVars.getItems();
        String[] abinVar = this.lstAbinVar.getItemCount() <= 0 ? null : this.lstAbinVar.getItems();
        String[] ofactorVar = this.lstOfactor.getItemCount() <= 0 ? null : this.lstOfactor.getItems();
        String[] factorVar = this.lstFactorVars.getItemCount() <= 0 ? null : this.lstFactorVars.getItems();
        boolean stand = this.btnDataStandardization.getSelection();
        String distance = this.cmboDistanceMeasures.getText();
        String clusmethod = this.ClusterMethods[this.cmboClusterMethod.getSelectionIndex()];
        boolean distMatrix = this.btnDistanceMatrix.getSelection();
        boolean copDistance = this.btnCopheneticDistances.getSelection();
        boolean clusterMem = true;
        boolean descriptiveStat = this.btnSummaryStatistics.getSelection();
        Integer clusterNum = this.txtNumCluster.getSelection();
        boolean dendrogram = true;
        boolean clusterBox = this.btnHighlightClusters.getSelection();
        boolean kgraph = true;
        boolean saveMem = this.btnSaveClusterMembership.getSelection();
        if (this.btnAgglomerative.getSelection()) {
            ProjectExplorerView.rJavaManager.getSTARAnalysisManager().doClusterAgglo(dataFileName, outputPath.replace(File.separator, "/"), var, idVar, sbinVar, abinVar, ofactorVar, factorVar, stand, distance, clusmethod, distMatrix, copDistance, clusterMem, descriptiveStat, clusterNum, dendrogram, clusterBox, saveMem);
        } else if (this.btnDivisive.getSelection()) {
            ProjectExplorerView.rJavaManager.getSTARAnalysisManager().doClusterDivisive(dataFileName, outputPath.replace(File.separator, "/"), var, idVar, sbinVar, abinVar, ofactorVar, factorVar, stand, distance, distMatrix, copDistance, clusterMem, descriptiveStat, clusterNum, dendrogram, clusterBox, saveMem);
        } else if (this.btnKmeans.getSelection()) {
            ProjectExplorerView.rJavaManager.getSTARAnalysisManager().doClusterKmeans(dataFileName, outputPath.replace(File.separator, "/"), var, idVar, clusterMem, descriptiveStat, clusterNum, kgraph, saveMem);
        }
        StarAnalysisUtilities.testVarArgs(dataFileName, outputPath.replace(File.separator, "/"), var, idVar, sbinVar, abinVar, ofactorVar, factorVar, stand, distance, clusmethod, distMatrix, clusterMem, descriptiveStat, clusterNum, dendrogram, clusterBox, saveMem, kgraph);
        this.getShell().setMinimized(true);
        StarAnalysisUtilities.openAndRefreshFolder(outputPath);
        this.btnOkay.setEnabled(true);
        rInfo.close();
    }

    public int countLines(String filename) throws IOException {
        LineNumberReader reader = new LineNumberReader(new FileReader(filename));
        int cnt = 0;
        while (reader.readLine() != null) {
        }
        cnt = reader.getLineNumber();
        reader.close();
        return cnt;
    }

    protected void createButtonsForButtonBar(Composite parent) {
        this.createButton(parent, 8, "Reset", false);
        this.btnOkay = this.createButton(parent, 0, IDialogConstants.OK_LABEL, true);
        this.createButton(parent, 1, IDialogConstants.CANCEL_LABEL, false);
    }

    protected Point getInitialSize() {
        return new Point(605, 637);
    }

    public class ClusterListValidator
    extends DialogListValidator {
        @Override
        public void postAddProcess() {
            this.updateComboBox();
        }

        @Override
        public void postRemoveProcess() {
            this.updateComboBox();
        }

        public void updateComboBox() {
            if (!this.listEmpty(ClusterAnalysisDialog.this.lstSelectedVariables) && this.listEmpty(ClusterAnalysisDialog.this.lstBinVars) && this.listEmpty(ClusterAnalysisDialog.this.lstFactorVars) && this.listEmpty(ClusterAnalysisDialog.this.lstAbinVar) && this.listEmpty(ClusterAnalysisDialog.this.lstOfactor)) {
                ClusterAnalysisDialog.this.cmboDistanceMeasures.setItems(new String[]{"Euclidean", "Maximum", "Manhattan", "Minkowski", "Canberra"});
                ClusterAnalysisDialog.this.cmboDistanceMeasures.select(0);
            } else if (this.listEmpty(ClusterAnalysisDialog.this.lstSelectedVariables) && !this.listEmpty(ClusterAnalysisDialog.this.lstBinVars) && this.listEmpty(ClusterAnalysisDialog.this.lstFactorVars) && this.listEmpty(ClusterAnalysisDialog.this.lstAbinVar) && this.listEmpty(ClusterAnalysisDialog.this.lstOfactor)) {
                ClusterAnalysisDialog.this.cmboDistanceMeasures.setItems(new String[]{"Simple Matching", "Sokal & Sneath", "Hamann coefficient"});
                ClusterAnalysisDialog.this.cmboDistanceMeasures.select(0);
            } else if (this.listEmpty(ClusterAnalysisDialog.this.lstSelectedVariables) && this.listEmpty(ClusterAnalysisDialog.this.lstBinVars) && this.listEmpty(ClusterAnalysisDialog.this.lstFactorVars) && !this.listEmpty(ClusterAnalysisDialog.this.lstAbinVar) && this.listEmpty(ClusterAnalysisDialog.this.lstOfactor)) {
                ClusterAnalysisDialog.this.cmboDistanceMeasures.setItems(new String[]{"Jaccard", "Dice"});
                ClusterAnalysisDialog.this.cmboDistanceMeasures.select(0);
            } else {
                System.out.println(ClusterAnalysisDialog.this.lstBinVars.getItemCount());
                ClusterAnalysisDialog.this.cmboDistanceMeasures.setItems(new String[]{"Gower"});
                ClusterAnalysisDialog.this.cmboDistanceMeasures.select(0);
            }
            ClusterAnalysisDialog.this.btnDataStandardization.setEnabled(!this.listEmpty(ClusterAnalysisDialog.this.lstSelectedVariables));
            ClusterAnalysisDialog.this.btnDataStandardization.setSelection(!this.listEmpty(ClusterAnalysisDialog.this.lstSelectedVariables));
            ClusterAnalysisDialog.this.btnSummaryStatistics.setEnabled(!this.listEmpty(ClusterAnalysisDialog.this.lstSelectedVariables));
            ClusterAnalysisDialog.this.btnSummaryStatistics.setSelection(!this.listEmpty(ClusterAnalysisDialog.this.lstSelectedVariables));
            ClusterAnalysisDialog.this.btnKmeans.setEnabled(this.listEmpty(ClusterAnalysisDialog.this.lstBinVars) && this.listEmpty(ClusterAnalysisDialog.this.lstFactorVars) && this.listEmpty(ClusterAnalysisDialog.this.lstAbinVar) && this.listEmpty(ClusterAnalysisDialog.this.lstOfactor));
        }

        public boolean listEmpty(List list) {
            return list.getItemCount() <= 0;
        }
    }
}

