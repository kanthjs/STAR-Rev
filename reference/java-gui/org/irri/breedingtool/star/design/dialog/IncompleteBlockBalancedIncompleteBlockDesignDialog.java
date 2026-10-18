/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.jface.dialogs.Dialog
 *  org.eclipse.jface.dialogs.IDialogConstants
 *  org.eclipse.jface.dialogs.MessageDialog
 *  org.eclipse.swt.graphics.Point
 *  org.eclipse.swt.layout.GridData
 *  org.eclipse.swt.layout.GridLayout
 *  org.eclipse.swt.widgets.Button
 *  org.eclipse.swt.widgets.Combo
 *  org.eclipse.swt.widgets.Composite
 *  org.eclipse.swt.widgets.Control
 *  org.eclipse.swt.widgets.Group
 *  org.eclipse.swt.widgets.Label
 *  org.eclipse.swt.widgets.Layout
 *  org.eclipse.swt.widgets.Shell
 *  org.eclipse.swt.widgets.Spinner
 *  org.eclipse.swt.widgets.Text
 */
package org.irri.breedingtool.star.design.dialog;

import java.io.File;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;
import org.irri.breedingtool.datamanipulation.dialog.OperationProgressDialog;
import org.irri.breedingtool.projectexplorer.view.ProjectExplorerView;
import org.irri.breedingtool.utility.StarRandomizationUtilities;

