/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.jface.dialogs.Dialog
 *  org.eclipse.jface.dialogs.IDialogConstants
 *  org.eclipse.jface.dialogs.MessageDialog
 *  org.eclipse.swt.custom.TableEditor
 *  org.eclipse.swt.events.FocusEvent
 *  org.eclipse.swt.events.FocusListener
 *  org.eclipse.swt.events.ModifyEvent
 *  org.eclipse.swt.events.ModifyListener
 *  org.eclipse.swt.events.SelectionAdapter
 *  org.eclipse.swt.events.SelectionEvent
 *  org.eclipse.swt.events.SelectionListener
 *  org.eclipse.swt.graphics.Point
 *  org.eclipse.swt.layout.GridData
 *  org.eclipse.swt.layout.GridLayout
 *  org.eclipse.swt.widgets.Button
 *  org.eclipse.swt.widgets.Combo
 *  org.eclipse.swt.widgets.Composite
 *  org.eclipse.swt.widgets.Control
 *  org.eclipse.swt.widgets.Event
 *  org.eclipse.swt.widgets.Group
 *  org.eclipse.swt.widgets.Label
 *  org.eclipse.swt.widgets.Layout
 *  org.eclipse.swt.widgets.List
 *  org.eclipse.swt.widgets.Listener
 *  org.eclipse.swt.widgets.Shell
 *  org.eclipse.swt.widgets.Spinner
 *  org.eclipse.swt.widgets.Table
 *  org.eclipse.swt.widgets.TableColumn
 *  org.eclipse.swt.widgets.TableItem
 *  org.eclipse.swt.widgets.Text
 */
package org.irri.breedingtool.star.analysis.dialog;

import java.io.File;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;
import org.irri.breedingtool.application.handler.PartStackHandler;
import org.irri.breedingtool.datamanipulation.dialog.OperationProgressDialog;
import org.irri.breedingtool.projectexplorer.view.ProjectExplorerView;
import org.irri.breedingtool.utility.DialogFormUtility;
import org.irri.breedingtool.utility.DialogListValidator;
import org.irri.breedingtool.utility.StarAnalysisUtilities;

