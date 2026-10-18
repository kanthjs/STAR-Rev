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
 *  org.eclipse.swt.widgets.Table
 *  org.eclipse.swt.widgets.TableColumn
 *  org.eclipse.swt.widgets.Text
 */
package org.irri.breedingtool.pbtools.randomization.dialog;

import java.io.File;
import java.util.ArrayList;
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
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;
import org.irri.breedingtool.datamanipulation.dialog.OperationProgressDialog;
import org.irri.breedingtool.graphs.managers.GraphTableManager;
import org.irri.breedingtool.graphs.managers.RowEntityModel;
import org.irri.breedingtool.projectexplorer.view.ProjectExplorerView;
import org.irri.breedingtool.utility.StarRandomizationUtilities;

public class RandomizedCompleteBlockDesignDialog
extends Dialog {
    private Spinner txtTotalBlocks;
    private Spinner txtTotalTrials;
    private Text txtFileName;
    private Button btnOk;
    private Spinner txtNumFactors;
    private Spinner txtRowsPerBlk;
    private Spinner txtFieldRows;
    private Combo cmbOrder;
    private Table table;
    private int maxLevel = 2000;
    private GraphTableManager tableManager;
    private RowEntityModel spinnerTableModel;

    public RandomizedCompleteBlockDesignDialog(Shell parentShell) {
        super(parentShell);
        this.setShellStyle(2288);
    }

    protected void configureShell(Shell shell) {
        super.configureShell(shell);
        shell.setText("Randomization and Layout");
    }

    protected Control createDialogArea(Composite parent) {
        Composite container = (Composite)super.createDialogArea(parent);
        Label lblLbldialogtitle = new Label(container, 0);
        lblLbldialogtitle.setFont(SWTResourceManager.getFont("Tahoma", 10, 1));
        lblLbldialogtitle.setLayoutData((Object)new GridData(16384, 0x1000000, true, false, 1, 1));
        lblLbldialogtitle.setText("Randomized Complete Block Design");
        Label label = new Label(container, 258);
        label.setLayoutData((Object)new GridData(4, 0x1000000, true, false, 1, 1));
        Group grpDesignParameters = new Group(container, 0);
        grpDesignParameters.setLayout((Layout)new GridLayout(1, false));
        grpDesignParameters.setLayoutData((Object)new GridData(4, 4, true, true, 1, 1));
        Group grpFactorDefinition = new Group((Composite)grpDesignParameters, 0);
        grpFactorDefinition.setLayout((Layout)new GridLayout(2, false));
        GridData gd_grpFactorDefinition = new GridData(4, 4, true, true, 1, 1);
        gd_grpFactorDefinition.heightHint = 193;
        grpFactorDefinition.setLayoutData((Object)gd_grpFactorDefinition);
        grpFactorDefinition.setText("Factor Definition:");
        Label lblNewLabel = new Label((Composite)grpFactorDefinition, 0);
        lblNewLabel.setText("Number of Factors");
        this.txtNumFactors = new Spinner((Composite)grpFactorDefinition, 2048);
        this.txtNumFactors.setBackground(SWTResourceManager.getColor(1));
        GridData gd_txtNumFactors = new GridData(16384, 0x1000000, true, false, 1, 1);
        gd_txtNumFactors.widthHint = 20;
        this.txtNumFactors.setLayoutData((Object)gd_txtNumFactors);
        this.txtNumFactors.setMaximum(99999);
        this.txtNumFactors.setMinimum(1);
        this.table = new Table((Composite)grpFactorDefinition, 67584);
        this.table.setLinesVisible(true);
        this.table.setHeaderVisible(true);
        this.table.setLayoutData((Object)new GridData(4, 4, true, true, 2, 1));
        TableColumn tableColumn = new TableColumn(this.table, 0);
        tableColumn.setWidth(100);
        tableColumn.setText("Name");
        TableColumn tableColumn_1 = new TableColumn(this.table, 0);
        tableColumn_1.setWidth(100);
        tableColumn_1.setText("Factor ID");
        TableColumn tableColumn_2 = new TableColumn(this.table, 0);
        tableColumn_2.setWidth(100);
        tableColumn_2.setText("Levels");
        ArrayList<Integer> tableHeaderIdentity = new ArrayList<Integer>();
        tableHeaderIdentity.add(1);
        tableHeaderIdentity.add(1);
        tableHeaderIdentity.add(5);
        this.spinnerTableModel = new RowEntityModel(5, (Object)2, 2, 99999, new ModifyListener(){

            public void modifyText(ModifyEvent e) {
                if (((Spinner)e.getSource()).getSelection() > RandomizedCompleteBlockDesignDialog.this.maxLevel) {
                    MessageDialog.openError((Shell)RandomizedCompleteBlockDesignDialog.this.getShell(), (String)"Error", (String)("The maximum allowable number of levels is " + RandomizedCompleteBlockDesignDialog.this.maxLevel + " "));
                    ((Spinner)e.getSource()).setSelection(RandomizedCompleteBlockDesignDialog.this.maxLevel);
                }
            }
        });
        this.tableManager = new GraphTableManager(this.table, tableHeaderIdentity);
        this.tableManager.addItem(new Object[]{"FactorA", "A", this.spinnerTableModel});
        this.txtNumFactors.addModifyListener(new ModifyListener(){

            public void modifyText(ModifyEvent e) {
                if (RandomizedCompleteBlockDesignDialog.this.txtNumFactors.getSelection() < 2) {
                    RandomizedCompleteBlockDesignDialog.this.maxLevel = 2000;
                } else if (RandomizedCompleteBlockDesignDialog.this.txtNumFactors.getSelection() <= 10) {
                    RandomizedCompleteBlockDesignDialog.this.maxLevel = 500;
                } else {
                    MessageDialog.openError((Shell)RandomizedCompleteBlockDesignDialog.this.getShell(), (String)"Error", (String)"The maximum allowable number of factors is 10.");
                    RandomizedCompleteBlockDesignDialog.this.txtNumFactors.setSelection(10);
                }
                RandomizedCompleteBlockDesignDialog.this.setTableRows(RandomizedCompleteBlockDesignDialog.this.table, RandomizedCompleteBlockDesignDialog.this.txtNumFactors.getSelection());
            }
        });
        Composite composite_1 = new Composite((Composite)grpDesignParameters, 0);
        composite_1.setLayout((Layout)new GridLayout(3, false));
        composite_1.setLayoutData((Object)new GridData(4, 0x1000000, true, true, 1, 1));
        Label lblNumberOfReplicates = new Label(composite_1, 0);
        lblNumberOfReplicates.setLayoutData((Object)new GridData(16384, 0x1000000, true, false, 1, 1));
        lblNumberOfReplicates.setText("Number of Blocks");
        Label lblNewLabel_2 = new Label(composite_1, 0);
        GridData gd_lblNewLabel_2 = new GridData(16384, 0x1000000, false, false, 1, 1);
        gd_lblNewLabel_2.widthHint = 80;
        lblNewLabel_2.setLayoutData((Object)gd_lblNewLabel_2);
        this.txtTotalBlocks = new Spinner(composite_1, 2048);
        GridData gd_txtTotalBlocks = new GridData(4, 0x1000000, true, false, 1, 1);
        gd_txtTotalBlocks.widthHint = 20;
        this.txtTotalBlocks.setLayoutData((Object)gd_txtTotalBlocks);
        this.txtTotalBlocks.setMaximum(500);
        this.txtTotalBlocks.setSelection(2);
        Label lblNumberOfRows = new Label(composite_1, 0);
        lblNumberOfRows.setText("Number of Rows Per Block");
        new Label(composite_1, 0);
        this.txtRowsPerBlk = new Spinner(composite_1, 2048);
        GridData gd_txtRowsPerBlk = new GridData(4, 0x1000000, false, false, 1, 1);
        gd_txtRowsPerBlk.widthHint = 20;
        this.txtRowsPerBlk.setLayoutData((Object)gd_txtRowsPerBlk);
        this.txtRowsPerBlk.setMaximum(500);
        this.txtRowsPerBlk.setMinimum(1);
        Label lblNumberOfField = new Label(composite_1, 0);
        lblNumberOfField.setText("Number of Field Rows");
        new Label(composite_1, 0);
        this.txtFieldRows = new Spinner(composite_1, 2048);
        GridData gd_txtFieldRows = new GridData(4, 0x1000000, false, false, 1, 1);
        gd_txtFieldRows.widthHint = 20;
        this.txtFieldRows.setLayoutData((Object)gd_txtFieldRows);
        this.txtFieldRows.setMaximum(500);
        this.txtFieldRows.setMinimum(1);
        Label lblNumberOfTrials = new Label(composite_1, 0);
        lblNumberOfTrials.setLayoutData((Object)new GridData(16384, 0x1000000, true, false, 1, 1));
        lblNumberOfTrials.setText("Number of Trials");
        new Label(composite_1, 0);
        this.txtTotalTrials = new Spinner(composite_1, 2048);
        GridData gd_txtTotalTrials = new GridData(4, 0x1000000, true, false, 1, 1);
        gd_txtTotalTrials.widthHint = 20;
        this.txtTotalTrials.setLayoutData((Object)gd_txtTotalTrials);
        this.txtTotalTrials.setMaximum(100);
        this.txtTotalTrials.setMinimum(1);
        Group grpFieldBookFilename = new Group(container, 0);
        grpFieldBookFilename.setLayout((Layout)new GridLayout(4, false));
        GridData gd_grpFieldBookFilename = new GridData(4, 128, true, true, 1, 1);
        gd_grpFieldBookFilename.heightHint = 30;
        grpFieldBookFilename.setLayoutData((Object)gd_grpFieldBookFilename);
        grpFieldBookFilename.setText("Field Book ");
        Label label_1 = new Label((Composite)grpFieldBookFilename, 0);
        label_1.setText("Name");
        GridData gd_label_1 = new GridData(16384, 0x1000000, true, false, 1, 1);
        gd_label_1.widthHint = 40;
        label_1.setLayoutData((Object)gd_label_1);
        this.txtFileName = new Text((Composite)grpFieldBookFilename, 2048);
        this.txtFileName.setText("fieldbookRCBD");
        GridData gd_txtFileName = new GridData(16384, 0x1000000, true, false, 1, 1);
        gd_txtFileName.widthHint = 100;
        this.txtFileName.setLayoutData((Object)gd_txtFileName);
        Label label_2 = new Label((Composite)grpFieldBookFilename, 0);
        GridData gd_label_2 = new GridData(131072, 0x1000000, true, false, 1, 1);
        gd_label_2.widthHint = 40;
        label_2.setLayoutData((Object)gd_label_2);
        label_2.setText("Order");
        this.cmbOrder = new Combo((Composite)grpFieldBookFilename, 8);
        this.cmbOrder.setItems(new String[]{"Plot Order", "Serpentine"});
        GridData gd_cmbOrder = new GridData(4, 0x1000000, true, false, 1, 1);
        gd_cmbOrder.widthHint = 70;
        this.cmbOrder.setLayoutData((Object)gd_cmbOrder);
        this.cmbOrder.select(0);
        return container;
    }

    protected void buttonPressed(int buttonId) {
        if (buttonId == 8) {
            this.tableManager.removeAll();
            this.spinnerTableModel = new RowEntityModel(5, (Object)2, 2, 99999, new ModifyListener(){

                public void modifyText(ModifyEvent e) {
                    if (((Spinner)e.getSource()).getSelection() > RandomizedCompleteBlockDesignDialog.this.maxLevel) {
                        MessageDialog.openError((Shell)RandomizedCompleteBlockDesignDialog.this.getShell(), (String)"Error", (String)("The maximum allowable number of levels is " + RandomizedCompleteBlockDesignDialog.this.maxLevel + " "));
                        ((Spinner)e.getSource()).setSelection(RandomizedCompleteBlockDesignDialog.this.maxLevel);
                    }
                }
            });
            this.txtNumFactors.setSelection(1);
            this.txtTotalBlocks.setSelection(2);
            this.txtTotalTrials.setSelection(1);
            this.txtRowsPerBlk.setSelection(1);
            this.txtFieldRows.setSelection(1);
            this.cmbOrder.setText("Plot Order");
            this.txtFileName.setText("fieldbookRCBD");
        }
        super.buttonPressed(buttonId);
    }

    protected void okPressed() {
        String outputFile;
        if (this.txtTotalBlocks.getSelection() < 2) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"The minimum value of the number of blocks is equal to 2.");
            this.txtTotalBlocks.setSelection(2);
            return;
        }
        if (this.txtFileName.getText().equals("")) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"Field Filename must not be empty.");
            return;
        }
        ArrayList<String> tableNames = new ArrayList<String>();
        ArrayList<String> tableID = new ArrayList<String>();
        ArrayList<Integer> tableLevels = new ArrayList<Integer>();
        long plot = 1L;
        long perBlk = 1L;
        for (String[] tableRow : this.tableManager.getDataToString()) {
            boolean isValidFactorID = StarRandomizationUtilities.validateVariableText(tableRow[1]);
            if (tableRow[0].contains(" ")) {
                MessageDialog.open((int)1, (Shell)this.getShell(), (String)"Error", (String)"Factor Name must not contain space. ", (int)0);
                return;
            }
            if (tableRow[1].contains(" ")) {
                MessageDialog.open((int)1, (Shell)this.getShell(), (String)"Error", (String)"Factor ID must not contain space. ", (int)0);
                return;
            }
            if (!isValidFactorID) {
                MessageDialog.open((int)1, (Shell)this.getShell(), (String)"Error", (String)"Factor ID must start with a letter and must contain:[a-z,A-Z] only. ", (int)0);
                return;
            }
            if (tableRow[0].equals("") || tableRow[1].equals("")) {
                MessageDialog.open((int)1, (Shell)this.getShell(), (String)"Error", (String)"All name fields must not be empty", (int)0);
                return;
            }
            if (tableNames.contains(tableRow[0]) || tableNames.contains(tableRow[1])) {
                MessageDialog.open((int)1, (Shell)this.getShell(), (String)"Error", (String)"Similar variable names detected. Make sure all the variables are unique.", (int)0);
                return;
            }
            if (tableID.contains(tableRow[0]) || tableID.contains(tableRow[1])) {
                MessageDialog.open((int)1, (Shell)this.getShell(), (String)"Error", (String)"Similar variable names detected. Make sure all the variables are unique.", (int)0);
                return;
            }
            if (tableRow[1].length() > 4) {
                MessageDialog.open((int)1, (Shell)this.getShell(), (String)"Error", (String)"Factor ID must contain not more than four characters. ", (int)1);
                return;
            }
            tableNames.add(tableRow[0]);
            tableID.add(tableRow[1]);
            tableLevels.add(Integer.parseInt(tableRow[2]));
            plot *= Long.valueOf(tableRow[2]).longValue();
            perBlk *= Long.valueOf(tableRow[2]).longValue();
        }
        plot = (long)this.txtTotalBlocks.getSelection() * plot;
        if (plot % (long)this.txtFieldRows.getSelection() != 0L) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)("The total number of plots (" + plot + ") must be divisible by the number of field rows."));
            return;
        }
        if (perBlk % (long)this.txtRowsPerBlk.getSelection() != 0L) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)("The total number of treatment levels (" + perBlk + ") must be divisible by the number of rows per block."));
            return;
        }
        if (this.txtFieldRows.getSelection() < this.txtRowsPerBlk.getSelection()) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"The number of field rows must be equal or greater than the number of rows per block.");
            return;
        }
        if (this.txtFieldRows.getSelection() % this.txtRowsPerBlk.getSelection() != 0) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"The number of field rows must be divisible by the number of rows per block.");
            return;
        }
        this.btnOk.setEnabled(false);
        OperationProgressDialog rInfo = new OperationProgressDialog(this.getShell(), "Performing Randomization");
        rInfo.open();
        String outputFileTxt = outputFile = StarRandomizationUtilities.createOutputFolder("RCBD");
        String outputFileCsv = this.txtFileName.getText();
        String fieldOrder = "Plot Order";
        if (this.cmbOrder.getText().equals("Serpentine")) {
            fieldOrder = "Serpentine";
        }
        ProjectExplorerView.rJavaManager.getPbToolRandomizationManager().doDesignRCBD(outputFileTxt.replace(File.separator, "/"), outputFileCsv.replace(File.separator, "/"), tableNames.toArray(new String[tableNames.size()]), tableID.toArray(new String[tableID.size()]), tableLevels.toArray(new Integer[tableNames.size()]), Integer.valueOf(this.txtTotalBlocks.getSelection()), Integer.valueOf(this.txtTotalTrials.getSelection()), Integer.valueOf(this.txtFieldRows.getSelection()), Integer.valueOf(this.txtRowsPerBlk.getSelection()), fieldOrder);
        StarRandomizationUtilities.testVarArgs(outputFileTxt.replace(File.separator, "/"), outputFileCsv.replace(File.separator, "/"), tableNames.toArray(new String[tableNames.size()]), tableID.toArray(new String[tableID.size()]), tableLevels.toArray(new Integer[tableNames.size()]), this.txtTotalBlocks.getSelection(), this.txtTotalTrials.getSelection(), this.txtFieldRows.getSelection(), this.txtRowsPerBlk.getSelection(), fieldOrder);
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
        return new Point(367, 545);
    }

    private void setTableRows(Table dialogTable, int levels) {
        block4: {
            int tableCount;
            block3: {
                int i = 0;
                while (i < this.table.getItemCount()) {
                    Spinner spinner = (Spinner)this.tableManager.getRowObjects(i)[2];
                    spinner.setMaximum(99999);
                    ++i;
                }
                this.spinnerTableModel = new RowEntityModel(5, (Object)2, 2, 99999, new ModifyListener(){

                    public void modifyText(ModifyEvent e) {
                        System.out.println(String.valueOf(((Spinner)e.getSource()).getSelection()) + " SELECTION SPINNER");
                        if (((Spinner)e.getSource()).getSelection() > RandomizedCompleteBlockDesignDialog.this.maxLevel) {
                            MessageDialog.openError((Shell)RandomizedCompleteBlockDesignDialog.this.getShell(), (String)"Error", (String)("The maximum allowable number of levels is " + RandomizedCompleteBlockDesignDialog.this.maxLevel + " "));
                            ((Spinner)e.getSource()).setSelection(RandomizedCompleteBlockDesignDialog.this.maxLevel);
                        }
                    }
                });
                tableCount = this.tableManager.getTableRowsObject().size();
                if (levels <= tableCount) break block3;
                int i2 = tableCount;
                while (i2 < levels) {
                    this.tableManager.addItem(new Object[]{"Factor" + (char)(65 + i2), String.valueOf((char)(65 + i2)), this.spinnerTableModel});
                    ++i2;
                }
                break block4;
            }
            if (levels >= tableCount) break block4;
            int i = tableCount;
            while (i > levels) {
                this.tableManager.deleteRow(i - 1);
                --i;
            }
        }
    }
}

