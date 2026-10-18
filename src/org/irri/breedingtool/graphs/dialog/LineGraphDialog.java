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
 *  org.eclipse.swt.graphics.Image
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
import org.eclipse.swt.graphics.Image;
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
import org.irri.breedingtool.utility.WindowDialogControlUtil;

public class LineGraphDialog
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
    private String[] yVars = null;
    private String xVar = null;
    private String mTitle = null;
    private String[] lineVars = null;
    private String[] yAxisLab = null;
    private String xAxisLab = null;
    private String[] yMinValue = null;
    private String[] yMaxValue = null;
    private int axisLabelStyle = 1;
    private String byVar = null;
    private String errBars = "FALSE";
    private String typeErrBar = null;
    private int errMult = 1;
    private double confLev = 0.95;
    private String showLineLabels = "FALSE";
    private String showLeg = "FALSE";
    private String legPos = "bottomright";
    private String legTitle = null;
    private int legCol = 1;
    private String boxed = "FALSE";
    private String multGraphs = "FALSE";
    int numRowsGraphs = 1;
    int numColsGraphs = 1;
    String orientGraphs = "top-bottom";
    private String[] plotCol = new String[]{"rgb(255,0,255, max = 255)", "rgb(0,255,0, max = 255)", "rgb(255,0,0, max = 255)"};
    private String[] linePtTypes = new String[]{"l", "l", "l"};
    private int[] lineTypes = new int[]{1, 2, 3};
    private double[] lineWidths = new double[]{1.0, 1.0, 1.0};
    private String[] pointChars = new String[]{"3", "NA", "2"};
    private double[] pointCharSizes = new double[]{1.0, 1.0, 1.0};
    private String analysisType;
    private Button addAxisBtn;
    private Button addGroupsBtn;
    private List axisVarList;
    private List groupsVarList;
    private Text mainTitleText;
    private Text categoryAxisText;
    private Text legTitleText;
    private Table table;
    private TabItem tbtmDisplay;
    private Button btnDrawBoxAround;
    private Button btnShowLegend;
    private Button btnDisplayMultipleGraphs;
    private CCombo legPositionComo;
    private Spinner numRowsSpinner;
    private Spinner numColsSpinner;
    private CCombo orientGraphsCombo;
    private Button confidenceLvlBtn;
    private Spinner confidenceLvlValue;
    private Button stdErrorBtn;
    private Spinner stdErrorValue;
    private Button displayErrorBarsBtn;
    private Label errorBarsLabel;
    private Label lblLevel;
    private Label lblMultiplier;
    private Label lblPosition;
    private Label lblTitle;
    private Label lblNewLabel;
    private Label lblOrientation;
    private Label lblNumberOfColumns;
    private Composite composite;
    private Button btnDisplaylinelabels;
    private Shell parentShell;
    private Image plotImage;
    private List lstCreateLines;
    private Label lblCreateLinesBy;
    private Button btnCreateLines;
    private Table tableAxis;
    private TableColumn tableColumn;
    private TableColumn tableColumn_5;
    private TableColumn tableColumn_6;
    private TableColumn tableColumn_8;
    private Label label;
    private CCombo cmboAxisOrientation;
    private Label lblNewLabel_1;
    private Composite composite_2;
    private TableColumn tblclmnLevels;
    private Label label_1;
    private Spinner numColsSpinner1;

    public LineGraphDialog(Shell parentShell, String analysisType, File file) {
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
                WindowDialogControlUtil.removeWindowDialogToList(LineGraphDialog.this.getShell());
            }
        });
        this.plotImage = ResourceManager.getPluginImage("Star", "icons/plotsymbol1.png");
        parent.getShell().setText("Line Graph: " + this.dataManipulationManager.getDisplayFileName(this.file.getAbsolutePath()));
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
        lblResponseVariables.setText("Y Variable(s):");
        this.numVarList = new List(modelComposite, 2818);
        GridData gd_numVarList = new GridData(4, 4, true, false, 2, 1);
        gd_numVarList.heightHint = 95;
        gd_numVarList.widthHint = 126;
        this.numVarList.setLayoutData((Object)gd_numVarList);
        this.numVarList.setItems(this.numericVariables);
        this.numVarList.addMouseListener(new MouseListener(){

            public void mouseDoubleClick(MouseEvent e) {
                String[] selectedNumVars = LineGraphDialog.this.numVarList.getSelection();
                if (selectedNumVars.length > 0) {
                    LineGraphDialog.this.addItemToTableAxis(selectedNumVars[0]);
                    LineGraphDialog.this.dataManipulationManager.moveSelectedStringFromTo(LineGraphDialog.this.numVarList, LineGraphDialog.this.responseVarList);
                    LineGraphDialog.this.getShell().setSize(LineGraphDialog.this.getShell().getSize().x - 1, LineGraphDialog.this.getShell().getSize().y);
                    LineGraphDialog.this.numVarList.remove(LineGraphDialog.this.numVarList.getSelectionIndices());
                    LineGraphDialog.this.moveBtn.setEnabled(false);
                    LineGraphDialog.this.addBtn.setEnabled(false);
                }
            }

            public void mouseDown(MouseEvent e) {
                if (LineGraphDialog.this.numVarList.getSelectionCount() > 0) {
                    LineGraphDialog.this.moveBtn.setEnabled(true);
                    LineGraphDialog.this.factorVarList.setSelection(-1);
                    LineGraphDialog.this.responseVarList.setSelection(-1);
                    LineGraphDialog.this.addBtn.setText("Add");
                    LineGraphDialog.this.moveBtn.setText("Set to Factor");
                    LineGraphDialog.this.enableFactorButtons(false);
                    LineGraphDialog.this.enableNumericButtons(true);
                }
            }

            public void mouseUp(MouseEvent e) {
            }
        });
        this.addBtn = new Button(modelComposite, 0);
        this.addBtn.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (LineGraphDialog.this.numVarList.getSelectionCount() > 0) {
                    String[] selectedNumVars = LineGraphDialog.this.numVarList.getSelection();
                    int i = 0;
                    while (i < selectedNumVars.length) {
                        LineGraphDialog.this.responseVarList.add(selectedNumVars[i]);
                        LineGraphDialog.this.addItemToTableAxis(selectedNumVars[i]);
                        ++i;
                    }
                    LineGraphDialog.this.table.pack();
                    LineGraphDialog.this.numVarList.remove(LineGraphDialog.this.numVarList.getSelectionIndices());
                } else {
                    String[] selectedNumVars = LineGraphDialog.this.responseVarList.getSelection();
                    int i = 0;
                    while (i < selectedNumVars.length) {
                        LineGraphDialog.this.numVarList.add(selectedNumVars[i]);
                        LineGraphDialog.this.removeItemFromTableAxis(selectedNumVars[i]);
                        ++i;
                    }
                    LineGraphDialog.this.responseVarList.remove(LineGraphDialog.this.responseVarList.getSelectionIndices());
                }
                LineGraphDialog.this.enableNumericButtons(false);
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
                if (LineGraphDialog.this.responseVarList.getSelection().length > 0) {
                    LineGraphDialog.this.removeItemFromTableAxis(LineGraphDialog.this.responseVarList.getSelection()[0]);
                    LineGraphDialog.this.dataManipulationManager.moveSelectedStringFromTo(LineGraphDialog.this.responseVarList, LineGraphDialog.this.numVarList);
                    LineGraphDialog.this.getShell().setSize(LineGraphDialog.this.getShell().getSize().x + 1, LineGraphDialog.this.getShell().getSize().y);
                    LineGraphDialog.this.responseVarList.remove(LineGraphDialog.this.responseVarList.getSelectionIndices());
                    LineGraphDialog.this.moveBtn.setEnabled(false);
                    LineGraphDialog.this.addBtn.setEnabled(false);
                }
            }

            public void mouseDown(MouseEvent e) {
                if (LineGraphDialog.this.responseVarList.getSelectionCount() > 0) {
                    LineGraphDialog.this.numVarList.setSelection(-1);
                    LineGraphDialog.this.addBtn.setText("Remove");
                    LineGraphDialog.this.addBtn.setEnabled(true);
                    LineGraphDialog.this.moveBtn.setEnabled(false);
                    LineGraphDialog.this.enableFactorButtons(false);
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
                if (LineGraphDialog.this.numVarList.getSelectionCount() > 0) {
                    String[] selectedNumVars = LineGraphDialog.this.numVarList.getSelection();
                    int i = 0;
                    while (i < selectedNumVars.length) {
                        LineGraphDialog.this.factorVarList.add(selectedNumVars[i]);
                        LineGraphDialog.this.dataManipulationManager.editVariableType(LineGraphDialog.this.file.getAbsolutePath(), LineGraphDialog.this.varInfo, selectedNumVars[i], "Factor");
                        ++i;
                    }
                    LineGraphDialog.this.numVarList.remove(LineGraphDialog.this.numVarList.getSelectionIndices());
                    LineGraphDialog.this.enableNumericButtons(false);
                } else {
                    String[] selectedNumVars = LineGraphDialog.this.factorVarList.getSelection();
                    int i = 0;
                    while (i < selectedNumVars.length) {
                        LineGraphDialog.this.numVarList.add(selectedNumVars[i]);
                        LineGraphDialog.this.dataManipulationManager.editVariableType(LineGraphDialog.this.file.getAbsolutePath(), LineGraphDialog.this.varInfo, selectedNumVars[i], "Numeric");
                        ++i;
                    }
                    LineGraphDialog.this.factorVarList.remove(LineGraphDialog.this.factorVarList.getSelectionIndices());
                    LineGraphDialog.this.enableFactorButtons(false);
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
        lblCategorizeBy.setText("X Variable:");
        this.factorVarList = new List(modelComposite, 2560);
        GridData gd_factorVarList = new GridData(4, 4, true, false, 2, 5);
        gd_factorVarList.widthHint = 65;
        this.factorVarList.setLayoutData((Object)gd_factorVarList);
        this.factorVarList.setItems(this.factorVariables);
        this.factorVarList.addListener(3, new Listener(){

            public void handleEvent(Event event) {
                if (LineGraphDialog.this.factorVarList.getSelectionIndex() > -1) {
                    LineGraphDialog.this.enableNumericButtons(false);
                    LineGraphDialog.this.enableFactorButtons(false);
                    LineGraphDialog.this.numVarList.setSelection(-1);
                    String[] s = LineGraphDialog.this.factorVarList.getSelection();
                    String isNumeric = LineGraphDialog.this.dataManipulationManager.isNumeric(LineGraphDialog.this.file.getAbsolutePath().replaceAll("\\\\", "/"), s[0]);
                    if (isNumeric.equals("TRUE")) {
                        LineGraphDialog.this.moveBtn.setText("Set to Numeric");
                        LineGraphDialog.this.moveBtn.setEnabled(true);
                    } else {
                        LineGraphDialog.this.moveBtn.setEnabled(false);
                    }
                    LineGraphDialog.this.enableFactorButtons(true);
                }
            }
        });
        this.addAxisBtn = new Button(modelComposite, 0);
        this.addAxisBtn.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (LineGraphDialog.this.factorVarList.getSelectionCount() > 0) {
                    String[] selectedNumVars = LineGraphDialog.this.factorVarList.getSelection();
                    LineGraphDialog.this.axisVarList.add(selectedNumVars[0]);
                    LineGraphDialog.this.categoryAxisText.setText(LineGraphDialog.this.axisVarList.getItems()[0]);
                    if (LineGraphDialog.this.lstCreateLines.getItemCount() == 0) {
                        LineGraphDialog.this.tblclmnLevels.setText("");
                        LineGraphDialog.this.addItemToVarTable("");
                    }
                    LineGraphDialog.this.getShell().setSize(LineGraphDialog.this.getShell().getSize().x - 1, LineGraphDialog.this.getShell().getSize().y);
                    LineGraphDialog.this.table.pack();
                    LineGraphDialog.this.factorVarList.remove(LineGraphDialog.this.factorVarList.getSelectionIndices());
                } else {
                    String[] selectedNumVars = LineGraphDialog.this.axisVarList.getSelection();
                    LineGraphDialog.this.factorVarList.add(selectedNumVars[0]);
                    LineGraphDialog.this.categoryAxisText.setText("");
                    LineGraphDialog.this.tblclmnLevels.setText("Levels");
                    LineGraphDialog.this.getShell().setSize(LineGraphDialog.this.getShell().getSize().x + 1, LineGraphDialog.this.getShell().getSize().y);
                    if (LineGraphDialog.this.lstCreateLines.getItemCount() == 0) {
                        LineGraphDialog.this.removeAllItemsFromVarTable();
                    }
                    LineGraphDialog.this.axisVarList.remove(LineGraphDialog.this.axisVarList.getSelectionIndices());
                }
                LineGraphDialog.this.enableFactorButtons(false);
                LineGraphDialog.this.moveBtn.setEnabled(false);
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
                LineGraphDialog.this.dataManipulationManager.moveSelectedStringFromTo(LineGraphDialog.this.axisVarList, LineGraphDialog.this.factorVarList);
                LineGraphDialog.this.addAxisBtn.setEnabled(false);
            }

            public void mouseDown(MouseEvent e) {
                if (LineGraphDialog.this.axisVarList.getSelectionCount() > 0) {
                    LineGraphDialog.this.factorVarList.setSelection(-1);
                    LineGraphDialog.this.addAxisBtn.setText("Remove");
                    LineGraphDialog.this.addAxisBtn.setEnabled(true);
                }
            }

            public void mouseUp(MouseEvent e) {
            }
        });
        new Label(modelComposite, 0);
        new Label(modelComposite, 0);
        this.lblCreateLinesBy = new Label(modelComposite, 0);
        this.lblCreateLinesBy.setText("Create lines by:");
        this.btnCreateLines = new Button(modelComposite, 0);
        this.btnCreateLines.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (LineGraphDialog.this.factorVarList.getSelectionCount() > 0) {
                    LineGraphDialog.this.tblclmnLevels.setText("Levels");
                    LineGraphDialog.this.removeAllItemsFromVarTable();
                    LineGraphDialog.this.dataManipulationManager.moveSelectedStringFromTo(LineGraphDialog.this.factorVarList, LineGraphDialog.this.lstCreateLines);
                    String[] catVar = DataManipulationManager.getEnvtLevels(LineGraphDialog.this.lstCreateLines.getItem(0), LineGraphDialog.this.file);
                    System.out.println(catVar);
                    String[] stringArray = catVar;
                    int n = catVar.length;
                    int n2 = 0;
                    while (n2 < n) {
                        String var = stringArray[n2];
                        LineGraphDialog.this.addItemToVarTable(var);
                        ++n2;
                    }
                    LineGraphDialog.this.getShell().setSize(LineGraphDialog.this.getShell().getSize().x - 1, LineGraphDialog.this.getShell().getSize().y);
                    LineGraphDialog.this.numColsSpinner1.setEnabled(true);
                } else {
                    String[] catVar = DataManipulationManager.getEnvtLevels(LineGraphDialog.this.lstCreateLines.getItem(0), LineGraphDialog.this.file);
                    System.out.println(catVar);
                    String[] stringArray = catVar;
                    int n = catVar.length;
                    int n3 = 0;
                    while (n3 < n) {
                        String var = stringArray[n3];
                        LineGraphDialog.this.removeItemFromVarTable(var);
                        ++n3;
                    }
                    LineGraphDialog.this.dataManipulationManager.moveSelectedStringFromTo(LineGraphDialog.this.lstCreateLines, LineGraphDialog.this.factorVarList);
                    if (LineGraphDialog.this.axisVarList.getItemCount() > 0 && LineGraphDialog.this.lstCreateLines.getItemCount() == 0) {
                        LineGraphDialog.this.tblclmnLevels.setText("");
                        LineGraphDialog.this.addItemToVarTable("");
                    }
                    LineGraphDialog.this.numColsSpinner1.setEnabled(false);
                }
                LineGraphDialog.this.enableFactorButtons(false);
                LineGraphDialog.this.moveBtn.setEnabled(false);
            }
        });
        this.btnCreateLines.setLayoutData((Object)new GridData(4, 0x1000000, false, true, 2, 1));
        this.btnCreateLines.setText("Add");
        this.btnCreateLines.setEnabled(false);
        this.lstCreateLines = new List(modelComposite, 2818);
        this.lstCreateLines.addMouseListener((MouseListener)new MouseAdapter(){

            public void mouseDoubleClick(MouseEvent e) {
                LineGraphDialog.this.dataManipulationManager.moveSelectedStringFromTo(LineGraphDialog.this.lstCreateLines, LineGraphDialog.this.factorVarList);
                LineGraphDialog.this.btnCreateLines.setEnabled(false);
                LineGraphDialog.this.numColsSpinner1.setEnabled(false);
            }

            public void mouseDown(MouseEvent e) {
                if (LineGraphDialog.this.lstCreateLines.getSelectionCount() > 0) {
                    LineGraphDialog.this.factorVarList.setSelection(-1);
                    LineGraphDialog.this.btnCreateLines.setText("Remove");
                    LineGraphDialog.this.btnCreateLines.setEnabled(true);
                }
            }

            public void mouseUp(MouseEvent e) {
            }
        });
        this.lstCreateLines.setLayoutData((Object)new GridData(4, 4, false, true, 1, 1));
        new Label(modelComposite, 0);
        new Label(modelComposite, 0);
        Label lblGroupBy = new Label(modelComposite, 0);
        lblGroupBy.setText("Group by:");
        this.addGroupsBtn = new Button(modelComposite, 0);
        this.addGroupsBtn.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (LineGraphDialog.this.factorVarList.getSelectionCount() > 0) {
                    LineGraphDialog.this.dataManipulationManager.moveSelectedStringFromTo(LineGraphDialog.this.factorVarList, LineGraphDialog.this.groupsVarList);
                } else {
                    LineGraphDialog.this.dataManipulationManager.moveSelectedStringFromTo(LineGraphDialog.this.groupsVarList, LineGraphDialog.this.factorVarList);
                }
                LineGraphDialog.this.enableFactorButtons(false);
                LineGraphDialog.this.moveBtn.setEnabled(false);
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
                LineGraphDialog.this.dataManipulationManager.moveSelectedStringFromTo(LineGraphDialog.this.groupsVarList, LineGraphDialog.this.factorVarList);
                LineGraphDialog.this.addGroupsBtn.setEnabled(false);
            }

            public void mouseDown(MouseEvent e) {
                if (LineGraphDialog.this.groupsVarList.getSelectionCount() > 0) {
                    LineGraphDialog.this.factorVarList.setSelection(-1);
                    LineGraphDialog.this.addGroupsBtn.setText("Remove");
                    LineGraphDialog.this.addGroupsBtn.setEnabled(true);
                }
            }

            public void mouseUp(MouseEvent e) {
            }
        });
        this.tbtmDisplay = new TabItem(tabFolder, 0);
        this.tbtmDisplay.setText("Display Options");
        Composite composite_2 = new Composite((Composite)tabFolder, 0);
        this.tbtmDisplay.setControl((Control)composite_2);
        composite_2.setLayout((Layout)new GridLayout(7, false));
        Label lblMainTitle = new Label(composite_2, 0);
        lblMainTitle.setLayoutData((Object)new GridData(16384, 0x1000000, false, false, 2, 1));
        lblMainTitle.setText("Main Title:");
        this.mainTitleText = new Text(composite_2, 2048);
        int maxLen = 45;
        this.mainTitleText.setTextLimit(maxLen);
        this.mainTitleText.setLayoutData((Object)new GridData(4, 0x1000000, false, false, 5, 1));
        Label lblHorizontalAxisLabel = new Label(composite_2, 0);
        lblHorizontalAxisLabel.setLayoutData((Object)new GridData(16384, 0x1000000, false, false, 2, 1));
        lblHorizontalAxisLabel.setText("Horizontal Axis Label:");
        this.categoryAxisText = new Text(composite_2, 2048);
        this.categoryAxisText.setLayoutData((Object)new GridData(4, 0x1000000, false, false, 5, 1));
        Label lblValueAxisLabel = new Label(composite_2, 0);
        lblValueAxisLabel.setLayoutData((Object)new GridData(16384, 0x1000000, false, false, 7, 1));
        lblValueAxisLabel.setText("Vertical Axis:");
        this.tableAxis = new Table(composite_2, 67586);
        this.tableAxis.setLinesVisible(true);
        this.tableAxis.setHeaderVisible(true);
        this.tableAxis.setBackground(SWTResourceManager.getColor(1));
        GridData gd_tableAxis = new GridData(4, 4, true, true, 7, 1);
        gd_tableAxis.heightHint = 126;
        this.tableAxis.setLayoutData((Object)gd_tableAxis);
        this.tableColumn = new TableColumn(this.tableAxis, 0);
        this.tableColumn.setWidth(85);
        this.tableColumn.setText("Variable");
        this.tableColumn_5 = new TableColumn(this.tableAxis, 0);
        this.tableColumn_5.setWidth(83);
        this.tableColumn_5.setText("Axis Label");
        this.tableColumn_6 = new TableColumn(this.tableAxis, 0);
        this.tableColumn_6.setWidth(87);
        this.tableColumn_6.setText("Lower Limit");
        this.tableColumn_8 = new TableColumn(this.tableAxis, 0);
        this.tableColumn_8.setWidth(72);
        this.tableColumn_8.setText("Upper Limit");
        this.label = new Label(composite_2, 0);
        this.label.setLayoutData((Object)new GridData(16384, 0x1000000, false, false, 2, 1));
        this.label.setText("Axis Label Orientation:");
        this.cmboAxisOrientation = new CCombo(composite_2, 2056);
        this.cmboAxisOrientation.setLayoutData((Object)new GridData(4, 0x1000000, false, false, 1, 1));
        this.cmboAxisOrientation.setItems(new String[]{"parallel to axis", "horizontal", "vertical", "perpendicular to axis"});
        this.cmboAxisOrientation.setBackground(SWTResourceManager.getColor(1));
        this.cmboAxisOrientation.select(0);
        new Label(composite_2, 0);
        new Label(composite_2, 0);
        new Label(composite_2, 0);
        new Label(composite_2, 0);
        this.btnDrawBoxAround = new Button(composite_2, 32);
        this.btnDrawBoxAround.setSelection(true);
        this.btnDrawBoxAround.setLayoutData((Object)new GridData(16384, 0x1000000, false, false, 7, 1));
        this.btnDrawBoxAround.setText("Draw box around plot");
        this.btnDisplaylinelabels = new Button(composite_2, 32);
        this.btnDisplaylinelabels.setEnabled(false);
        this.btnDisplaylinelabels.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (LineGraphDialog.this.btnDisplaylinelabels.getSelection()) {
                    LineGraphDialog.this.btnShowLegend.setEnabled(false);
                } else {
                    LineGraphDialog.this.btnShowLegend.setEnabled(true);
                }
            }
        });
        this.btnDisplaylinelabels.setLayoutData((Object)new GridData(16384, 0x1000000, false, false, 7, 1));
        this.btnDisplaylinelabels.setText("Display line labels");
        this.btnShowLegend = new Button(composite_2, 32);
        this.btnShowLegend.setSelection(true);
        this.btnShowLegend.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (LineGraphDialog.this.btnShowLegend.getSelection()) {
                    LineGraphDialog.this.enableShowLegend(true);
                    LineGraphDialog.this.btnDisplaylinelabels.setEnabled(false);
                } else {
                    LineGraphDialog.this.enableShowLegend(false);
                    LineGraphDialog.this.btnDisplaylinelabels.setEnabled(true);
                }
            }
        });
        this.btnShowLegend.setLayoutData((Object)new GridData(16384, 0x1000000, false, false, 7, 1));
        this.btnShowLegend.setText("Show legend");
        new Label(composite_2, 0);
        this.lblPosition = new Label(composite_2, 0);
        this.lblPosition.setText("Position:");
        this.legPositionComo = new CCombo(composite_2, 2056);
        this.legPositionComo.setBackground(SWTResourceManager.getColor(1));
        this.legPositionComo.setEditable(true);
        this.legPositionComo.setItems(new String[]{"bottom", "bottom-left", "bottom-right", "center", "left", "right", "top", "top-left", "top-right"});
        this.legPositionComo.setLayoutData((Object)new GridData(4, 0x1000000, false, false, 1, 1));
        this.legPositionComo.select(2);
        new Label(composite_2, 0);
        this.lblNewLabel_1 = new Label(composite_2, 0);
        this.lblNewLabel_1.setLayoutData((Object)new GridData(16384, 0x1000000, true, false, 1, 1));
        this.label_1 = new Label(composite_2, 0);
        this.label_1.setText("Number of columns:");
        this.label_1.setLayoutData((Object)new GridData(131072, 0x1000000, false, false, 1, 1));
        this.numColsSpinner1 = new Spinner(composite_2, 2056);
        this.numColsSpinner1.setEnabled(false);
        this.numColsSpinner1.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                String[] selectedStrings = LineGraphDialog.this.lstCreateLines.getItems();
                int maxLevels = 1;
                int i = 0;
                while (i < LineGraphDialog.this.lstCreateLines.getItemCount()) {
                    String[] catLevels = DataManipulationManager.getEnvtLevels(selectedStrings[i], LineGraphDialog.this.file);
                    maxLevels *= catLevels.length;
                    ++i;
                }
                LineGraphDialog.this.numColsSpinner1.setMaximum(maxLevels);
                LineGraphDialog.this.numColsSpinner1.setMinimum(1);
            }
        });
        this.numColsSpinner1.setBackground(SWTResourceManager.getColor(1));
        GridData gd_numColsSpinner1 = new GridData(16384, 0x1000000, false, false, 1, 1);
        gd_numColsSpinner1.widthHint = 10;
        this.numColsSpinner1.setLayoutData((Object)gd_numColsSpinner1);
        this.numColsSpinner1.setMaximum(5);
        this.numColsSpinner1.setMinimum(1);
        this.numColsSpinner1.setSelection(1);
        new Label(composite_2, 0);
        this.lblTitle = new Label(composite_2, 0);
        this.lblTitle.setText("Title:");
        this.legTitleText = new Text(composite_2, 2048);
        this.legTitleText.setLayoutData((Object)new GridData(4, 0x1000000, true, false, 5, 1));
        this.btnDisplayMultipleGraphs = new Button(composite_2, 32);
        this.btnDisplayMultipleGraphs.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (LineGraphDialog.this.btnDisplayMultipleGraphs.getSelection()) {
                    LineGraphDialog.this.displayMultipleGraphs(true);
                } else {
                    LineGraphDialog.this.displayMultipleGraphs(false);
                }
            }
        });
        this.btnDisplayMultipleGraphs.setLayoutData((Object)new GridData(16384, 0x1000000, false, false, 7, 1));
        this.btnDisplayMultipleGraphs.setText("Display multiple graphs in a page");
        new Label(composite_2, 0);
        this.lblNewLabel = new Label(composite_2, 0);
        this.lblNewLabel.setEnabled(false);
        this.lblNewLabel.setText("Number of rows:");
        this.numRowsSpinner = new Spinner(composite_2, 2056);
        this.numRowsSpinner.setBackground(SWTResourceManager.getColor(1));
        GridData gd_numRowsSpinner = new GridData(16384, 0x1000000, false, false, 1, 1);
        gd_numRowsSpinner.widthHint = 10;
        this.numRowsSpinner.setLayoutData((Object)gd_numRowsSpinner);
        this.numRowsSpinner.setMaximum(5);
        this.numRowsSpinner.setMinimum(1);
        this.numRowsSpinner.setSelection(2);
        this.numRowsSpinner.setEnabled(false);
        new Label(composite_2, 0);
        new Label(composite_2, 0);
        this.lblNumberOfColumns = new Label(composite_2, 0);
        this.lblNumberOfColumns.setEnabled(false);
        this.lblNumberOfColumns.setText("Number of columns:");
        this.numColsSpinner = new Spinner(composite_2, 2056);
        this.numColsSpinner.setBackground(SWTResourceManager.getColor(1));
        GridData gd_numColsSpinner = new GridData(16384, 0x1000000, true, false, 1, 1);
        gd_numColsSpinner.widthHint = 10;
        this.numColsSpinner.setLayoutData((Object)gd_numColsSpinner);
        this.numColsSpinner.setMaximum(5);
        this.numColsSpinner.setMinimum(1);
        this.numColsSpinner.setSelection(1);
        this.numColsSpinner.setEnabled(false);
        new Label(composite_2, 0);
        this.lblOrientation = new Label(composite_2, 0);
        this.lblOrientation.setLayoutData((Object)new GridData(16384, 0x1000000, false, true, 1, 1));
        this.lblOrientation.setEnabled(false);
        this.lblOrientation.setText("Orientation:");
        this.orientGraphsCombo = new CCombo(composite_2, 2056);
        this.orientGraphsCombo.setBackground(SWTResourceManager.getColor(1));
        this.orientGraphsCombo.setEditable(false);
        this.orientGraphsCombo.setEnabled(false);
        this.orientGraphsCombo.setItems(new String[]{"Left-to-right", "Top-to-bottom"});
        this.orientGraphsCombo.setLayoutData((Object)new GridData(4, 0x1000000, false, true, 1, 1));
        this.orientGraphsCombo.select(0);
        new Label(composite_2, 0);
        new Label(composite_2, 0);
        new Label(composite_2, 0);
        new Label(composite_2, 0);
        TabItem tbtmBars = new TabItem(tabFolder, 0);
        tbtmBars.setText("Other Options");
        this.composite = new Composite((Composite)tabFolder, 0);
        tbtmBars.setControl((Control)this.composite);
        this.composite.setLayout((Layout)new GridLayout(5, false));
        Label lblPlot = new Label(this.composite, 0);
        lblPlot.setLayoutData((Object)new GridData(16384, 0x1000000, true, false, 5, 1));
        lblPlot.setText("Format Components (lines and points):");
        this.table = new Table(this.composite, 559104);
        this.table.setBackground(SWTResourceManager.getColor(1));
        this.table.setLayoutData((Object)new GridData(4, 4, true, true, 5, 1));
        this.table.setLinesVisible(true);
        this.table.setHeaderVisible(true);
        this.table.setRedraw(true);
        this.tblclmnLevels = new TableColumn(this.table, 0);
        this.tblclmnLevels.setWidth(75);
        this.tblclmnLevels.setText("Levels");
        TableColumn tblclmnType = new TableColumn(this.table, 0);
        tblclmnType.setWidth(75);
        tblclmnType.setText("Type");
        TableColumn tableColumn_2 = new TableColumn(this.table, 0);
        tableColumn_2.setWidth(60);
        tableColumn_2.setText("Color");
        TableColumn tableColumn_3 = new TableColumn(this.table, 0);
        tableColumn_3.setWidth(25);
        TableColumn tblclmnLine = new TableColumn(this.table, 0);
        tblclmnLine.setWidth(60);
        tblclmnLine.setText("Line");
        TableColumn tableColumn_4 = new TableColumn(this.table, 0);
        tableColumn_4.setWidth(25);
        TableColumn tblclmnWidth = new TableColumn(this.table, 0);
        tblclmnWidth.setWidth(60);
        tblclmnWidth.setText("Width");
        TableColumn tblclmnPoint = new TableColumn(this.table, 0);
        tblclmnPoint.setWidth(30);
        tblclmnPoint.setText("Pt.");
        TableColumn tableColumn_7 = new TableColumn(this.table, 0);
        tableColumn_7.setWidth(25);
        TableColumn tblclmnPointSize = new TableColumn(this.table, 0);
        tblclmnPointSize.setWidth(70);
        tblclmnPointSize.setText("Point Size");
        this.table.addListener(41, new Listener(){

            public void handleEvent(Event event) {
                TableEditor[] te;
                event.height = ((LineGraphDialog)LineGraphDialog.this).plotImage.getBounds().height;
                TableItem item = (TableItem)event.item;
                TableEditor[] tableEditorArray = te = (TableEditor[])item.getData("Editors");
                int n = te.length;
                int n2 = 0;
                while (n2 < n) {
                    TableEditor t = tableEditorArray[n2];
                    t.layout();
                    ++n2;
                }
            }
        });
        this.displayErrorBarsBtn = new Button(this.composite, 32);
        this.displayErrorBarsBtn.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (LineGraphDialog.this.displayErrorBarsBtn.getSelection()) {
                    LineGraphDialog.this.enableErrorBarsOptions(true);
                } else {
                    LineGraphDialog.this.enableErrorBarsOptions(false);
                }
            }
        });
        this.displayErrorBarsBtn.setLayoutData((Object)new GridData(16384, 0x1000000, false, false, 4, 1));
        this.displayErrorBarsBtn.setText("Display Error Bars");
        new Label(this.composite, 0);
        new Label(this.composite, 0);
        this.errorBarsLabel = new Label(this.composite, 0);
        this.errorBarsLabel.setEnabled(false);
        this.errorBarsLabel.setLayoutData((Object)new GridData(16384, 0x1000000, false, false, 4, 1));
        this.errorBarsLabel.setText("ERROR BARS");
        new Label(this.composite, 0);
        new Label(this.composite, 0);
        this.confidenceLvlBtn = new Button(this.composite, 16);
        this.confidenceLvlBtn.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                LineGraphDialog.this.enableErrorBarsOptions(true);
            }
        });
        this.confidenceLvlBtn.setEnabled(false);
        this.confidenceLvlBtn.setSelection(true);
        this.confidenceLvlBtn.setText("Confidence level");
        new Label(this.composite, 0);
        new Label(this.composite, 0);
        new Label(this.composite, 0);
        new Label(this.composite, 0);
        this.lblLevel = new Label(this.composite, 0);
        this.lblLevel.setEnabled(false);
        this.lblLevel.setLayoutData((Object)new GridData(131072, 0x1000000, false, false, 1, 1));
        this.lblLevel.setText("Level (%):");
        this.confidenceLvlValue = new Spinner(this.composite, 2048);
        this.confidenceLvlValue.setEnabled(false);
        GridData gd_confidenceLvlValue = new GridData(16384, 0x1000000, false, false, 1, 1);
        gd_confidenceLvlValue.widthHint = 10;
        this.confidenceLvlValue.setLayoutData((Object)gd_confidenceLvlValue);
        this.confidenceLvlValue.setMaximum(99);
        this.confidenceLvlValue.setMinimum(1);
        this.confidenceLvlValue.setSelection(95);
        new Label(this.composite, 0);
        new Label(this.composite, 0);
        new Label(this.composite, 0);
        this.stdErrorBtn = new Button(this.composite, 16);
        this.stdErrorBtn.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                LineGraphDialog.this.enableErrorBarsOptions(true);
            }
        });
        this.stdErrorBtn.setEnabled(false);
        this.stdErrorBtn.setText("Standard Error");
        new Label(this.composite, 0);
        new Label(this.composite, 0);
        new Label(this.composite, 0);
        new Label(this.composite, 0);
        this.lblMultiplier = new Label(this.composite, 0);
        this.lblMultiplier.setEnabled(false);
        this.lblMultiplier.setLayoutData((Object)new GridData(131072, 0x1000000, false, false, 1, 1));
        this.lblMultiplier.setText("Multiplier:");
        this.stdErrorValue = new Spinner(this.composite, 2048);
        this.stdErrorValue.setEnabled(false);
        GridData gd_stdErrorValue = new GridData(16384, 0x1000000, false, false, 1, 1);
        gd_stdErrorValue.widthHint = 10;
        this.stdErrorValue.setLayoutData((Object)gd_stdErrorValue);
        this.stdErrorValue.setMaximum(3);
        this.stdErrorValue.setMinimum(1);
        new Label(this.composite, 0);
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

    protected void enableShowLegend(boolean state) {
        this.lblPosition.setEnabled(state);
        this.legTitleText.setEnabled(state);
        this.lblTitle.setEnabled(state);
        this.legPositionComo.setEnabled(state);
        this.label_1.setEnabled(state);
    }

    private void enableErrorBarsOptions(boolean state) {
        this.errorBarsLabel.setEnabled(state);
        this.confidenceLvlBtn.setEnabled(state);
        this.lblLevel.setEnabled(false);
        this.confidenceLvlValue.setEnabled(false);
        this.stdErrorBtn.setEnabled(state);
        this.lblMultiplier.setEnabled(false);
        this.stdErrorValue.setEnabled(false);
        if (state) {
            if (this.confidenceLvlBtn.getSelection()) {
                this.confidenceLvlValue.setEnabled(true);
                this.lblLevel.setEnabled(true);
            } else if (this.stdErrorBtn.getSelection()) {
                this.stdErrorValue.setEnabled(true);
                this.lblMultiplier.setEnabled(true);
            }
        }
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
        int i2 = 0;
        while (i2 < this.table.getItemCount()) {
            tableItem = this.table.getItem(i2);
            TableEditor[] tableEditorArray2 = editors = (TableEditor[])tableItem.getData("Editors");
            int n3 = editors.length;
            n = 0;
            while (n < n3) {
                TableEditor te = tableEditorArray2[n];
                te.layout();
                ++n;
            }
            ++i2;
        }
        this.table.update();
        this.table.pack();
        this.composite.pack();
    }

    protected void removeAllItemsFromVarTable() {
        int i = 0;
        while (i < this.table.getItemCount()) {
            TableEditor[] editors;
            TableItem tableItem = this.table.getItem(i);
            TableEditor[] tableEditorArray = editors = (TableEditor[])tableItem.getData("Editors");
            int n = editors.length;
            int n2 = 0;
            while (n2 < n) {
                TableEditor te = tableEditorArray[n2];
                te.getEditor().dispose();
                ++n2;
            }
            ++i;
        }
        this.table.pack();
        this.table.removeAll();
        this.table.pack();
        this.composite.pack();
    }

    protected void addItemToVarTable(String varName) {
        RGB[] colors = GraphsUtilities.getGrayShades(this.table.getItemCount() + 1);
        int ctr = this.table.getItemCount();
        TableItem tableItem = new TableItem(this.table, 0x1000000);
        tableItem.setText(0, varName);
        TableEditor[] tableEditors = new TableEditor[]{new TableEditor(this.table), new TableEditor(this.table), new TableEditor(this.table), new TableEditor(this.table), new TableEditor(this.table), new TableEditor(this.table), new TableEditor(this.table), new TableEditor(this.table)};
        TableEditor[] tableEditorArray = tableEditors;
        int n = tableEditors.length;
        int n2 = 0;
        while (n2 < n) {
            TableEditor te = tableEditorArray[n2];
            te.grabHorizontal = true;
            te.grabVertical = true;
            te.layout();
            ++n2;
        }
        CCombo varTypeList = new CCombo((Composite)this.table, 0x1000008);
        varTypeList.setBackground(Display.getCurrent().getSystemColor(1));
        varTypeList.setItems(new String[]{"lines only", "overplot", "both"});
        varTypeList.select(1);
        GraphsUtilities.refreshTableColors(this.table, 2, colors);
        Button chooseColorButton = new Button((Composite)this.table, 0x1000000);
        chooseColorButton.setImage(ResourceManager.getPluginImage("Star", "icons/ellipsis.png"));
        chooseColorButton.setBackground(Display.getCurrent().getSystemColor(1));
        chooseColorButton.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                LineGraphDialog.this.table.setSelection(-1);
                try {
                    RGB rgbColor = GraphsUtilities.chooseColor();
                    tableItem.setBackground(2, new Color((Device)Display.getCurrent(), rgbColor));
                }
                catch (Exception exception) {
                    System.out.println("Exception at RGB choose color");
                }
            }
        });
        Label lineLabel = new Label((Composite)this.table, 0x1000800);
        lineLabel.setBackground(SWTResourceManager.getColor(22));
        lineLabel.setImage(ResourceManager.getPluginImage("Star", "icons/line1.png"));
        lineLabel.setData("lineType", (Object)Integer.parseInt("1"));
        Button chooseLineButton = new Button((Composite)this.table, 0x1000000);
        chooseLineButton.setImage(ResourceManager.getPluginImage("Star", "icons/ellipsis.png"));
        chooseLineButton.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                ChooseLineTypesDialog lineDlg = new ChooseLineTypesDialog(LineGraphDialog.this.getShell());
                lineDlg.open();
                if (lineDlg.getReturnCode() == 0) {
                    lineLabel.setImage(ResourceManager.getPluginImage("Star", "icons/line" + lineDlg.getChosenPattern() + ".png"));
                    lineLabel.setData("lineType", (Object)Integer.parseInt(lineDlg.getChosenPattern()));
                }
            }
        });
        Spinner widthValue = new Spinner((Composite)this.table, 2048);
        widthValue.setMaximum(30);
        widthValue.setMinimum(5);
        widthValue.setSelection(10);
        widthValue.setDigits(1);
        Label pointLabel = new Label((Composite)this.table, 0x1000800);
        pointLabel.setBackground(SWTResourceManager.getColor(22));
        pointLabel.setImage(ResourceManager.getPluginImage("Star", "icons/plotsymbol1.png"));
        pointLabel.setBounds(tableItem.getBounds());
        pointLabel.setData("pointChars", (Object)"1");
        Button choosePlotBtn = new Button((Composite)this.table, 0x1000000);
        choosePlotBtn.setImage(ResourceManager.getPluginImage("Star", "icons/ellipsis.png"));
        choosePlotBtn.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                ChoosePlotSymbolsDialog choosePlotDlg = new ChoosePlotSymbolsDialog(LineGraphDialog.this.getShell());
                choosePlotDlg.open();
                if (choosePlotDlg.getReturnCode() == 0) {
                    pointLabel.setImage(ResourceManager.getPluginImage("Star", "icons/plotsymbol" + choosePlotDlg.getChosenSymbol() + ".png"));
                    pointLabel.setData("pointChars", (Object)choosePlotDlg.getChosenSymbol());
                }
            }
        });
        Spinner pointSize = new Spinner((Composite)this.table, 2048);
        pointSize.setMaximum(30);
        pointSize.setMinimum(5);
        pointSize.setSelection(10);
        pointSize.setDigits(1);
        tableEditors[0].setEditor((Control)varTypeList, tableItem, 1);
        tableEditors[1].setEditor((Control)chooseColorButton, tableItem, 3);
        tableEditors[2].setEditor((Control)lineLabel, tableItem, 4);
        tableEditors[3].setEditor((Control)chooseLineButton, tableItem, 5);
        tableEditors[4].setEditor((Control)widthValue, tableItem, 6);
        tableEditors[5].setEditor((Control)pointLabel, tableItem, 7);
        tableEditors[6].setEditor((Control)choosePlotBtn, tableItem, 8);
        tableEditors[7].setEditor((Control)pointSize, tableItem, 9);
        TableEditor[] tableEditorArray2 = tableEditors;
        int n3 = tableEditors.length;
        int n4 = 0;
        while (n4 < n3) {
            TableEditor te = tableEditorArray2[n4];
            te.layout();
            ++n4;
        }
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
        this.groupsVarList.setSelection(-1);
        this.axisVarList.setSelection(-1);
        this.lstCreateLines.setSelection(-1);
        this.addGroupsBtn.setEnabled(state);
        this.addAxisBtn.setEnabled(state);
        this.btnCreateLines.setEnabled(state);
        if (state) {
            this.addGroupsBtn.setText("Add");
            this.addAxisBtn.setText("Add");
            this.btnCreateLines.setText("Add");
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

    protected void createButtonsForButtonBar(Composite parent) {
        this.createButton(parent, 19, "Reset", true);
        this.createButton(parent, 0, IDialogConstants.OK_LABEL, true);
        this.createButton(parent, 1, IDialogConstants.CANCEL_LABEL, false);
    }

    protected void buttonPressed(int buttonId) {
        if (buttonId == 19) {
            System.out.println("graph type: " + this.analysisType);
            LineGraphDialog graph = new LineGraphDialog(this.parentShell, this.analysisType, this.file);
            this.close();
            graph.open();
        }
        super.buttonPressed(buttonId);
    }

    protected void okPressed() {
        boolean errorBarsChecked = false;
        boolean continueWithErrorBars = false;
        if (this.responseVarList.getItemCount() < 1) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"Please add variable(s) from the numeric variables list.");
        } else if (this.axisVarList.getItemCount() < 1) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"Please add x variable from the factors list.");
        } else if (this.lstCreateLines.getItemCount() > 3) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"Please enter three variables only on Create Lines List.");
        } else {
            this.yVars = this.responseVarList.getItems();
            this.mTitle = this.mainTitleText.getText();
            this.xAxisLab = this.categoryAxisText.getText();
            this.lineVars = this.lstCreateLines.getItems();
            this.xVar = this.axisVarList.getItemCount() < 1 ? null : this.axisVarList.getItem(0);
            this.byVar = this.groupsVarList.getItemCount() < 1 ? null : this.groupsVarList.getItem(0);
            if (this.displayErrorBarsBtn.getSelection() && this.displayErrorBarsBtn.getEnabled()) {
                this.errBars = "TRUE";
                errorBarsChecked = true;
                if (this.confidenceLvlBtn.getSelection()) {
                    this.confLev = (double)this.confidenceLvlValue.getSelection() / Math.pow(10.0, 2.0);
                    this.typeErrBar = "confInt";
                } else if (this.stdErrorBtn.getSelection()) {
                    this.errMult = this.stdErrorValue.getSelection();
                    this.typeErrBar = "stdErr";
                }
            } else {
                this.errBars = "FALSE";
            }
            if (this.btnShowLegend.getSelection()) {
                this.showLeg = "TRUE";
                this.legPos = this.legPositionComo.getText().replaceAll("-", "");
                this.legCol = this.numColsSpinner1.getSelection();
                this.legTitle = !this.legTitleText.getText().isEmpty() ? this.legTitleText.getText() : null;
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
            this.yAxisLab = new String[this.yVars.length];
            this.yMinValue = new String[this.yVars.length];
            this.yMaxValue = new String[this.yVars.length];
            int ctr1 = 0;
            TableItem[] tableItemArray = this.tableAxis.getItems();
            int n = tableItemArray.length;
            int n2 = 0;
            while (n2 < n) {
                TableItem tableAxisItem = tableItemArray[n2];
                TableEditor[] axisEditors = (TableEditor[])tableAxisItem.getData("Editors");
                this.yAxisLab[ctr1] = ((Text)axisEditors[0].getEditor()).getText();
                this.yMinValue[ctr1] = GraphsUtilities.checkIfAutoOrNumeric(((Text)axisEditors[1].getEditor()).getText());
                this.yMaxValue[ctr1] = GraphsUtilities.checkIfAutoOrNumeric(((Text)axisEditors[2].getEditor()).getText());
                ++ctr1;
                ++n2;
            }
            if (this.table.getItemCount() > 0) {
                this.plotCol = new String[this.table.getItemCount()];
                this.linePtTypes = new String[this.table.getItemCount()];
                this.lineTypes = new int[this.table.getItemCount()];
                this.lineWidths = new double[this.table.getItemCount()];
                this.pointChars = new String[this.table.getItemCount()];
                this.pointCharSizes = new double[this.table.getItemCount()];
            }
            this.showLineLabels = this.btnDisplaylinelabels.getSelection() ? "TRUE" : "FALSE";
            int ctr = 0;
            ArrayList<String> arrPlotCol = new ArrayList<String>();
            TableItem[] tableItemArray2 = this.table.getItems();
            int axisEditors = tableItemArray2.length;
            int n3 = 0;
            while (n3 < axisEditors) {
                TableItem tableItem = tableItemArray2[n3];
                int index = ctr++;
                TableEditor[] editors = (TableEditor[])tableItem.getData("Editors");
                this.linePtTypes[index] = ((CCombo)editors[0].getEditor()).getText().substring(0, 1);
                this.lineTypes[index] = (Integer)editors[2].getEditor().getData("lineType");
                System.out.println(String.valueOf(Integer.toString(index)) + " : this linePtTypes " + this.linePtTypes[index]);
                this.lineWidths[index] = this.dataManipulationManager.convertInttoDouble(((Spinner)editors[4].getEditor()).getSelection(), 1);
                this.pointChars[index] = (String)editors[5].getEditor().getData("pointChars");
                this.pointCharSizes[index] = this.dataManipulationManager.convertInttoDouble(((Spinner)editors[7].getEditor()).getSelection(), 1);
                arrPlotCol.add(GraphsUtilities.convertToRrgbFormat(tableItem.getBackground(2).getRGB().toString()));
                ++n3;
            }
            if (!arrPlotCol.isEmpty()) {
                this.plotCol = arrPlotCol.toArray(new String[arrPlotCol.size()]);
            }
            String dataFileName = this.file.getAbsolutePath().replaceAll("\\\\", "/");
            this.axisLabelStyle = this.cmboAxisOrientation.getSelectionIndex();
            if (this.axisVarList.getItemCount() < 1) {
                this.xVar = null;
            } else {
                this.xVar = this.axisVarList.getItem(0);
                if (errorBarsChecked) {
                    boolean hasLevelsWithOneDatum = false;
                    StringBuilder sb = new StringBuilder();
                    String[] stringArray = this.yVars;
                    int n4 = this.yVars.length;
                    int n5 = 0;
                    while (n5 < n4) {
                        String nvar = stringArray[n5];
                        String[] levelsWithOneValue = ProjectExplorerView.rJavaManager.getRJavaDataManipulationManager().checkLevelsWithOneDatum(dataFileName.replaceAll("\\\\", "/"), this.xVar, nvar, this.byVar);
                        System.out.println("value of levels with one value: " + levelsWithOneValue[0]);
                        if (levelsWithOneValue[0].equals("FALSE")) {
                            hasLevelsWithOneDatum = true;
                        } else if (!levelsWithOneValue[0].equals("TRUE") && !levelsWithOneValue[0].equals("FALSE")) {
                            hasLevelsWithOneDatum = true;
                            sb.append("\n");
                            sb.append(nvar);
                            sb.append(" [Group: No. of level(s)]\n");
                            String[] stringArray2 = levelsWithOneValue;
                            int n6 = levelsWithOneValue.length;
                            int n7 = 0;
                            while (n7 < n6) {
                                String s = stringArray2[n7];
                                sb.append(s);
                                sb.append("\n");
                                ++n7;
                            }
                        }
                        ++n5;
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
                }
            }
            if (this.errBars.equals("FALSE") || continueWithErrorBars) {
                File outputFolder = GraphsUtilities.createOutputFolder(this.file.getName(), this.analysisType);
                if (!outputFolder.exists()) {
                    outputFolder.mkdir();
                }
                OperationProgressDialog rInfo = new OperationProgressDialog(this.getShell(), "Creating Line Graph");
                rInfo.open();
                ProjectExplorerView.rJavaManager.getRJavaGraphManager().createGraphLineplot(String.valueOf(outputFolder.getAbsolutePath().replaceAll("\\\\", "/")) + "/", dataFileName, this.yVars, this.xVar, this.lineVars, this.mTitle, this.yAxisLab, this.xAxisLab, this.yMinValue, this.yMaxValue, this.axisLabelStyle, this.byVar, this.errBars, this.typeErrBar, this.errMult, this.confLev, this.plotCol, this.showLineLabels, this.showLeg, this.legPos, this.legTitle, this.legCol, this.boxed, this.linePtTypes, this.lineTypes, this.lineWidths, this.pointChars, this.pointCharSizes, this.multGraphs, this.numRowsGraphs, this.numColsGraphs, this.orientGraphs);
                rInfo.close();
                WindowDialogControlUtil.hideAllWindowDialog();
                GraphsUtilities.openFolder(outputFolder);
            }
        }
    }

    protected Point getInitialSize() {
        return new Point(741, 612);
    }

    protected boolean isResizable() {
        return true;
    }
}

