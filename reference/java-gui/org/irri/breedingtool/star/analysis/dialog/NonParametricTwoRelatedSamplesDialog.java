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
 *  org.eclipse.swt.widgets.Label
 *  org.eclipse.swt.widgets.Layout
 *  org.eclipse.swt.widgets.List
 *  org.eclipse.swt.widgets.Shell
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
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.irri.breedingtool.application.handler.PartStackHandler;
import org.irri.breedingtool.datamanipulation.dialog.OperationProgressDialog;
import org.irri.breedingtool.projectexplorer.view.ProjectExplorerView;
import org.irri.breedingtool.utility.DialogFormUtility;
import org.irri.breedingtool.utility.StarAnalysisUtilities;

public class NonParametricTwoRelatedSamplesDialog
extends Dialog {
    private String filePath = PartStackHandler.getActiveElementID();
    private List lstNumericVariables;
    private DialogFormUtility listManager = new DialogFormUtility();
    private Button btnOkay;
    private Label lblDataSet;
    private Button btnDataSet2;
    private Button btnDataSet1;
    private List lstDataSet1;
    private List lstDataSet2;
    private Label label_2;
    private Combo cmbAlternativeHypothesis;
    private TabItem tbtmVariableDescription;

    public NonParametricTwoRelatedSamplesDialog(Shell parentShell) {
        super(parentShell);
        this.setShellStyle(2256);
    }

    protected void configureShell(Shell shell) {
        super.configureShell(shell);
        shell.setText("Two Related Samples : " + new File(this.filePath).getName());
    }

    protected Control createDialogArea(Composite parent) {
        Composite container = (Composite)super.createDialogArea(parent);
        FillLayout fl_container = new FillLayout(256);
        fl_container.marginHeight = 8;
        fl_container.marginWidth = 8;
        container.setLayout((Layout)fl_container);
        TabFolder tabFolder = new TabFolder(container, 0);
        this.tbtmVariableDescription = new TabItem(tabFolder, 0);
        this.tbtmVariableDescription.setText("Variable Description");
        Composite composite = new Composite((Composite)tabFolder, 0);
        this.tbtmVariableDescription.setControl((Control)composite);
        composite.setLayout((Layout)new GridLayout(3, false));
        Label lblNumericVariables = new Label(composite, 0);
        lblNumericVariables.setLayoutData((Object)new GridData(4, 4, true, false, 1, 1));
        lblNumericVariables.setText("Numeric Variable(s)");
        new Label(composite, 0);
        Label lblTestVariables = new Label(composite, 0);
        lblTestVariables.setLayoutData((Object)new GridData(4, 4, true, false, 1, 1));
        lblTestVariables.setText("Data Set 1");
        this.lstNumericVariables = new List(composite, 2818);
        GridData gd_lstNumericVariables = new GridData(4, 4, true, true, 1, 3);
        gd_lstNumericVariables.heightHint = 130;
        gd_lstNumericVariables.widthHint = 80;
        this.lstNumericVariables.setLayoutData((Object)gd_lstNumericVariables);
        this.btnDataSet1 = new Button(composite, 0);
        GridData gd_btnDataSet1 = new GridData(4, 0x1000000, false, false, 1, 1);
        gd_btnDataSet1.widthHint = 90;
        this.btnDataSet1.setLayoutData((Object)gd_btnDataSet1);
        this.btnDataSet1.setText("Add");
        this.lstDataSet1 = new List(composite, 2818);
        GridData gd_lstDataSet1 = new GridData(4, 4, true, true, 1, 1);
        gd_lstDataSet1.heightHint = 60;
        gd_lstDataSet1.widthHint = 80;
        this.lstDataSet1.setLayoutData((Object)gd_lstDataSet1);
        new Label(composite, 0);
        this.lblDataSet = new Label(composite, 0);
        this.lblDataSet.setText("Data Set 2");
        this.btnDataSet2 = new Button(composite, 0);
        this.btnDataSet2.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
            }
        });
        GridData gd_btnDataSet2 = new GridData(4, 0x1000000, false, false, 1, 1);
        gd_btnDataSet2.widthHint = 90;
        this.btnDataSet2.setLayoutData((Object)gd_btnDataSet2);
        this.btnDataSet2.setText("Add");
        this.lstDataSet2 = new List(composite, 2818);
        GridData gd_lstDataSet2 = new GridData(4, 4, true, true, 1, 1);
        gd_lstDataSet2.heightHint = 60;
        gd_lstDataSet2.widthHint = 80;
        this.lstDataSet2.setLayoutData((Object)gd_lstDataSet2);
        this.label_2 = new Label(composite, 0);
        this.label_2.setLayoutData((Object)new GridData(131072, 0x1000000, false, false, 1, 1));
        this.label_2.setText("Alternative Hypothesis:");
        this.cmbAlternativeHypothesis = new Combo(composite, 8);
        this.cmbAlternativeHypothesis.setItems(new String[]{"less", "greater", "two.sided"});
        this.cmbAlternativeHypothesis.setLayoutData((Object)new GridData(4, 0x1000000, false, false, 2, 1));
        this.cmbAlternativeHypothesis.setText("two.sided");
        this.initializeContent();
        return container;
    }

    public void initializeContent() {
        this.listManager.initializeSingleButtonList(this.lstNumericVariables, this.lstDataSet1, this.btnDataSet1);
        this.listManager.initializeSingleButtonList(this.lstNumericVariables, this.lstDataSet2, this.btnDataSet2);
        this.listManager.initializeNumericList(this.lstNumericVariables, this.filePath);
    }

    void resetDialog() {
        this.lstNumericVariables.removeAll();
        this.lstDataSet1.removeAll();
        this.lstDataSet2.removeAll();
        this.listManager.initializeNumericList(this.lstNumericVariables, this.filePath);
        this.cmbAlternativeHypothesis.select(2);
        this.btnDataSet1.setText("Add");
        this.btnDataSet2.setText("Add");
        this.btnDataSet1.setEnabled(false);
        this.btnDataSet2.setEnabled(false);
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
        if (this.lstDataSet1.getItemCount() != this.lstDataSet2.getItemCount()) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"Data Set 1 and 2 should have equal variables");
            return;
        }
        if (this.lstDataSet1.getItemCount() > 0 && this.lstDataSet2.getItemCount() > 0) {
            this.btnOkay.setEnabled(false);
            String altHypo = this.cmbAlternativeHypothesis.getText();
            String outputFolder = StarAnalysisUtilities.createOutputFolder(this.filePath, "NonParamtericTwoRelatedSamples");
            OperationProgressDialog rInfo = new OperationProgressDialog(this.getShell(), "Performing Analysis");
            rInfo.open();
            String dataFileName = this.filePath.replace(File.separator, "/");
            String outFileName = String.valueOf(outputFolder) + "//Output.txt";
            StarAnalysisUtilities.testVarArgs(dataFileName, outputFolder.replace(File.separator, "/"), this.lstDataSet1.getItems(), this.lstDataSet2.getItems(), altHypo, false, 95);
            ProjectExplorerView.rJavaManager.getSTARAnalysisManager().doPairedMedian(dataFileName, outputFolder.replace(File.separator, "/"), this.lstDataSet1.getItems(), this.lstDataSet2.getItems(), altHypo, false, 95.0f);
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
        return new Point(485, 436);
    }
}

