/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.jface.dialogs.Dialog
 *  org.eclipse.jface.dialogs.IDialogConstants
 *  org.eclipse.jface.dialogs.MessageDialog
 *  org.eclipse.swt.custom.CCombo
 *  org.eclipse.swt.custom.TableEditor
 *  org.eclipse.swt.events.DisposeEvent
 *  org.eclipse.swt.events.DisposeListener
 *  org.eclipse.swt.events.MouseEvent
 *  org.eclipse.swt.events.MouseListener
 *  org.eclipse.swt.events.SelectionAdapter
 *  org.eclipse.swt.events.SelectionEvent
 *  org.eclipse.swt.events.SelectionListener
 *  org.eclipse.swt.graphics.Color
 *  org.eclipse.swt.graphics.Device
 *  org.eclipse.swt.graphics.Point
 *  org.eclipse.swt.graphics.RGB
 *  org.eclipse.swt.layout.GridData
 *  org.eclipse.swt.layout.GridLayout
 *  org.eclipse.swt.widgets.Button
 *  org.eclipse.swt.widgets.Composite
 *  org.eclipse.swt.widgets.Control
 *  org.eclipse.swt.widgets.Display
 *  org.eclipse.swt.widgets.Event
 *  org.eclipse.swt.widgets.Label
 *  org.eclipse.swt.widgets.Layout
 *  org.eclipse.swt.widgets.List
 *  org.eclipse.swt.widgets.Listener
 *  org.eclipse.swt.widgets.Shell
 *  org.eclipse.swt.widgets.Spinner
 *  org.eclipse.swt.widgets.TabFolder
 *  org.eclipse.swt.widgets.TabItem
 *  org.eclipse.swt.widgets.Table
 *  org.eclipse.swt.widgets.TableColumn
 *  org.eclipse.swt.widgets.TableItem
 *  org.eclipse.swt.widgets.Text
 */
package org.irri.breedingtool.graphs.dialog;

import java.io.File;
import java.util.ArrayList;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.ResourceManager;
import org.eclipse.wb.swt.SWTResourceManager;
import org.irri.breedingtool.application.model.ProjectExplorerTreeNodeModel;
import org.irri.breedingtool.datamanipulation.dialog.OperationProgressDialog;
import org.irri.breedingtool.graphs.dialog.ChooseLineTypesDialog;
import org.irri.breedingtool.graphs.dialog.ChoosePlotSymbolsDialog;
import org.irri.breedingtool.manager.impl.DataManipulationManager;
import org.irri.breedingtool.projectexplorer.view.ProjectExplorerView;
import org.irri.breedingtool.utility.DialogFormUtility;
import org.irri.breedingtool.utility.GraphsUtilities;
import org.irri.breedingtool.utility.TextVarValidator;
import org.irri.breedingtool.utility.WindowDialogControlUtil;

