/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.jface.dialogs.Dialog
 *  org.eclipse.jface.dialogs.IDialogConstants
 *  org.eclipse.jface.dialogs.MessageDialog
 *  org.eclipse.swt.graphics.Point
 *  org.eclipse.swt.layout.GridData
 *  org.eclipse.swt.layout.GridLayout
 *  org.eclipse.swt.widgets.Button
 *  org.eclipse.swt.widgets.Composite
 *  org.eclipse.swt.widgets.Control
 *  org.eclipse.swt.widgets.Group
 *  org.eclipse.swt.widgets.Label
 *  org.eclipse.swt.widgets.Layout
 *  org.eclipse.swt.widgets.List
 *  org.eclipse.swt.widgets.Shell
 *  org.eclipse.swt.widgets.TabFolder
 *  org.eclipse.swt.widgets.TabItem
 */
package org.irri.breedingtool.star.analysis.dialog;

import java.io.File;
import java.util.ArrayList;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.wb.swt.SWTResourceManager;
import org.irri.breedingtool.application.handler.PartStackHandler;
import org.irri.breedingtool.datamanipulation.dialog.OperationProgressDialog;
import org.irri.breedingtool.manager.impl.DataManipulationManager;
import org.irri.breedingtool.projectexplorer.view.ProjectExplorerView;
import org.irri.breedingtool.utility.DialogFormUtility;
import org.irri.breedingtool.utility.StarAnalysisUtilities;