public class TestOnProportionDialog
extends Dialog {
    private Table table;
    private Text txtGroupingVariable;
    private List lstNumericVariables;
    private Button btnToFactor;
    private List lstFactorVariables;
    private TableColumn tblColumn1;
    private TableColumn tblColumn2;
    private Button btnAddVariable;
    private Combo cmbProcedure;
    private Button btnGroupingVariable;
    private Combo cmbAlternative;
    private String filePath = PartStackHandler.getActiveElementID();
    private DialogFormUtility dlgManager = new DialogFormUtility();
    private Spinner txtConfidenceInterval;
    private int EDITABLECOLUMN;
    private Button btnConstructInterval;
    private Label lblGroupingVariable;

    public TestOnProportionDialog(Shell parentShell) {
        super(parentShell);
        this.setBlockOnOpen(false);
        this.setShellStyle(35024);
    }

    protected void configureShell(Shell shell) {
        super.configureShell(shell);
        shell.setText("Test on Proportion : " + new File(this.filePath).getName());
    }

    protected Control createDialogArea(Composite parent) {
        Composite container = (Composite)super.createDialogArea(parent);
        GridLayout gl_container = new GridLayout(4, false);
        gl_container.marginHeight = 8;
        gl_container.marginWidth = 8;
        container.setLayout((Layout)gl_container);
        Label lblNumericVariables = new Label(container, 0);
        lblNumericVariables.setText("Numeric Variables:");
        new Label(container, 0);
        Label lblTypeOfTest = new Label(container, 0);
        lblTypeOfTest.setText("Procedure");
        this.cmbProcedure = new Combo(container, 8);
        this.cmbProcedure.setItems(new String[]{"One Sample", "Two Related Sample", "Independent Sample"});
        this.cmbProcedure.select(0);
        this.lstNumericVariables = new List(container, 2818);
        GridData gd_lstNumericVariables = new GridData(4, 4, true, true, 1, 2);
        gd_lstNumericVariables.heightHint = 100;
        gd_lstNumericVariables.widthHint = 161;
        this.lstNumericVariables.setLayoutData((Object)gd_lstNumericVariables);
        new Label(container, 0);
        this.table = new Table(container, 67586);
        GridData gd_table = new GridData(4, 4, true, true, 2, 2);
        gd_table.heightHint = 122;
        this.table.setLayoutData((Object)gd_table);
        this.table.setHeaderVisible(true);
        this.table.setLinesVisible(true);
        this.tblColumn1 = new TableColumn(this.table, 0);
        this.tblColumn1.setWidth(116);
        this.tblColumn1.setText("Variable");
        this.tblColumn2 = new TableColumn(this.table, 0);
        this.tblColumn2.setWidth(102);
        this.tblColumn2.setText("Test Value");
        Spinner sprLevel = new Spinner((Composite)this.table, 0);
        sprLevel.setIncrement(1);
        this.btnAddVariable = new Button(container, 0);
        GridData gd_btnAddVariable = new GridData(131072, 0x1000000, false, false, 1, 1);
        gd_btnAddVariable.widthHint = 90;
        this.btnAddVariable.setLayoutData((Object)gd_btnAddVariable);
        this.btnAddVariable.setText("Add");
        this.btnToFactor = new Button(container, 0);
        GridData gd_btnToFactor = new GridData(131072, 0x1000000, false, true, 1, 1);
        gd_btnToFactor.heightHint = 30;
        gd_btnToFactor.widthHint = 110;
        this.btnToFactor.setLayoutData((Object)gd_btnToFactor);
        this.btnToFactor.setText("Set to Factor");
        new Label(container, 0);
        new Label(container, 0);
        new Label(container, 0);
        Label lblNewLabel = new Label(container, 0);
        lblNewLabel.setText("Factors:");
        new Label(container, 0);
        this.lblGroupingVariable = new Label(container, 0);
        this.lblGroupingVariable.setLayoutData((Object)new GridData(16384, 0x1000000, false, false, 2, 1));
        this.lblGroupingVariable.setEnabled(false);
        this.lblGroupingVariable.setText("Grouping Variable");
        this.lstFactorVariables = new List(container, 2816);
        GridData gd_lstFactorVariables = new GridData(4, 4, true, true, 1, 2);
        gd_lstFactorVariables.heightHint = 100;
        gd_lstFactorVariables.widthHint = 145;
        this.lstFactorVariables.setLayoutData((Object)gd_lstFactorVariables);
        this.btnGroupingVariable = new Button(container, 0);
        this.btnGroupingVariable.setEnabled(false);
        GridData gd_btnGroupingVariable = new GridData(131072, 0x1000000, false, false, 1, 1);
        gd_btnGroupingVariable.widthHint = 90;
        this.btnGroupingVariable.setLayoutData((Object)gd_btnGroupingVariable);
        this.btnGroupingVariable.setText("Add");
        this.txtGroupingVariable = new Text(container, 2056);
        this.txtGroupingVariable.setLayoutData((Object)new GridData(4, 0x1000000, false, false, 2, 1));
        this.txtGroupingVariable.setForeground(SWTResourceManager.getColor(2));
        this.txtGroupingVariable.setBackground(SWTResourceManager.getColor(1));
        Group grpOptions = new Group(container, 0);
        GridData gd_grpOptions = new GridData(4, 4, true, true, 3, 1);
        gd_grpOptions.heightHint = 80;
        grpOptions.setLayoutData((Object)gd_grpOptions);
        grpOptions.setText("Options");
        grpOptions.setLayout((Layout)new GridLayout(2, false));
        Label lblAlternativeHypothesis = new Label((Composite)grpOptions, 0);
        lblAlternativeHypothesis.setText("Alternative Hypothesis");
        this.cmbAlternative = new Combo((Composite)grpOptions, 0);
        this.cmbAlternative.setLayoutData((Object)new GridData(4, 0x1000000, false, false, 1, 1));
        this.cmbAlternative.setItems(new String[]{"less", "greater", "not equal"});
        this.cmbAlternative.select(2);
        this.btnConstructInterval = new Button((Composite)grpOptions, 32);
        GridData gd_btnConstructInterval = new GridData(4, 0x1000000, true, false, 1, 1);
        gd_btnConstructInterval.widthHint = 220;
        this.btnConstructInterval.setLayoutData((Object)gd_btnConstructInterval);
        this.btnConstructInterval.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                TestOnProportionDialog.this.txtConfidenceInterval.setEnabled(TestOnProportionDialog.this.btnConstructInterval.getSelection());
            }
        });
        this.btnConstructInterval.setText("Construct Confidence Interval");
        this.txtConfidenceInterval = new Spinner((Composite)grpOptions, 2048);
        GridData gd_txtConfidenceInterval = new GridData(4, 0x1000000, true, false, 1, 1);
        gd_txtConfidenceInterval.widthHint = 35;
        this.txtConfidenceInterval.setLayoutData((Object)gd_txtConfidenceInterval);
        this.txtConfidenceInterval.setEnabled(false);
        this.txtConfidenceInterval.setSelection(95);
        this.initializeForm();
        return container;
    }

    void initializeForm() {
        this.cmbProcedure.addModifyListener(new ModifyListener(){

            public void modifyText(ModifyEvent e) {
                TestOnProportionDialog.this.table.removeAll();
                TestOnProportionDialog.this.lstNumericVariables.removeAll();
                TestOnProportionDialog.this.dlgManager.initializeNumericList(TestOnProportionDialog.this.lstNumericVariables, TestOnProportionDialog.this.filePath);
                TestOnProportionDialog.this.txtGroupingVariable.setBackground(SWTResourceManager.getColor(15));
                TestOnProportionDialog.this.btnAddVariable.setText("Add");
                TestOnProportionDialog.this.lblGroupingVariable.setEnabled(false);
                if (TestOnProportionDialog.this.cmbProcedure.getText().equals("One Sample")) {
                    TestOnProportionDialog.this.txtGroupingVariable.setEnabled(false);
                    TestOnProportionDialog.this.btnGroupingVariable.setEnabled(false);
                    TestOnProportionDialog.this.tblColumn1.setText("Variable");
                    if (TestOnProportionDialog.this.tblColumn2.isDisposed()) {
                        TestOnProportionDialog.this.tblColumn2 = new TableColumn(TestOnProportionDialog.this.table, 0);
                        TestOnProportionDialog.this.tblColumn2.setWidth(116);
                    }
                    TestOnProportionDialog.this.tblColumn2.setText("Test Value");
                } else if (TestOnProportionDialog.this.cmbProcedure.getText().equals("Two Related Sample")) {
                    TestOnProportionDialog.this.tblColumn1.setText("Variable 1");
                    TestOnProportionDialog.this.btnGroupingVariable.setEnabled(false);
                    if (TestOnProportionDialog.this.tblColumn2.isDisposed()) {
                        TestOnProportionDialog.this.tblColumn2 = new TableColumn(TestOnProportionDialog.this.table, 0);
                        TestOnProportionDialog.this.tblColumn2.setWidth(116);
                    }
                    TestOnProportionDialog.this.tblColumn2.setText("Variable 2");
                } else if (TestOnProportionDialog.this.cmbProcedure.getText().equals("Independent Sample")) {
                    TestOnProportionDialog.this.txtGroupingVariable.setEnabled(true);
                    TestOnProportionDialog.this.btnGroupingVariable.setEnabled(true);
                    TestOnProportionDialog.this.txtGroupingVariable.setBackground(SWTResourceManager.getColor(1));
                    TestOnProportionDialog.this.lblGroupingVariable.setEnabled(true);
                    TestOnProportionDialog.this.tblColumn1.setText("Variable");
                    TestOnProportionDialog.this.tblColumn2.dispose();
                }
            }
        });
        this.table.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                TestOnProportionDialog.this.btnAddVariable.setText("Remove");
                TestOnProportionDialog.this.btnAddVariable.setEnabled(true);
            }
        });
        this.lstNumericVariables.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                TestOnProportionDialog.this.btnAddVariable.setText("Add");
                TestOnProportionDialog.this.btnAddVariable.setEnabled(true);
            }
        });
        this.btnAddVariable.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (TestOnProportionDialog.this.btnAddVariable.getText().equals("Add")) {
                    if (TestOnProportionDialog.this.cmbProcedure.getText().equals("One Sample")) {
                        int i = TestOnProportionDialog.this.lstNumericVariables.getSelectionCount() - 1;
                        while (i >= 0) {
                            TableItem tableItem = new TableItem(TestOnProportionDialog.this.table, 0);
                            tableItem.setText(new String[]{TestOnProportionDialog.this.lstNumericVariables.getSelection()[i], "0.5"});
                            TestOnProportionDialog.this.lstNumericVariables.remove(TestOnProportionDialog.this.lstNumericVariables.getSelectionIndices()[i]);
                            --i;
                        }
                    } else if (TestOnProportionDialog.this.cmbProcedure.getText().equals("Two Related Sample")) {
                        if (TestOnProportionDialog.this.lstNumericVariables.getSelectionCount() != 2) {
                            MessageDialog.openInformation((Shell)TestOnProportionDialog.this.getShell(), (String)"Variable Selection", (String)"Please select only two variables in the numeric list");
                            return;
                        }
                        TableItem tableItem = new TableItem(TestOnProportionDialog.this.table, 0);
                        tableItem.setText(new String[]{TestOnProportionDialog.this.lstNumericVariables.getSelection()[0], TestOnProportionDialog.this.lstNumericVariables.getSelection()[1]});
                        TestOnProportionDialog.this.lstNumericVariables.remove(TestOnProportionDialog.this.lstNumericVariables.getSelectionIndices()[1]);
                        TestOnProportionDialog.this.lstNumericVariables.remove(TestOnProportionDialog.this.lstNumericVariables.getSelectionIndices()[0]);
                    } else if (TestOnProportionDialog.this.cmbProcedure.getText().equals("Independent Sample")) {
                        int i = TestOnProportionDialog.this.lstNumericVariables.getSelectionCount() - 1;
                        while (i >= 0) {
                            TableItem tableItem = new TableItem(TestOnProportionDialog.this.table, 0);
                            tableItem.setText(TestOnProportionDialog.this.lstNumericVariables.getSelection()[i]);
                            TestOnProportionDialog.this.lstNumericVariables.remove(TestOnProportionDialog.this.lstNumericVariables.getSelectionIndices()[i]);
                            --i;
                        }
                    }
                    TestOnProportionDialog.this.btnAddVariable.setEnabled(false);
                } else {
                    if (TestOnProportionDialog.this.cmbProcedure.getText().equals("Two Related Sample")) {
                        int i = TestOnProportionDialog.this.table.getSelectionCount() - 1;
                        while (i >= 0) {
                            TestOnProportionDialog.this.lstNumericVariables.add(TestOnProportionDialog.this.table.getSelection()[i].getText(0));
                            TestOnProportionDialog.this.lstNumericVariables.add(TestOnProportionDialog.this.table.getSelection()[i].getText(1));
                            TestOnProportionDialog.this.table.remove(TestOnProportionDialog.this.table.getSelectionIndices()[i]);
                            --i;
                        }
                    } else {
                        int i = TestOnProportionDialog.this.table.getSelectionCount() - 1;
                        while (i >= 0) {
                            TestOnProportionDialog.this.lstNumericVariables.add(TestOnProportionDialog.this.table.getSelection()[i].getText(0));
                            TestOnProportionDialog.this.table.remove(TestOnProportionDialog.this.table.getSelectionIndices()[i]);
                            --i;
                        }
                    }
                    TestOnProportionDialog.this.btnAddVariable.setEnabled(false);
                }
            }
        });
        TableEditor editor = new TableEditor(this.table);
        this.table.addListener(3, new Listener(){

            public void handleEvent(Event e) {
                TableItem item;
                Control oldEditor = editor.getEditor();
                if (oldEditor != null) {
                    oldEditor.dispose();
                }
                if ((item = TestOnProportionDialog.this.table.getSelection()[0]) == null) {
                    return;
                }
                Point mPoint = new Point(e.x, e.y);
                int i = 0;
                while (i < item.getParent().getColumnCount()) {
                    if (item.getBounds(i).contains(mPoint)) {
                        TestOnProportionDialog.this.EDITABLECOLUMN = i;
                    }
                    ++i;
                }
                System.out.println(TestOnProportionDialog.this.EDITABLECOLUMN);
                if (!TestOnProportionDialog.this.cmbProcedure.getText().equals("One Sample")) {
                    return;
                }
                if (TestOnProportionDialog.this.EDITABLECOLUMN == 0 && TestOnProportionDialog.this.cmbProcedure.getText().equals("One Sample")) {
                    TestOnProportionDialog.this.EDITABLECOLUMN = 1;
                }
                if (TestOnProportionDialog.this.EDITABLECOLUMN == 1) {
                    Spinner newEditor = new Spinner((Composite)TestOnProportionDialog.this.table, 0);
                    newEditor.setIncrement(1);
                    newEditor.setMaximum(10);
                    newEditor.setMinimum(5);
                    newEditor.setDigits(1);
                    newEditor.setSelection((int)(5.0 * Double.parseDouble(item.getText(TestOnProportionDialog.this.EDITABLECOLUMN))));
                    newEditor.addModifyListener(new ModifyListener(){

                        public void modifyText(ModifyEvent me) {
                            Spinner spinner = (Spinner)editor.getEditor();
                            editor.getItem().setText(TestOnProportionDialog.this.EDITABLECOLUMN, spinner.getText());
                        }
                    });
                    newEditor.addFocusListener(new FocusListener(){

                        public void focusGained(FocusEvent e) {
                        }

                        public void focusLost(FocusEvent e) {
                            Spinner text = (Spinner)editor.getEditor();
                            text.dispose();
                        }
                    });
                    newEditor.setFocus();
                    editor.setEditor((Control)newEditor, item, TestOnProportionDialog.this.EDITABLECOLUMN);
                }
            }
        });
        editor.horizontalAlignment = 16384;
        editor.grabHorizontal = true;
        editor.minimumWidth = 70;
        this.dlgManager.initializeVariableMoveList(this.lstNumericVariables, this.lstFactorVariables, this.btnToFactor, this.filePath);
        this.dlgManager.initializeSingleSelectionList(this.lstFactorVariables, this.txtGroupingVariable, this.btnToFactor, this.btnGroupingVariable, new proportionValidate(this.cmbProcedure, this.btnGroupingVariable));
        this.dlgManager.initializeFactorList(this.lstFactorVariables, this.filePath);
        this.dlgManager.initializeNumericList(this.lstNumericVariables, this.filePath);
    }

    protected void buttonPressed(int bID) {
        if (bID == 0) {
            this.okPressed();
        } else if (1 == bID) {
            this.cancelPressed();
        } else if (8 == bID) {
            this.cmbAlternative.select(2);
            this.cmbProcedure.select(0);
            this.btnGroupingVariable.setText("Add");
            this.btnAddVariable.setText("Add");
            this.btnAddVariable.setEnabled(false);
            this.btnConstructInterval.setSelection(false);
            this.txtConfidenceInterval.setSelection(95);
            this.txtConfidenceInterval.setEnabled(false);
            this.table.removeAll();
            this.lstNumericVariables.removeAll();
            this.lstFactorVariables.removeAll();
            this.dlgManager.initializeFactorList(this.lstFactorVariables, this.filePath);
            this.dlgManager.initializeNumericList(this.lstNumericVariables, this.filePath);
            this.txtGroupingVariable.setText("");
        }
    }

    protected void okPressed() {
        String dataFileName = this.filePath.replace(File.separator, "/");
        String outputFolder = StarAnalysisUtilities.createOutputFolder(this.filePath, "TestOnProportion");
        OperationProgressDialog rInfo = new OperationProgressDialog(this.getShell(), "Performing Analysis");
        rInfo.open();
        String resultFolderPath = outputFolder.replace(File.separator, "/");
        String[] varX = new String[this.table.getItemCount()];
        String[] varY = new String[this.table.getItemCount()];
        double[] testVal = new double[this.table.getItemCount()];
        String procedure = "one";
        String grp = null;
        int i = 0;
        while (i < this.table.getItemCount()) {
            varX[i] = this.table.getItem(i).getText(0);
            if (this.cmbProcedure.getText().equals("One Sample")) {
                testVal[i] = Double.parseDouble(this.table.getItem(i).getText(1));
                varY = null;
                procedure = "one";
            } else if (this.cmbProcedure.getText().equals("Two Related Sample")) {
                varY[i] = this.table.getItem(i).getText(1);
                testVal = null;
                procedure = "paired";
            } else {
                varY = null;
                testVal = null;
                procedure = "independent";
                grp = this.txtGroupingVariable.getText();
            }
            ++i;
        }
        String alternative = this.cmbAlternative.getText();
        boolean confInterval = this.btnConstructInterval.getSelection();
        int confLevel = this.txtConfidenceInterval.getSelection();
        ProjectExplorerView.rJavaManager.getSTARAnalysisManager().doProportionTest(dataFileName, resultFolderPath, procedure, varX, varY, grp, testVal, alternative, confInterval, confLevel);
        StarAnalysisUtilities.openAndRefreshFolder(outputFolder);
        rInfo.close();
    }

    protected void createButtonsForButtonBar(Composite parent) {
        Button button = this.createButton(parent, 8, "New button", false);
        button.setText("Reset");
        this.createButton(parent, 0, IDialogConstants.OK_LABEL, true);
        this.createButton(parent, 1, IDialogConstants.CANCEL_LABEL, false);
    }

    protected Point getInitialSize() {
        return new Point(531, 413);
    }

    public class proportionValidate
    extends DialogListValidator {
        Combo cmbProcedure;
        Button btnGroupingVariable;

        public proportionValidate(Combo cmbProcedure, Button btnGroupingVariable) {
            this.btnGroupingVariable = btnGroupingVariable;
            this.cmbProcedure = cmbProcedure;
        }

        @Override
        public boolean validate() {
            this.btnGroupingVariable.setEnabled(this.cmbProcedure.getText().equals("Independent Sample"));
            return this.btnGroupingVariable.getText().equals("Independent Sample");
        }
    }
}

