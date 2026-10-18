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
import org.irri.breedingtool.graphs.dialog.ChooseFillPatternDialog;
import org.irri.breedingtool.manager.impl.DataManipulationManager;
import org.irri.breedingtool.projectexplorer.view.ProjectExplorerView;
import org.irri.breedingtool.utility.GraphsUtilities;
import org.irri.breedingtool.utility.WindowDialogControlUtil;

public class PieChartDialog
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
    private String[] barsRepresentList = new String[]{"freq", "mean", "median", "sum"};
    private String[] labelsList = new String[]{"none", "counts", "pcts", "cats", "countcats", "pctcats"};
    private int maxLevels = 20;
    private String cVar = null;
    private String mTitle = null;
    private String byVar = null;
    private String showLeg = "FALSE";
    private String legPos = null;
    private String legTitle = null;
    private int legCol = 1;
    private String boxed = "FALSE";
    private String multGraphs = "FALSE";
    private int numRowsGraphs = 1;
    private int numColsGraphs = 1;
    private String orientGraphs = "top-bottom";
    private String wVar = null;
    private String[] sliceColors = new String[]{"rgb(255,0,255, max = 255)"};
    private String useLabels = "none";
    private int[] pieDensity = null;
    private int[] pieLineAngle = null;
    private RGB[] grayShadesRGB;
    private String analysisType;
    private TableColumn tableColumn;
    private Button addAxisBtn;
    private Button addGroupsBtn;
    private List axisVarList;
    private List groupsVarList;
    private Text mainTitleText;
    private Text legTitleText;
    private TabItem tbtmModelSpecifications;
    private CCombo legPositionComo;
    private CCombo orientGraphsCombo;
    private Button btnShowLegend;
    private Button btnDrawBoxAround;
    private Button btnDisplayMultipleGraphs;
    private Spinner numColsSpinner;
    private Spinner numRowsSpinner;
    private Label lblPosition;
    private Label lblTitle;
    private Label lblNewLabel;
    private Label lblNumberOfColumns;
    private Label lblOrientation;
    private Label lblFormatSlices;
    private Label lblLabels;
    private Table table;
    private TabItem tbtmDisplay;
    private Composite composite_2;
    private Label lblInputFileContains;
    private Button btnRawData;
    private Button btnRadioButton;
    private Label lblResponseVariables;
    private CCombo combo;
    private Shell parentShell;
    private Label lblAlignment;
    private Spinner numColsSpinner1;
    private Label lblNewLabel_1;

    public PieChartDialog(Shell parentShell, String analysisType, File file) {
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
                WindowDialogControlUtil.removeWindowDialogToList(PieChartDialog.this.getShell());
            }
        });
        parent.getShell().setText("Pie Chart: " + this.dataManipulationManager.getDisplayFileName(this.file.getAbsolutePath()));
        Composite container = (Composite)super.createDialogArea(parent);
        TabFolder tabFolder = new TabFolder(container, 0);
        tabFolder.setLayoutData((Object)new GridData(4, 4, true, true, 1, 1));
        this.tbtmModelSpecifications = new TabItem(tabFolder, 0);
        this.tbtmModelSpecifications.setText("Variable Definition");
        Composite modelComposite = new Composite((Composite)tabFolder, 0);
        this.tbtmModelSpecifications.setControl((Control)modelComposite);
        modelComposite.setLayout((Layout)new GridLayout(5, false));
        this.lblInputFileContains = new Label(modelComposite, 0);
        this.lblInputFileContains.setText("Input File Contains:");
        this.btnRawData = new Button(modelComposite, 16);
        this.btnRawData.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                PieChartDialog.this.addBtn.setEnabled(false);
                if (PieChartDialog.this.responseVarList.getItemCount() > 0) {
                    PieChartDialog.this.dataManipulationManager.removeItemOfThenAddTo(PieChartDialog.this.responseVarList, PieChartDialog.this.numVarList);
                }
                PieChartDialog.this.responseVarList.setEnabled(false);
                PieChartDialog.this.lblResponseVariables.setEnabled(false);
            }
        });
        this.btnRawData.setSelection(true);
        this.btnRawData.setText("Raw Data");
        this.btnRadioButton = new Button(modelComposite, 16);
        this.btnRadioButton.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                PieChartDialog.this.numVarList.setSelection(-1);
                PieChartDialog.this.responseVarList.setEnabled(true);
                PieChartDialog.this.lblResponseVariables.setEnabled(true);
            }
        });
        this.btnRadioButton.setLayoutData((Object)new GridData(16384, 0x1000000, false, false, 3, 1));
        this.btnRadioButton.setText("Frequencies");
        Label lblNumericVariables = new Label(modelComposite, 0);
        lblNumericVariables.setLayoutData((Object)new GridData(16384, 0x1000000, false, false, 2, 1));
        lblNumericVariables.setText("Numeric Variables:");
        new Label(modelComposite, 0);
        new Label(modelComposite, 0);
        this.lblResponseVariables = new Label(modelComposite, 0);
        this.lblResponseVariables.setEnabled(false);
        this.lblResponseVariables.setText("Weight Variable:");
        this.numVarList = new List(modelComposite, 2560);
        GridData gd_numVarList = new GridData(4, 4, true, false, 2, 1);
        gd_numVarList.heightHint = 95;
        gd_numVarList.widthHint = 126;
        this.numVarList.setLayoutData((Object)gd_numVarList);
        this.numVarList.setItems(this.numericVariables);
        this.numVarList.addMouseListener(new MouseListener(){

            public void mouseDoubleClick(MouseEvent e) {
                if (PieChartDialog.this.numVarList.getSelectionCount() > 0 && PieChartDialog.this.lblResponseVariables.getEnabled() && PieChartDialog.this.responseVarList.getItemCount() < 1) {
                    PieChartDialog.this.dataManipulationManager.moveSelectedStringFromTo(PieChartDialog.this.numVarList, PieChartDialog.this.responseVarList, PieChartDialog.this.moveBtn);
                    PieChartDialog.this.numVarList.remove(PieChartDialog.this.numVarList.getSelectionIndices());
                    PieChartDialog.this.addBtn.setEnabled(false);
                } else if (PieChartDialog.this.lblResponseVariables.getEnabled()) {
                    MessageDialog.openWarning((Shell)PieChartDialog.this.getShell(), (String)"Invalid Input", (String)"You can only enter a single weight variable.");
                }
            }

            public void mouseDown(MouseEvent e) {
                if (PieChartDialog.this.numVarList.getSelectionCount() > 0) {
                    PieChartDialog.this.moveBtn.setEnabled(true);
                    PieChartDialog.this.factorVarList.setSelection(-1);
                    PieChartDialog.this.responseVarList.setSelection(-1);
                    PieChartDialog.this.addBtn.setText("Add");
                    PieChartDialog.this.moveBtn.setText("Set to Factor");
                    PieChartDialog.this.enableFactorButtons(false);
                    PieChartDialog.this.enableNumericButtons(true);
                }
            }

            public void mouseUp(MouseEvent e) {
            }
        });
        this.addBtn = new Button(modelComposite, 0);
        this.addBtn.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (PieChartDialog.this.numVarList.getSelectionCount() > 0) {
                    String[] selectedNumVars = PieChartDialog.this.numVarList.getSelection();
                    int i = 0;
                    while (i < selectedNumVars.length) {
                        PieChartDialog.this.responseVarList.add(selectedNumVars[i]);
                        ++i;
                    }
                    PieChartDialog.this.numVarList.remove(PieChartDialog.this.numVarList.getSelectionIndices());
                } else {
                    String[] selectedNumVars = PieChartDialog.this.responseVarList.getSelection();
                    int i = 0;
                    while (i < selectedNumVars.length) {
                        PieChartDialog.this.numVarList.add(selectedNumVars[i]);
                        ++i;
                    }
                    PieChartDialog.this.responseVarList.remove(PieChartDialog.this.responseVarList.getSelectionIndices());
                }
                PieChartDialog.this.enableNumericButtons(false);
            }
        });
        this.addBtn.setEnabled(false);
        GridData gd_addBtn = new GridData(16384, 128, false, false, 2, 1);
        gd_addBtn.widthHint = 52;
        this.addBtn.setLayoutData((Object)gd_addBtn);
        this.addBtn.setText("Add");
        this.responseVarList = new List(modelComposite, 2818);
        this.responseVarList.setEnabled(false);
        GridData gd_responseVarList = new GridData(4, 128, true, false, 1, 1);
        gd_responseVarList.heightHint = 5;
        gd_responseVarList.widthHint = 111;
        this.responseVarList.setLayoutData((Object)gd_responseVarList);
        this.responseVarList.addMouseListener(new MouseListener(){

            public void mouseDoubleClick(MouseEvent e) {
                if (PieChartDialog.this.responseVarList.getSelectionCount() > 0) {
                    PieChartDialog.this.dataManipulationManager.moveSelectedStringFromTo(PieChartDialog.this.responseVarList, PieChartDialog.this.numVarList, PieChartDialog.this.moveBtn);
                    PieChartDialog.this.responseVarList.remove(PieChartDialog.this.responseVarList.getSelectionIndices());
                    PieChartDialog.this.addBtn.setEnabled(false);
                }
            }

            public void mouseDown(MouseEvent e) {
                if (PieChartDialog.this.responseVarList.getSelectionCount() > 0) {
                    PieChartDialog.this.numVarList.setSelection(-1);
                    PieChartDialog.this.addBtn.setText("Remove");
                    PieChartDialog.this.addBtn.setEnabled(true);
                    PieChartDialog.this.moveBtn.setEnabled(false);
                    PieChartDialog.this.enableFactorButtons(false);
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
                if (PieChartDialog.this.numVarList.getSelectionCount() > 0) {
                    String[] selectedNumVars = PieChartDialog.this.numVarList.getSelection();
                    int i = 0;
                    while (i < selectedNumVars.length) {
                        PieChartDialog.this.factorVarList.add(selectedNumVars[i]);
                        PieChartDialog.this.dataManipulationManager.editVariableType(PieChartDialog.this.file.getAbsolutePath(), PieChartDialog.this.varInfo, selectedNumVars[i], "Factor");
                        ++i;
                    }
                    PieChartDialog.this.numVarList.remove(PieChartDialog.this.numVarList.getSelectionIndices());
                    PieChartDialog.this.enableNumericButtons(false);
                } else {
                    String[] selectedNumVars = PieChartDialog.this.factorVarList.getSelection();
                    int i = 0;
                    while (i < selectedNumVars.length) {
                        PieChartDialog.this.numVarList.add(selectedNumVars[i]);
                        PieChartDialog.this.dataManipulationManager.editVariableType(PieChartDialog.this.file.getAbsolutePath(), PieChartDialog.this.varInfo, selectedNumVars[i], "Numeric");
                        ++i;
                    }
                    PieChartDialog.this.factorVarList.remove(PieChartDialog.this.factorVarList.getSelectionIndices());
                    PieChartDialog.this.enableFactorButtons(false);
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
        Label lblCategory = new Label(modelComposite, 0);
        lblCategory.setText("Categorize by:");
        this.factorVarList = new List(modelComposite, 2560);
        GridData gd_factorVarList = new GridData(4, 4, true, true, 2, 3);
        gd_factorVarList.widthHint = 65;
        this.factorVarList.setLayoutData((Object)gd_factorVarList);
        this.factorVarList.setItems(this.factorVariables);
        this.factorVarList.addListener(3, new Listener(){

            public void handleEvent(Event event) {
                if (PieChartDialog.this.factorVarList.getSelectionIndex() > -1) {
                    PieChartDialog.this.enableNumericButtons(false);
                    PieChartDialog.this.enableFactorButtons(false);
                    PieChartDialog.this.numVarList.setSelection(-1);
                    String[] s = PieChartDialog.this.factorVarList.getSelection();
                    String isNumeric = PieChartDialog.this.dataManipulationManager.isNumeric(PieChartDialog.this.file.getAbsolutePath().replaceAll("\\\\", "/"), s[0]);
                    if (isNumeric.equals("TRUE")) {
                        PieChartDialog.this.moveBtn.setText("Set to Numeric");
                        PieChartDialog.this.moveBtn.setEnabled(true);
                    } else {
                        PieChartDialog.this.moveBtn.setEnabled(false);
                    }
                    PieChartDialog.this.enableFactorButtons(true);
                }
            }
        });
        this.addAxisBtn = new Button(modelComposite, 0);
        this.addAxisBtn.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (PieChartDialog.this.factorVarList.getSelectionCount() > 0) {
                    PieChartDialog.this.getLevelsThenAddItemToVarTable(PieChartDialog.this.factorVarList.getSelection()[0]);
                    PieChartDialog.this.dataManipulationManager.moveSelectedStringFromTo(PieChartDialog.this.factorVarList, PieChartDialog.this.axisVarList, PieChartDialog.this.moveBtn);
                    PieChartDialog.this.numColsSpinner1.setEnabled(true);
                } else {
                    PieChartDialog.this.removeItemsFromVarTable();
                    PieChartDialog.this.dataManipulationManager.moveSelectedStringFromTo(PieChartDialog.this.axisVarList, PieChartDialog.this.factorVarList, PieChartDialog.this.moveBtn);
                    PieChartDialog.this.numColsSpinner1.setEnabled(false);
                }
                PieChartDialog.this.enableFactorButtons(false);
            }
        });
        GridData gd_addAxisBtn = new GridData(16384, 128, false, false, 2, 1);
        gd_addAxisBtn.widthHint = 52;
        this.addAxisBtn.setLayoutData((Object)gd_addAxisBtn);
        this.addAxisBtn.setText("Add");
        this.addAxisBtn.setEnabled(false);
        this.axisVarList = new List(modelComposite, 2818);
        GridData gd_axisVarList = new GridData(4, 128, true, true, 1, 1);
        gd_axisVarList.heightHint = 5;
        this.axisVarList.setLayoutData((Object)gd_axisVarList);
        this.axisVarList.addMouseListener(new MouseListener(){

            public void mouseDoubleClick(MouseEvent e) {
                if (PieChartDialog.this.axisVarList.getSelectionCount() > 0) {
                    PieChartDialog.this.removeItemsFromVarTable();
                    PieChartDialog.this.dataManipulationManager.moveSelectedStringFromTo(PieChartDialog.this.axisVarList, PieChartDialog.this.factorVarList, PieChartDialog.this.moveBtn);
                    PieChartDialog.this.addAxisBtn.setEnabled(false);
                    PieChartDialog.this.numColsSpinner1.setEnabled(false);
                }
            }

            public void mouseDown(MouseEvent e) {
                if (PieChartDialog.this.axisVarList.getSelectionCount() > 0) {
                    PieChartDialog.this.factorVarList.setSelection(-1);
                    PieChartDialog.this.addAxisBtn.setText("Remove");
                    PieChartDialog.this.addAxisBtn.setEnabled(true);
                }
            }

            public void mouseUp(MouseEvent e) {
            }
        });
        new Label(modelComposite, 0);
        new Label(modelComposite, 0);
        Label lblCreatePlotsBy = new Label(modelComposite, 0);
        lblCreatePlotsBy.setText("Group by:");
        this.addGroupsBtn = new Button(modelComposite, 0);
        this.addGroupsBtn.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (PieChartDialog.this.factorVarList.getSelectionCount() > 0) {
                    PieChartDialog.this.dataManipulationManager.moveSelectedStringFromTo(PieChartDialog.this.factorVarList, PieChartDialog.this.groupsVarList, PieChartDialog.this.moveBtn);
                } else {
                    PieChartDialog.this.dataManipulationManager.moveSelectedStringFromTo(PieChartDialog.this.groupsVarList, PieChartDialog.this.factorVarList, PieChartDialog.this.moveBtn);
                }
                PieChartDialog.this.enableFactorButtons(false);
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
                if (PieChartDialog.this.groupsVarList.getSelectionCount() > 0) {
                    PieChartDialog.this.dataManipulationManager.moveSelectedStringFromTo(PieChartDialog.this.groupsVarList, PieChartDialog.this.factorVarList, PieChartDialog.this.moveBtn);
                    PieChartDialog.this.addGroupsBtn.setEnabled(false);
                }
            }

            public void mouseDown(MouseEvent e) {
                if (PieChartDialog.this.groupsVarList.getSelectionCount() > 0) {
                    PieChartDialog.this.factorVarList.setSelection(-1);
                    PieChartDialog.this.addGroupsBtn.setText("Remove");
                    PieChartDialog.this.addGroupsBtn.setEnabled(true);
                }
            }

            public void mouseUp(MouseEvent e) {
            }
        });
        this.tbtmDisplay = new TabItem(tabFolder, 0);
        this.tbtmDisplay.setText("Display Options");
        this.composite_2 = new Composite((Composite)tabFolder, 0);
        this.tbtmDisplay.setControl((Control)this.composite_2);
        this.composite_2.setLayout((Layout)new GridLayout(7, false));
        Label lblMainTitle = new Label(this.composite_2, 0);
        lblMainTitle.setLayoutData((Object)new GridData(131072, 0x1000000, false, false, 1, 1));
        lblMainTitle.setText("Main Title:");
        this.mainTitleText = new Text(this.composite_2, 2048);
        int maxLen = 45;
        this.mainTitleText.setTextLimit(maxLen);
        this.mainTitleText.setLayoutData((Object)new GridData(4, 0x1000000, true, false, 6, 1));
        this.lblFormatSlices = new Label(this.composite_2, 0);
        this.lblFormatSlices.setLayoutData((Object)new GridData(16384, 0x1000000, false, false, 7, 1));
        this.lblFormatSlices.setText("Format slices:");
        this.table = new Table(this.composite_2, 67586);
        GridData gd_table = new GridData(4, 4, true, true, 7, 2);
        gd_table.heightHint = 60;
        gd_table.widthHint = 204;
        this.table.setLayoutData((Object)gd_table);
        this.table.setHeaderVisible(true);
        this.table.setLinesVisible(true);
        TableColumn tblclmnNewColumn = new TableColumn(this.table, 0);
        tblclmnNewColumn.setWidth(100);
        tblclmnNewColumn.setText("Variable Level(s)");
        TableColumn tblclmnNewColumnf = new TableColumn(this.table, 0);
        tblclmnNewColumnf.setWidth(75);
        tblclmnNewColumnf.setText("Color");
        TableColumn tblclmnNewColumn_1 = new TableColumn(this.table, 0);
        tblclmnNewColumn_1.setWidth(25);
        TableColumn tblclmnNewColumn3 = new TableColumn(this.table, 0);
        tblclmnNewColumn3.setWidth(100);
        tblclmnNewColumn3.setText("Fill Pattern");
        tblclmnNewColumn3.pack();
        TableColumn tblclmnNewColumn4 = new TableColumn(this.table, 0);
        tblclmnNewColumn4.setWidth(25);
        this.lblLabels = new Label(this.composite_2, 0);
        this.lblLabels.setText("Labels:");
        this.combo = new CCombo(this.composite_2, 2048);
        this.combo.setItems(new String[]{"[none]", "counts", "percentages", "levels", "levels & counts", "levels & percentages"});
        this.combo.setBackground(SWTResourceManager.getColor(1));
        this.combo.setEditable(false);
        this.combo.setLayoutData((Object)new GridData(4, 0x1000000, false, false, 5, 1));
        this.combo.select(5);
        new Label(this.composite_2, 0);
        this.btnDrawBoxAround = new Button(this.composite_2, 32);
        this.btnDrawBoxAround.setSelection(true);
        this.btnDrawBoxAround.setLayoutData((Object)new GridData(16384, 0x1000000, false, false, 7, 1));
        this.btnDrawBoxAround.setText("Draw box around plot");
        this.btnShowLegend = new Button(this.composite_2, 32);
        this.btnShowLegend.setSelection(true);
        this.btnShowLegend.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (PieChartDialog.this.btnShowLegend.getSelection()) {
                    PieChartDialog.this.enableShowLegend(true);
                } else {
                    PieChartDialog.this.enableShowLegend(false);
                }
            }
        });
        this.btnShowLegend.setLayoutData((Object)new GridData(16384, 0x1000000, false, false, 7, 1));
        this.btnShowLegend.setText("Show legend");
        new Label(this.composite_2, 0);
        this.lblPosition = new Label(this.composite_2, 0);
        this.lblPosition.setText("Position:");
        this.legPositionComo = new CCombo(this.composite_2, 2056);
        this.legPositionComo.setBackground(SWTResourceManager.getColor(1));
        this.legPositionComo.setEditable(false);
        this.legPositionComo.setItems(new String[]{"bottom", "bottom-left", "bottom-right", "center", "left", "right", "top", "top-left", "top-right"});
        this.legPositionComo.setLayoutData((Object)new GridData(4, 0x1000000, false, false, 2, 1));
        this.legPositionComo.select(2);
        this.lblNewLabel_1 = new Label(this.composite_2, 0);
        GridData gd_lblNewLabel_1 = new GridData(16384, 0x1000000, false, false, 1, 1);
        gd_lblNewLabel_1.widthHint = 10;
        this.lblNewLabel_1.setLayoutData((Object)gd_lblNewLabel_1);
        this.lblAlignment = new Label(this.composite_2, 0);
        this.lblAlignment.setLayoutData((Object)new GridData(131072, 0x1000000, false, false, 1, 1));
        this.lblAlignment.setText("Number of columns:");
        this.numColsSpinner1 = new Spinner(this.composite_2, 2056);
        GridData gd_numColsSpinner1 = new GridData(16384, 0x1000000, false, false, 1, 1);
        gd_numColsSpinner1.widthHint = 10;
        this.numColsSpinner1.setLayoutData((Object)gd_numColsSpinner1);
        this.numColsSpinner1.setBackground(SWTResourceManager.getColor(1));
        this.numColsSpinner1.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                String selectedStrings = PieChartDialog.this.axisVarList.getItem(0);
                String[] catLevels = DataManipulationManager.getEnvtLevels(selectedStrings, PieChartDialog.this.file);
                PieChartDialog.this.maxLevels = catLevels.length;
                PieChartDialog.this.numColsSpinner1.setMaximum(PieChartDialog.this.maxLevels);
                PieChartDialog.this.numColsSpinner1.setMinimum(1);
            }
        });
        this.numColsSpinner1.setSelection(1);
        new Label(this.composite_2, 0);
        this.lblTitle = new Label(this.composite_2, 0);
        this.lblTitle.setText("Title:");
        this.legTitleText = new Text(this.composite_2, 2048);
        this.legTitleText.setLayoutData((Object)new GridData(4, 0x1000000, true, false, 5, 1));
        this.btnDisplayMultipleGraphs = new Button(this.composite_2, 32);
        this.btnDisplayMultipleGraphs.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (PieChartDialog.this.btnDisplayMultipleGraphs.getSelection()) {
                    PieChartDialog.this.displayMultipleGraphs(true);
                } else {
                    PieChartDialog.this.displayMultipleGraphs(false);
                }
            }
        });
        this.btnDisplayMultipleGraphs.setLayoutData((Object)new GridData(16384, 0x1000000, false, false, 7, 1));
        this.btnDisplayMultipleGraphs.setText("Display multiple graphs in a page");
        new Label(this.composite_2, 0);
        this.lblNewLabel = new Label(this.composite_2, 0);
        this.lblNewLabel.setLayoutData((Object)new GridData(16384, 0x1000000, false, false, 2, 1));
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
        new Label(this.composite_2, 0);
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
        this.orientGraphsCombo = new CCombo(this.composite_2, 2048);
        this.orientGraphsCombo.setEnabled(false);
        this.orientGraphsCombo.setBackground(SWTResourceManager.getColor(1));
        this.orientGraphsCombo.setEditable(false);
        this.orientGraphsCombo.setItems(new String[]{"Left-to-right", "Top-to-bottom"});
        this.orientGraphsCombo.setLayoutData((Object)new GridData(4, 0x1000000, false, false, 5, 1));
        this.orientGraphsCombo.select(1);
        return container;
    }

    protected void getLevelsThenAddItemToVarTable(String varName) {
        String[] levels = DataManipulationManager.getEnvtLevels(varName, this.file);
        this.grayShadesRGB = GraphsUtilities.getGrayShades(levels.length);
        this.sliceColors = new String[levels.length];
        this.pieLineAngle = new int[levels.length];
        this.pieDensity = new int[levels.length];
        int i = 0;
        while (i < levels.length) {
            this.sliceColors[i] = GraphsUtilities.convertToRrgbFormat(this.grayShadesRGB[i].toString());
            this.pieLineAngle[i] = 0;
            this.pieDensity[i] = 100;
            ++i;
        }
        String[] stringArray = levels;
        int n = levels.length;
        int n2 = 0;
        while (n2 < n) {
            String s = stringArray[n2];
            this.addItemToVarTable(s);
            ++n2;
        }
        this.table.pack();
        this.composite_2.pack();
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
        this.numColsSpinner1.setEnabled(state);
        this.lblAlignment.setEnabled(state);
    }

    protected void addItemToVarTable(String varName) {
        int ctr = this.table.getItemCount();
        TableItem tableItem = new TableItem(this.table, 0x1000000);
        tableItem.setData("index", (Object)ctr);
        tableItem.setText(0, varName);
        tableItem.setBackground(1, new Color((Device)Display.getCurrent(), this.grayShadesRGB[ctr]));
        TableEditor[] tableEditors = new TableEditor[]{new TableEditor(this.table), new TableEditor(this.table), new TableEditor(this.table)};
        Button chooseColorButton = new Button((Composite)this.table, 0x1000000);
        chooseColorButton.setImage(ResourceManager.getPluginImage("Star", "icons/ellipsis.png"));
        chooseColorButton.setBackground(Display.getCurrent().getSystemColor(1));
        chooseColorButton.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                PieChartDialog.this.table.setSelection(-1);
                try {
                    RGB rgbColor = GraphsUtilities.chooseColor();
                    tableItem.setBackground(1, new Color((Device)Display.getCurrent(), rgbColor));
                    System.out.println("replace color at index" + tableItem.getData("index").toString());
                    int index = Integer.parseInt(tableItem.getData("index").toString());
                    ((PieChartDialog)PieChartDialog.this).sliceColors[index] = GraphsUtilities.convertToRrgbFormat(rgbColor.toString());
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
                int index = Integer.parseInt(tableItem.getData("index").toString());
                ChooseFillPatternDialog fillDlg = new ChooseFillPatternDialog(PieChartDialog.this.getShell(), varName);
                fillDlg.open();
                if (fillDlg.getReturnCode() == 0) {
                    PieChartDialog.this.table.setSelection(-1);
                    try {
                        ((PieChartDialog)PieChartDialog.this).pieLineAngle[index] = fillDlg.getPatternAngle();
                        ((PieChartDialog)PieChartDialog.this).pieDensity[index] = fillDlg.getLineDensity();
                        lblImagePlaceHolder.setImage(ResourceManager.getPluginImage("Star", "icons/" + fillDlg.getChosenPattern() + ".png"));
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
    }

    protected void removeItemsFromVarTable() {
        int item = 0;
        while (item < this.table.getItemCount()) {
            TableEditor[] editors;
            TableItem tableItem = this.table.getItem(item);
            TableEditor[] tableEditorArray = editors = (TableEditor[])tableItem.getData("Editors");
            int n = editors.length;
            int n2 = 0;
            while (n2 < n) {
                TableEditor te = tableEditorArray[n2];
                te.getEditor().dispose();
                ++n2;
            }
            ++item;
        }
        this.table.clearAll();
        this.table.removeAll();
        this.table.pack();
        this.composite_2.pack();
    }

    protected void setFactors() {
        this.varInfo = this.dataManipulationManager.getVarInfoFromFile(this.file.getAbsolutePath());
        this.numericVariables = this.dataManipulationManager.getNumericVars(this.varInfo);
        this.factorVariables = this.dataManipulationManager.getFactorVars(this.varInfo);
    }

    public void enableFactorButtons(boolean state) {
        this.groupsVarList.setSelection(-1);
        this.axisVarList.setSelection(-1);
        this.addGroupsBtn.setEnabled(state);
        this.addAxisBtn.setEnabled(state);
        if (state) {
            this.addGroupsBtn.setText("Add");
            this.addAxisBtn.setText("Add");
            if (this.groupsVarList.getItemCount() > 0) {
                this.addGroupsBtn.setEnabled(false);
            }
            if (this.axisVarList.getItemCount() > 0) {
                this.addAxisBtn.setEnabled(false);
            }
        }
    }

    public void enableNumericButtons(boolean state) {
        if (state && this.lblResponseVariables.getEnabled() && this.responseVarList.getItemCount() < 1) {
            this.addBtn.setEnabled(state);
        } else {
            this.addBtn.setEnabled(false);
        }
        this.moveBtn.setEnabled(state);
    }

    protected void buttonPressed(int buttonId) {
        if (buttonId == 19) {
            System.out.println("graph type: " + this.analysisType);
            PieChartDialog graph = new PieChartDialog(this.parentShell, this.analysisType, this.file);
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
        if (this.axisVarList.getItemCount() < 1) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"Please select a category axis variable.");
        } else if (this.btnRadioButton.getSelection() && this.responseVarList.getItemCount() < 1) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"Please select a weight variable.");
        } else {
            this.cVar = this.axisVarList.getItem(0);
            this.mTitle = this.mainTitleText.getText();
            this.byVar = this.groupsVarList.getItemCount() < 1 ? null : this.groupsVarList.getItem(0);
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
            this.useLabels = this.labelsList[this.combo.getSelectionIndex()];
            boolean continueWithPie = true;
            if (this.responseVarList.getItemCount() > 0) {
                this.wVar = this.responseVarList.getItems()[0];
                String[] levelsWithOneValue = ProjectExplorerView.rJavaManager.getRJavaDataManipulationManager().checkLevelsWithMoreThanOneDatum(this.file.getAbsolutePath().replaceAll("\\\\", "/"), this.cVar, this.wVar, this.byVar);
                if (levelsWithOneValue[0].equals("FALSE")) {
                    MessageDialog.openError((Shell)this.getShell(), (String)"Invalid Input", (String)"There are category level(s) with more than one frequency.");
                    continueWithPie = false;
                } else if (!levelsWithOneValue[0].equals("FALSE") && !levelsWithOneValue[0].equals("TRUE")) {
                    StringBuilder sb = new StringBuilder();
                    String[] stringArray = levelsWithOneValue;
                    int n = levelsWithOneValue.length;
                    int n2 = 0;
                    while (n2 < n) {
                        String s = stringArray[n2];
                        sb.append(s);
                        sb.append("\n");
                        ++n2;
                    }
                    MessageDialog.openError((Shell)this.getShell(), (String)"Invalid Input", (String)("There are category level(s) with more than one frequency:\n" + sb.toString()));
                    continueWithPie = false;
                }
            } else {
                this.wVar = null;
            }
            if (continueWithPie) {
                String dataFileName = this.file.getAbsolutePath().replaceAll("\\\\", "/");
                File outputFolder = GraphsUtilities.createOutputFolder(this.file.getName(), this.analysisType);
                if (!outputFolder.exists()) {
                    outputFolder.mkdir();
                }
                OperationProgressDialog rInfo = new OperationProgressDialog(this.getShell(), "Creating Pie Chart");
                rInfo.open();
                ProjectExplorerView.rJavaManager.getRJavaGraphManager().createGraphPie(String.valueOf(outputFolder.getAbsolutePath().replaceAll("\\\\", "/")) + "/", dataFileName, this.cVar, this.mTitle, this.wVar, this.byVar, this.sliceColors, this.useLabels, this.showLeg, this.legPos, this.legTitle, this.legCol, this.pieDensity, this.pieLineAngle, this.boxed, this.multGraphs, this.numRowsGraphs, this.numColsGraphs, this.orientGraphs);
                rInfo.close();
                WindowDialogControlUtil.hideAllWindowDialog();
                GraphsUtilities.openFolder(outputFolder);
            }
        }
    }

    protected Point getInitialSize() {
        return new Point(703, 609);
    }

    protected boolean isResizable() {
        return true;
    }
}

