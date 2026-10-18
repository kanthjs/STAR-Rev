/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.jface.dialogs.Dialog
 *  org.eclipse.jface.dialogs.IDialogConstants
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
import org.irri.breedingtool.manager.impl.DataManipulationManager;
import org.irri.breedingtool.projectexplorer.view.ProjectExplorerView;
import org.irri.breedingtool.utility.DialogFormUtility;
import org.irri.breedingtool.utility.DialogListValidator;
import org.irri.breedingtool.utility.StarAnalysisUtilities;
import org.irri.breedingtool.utility.StarRandomizationUtilities;

public class ChiSquareGoodnessOfFitDialog
extends Dialog {
    private Table table;
    private String filePath = PartStackHandler.getActiveElementID();
    private Combo cmbInputType;
    private List lstAvailableVariables;
    private Button btnAllCategory;
    private Button btnSpecify;
    private Button button_2;
    private Text txtTestVariable;
    private Text txtFrequencyVariable;
    private Button btnAddFrequency;
    private DialogFormUtility dlgManager = new DialogFormUtility();
    private Button btnAddTest;
    private int EDITABLECOLUMN;
    private Label lblFrequencyVariable;

    public ChiSquareGoodnessOfFitDialog(Shell parentShell) {
        super(parentShell);
        this.setBlockOnOpen(false);
        this.setShellStyle(2256);
    }

    protected void configureShell(Shell shell) {
        super.configureShell(shell);
        shell.setText("Goodness of Fit Test : " + new File(this.filePath).getName());
    }

    protected Control createDialogArea(Composite parent) {
        Composite container = (Composite)super.createDialogArea(parent);
        container.setTouchEnabled(true);
        GridLayout gl_container = new GridLayout(4, false);
        gl_container.marginHeight = 8;
        gl_container.marginWidth = 8;
        container.setLayout((Layout)gl_container);
        Label lblTypeOfInput = new Label(container, 0);
        lblTypeOfInput.setText("Type of Input");
        this.cmbInputType = new Combo(container, 8);
        this.cmbInputType.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                ChiSquareGoodnessOfFitDialog.this.txtFrequencyVariable.setEditable(false);
                ChiSquareGoodnessOfFitDialog.this.btnAddFrequency.setEnabled(false);
                ChiSquareGoodnessOfFitDialog.this.lblFrequencyVariable.setEnabled(false);
            }
        });
        GridData gd_cmbInputType = new GridData(4, 0x1000000, true, false, 2, 1);
        gd_cmbInputType.widthHint = 189;
        this.cmbInputType.setLayoutData((Object)gd_cmbInputType);
        this.cmbInputType.setVisibleItemCount(2);
        this.cmbInputType.setItems(new String[]{"Raw Data", "Summary Data"});
        this.cmbInputType.select(0);
        new Label(container, 0);
        Label lblAvailableVariables = new Label(container, 0);
        GridData gd_lblAvailableVariables = new GridData(4, 4, true, false, 1, 1);
        gd_lblAvailableVariables.heightHint = 16;
        lblAvailableVariables.setLayoutData((Object)gd_lblAvailableVariables);
        lblAvailableVariables.setText("Available Variables");
        new Label(container, 0);
        new Label(container, 0);
        new Label(container, 0);
        this.lstAvailableVariables = new List(container, 2816);
        this.lstAvailableVariables.setEnabled(true);
        GridData gd_lstAvailableVariables = new GridData(4, 4, true, false, 1, 3);
        gd_lstAvailableVariables.widthHint = 210;
        gd_lstAvailableVariables.heightHint = 60;
        this.lstAvailableVariables.setLayoutData((Object)gd_lstAvailableVariables);
        this.btnAddTest = new Button(container, 0);
        GridData gd_btnAddTest = new GridData(16384, 0x1000000, false, false, 1, 1);
        gd_btnAddTest.widthHint = 90;
        this.btnAddTest.setLayoutData((Object)gd_btnAddTest);
        this.btnAddTest.setText("Add");
        Label lblTestVariable = new Label(container, 0);
        lblTestVariable.setText("Test Variable");
        this.txtTestVariable = new Text(container, 2056);
        this.txtTestVariable.setForeground(SWTResourceManager.getColor(2));
        this.txtTestVariable.setBackground(SWTResourceManager.getColor(1));
        GridData gd_txtTestVariable = new GridData(4, 0x1000000, true, false, 1, 1);
        gd_txtTestVariable.widthHint = 200;
        gd_txtTestVariable.heightHint = 15;
        this.txtTestVariable.setLayoutData((Object)gd_txtTestVariable);
        this.btnAddFrequency = new Button(container, 0);
        GridData gd_btnAddFrequency = new GridData(16384, 0x1000000, false, false, 1, 1);
        gd_btnAddFrequency.widthHint = 90;
        this.btnAddFrequency.setLayoutData((Object)gd_btnAddFrequency);
        this.btnAddFrequency.setEnabled(false);
        this.btnAddFrequency.setText("Add");
        this.lblFrequencyVariable = new Label(container, 0);
        this.lblFrequencyVariable.setEnabled(false);
        this.lblFrequencyVariable.setText("Frequency Variable");
        this.txtFrequencyVariable = new Text(container, 2056);
        this.txtFrequencyVariable.setForeground(SWTResourceManager.getColor(2));
        this.txtFrequencyVariable.setBackground(SWTResourceManager.getColor(1));
        this.txtFrequencyVariable.setTouchEnabled(true);
        GridData gd_txtFrequencyVariable = new GridData(4, 0x1000000, true, false, 1, 1);
        gd_txtFrequencyVariable.heightHint = 15;
        this.txtFrequencyVariable.setLayoutData((Object)gd_txtFrequencyVariable);
        new Label(container, 0);
        new Label(container, 0);
        new Label(container, 0);
        Group grpExpectedValues = new Group(container, 0);
        grpExpectedValues.setLayout((Layout)new GridLayout(1, false));
        grpExpectedValues.setLayoutData((Object)new GridData(4, 4, true, true, 4, 1));
        grpExpectedValues.setText("Expected Values");
        this.btnAllCategory = new Button((Composite)grpExpectedValues, 16);
        this.btnAllCategory.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                ChiSquareGoodnessOfFitDialog.this.table.setEnabled(false);
            }
        });
        this.btnAllCategory.setLayoutData((Object)new GridData(16384, 0x1000000, true, false, 1, 1));
        this.btnAllCategory.setSelection(true);
        this.btnAllCategory.setText("All categories are equal");
        this.btnSpecify = new Button((Composite)grpExpectedValues, 16);
        this.btnSpecify.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                ChiSquareGoodnessOfFitDialog.this.table.setEnabled(true);
            }
        });
        this.btnSpecify.setLayoutData((Object)new GridData(16384, 0x1000000, true, false, 1, 1));
        this.btnSpecify.setText("Specify");
        this.table = new Table((Composite)grpExpectedValues, 67584);
        this.table.setLayoutData((Object)new GridData(4, 4, true, true, 1, 1));
        this.table.setEnabled(false);
        this.table.setHeaderVisible(true);
        this.table.setLinesVisible(true);
        TableColumn tblclmnNewColumn = new TableColumn(this.table, 0x1000000);
        tblclmnNewColumn.setWidth(88);
        tblclmnNewColumn.setText("Category");
        TableColumn tblclmnNewColumn_1 = new TableColumn(this.table, 0x1000000);
        tblclmnNewColumn_1.setWidth(193);
        tblclmnNewColumn_1.setText("Expected Frequency/Ratio");
        Spinner sprLevel = new Spinner((Composite)this.table, 0);
        sprLevel.setIncrement(1);
        this.initializeForm();
        return container;
    }

    void initializeForm() {
        this.dlgManager.initializeSingleSelectionList(this.lstAvailableVariables, this.txtTestVariable, this.btnAddTest);
        this.dlgManager.initializeSingleSelectionList(this.lstAvailableVariables, this.txtFrequencyVariable, this.btnAddFrequency, new goodnessOfFitListValidator(this.cmbInputType, this.lblFrequencyVariable));
        this.dlgManager.initializeVariableList(this.lstAvailableVariables, this.filePath);
        this.txtTestVariable.addModifyListener(new ModifyListener(){

            public void modifyText(ModifyEvent e) {
                ChiSquareGoodnessOfFitDialog.this.table.removeAll();
                if (ChiSquareGoodnessOfFitDialog.this.txtTestVariable.getText().equals("")) {
                    return;
                }
                DataManipulationManager dataMan = new DataManipulationManager();
                String[] envtLevels = DataManipulationManager.getEnvtLevels(ChiSquareGoodnessOfFitDialog.this.txtTestVariable.getText(), new File(ChiSquareGoodnessOfFitDialog.this.filePath));
                int i = 0;
                while (i < envtLevels.length) {
                    TableItem tableItem = new TableItem(ChiSquareGoodnessOfFitDialog.this.table, 0);
                    tableItem.setText(new String[]{envtLevels[i], "1"});
                    ++i;
                }
            }
        });
        this.cmbInputType.addModifyListener(new ModifyListener(){

            public void modifyText(ModifyEvent e) {
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
                if ((item = ChiSquareGoodnessOfFitDialog.this.table.getSelection()[0]) == null) {
                    return;
                }
                Point mPoint = new Point(e.x, e.y);
                int i = 0;
                while (i < item.getParent().getColumnCount()) {
                    if (item.getBounds(i).contains(mPoint)) {
                        ChiSquareGoodnessOfFitDialog.this.EDITABLECOLUMN = i;
                    }
                    ++i;
                }
                System.out.println(ChiSquareGoodnessOfFitDialog.this.EDITABLECOLUMN);
                if (ChiSquareGoodnessOfFitDialog.this.EDITABLECOLUMN == 0) {
                    ChiSquareGoodnessOfFitDialog.this.EDITABLECOLUMN = 1;
                }
                if (ChiSquareGoodnessOfFitDialog.this.EDITABLECOLUMN == 1) {
                    Spinner newEditor = new Spinner((Composite)ChiSquareGoodnessOfFitDialog.this.table, 0);
                    newEditor.setIncrement(1);
                    newEditor.setMaximum(9999999);
                    newEditor.setMinimum(0);
                    newEditor.setSelection(Integer.valueOf(item.getText(ChiSquareGoodnessOfFitDialog.this.EDITABLECOLUMN)).intValue());
                    newEditor.addModifyListener(new ModifyListener(){

                        public void modifyText(ModifyEvent me) {
                            Spinner spinner = (Spinner)editor.getEditor();
                            editor.getItem().setText(ChiSquareGoodnessOfFitDialog.this.EDITABLECOLUMN, spinner.getText());
                            StarRandomizationUtilities.validateSpinner(spinner);
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
                    editor.setEditor((Control)newEditor, item, ChiSquareGoodnessOfFitDialog.this.EDITABLECOLUMN);
                }
            }
        });
        editor.horizontalAlignment = 16384;
        editor.grabHorizontal = true;
        editor.minimumWidth = 70;
    }

    protected void okPressed() {
        String dataFileName = this.filePath.replace(File.separator, "/");
        String freqVar = null;
        String inputType = this.cmbInputType.getText().equals("Raw Data") ? "raw" : "summary";
        String testvar = this.txtTestVariable.getText();
        if (this.cmbInputType.getText().equals("Raw Data")) {
            inputType = "raw";
            freqVar = null;
        } else {
            inputType = "summary";
            freqVar = this.txtFrequencyVariable.getText();
        }
        boolean categoryEqual = this.btnAllCategory.getSelection();
        String[] specifiedCategory = new String[this.table.getItemCount()];
        int[] specifiedExpFreq = new int[this.table.getItemCount()];
        int i = 0;
        while (i < this.table.getItemCount()) {
            specifiedCategory[i] = this.table.getItem(i).getText(0);
            specifiedExpFreq[i] = Integer.parseInt(this.table.getItem(i).getText(1));
            ++i;
        }
        if (this.btnAllCategory.getSelection()) {
            specifiedCategory = null;
            specifiedExpFreq = null;
        }
        String resultFolderPath = StarAnalysisUtilities.createOutputFolder(this.filePath, "ChiSquareGoodnessOfFit");
        OperationProgressDialog rInfo = new OperationProgressDialog(this.getShell(), "Performing Analysis");
        rInfo.open();
        ProjectExplorerView.rJavaManager.getSTARAnalysisManager().doChiSqGoodnessOfFit(dataFileName, resultFolderPath.replace(File.separator, "/"), inputType, testvar, freqVar, categoryEqual, specifiedCategory, specifiedExpFreq);
        StarAnalysisUtilities.openAndRefreshFolder(resultFolderPath);
        rInfo.close();
    }

    protected void buttonPressed(int bID) {
        if (bID == 0) {
            this.okPressed();
        } else if (1 == bID) {
            this.cancelPressed();
        } else if (8 == bID) {
            this.table.removeAll();
            this.cmbInputType.select(0);
            this.lstAvailableVariables.removeAll();
            this.btnAllCategory.setSelection(true);
            this.btnSpecify.setSelection(false);
            this.txtTestVariable.setText("");
            this.txtFrequencyVariable.setText("");
            this.btnAddFrequency.setEnabled(false);
            this.btnAddFrequency.setText("Add");
            this.btnAddTest.setText("Add");
            this.btnAddTest.setEnabled(false);
            this.dlgManager.initializeVariableList(this.lstAvailableVariables, this.filePath);
        }
    }

    protected void createButtonsForButtonBar(Composite parent) {
        Button button_1 = this.createButton(parent, 8, "New button", false);
        button_1.setText("Reset");
        this.button_2 = this.createButton(parent, 0, IDialogConstants.OK_LABEL, false);
        this.createButton(parent, 1, IDialogConstants.CANCEL_LABEL, false);
    }

    protected Point getInitialSize() {
        return new Point(479, 403);
    }

    public class goodnessOfFitListValidator
    extends DialogListValidator {
        Combo cmbInputType;
        Label lblFreq;

        public goodnessOfFitListValidator(Combo cmbInputType, Label lbl) {
            this.lblFreq = lbl;
            this.cmbInputType = cmbInputType;
        }

        @Override
        public boolean validate() {
            this.lblFreq.setEnabled(!this.cmbInputType.getText().equals("Raw Data"));
            return !this.cmbInputType.getText().equals("Raw Data");
        }
    }
}