public class BoxPlotDialog
extends Dialog {
    private ProjectExplorerTreeNodeModel fileModel;
    private ArrayList<String> varInfo;
    private List numVarList;
    private List factorVarList;
    private List responseVarList;
    private Button addBtn;
    private Button moveBtn;
    private String[] numericVariables;
    private String[] factorVariables;
    private File file;
    private DataManipulationManager dataManipulationManager = new DataManipulationManager();
    private DialogFormUtility dlgManager = new DialogFormUtility();
    private int axisLabelStyle = 1;
    String[] nVar = null;
    String[] cVars = null;
    String mTitle = null;
    String[] nAxisLab = null;
    String cAxisLab = null;
    String[] yMinValue = null;
    String[] yMaxValue = null;
    String byVar = null;
    double boxSize = 0.8;
    String boxColor = "rgb(0, 0, 0, max = 255)";
    String boxFillColor = "rgb(255, 255, 255, max = 255)";
    int medLineType = 1;
    double medLineWidth = 3.0;
    String medColor = "rgb(0, 0, 0, max = 255)";
    int whiskLineType = 2;
    double whiskLineWidth = 1.0;
    String whiskColor = "rgb(0, 0, 0, max = 255)";
    int outChar = 1;
    double outCharSize = 1.0;
    String outColor = "rgb(0, 0, 0, max = 255)";
    String boxed = "FALSE";
    String multGraphs = "FALSE";
    int numRowsGraphs = 1;
    int numColsGraphs = 1;
    String orientGraphs = "top-bottom";
    String plotHoriz = "FALSE";
    private ArrayList<Integer> barDensityList = new ArrayList();
    private String analysisType;
    private TableColumn tableColumn;
    private Button addAxisBtn;
    private Button addGroupsBtn;
    private List axisVarList;
    private Text mainTitleText;
    private Text categoryAxisText;
    private TabItem tbtmModelSpecifications;
    private CCombo orientGraphsCombo;
    private Button btnDrawBoxAround;
    private Button btnDisplayMultipleGraphs;
    private Spinner numColsSpinner;
    private Spinner numRowsSpinner;
    private Label lblOrientation_1;
    private Button btnVertical;
    private Button btnHorizontal;
    private TabItem tbtmFormat;
    private Composite composite;
    private Label lblBox;
    private Label lblOutlineColor;
    private Label outlineColor;
    private Button outlineColorBtn;
    private Label lblFillColor;
    private Label fillColor;
    private Button fillColorBtn;
    private Label lblSize;
    private Spinner boxSizeSpinner;
    private Label lblMedian;
    private Label lblColor;
    private Label medianColor;
    private Button medianColorBtn;
    private Button medianLineTypeBtn;
    private Label medianLineType;
    private Label lblLineType;
    private Label lblWidth;
    private Spinner medianWidthSpinner;
    private Label lblWhiskers;
    private Label lblColor_1;
    private Label label_4;
    private Label label_5;
    private Label lblOutliers;
    private Label label_6;
    private Label lblSymbol;
    private Label label_8;
    private Spinner whiskersWidthSpinner;
    private Spinner outliersSizeSpinner;
    private Label outliersColor;
    private Label whiskersColor;
    private Button whiskersColorBtn;
    private Button outliersColorBtn;
    private Button whiskersLineTypeBtn;
    private Button outliersSymbolBtn;
    private Label outliersSymbol;
    private Label whiskersLineType;
    private Label lblAsdasd;
    private Label lblAdasd;
    private Table table;
    private Label lblNewLabel;
    private Label lblNumberOfColumns;
    private Label lblOrientation;
    private Composite composite_2;
    private Shell parentShell;
    private Label lblAxisLabelOrientation;
    private CCombo cmboAxisOrientation;
    private Text groupsVarTxt;
    private Label lblGroupBy;

    public BoxPlotDialog(Shell parentShell, String analysisType, File file) {
        super(parentShell);
        this.setShellStyle(2256);
        this.setBlockOnOpen(false);
        this.parentShell = parentShell;
        this.analysisType = analysisType;
        this.file = file;
        this.setFactors();
    }

    protected Control createDialogArea(Composite parent) {
        this.getShell().setData("analysis", (Object)this.analysisType);
        this.getShell().setData("filePathID", (Object)this.file.getAbsolutePath());
        WindowDialogControlUtil.addWindowDialogToList(this.getShell());
        parent.addDisposeListener(new DisposeListener(){

            public void widgetDisposed(DisposeEvent e) {
                WindowDialogControlUtil.removeWindowDialogToList(BoxPlotDialog.this.getShell());
            }
        });
        parent.getShell().setText("Box-and-Whiskers Plot: " + this.dataManipulationManager.getDisplayFileName(this.file.getAbsolutePath()));
        Composite container = (Composite)super.createDialogArea(parent);
        TabFolder tabFolder = new TabFolder(container, 0);
        tabFolder.setLayoutData((Object)new GridData(4, 4, true, true, 1, 1));
        this.tbtmModelSpecifications = new TabItem(tabFolder, 0);
        this.tbtmModelSpecifications.setText("Variable Definition");
        Composite modelComposite = new Composite((Composite)tabFolder, 0);
        this.tbtmModelSpecifications.setControl((Control)modelComposite);
        modelComposite.setLayout((Layout)new GridLayout(5, false));
        Label lblNumericVariables = new Label(modelComposite, 0);
        lblNumericVariables.setLayoutData((Object)new GridData(16384, 0x1000000, false, false, 2, 1));
        lblNumericVariables.setText("Numeric Variables:");
        new Label(modelComposite, 0);
        new Label(modelComposite, 0);
        Label lblResponseVariables = new Label(modelComposite, 0);
        lblResponseVariables.setText("Variable(s):");
        this.numVarList = new List(modelComposite, 2818);
        GridData gd_numVarList = new GridData(4, 4, true, false, 2, 1);
        gd_numVarList.heightHint = 95;
        gd_numVarList.widthHint = 126;
        this.numVarList.setLayoutData((Object)gd_numVarList);
        this.numVarList.setItems(this.numericVariables);
        this.numVarList.addMouseListener(new MouseListener(){

            public void mouseDoubleClick(MouseEvent e) {
                if (BoxPlotDialog.this.numVarList.getSelectionCount() > 0) {
                    BoxPlotDialog.this.addItemToVarTable(BoxPlotDialog.this.numVarList.getSelection()[0]);
                    BoxPlotDialog.this.dataManipulationManager.moveSelectedStringFromTo(BoxPlotDialog.this.numVarList, BoxPlotDialog.this.responseVarList, BoxPlotDialog.this.moveBtn);
                    BoxPlotDialog.this.numVarList.remove(BoxPlotDialog.this.numVarList.getSelectionIndices());
                    BoxPlotDialog.this.addBtn.setEnabled(false);
                }
            }

            public void mouseDown(MouseEvent e) {
                if (BoxPlotDialog.this.numVarList.getSelectionCount() > 0) {
                    BoxPlotDialog.this.moveBtn.setEnabled(true);
                    BoxPlotDialog.this.factorVarList.setSelection(-1);
                    BoxPlotDialog.this.responseVarList.setSelection(-1);
                    BoxPlotDialog.this.addBtn.setText("Add");
                    BoxPlotDialog.this.moveBtn.setText("Set to Factor");
                    BoxPlotDialog.this.enableFactorButtons(false);
                    BoxPlotDialog.this.enableNumericButtons(true);
                }
            }

            public void mouseUp(MouseEvent e) {
            }
        });
        this.addBtn = new Button(modelComposite, 0);
        this.addBtn.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (BoxPlotDialog.this.numVarList.getSelectionCount() > 0) {
                    String[] selectedNumVars = BoxPlotDialog.this.numVarList.getSelection();
                    int i = 0;
                    while (i < selectedNumVars.length) {
                        BoxPlotDialog.this.addItemToVarTable(selectedNumVars[i]);
                        BoxPlotDialog.this.responseVarList.add(selectedNumVars[i]);
                        ++i;
                    }
                    BoxPlotDialog.this.numVarList.remove(BoxPlotDialog.this.numVarList.getSelectionIndices());
                } else {
                    String[] selectedNumVars = BoxPlotDialog.this.responseVarList.getSelection();
                    int i = 0;
                    while (i < selectedNumVars.length) {
                        BoxPlotDialog.this.removeItemFromVarTable(selectedNumVars[i]);
                        BoxPlotDialog.this.numVarList.add(selectedNumVars[i]);
                        ++i;
                    }
                    BoxPlotDialog.this.responseVarList.remove(BoxPlotDialog.this.responseVarList.getSelectionIndices());
                }
                BoxPlotDialog.this.enableNumericButtons(false);
            }
        });
        this.addBtn.setEnabled(false);
        GridData gd_addBtn = new GridData(16384, 0x1000000, false, false, 2, 1);
        gd_addBtn.widthHint = 52;
        this.addBtn.setLayoutData((Object)gd_addBtn);
        this.addBtn.setText("Add");
        this.responseVarList = new List(modelComposite, 2818);
        GridData gd_responseVarList = new GridData(4, 4, true, false, 1, 1);
        gd_responseVarList.widthHint = 111;
        this.responseVarList.setLayoutData((Object)gd_responseVarList);
        this.responseVarList.addMouseListener(new MouseListener(){

            public void mouseDoubleClick(MouseEvent e) {
                if (BoxPlotDialog.this.responseVarList.getSelectionCount() > 0) {
                    BoxPlotDialog.this.removeItemFromVarTable(BoxPlotDialog.this.responseVarList.getSelection()[0]);
                    BoxPlotDialog.this.dataManipulationManager.moveSelectedStringFromTo(BoxPlotDialog.this.responseVarList, BoxPlotDialog.this.numVarList, BoxPlotDialog.this.moveBtn);
                    BoxPlotDialog.this.responseVarList.remove(BoxPlotDialog.this.responseVarList.getSelectionIndices());
                    BoxPlotDialog.this.addBtn.setEnabled(false);
                }
            }

            public void mouseDown(MouseEvent e) {
                if (BoxPlotDialog.this.responseVarList.getSelectionCount() > 0) {
                    BoxPlotDialog.this.numVarList.setSelection(-1);
                    BoxPlotDialog.this.addBtn.setText("Remove");
                    BoxPlotDialog.this.addBtn.setEnabled(true);
                    BoxPlotDialog.this.moveBtn.setEnabled(false);
                    BoxPlotDialog.this.enableFactorButtons(false);
                }
            }

            public void mouseUp(MouseEvent e) {
            }
        });
        Label lblFactors = new Label(modelComposite, 0);
        lblFactors.setText("Factors:");
        this.moveBtn = new Button(modelComposite, 0);
        this.moveBtn.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (BoxPlotDialog.this.numVarList.getSelectionCount() > 0) {
                    String[] selectedNumVars = BoxPlotDialog.this.numVarList.getSelection();
                    int i = 0;
                    while (i < selectedNumVars.length) {
                        BoxPlotDialog.this.factorVarList.add(selectedNumVars[i]);
                        BoxPlotDialog.this.dataManipulationManager.editVariableType(BoxPlotDialog.this.file.getAbsolutePath(), BoxPlotDialog.this.varInfo, selectedNumVars[i], "Factor");
                        ++i;
                    }
                    BoxPlotDialog.this.numVarList.remove(BoxPlotDialog.this.numVarList.getSelectionIndices());
                    BoxPlotDialog.this.enableNumericButtons(false);
                } else {
                    String[] selectedNumVars = BoxPlotDialog.this.factorVarList.getSelection();
                    int i = 0;
                    while (i < selectedNumVars.length) {
                        BoxPlotDialog.this.numVarList.add(selectedNumVars[i]);
                        BoxPlotDialog.this.dataManipulationManager.editVariableType(BoxPlotDialog.this.file.getAbsolutePath(), BoxPlotDialog.this.varInfo, selectedNumVars[i], "Numeric");
                        ++i;
                    }
                    BoxPlotDialog.this.factorVarList.remove(BoxPlotDialog.this.factorVarList.getSelectionIndices());
                    BoxPlotDialog.this.enableFactorButtons(false);
                }
            }
        });
        this.moveBtn.setEnabled(false);
        GridData gd_moveBtn = new GridData(131072, 0x1000000, true, false, 1, 1);
        gd_moveBtn.heightHint = 24;
        gd_moveBtn.widthHint = 90;
        this.moveBtn.setLayoutData((Object)gd_moveBtn);
        this.moveBtn.setText("Set to Factor");
        new Label(modelComposite, 0);
        new Label(modelComposite, 0);
        Label lblCategorizeBy = new Label(modelComposite, 0);
        lblCategorizeBy.setText("Categorize by:");
        this.factorVarList = new List(modelComposite, 2560);
        GridData gd_factorVarList = new GridData(4, 4, true, true, 2, 3);
        gd_factorVarList.widthHint = 65;
        this.factorVarList.setLayoutData((Object)gd_factorVarList);
        this.factorVarList.setItems(this.factorVariables);
        this.factorVarList.addListener(3, new Listener(){

            public void handleEvent(Event event) {
                if (BoxPlotDialog.this.factorVarList.getSelectionIndex() > -1) {
                    BoxPlotDialog.this.enableNumericButtons(false);
                    BoxPlotDialog.this.enableFactorButtons(false);
                    BoxPlotDialog.this.numVarList.setSelection(-1);
                    String[] s = BoxPlotDialog.this.factorVarList.getSelection();
                    String isNumeric = BoxPlotDialog.this.dataManipulationManager.isNumeric(BoxPlotDialog.this.file.getAbsolutePath().replaceAll("\\\\", "/"), s[0]);
                    if (isNumeric.equals("TRUE")) {
                        BoxPlotDialog.this.moveBtn.setText("Set to Numeric");
                        BoxPlotDialog.this.moveBtn.setEnabled(true);
                    } else {
                        BoxPlotDialog.this.moveBtn.setEnabled(false);
                    }
                    BoxPlotDialog.this.enableFactorButtons(true);
                }
            }
        });
        this.addAxisBtn = new Button(modelComposite, 0);
        GridData gd_addAxisBtn = new GridData(16384, 0x1000000, false, true, 2, 1);
        gd_addAxisBtn.widthHint = 52;
        this.addAxisBtn.setLayoutData((Object)gd_addAxisBtn);
        this.addAxisBtn.setText("Add");
        this.addAxisBtn.setEnabled(false);
        this.axisVarList = new List(modelComposite, 2818);
        this.axisVarList.setLayoutData((Object)new GridData(4, 4, true, true, 1, 1));
        new Label(modelComposite, 0);
        new Label(modelComposite, 0);
        this.lblGroupBy = new Label(modelComposite, 0);
        this.lblGroupBy.setText("Group by:");
        this.addGroupsBtn = new Button(modelComposite, 0);
        this.addGroupsBtn.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
            }
        });
        GridData gd_addGroupsBtn = new GridData(16384, 0x1000000, false, false, 2, 1);
        gd_addGroupsBtn.widthHint = 52;
        this.addGroupsBtn.setLayoutData((Object)gd_addGroupsBtn);
        this.addGroupsBtn.setText("Add");
        this.addGroupsBtn.setEnabled(false);
        this.groupsVarTxt = new Text(modelComposite, 2056);
        this.groupsVarTxt.setBackground(SWTResourceManager.getColor(1));
        this.groupsVarTxt.setLayoutData((Object)new GridData(4, 0x1000000, true, false, 1, 1));
        TabItem tbtmDisplay = new TabItem(tabFolder, 0);
        tbtmDisplay.setText("Display Options");
        this.composite_2 = new Composite((Composite)tabFolder, 0);
        tbtmDisplay.setControl((Control)this.composite_2);
        this.composite_2.setLayout((Layout)new GridLayout(5, false));
        Label lblMainTitle = new Label(this.composite_2, 0);
        lblMainTitle.setText("Main Title:");
        this.mainTitleText = new Text(this.composite_2, 2048);
        int maxLen = 45;
        this.mainTitleText.setTextLimit(maxLen);
        this.mainTitleText.setLayoutData((Object)new GridData(4, 0x1000000, true, false, 4, 1));
        Label lblCategoryAxisLabel = new Label(this.composite_2, 0);
        lblCategoryAxisLabel.setText("Category Axis Label:");
        this.categoryAxisText = new Text(this.composite_2, 2048);
        this.categoryAxisText.setLayoutData((Object)new GridData(4, 0x1000000, true, false, 4, 1));
        Label lblValueAxisLabel = new Label(this.composite_2, 0);
        lblValueAxisLabel.setText("Value Axis:");
        new Label(this.composite_2, 0);
        new Label(this.composite_2, 0);
        new Label(this.composite_2, 0);
        new Label(this.composite_2, 0);
        this.table = new Table(this.composite_2, 67586);
        this.table.setLinesVisible(true);
        this.table.setHeaderVisible(true);
        this.table.setBackground(SWTResourceManager.getColor(1));
        this.table.setLayoutData((Object)new GridData(4, 4, true, true, 5, 2));
        TableColumn tableColumn_1 = new TableColumn(this.table, 0);
        tableColumn_1.setWidth(85);
        tableColumn_1.setText("Variable");
        TableColumn tblclmnType = new TableColumn(this.table, 0);
        tblclmnType.setWidth(83);
        tblclmnType.setText("Axis Label");
        TableColumn tblclmnLoewrLabel = new TableColumn(this.table, 0);
        tblclmnLoewrLabel.setText("Lower Limit");
        tblclmnLoewrLabel.setWidth(87);
        TableColumn tblclmnUpperLimit = new TableColumn(this.table, 0);
        tblclmnUpperLimit.setWidth(72);
        tblclmnUpperLimit.setText("Upper Limit");
        this.lblAxisLabelOrientation = new Label(this.composite_2, 0);
        this.lblAxisLabelOrientation.setText("Axis Label Orientation:");
        this.cmboAxisOrientation = new CCombo(this.composite_2, 2056);
        this.cmboAxisOrientation.setItems(new String[]{"parallel to axis", "horizontal", "vertical", "perpendicular to axis"});
        this.cmboAxisOrientation.setBackground(SWTResourceManager.getColor(1));
        this.cmboAxisOrientation.select(0);
        new Label(this.composite_2, 0);
        new Label(this.composite_2, 0);
        new Label(this.composite_2, 0);
        this.lblOrientation_1 = new Label(this.composite_2, 0);
        this.lblOrientation_1.setText("Orientation:");
        this.btnVertical = new Button(this.composite_2, 16);
        this.btnVertical.setSelection(true);
        this.btnVertical.setText("Vertical");
        this.btnHorizontal = new Button(this.composite_2, 16);
        this.btnHorizontal.setLayoutData((Object)new GridData(16384, 0x1000000, false, false, 2, 1));
        this.btnHorizontal.setText("Horizontal");
        new Label(this.composite_2, 0);
        this.btnDrawBoxAround = new Button(this.composite_2, 32);
        this.btnDrawBoxAround.setSelection(true);
        this.btnDrawBoxAround.setLayoutData((Object)new GridData(16384, 0x1000000, false, false, 5, 1));
        this.btnDrawBoxAround.setText("Draw box around plot");
        this.btnDisplayMultipleGraphs = new Button(this.composite_2, 32);
        this.btnDisplayMultipleGraphs.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (BoxPlotDialog.this.btnDisplayMultipleGraphs.getSelection()) {
                    BoxPlotDialog.this.displayMultipleGraphs(true);
                } else {
                    BoxPlotDialog.this.displayMultipleGraphs(false);
                }
            }
        });
        this.btnDisplayMultipleGraphs.setLayoutData((Object)new GridData(16384, 0x1000000, false, false, 5, 1));
        this.btnDisplayMultipleGraphs.setText("Display multiple graphs in a page");
        new Label(this.composite_2, 0);
        this.lblNewLabel = new Label(this.composite_2, 0);
        this.lblNewLabel.setEnabled(false);
        this.lblNewLabel.setText("Number of rows:");
        this.numRowsSpinner = new Spinner(this.composite_2, 2056);
        this.numRowsSpinner.setBackground(SWTResourceManager.getColor(1));
        GridData gd_numRowsSpinner = new GridData(16384, 0x1000000, false, false, 1, 1);
        gd_numRowsSpinner.widthHint = 10;
        this.numRowsSpinner.setLayoutData((Object)gd_numRowsSpinner);
        this.numRowsSpinner.setEnabled(false);
        this.numRowsSpinner.setMaximum(5);
        this.numRowsSpinner.setMinimum(1);
        this.numRowsSpinner.setSelection(2);
        this.lblNumberOfColumns = new Label(this.composite_2, 0);
        this.lblNumberOfColumns.setEnabled(false);
        this.lblNumberOfColumns.setText("Number of columns:");
        this.numColsSpinner = new Spinner(this.composite_2, 2056);
        this.numColsSpinner.setBackground(SWTResourceManager.getColor(1));
        GridData gd_numColsSpinner = new GridData(16384, 0x1000000, false, false, 1, 1);
        gd_numColsSpinner.widthHint = 10;
        this.numColsSpinner.setLayoutData((Object)gd_numColsSpinner);
        this.numColsSpinner.setEnabled(false);
        this.numColsSpinner.setMaximum(5);
        this.numColsSpinner.setMinimum(1);
        this.numColsSpinner.setSelection(1);
        new Label(this.composite_2, 0);
        this.lblOrientation = new Label(this.composite_2, 0);
        this.lblOrientation.setEnabled(false);
        this.lblOrientation.setText("Orientation:");
        this.orientGraphsCombo = new CCombo(this.composite_2, 2056);
        this.orientGraphsCombo.setEnabled(false);
        this.orientGraphsCombo.setBackground(SWTResourceManager.getColor(1));
        this.orientGraphsCombo.setEditable(false);
        this.orientGraphsCombo.setItems(new String[]{"Left-to-right", "Top-to-bottom"});
        this.orientGraphsCombo.setLayoutData((Object)new GridData(4, 0x1000000, false, false, 3, 1));
        this.orientGraphsCombo.select(1);
        this.tbtmFormat = new TabItem(tabFolder, 0);
        this.tbtmFormat.setText("Other Options");
        this.composite = new Composite((Composite)tabFolder, 0);
        this.tbtmFormat.setControl((Control)this.composite);
        this.composite.setLayout((Layout)new GridLayout(11, false));
        this.lblAdasd = new Label(this.composite, 0);
        this.lblAdasd.setText(" ");
        new Label(this.composite, 0);
        new Label(this.composite, 0);
        new Label(this.composite, 0);
        new Label(this.composite, 0);
        new Label(this.composite, 0);
        new Label(this.composite, 0);
        new Label(this.composite, 0);
        new Label(this.composite, 0);
        new Label(this.composite, 0);
        new Label(this.composite, 0);
        new Label(this.composite, 0);
        this.lblBox = new Label(this.composite, 0);
        this.lblBox.setLayoutData((Object)new GridData(16384, 0x1000000, false, false, 4, 1));
        this.lblBox.setText("BOX:");
        new Label(this.composite, 0);
        new Label(this.composite, 0);
        this.lblWhiskers = new Label(this.composite, 0);
        this.lblWhiskers.setLayoutData((Object)new GridData(16384, 0x1000000, false, false, 4, 1));
        this.lblWhiskers.setText("WHISKERS:");
        new Label(this.composite, 0);
        new Label(this.composite, 0);
        this.lblOutlineColor = new Label(this.composite, 0);
        this.lblOutlineColor.setText("Outline Color:");
        this.outlineColor = new Label(this.composite, 2048);
        GridData gd_outlineColor = new GridData(16384, 4, false, false, 1, 1);
        gd_outlineColor.widthHint = 50;
        this.outlineColor.setLayoutData((Object)gd_outlineColor);
        this.outlineColor.setBackground(SWTResourceManager.getColor(2));
        this.outlineColorBtn = new Button(this.composite, 0);
        this.outlineColorBtn.setImage(ResourceManager.getPluginImage("Star", "icons/ellipsis.png"));
        this.outlineColorBtn.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                try {
                    RGB rgbColor = GraphsUtilities.chooseColor();
                    BoxPlotDialog.this.boxColor = GraphsUtilities.convertToRrgbFormat(rgbColor.toString());
                    BoxPlotDialog.this.outlineColor.setBackground(new Color((Device)Display.getCurrent(), rgbColor));
                }
                catch (Exception exception) {
                    System.out.println("Exception at RGB choose color");
                }
            }
        });
        new Label(this.composite, 0);
        new Label(this.composite, 0);
        new Label(this.composite, 0);
        this.lblColor_1 = new Label(this.composite, 0);
        this.lblColor_1.setText("Color:");
        this.whiskersColor = new Label(this.composite, 2048);
        GridData gd_whiskersColor = new GridData(16384, 4, false, false, 1, 1);
        gd_whiskersColor.widthHint = 50;
        this.whiskersColor.setLayoutData((Object)gd_whiskersColor);
        this.whiskersColor.setBackground(SWTResourceManager.getColor(2));
        this.whiskersColorBtn = new Button(this.composite, 0);
        this.whiskersColorBtn.setImage(ResourceManager.getPluginImage("Star", "icons/ellipsis.png"));
        this.whiskersColorBtn.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                try {
                    RGB rgbColor = GraphsUtilities.chooseColor();
                    BoxPlotDialog.this.whiskColor = GraphsUtilities.convertToRrgbFormat(rgbColor.toString());
                    BoxPlotDialog.this.whiskersColor.setBackground(new Color((Device)Display.getCurrent(), rgbColor));
                }
                catch (Exception exception) {
                    System.out.println("Exception at RGB choose color");
                }
            }
        });
        new Label(this.composite, 0);
        new Label(this.composite, 0);
        this.lblFillColor = new Label(this.composite, 0);
        this.lblFillColor.setText("Fill Color:");
        this.fillColor = new Label(this.composite, 2048);
        GridData gd_fillColor = new GridData(16384, 4, false, false, 1, 1);
        gd_fillColor.widthHint = 50;
        this.fillColor.setLayoutData((Object)gd_fillColor);
        this.fillColor.setBackground(SWTResourceManager.getColor(1));
        this.fillColorBtn = new Button(this.composite, 0);
        this.fillColorBtn.setImage(ResourceManager.getPluginImage("Star", "icons/ellipsis.png"));
        this.fillColorBtn.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                try {
                    RGB rgbColor = GraphsUtilities.chooseColor();
                    BoxPlotDialog.this.boxFillColor = GraphsUtilities.convertToRrgbFormat(rgbColor.toString());
                    BoxPlotDialog.this.fillColor.setBackground(new Color((Device)Display.getCurrent(), rgbColor));
                }
                catch (Exception exception) {
                    System.out.println("Exception at RGB choose color");
                }
            }
        });
        new Label(this.composite, 0);
        new Label(this.composite, 0);
        new Label(this.composite, 0);
        this.label_4 = new Label(this.composite, 0);
        this.label_4.setText("Line Type:");
        this.whiskersLineType = new Label(this.composite, 2048);
        this.whiskersLineType.setAlignment(0x1000000);
        GridData gd_whiskersLineType = new GridData(16384, 0x1000000, false, false, 1, 1);
        gd_whiskersLineType.widthHint = 50;
        this.whiskersLineType.setLayoutData((Object)gd_whiskersLineType);
        this.whiskersLineType.setImage(ResourceManager.getPluginImage("Star", "icons/line3.png"));
        this.whiskersLineType.setBackground(SWTResourceManager.getColor(22));
        this.whiskersLineTypeBtn = new Button(this.composite, 0);
        this.whiskersLineTypeBtn.setImage(ResourceManager.getPluginImage("Star", "icons/ellipsis.png"));
        this.whiskersLineTypeBtn.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                ChooseLineTypesDialog fillDlg = new ChooseLineTypesDialog(BoxPlotDialog.this.getShell());
                fillDlg.open();
                if (fillDlg.getReturnCode() == 0) {
                    BoxPlotDialog.this.whiskLineType = Integer.parseInt(fillDlg.getChosenPattern());
                    BoxPlotDialog.this.whiskersLineType.setImage(ResourceManager.getPluginImage("Star", "icons/line" + fillDlg.getChosenPattern() + ".png"));
                }
            }
        });
        new Label(this.composite, 0);
        new Label(this.composite, 0);
        this.lblSize = new Label(this.composite, 0);
        this.lblSize.setText("Size:");
        this.boxSizeSpinner = new Spinner(this.composite, 2048);
        this.boxSizeSpinner.setMaximum(30);
        this.boxSizeSpinner.setMinimum(5);
        this.boxSizeSpinner.setSelection(8);
        this.boxSizeSpinner.setDigits(1);
        new Label(this.composite, 0);
        new Label(this.composite, 0);
        new Label(this.composite, 0);
        new Label(this.composite, 0);
        this.label_5 = new Label(this.composite, 0);
        this.label_5.setText("Width:");
        this.whiskersWidthSpinner = new Spinner(this.composite, 2048);
        this.whiskersWidthSpinner.setDigits(1);
        this.whiskersWidthSpinner.setMaximum(30);
        this.whiskersWidthSpinner.setMinimum(5);
        this.whiskersWidthSpinner.setSelection(10);
        new Label(this.composite, 0);
        new Label(this.composite, 0);
        new Label(this.composite, 0);
        new Label(this.composite, 0);
        new Label(this.composite, 0);
        new Label(this.composite, 0);
        new Label(this.composite, 0);
        new Label(this.composite, 0);
        new Label(this.composite, 0);
        new Label(this.composite, 0);
        new Label(this.composite, 0);
        new Label(this.composite, 0);
        new Label(this.composite, 0);
        new Label(this.composite, 0);
        new Label(this.composite, 0);
        new Label(this.composite, 0);
        new Label(this.composite, 0);
        new Label(this.composite, 0);
        this.lblAsdasd = new Label(this.composite, 0);
        this.lblAsdasd.setText(" ");
        new Label(this.composite, 0);
        new Label(this.composite, 0);
        new Label(this.composite, 0);
        new Label(this.composite, 0);
        new Label(this.composite, 0);
        this.lblMedian = new Label(this.composite, 0);
        this.lblMedian.setLayoutData((Object)new GridData(16384, 0x1000000, false, false, 4, 1));
        this.lblMedian.setText("MEDIAN:");
        new Label(this.composite, 0);
        new Label(this.composite, 0);
        this.lblOutliers = new Label(this.composite, 0);
        this.lblOutliers.setLayoutData((Object)new GridData(16384, 0x1000000, false, false, 4, 1));
        this.lblOutliers.setText("OUTLIER(S):");
        new Label(this.composite, 0);
        new Label(this.composite, 0);
        this.lblColor = new Label(this.composite, 0);
        this.lblColor.setText("Color:");
        this.medianColor = new Label(this.composite, 2048);
        GridData gd_medianColor = new GridData(16384, 4, false, false, 1, 1);
        gd_medianColor.widthHint = 50;
        this.medianColor.setLayoutData((Object)gd_medianColor);
        this.medianColor.setBackground(SWTResourceManager.getColor(2));
        this.medianColorBtn = new Button(this.composite, 0);
        this.medianColorBtn.setImage(ResourceManager.getPluginImage("Star", "icons/ellipsis.png"));
        this.medianColorBtn.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                try {
                    RGB rgbColor = GraphsUtilities.chooseColor();
                    BoxPlotDialog.this.medColor = GraphsUtilities.convertToRrgbFormat(rgbColor.toString());
                    BoxPlotDialog.this.medianColor.setBackground(new Color((Device)Display.getCurrent(), rgbColor));
                }
                catch (Exception exception) {
                    System.out.println("Exception at RGB choose color");
                }
            }
        });
        new Label(this.composite, 0);
        new Label(this.composite, 0);
        new Label(this.composite, 0);
        this.label_6 = new Label(this.composite, 0);
        this.label_6.setText("Color:");
        this.outliersColor = new Label(this.composite, 2048);
        GridData gd_outliersColor = new GridData(16384, 4, false, false, 1, 1);
        gd_outliersColor.widthHint = 50;
        this.outliersColor.setLayoutData((Object)gd_outliersColor);
        this.outliersColor.setBackground(SWTResourceManager.getColor(2));
        this.outliersColorBtn = new Button(this.composite, 0);
        this.outliersColorBtn.setImage(ResourceManager.getPluginImage("Star", "icons/ellipsis.png"));
        this.outliersColorBtn.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                try {
                    RGB rgbColor = GraphsUtilities.chooseColor();
                    BoxPlotDialog.this.outColor = GraphsUtilities.convertToRrgbFormat(rgbColor.toString());
                    BoxPlotDialog.this.outliersColor.setBackground(new Color((Device)Display.getCurrent(), rgbColor));
                }
                catch (Exception exception) {
                    System.out.println("Exception at RGB choose color");
                }
            }
        });
        new Label(this.composite, 0);
        new Label(this.composite, 0);
        this.lblLineType = new Label(this.composite, 0);
        this.lblLineType.setText("Line Type:");
        this.medianLineType = new Label(this.composite, 2048);
        this.medianLineType.setImage(ResourceManager.getPluginImage("Star", "icons/line1.png"));
        GridData gd_medianLineType = new GridData(16384, 4, false, false, 1, 1);
        gd_medianLineType.widthHint = 50;
        this.medianLineType.setLayoutData((Object)gd_medianLineType);
        this.medianLineType.setBackground(SWTResourceManager.getColor(22));
        this.medianLineTypeBtn = new Button(this.composite, 0);
        this.medianLineTypeBtn.setImage(ResourceManager.getPluginImage("Star", "icons/ellipsis.png"));
        this.medianLineTypeBtn.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                ChooseLineTypesDialog fillDlg = new ChooseLineTypesDialog(BoxPlotDialog.this.getShell());
                fillDlg.open();
                if (fillDlg.getReturnCode() == 0) {
                    BoxPlotDialog.this.medLineType = Integer.parseInt(fillDlg.getChosenPattern());
                    BoxPlotDialog.this.medianLineType.setImage(ResourceManager.getPluginImage("Star", "icons/line" + fillDlg.getChosenPattern() + ".png"));
                }
            }
        });
        new Label(this.composite, 0);
        new Label(this.composite, 0);
        new Label(this.composite, 0);
        this.lblSymbol = new Label(this.composite, 0);
        this.lblSymbol.setText("Symbol:");
        this.outliersSymbol = new Label(this.composite, 2048);
        this.outliersSymbol.setAlignment(0x1000000);
        this.outliersSymbol.setImage(ResourceManager.getPluginImage("Star", "icons/plotsymbol1.png"));
        GridData gd_outliersSymbol = new GridData(0x1000000, 4, false, false, 1, 1);
        gd_outliersSymbol.widthHint = 50;
        this.outliersSymbol.setLayoutData((Object)gd_outliersSymbol);
        this.outliersSymbol.setBackground(SWTResourceManager.getColor(22));
        this.outliersSymbolBtn = new Button(this.composite, 0);
        this.outliersSymbolBtn.setImage(ResourceManager.getPluginImage("Star", "icons/ellipsis.png"));
        this.outliersSymbolBtn.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                ChoosePlotSymbolsDialog fillDlg = new ChoosePlotSymbolsDialog(BoxPlotDialog.this.getShell());
                fillDlg.open();
                if (fillDlg.getReturnCode() == 0) {
                    BoxPlotDialog.this.outChar = Integer.parseInt(fillDlg.getChosenSymbol());
                    BoxPlotDialog.this.outliersSymbol.setImage(ResourceManager.getPluginImage("Star", "icons/plotsymbol" + fillDlg.getChosenSymbol() + ".png"));
                }
            }
        });
        new Label(this.composite, 0);
        new Label(this.composite, 0);
        this.lblWidth = new Label(this.composite, 0);
        this.lblWidth.setText("Width:");
        this.medianWidthSpinner = new Spinner(this.composite, 2048);
        this.medianWidthSpinner.setDigits(1);
        this.medianWidthSpinner.setMaximum(30);
        this.medianWidthSpinner.setMinimum(5);
        this.medianWidthSpinner.setSelection(30);
        new Label(this.composite, 0);
        new Label(this.composite, 0);
        new Label(this.composite, 0);
        new Label(this.composite, 0);
        this.label_8 = new Label(this.composite, 0);
        this.label_8.setText("Size:");
        this.outliersSizeSpinner = new Spinner(this.composite, 2048);
        this.outliersSizeSpinner.setDigits(1);
        this.outliersSizeSpinner.setMaximum(30);
        this.outliersSizeSpinner.setMinimum(5);
        this.outliersSizeSpinner.setSelection(10);
        new Label(this.composite, 0);
        this.initializeForm();
        return container;
    }

    void initializeForm() {
        this.dlgManager.initializeSingleSelectionList(this.factorVarList, this.groupsVarTxt, this.moveBtn, this.addGroupsBtn, new TextVarValidator(this.groupsVarTxt, this.lblGroupBy));
        this.dlgManager.initializeSingleButtonList(this.factorVarList, this.axisVarList, this.moveBtn, this.addAxisBtn);
    }

    protected void displayMultipleGraphs(boolean state) {
        this.orientGraphsCombo.setEnabled(state);
        this.lblOrientation.setEnabled(state);
        this.lblNewLabel.setEnabled(state);
        this.numRowsSpinner.setEnabled(state);
        this.lblNumberOfColumns.setEnabled(state);
        this.numColsSpinner.setEnabled(state);
    }

    protected void addItemToVarTable(String varName) {
        int ctr = this.table.getItemCount();
        TableItem tableItem = new TableItem(this.table, 0x1000000);
        tableItem.setText(0, varName);
        System.out.println("set tableItem name: " + varName);
        tableItem.setData("index", (Object)ctr);
        TableEditor[] tableEditors = new TableEditor[]{new TableEditor(this.table), new TableEditor(this.table), new TableEditor(this.table)};
        Text newEditor = new Text((Composite)this.table, 0);
        newEditor.setText(varName);
        Text lowerLimitEditor = new Text((Composite)this.table, 0);
        lowerLimitEditor.setText("Auto");
        GraphsUtilities.addTextModifyListener(lowerLimitEditor);
        Text upperLimitEditor = new Text((Composite)this.table, 0);
        upperLimitEditor.setText("Auto");
        GraphsUtilities.addTextModifyListener(upperLimitEditor);
        tableEditors[0].grabHorizontal = true;
        tableEditors[1].grabHorizontal = true;
        tableEditors[2].grabHorizontal = true;
        tableEditors[0].setEditor((Control)newEditor, tableItem, 1);
        tableEditors[1].setEditor((Control)lowerLimitEditor, tableItem, 2);
        tableEditors[2].setEditor((Control)upperLimitEditor, tableItem, 3);
        tableItem.setData("Editors", (Object)tableEditors);
        this.table.pack();
        this.composite_2.pack();
    }

    protected void removeItemFromVarTable(String varName) {
        TableEditor[] editors;
        int item = 0;
        int i = 0;
        while (i < this.table.getItemCount()) {
            if (varName.equals(this.table.getItem(i).getText(0))) {
                item = i;
            }
            ++i;
        }
        System.out.println(String.valueOf(varName) + "'s index is: " + Integer.toString(item));
        TableItem tableItem = this.table.getItem(item);
        TableEditor[] tableEditorArray = editors = (TableEditor[])tableItem.getData("Editors");
        int n = editors.length;
        int n2 = 0;
        while (n2 < n) {
            TableEditor te = tableEditorArray[n2];
            te.getEditor().dispose();
            ++n2;
        }
        this.table.remove(item);
        this.table.pack();
        this.composite_2.pack();
    }

    protected void setFactors() {
        this.varInfo = this.dataManipulationManager.getVarInfoFromFile(this.file.getAbsolutePath());
        this.numericVariables = this.dataManipulationManager.getNumericVars(this.varInfo);
        this.factorVariables = this.dataManipulationManager.getFactorVars(this.varInfo);
    }

    public void enableFactorButtons(boolean state) {
        this.groupsVarTxt.setSelection(-1);
        this.axisVarList.setSelection(-1);
        this.addGroupsBtn.setEnabled(state);
        this.addAxisBtn.setEnabled(state);
        if (state) {
            this.addGroupsBtn.setText("Add");
            this.addAxisBtn.setText("Add");
            if (this.groupsVarTxt.getSelection() != null) {
                this.addGroupsBtn.setEnabled(false);
            }
            if (this.axisVarList.getItemCount() > 0) {
                this.addAxisBtn.setEnabled(false);
            }
        }
    }

    public void enableNumericButtons(boolean state) {
        this.addBtn.setEnabled(state);
        this.moveBtn.setEnabled(state);
    }

    protected void buttonPressed(int buttonId) {
        if (buttonId == 19) {
            System.out.println("graph type: " + this.analysisType);
            BoxPlotDialog graph = new BoxPlotDialog(this.parentShell, this.analysisType, this.file);
            this.close();
            graph.open();
        }
        super.buttonPressed(buttonId);
    }

    protected void createButtonsForButtonBar(Composite parent) {
        this.createButton(parent, 19, "Reset", true);
        this.createButton(parent, 0, IDialogConstants.OK_LABEL, true);
        this.createButton(parent, 1, IDialogConstants.CANCEL_LABEL, false);
    }

    protected void okPressed() {
        if (this.responseVarList.getItemCount() < 1) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"Please add variable(s) from the numeric variables list.");
        } else if (this.axisVarList.getItemCount() > 3) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"Please enter three categorical variables only.");
        } else {
            this.nVar = this.responseVarList.getItems();
            this.cVars = this.axisVarList.getItemCount() == 0 ? null : this.axisVarList.getItems();
            this.mTitle = this.mainTitleText.getText();
            this.cAxisLab = this.categoryAxisText.getText();
            this.byVar = this.groupsVarTxt.getText().isEmpty() ? null : this.groupsVarTxt.getText();
            this.boxed = this.btnDrawBoxAround.getSelection() ? "TRUE" : "FALSE";
            if (this.btnDisplayMultipleGraphs.getSelection()) {
                this.multGraphs = "TRUE";
                this.numRowsGraphs = this.numRowsSpinner.getSelection();
                this.numColsGraphs = this.numColsSpinner.getSelection();
                this.orientGraphs = this.orientGraphsCombo.getText().replace("to-", "").toLowerCase();
            } else {
                this.multGraphs = "FALSE";
                this.numRowsGraphs = 1;
                this.numColsGraphs = 1;
                this.orientGraphs = "top-bottom";
            }
            this.nAxisLab = new String[this.nVar.length];
            this.yMinValue = new String[this.nVar.length];
            this.yMaxValue = new String[this.nVar.length];
            int ctr = 0;
            TableItem[] tableItemArray = this.table.getItems();
            int n = tableItemArray.length;
            int n2 = 0;
            while (n2 < n) {
                TableItem tableItem = tableItemArray[n2];
                TableEditor[] editors = (TableEditor[])tableItem.getData("Editors");
                this.nAxisLab[ctr] = ((Text)editors[0].getEditor()).getText();
                this.yMinValue[ctr] = GraphsUtilities.checkIfAutoOrNumeric(((Text)editors[1].getEditor()).getText());
                this.yMaxValue[ctr] = GraphsUtilities.checkIfAutoOrNumeric(((Text)editors[2].getEditor()).getText());
                ++ctr;
                ++n2;
            }
            this.boxSize = this.dataManipulationManager.convertInttoDouble(this.boxSizeSpinner.getSelection(), 1);
            this.medLineWidth = this.dataManipulationManager.convertInttoDouble(this.medianWidthSpinner.getSelection(), 1);
            this.whiskLineWidth = this.dataManipulationManager.convertInttoDouble(this.whiskersWidthSpinner.getSelection(), 1);
            this.outCharSize = this.dataManipulationManager.convertInttoDouble(this.outliersSizeSpinner.getSelection(), 1);
            this.plotHoriz = this.btnVertical.getSelection() ? "FALSE" : "TRUE";
            this.axisLabelStyle = this.cmboAxisOrientation.getSelectionIndex();
            String dataFileName = this.file.getAbsolutePath().replaceAll("\\\\", "/");
            File outputFolder = GraphsUtilities.createOutputFolder(this.file.getName(), this.analysisType);
            if (!outputFolder.exists()) {
                outputFolder.mkdir();
            }
            OperationProgressDialog rInfo = new OperationProgressDialog(this.getShell(), "Creating Box Plot");
            rInfo.open();
            ProjectExplorerView.rJavaManager.getRJavaGraphManager().createGraphBoxplot(String.valueOf(outputFolder.getAbsolutePath().replaceAll("\\\\", "/")) + "/", dataFileName, this.nVar, this.cVars, this.mTitle, this.nAxisLab, this.cAxisLab, this.yMinValue, this.yMaxValue, this.axisLabelStyle, this.plotHoriz, this.byVar, this.boxSize, this.boxColor, this.boxFillColor, this.medLineType, this.medLineWidth, this.medColor, this.whiskLineType, this.whiskLineWidth, this.whiskColor, this.outChar, this.outCharSize, this.outColor, this.boxed, this.multGraphs, this.numRowsGraphs, this.numColsGraphs, this.orientGraphs);
            rInfo.close();
            WindowDialogControlUtil.hideAllWindowDialog();
            GraphsUtilities.openFolder(outputFolder);
        }
    }

    protected Point getInitialSize() {
        return new Point(644, 559);
    }

    protected boolean isResizable() {
        return true;
    }
}

