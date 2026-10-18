/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.jface.dialogs.Dialog
 *  org.eclipse.jface.dialogs.IDialogConstants
 *  org.eclipse.jface.dialogs.MessageDialog
 *  org.eclipse.swt.events.SelectionAdapter
 *  org.eclipse.swt.events.SelectionEvent
 *  org.eclipse.swt.events.SelectionListener
 *  org.eclipse.swt.graphics.Point
 *  org.eclipse.swt.layout.FillLayout
 *  org.eclipse.swt.layout.GridData
 *  org.eclipse.swt.layout.GridLayout
 *  org.eclipse.swt.widgets.Button
 *  org.eclipse.swt.widgets.Composite
 *  org.eclipse.swt.widgets.Control
 *  org.eclipse.swt.widgets.Label
 *  org.eclipse.swt.widgets.Layout
 *  org.eclipse.swt.widgets.Shell
 *  org.eclipse.swt.widgets.Table
 *  org.eclipse.swt.widgets.TableColumn
 *  org.eclipse.swt.widgets.TableItem
 *  org.eclipse.swt.widgets.Text
 */
package org.irri.breedingtool.star.analysis.dialog;

import java.util.ArrayList;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.irri.breedingtool.application.handler.PartStackHandler;
import org.irri.breedingtool.graphs.managers.GraphTableManager;
import org.irri.breedingtool.manager.impl.DataManipulationManager;
import org.irri.breedingtool.utility.DialogFormUtility;

