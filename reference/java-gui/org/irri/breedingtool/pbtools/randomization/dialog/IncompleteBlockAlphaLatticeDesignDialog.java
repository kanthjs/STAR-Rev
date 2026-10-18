/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.jface.dialogs.Dialog
 *  org.eclipse.jface.dialogs.IDialogConstants
 *  org.eclipse.jface.dialogs.MessageDialog
 *  org.eclipse.swt.events.ModifyEvent
 *  org.eclipse.swt.events.ModifyListener
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
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
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

public class IncompleteBlockAlphaLatticeDesignDialog
extends Dialog {
    private Spinner txtTotalTreatments;
    private Spinner txtTotalReplicates;
    private Spinner txtTotalBlockSize;
    private Spinner txtTotalTrials;
    private Text txtFileName;
    private Button btnOk;
    private Combo cmbOrder;
    private Spinner txtFieldRows;
    private Text txtBlksPerRep;
    private Spinner txtRowsEachRep;
    private Spinner txtRowsEachBlk;

    public IncompleteBlockAlphaLatticeDesignDialog(Shell parentShell) {
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
        lblFactorialDesign.setText("Alpha Lattice Design");
        Label label_1 = new Label(container, 258);
        label_1.setLayoutData((Object)new GridData(4, 0x1000000, true, false, 1, 1));
        Composite composite_1 = new Composite(container, 0);
        composite_1.setLayoutData((Object)new GridData(4, 0x1000000, false, true, 1, 1));
        composite_1.setLayout((Layout)new GridLayout(3, false));
        Label lblNumberOfReplicated = new Label(composite_1, 0);
        lblNumberOfReplicated.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        lblNumberOfReplicated.setLayoutData((Object)new GridData(16384, 0x1000000, true, true, 1, 1));
        lblNumberOfReplicated.setText("Number of Treatments");
        Label lblNewLabel = new Label(composite_1, 0);
        GridData gd_lblNewLabel = new GridData(16384, 0x1000000, false, false, 1, 1);
        gd_lblNewLabel.widthHint = 60;
        lblNewLabel.setLayoutData((Object)gd_lblNewLabel);
        this.txtTotalTreatments = new Spinner(composite_1, 2048);
        GridData gd_txtTotalTreatments = new GridData(4, 0x1000000, true, true, 1, 1);
        gd_txtTotalTreatments.widthHint = 20;
        this.txtTotalTreatments.setLayoutData((Object)gd_txtTotalTreatments);
        this.txtTotalTreatments.setMaximum(100000);
        this.txtTotalTreatments.setSelection(9);
        Label lblNumberOfReplicates = new Label(composite_1, 0);
        lblNumberOfReplicates.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        lblNumberOfReplicates.setLayoutData((Object)new GridData(16384, 0x1000000, true, true, 1, 1));
        lblNumberOfReplicates.setText("Number of Replicates");
        new Label(composite_1, 0);
        this.txtTotalReplicates = new Spinner(composite_1, 2048);
        GridData gd_txtTotalReplicates = new GridData(4, 0x1000000, true, true, 1, 1);
        gd_txtTotalReplicates.widthHint = 20;
        this.txtTotalReplicates.setLayoutData((Object)gd_txtTotalReplicates);
        this.txtTotalReplicates.setMaximum(10000);
        this.txtTotalReplicates.setSelection(2);
        Label lblNumberOfPlots = new Label(composite_1, 0);
        lblNumberOfPlots.setLayoutData((Object)new GridData(16384, 0x1000000, true, true, 1, 1));
        lblNumberOfPlots.setText("Number of Plots per Block ");
        lblNumberOfPlots.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        new Label(composite_1, 0);
        this.txtTotalBlockSize = new Spinner(composite_1, 2048);
        GridData gd_txtTotalBlockSize = new GridData(4, 0x1000000, true, true, 1, 1);
        gd_txtTotalBlockSize.widthHint = 20;
        this.txtTotalBlockSize.setLayoutData((Object)gd_txtTotalBlockSize);
        this.txtTotalBlockSize.setSelection(3);
        Label lblNumberOfBlocks = new Label(composite_1, 0);
        lblNumberOfBlocks.setText("Number of Blocks per Replicate");
        lblNumberOfBlocks.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        new Label(composite_1, 0);
        this.txtBlksPerRep = new Text(composite_1, 2048);
        this.txtBlksPerRep.setText("3");
        this.txtBlksPerRep.setBackground(SWTResourceManager.getColor(1));
        this.txtBlksPerRep.setEditable(false);
        this.txtBlksPerRep.setLayoutData((Object)new GridData(4, 0x1000000, true, false, 1, 1));
        Label lblNumberOfRows = new Label(composite_1, 0);
        lblNumberOfRows.setText("Number of Rows in Each Block");
        lblNumberOfRows.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        new Label(composite_1, 0);
        this.txtRowsEachBlk = new Spinner(composite_1, 2048);
        this.txtRowsEachBlk.setLayoutData((Object)new GridData(4, 0x1000000, false, false, 1, 1));
        this.txtRowsEachBlk.setMinimum(1);
        this.txtRowsEachBlk.setSelection(3);
        Label lblNumberOfRows_1 = new Label(composite_1, 0);
        lblNumberOfRows_1.setText("Number of Rows in Each Replicate");
        lblNumberOfRows_1.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
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
        Label lblNumberOfTrials = new Label(composite_1, 0);
        lblNumberOfTrials.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        lblNumberOfTrials.setLayoutData((Object)new GridData(16384, 0x1000000, true, true, 1, 1));
        lblNumberOfTrials.setText("Number of Trials");
        new Label(composite_1, 0);
        this.txtTotalTrials = new Spinner(composite_1, 2048);
        GridData gd_txtTotalTrials = new GridData(4, 0x1000000, true, true, 1, 1);
        gd_txtTotalTrials.widthHint = 20;
        this.txtTotalTrials.setLayoutData((Object)gd_txtTotalTrials);
        this.txtTotalTrials.setMaximum(100);
        this.txtTotalTrials.setMinimum(1);
        this.txtTotalTreatments.addModifyListener(new ModifyListener(){

            public void modifyText(ModifyEvent e) {
                IncompleteBlockAlphaLatticeDesignDialog.this.setPlotValue();
            }
        });
        this.txtTotalBlockSize.addModifyListener(new ModifyListener(){

            public void modifyText(ModifyEvent e) {
                IncompleteBlockAlphaLatticeDesignDialog.this.setPlotValue();
            }
        });
        Group group = new Group(container, 0);
        group.setLayoutData((Object)new GridData(4, 0x1000000, false, true, 1, 1));
        group.setText("Field Book ");
        group.setLayout((Layout)new GridLayout(4, false));
        Label label_2 = new Label((Composite)group, 0);
        GridData gd_label_2 = new GridData(16384, 0x1000000, true, false, 1, 1);
        gd_label_2.widthHint = 40;
        label_2.setLayoutData((Object)gd_label_2);
        label_2.setText("Name");
        this.txtFileName = new Text((Composite)group, 2048);
        this.txtFileName.setText("fieldbookAlpha");
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

    public void setPlotValue() {
        double i = (double)this.txtTotalTreatments.getSelection() / (double)this.txtTotalBlockSize.getSelection();
        String value = Double.toString(i);
        this.txtBlksPerRep.setText(value);
    }

    protected void buttonPressed(int buttonId) {
        if (buttonId == 8) {
            this.txtTotalTreatments.setSelection(9);
            this.txtTotalBlockSize.setSelection(3);
            this.txtTotalReplicates.setSelection(2);
            this.txtBlksPerRep.setSelection(3);
            this.txtRowsEachBlk.setSelection(3);
            this.txtRowsEachRep.setSelection(3);
            this.txtFieldRows.setSelection(3);
            this.txtTotalTrials.setSelection(1);
            this.cmbOrder.setText("Plot Order");
            this.txtFileName.setText("fieldbookAlpha");
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
        if (this.txtTotalBlockSize.getSelection() < 2) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"The minimum value of the number of plots per block is equal to 2.");
            this.txtTotalBlockSize.setSelection(3);
            return;
        }
        if (this.txtRowsEachRep.getSelection() < this.txtRowsEachBlk.getSelection()) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"The minimum value of the number of rows in each replicate is equal to the number of rows in each block.");
            this.txtRowsEachRep.setSelection(this.txtRowsEachBlk.getSelection());
            return;
        }
        if (this.txtFieldRows.getSelection() < this.txtRowsEachRep.getSelection()) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"The minimum value of the field rows is equal to the number of rows in each replicate.");
            this.txtFieldRows.setSelection(this.txtRowsEachRep.getSelection());
            return;
        }
        if (this.txtRowsEachBlk.getSelection() > this.txtTotalBlockSize.getSelection()) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"The maximum value of the number of rows in each block is equal to the number of plots per block.");
            this.txtRowsEachBlk.setSelection(this.txtTotalBlockSize.getSelection());
            return;
        }
        if (this.txtRowsEachRep.getSelection() > this.txtTotalTreatments.getSelection()) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"The maximum value of the number of rows in each replicate is equal to the number of treatments.");
            this.txtRowsEachRep.setSelection(this.txtTotalTreatments.getSelection());
            return;
        }
        if (this.txtFieldRows.getSelection() > this.txtRowsEachRep.getSelection() * this.txtTotalReplicates.getSelection()) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"The maximum value of the field rows is equal to the product of number of rows in each replicate and total number of replicates.");
            this.txtFieldRows.setSelection(this.txtRowsEachRep.getSelection() * this.txtTotalReplicates.getSelection());
            return;
        }
        if (this.txtFileName.getText().equals("")) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"Field Filename must not be empty.");
            return;
        }
        if (this.txtTotalTreatments.getSelection() > 1500) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"The maximum total number of experimental units is 1500.");
            return;
        }
        if (this.txtTotalTreatments.getSelection() % this.txtTotalBlockSize.getSelection() != 0) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"The number of treatments should be divisible by the number of plots per block.");
            return;
        }
        if (this.txtTotalBlockSize.getSelection() % this.txtRowsEachBlk.getSelection() != 0) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"The number of plots per block should be divisible by the number of rows in each block");
            return;
        }
        if (this.txtFieldRows.getSelection() % this.txtRowsEachRep.getSelection() != 0) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"The number of field rows should be divisible by the number of rows in each replicate.");
            return;
        }
        if (this.txtTotalReplicates.getSelection() % (this.txtFieldRows.getSelection() / this.txtRowsEachRep.getSelection()) != 0) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"The quotient of the number of field rows and number of rows in each replicate should be a factor of the number of the replicates.");
            return;
        }
        if (this.txtTotalTreatments.getSelection() % this.txtRowsEachRep.getSelection() != 0) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"The number of treatment should be divisible by the number of rows in each replicate.");
            return;
        }
        if (this.txtRowsEachRep.getSelection() % this.txtRowsEachBlk.getSelection() != 0) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"The number of rows per replicate should be divisible by the number of rows per block.");
            return;
        }
        if (this.txtTotalTreatments.getSelection() / this.txtTotalBlockSize.getSelection() % (this.txtRowsEachRep.getSelection() / this.txtRowsEachBlk.getSelection()) != 0) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"The quotient of the number of rows in each replicate and number of rows in each block should be a factor of the number of blocks per replicate.");
            return;
        }
        OperationProgressDialog rInfo = new OperationProgressDialog(this.getShell(), "Performing Randomization");
        rInfo.open();
        this.btnOk.setEnabled(false);
        String outputFileTxt = outputFile = StarRandomizationUtilities.createOutputFolder("AlphaLattice");
        String outputFileCsv = this.txtFileName.getText();
        String fieldOrder = "Plot Order";
        if (this.cmbOrder.getText().equals("Serpentine")) {
            fieldOrder = "Serpentine";
        }
        ProjectExplorerView.rJavaManager.getPbToolRandomizationManager().doDesignAlpha(outputFileTxt.replace(File.separator, "/"), outputFileCsv.replace(File.separator, "/"), Integer.valueOf(this.txtTotalTreatments.getSelection()), Integer.valueOf(this.txtTotalBlockSize.getSelection()), Integer.valueOf(this.txtTotalReplicates.getSelection()), Integer.valueOf(this.txtTotalTrials.getSelection()), Integer.valueOf(this.txtRowsEachBlk.getSelection()), Integer.valueOf(this.txtRowsEachRep.getSelection()), Integer.valueOf(this.txtFieldRows.getSelection()), fieldOrder);
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
        return new Point(382, 426);
    }
}

