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
 *  org.eclipse.swt.widgets.TabFolder
 *  org.eclipse.swt.widgets.TabItem
 *  org.eclipse.swt.widgets.Text
 */
package org.irri.breedingtool.star.analysis.dialog;

import java.io.File;
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
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;
import org.irri.breedingtool.application.handler.PartStackHandler;
import org.irri.breedingtool.datamanipulation.dialog.OperationProgressDialog;
import org.irri.breedingtool.projectexplorer.view.ProjectExplorerView;
import org.irri.breedingtool.utility.DialogFormUtility;
import org.irri.breedingtool.utility.DialogListValidator;
import org.irri.breedingtool.utility.StarAnalysisUtilities;

public class ManovaDialog
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
    private List lstResponseVariables;
    private ListViewer listViewer_2;
    private Button btnAddNumeric;
    private Label lblFactor;
    private Label lblResponseVars;
    private Text txtFactor;
    private Button btnAddFactor;
    private TabItem tbtmOptions_1;
    private Composite composite_1;
    private Label lblTestStat;
    private Combo cmboTestStat;
    private Group grpDisplay;
    private Button btnStatRespVars;
    private Button btnShapiroWilk;
    private Button btnBoxM;
    private Button btnBartletts;
    private List lstNumericVariables;
    private Button btnAddBlk;
    private Text txtBlk;
    private Label lblBlock;
    private Label lblDesign;
    private Combo cmbDesign;
    private Group grpSave;
    private Button btnSSHypo;
    private Button btnSSError;
    private String[] testStatistics = new String[]{"Pillai", "Wilks", "Hotelling-Lawley", "Roy"};

    public ManovaDialog(Shell parentShell) {
        super(parentShell);
        this.setShellStyle(2288);
    }

    protected void configureShell(Shell shell) {
        super.configureShell(shell);
        shell.setText("Multivariate Analysis of Variance : " + new File(this.filePath).getName());
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
        this.lblDesign = new Label(composite, 0);
        this.lblDesign.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        this.lblDesign.setText("Design:");
        this.cmbDesign = new Combo(composite, 8);
        this.cmbDesign.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (ManovaDialog.this.cmbDesign.getSelectionIndex() == 0) {
                    ManovaDialog.this.btnAddBlk.setEnabled(false);
                    ManovaDialog.this.lblBlock.setEnabled(false);
                    ManovaDialog.this.txtBlk.setEnabled(false);
                } else if (ManovaDialog.this.cmbDesign.getSelectionIndex() == 1) {
                    ManovaDialog.this.btnAddFactor.setEnabled(true);
                    ManovaDialog.this.btnAddBlk.setEnabled(true);
                    ManovaDialog.this.lblBlock.setEnabled(true);
                    ManovaDialog.this.txtBlk.setEnabled(true);
                }
            }
        });
        this.cmbDesign.setItems(new String[]{"Completely Randomized Design (CRD)", "Randomized Complete Block Design (RCBD)"});
        this.cmbDesign.setLayoutData((Object)new GridData(4, 0x1000000, true, false, 2, 1));
        this.cmbDesign.select(1);
        this.lblNumericVariables = new Label(composite, 0);
        this.lblNumericVariables.setLayoutData((Object)new GridData(16384, 0x1000000, true, false, 1, 1));
        this.lblNumericVariables.setText("Numeric Variables:");
        this.lblNumericVariables.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        new Label(composite, 0);
        this.lblResponseVars = new Label(composite, 0);
        this.lblResponseVars.setLayoutData((Object)new GridData(16384, 0x1000000, true, false, 1, 1));
        this.lblResponseVars.setText("Response Variables:");
        this.lblResponseVars.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        ListViewer listViewer = new ListViewer(composite, 2818);
        this.lstNumericVariables = listViewer.getList();
        GridData gd_lstNumericVariables = new GridData(4, 128, true, true, 1, 1);
        gd_lstNumericVariables.widthHint = 140;
        gd_lstNumericVariables.heightHint = 100;
        this.lstNumericVariables.setLayoutData((Object)gd_lstNumericVariables);
        this.lstNumericVariables.setItems(new String[0]);
        this.btnAddNumeric = new Button(composite, 0);
        GridData gd_btnAddNumeric = new GridData(0x1000000, 0x1000000, true, true, 1, 1);
        gd_btnAddNumeric.widthHint = 90;
        this.btnAddNumeric.setLayoutData((Object)gd_btnAddNumeric);
        this.btnAddNumeric.setText("Add");
        this.listViewer_2 = new ListViewer(composite, 2818);
        this.lstResponseVariables = this.listViewer_2.getList();
        GridData gd_lstResponseVariables = new GridData(4, 4, true, true, 1, 1);
        gd_lstResponseVariables.heightHint = 100;
        gd_lstResponseVariables.widthHint = 140;
        this.lstResponseVariables.setLayoutData((Object)gd_lstResponseVariables);
        this.lstResponseVariables.setItems(new String[0]);
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
        this.lblFactor = new Label(composite, 0);
        this.lblFactor.setLayoutData((Object)new GridData(16384, 0x1000000, true, false, 1, 1));
        this.lblFactor.setText("Treatment:");
        this.lblFactor.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        this.listViewer_1 = new ListViewer(composite, 2818);
        this.lstFactors = this.listViewer_1.getList();
        GridData gd_lstFactors = new GridData(4, 4, true, true, 1, 3);
        gd_lstFactors.widthHint = 140;
        gd_lstFactors.heightHint = 100;
        this.lstFactors.setLayoutData((Object)gd_lstFactors);
        this.lstFactors.setItems(new String[0]);
        this.btnAddFactor = new Button(composite, 0);
        GridData gd_btnAddFactor = new GridData(0x1000000, 128, true, true, 1, 1);
        gd_btnAddFactor.widthHint = 90;
        this.btnAddFactor.setLayoutData((Object)gd_btnAddFactor);
        this.btnAddFactor.setText("Add");
        this.txtFactor = new Text(composite, 2048);
        this.txtFactor.setBackground(SWTResourceManager.getColor(1));
        this.txtFactor.setEditable(false);
        GridData gd_txtFactor = new GridData(4, 128, true, true, 1, 1);
        gd_txtFactor.widthHint = 140;
        this.txtFactor.setLayoutData((Object)gd_txtFactor);
        new Label(composite, 0);
        this.lblBlock = new Label(composite, 0);
        this.lblBlock.setText("Block:");
        this.lblBlock.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        this.btnAddBlk = new Button(composite, 0);
        GridData gd_btnAddBlk = new GridData(0x1000000, 128, false, false, 1, 1);
        gd_btnAddBlk.widthHint = 90;
        this.btnAddBlk.setLayoutData((Object)gd_btnAddBlk);
        this.btnAddBlk.setText("Add");
        this.txtBlk = new Text(composite, 2048);
        this.txtBlk.setEditable(false);
        this.txtBlk.setBackground(SWTResourceManager.getColor(1));
        this.txtBlk.setLayoutData((Object)new GridData(4, 128, true, false, 1, 1));
        this.tbtmOptions_1 = new TabItem(tabFolder, 0);
        this.tbtmOptions_1.setText("Options");
        this.composite_1 = new Composite((Composite)tabFolder, 0);
        this.tbtmOptions_1.setControl((Control)this.composite_1);
        GridLayout gl_composite_1 = new GridLayout(2, false);
        gl_composite_1.marginHeight = 10;
        gl_composite_1.marginWidth = 10;
        this.composite_1.setLayout((Layout)gl_composite_1);
        this.lblTestStat = new Label(this.composite_1, 0);
        this.lblTestStat.setLayoutData((Object)new GridData(4, 4, true, false, 1, 1));
        this.lblTestStat.setText("Test Statistic:");
        this.cmboTestStat = new Combo(this.composite_1, 8);
        this.cmboTestStat.setItems(new String[]{"Pillai's Trace", "Wilks' Lambda", "Hotelling-Lawley Trace", "Roy's maximum-root"});
        GridData gd_cmboTestStat = new GridData(16384, 4, true, false, 1, 1);
        gd_cmboTestStat.widthHint = 134;
        this.cmboTestStat.setLayoutData((Object)gd_cmboTestStat);
        this.cmboTestStat.select(1);
        this.grpDisplay = new Group(this.composite_1, 0);
        this.grpDisplay.setText("Display");
        this.grpDisplay.setLayout((Layout)new GridLayout(1, false));
        GridData gd_grpDisplay = new GridData(4, 4, true, true, 2, 1);
        gd_grpDisplay.heightHint = 110;
        gd_grpDisplay.widthHint = 200;
        this.grpDisplay.setLayoutData((Object)gd_grpDisplay);
        this.btnStatRespVars = new Button((Composite)this.grpDisplay, 32);
        this.btnStatRespVars.setText("Descriptive Statistics of the Response Variables");
        this.btnShapiroWilk = new Button((Composite)this.grpDisplay, 32);
        this.btnShapiroWilk.setText("Shapiro-Wilk Multivariate Normality Test");
        this.btnBoxM = new Button((Composite)this.grpDisplay, 32);
        this.btnBoxM.setText("Box's M Test for Homogeneity of Covariance Matrices");
        this.btnBartletts = new Button((Composite)this.grpDisplay, 32);
        this.btnBartletts.setText("Bartlett's Sphericity Test");
        this.grpSave = new Group(this.composite_1, 0);
        this.grpSave.setLayout((Layout)new GridLayout(1, false));
        GridData gd_grpSave = new GridData(4, 0x1000000, true, true, 2, 1);
        gd_grpSave.widthHint = 200;
        gd_grpSave.heightHint = 60;
        this.grpSave.setLayoutData((Object)gd_grpSave);
        this.grpSave.setText("Save");
        this.btnSSHypo = new Button((Composite)this.grpSave, 32);
        this.btnSSHypo.setText("Sum of Squares and cross products for the hypothesis");
        this.btnSSError = new Button((Composite)this.grpSave, 32);
        this.btnSSError.setText("Sum of Squares and cross products for error");
        this.initializeContent();
        return container;
    }

    void initializeContent() {
        this.listManager.initializeSingleButtonList(this.lstNumericVariables, this.lstResponseVariables, this.btnAddNumeric);
        this.listManager.initializeSingleSelectionList(this.lstFactors, this.txtFactor, this.btnAddFactor);
        this.listManager.initializeSingleSelectionList(this.lstFactors, this.txtBlk, this.btnSetToFactor, this.btnAddBlk, new manovaValidate(this.cmbDesign, this.btnAddBlk));
        this.listManager.initializeVariableMoveList(this.lstNumericVariables, this.lstFactors, this.btnSetToFactor, this.filePath);
        this.listManager.initializeNumericList(this.lstNumericVariables, this.filePath);
        this.listManager.initializeFactorList(this.lstFactors, this.filePath);
    }

    void resetDialog() {
        this.lstNumericVariables.removeAll();
        this.lstResponseVariables.removeAll();
        this.lstFactors.removeAll();
        this.txtFactor.setText("");
        this.txtBlk.setText("");
        this.listManager.initializeNumericList(this.lstNumericVariables, this.filePath);
        this.listManager.initializeFactorList(this.lstFactors, this.filePath);
        this.btnAddNumeric.setText("Add");
        this.btnAddFactor.setText("Add");
        this.btnAddBlk.setText("Add");
        this.btnAddFactor.setEnabled(false);
        this.btnAddBlk.setEnabled(false);
        this.btnStatRespVars.setSelection(false);
        this.btnSSHypo.setSelection(false);
        this.btnSSError.setSelection(false);
        this.btnBartletts.setSelection(false);
        this.btnBoxM.setSelection(false);
        this.btnShapiroWilk.setSelection(false);
        this.cmbDesign.setText("Randomized Complete Block Design (RCBD)");
        this.cmboTestStat.setText("Wilks' Lambda");
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
        String outputFolder = StarAnalysisUtilities.createOutputFolder(this.filePath, "Manova");
        String dataFileName = this.filePath.replace(File.separator, "/");
        String outputPath = outputFolder;
        String[] yVars = this.lstResponseVariables.getItems();
        String factorVar = this.txtFactor.getText();
        String repVar = this.txtBlk.getText();
        repVar = this.cmbDesign.getText().equals("Completely Randomized Design (CRD)") ? null : this.txtBlk.getText();
        String testStat = this.testStatistics[this.cmboTestStat.getSelectionIndex()];
        String descriptive = "FALSE";
        String correlate = "FALSE";
        String doNormalTest = "FALSE";
        String doBoxM = "FALSE";
        String doSphericity = "FALSE";
        String saveSSH = "FALSE";
        String saveSSE = "FALSE";
        descriptive = String.valueOf(this.btnStatRespVars.getSelection()).toUpperCase();
        doNormalTest = String.valueOf(this.btnShapiroWilk.getSelection()).toUpperCase();
        doBoxM = String.valueOf(this.btnBoxM.getSelection()).toUpperCase();
        doSphericity = String.valueOf(this.btnBartletts.getSelection()).toUpperCase();
        saveSSH = String.valueOf(this.btnSSHypo.getSelection()).toUpperCase();
        saveSSE = String.valueOf(this.btnSSError.getSelection()).toUpperCase();
        if (this.lstResponseVariables.getItemCount() < 2) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"Please add at least two response variables.");
            return;
        }
        if (this.txtFactor.getText().isEmpty()) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"Please add a factor variable.");
            return;
        }
        if (this.txtBlk.getText().isEmpty() && this.cmbDesign.getSelectionIndex() == 1) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"Please add a block variable.");
            return;
        }
        OperationProgressDialog rInfo = new OperationProgressDialog(this.getShell(), "Performing Analysis");
        rInfo.open();
        this.btnOkay.setEnabled(false);
        StarAnalysisUtilities.testVarArgs(dataFileName, outputPath.replace(File.separator, "/"), yVars, factorVar, repVar, testStat, descriptive, correlate, doNormalTest, doBoxM, doSphericity, saveSSH, saveSSE);
        ProjectExplorerView.rJavaManager.getSTARAnalysisManager().doManova(dataFileName, outputPath.replace(File.separator, "/"), yVars, factorVar, repVar, testStat, descriptive, correlate, doNormalTest, doBoxM, doSphericity, saveSSH, saveSSE);
        this.getShell().setMinimized(true);
        StarAnalysisUtilities.openAndRefreshFolder(outputPath);
        this.btnOkay.setEnabled(true);
        rInfo.close();
    }

    protected void createButtonsForButtonBar(Composite parent) {
        this.createButton(parent, 8, "Reset", false);
        this.btnOkay = this.createButton(parent, 0, IDialogConstants.OK_LABEL, true);
        this.createButton(parent, 1, IDialogConstants.CANCEL_LABEL, false);
    }

    protected Point getInitialSize() {
        return new Point(534, 468);
    }

    public class manovaValidate
    extends DialogListValidator {
        Combo cmbDesign;
        Button btnAddBlk;

        public manovaValidate(Combo cmbDesign, Button btnAddBlk) {
            this.btnAddBlk = btnAddBlk;
            this.cmbDesign = cmbDesign;
        }

        @Override
        public boolean validate() {
            this.btnAddBlk.setEnabled(!this.cmbDesign.getText().equals("Completely Randomized Design (CRD)"));
            return !this.cmbDesign.getText().equals("Completely Randomized Design (CRD)");
        }
    }
}

