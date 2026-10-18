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

public class StripSplitPlotDesignDialog
extends Dialog {
    private Spinner txtTotalBlocks;
    private Spinner txtTotalTrials;
    private Text txtVerticalFactorNames;
    private Text txtVerticalFactorID;
    private Text txtHorizontalFactorNames;
    private Text txtHorizontalFactorID;
    private Text txtSubPlotFactorNames;
    private Text txtSubPlotFactorID;
    private Spinner txtSubPlotFactorLevels;
    private Spinner txtHorizontalFactorLevels;
    private Spinner txtVerticalFactorLevels;
    private Text txtFileName;
    private Button btnOk;
    private Spinner txtRows;
    private Spinner txtFieldRows;
    private Combo cmbOrder;

    public StripSplitPlotDesignDialog(Shell parentShell) {
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
        lblFactorialDesign.setFont(SWTResourceManager.getFont("Tahoma", 9, 1));
        lblFactorialDesign.setText("Strip-Split Plot Design");
        Label label = new Label(container, 258);
        label.setLayoutData((Object)new GridData(4, 0x1000000, true, false, 1, 1));
        Group grpDesignParameters = new Group(container, 0);
        grpDesignParameters.setLayout((Layout)new GridLayout(1, false));
        GridData gd_grpDesignParameters = new GridData(4, 4, true, true, 1, 1);
        gd_grpDesignParameters.heightHint = 237;
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
        label_2.setLayoutData((Object)new GridData(16384, 128, false, false, 1, 1));
        label_2.setText("Name");
        label_2.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        Label label_3 = new Label((Composite)group, 0);
        label_3.setText("Factor ID");
        label_3.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        Label label_4 = new Label((Composite)group, 0);
        label_4.setLayoutData((Object)new GridData(16384, 128, true, false, 1, 1));
        label_4.setText("Levels");
        label_4.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        Label lblVertical = new Label((Composite)group, 0);
        lblVertical.setLayoutData((Object)new GridData(16384, 0x1000000, true, false, 1, 1));
        lblVertical.setText("Vertical ");
        lblVertical.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        this.txtVerticalFactorNames = new Text((Composite)group, 2048);
        this.txtVerticalFactorNames.setText("vertical");
        GridData gd_txtVerticalFactorNames = new GridData(4, 4, false, false, 1, 1);
        gd_txtVerticalFactorNames.widthHint = 80;
        this.txtVerticalFactorNames.setLayoutData((Object)gd_txtVerticalFactorNames);
        this.txtVerticalFactorID = new Text((Composite)group, 2048);
        this.txtVerticalFactorID.setText("A");
        GridData gd_txtVerticalFactorID = new GridData(4, 0x1000000, false, false, 1, 1);
        gd_txtVerticalFactorID.widthHint = 50;
        this.txtVerticalFactorID.setLayoutData((Object)gd_txtVerticalFactorID);
        this.txtVerticalFactorLevels = new Spinner((Composite)group, 2048);
        GridData gd_txtVerticalFactorLevels = new GridData(4, 4, true, false, 1, 1);
        gd_txtVerticalFactorLevels.widthHint = 20;
        this.txtVerticalFactorLevels.setLayoutData((Object)gd_txtVerticalFactorLevels);
        this.txtVerticalFactorLevels.setMaximum(500);
        this.txtVerticalFactorLevels.setMinimum(2);
        Label lblHorizontal = new Label((Composite)group, 0);
        lblHorizontal.setLayoutData((Object)new GridData(16384, 0x1000000, true, false, 1, 1));
        lblHorizontal.setText("Horizontal");
        lblHorizontal.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        this.txtHorizontalFactorNames = new Text((Composite)group, 2048);
        this.txtHorizontalFactorNames.setText("horizontal");
        GridData gd_txtHorizontalFactorNames = new GridData(4, 0x1000000, false, false, 1, 1);
        gd_txtHorizontalFactorNames.widthHint = 80;
        this.txtHorizontalFactorNames.setLayoutData((Object)gd_txtHorizontalFactorNames);
        this.txtHorizontalFactorID = new Text((Composite)group, 2048);
        this.txtHorizontalFactorID.setText("B");
        GridData gd_txtHorizontalFactorID = new GridData(4, 0x1000000, false, false, 1, 1);
        gd_txtHorizontalFactorID.widthHint = 50;
        this.txtHorizontalFactorID.setLayoutData((Object)gd_txtHorizontalFactorID);
        this.txtHorizontalFactorLevels = new Spinner((Composite)group, 2048);
        GridData gd_txtHorizontalFactorLevels = new GridData(4, 0x1000000, true, false, 1, 1);
        gd_txtHorizontalFactorLevels.widthHint = 20;
        this.txtHorizontalFactorLevels.setLayoutData((Object)gd_txtHorizontalFactorLevels);
        this.txtHorizontalFactorLevels.setMaximum(500);
        this.txtHorizontalFactorLevels.setMinimum(2);
        Label lblSubPlot = new Label((Composite)group, 0);
        lblSubPlot.setLayoutData((Object)new GridData(16384, 0x1000000, true, false, 1, 1));
        lblSubPlot.setText("Subplot");
        lblSubPlot.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        this.txtSubPlotFactorNames = new Text((Composite)group, 2048);
        this.txtSubPlotFactorNames.setText("sub");
        GridData gd_txtSubPlotFactorNames = new GridData(4, 0x1000000, true, false, 1, 1);
        gd_txtSubPlotFactorNames.widthHint = 80;
        this.txtSubPlotFactorNames.setLayoutData((Object)gd_txtSubPlotFactorNames);
        this.txtSubPlotFactorID = new Text((Composite)group, 2048);
        this.txtSubPlotFactorID.setText("C");
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
        GridData gd_composite_1 = new GridData(4, 0x1000000, true, true, 1, 1);
        gd_composite_1.widthHint = 60;
        composite_1.setLayoutData((Object)gd_composite_1);
        Label lblNumberOfReplicates = new Label(composite_1, 0);
        lblNumberOfReplicates.setLayoutData((Object)new GridData(4, 0x1000000, true, true, 1, 1));
        lblNumberOfReplicates.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        lblNumberOfReplicates.setText("Number of Blocks");
        Label lblNewLabel = new Label(composite_1, 0);
        GridData gd_lblNewLabel = new GridData(16384, 0x1000000, false, false, 1, 1);
        gd_lblNewLabel.widthHint = 10;
        lblNewLabel.setLayoutData((Object)gd_lblNewLabel);
        this.txtTotalBlocks = new Spinner(composite_1, 2048);
        GridData gd_txtTotalBlocks = new GridData(4, 0x1000000, true, true, 1, 1);
        gd_txtTotalBlocks.widthHint = 20;
        this.txtTotalBlocks.setLayoutData((Object)gd_txtTotalBlocks);
        this.txtTotalBlocks.setMaximum(500);
        this.txtTotalBlocks.setMinimum(2);
        Label lblNumberOfRows = new Label(composite_1, 0);
        lblNumberOfRows.setLayoutData((Object)new GridData(4, 0x1000000, true, false, 1, 1));
        lblNumberOfRows.setText("Number of Subplots along the Length of Each VxH plot");
        lblNumberOfRows.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        new Label(composite_1, 0);
        this.txtRows = new Spinner(composite_1, 2048);
        this.txtRows.setLayoutData((Object)new GridData(4, 0x1000000, false, false, 1, 1));
        this.txtRows.setMinimum(1);
        Label lblNumberOfField = new Label(composite_1, 0);
        lblNumberOfField.setLayoutData((Object)new GridData(4, 0x1000000, true, false, 1, 1));
        lblNumberOfField.setText("Number of Field Rows");
        lblNumberOfField.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        new Label(composite_1, 0);
        this.txtFieldRows = new Spinner(composite_1, 2048);
        this.txtFieldRows.setLayoutData((Object)new GridData(4, 0x1000000, false, false, 1, 1));
        this.txtFieldRows.setMaximum(500);
        this.txtFieldRows.setMinimum(2);
        Label lblNumberOfTrials = new Label(composite_1, 0);
        lblNumberOfTrials.setLayoutData((Object)new GridData(4, 0x1000000, true, true, 1, 1));
        lblNumberOfTrials.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        lblNumberOfTrials.setText("Number of Trials");
        new Label(composite_1, 0);
        this.txtTotalTrials = new Spinner(composite_1, 2048);
        this.txtTotalTrials.setLayoutData((Object)new GridData(4, 0x1000000, true, true, 1, 1));
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
        this.txtFileName.setText("fieldbookStripSplit");
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
            this.txtVerticalFactorNames.setText("vertical");
            this.txtHorizontalFactorNames.setText("horizontal");
            this.txtSubPlotFactorNames.setText("sub");
            this.txtVerticalFactorID.setText("A");
            this.txtHorizontalFactorID.setText("B");
            this.txtSubPlotFactorID.setText("C");
            this.txtVerticalFactorLevels.setSelection(2);
            this.txtHorizontalFactorLevels.setSelection(2);
            this.txtSubPlotFactorLevels.setSelection(2);
            this.txtTotalBlocks.setSelection(2);
            this.txtRows.setSelection(1);
            this.txtTotalTrials.setSelection(1);
            this.txtFieldRows.setSelection(2);
            this.cmbOrder.setText("Plot Order");
            this.txtFileName.setText("fieldbookStripSplit");
        }
        super.buttonPressed(buttonId);
    }

    protected void okPressed() {
        String outputFile;
        ArrayList<Text> listText = new ArrayList<Text>();
        listText.add(this.txtVerticalFactorID);
        listText.add(this.txtHorizontalFactorID);
        listText.add(this.txtSubPlotFactorID);
        if (!StarRandomizationUtilities.isTextUnique(listText)) {
            MessageDialog.open((int)1, (Shell)this.getShell(), (String)"Error", (String)"Similar variable names detected. Make sure all the variables are unique.", (int)0);
            return;
        }
        if (this.txtVerticalFactorNames.getText().contains(" ") || this.txtHorizontalFactorNames.getText().contains(" ")) {
            MessageDialog.open((int)1, (Shell)this.getShell(), (String)"Error", (String)"Factor Name must not contain space", (int)1);
            return;
        }
        if (this.txtSubPlotFactorNames.getText().contains(" ")) {
            MessageDialog.open((int)1, (Shell)this.getShell(), (String)"Error", (String)"Factor Name must not contain space", (int)1);
            return;
        }
        if (this.txtVerticalFactorID.getText().contains(" ") || this.txtHorizontalFactorID.getText().contains(" ")) {
            MessageDialog.open((int)1, (Shell)this.getShell(), (String)"Error", (String)"Factor ID must not contain space", (int)1);
            return;
        }
        if (this.txtSubPlotFactorID.getText().contains(" ")) {
            MessageDialog.open((int)1, (Shell)this.getShell(), (String)"Error", (String)"Factor ID must not contain space", (int)1);
            return;
        }
        int i = 0;
        while (i < listText.size()) {
            if (listText.get(i) != this.txtFileName && !StarRandomizationUtilities.validateVariableText(listText.get(i).getText())) {
                MessageDialog.open((int)1, (Shell)this.getShell(), (String)"Error", (String)("'" + listText.get(i).getText() + "' is not a valid name. \nAll name fields must start with a letter and must contain:[a-z,A-Z] only. "), (int)0);
                return;
            }
            ++i;
        }
        if (this.txtFileName.getText().equals("")) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"Field Filename must not be empty.");
            return;
        }
        if (!StarRandomizationUtilities.validateTextFiles(this.txtVerticalFactorNames, this.txtHorizontalFactorNames, this.txtSubPlotFactorNames)) {
            MessageDialog.open((int)1, (Shell)this.getShell(), (String)"Error", (String)"Fields must not be empty", (int)1);
            return;
        }
        if (!StarRandomizationUtilities.validateTextFiles(this.txtVerticalFactorID, this.txtHorizontalFactorID, this.txtSubPlotFactorID)) {
            MessageDialog.open((int)1, (Shell)this.getShell(), (String)"Error", (String)"Fields must not be empty", (int)1);
            return;
        }
        String mainfactorID = this.txtVerticalFactorID.getText().toString();
        if (!StarRandomizationUtilities.validateVariableText(mainfactorID)) {
            MessageDialog.open((int)1, (Shell)this.getShell(), (String)"Error", (String)"Factor ID must start with a letter and must contain:[a-z,A-Z] only.", (int)1);
            return;
        }
        String subfactorID = this.txtHorizontalFactorID.getText().toString();
        if (!StarRandomizationUtilities.validateVariableText(subfactorID)) {
            MessageDialog.open((int)1, (Shell)this.getShell(), (String)"Error", (String)"Factor ID must start with a letter and must contain:[a-z,A-Z] only.", (int)1);
            return;
        }
        String subsubfactorID = this.txtSubPlotFactorID.getText().toString();
        if (!StarRandomizationUtilities.validateVariableText(subsubfactorID)) {
            MessageDialog.open((int)1, (Shell)this.getShell(), (String)"Error", (String)"Factor ID must start with a letter and must contain:[a-z,A-Z] only.", (int)1);
            return;
        }
        int plot = this.txtTotalBlocks.getSelection() * this.txtVerticalFactorLevels.getSelection() * this.txtHorizontalFactorLevels.getSelection() * this.txtSubPlotFactorLevels.getSelection();
        if (plot % this.txtFieldRows.getSelection() != 0) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"Number of field rows must divide number of plots.");
            return;
        }
        if (this.txtFieldRows.getSelection() % (this.txtHorizontalFactorLevels.getSelection() * this.txtRows.getSelection()) != 0) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"Number of field rows must be divisible by the product of the levels of the horizontal and number of plots along the length of each VxH plot.");
            return;
        }
        if (this.txtRows.getSelection() > this.txtSubPlotFactorLevels.getSelection()) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"Number of plots along the length of each VxH plot should divide the levels of the sub plot factor.");
            return;
        }
        if (this.txtVerticalFactorID.getCharCount() > 4) {
            MessageDialog.open((int)1, (Shell)this.getShell(), (String)"Error", (String)"Factor ID must contain not more than four characters. ", (int)1);
            return;
        }
        if (this.txtHorizontalFactorID.getCharCount() > 4) {
            MessageDialog.open((int)1, (Shell)this.getShell(), (String)"Error", (String)"Factor ID must contain not more than four characters. ", (int)1);
            return;
        }
        if (this.txtSubPlotFactorID.getCharCount() > 4) {
            MessageDialog.open((int)1, (Shell)this.getShell(), (String)"Error", (String)"Factor ID must contain not more than four characters. ", (int)1);
            return;
        }
        OperationProgressDialog rInfo = new OperationProgressDialog(this.getShell(), "Performing Randomization");
        rInfo.open();
        this.btnOk.setEnabled(false);
        String outputFileTxt = outputFile = StarRandomizationUtilities.createOutputFolder("StripSplitPlot");
        String outputFileCsv = this.txtFileName.getText();
        String fieldOrder = "Plot Order";
        if (this.cmbOrder.getText().equals("Serpentine")) {
            fieldOrder = "Serpentine";
        }
        Integer[] factorLevels = new Integer[]{this.txtVerticalFactorLevels.getSelection(), this.txtHorizontalFactorLevels.getSelection(), this.txtSubPlotFactorLevels.getSelection()};
        String[] factorID = new String[]{this.txtVerticalFactorID.getText(), this.txtHorizontalFactorID.getText(), this.txtSubPlotFactorID.getText()};
        ProjectExplorerView.rJavaManager.getSTARDesignManager().doDesignStrip(outputFileTxt.replace(File.separator, "/"), outputFileCsv.replace(File.separator, "/"), this.txtVerticalFactorNames.getText(), this.txtHorizontalFactorNames.getText(), this.txtSubPlotFactorNames.getText(), null, factorID, factorLevels, Integer.valueOf(this.txtTotalBlocks.getSelection()), Integer.valueOf(this.txtTotalTrials.getSelection()), Integer.valueOf(this.txtFieldRows.getSelection()), Integer.valueOf(this.txtRows.getSelection()), null, fieldOrder);
        StarRandomizationUtilities.testVarArgs(outputFileTxt.replace(File.separator, "/"), outputFileCsv.replace(File.separator, "/"), this.txtVerticalFactorNames.getText(), this.txtHorizontalFactorNames.getText(), this.txtSubPlotFactorNames.getText(), null, factorID, factorLevels, this.txtTotalBlocks.getSelection(), this.txtTotalTrials.getSelection(), this.txtFieldRows.getSelection(), this.txtRows.getSelection(), null, fieldOrder);
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
        return new Point(394, 472);
    }
}

