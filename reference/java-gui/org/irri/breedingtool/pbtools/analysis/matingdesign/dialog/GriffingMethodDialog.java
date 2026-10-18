/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.jface.dialogs.Dialog
 *  org.eclipse.jface.dialogs.IDialogConstants
 *  org.eclipse.jface.dialogs.MessageDialog
 *  org.eclipse.swt.events.DisposeEvent
 *  org.eclipse.swt.events.DisposeListener
 *  org.eclipse.swt.events.SelectionAdapter
 *  org.eclipse.swt.events.SelectionEvent
 *  org.eclipse.swt.events.SelectionListener
 *  org.eclipse.swt.events.ShellListener
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
 *  org.eclipse.swt.widgets.Text
 */
package org.irri.breedingtool.pbtools.analysis.matingdesign.dialog;

import java.io.File;
import java.util.ArrayList;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.events.ShellListener;
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
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;
import org.irri.breedingtool.datamanipulation.dialog.OperationProgressDialog;
import org.irri.breedingtool.manager.impl.DataManipulationManager;
import org.irri.breedingtool.projectexplorer.view.ProjectExplorerView;
import org.irri.breedingtool.utility.DialogFormUtility;
import org.irri.breedingtool.utility.EnvVarValidator;
import org.irri.breedingtool.utility.PBToolAnalysisUtilities;
import org.irri.breedingtool.utility.TextVarValidator;
import org.irri.breedingtool.utility.WindowDialogControlUtil;

