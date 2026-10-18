/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.jface.dialogs.Dialog
 *  org.eclipse.jface.dialogs.IDialogConstants
 *  org.eclipse.jface.dialogs.MessageDialog
 *  org.eclipse.swt.custom.CCombo
 *  org.eclipse.swt.events.SelectionAdapter
 *  org.eclipse.swt.events.SelectionEvent
 *  org.eclipse.swt.events.SelectionListener
 *  org.eclipse.swt.graphics.Point
 *  org.eclipse.swt.layout.GridData
 *  org.eclipse.swt.layout.GridLayout
 *  org.eclipse.swt.widgets.Combo
 *  org.eclipse.swt.widgets.Composite
 *  org.eclipse.swt.widgets.Control
 *  org.eclipse.swt.widgets.Display
 *  org.eclipse.swt.widgets.Event
 *  org.eclipse.swt.widgets.Group
 *  org.eclipse.swt.widgets.Label
 *  org.eclipse.swt.widgets.Listener
 *  org.eclipse.swt.widgets.Shell
 *  org.eclipse.swt.widgets.Table
 *  org.eclipse.swt.widgets.TableColumn
 *  org.eclipse.swt.widgets.TableItem
 *  org.eclipse.swt.widgets.Text
 */
package org.irri.breedingtool.datamanipulation.dialog;

import java.util.ArrayList;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;
import org.irri.breedingtool.application.handler.PartStackHandler;
import org.irri.breedingtool.application.model.ProjectExplorerTreeNodeModel;
import org.irri.breedingtool.manager.impl.DataManipulationManager;
import org.irri.breedingtool.projectexplorer.view.ProjectExplorerView;
import org.irri.breedingtool.utility.WindowDialogControlUtil;

