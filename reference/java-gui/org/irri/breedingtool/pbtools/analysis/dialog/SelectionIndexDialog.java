/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.jface.dialogs.Dialog
 *  org.eclipse.jface.dialogs.IDialogConstants
 *  org.eclipse.jface.dialogs.MessageDialog
 *  org.eclipse.swt.custom.CCombo
 *  org.eclipse.swt.events.DisposeEvent
 *  org.eclipse.swt.events.DisposeListener
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
 *  org.eclipse.swt.widgets.Shell
 *  org.eclipse.swt.widgets.Spinner
 */
package org.irri.breedingtool.pbtools.analysis.dialog;

import java.io.File;
import java.util.ArrayList;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
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
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.wb.swt.SWTResourceManager;
import org.irri.breedingtool.application.model.ProjectExplorerTreeNodeModel;
import org.irri.breedingtool.datamanipulation.dialog.OperationProgressDialog;
import org.irri.breedingtool.manager.impl.DataManipulationManager;
import org.irri.breedingtool.manager.impl.ProjectExplorerManager;
import org.irri.breedingtool.projectexplorer.view.ProjectExplorerView;
import org.irri.breedingtool.utility.PBToolAnalysisUtilities;
import org.irri.breedingtool.utility.WindowDialogControlUtil;

