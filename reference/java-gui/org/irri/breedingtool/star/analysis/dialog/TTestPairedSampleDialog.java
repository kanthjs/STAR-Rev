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
 */
package org.irri.breedingtool.star.analysis.dialog;

import java.io.File;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
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
import org.irri.breedingtool.application.handler.PartStackHandler;
import org.irri.breedingtool.datamanipulation.dialog.OperationProgressDialog;
import org.irri.breedingtool.projectexplorer.view.ProjectExplorerView;
import org.irri.breedingtool.utility.DialogFormUtility;
import org.irri.breedingtool.utility.StarAnalysisUtilities;

public class TTestPairedSampleDialog
extends Dialog {
    private String filePath = PartStackHandler.getActiveElementID();
    private List lstNumericVariables;
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
    private Group grpTestForNormality;
    private Button btnOkay;
    private Label lblDataSet;
    private Button btnDataSet2;
    private Button btnDataSet1;
    private List lstDataSet1;
    private List lstDataSet2;
    private Group grpDisplay;

    public TTestPairedSampleDialog(Shell parentShell) {
        super(parentShell);
        this.setShellStyle(2256);
    }

    protected void configureShell(Shell shell) {
        super.configureShell(shell);
        shell.setText("Paired Sample t-Test : " + new File(this.filePath).getName());
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
        composite.setLayout((Layout)new GridLayout(4, false));
        Label lblNumericVariables = new Label(composite, 0);
        lblNumericVariables.setLayoutData((Object)new GridData(4, 4, true, false, 1, 1));
        lblNumericVariables.setText("Numeric Variable(s):");
        new Label(composite, 0);
        Label lblTestVariables = new Label(composite, 0);
        lblTestVariables.setLayoutData((Object)new GridData(4, 4, true, false, 2, 1));
        lblTestVariables.setText("Data Set 1:");
        this.lstNumericVariables = new List(composite, 2818);
        GridData gd_lstNumericVariables = new GridData(4, 4, true, true, 1, 3);
        gd_lstNumericVariables.heightHint = 130;
        gd_lstNumericVariables.widthHint = 80;
        this.lstNumericVariables.setLayoutData((Object)gd_lstNumericVariables);
        this.btnDataSet1 = new Button(composite, 0);
        GridData gd_btnDataSet1 = new GridData(0x1000000, 0x1000000, false, false, 1, 1);
        gd_btnDataSet1.widthHint = 90;
        this.btnDataSet1.setLayoutData((Object)gd_btnDataSet1);
        this.btnDataSet1.setText("Add");
        TabItem tbtmOption = new TabItem(tabFolder, 0);
        tbtmOption.setText("Options");
        Composite composite_1 = new Composite((Composite)tabFolder, 0);
        tbtmOption.setControl((Control)composite_1);
        composite_1.setLayout((Layout)new GridLayout(2, false));
        Label lblAlternativeHypothesis = new Label(composite_1, 0);
        lblAlternativeHypothesis.setLayoutData((Object)new GridData(131072, 0x1000000, false, false, 1, 1));
        lblAlternativeHypothesis.setText("Alternative Hypothesis:");
        this.cmbAlternativeHypothesis = new Combo(composite_1, 8);
        this.cmbAlternativeHypothesis.setItems(new String[]{"less", "greater", "two.sided"});
        GridData gd_cmbAlternativeHypothesis = new GridData(4, 0x1000000, true, false, 1, 1);
        gd_cmbAlternativeHypothesis.widthHint = 5;
        this.cmbAlternativeHypothesis.setLayoutData((Object)gd_cmbAlternativeHypothesis);
        this.cmbAlternativeHypothesis.setText("two.sided");
        this.grpDisplay = new Group(composite_1, 0);
        this.grpDisplay.setText("Display");
        this.grpDisplay.setLayout((Layout)new GridLayout(3, false));
        this.grpDisplay.setLayoutData((Object)new GridData(4, 4, true, true, 2, 1));
        this.btnSummaryStatistics = new Button((Composite)this.grpDisplay, 32);
        GridData gd_btnSummaryStatistics = new GridData(16384, 0x1000000, false, false, 2, 1);
        gd_btnSummaryStatistics.heightHint = 25;
        this.btnSummaryStatistics.setLayoutData((Object)gd_btnSummaryStatistics);
        this.btnSummaryStatistics.setText("Summary Statistics");
        new Label((Composite)this.grpDisplay, 0);
        this.btnConfidenceInterval = new Button((Composite)this.grpDisplay, 32);
        this.btnConfidenceInterval.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                TTestPairedSampleDialog.this.txtConfidenceInterval.setEnabled(TTestPairedSampleDialog.this.btnConfidenceInterval.getSelection());
            }
        });
        GridData gd_btnConfidenceInterval = new GridData(4, 0x1000000, true, false, 1, 1);
        gd_btnConfidenceInterval.heightHint = 26;
        this.btnConfidenceInterval.setLayoutData((Object)gd_btnConfidenceInterval);
        this.btnConfidenceInterval.setText("Confidence Interval");
        this.txtConfidenceInterval = new Spinner((Composite)this.grpDisplay, 2048);
        this.txtConfidenceInterval.setDigits(2);
        this.txtConfidenceInterval.setLayoutData((Object)new GridData(4, 0x1000000, true, false, 1, 1));
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
        this.lstDataSet1 = new List(composite, 2818);
        GridData gd_lstDataSet1 = new GridData(4, 4, true, true, 2, 1);
        gd_lstDataSet1.heightHint = 60;
        gd_lstDataSet1.widthHint = 80;
        this.lstDataSet1.setLayoutData((Object)gd_lstDataSet1);
        new Label(composite, 0);
        this.lblDataSet = new Label(composite, 0);
        this.lblDataSet.setText("Data Set 2:");
        new Label(composite, 0);
        this.btnDataSet2 = new Button(composite, 0);
        GridData gd_btnDataSet2 = new GridData(0x1000000, 0x1000000, false, false, 1, 1);
        gd_btnDataSet2.widthHint = 90;
        this.btnDataSet2.setLayoutData((Object)gd_btnDataSet2);
        this.btnDataSet2.setText("Add");
        this.lstDataSet2 = new List(composite, 2818);
        GridData gd_lstDataSet2 = new GridData(4, 4, true, true, 2, 1);
        gd_lstDataSet2.heightHint = 60;
        gd_lstDataSet2.widthHint = 80;
        this.lstDataSet2.setLayoutData((Object)gd_lstDataSet2);
        this.initializeContent();
        return container;
    }

    public void initializeContent() {
        this.listManager.initializeNumericList(this.lstNumericVariables, this.filePath);
        this.listManager.initializeSingleButtonList(this.lstNumericVariables, this.lstDataSet1, this.btnDataSet1);
        this.listManager.initializeSingleButtonList(this.lstNumericVariables, this.lstDataSet2, this.btnDataSet2);
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
        this.lstDataSet1.removeAll();
        this.lstDataSet2.removeAll();
        this.listManager.initializeNumericList(this.lstNumericVariables, this.filePath);
        this.cmbAlternativeHypothesis.select(2);
        this.listManager.setCheckBoxesToBoolean(false, new Composite[]{this.grpDisplay, this.grpTestForNormality});
        this.txtConfidenceInterval.setSelection(9500);
        this.txtConfidenceInterval.setEnabled(false);
        this.btnDataSet1.setText("Add");
        this.btnDataSet2.setText("Add");
        this.btnDataSet1.setEnabled(false);
        this.btnDataSet2.setEnabled(false);
    }

    protected void okPressed() {
        if (this.lstDataSet1.getItemCount() > 0 && this.lstDataSet2.getItemCount() > 0) {
            if (this.lstDataSet1.getItemCount() != this.lstDataSet2.getItemCount()) {
                MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"Data Set 1 and Data Set 2 must be equal");
                return;
            }
            this.btnOkay.setEnabled(false);
            String altHypo = this.cmbAlternativeHypothesis.getText();
            boolean statistics = this.btnSummaryStatistics.getSelection();
            boolean confInt = this.btnConfidenceInterval.getSelection();
            float confLevel = (float)this.txtConfidenceInterval.getSelection() / 100.0f;
            Composite[] cmpGroup = new Composite[]{this.grpTestForNormality};
            String[] method = this.listManager.getCheckBoxesValue(cmpGroup);
            if (method.length <= 0) {
                method = null;
            }
            String outputFolder = StarAnalysisUtilities.createOutputFolder(this.filePath, "TTestPairedSample");
            OperationProgressDialog rInfo = new OperationProgressDialog(this.getShell(), "Performing Analysis");
            rInfo.open();
            String dataFileName = this.filePath.replace(File.separator, "/");
            String outFileName = String.valueOf(outputFolder) + "//Output.txt";
            StarAnalysisUtilities.testVarArgs(dataFileName, outFileName.replace(File.separator, "/"), this.lstDataSet1.getItems(), this.lstDataSet2.getItems(), altHypo, statistics, confInt, Float.valueOf(confLevel), method);
            ProjectExplorerView.rJavaManager.getSTARAnalysisManager().doPairedMean(dataFileName, outFileName.replace(File.separator, "/"), this.lstDataSet1.getItems(), this.lstDataSet2.getItems(), altHypo, statistics, confInt, confLevel, method);
            this.getShell().setMinimized(true);
            StarAnalysisUtilities.openAndRefreshFolder(outFileName);
            rInfo.close();
            this.btnOkay.setEnabled(true);
        } else {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"Data Set 1 and Data Set 2 must not be empty");
        }
    }

    protected void createButtonsForButtonBar(Composite parent) {
        this.createButton(parent, 8, "Reset", false);
        this.btnOkay = this.createButton(parent, 0, IDialogConstants.OK_LABEL, true);
        this.createButton(parent, 1, IDialogConstants.CANCEL_LABEL, false);
    }

    protected Point getInitialSize() {
        return new Point(432, 420);
    }
}

