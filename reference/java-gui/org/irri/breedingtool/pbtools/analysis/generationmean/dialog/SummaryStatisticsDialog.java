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
 *  org.eclipse.swt.graphics.Point
 *  org.eclipse.swt.layout.GridData
 *  org.eclipse.swt.layout.GridLayout
 *  org.eclipse.swt.widgets.Button
 *  org.eclipse.swt.widgets.Composite
 *  org.eclipse.swt.widgets.Control
 *  org.eclipse.swt.widgets.Event
 *  org.eclipse.swt.widgets.FileDialog
 *  org.eclipse.swt.widgets.Group
 *  org.eclipse.swt.widgets.Label
 *  org.eclipse.swt.widgets.Layout
 *  org.eclipse.swt.widgets.List
 *  org.eclipse.swt.widgets.Listener
 *  org.eclipse.swt.widgets.Shell
 *  org.eclipse.swt.widgets.Text
 */
package org.irri.breedingtool.pbtools.analysis.generationmean.dialog;

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
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.irri.breedingtool.application.model.ProjectExplorerTreeNodeModel;
import org.irri.breedingtool.datamanipulation.dialog.OperationProgressDialog;
import org.irri.breedingtool.manager.impl.DataManipulationManager;
import org.irri.breedingtool.projectexplorer.view.ProjectExplorerView;
import org.irri.breedingtool.utility.PBToolAnalysisUtilities;
import org.irri.breedingtool.utility.WindowDialogControlUtil;

