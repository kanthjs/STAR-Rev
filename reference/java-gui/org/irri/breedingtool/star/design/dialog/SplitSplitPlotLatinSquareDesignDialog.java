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

public class SplitSplitPlotLatinSquareDesignDialog
extends Dialog {
    private Spinner txtTotalTrials;
    private Text txtMainPlotFactorName;
    private Text txtMainPlotFactorID;
    private Text txtSubPlotFactorName;
    private Text txtSubPlotFactorID;
    private Text txtSubSubPlotFactorName;
    private Text txtSubSubPlotFactorID;
    private Spinner txtMainPlotFactorLevels;
    private Spinner txtSubSubPlotFactorLevels;
    private Spinner txtSubPlotFactorLevels;
    private Text txtFileName;
    private Spinner txtRowsPerSub;
    private Spinner txtRows;
    private Combo cmbOrder;
    private Button btnOk;

    public SplitSplitPlotLatinSquareDesignDialog(Shell parentShell) {
        super(parentShell);
        this.setShellStyle(2288);
    }

    protected void configureShell(Shell shell) {
        super.configureShell(shell);
        shell.setText("Randomization and Layout");
    }

    protected Control createDialogArea(Composite parent) {
        Composite container = (Composite)super.createDialogArea(parent);
        container.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        Label lblFactorialDesign = new Label(container, 0);
        lblFactorialDesign.setLayoutData((Object)new GridData(16384, 0x1000000, true, false, 1, 1));
        lblFactorialDesign.setFont(SWTResourceManager.getFont("Tahoma", 10, 1));
        lblFactorialDesign.setText("Split-Split Plot in Latin Square Design");
        Label label = new Label(container, 258);
        label.setLayoutData((Object)new GridData(4, 0x1000000, false, false, 1, 1));
        Group grpDesignParameters = new Group(container, 0);
        grpDesignParameters.setLayout((Layout)new GridLayout(1, false));
        GridData gd_grpDesignParameters = new GridData(4, 4, true, true, 1, 1);
        gd_grpDesignParameters.heightHint = 218;
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
        Label lblMainplot = new Label((Composite)group, 0);
        lblMainplot.setLayoutData((Object)new GridData(16384, 0x1000000, true, false, 1, 1));
        lblMainplot.setText("Mainplot");
        lblMainplot.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
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
        this.txtMainPlotFactorLevels.setMaximum(11);
        this.txtMainPlotFactorLevels.setMinimum(2);
        Label lblSubplot = new Label((Composite)group, 0);
        lblSubplot.setLayoutData((Object)new GridData(16384, 0x1000000, true, false, 1, 1));
        lblSubplot.setText("Subplot");
        lblSubplot.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
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
        Label lblSubsubPlot = new Label((Composite)group, 0);
        lblSubsubPlot.setLayoutData((Object)new GridData(16384, 0x1000000, true, false, 1, 1));
        lblSubsubPlot.setText("Sub-subplot");
        lblSubsubPlot.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        this.txtSubSubPlotFactorName = new Text((Composite)group, 2048);
        this.txtSubSubPlotFactorName.setText("ssub");
        this.txtSubSubPlotFactorName.setLayoutData((Object)new GridData(4, 0x1000000, true, false, 1, 1));
        this.txtSubSubPlotFactorID = new Text((Composite)group, 2048);
        this.txtSubSubPlotFactorID.setText("C");
        this.txtSubSubPlotFactorID.setLayoutData((Object)new GridData(4, 0x1000000, true, false, 1, 1));
        this.txtSubSubPlotFactorLevels = new Spinner((Composite)group, 2048);
        this.txtSubSubPlotFactorLevels.setLayoutData((Object)new GridData(4, 0x1000000, false, false, 1, 1));
        this.txtSubSubPlotFactorLevels.setMaximum(500);
        this.txtSubSubPlotFactorLevels.setMinimum(2);
        Composite composite_1 = new Composite((Composite)grpDesignParameters, 0);
        composite_1.setLayout((Layout)new GridLayout(3, false));
        composite_1.setLayoutData((Object)new GridData(4, 0x1000000, true, true, 1, 1));
        Label lblNumberOfRows = new Label(composite_1, 0);
        lblNumberOfRows.setText("Number of Subplots along the Length of Each Mainplot");
        lblNumberOfRows.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        Label lblNewLabel = new Label(composite_1, 0);
        GridData gd_lblNewLabel = new GridData(16384, 0x1000000, false, false, 1, 1);
        gd_lblNewLabel.widthHint = 10;
        lblNewLabel.setLayoutData((Object)gd_lblNewLabel);
        this.txtRows = new Spinner(composite_1, 2048);
        GridData gd_txtRows = new GridData(4, 0x1000000, false, false, 1, 1);
        gd_txtRows.widthHint = 20;
        this.txtRows.setLayoutData((Object)gd_txtRows);
        this.txtRows.setMaximum(500);
        this.txtRows.setMinimum(1);
        Label lblNumberOfRows_1 = new Label(composite_1, 0);
        lblNumberOfRows_1.setText("Number of Sub-subplots along the Length of Each Subplot");
        lblNumberOfRows_1.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        new Label(composite_1, 0);
        this.txtRowsPerSub = new Spinner(composite_1, 2048);
        GridData gd_txtRowsPerSub = new GridData(4, 0x1000000, false, false, 1, 1);
        gd_txtRowsPerSub.widthHint = 20;
        this.txtRowsPerSub.setLayoutData((Object)gd_txtRowsPerSub);
        this.txtRowsPerSub.setMaximum(500);
        this.txtRowsPerSub.setMinimum(1);
        Label lblNumberOfTrials = new Label(composite_1, 0);
        lblNumberOfTrials.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        lblNumberOfTrials.setLayoutData((Object)new GridData(16384, 0x1000000, true, true, 1, 1));
        lblNumberOfTrials.setText("Number of Trials");
        new Label(composite_1, 0);
        this.txtTotalTrials = new Spinner(composite_1, 2048);
        GridData gd_txtTotalTrials = new GridData(4, 0x1000000, true, true, 1, 1);
        gd_txtTotalTrials.widthHint = 20;
        this.txtTotalTrials.setLayoutData((Object)gd_txtTotalTrials);
        this.txtRowsPerSub.setMaximum(100);
        this.txtTotalTrials.setMinimum(1);
        Group group_1 = new Group(container, 0);
        group_1.setLayoutData((Object)new GridData(4, 0x1000000, false, true, 1, 1));
        group_1.setText("Field Book ");
        group_1.setLayout((Layout)new GridLayout(4, false));
        Label label_8 = new Label((Composite)group_1, 0);
        GridData gd_label_8 = new GridData(16384, 0x1000000, true, false, 1, 1);
        gd_label_8.widthHint = 40;
        label_8.setLayoutData((Object)gd_label_8);
        label_8.setText("Name");
        this.txtFileName = new Text((Composite)group_1, 2048);
        this.txtFileName.setText("fieldSplit2LSD");
        GridData gd_txtFileName = new GridData(4, 0x1000000, true, false, 1, 1);
        gd_txtFileName.widthHint = 100;
        this.txtFileName.setLayoutData((Object)gd_txtFileName);
        Label label_10 = new Label((Composite)group_1, 0);
        GridData gd_label_10 = new GridData(131072, 0x1000000, true, false, 1, 1);
        gd_label_10.widthHint = 40;
        label_10.setLayoutData((Object)gd_label_10);
        label_10.setText("Order");
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
            this.txtSubSubPlotFactorName.setText("ssub");
            this.txtMainPlotFactorID.setText("A");
            this.txtSubPlotFactorID.setText("B");
            this.txtSubSubPlotFactorID.setText("C");
            this.txtMainPlotFactorLevels.setSelection(2);
            this.txtSubPlotFactorLevels.setSelection(2);
            this.txtSubSubPlotFactorLevels.setSelection(2);
            this.txtRows.setSelection(1);
            this.txtRowsPerSub.setSelection(1);
            this.txtTotalTrials.setSelection(1);
            this.cmbOrder.setText("Plot Order");
            this.txtFileName.setText("fieldSplit2LSD");
        }
        super.buttonPressed(buttonId);
    }

    protected void okPressed() {
        String outputFile;
        ArrayList<Text> listText = new ArrayList<Text>();
        listText.add(this.txtMainPlotFactorID);
        listText.add(this.txtSubPlotFactorID);
        listText.add(this.txtSubSubPlotFactorID);
        if (!StarRandomizationUtilities.isTextUnique(listText)) {
            MessageDialog.open((int)1, (Shell)this.getShell(), (String)"Error", (String)"Similar variable names detected. Make sure all the variables are unique.", (int)0);
            return;
        }
        if (this.txtMainPlotFactorName.getText().contains(" ") || this.txtSubPlotFactorName.getText().contains(" ")) {
            MessageDialog.open((int)1, (Shell)this.getShell(), (String)"Error", (String)"Factor Name must not contain space", (int)1);
            return;
        }
        if (this.txtSubSubPlotFactorName.getText().contains(" ")) {
            MessageDialog.open((int)1, (Shell)this.getShell(), (String)"Error", (String)"Factor Name must not contain space", (int)1);
            return;
        }
        if (this.txtMainPlotFactorID.getText().contains(" ") || this.txtSubPlotFactorID.getText().contains(" ")) {
            MessageDialog.open((int)1, (Shell)this.getShell(), (String)"Error", (String)"Factor ID must not contain space", (int)1);
            return;
        }
        if (this.txtSubSubPlotFactorID.getText().contains(" ")) {
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
        if (!StarRandomizationUtilities.validateTextFiles(this.txtMainPlotFactorName, this.txtSubPlotFactorName, this.txtSubSubPlotFactorName)) {
            MessageDialog.open((int)1, (Shell)this.getShell(), (String)"Error", (String)"Fields must not be empty", (int)1);
            return;
        }
        if (!StarRandomizationUtilities.validateTextFiles(this.txtMainPlotFactorID, this.txtSubPlotFactorID, this.txtSubSubPlotFactorID)) {
            MessageDialog.open((int)1, (Shell)this.getShell(), (String)"Error", (String)"Fields must not be empty", (int)1);
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
        String subsubfactorID = this.txtSubSubPlotFactorID.getText().toString();
        if (!StarRandomizationUtilities.validateVariableText(subsubfactorID)) {
            MessageDialog.open((int)1, (Shell)this.getShell(), (String)"Error", (String)"Factor ID must start with a letter and must contain:[a-z,A-Z] only.", (int)1);
            return;
        }
        if (this.txtRows.getSelection() > this.txtSubPlotFactorLevels.getSelection()) {
            MessageDialog.open((int)1, (Shell)this.getShell(), (String)"Error", (String)"Number of plots along the length of each main plot should be less than or equal to the levels of the sub plot factor and must divide the levels of the sub plot factor", (int)1);
            return;
        }
        if (this.txtSubPlotFactorLevels.getSelection() % this.txtRows.getSelection() != 0) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"Number of plots along the length of each main plot should divide the levels of sub plot factor.");
            return;
        }
        if (this.txtRowsPerSub.getSelection() > this.txtSubSubPlotFactorLevels.getSelection()) {
            MessageDialog.open((int)1, (Shell)this.getShell(), (String)"Error", (String)"Number of row per sub plot should be less than or equal to the levels of the sub-sub plot factor and must divider the levels of the sub-sub plot factor", (int)1);
            return;
        }
        if (this.txtSubSubPlotFactorLevels.getSelection() % this.txtRowsPerSub.getSelection() != 0) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"Number of plots along the length of each sub plot should divide the levels of sub-sub plot factor.");
            return;
        }
        if (this.txtMainPlotFactorID.getCharCount() > 4) {
            MessageDialog.open((int)1, (Shell)this.getShell(), (String)"Error", (String)"Factor ID must contain not more than four characters. ", (int)1);
            return;
        }
        if (this.txtSubPlotFactorID.getCharCount() > 4) {
            MessageDialog.open((int)1, (Shell)this.getShell(), (String)"Error", (String)"Factor ID must contain not more than four characters. ", (int)1);
            return;
        }
        if (this.txtSubSubPlotFactorID.getCharCount() > 4) {
            MessageDialog.open((int)1, (Shell)this.getShell(), (String)"Error", (String)"Factor ID must contain not more than four characters. ", (int)1);
            return;
        }
        OperationProgressDialog rInfo = new OperationProgressDialog(this.getShell(), "Performing Randomization");
        rInfo.open();
        this.btnOk.setEnabled(false);
        String outputFileTxt = outputFile = StarRandomizationUtilities.createOutputFolder("SplitSplitPlotLSD");
        String outputFileCsv = this.txtFileName.getText();
        String fieldOrder = "Plot Order";
        if (this.cmbOrder.getText().equals("Serpentine")) {
            fieldOrder = "Serpentine";
        }
        Integer[] factorLevels = new Integer[]{this.txtMainPlotFactorLevels.getSelection(), this.txtSubPlotFactorLevels.getSelection(), this.txtSubSubPlotFactorLevels.getSelection()};
        String[] factorID = new String[]{this.txtMainPlotFactorID.getText(), this.txtSubPlotFactorID.getText(), this.txtSubSubPlotFactorID.getText()};
        String design = "lsd";
        ProjectExplorerView.rJavaManager.getSTARDesignManager().doDesignSplit(outputFileTxt.replace(File.separator, "/"), outputFileCsv.replace(File.separator, "/"), this.txtMainPlotFactorName.getText(), this.txtSubPlotFactorName.getText(), this.txtSubSubPlotFactorName.getText(), null, factorID, factorLevels, null, Integer.valueOf(this.txtTotalTrials.getSelection()), design, null, null, Integer.valueOf(this.txtRows.getSelection()), Integer.valueOf(this.txtRowsPerSub.getSelection()), null, fieldOrder);
        StarRandomizationUtilities.testVarArgs(outputFileTxt.replace(File.separator, "/"), outputFileCsv.replace(File.separator, "/"), this.txtMainPlotFactorName.getText(), this.txtSubPlotFactorName.getText(), this.txtSubSubPlotFactorName.getText(), null, factorID, factorLevels, null, this.txtTotalTrials.getSelection(), design, null, null, this.txtRows.getSelection(), this.txtRowsPerSub.getSelection(), null, fieldOrder);
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
        return new Point(413, 450);
    }
}

