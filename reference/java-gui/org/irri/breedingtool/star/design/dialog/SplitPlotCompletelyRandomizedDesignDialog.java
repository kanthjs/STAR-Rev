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
import java.util.ArrayList;
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

public class SplitPlotCompletelyRandomizedDesignDialog
extends Dialog {
    private Spinner txtTotalReplicates;
    private Spinner txtTotalTrials;
    private Text txtMainPlotFactorName;
    private Text txtMainPlotFactorID;
    private Text txtSubPlotFactorName;
    private Text txtSubPlotFactorID;
    private Spinner txtMainPlotFactorLevels;
    private Spinner txtSubPlotFactorLevels;
    private Label lblMainPlotFactor;
    private Label lblSubplotFactor;
    private Label lblNumberOfTrials;
    private Label lblNumberOfRows;
    private Label lblNumberOfField;
    private Label lblNumberOfReplicates;
    private Spinner txtRows;
    private Spinner txtFieldRows;
    private Text txtFileName;
    private Label lblNewLabel;
    private Combo cmbOrder;
    private Button btnOk;

    public SplitPlotCompletelyRandomizedDesignDialog(Shell parentShell) {
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
        lblFactorialDesign.setText("Split Plot in CRD");
        Label label = new Label(container, 258);
        label.setLayoutData((Object)new GridData(4, 0x1000000, true, false, 1, 1));
        Group grpDesignParameters = new Group(container, 0);
        grpDesignParameters.setLayout((Layout)new GridLayout(1, false));
        GridData gd_grpDesignParameters = new GridData(4, 4, true, true, 1, 1);
        gd_grpDesignParameters.heightHint = 234;
        grpDesignParameters.setLayoutData((Object)gd_grpDesignParameters);
        Group group = new Group((Composite)grpDesignParameters, 0);
        group.setLayoutData((Object)new GridData(4, 0x1000000, false, true, 1, 1));
        group.setText("Factor Definition:");
        group.setLayout((Layout)new GridLayout(4, false));
        Label label_1 = new Label((Composite)group, 0);
        label_1.setLayoutData((Object)new GridData(16384, 0x1000000, true, false, 1, 1));
        label_1.setText("Factor");
        label_1.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        Label label_2 = new Label((Composite)group, 0);
        label_2.setLayoutData((Object)new GridData(16384, 128, true, false, 1, 1));
        label_2.setText("Name");
        label_2.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        Label label_3 = new Label((Composite)group, 0);
        label_3.setText("Factor ID");
        label_3.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        Label label_4 = new Label((Composite)group, 0);
        label_4.setLayoutData((Object)new GridData(16384, 128, true, false, 1, 1));
        label_4.setText("Levels");
        label_4.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        this.lblMainPlotFactor = new Label((Composite)group, 0);
        this.lblMainPlotFactor.setLayoutData((Object)new GridData(16384, 0x1000000, true, false, 1, 1));
        this.lblMainPlotFactor.setText("Mainplot");
        this.lblMainPlotFactor.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        this.txtMainPlotFactorName = new Text((Composite)group, 2048);
        this.txtMainPlotFactorName.setText("main");
        GridData gd_txtMainPlotFactorName = new GridData(4, 4, true, false, 1, 1);
        gd_txtMainPlotFactorName.widthHint = 80;
        this.txtMainPlotFactorName.setLayoutData((Object)gd_txtMainPlotFactorName);
        this.txtMainPlotFactorID = new Text((Composite)group, 2048);
        this.txtMainPlotFactorID.setText("A");
        GridData gd_txtMainPlotFactorID = new GridData(4, 0x1000000, true, false, 1, 1);
        gd_txtMainPlotFactorID.widthHint = 50;
        this.txtMainPlotFactorID.setLayoutData((Object)gd_txtMainPlotFactorID);
        this.txtMainPlotFactorLevels = new Spinner((Composite)group, 2048);
        GridData gd_txtMainPlotFactorLevels = new GridData(4, 4, true, false, 1, 1);
        gd_txtMainPlotFactorLevels.widthHint = 20;
        this.txtMainPlotFactorLevels.setLayoutData((Object)gd_txtMainPlotFactorLevels);
        this.txtMainPlotFactorLevels.setMaximum(500);
        this.txtMainPlotFactorLevels.setMinimum(2);
        this.lblSubplotFactor = new Label((Composite)group, 0);
        this.lblSubplotFactor.setLayoutData((Object)new GridData(16384, 0x1000000, true, false, 1, 1));
        this.lblSubplotFactor.setText("Subplot");
        this.lblSubplotFactor.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        this.txtSubPlotFactorName = new Text((Composite)group, 2048);
        this.txtSubPlotFactorName.setText("sub");
        GridData gd_txtSubPlotFactorName = new GridData(4, 0x1000000, true, false, 1, 1);
        gd_txtSubPlotFactorName.widthHint = 80;
        this.txtSubPlotFactorName.setLayoutData((Object)gd_txtSubPlotFactorName);
        this.txtSubPlotFactorID = new Text((Composite)group, 2048);
        this.txtSubPlotFactorID.setText("B");
        GridData gd_txtSubPlotFactorID = new GridData(4, 0x1000000, true, false, 1, 1);
        gd_txtSubPlotFactorID.widthHint = 50;
        this.txtSubPlotFactorID.setLayoutData((Object)gd_txtSubPlotFactorID);
        this.txtSubPlotFactorLevels = new Spinner((Composite)group, 2048);
        GridData gd_txtSubPlotFactorLevels = new GridData(4, 0x1000000, true, false, 1, 1);
        gd_txtSubPlotFactorLevels.widthHint = 20;
        this.txtSubPlotFactorLevels.setLayoutData((Object)gd_txtSubPlotFactorLevels);
        this.txtSubPlotFactorLevels.setMaximum(500);
        this.txtSubPlotFactorLevels.setMinimum(2);
        Composite composite_1 = new Composite((Composite)grpDesignParameters, 0);
        composite_1.setLayout((Layout)new GridLayout(3, false));
        composite_1.setLayoutData((Object)new GridData(4, 0x1000000, true, true, 1, 1));
        this.lblNumberOfReplicates = new Label(composite_1, 0);
        this.lblNumberOfReplicates.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        this.lblNumberOfReplicates.setLayoutData((Object)new GridData(4, 0x1000000, true, false, 1, 1));
        this.lblNumberOfReplicates.setText("Number of Replicates");
        this.lblNewLabel = new Label(composite_1, 0);
        GridData gd_lblNewLabel = new GridData(4, 0x1000000, false, false, 1, 1);
        gd_lblNewLabel.widthHint = 10;
        this.lblNewLabel.setLayoutData((Object)gd_lblNewLabel);
        this.txtTotalReplicates = new Spinner(composite_1, 2048);
        GridData gd_txtTotalReplicates = new GridData(4, 0x1000000, true, false, 1, 1);
        gd_txtTotalReplicates.widthHint = 20;
        this.txtTotalReplicates.setLayoutData((Object)gd_txtTotalReplicates);
        this.txtTotalReplicates.setMaximum(500);
        this.txtTotalReplicates.setMinimum(2);
        this.lblNumberOfRows = new Label(composite_1, 0);
        this.lblNumberOfRows.setLayoutData((Object)new GridData(4, 0x1000000, false, false, 1, 1));
        this.lblNumberOfRows.setText("Number of Subplots along the Length of Each Mainplot");
        this.lblNumberOfRows.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        new Label(composite_1, 0);
        this.txtRows = new Spinner(composite_1, 2048);
        GridData gd_txtRows = new GridData(4, 0x1000000, false, false, 1, 1);
        gd_txtRows.widthHint = 20;
        this.txtRows.setLayoutData((Object)gd_txtRows);
        this.txtRows.setMaximum(500);
        this.txtRows.setMinimum(1);
        this.lblNumberOfField = new Label(composite_1, 0);
        this.lblNumberOfField.setLayoutData((Object)new GridData(4, 0x1000000, false, false, 1, 1));
        this.lblNumberOfField.setText("Number of Field Rows");
        this.lblNumberOfField.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        new Label(composite_1, 0);
        this.txtFieldRows = new Spinner(composite_1, 2048);
        GridData gd_txtFieldRows = new GridData(4, 0x1000000, false, false, 1, 1);
        gd_txtFieldRows.widthHint = 20;
        this.txtFieldRows.setLayoutData((Object)gd_txtFieldRows);
        this.txtFieldRows.setMaximum(500);
        this.txtFieldRows.setMinimum(1);
        this.lblNumberOfTrials = new Label(composite_1, 0);
        this.lblNumberOfTrials.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        this.lblNumberOfTrials.setLayoutData((Object)new GridData(4, 0x1000000, true, false, 1, 1));
        this.lblNumberOfTrials.setText("Number of Trials");
        new Label(composite_1, 0);
        this.txtTotalTrials = new Spinner(composite_1, 2048);
        GridData gd_txtTotalTrials = new GridData(4, 0x1000000, true, false, 1, 1);
        gd_txtTotalTrials.widthHint = 20;
        this.txtTotalTrials.setLayoutData((Object)gd_txtTotalTrials);
        this.txtTotalTrials.setMaximum(100);
        this.txtTotalTrials.setMinimum(1);
        Group group_1 = new Group(container, 0);
        group_1.setLayoutData((Object)new GridData(4, 0x1000000, false, true, 1, 1));
        group_1.setText("Field Book ");
        group_1.setLayout((Layout)new GridLayout(4, false));
        Label label_5 = new Label((Composite)group_1, 0);
        GridData gd_label_5 = new GridData(16384, 0x1000000, true, false, 1, 1);
        gd_label_5.widthHint = 40;
        label_5.setLayoutData((Object)gd_label_5);
        label_5.setText("Name");
        this.txtFileName = new Text((Composite)group_1, 2048);
        this.txtFileName.setText("fieldbookSplitCRD");
        GridData gd_txtFileName = new GridData(4, 0x1000000, true, false, 1, 1);
        gd_txtFileName.widthHint = 100;
        this.txtFileName.setLayoutData((Object)gd_txtFileName);
        Label label_7 = new Label((Composite)group_1, 0);
        GridData gd_label_7 = new GridData(131072, 0x1000000, true, false, 1, 1);
        gd_label_7.widthHint = 40;
        label_7.setLayoutData((Object)gd_label_7);
        label_7.setText("Order");
        this.cmbOrder = new Combo((Composite)group_1, 8);
        this.cmbOrder.setItems(new String[]{"Plot Order", "Serpentine"});
        GridData gd_cmbOrder = new GridData(4, 0x1000000, true, false, 1, 1);
        gd_cmbOrder.widthHint = 70;
        this.cmbOrder.setLayoutData((Object)gd_cmbOrder);
        this.cmbOrder.select(0);
        return container;
    }

    protected void buttonPressed(int buttonId) {
        if (buttonId == 8) {
            this.txtMainPlotFactorName.setText("main");
            this.txtSubPlotFactorName.setText("sub");
            this.txtMainPlotFactorID.setText("A");
            this.txtSubPlotFactorID.setText("B");
            this.txtMainPlotFactorLevels.setSelection(2);
            this.txtSubPlotFactorLevels.setSelection(2);
            this.txtTotalReplicates.setSelection(2);
            this.txtRows.setSelection(1);
            this.txtTotalTrials.setSelection(1);
            this.txtFieldRows.setSelection(1);
            this.cmbOrder.setText("Plot Order");
            this.txtFileName.setText("fieldbookSplitCRD");
        }
        super.buttonPressed(buttonId);
    }

    protected void okPressed() {
        String outputFile;
        ArrayList<Text> listText = new ArrayList<Text>();
        listText.add(this.txtMainPlotFactorID);
        listText.add(this.txtSubPlotFactorID);
        if (!StarRandomizationUtilities.isTextUnique(listText)) {
            MessageDialog.open((int)1, (Shell)this.getShell(), (String)"Error", (String)"Similar variable names detected. Make sure all the variables are unique.", (int)0);
            return;
        }
        if (this.txtFileName.getText().equals("")) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"Field Filename must not be empty.");
            return;
        }
        if (this.txtMainPlotFactorName.getText().contains(" ") || this.txtSubPlotFactorName.getText().contains(" ")) {
            MessageDialog.open((int)1, (Shell)this.getShell(), (String)"Error", (String)"Factor Name must not contain space", (int)1);
            return;
        }
        if (this.txtMainPlotFactorID.getText().contains(" ") || this.txtSubPlotFactorID.getText().contains(" ")) {
            MessageDialog.open((int)1, (Shell)this.getShell(), (String)"Error", (String)"Factor ID must not contain space", (int)1);
            return;
        }
        if (!StarRandomizationUtilities.validateTextFiles(this.txtMainPlotFactorName, this.txtSubPlotFactorName)) {
            MessageDialog.open((int)1, (Shell)this.getShell(), (String)"Error", (String)"Fields must not be empty.", (int)1);
            return;
        }
        String mainfactorID = this.txtMainPlotFactorID.getText().toString();
        if (!StarRandomizationUtilities.validateVariableText(mainfactorID)) {
            MessageDialog.open((int)1, (Shell)this.getShell(), (String)"Error", (String)"Factor ID must start with a letter and must contain:[a-z,A-Z] only.", (int)1);
            return;
        }
        String subfactorID = this.txtSubPlotFactorID.getText().toString();
        if (!StarRandomizationUtilities.validateVariableText(subfactorID)) {
            MessageDialog.open((int)1, (Shell)this.getShell(), (String)"Error", (String)"Factor ID must start with a letter and must contain:[a-z,A-Z] only.", (int)1);
            return;
        }
        if (!StarRandomizationUtilities.validateTextFiles(this.txtMainPlotFactorID, this.txtSubPlotFactorID)) {
            MessageDialog.open((int)1, (Shell)this.getShell(), (String)"Error", (String)"Fields must not be empty", (int)1);
            return;
        }
        if (this.txtMainPlotFactorID.getCharCount() > 4 || this.txtSubPlotFactorID.getCharCount() > 4) {
            MessageDialog.open((int)1, (Shell)this.getShell(), (String)"Error", (String)"Factor ID must contain not more than four characters. ", (int)1);
            return;
        }
        int plot = this.txtTotalReplicates.getSelection() * this.txtMainPlotFactorLevels.getSelection() * this.txtSubPlotFactorLevels.getSelection();
        if (plot % this.txtFieldRows.getSelection() != 0) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"Number of field rows must divide number of plots.");
            return;
        }
        if (this.txtFieldRows.getSelection() % this.txtRows.getSelection() != 0) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"Number of field rows must be divisible by the number of plots along the length of each main plot.");
            return;
        }
        if (this.txtRows.getSelection() > this.txtFieldRows.getSelection()) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"Number of plots along the length of each main plot should be less than or equal to the number of field rows and should divide the number of field rows.");
            return;
        }
        if (this.txtRows.getSelection() > this.txtSubPlotFactorLevels.getSelection()) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"Number of plots along the length of each main plot should divide the levels of sub plot factor.");
            return;
        }
        OperationProgressDialog rInfo = new OperationProgressDialog(this.getShell(), "Performing Randomization");
        rInfo.open();
        this.btnOk.setEnabled(false);
        String outputFileTxt = outputFile = StarRandomizationUtilities.createOutputFolder("SplitPlotCRD");
        String outputFileCsv = this.txtFileName.getText();
        String fieldOrder = "Plot Order";
        if (this.cmbOrder.getText().equals("Serpentine")) {
            fieldOrder = "Serpentine";
        }
        Integer[] factorLevels = new Integer[]{this.txtMainPlotFactorLevels.getSelection(), this.txtSubPlotFactorLevels.getSelection()};
        String[] factorID = new String[]{this.txtMainPlotFactorID.getText(), this.txtSubPlotFactorID.getText()};
        String design = "crd";
        ProjectExplorerView.rJavaManager.getSTARDesignManager().doDesignSplit(outputFileTxt.replace(File.separator, "/"), outputFileCsv.replace(File.separator, "/"), this.txtMainPlotFactorName.getText(), this.txtSubPlotFactorName.getText(), null, null, factorID, factorLevels, Integer.valueOf(this.txtTotalReplicates.getSelection()), Integer.valueOf(this.txtTotalTrials.getSelection()), design, Integer.valueOf(this.txtFieldRows.getSelection()), null, Integer.valueOf(this.txtRows.getSelection()), null, null, fieldOrder);
        StarRandomizationUtilities.testVarArgs(outputFileTxt.replace(File.separator, "/"), outputFileCsv.replace(File.separator, "/"), this.txtMainPlotFactorName.getText(), this.txtSubPlotFactorName.getText(), null, null, factorID, factorLevels, this.txtTotalReplicates.getSelection(), this.txtTotalTrials.getSelection(), design, this.txtFieldRows.getSelection(), null, this.txtRows.getSelection(), null, null, fieldOrder);
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
        return new Point(391, 455);
    }
}

