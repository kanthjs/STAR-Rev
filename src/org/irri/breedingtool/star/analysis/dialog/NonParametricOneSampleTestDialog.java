/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.jface.dialogs.Dialog
 *  org.eclipse.jface.dialogs.IDialogConstants
 *  org.eclipse.swt.graphics.Point
 *  org.eclipse.swt.layout.FillLayout
 *  org.eclipse.swt.layout.GridData
 *  org.eclipse.swt.layout.GridLayout
 *  org.eclipse.swt.widgets.Button
 *  org.eclipse.swt.widgets.Combo
 *  org.eclipse.swt.widgets.Composite
 *  org.eclipse.swt.widgets.Control
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
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
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

public class NonParametricOneSampleTestDialog
extends Dialog {
    private String filePath = PartStackHandler.getActiveElementID();
    private List lstNumericVariables;
    private Button btnAdd;
    private Spinner txtTestValue;
    private DialogFormUtility listManager = new DialogFormUtility();
    private List lstTestVariables;
    private Button btnOkay;
    private Label label_2;
    private Combo cmbAlternativeHypothesis;

    public NonParametricOneSampleTestDialog(Shell parentShell) {
        super(parentShell);
        this.setShellStyle(2256);
    }

    protected void configureShell(Shell shell) {
        super.configureShell(shell);
        shell.setText("One Sample Non-Parametric : " + new File(this.filePath).getName());
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
        lblNumericVariables.setText("Numeric Variable(s)");
        new Label(composite, 0);
        Label lblTestVariables = new Label(composite, 0);
        lblTestVariables.setLayoutData((Object)new GridData(4, 4, true, false, 1, 1));
        lblTestVariables.setText("Test Variable(s)");
        this.lstNumericVariables = new List(composite, 2818);
        GridData gd_lstNumericVariables = new GridData(4, 4, true, true, 1, 4);
        gd_lstNumericVariables.heightHint = 200;
        gd_lstNumericVariables.widthHint = 80;
        this.lstNumericVariables.setLayoutData((Object)gd_lstNumericVariables);
        this.btnAdd = new Button(composite, 0);
        GridData gd_btnAdd = new GridData(0x1000000, 0x1000000, false, true, 1, 1);
        gd_btnAdd.widthHint = 90;
        this.btnAdd.setLayoutData((Object)gd_btnAdd);
        this.btnAdd.setText("Add");
        this.lstTestVariables = new List(composite, 2818);
        GridData gd_lstTestVariables = new GridData(4, 4, true, true, 1, 1);
        gd_lstTestVariables.widthHint = 80;
        gd_lstTestVariables.heightHint = 170;
        this.lstTestVariables.setLayoutData((Object)gd_lstTestVariables);
        new Label(composite, 0);
        new Label(composite, 0);
        Label lblTestValue = new Label(composite, 0);
        lblTestValue.setLayoutData((Object)new GridData(131072, 0x1000000, false, false, 1, 1));
        lblTestValue.setText("Test Value:");
        this.txtTestValue = new Spinner(composite, 2048);
        this.txtTestValue.setLayoutData((Object)new GridData(131072, 0x1000000, false, false, 1, 1));
        this.txtTestValue.setMaximum(100000);
        this.label_2 = new Label(composite, 0);
        this.label_2.setLayoutData((Object)new GridData(131072, 0x1000000, false, false, 1, 1));
        this.label_2.setText("Alternative Hypothesis:");
        this.cmbAlternativeHypothesis = new Combo(composite, 8);
        this.cmbAlternativeHypothesis.setItems(new String[]{"less", "greater", "two.sided"});
        this.cmbAlternativeHypothesis.setLayoutData((Object)new GridData(4, 0x1000000, true, false, 1, 1));
        this.cmbAlternativeHypothesis.setText("two.sided");
        this.initializeContent();
        return container;
    }

    void initializeContent() {
        this.listManager.initializeNumericList(this.lstNumericVariables, this.filePath);
        this.listManager.initializeSingleButtonList(this.lstNumericVariables, this.lstTestVariables, this.btnAdd);
    }

    void resetDialog() {
        this.lstNumericVariables.removeAll();
        this.lstTestVariables.removeAll();
        this.txtTestValue.setSelection(0);
        this.cmbAlternativeHypothesis.select(2);
        this.listManager.initializeNumericList(this.lstNumericVariables, this.filePath);
        this.btnAdd.setText("Add");
        this.btnAdd.setEnabled(false);
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
        if (this.lstTestVariables.getItemCount() >= 1) {
            this.btnOkay.setEnabled(false);
            String[] respvars = this.lstTestVariables.getItems();
            Integer testValue = this.txtTestValue.getSelection() / 100;
            String altHypo = this.cmbAlternativeHypothesis.getText();
            String outputFolder = StarAnalysisUtilities.createOutputFolder(this.filePath, "NonParametricOneSample");
            boolean confInt = true;
            OperationProgressDialog rInfo = new OperationProgressDialog(this.getShell(), "Performing Analysis");
            rInfo.open();
            String dataFileName = this.filePath.replace(File.separator, "/");
            String outFileName = String.valueOf(outputFolder) + "//Output.txt";
            StarAnalysisUtilities.testVarArgs(dataFileName, outputFolder.replace(File.separator, "/"), respvars, testValue, altHypo, confInt, 0.95);
            ProjectExplorerView.rJavaManager.getSTARAnalysisManager().doOneMedian(dataFileName, outputFolder.replace(File.separator, "/"), respvars, testValue, altHypo, confInt, 0.95f);
            this.getShell().setMinimized(true);
            StarAnalysisUtilities.openAndRefreshFolder(outFileName);
            this.btnOkay.setEnabled(true);
            rInfo.close();
        }
    }

    protected void createButtonsForButtonBar(Composite parent) {
        this.createButton(parent, 8, "Reset", false);
        this.btnOkay = this.createButton(parent, 0, IDialogConstants.OK_LABEL, true);
        this.createButton(parent, 1, IDialogConstants.CANCEL_LABEL, false);
    }

    protected Point getInitialSize() {
        return new Point(525, 433);
    }
}

