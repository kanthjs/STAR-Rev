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

public class StripSplitSplitPlotDesignDialog
extends Dialog {
    private Text txtVerticalFactorName;
    private Text txtVerticalFactorID;
    private Text txtHorizontalFactorName;
    private Text txtHorizontalFactorID;
    private Text txtSubPlotFactorName;
    private Text txtSubPlotFactorID;
    private Text txtSubSubPlotFactorName;
    private Text txtSubSubPlotFactorID;
    private Spinner txtVerticalFactorLevels;
    private Spinner txtHorizontalFactorLevels;
    private Spinner txtSubPlotFactorLevels;
    private Spinner txtSubSubPlotFactorLevels;
    private Spinner txtTotalBlocks;
    private Spinner txtTotalTrials;
    private Text txtFileName;
    private Spinner txtRows;
    private Spinner txtRowsPerSub;
    private Spinner txtFieldRows;
    private Combo cmbOrder;
    private Button btnOk;

    public StripSplitSplitPlotDesignDialog(Shell parentShell) {
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
        lblFactorialDesign.setText("Strip-Split-Split Plot Design");
        Label label = new Label(container, 258);
        label.setLayoutData((Object)new GridData(4, 0x1000000, false, false, 1, 1));
        Group grpDesignParameters = new Group(container, 0);
        grpDesignParameters.setLayout((Layout)new GridLayout(1, false));
        GridData gd_grpDesignParameters = new GridData(4, 4, true, true, 1, 1);
        gd_grpDesignParameters.heightHint = 299;
        grpDesignParameters.setLayoutData((Object)gd_grpDesignParameters);
        Group group = new Group((Composite)grpDesignParameters, 0);
        group.setLayoutData((Object)new GridData(4, 0x1000000, true, true, 1, 1));
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
        Label label_5 = new Label((Composite)group, 0);
        label_5.setLayoutData((Object)new GridData(16384, 0x1000000, true, false, 1, 1));
        label_5.setText("Vertical ");
        label_5.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        this.txtVerticalFactorName = new Text((Composite)group, 2048);
        this.txtVerticalFactorName.setText("vertical");
        GridData gd_txtVerticalFactorName = new GridData(4, 4, false, false, 1, 1);
        gd_txtVerticalFactorName.widthHint = 80;
        this.txtVerticalFactorName.setLayoutData((Object)gd_txtVerticalFactorName);
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
        Label label_6 = new Label((Composite)group, 0);
        label_6.setLayoutData((Object)new GridData(16384, 0x1000000, true, false, 1, 1));
        label_6.setText("Horizontal");
        label_6.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        this.txtHorizontalFactorName = new Text((Composite)group, 2048);
        this.txtHorizontalFactorName.setText("horizontal");
        GridData gd_txtHorizontalFactorName = new GridData(4, 0x1000000, false, false, 1, 1);
        gd_txtHorizontalFactorName.widthHint = 80;
        this.txtHorizontalFactorName.setLayoutData((Object)gd_txtHorizontalFactorName);
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
        Label lblSubsubPlot = new Label((Composite)group, 0);
        lblSubsubPlot.setLayoutData((Object)new GridData(16384, 0x1000000, true, false, 1, 1));
        lblSubsubPlot.setText("Sub-subplot");
        lblSubsubPlot.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        this.txtSubSubPlotFactorName = new Text((Composite)group, 2048);
        this.txtSubSubPlotFactorName.setText("ssub");
        GridData gd_txtSubSubPlotFactorName = new GridData(4, 0x1000000, true, false, 1, 1);
        gd_txtSubSubPlotFactorName.widthHint = 80;
        this.txtSubSubPlotFactorName.setLayoutData((Object)gd_txtSubSubPlotFactorName);
        this.txtSubSubPlotFactorID = new Text((Composite)group, 2048);
        this.txtSubSubPlotFactorID.setText("D");
        GridData gd_txtSubSubPlotFactorID = new GridData(4, 0x1000000, true, false, 1, 1);
        gd_txtSubSubPlotFactorID.widthHint = 50;
        this.txtSubSubPlotFactorID.setLayoutData((Object)gd_txtSubSubPlotFactorID);
        this.txtSubSubPlotFactorLevels = new Spinner((Composite)group, 2048);
        GridData gd_txtSubSubPlotFactorLevels = new GridData(4, 0x1000000, true, false, 1, 1);
        gd_txtSubSubPlotFactorLevels.widthHint = 20;
        this.txtSubSubPlotFactorLevels.setLayoutData((Object)gd_txtSubSubPlotFactorLevels);
        this.txtSubSubPlotFactorLevels.setMaximum(500);
        this.txtSubSubPlotFactorLevels.setMinimum(2);
        Composite composite = new Composite((Composite)grpDesignParameters, 0);
        GridData gd_composite = new GridData(4, 0x1000000, true, true, 1, 1);
        gd_composite.widthHint = 60;
        composite.setLayoutData((Object)gd_composite);
        composite.setLayout((Layout)new GridLayout(3, false));
        Label lblNumberOfBlocks = new Label(composite, 0);
        lblNumberOfBlocks.setLayoutData((Object)new GridData(4, 0x1000000, false, true, 1, 1));
        lblNumberOfBlocks.setText("Number of Blocks");
        lblNumberOfBlocks.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        Label label_9 = new Label(composite, 0);
        GridData gd_label_9 = new GridData(16384, 0x1000000, false, false, 1, 1);
        gd_label_9.widthHint = 10;
        label_9.setLayoutData((Object)gd_label_9);
        this.txtTotalBlocks = new Spinner(composite, 2048);
        GridData gd_txtTotalBlocks = new GridData(4, 0x1000000, true, true, 1, 1);
        gd_txtTotalBlocks.widthHint = 20;
        this.txtTotalBlocks.setLayoutData((Object)gd_txtTotalBlocks);
        this.txtTotalBlocks.setMaximum(500);
        this.txtTotalBlocks.setMinimum(2);
        Label lblNumberOfPlots = new Label(composite, 0);
        lblNumberOfPlots.setLayoutData((Object)new GridData(4, 0x1000000, false, false, 1, 1));
        lblNumberOfPlots.setText("Number of Subplots along the Length of Each VxH plot");
        lblNumberOfPlots.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        Label label_11 = new Label(composite, 0);
        GridData gd_label_11 = new GridData(16384, 0x1000000, false, false, 1, 1);
        gd_label_11.widthHint = 10;
        label_11.setLayoutData((Object)gd_label_11);
        this.txtRows = new Spinner(composite, 2048);
        GridData gd_txtRows = new GridData(4, 0x1000000, false, false, 1, 1);
        gd_txtRows.widthHint = 20;
        this.txtRows.setLayoutData((Object)gd_txtRows);
        this.txtRows.setMaximum(500);
        this.txtRows.setMinimum(1);
        Label lblNumberOfRows = new Label(composite, 0);
        lblNumberOfRows.setText("Number of Sub-subplots along the Length of Each Subplot");
        lblNumberOfRows.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        new Label(composite, 0);
        this.txtRowsPerSub = new Spinner(composite, 2048);
        GridData gd_txtRowsPerSub = new GridData(4, 0x1000000, false, false, 1, 1);
        gd_txtRowsPerSub.widthHint = 20;
        this.txtRowsPerSub.setLayoutData((Object)gd_txtRowsPerSub);
        this.txtRowsPerSub.setMaximum(500);
        this.txtRowsPerSub.setMinimum(1);
        Label label_12 = new Label(composite, 0);
        label_12.setLayoutData((Object)new GridData(4, 0x1000000, false, false, 1, 1));
        label_12.setText("Number of Field Rows");
        label_12.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        Label label_13 = new Label(composite, 0);
        GridData gd_label_13 = new GridData(16384, 0x1000000, false, false, 1, 1);
        gd_label_13.widthHint = 10;
        label_13.setLayoutData((Object)gd_label_13);
        this.txtFieldRows = new Spinner(composite, 2048);
        GridData gd_txtFieldRows = new GridData(4, 0x1000000, false, false, 1, 1);
        gd_txtFieldRows.widthHint = 20;
        this.txtFieldRows.setLayoutData((Object)gd_txtFieldRows);
        this.txtFieldRows.setMaximum(500);
        this.txtFieldRows.setMinimum(2);
        Label label_14 = new Label(composite, 0);
        label_14.setLayoutData((Object)new GridData(4, 0x1000000, false, true, 1, 1));
        label_14.setText("Number of Trials");
        label_14.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        Label label_15 = new Label(composite, 0);
        GridData gd_label_15 = new GridData(16384, 0x1000000, false, false, 1, 1);
        gd_label_15.widthHint = 10;
        label_15.setLayoutData((Object)gd_label_15);
        this.txtTotalTrials = new Spinner(composite, 2048);
        GridData gd_txtTotalTrials = new GridData(4, 0x1000000, true, true, 1, 1);
        gd_txtTotalTrials.widthHint = 20;
        this.txtTotalTrials.setLayoutData((Object)gd_txtTotalTrials);
        this.txtTotalTrials.setMaximum(100);
        this.txtTotalTrials.setMinimum(1);
        Group group_1 = new Group(container, 0);
        group_1.setLayoutData((Object)new GridData(4, 0x1000000, false, true, 1, 1));
        group_1.setText("Field Book ");
        group_1.setLayout((Layout)new GridLayout(4, false));
        Label label_16 = new Label((Composite)group_1, 0);
        GridData gd_label_16 = new GridData(16384, 0x1000000, true, false, 1, 1);
        gd_label_16.widthHint = 40;
        label_16.setLayoutData((Object)gd_label_16);
        label_16.setText("Name");
        this.txtFileName = new Text((Composite)group_1, 2048);
        this.txtFileName.setText("fieldbookStripSplit2");
        GridData gd_txtFileName = new GridData(4, 0x1000000, true, false, 1, 1);
        gd_txtFileName.widthHint = 100;
        this.txtFileName.setLayoutData((Object)gd_txtFileName);
        Label label_18 = new Label((Composite)group_1, 0);
        GridData gd_label_18 = new GridData(131072, 0x1000000, true, false, 1, 1);
        gd_label_18.widthHint = 40;
        label_18.setLayoutData((Object)gd_label_18);
        label_18.setText("Order");
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
            this.txtVerticalFactorName.setText("vertical");
            this.txtHorizontalFactorName.setText("horizontal");
            this.txtSubPlotFactorName.setText("sub");
            this.txtSubSubPlotFactorName.setText("ssub");
            this.txtVerticalFactorID.setText("A");
            this.txtHorizontalFactorID.setText("B");
            this.txtSubPlotFactorID.setText("C");
            this.txtSubSubPlotFactorID.setText("D");
            this.txtVerticalFactorLevels.setSelection(2);
            this.txtHorizontalFactorLevels.setSelection(2);
            this.txtSubPlotFactorLevels.setSelection(2);
            this.txtSubSubPlotFactorLevels.setSelection(2);
            this.txtTotalBlocks.setSelection(2);
            this.txtRows.setSelection(1);
            this.txtRowsPerSub.setSelection(1);
            this.txtTotalTrials.setSelection(1);
            this.txtFieldRows.setSelection(2);
            this.cmbOrder.setText("Plot Order");
            this.txtFileName.setText("fieldbookStripSplit2");
        }
        super.buttonPressed(buttonId);
    }

    protected void okPressed() {
        String outputFile;
        ArrayList<Text> listText = new ArrayList<Text>();
        listText.add(this.txtVerticalFactorID);
        listText.add(this.txtHorizontalFactorID);
        listText.add(this.txtSubPlotFactorID);
        listText.add(this.txtSubSubPlotFactorID);
        if (!StarRandomizationUtilities.isTextUnique(listText)) {
            MessageDialog.open((int)1, (Shell)this.getShell(), (String)"Error", (String)"Similar variable names detected. Make sure all the variables are unique.", (int)0);
            return;
        }
        if (this.txtVerticalFactorName.getText().contains(" ") || this.txtHorizontalFactorName.getText().contains(" ")) {
            MessageDialog.open((int)1, (Shell)this.getShell(), (String)"Error", (String)"Factor Name must not contain space", (int)1);
            return;
        }
        if (this.txtSubPlotFactorName.getText().contains(" ") || this.txtSubSubPlotFactorName.getText().contains(" ")) {
            MessageDialog.open((int)1, (Shell)this.getShell(), (String)"Error", (String)"Factor Name must not contain space", (int)1);
            return;
        }
        if (this.txtVerticalFactorID.getText().contains(" ") || this.txtHorizontalFactorID.getText().contains(" ")) {
            MessageDialog.open((int)1, (Shell)this.getShell(), (String)"Error", (String)"Factor ID must not contain space", (int)1);
            return;
        }
        if (this.txtSubPlotFactorID.getText().contains(" ") || this.txtSubSubPlotFactorID.getText().contains(" ")) {
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
        if (!StarRandomizationUtilities.validateTextFiles(this.txtVerticalFactorName, this.txtHorizontalFactorName, this.txtSubPlotFactorName, this.txtSubSubPlotFactorName)) {
            MessageDialog.open((int)1, (Shell)this.getShell(), (String)"Error", (String)"Fields must not be empty", (int)1);
            return;
        }
        if (!StarRandomizationUtilities.validateTextFiles(this.txtVerticalFactorID, this.txtHorizontalFactorID, this.txtSubPlotFactorID, this.txtSubSubPlotFactorID)) {
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
        String subsubsubfactorID = this.txtSubSubPlotFactorID.getText().toString();
        if (!StarRandomizationUtilities.validateVariableText(subsubsubfactorID)) {
            MessageDialog.open((int)1, (Shell)this.getShell(), (String)"Error", (String)"Factor ID must start with a letter and must contain:[a-z,A-Z] only.", (int)1);
            return;
        }
        int plot = this.txtTotalBlocks.getSelection() * this.txtVerticalFactorLevels.getSelection() * this.txtHorizontalFactorLevels.getSelection() * this.txtSubPlotFactorLevels.getSelection() * this.txtSubSubPlotFactorLevels.getSelection();
        if (plot % this.txtFieldRows.getSelection() != 0) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"Number of field rows must divide number of plots.");
            return;
        }
        if (this.txtFieldRows.getSelection() % (this.txtHorizontalFactorLevels.getSelection() * this.txtRows.getSelection() * this.txtRowsPerSub.getSelection()) != 0) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"Number of field rows must be divisible by the product of the levels of the horizontal, number of plots along the length of each VxH plot and number of plots along the length of each sub plot.");
            return;
        }
        if (this.txtRows.getSelection() > this.txtSubPlotFactorLevels.getSelection()) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"Number of plots along the length of each VxH plot should divide the levels of the sub plot factor.");
            return;
        }
        if (this.txtRowsPerSub.getSelection() > this.txtSubSubPlotFactorLevels.getSelection()) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"Number of plots along the length of each sub plot should divide the levels of the sub-sub plot factor.");
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
        if (this.txtSubSubPlotFactorID.getCharCount() > 4) {
            MessageDialog.open((int)1, (Shell)this.getShell(), (String)"Error", (String)"Factor ID must contain not more than four characters. ", (int)1);
            return;
        }
        OperationProgressDialog rInfo = new OperationProgressDialog(this.getShell(), "Performing Randomization");
        rInfo.open();
        this.btnOk.setEnabled(false);
        String outputFileTxt = outputFile = StarRandomizationUtilities.createOutputFolder("StripSplitSplitPlot");
        String outputFileCsv = this.txtFileName.getText();
        String fieldOrder = "Plot Order";
        if (this.cmbOrder.getText().equals("Serpentine")) {
            fieldOrder = "Serpentine";
        }
        Integer[] factorLevels = new Integer[]{this.txtVerticalFactorLevels.getSelection(), this.txtHorizontalFactorLevels.getSelection(), this.txtSubPlotFactorLevels.getSelection(), this.txtSubSubPlotFactorLevels.getSelection()};
        String[] factorID = new String[]{this.txtVerticalFactorID.getText(), this.txtHorizontalFactorID.getText(), this.txtSubPlotFactorID.getText(), this.txtSubSubPlotFactorID.getText()};
        ProjectExplorerView.rJavaManager.getSTARDesignManager().doDesignStrip(outputFileTxt.replace(File.separator, "/"), outputFileCsv.replace(File.separator, "/"), this.txtVerticalFactorName.getText(), this.txtHorizontalFactorName.getText(), this.txtSubPlotFactorName.getText(), this.txtSubSubPlotFactorName.getText(), factorID, factorLevels, Integer.valueOf(this.txtTotalBlocks.getSelection()), Integer.valueOf(this.txtTotalTrials.getSelection()), Integer.valueOf(this.txtFieldRows.getSelection()), Integer.valueOf(this.txtRows.getSelection()), Integer.valueOf(this.txtRowsPerSub.getSelection()), fieldOrder);
        StarRandomizationUtilities.testVarArgs(outputFileTxt.replace(File.separator, "/"), outputFileCsv.replace(File.separator, "/"), this.txtVerticalFactorName.getText(), this.txtHorizontalFactorName.getText(), this.txtSubPlotFactorName.getText(), this.txtSubSubPlotFactorName.getText(), factorID, factorLevels, this.txtTotalBlocks.getSelection(), this.txtTotalTrials.getSelection(), this.txtFieldRows.getSelection(), this.txtRows.getSelection(), this.txtRowsPerSub.getSelection(), fieldOrder);
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
        return new Point(414, 521);
    }
}

