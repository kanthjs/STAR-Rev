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
 *  org.eclipse.swt.layout.GridData
 *  org.eclipse.swt.layout.GridLayout
 *  org.eclipse.swt.widgets.Button
 *  org.eclipse.swt.widgets.Combo
 *  org.eclipse.swt.widgets.Composite
 *  org.eclipse.swt.widgets.Control
 *  org.eclipse.swt.widgets.Group
 *  org.eclipse.swt.widgets.Label
 *  org.eclipse.swt.widgets.Layout
 *  org.eclipse.swt.widgets.List
 *  org.eclipse.swt.widgets.Shell
 *  org.eclipse.swt.widgets.TabFolder
 *  org.eclipse.swt.widgets.TabItem
 *  org.eclipse.swt.widgets.Text
 */
package org.irri.breedingtool.star.analysis.dialog;

import java.io.File;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
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
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;
import org.irri.breedingtool.application.handler.PartStackHandler;
import org.irri.breedingtool.datamanipulation.dialog.OperationProgressDialog;
import org.irri.breedingtool.manager.impl.DataManipulationManager;
import org.irri.breedingtool.projectexplorer.view.ProjectExplorerView;
import org.irri.breedingtool.utility.DialogFormUtility;
import org.irri.breedingtool.utility.DialogListValidator;
import org.irri.breedingtool.utility.StarAnalysisUtilities;