public class DescriptiveStatisticsHeteroskedasticityDialog
extends Dialog {
    private static final int RESET = 2135;
    private DialogFormUtility listManager = new DialogFormUtility();
    private String filePath = PartStackHandler.getActiveElementID();
    private Button btnOk;
    private List lstVariables;
    private List lstByVariables;
    private ArrayList<Composite> groups = new ArrayList();
    private Button btnBartlettsTest;
    private Button btnBoxplot;
    private Button btnHistogram;
    private Button btnLevenesTest;
    private List lstNumericVariables;
    private List lstFactorialVariables;
    private Button btnAddSingleVariable;
    private Button btnAddFactorVariable;
    private Button btnSettofactor;

    public DescriptiveStatisticsHeteroskedasticityDialog(Shell parentShell) {
        super(parentShell);
        this.setShellStyle(2288);
    }

    protected void configureShell(Shell shell) {
        super.configureShell(shell);
        shell.setText("Homogeniety of Variances: " + new File(this.filePath).getName());
    }

    protected Control createDialogArea(Composite parent) {
        Composite container = (Composite)super.createDialogArea(parent);
        GridLayout gl_container = new GridLayout(1, false);
        gl_container.marginHeight = 8;
        gl_container.marginWidth = 8;
        container.setLayout((Layout)gl_container);
        TabFolder tabFolder = new TabFolder(container, 0);
        tabFolder.setLayoutData((Object)new GridData(4, 4, true, true, 1, 1));
        TabItem tbtmVariableDescription = new TabItem(tabFolder, 0);
        tbtmVariableDescription.setText("Variable Description");
        Composite composite = new Composite((Composite)tabFolder, 0);
        tbtmVariableDescription.setControl((Control)composite);
        composite.setLayout((Layout)new GridLayout(3, false));
        Label lblAvailableData = new Label(composite, 0);
        lblAvailableData.setLayoutData((Object)new GridData(16384, 0x1000000, true, false, 1, 1));
        lblAvailableData.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        lblAvailableData.setText("Numeric Variable(s):");
        new Label(composite, 0);
        Label lblVariables = new Label(composite, 0);
        lblVariables.setLayoutData((Object)new GridData(16384, 0x1000000, true, false, 1, 1));
        lblVariables.setText("Variable(s):");
        lblVariables.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        this.lstNumericVariables = new List(composite, 2818);
        GridData gd_lstNumericVariables = new GridData(4, 4, true, false, 1, 1);
        gd_lstNumericVariables.heightHint = 121;
        gd_lstNumericVariables.widthHint = 148;
        this.lstNumericVariables.setLayoutData((Object)gd_lstNumericVariables);
        Composite composite_1 = new Composite(composite, 0);
        composite_1.setLayout((Layout)new GridLayout(1, false));
        GridData gd_composite_1 = new GridData(0x1000000, 0x1000000, false, false, 1, 1);
        gd_composite_1.widthHint = 99;
        composite_1.setLayoutData((Object)gd_composite_1);
        this.btnAddSingleVariable = new Button(composite_1, 0);
        this.btnAddSingleVariable.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        GridData gd_btnAddSingleVariable = new GridData(16384, 0x1000000, false, false, 1, 1);
        gd_btnAddSingleVariable.widthHint = 90;
        this.btnAddSingleVariable.setLayoutData((Object)gd_btnAddSingleVariable);
        this.btnAddSingleVariable.setText("Add");
        this.lstVariables = new List(composite, 2818);
        GridData gd_lstVariables = new GridData(4, 4, true, false, 1, 1);
        gd_lstVariables.widthHint = 148;
        gd_lstVariables.heightHint = 121;
        this.lstVariables.setLayoutData((Object)gd_lstVariables);
        this.btnSettofactor = new Button(composite, 0);
        GridData gd_btnSettofactor = new GridData(131072, 0x1000000, false, false, 1, 1);
        gd_btnSettofactor.widthHint = 110;
        this.btnSettofactor.setLayoutData((Object)gd_btnSettofactor);
        this.btnSettofactor.setText("Set to Factor");
        new Label(composite, 0);
        new Label(composite, 0);
        Label lblFactors = new Label(composite, 0);
        GridData gd_lblFactors = new GridData(16384, 1024, true, false, 1, 1);
        gd_lblFactors.heightHint = 15;
        lblFactors.setLayoutData((Object)gd_lblFactors);
        lblFactors.setText("Factor(s):");
        lblFactors.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        new Label(composite, 0);
        Label lblByVariables = new Label(composite, 0);
        lblByVariables.setText("By Variable(s):");
        lblByVariables.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        this.lstFactorialVariables = new List(composite, 2818);
        GridData gd_lstFactorialVariables = new GridData(4, 4, true, false, 1, 1);
        gd_lstFactorialVariables.widthHint = 148;
        gd_lstFactorialVariables.heightHint = 121;
        this.lstFactorialVariables.setLayoutData((Object)gd_lstFactorialVariables);
        Composite composite_2 = new Composite(composite, 0);
        composite_2.setLayout((Layout)new GridLayout(1, false));
        this.btnAddFactorVariable = new Button(composite_2, 0);
        GridData gd_btnAddFactorVariable = new GridData(16384, 1024, false, false, 1, 1);
        gd_btnAddFactorVariable.widthHint = 90;
        this.btnAddFactorVariable.setLayoutData((Object)gd_btnAddFactorVariable);
        this.btnAddFactorVariable.setText("Add");
        this.btnAddFactorVariable.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        this.lstByVariables = new List(composite, 2818);
        GridData gd_lstByVariables = new GridData(4, 4, true, false, 1, 1);
        gd_lstByVariables.widthHint = 148;
        gd_lstByVariables.heightHint = 121;
        this.lstByVariables.setLayoutData((Object)gd_lstByVariables);
        this.listVariables(this.lstNumericVariables, this.lstFactorialVariables);
        TabItem tbtmOptions = new TabItem(tabFolder, 0);
        tbtmOptions.setText("Options");
        Composite composite_3 = new Composite((Composite)tabFolder, 0);
        tbtmOptions.setControl((Control)composite_3);
        composite_3.setLayout((Layout)new GridLayout(1, false));
        Group grpTestProcedures = new Group(composite_3, 0);
        grpTestProcedures.setLayout((Layout)new GridLayout(1, false));
        GridData gd_grpTestProcedures = new GridData(4, 4, false, false, 1, 1);
        gd_grpTestProcedures.heightHint = 47;
        gd_grpTestProcedures.widthHint = 245;
        grpTestProcedures.setLayoutData((Object)gd_grpTestProcedures);
        grpTestProcedures.setText("Test Procedures");
        this.btnBartlettsTest = new Button((Composite)grpTestProcedures, 32);
        this.btnBartlettsTest.setLayoutData((Object)new GridData(4, 4, false, false, 1, 1));
        this.btnBartlettsTest.setSelection(true);
        this.btnBartlettsTest.setText("Bartlett's Test");
        this.btnBartlettsTest.setData((Object)"bartlett");
        this.btnLevenesTest = new Button((Composite)grpTestProcedures, 32);
        this.btnLevenesTest.setLayoutData((Object)new GridData(4, 4, false, false, 1, 1));
        this.btnLevenesTest.setText("Levene's Test");
        this.btnLevenesTest.setData((Object)"levene");
        Group grpGraph = new Group(composite_3, 0);
        grpGraph.setText("Graph");
        grpGraph.setLayout((Layout)new GridLayout(1, false));
        GridData gd_grpGraph = new GridData(4, 0x1000000, false, false, 1, 1);
        gd_grpGraph.heightHint = 46;
        grpGraph.setLayoutData((Object)gd_grpGraph);
        this.btnBoxplot = new Button((Composite)grpGraph, 32);
        this.btnBoxplot.setLayoutData((Object)new GridData(4, 4, false, false, 1, 1));
        this.btnBoxplot.setText("Boxplot");
        this.btnHistogram = new Button((Composite)grpGraph, 32);
        this.btnHistogram.setLayoutData((Object)new GridData(4, 4, false, false, 1, 1));
        this.btnHistogram.setText("Histogram");
        this.groups.add((Composite)grpTestProcedures);
        this.initializeContent();
        return container;
    }

    void initializeContent() {
        this.listManager.initializeSingleButtonList(this.lstNumericVariables, this.lstVariables, this.btnSettofactor, this.btnAddSingleVariable);
        this.listManager.initializeSingleButtonList(this.lstFactorialVariables, this.lstByVariables, this.btnSettofactor, this.btnAddFactorVariable);
        this.listManager.initializeVariableMoveList(this.lstNumericVariables, this.lstFactorialVariables, this.btnSettofactor, this.filePath);
    }

    private void listVariables(List lstNumericVariables, List lstFactorialVariables) {
        DataManipulationManager dataManipulationManager = new DataManipulationManager();
        ArrayList<String> varList = dataManipulationManager.getVarInfoFromFile(this.filePath);
        int i = 0;
        while (i < varList.size()) {
            if (varList.get(i).toString().split(":")[1].equals("Numeric")) {
                lstNumericVariables.add(varList.get(i).toString().split(":")[0]);
            } else {
                lstFactorialVariables.add(varList.get(i).toString().split(":")[0]);
            }
            ++i;
        }
    }

    protected void buttonPressed(int buttonId) {
        if (buttonId == 0) {
            this.okPressed();
        } else if (buttonId == 2135) {
            this.lstFactorialVariables.removeAll();
            this.lstNumericVariables.removeAll();
            this.lstVariables.removeAll();
            this.lstByVariables.removeAll();
            this.listManager.initializeFactorList(this.lstFactorialVariables, this.filePath);
            this.listManager.initializeNumericList(this.lstNumericVariables, this.filePath);
            this.btnBartlettsTest.setSelection(true);
            this.btnLevenesTest.setSelection(false);
            this.btnBoxplot.setSelection(false);
            this.btnHistogram.setSelection(false);
            this.btnAddSingleVariable.setText("Add");
            this.btnAddSingleVariable.setEnabled(false);
            this.btnAddFactorVariable.setText("Add");
            this.btnAddFactorVariable.setEnabled(false);
            this.btnSettofactor.setText("Set to Factor");
            this.btnSettofactor.setEnabled(false);
        } else if (1 == buttonId) {
            this.close();
        }
    }

    protected void okPressed() {
        if (this.validateForm(this.lstVariables, this.lstByVariables, this.groups.toArray(new Composite[this.groups.size()]), this.btnOk)) {
            this.btnOk.setEnabled(false);
            String outputFolder = StarAnalysisUtilities.createOutputFolder(this.filePath, "Heteroskedasticity");
            OperationProgressDialog rInfo = new OperationProgressDialog(this.getShell(), "Performing Analysis");
            rInfo.open();
            String outFileName = String.valueOf(outputFolder.replace(File.separator, "/")) + "/Output.txt";
            String graphBoxFileName = String.valueOf(outputFolder.replace(File.separator, "/")) + "/Boxplot_";
            String graphHistFileName = String.valueOf(outputFolder.replace(File.separator, "/")) + "/Histogram_";
            String[] method = this.listManager.getCheckBoxesValue(this.groups.toArray(new Composite[this.groups.size()]));
            String outputBoxplot = this.btnBoxplot.getSelection() ? "TRUE" : "FALSE";
            String outputHist = this.btnHistogram.getSelection() ? "TRUE" : "FALSE";
            StarAnalysisUtilities.testVarArgs(this.filePath.replace(File.separator, "/"), outFileName, this.lstVariables.getItems(), this.lstByVariables.getItems(), method, outputBoxplot, graphBoxFileName, outputHist, graphHistFileName);
            ProjectExplorerView.rJavaManager.getSTARAnalysisManager().doHeteroskedasticity(this.filePath.replace(File.separator, "/"), outFileName, this.lstVariables.getItems(), this.lstByVariables.getItems(), method, outputBoxplot, graphBoxFileName, outputHist, graphHistFileName);
            this.getShell().setMinimized(true);
            StarAnalysisUtilities.openAndRefreshFolder(outputFolder);
            rInfo.close();
            this.btnOk.setEnabled(true);
        }
    }

    protected void createButtonsForButtonBar(Composite parent) {
        this.createButton(parent, 2135, "Reset", false);
        this.btnOk = this.createButton(parent, 0, IDialogConstants.OK_LABEL, true);
        this.createButton(parent, 1, IDialogConstants.CANCEL_LABEL, false);
    }

    private boolean validateForm(List lstVariables, List lstByVariables, Composite[] groupComposites, Button btnOkay) {
        if (lstVariables.getItemCount() >= 1 && lstByVariables.getItemCount() >= 1 && this.listManager.isCheckBoxesHasBoolean(groupComposites)) {
            return true;
        }
        MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"At least one required field is empty.  Variable(s) and By Variable(s) list box of the Variable Description tab should both contain at least one item and at least one item of the Test Procedure in the Options tab should be chosen.");
        return false;
    }

    protected Point getInitialSize() {
        return new Point(482, 501);
    }
}