public class IncompleteBlockBalancedIncompleteBlockDesignDialog
extends Dialog {
    private Spinner txtTotalTreatments;
    private Spinner txtTotalBlockSize;
    private Spinner txtTotalTrials;
    private Text txtFileName;
    private Button btnOk;
    private Spinner txtFieldRows;
    private Spinner txtRowsPerBlk;
    private Combo cmbOrder;

    public IncompleteBlockBalancedIncompleteBlockDesignDialog(Shell parentShell) {
        super(parentShell);
        this.setShellStyle(2288);
    }

    protected void configureShell(Shell shell) {
        super.configureShell(shell);
        shell.setText("Randomization and Layout");
    }

    protected Control createDialogArea(Composite parent) {
        Composite container = (Composite)super.createDialogArea(parent);
        Label lblFactorialDesign = new Label(container, 0);
        lblFactorialDesign.setLayoutData((Object)new GridData(16384, 0x1000000, true, false, 1, 1));
        lblFactorialDesign.setFont(SWTResourceManager.getFont("Tahoma", 10, 1));
        lblFactorialDesign.setText("Balanced Incomplete Block Design");
        Label label = new Label(container, 258);
        label.setLayoutData((Object)new GridData(4, 0x1000000, true, false, 1, 1));
        Composite composite_1 = new Composite(container, 0);
        composite_1.setLayoutData((Object)new GridData(4, 0x1000000, false, true, 1, 1));
        composite_1.setLayout((Layout)new GridLayout(3, false));
        Label lblNumberOfReplicated = new Label(composite_1, 0);
        lblNumberOfReplicated.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        lblNumberOfReplicated.setLayoutData((Object)new GridData(16384, 0x1000000, true, false, 1, 1));
        lblNumberOfReplicated.setText("Number of Treatments");
        Label lblNewLabel = new Label(composite_1, 0);
        GridData gd_lblNewLabel = new GridData(16384, 0x1000000, false, false, 1, 1);
        gd_lblNewLabel.widthHint = 80;
        lblNewLabel.setLayoutData((Object)gd_lblNewLabel);
        this.txtTotalTreatments = new Spinner(composite_1, 2048);
        GridData gd_txtTotalTreatments = new GridData(4, 0x1000000, true, false, 1, 1);
        gd_txtTotalTreatments.widthHint = 20;
        this.txtTotalTreatments.setLayoutData((Object)gd_txtTotalTreatments);
        this.txtTotalTreatments.setMaximum(100000);
        this.txtTotalTreatments.setSelection(3);
        Label lblNumberOfReplicates = new Label(composite_1, 0);
        lblNumberOfReplicates.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        lblNumberOfReplicates.setLayoutData((Object)new GridData(16384, 0x1000000, true, false, 1, 1));
        lblNumberOfReplicates.setText("Plots per Block (block size)");
        new Label(composite_1, 0);
        this.txtTotalBlockSize = new Spinner(composite_1, 2048);
        GridData gd_txtTotalBlockSize = new GridData(4, 0x1000000, true, false, 1, 1);
        gd_txtTotalBlockSize.widthHint = 20;
        this.txtTotalBlockSize.setLayoutData((Object)gd_txtTotalBlockSize);
        this.txtTotalBlockSize.setMaximum(100000);
        this.txtTotalBlockSize.setSelection(2);
        Label lblNumberOfRows = new Label(composite_1, 0);
        lblNumberOfRows.setText("Number of Rows Per Block");
        lblNumberOfRows.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        new Label(composite_1, 0);
        this.txtRowsPerBlk = new Spinner(composite_1, 2048);
        this.txtRowsPerBlk.setLayoutData((Object)new GridData(4, 0x1000000, false, false, 1, 1));
        this.txtRowsPerBlk.setMaximum(100000);
        this.txtRowsPerBlk.setMinimum(1);
        Label lblNumberOfField = new Label(composite_1, 0);
        lblNumberOfField.setText("Number of Field Rows");
        lblNumberOfField.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        new Label(composite_1, 0);
        this.txtFieldRows = new Spinner(composite_1, 2048);
        GridData gd_txtFieldRows = new GridData(4, 0x1000000, false, false, 1, 1);
        gd_txtFieldRows.widthHint = 20;
        this.txtFieldRows.setLayoutData((Object)gd_txtFieldRows);
        this.txtFieldRows.setMaximum(100000);
        this.txtFieldRows.setMinimum(1);
        Label lblNumberOfTrials = new Label(composite_1, 0);
        lblNumberOfTrials.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        lblNumberOfTrials.setLayoutData((Object)new GridData(16384, 0x1000000, true, false, 1, 1));
        lblNumberOfTrials.setText("Number of Trials");
        new Label(composite_1, 0);
        this.txtTotalTrials = new Spinner(composite_1, 2048);
        GridData gd_txtTotalTrials = new GridData(4, 0x1000000, true, false, 1, 1);
        gd_txtTotalTrials.widthHint = 20;
        this.txtTotalTrials.setLayoutData((Object)gd_txtTotalTrials);
        this.txtTotalTrials.setMaximum(100000);
        this.txtTotalTrials.setMinimum(1);
        Group group = new Group(container, 0);
        group.setLayoutData((Object)new GridData(4, 0x1000000, false, true, 1, 1));
        group.setText("Field Book ");
        group.setLayout((Layout)new GridLayout(5, false));
        Label label_1 = new Label((Composite)group, 0);
        GridData gd_label_1 = new GridData(4, 0x1000000, true, false, 1, 1);
        gd_label_1.widthHint = 30;
        label_1.setLayoutData((Object)gd_label_1);
        label_1.setText("Name");
        this.txtFileName = new Text((Composite)group, 2048);
        this.txtFileName.setText("fieldbookBIBD");
        GridData gd_txtFileName = new GridData(4, 0x1000000, true, false, 1, 1);
        gd_txtFileName.widthHint = 100;
        this.txtFileName.setLayoutData((Object)gd_txtFileName);
        Label label_2 = new Label((Composite)group, 0);
        GridData gd_label_2 = new GridData(16384, 0x1000000, false, false, 1, 1);
        gd_label_2.widthHint = 10;
        label_2.setLayoutData((Object)gd_label_2);
        Label label_3 = new Label((Composite)group, 0);
        GridData gd_label_3 = new GridData(4, 0x1000000, true, false, 1, 1);
        gd_label_3.widthHint = 30;
        label_3.setLayoutData((Object)gd_label_3);
        label_3.setText("Order");
        this.cmbOrder = new Combo((Composite)group, 8);
        this.cmbOrder.setItems(new String[]{"Plot Order", "Serpentine"});
        GridData gd_cmbOrder = new GridData(4, 0x1000000, true, false, 1, 1);
        gd_cmbOrder.widthHint = 70;
        this.cmbOrder.setLayoutData((Object)gd_cmbOrder);
        this.cmbOrder.select(0);
        return container;
    }

    protected void buttonPressed(int buttonId) {
        if (buttonId == 8) {
            this.txtTotalTreatments.setSelection(3);
            this.txtTotalBlockSize.setSelection(2);
            this.txtTotalTrials.setSelection(1);
            this.txtFieldRows.setSelection(1);
            this.txtRowsPerBlk.setSelection(1);
            this.txtFileName.setText("fieldbookBIBD");
        }
        super.buttonPressed(buttonId);
    }

    protected void okPressed() {
        String outputFile;
        if (this.txtTotalTreatments.getSelection() < 3) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"The minimum value of the number of treatments is equal to 3.");
            this.txtTotalTreatments.setSelection(3);
            return;
        }
        if (this.txtTotalBlockSize.getSelection() < 2) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"The minimum value of the number of plots per block is equal to 2.");
            this.txtTotalBlockSize.setSelection(2);
            return;
        }
        if (this.txtFileName.getText().equals("")) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"Field Filename must not be empty.");
            return;
        }
        if (this.txtTotalTreatments.getSelection() <= this.txtTotalBlockSize.getSelection()) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"Number of treatments must be greater than Total Block Size");
            return;
        }
        int blks = this.fact(this.txtTotalTreatments.getSelection()) / (this.txtTotalBlockSize.getSelection() * this.fact(this.txtTotalTreatments.getSelection() - this.txtTotalBlockSize.getSelection()));
        if (this.txtTotalBlockSize.getSelection() * blks % this.txtFieldRows.getSelection() != 0) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"Number of field rows must divide the total number of plots.");
            return;
        }
        if (this.txtTotalBlockSize.getSelection() % this.txtRowsPerBlk.getSelection() != 0) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"Number of plots per block should be divisible by the number of rows per block.");
            return;
        }
        if (this.txtFieldRows.getSelection() < this.txtRowsPerBlk.getSelection()) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"Number of field rows must be greater than the number of rows per block.");
            return;
        }
        if (this.txtFieldRows.getSelection() % this.txtRowsPerBlk.getSelection() != 0) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"Number of field rows should be divisible by the number of rows per block.");
            return;
        }
        OperationProgressDialog rInfo = new OperationProgressDialog(this.getShell(), "Performing Randomization");
        rInfo.open();
        this.btnOk.setEnabled(false);
        String outputFileTxt = outputFile = StarRandomizationUtilities.createOutputFolder("BalancedIBD");
        String outputFileCsv = this.txtFileName.getText();
        String fieldOrder = "Plot Order";
        if (this.cmbOrder.getText().equals("Serpentine")) {
            fieldOrder = "Serpentine";
        }
        ProjectExplorerView.rJavaManager.getSTARDesignManager().doDesignBIBD(outputFileTxt.replace(File.separator, "/"), outputFileCsv.replace(File.separator, "/"), Integer.valueOf(this.txtTotalTreatments.getSelection()), Integer.valueOf(this.txtTotalBlockSize.getSelection()), Integer.valueOf(this.txtTotalTrials.getSelection()), Integer.valueOf(this.txtFieldRows.getSelection()), Integer.valueOf(this.txtRowsPerBlk.getSelection()), fieldOrder);
        StarRandomizationUtilities.testVarArgs(outputFileTxt.replace(File.separator, "/"), outputFileCsv.replace(File.separator, "/"), this.txtTotalTreatments.getSelection(), this.txtTotalBlockSize.getSelection(), this.txtTotalTrials.getSelection(), this.txtFieldRows.getSelection(), this.txtRowsPerBlk.getSelection(), fieldOrder);
        rInfo.close();
        this.getShell().setMinimized(true);
        StarRandomizationUtilities.openAndRefreshFileFolder(String.valueOf(outputFile) + outputFileCsv + ".csv");
        this.btnOk.setEnabled(true);
    }

    int fact(int n) {
        if (n == 1) {
            return 1;
        }
        int result = this.fact(n - 1) * n;
        return result;
    }

    protected void createButtonsForButtonBar(Composite parent) {
        this.createButton(parent, 8, "Reset", false);
        this.btnOk = this.createButton(parent, 0, IDialogConstants.OK_LABEL, true);
        this.createButton(parent, 1, IDialogConstants.CANCEL_LABEL, false);
    }

    protected Point getInitialSize() {
        return new Point(368, 330);
    }
}

