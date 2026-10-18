/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.jface.dialogs.Dialog
 *  org.eclipse.jface.dialogs.IDialogConstants
 *  org.eclipse.jface.dialogs.MessageDialog
 *  org.eclipse.swt.events.DisposeEvent
 *  org.eclipse.swt.events.DisposeListener
 *  org.eclipse.swt.events.MouseEvent
 *  org.eclipse.swt.events.MouseListener
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
 *  org.eclipse.swt.widgets.Event
 *  org.eclipse.swt.widgets.Group
 *  org.eclipse.swt.widgets.Label
 *  org.eclipse.swt.widgets.Layout
 *  org.eclipse.swt.widgets.List
 *  org.eclipse.swt.widgets.Listener
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
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
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
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;
import org.irri.breedingtool.datamanipulation.dialog.OperationProgressDialog;
import org.irri.breedingtool.manager.impl.DataManipulationManager;
import org.irri.breedingtool.projectexplorer.view.ProjectExplorerView;
import org.irri.breedingtool.utility.DialogFormUtility;
import org.irri.breedingtool.utility.DialogListValidator;
import org.irri.breedingtool.utility.EnvVarValidator;
import org.irri.breedingtool.utility.PBToolAnalysisUtilities;
import org.irri.breedingtool.utility.TextVarValidator;
import org.irri.breedingtool.utility.WindowDialogControlUtil;

