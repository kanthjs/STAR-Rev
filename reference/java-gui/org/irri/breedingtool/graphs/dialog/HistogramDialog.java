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
import org.irri.breedingtool.manager.impl.DataManipulationManager;
import org.irri.breedingtool.projectexplorer.view.ProjectExplorerView;
import org.irri.breedingtool.utility.GraphsUtilities;
import org.irri.breedingtool.utility.WindowDialogControlUtil;

public class HistogramDialog
extends Dialog {
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
    private String[] nVar = null;
    private String mTitle = null;
    private String[] yAxisLabs = null;
    private String[] xAxisLabs = null;
    private String[] xMinValues = null;
    private String[] xMaxValues = null;
    private String[] yMinValues = null;
    private String[] yMaxValues = null;
    private String useFreq = "FALSE";
    private String numBins = "Auto";
    private String byVar = null;
    private String boxed = "FALSE";
    private String multGraphs = "FALSE";
    private int numRowsGraphs = 1;
    private int numColsGraphs = 1;
    private String orientGraphs = "top-bottom";
    private int axisLabelStyle = 1;
    private String barColor = "rgb(255,0,255, max = 255, alpha = 127)";
    private String dispCurve = "TRUE";
    private String lineColor = "rgb(0,0,0, max = 255)";
    private int lineType = 1;
    private double lineWidth = 1.0;
    private String analysisType;
    private Button addGroupsBtn;
    private List groupsVarList;
    private Text mainTitleText;
    private Button btnDrawBoxAround;
    private Button btnDisplayMultipleGraphs;
    private CCombo orientGraphsCombo;
    private Label lblOrientation;
    private Composite composite;
    private Label label_3;
    private Button btnFrequency;
    private Button btnProbabilityDensities;
    private Label lblUse;
    private Button btnOverlayKernelDensity;
    private Label lblNumberOfBars;
    private Text txtNumBars;
    private Shell parentShell;
    private Label lblNewLabel_1;
    private CCombo cmboAxisOrientation;
    private Table tableXaxis;
    private TableColumn tableColumn;
    private TableColumn tableColumn_7;
    private TableColumn tableColumn_8;
    private TableColumn tableColumn_9;
    private TabItem tbtmDisplayOptions;
    private Table tableYaxis;
    private TableColumn tableColumn_10;
    private TableColumn tableColumn_11;
    private TableColumn tableColumn_12;
    private TableColumn tableColumn_13;
    private Label lblNewLabel;
    private Spinner numRowsSpinner;
    private Label lblNumberOfColumns;
    private Spinner numColsSpinner;
    private Label lblLineType;
    private Label lineDefaultType;
    private Button lineDefaultTypebtn;
    private Label lblWidth;
    private Spinner lineWidthSpinner;
    private Label lblBarColor;
    private Label barDefaultColor;
    private Button barDefaultColorbtn;
    private Label lblLineColor;
    private Label lineDefaultColor;
    private Button lineDefaultColorbtn;
    private Label lblNewLabel_2;

    public HistogramDialog(Shell parentShell, String analysisType, File file) {
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
                WindowDialogControlUtil.removeWindowDialogToList(HistogramDialog.this.getShell());
            }
        });
        parent.getShell().setText("Histogram: " + this.dataManipulationManager.getDisplayFileName(this.file.getAbsolutePath()));
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
                String[] selectedNumVars = HistogramDialog.this.numVarList.getSelection();
                HistogramDialog.this.addItemToXaxisTable(selectedNumVars[0], HistogramDialog.this.responseVarList);
                HistogramDialog.this.addItemToYaxisTable(selectedNumVars[0], HistogramDialog.this.responseVarList);
                HistogramDialog.this.dataManipulationManager.moveSelectedStringFromTo(HistogramDialog.this.numVarList, HistogramDialog.this.responseVarList, HistogramDialog.this.moveBtn);
                HistogramDialog.this.numVarList.remove(HistogramDialog.this.numVarList.getSelectionIndices());
                HistogramDialog.this.addBtn.setEnabled(false);
            }

            public void mouseDown(MouseEvent e) {
                if (HistogramDialog.this.numVarList.getSelectionCount() > 0) {
                    HistogramDialog.this.moveBtn.setEnabled(true);
                    HistogramDialog.this.factorVarList.setSelection(-1);
                    HistogramDialog.this.responseVarList.setSelection(-1);
                    HistogramDialog.this.addBtn.setText("Add");
                    HistogramDialog.this.moveBtn.setText("Set to Factor");
                    HistogramDialog.this.enableFactorButtons(false);
                    HistogramDialog.this.enableNumericButtons(true);
                }
            }

            public void mouseUp(MouseEvent e) {
            }
        });
        this.addBtn = new Button(modelComposite, 0);
        this.addBtn.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (HistogramDialog.this.numVarList.getSelectionCount() > 0) {
                    String[] selectedNumVars = HistogramDialog.this.numVarList.getSelection();
                    int i = 0;
                    while (i < selectedNumVars.length) {
                        HistogramDialog.this.addItemToXaxisTable(selectedNumVars[i], HistogramDialog.this.responseVarList);
                        HistogramDialog.this.addItemToYaxisTable(selectedNumVars[i], HistogramDialog.this.responseVarList);
                        HistogramDialog.this.responseVarList.add(selectedNumVars[i]);
                        ++i;
                    }
                    HistogramDialog.this.numVarList.remove(HistogramDialog.this.numVarList.getSelectionIndices());
                } else {
                    String[] selectedNumVars = HistogramDialog.this.responseVarList.getSelection();
                    int i = 0;
                    while (i < selectedNumVars.length) {
                        HistogramDialog.this.removeItemFromXaxisTable(selectedNumVars[i]);
                        HistogramDialog.this.removeItemFromYaxisTable(selectedNumVars[i]);
                        HistogramDialog.this.numVarList.add(selectedNumVars[i]);
                        ++i;
                    }
                    HistogramDialog.this.responseVarList.remove(HistogramDialog.this.responseVarList.getSelectionIndices());
                }
                HistogramDialog.this.enableNumericButtons(false);
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
                HistogramDialog.this.removeItemFromXaxisTable(HistogramDialog.this.responseVarList.getSelection()[0]);
                HistogramDialog.this.removeItemFromYaxisTable(HistogramDialog.this.responseVarList.getSelection()[0]);
                HistogramDialog.this.dataManipulationManager.moveSelectedStringFromTo(HistogramDialog.this.responseVarList, HistogramDialog.this.numVarList, HistogramDialog.this.moveBtn);
                HistogramDialog.this.responseVarList.remove(HistogramDialog.this.responseVarList.getSelectionIndices());
                HistogramDialog.this.addBtn.setEnabled(false);
            }

            public void mouseDown(MouseEvent e) {
                if (HistogramDialog.this.responseVarList.getSelectionCount() > 0) {
                    HistogramDialog.this.numVarList.setSelection(-1);
                    HistogramDialog.this.addBtn.setText("Remove");
                    HistogramDialog.this.addBtn.setEnabled(true);
                    HistogramDialog.this.moveBtn.setEnabled(false);
                    HistogramDialog.this.enableFactorButtons(false);
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
                if (HistogramDialog.this.numVarList.getSelectionCount() > 0) {
                    String[] selectedNumVars = HistogramDialog.this.numVarList.getSelection();
                    int i = 0;
                    while (i < selectedNumVars.length) {
                        HistogramDialog.this.factorVarList.add(selectedNumVars[i]);
                        HistogramDialog.this.dataManipulationManager.editVariableType(HistogramDialog.this.file.getAbsolutePath(), HistogramDialog.this.varInfo, selectedNumVars[i], "Factor");
                        ++i;
                    }
                    HistogramDialog.this.numVarList.remove(HistogramDialog.this.numVarList.getSelectionIndices());
                    HistogramDialog.this.enableNumericButtons(false);
                } else {
                    String[] selectedNumVars = HistogramDialog.this.factorVarList.getSelection();
                    int i = 0;
                    while (i < selectedNumVars.length) {
                        HistogramDialog.this.numVarList.add(selectedNumVars[i]);
                        HistogramDialog.this.dataManipulationManager.editVariableType(HistogramDialog.this.file.getAbsolutePath(), HistogramDialog.this.varInfo, selectedNumVars[i], "Numeric");
                        ++i;
                    }
                    HistogramDialog.this.factorVarList.remove(HistogramDialog.this.factorVarList.getSelectionIndices());
                    HistogramDialog.this.enableFactorButtons(false);
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
        Label lblGroupBy = new Label(modelComposite, 0);
        lblGroupBy.setText("Group by:");
        this.factorVarList = new List(modelComposite, 2560);
        GridData gd_factorVarList = new GridData(4, 4, true, true, 2, 1);
        gd_factorVarList.widthHint = 65;
        this.factorVarList.setLayoutData((Object)gd_factorVarList);
        this.factorVarList.setItems(this.factorVariables);
        this.factorVarList.addMouseListener(new MouseListener(){

            public void mouseDoubleClick(MouseEvent e) {
                HistogramDialog.this.dataManipulationManager.moveSelectedStringFromTo(HistogramDialog.this.factorVarList, HistogramDialog.this.groupsVarList);
                HistogramDialog.this.addGroupsBtn.setEnabled(false);
            }

            public void mouseDown(MouseEvent e) {
                if (HistogramDialog.this.factorVarList.getSelectionCount() > 0) {
                    HistogramDialog.this.groupsVarList.setSelection(-1);
                    HistogramDialog.this.addGroupsBtn.setText("Remove");
                    HistogramDialog.this.addGroupsBtn.setEnabled(true);
                }
            }

            public void mouseUp(MouseEvent e) {
            }
        });
        this.factorVarList.addListener(3, new Listener(){

            public void handleEvent(Event event) {
                if (HistogramDialog.this.factorVarList.getSelectionIndex() > -1) {
                    HistogramDialog.this.enableNumericButtons(false);
                    HistogramDialog.this.enableFactorButtons(false);
                    HistogramDialog.this.numVarList.setSelection(-1);
                    String[] s = HistogramDialog.this.factorVarList.getSelection();
                    String isNumeric = HistogramDialog.this.dataManipulationManager.isNumeric(HistogramDialog.this.file.getAbsolutePath().replaceAll("\\\\", "/"), s[0]);
                    if (isNumeric.equals("TRUE")) {
                        HistogramDialog.this.moveBtn.setText("Set to Numeric");
                        HistogramDialog.this.moveBtn.setEnabled(true);
                    } else {
                        HistogramDialog.this.moveBtn.setEnabled(false);
                    }
                    HistogramDialog.this.enableFactorButtons(true);
                }
            }
        });
        this.addGroupsBtn = new Button(modelComposite, 0);
        this.addGroupsBtn.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (HistogramDialog.this.factorVarList.getSelectionCount() > 0) {
                    HistogramDialog.this.dataManipulationManager.moveSelectedStringFromTo(HistogramDialog.this.factorVarList, HistogramDialog.this.groupsVarList);
                } else {
                    HistogramDialog.this.dataManipulationManager.moveSelectedStringFromTo(HistogramDialog.this.groupsVarList, HistogramDialog.this.factorVarList);
                }
                HistogramDialog.this.enableFactorButtons(false);
            }
        });
        GridData gd_addGroupsBtn = new GridData(16384, 128, false, false, 2, 1);
        gd_addGroupsBtn.widthHint = 52;
        this.addGroupsBtn.setLayoutData((Object)gd_addGroupsBtn);
        this.addGroupsBtn.setText("Add");
        this.addGroupsBtn.setEnabled(false);
        this.groupsVarList = new List(modelComposite, 2818);
        GridData gd_groupsVarList = new GridData(4, 128, true, true, 1, 1);
        gd_groupsVarList.heightHint = 5;
        this.groupsVarList.setLayoutData((Object)gd_groupsVarList);
        this.groupsVarList.addMouseListener(new MouseListener(){

            public void mouseDoubleClick(MouseEvent e) {
                HistogramDialog.this.dataManipulationManager.moveSelectedStringFromTo(HistogramDialog.this.groupsVarList, HistogramDialog.this.factorVarList);
                HistogramDialog.this.addGroupsBtn.setEnabled(false);
            }

            public void mouseDown(MouseEvent e) {
                if (HistogramDialog.this.groupsVarList.getSelectionCount() > 0) {
                    HistogramDialog.this.factorVarList.setSelection(-1);
                    HistogramDialog.this.addGroupsBtn.setText("Remove");
                    HistogramDialog.this.addGroupsBtn.setEnabled(true);
                }
            }

            public void mouseUp(MouseEvent e) {
            }
        });
        this.tbtmDisplayOptions = new TabItem(tabFolder, 0);
        this.tbtmDisplayOptions.setText("Display Options");
        Composite composite_2 = new Composite((Composite)tabFolder, 0);
        this.tbtmDisplayOptions.setControl((Control)composite_2);
        composite_2.setLayout((Layout)new GridLayout(6, false));
        Label lblMainTitle = new Label(composite_2, 0);
        lblMainTitle.setText("Main Title:");
        this.mainTitleText = new Text(composite_2, 2048);
        int maxLen = 45;
        this.mainTitleText.setTextLimit(maxLen);
        this.mainTitleText.setLayoutData((Object)new GridData(4, 0x1000000, false, false, 5, 1));
        this.lblUse = new Label(composite_2, 0);
        this.lblUse.setText("Use:");
        this.btnFrequency = new Button(composite_2, 16);
        this.btnFrequency.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (HistogramDialog.this.btnFrequency.getSelection()) {
                    HistogramDialog.this.btnOverlayKernelDensity.setSelection(false);
                    HistogramDialog.this.btnProbabilityDensities.setSelection(false);
                    HistogramDialog.this.enableOverlayDensityOptions(false);
                    int i = 0;
                    while (i < HistogramDialog.this.tableYaxis.getItemCount()) {
                        TableItem tableItem = HistogramDialog.this.tableYaxis.getItem(i);
                        TableEditor[] tb = (TableEditor[])tableItem.getData("Editors");
                        int c = 0;
                        TableEditor[] tableEditorArray = tb;
                        int n = tb.length;
                        int n2 = 0;
                        while (n2 < n) {
                            TableEditor te = tableEditorArray[n2];
                            if (c == 0) {
                                ((Text)te.getEditor()).setText("Frequency");
                            }
                            ++c;
                            ++n2;
                        }
                        ++i;
                    }
                }
            }
        });
        this.btnFrequency.setSelection(true);
        this.btnFrequency.setLayoutData((Object)new GridData(0x1000000, 0x1000000, false, false, 1, 1));
        this.btnFrequency.setText("frequencies");
        this.btnProbabilityDensities = new Button(composite_2, 16);
        this.btnProbabilityDensities.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (HistogramDialog.this.btnProbabilityDensities.getSelection()) {
                    HistogramDialog.this.btnFrequency.setSelection(false);
                    int i = 0;
                    while (i < HistogramDialog.this.tableYaxis.getItemCount()) {
                        TableItem tableItem = HistogramDialog.this.tableYaxis.getItem(i);
                        TableEditor[] tb = (TableEditor[])tableItem.getData("Editors");
                        int c = 0;
                        TableEditor[] tableEditorArray = tb;
                        int n = tb.length;
                        int n2 = 0;
                        while (n2 < n) {
                            TableEditor te = tableEditorArray[n2];
                            if (c == 0) {
                                ((Text)te.getEditor()).setText("Probability");
                            }
                            ++c;
                            ++n2;
                        }
                        ++i;
                    }
                }
            }
        });
        this.btnProbabilityDensities.setText("probability densities");
        new Label(composite_2, 0);
        new Label(composite_2, 0);
        new Label(composite_2, 0);
        this.label_3 = new Label(composite_2, 0);
        this.label_3.setText("X-Axis:");
        new Label(composite_2, 0);
        new Label(composite_2, 0);
        Label lblValueAxisLabel = new Label(composite_2, 0);
        lblValueAxisLabel.setText("Y-Axis:");
        new Label(composite_2, 0);
        new Label(composite_2, 0);
        this.tableXaxis = new Table(composite_2, 67586);
        this.tableXaxis.setLinesVisible(true);
        this.tableXaxis.setHeaderVisible(true);
        this.tableXaxis.setBackground(SWTResourceManager.getColor(1));
        this.tableXaxis.setLayoutData((Object)new GridData(4, 4, true, true, 3, 1));
        this.tableColumn = new TableColumn(this.tableXaxis, 0);
        this.tableColumn.setWidth(85);
        this.tableColumn.setText("Variable");
        this.tableColumn_7 = new TableColumn(this.tableXaxis, 0);
        this.tableColumn_7.setWidth(83);
        this.tableColumn_7.setText("Label");
        this.tableColumn_8 = new TableColumn(this.tableXaxis, 0);
        this.tableColumn_8.setWidth(87);
        this.tableColumn_8.setText("Lower Limit");
        this.tableColumn_9 = new TableColumn(this.tableXaxis, 0);
        this.tableColumn_9.setWidth(72);
        this.tableColumn_9.setText("Upper Limit");
        this.tableYaxis = new Table(composite_2, 67586);
        this.tableYaxis.setLinesVisible(true);
        this.tableYaxis.setHeaderVisible(true);
        this.tableYaxis.setBackground(SWTResourceManager.getColor(1));
        this.tableYaxis.setLayoutData((Object)new GridData(4, 4, true, true, 3, 1));
        this.tableColumn_10 = new TableColumn(this.tableYaxis, 0);
        this.tableColumn_10.setWidth(85);
        this.tableColumn_10.setText("Variable");
        this.tableColumn_11 = new TableColumn(this.tableYaxis, 0);
        this.tableColumn_11.setWidth(83);
        this.tableColumn_11.setText("Label");
        this.tableColumn_12 = new TableColumn(this.tableYaxis, 0);
        this.tableColumn_12.setWidth(87);
        this.tableColumn_12.setText("Lower Limit");
        this.tableColumn_13 = new TableColumn(this.tableYaxis, 0);
        this.tableColumn_13.setWidth(72);
        this.tableColumn_13.setText("Upper Limit");
        this.lblNewLabel_1 = new Label(composite_2, 0);
        this.lblNewLabel_1.setLayoutData((Object)new GridData(16384, 0x1000000, false, false, 2, 1));
        this.lblNewLabel_1.setText("Axis Label Orientation:");
        this.cmboAxisOrientation = new CCombo(composite_2, 2056);
        GridData gd_cmboAxisOrientation = new GridData(4, 0x1000000, false, false, 1, 1);
        gd_cmboAxisOrientation.widthHint = 193;
        this.cmboAxisOrientation.setLayoutData((Object)gd_cmboAxisOrientation);
        this.cmboAxisOrientation.setItems(new String[]{"parallel to axis", "horizontal", "vertical", "perpendicular to axis"});
        this.cmboAxisOrientation.setBackground(SWTResourceManager.getColor(1));
        this.cmboAxisOrientation.select(0);
        new Label(composite_2, 0);
        new Label(composite_2, 0);
        new Label(composite_2, 0);
        this.btnDrawBoxAround = new Button(composite_2, 32);
        this.btnDrawBoxAround.setSelection(true);
        this.btnDrawBoxAround.setLayoutData((Object)new GridData(16384, 0x1000000, false, false, 6, 1));
        this.btnDrawBoxAround.setText("Draw box around plot");
        this.btnDisplayMultipleGraphs = new Button(composite_2, 32);
        this.btnDisplayMultipleGraphs.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (HistogramDialog.this.btnDisplayMultipleGraphs.getSelection()) {
                    HistogramDialog.this.displayMultipleGraphs(true);
                } else {
                    HistogramDialog.this.displayMultipleGraphs(false);
                }
            }
        });
        this.btnDisplayMultipleGraphs.setLayoutData((Object)new GridData(16384, 0x1000000, false, false, 6, 1));
        this.btnDisplayMultipleGraphs.setText("Display multiple graphs in a page");
        new Label(composite_2, 0);
        this.lblNewLabel = new Label(composite_2, 0);
        this.lblNewLabel.setText("Number of rows:");
        this.lblNewLabel.setEnabled(false);
        this.numRowsSpinner = new Spinner(composite_2, 2056);
        this.numRowsSpinner.setBackground(SWTResourceManager.getColor(1));
        GridData gd_numRowsSpinner = new GridData(16384, 0x1000000, false, false, 1, 1);
        gd_numRowsSpinner.widthHint = 10;
        this.numRowsSpinner.setLayoutData((Object)gd_numRowsSpinner);
        this.numRowsSpinner.setMaximum(5);
        this.numRowsSpinner.setMinimum(1);
        this.numRowsSpinner.setSelection(2);
        this.numRowsSpinner.setEnabled(false);
        this.lblNumberOfColumns = new Label(composite_2, 0);
        this.lblNumberOfColumns.setText("Number of columns:");
        this.lblNumberOfColumns.setEnabled(false);
        this.numColsSpinner = new Spinner(composite_2, 2056);
        this.numColsSpinner.setBackground(SWTResourceManager.getColor(1));
        GridData gd_numColsSpinner = new GridData(16384, 0x1000000, false, false, 1, 1);
        gd_numColsSpinner.widthHint = 10;
        this.numColsSpinner.setLayoutData((Object)gd_numColsSpinner);
        this.numColsSpinner.setMaximum(5);
        this.numColsSpinner.setMinimum(1);
        this.numColsSpinner.setSelection(1);
        this.numColsSpinner.setEnabled(false);
        new Label(composite_2, 0);
        new Label(composite_2, 0);
        this.lblOrientation = new Label(composite_2, 0);
        this.lblOrientation.setEnabled(false);
        this.lblOrientation.setText("Orientation:");
        this.orientGraphsCombo = new CCombo(composite_2, 2056);
        this.orientGraphsCombo.setBackground(SWTResourceManager.getColor(1));
        this.orientGraphsCombo.setEditable(false);
        this.orientGraphsCombo.setEnabled(false);
        this.orientGraphsCombo.setItems(new String[]{"Left-to-right", "Top-to-bottom"});
        this.orientGraphsCombo.setLayoutData((Object)new GridData(4, 0x1000000, false, false, 3, 1));
        this.orientGraphsCombo.select(0);
        new Label(composite_2, 0);
        TabItem tbtmBars = new TabItem(tabFolder, 0);
        tbtmBars.setText("Other Options");
        this.composite = new Composite((Composite)tabFolder, 0);
        tbtmBars.setControl((Control)this.composite);
        this.composite.setLayout((Layout)new GridLayout(6, false));
        this.lblNumberOfBars = new Label(this.composite, 0);
        this.lblNumberOfBars.setLayoutData((Object)new GridData(16384, 0x1000000, false, false, 3, 1));
        this.lblNumberOfBars.setText("Number of bars:");
        this.txtNumBars = new Text(this.composite, 2048);
        this.txtNumBars.setText("Auto");
        new Label(this.composite, 0);
        new Label(this.composite, 0);
        this.btnOverlayKernelDensity = new Button(this.composite, 32);
        this.btnOverlayKernelDensity.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (HistogramDialog.this.btnOverlayKernelDensity.getSelection()) {
                    HistogramDialog.this.btnFrequency.setSelection(false);
                    HistogramDialog.this.btnProbabilityDensities.setSelection(true);
                    HistogramDialog.this.enableOverlayDensityOptions(true);
                    int i = 0;
                    while (i < HistogramDialog.this.tableYaxis.getItemCount()) {
                        TableItem tableItem = HistogramDialog.this.tableYaxis.getItem(i);
                        TableEditor[] tb = (TableEditor[])tableItem.getData("Editors");
                        int c = 0;
                        TableEditor[] tableEditorArray = tb;
                        int n = tb.length;
                        int n2 = 0;
                        while (n2 < n) {
                            TableEditor te = tableEditorArray[n2];
                            if (c == 0) {
                                ((Text)te.getEditor()).setText("Probability");
                            }
                            ++c;
                            ++n2;
                        }
                        ++i;
                    }
                } else {
                    HistogramDialog.this.enableOverlayDensityOptions(false);
                }
            }
        });
        this.btnOverlayKernelDensity.setLayoutData((Object)new GridData(16384, 0x1000000, false, false, 6, 1));
        this.btnOverlayKernelDensity.setText("Overlay kernel density estimates");
        Label lblPlot = new Label(this.composite, 0);
        lblPlot.setLayoutData((Object)new GridData(16384, 0x1000000, true, false, 6, 1));
        lblPlot.setText("Format Components:");
        new Label(this.composite, 0);
        new Label(this.composite, 0);
        this.lblBarColor = new Label(this.composite, 0);
        this.lblBarColor.setLayoutData((Object)new GridData(4, 0x1000000, true, false, 1, 1));
        this.lblBarColor.setText("Bar Color:");
        this.barDefaultColor = new Label(this.composite, 2048);
        GridData gd_barDefaultColor = new GridData(4, 4, true, false, 1, 1);
        gd_barDefaultColor.widthHint = 102;
        this.barDefaultColor.setLayoutData((Object)gd_barDefaultColor);
        this.barDefaultColor.setText(" ");
        this.barDefaultColor.setBackground(SWTResourceManager.getColor(11));
        this.barDefaultColorbtn = new Button(this.composite, 0);
        GridData gd_barDefaultColorbtn = new GridData(4, 4, true, false, 1, 1);
        gd_barDefaultColorbtn.widthHint = 37;
        this.barDefaultColorbtn.setLayoutData((Object)gd_barDefaultColorbtn);
        this.barDefaultColorbtn.setImage(ResourceManager.getPluginImage("Star", "icons/ellipsis.png"));
        this.barDefaultColorbtn.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                HistogramDialog.this.barColor = "rgb(255,0,255, max = 255, alpha = 127)";
                try {
                    RGB rgbColor = GraphsUtilities.chooseColor();
                    HistogramDialog.this.barDefaultColor.setBackground(new Color((Device)Display.getCurrent(), rgbColor));
                    String color = GraphsUtilities.convertToRrgbFormat(rgbColor.toString());
                    HistogramDialog.this.barColor = color;
                }
                catch (Exception exception) {
                    HistogramDialog.this.barColor = "rgb(255,0,255, max = 255, alpha = 127)";
                    System.out.println("Exception at RGB choose color");
                }
            }
        });
        this.barDefaultColorbtn.setImage(ResourceManager.getPluginImage("Star", "icons/ellipsis.png"));
        this.lblNewLabel_2 = new Label(this.composite, 0);
        GridData gd_lblNewLabel_2 = new GridData(4, 0x1000000, true, false, 1, 1);
        gd_lblNewLabel_2.widthHint = 429;
        this.lblNewLabel_2.setLayoutData((Object)gd_lblNewLabel_2);
        new Label(this.composite, 0);
        new Label(this.composite, 0);
        this.lblLineType = new Label(this.composite, 0);
        this.lblLineType.setLayoutData((Object)new GridData(4, 0x1000000, true, false, 1, 1));
        this.lblLineType.setText("Line Type:");
        this.lblLineType.setEnabled(false);
        this.lineDefaultType = new Label(this.composite, 2048);
        this.lineDefaultType.setLayoutData((Object)new GridData(4, 4, true, false, 1, 1));
        this.lineDefaultType.setImage(ResourceManager.getPluginImage("Star", "icons/line1.png"));
        this.lineDefaultType.setEnabled(false);
        this.lineDefaultType.setAlignment(0x1000000);
        this.lineDefaultTypebtn = new Button(this.composite, 0);
        this.lineDefaultTypebtn.setLayoutData((Object)new GridData(4, 4, true, false, 1, 1));
        this.lineDefaultTypebtn.setImage(ResourceManager.getPluginImage("Star", "icons/ellipsis.png"));
        this.lineDefaultTypebtn.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                HistogramDialog.this.lineType = 1;
                ChooseLineTypesDialog chooseLineDlg = new ChooseLineTypesDialog(HistogramDialog.this.getShell());
                chooseLineDlg.open();
                if (chooseLineDlg.getReturnCode() == 0) {
                    HistogramDialog.this.lineType = Integer.parseInt(chooseLineDlg.getChosenPattern());
                    HistogramDialog.this.lineDefaultType.setImage(ResourceManager.getPluginImage("Star", "icons/line" + chooseLineDlg.getChosenPattern() + ".png"));
                }
            }
        });
        this.lineDefaultTypebtn.setImage(ResourceManager.getPluginImage("Star", "icons/ellipsis.png"));
        this.lineDefaultTypebtn.setEnabled(false);
        new Label(this.composite, 0);
        new Label(this.composite, 0);
        new Label(this.composite, 0);
        this.lblLineColor = new Label(this.composite, 0);
        this.lblLineColor.setLayoutData((Object)new GridData(4, 0x1000000, true, false, 1, 1));
        this.lblLineColor.setText("Line Color:");
        this.lblLineColor.setEnabled(false);
        this.lineDefaultColor = new Label(this.composite, 2048);
        this.lineDefaultColor.setLayoutData((Object)new GridData(4, 4, true, false, 1, 1));
        this.lineDefaultColor.setText(" ");
        this.lineDefaultColor.setEnabled(false);
        this.lineDefaultColor.setBackground(SWTResourceManager.getColor(2));
        this.lineDefaultColorbtn = new Button(this.composite, 0);
        this.lineDefaultColorbtn.setLayoutData((Object)new GridData(4, 4, true, false, 1, 1));
        this.lineDefaultColorbtn.setImage(ResourceManager.getPluginImage("Star", "icons/ellipsis.png"));
        this.lineDefaultColorbtn.setEnabled(false);
        this.lineDefaultColorbtn.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                HistogramDialog.this.lineColor = "rgb(0,0,0, max = 255)";
                try {
                    RGB rgbColor = GraphsUtilities.chooseColor();
                    HistogramDialog.this.lineDefaultColor.setBackground(new Color((Device)Display.getCurrent(), rgbColor));
                    String color = GraphsUtilities.convertToRrgbFormat(rgbColor.toString());
                    HistogramDialog.this.lineColor = color;
                }
                catch (Exception exception) {
                    HistogramDialog.this.lineColor = "rgb(0,0,0, max = 255)";
                    System.out.println("Exception at RGB choose color");
                }
            }
        });
        this.lineDefaultColorbtn.setImage(ResourceManager.getPluginImage("Star", "icons/ellipsis.png"));
        this.lineDefaultColorbtn.setEnabled(false);
        new Label(this.composite, 0);
        new Label(this.composite, 0);
        new Label(this.composite, 0);
        this.lblWidth = new Label(this.composite, 0);
        this.lblWidth.setLayoutData((Object)new GridData(4, 0x1000000, true, false, 1, 1));
        this.lblWidth.setText("Width:");
        this.lblWidth.setEnabled(false);
        this.lineWidthSpinner = new Spinner(this.composite, 2048);
        this.lineWidthSpinner.setLayoutData((Object)new GridData(16384, 4, false, false, 1, 1));
        this.lineWidthSpinner.setMaximum(30);
        this.lineWidthSpinner.setMinimum(5);
        this.lineWidthSpinner.setSelection(10);
        this.lineWidthSpinner.setEnabled(false);
        this.lineWidthSpinner.setDigits(1);
        new Label(this.composite, 0);
        new Label(this.composite, 0);
        this.enableOverlayDensityOptions(false);
        return container;
    }

    protected void enableOverlayDensityOptions(boolean state) {
        this.lblLineType.setEnabled(state);
        this.lineDefaultType.setEnabled(state);
        this.lineDefaultTypebtn.setEnabled(state);
        this.lblLineColor.setEnabled(state);
        this.lineDefaultColor.setEnabled(state);
        this.lineDefaultColorbtn.setEnabled(state);
        this.lblWidth.setEnabled(state);
        this.lineWidthSpinner.setEnabled(state);
    }

    protected void displayMultipleGraphs(boolean state) {
        this.orientGraphsCombo.setEnabled(state);
        this.lblOrientation.setEnabled(state);
        this.lblNewLabel.setEnabled(state);
        this.numRowsSpinner.setEnabled(state);
        this.lblNumberOfColumns.setEnabled(state);
        this.numColsSpinner.setEnabled(state);
    }

    protected void removeItemFromXaxisTable(String varName) {
        TableEditor[] editors;
        int item = 0;
        int i = 0;
        while (i < this.tableXaxis.getItemCount()) {
            if (varName.equals(this.tableXaxis.getItem(i).getText(0))) {
                item = i;
            }
            ++i;
        }
        System.out.println(String.valueOf(varName) + "'s index is: " + Integer.toString(item));
        TableItem tableItem = this.tableXaxis.getItem(item);
        TableEditor[] tableEditorArray = editors = (TableEditor[])tableItem.getData("Editors");
        int n = editors.length;
        int n2 = 0;
        while (n2 < n) {
            TableEditor te = tableEditorArray[n2];
            te.getEditor().dispose();
            ++n2;
        }
        this.tableXaxis.remove(item);
        this.tableXaxis.pack();
        this.composite.pack();
    }

    protected void addItemToXaxisTable(String varName, List varList) {
        int ctr = this.tableXaxis.getItemCount();
        if (varList.equals(this.responseVarList)) {
            ctr = varList.getItemCount();
        }
        TableItem tableItem = new TableItem(this.tableXaxis, 0x1000000, ctr);
        tableItem.setText(0, varName);
        System.out.println("set tableItem name: " + varName);
        tableItem.setData("index", (Object)ctr);
        TableEditor[] tableEditors = new TableEditor[]{new TableEditor(this.tableXaxis), new TableEditor(this.tableXaxis), new TableEditor(this.tableXaxis)};
        Text newEditor = new Text((Composite)this.tableXaxis, 0);
        newEditor.setText(varName);
        Text lowerLimitEditor = new Text((Composite)this.tableXaxis, 0);
        lowerLimitEditor.setText("Auto");
        GraphsUtilities.addTextModifyListener(lowerLimitEditor);
        Text upperLimitEditor = new Text((Composite)this.tableXaxis, 0);
        upperLimitEditor.setText("Auto");
        GraphsUtilities.addTextModifyListener(upperLimitEditor);
        tableEditors[0].grabHorizontal = true;
        tableEditors[1].grabHorizontal = true;
        tableEditors[2].grabHorizontal = true;
        tableEditors[0].setEditor((Control)newEditor, tableItem, 1);
        tableEditors[1].setEditor((Control)lowerLimitEditor, tableItem, 2);
        tableEditors[2].setEditor((Control)upperLimitEditor, tableItem, 3);
        tableItem.setData("Editors", (Object)tableEditors);
        this.tableXaxis.pack();
        this.composite.pack();
        this.getShell().setSize(this.getShell().getSize().x - 1, this.getShell().getSize().y);
        this.getShell().setSize(this.getShell().getSize().x + 1, this.getShell().getSize().y);
    }

    protected void removeItemFromYaxisTable(String varName) {
        TableEditor[] editors;
        int item = 0;
        int i = 0;
        while (i < this.tableYaxis.getItemCount()) {
            if (varName.equals(this.tableYaxis.getItem(i).getText(0))) {
                item = i;
            }
            ++i;
        }
        System.out.println(String.valueOf(varName) + "'s index is: " + Integer.toString(item));
        TableItem tableItem = this.tableYaxis.getItem(item);
        TableEditor[] tableEditorArray = editors = (TableEditor[])tableItem.getData("Editors");
        int n = editors.length;
        int n2 = 0;
        while (n2 < n) {
            TableEditor te = tableEditorArray[n2];
            te.getEditor().dispose();
            ++n2;
        }
        this.tableYaxis.remove(item);
        this.tableYaxis.pack();
        this.composite.pack();
    }

    protected void addItemToYaxisTable(String varName, List varList) {
        int ctr = this.tableYaxis.getItemCount();
        if (varList.equals(this.responseVarList)) {
            ctr = varList.getItemCount();
        }
        TableItem tableItem = new TableItem(this.tableYaxis, 0x1000000, ctr);
        tableItem.setText(0, varName);
        System.out.println("set tableItem name: " + varName);
        tableItem.setData("index", (Object)ctr);
        TableEditor[] tableEditors = new TableEditor[]{new TableEditor(this.tableYaxis), new TableEditor(this.tableYaxis), new TableEditor(this.tableYaxis)};
        String useLabel = this.btnFrequency.getSelection() ? "Frequency" : "Probability";
        Text newEditor = new Text((Composite)this.tableYaxis, 0);
        newEditor.setText(useLabel);
        Text lowerLimitEditor = new Text((Composite)this.tableYaxis, 0);
        lowerLimitEditor.setText("Auto");
        GraphsUtilities.addTextModifyListener(lowerLimitEditor);
        Text upperLimitEditor = new Text((Composite)this.tableYaxis, 0);
        upperLimitEditor.setText("Auto");
        GraphsUtilities.addTextModifyListener(upperLimitEditor);
        tableEditors[0].grabHorizontal = true;
        tableEditors[1].grabHorizontal = true;
        tableEditors[2].grabHorizontal = true;
        tableEditors[0].setEditor((Control)newEditor, tableItem, 1);
        tableEditors[1].setEditor((Control)lowerLimitEditor, tableItem, 2);
        tableEditors[2].setEditor((Control)upperLimitEditor, tableItem, 3);
        tableItem.setData("Editors", (Object)tableEditors);
        this.tableYaxis.pack();
        this.composite.pack();
        this.getShell().setSize(this.getShell().getSize().x - 1, this.getShell().getSize().y);
        this.getShell().setSize(this.getShell().getSize().x + 1, this.getShell().getSize().y);
    }

    protected void setFactors() {
        this.varInfo = this.dataManipulationManager.getVarInfoFromFile(this.file.getAbsolutePath());
        this.numericVariables = this.dataManipulationManager.getNumericVars(this.varInfo);
        this.factorVariables = this.dataManipulationManager.getFactorVars(this.varInfo);
    }

    public void enableFactorButtons(boolean state) {
        this.groupsVarList.setSelection(-1);
        this.addGroupsBtn.setEnabled(state);
        if (state) {
            this.addGroupsBtn.setText("Add");
            if (this.groupsVarList.getItemCount() > 0) {
                this.addGroupsBtn.setEnabled(false);
            }
        }
    }

    public void enableNumericButtons(boolean state) {
        this.addBtn.setEnabled(state);
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
            HistogramDialog graph = new HistogramDialog(this.parentShell, this.analysisType, this.file);
            this.close();
            graph.open();
        }
        super.buttonPressed(buttonId);
    }

    protected void okPressed() {
        if (this.responseVarList.getItemCount() < 1) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"Please add variable(s) from the numeric variables list.");
        } else {
            this.nVar = this.responseVarList.getItems();
            this.mTitle = this.mainTitleText.getText();
            this.byVar = this.groupsVarList.getItemCount() < 1 ? null : this.groupsVarList.getItem(0);
            this.useFreq = this.btnFrequency.getSelection() ? "TRUE" : "FALSE";
            this.xAxisLabs = new String[this.nVar.length];
            this.xMinValues = new String[this.nVar.length];
            this.xMaxValues = new String[this.nVar.length];
            this.yAxisLabs = new String[this.nVar.length];
            this.yMinValues = new String[this.nVar.length];
            this.yMaxValues = new String[this.nVar.length];
            int ctr = 0;
            TableItem[] tableItemArray = this.tableXaxis.getItems();
            int n = tableItemArray.length;
            int n2 = 0;
            while (n2 < n) {
                TableItem tableItem = tableItemArray[n2];
                TableEditor[] editors = (TableEditor[])tableItem.getData("Editors");
                if (ctr < this.nVar.length) {
                    this.xAxisLabs[ctr] = ((Text)editors[0].getEditor()).getText();
                    this.xMinValues[ctr] = GraphsUtilities.checkIfAutoOrNumeric(((Text)editors[1].getEditor()).getText());
                    this.xMaxValues[ctr] = GraphsUtilities.checkIfAutoOrNumeric(((Text)editors[2].getEditor()).getText());
                }
                ++ctr;
                ++n2;
            }
            int ctr1 = 0;
            TableItem[] tableItemArray2 = this.tableYaxis.getItems();
            int n3 = tableItemArray2.length;
            n = 0;
            while (n < n3) {
                TableItem tableItem = tableItemArray2[n];
                TableEditor[] editors = (TableEditor[])tableItem.getData("Editors");
                if (ctr1 < this.nVar.length) {
                    this.yAxisLabs[ctr1] = ((Text)editors[0].getEditor()).getText();
                    this.yMinValues[ctr1] = GraphsUtilities.checkIfAutoOrNumeric(((Text)editors[1].getEditor()).getText());
                    this.yMaxValues[ctr1] = GraphsUtilities.checkIfAutoOrNumeric(((Text)editors[2].getEditor()).getText());
                }
                ++ctr1;
                ++n;
            }
            this.numBins = this.txtNumBars.getText();
            this.axisLabelStyle = this.cmboAxisOrientation.getSelectionIndex();
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
            this.lineWidth = this.dataManipulationManager.convertInttoDouble(this.lineWidthSpinner.getSelection(), 1);
            this.dispCurve = this.btnOverlayKernelDensity.getSelection() ? "TRUE" : "FALSE";
            String dataFileName = this.file.getAbsolutePath().replaceAll("\\\\", "/");
            File outputFolder = GraphsUtilities.createOutputFolder(this.file.getName(), this.analysisType);
            if (!outputFolder.exists()) {
                outputFolder.mkdir();
            }
            OperationProgressDialog rInfo = new OperationProgressDialog(this.getShell(), "Creating Histogram");
            rInfo.open();
            ProjectExplorerView.rJavaManager.getRJavaGraphManager().createGraphHist(String.valueOf(outputFolder.getAbsolutePath().replaceAll("\\\\", "/")) + "/", dataFileName, this.nVar, this.mTitle, this.yAxisLabs, this.xAxisLabs, this.yMinValues, this.yMaxValues, this.xMinValues, this.xMaxValues, this.useFreq, this.numBins, this.axisLabelStyle, this.byVar, this.barColor, this.dispCurve, this.lineType, this.lineColor, this.lineWidth, this.boxed, this.multGraphs, this.numRowsGraphs, this.numColsGraphs, this.orientGraphs);
            rInfo.close();
            WindowDialogControlUtil.hideAllWindowDialog();
            GraphsUtilities.openFolder(outputFolder);
        }
    }

    protected Point getInitialSize() {
        return new Point(721, 510);
    }

    protected boolean isResizable() {
        return true;
    }
}