public class CreateNewVarDialog
extends Dialog {
    private Text varNameText;
    private Combo values_combo;
    private Table table;
    private String[] formulaList = new String[]{"Logarithm (base 10)", "Natural Logarithm", "Square root", "Power", "Exponential", "Standardized"};
    private String[] formulaListEquiv = new String[]{"log", "ln", "sqrt", "power", "exp", "standardize"};
    private Group group;
    private Shell parentShell;
    private ProjectExplorerTreeNodeModel fileModel;
    private ArrayList<String> checkedTableItems = new ArrayList();
    DataManipulationManager dataManipulationManager = new DataManipulationManager();
    private CCombo comboVar;
    private CCombo comboFormula;
    private ArrayList<String> varInfo;
    private String[] columnHeaders;
    private Composite container;

    public CreateNewVarDialog(Shell parentShell, ArrayList<String> varInfo, ProjectExplorerTreeNodeModel fileModel) {
        super(parentShell);
        this.varInfo = varInfo;
        this.columnHeaders = new String[varInfo.size()];
        this.parentShell = parentShell;
        this.fileModel = fileModel;
    }

    protected Control createDialogArea(Composite parent) {
        parent.getShell().setText("Create New Variable: " + this.dataManipulationManager.getDisplayFileName(this.fileModel.getProjectFile().getAbsolutePath()));
        this.container = (Composite)super.createDialogArea(parent);
        GridLayout gridLayout = (GridLayout)this.container.getLayout();
        gridLayout.numColumns = 2;
        Label lblNewLabel = new Label(this.container, 0);
        lblNewLabel.setLayoutData((Object)new GridData(4, 4, false, false, 1, 1));
        lblNewLabel.setFont(SWTResourceManager.getFont("Tahoma", 9, 0));
        lblNewLabel.setText("Name:");
        this.varNameText = new Text(this.container, 2048);
        this.varNameText.setText("newVar");
        GridData gd_varNameText = new GridData(4, 4, true, false, 1, 1);
        gd_varNameText.widthHint = 96;
        this.varNameText.setLayoutData((Object)gd_varNameText);
        this.varNameText.addListener(25, new Listener(){

            public void handleEvent(Event event) {
                String string = event.text;
                char[] chars = new char[string.length()];
                string.getChars(0, chars.length, chars, 0);
                if (CreateNewVarDialog.this.varNameText.getText().isEmpty() && Character.isDigit(chars[0])) {
                    event.doit = false;
                    MessageDialog.openWarning((Shell)parent.getShell(), (String)"Invalid Input", (String)"Sorry, you can't have an integer as the first character of your variable name.");
                    return;
                }
            }
        });
        Label lblValues = new Label(this.container, 0);
        GridData gd_lblValues = new GridData(4, 4, false, false, 1, 1);
        gd_lblValues.widthHint = 45;
        lblValues.setLayoutData((Object)gd_lblValues);
        lblValues.setText("Values:");
        lblValues.setFont(SWTResourceManager.getFont("Tahoma", 9, 0));
        this.values_combo = new Combo(this.container, 8);
        this.values_combo.setItems(new String[]{"Concatenate Columns", "Transform"});
        GridData gd_values_combo = new GridData(4, 4, false, false, 1, 1);
        gd_values_combo.widthHint = 136;
        this.values_combo.select(1);
        this.values_combo.setLayoutData((Object)gd_values_combo);
        this.group = new Group(this.container, 0);
        this.group.setLayout(null);
        GridData gd_group = new GridData(4, 4, true, false, 2, 1);
        gd_group.heightHint = 230;
        gd_group.widthHint = 365;
        this.group.setLayoutData((Object)gd_group);
        Label lblSpecifyTransformation = new Label((Composite)this.group, 0);
        lblSpecifyTransformation.setBounds(10, 10, 228, 25);
        lblSpecifyTransformation.setFont(SWTResourceManager.getFont("Tahoma", 9, 0));
        lblSpecifyTransformation.setText("Specify transformation to be performed:");
        Label lblNewLabel_1 = new Label((Composite)this.group, 0);
        lblNewLabel_1.setFont(SWTResourceManager.getFont("Tahoma", 9, 0));
        lblNewLabel_1.setBounds(22, 43, 109, 25);
        lblNewLabel_1.setText("Input Variable:");
        this.comboVar = new CCombo((Composite)this.group, 2056);
        this.comboVar.setBackground(SWTResourceManager.getColor(1));
        this.comboVar.setEditable(false);
        this.comboVar.setBounds(146, 47, 156, 21);
        this.populateNumVarCombo();
        Label lblNewLabel_2 = new Label((Composite)this.group, 0);
        lblNewLabel_2.setFont(SWTResourceManager.getFont("Tahoma", 9, 0));
        lblNewLabel_2.setBounds(21, 79, 110, 21);
        lblNewLabel_2.setText("Formula:");
        this.comboFormula = new CCombo((Composite)this.group, 2056);
        this.comboFormula.setBackground(SWTResourceManager.getColor(1));
        this.comboFormula.setBounds(146, 79, 156, 21);
        this.comboFormula.setItems(this.formulaList);
        this.values_combo.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (CreateNewVarDialog.this.values_combo.getSelectionIndex() == 1) {
                    Control[] controlArray = CreateNewVarDialog.this.group.getChildren();
                    int n = controlArray.length;
                    int n2 = 0;
                    while (n2 < n) {
                        Control kid = controlArray[n2];
                        kid.dispose();
                        ++n2;
                    }
                    Label lblSpecifyTransformation = new Label((Composite)CreateNewVarDialog.this.group, 0);
                    lblSpecifyTransformation.setBounds(10, 10, 228, 25);
                    lblSpecifyTransformation.setFont(SWTResourceManager.getFont("Tahoma", 9, 0));
                    lblSpecifyTransformation.setText("Specify transformation to be performed:");
                    Label lblNewLabel_1 = new Label((Composite)CreateNewVarDialog.this.group, 0);
                    lblNewLabel_1.setFont(SWTResourceManager.getFont("Tahoma", 9, 0));
                    lblNewLabel_1.setBounds(22, 43, 85, 25);
                    lblNewLabel_1.setText("Input Variable:");
                    CreateNewVarDialog.this.comboVar = new CCombo((Composite)CreateNewVarDialog.this.group, 2056);
                    CreateNewVarDialog.this.comboVar.setBounds(112, 41, 156, 21);
                    CreateNewVarDialog.this.comboVar.setBackground(SWTResourceManager.getColor(1));
                    CreateNewVarDialog.this.populateNumVarCombo();
                    Label lblNewLabel_2 = new Label((Composite)CreateNewVarDialog.this.group, 0);
                    lblNewLabel_2.setFont(SWTResourceManager.getFont("Tahoma", 9, 0));
                    lblNewLabel_2.setBounds(21, 79, 49, 13);
                    lblNewLabel_2.setText("Formula:");
                    CreateNewVarDialog.this.comboFormula = new CCombo((Composite)CreateNewVarDialog.this.group, 2056);
                    CreateNewVarDialog.this.comboFormula.setBounds(112, 79, 156, 21);
                    CreateNewVarDialog.this.comboFormula.setBackground(SWTResourceManager.getColor(1));
                    CreateNewVarDialog.this.comboFormula.setItems(CreateNewVarDialog.this.formulaList);
                    CreateNewVarDialog.this.group.update();
                    CreateNewVarDialog.this.container.update();
                } else {
                    Control[] controlArray = CreateNewVarDialog.this.group.getChildren();
                    int lblNewLabel_2 = controlArray.length;
                    int lblNewLabel_1 = 0;
                    while (lblNewLabel_1 < lblNewLabel_2) {
                        Control kid = controlArray[lblNewLabel_1];
                        kid.dispose();
                        ++lblNewLabel_1;
                    }
                    Label lblSpecifyVariables = new Label((Composite)CreateNewVarDialog.this.group, 0);
                    lblSpecifyVariables.setBounds(10, 10, 228, 25);
                    lblSpecifyVariables.setFont(SWTResourceManager.getFont("Tahoma", 9, 0));
                    lblSpecifyVariables.setText("Specify variables:");
                    CreateNewVarDialog.this.table = new Table((Composite)CreateNewVarDialog.this.group, 2080);
                    CreateNewVarDialog.this.table.setBounds(10, 53, 250, 183);
                    CreateNewVarDialog.this.table.setHeaderVisible(true);
                    CreateNewVarDialog.this.table.setLinesVisible(true);
                    TableColumn column = new TableColumn(CreateNewVarDialog.this.table, 0x1000000);
                    column.setText("Column Names");
                    column.setWidth(245);
                    int i = 0;
                    while (i < CreateNewVarDialog.this.columnHeaders.length) {
                        TableItem tableItem = new TableItem(CreateNewVarDialog.this.table, 0x1000000);
                        tableItem.setText(0, CreateNewVarDialog.this.columnHeaders[i]);
                        ++i;
                    }
                    CreateNewVarDialog.this.table.addListener(13, new Listener(){

                        public void handleEvent(Event event) {
                            if (event.detail == 32) {
                                TableItem tableItem = (TableItem)event.item;
                                try {
                                    if (tableItem.getData("checked").equals("true")) {
                                        CreateNewVarDialog.this.checkedTableItems.remove(tableItem.getText());
                                        tableItem.setData("checked", (Object)"false");
                                        System.out.println("unchecked " + event.item.toString());
                                    } else {
                                        tableItem.setData("checked", (Object)"true");
                                        CreateNewVarDialog.this.checkedTableItems.add(tableItem.getText());
                                        System.out.println("checked " + event.item.toString());
                                    }
                                }
                                catch (NullPointerException nullPointerException) {
                                    event.item.setData("checked", (Object)"true");
                                    CreateNewVarDialog.this.checkedTableItems.add(tableItem.getText());
                                    System.out.println("checked " + event.item.toString());
                                }
                            }
                        }
                    });
                    CreateNewVarDialog.this.group.update();
                    CreateNewVarDialog.this.container.update();
                }
            }

            public void widgetDefaultSelected(SelectionEvent e) {
            }
        });
        new Label(this.container, 0);
        new Label(this.container, 0);
        return this.container;
    }

    protected void okPressed() {
        String string = this.varNameText.getText();
        char[] chars = new char[string.length()];
        string.getChars(0, chars.length, chars, 0);
        if (this.dataManipulationManager.checkColumnNameIfUsed(this.varNameText.getText(), this.columnHeaders)) {
            MessageDialog.openWarning((Shell)this.parentShell.getShell(), (String)"Invalid Input", (String)("Sorry, the variable name \"" + this.varNameText.getText() + "\" already exist."));
        } else if (this.varNameText.getText().isEmpty()) {
            MessageDialog.openWarning((Shell)this.parentShell.getShell(), (String)"Invalid Input", (String)"Please enter a variable name.");
        } else if (!this.dataManipulationManager.variableNameInputValidation(this.varNameText.getText()).equals("true")) {
            MessageDialog.openWarning((Shell)this.parentShell.getShell(), (String)"Invalid Input", (String)this.dataManipulationManager.variableNameInputValidation(this.varNameText.getText()));
        } else {
            String dataFileName;
            String newFileName = dataFileName = this.fileModel.getProjectFile().getAbsolutePath().toString().replaceAll("\\\\+", "/");
            String newVariableName = this.varNameText.getText();
            Table fileTable = DataManipulationManager.getActiveTable(this.fileModel.getProjectFile().getAbsolutePath());
            if (this.values_combo.getSelectionIndex() == 0) {
                if (this.checkedTableItems.isEmpty()) {
                    MessageDialog.openWarning((Shell)this.parentShell.getShell(), (String)"Invalid Input", (String)"Please select the variables to be combined.");
                } else if (this.checkedTableItems.size() < 2) {
                    MessageDialog.openWarning((Shell)this.parentShell.getShell(), (String)"Invalid Input", (String)"Please select at least two variables to be combined.");
                } else {
                    String[] inputVariables = this.checkedTableItems.toArray(new String[this.checkedTableItems.size()]);
                    ProjectExplorerView.rJavaManager.getRJavaDataManipulationManager().combineFactorLevels(dataFileName, newFileName, inputVariables, newVariableName);
                    this.close();
                    DataManipulationManager.removeTable(fileTable);
                    WindowDialogControlUtil.closeAllWindowDialogOfFile(this.fileModel.getProjectFile().getAbsolutePath());
                    PartStackHandler.reOpenPart(this.fileModel);
                }
            } else if (this.comboVar.getText().isEmpty()) {
                MessageDialog.openWarning((Shell)this.parentShell.getShell(), (String)"Invalid Input", (String)"Please select the input variable to be transformed.");
            } else if (this.comboFormula.getSelectionIndex() < 0) {
                MessageDialog.openWarning((Shell)this.parentShell.getShell(), (String)"Invalid Input", (String)"Please select the transformation formula to be used.");
            } else {
                String inputVariable = this.comboVar.getText();
                String formula = this.formulaListEquiv[this.comboFormula.getSelectionIndex()];
                ProjectExplorerView.rJavaManager.getRJavaDataManipulationManager().dataTransformation(dataFileName, newFileName, newVariableName, inputVariable, formula);
                this.close();
                try {
                    DataManipulationManager.removeTable(fileTable);
                    WindowDialogControlUtil.closeAllWindowDialogOfFile(this.fileModel.getProjectFile().getAbsolutePath());
                    PartStackHandler.reOpenPart(this.fileModel);
                }
                catch (NullPointerException nullPointerException) {
                    MessageDialog.openWarning((Shell)Display.getCurrent().getActiveShell(), (String)"Failed to create new variable in file.", (String)"Please specify the correct variables.");
                }
            }
        }
    }

    private void populateNumVarCombo() {
        int ctr = 0;
        for (String s : this.varInfo) {
            String[] tmp = s.split(":");
            this.columnHeaders[ctr] = tmp[0];
            if (tmp[1].toLowerCase().equals("numeric")) {
                this.comboVar.add(tmp[0]);
                this.comboVar.setData(tmp[0], (Object)tmp[1]);
            }
            ++ctr;
        }
    }

    protected void buttonPressed(int buttonId) {
        if (buttonId == 19) {
            this.varNameText.setText("newVar");
            this.values_combo.select(1);
            this.checkedTableItems.clear();
            Control[] controlArray = this.group.getChildren();
            int n = controlArray.length;
            int n2 = 0;
            while (n2 < n) {
                Control kid = controlArray[n2];
                kid.dispose();
                ++n2;
            }
            Label lblSpecifyTransformation = new Label((Composite)this.group, 0);
            lblSpecifyTransformation.setBounds(10, 10, 228, 25);
            lblSpecifyTransformation.setFont(SWTResourceManager.getFont("Tahoma", 9, 0));
            lblSpecifyTransformation.setText("Specify transformation to be performed:");
            Label lblNewLabel_1 = new Label((Composite)this.group, 0);
            lblNewLabel_1.setFont(SWTResourceManager.getFont("Tahoma", 9, 0));
            lblNewLabel_1.setBounds(22, 43, 85, 25);
            lblNewLabel_1.setText("Input Variable:");
            this.comboVar = new CCombo((Composite)this.group, 2056);
            this.comboVar.setBounds(112, 41, 156, 21);
            this.comboVar.setBackground(SWTResourceManager.getColor(1));
            this.populateNumVarCombo();
            Label lblNewLabel_2 = new Label((Composite)this.group, 0);
            lblNewLabel_2.setFont(SWTResourceManager.getFont("Tahoma", 9, 0));
            lblNewLabel_2.setBounds(21, 79, 49, 13);
            lblNewLabel_2.setText("Formula:");
            this.comboFormula = new CCombo((Composite)this.group, 2056);
            this.comboFormula.setBounds(112, 79, 156, 21);
            this.comboFormula.setBackground(SWTResourceManager.getColor(1));
            this.comboFormula.setItems(this.formulaList);
            this.group.update();
            this.container.update();
        }
        super.buttonPressed(buttonId);
    }

    protected void createButtonsForButtonBar(Composite parent) {
        this.createButton(parent, 19, "Reset", true);
        this.createButton(parent, 0, IDialogConstants.OK_LABEL, true);
        this.createButton(parent, 1, IDialogConstants.CANCEL_LABEL, false);
    }

    protected Point getInitialSize() {
        return new Point(471, 483);
    }

    protected boolean isResizable() {
        return true;
    }
}

