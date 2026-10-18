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
package org.irri.breedingtool.star.design.dialog;

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
import org.irri.breedingtool.utility.StarAnalysisUtilities;
import org.irri.breedingtool.utility.StarRandomizationUtilities;

public class AugmentedLatinSquareDesignDialog
extends Dialog {
    private Spinner txtTotalReplicatedTreatments;
    private Spinner txtTotalUnreplicatedTreatments;
    private Composite composite;
    private Spinner txtTotalTrials;
    private Button btnOk;
    private Text txtPlots;
    private Spinner txtRows;
    private Text txtFileName;
    private Combo cmbOrder;

    public AugmentedLatinSquareDesignDialog(Shell parentShell) {
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
        lblFactorialDesign.setText("Augmented Latin Square Design");
        Label label = new Label(container, 258);
        label.setLayoutData((Object)new GridData(4, 0x1000000, true, false, 1, 1));
        this.composite = new Composite(container, 0);
        this.composite.setLayoutData((Object)new GridData(4, 0x1000000, false, true, 1, 1));
        this.composite.setLayout((Layout)new GridLayout(3, false));
        Label lblNumberOfReplicated = new Label(this.composite, 0);
        lblNumberOfReplicated.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        lblNumberOfReplicated.setLayoutData((Object)new GridData(16384, 0x1000000, true, false, 1, 1));
        lblNumberOfReplicated.setText("Number of Replicated Treatments");
        Label lblNewLabel = new Label(this.composite, 0);
        GridData gd_lblNewLabel = new GridData(16384, 0x1000000, false, false, 1, 1);
        gd_lblNewLabel.widthHint = 60;
        lblNewLabel.setLayoutData((Object)gd_lblNewLabel);
        this.txtTotalReplicatedTreatments = new Spinner(this.composite, 2048);
        GridData gd_txtTotalReplicatedTreatments = new GridData(4, 0x1000000, true, false, 1, 1);
        gd_txtTotalReplicatedTreatments.widthHint = 20;
        this.txtTotalReplicatedTreatments.setLayoutData((Object)gd_txtTotalReplicatedTreatments);
        this.txtTotalReplicatedTreatments.setMaximum(11);
        this.txtTotalReplicatedTreatments.setSelection(2);
        Label lblNumberOfUnreplicated = new Label(this.composite, 0);
        lblNumberOfUnreplicated.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        lblNumberOfUnreplicated.setLayoutData((Object)new GridData(16384, 0x1000000, true, false, 1, 1));
        lblNumberOfUnreplicated.setText("Number of Unreplicated Treatments");
        new Label(this.composite, 0);
        this.txtTotalUnreplicatedTreatments = new Spinner(this.composite, 2048);
        GridData gd_txtTotalUnreplicatedTreatments = new GridData(4, 0x1000000, true, false, 1, 1);
        gd_txtTotalUnreplicatedTreatments.widthHint = 20;
        this.txtTotalUnreplicatedTreatments.setLayoutData((Object)gd_txtTotalUnreplicatedTreatments);
        this.txtTotalUnreplicatedTreatments.setMaximum(2000);
        this.txtTotalUnreplicatedTreatments.setSelection(2);
        Label lblNumberOfRows = new Label(this.composite, 0);
        lblNumberOfRows.setText("Number of Field Rows");
        lblNumberOfRows.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        new Label(this.composite, 0);
        this.txtRows = new Spinner(this.composite, 2048);
        GridData gd_txtRows = new GridData(4, 0x1000000, false, false, 1, 1);
        gd_txtRows.widthHint = 20;
        this.txtRows.setLayoutData((Object)gd_txtRows);
        this.txtRows.setMaximum(500);
        this.txtRows.setSelection(2);
        Label lblNumberOfPlots = new Label(this.composite, 0);
        lblNumberOfPlots.setText("Number of Plots in the Design");
        lblNumberOfPlots.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        new Label(this.composite, 0);
        this.txtPlots = new Text(this.composite, 2048);
        this.txtPlots.setBackground(SWTResourceManager.getColor(1));
        this.txtPlots.setText("6");
        this.txtPlots.setEditable(false);
        GridData gd_txtPlots = new GridData(4, 0x1000000, true, false, 1, 1);
        gd_txtPlots.widthHint = 20;
        this.txtPlots.setLayoutData((Object)gd_txtPlots);
        Label lblNumberOfTrials = new Label(this.composite, 0);
        lblNumberOfTrials.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        lblNumberOfTrials.setLayoutData((Object)new GridData(16384, 0x1000000, true, false, 1, 1));
        lblNumberOfTrials.setText("Number of Trials");
        new Label(this.composite, 0);
        this.txtTotalTrials = new Spinner(this.composite, 2048);
        GridData gd_txtTotalTrials = new GridData(4, 0x1000000, true, false, 1, 1);
        gd_txtTotalTrials.widthHint = 20;
        this.txtTotalTrials.setLayoutData((Object)gd_txtTotalTrials);
        this.txtTotalTrials.setMaximum(100);
        this.txtTotalTrials.setMinimum(1);
        this.txtTotalReplicatedTreatments.addModifyListener(new ModifyListener(){

            public void modifyText(ModifyEvent e) {
                AugmentedLatinSquareDesignDialog.this.setPlotValue();
            }
        });
        this.txtTotalUnreplicatedTreatments.addModifyListener(new ModifyListener(){

            public void modifyText(ModifyEvent e) {
                AugmentedLatinSquareDesignDialog.this.setPlotValue();
            }
        });
        Group group = new Group(container, 0);
        group.setLayoutData((Object)new GridData(4, 0x1000000, false, true, 1, 1));
        group.setText("Field Book ");
        group.setLayout((Layout)new GridLayout(5, false));
        Label label_1 = new Label((Composite)group, 0);
        GridData gd_label_1 = new GridData(16384, 0x1000000, true, false, 1, 1);
        gd_label_1.widthHint = 40;
        label_1.setLayoutData((Object)gd_label_1);
        label_1.setText("Name");
        this.txtFileName = new Text((Composite)group, 2048);
        this.txtFileName.setText("fieldbookAugLSD");
        GridData gd_txtFileName = new GridData(4, 0x1000000, true, false, 1, 1);
        gd_txtFileName.widthHint = 100;
        this.txtFileName.setLayoutData((Object)gd_txtFileName);
        Label label_2 = new Label((Composite)group, 0);
        GridData gd_label_2 = new GridData(16384, 0x1000000, false, false, 1, 1);
        gd_label_2.widthHint = 10;
        label_2.setLayoutData((Object)gd_label_2);
        Label label_3 = new Label((Composite)group, 0);
        GridData gd_label_3 = new GridData(131072, 0x1000000, true, false, 1, 1);
        gd_label_3.widthHint = 40;
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

    public void setPlotValue() {
        int i = this.txtTotalReplicatedTreatments.getSelection() * this.txtTotalReplicatedTreatments.getSelection() + this.txtTotalUnreplicatedTreatments.getSelection();
        String value = Integer.toString(i);
        this.txtPlots.setText(value);
    }

    protected void buttonPressed(int buttonId) {
        if (buttonId == 8) {
            this.txtTotalReplicatedTreatments.setSelection(2);
            this.txtTotalUnreplicatedTreatments.setSelection(2);
            this.txtRows.setSelection(2);
            this.txtPlots.setSelection(6);
            this.txtTotalTrials.setSelection(1);
            this.cmbOrder.setText("Plot Order");
            this.txtFileName.setText("fieldbookAugLSD");
        }
        super.buttonPressed(buttonId);
    }

    protected void okPressed() {
        String outputFile;
        if (this.txtTotalReplicatedTreatments.getSelection() < 2) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"The minimum value of the number of replicated treatments is equal to 2.");
            this.txtTotalReplicatedTreatments.setSelection(2);
            return;
        }
        if (this.txtTotalUnreplicatedTreatments.getSelection() < 2) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"The minimum value of the number of unreplicated treatments is equal to 2.");
            this.txtTotalUnreplicatedTreatments.setSelection(2);
            return;
        }
        if (this.txtRows.getSelection() < 2) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"The minimum value of the number of field rows is equal to 2.");
            this.txtRows.setSelection(2);
            return;
        }
        if (this.txtFileName.getText().equals("")) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"Field Filename must not be empty.");
            return;
        }
        if (this.txtTotalReplicatedTreatments.getSelection() > this.txtRows.getSelection()) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"Too few field rows for blocking.");
            return;
        }
        if (Double.parseDouble(this.txtPlots.getText()) % (double)this.txtRows.getSelection() != 0.0) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"Number of field rows must divide number of plots.");
            return;
        }
        if ((double)this.txtTotalReplicatedTreatments.getSelection() > Double.parseDouble(this.txtPlots.getText()) / (double)this.txtRows.getSelection()) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"Too few field column for blocking.");
            return;
        }
        OperationProgressDialog rInfo = new OperationProgressDialog(this.getShell(), "Performing Randomization");
        rInfo.open();
        this.btnOk.setEnabled(false);
        String outputFileTxt = outputFile = StarRandomizationUtilities.createOutputFolder("AugmentedLSD");
        String outputFileCsv = this.txtFileName.getText();
        String fieldOrder = "Plot Order";
        if (this.cmbOrder.getText().equals("Serpentine")) {
            fieldOrder = "Serpentine";
        }
        ProjectExplorerView.rJavaManager.getSTARDesignManager().doDesignAugLSD(outputFileTxt.replace(File.separator, "/"), outputFileCsv.replace(File.separator, "/"), Integer.valueOf(this.txtTotalReplicatedTreatments.getSelection()), Integer.valueOf(this.txtTotalUnreplicatedTreatments.getSelection()), Integer.valueOf(this.txtRows.getSelection()), Integer.valueOf(this.txtTotalTrials.getSelection()), fieldOrder);
        StarAnalysisUtilities.testVarArgs(outputFileTxt.replace(File.separator, "/"), outputFileCsv.replace(File.separator, "/"), this.txtTotalReplicatedTreatments.getSelection(), this.txtTotalUnreplicatedTreatments.getSelection(), this.txtRows.getSelection(), this.txtTotalTrials.getSelection(), fieldOrder);
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
        return new Point(382, 362);
    }
}

