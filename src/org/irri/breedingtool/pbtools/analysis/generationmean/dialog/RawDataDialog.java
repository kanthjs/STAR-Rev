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
 *  org.eclipse.swt.widgets.FileDialog
 *  org.eclipse.swt.widgets.Group
 *  org.eclipse.swt.widgets.Label
 *  org.eclipse.swt.widgets.Layout
 *  org.eclipse.swt.widgets.List
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
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.irri.breedingtool.application.model.ProjectExplorerTreeNodeModel;
import org.irri.breedingtool.datamanipulation.dialog.OperationProgressDialog;
import org.irri.breedingtool.manager.impl.DataManipulationManager;
import org.irri.breedingtool.projectexplorer.view.ProjectExplorerView;
import org.irri.breedingtool.utility.PBToolAnalysisUtilities;
import org.irri.breedingtool.utility.WindowDialogControlUtil;

public class RawDataDialog
extends Dialog {
    private ProjectExplorerTreeNodeModel fileModel;
    private ArrayList<String> varInfo;
    private String[] numericVariables;
    private String[] factorVariables;
    private File file;
    private DataManipulationManager dataManipulationManager = new DataManipulationManager();
    private FileDialog fd;
    private int selectionIndex = 0;
    private int designIndex = 1;
    private boolean basisCorrelation = true;
    private int percentSelected = 5;
    private String weightsFileName = "";
    private String markersFileName = "";
    private String qtlFileName = "";
    private ArrayList<String> userNotationList = new ArrayList();
    private ArrayList<String> convertedNotationList = new ArrayList();
    private Text levelOfSignificance;
    private Text fGenText;
    private Text bc1GenText;
    private Text bc2GenText;
    private String[] allVariables;
    private Group grpRecordingGenerationLevels;
    private List generationLevels;
    private Button btnP1;
    private Button btnP2;
    private Button btnF;
    private Button btnBc1;
    private Button btnBc2;
    private List notationList;
    private Button removeNotationBtn;
    private String analysisType;
    private Button addBtn;

    public RawDataDialog(Shell parentShell, String analysisType, File file) {
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
                WindowDialogControlUtil.removeWindowDialogToList(RawDataDialog.this.getShell());
            }
        });
        this.fd = new FileDialog(this.getShell(), 4096);
        this.fd.setText("Choose File Input");
        String[] filterExt = new String[]{"*.csv"};
        this.fd.setFilterExtensions(filterExt);
        parent.getShell().setText("Generation Mean Analysis(Raw Data): " + this.dataManipulationManager.getDisplayFileName(this.file.getAbsolutePath()));
        Composite container = (Composite)super.createDialogArea(parent);
        GridLayout gridLayout = (GridLayout)container.getLayout();
        gridLayout.numColumns = 4;
        this.grpRecordingGenerationLevels = new Group(container, 0);
        this.grpRecordingGenerationLevels.setLayout((Layout)new GridLayout(4, false));
        this.grpRecordingGenerationLevels.setLayoutData((Object)new GridData(4, 4, true, true, 4, 1));
        Label lblNewLabel_2 = new Label((Composite)this.grpRecordingGenerationLevels, 0);
        lblNewLabel_2.setText("User's Notation");
        Label lblNewLabel_3 = new Label((Composite)this.grpRecordingGenerationLevels, 64);
        GridData gd_lblNewLabel_3 = new GridData(4, 0x1000000, false, false, 1, 1);
        gd_lblNewLabel_3.widthHint = 159;
        gd_lblNewLabel_3.heightHint = 16;
        lblNewLabel_3.setLayoutData((Object)gd_lblNewLabel_3);
        lblNewLabel_3.setText("Convert to General Notation of:");
        new Label((Composite)this.grpRecordingGenerationLevels, 0);
        Label lblNotationList = new Label((Composite)this.grpRecordingGenerationLevels, 0);
        GridData gd_lblNotationList = new GridData(16384, 0x1000000, false, false, 1, 1);
        gd_lblNotationList.widthHint = 121;
        lblNotationList.setLayoutData((Object)gd_lblNotationList);
        lblNotationList.setText("Notation List:       ");
        this.generationLevels = new List((Composite)this.grpRecordingGenerationLevels, 2816);
        this.generationLevels.setItems(this.allVariables);
        GridData gd_generationLevels = new GridData(4, 4, true, true, 1, 4);
        gd_generationLevels.widthHint = 88;
        this.generationLevels.setLayoutData((Object)gd_generationLevels);
        this.generationLevels.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                RawDataDialog.this.notationList.setSelection(-1);
                RawDataDialog.this.removeNotationBtn.setEnabled(false);
                RawDataDialog.this.addBtn.setEnabled(true);
            }
        });
        Group group = new Group((Composite)this.grpRecordingGenerationLevels, 0);
        group.setLayoutData((Object)new GridData(4, 4, true, true, 1, 4));
        group.setLayout((Layout)new GridLayout(4, false));
        this.btnP1 = new Button((Composite)group, 16);
        this.btnP1.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                RawDataDialog.this.activateGeneralNotationText();
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
                RawDataDialog.this.activateGeneralNotationText();
            }
        });
        this.btnP2.setText("P2");
        new Label((Composite)group, 0);
        new Label((Composite)group, 0);
        new Label((Composite)group, 0);
        this.btnF = new Button((Composite)group, 16);
        GridData gd_btnF = new GridData(4, 0x1000000, false, false, 1, 1);
        gd_btnF.widthHint = 82;
        this.btnF.setLayoutData((Object)gd_btnF);
        this.btnF.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                RawDataDialog.this.activateGeneralNotationText();
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
                RawDataDialog.this.activateGeneralNotationText();
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
                RawDataDialog.this.activateGeneralNotationText();
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
                if (RawDataDialog.this.notationList.getSelectionCount() > 0) {
                    RawDataDialog.this.generationLevels.add((String)RawDataDialog.this.userNotationList.get(RawDataDialog.this.notationList.getSelectionIndex()));
                    RawDataDialog.this.userNotationList.remove(RawDataDialog.this.userNotationList.get(RawDataDialog.this.notationList.getSelectionIndex()));
                    RawDataDialog.this.convertedNotationList.remove(RawDataDialog.this.notationList.getSelectionIndex());
                    RawDataDialog.this.notationList.remove(RawDataDialog.this.notationList.getSelectionIndex());
                    RawDataDialog.this.removeNotationBtn.setEnabled(false);
                }
            }

            public void mouseDown(MouseEvent e) {
                if (RawDataDialog.this.notationList.getSelectionCount() > 0) {
                    RawDataDialog.this.generationLevels.setSelection(-1);
                    RawDataDialog.this.addBtn.setEnabled(false);
                    RawDataDialog.this.removeNotationBtn.setEnabled(true);
                }
            }

            public void mouseUp(MouseEvent e) {
            }
        });
        this.addBtn = new Button((Composite)this.grpRecordingGenerationLevels, 0);
        this.addBtn.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                String[] genLevels = RawDataDialog.this.generationLevels.getSelection();
                if (genLevels.length < 1) {
                    MessageDialog.openError((Shell)RawDataDialog.this.getShell(), (String)"Error", (String)"Please select a variable from the User's Notation list!");
                } else if (RawDataDialog.this.btnF.getSelection() && RawDataDialog.this.fGenText.getText().isEmpty()) {
                    MessageDialog.openError((Shell)RawDataDialog.this.getShell(), (String)"Error", (String)"Please add a generation value for the F general notation!");
                } else if (RawDataDialog.this.btnBc1.getSelection() && RawDataDialog.this.bc1GenText.getText().isEmpty()) {
                    MessageDialog.openError((Shell)RawDataDialog.this.getShell(), (String)"Error", (String)"Please add a generation value for the BC1 general notation!");
                } else if (RawDataDialog.this.btnBc2.getSelection() && RawDataDialog.this.bc2GenText.getText().isEmpty()) {
                    MessageDialog.openError((Shell)RawDataDialog.this.getShell(), (String)"Error", (String)"Please add a generation value for the BC2 general notation!");
                } else {
                    try {
                        if (RawDataDialog.this.btnF.getSelection()) {
                            if (Double.parseDouble(RawDataDialog.this.fGenText.getText()) < 1.0) {
                                MessageDialog.openWarning((Shell)RawDataDialog.this.getShell(), (String)"Invalid Input", (String)"Sorry, the generation value of F should be should be atleast 1.");
                            }
                        } else if (RawDataDialog.this.btnBc1.getSelection()) {
                            if (Double.parseDouble(RawDataDialog.this.bc1GenText.getText()) < 1.0) {
                                MessageDialog.openWarning((Shell)RawDataDialog.this.getShell(), (String)"Invalid Input", (String)"Sorry, the generation value of BC1 should be should be atleast 1.");
                            }
                        } else if (RawDataDialog.this.btnBc2.getSelection() && Double.parseDouble(RawDataDialog.this.bc2GenText.getText()) < 1.0) {
                            MessageDialog.openWarning((Shell)RawDataDialog.this.getShell(), (String)"Invalid Input", (String)"Sorry, the generation value of BC2 should be should be atleast 1.");
                        }
                        String convertedNotation = RawDataDialog.this.getConvertedGeneralNotationOf();
                        RawDataDialog.this.userNotationList.add(genLevels[0]);
                        RawDataDialog.this.convertedNotationList.add(convertedNotation);
                        RawDataDialog.this.notationList.add(String.valueOf(genLevels[0]) + "->" + convertedNotation);
                        RawDataDialog.this.generationLevels.remove(RawDataDialog.this.generationLevels.getSelectionIndex());
                        RawDataDialog.this.generationLevels.setSelection(-1);
                    }
                    catch (NumberFormatException numberFormatException) {
                        MessageDialog.openWarning((Shell)RawDataDialog.this.getShell(), (String)"Invalid Format", (String)"Sorry, the generation value should be a number.");
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
                if (RawDataDialog.this.notationList.getSelectionCount() > 0) {
                    RawDataDialog.this.generationLevels.add((String)RawDataDialog.this.userNotationList.get(RawDataDialog.this.notationList.getSelectionIndex()));
                    RawDataDialog.this.userNotationList.remove(RawDataDialog.this.userNotationList.get(RawDataDialog.this.notationList.getSelectionIndex()));
                    RawDataDialog.this.convertedNotationList.remove(RawDataDialog.this.notationList.getSelectionIndex());
                    RawDataDialog.this.notationList.remove(RawDataDialog.this.notationList.getSelectionIndex());
                    RawDataDialog.this.removeNotationBtn.setEnabled(false);
                }
            }
        });
        this.removeNotationBtn.setEnabled(false);
        GridData gd_removeNotationBtn = new GridData(16384, 0x1000000, false, false, 1, 1);
        gd_removeNotationBtn.widthHint = 77;
        this.removeNotationBtn.setLayoutData((Object)gd_removeNotationBtn);
        this.removeNotationBtn.setText("Remove");
        new Label((Composite)this.grpRecordingGenerationLevels, 0);
        Label lblLevelOfSignificance = new Label(container, 0);
        lblLevelOfSignificance.setLayoutData((Object)new GridData(131072, 0x1000000, false, false, 1, 1));
        lblLevelOfSignificance.setText("Level of Significance:");
        this.levelOfSignificance = new Text(container, 2048);
        this.levelOfSignificance.setText("0.05");
        GridData gd_levelOfSignificance = new GridData(16384, 0x1000000, true, false, 2, 1);
        gd_levelOfSignificance.widthHint = 50;
        this.levelOfSignificance.setLayoutData((Object)gd_levelOfSignificance);
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
                OperationProgressDialog rInfo = new OperationProgressDialog(this.getShell(), "Generation Mean Analysis (Raw Data as input)");
                rInfo.open();
                ProjectExplorerView.rJavaManager.getPbToolAnalysisManager().doGenerationMeanRawData(dataFileName, newOutputFileName, usersNotation, generalNotation, alpha);
                rInfo.close();
                WindowDialogControlUtil.hideAllWindowDialog();
                PBToolAnalysisUtilities.openFolder(outputFolder);
            }
        }
        catch (NumberFormatException numberFormatException) {
            MessageDialog.openWarning((Shell)this.getShell(), (String)"Invalid Format", (String)"The Level of Signifcance should have a numeric value of between 0 and 1.");
        }
    }

    protected Point getInitialSize() {
        return new Point(689, 409);
    }

    protected boolean isResizable() {
        return true;
    }
}

