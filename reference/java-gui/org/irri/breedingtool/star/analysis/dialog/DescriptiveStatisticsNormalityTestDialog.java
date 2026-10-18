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
import java.util.Calendar;
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
import org.irri.breedingtool.application.model.ProjectExplorerTreeNodeModel;
import org.irri.breedingtool.datamanipulation.dialog.OperationProgressDialog;
import org.irri.breedingtool.manager.impl.ProjectExplorerManager;
import org.irri.breedingtool.projectexplorer.view.ProjectExplorerView;
import org.irri.breedingtool.utility.DialogFormUtility;
import org.irri.breedingtool.utility.StarAnalysisUtilities;
import org.irri.breedingtool.utility.StarRandomizationUtilities;

public class DescriptiveStatisticsNormalityTestDialog
extends Dialog {
    private static final int RESET = 2135;
    private DialogFormUtility listManager = new DialogFormUtility();
    private String filePath = PartStackHandler.getActiveElementID();
    private Button btnOk;
    private List lstVariables;
    private List lstByVariables;
    private ArrayList<Composite> groups = new ArrayList();
    private List lstNumericVariables;
    private List lstFactorialVariables;
    private Button btnBoxplot;
    private Button btnHistogram;
    private Button btnAddSingleVariable;
    private Button btnAddFactorVariable;
    private Button btnToFactor;
    private Button btnShapirowilk;
    private Button btnShapirofrancia;
    private Button btnLilliefors;
    private Button btnCramervonMises;
    private Button btnAndersondarling;

    public DescriptiveStatisticsNormalityTestDialog(Shell parentShell) {
        super(parentShell);
        this.setShellStyle(2288);
    }

    protected void configureShell(Shell shell) {
        super.configureShell(shell);
        shell.setText("Test for Normality : " + new File(this.filePath).getName());
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
        lblAvailableData.setText("Numeric Variable(s)");
        new Label(composite, 0);
        Label lblVariables = new Label(composite, 0);
        lblVariables.setLayoutData((Object)new GridData(16384, 0x1000000, true, false, 1, 1));
        lblVariables.setText("Variable(s)");
        lblVariables.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        this.lstNumericVariables = new List(composite, 2818);
        GridData gd_lstNumericVariables = new GridData(4, 4, true, true, 1, 1);
        gd_lstNumericVariables.heightHint = 80;
        gd_lstNumericVariables.widthHint = 80;
        this.lstNumericVariables.setLayoutData((Object)gd_lstNumericVariables);
        Composite composite_1 = new Composite(composite, 0);
        composite_1.setLayout((Layout)new GridLayout(1, false));
        GridData gd_composite_1 = new GridData(0x1000000, 0x1000000, false, false, 1, 1);
        gd_composite_1.widthHint = 100;
        composite_1.setLayoutData((Object)gd_composite_1);
        this.btnAddSingleVariable = new Button(composite_1, 0);
        this.btnAddSingleVariable.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        GridData gd_btnAddSingleVariable = new GridData(16384, 1024, false, false, 1, 1);
        gd_btnAddSingleVariable.widthHint = 90;
        this.btnAddSingleVariable.setLayoutData((Object)gd_btnAddSingleVariable);
        this.btnAddSingleVariable.setText("Add");
        this.lstVariables = new List(composite, 2818);
        GridData gd_lstVariables = new GridData(4, 4, true, true, 1, 1);
        gd_lstVariables.widthHint = 80;
        gd_lstVariables.heightHint = 80;
        this.lstVariables.setLayoutData((Object)gd_lstVariables);
        this.btnToFactor = new Button(composite, 0);
        GridData gd_btnToFactor = new GridData(131072, 0x1000000, true, false, 1, 1);
        gd_btnToFactor.widthHint = 110;
        gd_btnToFactor.heightHint = 25;
        this.btnToFactor.setLayoutData((Object)gd_btnToFactor);
        this.btnToFactor.setText("Set to Factor");
        new Label(composite, 0);
        new Label(composite, 0);
        Label lblFactors = new Label(composite, 0);
        GridData gd_lblFactors = new GridData(4, 0x1000000, true, false, 1, 1);
        gd_lblFactors.heightHint = 15;
        lblFactors.setLayoutData((Object)gd_lblFactors);
        lblFactors.setText("Factor(s)");
        lblFactors.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        new Label(composite, 0);
        Label lblByVariables = new Label(composite, 0);
        GridData gd_lblByVariables = new GridData(4, 0x1000000, false, false, 1, 1);
        gd_lblByVariables.heightHint = 15;
        lblByVariables.setLayoutData((Object)gd_lblByVariables);
        lblByVariables.setText("By Variable(s)");
        lblByVariables.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        this.lstFactorialVariables = new List(composite, 2818);
        GridData gd_lstFactorialVariables = new GridData(4, 4, true, true, 1, 1);
        gd_lstFactorialVariables.widthHint = 80;
        gd_lstFactorialVariables.heightHint = 60;
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
        GridData gd_lstByVariables = new GridData(4, 4, true, true, 1, 1);
        gd_lstByVariables.widthHint = 80;
        gd_lstByVariables.heightHint = 60;
        this.lstByVariables.setLayoutData((Object)gd_lstByVariables);
        TabItem tbtmStatistics_1 = new TabItem(tabFolder, 0);
        tbtmStatistics_1.setText("Options");
        Composite cmpStatistics = new Composite((Composite)tabFolder, 0);
        tbtmStatistics_1.setControl((Control)cmpStatistics);
        cmpStatistics.setLayout((Layout)new GridLayout(2, false));
        Group grpTestProcedures = new Group(cmpStatistics, 0);
        grpTestProcedures.setLayoutData((Object)new GridData(4, 128, true, false, 1, 2));
        grpTestProcedures.setLayout((Layout)new GridLayout(1, false));
        grpTestProcedures.setText("Test Procedure");
        this.btnShapirowilk = new Button((Composite)grpTestProcedures, 32);
        this.btnShapirowilk.setSelection(true);
        this.btnShapirowilk.setLayoutData((Object)new GridData(16384, 0x1000000, true, false, 1, 1));
        this.btnShapirowilk.setText("Shapiro-Wilk");
        this.btnShapirowilk.setData((Object)"swilk");
        this.btnShapirofrancia = new Button((Composite)grpTestProcedures, 32);
        this.btnShapirofrancia.setText("Shapiro-Francia");
        this.btnShapirofrancia.setData((Object)"sfrancia");
        this.btnLilliefors = new Button((Composite)grpTestProcedures, 32);
        this.btnLilliefors.setText("Lilliefors (Kolmogorov-Smirnov)");
        this.btnLilliefors.setData((Object)"ks");
        this.btnCramervonMises = new Button((Composite)grpTestProcedures, 32);
        this.btnCramervonMises.setText("Cramer-Von Mises");
        this.btnCramervonMises.setData((Object)"cramer");
        this.btnAndersondarling = new Button((Composite)grpTestProcedures, 32);
        this.btnAndersondarling.setText("Anderson-Darling");
        this.btnAndersondarling.setData((Object)"anderson");
        Group grpGraph = new Group(cmpStatistics, 0);
        GridData gd_grpGraph = new GridData(4, 128, true, false, 1, 1);
        gd_grpGraph.widthHint = 174;
        grpGraph.setLayoutData((Object)gd_grpGraph);
        grpGraph.setLayout((Layout)new GridLayout(1, false));
        grpGraph.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        grpGraph.setText("Graph");
        this.btnBoxplot = new Button((Composite)grpGraph, 32);
        this.btnBoxplot.setText("Boxplot");
        this.btnHistogram = new Button((Composite)grpGraph, 32);
        this.btnHistogram.setText("Histogram");
        new Label(cmpStatistics, 0);
        this.groups.clear();
        this.groups.add((Composite)grpTestProcedures);
        this.initializeContent();
        return container;
    }

    private void initializeContent() {
        this.listManager.initializeSingleButtonList(this.lstNumericVariables, this.lstVariables, this.btnToFactor, this.btnAddSingleVariable);
        this.listManager.initializeSingleButtonList(this.lstFactorialVariables, this.lstByVariables, this.btnToFactor, this.btnAddFactorVariable);
        this.listManager.initializeVariableMoveList(this.lstNumericVariables, this.lstFactorialVariables, this.btnToFactor, this.filePath);
        this.listManager.initializeFactorList(this.lstFactorialVariables, this.filePath);
        this.listManager.initializeNumericList(this.lstNumericVariables, this.filePath);
    }

    protected void buttonPressed(int buttonId) {
        if (buttonId == 0) {
            this.okPressed();
        } else if (buttonId == 2135) {
            this.lstNumericVariables.removeAll();
            this.lstFactorialVariables.removeAll();
            this.lstVariables.removeAll();
            this.lstByVariables.removeAll();
            this.btnAddSingleVariable.setText("Add");
            this.btnAddSingleVariable.setEnabled(false);
            this.btnAddFactorVariable.setText("Add");
            this.btnAddFactorVariable.setEnabled(false);
            this.btnToFactor.setText("Set to Factor");
            this.btnToFactor.setEnabled(false);
            this.btnAndersondarling.setSelection(false);
            this.btnBoxplot.setSelection(false);
            this.btnCramervonMises.setSelection(false);
            this.btnHistogram.setSelection(false);
            this.btnLilliefors.setSelection(false);
            this.btnShapirofrancia.setSelection(false);
            this.btnShapirowilk.setSelection(false);
            this.listManager.initializeFactorList(this.lstFactorialVariables, this.filePath);
            this.listManager.initializeNumericList(this.lstNumericVariables, this.filePath);
        } else if (1 == buttonId) {
            this.close();
        }
    }

    protected void okPressed() {
        if (this.validateForm(this.lstVariables, this.lstByVariables, this.groups.toArray(new Composite[this.groups.size()]), this.btnOk)) {
            this.btnOk.setEnabled(false);
            ProjectExplorerManager projectMan = new ProjectExplorerManager();
            Calendar.getInstance();
            this.listManager.getCheckBoxesValue(this.groups.toArray(new Composite[this.groups.size()]));
            File outputFolder = new File(StarAnalysisUtilities.createOutputFolder(this.filePath, "Descriptive_Normality"));
            OperationProgressDialog rInfo = new OperationProgressDialog(this.getShell(), "Performing Analysis");
            rInfo.open();
            if (!outputFolder.exists()) {
                outputFolder.mkdir();
            }
            this.filePath.replace(File.separator, "/");
            String outFileName = String.valueOf(outputFolder.getAbsolutePath().replace(File.separator, "/")) + "/Output.txt";
            String graphBoxFileName = String.valueOf(outputFolder.getAbsolutePath().replace(File.separator, "/")) + "/Boxplot_";
            String graphHistFileName = String.valueOf(outputFolder.getAbsolutePath().replace(File.separator, "/")) + "/Histogram_";
            String[] respvars = this.lstVariables.getItems();
            String[] grp = this.lstByVariables.getItemCount() > 0 ? this.lstByVariables.getItems() : null;
            String[] method = this.listManager.getCheckBoxesValue(this.groups.toArray(new Composite[this.groups.size()]));
            String outputBoxplot = this.btnBoxplot.getSelection() ? "TRUE" : "FALSE";
            String outputHist = this.btnHistogram.getSelection() ? "TRUE" : "FALSE";
            StarRandomizationUtilities.testVarArgs(this.filePath.replace(File.separator, "/"), outFileName, respvars, grp, method, outputBoxplot, graphBoxFileName, outputHist, graphHistFileName);
            ProjectExplorerView.rJavaManager.getSTARAnalysisManager().doNormality(this.filePath.replace(File.separator, "/"), outFileName, respvars, grp, method, outputBoxplot, graphBoxFileName, outputHist, graphHistFileName);
            this.getShell().setMinimized(true);
            projectMan.refreshFolder((ProjectExplorerTreeNodeModel)projectMan.getOutputFolder(ProjectExplorerView.projectTree).getData());
            PartStackHandler.execute(projectMan.getTreeNodeModelbyFilePath(outputFolder.getAbsolutePath()));
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
        if (lstVariables.getItemCount() >= 1 && this.listManager.isCheckBoxesHasBoolean(groupComposites)) {
            return true;
        }
        MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"At least one required field is empty.  Variable(s) list box of the Variable Description tab should contain at least one item and at least one item of the Test Procedure in the Options tab should be chosen.");
        return false;
    }

    protected Point getInitialSize() {
        return new Point(510, 420);
    }
}

