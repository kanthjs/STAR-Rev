/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.jface.dialogs.Dialog
 *  org.eclipse.jface.dialogs.IDialogConstants
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
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
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
import org.irri.breedingtool.projectexplorer.view.ProjectExplorerView;
import org.irri.breedingtool.utility.DialogFormUtility;
import org.irri.breedingtool.utility.StarAnalysisUtilities;

public class TTestIndependentSampleDialog
extends Dialog {
    private String filePath = PartStackHandler.getActiveElementID();
    private List lstNumericVariables;
    private Button btnToFactor;
    private Button btnSummaryStatistics;
    private Combo cmbAlternativeHypothesis;
    private Spinner txtConfidenceInterval;
    private Button btnAndersondarling;
    private Button btnCramervonMises;
    private Button btnLilliefors;
    private Button btnShapirofrancia;
    private Button btnShapirowilk;
    private DialogFormUtility listManager = new DialogFormUtility();
    private Button btnConfidenceInterval;
    private List lstTestVariables;
    private Group grpTestForNormality;
    private Button btnOkay;
    private Label lblFactors;
    private List lstFactors;
    private Text txtGroupingVariable;
    private Button btnAddRemove2;
    private Label lblNewLabel;
    private Button btnAddRemove1;
    private Label lblLevelOfSig;
    private Spinner txtLevelOfSig;
    private Group grpDisplay;

    public TTestIndependentSampleDialog(Shell parentShell) {
        super(parentShell);
        this.setShellStyle(2256);
    }

    protected void configureShell(Shell shell) {
        super.configureShell(shell);
        shell.setText("Two Independent Sample t-Test: " + new File(this.filePath).getName());
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
        composite.setLayout((Layout)new GridLayout(3, false));
        Label lblNumericVariables = new Label(composite, 0);
        lblNumericVariables.setLayoutData((Object)new GridData(4, 4, true, false, 1, 1));
        lblNumericVariables.setText("Numeric Variable(s):");
        new Label(composite, 0);
        Label lblTestVariables = new Label(composite, 0);
        lblTestVariables.setLayoutData((Object)new GridData(4, 4, true, false, 1, 1));
        lblTestVariables.setText("Test Variable(s):");
        this.lstNumericVariables = new List(composite, 2818);
        GridData gd_lstNumericVariables = new GridData(4, 4, true, true, 1, 1);
        gd_lstNumericVariables.heightHint = 80;
        gd_lstNumericVariables.widthHint = 80;
        this.lstNumericVariables.setLayoutData((Object)gd_lstNumericVariables);
        this.btnAddRemove1 = new Button(composite, 0);
        GridData gd_btnAddRemove1 = new GridData(0x1000000, 0x1000000, false, false, 1, 1);
        gd_btnAddRemove1.heightHint = 22;
        gd_btnAddRemove1.widthHint = 90;
        this.btnAddRemove1.setLayoutData((Object)gd_btnAddRemove1);
        this.btnAddRemove1.setText("Add");
        this.lstTestVariables = new List(composite, 2818);
        GridData gd_lstTestVariables = new GridData(4, 4, true, true, 1, 1);
        gd_lstTestVariables.widthHint = 80;
        gd_lstTestVariables.heightHint = 80;
        this.lstTestVariables.setLayoutData((Object)gd_lstTestVariables);
        this.btnToFactor = new Button(composite, 0);
        GridData gd_btnToFactor = new GridData(131072, 0x1000000, true, false, 1, 1);
        gd_btnToFactor.heightHint = 24;
        gd_btnToFactor.widthHint = 110;
        this.btnToFactor.setLayoutData((Object)gd_btnToFactor);
        this.btnToFactor.setText("Set to Factor");
        new Label(composite, 0);
        new Label(composite, 0);
        TabItem tbtmOption = new TabItem(tabFolder, 0);
        tbtmOption.setText("Options");
        Composite composite_1 = new Composite((Composite)tabFolder, 0);
        tbtmOption.setControl((Control)composite_1);
        composite_1.setLayout((Layout)new GridLayout(2, false));
        Label lblAlternativeHypothesis = new Label(composite_1, 0);
        lblAlternativeHypothesis.setLayoutData((Object)new GridData(16384, 0x1000000, true, false, 1, 1));
        lblAlternativeHypothesis.setText("Alternative Hypothesis:");
        this.cmbAlternativeHypothesis = new Combo(composite_1, 8);
        this.cmbAlternativeHypothesis.setItems(new String[]{"less", "greater", "two.sided"});
        GridData gd_cmbAlternativeHypothesis = new GridData(4, 0x1000000, true, false, 1, 1);
        gd_cmbAlternativeHypothesis.widthHint = 161;
        this.cmbAlternativeHypothesis.setLayoutData((Object)gd_cmbAlternativeHypothesis);
        this.cmbAlternativeHypothesis.setText("two.sided");
        this.lblLevelOfSig = new Label(composite_1, 0);
        GridData gd_lblLevelOfSig = new GridData(4, 0x1000000, true, true, 1, 1);
        gd_lblLevelOfSig.heightHint = 24;
        gd_lblLevelOfSig.widthHint = 205;
        this.lblLevelOfSig.setLayoutData((Object)gd_lblLevelOfSig);
        this.lblLevelOfSig.setText("Level of Sig. (Testing Variance Equality): ");
        this.txtLevelOfSig = new Spinner(composite_1, 2048);
        this.txtLevelOfSig.setMaximum(1000);
        this.txtLevelOfSig.setMinimum(1);
        this.txtLevelOfSig.setSelection(5);
        this.txtLevelOfSig.setDigits(2);
        this.txtLevelOfSig.setLayoutData((Object)new GridData(16384, 0x1000000, true, false, 1, 1));
        this.grpDisplay = new Group(composite_1, 0);
        this.grpDisplay.setText("Display");
        this.grpDisplay.setLayout((Layout)new GridLayout(3, false));
        GridData gd_grpDisplay = new GridData(4, 4, true, true, 2, 1);
        gd_grpDisplay.heightHint = 35;
        this.grpDisplay.setLayoutData((Object)gd_grpDisplay);
        this.btnSummaryStatistics = new Button((Composite)this.grpDisplay, 32);
        this.btnSummaryStatistics.setLayoutData((Object)new GridData(16384, 0x1000000, false, false, 2, 1));
        this.btnSummaryStatistics.setText("Summary Statistics");
        new Label((Composite)this.grpDisplay, 0);
        this.btnConfidenceInterval = new Button((Composite)this.grpDisplay, 32);
        this.btnConfidenceInterval.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                TTestIndependentSampleDialog.this.txtConfidenceInterval.setEnabled(TTestIndependentSampleDialog.this.btnConfidenceInterval.getSelection());
            }
        });
        GridData gd_btnConfidenceInterval = new GridData(4, 0x1000000, true, true, 1, 1);
        gd_btnConfidenceInterval.widthHint = 157;
        this.btnConfidenceInterval.setLayoutData((Object)gd_btnConfidenceInterval);
        this.btnConfidenceInterval.setText("Confidence Interval");
        this.txtConfidenceInterval = new Spinner((Composite)this.grpDisplay, 2048);
        this.txtConfidenceInterval.setPageIncrement(100);
        this.txtConfidenceInterval.setDigits(2);
        this.txtConfidenceInterval.setLayoutData((Object)new GridData(4, 0x1000000, true, true, 1, 1));
        this.txtConfidenceInterval.setMaximum(9900);
        this.txtConfidenceInterval.setMinimum(9000);
        this.txtConfidenceInterval.setSelection(9500);
        this.txtConfidenceInterval.setEnabled(false);
        Label label = new Label((Composite)this.grpDisplay, 0);
        GridData gd_label = new GridData(4, 0x1000000, true, false, 1, 1);
        gd_label.widthHint = 127;
        label.setLayoutData((Object)gd_label);
        label.setText("%");
        this.grpTestForNormality = new Group(composite_1, 0);
        this.grpTestForNormality.setText("Test for Normality");
        this.grpTestForNormality.setLayout((Layout)new GridLayout(1, false));
        this.grpTestForNormality.setLayoutData((Object)new GridData(4, 4, true, true, 2, 1));
        this.btnShapirowilk = new Button((Composite)this.grpTestForNormality, 32);
        this.btnShapirowilk.setLayoutData((Object)new GridData(16384, 4, true, true, 1, 1));
        this.btnShapirowilk.setText("Shapiro-Wilk");
        this.btnShapirowilk.setData((Object)"swilk");
        this.btnShapirofrancia = new Button((Composite)this.grpTestForNormality, 32);
        this.btnShapirofrancia.setLayoutData((Object)new GridData(16384, 4, true, true, 1, 1));
        this.btnShapirofrancia.setText("Shapiro-Francia");
        this.btnShapirofrancia.setData((Object)"sfrancia");
        this.btnLilliefors = new Button((Composite)this.grpTestForNormality, 32);
        this.btnLilliefors.setLayoutData((Object)new GridData(16384, 4, true, true, 1, 1));
        this.btnLilliefors.setText("Lilliefors (Kolmogorov-Smirnov)");
        this.btnLilliefors.setData((Object)"ks");
        this.btnCramervonMises = new Button((Composite)this.grpTestForNormality, 32);
        this.btnCramervonMises.setLayoutData((Object)new GridData(16384, 4, true, true, 1, 1));
        this.btnCramervonMises.setText("Cramer-Von Mises");
        this.btnCramervonMises.setData((Object)"cramer");
        this.btnAndersondarling = new Button((Composite)this.grpTestForNormality, 32);
        this.btnAndersondarling.setLayoutData((Object)new GridData(16384, 4, true, true, 1, 1));
        this.btnAndersondarling.setText("Anderson-Darling");
        this.btnAndersondarling.setData((Object)"anderson");
        this.lblFactors = new Label(composite, 0);
        this.lblFactors.setText("Factor(s):");
        new Label(composite, 0);
        this.lblNewLabel = new Label(composite, 0);
        this.lblNewLabel.setText("Grouping Variable:");
        this.lstFactors = new List(composite, 2816);
        GridData gd_lstFactors = new GridData(4, 4, true, true, 1, 2);
        gd_lstFactors.widthHint = 80;
        gd_lstFactors.heightHint = 60;
        this.lstFactors.setLayoutData((Object)gd_lstFactors);
        this.btnAddRemove2 = new Button(composite, 0);
        GridData gd_btnAddRemove2 = new GridData(0x1000000, 4, false, false, 1, 1);
        gd_btnAddRemove2.heightHint = 22;
        gd_btnAddRemove2.widthHint = 90;
        this.btnAddRemove2.setLayoutData((Object)gd_btnAddRemove2);
        this.btnAddRemove2.setText("Add");
        this.txtGroupingVariable = new Text(composite, 2056);
        this.txtGroupingVariable.setBackground(SWTResourceManager.getColor(25));
        GridData gd_txtGroupingVariable = new GridData(4, 4, true, false, 1, 1);
        gd_txtGroupingVariable.widthHint = 80;
        gd_txtGroupingVariable.heightHint = 14;
        this.txtGroupingVariable.setLayoutData((Object)gd_txtGroupingVariable);
        new Label(composite, 0);
        new Label(composite, 0);
        this.initializeContent();
        return container;
    }

    void initializeContent() {
        this.listManager.initializeSingleButtonList(this.lstNumericVariables, this.lstTestVariables, this.btnToFactor, this.btnAddRemove1);
        this.listManager.initializeSingleSelectionList(this.lstFactors, this.txtGroupingVariable, this.btnToFactor, this.btnAddRemove2);
        this.listManager.initializeVariableMoveList(this.lstNumericVariables, this.lstFactors, this.btnToFactor, this.filePath, 2);
        this.listManager.initializeNumericList(this.lstNumericVariables, this.filePath);
        this.listManager.initializeFactorList(this.lstFactors, this.filePath, 2);
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
        this.lstNumericVariables.removeAll();
        this.lstTestVariables.removeAll();
        this.lstFactors.removeAll();
        this.txtGroupingVariable.setText("");
        this.listManager.initializeNumericList(this.lstNumericVariables, this.filePath);
        this.listManager.initializeFactorList(this.lstFactors, this.filePath, 2);
        this.cmbAlternativeHypothesis.select(2);
        this.txtLevelOfSig.setSelection(5);
        this.listManager.setCheckBoxesToBoolean(false, new Composite[]{this.grpDisplay, this.grpTestForNormality});
        this.txtConfidenceInterval.setSelection(9500);
        this.txtConfidenceInterval.setEnabled(false);
        this.btnAddRemove1.setText("Add");
        this.btnAddRemove2.setText("Add");
        this.btnAddRemove1.setEnabled(false);
        this.btnAddRemove2.setEnabled(false);
    }

    protected void okPressed() {
        if (this.lstTestVariables.getItemCount() >= 1) {
            this.btnOkay.setEnabled(false);
            String[] respvars = this.lstTestVariables.getItems();
            float levelSig = (float)this.txtLevelOfSig.getSelection() / 100.0f;
            String grp = this.txtGroupingVariable.getText();
            String altHypo = this.cmbAlternativeHypothesis.getText();
            boolean statistics = this.btnSummaryStatistics.getSelection();
            boolean confInt = this.btnConfidenceInterval.getSelection();
            float confLevel = (float)this.txtConfidenceInterval.getSelection() / 100.0f;
            Composite[] cmpGroup = new Composite[]{this.grpTestForNormality};
            String[] method = this.listManager.getCheckBoxesValue(cmpGroup);
            String outputFolder = StarAnalysisUtilities.createOutputFolder(this.filePath, "TTestTwoIndependentSample");
            OperationProgressDialog rInfo = new OperationProgressDialog(this.getShell(), "Performing Analysis");
            rInfo.open();
            String dataFileName = this.filePath.replace(File.separator, "/");
            String outFileName = String.valueOf(outputFolder) + File.separator + "Output.txt";
            StarAnalysisUtilities.testVarArgs(dataFileName, outFileName.replace(File.separator, "/"), respvars, grp, altHypo, Float.valueOf(levelSig), statistics, confInt, Float.valueOf(confLevel), method);
            ProjectExplorerView.rJavaManager.getSTARAnalysisManager().doIndependentMean(dataFileName, outFileName.replace(File.separator, "/"), respvars, grp, altHypo, (double)levelSig, statistics, confInt, confLevel, method);
            this.getShell().setMinimized(true);
            StarAnalysisUtilities.openAndRefreshFolder(outFileName);
            rInfo.close();
            this.btnOkay.setEnabled(true);
        }
    }

    protected void createButtonsForButtonBar(Composite parent) {
        this.createButton(parent, 8, "Reset", false);
        this.btnOkay = this.createButton(parent, 0, IDialogConstants.OK_LABEL, true);
        this.createButton(parent, 1, IDialogConstants.CANCEL_LABEL, false);
    }

    protected Point getInitialSize() {
        return new Point(482, 449);
    }
}

