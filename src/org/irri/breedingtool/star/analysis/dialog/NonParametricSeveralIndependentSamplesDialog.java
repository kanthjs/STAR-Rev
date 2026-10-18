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
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
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

public class NonParametricSeveralIndependentSamplesDialog
extends Dialog {
    private String filePath = PartStackHandler.getActiveElementID();
    private List lstNumericVariables;
    private Button btnToFactor;
    private DialogFormUtility listManager = new DialogFormUtility();
    private List lstTestVariables;
    private Button btnOkay;
    private Label lblFactors;
    private List lstFactors;
    private Button btnAddRemove2;
    private Label lblNewLabel;
    private Button btnAddRemove1;
    private List lstGroupingVariables;

    public NonParametricSeveralIndependentSamplesDialog(Shell parentShell) {
        super(parentShell);
        this.setShellStyle(2256);
    }

    protected void configureShell(Shell shell) {
        super.configureShell(shell);
        shell.setText("Test for Several Independent Samples : " + new File(this.filePath).getName());
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
        GridData gd_lstNumericVariables = new GridData(4, 4, true, true, 1, 1);
        gd_lstNumericVariables.heightHint = 80;
        gd_lstNumericVariables.widthHint = 80;
        this.lstNumericVariables.setLayoutData((Object)gd_lstNumericVariables);
        this.btnAddRemove1 = new Button(composite, 0);
        GridData gd_btnAddRemove1 = new GridData(0x1000000, 0x1000000, false, false, 1, 1);
        gd_btnAddRemove1.heightHint = 26;
        gd_btnAddRemove1.widthHint = 90;
        this.btnAddRemove1.setLayoutData((Object)gd_btnAddRemove1);
        this.btnAddRemove1.setText("Add");
        this.lstTestVariables = new List(composite, 2818);
        GridData gd_lstTestVariables = new GridData(4, 4, true, true, 1, 1);
        gd_lstTestVariables.widthHint = 80;
        gd_lstTestVariables.heightHint = 80;
        this.lstTestVariables.setLayoutData((Object)gd_lstTestVariables);
        this.btnToFactor = new Button(composite, 0);
        GridData gd_btnToFactor = new GridData(131072, 0x1000000, true, true, 1, 1);
        gd_btnToFactor.heightHint = 25;
        gd_btnToFactor.widthHint = 110;
        this.btnToFactor.setLayoutData((Object)gd_btnToFactor);
        this.btnToFactor.setText("Set to Factor");
        new Label(composite, 0);
        new Label(composite, 0);
        this.lblFactors = new Label(composite, 0);
        this.lblFactors.setText("Factor(s)");
        new Label(composite, 0);
        this.lblNewLabel = new Label(composite, 0);
        this.lblNewLabel.setText("Grouping Variable");
        this.lstFactors = new List(composite, 2816);
        GridData gd_lstFactors = new GridData(4, 4, true, true, 1, 1);
        gd_lstFactors.widthHint = 80;
        gd_lstFactors.heightHint = 50;
        this.lstFactors.setLayoutData((Object)gd_lstFactors);
        this.btnAddRemove2 = new Button(composite, 0);
        GridData gd_btnAddRemove2 = new GridData(0x1000000, 128, false, false, 1, 1);
        gd_btnAddRemove2.heightHint = 26;
        gd_btnAddRemove2.widthHint = 90;
        this.btnAddRemove2.setLayoutData((Object)gd_btnAddRemove2);
        this.btnAddRemove2.setText("Add");
        this.lstGroupingVariables = new List(composite, 2048);
        GridData gd_lstGroupingVariables = new GridData(4, 128, true, true, 1, 1);
        gd_lstGroupingVariables.heightHint = 20;
        gd_lstGroupingVariables.widthHint = 80;
        this.lstGroupingVariables.setLayoutData((Object)gd_lstGroupingVariables);
        this.initializeContent();
        return container;
    }

    void initializeContent() {
        this.listManager.initializeSingleButtonList(this.lstNumericVariables, this.lstTestVariables, this.btnToFactor, this.btnAddRemove1);
        this.listManager.initializeSingleButtonList(this.lstFactors, this.lstGroupingVariables, this.btnToFactor, this.btnAddRemove2);
        this.listManager.initializeVariableMoveList(this.lstNumericVariables, this.lstFactors, this.btnToFactor, this.filePath);
        this.listManager.initializeNumericList(this.lstNumericVariables, this.filePath);
        this.listManager.initializeFactorList(this.lstFactors, this.filePath);
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
        this.lstGroupingVariables.removeAll();
        this.listManager.initializeNumericList(this.lstNumericVariables, this.filePath);
        this.listManager.initializeFactorList(this.lstFactors, this.filePath);
        this.btnAddRemove1.setText("Add");
        this.btnAddRemove2.setText("Add");
        this.btnAddRemove1.setEnabled(false);
        this.btnAddRemove2.setEnabled(false);
        this.btnToFactor.setText("Set to Factor");
        this.btnToFactor.setEnabled(false);
    }

    protected void okPressed() {
        if (this.lstTestVariables.getItemCount() >= 1) {
            this.btnOkay.setEnabled(false);
            String[] respvars = this.lstTestVariables.getItems();
            String[] grp = this.lstGroupingVariables.getItems();
            String outputFolder = StarAnalysisUtilities.createOutputFolder(this.filePath, "NonParametricSeveralIndependentSample");
            OperationProgressDialog rInfo = new OperationProgressDialog(this.getShell(), "Performing Analysis");
            rInfo.open();
            String dataFileName = this.filePath.replace(File.separator, "/");
            String cfr_ignored_0 = String.valueOf(outputFolder) + File.separator + "Output.txt";
            StarAnalysisUtilities.testVarArgs(dataFileName, outputFolder.replace(File.separator, "/"), respvars, grp);
            ProjectExplorerView.rJavaManager.getSTARAnalysisManager().doKruskal(dataFileName, outputFolder.replace(File.separator, "/"), respvars, grp);
            this.getShell().setMinimized(true);
            StarAnalysisUtilities.openAndRefreshFolder(outputFolder);
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
        return new Point(516, 393);
    }
}