public class SelectionIndexDialog
extends Dialog {
    private ProjectExplorerTreeNodeModel fileModel;
    private ArrayList<String> varInfo;
    private String[] numericVariables;
    private String[] factorVariables;
    private File file;
    private DataManipulationManager dataManipulationManager = new DataManipulationManager();
    private FileDialog fd;
    private int selectionIndex = 0;
    private int designIndex = 0;
    private boolean basisCorrelation = true;
    private int percentSelected = 5;
    private String weightsFileName = "";
    private String markersFileName = "";
    private String qtlFileName = "";
    private Spinner percentSelectedSpinner;
    private String analysisType;
    private Group grpMarkersFilecsv;
    private Group grpQtlFilecsv;
    private CCombo markersFileText;
    private CCombo qtlFileText;
    private CCombo weightsFileText;

    public SelectionIndexDialog(Shell parentShell, String analysisType, File file) {
        super(parentShell);
        this.setShellStyle(2256);
        this.setBlockOnOpen(false);
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
                WindowDialogControlUtil.removeWindowDialogToList(SelectionIndexDialog.this.getShell());
            }
        });
        this.fd = new FileDialog(this.getShell(), 4096);
        this.fd.setText("Choose File Input");
        ProjectExplorerManager projectExplorerManger = new ProjectExplorerManager();
        this.fd.setFilterPath(((ProjectExplorerTreeNodeModel)projectExplorerManger.getDataFolder(ProjectExplorerView.projectTree).getData()).getProjectFile().getAbsolutePath());
        String[] filterExt = new String[]{"*.csv"};
        this.fd.setFilterExtensions(filterExt);
        parent.getShell().setText("Selection Index Analysis: " + this.dataManipulationManager.getDisplayFileName(this.file.getAbsolutePath()));
        Composite container = (Composite)super.createDialogArea(parent);
        GridLayout gridLayout = (GridLayout)container.getLayout();
        gridLayout.numColumns = 2;
        Group grpSelectionIndex = new Group(container, 0);
        grpSelectionIndex.setText("Selection Index");
        grpSelectionIndex.setLayout((Layout)new GridLayout(2, false));
        GridData gd_grpSelectionIndex = new GridData(4, 4, true, true, 2, 1);
        gd_grpSelectionIndex.heightHint = 227;
        grpSelectionIndex.setLayoutData((Object)gd_grpSelectionIndex);
        Label lblPhenotypicSelectionIndices = new Label((Composite)grpSelectionIndex, 0);
        lblPhenotypicSelectionIndices.setLayoutData((Object)new GridData(16384, 0x1000000, false, false, 2, 1));
        lblPhenotypicSelectionIndices.setFont(SWTResourceManager.getFont("Tahoma", 9, 0));
        lblPhenotypicSelectionIndices.setText("Phenotypic Selection Indices");
        new Label((Composite)grpSelectionIndex, 0);
        Button btnSmithSelectionIndex = new Button((Composite)grpSelectionIndex, 16);
        btnSmithSelectionIndex.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                SelectionIndexDialog.this.selectionIndex = 0;
                SelectionIndexDialog.this.enableMarkersAndQTL(false);
            }
        });
        btnSmithSelectionIndex.setSelection(true);
        btnSmithSelectionIndex.setText("Smith Selection Index");
        new Label((Composite)grpSelectionIndex, 0);
        Button btnEigenSelectionIndex = new Button((Composite)grpSelectionIndex, 16);
        btnEigenSelectionIndex.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                SelectionIndexDialog.this.selectionIndex = 1;
                SelectionIndexDialog.this.enableMarkersAndQTL(false);
            }
        });
        btnEigenSelectionIndex.setText("Eigen Selection Index Method (ESIM)");
        new Label((Composite)grpSelectionIndex, 0);
        Button btnRestrictiveKempthorneAnd = new Button((Composite)grpSelectionIndex, 16);
        btnRestrictiveKempthorneAnd.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                SelectionIndexDialog.this.selectionIndex = 2;
                SelectionIndexDialog.this.enableMarkersAndQTL(false);
            }
        });
        btnRestrictiveKempthorneAnd.setText("Restrictive Kempthorne and Nordskog Selection Index");
        new Label((Composite)grpSelectionIndex, 0);
        Button btnRestrictiveEigenSelection = new Button((Composite)grpSelectionIndex, 16);
        btnRestrictiveEigenSelection.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                SelectionIndexDialog.this.selectionIndex = 3;
                SelectionIndexDialog.this.enableMarkersAndQTL(false);
            }
        });
        btnRestrictiveEigenSelection.setText("Restrictive Eigen Selection Index Method");
        Label lblMolecularSelectionIndices = new Label((Composite)grpSelectionIndex, 0);
        GridData gd_lblMolecularSelectionIndices = new GridData(16384, 0x1000000, false, false, 2, 1);
        gd_lblMolecularSelectionIndices.widthHint = 207;
        lblMolecularSelectionIndices.setLayoutData((Object)gd_lblMolecularSelectionIndices);
        lblMolecularSelectionIndices.setText("Molecular Selection Indices");
        lblMolecularSelectionIndices.setFont(SWTResourceManager.getFont("Tahoma", 9, 0));
        new Label((Composite)grpSelectionIndex, 0);
        Button btnLandeAndThompson = new Button((Composite)grpSelectionIndex, 16);
        btnLandeAndThompson.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                SelectionIndexDialog.this.selectionIndex = 4;
                SelectionIndexDialog.this.enableMarkersAndQTL(true);
            }
        });
        btnLandeAndThompson.setText("Lande and Thompson Selection Index");
        new Label((Composite)grpSelectionIndex, 0);
        Button btnMolecularEsim = new Button((Composite)grpSelectionIndex, 16);
        btnMolecularEsim.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                SelectionIndexDialog.this.selectionIndex = 5;
                SelectionIndexDialog.this.enableMarkersAndQTL(true);
            }
        });
        btnMolecularEsim.setText("Molecular ESIM");
        Group grpBasisForSelection = new Group(container, 0);
        grpBasisForSelection.setText("Basis for Selection Index");
        grpBasisForSelection.setLayout((Layout)new GridLayout(1, false));
        GridData gd_grpBasisForSelection = new GridData(4, 4, true, true, 1, 1);
        gd_grpBasisForSelection.heightHint = 82;
        grpBasisForSelection.setLayoutData((Object)gd_grpBasisForSelection);
        Button btnVariancecovarianceMatrix = new Button((Composite)grpBasisForSelection, 16);
        btnVariancecovarianceMatrix.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                SelectionIndexDialog.this.basisCorrelation = false;
            }
        });
        btnVariancecovarianceMatrix.setSelection(true);
        btnVariancecovarianceMatrix.setText("Variance-Covariance Matrix");
        Button btnMatrix = new Button((Composite)grpBasisForSelection, 16);
        btnMatrix.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                SelectionIndexDialog.this.basisCorrelation = true;
            }
        });
        btnMatrix.setLayoutData((Object)new GridData(16384, 128, false, false, 1, 1));
        btnMatrix.setText("Correlation Matrix");
        Group grpDesign = new Group(container, 0);
        grpDesign.setText("Design");
        grpDesign.setLayout((Layout)new GridLayout(1, false));
        GridData gd_grpDesign = new GridData(4, 4, true, true, 1, 1);
        gd_grpDesign.heightHint = 77;
        grpDesign.setLayoutData((Object)gd_grpDesign);
        Button btnLattice = new Button((Composite)grpDesign, 16);
        btnLattice.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                SelectionIndexDialog.this.designIndex = 0;
            }
        });
        btnLattice.setSelection(true);
        btnLattice.setText("Lattice");
        Button btnRansomizedCompleteBlock = new Button((Composite)grpDesign, 16);
        btnRansomizedCompleteBlock.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                SelectionIndexDialog.this.designIndex = 1;
            }
        });
        btnRansomizedCompleteBlock.setText("Randomized Complete Block");
        Group grpWeightsFilecsv = new Group(container, 0);
        grpWeightsFilecsv.setText("Weights file (.csv)");
        grpWeightsFilecsv.setLayout((Layout)new GridLayout(3, false));
        GridData gd_grpWeightsFilecsv = new GridData(4, 4, true, true, 2, 1);
        gd_grpWeightsFilecsv.heightHint = 67;
        grpWeightsFilecsv.setLayoutData((Object)gd_grpWeightsFilecsv);
        Label label = new Label((Composite)grpWeightsFilecsv, 0);
        label.setLayoutData((Object)new GridData(131072, 0x1000000, false, false, 1, 1));
        label.setText("Filename:");
        this.weightsFileText = new CCombo((Composite)grpWeightsFilecsv, 2048);
        this.weightsFileText.setBackground(SWTResourceManager.getColor(1));
        this.weightsFileText.setEditable(false);
        this.weightsFileText.setLayoutData((Object)new GridData(4, 0x1000000, true, false, 1, 1));
        this.weightsFileText.setItems(this.dataManipulationManager.getCsvFilesFromDir(this.file));
        this.weightsFileText.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                SelectionIndexDialog.this.weightsFileName = String.valueOf(SelectionIndexDialog.this.file.getParentFile().getAbsolutePath()) + "\\" + SelectionIndexDialog.this.weightsFileText.getText();
            }
        });
        Button button = new Button((Composite)grpWeightsFilecsv, 0);
        button.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                try {
                    String selected = SelectionIndexDialog.this.fd.open();
                    SelectionIndexDialog.this.weightsFileName = selected;
                    SelectionIndexDialog.this.weightsFileText.setText(new File(selected).getName());
                }
                catch (NullPointerException nullPointerException) {}
            }
        });
        button.setText("Browse");
        this.grpMarkersFilecsv = new Group(container, 0);
        this.grpMarkersFilecsv.setText("Markers file (.csv)");
        this.grpMarkersFilecsv.setLayout((Layout)new GridLayout(3, false));
        this.grpMarkersFilecsv.setVisible(false);
        GridData gd_grpMarkersFilecsv = new GridData(4, 4, true, true, 2, 1);
        gd_grpMarkersFilecsv.heightHint = 63;
        this.grpMarkersFilecsv.setLayoutData((Object)gd_grpMarkersFilecsv);
        Label label_1 = new Label((Composite)this.grpMarkersFilecsv, 0);
        label_1.setLayoutData((Object)new GridData(131072, 0x1000000, false, false, 1, 1));
        label_1.setText("Filename:");
        this.markersFileText = new CCombo((Composite)this.grpMarkersFilecsv, 2048);
        this.markersFileText.setBackground(SWTResourceManager.getColor(1));
        this.markersFileText.setEditable(false);
        this.markersFileText.setLayoutData((Object)new GridData(4, 0x1000000, true, false, 1, 1));
        this.markersFileText.setItems(this.dataManipulationManager.getCsvFilesFromDir(this.file));
        this.markersFileText.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                SelectionIndexDialog.this.markersFileName = String.valueOf(SelectionIndexDialog.this.file.getParentFile().getAbsolutePath()) + "\\" + SelectionIndexDialog.this.markersFileText.getText();
            }
        });
        Button button_1 = new Button((Composite)this.grpMarkersFilecsv, 0);
        button_1.setText("Browse");
        button_1.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                try {
                    String selected = SelectionIndexDialog.this.fd.open();
                    SelectionIndexDialog.this.markersFileName = selected;
                    SelectionIndexDialog.this.markersFileText.setText(new File(selected).getName());
                }
                catch (NullPointerException nullPointerException) {}
            }
        });
        this.grpQtlFilecsv = new Group(container, 0);
        this.grpQtlFilecsv.setText("QTL file (.csv)");
        this.grpQtlFilecsv.setLayout((Layout)new GridLayout(3, false));
        this.grpQtlFilecsv.setVisible(false);
        GridData gd_grpQtlFilecsv = new GridData(4, 4, true, true, 2, 1);
        gd_grpQtlFilecsv.heightHint = 65;
        this.grpQtlFilecsv.setLayoutData((Object)gd_grpQtlFilecsv);
        Label label_2 = new Label((Composite)this.grpQtlFilecsv, 0);
        label_2.setLayoutData((Object)new GridData(131072, 0x1000000, false, false, 1, 1));
        label_2.setText("Filename:");
        this.qtlFileText = new CCombo((Composite)this.grpQtlFilecsv, 2048);
        this.qtlFileText.setBackground(SWTResourceManager.getColor(1));
        this.qtlFileText.setEditable(false);
        this.qtlFileText.setLayoutData((Object)new GridData(4, 0x1000000, true, false, 1, 1));
        this.qtlFileText.setItems(this.dataManipulationManager.getCsvFilesFromDir(this.file));
        this.qtlFileText.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                SelectionIndexDialog.this.qtlFileName = String.valueOf(SelectionIndexDialog.this.file.getParentFile().getAbsolutePath()) + "\\" + SelectionIndexDialog.this.qtlFileText.getText();
            }
        });
        Button button_2 = new Button((Composite)this.grpQtlFilecsv, 0);
        button_2.setText("Browse");
        button_2.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                try {
                    String selected = SelectionIndexDialog.this.fd.open();
                    SelectionIndexDialog.this.qtlFileName = selected;
                    SelectionIndexDialog.this.qtlFileText.setText(new File(selected).getName());
                }
                catch (NullPointerException nullPointerException) {}
            }
        });
        Group grpSelectedGenotypes = new Group(container, 0);
        grpSelectedGenotypes.setText("Selected genotypes");
        grpSelectedGenotypes.setLayout((Layout)new GridLayout(2, false));
        GridData gd_grpSelectedGenotypes = new GridData(4, 4, true, true, 2, 1);
        gd_grpSelectedGenotypes.heightHint = 65;
        grpSelectedGenotypes.setLayoutData((Object)gd_grpSelectedGenotypes);
        Label lblPercentSelected = new Label((Composite)grpSelectedGenotypes, 0);
        lblPercentSelected.setText("Percent Selected:");
        this.percentSelectedSpinner = new Spinner((Composite)grpSelectedGenotypes, 2048);
        this.percentSelectedSpinner.setSelection(5);
        return container;
    }

    protected void enableMarkersAndQTL(boolean state) {
        this.grpMarkersFilecsv.setVisible(state);
        this.grpQtlFilecsv.setVisible(state);
    }

    protected void activateDesign(String design) {
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
        System.out.println("designIndex" + Integer.toString(this.designIndex));
        System.out.println("WEIGHT: " + this.weightsFileName);
        System.out.println("MARKERS: " + this.markersFileName);
        System.out.println("QTL: " + this.qtlFileName);
        if (this.weightsFileText.getText().equals("")) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"Please specify which file to use for the Weights file!");
        } else if (this.markersFileText.getText().equals("") && this.grpMarkersFilecsv.getVisible()) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"Please specify which file to use for the Markers file!");
        } else if (this.qtlFileText.getText().equals("") && this.grpQtlFilecsv.getVisible()) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"Please specify which file to use for the QTL file!");
        } else {
            if (!this.grpQtlFilecsv.getVisible()) {
                this.qtlFileName = "NULL";
            }
            if (!this.grpMarkersFilecsv.getVisible()) {
                this.markersFileName = "NULL";
            }
            this.weightsFileName = this.weightsFileName.replaceAll("\\\\+", "/");
            this.markersFileName = this.markersFileName.replaceAll("\\\\+", "/");
            this.qtlFileName = this.qtlFileName.replaceAll("\\\\+", "/");
            this.percentSelected = this.percentSelectedSpinner.getSelection();
            String dataFileName = this.file.getAbsolutePath().toString().replaceAll("\\\\+", "/");
            File outputFolder = PBToolAnalysisUtilities.createOutputFolder(this.file.getName(), this.analysisType);
            String newOutputFileName = String.valueOf(outputFolder.getAbsolutePath()) + "/output.txt";
            OperationProgressDialog rInfo = new OperationProgressDialog(this.getShell(), "Selection Index Analysis");
            rInfo.open();
            ProjectExplorerView.rJavaManager.getPbToolAnalysisManager().doSelectionIndex(dataFileName, newOutputFileName.replaceAll("\\\\+", "/"), String.valueOf(outputFolder.getAbsolutePath().replaceAll("\\\\+", "/")) + "/", this.selectionIndex, this.designIndex, this.basisCorrelation, this.weightsFileName, this.markersFileName, this.qtlFileName, this.percentSelected);
            rInfo.close();
            WindowDialogControlUtil.hideAllWindowDialog();
            PBToolAnalysisUtilities.openFolder(outputFolder);
        }
    }

    protected Point getInitialSize() {
        return new Point(645, 726);
    }

    protected boolean isResizable() {
        return true;
    }
}

