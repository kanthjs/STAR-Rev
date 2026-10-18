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
 *  org.eclipse.swt.events.MouseAdapter
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
import org.eclipse.swt.events.MouseAdapter;
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
import org.irri.breedingtool.graphs.dialog.ChooseFillPatternDialog;
import org.irri.breedingtool.manager.impl.DataManipulationManager;
import org.irri.breedingtool.projectexplorer.view.ProjectExplorerView;
import org.irri.breedingtool.utility.DialogFormUtility;
import org.irri.breedingtool.utility.GraphsUtilities;
import org.irri.breedingtool.utility.WindowDialogControlUtil;

public class BarGraphDialog
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
    private String[] barsRepresentList = new String[]{"freq", "mean", "median", "sum"};
    private Shell parentShell;
    private int maxLevels = 20;
    private String[] nVar = null;
    private String cVar = null;
    private String mTitle = null;
    private String[] clustVars = null;
    private String[] yAxisLab = null;
    private String xAxisLab = null;
    private String[] minValue = null;
    private String[] maxValue = null;
    private String typeData = "raw";
    private String descStat = "mean";
    private String barsHoriz = "FALSE";
    private String barsClus = "TRUE";
    private String byVar = null;
    private String errBars = "FALSE";
    private String typeErrBar = null;
    private int errMult = 1;
    private double confLev = 0.95;
    private int axisLabelStyle = 1;
    private String[] barColor = new String[]{"rgb(192,192,192, max = 255)"};
    private String showLeg = "FALSE";
    private String legPos = null;
    private String legTitle = null;
    private String legHoriz = "FALSE";
    private int legCol = 1;
    private String boxed = "FALSE";
    private String multGraphs = "FALSE";
    private int numRowsGraphs = 1;
    private int numColsGraphs = 1;
    private String orientGraphs = "top-bottom";
    private String showCatVarLevels = "FALSE";
    private ArrayList<Integer> barDensityList = new ArrayList();
    private int[] barDensity = new int[]{100};
    private ArrayList<Integer> barLineAngleList = new ArrayList();
    private int[] barLineAngle;
    private String analysisType;
    private Table table;
    private TableColumn tableColumn;
    private Button addAxisBtn;
    private Button addGroupsBtn;
    private List axisVarList;
    private List groupsVarList;
    private Label barsRepresentLabel;
    private Button horizontalOrientationBtn;
    private Button verticalOrientationBtn;
    private Button clusteredStyleBtn;
    private Button stackedStyleBtn;
    private Button confidenceLvlBtn;
    private Button stdErrorBtn;
    private Button stdDevBtn;
    private Button displayErrorBars;
    private Label stdErrorLabel;
    private Label confidenceLvlLabel;
    private Label stdDevLabel;
    private Label styleLabel;
    private CCombo barsRepresentCombo;
    private Text mainTitleText;
    private Text categoryAxisText;
    private Text legTitleText;
    private Button summaryStatBtn;
    private TabItem tbtmModelSpecifications;
    private Label errorBarsLabel;
    private Spinner confidenceLvlValue;
    private Spinner stdErrorValue;
    private Spinner stdDevValue;
    private Button displayVarNamesBtn;
    private CCombo legPositionComo;
    private CCombo orientGraphsCombo;
    private Button rawDataBtn;
    private Button btnShowLegend;
    private Button btnDrawBoxAround;
    private Button btnDisplayMultipleGraphs;
    private Spinner numColsSpinner;
    private Spinner numRowsSpinner;
    private Composite compositeConfigureBars;
    private Label lblPosition;
    private Label lblTitle;
    private Label lblNewLabel;
    private Label lblNumberOfColumns;
    private Label lblOrientation;
    private Composite composite;
    private Table tableAxis;
    private TableColumn tableColumn_1;
    private TableColumn tableColumn_2;
    private TableColumn tableColumn_3;
    private TableColumn tableColumn_4;
    private Label label;
    private CCombo cmboAxisOrientation;
    private Label lblAlignment;
    private Label lblNewLabel_1;
    private List lstClusterBy;
    private Label lblClusterBarsBy;
    private Button addClusterBybtn;
    private Spinner numColsSpinner1;

    public BarGraphDialog(Shell parentShell, String analysisType, File file) {
        super(parentShell);
        int[] nArray = new int[2];
        nArray[1] = 45;
        this.barLineAngle = nArray;
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
                WindowDialogControlUtil.removeWindowDialogToList(BarGraphDialog.this.getShell());
            }
        });
        parent.getShell().setText("Bar Graph: " + this.dataManipulationManager.getDisplayFileName(this.file.getAbsolutePath()));
        Composite container = (Composite)super.createDialogArea(parent);
        TabFolder tabFolder = new TabFolder(container, 0);
        tabFolder.setLayoutData((Object)new GridData(4, 4, true, true, 1, 1));
        this.tbtmModelSpecifications = new TabItem(tabFolder, 0);
        this.tbtmModelSpecifications.setText("Variable Definition");
        Composite modelComposite = new Composite((Composite)tabFolder, 0);
        this.tbtmModelSpecifications.setControl((Control)modelComposite);
        modelComposite.setLayout((Layout)new GridLayout(5, false));
        Label label_8 = new Label(modelComposite, 0);
        label_8.setText("Input File Contains:");
        this.rawDataBtn = new Button(modelComposite, 16);
        this.rawDataBtn.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                BarGraphDialog.this.enableBarsRepresent(true);
            }
        });
        this.rawDataBtn.setText("Raw Data");
        this.rawDataBtn.setSelection(true);
        this.summaryStatBtn = new Button(modelComposite, 16);
        this.summaryStatBtn.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                BarGraphDialog.this.enableBarsRepresent(false);
            }
        });
        this.summaryStatBtn.setLayoutData((Object)new GridData(16384, 0x1000000, false, false, 3, 1));
        this.summaryStatBtn.setText("Summary Statistics");
        this.barsRepresentLabel = new Label(modelComposite, 0);
        this.barsRepresentLabel.setText("Bars Represent:");
        this.barsRepresentCombo = new CCombo(modelComposite, 2048);
        this.barsRepresentCombo.setItems(new String[]{"Frequency", "Mean", "Median", "Sum"});
        this.barsRepresentCombo.select(1);
        this.barsRepresentCombo.setBackground(SWTResourceManager.getColor(1));
        this.barsRepresentCombo.setEditable(false);
        this.barsRepresentCombo.setLayoutData((Object)new GridData(4, 0x1000000, false, false, 1, 1));
        this.barsRepresentCombo.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (BarGraphDialog.this.barsRepresentCombo.getSelectionIndex() == 1) {
                    if (BarGraphDialog.this.clusteredStyleBtn.getSelection()) {
                        BarGraphDialog.this.enableBarsRepresent(true);
                    }
                } else {
                    BarGraphDialog.this.enableDisplayErrorBars(false);
                }
            }
        });
        new Label(modelComposite, 0);
        new Label(modelComposite, 0);
        new Label(modelComposite, 0);
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
                BarGraphDialog.this.addItemToTableAxis(BarGraphDialog.this.numVarList.getSelection()[0]);
                BarGraphDialog.this.dataManipulationManager.moveSelectedStringFromTo(BarGraphDialog.this.numVarList, BarGraphDialog.this.responseVarList, BarGraphDialog.this.moveBtn);
                BarGraphDialog.this.getShell().setSize(BarGraphDialog.this.getShell().getSize().x - 1, BarGraphDialog.this.getShell().getSize().y);
                BarGraphDialog.this.numVarList.remove(BarGraphDialog.this.numVarList.getSelectionIndices());
                BarGraphDialog.this.addBtn.setEnabled(false);
            }

            public void mouseDown(MouseEvent e) {
                if (BarGraphDialog.this.numVarList.getSelectionCount() > 0) {
                    BarGraphDialog.this.moveBtn.setEnabled(true);
                    BarGraphDialog.this.factorVarList.setSelection(-1);
                    BarGraphDialog.this.responseVarList.setSelection(-1);
                    BarGraphDialog.this.addBtn.setText("Add");
                    BarGraphDialog.this.moveBtn.setText("Set to Factor");
                    BarGraphDialog.this.enableFactorButtons(false);
                    BarGraphDialog.this.enableNumericButtons(true);
                }
            }

            public void mouseUp(MouseEvent e) {
            }
        });
        this.addBtn = new Button(modelComposite, 0);
        this.addBtn.setForeground(SWTResourceManager.getColor(13));
        this.addBtn.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (BarGraphDialog.this.numVarList.getSelectionCount() > 0) {
                    String[] selectedNumVars = BarGraphDialog.this.numVarList.getSelection();
                    int i = 0;
                    while (i < selectedNumVars.length) {
                        BarGraphDialog.this.responseVarList.add(selectedNumVars[i]);
                        BarGraphDialog.this.addItemToTableAxis(selectedNumVars[i]);
                        ++i;
                    }
                    BarGraphDialog.this.numVarList.remove(BarGraphDialog.this.numVarList.getSelectionIndices());
                } else {
                    String[] selectedNumVars = BarGraphDialog.this.responseVarList.getSelection();
                    int i = 0;
                    while (i < selectedNumVars.length) {
                        BarGraphDialog.this.numVarList.add(selectedNumVars[i]);
                        BarGraphDialog.this.removeItemFromTableAxis(selectedNumVars[i]);
                        ++i;
                    }
                    BarGraphDialog.this.responseVarList.remove(BarGraphDialog.this.responseVarList.getSelectionIndices());
                }
                BarGraphDialog.this.enableNumericButtons(false);
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
                BarGraphDialog.this.removeItemFromTableAxis(BarGraphDialog.this.responseVarList.getSelection()[0]);
                BarGraphDialog.this.dataManipulationManager.moveSelectedStringFromTo(BarGraphDialog.this.responseVarList, BarGraphDialog.this.numVarList);
                BarGraphDialog.this.getShell().setSize(BarGraphDialog.this.getShell().getSize().x + 1, BarGraphDialog.this.getShell().getSize().y);
                BarGraphDialog.this.responseVarList.remove(BarGraphDialog.this.responseVarList.getSelectionIndices());
                BarGraphDialog.this.addBtn.setEnabled(false);
            }

            public void mouseDown(MouseEvent e) {
                if (BarGraphDialog.this.responseVarList.getSelectionCount() > 0) {
                    BarGraphDialog.this.numVarList.setSelection(-1);
                    BarGraphDialog.this.addBtn.setText("Remove");
                    BarGraphDialog.this.addBtn.setEnabled(true);
                    BarGraphDialog.this.moveBtn.setEnabled(false);
                    BarGraphDialog.this.enableFactorButtons(false);
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
                if (BarGraphDialog.this.numVarList.getSelectionCount() > 0) {
                    String[] selectedNumVars = BarGraphDialog.this.numVarList.getSelection();
                    int i = 0;
                    while (i < selectedNumVars.length) {
                        BarGraphDialog.this.factorVarList.add(selectedNumVars[i]);
                        BarGraphDialog.this.dataManipulationManager.editVariableType(BarGraphDialog.this.file.getAbsolutePath(), BarGraphDialog.this.varInfo, selectedNumVars[i], "Factor");
                        ++i;
                    }
                    BarGraphDialog.this.numVarList.remove(BarGraphDialog.this.numVarList.getSelectionIndices());
                    BarGraphDialog.this.enableNumericButtons(false);
                } else {
                    String[] selectedNumVars = BarGraphDialog.this.factorVarList.getSelection();
                    int i = 0;
                    while (i < selectedNumVars.length) {
                        BarGraphDialog.this.numVarList.add(selectedNumVars[i]);
                        BarGraphDialog.this.dataManipulationManager.editVariableType(BarGraphDialog.this.file.getAbsolutePath(), BarGraphDialog.this.varInfo, selectedNumVars[i], "Numeric");
                        ++i;
                    }
                    BarGraphDialog.this.factorVarList.remove(BarGraphDialog.this.factorVarList.getSelectionIndices());
                    BarGraphDialog.this.enableFactorButtons(false);
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
        GridData gd_factorVarList = new GridData(4, 4, true, true, 2, 5);
        gd_factorVarList.widthHint = 65;
        this.factorVarList.setLayoutData((Object)gd_factorVarList);
        this.factorVarList.setItems(this.factorVariables);
        this.factorVarList.addListener(3, new Listener(){

            public void handleEvent(Event event) {
                if (BarGraphDialog.this.factorVarList.getSelectionIndex() > -1) {
                    BarGraphDialog.this.enableNumericButtons(false);
                    BarGraphDialog.this.enableFactorButtons(false);
                    BarGraphDialog.this.numVarList.setSelection(-1);
                    String[] s = BarGraphDialog.this.factorVarList.getSelection();
                    String isNumeric = BarGraphDialog.this.dataManipulationManager.isNumeric(BarGraphDialog.this.file.getAbsolutePath().replaceAll("\\\\", "/"), s[0]);
                    if (isNumeric.equals("TRUE")) {
                        BarGraphDialog.this.moveBtn.setText("Set to Numeric");
                        BarGraphDialog.this.moveBtn.setEnabled(true);
                    } else {
                        BarGraphDialog.this.moveBtn.setEnabled(false);
                    }
                    BarGraphDialog.this.enableFactorButtons(true);
                }
            }
        });
        this.addAxisBtn = new Button(modelComposite, 0);
        this.addAxisBtn.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (BarGraphDialog.this.factorVarList.getSelectionCount() > 0) {
                    BarGraphDialog.this.dataManipulationManager.moveSelectedStringFromTo(BarGraphDialog.this.factorVarList, BarGraphDialog.this.axisVarList);
                    BarGraphDialog.this.categoryAxisText.setText(BarGraphDialog.this.axisVarList.getItems()[0]);
                    String[] catVar = DataManipulationManager.getEnvtLevels(BarGraphDialog.this.axisVarList.getItem(0), BarGraphDialog.this.file);
                    System.out.println(catVar);
                    String[] stringArray = catVar;
                    int n = catVar.length;
                    int n2 = 0;
                    while (n2 < n) {
                        String var = stringArray[n2];
                        BarGraphDialog.this.addItemToVarTable(var);
                        ++n2;
                    }
                    BarGraphDialog.this.getShell().setSize(BarGraphDialog.this.getShell().getSize().x - 1, BarGraphDialog.this.getShell().getSize().y);
                    BarGraphDialog.this.numColsSpinner1.setEnabled(true);
                } else {
                    String[] catVar = DataManipulationManager.getEnvtLevels(BarGraphDialog.this.axisVarList.getItem(0), BarGraphDialog.this.file);
                    System.out.println(catVar);
                    String[] stringArray = catVar;
                    int n = catVar.length;
                    int n3 = 0;
                    while (n3 < n) {
                        String var = stringArray[n3];
                        BarGraphDialog.this.removeItemFromVarTable(var);
                        ++n3;
                    }
                    BarGraphDialog.this.dataManipulationManager.moveSelectedStringFromTo(BarGraphDialog.this.axisVarList, BarGraphDialog.this.factorVarList);
                    BarGraphDialog.this.categoryAxisText.setText("");
                    BarGraphDialog.this.getShell().setSize(BarGraphDialog.this.getShell().getSize().x + 1, BarGraphDialog.this.getShell().getSize().y);
                    BarGraphDialog.this.numColsSpinner1.setEnabled(false);
                }
                BarGraphDialog.this.enableFactorButtons(false);
                BarGraphDialog.this.moveBtn.setEnabled(false);
            }
        });
        GridData gd_addAxisBtn = new GridData(16384, 128, false, false, 2, 1);
        gd_addAxisBtn.widthHint = 52;
        this.addAxisBtn.setLayoutData((Object)gd_addAxisBtn);
        this.addAxisBtn.setText("Add");
        this.addAxisBtn.setEnabled(false);
        this.axisVarList = new List(modelComposite, 2818);
        GridData gd_axisVarList = new GridData(4, 128, true, false, 1, 1);
        gd_axisVarList.heightHint = 5;
        this.axisVarList.setLayoutData((Object)gd_axisVarList);
        this.axisVarList.addMouseListener(new MouseListener(){

            public void mouseDoubleClick(MouseEvent e) {
                BarGraphDialog.this.dataManipulationManager.moveSelectedStringFromTo(BarGraphDialog.this.axisVarList, BarGraphDialog.this.factorVarList);
                BarGraphDialog.this.addAxisBtn.setEnabled(false);
                BarGraphDialog.this.numColsSpinner1.setEnabled(false);
            }

            public void mouseDown(MouseEvent e) {
                if (BarGraphDialog.this.axisVarList.getSelectionCount() > 0) {
                    BarGraphDialog.this.factorVarList.setSelection(-1);
                    BarGraphDialog.this.addAxisBtn.setText("Remove");
                    BarGraphDialog.this.addAxisBtn.setEnabled(true);
                }
            }

            public void mouseUp(MouseEvent e) {
            }
        });
        new Label(modelComposite, 0);
        new Label(modelComposite, 0);
        this.lblClusterBarsBy = new Label(modelComposite, 0);
        this.lblClusterBarsBy.setText("Cluster bars by:");
        this.addClusterBybtn = new Button(modelComposite, 0);
        this.addClusterBybtn.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (BarGraphDialog.this.factorVarList.getSelectionCount() > 0) {
                    BarGraphDialog.this.dataManipulationManager.moveSelectedStringFromTo(BarGraphDialog.this.factorVarList, BarGraphDialog.this.lstClusterBy);
                } else {
                    BarGraphDialog.this.dataManipulationManager.moveSelectedStringFromTo(BarGraphDialog.this.lstClusterBy, BarGraphDialog.this.factorVarList);
                }
                BarGraphDialog.this.enableFactorButtons(false);
                BarGraphDialog.this.moveBtn.setEnabled(false);
            }
        });
        this.addClusterBybtn.setLayoutData((Object)new GridData(4, 0x1000000, false, false, 2, 1));
        this.addClusterBybtn.setText("Add");
        this.addClusterBybtn.setEnabled(false);
        this.lstClusterBy = new List(modelComposite, 2818);
        this.lstClusterBy.addMouseListener((MouseListener)new MouseAdapter(){

            public void mouseDoubleClick(MouseEvent e) {
                BarGraphDialog.this.dataManipulationManager.moveSelectedStringFromTo(BarGraphDialog.this.lstClusterBy, BarGraphDialog.this.factorVarList);
                BarGraphDialog.this.addClusterBybtn.setEnabled(false);
            }

            public void mouseDown(MouseEvent e) {
                if (BarGraphDialog.this.lstClusterBy.getSelectionCount() > 0) {
                    BarGraphDialog.this.factorVarList.setSelection(-1);
                    BarGraphDialog.this.addClusterBybtn.setText("Remove");
                    BarGraphDialog.this.addClusterBybtn.setEnabled(true);
                }
            }

            public void mouseUp(MouseEvent e) {
            }
        });
        this.lstClusterBy.setLayoutData((Object)new GridData(4, 0x1000000, false, false, 1, 1));
        new Label(modelComposite, 0);
        new Label(modelComposite, 0);
        Label lblGroupBy = new Label(modelComposite, 0);
        lblGroupBy.setText("Group by:");
        this.addGroupsBtn = new Button(modelComposite, 0);
        this.addGroupsBtn.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (BarGraphDialog.this.factorVarList.getSelectionCount() > 0) {
                    BarGraphDialog.this.dataManipulationManager.moveSelectedStringFromTo(BarGraphDialog.this.factorVarList, BarGraphDialog.this.groupsVarList);
                } else {
                    BarGraphDialog.this.dataManipulationManager.moveSelectedStringFromTo(BarGraphDialog.this.groupsVarList, BarGraphDialog.this.factorVarList);
                }
                BarGraphDialog.this.enableFactorButtons(false);
                BarGraphDialog.this.moveBtn.setEnabled(false);
            }
        });
        GridData gd_addGroupsBtn = new GridData(16384, 128, false, false, 2, 1);
        gd_addGroupsBtn.widthHint = 52;
        this.addGroupsBtn.setLayoutData((Object)gd_addGroupsBtn);
        this.addGroupsBtn.setText("Add");
        this.addGroupsBtn.setEnabled(false);
        this.groupsVarList = new List(modelComposite, 2818);
        GridData gd_groupsVarList = new GridData(4, 128, true, false, 1, 1);
        gd_groupsVarList.heightHint = 5;
        this.groupsVarList.setLayoutData((Object)gd_groupsVarList);
        this.groupsVarList.addMouseListener(new MouseListener(){

            public void mouseDoubleClick(MouseEvent e) {
                BarGraphDialog.this.dataManipulationManager.moveSelectedStringFromTo(BarGraphDialog.this.groupsVarList, BarGraphDialog.this.factorVarList);
                BarGraphDialog.this.addGroupsBtn.setEnabled(false);
            }

            public void mouseDown(MouseEvent e) {
                if (BarGraphDialog.this.groupsVarList.getSelectionCount() > 0) {
                    BarGraphDialog.this.factorVarList.setSelection(-1);
                    BarGraphDialog.this.addGroupsBtn.setText("Remove");
                    BarGraphDialog.this.addGroupsBtn.setEnabled(true);
                }
            }

            public void mouseUp(MouseEvent e) {
            }
        });
        TabItem tbtmDisplay = new TabItem(tabFolder, 0);
        tbtmDisplay.setText("Display Options");
        Composite composite_2 = new Composite((Composite)tabFolder, 0);
        tbtmDisplay.setControl((Control)composite_2);
        composite_2.setLayout((Layout)new GridLayout(6, false));
        Label lblMainTitle = new Label(composite_2, 0);
        lblMainTitle.setLayoutData((Object)new GridData(16384, 0x1000000, false, false, 2, 1));
        lblMainTitle.setText("Main Title:");
        this.mainTitleText = new Text(composite_2, 2048);
        int maxLen = 45;
        this.mainTitleText.setTextLimit(maxLen);
        this.mainTitleText.setLayoutData((Object)new GridData(4, 0x1000000, false, false, 4, 1));
        Label lblCategoryAxisLabel = new Label(composite_2, 0);
        lblCategoryAxisLabel.setLayoutData((Object)new GridData(16384, 0x1000000, false, false, 2, 1));
        lblCategoryAxisLabel.setText("Category Axis Label:");
        this.categoryAxisText = new Text(composite_2, 2048);
        this.categoryAxisText.setLayoutData((Object)new GridData(4, 0x1000000, false, false, 4, 1));
        new Label(composite_2, 0);
        new Label(composite_2, 0);
        this.displayVarNamesBtn = new Button(composite_2, 32);
        this.displayVarNamesBtn.setLayoutData((Object)new GridData(16384, 0x1000000, false, false, 2, 1));
        this.displayVarNamesBtn.setText("Display levels");
        new Label(composite_2, 0);
        new Label(composite_2, 0);
        Label lblValueAxisLabel = new Label(composite_2, 0);
        lblValueAxisLabel.setLayoutData((Object)new GridData(16384, 0x1000000, false, false, 4, 1));
        lblValueAxisLabel.setText("Value Axis:");
        new Label(composite_2, 0);
        new Label(composite_2, 0);
        this.tableAxis = new Table(composite_2, 67586);
        this.tableAxis.setLinesVisible(true);
        this.tableAxis.setHeaderVisible(true);
        this.tableAxis.setBackground(SWTResourceManager.getColor(1));
        GridData gd_tableAxis = new GridData(4, 4, true, true, 6, 1);
        gd_tableAxis.heightHint = 119;
        this.tableAxis.setLayoutData((Object)gd_tableAxis);
        this.tableColumn_1 = new TableColumn(this.tableAxis, 0);
        this.tableColumn_1.setWidth(85);
        this.tableColumn_1.setText("Variable");
        this.tableColumn_2 = new TableColumn(this.tableAxis, 0);
        this.tableColumn_2.setWidth(83);
        this.tableColumn_2.setText("Axis Label");
        this.tableColumn_3 = new TableColumn(this.tableAxis, 0);
        this.tableColumn_3.setWidth(87);
        this.tableColumn_3.setText("Lower Limit");
        this.tableColumn_4 = new TableColumn(this.tableAxis, 0);
        this.tableColumn_4.setWidth(85);
        this.tableColumn_4.setText("Upper Limit");
        this.label = new Label(composite_2, 0);
        this.label.setLayoutData((Object)new GridData(16384, 0x1000000, false, false, 2, 1));
        this.label.setText("Axis Label Orientation:");
        this.cmboAxisOrientation = new CCombo(composite_2, 2056);
        this.cmboAxisOrientation.setItems(new String[]{"parallel to axis", "horizontal", "vertical", "perpendicular to axis"});
        this.cmboAxisOrientation.setBackground(SWTResourceManager.getColor(1));
        this.cmboAxisOrientation.select(0);
        new Label(composite_2, 0);
        new Label(composite_2, 0);
        new Label(composite_2, 0);
        this.btnDrawBoxAround = new Button(composite_2, 32);
        this.btnDrawBoxAround.setLayoutData((Object)new GridData(16384, 0x1000000, false, false, 4, 1));
        this.btnDrawBoxAround.setText("Draw box around plot");
        new Label(composite_2, 0);
        new Label(composite_2, 0);
        this.btnShowLegend = new Button(composite_2, 32);
        this.btnShowLegend.setSelection(true);
        this.btnShowLegend.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (BarGraphDialog.this.btnShowLegend.getSelection()) {
                    BarGraphDialog.this.enableShowLegend(true);
                } else {
                    BarGraphDialog.this.enableShowLegend(false);
                }
            }
        });
        this.btnShowLegend.setLayoutData((Object)new GridData(16384, 0x1000000, false, false, 4, 1));
        this.btnShowLegend.setText("Show legend");
        new Label(composite_2, 0);
        new Label(composite_2, 0);
        new Label(composite_2, 0);
        this.lblPosition = new Label(composite_2, 0);
        this.lblPosition.setText("Position:");
        this.legPositionComo = new CCombo(composite_2, 2056);
        this.legPositionComo.setBackground(SWTResourceManager.getColor(1));
        this.legPositionComo.setEditable(false);
        this.legPositionComo.setItems(new String[]{"bottom", "bottom-left", "bottom-right", "center", "left", "right", "top", "top-left", "top-right"});
        this.legPositionComo.setLayoutData((Object)new GridData(4, 0x1000000, false, false, 1, 1));
        this.legPositionComo.select(2);
        this.lblNewLabel_1 = new Label(composite_2, 0);
        this.lblAlignment = new Label(composite_2, 0);
        this.lblAlignment.setText("Number of columns:");
        this.numColsSpinner1 = new Spinner(composite_2, 2056);
        this.numColsSpinner1.setEnabled(false);
        GridData gd_numColsSpinner1 = new GridData(16384, 0x1000000, false, false, 1, 1);
        gd_numColsSpinner1.widthHint = 10;
        this.numColsSpinner1.setLayoutData((Object)gd_numColsSpinner1);
        this.numColsSpinner1.setBackground(SWTResourceManager.getColor(1));
        this.numColsSpinner1.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                String selectedStrings = BarGraphDialog.this.axisVarList.getItem(0);
                String[] catLevels = DataManipulationManager.getEnvtLevels(selectedStrings, BarGraphDialog.this.file);
                BarGraphDialog.this.maxLevels = catLevels.length;
                BarGraphDialog.this.numColsSpinner1.setMaximum(BarGraphDialog.this.maxLevels);
                BarGraphDialog.this.numColsSpinner1.setMinimum(1);
            }
        });
        this.numColsSpinner1.setSelection(1);
        new Label(composite_2, 0);
        this.lblTitle = new Label(composite_2, 0);
        this.lblTitle.setText("Title:");
        this.legTitleText = new Text(composite_2, 2048);
        this.legTitleText.setLayoutData((Object)new GridData(4, 0x1000000, false, false, 4, 1));
        this.btnDisplayMultipleGraphs = new Button(composite_2, 32);
        this.btnDisplayMultipleGraphs.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (BarGraphDialog.this.btnDisplayMultipleGraphs.getSelection()) {
                    BarGraphDialog.this.displayMultipleGraphs(true);
                } else {
                    BarGraphDialog.this.displayMultipleGraphs(false);
                }
            }
        });
        this.btnDisplayMultipleGraphs.setLayoutData((Object)new GridData(16384, 0x1000000, false, false, 4, 1));
        this.btnDisplayMultipleGraphs.setText("Display multiple graphs in a page");
        new Label(composite_2, 0);
        new Label(composite_2, 0);
        new Label(composite_2, 0);
        this.lblNewLabel = new Label(composite_2, 0);
        this.lblNewLabel.setEnabled(false);
        this.lblNewLabel.setText("Number of rows:");
        this.numRowsSpinner = new Spinner(composite_2, 2056);
        this.numRowsSpinner.setBackground(SWTResourceManager.getColor(1));
        GridData gd_numRowsSpinner = new GridData(16384, 0x1000000, false, false, 1, 1);
        gd_numRowsSpinner.widthHint = 10;
        this.numRowsSpinner.setLayoutData((Object)gd_numRowsSpinner);
        this.numRowsSpinner.setEnabled(false);
        this.numRowsSpinner.setMaximum(5);
        this.numRowsSpinner.setMinimum(1);
        this.numRowsSpinner.setSelection(2);
        new Label(composite_2, 0);
        this.lblNumberOfColumns = new Label(composite_2, 0);
        this.lblNumberOfColumns.setEnabled(false);
        this.lblNumberOfColumns.setText("Number of columns:");
        this.numColsSpinner = new Spinner(composite_2, 2056);
        this.numColsSpinner.setBackground(SWTResourceManager.getColor(1));
        GridData gd_numColsSpinner = new GridData(16384, 0x1000000, false, false, 1, 1);
        gd_numColsSpinner.widthHint = 10;
        this.numColsSpinner.setLayoutData((Object)gd_numColsSpinner);
        this.numColsSpinner.setEnabled(false);
        this.numColsSpinner.setMaximum(5);
        this.numColsSpinner.setMinimum(1);
        this.numColsSpinner.setSelection(1);
        new Label(composite_2, 0);
        this.lblOrientation = new Label(composite_2, 0);
        this.lblOrientation.setLayoutData((Object)new GridData(16384, 0x1000000, false, true, 1, 1));
        this.lblOrientation.setEnabled(false);
        this.lblOrientation.setText("Orientation:");
        this.orientGraphsCombo = new CCombo(composite_2, 2056);
        this.orientGraphsCombo.setEnabled(false);
        this.orientGraphsCombo.setBackground(SWTResourceManager.getColor(1));
        this.orientGraphsCombo.setEditable(false);
        this.orientGraphsCombo.setItems(new String[]{"Left-to-right", "Top-to-bottom"});
        this.orientGraphsCombo.setLayoutData((Object)new GridData(4, 0x1000000, false, true, 1, 1));
        this.orientGraphsCombo.select(1);
        new Label(composite_2, 0);
        new Label(composite_2, 0);
        new Label(composite_2, 0);
        TabItem tbtmBars = new TabItem(tabFolder, 0);
        tbtmBars.setText("Other Options");
        this.composite = new Composite((Composite)tabFolder, 0);
        tbtmBars.setControl((Control)this.composite);
        this.composite.setLayout((Layout)new GridLayout(2, false));
        Label label_6 = new Label(this.composite, 0);
        label_6.setLayoutData((Object)new GridData(16384, 0x1000000, true, false, 2, 1));
        label_6.setText("CONFIGURE BARS:");
        this.compositeConfigureBars = new Composite(this.composite, 2048);
        this.compositeConfigureBars.setLayoutData((Object)new GridData(4, 4, true, true, 1, 3));
        this.compositeConfigureBars.setLayout((Layout)new GridLayout(1, false));
        Composite composite_3 = new Composite(this.compositeConfigureBars, 0);
        composite_3.setLayoutData((Object)new GridData(4, 0x1000000, false, false, 1, 1));
        composite_3.setLayout((Layout)new GridLayout(2, false));
        Label label_3 = new Label(composite_3, 0);
        label_3.setText("Orientation:");
        new Label(composite_3, 0);
        this.verticalOrientationBtn = new Button(composite_3, 16);
        this.verticalOrientationBtn.setText("Vertical");
        this.verticalOrientationBtn.setSelection(true);
        this.horizontalOrientationBtn = new Button(composite_3, 16);
        this.horizontalOrientationBtn.setText("Horizontal");
        Composite composite_4 = new Composite(this.compositeConfigureBars, 0);
        composite_4.setLayoutData((Object)new GridData(4, 0x1000000, false, false, 1, 1));
        composite_4.setLayout((Layout)new GridLayout(2, false));
        this.styleLabel = new Label(composite_4, 0);
        this.styleLabel.setText("Style:");
        new Label(composite_4, 0);
        this.clusteredStyleBtn = new Button(composite_4, 16);
        this.clusteredStyleBtn.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (BarGraphDialog.this.barsRepresentCombo.getEnabled() && BarGraphDialog.this.barsRepresentCombo.getSelectionIndex() == 1) {
                    BarGraphDialog.this.enableDisplayErrorBars(true);
                }
            }
        });
        this.clusteredStyleBtn.setText("Clustered");
        this.clusteredStyleBtn.setSelection(true);
        this.stackedStyleBtn = new Button(composite_4, 16);
        this.stackedStyleBtn.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                BarGraphDialog.this.displayErrorBars.setSelection(false);
                BarGraphDialog.this.enableDisplayErrorBars(false);
            }
        });
        this.stackedStyleBtn.setText("Stacked");
        Composite composite_5 = new Composite(this.compositeConfigureBars, 0);
        composite_5.setLayout((Layout)new GridLayout(1, false));
        Label lblColorAndFill = new Label(composite_5, 0);
        lblColorAndFill.setText("Color and Fill:");
        this.table = new Table(this.compositeConfigureBars, 67586);
        this.table.setBackground(SWTResourceManager.getColor(1));
        GridData gd_table = new GridData(4, 4, true, true, 1, 1);
        gd_table.widthHint = 204;
        this.table.setLayoutData((Object)gd_table);
        this.table.setHeaderVisible(true);
        this.table.setLinesVisible(true);
        TableColumn tblclmnNewColumn = new TableColumn(this.table, 0);
        tblclmnNewColumn.setWidth(75);
        tblclmnNewColumn.setText("Level");
        TableColumn tblclmnNewColumnf = new TableColumn(this.table, 0);
        tblclmnNewColumnf.setWidth(60);
        tblclmnNewColumnf.setText("Color");
        TableColumn tblclmnNewColumn_1 = new TableColumn(this.table, 0);
        tblclmnNewColumn_1.setWidth(25);
        TableColumn tblclmnNewColumn3 = new TableColumn(this.table, 0);
        tblclmnNewColumn3.setWidth(91);
        tblclmnNewColumn3.setText("Fill Pattern");
        tblclmnNewColumn3.pack();
        TableColumn tblclmnNewColumn4 = new TableColumn(this.table, 0);
        tblclmnNewColumn4.setWidth(25);
        this.displayErrorBars = new Button(this.composite, 32);
        this.displayErrorBars.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (BarGraphDialog.this.displayErrorBars.getSelection()) {
                    BarGraphDialog.this.enableErrorBarsOptions(true);
                } else {
                    BarGraphDialog.this.enableErrorBarsOptions(false);
                    BarGraphDialog.this.enableStyleOptions(true);
                }
            }
        });
        this.displayErrorBars.setText("Display Error Bars");
        this.errorBarsLabel = new Label(this.composite, 0);
        this.errorBarsLabel.setEnabled(false);
        this.errorBarsLabel.setText("ERROR BARS:");
        Composite composite_1 = new Composite(this.composite, 0);
        composite_1.setLayoutData((Object)new GridData(16384, 4, false, false, 1, 1));
        composite_1.setLayout((Layout)new GridLayout(2, false));
        this.confidenceLvlBtn = new Button(composite_1, 16);
        this.confidenceLvlBtn.setEnabled(false);
        this.confidenceLvlBtn.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                BarGraphDialog.this.EnableConfidenceLevel(true);
                BarGraphDialog.this.EnableStandardError(false);
                BarGraphDialog.this.EnableStandardDeviation(false);
            }
        });
        this.confidenceLvlBtn.setLayoutData((Object)new GridData(16384, 0x1000000, false, false, 2, 1));
        this.confidenceLvlBtn.setText("Confidence Level");
        this.confidenceLvlBtn.setSelection(true);
        this.confidenceLvlBtn.setGrayed(true);
        this.confidenceLvlLabel = new Label(composite_1, 0);
        this.confidenceLvlLabel.setEnabled(false);
        this.confidenceLvlLabel.setText("Level (%)");
        this.confidenceLvlValue = new Spinner(composite_1, 2048);
        this.confidenceLvlValue.setEnabled(false);
        GridData gd_confidenceLvlValue = new GridData(16384, 0x1000000, false, false, 1, 1);
        gd_confidenceLvlValue.widthHint = 10;
        this.confidenceLvlValue.setLayoutData((Object)gd_confidenceLvlValue);
        this.confidenceLvlValue.setPageIncrement(1);
        this.confidenceLvlValue.setMaximum(99);
        this.confidenceLvlValue.setMinimum(1);
        this.confidenceLvlValue.setSelection(95);
        this.stdErrorBtn = new Button(composite_1, 16);
        this.stdErrorBtn.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                BarGraphDialog.this.EnableConfidenceLevel(false);
                BarGraphDialog.this.EnableStandardError(true);
                BarGraphDialog.this.EnableStandardDeviation(false);
            }
        });
        this.stdErrorBtn.setEnabled(false);
        this.stdErrorBtn.setLayoutData((Object)new GridData(16384, 0x1000000, false, false, 2, 1));
        this.stdErrorBtn.setText("Standard Error");
        this.stdErrorLabel = new Label(composite_1, 0);
        this.stdErrorLabel.setEnabled(false);
        this.stdErrorLabel.setLayoutData((Object)new GridData(16384, 4, false, false, 1, 1));
        this.stdErrorLabel.setText("Multiplier");
        this.stdErrorValue = new Spinner(composite_1, 2048);
        this.stdErrorValue.setEnabled(false);
        GridData gd_stdErrorValue = new GridData(16384, 0x1000000, false, false, 1, 1);
        gd_stdErrorValue.widthHint = 10;
        this.stdErrorValue.setLayoutData((Object)gd_stdErrorValue);
        this.stdErrorValue.setPageIncrement(1);
        this.stdErrorValue.setMaximum(3);
        this.stdErrorValue.setMinimum(1);
        this.stdErrorValue.setSelection(1);
        this.stdDevBtn = new Button(composite_1, 16);
        this.stdDevBtn.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                BarGraphDialog.this.EnableStandardError(false);
                BarGraphDialog.this.EnableConfidenceLevel(false);
                BarGraphDialog.this.EnableStandardDeviation(true);
            }
        });
        this.stdDevBtn.setEnabled(false);
        this.stdDevBtn.setLayoutData((Object)new GridData(16384, 0x1000000, false, false, 2, 1));
        this.stdDevBtn.setText("Standard Deviation");
        this.stdDevLabel = new Label(composite_1, 0);
        this.stdDevLabel.setEnabled(false);
        this.stdDevLabel.setText("Multiplier");
        this.stdDevValue = new Spinner(composite_1, 2048);
        this.stdDevValue.setEnabled(false);
        GridData gd_stdDevValue = new GridData(16384, 0x1000000, false, false, 1, 1);
        gd_stdDevValue.widthHint = 10;
        this.stdDevValue.setLayoutData((Object)gd_stdDevValue);
        this.stdDevValue.setPageIncrement(1);
        this.stdDevValue.setMaximum(3);
        this.stdDevValue.setMinimum(1);
        this.stdDevValue.setSelection(1);
        return container;
    }

    protected void EnableStandardDeviation(boolean state) {
        this.stdDevLabel.setEnabled(state);
        this.stdDevValue.setEnabled(state);
    }

    protected void EnableStandardError(boolean state) {
        this.stdErrorLabel.setEnabled(state);
        this.stdErrorValue.setEnabled(state);
    }

    protected void EnableConfidenceLevel(boolean state) {
        this.confidenceLvlLabel.setEnabled(state);
        this.confidenceLvlValue.setEnabled(state);
    }

    protected void displayMultipleGraphs(boolean state) {
        this.orientGraphsCombo.setEnabled(state);
        this.lblOrientation.setEnabled(state);
        this.lblNewLabel.setEnabled(state);
        this.numRowsSpinner.setEnabled(state);
        this.lblNumberOfColumns.setEnabled(state);
        this.numColsSpinner.setEnabled(state);
    }

    protected void enableShowLegend(boolean state) {
        this.lblPosition.setEnabled(state);
        this.legTitleText.setEnabled(state);
        this.lblTitle.setEnabled(state);
        this.legPositionComo.setEnabled(state);
        this.lblAlignment.setEnabled(state);
    }

    protected void addItemToTableAxis(String varAxisName) {
        int ctr1 = this.tableAxis.getItemCount();
        TableItem tableAxisItem = new TableItem(this.tableAxis, 0x1000000);
        tableAxisItem.setText(0, varAxisName);
        System.out.println("set tableItem name: " + varAxisName);
        tableAxisItem.setData("index", (Object)ctr1);
        TableEditor[] tableAxisEditors = new TableEditor[]{new TableEditor(this.tableAxis), new TableEditor(this.tableAxis), new TableEditor(this.tableAxis)};
        Text newEditor = new Text((Composite)this.tableAxis, 0);
        newEditor.setText(varAxisName);
        Text lowerLimitEditor = new Text((Composite)this.tableAxis, 0);
        lowerLimitEditor.setText("Auto");
        GraphsUtilities.addTextModifyListener(lowerLimitEditor);
        Text upperLimitEditor = new Text((Composite)this.tableAxis, 0);
        upperLimitEditor.setText("Auto");
        GraphsUtilities.addTextModifyListener(upperLimitEditor);
        tableAxisEditors[0].grabHorizontal = true;
        tableAxisEditors[1].grabHorizontal = true;
        tableAxisEditors[2].grabHorizontal = true;
        tableAxisEditors[0].setEditor((Control)newEditor, tableAxisItem, 1);
        tableAxisEditors[1].setEditor((Control)lowerLimitEditor, tableAxisItem, 2);
        tableAxisEditors[2].setEditor((Control)upperLimitEditor, tableAxisItem, 3);
        tableAxisItem.setData("Editors", (Object)tableAxisEditors);
        this.table.pack();
        this.getShell().setSize(this.getShell().getSize().x - 1, this.getShell().getSize().y);
        this.getShell().setSize(this.getShell().getSize().x + 1, this.getShell().getSize().y);
    }

    protected void removeItemFromTableAxis(String varAxisName) {
        TableEditor[] axisEditors;
        int item = 0;
        int i = 0;
        while (i < this.tableAxis.getItemCount()) {
            if (varAxisName.equals(this.tableAxis.getItem(i).getText(0))) {
                item = i;
            }
            ++i;
        }
        System.out.println(String.valueOf(varAxisName) + "'s index is: " + Integer.toString(item));
        TableItem tableAxisItem = this.tableAxis.getItem(item);
        TableEditor[] tableEditorArray = axisEditors = (TableEditor[])tableAxisItem.getData("Editors");
        int n = axisEditors.length;
        int n2 = 0;
        while (n2 < n) {
            TableEditor tex = tableEditorArray[n2];
            tex.getEditor().dispose();
            ++n2;
        }
        this.tableAxis.remove(item);
        this.tableAxis.pack();
    }

    protected void addItemToVarTable(String varName) {
        RGB[] colors = GraphsUtilities.getGrayShades(this.table.getItemCount() + 1);
        int ctr = this.table.getItemCount();
        TableItem tableItem = new TableItem(this.table, 0x1000000);
        tableItem.setData("index", (Object)ctr);
        this.barDensityList.add(100);
        this.barLineAngleList.add(0);
        tableItem.setText(0, varName);
        GraphsUtilities.refreshTableColors(this.table, 1, colors);
        TableEditor[] tableEditors = new TableEditor[]{new TableEditor(this.table), new TableEditor(this.table), new TableEditor(this.table)};
        Button chooseColorButton = new Button((Composite)this.table, 0x1000000);
        chooseColorButton.setImage(ResourceManager.getPluginImage("Star", "icons/ellipsis.png"));
        chooseColorButton.setBackground(Display.getCurrent().getSystemColor(1));
        chooseColorButton.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                BarGraphDialog.this.table.setSelection(-1);
                try {
                    RGB rgbColor = GraphsUtilities.chooseColor();
                    tableItem.setBackground(1, new Color((Device)Display.getCurrent(), rgbColor));
                }
                catch (Exception exception) {
                    System.out.println("Exception at RGB choose color");
                }
            }
        });
        Button chooseFillButton = new Button((Composite)this.table, 0x1000000);
        chooseFillButton.setBackground(Display.getCurrent().getSystemColor(1));
        chooseFillButton.setImage(ResourceManager.getPluginImage("Star", "icons/ellipsis.png"));
        Label lblImagePlaceHolder = new Label((Composite)this.table, 0x1000004);
        lblImagePlaceHolder.setImage(ResourceManager.getPluginImage("Star", "icons/solid.png"));
        chooseFillButton.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                int index = ctr;
                ChooseFillPatternDialog fillDlg = new ChooseFillPatternDialog(BarGraphDialog.this.getShell(), varName);
                fillDlg.open();
                if (fillDlg.getReturnCode() == 0) {
                    BarGraphDialog.this.table.setSelection(-1);
                    try {
                        BarGraphDialog.this.barDensityList.remove(index);
                        BarGraphDialog.this.barLineAngleList.remove(index);
                        lblImagePlaceHolder.setImage(ResourceManager.getPluginImage("Star", "icons/" + fillDlg.getChosenPattern() + ".png"));
                        BarGraphDialog.this.barDensityList.add(index, fillDlg.getLineDensity());
                        BarGraphDialog.this.barLineAngleList.add(index, fillDlg.getPatternAngle());
                    }
                    catch (Exception exception) {}
                }
            }
        });
        tableEditors[0].grabHorizontal = true;
        tableEditors[1].grabHorizontal = true;
        tableEditors[2].grabHorizontal = true;
        tableEditors[0].setEditor((Control)chooseColorButton, tableItem, 2);
        tableEditors[1].setEditor((Control)chooseFillButton, tableItem, 4);
        tableEditors[2].setEditor((Control)lblImagePlaceHolder, tableItem, 3);
        tableItem.setData("Editors", (Object)tableEditors);
        this.table.pack();
        this.compositeConfigureBars.pack();
        this.composite.pack();
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
        this.barDensityList.remove(item);
        this.barLineAngleList.remove(item);
        this.table.remove(item);
        this.table.pack();
        this.compositeConfigureBars.pack();
    }

    protected void enableStyleOptions(boolean state) {
        this.styleLabel.setEnabled(state);
        this.clusteredStyleBtn.setEnabled(state);
        this.stackedStyleBtn.setEnabled(state);
    }

    protected void enableBarsRepresent(boolean state) {
        this.barsRepresentLabel.setEnabled(state);
        this.barsRepresentCombo.setEnabled(state);
        if (state && this.barsRepresentCombo.getSelectionIndex() == 1 && this.clusteredStyleBtn.getSelection()) {
            this.enableDisplayErrorBars(true);
        } else {
            this.enableDisplayErrorBars(false);
        }
        if (state && this.displayErrorBars.getSelection()) {
            this.enableStyleOptions(false);
        }
    }

    private void enableDisplayErrorBars(boolean state) {
        this.displayErrorBars.setEnabled(state);
        if (state && this.displayErrorBars.getSelection()) {
            this.enableErrorBarsOptions(true);
        } else {
            this.enableErrorBarsOptions(false);
        }
    }

    private void enableErrorBarsOptions(boolean state) {
        this.errorBarsLabel.setEnabled(state);
        this.confidenceLvlBtn.setEnabled(state);
        this.confidenceLvlLabel.setEnabled(false);
        this.confidenceLvlValue.setEnabled(false);
        this.stdErrorBtn.setEnabled(state);
        this.stdErrorLabel.setEnabled(false);
        this.stdErrorValue.setEnabled(false);
        this.stdDevBtn.setEnabled(state);
        this.stdDevLabel.setEnabled(false);
        this.stdDevValue.setEnabled(false);
        if (state) {
            if (this.confidenceLvlBtn.getSelection()) {
                this.confidenceLvlValue.setEnabled(true);
                this.confidenceLvlLabel.setEnabled(true);
            } else if (this.stdErrorBtn.getSelection()) {
                this.stdErrorValue.setEnabled(true);
                this.stdErrorLabel.setEnabled(true);
            } else if (this.stdDevBtn.getSelection()) {
                this.stdDevValue.setEnabled(true);
                this.stdDevLabel.setEnabled(true);
            }
        } else {
            this.enableStyleOptions(true);
        }
    }

    protected void setFactors() {
        this.varInfo = this.dataManipulationManager.getVarInfoFromFile(this.file.getAbsolutePath());
        this.numericVariables = this.dataManipulationManager.getNumericVars(this.varInfo);
        this.factorVariables = this.dataManipulationManager.getFactorVars(this.varInfo);
    }

    public void enableFactorButtons(boolean state) {
        this.groupsVarList.setSelection(-1);
        this.axisVarList.setSelection(-1);
        this.lstClusterBy.setSelection(-1);
        this.addGroupsBtn.setEnabled(state);
        this.addAxisBtn.setEnabled(state);
        this.addClusterBybtn.setEnabled(state);
        if (state) {
            this.addGroupsBtn.setText("Add");
            this.addAxisBtn.setText("Add");
            this.addClusterBybtn.setText("Add");
            if (this.groupsVarList.getItemCount() > 0) {
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
            BarGraphDialog bar = new BarGraphDialog(this.parentShell, this.analysisType, this.file);
            this.close();
            bar.open();
        }
        super.buttonPressed(buttonId);
    }

    protected void createButtonsForButtonBar(Composite parent) {
        this.createButton(parent, 19, "Reset", true);
        this.createButton(parent, 0, IDialogConstants.OK_LABEL, true);
        this.createButton(parent, 1, IDialogConstants.CANCEL_LABEL, false);
    }

    protected void okPressed() {
        String dataFileName = this.file.getAbsolutePath().replaceAll("\\\\", "/");
        boolean continueWithErrorBars = false;
        if (this.responseVarList.getItemCount() < 1) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"Please add variable(s) from the numeric variables list.");
        } else if (this.axisVarList.getItemCount() < 1) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"Please select a category axis variable.");
        } else if (this.lstClusterBy.getItemCount() > 3) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"Please enter three variables only on Cluster List.");
        } else {
            int n;
            String[] stringArray;
            this.nVar = this.responseVarList.getItems();
            this.cVar = this.axisVarList.getItemCount() == 0 ? null : this.axisVarList.getItem(0);
            this.mTitle = this.mainTitleText.getText();
            this.xAxisLab = this.categoryAxisText.getText();
            this.byVar = this.groupsVarList.getItemCount() == 0 ? null : this.groupsVarList.getItem(0);
            this.clustVars = this.lstClusterBy.getItems();
            if (this.rawDataBtn.getSelection()) {
                this.typeData = "raw";
                this.descStat = this.barsRepresentList[this.barsRepresentCombo.getSelectionIndex()];
            } else {
                this.descStat = null;
                this.typeData = "sumStat";
            }
            this.barsHoriz = this.verticalOrientationBtn.getSelection() ? "FALSE" : "TRUE";
            this.barsClus = this.clusteredStyleBtn.getSelection() ? "TRUE" : "FALSE";
            this.errBars = this.displayErrorBars.getSelection() && this.displayErrorBars.getEnabled() ? "TRUE" : "FALSE";
            if (this.errBars.equals("TRUE")) {
                if (this.confidenceLvlBtn.getSelection()) {
                    this.confLev = (double)this.confidenceLvlValue.getSelection() / Math.pow(10.0, 2.0);
                    this.typeErrBar = "confInt";
                } else if (this.stdErrorBtn.getSelection()) {
                    this.errMult = this.stdErrorValue.getSelection();
                    this.typeErrBar = "stdErr";
                } else if (this.stdDevBtn.getSelection()) {
                    this.errMult = this.stdDevValue.getSelection();
                    this.typeErrBar = "stdDev";
                }
                boolean hasLevelsWithOneDatum = false;
                StringBuilder sb = new StringBuilder();
                stringArray = this.nVar;
                int n2 = this.nVar.length;
                n = 0;
                while (n < n2) {
                    String nvar = stringArray[n];
                    String[] levelsWithOneValue = ProjectExplorerView.rJavaManager.getRJavaDataManipulationManager().checkLevelsWithOneDatum(dataFileName.replaceAll("\\\\", "/"), this.cVar, nvar, this.byVar);
                    System.out.println("value of levels with one value: " + levelsWithOneValue[0]);
                    if (levelsWithOneValue[0].equals("FALSE")) {
                        hasLevelsWithOneDatum = true;
                    } else if (!levelsWithOneValue[0].equals("TRUE") && !levelsWithOneValue[0].equals("FALSE")) {
                        hasLevelsWithOneDatum = true;
                        sb.append("\n");
                        sb.append(nvar);
                        sb.append(" [Group:level(s)]\n");
                        String[] stringArray2 = levelsWithOneValue;
                        int n3 = levelsWithOneValue.length;
                        int n4 = 0;
                        while (n4 < n3) {
                            String s = stringArray2[n4];
                            sb.append(s);
                            sb.append("\n");
                            ++n4;
                        }
                    }
                    ++n;
                }
                if (hasLevelsWithOneDatum) {
                    System.out.println(sb.toString());
                    if (sb.length() < 1) {
                        if (MessageDialog.openQuestion((Shell)this.getShell(), (String)"Invalid Input", (String)"There are category level(s) with only one observation.\nFor these, error bars cannot be displayed.\n\nDo you still want to proceed?")) {
                            continueWithErrorBars = true;
                        }
                    } else if (MessageDialog.openQuestion((Shell)this.getShell(), (String)"Invalid Input", (String)("There are category level(s) with only one observation:\n" + sb.toString() + "\nDo you still want to proceed?"))) {
                        continueWithErrorBars = true;
                    }
                } else {
                    continueWithErrorBars = true;
                }
            } else {
                this.typeErrBar = null;
            }
            if (this.btnShowLegend.getSelection()) {
                this.showLeg = "TRUE";
                this.legPos = this.legPositionComo.getText().replaceAll("-", "");
                this.legTitle = !this.legTitleText.getText().isEmpty() ? this.legTitleText.getText() : null;
                this.legCol = this.numColsSpinner1.getSelection();
            } else {
                this.showLeg = "FALSE";
                this.legPos = null;
                this.legTitle = null;
            }
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
            this.axisLabelStyle = this.cmboAxisOrientation.getSelectionIndex();
            this.showCatVarLevels = this.displayVarNamesBtn.getEnabled() && this.displayVarNamesBtn.getSelection() ? "TRUE" : "FALSE";
            this.yAxisLab = new String[this.nVar.length];
            this.minValue = new String[this.nVar.length];
            this.maxValue = new String[this.nVar.length];
            int ctr1 = 0;
            TableItem[] tableItemArray = this.tableAxis.getItems();
            n = tableItemArray.length;
            int nvar = 0;
            while (nvar < n) {
                TableItem tableAxisItem = tableItemArray[nvar];
                TableEditor[] axisEditors = (TableEditor[])tableAxisItem.getData("Editors");
                this.yAxisLab[ctr1] = ((Text)axisEditors[0].getEditor()).getText();
                this.minValue[ctr1] = GraphsUtilities.checkIfAutoOrNumeric(((Text)axisEditors[1].getEditor()).getText());
                this.maxValue[ctr1] = GraphsUtilities.checkIfAutoOrNumeric(((Text)axisEditors[2].getEditor()).getText());
                ++ctr1;
                ++nvar;
            }
            this.barDensity = GraphsUtilities.convertIntListToIntAray(this.barDensityList);
            this.barLineAngle = GraphsUtilities.convertIntListToIntAray(this.barLineAngleList);
            this.barColor = new String[this.table.getItemCount()];
            int ctr = 0;
            stringArray = this.table.getItems();
            int n5 = stringArray.length;
            n = 0;
            while (n < n5) {
                String tableItem = stringArray[n];
                this.barColor[ctr] = GraphsUtilities.convertToRrgbFormat(tableItem.getBackground(1).getRGB().toString());
                ++ctr;
                ++n;
            }
            if (this.errBars.equals("FALSE") || continueWithErrorBars) {
                File outputFolder = GraphsUtilities.createOutputFolder(this.file.getName(), this.analysisType);
                if (!outputFolder.exists()) {
                    outputFolder.mkdir();
                }
                OperationProgressDialog rInfo = new OperationProgressDialog(this.getShell(), "Creating Bar Graph");
                rInfo.open();
                ProjectExplorerView.rJavaManager.getRJavaGraphManager().createGraphBarplot(String.valueOf(outputFolder.getAbsolutePath().replaceAll("\\\\", "/")) + "/", dataFileName, this.nVar, this.cVar, this.clustVars, this.mTitle, this.yAxisLab, this.xAxisLab, this.minValue, this.maxValue, this.typeData, this.descStat, this.barsHoriz, this.barsClus, this.byVar, this.errBars, this.typeErrBar, this.errMult, this.confLev, this.axisLabelStyle, this.barColor, this.showLeg, this.legPos, this.legTitle, this.legCol, this.boxed, this.multGraphs, this.numRowsGraphs, this.numColsGraphs, this.orientGraphs, this.showCatVarLevels, this.barDensity, this.barLineAngle);
                rInfo.close();
                WindowDialogControlUtil.hideAllWindowDialog();
                GraphsUtilities.openFolder(outputFolder);
            }
        }
    }

    protected Point getInitialSize() {
        return new Point(595, 580);
    }

    protected boolean isResizable() {
        return true;
    }
}