public class TripleTestCrossDialog
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
    private Button btnAddF2;
    private Button btnAddTester;
    private Button btnAddEnv;
    private Button performMultiRadio;
    private List responseVarList;
    private Text significanceLevel;
    private String[] envtLevels;
    private List testerLevels;
    private Button btnAddP1;
    private Button btnAddP2;
    private Button btnAddF1;
    private String[] designs = new String[]{"CRD", "RCB", "Alpha", "RowColumn"};
    private String design = "CRD";
    private String[] respvars;
    private String tester;
    private String f2lines;
    private String rep = "NULL";
    private String block = "NULL";
    private String row = "NULL";
    private String column = "NULL";
    private String environment = "NULL";
    private String individual;
    private String codeP1;
    private String codeP2;
    private String codeF1;
    private String alpha;
    private Label lblRep;
    private Label lblBlock;
    private Combo designCombo;
    private Button btnAddBlock;
    private ShellListener newShellListener;
    protected boolean reFocused;
    protected boolean pressedOkButton;
    private Button btnAddRow;
    private Label lblRow;
    private Label lblCol;
    private Button btnAddCol;
    private Text txtEnvVar;
    private Text txtTesterVar;
    private Text txtP1Var;
    private Text txtP2Var;
    private Text txtF1Var;
    private Text txtF2Var;
    private Text txtBlockVar;
    private Text txtRepVar;
    private Text txtRowVar;
    private Text txtColVar;
    private Label lblTester;
    private Label lblP1;
    private Label lblP2;
    private Label lblF1;
    private Label lblF2;
    private Label lblEnv;

    public TripleTestCrossDialog(Shell parentShell, String analysisType, File file) {
        super(parentShell);
        this.setShellStyle(2256);
        this.setBlockOnOpen(false);
        this.analysisType = analysisType;
        this.file = file;
        this.setFactors();
        this.ttcDialogSetOpenState(analysisType, true);
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
                WindowDialogControlUtil.removeWindowDialogToList(TripleTestCrossDialog.this.getShell());
            }
        });
        Group group = new Group(container, 0);
        group.setLayout((Layout)new GridLayout(2, false));
        GridData gd_group = new GridData(4, 4, true, false, 5, 1);
        gd_group.heightHint = 20;
        gd_group.widthHint = 318;
        group.setLayoutData((Object)gd_group);
        Label lblTypeOfDesign = new Label((Composite)group, 0);
        lblTypeOfDesign.setLayoutData((Object)new GridData(16384, 0x1000000, true, false, 2, 1));
        lblTypeOfDesign.setText("TYPE OF DESIGN:");
        this.designCombo = new Combo((Composite)group, 8);
        this.designCombo.setItems(new String[]{"Completely Randomized Design (CRD)", "Randomized Complete Block (RCB)", "Alpha-Lattice", "Row-Column"});
        this.designCombo.setLayoutData((Object)new GridData(4, 0x1000000, true, false, 2, 1));
        this.designCombo.select(1);
        this.designCombo.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                TripleTestCrossDialog.this.enableFactorButtons(false);
                if (TripleTestCrossDialog.this.designCombo.getSelectionIndex() == 1) {
                    TripleTestCrossDialog.this.lblBlock.setEnabled(true);
                    TripleTestCrossDialog.this.disable("replicate");
                    TripleTestCrossDialog.this.disable("row");
                    TripleTestCrossDialog.this.disable("column");
                } else if (TripleTestCrossDialog.this.designCombo.getSelectionIndex() == 0) {
                    TripleTestCrossDialog.this.disable("replicate");
                    TripleTestCrossDialog.this.disable("block");
                    TripleTestCrossDialog.this.disable("row");
                    TripleTestCrossDialog.this.disable("column");
                } else if (TripleTestCrossDialog.this.designCombo.getSelectionIndex() == 2) {
                    TripleTestCrossDialog.this.lblRep.setEnabled(true);
                    TripleTestCrossDialog.this.lblBlock.setEnabled(true);
                    TripleTestCrossDialog.this.disable("row");
                    TripleTestCrossDialog.this.disable("column");
                } else if (TripleTestCrossDialog.this.designCombo.getSelectionIndex() == 3) {
                    TripleTestCrossDialog.this.lblRep.setEnabled(true);
                    TripleTestCrossDialog.this.lblRow.setEnabled(true);
                    TripleTestCrossDialog.this.lblCol.setEnabled(true);
                    TripleTestCrossDialog.this.disable("block");
                }
            }
        });
        Group group_1 = new Group(container, 0);
        group_1.setLayout((Layout)new GridLayout(1, false));
        GridData gd_group_1 = new GridData(4, 0x1000000, true, false, 2, 1);
        gd_group_1.heightHint = 60;
        gd_group_1.widthHint = 104;
        group_1.setLayoutData((Object)gd_group_1);
        Label lblDataInput = new Label((Composite)group_1, 0);
        lblDataInput.setLayoutData((Object)new GridData(4, 0x1000000, true, false, 1, 1));
        lblDataInput.setText("DATA INPUT:");
        Button btnPlotMeans = new Button((Composite)group_1, 16);
        btnPlotMeans.setSelection(true);
        btnPlotMeans.setLayoutData((Object)new GridData(4, 0x1000000, true, false, 1, 1));
        btnPlotMeans.setText("Plot Means");
        Button btnDataMeans = new Button((Composite)group_1, 16);
        btnDataMeans.setEnabled(false);
        btnDataMeans.setLayoutData((Object)new GridData(4, 0x1000000, true, false, 1, 1));
        btnDataMeans.setText("Individual Values");
        Label lblNumericVariables = new Label(container, 0);
        GridData gd_lblNumericVariables = new GridData(16384, 0x1000000, false, false, 3, 1);
        gd_lblNumericVariables.widthHint = 147;
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
                if (TripleTestCrossDialog.this.numVarList.getSelectionCount() > 0) {
                    TripleTestCrossDialog.this.enableFactorButtons(false);
                    TripleTestCrossDialog.this.enableNumericButtons(true);
                }
            }
        });
        GridData gd_numVarList = new GridData(4, 4, true, false, 2, 4);
        gd_numVarList.heightHint = 30;
        gd_numVarList.widthHint = 45;
        this.numVarList.setLayoutData((Object)gd_numVarList);
        new Label(container, 0);
        this.addBtn = new Button(container, 0);
        this.addBtn.setEnabled(false);
        GridData gd_addBtn = new GridData(0x1000000, 0x1000000, false, false, 1, 1);
        gd_addBtn.widthHint = 60;
        this.addBtn.setLayoutData((Object)gd_addBtn);
        this.addBtn.setText("Add");
        this.numVarList.setItems(this.numericVariables);
        new Label(container, 0);
        this.responseVarList = new List(container, 2818);
        GridData gd_responseVarList = new GridData(4, 4, true, false, 2, 2);
        gd_responseVarList.heightHint = 30;
        this.responseVarList.setLayoutData((Object)gd_responseVarList);
        new Label(container, 0);
        new Label(container, 0);
        new Label(container, 0);
        Group performAnalysisGroup = new Group(container, 0);
        performAnalysisGroup.setLayout((Layout)new GridLayout(4, false));
        GridData gd_performAnalysisGroup = new GridData(4, 4, true, true, 5, 4);
        gd_performAnalysisGroup.heightHint = 91;
        performAnalysisGroup.setLayoutData((Object)gd_performAnalysisGroup);
        Button performPerSiteRadio = new Button((Composite)performAnalysisGroup, 16);
        performPerSiteRadio.setLayoutData((Object)new GridData(16384, 0x1000000, false, false, 4, 1));
        performPerSiteRadio.setTouchEnabled(false);
        performPerSiteRadio.setSelection(true);
        performPerSiteRadio.setGrayed(false);
        performPerSiteRadio.setEnabled(true);
        performPerSiteRadio.setText("Perform Analysis Per Environment");
        this.performMultiRadio = new Button((Composite)performAnalysisGroup, 16);
        this.performMultiRadio.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                String[] levelItems;
                String selectedNumVars = TripleTestCrossDialog.this.txtEnvVar.getText();
                if (!TripleTestCrossDialog.this.txtEnvVar.getText().isEmpty() && (levelItems = DataManipulationManager.getEnvtLevels(selectedNumVars, TripleTestCrossDialog.this.file)).length < 2) {
                    TripleTestCrossDialog.this.factorVarList.add(selectedNumVars);
                    TripleTestCrossDialog.this.txtEnvVar.setText("");
                    MessageDialog.openWarning((Shell)TripleTestCrossDialog.this.getShell(), (String)"Invalid factor", (String)("The environment factor for a Multi-Environment Analysis should have atleast 2(two) levels.\n\n" + selectedNumVars + " has 1 level only. \n\n" + "Please choose a different environment factor."));
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
        GridData gd_lblEnv = new GridData(131072, 0x1000000, false, false, 1, 1);
        gd_lblEnv.widthHint = 75;
        this.lblEnv.setLayoutData((Object)gd_lblEnv);
        this.lblEnv.setText("Environment:");
        this.txtEnvVar = new Text((Composite)performAnalysisGroup, 2048);
        this.txtEnvVar.setBackground(SWTResourceManager.getColor(1));
        this.txtEnvVar.setEditable(false);
        GridData gd_txtEnvVar = new GridData(4, 4, true, false, 2, 1);
        gd_txtEnvVar.widthHint = 206;
        this.txtEnvVar.setLayoutData((Object)gd_txtEnvVar);
        Label lblFactors = new Label(container, 0);
        lblFactors.setLayoutData((Object)new GridData(16384, 1024, false, false, 1, 1));
        lblFactors.setText("Factors:");
        this.moveBtn = new Button(container, 0);
        GridData gd_moveBtn = new GridData(131072, 0x1000000, true, false, 1, 1);
        gd_moveBtn.heightHint = 24;
        gd_moveBtn.widthHint = 85;
        this.moveBtn.setLayoutData((Object)gd_moveBtn);
        this.moveBtn.setText("Set to Factor");
        this.factorVarList = new List(container, 2816);
        this.factorVarList.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (TripleTestCrossDialog.this.factorVarList.getSelectionCount() > 0) {
                    TripleTestCrossDialog.this.enableFactorButtons(true);
                    TripleTestCrossDialog.this.enableNumericButtons(false);
                }
            }
        });
        GridData gd_factorVarList = new GridData(4, 4, true, true, 2, 3);
        gd_factorVarList.widthHint = 53;
        gd_factorVarList.heightHint = 239;
        this.factorVarList.setLayoutData((Object)gd_factorVarList);
        this.factorVarList.setItems(this.factorVariables);
        Group dynamicGroup = new Group(container, 0);
        dynamicGroup.setLayout((Layout)new GridLayout(4, false));
        GridData gd_dynamicGroup = new GridData(4, 4, true, true, 5, 1);
        gd_dynamicGroup.widthHint = 368;
        gd_dynamicGroup.heightHint = 178;
        dynamicGroup.setLayoutData((Object)gd_dynamicGroup);
        this.btnAddTester = new Button((Composite)dynamicGroup, 0);
        this.btnAddTester.setEnabled(false);
        GridData gd_btnAddTester = new GridData(16384, 0x1000000, false, false, 1, 1);
        gd_btnAddTester.widthHint = 62;
        this.btnAddTester.setLayoutData((Object)gd_btnAddTester);
        this.btnAddTester.setText("Add");
        this.lblTester = new Label((Composite)dynamicGroup, 0);
        GridData gd_lblTester = new GridData(16384, 0x1000000, false, false, 1, 1);
        gd_lblTester.widthHint = 66;
        this.lblTester.setLayoutData((Object)gd_lblTester);
        this.lblTester.setText("Tester:");
        this.txtTesterVar = new Text((Composite)dynamicGroup, 2048);
        this.txtTesterVar.setEditable(false);
        this.txtTesterVar.setBackground(SWTResourceManager.getColor(1));
        GridData gd_txtTesterVar = new GridData(4, 4, true, false, 2, 1);
        gd_txtTesterVar.widthHint = 206;
        this.txtTesterVar.setLayoutData((Object)gd_txtTesterVar);
        this.txtTesterVar.addMouseListener(new MouseListener(){

            public void mouseDoubleClick(MouseEvent e) {
                TripleTestCrossDialog.this.testerLevels.removeAll();
                TripleTestCrossDialog.this.txtP1Var.setText("");
                TripleTestCrossDialog.this.txtP2Var.setText("");
                TripleTestCrossDialog.this.txtF1Var.setText("");
            }

            public void mouseDown(MouseEvent e) {
            }

            public void mouseUp(MouseEvent e) {
            }
        });
        Label lblLevelsOfTester = new Label((Composite)dynamicGroup, 0);
        lblLevelsOfTester.setText("Levels of Tester:");
        new Label((Composite)dynamicGroup, 0);
        new Label((Composite)dynamicGroup, 0);
        new Label((Composite)dynamicGroup, 0);
        this.testerLevels = new List((Composite)dynamicGroup, 2816);
        this.testerLevels.setLayoutData((Object)new GridData(4, 4, true, true, 1, 3));
        this.testerLevels.addListener(3, new Listener(){

            public void handleEvent(Event event) {
                if (TripleTestCrossDialog.this.testerLevels.getSelectionIndex() > -1) {
                    TripleTestCrossDialog.this.enableNumericButtons(false);
                    TripleTestCrossDialog.this.enableFactorButtons(false);
                    TripleTestCrossDialog.this.txtP1Var.setSelection(-1);
                    TripleTestCrossDialog.this.txtP2Var.setSelection(-1);
                    TripleTestCrossDialog.this.txtF1Var.setSelection(-1);
                    TripleTestCrossDialog.this.enableTesterButtons(true);
                }
            }
        });
        this.btnAddP1 = new Button((Composite)dynamicGroup, 0);
        GridData gd_btnAddP1 = new GridData(16384, 0x1000000, false, false, 1, 1);
        gd_btnAddP1.widthHint = 52;
        this.btnAddP1.setLayoutData((Object)gd_btnAddP1);
        this.btnAddP1.setText("Add");
        this.lblP1 = new Label((Composite)dynamicGroup, 0);
        this.lblP1.setLayoutData((Object)new GridData(131072, 0x1000000, false, false, 1, 1));
        this.lblP1.setText("P1:");
        this.txtP1Var = new Text((Composite)dynamicGroup, 2048);
        this.txtP1Var.setEditable(false);
        this.txtP1Var.setBackground(SWTResourceManager.getColor(1));
        this.txtP1Var.setLayoutData((Object)new GridData(4, 4, true, false, 1, 1));
        this.btnAddP2 = new Button((Composite)dynamicGroup, 0);
        GridData gd_btnAddP2 = new GridData(16384, 0x1000000, false, false, 1, 1);
        gd_btnAddP2.widthHint = 52;
        this.btnAddP2.setLayoutData((Object)gd_btnAddP2);
        this.btnAddP2.setText("Add");
        this.lblP2 = new Label((Composite)dynamicGroup, 0);
        this.lblP2.setLayoutData((Object)new GridData(131072, 0x1000000, false, false, 1, 1));
        this.lblP2.setText("P2:");
        this.txtP2Var = new Text((Composite)dynamicGroup, 2048);
        this.txtP2Var.setEditable(false);
        this.txtP2Var.setBackground(SWTResourceManager.getColor(1));
        this.txtP2Var.setLayoutData((Object)new GridData(4, 4, true, false, 1, 1));
        this.btnAddF1 = new Button((Composite)dynamicGroup, 0);
        GridData gd_btnAddF1 = new GridData(16384, 0x1000000, false, false, 1, 1);
        gd_btnAddF1.widthHint = 51;
        this.btnAddF1.setLayoutData((Object)gd_btnAddF1);
        this.btnAddF1.setText("Add");
        this.lblF1 = new Label((Composite)dynamicGroup, 0);
        this.lblF1.setLayoutData((Object)new GridData(131072, 0x1000000, false, false, 1, 1));
        this.lblF1.setText("F1:");
        this.txtF1Var = new Text((Composite)dynamicGroup, 2048);
        this.txtF1Var.setEditable(false);
        this.txtF1Var.setBackground(SWTResourceManager.getColor(1));
        this.txtF1Var.setLayoutData((Object)new GridData(4, 4, true, false, 1, 1));
        this.btnAddF2 = new Button((Composite)dynamicGroup, 0);
        this.btnAddF2.setEnabled(false);
        GridData gd_btnAddF2 = new GridData(16384, 0x1000000, false, false, 1, 1);
        gd_btnAddF2.widthHint = 62;
        this.btnAddF2.setLayoutData((Object)gd_btnAddF2);
        this.btnAddF2.setText("Add");
        this.lblF2 = new Label((Composite)dynamicGroup, 0);
        GridData gd_lblF2 = new GridData(16384, 0x1000000, false, false, 1, 1);
        gd_lblF2.widthHint = 21;
        this.lblF2.setLayoutData((Object)gd_lblF2);
        this.lblF2.setText("F2:");
        this.txtF2Var = new Text((Composite)dynamicGroup, 2048);
        this.txtF2Var.setEditable(false);
        this.txtF2Var.setBackground(SWTResourceManager.getColor(1));
        this.txtF2Var.setLayoutData((Object)new GridData(4, 4, true, false, 2, 1));
        this.getShell().setText(this.getDialogName(this.analysisType, this.file));
        Group group_3 = new Group(container, 0);
        group_3.setLayout((Layout)new GridLayout(3, false));
        GridData gd_group_3 = new GridData(4, 4, true, false, 5, 1);
        gd_group_3.heightHint = 162;
        group_3.setLayoutData((Object)gd_group_3);
        this.btnAddBlock = new Button((Composite)group_3, 0);
        this.btnAddBlock.setEnabled(false);
        GridData gd_btnAddBlock = new GridData(16384, 0x1000000, false, false, 1, 1);
        gd_btnAddBlock.widthHint = 62;
        this.btnAddBlock.setLayoutData((Object)gd_btnAddBlock);
        this.btnAddBlock.setText("Add");
        this.lblBlock = new Label((Composite)group_3, 0);
        GridData gd_lblBlock = new GridData(131072, 0x1000000, false, false, 1, 1);
        gd_lblBlock.widthHint = 75;
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
        gd_lblRep.widthHint = 75;
        this.lblRep.setLayoutData((Object)gd_lblRep);
        this.lblRep.setText("Replicate:");
        this.txtRepVar = new Text((Composite)group_3, 2048);
        this.txtRepVar.setEditable(false);
        this.txtRepVar.setBackground(SWTResourceManager.getColor(1));
        this.txtRepVar.setLayoutData((Object)new GridData(4, 4, true, false, 1, 1));
        this.btnAddRow = new Button((Composite)group_3, 0);
        this.btnAddRow.setEnabled(false);
        this.btnAddRow.setLayoutData((Object)new GridData(4, 0x1000000, false, false, 1, 1));
        this.btnAddRow.setText("Add");
        this.lblRow = new Label((Composite)group_3, 0);
        this.lblRow.setEnabled(false);
        this.lblRow.setText("Row:");
        this.txtRowVar = new Text((Composite)group_3, 2048);
        this.txtRowVar.setEditable(false);
        this.txtRowVar.setBackground(SWTResourceManager.getColor(1));
        this.txtRowVar.setLayoutData((Object)new GridData(4, 4, true, false, 1, 1));
        this.btnAddCol = new Button((Composite)group_3, 0);
        this.btnAddCol.setEnabled(false);
        this.btnAddCol.setLayoutData((Object)new GridData(4, 0x1000000, false, false, 1, 1));
        this.btnAddCol.setText("Add");
        this.lblCol = new Label((Composite)group_3, 0);
        this.lblCol.setEnabled(false);
        this.lblCol.setText("Column:");
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
        GridData gd_lblIndividual = new GridData(16384, 0x1000000, false, false, 1, 1);
        gd_lblIndividual.widthHint = 75;
        lblIndividual.setLayoutData((Object)gd_lblIndividual);
        lblIndividual.setEnabled(false);
        lblIndividual.setText("Individual:");
        List list_8 = new List((Composite)group_3, 2562);
        GridData gd_list_8 = new GridData(4, 4, true, false, 1, 1);
        gd_list_8.heightHint = 20;
        gd_list_8.widthHint = 169;
        list_8.setLayoutData((Object)gd_list_8);
        Label lblLevelOfSignificance = new Label(container, 0);
        GridData gd_lblLevelOfSignificance = new GridData(131072, 0x1000000, false, false, 1, 1);
        gd_lblLevelOfSignificance.widthHint = 108;
        lblLevelOfSignificance.setLayoutData((Object)gd_lblLevelOfSignificance);
        lblLevelOfSignificance.setText("Level of Significance: ");
        this.significanceLevel = new Text(container, 2048);
        this.significanceLevel.setText("0.05");
        this.significanceLevel.setLayoutData((Object)new GridData(4, 0x1000000, false, false, 1, 1));
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
        this.dlgManager.initializeSingleSelectionList(this.factorVarList, this.txtTesterVar, this.moveBtn, this.btnAddTester, new testerVarValidator());
        this.dlgManager.initializeSingleSelectionList(this.testerLevels, this.txtP1Var, this.moveBtn, this.btnAddP1, new TextVarValidator(this.txtP1Var, this.lblP1));
        this.dlgManager.initializeSingleSelectionList(this.testerLevels, this.txtP2Var, this.moveBtn, this.btnAddP2, new TextVarValidator(this.txtP2Var, this.lblP2));
        this.dlgManager.initializeSingleSelectionList(this.testerLevels, this.txtF1Var, this.moveBtn, this.btnAddF1, new TextVarValidator(this.txtF1Var, this.lblF1));
        this.dlgManager.initializeSingleSelectionList(this.factorVarList, this.txtF2Var, this.moveBtn, this.btnAddF2, new TextVarValidator(this.txtF2Var, this.lblF2));
        this.dlgManager.initializeSingleSelectionList(this.factorVarList, this.txtBlockVar, this.moveBtn, this.btnAddBlock, new TextVarValidator(this.txtBlockVar, this.lblBlock));
        this.dlgManager.initializeSingleSelectionList(this.factorVarList, this.txtRepVar, this.moveBtn, this.btnAddRep, new TextVarValidator(this.txtRepVar, this.lblRep));
        this.dlgManager.initializeSingleSelectionList(this.factorVarList, this.txtRowVar, this.moveBtn, this.btnAddRow, new TextVarValidator(this.txtRowVar, this.lblRow));
        this.dlgManager.initializeSingleSelectionList(this.factorVarList, this.txtColVar, this.moveBtn, this.btnAddCol, new TextVarValidator(this.txtColVar, this.lblCol));
        this.dlgManager.initializeVariableMoveList(this.numVarList, this.factorVarList, this.moveBtn, this.file.getAbsolutePath());
        this.dlgManager.initializeSingleButtonList(this.numVarList, this.responseVarList, this.moveBtn, this.addBtn);
    }

    protected void setFactors() {
        this.varInfo = this.dataManipulationManager.getVarInfoFromFile(this.file.getAbsolutePath());
        this.numericVariables = this.dataManipulationManager.getNumericVars(this.varInfo);
        this.factorVariables = this.dataManipulationManager.getFactorVars(this.varInfo);
    }

    protected void reset() {
        this.testerLevels.removeAll();
        this.numVarList.removeAll();
        this.factorVarList.removeAll();
        this.responseVarList.removeAll();
        this.txtRepVar.setText("");
        this.txtBlockVar.setText("");
        this.txtEnvVar.setText("");
        this.txtTesterVar.setText("");
        this.txtF2Var.setText("");
        this.txtP1Var.setText("");
        this.txtP2Var.setText("");
        this.txtF1Var.setText("");
        this.txtRowVar.setText("");
        this.txtColVar.setText("");
    }

    public void enableFactorButtons(boolean state) {
        this.txtTesterVar.setSelection(-1);
        this.txtF2Var.setSelection(-1);
        this.txtRepVar.setSelection(-1);
        this.txtBlockVar.setSelection(-1);
        this.txtEnvVar.setSelection(-1);
        this.txtP1Var.setSelection(-1);
        this.txtP2Var.setSelection(-1);
        this.txtF1Var.setSelection(-1);
        this.txtRowVar.setSelection(-1);
        this.txtColVar.setSelection(-1);
        if (state) {
            this.btnAddF2.setText("Add");
            this.btnAddRep.setText("Add");
            this.btnAddBlock.setText("Add");
            this.btnAddEnv.setText("Add");
            this.btnAddTester.setText("Add");
            this.btnAddRow.setText("Add");
            this.btnAddCol.setText("Add");
            if (this.txtEnvVar.getText().isEmpty()) {
                this.btnAddEnv.setEnabled(state);
            }
            if (this.txtTesterVar.getText().isEmpty()) {
                this.btnAddTester.setEnabled(state);
            }
            if (this.txtF2Var.getText().isEmpty()) {
                this.btnAddF2.setEnabled(state);
            }
            if (this.txtRepVar.getText().isEmpty() && this.lblRep.getEnabled()) {
                this.btnAddRep.setEnabled(state);
            }
            if (this.txtBlockVar.getText().isEmpty() && this.lblBlock.getEnabled()) {
                this.btnAddBlock.setEnabled(state);
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
            this.btnAddRow.setEnabled(state);
            this.btnAddCol.setEnabled(state);
            this.btnAddTester.setEnabled(state);
            this.btnAddF2.setEnabled(state);
            this.btnAddEnv.setEnabled(state);
            this.btnAddP1.setEnabled(state);
            this.btnAddP2.setEnabled(state);
            this.btnAddF1.setEnabled(state);
        }
    }

    public void enableTesterButtons(boolean state) {
        this.txtP1Var.setSelection(-1);
        this.txtP2Var.setSelection(-1);
        this.txtF1Var.setSelection(-1);
        if (state) {
            this.btnAddP1.setText("Add");
            this.btnAddP2.setText("Add");
            this.btnAddF1.setText("Add");
            if (this.txtP1Var.getText().isEmpty()) {
                this.btnAddP1.setEnabled(state);
            }
            if (this.txtP2Var.getText().isEmpty()) {
                this.btnAddP2.setEnabled(state);
            }
            if (this.txtF1Var.getText().isEmpty()) {
                this.btnAddF1.setEnabled(state);
            }
        } else {
            this.btnAddP1.setEnabled(state);
            this.btnAddP2.setEnabled(state);
            this.btnAddF1.setEnabled(state);
        }
    }

    public void enableNumericButtons(boolean state) {
        this.addBtn.setEnabled(state);
    }

    protected void createButtonsForButtonBar(Composite parent) {
        this.createButton(parent, 0, IDialogConstants.OK_LABEL, true);
        this.createButton(parent, 1, IDialogConstants.CANCEL_LABEL, false);
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

    protected void ttcDialogSetOpenState(String analysisType2, boolean state) {
    }

    private String getDialogName(String analysisType2, File file2) {
        String dialogName = "TripleTestCross";
        if (analysisType2.equals("TripleTestCross")) {
            dialogName = "Triple Test Cross (no parent): " + this.dataManipulationManager.getDisplayFileName(file2.getAbsolutePath());
        }
        return dialogName;
    }

    protected void okPressed() {
        this.pressedOkButton = true;
        if (this.responseVarList.getItemCount() < 1) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"Please add a Response variable.");
        } else if (this.performMultiRadio.getSelection() && this.txtEnvVar.getText().isEmpty()) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"An environment factor is required for a multiple environment analysis.\n\nPlease add an environment factor.");
        } else if (this.txtTesterVar.getText().isEmpty()) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"Please add a Tester factor.");
        } else if (this.txtP1Var.getText().isEmpty()) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"Please specify the level of Tester which corresponds to P1.");
        } else if (this.txtP2Var.getText().isEmpty()) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"Please specify the level of Tester which corresponds to P2.");
        } else if (this.txtF1Var.getText().isEmpty()) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"Please specify the level of Tester which corresponds to F1.");
        } else if (this.txtF2Var.getText().isEmpty()) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"Please add an F2 factor.");
        } else if (this.txtRepVar.getText().isEmpty() && this.lblRep.getEnabled()) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"Please add a Replicate factor.");
        } else if (this.txtBlockVar.getText().isEmpty() && this.lblBlock.getEnabled()) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"Please add a Block factor.");
        } else if (this.txtRowVar.getText().isEmpty() && this.lblRow.getEnabled()) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"Please add a Row factor.");
        } else if (this.txtColVar.getText().isEmpty() && this.lblCol.getEnabled()) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"Please add a Column factor.");
        } else {
            this.respvars = this.responseVarList.getItems();
            this.tester = this.txtTesterVar.getText();
            this.f2lines = this.txtF2Var.getText();
            this.environment = "NULL";
            if (this.lblRep.getEnabled()) {
                this.rep = this.txtRepVar.getText();
            }
            if (this.lblBlock.getEnabled()) {
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
            this.individual = "NULL";
            this.codeP1 = this.txtP1Var.getText();
            this.codeP2 = this.txtP2Var.getText();
            this.codeF1 = this.txtF1Var.getText();
            this.alpha = this.significanceLevel.getText();
            this.design = this.designs[this.designCombo.getSelectionIndex()];
            String dataFileName = this.file.getAbsolutePath().toString().replaceAll("\\\\+", "/");
            File outputFolder = PBToolAnalysisUtilities.createOutputFolder(this.file.getName(), this.analysisType);
            String newOutputFileName = "output.txt";
            String newFileName = String.valueOf(outputFolder.getAbsolutePath().toString().replaceAll("\\\\+", "/")) + "/" + newOutputFileName;
            OperationProgressDialog rInfo = new OperationProgressDialog(this.getShell(), "Triple Test Cross (no parent) Analysis");
            rInfo.open();
            if (this.performMultiRadio.getSelection()) {
                ProjectExplorerView.rJavaManager.getPbToolAnalysisManager().doTTCMETest(dataFileName, newFileName, this.design, this.respvars, this.tester, this.f2lines, this.rep, this.block, this.row, this.column, this.individual, this.environment, this.codeP1, this.codeP2, this.codeF1, this.alpha);
            } else {
                ProjectExplorerView.rJavaManager.getPbToolAnalysisManager().doTTCTest(dataFileName, newFileName, this.design, this.respvars, this.tester, this.f2lines, this.rep, this.block, this.row, this.column, this.individual, this.environment, this.codeP1, this.codeP2, this.codeF1, this.alpha);
            }
            rInfo.close();
            WindowDialogControlUtil.hideAllWindowDialog();
            PBToolAnalysisUtilities.openFolder(outputFolder);
        }
    }

    protected Point getInitialSize() {
        return new Point(594, 738);
    }

    protected boolean isResizable() {
        return true;
    }

    public class testerVarValidator
    extends DialogListValidator {
        @Override
        public boolean validate() {
            return true;
        }

        @Override
        public boolean performAddProcess() {
            String[] selectedNumVars = TripleTestCrossDialog.this.factorVarList.getSelection();
            String[] levelItems = DataManipulationManager.getEnvtLevels(selectedNumVars[0], TripleTestCrossDialog.this.file);
            if (levelItems.length != 3) {
                MessageDialog.openWarning((Shell)TripleTestCrossDialog.this.getShell(), (String)"Invalid factor", (String)("The tester factor should have three levels.\n" + selectedNumVars[0] + " has " + levelItems.length + " level(s). \n\n" + "Please choose a different tester factor."));
                return false;
            }
            TripleTestCrossDialog.this.testerLevels.setItems(levelItems);
            return true;
        }

        @Override
        public boolean performRemoveProcess() {
            String selectedNumVars = TripleTestCrossDialog.this.txtTesterVar.getText();
            TripleTestCrossDialog.this.factorVarList.add(selectedNumVars);
            TripleTestCrossDialog.this.testerLevels.removeAll();
            TripleTestCrossDialog.this.txtP1Var.setText("");
            TripleTestCrossDialog.this.txtP2Var.setText("");
            TripleTestCrossDialog.this.txtF1Var.setText("");
            TripleTestCrossDialog.this.txtTesterVar.setText("");
            return true;
        }
    }
}