public class GriffingMethodDialog
extends Dialog {
    String analysisType;
    DataManipulationManager dataManipulationManager = new DataManipulationManager();
    private DialogFormUtility dlgManager = new DialogFormUtility();
    ArrayList<String> varInfo;
    private File file;
    private String[] factorVariables;
    private String[] numericVariables;
    private List numVarList;
    private Button addBtn;
    private Button moveBtn;
    private List factorVarList;
    private Button btnAddRep;
    private Button btnAddP2;
    private Button btnAddP1;
    private Button btnAddEnv;
    private Button performMultiRadio;
    private List responseVarList;
    private Combo designCombo;
    private String[] designs = new String[]{"CRD", "RCB", "Alpha", "RowColumn"};
    private Button btnCross;
    private Button btnAddBlock;
    private Label lblBlock;
    private Label lblRep;
    private String design = "CRD";
    private String[] respvars;
    private String p1 = "NULL";
    private String p2 = "NULL";
    private String rep = "NULL";
    private String block = "NULL";
    private String row = "NULL";
    private String column = "NULL";
    private String cross = "TRUE";
    private String environment = "NULL";
    private String individual = "NULL";
    private String alpha = "NULL";
    private Button performPerSiteRadio;
    private Label lblP2;
    private Label lblP1;
    private Text significanceLevel;
    private ShellListener newShellListener;
    protected boolean reFocused;
    protected boolean pressedOkButton;
    private Label lblLevelOfSignificance;
    private Button btnAddRow;
    private Label lblRow;
    private Button btnAddCol;
    private Label lblCol;
    private Text txtEnvVar;
    private Text txtP1Var;
    private Text txtP2Var;
    private Text txtBlockVar;
    private Text txtRepVar;
    private Text txtRowVar;
    private Text txtColVar;
    private Text text;
    private Label lblEnv;

    public GriffingMethodDialog(Shell parentShell, String analysisType, File file) {
        super(parentShell);
        this.setShellStyle(2256);
        this.setBlockOnOpen(false);
        this.analysisType = analysisType;
        this.file = file;
        this.setFactors();
    }

    protected Control createDialogArea(Composite parent) {
        Composite container = (Composite)super.createDialogArea(parent);
        GridLayout gridLayout = (GridLayout)container.getLayout();
        gridLayout.numColumns = 7;
        this.getShell().setData("analysis", (Object)this.analysisType);
        this.getShell().setData("filePathID", (Object)this.file.getAbsolutePath());
        WindowDialogControlUtil.addWindowDialogToList(this.getShell());
        parent.addDisposeListener(new DisposeListener(){

            public void widgetDisposed(DisposeEvent e) {
                WindowDialogControlUtil.removeWindowDialogToList(GriffingMethodDialog.this.getShell());
            }
        });
        Group group = new Group(container, 0);
        group.setLayout((Layout)new GridLayout(2, false));
        GridData gd_group = new GridData(4, 4, true, true, 5, 1);
        gd_group.heightHint = 46;
        gd_group.widthHint = 318;
        group.setLayoutData((Object)gd_group);
        Label lblTypeOfDesign = new Label((Composite)group, 0);
        GridData gd_lblTypeOfDesign = new GridData(16384, 1024, true, true, 2, 1);
        gd_lblTypeOfDesign.heightHint = 17;
        lblTypeOfDesign.setLayoutData((Object)gd_lblTypeOfDesign);
        lblTypeOfDesign.setText("TYPE OF DESIGN:");
        this.designCombo = new Combo((Composite)group, 8);
        this.designCombo.setItems(new String[]{"Completely Randomized Design (CRD)", "Randomized Complete Block (RCB)", "Alpha-Lattice", "Row-Column"});
        this.designCombo.setLayoutData((Object)new GridData(4, 0x1000000, true, true, 2, 1));
        this.designCombo.select(1);
        this.designCombo.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                GriffingMethodDialog.this.enableFactorButtons(false);
                if (GriffingMethodDialog.this.designCombo.getSelectionIndex() == 1) {
                    GriffingMethodDialog.this.lblBlock.setEnabled(true);
                    GriffingMethodDialog.this.disable("replicate");
                    GriffingMethodDialog.this.disable("row");
                    GriffingMethodDialog.this.disable("column");
                } else if (GriffingMethodDialog.this.designCombo.getSelectionIndex() == 0) {
                    GriffingMethodDialog.this.disable("replicate");
                    GriffingMethodDialog.this.disable("block");
                    GriffingMethodDialog.this.disable("row");
                    GriffingMethodDialog.this.disable("column");
                } else if (GriffingMethodDialog.this.designCombo.getSelectionIndex() == 2) {
                    GriffingMethodDialog.this.lblRep.setEnabled(true);
                    GriffingMethodDialog.this.lblBlock.setEnabled(true);
                    GriffingMethodDialog.this.disable("row");
                    GriffingMethodDialog.this.disable("column");
                } else if (GriffingMethodDialog.this.designCombo.getSelectionIndex() == 3) {
                    GriffingMethodDialog.this.lblRep.setEnabled(true);
                    GriffingMethodDialog.this.lblRow.setEnabled(true);
                    GriffingMethodDialog.this.lblCol.setEnabled(true);
                    GriffingMethodDialog.this.disable("block");
                }
            }
        });
        this.btnCross = new Button((Composite)group, 16);
        this.btnCross.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                GriffingMethodDialog.this.cross = "TRUE";
            }
        });
        this.btnCross.setSelection(true);
        this.btnCross.setLayoutData((Object)new GridData(16384, 128, true, true, 1, 1));
        this.btnCross.setText("Cross");
        Button btnSelf = new Button((Composite)group, 16);
        btnSelf.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                GriffingMethodDialog.this.cross = "FALSE";
            }
        });
        GridData gd_btnSelf = new GridData(16384, 128, true, true, 1, 1);
        gd_btnSelf.widthHint = 124;
        btnSelf.setLayoutData((Object)gd_btnSelf);
        btnSelf.setText("Self");
        Group group_1 = new Group(container, 0);
        group_1.setLayout((Layout)new GridLayout(1, false));
        GridData gd_group_1 = new GridData(4, 4, true, true, 2, 1);
        gd_group_1.heightHint = 35;
        gd_group_1.widthHint = 104;
        group_1.setLayoutData((Object)gd_group_1);
        Label lblDataInput = new Label((Composite)group_1, 0);
        lblDataInput.setLayoutData((Object)new GridData(4, 0x1000000, true, true, 1, 1));
        lblDataInput.setText("DATA INPUT:");
        Button btnPlotMeans = new Button((Composite)group_1, 16);
        btnPlotMeans.setSelection(true);
        btnPlotMeans.setLayoutData((Object)new GridData(4, 0x1000000, true, true, 1, 1));
        btnPlotMeans.setText("Plot Means");
        Button btnDataMeans = new Button((Composite)group_1, 16);
        btnDataMeans.setEnabled(false);
        btnDataMeans.setLayoutData((Object)new GridData(4, 0x1000000, true, true, 1, 1));
        btnDataMeans.setText("Individual Values");
        Label lblNumericVariables = new Label(container, 0);
        GridData gd_lblNumericVariables = new GridData(16384, 0x1000000, false, false, 3, 1);
        gd_lblNumericVariables.widthHint = 194;
        lblNumericVariables.setLayoutData((Object)gd_lblNumericVariables);
        lblNumericVariables.setText("Numeric Variable(s):");
        new Label(container, 0);
        new Label(container, 0);
        Label lblResponseVariables = new Label(container, 0);
        lblResponseVariables.setLayoutData((Object)new GridData(16384, 0x1000000, false, false, 2, 1));
        lblResponseVariables.setText("Response Variable(s):");
        this.numVarList = new List(container, 2818);
        this.numVarList.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (GriffingMethodDialog.this.numVarList.getSelectionCount() > 0) {
                    GriffingMethodDialog.this.enableFactorButtons(false);
                    GriffingMethodDialog.this.enableNumericButtons(true);
                }
            }
        });
        GridData gd_numVarList = new GridData(4, 4, true, false, 2, 2);
        gd_numVarList.heightHint = 50;
        gd_numVarList.widthHint = 45;
        this.numVarList.setLayoutData((Object)gd_numVarList);
        this.numVarList.setItems(this.numericVariables);
        new Label(container, 0);
        new Label(container, 0);
        new Label(container, 0);
        this.responseVarList = new List(container, 2818);
        GridData gd_responseVarList = new GridData(4, 4, true, false, 2, 2);
        gd_responseVarList.heightHint = 50;
        this.responseVarList.setLayoutData((Object)gd_responseVarList);
        new Label(container, 0);
        this.addBtn = new Button(container, 0);
        this.addBtn.setEnabled(false);
        GridData gd_addBtn = new GridData(0x1000000, 0x1000000, false, false, 1, 1);
        gd_addBtn.widthHint = 76;
        this.addBtn.setLayoutData((Object)gd_addBtn);
        this.addBtn.setText("Add");
        new Label(container, 0);
        Label lblFactors = new Label(container, 0);
        GridData gd_lblFactors = new GridData(16384, 0x1000000, false, false, 1, 1);
        gd_lblFactors.widthHint = 65;
        lblFactors.setLayoutData((Object)gd_lblFactors);
        lblFactors.setText("Factors:");
        this.moveBtn = new Button(container, 0);
        GridData gd_moveBtn = new GridData(131072, 0x1000000, true, false, 1, 1);
        gd_moveBtn.heightHint = 24;
        gd_moveBtn.widthHint = 90;
        this.moveBtn.setLayoutData((Object)gd_moveBtn);
        this.moveBtn.setText("Set to Factor");
        Group performAnalysisGroup = new Group(container, 0);
        performAnalysisGroup.setLayout((Layout)new GridLayout(4, false));
        GridData gd_performAnalysisGroup = new GridData(4, 4, true, true, 5, 2);
        gd_performAnalysisGroup.heightHint = 57;
        gd_performAnalysisGroup.widthHint = 71;
        performAnalysisGroup.setLayoutData((Object)gd_performAnalysisGroup);
        this.performPerSiteRadio = new Button((Composite)performAnalysisGroup, 16);
        this.performPerSiteRadio.setLayoutData((Object)new GridData(16384, 0x1000000, false, false, 4, 1));
        this.performPerSiteRadio.setTouchEnabled(false);
        this.performPerSiteRadio.setSelection(true);
        this.performPerSiteRadio.setGrayed(false);
        this.performPerSiteRadio.setEnabled(true);
        this.performPerSiteRadio.setText("Perform Analysis Per Environment");
        this.performMultiRadio = new Button((Composite)performAnalysisGroup, 16);
        this.performMultiRadio.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                String[] levelItems;
                String selectedNumVars = GriffingMethodDialog.this.txtEnvVar.getText();
                if (!GriffingMethodDialog.this.txtEnvVar.getText().isEmpty() && (levelItems = DataManipulationManager.getEnvtLevels(selectedNumVars, GriffingMethodDialog.this.file)).length < 2) {
                    GriffingMethodDialog.this.factorVarList.add(selectedNumVars);
                    GriffingMethodDialog.this.txtEnvVar.setText("");
                    MessageDialog.openWarning((Shell)GriffingMethodDialog.this.getShell(), (String)"Invalid factor", (String)("The environment factor for a Multi-Environment Analysis should have atleast 2(two) levels.\n\n" + selectedNumVars + " has 1 level only. \n\n" + "Please choose a different environment factor."));
                }
            }
        });
        this.performMultiRadio.setLayoutData((Object)new GridData(16384, 0x1000000, false, false, 4, 1));
        this.performMultiRadio.setText("Perform Multiple Environment Analysis");
        this.btnAddEnv = new Button((Composite)performAnalysisGroup, 0);
        this.btnAddEnv.setEnabled(false);
        GridData gd_btnAddEnv = new GridData(16384, 0x1000000, false, false, 1, 1);
        gd_btnAddEnv.widthHint = 62;
        this.btnAddEnv.setLayoutData((Object)gd_btnAddEnv);
        this.btnAddEnv.setText("Add");
        this.lblEnv = new Label((Composite)performAnalysisGroup, 0);
        GridData gd_lblEnv = new GridData(4, 0x1000000, true, false, 2, 1);
        gd_lblEnv.widthHint = 62;
        this.lblEnv.setLayoutData((Object)gd_lblEnv);
        this.lblEnv.setText("Environment:");
        this.txtEnvVar = new Text((Composite)performAnalysisGroup, 2048);
        this.txtEnvVar.setBackground(SWTResourceManager.getColor(1));
        this.txtEnvVar.setEditable(false);
        GridData gd_txtEnvVar = new GridData(4, 4, true, false, 1, 1);
        gd_txtEnvVar.widthHint = 206;
        this.txtEnvVar.setLayoutData((Object)gd_txtEnvVar);
        this.factorVarList = new List(container, 2816);
        this.factorVarList.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (GriffingMethodDialog.this.factorVarList.getSelectionCount() > 0) {
                    GriffingMethodDialog.this.enableFactorButtons(true);
                    GriffingMethodDialog.this.enableNumericButtons(false);
                }
            }
        });
        GridData gd_factorVarList = new GridData(4, 4, true, true, 2, 3);
        gd_factorVarList.widthHint = 53;
        gd_factorVarList.heightHint = 239;
        this.factorVarList.setLayoutData((Object)gd_factorVarList);
        this.factorVarList.setItems(this.factorVariables);
        Group dynamicGroup = new Group(container, 0);
        dynamicGroup.setLayout((Layout)new GridLayout(3, false));
        GridData gd_dynamicGroup = new GridData(4, 4, true, true, 5, 1);
        gd_dynamicGroup.heightHint = 40;
        dynamicGroup.setLayoutData((Object)gd_dynamicGroup);
        this.btnAddP1 = new Button((Composite)dynamicGroup, 0);
        this.btnAddP1.setEnabled(false);
        GridData gd_btnAddP1 = new GridData(16384, 0x1000000, false, false, 1, 1);
        gd_btnAddP1.widthHint = 62;
        this.btnAddP1.setLayoutData((Object)gd_btnAddP1);
        this.btnAddP1.setText("Add");
        this.lblP1 = new Label((Composite)dynamicGroup, 0);
        GridData gd_lblP1 = new GridData(131072, 0x1000000, false, false, 1, 1);
        gd_lblP1.widthHint = 62;
        this.lblP1.setLayoutData((Object)gd_lblP1);
        this.lblP1.setText("P1:");
        this.txtP1Var = new Text((Composite)dynamicGroup, 2048);
        this.txtP1Var.setEditable(false);
        this.txtP1Var.setBackground(SWTResourceManager.getColor(1));
        this.txtP1Var.setLayoutData((Object)new GridData(4, 4, true, false, 1, 1));
        this.btnAddP2 = new Button((Composite)dynamicGroup, 0);
        this.btnAddP2.setEnabled(false);
        GridData gd_btnAddP2 = new GridData(16384, 0x1000000, false, false, 1, 1);
        gd_btnAddP2.widthHint = 62;
        this.btnAddP2.setLayoutData((Object)gd_btnAddP2);
        this.btnAddP2.setText("Add");
        this.lblP2 = new Label((Composite)dynamicGroup, 0);
        GridData gd_lblP2 = new GridData(131072, 0x1000000, false, false, 1, 1);
        gd_lblP2.widthHint = 62;
        this.lblP2.setLayoutData((Object)gd_lblP2);
        this.lblP2.setText("P2:");
        this.txtP2Var = new Text((Composite)dynamicGroup, 2048);
        this.txtP2Var.setEditable(false);
        this.txtP2Var.setBackground(SWTResourceManager.getColor(1));
        this.txtP2Var.setLayoutData((Object)new GridData(4, 4, true, false, 1, 1));
        if (this.analysisType.equals("NCIII")) {
            this.lblP1.setText("Tester:");
            this.lblP2.setText("F2:");
        }
        parent.getShell().setText(this.getDialogName(this.analysisType, this.file));
        Group group_3 = new Group(container, 0);
        group_3.setLayout((Layout)new GridLayout(3, false));
        GridData gd_group_3 = new GridData(4, 4, true, true, 5, 1);
        gd_group_3.heightHint = 110;
        group_3.setLayoutData((Object)gd_group_3);
        this.btnAddBlock = new Button((Composite)group_3, 0);
        this.btnAddBlock.setEnabled(false);
        GridData gd_btnAddBlock = new GridData(16384, 0x1000000, false, false, 1, 1);
        gd_btnAddBlock.widthHint = 62;
        this.btnAddBlock.setLayoutData((Object)gd_btnAddBlock);
        this.btnAddBlock.setText("Add");
        this.lblBlock = new Label((Composite)group_3, 0);
        GridData gd_lblBlock = new GridData(131072, 0x1000000, false, false, 1, 1);
        gd_lblBlock.widthHint = 62;
        this.lblBlock.setLayoutData((Object)gd_lblBlock);
        this.lblBlock.setText("Block:");
        this.txtBlockVar = new Text((Composite)group_3, 2048);
        this.txtBlockVar.setEditable(false);
        this.txtBlockVar.setBackground(SWTResourceManager.getColor(1));
        this.txtBlockVar.setLayoutData((Object)new GridData(4, 4, true, false, 1, 1));
        this.btnAddRep = new Button((Composite)group_3, 0);
        this.btnAddRep.setEnabled(false);
        GridData gd_btnAddRep = new GridData(16384, 0x1000000, false, false, 1, 1);
        gd_btnAddRep.widthHint = 62;
        this.btnAddRep.setLayoutData((Object)gd_btnAddRep);
        this.btnAddRep.setText("Add");
        this.lblRep = new Label((Composite)group_3, 0);
        GridData gd_lblRep = new GridData(131072, 0x1000000, false, false, 1, 1);
        gd_lblRep.widthHint = 62;
        this.lblRep.setLayoutData((Object)gd_lblRep);
        this.lblRep.setText("Replicate:");
        this.txtRepVar = new Text((Composite)group_3, 2048);
        this.txtRepVar.setEditable(false);
        this.txtRepVar.setBackground(SWTResourceManager.getColor(1));
        this.txtRepVar.setLayoutData((Object)new GridData(4, 4, true, false, 1, 1));
        this.btnAddRow = new Button((Composite)group_3, 0);
        this.btnAddRow.setLayoutData((Object)new GridData(4, 0x1000000, false, false, 1, 1));
        this.btnAddRow.setText("Add");
        this.btnAddRow.setEnabled(false);
        this.lblRow = new Label((Composite)group_3, 0);
        GridData gd_lblRow = new GridData(131072, 0x1000000, false, false, 1, 1);
        gd_lblRow.widthHint = 62;
        this.lblRow.setLayoutData((Object)gd_lblRow);
        this.lblRow.setText("Row:");
        this.lblRow.setEnabled(false);
        this.txtRowVar = new Text((Composite)group_3, 2048);
        this.txtRowVar.setEditable(false);
        this.txtRowVar.setBackground(SWTResourceManager.getColor(1));
        this.txtRowVar.setLayoutData((Object)new GridData(4, 4, true, false, 1, 1));
        this.btnAddCol = new Button((Composite)group_3, 0);
        this.btnAddCol.setLayoutData((Object)new GridData(4, 0x1000000, false, false, 1, 1));
        this.btnAddCol.setText("Add");
        this.btnAddCol.setEnabled(false);
        this.lblCol = new Label((Composite)group_3, 0);
        GridData gd_lblCol = new GridData(131072, 0x1000000, false, false, 1, 1);
        gd_lblCol.widthHint = 62;
        this.lblCol.setLayoutData((Object)gd_lblCol);
        this.lblCol.setText("Column:");
        this.lblCol.setEnabled(false);
        this.txtColVar = new Text((Composite)group_3, 2048);
        this.txtColVar.setEditable(false);
        this.txtColVar.setBackground(SWTResourceManager.getColor(1));
        this.txtColVar.setLayoutData((Object)new GridData(4, 4, true, false, 1, 1));
        Button button_4 = new Button((Composite)group_3, 0);
        button_4.setEnabled(false);
        GridData gd_button_4 = new GridData(16384, 0x1000000, false, false, 1, 1);
        gd_button_4.widthHint = 62;
        button_4.setLayoutData((Object)gd_button_4);
        button_4.setText("Add");
        Label lblIndividual = new Label((Composite)group_3, 0);
        GridData gd_lblIndividual = new GridData(131072, 0x1000000, false, false, 1, 1);
        gd_lblIndividual.widthHint = 62;
        lblIndividual.setLayoutData((Object)gd_lblIndividual);
        lblIndividual.setEnabled(false);
        lblIndividual.setText("Individual:");
        this.text = new Text((Composite)group_3, 2048);
        this.text.setEditable(false);
        this.text.setBackground(SWTResourceManager.getColor(1));
        GridData gd_text = new GridData(4, 0x1000000, true, false, 1, 1);
        gd_text.widthHint = 206;
        this.text.setLayoutData((Object)gd_text);
        this.lblLevelOfSignificance = new Label(container, 0);
        this.lblLevelOfSignificance.setLayoutData((Object)new GridData(131072, 0x1000000, false, false, 1, 1));
        this.lblLevelOfSignificance.setText("Level of Significance:");
        this.significanceLevel = new Text(container, 2048);
        this.significanceLevel.setText("0.05");
        new Label(container, 0);
        new Label(container, 0);
        new Label(container, 0);
        new Label(container, 0);
        new Label(container, 0);
        this.disable("replicate");
        this.initializeForm();
        return container;
    }

    void initializeForm() {
        this.dlgManager.initializeSingleSelectionList(this.factorVarList, this.txtEnvVar, this.moveBtn, this.btnAddEnv, new EnvVarValidator(this.factorVarList, this.performMultiRadio.getSelection(), this.file, this.txtEnvVar, this.lblEnv));
        this.dlgManager.initializeSingleSelectionList(this.factorVarList, this.txtP1Var, this.moveBtn, this.btnAddP1, new TextVarValidator(this.txtP1Var, this.lblP1));
        this.dlgManager.initializeSingleSelectionList(this.factorVarList, this.txtP2Var, this.moveBtn, this.btnAddP2, new TextVarValidator(this.txtP2Var, this.lblP2));
        this.dlgManager.initializeSingleSelectionList(this.factorVarList, this.txtBlockVar, this.moveBtn, this.btnAddBlock, new TextVarValidator(this.txtBlockVar, this.lblBlock));
        this.dlgManager.initializeSingleSelectionList(this.factorVarList, this.txtRepVar, this.moveBtn, this.btnAddRep, new TextVarValidator(this.txtRepVar, this.lblRep));
        this.dlgManager.initializeSingleSelectionList(this.factorVarList, this.txtRowVar, this.moveBtn, this.btnAddRow, new TextVarValidator(this.txtRowVar, this.lblRow));
        this.dlgManager.initializeSingleSelectionList(this.factorVarList, this.txtColVar, this.moveBtn, this.btnAddCol, new TextVarValidator(this.txtColVar, this.lblCol));
        this.dlgManager.initializeVariableMoveList(this.numVarList, this.factorVarList, this.moveBtn, this.file.getAbsolutePath());
        this.dlgManager.initializeSingleButtonList(this.numVarList, this.responseVarList, this.moveBtn, this.addBtn);
    }

    protected void disable(String string) {
        if (string.equals("replicate")) {
            this.lblRep.setEnabled(false);
            if (!this.txtRepVar.getText().isEmpty()) {
                this.factorVarList.add(this.txtRepVar.getText());
            }
            this.txtRepVar.setText("");
            this.btnAddRep.setEnabled(false);
        } else if (string.equals("block")) {
            this.lblBlock.setEnabled(false);
            if (!this.txtBlockVar.getText().isEmpty()) {
                this.factorVarList.add(this.txtBlockVar.getText());
            }
            this.txtBlockVar.setText("");
            this.btnAddBlock.setEnabled(false);
        } else if (string.equals("row")) {
            this.lblRow.setEnabled(false);
            if (!this.txtRowVar.getText().isEmpty()) {
                this.factorVarList.add(this.txtRowVar.getText());
            }
            this.txtRowVar.setText("");
            this.btnAddRow.setEnabled(false);
        } else if (string.equals("column")) {
            this.lblCol.setEnabled(false);
            if (!this.txtColVar.getText().isEmpty()) {
                this.factorVarList.add(this.txtColVar.getText());
            }
            this.txtColVar.setText("");
            this.btnAddCol.setEnabled(false);
        }
    }

    protected void setFactors() {
        this.varInfo = this.dataManipulationManager.getVarInfoFromFile(this.file.getAbsolutePath());
        this.numericVariables = this.dataManipulationManager.getNumericVars(this.varInfo);
        this.factorVariables = this.dataManipulationManager.getFactorVars(this.varInfo);
    }

    protected void reset() {
        this.txtP1Var.setText("");
        this.txtP2Var.setText("");
        this.txtRepVar.setText("");
        this.txtBlockVar.setText("");
        this.txtEnvVar.setText("");
        this.txtColVar.setText("");
        this.txtRowVar.setText("");
        this.responseVarList.removeAll();
        this.numVarList.removeAll();
        this.factorVarList.removeAll();
    }

    private String getDialogName(String analysisType2, File file2) {
        String dialogName = analysisType2.equals("GriffingI") ? "Diallel Analysis - Griffing Method I: " + this.dataManipulationManager.getDisplayFileName(file2.getAbsolutePath()) : (analysisType2.equals("GriffingII") ? "Diallel Analysis - Griffing Method II: " + this.dataManipulationManager.getDisplayFileName(file2.getAbsolutePath()) : (analysisType2.equals("GriffingIII") ? "Diallel Analysis - Griffing Method III: " + this.dataManipulationManager.getDisplayFileName(file2.getAbsolutePath()) : "Diallel Analysis - Griffing Method IV: " + this.dataManipulationManager.getDisplayFileName(file2.getAbsolutePath())));
        return dialogName;
    }

    public void enableFactorButtons(boolean state) {
        this.txtP1Var.setSelection(-1);
        this.txtP2Var.setSelection(-1);
        this.txtRepVar.setSelection(-1);
        this.txtBlockVar.setSelection(-1);
        this.txtEnvVar.setSelection(-1);
        this.txtRowVar.setSelection(-1);
        this.txtColVar.setSelection(-1);
        if (state) {
            this.btnAddP2.setText("Add");
            this.btnAddRep.setText("Add");
            this.btnAddBlock.setText("Add");
            this.btnAddEnv.setText("Add");
            this.btnAddP1.setText("Add");
            this.btnAddRow.setText("Add");
            this.btnAddCol.setText("Add");
            if (this.txtEnvVar.getText().isEmpty()) {
                this.btnAddEnv.setEnabled(state);
            }
            if (this.txtRepVar.getText().isEmpty() && this.lblRep.getEnabled()) {
                this.btnAddRep.setEnabled(state);
            }
            if (this.txtBlockVar.getText().isEmpty() && this.lblBlock.getEnabled()) {
                this.btnAddBlock.setEnabled(state);
            }
            if (this.txtP1Var.getText().isEmpty()) {
                this.btnAddP1.setEnabled(state);
            }
            if (this.txtP2Var.getText().isEmpty()) {
                this.btnAddP2.setEnabled(state);
            }
            if (this.txtRowVar.getText().isEmpty() && this.lblRow.getEnabled()) {
                this.btnAddRow.setEnabled(state);
            }
            if (this.txtColVar.getText().isEmpty() && this.lblCol.getEnabled()) {
                this.btnAddCol.setEnabled(state);
            }
        } else {
            this.btnAddRep.setEnabled(state);
            this.btnAddBlock.setEnabled(state);
            this.btnAddP1.setEnabled(state);
            this.btnAddP2.setEnabled(state);
            this.btnAddEnv.setEnabled(state);
            this.btnAddRow.setEnabled(state);
            this.btnAddCol.setEnabled(state);
        }
    }

    public void enableNumericButtons(boolean state) {
        this.addBtn.setEnabled(state);
    }

    protected void okPressed() {
        this.pressedOkButton = true;
        if (this.responseVarList.getItemCount() < 1) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"Please add a Response variable.");
        } else if (this.performMultiRadio.getSelection() && this.txtEnvVar.getText().isEmpty()) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"An environment factor is required for a multiple environment analysis.\n\nPlease add an environment factor.");
        } else if (this.txtP1Var.getText().isEmpty()) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"Please add a P1 factor.");
        } else if (this.txtP2Var.getText().isEmpty()) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"Please add a P2 factor.");
        } else if (this.txtRepVar.getText().isEmpty() && this.lblRep.getEnabled()) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"Please add a Replicate factor.");
        } else if (this.txtBlockVar.getText().isEmpty() && this.lblBlock.getEnabled()) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"Please add a Block factor.");
        } else if (this.txtRowVar.getText().isEmpty() && this.lblRow.getEnabled()) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"Please add a Row factor.");
        } else if (this.txtColVar.getText().isEmpty() && this.lblCol.getEnabled()) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"Please add a Column factor.");
        } else {
            try {
                if (Double.parseDouble(this.significanceLevel.getText()) <= 0.0 || Double.parseDouble(this.significanceLevel.getText()) >= 1.0) {
                    MessageDialog.openWarning((Shell)this.getShell(), (String)"Invalid Input", (String)"Sorry, the level of signifcance should have a value of between 0 and 1.");
                } else {
                    this.respvars = this.responseVarList.getItems();
                    this.p1 = this.txtP1Var.getText();
                    this.p2 = this.txtP2Var.getText();
                    this.environment = "NULL";
                    if (!this.txtRepVar.getText().isEmpty()) {
                        this.rep = this.txtRepVar.getText();
                    }
                    if (!this.txtBlockVar.getText().isEmpty()) {
                        this.block = this.txtBlockVar.getText();
                    }
                    if (!this.txtColVar.getText().isEmpty()) {
                        this.column = this.txtColVar.getText();
                    }
                    if (!this.txtRowVar.getText().isEmpty()) {
                        this.row = this.txtRowVar.getText();
                    }
                    if (!this.txtEnvVar.getText().isEmpty()) {
                        this.environment = this.txtEnvVar.getText();
                    }
                    this.alpha = this.significanceLevel.getText();
                    this.design = this.designs[this.designCombo.getSelectionIndex()];
                    String dataFileName = this.file.getAbsolutePath().toString().replaceAll("\\\\+", "/");
                    File outputFolder = PBToolAnalysisUtilities.createOutputFolder(this.file.getName(), this.analysisType);
                    String newFileName = String.valueOf(outputFolder.getAbsolutePath().toString().replaceAll("\\\\+", "/")) + "/output.txt";
                    OperationProgressDialog rInfo = new OperationProgressDialog(this.getShell(), "Diallel Analysis: " + this.analysisType);
                    rInfo.open();
                    if (this.analysisType.equals("GriffingI")) {
                        if (this.performPerSiteRadio.getSelection()) {
                            ProjectExplorerView.rJavaManager.getPbToolAnalysisManager().doDiallel1Test(dataFileName, newFileName, String.valueOf(outputFolder.getAbsolutePath().replaceAll("\\\\+", "/")) + "/", this.design, this.respvars, this.p1, this.p2, this.rep, this.block, this.row, this.column, this.cross, this.individual, this.environment, this.alpha);
                        } else {
                            ProjectExplorerView.rJavaManager.getPbToolAnalysisManager().doDiallel1METest(dataFileName, newFileName, String.valueOf(outputFolder.getAbsolutePath().replaceAll("\\\\+", "/")) + "/", this.design, this.respvars, this.p1, this.p2, this.rep, this.block, this.row, this.column, this.cross, this.individual, this.environment, this.alpha);
                        }
                    } else if (this.analysisType.equals("GriffingII")) {
                        if (this.performPerSiteRadio.getSelection()) {
                            ProjectExplorerView.rJavaManager.getPbToolAnalysisManager().doDiallel2Test(dataFileName, newFileName, String.valueOf(outputFolder.getAbsolutePath().replaceAll("\\\\+", "/")) + "/", this.design, this.respvars, this.p1, this.p2, this.rep, this.block, this.row, this.column, this.cross, this.individual, this.environment, this.alpha);
                        } else {
                            ProjectExplorerView.rJavaManager.getPbToolAnalysisManager().doDiallel2METest(dataFileName, newFileName, String.valueOf(outputFolder.getAbsolutePath().replaceAll("\\\\+", "/")) + "/", this.design, this.respvars, this.p1, this.p2, this.rep, this.block, this.row, this.column, this.cross, this.individual, this.environment, this.alpha);
                        }
                    } else if (this.analysisType.equals("GriffingIII")) {
                        if (this.performPerSiteRadio.getSelection()) {
                            ProjectExplorerView.rJavaManager.getPbToolAnalysisManager().doDiallel3Test(dataFileName, newFileName, String.valueOf(outputFolder.getAbsolutePath().replaceAll("\\\\+", "/")) + "/", this.design, this.respvars, this.p1, this.p2, this.rep, this.block, this.row, this.column, this.cross, this.individual, this.environment, this.alpha);
                        } else {
                            ProjectExplorerView.rJavaManager.getPbToolAnalysisManager().doDiallel3METest(dataFileName, newFileName, String.valueOf(outputFolder.getAbsolutePath().replaceAll("\\\\+", "/")) + "/", this.design, this.respvars, this.p1, this.p2, this.rep, this.block, this.row, this.column, this.cross, this.individual, this.environment, this.alpha);
                        }
                    } else if (this.performPerSiteRadio.getSelection()) {
                        ProjectExplorerView.rJavaManager.getPbToolAnalysisManager().doDiallel4Test(dataFileName, newFileName, String.valueOf(outputFolder.getAbsolutePath().replaceAll("\\\\+", "/")) + "/", this.design, this.respvars, this.p1, this.p2, this.rep, this.block, this.row, this.column, this.cross, this.individual, this.environment, this.alpha);
                    } else {
                        ProjectExplorerView.rJavaManager.getPbToolAnalysisManager().doDiallel4METest(dataFileName, newFileName, String.valueOf(outputFolder.getAbsolutePath().replaceAll("\\\\+", "/")) + "/", this.design, this.respvars, this.p1, this.p2, this.rep, this.block, this.row, this.column, this.cross, this.individual, this.environment, this.alpha);
                    }
                    rInfo.close();
                    WindowDialogControlUtil.hideAllWindowDialog();
                    PBToolAnalysisUtilities.openFolder(outputFolder);
                }
            }
            catch (NumberFormatException numberFormatException) {
                MessageDialog.openWarning((Shell)this.getShell(), (String)"Invalid Format", (String)"The Level of Signifcance should have a numeric value of between 0 and 1.");
            }
        }
    }

    protected void createButtonsForButtonBar(Composite parent) {
        this.createButton(parent, 0, IDialogConstants.OK_LABEL, true);
        this.createButton(parent, 1, IDialogConstants.CANCEL_LABEL, false);
    }

    protected Point getInitialSize() {
        return new Point(579, 695);
    }
}