public class ChiSquareIndependenceTestDialog
extends Dialog {
    private String filePath = PartStackHandler.getActivePart().getElementId().toString();
    private List lstAvailableData;
    private List lstRowVariables;
    private List lstColumnVariables;
    private Button btnExpected;
    private Button btnRow;
    private Button btnColumn;
    private Button btnTotal;
    private Button btnStandardize;
    private Button btnPhiCoefficient;
    private Button btnCramersV;
    private Button btnContingencyCoefficient;
    private DialogFormUtility listManager = new DialogFormUtility();
    private DataManipulationManager dataManipulationManager = new DataManipulationManager();
    private Button btnOk;
    private TabItem tbtmCellDisplay;
    private Composite cmpOptions;
    private Group grpCount;
    private Group grpPercentages;
    private Group grpResidual;
    private Group grpNominal;
    private Button btnAddRowVariables;
    private Button btnAddColumnVariables;
    private Label lblTypeOfInput;
    private Combo cmboType;
    private Button btnAddFreq;
    private Text txtFreq;
    private Label lblFrequencyVariable;
    private Label lblNewLabel;

    public ChiSquareIndependenceTestDialog(Shell parentShell) {
        super(parentShell);
        this.setShellStyle(2288);
    }

    protected void configureShell(Shell shell) {
        super.configureShell(shell);
        shell.setText("Test of Independence :" + new File(this.filePath).getName());
    }

    protected Control createDialogArea(Composite parent) {
        Composite container = (Composite)super.createDialogArea(parent);
        GridLayout gl_container = new GridLayout(1, false);
        gl_container.marginHeight = 8;
        gl_container.marginWidth = 8;
        container.setLayout((Layout)gl_container);
        TabFolder tabFolder = new TabFolder(container, 0);
        tabFolder.setLayoutData((Object)new GridData(4, 4, true, true, 1, 1));
        TabItem tbtmDescription = new TabItem(tabFolder, 0);
        tbtmDescription.setText("Description");
        Composite composite = new Composite((Composite)tabFolder, 0);
        tbtmDescription.setControl((Control)composite);
        composite.setLayout((Layout)new GridLayout(3, false));
        this.lblTypeOfInput = new Label(composite, 0);
        this.lblTypeOfInput.setText("Type of Input");
        this.cmboType = new Combo(composite, 8);
        this.cmboType.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (ChiSquareIndependenceTestDialog.this.cmboType.getSelectionIndex() == 0) {
                    ChiSquareIndependenceTestDialog.this.lblFrequencyVariable.setEnabled(false);
                    ChiSquareIndependenceTestDialog.this.btnAddFreq.setEnabled(false);
                    ChiSquareIndependenceTestDialog.this.txtFreq.setEnabled(false);
                } else {
                    ChiSquareIndependenceTestDialog.this.lblFrequencyVariable.setEnabled(true);
                    ChiSquareIndependenceTestDialog.this.btnAddFreq.setEnabled(true);
                    ChiSquareIndependenceTestDialog.this.txtFreq.setEnabled(true);
                    int i = ChiSquareIndependenceTestDialog.this.lstColumnVariables.getItemCount() - 1;
                    while (i > 0) {
                        ChiSquareIndependenceTestDialog.this.lstAvailableData.add(ChiSquareIndependenceTestDialog.this.lstColumnVariables.getItem(i));
                        ChiSquareIndependenceTestDialog.this.lstColumnVariables.remove(i);
                        --i;
                    }
                    i = ChiSquareIndependenceTestDialog.this.lstRowVariables.getItemCount() - 1;
                    while (i > 0) {
                        ChiSquareIndependenceTestDialog.this.lstAvailableData.add(ChiSquareIndependenceTestDialog.this.lstRowVariables.getItem(i));
                        ChiSquareIndependenceTestDialog.this.lstRowVariables.remove(i);
                        --i;
                    }
                    ChiSquareIndependenceTestDialog.this.btnAddColumnVariables.setEnabled(false);
                    ChiSquareIndependenceTestDialog.this.btnAddRowVariables.setEnabled(false);
                }
            }
        });
        this.cmboType.setItems(new String[]{"Raw Data", "Summary Data"});
        GridData gd_cmboType = new GridData(16384, 0x1000000, true, false, 2, 1);
        gd_cmboType.widthHint = 100;
        this.cmboType.setLayoutData((Object)gd_cmboType);
        this.cmboType.select(0);
        Label lblAvailableData = new Label(composite, 0);
        lblAvailableData.setText("Available Data");
        new Label(composite, 0);
        Label lblRowVariabless = new Label(composite, 0);
        lblRowVariabless.setLayoutData((Object)new GridData(16384, 0x1000000, true, false, 1, 1));
        lblRowVariabless.setText("Row Variables(s)");
        this.lstAvailableData = new List(composite, 2818);
        GridData gd_lstAvailableData = new GridData(4, 4, true, true, 1, 12);
        gd_lstAvailableData.widthHint = 50;
        gd_lstAvailableData.heightHint = 120;
        this.lstAvailableData.setLayoutData((Object)gd_lstAvailableData);
        new Label(composite, 0);
        this.lstRowVariables = new List(composite, 2818);
        GridData gd_lstRowVariables = new GridData(4, 4, true, true, 1, 5);
        gd_lstRowVariables.widthHint = 50;
        gd_lstRowVariables.heightHint = 60;
        this.lstRowVariables.setLayoutData((Object)gd_lstRowVariables);
        new Label(composite, 0);
        new Label(composite, 0);
        this.btnAddRowVariables = new Button(composite, 0);
        GridData gd_btnAddRowVariables = new GridData(4, 0x1000000, false, false, 1, 1);
        gd_btnAddRowVariables.heightHint = 23;
        gd_btnAddRowVariables.widthHint = 90;
        this.btnAddRowVariables.setLayoutData((Object)gd_btnAddRowVariables);
        this.btnAddRowVariables.setText("Add");
        new Label(composite, 0);
        new Label(composite, 0);
        Label lblColumnVariables = new Label(composite, 0);
        lblColumnVariables.setText("Column Variable(s)");
        new Label(composite, 0);
        this.lstColumnVariables = new List(composite, 2818);
        GridData gd_lstColumnVariables = new GridData(4, 4, true, true, 1, 4);
        gd_lstColumnVariables.widthHint = 50;
        gd_lstColumnVariables.heightHint = 60;
        this.lstColumnVariables.setLayoutData((Object)gd_lstColumnVariables);
        new Label(composite, 0);
        this.btnAddColumnVariables = new Button(composite, 0);
        GridData gd_btnAddColumnVariables = new GridData(4, 0x1000000, false, false, 1, 1);
        gd_btnAddColumnVariables.heightHint = 23;
        gd_btnAddColumnVariables.widthHint = 90;
        this.btnAddColumnVariables.setLayoutData((Object)gd_btnAddColumnVariables);
        this.btnAddColumnVariables.setText("Add");
        new Label(composite, 0);
        this.tbtmCellDisplay = new TabItem(tabFolder, 0);
        this.tbtmCellDisplay.setText("Options");
        this.cmpOptions = new Composite((Composite)tabFolder, 0);
        this.tbtmCellDisplay.setControl((Control)this.cmpOptions);
        this.cmpOptions.setLayout((Layout)new GridLayout(2, false));
        Group grpStatistics = new Group(this.cmpOptions, 0);
        grpStatistics.setLayout((Layout)new GridLayout(1, false));
        GridData gd_grpStatistics = new GridData(4, 4, true, true, 1, 1);
        gd_grpStatistics.widthHint = 243;
        grpStatistics.setLayoutData((Object)gd_grpStatistics);
        grpStatistics.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        grpStatistics.setText("Statistics");
        this.grpNominal = new Group((Composite)grpStatistics, 0);
        this.grpNominal.setText("Nominal:");
        this.grpNominal.setLayout((Layout)new GridLayout(1, false));
        GridData gd_grpNominal = new GridData(4, 128, true, true, 1, 1);
        gd_grpNominal.heightHint = 115;
        this.grpNominal.setLayoutData((Object)gd_grpNominal);
        this.btnPhiCoefficient = new Button((Composite)this.grpNominal, 32);
        this.btnPhiCoefficient.setLayoutData((Object)new GridData(4, 4, true, true, 1, 1));
        this.btnPhiCoefficient.setText("Phi Coefficient");
        this.btnCramersV = new Button((Composite)this.grpNominal, 32);
        this.btnCramersV.setLayoutData((Object)new GridData(4, 4, true, true, 1, 1));
        this.btnCramersV.setText("Cramers V");
        this.btnContingencyCoefficient = new Button((Composite)this.grpNominal, 32);
        this.btnContingencyCoefficient.setLayoutData((Object)new GridData(4, 4, true, true, 1, 1));
        this.btnContingencyCoefficient.setText("Contingency Coefficient");
        Group grpCellDisplay = new Group(this.cmpOptions, 0);
        grpCellDisplay.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        grpCellDisplay.setLayout((Layout)new GridLayout(1, false));
        GridData gd_grpCellDisplay = new GridData(4, 4, true, true, 1, 1);
        gd_grpCellDisplay.widthHint = 256;
        grpCellDisplay.setLayoutData((Object)gd_grpCellDisplay);
        grpCellDisplay.setText("Cell Display");
        this.grpCount = new Group((Composite)grpCellDisplay, 0);
        this.grpCount.setLayout((Layout)new GridLayout(1, false));
        this.grpCount.setLayoutData((Object)new GridData(4, 4, true, true, 1, 1));
        this.grpCount.setText("Count:");
        this.btnExpected = new Button((Composite)this.grpCount, 32);
        this.btnExpected.setLayoutData((Object)new GridData(16384, 0x1000000, true, true, 1, 1));
        this.btnExpected.setText("Expected");
        this.grpPercentages = new Group((Composite)grpCellDisplay, 0);
        this.grpPercentages.setLayout((Layout)new GridLayout(1, false));
        this.grpPercentages.setLayoutData((Object)new GridData(4, 4, true, true, 1, 1));
        this.grpPercentages.setText("Percentages:");
        this.btnRow = new Button((Composite)this.grpPercentages, 32);
        this.btnRow.setLayoutData((Object)new GridData(16384, 0x1000000, true, true, 1, 1));
        this.btnRow.setText("Row");
        this.btnColumn = new Button((Composite)this.grpPercentages, 32);
        this.btnColumn.setLayoutData((Object)new GridData(16384, 0x1000000, true, true, 1, 1));
        this.btnColumn.setText("Column");
        this.btnTotal = new Button((Composite)this.grpPercentages, 32);
        this.btnTotal.setLayoutData((Object)new GridData(16384, 0x1000000, true, true, 1, 1));
        this.btnTotal.setText("Total");
        this.grpResidual = new Group((Composite)grpCellDisplay, 0);
        this.grpResidual.setLayout((Layout)new GridLayout(1, false));
        this.grpResidual.setLayoutData((Object)new GridData(4, 4, true, true, 1, 1));
        this.grpResidual.setText("Residual:");
        this.btnStandardize = new Button((Composite)this.grpResidual, 32);
        this.btnStandardize.setText("Standardize");
        new Label(composite, 0);
        this.lblFrequencyVariable = new Label(composite, 0);
        this.lblFrequencyVariable.setEnabled(false);
        this.lblFrequencyVariable.setText("Frequency Variable:");
        this.btnAddFreq = new Button(composite, 0);
        this.btnAddFreq.setEnabled(false);
        GridData gd_btnAddFreq = new GridData(4, 0x1000000, false, false, 1, 1);
        gd_btnAddFreq.heightHint = 23;
        gd_btnAddFreq.widthHint = 90;
        this.btnAddFreq.setLayoutData((Object)gd_btnAddFreq);
        this.btnAddFreq.setText("Add");
        this.txtFreq = new Text(composite, 2048);
        this.txtFreq.setBackground(SWTResourceManager.getColor(1));
        this.txtFreq.setEnabled(false);
        GridData gd_txtFreq = new GridData(4, 4, true, false, 1, 1);
        gd_txtFreq.heightHint = 15;
        this.txtFreq.setLayoutData((Object)gd_txtFreq);
        this.lblNewLabel = new Label(composite, 0);
        new Label(composite, 0);
        new Label(composite, 0);
        this.initializeContent();
        return container;
    }

    public void initializeContent() {
        this.listManager.initializeSingleButtonList(this.lstAvailableData, this.lstColumnVariables, this.btnAddColumnVariables, new validateAdding(this.lstColumnVariables));
        this.listManager.initializeVariableList(this.lstAvailableData, this.filePath);
        this.listManager.initializeSingleButtonList(this.lstAvailableData, this.lstRowVariables, this.btnAddRowVariables, new validateAdding(this.lstRowVariables));
        this.listManager.initializeSingleSelectionList(this.lstAvailableData, this.txtFreq, this.btnAddFreq, new validateType(this.cmboType, this.btnAddFreq));
    }

    protected void buttonPressed(int buttonId) {
        if (buttonId == 0) {
            this.okPressed();
        } else if (buttonId == 1) {
            this.cancelPressed();
        } else if (buttonId == 8) {
            this.lstAvailableData.removeAll();
            this.lstColumnVariables.removeAll();
            this.lstRowVariables.removeAll();
            this.txtFreq.setText("");
            this.btnAddRowVariables.setText("Add");
            this.btnAddColumnVariables.setText("Add");
            this.btnAddFreq.setText("Add");
            this.btnAddRowVariables.setEnabled(false);
            this.btnAddColumnVariables.setEnabled(false);
            this.btnAddFreq.setEnabled(false);
            this.cmboType.setText("Raw Data");
            this.btnColumn.setSelection(false);
            this.btnContingencyCoefficient.setSelection(false);
            this.btnCramersV.setSelection(false);
            this.btnExpected.setSelection(false);
            this.btnPhiCoefficient.setSelection(false);
            this.btnRow.setSelection(false);
            this.btnStandardize.setSelection(false);
            this.btnTotal.setSelection(false);
            this.listManager.setCheckBoxesToBoolean(false, new Composite[]{this.grpResidual, this.grpCount, this.grpPercentages, this.grpNominal});
            this.listManager.initializeVariableList(this.lstAvailableData, this.filePath);
        }
    }

    protected void okPressed() {
        if (!this.txtFreq.getText().isEmpty()) {
            String isNumeric = "TRUE";
            isNumeric = this.dataManipulationManager.isNumeric(this.filePath, this.txtFreq.getText());
            if (isNumeric.equals("FALSE")) {
                MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"The Frequency Variable should be a numeric variable");
                return;
            }
        }
        if (this.lstRowVariables.getItemCount() <= 0 || this.lstColumnVariables.getItemCount() <= 0) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"Atleast one item in Column Variables and Row Variables and\n atleast one option is chosen in the Option tab");
            return;
        }
        this.btnOk.setEnabled(false);
        String dataFileName = this.filePath;
        String outFolder = StarAnalysisUtilities.createOutputFolder(this.filePath, "ChiSquareTestOfIndependence");
        String type = "raw";
        type = this.cmboType.getSelectionIndex() == 0 ? "raw" : "summary";
        OperationProgressDialog rInfo = new OperationProgressDialog(this.getShell(), "Performing Analysis");
        rInfo.open();
        String outFileName = String.valueOf(outFolder) + "Output.txt";
        StarAnalysisUtilities.testVarArgs(dataFileName.replace(File.separator, "/"), outFileName.replace(File.separator, "/"), this.lstRowVariables.getItems(), this.lstColumnVariables.getItems(), this.btnExpected.getSelection(), this.btnStandardize.getSelection(), this.btnTotal.getSelection(), this.btnRow.getSelection(), this.btnColumn.getSelection(), this.btnPhiCoefficient.getSelection(), this.btnCramersV.getSelection(), this.btnContingencyCoefficient.getSelection());
        ProjectExplorerView.rJavaManager.getSTARAnalysisManager().doChiSqTestOfIndependence(dataFileName.replace(File.separator, "/"), outFileName.replace(File.separator, "/"), type, this.lstRowVariables.getItems(), this.lstColumnVariables.getItems(), this.txtFreq.getText(), true, this.btnExpected.getSelection(), this.btnStandardize.getSelection(), this.btnTotal.getSelection(), this.btnRow.getSelection(), this.btnColumn.getSelection(), this.btnPhiCoefficient.getSelection(), this.btnCramersV.getSelection(), this.btnContingencyCoefficient.getSelection());
        this.getShell().setMinimized(true);
        StarAnalysisUtilities.openAndRefreshFolder(outFolder);
        this.btnOk.setEnabled(true);
        rInfo.close();
    }

    protected void createButtonsForButtonBar(Composite parent) {
        this.createButton(parent, 8, "Reset", false);
        this.btnOk = this.createButton(parent, 0, IDialogConstants.OK_LABEL, true);
        this.createButton(parent, 1, IDialogConstants.CANCEL_LABEL, false);
    }

    protected Point getInitialSize() {
        return new Point(471, 442);
    }

    public class validateAdding
    extends DialogListValidator {
        private List varList;

        public validateAdding(List list) {
            this.varList = list;
        }

        @Override
        public boolean validate() {
            return ChiSquareIndependenceTestDialog.this.cmboType.getSelectionIndex() != 1 || this.varList.getItemCount() <= 0;
        }

        @Override
        public boolean performAddProcess() {
            if (ChiSquareIndependenceTestDialog.this.cmboType.getSelectionIndex() == 1 && ChiSquareIndependenceTestDialog.this.lstAvailableData.getSelectionCount() > 1) {
                MessageDialog.openError((Shell)ChiSquareIndependenceTestDialog.this.getShell(), (String)"Error", (String)"Cannot add multiple variable if type of input is Summary Data");
                return false;
            }
            return true;
        }
    }

    public class validateType
    extends DialogListValidator {
        Combo cmbType;
        Button btnAddFreq;

        public validateType(Combo cmbType, Button btnAddFreq) {
            this.btnAddFreq = btnAddFreq;
            this.cmbType = cmbType;
        }

        @Override
        public boolean validate() {
            this.btnAddFreq.setEnabled(!this.cmbType.getText().equals("Raw Data"));
            return !this.cmbType.getText().equals("Raw Data");
        }
    }
}

