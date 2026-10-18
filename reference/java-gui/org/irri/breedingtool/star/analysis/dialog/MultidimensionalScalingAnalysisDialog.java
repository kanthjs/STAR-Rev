/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.jface.dialogs.Dialog
 *  org.eclipse.jface.dialogs.IDialogConstants
 *  org.eclipse.jface.dialogs.MessageDialog
 *  org.eclipse.swt.custom.CCombo
 *  org.eclipse.swt.events.MouseAdapter
 *  org.eclipse.swt.events.MouseEvent
 *  org.eclipse.swt.events.MouseListener
 *  org.eclipse.swt.events.SelectionAdapter
 *  org.eclipse.swt.events.SelectionEvent
 *  org.eclipse.swt.events.SelectionListener
 *  org.eclipse.swt.graphics.Point
 *  org.eclipse.swt.layout.FillLayout
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
 *  org.eclipse.swt.widgets.Spinner
 *  org.eclipse.swt.widgets.TabFolder
 *  org.eclipse.swt.widgets.TabItem
 *  org.eclipse.swt.widgets.Table
 *  org.eclipse.swt.widgets.TableColumn
 *  org.eclipse.swt.widgets.TableItem
 *  org.eclipse.swt.widgets.Text
 */
package org.irri.breedingtool.star.analysis.dialog;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
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
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;
import org.irri.breedingtool.application.handler.PartStackHandler;
import org.irri.breedingtool.datamanipulation.dialog.OperationProgressDialog;
import org.irri.breedingtool.graphs.managers.GraphTableManager;
import org.irri.breedingtool.graphs.managers.RowEntityModel;
import org.irri.breedingtool.manager.impl.DataManipulationManager;
import org.irri.breedingtool.projectexplorer.view.ProjectExplorerView;
import org.irri.breedingtool.utility.DialogFormUtility;
import org.irri.breedingtool.utility.GraphsUtilities;
import org.irri.breedingtool.utility.StarAnalysisUtilities;

