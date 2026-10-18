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
 */
package org.irri.breedingtool.star.analysis.dialog;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
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
import org.eclipse.wb.swt.SWTResourceManager;
import org.irri.breedingtool.application.handler.PartStackHandler;
import org.irri.breedingtool.application.model.ProjectExplorerTreeNodeModel;
import org.irri.breedingtool.datamanipulation.dialog.OperationProgressDialog;
import org.irri.breedingtool.manager.impl.ProjectExplorerManager;
import org.irri.breedingtool.projectexplorer.view.ProjectExplorerView;
import org.irri.breedingtool.utility.DialogFormUtility;
import org.irri.breedingtool.utility.StarAnalysisUtilities;

public class DescriptiveAnalysisDialog
extends Dialog {
    private static final int RESET = 2135;
    private DialogFormUtility listManager = new DialogFormUtility();
    private String filePath = PartStackHandler.getActivePart().getElementId().toString();
    private Button btnOk;
    private List lstVariables;
    private List lstByVariables;
    private ArrayList<Composite> groups = new ArrayList();
    private Button btnDefaults;
    private Button btnAddFactorVariable;
    private Button btnAddSingleVariable;
    private Button btnFactornumeric;
    private List lstNumericVariables;
    private List lstFactorialVariables;

    public DescriptiveAnalysisDialog(Shell parentShell) {
        super(parentShell);
        this.setShellStyle(2288);
    }

    protected void configureShell(Shell shell) {
        super.configureShell(shell);
        shell.setText("Descriptive Statistics: " + new File(this.filePath).getName());
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
        tbtmVariableDescription.setText("Variable Description");
        Composite composite = new Composite((Composite)tabFolder, 0);
        tbtmVariableDescription.setControl((Control)composite);
        composite.setLayout((Layout)new GridLayout(3, false));
        Label lblAvailableData = new Label(composite, 0);
        lblAvailableData.setLayoutData((Object)new GridData(16384, 0x1000000, true, false, 1, 1));
        lblAvailableData.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        lblAvailableData.setText("Numeric Variable(s)");
        new Label(composite, 0);
        Label lblVariables = new Label(composite, 0);
        lblVariables.setLayoutData((Object)new GridData(16384, 0x1000000, true, false, 1, 1));
        lblVariables.setText("Variable(s)");
        lblVariables.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        this.lstNumericVariables = new List(composite, 2818);
        GridData gd_lstNumericVariables_1 = new GridData(4, 4, true, true, 1, 1);
        gd_lstNumericVariables_1.heightHint = 150;
        gd_lstNumericVariables_1.widthHint = 200;
        this.lstNumericVariables.setLayoutData((Object)gd_lstNumericVariables_1);
        this.btnAddSingleVariable = new Button(composite, 0);
        GridData gd_btnAddSingleVariable = new GridData(4, 0x1000000, false, false, 1, 1);
        gd_btnAddSingleVariable.widthHint = 90;
        this.btnAddSingleVariable.setLayoutData((Object)gd_btnAddSingleVariable);
        this.btnAddSingleVariable.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        this.btnAddSingleVariable.setText("Add");
        this.lstVariables = new List(composite, 2818);
        GridData gd_lstVariables = new GridData(4, 4, true, true, 1, 1);
        gd_lstVariables.widthHint = 200;
        gd_lstVariables.heightHint = 150;
        this.lstVariables.setLayoutData((Object)gd_lstVariables);
        this.btnFactornumeric = new Button(composite, 0);
        GridData gd_btnFactornumeric = new GridData(131072, 0x1000000, true, true, 1, 1);
        gd_btnFactornumeric.widthHint = 110;
        gd_btnFactornumeric.minimumHeight = 25;
        gd_btnFactornumeric.minimumWidth = 30;
        gd_btnFactornumeric.heightHint = 24;
        this.btnFactornumeric.setLayoutData((Object)gd_btnFactornumeric);
        this.btnFactornumeric.setText("Set to Factor");
        new Label(composite, 0);
        new Label(composite, 0);
        Label lblFactors = new Label(composite, 0);
        GridData gd_lblFactors = new GridData(16384, 0x1000000, true, false, 1, 1);
        gd_lblFactors.heightHint = 15;
        lblFactors.setLayoutData((Object)gd_lblFactors);
        lblFactors.setText("Factor(s)");
        lblFactors.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        new Label(composite, 0);
        Label lblByVariables = new Label(composite, 0);
        GridData gd_lblByVariables = new GridData(16384, 0x1000000, false, false, 1, 1);
        gd_lblByVariables.heightHint = 15;
        lblByVariables.setLayoutData((Object)gd_lblByVariables);
        lblByVariables.setText("By Variable(s)");
        lblByVariables.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        this.lstFactorialVariables = new List(composite, 2818);
        GridData gd_lstFactorialVariables_1 = new GridData(4, 4, true, true, 1, 1);
        gd_lstFactorialVariables_1.widthHint = 200;
        gd_lstFactorialVariables_1.heightHint = 100;
        this.lstFactorialVariables.setLayoutData((Object)gd_lstFactorialVariables_1);
        this.btnAddFactorVariable = new Button(composite, 0);
        GridData gd_btnAddFactorVariable = new GridData(4, 0x1000000, false, false, 1, 1);
        gd_btnAddFactorVariable.widthHint = 90;
        this.btnAddFactorVariable.setLayoutData((Object)gd_btnAddFactorVariable);
        this.btnAddFactorVariable.setText("Add");
        this.btnAddFactorVariable.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        this.lstByVariables = new List(composite, 2818);
        GridData gd_lstByVariables = new GridData(4, 4, true, true, 1, 1);
        gd_lstByVariables.widthHint = 200;
        gd_lstByVariables.heightHint = 100;
        this.lstByVariables.setLayoutData((Object)gd_lstByVariables);
        TabItem tbtmStatistics = new TabItem(tabFolder, 0);
        tbtmStatistics.setText("Statistics");
        Composite cmpStatistics = new Composite((Composite)tabFolder, 0);
        tbtmStatistics.setControl((Control)cmpStatistics);
        cmpStatistics.setLayout((Layout)new GridLayout(4, false));
        Group grpOthers = new Group(cmpStatistics, 0);
        grpOthers.setLayoutData((Object)new GridData(4, 4, true, true, 1, 2));
        grpOthers.setLayout((Layout)new GridLayout(1, false));
        grpOthers.setText("Others");
        Button btnNoOfObs = new Button((Composite)grpOthers, 32);
        btnNoOfObs.setLayoutData((Object)new GridData(4, 4, true, true, 1, 1));
        btnNoOfObs.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        btnNoOfObs.setText("No. of Obs.");
        btnNoOfObs.setData((Object)"n");
        Button btnNoOfNonmissing = new Button((Composite)grpOthers, 32);
        btnNoOfNonmissing.setSelection(true);
        btnNoOfNonmissing.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        btnNoOfNonmissing.setLayoutData((Object)new GridData(4, 4, true, true, 1, 1));
        btnNoOfNonmissing.setText("No. of Non-missing Obs");
        btnNoOfNonmissing.setData((Object)"nnmiss");
        Button btnNoOfMissing = new Button((Composite)grpOthers, 32);
        btnNoOfMissing.setLayoutData((Object)new GridData(4, 4, true, true, 1, 1));
        btnNoOfMissing.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        btnNoOfMissing.setText("No. of Missing Obs.");
        btnNoOfMissing.setData((Object)"nmiss");
        Button btnSumOfValues = new Button((Composite)grpOthers, 32);
        btnSumOfValues.setLayoutData((Object)new GridData(4, 4, true, true, 1, 1));
        btnSumOfValues.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        btnSumOfValues.setText("Sum of Values");
        btnSumOfValues.setData((Object)"sum");
        Button btnCorrectedSumOf = new Button((Composite)grpOthers, 32);
        btnCorrectedSumOf.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        btnCorrectedSumOf.setLayoutData((Object)new GridData(4, 4, true, true, 1, 1));
        btnCorrectedSumOf.setText("Corrected Sum of Squares");
        btnCorrectedSumOf.setData((Object)"css");
        Button btnUncorrectedSumOf = new Button((Composite)grpOthers, 32);
        btnUncorrectedSumOf.setLayoutData((Object)new GridData(4, 4, true, true, 1, 1));
        btnUncorrectedSumOf.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        btnUncorrectedSumOf.setText("Uncorrected Sum of Squares");
        btnUncorrectedSumOf.setData((Object)"ucss");
        Button btnStandardErrorOfSkewness = new Button((Composite)grpOthers, 32);
        btnStandardErrorOfSkewness.setLayoutData((Object)new GridData(4, 4, true, true, 1, 1));
        btnStandardErrorOfSkewness.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        btnStandardErrorOfSkewness.setText("Standard of Error of Skewness");
        btnStandardErrorOfSkewness.setData((Object)"se.skew");
        Button btnStandardErrorOfKurtosis = new Button((Composite)grpOthers, 32);
        btnStandardErrorOfKurtosis.setLayoutData((Object)new GridData(4, 4, true, true, 1, 1));
        btnStandardErrorOfKurtosis.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        btnStandardErrorOfKurtosis.setText("Standard Error of Kurtosis");
        btnStandardErrorOfKurtosis.setData((Object)"se.kurtosis");
        new Label(cmpStatistics, 0);
        Group grpCentralTendency = new Group(cmpStatistics, 0);
        grpCentralTendency.setLayoutData((Object)new GridData(4, 4, true, true, 2, 1));
        grpCentralTendency.setLayout((Layout)new GridLayout(1, false));
        grpCentralTendency.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        grpCentralTendency.setText("Central Tendency");
        Button btnMean = new Button((Composite)grpCentralTendency, 32);
        btnMean.setSelection(true);
        btnMean.setLayoutData((Object)new GridData(4, 4, true, true, 1, 1));
        btnMean.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        btnMean.setText("Mean");
        btnMean.setData((Object)"mean");
        Button btnMedian = new Button((Composite)grpCentralTendency, 32);
        btnMedian.setLayoutData((Object)new GridData(4, 4, true, true, 1, 1));
        btnMedian.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        btnMedian.setText("Median");
        btnMedian.setData((Object)"median");
        new Label(cmpStatistics, 0);
        Group grpLocation = new Group(cmpStatistics, 0);
        grpLocation.setLayoutData((Object)new GridData(4, 4, true, true, 2, 1));
        grpLocation.setText("Location");
        grpLocation.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        grpLocation.setLayout((Layout)new GridLayout(1, false));
        Button btnMinimum = new Button((Composite)grpLocation, 32);
        btnMinimum.setSelection(true);
        btnMinimum.setLayoutData((Object)new GridData(4, 4, true, true, 1, 1));
        btnMinimum.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        btnMinimum.setText("Minimum");
        btnMinimum.setData((Object)"min");
        Button btnMaximum = new Button((Composite)grpLocation, 32);
        btnMaximum.setSelection(true);
        btnMaximum.setLayoutData((Object)new GridData(4, 4, true, true, 1, 1));
        btnMaximum.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        btnMaximum.setText("Maximum");
        btnMaximum.setData((Object)"max");
        Button btnStQuartile = new Button((Composite)grpLocation, 32);
        btnStQuartile.setLayoutData((Object)new GridData(4, 4, true, true, 1, 1));
        btnStQuartile.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        btnStQuartile.setText("1st Quartile");
        btnStQuartile.setData((Object)"q1");
        Button btnrdQuartile = new Button((Composite)grpLocation, 32);
        btnrdQuartile.setLayoutData((Object)new GridData(4, 4, true, true, 1, 1));
        btnrdQuartile.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        btnrdQuartile.setText("3rd Quartile");
        btnrdQuartile.setData((Object)"q3");
        Group grpDispersion = new Group(cmpStatistics, 0);
        grpDispersion.setLayoutData((Object)new GridData(4, 4, true, true, 1, 2));
        grpDispersion.setText("Dispersion");
        grpDispersion.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        grpDispersion.setLayout((Layout)new GridLayout(1, false));
        Button btnRange = new Button((Composite)grpDispersion, 32);
        btnRange.setLayoutData((Object)new GridData(4, 4, true, true, 1, 1));
        btnRange.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        btnRange.setText("Range");
        btnRange.setData((Object)"range");
        Button btnInterquartileRange = new Button((Composite)grpDispersion, 32);
        btnInterquartileRange.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        btnInterquartileRange.setLayoutData((Object)new GridData(4, 4, true, true, 1, 1));
        btnInterquartileRange.setText("Interquartile Range");
        btnInterquartileRange.setData((Object)"iqr");
        Button btnVariance = new Button((Composite)grpDispersion, 32);
        btnVariance.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        btnVariance.setLayoutData((Object)new GridData(4, 4, true, true, 1, 1));
        btnVariance.setText("Variance");
        btnVariance.setData((Object)"var");
        Button btnStandardDeviation = new Button((Composite)grpDispersion, 32);
        btnStandardDeviation.setSelection(true);
        btnStandardDeviation.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        btnStandardDeviation.setLayoutData((Object)new GridData(4, 4, true, true, 1, 1));
        btnStandardDeviation.setText("Standard Deviation");
        btnStandardDeviation.setData((Object)"sd");
        Button btnStandardErrorOfTheMean = new Button((Composite)grpDispersion, 32);
        btnStandardErrorOfTheMean.setLayoutData((Object)new GridData(4, 4, true, true, 1, 1));
        btnStandardErrorOfTheMean.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        btnStandardErrorOfTheMean.setText("Standard Error of the Mean");
        btnStandardErrorOfTheMean.setData((Object)"se.mean");
        Button btnCoefficientOfVariation = new Button((Composite)grpDispersion, 32);
        btnCoefficientOfVariation.setLayoutData((Object)new GridData(4, 4, true, true, 1, 1));
        btnCoefficientOfVariation.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        btnCoefficientOfVariation.setText("Coefficient of Variation");
        btnCoefficientOfVariation.setData((Object)"cv");
        new Label(cmpStatistics, 0);
        Group grpDistribution = new Group(cmpStatistics, 0);
        grpDistribution.setLayoutData((Object)new GridData(4, 4, true, true, 2, 1));
        grpDistribution.setLayout((Layout)new GridLayout(1, false));
        grpDistribution.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        grpDistribution.setText("Distribution");
        Button btnSkewness = new Button((Composite)grpDistribution, 32);
        btnSkewness.setLayoutData((Object)new GridData(4, 4, true, true, 1, 1));
        btnSkewness.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        btnSkewness.setText("Skewness");
        btnSkewness.setData((Object)"skew");
        Button btnKurtosis = new Button((Composite)grpDistribution, 32);
        btnKurtosis.setLayoutData((Object)new GridData(4, 4, true, true, 1, 1));
        btnKurtosis.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        btnKurtosis.setText("Kurtosis");
        btnKurtosis.setData((Object)"kurtosis");
        new Label(cmpStatistics, 0);
        Composite grpButtons = new Composite(cmpStatistics, 0);
        grpButtons.setLayoutData((Object)new GridData(4, 4, true, false, 1, 1));
        GridLayout gl_grpButtons = new GridLayout(1, false);
        grpButtons.setLayout((Layout)gl_grpButtons);
        Button btnUncheckAll = new Button(grpButtons, 0);
        GridData gd_btnUncheckAll = new GridData(4, 4, true, true, 1, 1);
        gd_btnUncheckAll.widthHint = 20;
        btnUncheckAll.setLayoutData((Object)gd_btnUncheckAll);
        btnUncheckAll.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        btnUncheckAll.setText("Uncheck All");
        Button btnCheckAll = new Button(grpButtons, 0);
        GridData gd_btnCheckAll = new GridData(4, 4, true, true, 1, 1);
        gd_btnCheckAll.widthHint = 20;
        btnCheckAll.setLayoutData((Object)gd_btnCheckAll);
        btnCheckAll.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        btnCheckAll.setText("Check All");
        this.btnDefaults = new Button(grpButtons, 0);
        GridData gd_btnDefaults = new GridData(4, 4, true, true, 1, 1);
        gd_btnDefaults.widthHint = 20;
        this.btnDefaults.setLayoutData((Object)gd_btnDefaults);
        this.btnDefaults.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                DescriptiveAnalysisDialog.this.listManager.setCheckBoxesToBoolean(DescriptiveAnalysisDialog.this.groups.toArray(new Composite[DescriptiveAnalysisDialog.this.groups.size()]), false);
                btnNoOfNonmissing.setSelection(true);
                btnStandardDeviation.setSelection(true);
                btnMean.setSelection(true);
                btnMinimum.setSelection(true);
                btnMaximum.setSelection(true);
            }
        });
        this.btnDefaults.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        this.btnDefaults.setText("Defaults");
        btnCheckAll.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                DescriptiveAnalysisDialog.this.listManager.setCheckBoxesToBoolean(DescriptiveAnalysisDialog.this.groups.toArray(new Composite[DescriptiveAnalysisDialog.this.groups.size()]), true);
            }
        });
        btnUncheckAll.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                DescriptiveAnalysisDialog.this.listManager.setCheckBoxesToBoolean(DescriptiveAnalysisDialog.this.groups.toArray(new Composite[DescriptiveAnalysisDialog.this.groups.size()]), false);
            }
        });
        this.groups.addAll(Arrays.asList(grpOthers, grpDispersion, grpCentralTendency, grpLocation, grpDistribution));
        new Label(cmpStatistics, 0);
        this.initializeList();
        return container;
    }

    protected void buttonPressed(int buttonId) {
        if (buttonId == 0) {
            this.okPressed();
        } else if (buttonId == 2135) {
            this.btnDefaults.notifyListeners(13, null);
            this.lstFactorialVariables.removeAll();
            this.lstNumericVariables.removeAll();
            this.lstVariables.removeAll();
            this.lstByVariables.removeAll();
            this.btnAddSingleVariable.setText("Add");
            this.btnAddSingleVariable.setEnabled(false);
            this.btnAddFactorVariable.setText("Add");
            this.btnAddFactorVariable.setEnabled(false);
            this.btnFactornumeric.setText("Set to Factor");
            this.btnFactornumeric.setEnabled(false);
            this.listManager.initializeFactorList(this.lstFactorialVariables, this.filePath);
            this.listManager.initializeNumericList(this.lstNumericVariables, this.filePath);
        } else if (1 == buttonId) {
            this.close();
        }
    }

    protected void okPressed() {
        if (this.validateForm(this.lstVariables, this.lstByVariables, this.groups.toArray(new Composite[this.groups.size()]), this.btnOk)) {
            ProjectExplorerManager projectMan = new ProjectExplorerManager();
            Calendar.getInstance();
            File outputFolder = new File(StarAnalysisUtilities.createOutputFolder(this.filePath, "DescriptiveAnalysis"));
            OperationProgressDialog rInfo = new OperationProgressDialog(this.getShell(), "Performing Analysis");
            rInfo.open();
            String outputFile = (String.valueOf(outputFolder.getPath().toString()) + File.separator + "Output.txt").toString().replace(File.separator, "/");
            outputFolder.mkdir();
            String file = this.filePath.replace(File.separator, "/");
            String[] respVar = this.lstVariables.getItems();
            String[] grpVar = this.lstByVariables.getItemCount() > 0 ? this.lstByVariables.getItems() : null;
            String[] statistics = this.listManager.getCheckBoxesValue(this.groups.toArray(new Composite[this.groups.size()]));
            System.out.println("DATA:  " + file + " " + outputFile + " " + respVar + " " + grpVar + " " + statistics);
            ProjectExplorerView.rJavaManager.getSTARAnalysisManager().doDescriptiveStatistics(file, outputFile, respVar, grpVar, statistics);
            projectMan.refreshFolder((ProjectExplorerTreeNodeModel)projectMan.getOutputFolder(ProjectExplorerView.projectTree).getData());
            projectMan.refreshFolder(projectMan.getTreeNodeModelbyFilePath(outputFolder.getAbsolutePath()));
            this.getShell().setMinimized(true);
            PartStackHandler.execute(projectMan.getTreeNodeModelbyFilePath(outputFile.replace("/", File.separator)));
            rInfo.close();
        }
    }

    protected void createButtonsForButtonBar(Composite parent) {
        this.createButton(parent, 2135, "Reset", false);
        this.btnOk = this.createButton(parent, 0, IDialogConstants.OK_LABEL, true);
        this.createButton(parent, 1, IDialogConstants.CANCEL_LABEL, false);
    }

    private void initializeList() {
        this.listManager.initializeSingleButtonList(this.lstNumericVariables, this.lstVariables, this.btnFactornumeric, this.btnAddSingleVariable);
        this.listManager.initializeSingleButtonList(this.lstFactorialVariables, this.lstByVariables, this.btnFactornumeric, this.btnAddFactorVariable);
        this.listManager.initializeFactorList(this.lstFactorialVariables, this.filePath);
        this.listManager.initializeNumericList(this.lstNumericVariables, this.filePath);
        this.listManager.initializeVariableMoveList(this.lstNumericVariables, this.lstFactorialVariables, this.btnFactornumeric, this.filePath);
    }

    private boolean validateForm(List lstVariables, List lstByVariables, Composite[] groupComposites, Button btnOkay) {
        if (lstVariables.getItemCount() >= 1 && this.listManager.isCheckBoxesHasBoolean(groupComposites)) {
            return true;
        }
        MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"At least one required field is empty.  Variable(s) list box of the Variable Description tab should contain at least one item and at least one item in the Statistics tab should be chosen");
        return false;
    }

    protected Point getInitialSize() {
        return new Point(499, 479);
    }
}