public class NonLinearRegressionAnalysisParameters
extends Dialog {
    private Button btnOk;
    private String filePath = PartStackHandler.getActiveElementID();
    private DataManipulationManager dataManipulationManager = new DataManipulationManager();
    private GraphTableManager tableManager;
    private DialogFormUtility listManager = new DialogFormUtility();
    private ArrayList<String> tableData = new ArrayList();
    private Composite composite;
    private Label lblStartingValue;
    private Button btnAdd;
    private TableColumn tableName;
    private TableColumn tableStartValue;
    private Label lblName;
    private Label lblSummary;
    private Button btnEdit;
    private Button btnDelete;
    private Label lblNewLabel;
    private Label label;
    private Label lblNewLabel_1;
    private String returnStartVal;
    public ArrayList<ParameterModel> parameterList = new ArrayList();
    private String returnVal;
    private Table table;
    private Text txtName;
    private Text txtStartingValue;

    public ArrayList<ParameterModel> getParameterList() {
        System.out.println(this.parameterList.isEmpty());
        return this.parameterList;
    }

    public void reloadParameterList() {
        this.parameterList.clear();
        System.out.println("it: " + this.table.getItemCount());
        int i = 0;
        while (i < this.table.getItemCount()) {
            this.parameterList.add(new ParameterModel(this.table.getItem(i).getText(0), this.table.getItem(i).getText(1)));
            System.out.println("i: 0");
            ++i;
        }
    }

    public void setParameterList(ArrayList<ParameterModel> parameterList) {
        this.parameterList = parameterList;
    }

    public String getReturnVal() {
        return this.returnVal;
    }

    public void setReturnVal(String returnVal) {
        this.returnVal = returnVal;
    }

    public NonLinearRegressionAnalysisParameters(Shell parentShell, ArrayList<ParameterModel> parameterList) {
        super(parentShell);
        this.setShellStyle(133360);
        this.parameterList = parameterList;
    }

    protected void configureShell(Shell shell) {
        super.configureShell(shell);
        shell.setText("Nonlinear Regression: Parameters");
    }

    protected Control createDialogArea(Composite parent) {
        Composite container = (Composite)super.createDialogArea(parent);
        FillLayout fl_container = new FillLayout(256);
        fl_container.marginHeight = 8;
        fl_container.marginWidth = 8;
        container.setLayout((Layout)fl_container);
        this.composite = new Composite(container, 0);
        this.composite.setLayout((Layout)new GridLayout(6, false));
        this.lblNewLabel = new Label(this.composite, 0);
        GridData gd_lblNewLabel = new GridData(16384, 0x1000000, false, false, 1, 1);
        gd_lblNewLabel.widthHint = 10;
        this.lblNewLabel.setLayoutData((Object)gd_lblNewLabel);
        this.lblName = new Label(this.composite, 0);
        this.lblName.setLayoutData((Object)new GridData(16384, 0x1000000, true, false, 1, 1));
        this.lblName.setText("Name:");
        this.txtName = new Text(this.composite, 2048);
        this.txtName.setLayoutData((Object)new GridData(4, 0x1000000, true, false, 3, 1));
        this.label = new Label(this.composite, 0);
        GridData gd_label = new GridData(16384, 0x1000000, false, false, 1, 1);
        gd_label.widthHint = 10;
        this.label.setLayoutData((Object)gd_label);
        new Label(this.composite, 0);
        this.lblStartingValue = new Label(this.composite, 0);
        this.lblStartingValue.setLayoutData((Object)new GridData(16384, 0x1000000, true, false, 1, 1));
        this.lblStartingValue.setText("Starting Value:");
        this.txtStartingValue = new Text(this.composite, 2048);
        this.txtStartingValue.setLayoutData((Object)new GridData(4, 0x1000000, true, false, 3, 1));
        new Label(this.composite, 0);
        new Label(this.composite, 0);
        new Label(this.composite, 0);
        new Label(this.composite, 0);
        this.lblNewLabel_1 = new Label(this.composite, 0);
        GridData gd_lblNewLabel_1 = new GridData(16384, 0x1000000, false, false, 1, 1);
        gd_lblNewLabel_1.widthHint = 15;
        this.lblNewLabel_1.setLayoutData((Object)gd_lblNewLabel_1);
        this.btnAdd = new Button(this.composite, 0);
        this.btnAdd.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (NonLinearRegressionAnalysisParameters.this.txtName.getText().equals("")) {
                    if (NonLinearRegressionAnalysisParameters.this.txtStartingValue.getText().equals("")) {
                        MessageDialog.openError((Shell)NonLinearRegressionAnalysisParameters.this.getShell(), (String)"Error", (String)"Please specify parameter name and a starting value.");
                    } else {
                        MessageDialog.openError((Shell)NonLinearRegressionAnalysisParameters.this.getShell(), (String)"Error", (String)"Please specify a Parameter Name.");
                    }
                } else if (NonLinearRegressionAnalysisParameters.this.txtStartingValue.getText().equals("")) {
                    MessageDialog.openError((Shell)NonLinearRegressionAnalysisParameters.this.getShell(), (String)"Error", (String)"Please specify a starting value.");
                } else {
                    NonLinearRegressionAnalysisParameters.this.addVariablesToTable();
                }
            }
        });
        GridData gd_btnAdd = new GridData(4, 0x1000000, true, false, 1, 1);
        gd_btnAdd.widthHint = 70;
        this.btnAdd.setLayoutData((Object)gd_btnAdd);
        this.btnAdd.setText("Add");
        new Label(this.composite, 0);
        new Label(this.composite, 0);
        new Label(this.composite, 0);
        new Label(this.composite, 0);
        new Label(this.composite, 0);
        new Label(this.composite, 0);
        new Label(this.composite, 0);
        new Label(this.composite, 0);
        this.lblSummary = new Label(this.composite, 0);
        this.lblSummary.setLayoutData((Object)new GridData(16384, 0x1000000, true, false, 1, 1));
        this.lblSummary.setText("Summary:");
        new Label(this.composite, 0);
        new Label(this.composite, 0);
        new Label(this.composite, 0);
        new Label(this.composite, 0);
        new Label(this.composite, 0);
        this.table = new Table(this.composite, 67584);
        this.table.setLinesVisible(true);
        this.table.setHeaderVisible(true);
        GridData gd_table = new GridData(4, 4, true, true, 4, 1);
        gd_table.heightHint = 66;
        gd_table.widthHint = 214;
        this.table.setLayoutData((Object)gd_table);
        this.tableName = new TableColumn(this.table, 0);
        this.tableName.setWidth(119);
        this.tableName.setText("Name");
        this.tableStartValue = new TableColumn(this.table, 0);
        this.tableStartValue.setWidth(111);
        this.tableStartValue.setText("Starting Value");
        new Label(this.composite, 0);
        new Label(this.composite, 0);
        new Label(this.composite, 0);
        this.btnEdit = new Button(this.composite, 0);
        this.btnEdit.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (NonLinearRegressionAnalysisParameters.this.table.getItemCount() > 0) {
                    if (NonLinearRegressionAnalysisParameters.this.table.getItemCount() == 1 || NonLinearRegressionAnalysisParameters.this.table.getSelectionCount() > 0) {
                        int index = NonLinearRegressionAnalysisParameters.this.table.getSelectionIndex();
                        if (NonLinearRegressionAnalysisParameters.this.table.getSelectionCount() == 0) {
                            index = 0;
                        }
                        NonLinearRegressionAnalysisParameters.this.editRowValues(index);
                    } else {
                        MessageDialog.openWarning((Shell)NonLinearRegressionAnalysisParameters.this.getShell(), (String)"No selected row", (String)"Please select the row you want to delete.");
                    }
                } else {
                    MessageDialog.openWarning((Shell)NonLinearRegressionAnalysisParameters.this.getShell(), (String)"Invalid input", (String)"Table is empty. There is nothing to edit.");
                }
            }
        });
        GridData gd_btnEdit = new GridData(4, 0x1000000, true, false, 1, 1);
        gd_btnEdit.widthHint = 77;
        this.btnEdit.setLayoutData((Object)gd_btnEdit);
        this.btnEdit.setText("Edit");
        new Label(this.composite, 0);
        this.btnDelete = new Button(this.composite, 0);
        this.btnDelete.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (NonLinearRegressionAnalysisParameters.this.table.getItemCount() > 0) {
                    if (NonLinearRegressionAnalysisParameters.this.table.getItemCount() == 1 || NonLinearRegressionAnalysisParameters.this.table.getSelectionCount() > 0) {
                        int index = NonLinearRegressionAnalysisParameters.this.table.getSelectionIndex();
                        if (NonLinearRegressionAnalysisParameters.this.table.getSelectionCount() == 0) {
                            index = 0;
                        }
                        NonLinearRegressionAnalysisParameters.this.dataManipulationManager.deleteTableRow(NonLinearRegressionAnalysisParameters.this.table, index);
                    } else {
                        MessageDialog.openWarning((Shell)NonLinearRegressionAnalysisParameters.this.getShell(), (String)"No selected row", (String)"Please select the row you want to delete.");
                    }
                } else {
                    MessageDialog.openWarning((Shell)NonLinearRegressionAnalysisParameters.this.getShell(), (String)"Invalid input", (String)"Table is empty. There is nothing to delete.");
                }
            }
        });
        GridData gd_btnDelete = new GridData(4, 0x1000000, true, false, 1, 1);
        gd_btnDelete.widthHint = 70;
        this.btnDelete.setLayoutData((Object)gd_btnDelete);
        this.btnDelete.setText("Delete");
        new Label(this.composite, 0);
        this.initTable();
        return container;
    }

    protected void editRowValues(int index) {
        TableItem tableItem = this.table.getItem(index);
        if (!tableItem.getText(0).equals("")) {
            this.txtName.setText(tableItem.getText(0));
        }
        if (!tableItem.getText(1).equals("")) {
            this.txtStartingValue.setText(tableItem.getText(1));
        }
        this.dataManipulationManager.deleteTableRow(this.table, index);
    }

    protected void initTable() {
        for (ParameterModel param : this.parameterList) {
            TableItem tableItem = new TableItem(this.table, 0x1000000);
            tableItem.setText(0, param.name);
            tableItem.setText(1, param.value);
        }
    }

    protected void addVariablesToTable() {
        String[] values = this.getVariableValues();
        TableItem tableItem = new TableItem(this.table, 0x1000000);
        tableItem.setText(0, values[0]);
        tableItem.setText(1, values[1]);
    }

    private String[] getVariableValues() {
        String[] values = new String[]{this.txtName.getText() != null ? this.txtName.getText() : "", this.txtStartingValue.getText() != null ? this.txtStartingValue.getText() : ""};
        this.removeVarValues();
        return values;
    }

    private void removeVarValues() {
        this.txtName.setText("");
        this.txtStartingValue.setText("");
    }

    protected void buttonPressed(int buttonID) {
        if (buttonID == 0) {
            this.okPressed();
        } else if (buttonID == 8) {
            this.txtName.setText("");
            this.txtStartingValue.setText("");
            this.table.removeAll();
        } else {
            this.close();
        }
    }

    protected void okPressed() {
        this.btnOk.setEnabled(false);
        this.reloadParameterList();
        this.close();
    }

    protected void createButtonsForButtonBar(Composite parent) {
        this.createButton(parent, 8, "Reset", false);
        this.btnOk = this.createButton(parent, 0, IDialogConstants.OK_LABEL, true);
        this.createButton(parent, 1, IDialogConstants.CANCEL_LABEL, false);
    }

    protected Point getInitialSize() {
        return new Point(360, 412);
    }

    public String getReturnStartVal() {
        return this.returnStartVal;
    }

    public void setReturnStartVal(String returnStartVal) {
        this.returnStartVal = returnStartVal;
    }

    public class ParameterModel {
        public String name;
        public String value;

        public ParameterModel() {
        }

        public ParameterModel(String name, String value) {
            this.name = name;
            this.value = value;
        }
    }
}

