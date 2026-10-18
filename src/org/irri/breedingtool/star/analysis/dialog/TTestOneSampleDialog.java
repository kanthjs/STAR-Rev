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

public class TTestOneSampleDialog
extends Dialog {
    private String filePath = PartStackHandler.getActiveElementID();
    private List lstNumericVariables;
    private Button btnAdd;
    private Spinner txtTestValue;
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
    private Group grpDisplay;

    public TTestOneSampleDialog(Shell parentShell) {
        super(parentShell);
        this.setShellStyle(2256);
    }

    protected void configureShell(Shell shell) {
        super.configureShell(shell);
        shell.setText("One Sample t-Test: " + new File(this.filePath).getName());
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
        lblTestVariables.setText("Test Variable(s):");
        this.lstNumericVariables = new List(composite, 2818);
        GridData gd_lstNumericVariables = new GridData(4, 4, true, true, 1, 2);
        gd_lstNumericVariables.heightHint = 204;
        gd_lstNumericVariables.widthHint = 143;
        this.lstNumericVariables.setLayoutData((Object)gd_lstNumericVariables);
        this.btnAdd = new Button(composite, 0);
        GridData gd_btnAdd = new GridData(0x1000000, 0x1000000, true, true, 1, 1);
        gd_btnAdd.widthHint = 90;
        this.btnAdd.setLayoutData((Object)gd_btnAdd);
        this.btnAdd.setText("Add");
        this.lstTestVariables = new List(composite, 2818);
        GridData gd_lstTestVariables = new GridData(4, 4, true, true, 2, 1);
        gd_lstTestVariables.widthHint = 143;
        gd_lstTestVariables.heightHint = 204;
        this.lstTestVariables.setLayoutData((Object)gd_lstTestVariables);
        new Label(composite, 0);
        Label lblTestValue = new Label(composite, 0);
        lblTestValue.setLayoutData((Object)new GridData(16384, 0x1000000, true, true, 1, 1));
        lblTestValue.setText("Test Value:");
        this.txtTestValue = new Spinner(composite, 2048);
        this.txtTestValue.setMaximum(100000);
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
        this.btnSummaryStatistics.setLayoutData((Object)new GridData(16384, 0x1000000, false, false, 2, 1));
        this.btnSummaryStatistics.setText("Summary Statistics");
        new Label((Composite)this.grpDisplay, 0);
        this.btnConfidenceInterval = new Button((Composite)this.grpDisplay, 32);
        this.btnConfidenceInterval.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                TTestOneSampleDialog.this.txtConfidenceInterval.setEnabled(TTestOneSampleDialog.this.btnConfidenceInterval.getSelection());
            }
        });
        this.btnConfidenceInterval.setLayoutData((Object)new GridData(4, 0x1000000, true, true, 1, 1));
        this.btnConfidenceInterval.setText("Confidence Interval");
        this.txtConfidenceInterval = new Spinner((Composite)this.grpDisplay, 2048);
        this.txtConfidenceInterval.setIncrement(100);
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
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        this.listManager.initializeSingleButtonList(this.lstNumericVariables, this.lstTestVariables, this.btnAdd);
        this.listManager.initializeNumericList(this.lstNumericVariables, this.filePath);
        return container;
    }

    protected void okPressed() {
        if (this.lstTestVariables.getItemCount() >= 1) {
            this.btnOkay.setEnabled(false);
            String[] respvars = this.lstTestVariables.getItems();
            Integer testValue = this.txtTestValue.getSelection();
            String altHypo = this.cmbAlternativeHypothesis.getText();
            boolean statistics = this.btnSummaryStatistics.getSelection();
            boolean confInt = this.btnConfidenceInterval.getSelection();
            float confLevel = (float)this.txtConfidenceInterval.getSelection() / 100.0f;
            Composite[] cmpGroup = new Composite[]{this.grpTestForNormality};
            String[] method = this.listManager.getCheckBoxesValue(cmpGroup);
            String outputFolder = StarAnalysisUtilities.createOutputFolder(this.filePath, "TTestOneSample");
            OperationProgressDialog rInfo = new OperationProgressDialog(this.getShell(), "Performing Analysis");
            rInfo.open();
            String dataFileName = this.filePath.replace(File.separator, "/");
            String outFileName = String.valueOf(outputFolder) + "//Output.txt";
            ProjectExplorerView.rJavaManager.getSTARAnalysisManager().doOneMean(dataFileName, outFileName.replace(File.separator, "/"), respvars, testValue, altHypo, statistics, confInt, confLevel, method);
            this.getShell().setMinimized(true);
            StarAnalysisUtilities.openAndRefreshFolder(outFileName);
            rInfo.close();
            this.btnOkay.setEnabled(true);
        } else {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"Test Variable(s) listbox of the Variable Description Tab should contain atleast one item.");
        }
    }

    protected void buttonPressed(int buttonId) {
        if (buttonId == 0) {
            this.okPressed();
        } else if (buttonId == 8) {
            this.lstNumericVariables.removeAll();
            this.lstTestVariables.removeAll();
            this.listManager.initializeNumericList(this.lstNumericVariables, this.filePath);
            this.listManager.setCheckBoxesToBoolean(false, new Composite[]{this.grpDisplay, this.grpTestForNormality});
            this.cmbAlternativeHypothesis.select(2);
            this.txtConfidenceInterval.setSelection(9500);
            this.txtTestValue.setSelection(0);
            this.txtConfidenceInterval.setEnabled(false);
            this.btnAdd.setText("Add");
            this.btnAdd.setEnabled(false);
        } else if (1 == buttonId) {
            this.close();
        }
    }

    protected void createButtonsForButtonBar(Composite parent) {
        this.createButton(parent, 8, "Reset", false);
        this.btnOkay = this.createButton(parent, 0, IDialogConstants.OK_LABEL, true);
        this.createButton(parent, 1, IDialogConstants.CANCEL_LABEL, false);
    }

    protected Point getInitialSize() {
        return new Point(417, 398);
    }
}

