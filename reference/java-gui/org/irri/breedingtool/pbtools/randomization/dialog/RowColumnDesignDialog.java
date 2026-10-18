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
package org.irri.breedingtool.pbtools.randomization.dialog;

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

public class RowColumnDesignDialog
extends Dialog {
    private Spinner txtTotalTreatments;
    private Spinner txtTotalReplicates;
    private Spinner txtTotalTrials;
    private Text txtFileName;
    private Button btnOk;
    private Spinner txtFieldRows;
    private Combo cmbOrder;
    private Spinner txtRowsEachRep;

    public RowColumnDesignDialog(Shell parentShell) {
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
        lblFactorialDesign.setText("Row-Column Design");
        Label label = new Label(container, 258);
        label.setLayoutData((Object)new GridData(4, 0x1000000, false, false, 1, 1));
        Composite composite_1 = new Composite(container, 0);
        composite_1.setLayoutData((Object)new GridData(4, 0x1000000, false, true, 1, 1));
        composite_1.setLayout((Layout)new GridLayout(3, false));
        Label lblNumberOfReplicated = new Label(composite_1, 0);
        lblNumberOfReplicated.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        lblNumberOfReplicated.setLayoutData((Object)new GridData(16384, 0x1000000, true, false, 1, 1));
        lblNumberOfReplicated.setText("Number of Treatments");
        Label lblNewLabel = new Label(composite_1, 0);
        GridData gd_lblNewLabel = new GridData(16384, 0x1000000, false, false, 1, 1);
        gd_lblNewLabel.widthHint = 60;
        lblNewLabel.setLayoutData((Object)gd_lblNewLabel);
        this.txtTotalTreatments = new Spinner(composite_1, 2048);
        this.txtTotalTreatments.setMaximum(10000);
        GridData gd_txtTotalTreatments = new GridData(4, 0x1000000, true, false, 1, 1);
        gd_txtTotalTreatments.widthHint = 20;
        this.txtTotalTreatments.setLayoutData((Object)gd_txtTotalTreatments);
        this.txtTotalTreatments.setSelection(9);
        Label lblNumberOfReplicates = new Label(composite_1, 0);
        lblNumberOfReplicates.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        lblNumberOfReplicates.setLayoutData((Object)new GridData(16384, 0x1000000, true, false, 1, 1));
        lblNumberOfReplicates.setText("Number of Replicates");
        new Label(composite_1, 0);
        this.txtTotalReplicates = new Spinner(composite_1, 2048);
        GridData gd_txtTotalReplicates = new GridData(4, 0x1000000, true, false, 1, 1);
        gd_txtTotalReplicates.widthHint = 20;
        this.txtTotalReplicates.setLayoutData((Object)gd_txtTotalReplicates);
        this.txtTotalReplicates.setSelection(2);
        Label lblNumberOfRows = new Label(composite_1, 0);
        lblNumberOfRows.setText("Number of Rows in each Replicate");
        lblNumberOfRows.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        new Label(composite_1, 0);
        this.txtRowsEachRep = new Spinner(composite_1, 2048);
        this.txtRowsEachRep.setLayoutData((Object)new GridData(4, 0x1000000, false, false, 1, 1));
        this.txtRowsEachRep.setMinimum(1);
        this.txtRowsEachRep.setSelection(3);
        Label lblNumberOfField = new Label(composite_1, 0);
        lblNumberOfField.setText("Number of Field Rows");
        lblNumberOfField.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        new Label(composite_1, 0);
        this.txtFieldRows = new Spinner(composite_1, 2048);
        GridData gd_txtFieldRows = new GridData(4, 0x1000000, false, false, 1, 1);
        gd_txtFieldRows.widthHint = 20;
        this.txtFieldRows.setLayoutData((Object)gd_txtFieldRows);
        this.txtFieldRows.setMinimum(1);
        this.txtFieldRows.setSelection(3);
        this.txtFieldRows.setMaximum(500);
        Label lblNumberOfTrials = new Label(composite_1, 0);
        lblNumberOfTrials.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        lblNumberOfTrials.setLayoutData((Object)new GridData(16384, 0x1000000, true, false, 1, 1));
        lblNumberOfTrials.setText("Number of Trials");
        new Label(composite_1, 0);
        this.txtTotalTrials = new Spinner(composite_1, 2048);
        GridData gd_txtTotalTrials = new GridData(4, 0x1000000, true, false, 1, 1);
        gd_txtTotalTrials.widthHint = 20;
        this.txtTotalTrials.setLayoutData((Object)gd_txtTotalTrials);
        this.txtTotalTrials.setMaximum(1000);
        this.txtTotalTrials.setMinimum(1);
        Group group = new Group(container, 0);
        group.setLayoutData((Object)new GridData(4, 0x1000000, true, true, 1, 1));
        group.setText("Field Book ");
        group.setLayout((Layout)new GridLayout(4, false));
        Label label_2 = new Label((Composite)group, 0);
        GridData gd_label_2 = new GridData(16384, 0x1000000, true, false, 1, 1);
        gd_label_2.widthHint = 40;
        label_2.setLayoutData((Object)gd_label_2);
        label_2.setText("Name");
        this.txtFileName = new Text((Composite)group, 2048);
        this.txtFileName.setText("fieldbookRowCol");
        GridData gd_txtFileName = new GridData(4, 0x1000000, true, false, 1, 1);
        gd_txtFileName.widthHint = 100;
        this.txtFileName.setLayoutData((Object)gd_txtFileName);
        Label label_4 = new Label((Composite)group, 0);
        GridData gd_label_4 = new GridData(131072, 0x1000000, true, false, 1, 1);
        gd_label_4.widthHint = 40;
        label_4.setLayoutData((Object)gd_label_4);
        label_4.setText("Order");
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
            this.txtTotalTreatments.setSelection(9);
            this.txtTotalReplicates.setSelection(2);
            this.txtFieldRows.setSelection(3);
            this.txtRowsEachRep.setSelection(3);
            this.txtTotalTrials.setSelection(1);
            this.cmbOrder.setText("Plot Order");
            this.txtFileName.setText("fieldbookRowCol");
        }
        super.buttonPressed(buttonId);
    }

    protected void okPressed() {
        String outputFile;
        if (this.txtTotalTreatments.getSelection() < 9) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"The minimum value of the number of treatments is equal to 9.");
            this.txtTotalTreatments.setSelection(9);
            return;
        }
        if (this.txtTotalReplicates.getSelection() < 2) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"The minimum value of the number of replicates is equal to 2.");
            this.txtTotalReplicates.setSelection(2);
            return;
        }
        if (this.txtFieldRows.getSelection() < 2) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"The minimum value of the number of field rows is equal to 2.");
            this.txtFieldRows.setSelection(2);
            return;
        }
        if (this.txtRowsEachRep.getSelection() >= this.txtTotalTreatments.getSelection()) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"The maximum value of the number of rows in each replicate should be less than the total number of treaments.");
            this.txtRowsEachRep.getSelection();
            return;
        }
        if (this.txtFieldRows.getSelection() > this.txtRowsEachRep.getSelection() * this.txtTotalReplicates.getSelection()) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"The maximum value of the number of field rows should be less than or equal to the product of rows in each replicate and total number of replicates.");
            this.txtFieldRows.setSelection(this.txtRowsEachRep.getSelection() * this.txtTotalReplicates.getSelection());
            return;
        }
        if (this.txtFileName.getText().equals("")) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"Field Filename must not be empty.");
            return;
        }
        if (this.txtTotalTreatments.getSelection() * this.txtTotalReplicates.getSelection() > 1500) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"The maximum total number of experimental units is 1500.");
            return;
        }
        if (this.txtTotalTreatments.getSelection() % this.txtRowsEachRep.getSelection() != 0 || this.txtRowsEachRep.getSelection() < 2) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"The number of rows in each replicate should be a factor of the number of treatments and should be greater than 1.");
            return;
        }
        if (this.txtFieldRows.getSelection() % this.txtRowsEachRep.getSelection() != 0) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"The number of field rows should be divisible by the number of rows in each replicate.");
            return;
        }
        if (this.txtRowsEachRep.getSelection() == 1 || this.txtRowsEachRep.getSelection() == this.txtTotalTreatments.getSelection()) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"The number of rows should not be equal to one or the number of treatments.");
            return;
        }
        if (this.txtTotalReplicates.getSelection() % (this.txtFieldRows.getSelection() / this.txtRowsEachRep.getSelection()) != 0) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"The quotient of the number of field rows and number of rows in each replicate should be a factor of the number of the replicates.");
            return;
        }
        OperationProgressDialog rInfo = new OperationProgressDialog(this.getShell(), "Performing Randomization");
        rInfo.open();
        this.btnOk.setEnabled(false);
        String outputFileTxt = outputFile = StarRandomizationUtilities.createOutputFolder("Row-Column");
        String outputFileCsv = this.txtFileName.getText();
        String fieldOrder = "Plot Order";
        if (this.cmbOrder.getText().equals("Serpentine")) {
            fieldOrder = "Serpentine";
        }
        StarRandomizationUtilities.testVarArgs(outputFileCsv.replace(File.separator, "/"), outputFileTxt.replace(File.separator, "/"), this.txtTotalTreatments.getSelection(), this.txtTotalReplicates.getSelection(), this.txtTotalTrials.getSelection());
        ProjectExplorerView.rJavaManager.getPbToolRandomizationManager().doDesignRowColumn(outputFileTxt.replace(File.separator, "/"), outputFileCsv.replace(File.separator, "/"), Integer.valueOf(this.txtTotalTreatments.getSelection()), Integer.valueOf(this.txtTotalReplicates.getSelection()), Integer.valueOf(this.txtTotalTrials.getSelection()), Integer.valueOf(this.txtRowsEachRep.getSelection()), Integer.valueOf(this.txtFieldRows.getSelection()), fieldOrder);
        rInfo.close();
        this.getShell().setMinimized(true);
        StarRandomizationUtilities.openAndRefreshFileFolder(String.valueOf(outputFile) + outputFileCsv + ".csv");
        this.btnOk.setEnabled(true);
    }

    protected void createButtonsForButtonBar(Composite parent) {
        this.createButton(parent, 8, "Reset", false);
        this.btnOk = this.createButton(parent, 0, IDialogConstants.OK_LABEL, true);
        this.createButton(parent, 1, IDialogConstants.CANCEL_LABEL, false);
    }

    protected Point getInitialSize() {
        return new Point(362, 357);
    }

    boolean isPerfectSquare(long n) {
        if (n < 0L) {
            return false;
        }
        switch ((int)(n & 0xFL)) {
            case 0: 
            case 1: 
            case 4: 
            case 9: {
                long tst = (long)Math.sqrt(n);
                return tst * tst == n;
            }
        }
        return false;
    }
}

