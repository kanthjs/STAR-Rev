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
import org.irri.breedingtool.utility.DialogFormUtility;

public class PairedSampleDialog
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
    private Text txtDataSet1;
    private Label lblDataSet_1;
    private Text txtDataSet2;
    private Label lblNewLabel;
    private Label lblNewLabel_1;
    private Label lblNewLabel_2;
    private Button button;
    private Button button_1;

    public PairedSampleDialog(Shell parentShell) {
        super(parentShell);
        this.setShellStyle(2256);
    }

    protected void configureShell(Shell shell) {
        super.configureShell(shell);
        shell.setText("Paired Sample T-Test");
    }

    protected Control createDialogArea(Composite parent) {
        Composite container = (Composite)super.createDialogArea(parent);
        FillLayout fl_container = new FillLayout(256);
        fl_container.marginHeight = 8;
        fl_container.marginWidth = 8;
        container.setLayout((Layout)fl_container);
        TabFolder tabFolder = new TabFolder(container, 0);
        TabItem tbtmTitleCase = new TabItem(tabFolder, 0);
        tbtmTitleCase.setText("Title Case");
        Composite composite = new Composite((Composite)tabFolder, 0);
        tbtmTitleCase.setControl((Control)composite);
        composite.setLayout((Layout)new GridLayout(5, false));
        new Label(composite, 0);
        new Label(composite, 0);
        new Label(composite, 0);
        new Label(composite, 0);
        new Label(composite, 0);
        Label lblNumericVariables = new Label(composite, 0);
        lblNumericVariables.setLayoutData((Object)new GridData(4, 4, true, false, 1, 1));
        lblNumericVariables.setText("Numeric Variable(s)");
        new Label(composite, 0);
        new Label(composite, 0);
        new Label(composite, 0);
        new Label(composite, 0);
        this.lstNumericVariables = new List(composite, 2818);
        GridData gd_lstNumericVariables = new GridData(4, 4, true, true, 1, 7);
        gd_lstNumericVariables.heightHint = 238;
        gd_lstNumericVariables.widthHint = 173;
        this.lstNumericVariables.setLayoutData((Object)gd_lstNumericVariables);
        TabItem tbtmOption = new TabItem(tabFolder, 0);
        tbtmOption.setText("Options");
        Composite composite_1 = new Composite((Composite)tabFolder, 0);
        tbtmOption.setControl((Control)composite_1);
        composite_1.setLayout((Layout)new GridLayout(2, false));
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        Label lblAlternativeHypothesis = new Label(composite_1, 0);
        lblAlternativeHypothesis.setLayoutData((Object)new GridData(131072, 0x1000000, false, false, 1, 1));
        lblAlternativeHypothesis.setText("Alternative Hypothesis:");
        this.cmbAlternativeHypothesis = new Combo(composite_1, 8);
        this.cmbAlternativeHypothesis.setItems(new String[]{"less", "greater", "two.sided"});
        GridData gd_cmbAlternativeHypothesis = new GridData(4, 0x1000000, true, false, 1, 1);
        gd_cmbAlternativeHypothesis.widthHint = 5;
        this.cmbAlternativeHypothesis.setLayoutData((Object)gd_cmbAlternativeHypothesis);
        this.cmbAlternativeHypothesis.setText("two.sided");
        Group grpDisplay = new Group(composite_1, 0);
        grpDisplay.setText("Display");
        grpDisplay.setLayout((Layout)new GridLayout(3, false));
        grpDisplay.setLayoutData((Object)new GridData(4, 4, true, true, 2, 1));
        this.btnSummaryStatistics = new Button((Composite)grpDisplay, 32);
        GridData gd_btnSummaryStatistics = new GridData(16384, 0x1000000, false, false, 2, 1);
        gd_btnSummaryStatistics.heightHint = 23;
        this.btnSummaryStatistics.setLayoutData((Object)gd_btnSummaryStatistics);
        this.btnSummaryStatistics.setText("Summary Statistics");
        new Label((Composite)grpDisplay, 0);
        this.btnConfidenceInterval = new Button((Composite)grpDisplay, 32);
        this.btnConfidenceInterval.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                PairedSampleDialog.this.txtConfidenceInterval.setEnabled(PairedSampleDialog.this.btnConfidenceInterval.getSelection());
            }
        });
        this.btnConfidenceInterval.setLayoutData((Object)new GridData(4, 0x1000000, true, false, 1, 1));
        this.btnConfidenceInterval.setText("Confidence Interval");
        this.txtConfidenceInterval = new Spinner((Composite)grpDisplay, 2048);
        this.txtConfidenceInterval.setDigits(2);
        this.txtConfidenceInterval.setLayoutData((Object)new GridData(4, 0x1000000, true, false, 1, 1));
        this.txtConfidenceInterval.setMaximum(9900);
        this.txtConfidenceInterval.setMinimum(9000);
        this.txtConfidenceInterval.setSelection(9500);
        this.txtConfidenceInterval.setEnabled(false);
        Label label = new Label((Composite)grpDisplay, 0);
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
        this.listManager.initializeNumericList(this.lstNumericVariables, this.filePath);
        new Label(composite, 0);
        this.lblDataSet = new Label(composite, 0);
        this.lblDataSet.setLayoutData((Object)new GridData(16384, 0x1000000, false, false, 2, 1));
        this.lblDataSet.setText("Data Set 1:");
        new Label(composite, 0);
        this.button = new Button(composite, 0);
        GridData gd_button = new GridData(16384, 0x1000000, false, false, 1, 1);
        gd_button.widthHint = 90;
        this.button.setLayoutData((Object)gd_button);
        this.button.setText("Add");
        this.txtDataSet1 = new Text(composite, 2048);
        this.txtDataSet1.setBackground(SWTResourceManager.getColor(1));
        this.txtDataSet1.setEditable(false);
        GridData gd_txtDataSet1 = new GridData(4, 4, true, true, 3, 1);
        gd_txtDataSet1.widthHint = 158;
        gd_txtDataSet1.heightHint = 14;
        this.txtDataSet1.setLayoutData((Object)gd_txtDataSet1);
        new Label(composite, 0);
        this.lblNewLabel_1 = new Label(composite, 0);
        this.lblNewLabel_1.setLayoutData((Object)new GridData(16384, 0x1000000, true, true, 1, 1));
        new Label(composite, 0);
        this.lblNewLabel_2 = new Label(composite, 0);
        new Label(composite, 0);
        this.lblDataSet_1 = new Label(composite, 0);
        this.lblDataSet_1.setLayoutData((Object)new GridData(16384, 0x1000000, false, false, 2, 1));
        this.lblDataSet_1.setText("Data Set 2:");
        new Label(composite, 0);
        this.button_1 = new Button(composite, 0);
        GridData gd_button_1 = new GridData(16384, 0x1000000, false, false, 1, 1);
        gd_button_1.widthHint = 90;
        this.button_1.setLayoutData((Object)gd_button_1);
        this.button_1.setText("Add");
        this.txtDataSet2 = new Text(composite, 2048);
        this.txtDataSet2.setEditable(false);
        this.txtDataSet2.setBackground(SWTResourceManager.getColor(1));
        this.txtDataSet2.setLayoutData((Object)new GridData(4, 4, true, true, 3, 1));
        new Label(composite, 0);
        new Label(composite, 0);
        new Label(composite, 0);
        new Label(composite, 0);
        new Label(composite, 0);
        new Label(composite, 0);
        this.lblNewLabel = new Label(composite, 0);
        GridData gd_lblNewLabel = new GridData(16384, 0x1000000, true, true, 1, 1);
        gd_lblNewLabel.heightHint = 90;
        this.lblNewLabel.setLayoutData((Object)gd_lblNewLabel);
        new Label(composite, 0);
        this.listManager.initializeNumericList(this.lstNumericVariables, this.filePath);
        return container;
    }

    protected void okPressed() {
        this.btnOkay.setEnabled(false);
        this.btnOkay.setEnabled(true);
    }

    protected void createButtonsForButtonBar(Composite parent) {
        this.btnOkay = this.createButton(parent, 0, IDialogConstants.OK_LABEL, true);
        this.createButton(parent, 1, IDialogConstants.CANCEL_LABEL, false);
    }

    protected Point getInitialSize() {
        return new Point(533, 493);
    }
}

