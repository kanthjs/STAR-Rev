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
import org.irri.breedingtool.datamanipulation.dialog.OperationProgressDialog;
import org.irri.breedingtool.graphs.dialog.ChooseLineTypesDialog;
import org.irri.breedingtool.graphs.dialog.ChoosePlotSymbolsDialog;
import org.irri.breedingtool.manager.impl.DataManipulationManager;
import org.irri.breedingtool.projectexplorer.view.ProjectExplorerView;
import org.irri.breedingtool.utility.DialogFormUtility;
import org.irri.breedingtool.utility.GraphsUtilities;
import org.irri.breedingtool.utility.WindowDialogControlUtil;

public class ScatterPlotGraphDialog
extends Dialog {
    private ArrayList<String> varInfo;
    private List numVarList;
    private List factorVarList;
    private List xVarList;
    private Button addXBtn;
    private Button moveBtn;
    private String[] numericVariables;
    private String[] factorVariables;
    private File file;
    private DataManipulationManager dataManipulationManager = new DataManipulationManager();
    private DialogFormUtility dlgManager = new DialogFormUtility();
    private String[] xVar = null;
    private String[] yVar = null;
    private String mTitle = null;
    private String[] xAxisLab = null;
    private String[] yAxisLab = null;
    private String[] xMinValue = null;
    private String[] xMaxValue = null;
    private String[] yMinValue = null;
    private String[] yMaxValue = null;
    private String dispLine = "FALSE";
    private String byVar = null;
    private String pointCol = "rgb(0,0,0, max = 255)";
    private int pointChar = 1;
    private double pointCharSize = 1.0;
    private int lineType = 1;
    private String lineCol = "rgb(0,0,0, max = 255)";
    private double lineWidth = 1.0;
    private String dispR2P = "FALSE";
    private String r2PPos = "bottomright";
    private String boxed = "FALSE";
    private String multGraphs = "FALSE";
    private int numRowsGraphs = 1;
    private int numColsGraphs = 1;
    private String orientGraphs = "top-bottom";
    private int axisLabelStyle = 1;
    private String analysisType;
    private Button addYBtn;
    private Button addPlotsBtn;
    private List yVarList;
    private Table table;
    private TabItem tbtmDisplay;
    private Button btnDrawBoxAround;
    private Text mainTitleText;
    private Button btnDisplayMultipleGraphs;
    private Text colorText;
    private CCombo r2pposCombo;
    private Button btndispR2PButton;
    private Label regressionLineColor;
    private Spinner lineWidthSpinner;
    private Label typeLabel;
    private Button regressionLineTypeBtn;
    private Button button_1;
    private Label label;
    private Button regressionColorBtn;
    private Spinner sizeSpinner;
    private Label lblFormat;
    private Label lblType;
    private Label lblWidth;
    private Label lblColor_1;
    private Label lblPosition;
    private Button btnPlotRegressionLine;
    private Label lblNewLabel;
    private Spinner numRowsSpinner;
    private Label lblNumberOfColumns;
    private Spinner numColsSpinner;
    private Label lblOrientation;
    private CCombo orientGraphsCombo;
    private Composite composite;
    private Shell parentShell;
    private Label lblAxisLabelOrientation;
    private CCombo cmboAxisOrientation;
    private Label lblCreatePlotsBy;
    private List plotsVarList;

    public ScatterPlotGraphDialog(Shell parentShell, String analysisType, File file) {
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
                WindowDialogControlUtil.removeWindowDialogToList(ScatterPlotGraphDialog.this.getShell());
            }
        });
        parent.getShell().setText("Scatter Plot Graph: " + this.dataManipulationManager.getDisplayFileName(this.file.getAbsolutePath()));
        Composite container = (Composite)super.createDialogArea(parent);
        TabFolder tabFolder = new TabFolder(container, 0);
        tabFolder.setLayoutData((Object)new GridData(4, 4, true, true, 1, 1));
        TabItem tbtmModelSpecifications_1 = new TabItem(tabFolder, 0);
        tbtmModelSpecifications_1.setText("Variable Definition");
        Composite modelComposite = new Composite((Composite)tabFolder, 0);
        tbtmModelSpecifications_1.setControl((Control)modelComposite);
        modelComposite.setLayout((Layout)new GridLayout(5, false));
        Label lblNumericVariables = new Label(modelComposite, 0);
        lblNumericVariables.setLayoutData((Object)new GridData(16384, 0x1000000, false, false, 2, 1));
        lblNumericVariables.setText("Numeric Variables:");
        new Label(modelComposite, 0);
        new Label(modelComposite, 0);
        Label lblYVariables = new Label(modelComposite, 0);
        lblYVariables.setText("Y Variable(s):");
        this.numVarList = new List(modelComposite, 2818);
        GridData gd_numVarList = new GridData(4, 4, true, false, 2, 3);
        gd_numVarList.heightHint = 95;
        gd_numVarList.widthHint = 126;
        this.numVarList.setLayoutData((Object)gd_numVarList);
        this.numVarList.setItems(this.numericVariables);
        this.numVarList.addMouseListener(new MouseListener(){

            public void mouseDoubleClick(MouseEvent e) {
            }

            public void mouseDown(MouseEvent e) {
                if (ScatterPlotGraphDialog.this.numVarList.getSelectionCount() > 0) {
                    ScatterPlotGraphDialog.this.moveBtn.setEnabled(true);
                    ScatterPlotGraphDialog.this.factorVarList.setSelection(-1);
                    ScatterPlotGraphDialog.this.xVarList.setSelection(-1);
                    ScatterPlotGraphDialog.this.addXBtn.setText("Add");
                    ScatterPlotGraphDialog.this.addYBtn.setText("Add");
                    ScatterPlotGraphDialog.this.moveBtn.setText("Set to Factor");
                    ScatterPlotGraphDialog.this.enableFactorButtons(false);
                    ScatterPlotGraphDialog.this.enableNumericButtons(true);
                }
            }

            public void mouseUp(MouseEvent e) {
            }
        });
        this.addYBtn = new Button(modelComposite, 0);
        this.addYBtn.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (ScatterPlotGraphDialog.this.numVarList.getSelectionCount() > 0) {
                    int i = 0;
                    while (i < ScatterPlotGraphDialog.this.numVarList.getSelectionCount()) {
                        ScatterPlotGraphDialog.this.addItemToVarTable(ScatterPlotGraphDialog.this.numVarList.getSelection()[i], ScatterPlotGraphDialog.this.yVarList);
                        ++i;
                    }
                    ScatterPlotGraphDialog.this.dataManipulationManager.moveSelectedStringFromTo(ScatterPlotGraphDialog.this.numVarList, ScatterPlotGraphDialog.this.yVarList, ScatterPlotGraphDialog.this.moveBtn);
                } else {
                    int i = 0;
                    while (i < ScatterPlotGraphDialog.this.yVarList.getSelectionCount()) {
                        ScatterPlotGraphDialog.this.removeItemFromVarTable(ScatterPlotGraphDialog.this.yVarList.getSelection()[i]);
                        ++i;
                    }
                    ScatterPlotGraphDialog.this.dataManipulationManager.moveSelectedStringFromTo(ScatterPlotGraphDialog.this.yVarList, ScatterPlotGraphDialog.this.numVarList, ScatterPlotGraphDialog.this.moveBtn);
                }
                ScatterPlotGraphDialog.this.enableNumericButtons(false);
            }
        });
        GridData gd_addYBtn = new GridData(16384, 0x1000000, false, true, 2, 1);
        gd_addYBtn.widthHint = 52;
        this.addYBtn.setLayoutData((Object)gd_addYBtn);
        this.addYBtn.setText("Add");
        this.addYBtn.setEnabled(false);
        this.yVarList = new List(modelComposite, 2818);
        this.yVarList.setLayoutData((Object)new GridData(4, 4, true, true, 1, 1));
        this.yVarList.addMouseListener(new MouseListener(){

            public void mouseDoubleClick(MouseEvent e) {
                int i = 0;
                while (i < ScatterPlotGraphDialog.this.yVarList.getSelectionCount()) {
                    ScatterPlotGraphDialog.this.removeItemFromVarTable(ScatterPlotGraphDialog.this.yVarList.getSelection()[i]);
                    ++i;
                }
                ScatterPlotGraphDialog.this.dataManipulationManager.moveSelectedStringFromTo(ScatterPlotGraphDialog.this.yVarList, ScatterPlotGraphDialog.this.numVarList, ScatterPlotGraphDialog.this.moveBtn);
                ScatterPlotGraphDialog.this.addYBtn.setEnabled(false);
            }

            public void mouseDown(MouseEvent e) {
                if (ScatterPlotGraphDialog.this.yVarList.getSelectionCount() > 0) {
                    ScatterPlotGraphDialog.this.numVarList.setSelection(-1);
                    ScatterPlotGraphDialog.this.addYBtn.setText("Remove");
                    ScatterPlotGraphDialog.this.addYBtn.setEnabled(true);
                }
            }

            public void mouseUp(MouseEvent e) {
            }
        });
        new Label(modelComposite, 0);
        new Label(modelComposite, 0);
        Label lblResponseVariables = new Label(modelComposite, 0);
        lblResponseVariables.setText("X Variable(s):");
        this.addXBtn = new Button(modelComposite, 0);
        this.addXBtn.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (ScatterPlotGraphDialog.this.numVarList.getSelectionCount() > 0) {
                    String[] selectedNumVars = ScatterPlotGraphDialog.this.numVarList.getSelection();
                    int i = 0;
                    while (i < selectedNumVars.length) {
                        ScatterPlotGraphDialog.this.addItemToVarTable(selectedNumVars[i], ScatterPlotGraphDialog.this.xVarList);
                        ScatterPlotGraphDialog.this.xVarList.add(selectedNumVars[i]);
                        ++i;
                    }
                    ScatterPlotGraphDialog.this.numVarList.remove(ScatterPlotGraphDialog.this.numVarList.getSelectionIndices());
                } else {
                    String[] selectedNumVars = ScatterPlotGraphDialog.this.xVarList.getSelection();
                    int i = 0;
                    while (i < selectedNumVars.length) {
                        ScatterPlotGraphDialog.this.removeItemFromVarTable(selectedNumVars[i]);
                        ScatterPlotGraphDialog.this.numVarList.add(selectedNumVars[i]);
                        ++i;
                    }
                    ScatterPlotGraphDialog.this.xVarList.remove(ScatterPlotGraphDialog.this.xVarList.getSelectionIndices());
                }
                ScatterPlotGraphDialog.this.enableNumericButtons(false);
            }
        });
        this.addXBtn.setEnabled(false);
        GridData gd_addXBtn = new GridData(16384, 0x1000000, false, false, 2, 1);
        gd_addXBtn.widthHint = 52;
        this.addXBtn.setLayoutData((Object)gd_addXBtn);
        this.addXBtn.setText("Add");
        this.xVarList = new List(modelComposite, 2818);
        GridData gd_xVarList = new GridData(4, 4, true, true, 1, 1);
        gd_xVarList.widthHint = 111;
        this.xVarList.setLayoutData((Object)gd_xVarList);
        this.xVarList.addMouseListener(new MouseListener(){

            public void mouseDoubleClick(MouseEvent e) {
                int i = 0;
                while (i < ScatterPlotGraphDialog.this.xVarList.getSelectionCount()) {
                    ScatterPlotGraphDialog.this.removeItemFromVarTable(ScatterPlotGraphDialog.this.xVarList.getSelection()[i]);
                    ++i;
                }
                ScatterPlotGraphDialog.this.dataManipulationManager.moveSelectedStringFromTo(ScatterPlotGraphDialog.this.xVarList, ScatterPlotGraphDialog.this.numVarList, ScatterPlotGraphDialog.this.moveBtn);
                ScatterPlotGraphDialog.this.xVarList.remove(ScatterPlotGraphDialog.this.xVarList.getSelectionIndices());
            }

            public void mouseDown(MouseEvent e) {
                if (ScatterPlotGraphDialog.this.xVarList.getSelectionCount() > 0) {
                    ScatterPlotGraphDialog.this.numVarList.setSelection(-1);
                    ScatterPlotGraphDialog.this.addXBtn.setText("Remove");
                    ScatterPlotGraphDialog.this.addXBtn.setEnabled(true);
                    ScatterPlotGraphDialog.this.moveBtn.setEnabled(false);
                    ScatterPlotGraphDialog.this.enableFactorButtons(false);
                }
            }

            public void mouseUp(MouseEvent e) {
            }
        });
        new Label(modelComposite, 0);
        new Label(modelComposite, 0);
        new Label(modelComposite, 0);
        new Label(modelComposite, 0);
        new Label(modelComposite, 0);
        new Label(modelComposite, 0);
        this.moveBtn = new Button(modelComposite, 0);
        this.moveBtn.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (ScatterPlotGraphDialog.this.numVarList.getSelectionCount() > 0) {
                    String[] selectedNumVars = ScatterPlotGraphDialog.this.numVarList.getSelection();
                    int i = 0;
                    while (i < selectedNumVars.length) {
                        ScatterPlotGraphDialog.this.factorVarList.add(selectedNumVars[i]);
                        ScatterPlotGraphDialog.this.dataManipulationManager.editVariableType(ScatterPlotGraphDialog.this.file.getAbsolutePath(), ScatterPlotGraphDialog.this.varInfo, selectedNumVars[i], "Factor");
                        ++i;
                    }
                    ScatterPlotGraphDialog.this.numVarList.remove(ScatterPlotGraphDialog.this.numVarList.getSelectionIndices());
                    ScatterPlotGraphDialog.this.enableNumericButtons(false);
                } else {
                    String[] selectedNumVars = ScatterPlotGraphDialog.this.factorVarList.getSelection();
                    int i = 0;
                    while (i < selectedNumVars.length) {
                        ScatterPlotGraphDialog.this.numVarList.add(selectedNumVars[i]);
                        ScatterPlotGraphDialog.this.dataManipulationManager.editVariableType(ScatterPlotGraphDialog.this.file.getAbsolutePath(), ScatterPlotGraphDialog.this.varInfo, selectedNumVars[i], "Numeric");
                        ++i;
                    }
                    ScatterPlotGraphDialog.this.factorVarList.remove(ScatterPlotGraphDialog.this.factorVarList.getSelectionIndices());
                    ScatterPlotGraphDialog.this.enableFactorButtons(false);
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
        new Label(modelComposite, 0);
        Label lblFactors = new Label(modelComposite, 0);
        lblFactors.setText("Factors:");
        new Label(modelComposite, 0);
        new Label(modelComposite, 0);
        new Label(modelComposite, 0);
        this.lblCreatePlotsBy = new Label(modelComposite, 0);
        this.lblCreatePlotsBy.setText("Group by:");
        this.factorVarList = new List(modelComposite, 2560);
        this.factorVarList.setBackground(SWTResourceManager.getColor(1));
        GridData gd_factorVarList = new GridData(4, 4, true, true, 2, 2);
        gd_factorVarList.widthHint = 65;
        this.factorVarList.setLayoutData((Object)gd_factorVarList);
        this.factorVarList.setItems(this.factorVariables);
        this.factorVarList.addMouseListener(new MouseListener(){

            public void mouseDoubleClick(MouseEvent e) {
                ScatterPlotGraphDialog.this.dataManipulationManager.moveSelectedStringFromTo(ScatterPlotGraphDialog.this.factorVarList, ScatterPlotGraphDialog.this.plotsVarList);
                ScatterPlotGraphDialog.this.moveBtn.setEnabled(false);
            }

            public void mouseDown(MouseEvent e) {
                if (ScatterPlotGraphDialog.this.factorVarList.getSelectionCount() > 0) {
                    ScatterPlotGraphDialog.this.plotsVarList.setSelection(-1);
                    ScatterPlotGraphDialog.this.addPlotsBtn.setText("Remove");
                    ScatterPlotGraphDialog.this.addPlotsBtn.setEnabled(true);
                }
            }

            public void mouseUp(MouseEvent e) {
            }
        });
        this.factorVarList.addListener(3, new Listener(){

            public void handleEvent(Event event) {
                if (ScatterPlotGraphDialog.this.factorVarList.getSelectionIndex() > -1) {
                    ScatterPlotGraphDialog.this.enableNumericButtons(false);
                    ScatterPlotGraphDialog.this.enableFactorButtons(false);
                    ScatterPlotGraphDialog.this.numVarList.setSelection(-1);
                    String[] s = ScatterPlotGraphDialog.this.factorVarList.getSelection();
                    String isNumeric = ScatterPlotGraphDialog.this.dataManipulationManager.isNumeric(ScatterPlotGraphDialog.this.file.getAbsolutePath().replaceAll("\\\\", "/"), s[0]);
                    if (isNumeric.equals("TRUE")) {
                        ScatterPlotGraphDialog.this.moveBtn.setText("Set to Numeric");
                        ScatterPlotGraphDialog.this.moveBtn.setEnabled(true);
                    } else {
                        ScatterPlotGraphDialog.this.moveBtn.setEnabled(false);
                    }
                    ScatterPlotGraphDialog.this.enableFactorButtons(true);
                }
            }
        });
        this.addPlotsBtn = new Button(modelComposite, 0);
        this.addPlotsBtn.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (ScatterPlotGraphDialog.this.factorVarList.getSelectionCount() > 0) {
                    ScatterPlotGraphDialog.this.dataManipulationManager.moveSelectedStringFromTo(ScatterPlotGraphDialog.this.factorVarList, ScatterPlotGraphDialog.this.plotsVarList, ScatterPlotGraphDialog.this.moveBtn);
                } else {
                    ScatterPlotGraphDialog.this.dataManipulationManager.moveSelectedStringFromTo(ScatterPlotGraphDialog.this.plotsVarList, ScatterPlotGraphDialog.this.factorVarList, ScatterPlotGraphDialog.this.moveBtn);
                }
                ScatterPlotGraphDialog.this.enableFactorButtons(false);
            }
        });
        GridData gd_addPlotsBtn = new GridData(16384, 0x1000000, false, false, 2, 1);
        gd_addPlotsBtn.widthHint = 52;
        this.addPlotsBtn.setLayoutData((Object)gd_addPlotsBtn);
        this.addPlotsBtn.setText("Add");
        this.addPlotsBtn.setEnabled(false);
        this.plotsVarList = new List(modelComposite, 2818);
        GridData gd_plotsVarList = new GridData(4, 0x1000000, false, false, 1, 1);
        gd_plotsVarList.heightHint = 5;
        this.plotsVarList.setLayoutData((Object)gd_plotsVarList);
        this.plotsVarList.addMouseListener(new MouseListener(){

            public void mouseDoubleClick(MouseEvent e) {
                ScatterPlotGraphDialog.this.dataManipulationManager.moveSelectedStringFromTo(ScatterPlotGraphDialog.this.plotsVarList, ScatterPlotGraphDialog.this.factorVarList);
                ScatterPlotGraphDialog.this.moveBtn.setEnabled(false);
            }

            public void mouseDown(MouseEvent e) {
                if (ScatterPlotGraphDialog.this.plotsVarList.getSelectionCount() > 0) {
                    ScatterPlotGraphDialog.this.factorVarList.setSelection(-1);
                    ScatterPlotGraphDialog.this.addPlotsBtn.setText("Remove");
                    ScatterPlotGraphDialog.this.addPlotsBtn.setEnabled(true);
                }
            }

            public void mouseUp(MouseEvent e) {
            }
        });
        new Label(modelComposite, 0);
        new Label(modelComposite, 0);
        new Label(modelComposite, 0);
        TabItem tbtmBars = new TabItem(tabFolder, 0);
        tbtmBars.setText("Display Options");
        this.composite = new Composite((Composite)tabFolder, 0);
        tbtmBars.setControl((Control)this.composite);
        this.composite.setLayout((Layout)new GridLayout(8, false));
        Label labelMainTitle = new Label(this.composite, 0);
        labelMainTitle.setText("Main Title:");
        this.mainTitleText = new Text(this.composite, 2048);
        int maxLen = 45;
        this.mainTitleText.setTextLimit(maxLen);
        this.mainTitleText.setLayoutData((Object)new GridData(4, 0x1000000, true, false, 7, 1));
        Label lblPlot = new Label(this.composite, 0);
        lblPlot.setLayoutData((Object)new GridData(16384, 0x1000000, true, false, 8, 1));
        lblPlot.setText("Format Axes:");
        this.table = new Table(this.composite, 67586);
        this.table.setLinesVisible(true);
        this.table.setHeaderVisible(true);
        this.table.setBackground(SWTResourceManager.getColor(1));
        this.table.setLayoutData((Object)new GridData(4, 4, true, true, 8, 1));
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
        this.lblAxisLabelOrientation = new Label(this.composite, 0);
        this.lblAxisLabelOrientation.setLayoutData((Object)new GridData(131072, 0x1000000, false, false, 1, 1));
        this.lblAxisLabelOrientation.setText("Axis Label Orientation:");
        this.cmboAxisOrientation = new CCombo(this.composite, 2056);
        this.cmboAxisOrientation.setBackground(SWTResourceManager.getColor(1));
        this.cmboAxisOrientation.setItems(new String[]{"parallel to axis", "horizontal", "vertical", "perpendicular to axis"});
        this.cmboAxisOrientation.setLayoutData((Object)new GridData(4, 0x1000000, true, false, 1, 1));
        this.cmboAxisOrientation.select(0);
        new Label(this.composite, 0);
        new Label(this.composite, 0);
        new Label(this.composite, 0);
        new Label(this.composite, 0);
        new Label(this.composite, 0);
        new Label(this.composite, 0);
        Label lblFormatPoints = new Label(this.composite, 0);
        lblFormatPoints.setLayoutData((Object)new GridData(16384, 0x1000000, false, false, 8, 1));
        lblFormatPoints.setText("Format Points:");
        Label lblSymbols = new Label(this.composite, 0);
        lblSymbols.setLayoutData((Object)new GridData(131072, 0x1000000, false, false, 1, 1));
        lblSymbols.setText("Symbol:");
        this.label = new Label(this.composite, 0x1000800);
        this.label.setForeground(SWTResourceManager.getColor(1));
        this.label.setText(" ");
        this.label.setBackground(SWTResourceManager.getColor(22));
        this.label.setImage(ResourceManager.getPluginImage("Star", "icons/plotsymbol1.png"));
        GridData gd_label = new GridData(4, 4, true, false, 1, 1);
        gd_label.heightHint = 25;
        gd_label.widthHint = 25;
        this.label.setLayoutData((Object)gd_label);
        this.button_1 = new Button(this.composite, 0);
        this.button_1.setImage(ResourceManager.getPluginImage("Star", "icons/ellipsis.png"));
        this.button_1.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                ChoosePlotSymbolsDialog choosePlotDlg = new ChoosePlotSymbolsDialog(ScatterPlotGraphDialog.this.getShell());
                choosePlotDlg.open();
                if (choosePlotDlg.getReturnCode() == 0) {
                    ScatterPlotGraphDialog.this.pointChar = Integer.parseInt(choosePlotDlg.getChosenSymbol());
                    ScatterPlotGraphDialog.this.label.setImage(ResourceManager.getPluginImage("Star", "icons/plotsymbol" + choosePlotDlg.getChosenSymbol() + ".png"));
                }
            }
        });
        Label lblSize = new Label(this.composite, 0);
        lblSize.setLayoutData((Object)new GridData(131072, 0x1000000, false, false, 1, 1));
        lblSize.setText("Size:");
        this.sizeSpinner = new Spinner(this.composite, 2056);
        this.sizeSpinner.setBackground(SWTResourceManager.getColor(1));
        this.sizeSpinner.setPageIncrement(1);
        this.sizeSpinner.setMaximum(30);
        this.sizeSpinner.setMinimum(5);
        this.sizeSpinner.setSelection(10);
        this.sizeSpinner.setDigits(1);
        Label lblColor = new Label(this.composite, 0);
        lblColor.setLayoutData((Object)new GridData(131072, 0x1000000, false, false, 1, 1));
        lblColor.setText("Color:");
        this.colorText = new Text(this.composite, 2048);
        this.colorText.setBackground(SWTResourceManager.getColor(2));
        this.colorText.setEditable(false);
        this.colorText.setLayoutData((Object)new GridData(4, 4, true, false, 1, 1));
        Button button = new Button(this.composite, 0);
        button.setImage(ResourceManager.getPluginImage("Star", "icons/ellipsis.png"));
        button.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                ScatterPlotGraphDialog.this.pointCol = "rgb(0,0,0, max = 255)";
                try {
                    RGB rgbColor = GraphsUtilities.chooseColor();
                    ScatterPlotGraphDialog.this.colorText.setBackground(new Color((Device)Display.getCurrent(), rgbColor));
                    String color = GraphsUtilities.convertToRrgbFormat(rgbColor.toString());
                    ScatterPlotGraphDialog.this.pointCol = color;
                }
                catch (Exception exception) {
                    ScatterPlotGraphDialog.this.pointCol = "rgb(0,0,0, max = 255)";
                    System.out.println("Exception at RGB choose color");
                }
            }
        });
        this.btnDrawBoxAround = new Button(this.composite, 32);
        this.btnDrawBoxAround.setSelection(true);
        this.btnDrawBoxAround.setLayoutData((Object)new GridData(16384, 0x1000000, false, false, 8, 1));
        this.btnDrawBoxAround.setText("Draw box around plot");
        this.btnDisplayMultipleGraphs = new Button(this.composite, 32);
        this.btnDisplayMultipleGraphs.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (ScatterPlotGraphDialog.this.btnDisplayMultipleGraphs.getSelection()) {
                    ScatterPlotGraphDialog.this.displayMultipleGraphs(true);
                } else {
                    ScatterPlotGraphDialog.this.displayMultipleGraphs(false);
                }
            }
        });
        this.btnDisplayMultipleGraphs.setLayoutData((Object)new GridData(16384, 0x1000000, false, false, 8, 1));
        this.btnDisplayMultipleGraphs.setText("Display multiple graphs in a page");
        new Label(this.composite, 0);
        this.lblNewLabel = new Label(this.composite, 0);
        this.lblNewLabel.setEnabled(false);
        this.lblNewLabel.setLayoutData((Object)new GridData(16384, 0x1000000, false, false, 2, 1));
        this.lblNewLabel.setText("Number of rows:");
        this.numRowsSpinner = new Spinner(this.composite, 2056);
        this.numRowsSpinner.setBackground(SWTResourceManager.getColor(1));
        GridData gd_numRowsSpinner = new GridData(16384, 0x1000000, false, false, 1, 1);
        gd_numRowsSpinner.widthHint = 10;
        this.numRowsSpinner.setLayoutData((Object)gd_numRowsSpinner);
        this.numRowsSpinner.setEnabled(false);
        this.numRowsSpinner.setMaximum(5);
        this.numRowsSpinner.setMinimum(1);
        this.numRowsSpinner.setSelection(2);
        this.lblNumberOfColumns = new Label(this.composite, 0);
        this.lblNumberOfColumns.setEnabled(false);
        this.lblNumberOfColumns.setLayoutData((Object)new GridData(16384, 0x1000000, false, false, 2, 1));
        this.lblNumberOfColumns.setText("Number of columns:");
        this.numColsSpinner = new Spinner(this.composite, 2056);
        this.numColsSpinner.setBackground(SWTResourceManager.getColor(1));
        GridData gd_numColsSpinner = new GridData(16384, 0x1000000, false, false, 1, 1);
        gd_numColsSpinner.widthHint = 10;
        this.numColsSpinner.setLayoutData((Object)gd_numColsSpinner);
        this.numColsSpinner.setEnabled(false);
        this.numColsSpinner.setMaximum(5);
        this.numColsSpinner.setMinimum(1);
        this.numColsSpinner.setSelection(1);
        new Label(this.composite, 0);
        new Label(this.composite, 0);
        this.lblOrientation = new Label(this.composite, 0);
        this.lblOrientation.setEnabled(false);
        this.lblOrientation.setLayoutData((Object)new GridData(16384, 0x1000000, false, false, 2, 1));
        this.lblOrientation.setText("Orientation:");
        this.orientGraphsCombo = new CCombo(this.composite, 2056);
        this.orientGraphsCombo.setItems(new String[]{"Left-to-right", "Top-to-bottom"});
        this.orientGraphsCombo.setEnabled(false);
        this.orientGraphsCombo.setBackground(SWTResourceManager.getColor(1));
        this.orientGraphsCombo.setEditable(false);
        this.orientGraphsCombo.setLayoutData((Object)new GridData(4, 0x1000000, false, false, 5, 1));
        this.orientGraphsCombo.select(0);
        this.tbtmDisplay = new TabItem(tabFolder, 0);
        this.tbtmDisplay.setText("Other Options");
        Composite composite_1 = new Composite((Composite)tabFolder, 0);
        this.tbtmDisplay.setControl((Control)composite_1);
        composite_1.setLayout((Layout)new GridLayout(5, false));
        this.btnPlotRegressionLine = new Button(composite_1, 32);
        this.btnPlotRegressionLine.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (ScatterPlotGraphDialog.this.btnPlotRegressionLine.getSelection()) {
                    ScatterPlotGraphDialog.this.enablePlotRegerssionLine(true);
                } else {
                    ScatterPlotGraphDialog.this.enablePlotRegerssionLine(false);
                }
            }
        });
        this.btnPlotRegressionLine.setLayoutData((Object)new GridData(16384, 0x1000000, false, false, 5, 1));
        this.btnPlotRegressionLine.setText("Plot regression line");
        new Label(composite_1, 0);
        this.lblFormat = new Label(composite_1, 0);
        this.lblFormat.setEnabled(false);
        this.lblFormat.setLayoutData((Object)new GridData(16384, 0x1000000, false, false, 4, 1));
        this.lblFormat.setText("Format:");
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        this.lblType = new Label(composite_1, 0);
        this.lblType.setEnabled(false);
        this.lblType.setText("Type:");
        this.typeLabel = new Label(composite_1, 2048);
        this.typeLabel.setEnabled(false);
        this.typeLabel.setAlignment(0x1000000);
        this.typeLabel.setLayoutData((Object)new GridData(4, 4, false, false, 1, 1));
        this.typeLabel.setImage(ResourceManager.getPluginImage("Star", "icons/line1.png"));
        this.regressionLineTypeBtn = new Button(composite_1, 0);
        this.regressionLineTypeBtn.setImage(ResourceManager.getPluginImage("Star", "icons/ellipsis.png"));
        this.regressionLineTypeBtn.setEnabled(false);
        this.regressionLineTypeBtn.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                ScatterPlotGraphDialog.this.lineType = 1;
                ChooseLineTypesDialog chooseLineDlg = new ChooseLineTypesDialog(ScatterPlotGraphDialog.this.getShell());
                chooseLineDlg.open();
                if (chooseLineDlg.getReturnCode() == 0) {
                    ScatterPlotGraphDialog.this.lineType = Integer.parseInt(chooseLineDlg.getChosenPattern());
                    ScatterPlotGraphDialog.this.typeLabel.setImage(ResourceManager.getPluginImage("Star", "icons/line" + chooseLineDlg.getChosenPattern() + ".png"));
                }
            }
        });
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        this.lblWidth = new Label(composite_1, 0);
        this.lblWidth.setEnabled(false);
        this.lblWidth.setText("Width:");
        this.lineWidthSpinner = new Spinner(composite_1, 2056);
        this.lineWidthSpinner.setBackground(SWTResourceManager.getColor(1));
        this.lineWidthSpinner.setEnabled(false);
        this.lineWidthSpinner.setMaximum(30);
        this.lineWidthSpinner.setDigits(1);
        this.lineWidthSpinner.setMinimum(5);
        this.lineWidthSpinner.setSelection(10);
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        this.lblColor_1 = new Label(composite_1, 0);
        this.lblColor_1.setEnabled(false);
        this.lblColor_1.setText("Color:");
        this.regressionLineColor = new Label(composite_1, 2048);
        this.regressionLineColor.setEnabled(false);
        this.regressionLineColor.setLayoutData((Object)new GridData(4, 4, false, false, 1, 1));
        this.regressionLineColor.setBackground(SWTResourceManager.getColor(2));
        this.regressionLineColor.setText(" ");
        this.regressionColorBtn = new Button(composite_1, 0);
        this.regressionColorBtn.setImage(ResourceManager.getPluginImage("Star", "icons/ellipsis.png"));
        this.regressionColorBtn.setEnabled(false);
        this.regressionColorBtn.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                ScatterPlotGraphDialog.this.lineCol = "rgb(0,0,0, max = 255)";
                try {
                    RGB rgbColor = GraphsUtilities.chooseColor();
                    ScatterPlotGraphDialog.this.regressionLineColor.setBackground(new Color((Device)Display.getCurrent(), rgbColor));
                    String color = GraphsUtilities.convertToRrgbFormat(rgbColor.toString());
                    ScatterPlotGraphDialog.this.lineCol = color;
                }
                catch (Exception exception) {
                    ScatterPlotGraphDialog.this.lineCol = "rgb(0,0,0, max = 255)";
                    System.out.println("Exception at RGB choose color");
                }
            }
        });
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        this.btndispR2PButton = new Button(composite_1, 32);
        this.btndispR2PButton.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (ScatterPlotGraphDialog.this.btndispR2PButton.getSelection()) {
                    ScatterPlotGraphDialog.this.enableRSquaredPValue(true);
                } else {
                    ScatterPlotGraphDialog.this.enableRSquaredPValue(false);
                }
            }
        });
        this.btndispR2PButton.setEnabled(false);
        this.btndispR2PButton.setLayoutData((Object)new GridData(16384, 0x1000000, false, false, 4, 1));
        this.btndispR2PButton.setText("Print R-squared and p-value");
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        this.lblPosition = new Label(composite_1, 0);
        this.lblPosition.setEnabled(false);
        this.lblPosition.setText("Position:");
        this.r2pposCombo = new CCombo(composite_1, 2056);
        this.r2pposCombo.setEnabled(false);
        this.r2pposCombo.setBackground(SWTResourceManager.getColor(1));
        this.r2pposCombo.setEditable(false);
        this.r2pposCombo.setItems(new String[]{"bottom", "bottom-left", "bottom-right", "center", "left", "right", "top", "top-left", "top-right"});
        this.r2pposCombo.setText("bottom-right");
        new Label(composite_1, 0);
        return container;
    }

    protected void displayMultipleGraphs(boolean state) {
        this.orientGraphsCombo.setEnabled(state);
        this.lblOrientation.setEnabled(state);
        this.lblNewLabel.setEnabled(state);
        this.numRowsSpinner.setEnabled(state);
        this.lblNumberOfColumns.setEnabled(state);
        this.numColsSpinner.setEnabled(state);
    }

    protected void enableRSquaredPValue(boolean state) {
        this.lblPosition.setEnabled(state);
        this.r2pposCombo.setEnabled(state);
    }

    protected void enablePlotRegerssionLine(boolean state) {
        this.lblFormat.setEnabled(state);
        this.lblType.setEnabled(state);
        this.typeLabel.setEnabled(state);
        this.regressionLineTypeBtn.setEnabled(state);
        this.lblWidth.setEnabled(state);
        this.lineWidthSpinner.setEnabled(state);
        this.lblColor_1.setEnabled(state);
        this.regressionLineColor.setEnabled(state);
        this.regressionColorBtn.setEnabled(state);
        this.btndispR2PButton.setEnabled(state);
        if (this.btndispR2PButton.getSelection()) {
            this.enableRSquaredPValue(state);
        }
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
        this.composite.pack();
    }

    protected void addItemToVarTable(String varName, List varList) {
        int ctr = this.table.getItemCount();
        if (varList.equals(this.xVarList)) {
            ctr = varList.getItemCount();
        }
        TableItem tableItem = new TableItem(this.table, 0x1000000, ctr);
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
        this.composite.pack();
    }

    protected void setFactors() {
        this.varInfo = this.dataManipulationManager.getVarInfoFromFile(this.file.getAbsolutePath());
        this.numericVariables = this.dataManipulationManager.getNumericVars(this.varInfo);
        this.factorVariables = this.dataManipulationManager.getFactorVars(this.varInfo);
    }

    public void enableFactorButtons(boolean state) {
        this.plotsVarList.setSelection(-1);
        this.addPlotsBtn.setEnabled(state);
        if (state) {
            this.addPlotsBtn.setText("Add");
            if (this.plotsVarList.getItemCount() > 0) {
                this.addPlotsBtn.setEnabled(false);
            }
        }
    }

    public void enableNumericButtons(boolean state) {
        this.addXBtn.setEnabled(state);
        this.addYBtn.setEnabled(state);
        this.moveBtn.setEnabled(state);
    }

    protected void createButtonsForButtonBar(Composite parent) {
        this.createButton(parent, 19, "Reset", true);
        this.createButton(parent, 0, IDialogConstants.OK_LABEL, true);
        this.createButton(parent, 1, IDialogConstants.CANCEL_LABEL, false);
    }

    protected void buttonPressed(int buttonId) {
        if (buttonId == 19) {
            System.out.println("graph type: " + this.analysisType);
            ScatterPlotGraphDialog graph = new ScatterPlotGraphDialog(this.parentShell, this.analysisType, this.file);
            this.close();
            graph.open();
        }
        super.buttonPressed(buttonId);
    }

    protected void okPressed() {
        if (this.xVarList.getItemCount() < 1) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"Please add an x-variable from the numeric variables list.");
        } else if (this.yVarList.getItemCount() < 1) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"Please add a y-variable from the numeric variables list.");
        } else {
            this.xVar = this.xVarList.getItems();
            this.yVar = this.yVarList.getItems();
            this.xAxisLab = new String[this.xVar.length];
            this.xMinValue = new String[this.xVar.length];
            this.xMaxValue = new String[this.xVar.length];
            this.yAxisLab = new String[this.yVar.length];
            this.yMinValue = new String[this.yVar.length];
            this.yMaxValue = new String[this.yVar.length];
            int ctr = 0;
            TableItem[] tableItemArray = this.table.getItems();
            int n = tableItemArray.length;
            int n2 = 0;
            while (n2 < n) {
                TableItem tableItem = tableItemArray[n2];
                TableEditor[] editors = (TableEditor[])tableItem.getData("Editors");
                if (ctr < this.xVar.length) {
                    this.xAxisLab[ctr] = ((Text)editors[0].getEditor()).getText();
                    this.xMinValue[ctr] = GraphsUtilities.checkIfAutoOrNumeric(((Text)editors[1].getEditor()).getText());
                    this.xMaxValue[ctr] = GraphsUtilities.checkIfAutoOrNumeric(((Text)editors[2].getEditor()).getText());
                } else {
                    System.out.println("Now Y: " + Integer.toString(ctr - this.xVar.length));
                    this.yAxisLab[ctr - this.xVar.length] = ((Text)editors[0].getEditor()).getText();
                    this.yMinValue[ctr - this.xVar.length] = GraphsUtilities.checkIfAutoOrNumeric(((Text)editors[1].getEditor()).getText());
                    this.yMaxValue[ctr - this.xVar.length] = GraphsUtilities.checkIfAutoOrNumeric(((Text)editors[2].getEditor()).getText());
                }
                ++ctr;
                ++n2;
            }
            this.mTitle = this.mainTitleText.getText();
            this.byVar = this.plotsVarList.getItemCount() < 1 ? null : this.plotsVarList.getItem(0);
            this.boxed = this.btnDrawBoxAround.getSelection() ? "TRUE" : "FALSE";
            this.pointCharSize = this.dataManipulationManager.convertInttoDouble(this.sizeSpinner.getSelection(), 1);
            this.lineWidth = this.dataManipulationManager.convertInttoDouble(this.lineWidthSpinner.getSelection(), 1);
            this.dispR2P = this.btndispR2PButton.getSelection() ? "TRUE" : "FALSE";
            this.r2PPos = this.r2pposCombo.getText().replaceAll("-", "").toLowerCase();
            this.boxed = this.btnDrawBoxAround.getSelection() ? "TRUE" : "FALSE";
            this.axisLabelStyle = this.cmboAxisOrientation.getSelectionIndex();
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
            this.dispLine = this.btnPlotRegressionLine.getSelection() ? "TRUE" : "FALSE";
            File outputFolder = GraphsUtilities.createOutputFolder(this.file.getName(), this.analysisType);
            if (!outputFolder.exists()) {
                outputFolder.mkdir();
            }
            String dataFileName = this.file.getAbsolutePath().replaceAll("\\\\", "/");
            OperationProgressDialog rInfo = new OperationProgressDialog(this.getShell(), "Creating Scatter Plot");
            rInfo.open();
            ProjectExplorerView.rJavaManager.getRJavaGraphManager().createGraphScatterplot(String.valueOf(outputFolder.getAbsolutePath().replaceAll("\\\\", "/")) + "/", dataFileName, this.xVar, this.yVar, this.mTitle, this.xAxisLab, this.yAxisLab, this.xMinValue, this.xMaxValue, this.yMinValue, this.yMaxValue, this.axisLabelStyle, this.byVar, this.pointCol, this.pointChar, this.pointCharSize, this.dispLine, this.lineType, this.lineCol, this.lineWidth, this.dispR2P, this.r2PPos, this.boxed, this.multGraphs, this.numRowsGraphs, this.numColsGraphs, this.orientGraphs);
            rInfo.close();
            WindowDialogControlUtil.hideAllWindowDialog();
            GraphsUtilities.openFolder(outputFolder);
        }
    }

    protected Point getInitialSize() {
        return new Point(648, 580);
    }

    protected boolean isResizable() {
        return true;
    }
}