public class SummaryStatisticsDialog
extends Dialog {
    private ProjectExplorerTreeNodeModel fileModel;
    private ArrayList<String> varInfo;
    private String[] numericVariables;
    private String[] factorVariables;
    private File file;
    private DataManipulationManager dataManipulationManager = new DataManipulationManager();
    private FileDialog fd;
    private String generationMean = "NULL";
    private String weights = "NULL";
    private String generationStandardDeviation = "NULL";
    private String numberObservations = "NULL";
    private String generation = "NULL";
    private Text levelOfSignificance;
    private Text fGenText;
    private Text bc1GenText;
    private Text bc2GenText;
    private List varList;
    private String[] allVariables;
    private Button btnWithComputedWeights;
    private Label lblWeights;
    private Button addWeightBtn;
    private List weightVarList;
    private Button addMeanBtn;
    private Button addStdDevBtn;
    private Button addNumOfObsBtn;
    private Button addGenBtn;
    private Label lblGen;
    private Label lblNumberOfObservations;
    private Label lblStandardDeviation;
    private Label lblMean;
    private List meanVarList;
    private List stdDevVarList;
    private List numOfObsVarList;
    private List genVarList;
    private Group grpRecordingGenerationLevels;
    private List generationLevels;
    private Button btnP1;
    private Button btnP2;
    private Button btnF;
    private Button btnBc1;
    private Button btnBc2;
    private List notationList;
    private Button removeNotationBtn;
    protected String[] genotypeLevels;
    private ArrayList<String> userNotationList = new ArrayList();
    private ArrayList<String> convertedNotationList = new ArrayList();
    private Button btnNoComputedWeights;
    private String analysisType;
    private Button addBtn;

    public SummaryStatisticsDialog(Shell parentShell, String analysisType, File file) {
        super(parentShell);
        this.setShellStyle(2256);
        this.setBlockOnOpen(false);
        this.analysisType = analysisType;
        this.file = file;
        this.setFactors();
        this.allVariables = this.dataManipulationManager.getListItems(this.varInfo);
    }

    protected Control createDialogArea(Composite parent) {
        this.getShell().setData("analysis", (Object)this.analysisType);
        this.getShell().setData("filePathID", (Object)this.file.getAbsolutePath());
        WindowDialogControlUtil.addWindowDialogToList(this.getShell());
        parent.addDisposeListener(new DisposeListener(){

            public void widgetDisposed(DisposeEvent e) {
                WindowDialogControlUtil.removeWindowDialogToList(SummaryStatisticsDialog.this.getShell());
            }
        });
        this.fd = new FileDialog(this.getShell(), 4096);
        this.fd.setText("Choose File Input");
        String[] filterExt = new String[]{"*.csv"};
        this.fd.setFilterExtensions(filterExt);
        parent.getShell().setText("Generation Mean Analysis(Summary Statistics): " + this.dataManipulationManager.getDisplayFileName(this.file.getAbsolutePath()));
        Composite container = (Composite)super.createDialogArea(parent);
        GridLayout gridLayout = (GridLayout)container.getLayout();
        gridLayout.numColumns = 5;
        Group grpStatisticalWieghts = new Group(container, 0);
        grpStatisticalWieghts.setLayout((Layout)new GridLayout(2, false));
        grpStatisticalWieghts.setLayoutData((Object)new GridData(4, 4, false, false, 5, 1));
        grpStatisticalWieghts.setText("Statistical Weights");
        this.btnNoComputedWeights = new Button((Composite)grpStatisticalWieghts, 16);
        this.btnNoComputedWeights.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                SummaryStatisticsDialog.this.lblWeights.setEnabled(false);
                SummaryStatisticsDialog.this.weightVarList.setEnabled(false);
                SummaryStatisticsDialog.this.weights = "NULL";
                if (SummaryStatisticsDialog.this.weightVarList.getItemCount() > 0) {
                    SummaryStatisticsDialog.this.varList.add(SummaryStatisticsDialog.this.weightVarList.getItems()[0]);
                    SummaryStatisticsDialog.this.weightVarList.removeAll();
                }
                SummaryStatisticsDialog.this.addWeightBtn.setEnabled(false);
                SummaryStatisticsDialog.this.lblStandardDeviation.setEnabled(true);
                SummaryStatisticsDialog.this.stdDevVarList.setEnabled(true);
                SummaryStatisticsDialog.this.addStdDevBtn.setEnabled(true);
                SummaryStatisticsDialog.this.lblNumberOfObservations.setEnabled(true);
                SummaryStatisticsDialog.this.numOfObsVarList.setEnabled(true);
                SummaryStatisticsDialog.this.addNumOfObsBtn.setEnabled(true);
            }
        });
        this.btnNoComputedWeights.setSelection(true);
        this.btnNoComputedWeights.setText("No computed Weights");
        this.btnWithComputedWeights = new Button((Composite)grpStatisticalWieghts, 16);
        this.btnWithComputedWeights.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                SummaryStatisticsDialog.this.lblWeights.setEnabled(true);
                SummaryStatisticsDialog.this.weightVarList.setEnabled(true);
                SummaryStatisticsDialog.this.lblStandardDeviation.setEnabled(false);
                SummaryStatisticsDialog.this.stdDevVarList.setEnabled(false);
                SummaryStatisticsDialog.this.generationStandardDeviation = "NULL";
                if (SummaryStatisticsDialog.this.stdDevVarList.getItemCount() > 0) {
                    SummaryStatisticsDialog.this.varList.add(SummaryStatisticsDialog.this.stdDevVarList.getItems()[0]);
                    SummaryStatisticsDialog.this.stdDevVarList.removeAll();
                }
                SummaryStatisticsDialog.this.addStdDevBtn.setEnabled(false);
                SummaryStatisticsDialog.this.lblNumberOfObservations.setEnabled(false);
                SummaryStatisticsDialog.this.numOfObsVarList.setEnabled(false);
                SummaryStatisticsDialog.this.addNumOfObsBtn.setEnabled(false);
                SummaryStatisticsDialog.this.numberObservations = "NULL";
                if (SummaryStatisticsDialog.this.numOfObsVarList.getItemCount() > 0) {
                    SummaryStatisticsDialog.this.varList.add(SummaryStatisticsDialog.this.numOfObsVarList.getItems()[0]);
                    SummaryStatisticsDialog.this.numOfObsVarList.removeAll();
                }
            }
        });
        this.btnWithComputedWeights.setText("With Computed Weights");
        Label lblNewLabel = new Label(container, 0);
        lblNewLabel.setLayoutData((Object)new GridData(16384, 0x1000000, false, false, 2, 1));
        lblNewLabel.setText("Variables:");
        new Label(container, 0);
        new Label(container, 0);
        new Label(container, 0);
        this.varList = new List(container, 2816);
        GridData gd_varList = new GridData(4, 4, true, false, 2, 5);
        gd_varList.heightHint = 153;
        this.varList.setLayoutData((Object)gd_varList);
        this.varList.setItems(this.allVariables);
        this.varList.addListener(3, new Listener(){

            public void handleEvent(Event event) {
                SummaryStatisticsDialog.this.enableAddVariableButtons(false);
                if (SummaryStatisticsDialog.this.varList.getSelectionIndex() > -1) {
                    SummaryStatisticsDialog.this.enableAddVariableButtons(true);
                }
            }
        });
        this.addMeanBtn = new Button(container, 0);
        this.addMeanBtn.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (SummaryStatisticsDialog.this.varList.getSelectionCount() > 0) {
                    String[] selectedStrings = SummaryStatisticsDialog.this.varList.getSelection();
                    SummaryStatisticsDialog.this.dataManipulationManager.moveSelectedStringFromTo(SummaryStatisticsDialog.this.varList, SummaryStatisticsDialog.this.meanVarList);
                    SummaryStatisticsDialog.this.generationMean = selectedStrings[0];
                    SummaryStatisticsDialog.this.addMeanBtn.setEnabled(false);
                } else {
                    SummaryStatisticsDialog.this.generationMean = "NULL";
                    SummaryStatisticsDialog.this.dataManipulationManager.moveSelectedStringFromTo(SummaryStatisticsDialog.this.meanVarList, SummaryStatisticsDialog.this.varList);
                }
                SummaryStatisticsDialog.this.enableAddVariableButtons(false);
            }
        });
        GridData gd_addMeanBtn = new GridData(16384, 0x1000000, false, false, 1, 1);
        gd_addMeanBtn.widthHint = 52;
        this.addMeanBtn.setLayoutData((Object)gd_addMeanBtn);
        this.addMeanBtn.setText("Add");
        this.lblMean = new Label(container, 0);
        this.lblMean.setText("Mean:");
        this.meanVarList = new List(container, 2048);
        GridData gd_meanVarList = new GridData(4, 0x1000000, true, false, 1, 1);
        gd_meanVarList.heightHint = 22;
        this.meanVarList.setLayoutData((Object)gd_meanVarList);
        this.meanVarList.addMouseListener(new MouseListener(){

            public void mouseDoubleClick(MouseEvent e) {
                SummaryStatisticsDialog.this.generationMean = "NULL";
                SummaryStatisticsDialog.this.dataManipulationManager.moveSelectedStringFromTo(SummaryStatisticsDialog.this.meanVarList, SummaryStatisticsDialog.this.varList);
                SummaryStatisticsDialog.this.addMeanBtn.setEnabled(false);
            }

            public void mouseDown(MouseEvent e) {
                if (SummaryStatisticsDialog.this.meanVarList.getSelectionCount() > 0) {
                    SummaryStatisticsDialog.this.varList.setSelection(-1);
                    SummaryStatisticsDialog.this.addMeanBtn.setText("Remove");
                    SummaryStatisticsDialog.this.addMeanBtn.setEnabled(true);
                }
            }

            public void mouseUp(MouseEvent e) {
            }
        });
        this.addWeightBtn = new Button(container, 0);
        this.addWeightBtn.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (SummaryStatisticsDialog.this.varList.getSelectionCount() > 0) {
                    String[] selectedStrings = SummaryStatisticsDialog.this.varList.getSelection();
                    SummaryStatisticsDialog.this.dataManipulationManager.moveSelectedStringFromTo(SummaryStatisticsDialog.this.varList, SummaryStatisticsDialog.this.weightVarList);
                    SummaryStatisticsDialog.this.weights = selectedStrings[0];
                    SummaryStatisticsDialog.this.addWeightBtn.setEnabled(false);
                } else {
                    SummaryStatisticsDialog.this.weights = "NULL";
                    SummaryStatisticsDialog.this.dataManipulationManager.moveSelectedStringFromTo(SummaryStatisticsDialog.this.weightVarList, SummaryStatisticsDialog.this.varList);
                }
                SummaryStatisticsDialog.this.enableAddVariableButtons(false);
            }
        });
        this.addWeightBtn.setEnabled(false);
        GridData gd_addWeightBtn = new GridData(16384, 0x1000000, false, false, 1, 1);
        gd_addWeightBtn.widthHint = 52;
        this.addWeightBtn.setLayoutData((Object)gd_addWeightBtn);
        this.addWeightBtn.setText("Add");
        this.lblWeights = new Label(container, 0);
        this.lblWeights.setEnabled(false);
        this.lblWeights.setText("Weights:");
        this.weightVarList = new List(container, 2048);
        this.weightVarList.setEnabled(false);
        GridData gd_weightVarList = new GridData(4, 0x1000000, true, false, 1, 1);
        gd_weightVarList.heightHint = 22;
        this.weightVarList.setLayoutData((Object)gd_weightVarList);
        this.weightVarList.addMouseListener(new MouseListener(){

            public void mouseDoubleClick(MouseEvent e) {
                SummaryStatisticsDialog.this.weights = "NULL";
                SummaryStatisticsDialog.this.dataManipulationManager.moveSelectedStringFromTo(SummaryStatisticsDialog.this.weightVarList, SummaryStatisticsDialog.this.varList);
                SummaryStatisticsDialog.this.addWeightBtn.setEnabled(false);
            }

            public void mouseDown(MouseEvent e) {
                if (SummaryStatisticsDialog.this.weightVarList.getSelectionCount() > 0) {
                    SummaryStatisticsDialog.this.varList.setSelection(-1);
                    SummaryStatisticsDialog.this.addWeightBtn.setText("Remove");
                    SummaryStatisticsDialog.this.addWeightBtn.setEnabled(true);
                }
            }

            public void mouseUp(MouseEvent e) {
            }
        });
        this.addStdDevBtn = new Button(container, 0);
        this.addStdDevBtn.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (SummaryStatisticsDialog.this.varList.getSelectionCount() > 0) {
                    String[] selectedStrings = SummaryStatisticsDialog.this.varList.getSelection();
                    SummaryStatisticsDialog.this.dataManipulationManager.moveSelectedStringFromTo(SummaryStatisticsDialog.this.varList, SummaryStatisticsDialog.this.stdDevVarList);
                    SummaryStatisticsDialog.this.generationStandardDeviation = selectedStrings[0];
                    SummaryStatisticsDialog.this.addStdDevBtn.setEnabled(false);
                } else {
                    SummaryStatisticsDialog.this.generationStandardDeviation = "NULL";
                    SummaryStatisticsDialog.this.dataManipulationManager.moveSelectedStringFromTo(SummaryStatisticsDialog.this.stdDevVarList, SummaryStatisticsDialog.this.varList);
                }
                SummaryStatisticsDialog.this.enableAddVariableButtons(false);
            }
        });
        GridData gd_addStdDevBtn = new GridData(16384, 0x1000000, false, false, 1, 1);
        gd_addStdDevBtn.widthHint = 52;
        this.addStdDevBtn.setLayoutData((Object)gd_addStdDevBtn);
        this.addStdDevBtn.setText("Add");
        this.lblStandardDeviation = new Label(container, 0);
        this.lblStandardDeviation.setText("Standard Deviation:");
        this.stdDevVarList = new List(container, 2048);
        GridData gd_stdDevVarList = new GridData(4, 0x1000000, true, false, 1, 1);
        gd_stdDevVarList.heightHint = 22;
        this.stdDevVarList.setLayoutData((Object)gd_stdDevVarList);
        this.stdDevVarList.addMouseListener(new MouseListener(){

            public void mouseDoubleClick(MouseEvent e) {
                SummaryStatisticsDialog.this.generationStandardDeviation = "NULL";
                SummaryStatisticsDialog.this.dataManipulationManager.moveSelectedStringFromTo(SummaryStatisticsDialog.this.stdDevVarList, SummaryStatisticsDialog.this.varList);
                SummaryStatisticsDialog.this.addStdDevBtn.setEnabled(false);
            }

            public void mouseDown(MouseEvent e) {
                if (SummaryStatisticsDialog.this.stdDevVarList.getSelectionCount() > 0) {
                    SummaryStatisticsDialog.this.varList.setSelection(-1);
                    SummaryStatisticsDialog.this.addStdDevBtn.setText("Remove");
                    SummaryStatisticsDialog.this.addStdDevBtn.setEnabled(true);
                }
            }

            public void mouseUp(MouseEvent e) {
            }
        });
        this.addNumOfObsBtn = new Button(container, 0);
        this.addNumOfObsBtn.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (SummaryStatisticsDialog.this.varList.getSelectionCount() > 0) {
                    String[] selectedStrings = SummaryStatisticsDialog.this.varList.getSelection();
                    SummaryStatisticsDialog.this.dataManipulationManager.moveSelectedStringFromTo(SummaryStatisticsDialog.this.varList, SummaryStatisticsDialog.this.numOfObsVarList);
                    SummaryStatisticsDialog.this.numberObservations = selectedStrings[0];
                    SummaryStatisticsDialog.this.addNumOfObsBtn.setEnabled(false);
                } else {
                    SummaryStatisticsDialog.this.numberObservations = "NULL";
                    SummaryStatisticsDialog.this.dataManipulationManager.moveSelectedStringFromTo(SummaryStatisticsDialog.this.numOfObsVarList, SummaryStatisticsDialog.this.varList);
                }
                SummaryStatisticsDialog.this.enableAddVariableButtons(false);
            }
        });
        GridData gd_addNumOfObsBtn = new GridData(16384, 0x1000000, false, false, 1, 1);
        gd_addNumOfObsBtn.widthHint = 52;
        this.addNumOfObsBtn.setLayoutData((Object)gd_addNumOfObsBtn);
        this.addNumOfObsBtn.setText("Add");
        this.lblNumberOfObservations = new Label(container, 0);
        this.lblNumberOfObservations.setText("Number of Observations:");
        this.numOfObsVarList = new List(container, 2048);
        GridData gd_numOfObsVarList = new GridData(4, 0x1000000, true, false, 1, 1);
        gd_numOfObsVarList.heightHint = 22;
        this.numOfObsVarList.setLayoutData((Object)gd_numOfObsVarList);
        this.numOfObsVarList.addMouseListener(new MouseListener(){

            public void mouseDoubleClick(MouseEvent e) {
                SummaryStatisticsDialog.this.numberObservations = "NULL";
                SummaryStatisticsDialog.this.dataManipulationManager.moveSelectedStringFromTo(SummaryStatisticsDialog.this.numOfObsVarList, SummaryStatisticsDialog.this.varList);
                SummaryStatisticsDialog.this.addNumOfObsBtn.setEnabled(false);
            }

            public void mouseDown(MouseEvent e) {
                if (SummaryStatisticsDialog.this.numOfObsVarList.getSelectionCount() > 0) {
                    SummaryStatisticsDialog.this.varList.setSelection(-1);
                    SummaryStatisticsDialog.this.addNumOfObsBtn.setText("Remove");
                    SummaryStatisticsDialog.this.addNumOfObsBtn.setEnabled(true);
                }
            }

            public void mouseUp(MouseEvent e) {
            }
        });
        this.addGenBtn = new Button(container, 0);
        this.addGenBtn.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (SummaryStatisticsDialog.this.varList.getSelectionCount() > 0) {
                    String[] selectedStrings = SummaryStatisticsDialog.this.varList.getSelection();
                    SummaryStatisticsDialog.this.genotypeLevels = DataManipulationManager.getEnvtLevels(selectedStrings[0], SummaryStatisticsDialog.this.file);
                    SummaryStatisticsDialog.this.dataManipulationManager.moveSelectedStringFromTo(SummaryStatisticsDialog.this.varList, SummaryStatisticsDialog.this.genVarList);
                    SummaryStatisticsDialog.this.generation = selectedStrings[0];
                    SummaryStatisticsDialog.this.addGenBtn.setEnabled(false);
                    SummaryStatisticsDialog.this.grpRecordingGenerationLevels.setEnabled(true);
                    SummaryStatisticsDialog.this.generationLevels.setItems(SummaryStatisticsDialog.this.genotypeLevels);
                } else {
                    SummaryStatisticsDialog.this.generation = "NULL";
                    SummaryStatisticsDialog.this.dataManipulationManager.moveSelectedStringFromTo(SummaryStatisticsDialog.this.genVarList, SummaryStatisticsDialog.this.varList);
                    SummaryStatisticsDialog.this.grpRecordingGenerationLevels.setEnabled(false);
                    SummaryStatisticsDialog.this.generationLevels.removeAll();
                    SummaryStatisticsDialog.this.notationList.removeAll();
                }
                SummaryStatisticsDialog.this.enableAddVariableButtons(false);
            }
        });
        GridData gd_addGenBtn = new GridData(16384, 0x1000000, false, false, 1, 1);
        gd_addGenBtn.widthHint = 52;
        this.addGenBtn.setLayoutData((Object)gd_addGenBtn);
        this.addGenBtn.setText("Add");
        this.lblGen = new Label(container, 0);
        this.lblGen.setText("Generation:");
        this.genVarList = new List(container, 2048);
        GridData gd_genVarList = new GridData(4, 0x1000000, true, false, 1, 1);
        gd_genVarList.heightHint = 22;
        this.genVarList.setLayoutData((Object)gd_genVarList);
        this.genVarList.addMouseListener(new MouseListener(){

            public void mouseDoubleClick(MouseEvent e) {
                SummaryStatisticsDialog.this.generation = "NULL";
                SummaryStatisticsDialog.this.dataManipulationManager.moveSelectedStringFromTo(SummaryStatisticsDialog.this.genVarList, SummaryStatisticsDialog.this.varList);
                SummaryStatisticsDialog.this.grpRecordingGenerationLevels.setEnabled(false);
                SummaryStatisticsDialog.this.generationLevels.removeAll();
                SummaryStatisticsDialog.this.notationList.removeAll();
                SummaryStatisticsDialog.this.addGenBtn.setEnabled(false);
            }

            public void mouseDown(MouseEvent e) {
                if (SummaryStatisticsDialog.this.genVarList.getSelectionCount() > 0) {
                    SummaryStatisticsDialog.this.varList.setSelection(-1);
                    SummaryStatisticsDialog.this.addGenBtn.setText("Remove");
                    SummaryStatisticsDialog.this.addGenBtn.setEnabled(true);
                }
            }

            public void mouseUp(MouseEvent e) {
            }
        });
        this.grpRecordingGenerationLevels = new Group(container, 0);
        this.grpRecordingGenerationLevels.setEnabled(false);
        this.grpRecordingGenerationLevels.setText("Recording Generation Levels:");
        this.grpRecordingGenerationLevels.setLayout((Layout)new GridLayout(4, false));
        this.grpRecordingGenerationLevels.setLayoutData((Object)new GridData(4, 4, true, true, 5, 1));
        Label lblNewLabel_2 = new Label((Composite)this.grpRecordingGenerationLevels, 0);
        lblNewLabel_2.setText("Generation Levels:");
        Label lblNewLabel_3 = new Label((Composite)this.grpRecordingGenerationLevels, 64);
        GridData gd_lblNewLabel_3 = new GridData(4, 0x1000000, false, false, 1, 1);
        gd_lblNewLabel_3.widthHint = 159;
        gd_lblNewLabel_3.heightHint = 16;
        lblNewLabel_3.setLayoutData((Object)gd_lblNewLabel_3);
        lblNewLabel_3.setText("Convert to General Notation of:");
        new Label((Composite)this.grpRecordingGenerationLevels, 0);
        Label lblNotationList = new Label((Composite)this.grpRecordingGenerationLevels, 0);
        GridData gd_lblNotationList = new GridData(16384, 0x1000000, false, false, 1, 1);
        gd_lblNotationList.widthHint = 73;
        lblNotationList.setLayoutData((Object)gd_lblNotationList);
        lblNotationList.setText("Notation List:       ");
        this.generationLevels = new List((Composite)this.grpRecordingGenerationLevels, 2816);
        GridData gd_generationLevels = new GridData(4, 4, true, true, 1, 4);
        gd_generationLevels.widthHint = 88;
        this.generationLevels.setLayoutData((Object)gd_generationLevels);
        this.generationLevels.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                SummaryStatisticsDialog.this.notationList.setSelection(-1);
                SummaryStatisticsDialog.this.removeNotationBtn.setEnabled(false);
                SummaryStatisticsDialog.this.addBtn.setEnabled(true);
            }
        });
        Group group = new Group((Composite)this.grpRecordingGenerationLevels, 0);
        group.setLayoutData((Object)new GridData(4, 4, true, true, 1, 4));
        group.setLayout((Layout)new GridLayout(4, false));
        this.btnP1 = new Button((Composite)group, 16);
        this.btnP1.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                SummaryStatisticsDialog.this.activateGeneralNotationText();
            }
        });
        this.btnP1.setSelection(true);
        this.btnP1.setText("P1");
        new Label((Composite)group, 0);
        new Label((Composite)group, 0);
        new Label((Composite)group, 0);
        this.btnP2 = new Button((Composite)group, 16);
        this.btnP2.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                SummaryStatisticsDialog.this.activateGeneralNotationText();
            }
        });
        this.btnP2.setText("P2");
        new Label((Composite)group, 0);
        new Label((Composite)group, 0);
        new Label((Composite)group, 0);
        this.btnF = new Button((Composite)group, 16);
        this.btnF.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                SummaryStatisticsDialog.this.activateGeneralNotationText();
            }
        });
        this.btnF.setText("F");
        Label lblGeneration = new Label((Composite)group, 0);
        lblGeneration.setEnabled(true);
        lblGeneration.setLayoutData((Object)new GridData(16384, 0x1000000, false, false, 2, 1));
        lblGeneration.setText("Generation:");
        this.fGenText = new Text((Composite)group, 2048);
        this.fGenText.setEnabled(false);
        GridData gd_fGenText = new GridData(4, 0x1000000, false, true, 1, 1);
        gd_fGenText.widthHint = 15;
        this.fGenText.setLayoutData((Object)gd_fGenText);
        this.btnBc1 = new Button((Composite)group, 16);
        this.btnBc1.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                SummaryStatisticsDialog.this.activateGeneralNotationText();
            }
        });
        this.btnBc1.setText("BC1");
        Label label = new Label((Composite)group, 0);
        label.setEnabled(true);
        label.setLayoutData((Object)new GridData(16384, 0x1000000, false, false, 2, 1));
        label.setText("Generation:");
        this.bc1GenText = new Text((Composite)group, 2048);
        this.bc1GenText.setEnabled(false);
        this.bc1GenText.setLayoutData((Object)new GridData(4, 0x1000000, true, true, 1, 1));
        this.btnBc2 = new Button((Composite)group, 16);
        this.btnBc2.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                SummaryStatisticsDialog.this.activateGeneralNotationText();
            }
        });
        this.btnBc2.setText("BC2");
        Label label_1 = new Label((Composite)group, 0);
        label_1.setEnabled(true);
        label_1.setLayoutData((Object)new GridData(16384, 0x1000000, false, false, 2, 1));
        label_1.setText("Generation:");
        this.bc2GenText = new Text((Composite)group, 2048);
        this.bc2GenText.setEnabled(false);
        this.bc2GenText.setLayoutData((Object)new GridData(4, 0x1000000, true, true, 1, 1));
        new Label((Composite)this.grpRecordingGenerationLevels, 0);
        this.notationList = new List((Composite)this.grpRecordingGenerationLevels, 2816);
        GridData gd_notationList = new GridData(4, 4, true, true, 1, 4);
        gd_notationList.widthHint = 70;
        this.notationList.setLayoutData((Object)gd_notationList);
        this.notationList.addMouseListener(new MouseListener(){

            public void mouseDoubleClick(MouseEvent e) {
                if (SummaryStatisticsDialog.this.notationList.getSelectionCount() > 0) {
                    SummaryStatisticsDialog.this.generationLevels.add((String)SummaryStatisticsDialog.this.userNotationList.get(SummaryStatisticsDialog.this.notationList.getSelectionIndex()));
                    SummaryStatisticsDialog.this.userNotationList.remove(SummaryStatisticsDialog.this.userNotationList.get(SummaryStatisticsDialog.this.notationList.getSelectionIndex()));
                    SummaryStatisticsDialog.this.convertedNotationList.remove(SummaryStatisticsDialog.this.notationList.getSelectionIndex());
                    SummaryStatisticsDialog.this.notationList.remove(SummaryStatisticsDialog.this.notationList.getSelectionIndex());
                    SummaryStatisticsDialog.this.removeNotationBtn.setEnabled(false);
                }
            }

            public void mouseDown(MouseEvent e) {
                if (SummaryStatisticsDialog.this.notationList.getSelectionCount() > 0) {
                    SummaryStatisticsDialog.this.generationLevels.setSelection(-1);
                    SummaryStatisticsDialog.this.addBtn.setEnabled(false);
                    SummaryStatisticsDialog.this.removeNotationBtn.setEnabled(true);
                }
            }

            public void mouseUp(MouseEvent e) {
            }
        });
        this.addBtn = new Button((Composite)this.grpRecordingGenerationLevels, 0);
        this.addBtn.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                String[] genLevels = SummaryStatisticsDialog.this.generationLevels.getSelection();
                if (genLevels.length < 1) {
                    MessageDialog.openError((Shell)SummaryStatisticsDialog.this.getShell(), (String)"Error", (String)"Please select a variable from the generation levels!");
                } else if (SummaryStatisticsDialog.this.btnF.getSelection() && SummaryStatisticsDialog.this.fGenText.getText().isEmpty()) {
                    MessageDialog.openError((Shell)SummaryStatisticsDialog.this.getShell(), (String)"Error", (String)"Please add a generation value for the F general notation!");
                } else if (SummaryStatisticsDialog.this.btnBc1.getSelection() && SummaryStatisticsDialog.this.bc1GenText.getText().isEmpty()) {
                    MessageDialog.openError((Shell)SummaryStatisticsDialog.this.getShell(), (String)"Error", (String)"Please add a generation value for the BC1 general notation!");
                } else if (SummaryStatisticsDialog.this.btnBc2.getSelection() && SummaryStatisticsDialog.this.bc2GenText.getText().isEmpty()) {
                    MessageDialog.openError((Shell)SummaryStatisticsDialog.this.getShell(), (String)"Error", (String)"Please add a generation value for the BC2 general notation!");
                } else {
                    try {
                        if (SummaryStatisticsDialog.this.btnF.getSelection()) {
                            if (Double.parseDouble(SummaryStatisticsDialog.this.fGenText.getText()) < 1.0) {
                                MessageDialog.openWarning((Shell)SummaryStatisticsDialog.this.getShell(), (String)"Invalid Input", (String)"Sorry, the generation value of F should be should be atleast 1.");
                            }
                        } else if (SummaryStatisticsDialog.this.btnBc1.getSelection()) {
                            if (Double.parseDouble(SummaryStatisticsDialog.this.bc1GenText.getText()) < 1.0) {
                                MessageDialog.openWarning((Shell)SummaryStatisticsDialog.this.getShell(), (String)"Invalid Input", (String)"Sorry, the generation value of BC1 should be should be atleast 1.");
                            }
                        } else if (SummaryStatisticsDialog.this.btnBc2.getSelection() && Double.parseDouble(SummaryStatisticsDialog.this.bc2GenText.getText()) < 1.0) {
                            MessageDialog.openWarning((Shell)SummaryStatisticsDialog.this.getShell(), (String)"Invalid Input", (String)"Sorry, the generation value of BC2 should be should be atleast 1.");
                        }
                        String convertedNotation = SummaryStatisticsDialog.this.getConvertedGeneralNotationOf();
                        SummaryStatisticsDialog.this.userNotationList.add(genLevels[0]);
                        SummaryStatisticsDialog.this.convertedNotationList.add(convertedNotation);
                        SummaryStatisticsDialog.this.notationList.add(String.valueOf(genLevels[0]) + "->" + convertedNotation);
                        SummaryStatisticsDialog.this.generationLevels.remove(SummaryStatisticsDialog.this.generationLevels.getSelectionIndex());
                    }
                    catch (NumberFormatException numberFormatException) {
                        MessageDialog.openWarning((Shell)SummaryStatisticsDialog.this.getShell(), (String)"Invalid Format", (String)"Sorry, the generation value should be between 0 and 1.");
                    }
                }
            }
        });
        GridData gd_addBtn = new GridData(4, 0x1000000, false, false, 1, 1);
        gd_addBtn.widthHint = 52;
        this.addBtn.setLayoutData((Object)gd_addBtn);
        this.addBtn.setText("Add");
        this.removeNotationBtn = new Button((Composite)this.grpRecordingGenerationLevels, 0);
        this.removeNotationBtn.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (SummaryStatisticsDialog.this.notationList.getSelectionCount() > 0) {
                    SummaryStatisticsDialog.this.generationLevels.add((String)SummaryStatisticsDialog.this.userNotationList.get(SummaryStatisticsDialog.this.notationList.getSelectionIndex()));
                    SummaryStatisticsDialog.this.userNotationList.remove(SummaryStatisticsDialog.this.userNotationList.get(SummaryStatisticsDialog.this.notationList.getSelectionIndex()));
                    SummaryStatisticsDialog.this.convertedNotationList.remove(SummaryStatisticsDialog.this.notationList.getSelectionIndex());
                    SummaryStatisticsDialog.this.notationList.remove(SummaryStatisticsDialog.this.notationList.getSelectionIndex());
                    SummaryStatisticsDialog.this.removeNotationBtn.setEnabled(false);
                }
            }
        });
        this.removeNotationBtn.setEnabled(false);
        GridData gd_removeNotationBtn = new GridData(4, 0x1000000, false, false, 1, 1);
        gd_removeNotationBtn.widthHint = 78;
        this.removeNotationBtn.setLayoutData((Object)gd_removeNotationBtn);
        this.removeNotationBtn.setText("Remove");
        new Label((Composite)this.grpRecordingGenerationLevels, 0);
        Label lblLevelOfSignificance = new Label(container, 0);
        lblLevelOfSignificance.setLayoutData((Object)new GridData(131072, 0x1000000, false, false, 1, 1));
        lblLevelOfSignificance.setText("Level of Significance:");
        this.levelOfSignificance = new Text(container, 2048);
        this.levelOfSignificance.setText("0.05");
        GridData gd_levelOfSignificance = new GridData(16384, 0x1000000, true, false, 1, 1);
        gd_levelOfSignificance.widthHint = 50;
        this.levelOfSignificance.setLayoutData((Object)gd_levelOfSignificance);
        new Label(container, 0);
        new Label(container, 0);
        new Label(container, 0);
        return container;
    }

    protected String getConvertedGeneralNotationOf() {
        if (this.btnP1.getSelection()) {
            return "B0F0P1";
        }
        if (this.btnP2.getSelection()) {
            return "B0F0P2";
        }
        if (this.btnF.getSelection()) {
            return "B0F" + this.fGenText.getText() + "P0";
        }
        if (this.btnBc1.getSelection()) {
            return "B" + this.bc1GenText.getText() + "F1P1";
        }
        return "B" + this.bc2GenText.getText() + "F1P2";
    }

    protected void activateGeneralNotationText() {
        this.fGenText.setEnabled(false);
        this.bc1GenText.setEnabled(false);
        this.bc2GenText.setEnabled(false);
        if (this.btnF.getSelection()) {
            this.fGenText.setEnabled(true);
        } else if (this.btnBc1.getSelection()) {
            this.bc1GenText.setEnabled(true);
        } else if (this.btnBc2.getSelection()) {
            this.bc2GenText.setEnabled(true);
        }
    }

    protected void enableAddVariableButtons(boolean state) {
        this.meanVarList.setSelection(-1);
        this.stdDevVarList.setSelection(-1);
        this.genVarList.setSelection(-1);
        this.numOfObsVarList.setSelection(-1);
        this.weightVarList.setSelection(-1);
        if (state) {
            this.addMeanBtn.setText("Add");
            this.addNumOfObsBtn.setText("Add");
            this.addGenBtn.setText("Add");
            this.addWeightBtn.setText("Add");
            this.addStdDevBtn.setText("Add");
            if (this.weightVarList.getItemCount() < 1 && this.lblWeights.getEnabled()) {
                this.addWeightBtn.setEnabled(state);
            }
            if (this.numOfObsVarList.getItemCount() < 1 && this.lblNumberOfObservations.getEnabled()) {
                this.addNumOfObsBtn.setEnabled(state);
            }
            if (this.genVarList.getItemCount() < 1) {
                this.addGenBtn.setEnabled(state);
            }
            if (this.stdDevVarList.getItemCount() < 1 && this.lblStandardDeviation.getEnabled()) {
                this.addStdDevBtn.setEnabled(state);
            }
            if (this.meanVarList.getItemCount() < 1 && this.lblMean.getEnabled()) {
                this.addMeanBtn.setEnabled(state);
            }
        } else {
            this.addMeanBtn.setEnabled(state);
            this.addNumOfObsBtn.setEnabled(state);
            this.addGenBtn.setEnabled(state);
            this.addWeightBtn.setEnabled(state);
            this.addStdDevBtn.setEnabled(state);
        }
    }

    protected void setFactors() {
        this.varInfo = this.dataManipulationManager.getVarInfoFromFile(this.file.getAbsolutePath());
        this.numericVariables = this.dataManipulationManager.getNumericVars(this.varInfo);
        this.factorVariables = this.dataManipulationManager.getFactorVars(this.varInfo);
    }

    protected void createButtonsForButtonBar(Composite parent) {
        this.createButton(parent, 0, IDialogConstants.OK_LABEL, true);
        this.createButton(parent, 1, IDialogConstants.CANCEL_LABEL, false);
    }

    protected void okPressed() {
        if (this.meanVarList.getItemCount() < 1) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"Please enter a mean variable.");
        } else if (this.weightVarList.getItemCount() < 1 && this.btnWithComputedWeights.getSelection()) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"Please enter a weight variable.");
        } else if (this.stdDevVarList.getItemCount() < 1 && this.btnNoComputedWeights.getSelection()) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"Please enter a standard deviation variable.");
        } else if (this.numOfObsVarList.getItemCount() < 1 && this.btnNoComputedWeights.getSelection()) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"Please enter a number of observations variable.");
        } else if (this.genVarList.getItemCount() < 1) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"Please enter a generation variable.");
        } else if (this.userNotationList.size() < 1) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"Please specify variables on the notation list.");
        } else {
            try {
                if (Double.parseDouble(this.levelOfSignificance.getText()) <= 0.0 || Double.parseDouble(this.levelOfSignificance.getText()) >= 1.0) {
                    MessageDialog.openWarning((Shell)this.getShell(), (String)"Invalid Input", (String)"Sorry, the level of signifcance should have a value of between 0 and 1.");
                } else if (this.convertedNotationList.isEmpty()) {
                    MessageDialog.openWarning((Shell)this.getShell(), (String)"Empty Field", (String)"Please add variables to the Notation List.");
                } else {
                    String[] usersNotation = this.userNotationList.toArray(new String[this.userNotationList.size()]);
                    String[] generalNotation = this.convertedNotationList.toArray(new String[this.convertedNotationList.size()]);
                    String alpha = this.levelOfSignificance.getText();
                    String dataFileName = this.file.getAbsolutePath().toString().replaceAll("\\\\+", "/");
                    File outputFolder = PBToolAnalysisUtilities.createOutputFolder(this.file.getName(), this.analysisType);
                    String newOutputFileName = String.valueOf(outputFolder.getAbsolutePath().replaceAll("\\\\+", "/")) + "/output.txt";
                    OperationProgressDialog rInfo = new OperationProgressDialog(this.getShell(), "Generation Mean Analysis (Summary as input)");
                    rInfo.open();
                    ProjectExplorerView.rJavaManager.getPbToolAnalysisManager().doGenerationMeanSummaryStats(dataFileName, newOutputFileName, this.generationMean, this.weights, this.generationStandardDeviation, this.numberObservations, this.generation, usersNotation, generalNotation, alpha);
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

    protected Point getInitialSize() {
        return new Point(774, 687);
    }

    protected boolean isResizable() {
        return true;
    }
}