public class MultidimensionalScalingAnalysisDialog
extends Dialog {
    private Button btnOk;
    private Button btnAddNumeric;
    private List lstNumericVars;
    private List lstVars;
    private String filePath = PartStackHandler.getActiveElementID();
    private DataManipulationManager dataManipulationManager = new DataManipulationManager();
    private DialogFormUtility listManager = new DialogFormUtility();
    private ArrayList<String> tableData = new ArrayList();
    private TabItem tbtmNewItem_1;
    private Composite composite_2;
    private Group group_9;
    private Button btnCorrelationMatrix;
    private Button btnDescriptive;
    private Group grpPlot;
    private Table table;
    private TableItem tableItem;
    private TableItem tableItem_1;
    private TableColumn tblclmnNewColumn;
    private TableColumn tblclmnNewColumn_1;
    private TableColumn tblclmnNewColumn_2;
    private TableColumn tblclmnSize;
    private Button btnUseIDVar;
    private Label lblFormatPoints;
    private Composite composite_1;
    private Button btnShowLegend;
    private Label lblPosition;
    private CCombo cmbPosition;
    private Label lblNoOfColumns;
    private Spinner spinColumns;
    private Label lblTitle;
    private Text txtTitle;
    private Label lblFactors;
    private List lstFactors;
    private Button btnMove;
    private Button btnAddFactor;
    private Text txtIDVar;
    private Label lblIdVar;
    private Label label_3;
    private Button btnClassical;
    private Button btnNonMetric;
    private Label lblDistanceMeasure;
    private Combo cmbDistance;
    private Label label_4;
    private Spinner spinDimensions;
    private Label lblNewLabel;
    private TabItem tbtmVariableDescription;
    private int CURRENT_COLOR = 0;
    private GraphTableManager tableManager;
    private String legPos = "bottom-right";
    private String legTitle = null;
    private int legNcol = 1;
    private String[] pCol = new String[]{"rgb(255,0,0,max = 255)", "rgb(0,255,0,max = 255)", "rgb(0,0,255,max = 255)", "rgb(0,255,255,max = 255)", "rgb(127,127,127,max = 255)"};
    private int[] pChars = new int[]{1, 2, 3, 4, 5};
    private Double[] pSizes = new Double[]{1.0, 1.0, 1.0, 1.0, 1.0};
    private Label lblInputType;
    private Button btnRawData;
    private Button btnDistanceMatrix;

    public MultidimensionalScalingAnalysisDialog(Shell parentShell) {
        super(parentShell);
        this.setShellStyle(2288);
    }

    protected void configureShell(Shell shell) {
        super.configureShell(shell);
        shell.setText("Multidimensional Scaling: " + new File(this.filePath).getName());
    }

    protected Control createDialogArea(Composite parent) {
        Composite container = (Composite)super.createDialogArea(parent);
        FillLayout fl_container = new FillLayout(256);
        fl_container.marginHeight = 8;
        fl_container.marginWidth = 8;
        container.setLayout((Layout)fl_container);
        TabFolder tabFolder = new TabFolder(container, 0);
        this.tbtmVariableDescription = new TabItem(tabFolder, 0);
        this.tbtmVariableDescription.setText("Variable Description");
        Composite composite = new Composite((Composite)tabFolder, 0);
        this.tbtmVariableDescription.setControl((Control)composite);
        composite.setLayout((Layout)new GridLayout(6, false));
        this.lblInputType = new Label(composite, 0);
        this.lblInputType.setText("Input Type:");
        new Label(composite, 0);
        this.btnRawData = new Button(composite, 16);
        this.btnRawData.setSelection(true);
        this.btnRawData.setText("Raw Data");
        this.btnDistanceMatrix = new Button(composite, 16);
        this.btnDistanceMatrix.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (MultidimensionalScalingAnalysisDialog.this.btnDistanceMatrix.getSelection()) {
                    String[] catVar;
                    MultidimensionalScalingAnalysisDialog.this.cmbDistance.setEnabled(false);
                    MultidimensionalScalingAnalysisDialog.this.lblDistanceMeasure.setEnabled(false);
                    MultidimensionalScalingAnalysisDialog.this.btnDescriptive.setEnabled(false);
                    MultidimensionalScalingAnalysisDialog.this.btnCorrelationMatrix.setEnabled(false);
                    MultidimensionalScalingAnalysisDialog.this.lstVars.setItems(MultidimensionalScalingAnalysisDialog.this.lstNumericVars.getItems());
                    try {
                        BufferedReader br = new BufferedReader(new FileReader(MultidimensionalScalingAnalysisDialog.this.filePath));
                        String line = br.readLine();
                        String[] columns = line.split(",");
                        String fcol = columns[0];
                        MultidimensionalScalingAnalysisDialog.this.txtIDVar.setText(fcol);
                    }
                    catch (FileNotFoundException e1) {
                        e1.printStackTrace();
                    }
                    catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    String[] stringArray = catVar = DataManipulationManager.getEnvtLevels(MultidimensionalScalingAnalysisDialog.this.txtIDVar.getText(), new File(MultidimensionalScalingAnalysisDialog.this.filePath));
                    int n = catVar.length;
                    int n2 = 0;
                    while (n2 < n) {
                        String var = stringArray[n2];
                        MultidimensionalScalingAnalysisDialog.this.addDataToTable(var);
                        ++n2;
                    }
                    MultidimensionalScalingAnalysisDialog.this.lstNumericVars.removeAll();
                    MultidimensionalScalingAnalysisDialog.this.lstFactors.removeAll();
                    MultidimensionalScalingAnalysisDialog.this.lstVars.removeAll();
                    MultidimensionalScalingAnalysisDialog.this.txtIDVar.setText("");
                    MultidimensionalScalingAnalysisDialog.this.lstNumericVars.setEnabled(false);
                    MultidimensionalScalingAnalysisDialog.this.lstFactors.setEnabled(false);
                    MultidimensionalScalingAnalysisDialog.this.lstVars.setEnabled(false);
                    MultidimensionalScalingAnalysisDialog.this.txtIDVar.setEnabled(false);
                } else {
                    MultidimensionalScalingAnalysisDialog.this.cmbDistance.setEnabled(true);
                    MultidimensionalScalingAnalysisDialog.this.lblDistanceMeasure.setEnabled(true);
                    MultidimensionalScalingAnalysisDialog.this.btnClassical.setSelection(true);
                    MultidimensionalScalingAnalysisDialog.this.btnNonMetric.setSelection(false);
                    MultidimensionalScalingAnalysisDialog.this.cmbDistance.setText("Euclidean");
                    MultidimensionalScalingAnalysisDialog.this.btnDescriptive.setSelection(false);
                    MultidimensionalScalingAnalysisDialog.this.btnCorrelationMatrix.setSelection(false);
                    MultidimensionalScalingAnalysisDialog.this.btnDescriptive.setEnabled(true);
                    MultidimensionalScalingAnalysisDialog.this.btnCorrelationMatrix.setEnabled(true);
                    MultidimensionalScalingAnalysisDialog.this.spinDimensions.setSelection(2);
                    MultidimensionalScalingAnalysisDialog.this.btnShowLegend.setEnabled(true);
                    MultidimensionalScalingAnalysisDialog.this.btnShowLegend.setSelection(true);
                    MultidimensionalScalingAnalysisDialog.this.btnUseIDVar.setSelection(false);
                    MultidimensionalScalingAnalysisDialog.this.legendOptions(true);
                    MultidimensionalScalingAnalysisDialog.this.txtTitle.setText("Legend:");
                    MultidimensionalScalingAnalysisDialog.this.cmbPosition.setText("bottom-right");
                    MultidimensionalScalingAnalysisDialog.this.spinColumns.setSelection(1);
                    MultidimensionalScalingAnalysisDialog.this.lstNumericVars.removeAll();
                    MultidimensionalScalingAnalysisDialog.this.lstFactors.removeAll();
                    MultidimensionalScalingAnalysisDialog.this.lstVars.removeAll();
                    MultidimensionalScalingAnalysisDialog.this.txtIDVar.setText("");
                    MultidimensionalScalingAnalysisDialog.this.tableManager.removeAll();
                    MultidimensionalScalingAnalysisDialog.this.listManager.initializeNumericList(MultidimensionalScalingAnalysisDialog.this.lstNumericVars, MultidimensionalScalingAnalysisDialog.this.filePath);
                    MultidimensionalScalingAnalysisDialog.this.listManager.initializeFactorList(MultidimensionalScalingAnalysisDialog.this.lstFactors, MultidimensionalScalingAnalysisDialog.this.filePath);
                    MultidimensionalScalingAnalysisDialog.this.lstNumericVars.setEnabled(true);
                    MultidimensionalScalingAnalysisDialog.this.lstFactors.setEnabled(true);
                    MultidimensionalScalingAnalysisDialog.this.lstVars.setEnabled(true);
                    MultidimensionalScalingAnalysisDialog.this.txtIDVar.setEnabled(true);
                }
            }
        });
        this.btnDistanceMatrix.setLayoutData((Object)new GridData(16384, 0x1000000, false, false, 2, 1));
        this.btnDistanceMatrix.setText("Distance Matrix");
        new Label(composite, 0);
        Label lblAvailableData = new Label(composite, 0);
        GridData gd_lblAvailableData = new GridData(4, 0x1000000, false, false, 2, 1);
        gd_lblAvailableData.widthHint = 102;
        lblAvailableData.setLayoutData((Object)gd_lblAvailableData);
        lblAvailableData.setText("Numeric Variables:");
        new Label(composite, 0);
        new Label(composite, 0);
        Label lblDependentVariables = new Label(composite, 0);
        lblDependentVariables.setText("Variables:");
        new Label(composite, 0);
        this.lstNumericVars = new List(composite, 2818);
        GridData gd_lstNumericVars = new GridData(4, 4, false, true, 3, 1);
        gd_lstNumericVars.heightHint = 140;
        gd_lstNumericVars.widthHint = 155;
        this.lstNumericVars.setLayoutData((Object)gd_lstNumericVars);
        this.btnAddNumeric = new Button(composite, 0);
        GridData gd_btnAddNumeric = new GridData(4, 0x1000000, false, true, 1, 1);
        gd_btnAddNumeric.widthHint = 90;
        this.btnAddNumeric.setLayoutData((Object)gd_btnAddNumeric);
        this.btnAddNumeric.setText("Add");
        this.lstVars = new List(composite, 2818);
        GridData gd_lstVars = new GridData(4, 4, false, true, 2, 1);
        gd_lstVars.heightHint = 140;
        gd_lstVars.widthHint = 155;
        this.lstVars.setLayoutData((Object)gd_lstVars);
        this.lblFactors = new Label(composite, 0);
        this.lblFactors.setText("Factors:");
        new Label(composite, 0);
        this.btnMove = new Button(composite, 0);
        GridData gd_btnMove = new GridData(4, 0x1000000, true, false, 1, 1);
        gd_btnMove.widthHint = 90;
        this.btnMove.setLayoutData((Object)gd_btnMove);
        this.btnMove.setText("Set to Factor");
        new Label(composite, 0);
        this.lblIdVar = new Label(composite, 0);
        this.lblIdVar.setText("ID variable:");
        new Label(composite, 0);
        this.lstFactors = new List(composite, 2818);
        GridData gd_lstFactors = new GridData(4, 4, false, true, 3, 1);
        gd_lstFactors.heightHint = 140;
        gd_lstFactors.widthHint = 155;
        this.lstFactors.setLayoutData((Object)gd_lstFactors);
        this.btnAddFactor = new Button(composite, 0);
        this.btnAddFactor.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (MultidimensionalScalingAnalysisDialog.this.lstFactors.getSelectionCount() > 0) {
                    String[] catVar;
                    String[] selectedVar = MultidimensionalScalingAnalysisDialog.this.lstFactors.getSelection();
                    MultidimensionalScalingAnalysisDialog.this.txtIDVar.setText(selectedVar[0]);
                    String[] stringArray = catVar = DataManipulationManager.getEnvtLevels(MultidimensionalScalingAnalysisDialog.this.txtIDVar.getText(), new File(MultidimensionalScalingAnalysisDialog.this.filePath));
                    int n = catVar.length;
                    int n2 = 0;
                    while (n2 < n) {
                        String var = stringArray[n2];
                        MultidimensionalScalingAnalysisDialog.this.addDataToTable(var);
                        ++n2;
                    }
                } else {
                    MultidimensionalScalingAnalysisDialog.this.tableManager.removeAll();
                }
            }
        });
        GridData gd_btnAddFactor = new GridData(4, 128, false, true, 1, 1);
        gd_btnAddFactor.widthHint = 90;
        this.btnAddFactor.setLayoutData((Object)gd_btnAddFactor);
        this.btnAddFactor.setText("Add");
        this.txtIDVar = new Text(composite, 2048);
        this.txtIDVar.addMouseListener((MouseListener)new MouseAdapter(){

            public void mouseDoubleClick(MouseEvent e) {
                MultidimensionalScalingAnalysisDialog.this.tableManager.removeAll();
            }
        });
        this.txtIDVar.setBackground(SWTResourceManager.getColor(1));
        this.txtIDVar.setEditable(false);
        GridData gd_txtIDVar = new GridData(4, 128, true, true, 2, 1);
        gd_txtIDVar.widthHint = 155;
        this.txtIDVar.setLayoutData((Object)gd_txtIDVar);
        this.tbtmNewItem_1 = new TabItem(tabFolder, 0);
        this.tbtmNewItem_1.setText("Options");
        this.composite_2 = new Composite((Composite)tabFolder, 0);
        this.tbtmNewItem_1.setControl((Control)this.composite_2);
        this.composite_2.setLayout((Layout)new GridLayout(5, false));
        this.label_3 = new Label(this.composite_2, 0);
        this.label_3.setLayoutData((Object)new GridData(16384, 0x1000000, true, false, 1, 1));
        this.label_3.setText("Type:");
        this.btnClassical = new Button(this.composite_2, 16);
        this.btnClassical.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (MultidimensionalScalingAnalysisDialog.this.btnClassical.getSelection()) {
                    MultidimensionalScalingAnalysisDialog.this.cmbDistance.removeAll();
                    MultidimensionalScalingAnalysisDialog.this.cmbDistance.setItems(new String[]{"Euclidean", "Maximum", "Manhattan", "Canberra", "Minkowski"});
                    MultidimensionalScalingAnalysisDialog.this.cmbDistance.select(0);
                }
            }
        });
        this.btnClassical.setLayoutData((Object)new GridData(16384, 0x1000000, true, false, 1, 1));
        this.btnClassical.setText("Classical");
        this.btnClassical.setSelection(true);
        this.lblNewLabel = new Label(this.composite_2, 0);
        this.btnNonMetric = new Button(this.composite_2, 16);
        this.btnNonMetric.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (MultidimensionalScalingAnalysisDialog.this.btnNonMetric.getSelection()) {
                    MultidimensionalScalingAnalysisDialog.this.cmbDistance.removeAll();
                    MultidimensionalScalingAnalysisDialog.this.cmbDistance.setItems(new String[]{"Bray", "Euclidean", "Maximum", "Manhattan", "Canberra", "Minkowski"});
                    MultidimensionalScalingAnalysisDialog.this.cmbDistance.select(1);
                }
            }
        });
        this.btnNonMetric.setLayoutData((Object)new GridData(16384, 0x1000000, true, false, 1, 1));
        this.btnNonMetric.setText("Nonmetric");
        new Label(this.composite_2, 0);
        this.lblDistanceMeasure = new Label(this.composite_2, 0);
        this.lblDistanceMeasure.setLayoutData((Object)new GridData(16384, 0x1000000, true, false, 1, 1));
        this.lblDistanceMeasure.setText("Distance Measure:");
        this.cmbDistance = new Combo(this.composite_2, 0);
        GridData gd_cmbDistance = new GridData(4, 0x1000000, true, false, 1, 1);
        gd_cmbDistance.widthHint = 93;
        this.cmbDistance.setLayoutData((Object)gd_cmbDistance);
        this.cmbDistance.setItems(new String[]{"Euclidean", "Maximum", "Manhattan", "Canberra", "Minkowski"});
        this.cmbDistance.select(0);
        new Label(this.composite_2, 0);
        this.label_4 = new Label(this.composite_2, 0);
        this.label_4.setLayoutData((Object)new GridData(16384, 0x1000000, true, false, 1, 1));
        this.label_4.setText("Number of dimensions:");
        this.spinDimensions = new Spinner(this.composite_2, 2056);
        this.spinDimensions.setBackground(SWTResourceManager.getColor(1));
        this.spinDimensions.setLayoutData((Object)new GridData(4, 0x1000000, true, false, 1, 1));
        this.spinDimensions.setMaximum(5);
        this.spinDimensions.setMinimum(2);
        this.group_9 = new Group(this.composite_2, 0);
        GridData gd_group_9 = new GridData(4, 4, true, false, 2, 1);
        gd_group_9.widthHint = 214;
        this.group_9.setLayoutData((Object)gd_group_9);
        this.group_9.setText("Display");
        this.group_9.setLayout((Layout)new GridLayout(1, false));
        this.btnDescriptive = new Button((Composite)this.group_9, 32);
        this.btnDescriptive.setLayoutData((Object)new GridData(0x1000000, 0x1000000, false, true, 1, 1));
        this.btnDescriptive.setText("Descriptive Statistics");
        this.btnCorrelationMatrix = new Button((Composite)this.group_9, 32);
        this.btnCorrelationMatrix.setLayoutData((Object)new GridData(16384, 0x1000000, false, true, 1, 1));
        this.btnCorrelationMatrix.setText("Correlation Matrix");
        new Label(this.composite_2, 0);
        new Label(this.composite_2, 0);
        new Label(this.composite_2, 0);
        this.grpPlot = new Group(this.composite_2, 0);
        this.grpPlot.setLayout((Layout)new GridLayout(6, false));
        GridData gd_grpPlot = new GridData(4, 4, true, true, 5, 1);
        gd_grpPlot.heightHint = 233;
        gd_grpPlot.widthHint = 392;
        this.grpPlot.setLayoutData((Object)gd_grpPlot);
        this.grpPlot.setText("Plot");
        this.composite_1 = new Composite((Composite)this.grpPlot, 2048);
        GridData gd_composite_1 = new GridData(4, 4, true, true, 6, 1);
        gd_composite_1.widthHint = 438;
        this.composite_1.setLayoutData((Object)gd_composite_1);
        this.composite_1.setLayout((Layout)new GridLayout(4, false));
        this.lblFormatPoints = new Label(this.composite_1, 0);
        this.lblFormatPoints.setLayoutData((Object)new GridData(16384, 0x1000000, false, true, 1, 1));
        this.lblFormatPoints.setText("Format points");
        new Label(this.composite_1, 0);
        new Label(this.composite_1, 0);
        new Label(this.composite_1, 0);
        this.table = new Table(this.composite_1, 67584);
        GridData gd_table = new GridData(4, 4, true, true, 4, 1);
        gd_table.heightHint = 150;
        gd_table.widthHint = 436;
        this.table.setLayoutData((Object)gd_table);
        this.table.setHeaderVisible(true);
        this.table.setLinesVisible(true);
        this.tblclmnNewColumn = new TableColumn(this.table, 0);
        this.tblclmnNewColumn.setWidth(109);
        this.tblclmnNewColumn.setText("Levels");
        this.tblclmnNewColumn_2 = new TableColumn(this.table, 0);
        this.tblclmnNewColumn_2.setResizable(false);
        this.tblclmnNewColumn_2.setWidth(78);
        this.tblclmnNewColumn_2.setText("Color");
        this.tblclmnSize = new TableColumn(this.table, 0);
        this.tblclmnSize.setWidth(44);
        this.tblclmnSize.setText("Size");
        this.tblclmnNewColumn_1 = new TableColumn(this.table, 0);
        this.tblclmnNewColumn_1.setResizable(false);
        this.tblclmnNewColumn_1.setWidth(75);
        this.tblclmnNewColumn_1.setText("Symbol");
        new Label(this.composite_1, 0);
        new Label(this.composite_1, 0);
        new Label(this.composite_1, 0);
        this.btnUseIDVar = new Button(this.composite_1, 32);
        this.btnUseIDVar.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                MultidimensionalScalingAnalysisDialog.this.tableManager.setColumnsDisabled(!MultidimensionalScalingAnalysisDialog.this.btnUseIDVar.getSelection(), 3);
                if (MultidimensionalScalingAnalysisDialog.this.btnUseIDVar.getSelection()) {
                    MultidimensionalScalingAnalysisDialog.this.btnShowLegend.setEnabled(false);
                    MultidimensionalScalingAnalysisDialog.this.btnShowLegend.setSelection(false);
                    MultidimensionalScalingAnalysisDialog.this.legendOptions(false);
                } else {
                    MultidimensionalScalingAnalysisDialog.this.btnShowLegend.setEnabled(true);
                    MultidimensionalScalingAnalysisDialog.this.btnShowLegend.setSelection(true);
                    MultidimensionalScalingAnalysisDialog.this.legendOptions(true);
                }
            }
        });
        this.btnUseIDVar.setLayoutData((Object)new GridData(131072, 0x1000000, true, true, 1, 1));
        this.btnUseIDVar.setText("Use ID variable");
        this.btnShowLegend = new Button((Composite)this.grpPlot, 32);
        this.btnShowLegend.setSelection(true);
        this.btnShowLegend.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (MultidimensionalScalingAnalysisDialog.this.btnShowLegend.getSelection()) {
                    MultidimensionalScalingAnalysisDialog.this.legendOptions(true);
                } else {
                    MultidimensionalScalingAnalysisDialog.this.legendOptions(false);
                }
            }
        });
        this.btnShowLegend.setLayoutData((Object)new GridData(16384, 0x1000000, false, false, 3, 1));
        this.btnShowLegend.setText("Show legend");
        new Label((Composite)this.grpPlot, 0);
        new Label((Composite)this.grpPlot, 0);
        new Label((Composite)this.grpPlot, 0);
        new Label((Composite)this.grpPlot, 0);
        this.lblPosition = new Label((Composite)this.grpPlot, 0);
        this.lblPosition.setText("Position:");
        this.cmbPosition = new CCombo((Composite)this.grpPlot, 2048);
        this.cmbPosition.setLayoutData((Object)new GridData(4, 0x1000000, false, false, 1, 1));
        this.cmbPosition.setItems(new String[]{"bottom-right", "bottom", "bottom-left", "left", "top-left", "top", "top-right", "right", "center"});
        this.cmbPosition.setEditable(false);
        this.cmbPosition.select(0);
        this.cmbPosition.setBackground(SWTResourceManager.getColor(1));
        new Label((Composite)this.grpPlot, 0);
        this.lblNoOfColumns = new Label((Composite)this.grpPlot, 0);
        this.lblNoOfColumns.setText("No. of Columns:");
        this.spinColumns = new Spinner((Composite)this.grpPlot, 2048);
        this.spinColumns.setLayoutData((Object)new GridData(16384, 0x1000000, true, false, 1, 1));
        this.spinColumns.setMaximum(5);
        this.spinColumns.setMinimum(1);
        this.spinColumns.setSelection(1);
        new Label((Composite)this.grpPlot, 0);
        this.lblTitle = new Label((Composite)this.grpPlot, 0);
        this.lblTitle.setText("Title:");
        this.txtTitle = new Text((Composite)this.grpPlot, 2048);
        this.txtTitle.setText("Legend:");
        this.txtTitle.setBackground(SWTResourceManager.getColor(1));
        GridData gd_txtTitle = new GridData(4, 0x1000000, true, false, 4, 1);
        gd_txtTitle.widthHint = 245;
        this.txtTitle.setLayoutData((Object)gd_txtTitle);
        this.initializeContent();
        return container;
    }

    void initializeContent() {
        this.listManager.initializeSingleButtonList(this.lstNumericVars, this.lstVars, this.btnMove, this.btnAddNumeric);
        this.listManager.initializeSingleSelectionList(this.lstFactors, this.txtIDVar, this.btnMove, this.btnAddFactor);
        this.listManager.initializeNumericList(this.lstNumericVars, this.filePath);
        this.listManager.initializeFactorList(this.lstFactors, this.filePath);
        this.listManager.initializeVariableMoveList(this.lstNumericVars, this.lstFactors, this.btnMove, this.filePath);
        ArrayList<Integer> headers = new ArrayList<Integer>();
        headers.add(0);
        headers.add(2);
        headers.add(5);
        headers.add(7);
        this.tableManager = new GraphTableManager(this.table, headers);
        this.tableManager.ROW_HEIGHT = 28;
        this.tableManager.SYMBOL_HEIGHT = 25;
    }

    protected void addDataToTable(String selectedNumVars) {
        GraphsUtilities.getGrayShades(this.table.getItemCount() + 1);
        this.tableManager.addItem(new Object[]{selectedNumVars, GraphsUtilities.getRandomColor(), new RowEntityModel(5, (Object)10, 10, -1, 1), "0"});
        this.getShell().setSize(this.getShell().getSize().x - 1, this.getShell().getSize().y);
        this.getShell().setSize(this.getShell().getSize().x + 1, this.getShell().getSize().y);
    }

    protected void legendOptions(boolean state) {
        this.lblPosition.setEnabled(state);
        this.lblTitle.setEnabled(state);
        this.lblNoOfColumns.setEnabled(state);
        this.cmbPosition.setEnabled(state);
        this.txtTitle.setEnabled(state);
        this.spinColumns.setEnabled(state);
    }

    protected void buttonPressed(int buttonID) {
        if (buttonID == 0) {
            this.okPressed();
        } else if (buttonID == 8) {
            this.btnClassical.setSelection(true);
            this.btnNonMetric.setSelection(false);
            this.cmbDistance.setText("Euclidean");
            this.btnDescriptive.setSelection(false);
            this.btnCorrelationMatrix.setSelection(false);
            this.btnDescriptive.setEnabled(true);
            this.btnCorrelationMatrix.setEnabled(true);
            this.spinDimensions.setSelection(2);
            this.btnShowLegend.setEnabled(true);
            this.btnShowLegend.setSelection(true);
            this.btnUseIDVar.setSelection(false);
            this.legendOptions(true);
            this.txtTitle.setText("Legend:");
            this.cmbPosition.setText("bottom-right");
            this.spinColumns.setSelection(1);
            this.lstNumericVars.removeAll();
            this.lstFactors.removeAll();
            this.lstVars.removeAll();
            this.txtIDVar.setText("");
            this.tableManager.removeAll();
            this.listManager.initializeNumericList(this.lstNumericVars, this.filePath);
            this.listManager.initializeFactorList(this.lstFactors, this.filePath);
            this.lstNumericVars.setEnabled(true);
            this.lstFactors.setEnabled(true);
            this.lstVars.setEnabled(true);
            this.txtIDVar.setEnabled(true);
            this.btnDistanceMatrix.setSelection(false);
            this.btnRawData.setSelection(true);
            this.cmbDistance.setEnabled(true);
            this.lblDistanceMeasure.setEnabled(true);
        } else {
            this.close();
        }
    }

    protected void okPressed() {
        String outputFolder = StarAnalysisUtilities.createOutputFolder(this.filePath, "MDS");
        String dataFileName = this.filePath.replace(File.separator, "/");
        String outputPath = outputFolder;
        String inputType = "raw";
        String[] vars = this.lstVars.getItems();
        String idVar = this.txtIDVar.getText();
        if (this.btnDistanceMatrix.getSelection()) {
            inputType = "distMat";
        } else {
            inputType = "raw";
            if (this.lstVars.getItemCount() < 2) {
                MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"Please add at least two variables.");
                return;
            }
            if (this.txtIDVar.getText().equals("")) {
                MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"ID Variable list box should not be empty.");
                return;
            }
            try {
                if (this.spinDimensions.getSelection() > this.countLines(this.filePath) - 1 - 1) {
                    MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"The number of dimensions should be less than the number of rows of the input file.");
                    return;
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        String type = "Classical";
        String distClass = this.cmbDistance.getText();
        String distNonmet = this.cmbDistance.getText();
        type = this.btnNonMetric.getSelection() ? "Nonmetric" : "Classical";
        Integer dimnum = this.spinDimensions.getSelection();
        if (this.table.getItemCount() > 0) {
            this.pCol = new String[this.table.getItemCount()];
            this.pSizes = new Double[this.table.getItemCount()];
            this.pChars = new int[this.table.getItemCount()];
        }
        ArrayList<String> arrpCol = new ArrayList<String>();
        ArrayList<Double> arrpSizes = new ArrayList<Double>();
        ArrayList<Integer> arrpChars = new ArrayList<Integer>();
        for (String[] data : this.tableManager.getDataToString()) {
            arrpCol.add(GraphsUtilities.convertToRrgbFormat(data[1]));
            arrpSizes.add(Double.parseDouble(data[2]));
            arrpChars.add(Integer.parseInt(data[3]));
            System.out.println(String.valueOf(data[1]) + " " + data[2] + " " + data[3]);
        }
        if (!arrpCol.isEmpty()) {
            this.pCol = arrpCol.toArray(new String[arrpCol.size()]);
        }
        if (!arrpSizes.isEmpty()) {
            this.pSizes = arrpSizes.toArray(new Double[arrpSizes.size()]);
        }
        if (!arrpChars.isEmpty()) {
            this.pChars = this.convertToPrimitiveInt(arrpChars.toArray(new Integer[arrpChars.size()]));
        }
        boolean useIdVar = this.btnUseIDVar.getSelection();
        boolean showLeg = false;
        boolean descriptive = this.btnDescriptive.getSelection();
        boolean correlate = this.btnCorrelationMatrix.getSelection();
        if (this.btnShowLegend.getSelection()) {
            showLeg = true;
            this.legPos = this.cmbPosition.getText().replaceAll("-", "");
            this.legTitle = !this.txtTitle.getText().isEmpty() ? this.txtTitle.getText() : "";
            this.legNcol = this.spinColumns.getSelection();
        }
        System.out.println(type);
        OperationProgressDialog rInfo = new OperationProgressDialog(this.getShell(), "Performing Analysis");
        rInfo.open();
        this.btnOk.setEnabled(false);
        ProjectExplorerView.rJavaManager.getSTARAnalysisManager().doMDS(dataFileName, outputPath.replace(File.separator, "/"), inputType, vars, idVar, type, distClass, distNonmet, dimnum.intValue(), useIdVar, this.pChars, this.convertToPrimitiveDouble(this.pSizes), this.pCol, showLeg, this.legTitle, this.legPos, this.legNcol, descriptive, correlate);
        this.getShell().setMinimized(true);
        StarAnalysisUtilities.openAndRefreshFolder(outputFolder);
        this.btnOk.setEnabled(true);
        rInfo.close();
    }

    int[] convertToPrimitiveInt(Integer[] val) {
        int[] returnVal = new int[val.length];
        int i = 0;
        while (i < val.length) {
            returnVal[i] = val[i];
            ++i;
        }
        return returnVal;
    }

    double[] convertToPrimitiveDouble(Double[] val) {
        double[] returnVal = new double[val.length];
        int i = 0;
        while (i < val.length) {
            returnVal[i] = val[i];
            ++i;
        }
        return returnVal;
    }

    public int countLines(String filename) throws IOException {
        LineNumberReader reader = new LineNumberReader(new FileReader(filename));
        int cnt = 0;
        while (reader.readLine() != null) {
        }
        cnt = reader.getLineNumber();
        reader.close();
        return cnt;
    }

    protected void createButtonsForButtonBar(Composite parent) {
        this.createButton(parent, 8, "Reset", false);
        this.btnOk = this.createButton(parent, 0, IDialogConstants.OK_LABEL, true);
        this.createButton(parent, 1, IDialogConstants.CANCEL_LABEL, false);
    }

    protected Point getInitialSize() {
        return new Point(529, 551);
    }
}

