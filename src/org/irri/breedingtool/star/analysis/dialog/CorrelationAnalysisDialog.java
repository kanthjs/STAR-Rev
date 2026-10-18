/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.jface.dialogs.Dialog
 *  org.eclipse.jface.dialogs.IDialogConstants
 *  org.eclipse.jface.dialogs.MessageDialog
 *  org.eclipse.swt.events.SelectionAdapter
 *  org.eclipse.swt.events.SelectionEvent
 *  org.eclipse.swt.events.SelectionListener
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
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
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
import org.irri.breedingtool.manager.impl.DataManipulationManager;
import org.irri.breedingtool.manager.impl.ProjectExplorerManager;
import org.irri.breedingtool.projectexplorer.view.ProjectExplorerView;
import org.irri.breedingtool.utility.DialogFormUtility;
import org.irri.breedingtool.utility.StarAnalysisUtilities;

public class CorrelationAnalysisDialog
extends Dialog {
    private static final int RESET = 2135;
    private String filePath = PartStackHandler.getActivePart().getElementId().toString();
    private Button btnOk;
    private Button btnPearson;
    private Button btnKendallsTaub;
    private Button btnSpearman;
    private Button btnLessThan;
    private Button btnGreaterThan;
    private Button btnNotEqual;
    private Button btnSummaryStatistics;
    private Button btnScatterplot;
    private DialogFormUtility listManager = new DialogFormUtility();
    private List lstNumericVariables;
    private Button btnAdd;
    private Group grpCorrelationCoefficients;
    private List lstTestVariables;

    public CorrelationAnalysisDialog(Shell parentShell) {
        super(parentShell);
        this.setShellStyle(2288);
    }

    protected void configureShell(Shell shell) {
        super.configureShell(shell);
        shell.setText("Correlation Analysis : " + new File(this.filePath).getName());
    }

    protected Control createDialogArea(Composite parent) {
        Composite container = (Composite)super.createDialogArea(parent);
        GridLayout gridLayout = (GridLayout)container.getLayout();
        gridLayout.marginHeight = 8;
        gridLayout.marginWidth = 8;
        TabFolder tabFolder = new TabFolder(container, 0);
        tabFolder.setLayoutData((Object)new GridData(4, 4, true, true, 1, 1));
        TabItem tbtmVariableDescription = new TabItem(tabFolder, 0);
        tbtmVariableDescription.setText("Variable Description");
        Composite composite = new Composite((Composite)tabFolder, 0);
        tbtmVariableDescription.setControl((Control)composite);
        composite.setLayout((Layout)new GridLayout(1, false));
        Composite composite_1 = new Composite(composite, 0);
        composite_1.setLayout((Layout)new GridLayout(3, false));
        GridData gd_composite_1 = new GridData(4, 4, true, true, 1, 1);
        gd_composite_1.heightHint = 228;
        composite_1.setLayoutData((Object)gd_composite_1);
        Label lblAvailableData = new Label(composite_1, 0);
        lblAvailableData.setLayoutData((Object)new GridData(16384, 0x1000000, true, false, 1, 1));
        lblAvailableData.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        lblAvailableData.setText("Numeric Variables:");
        new Label(composite_1, 0);
        Label lblTestVariables = new Label(composite_1, 0);
        lblTestVariables.setLayoutData((Object)new GridData(16384, 0x1000000, true, false, 1, 1));
        lblTestVariables.setSize(109, 18);
        lblTestVariables.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        lblTestVariables.setText("Test Variable(s):");
        this.lstNumericVariables = new List(composite_1, 2818);
        this.lstNumericVariables.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        GridData gd_lstNumericVariables = new GridData(4, 4, true, true, 1, 1);
        gd_lstNumericVariables.widthHint = 80;
        gd_lstNumericVariables.heightHint = 130;
        this.lstNumericVariables.setLayoutData((Object)gd_lstNumericVariables);
        DataManipulationManager dataManipulationManager = new DataManipulationManager();
        dataManipulationManager.getVarInfoFromFile(this.filePath);
        this.btnAdd = new Button(composite_1, 0);
        GridData gd_btnAdd = new GridData(16384, 0x1000000, false, false, 1, 1);
        gd_btnAdd.widthHint = 90;
        this.btnAdd.setLayoutData((Object)gd_btnAdd);
        this.btnAdd.setText("Add");
        this.lstTestVariables = new List(composite_1, 2818);
        GridData gd_lstTestVariables = new GridData(4, 4, false, false, 1, 1);
        gd_lstTestVariables.heightHint = 130;
        gd_lstTestVariables.widthHint = 80;
        this.lstTestVariables.setLayoutData((Object)gd_lstTestVariables);
        this.lstTestVariables.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        this.grpCorrelationCoefficients = new Group(composite, 0);
        this.grpCorrelationCoefficients.setLayout((Layout)new GridLayout(3, false));
        this.grpCorrelationCoefficients.setLayoutData((Object)new GridData(4, 0x1000000, true, false, 1, 1));
        this.grpCorrelationCoefficients.setText("Correlation Coefficients");
        this.btnPearson = new Button((Composite)this.grpCorrelationCoefficients, 32);
        this.btnPearson.setSelection(true);
        this.btnPearson.setLayoutData((Object)new GridData(16384, 4, true, true, 1, 1));
        this.btnPearson.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        this.btnPearson.setText("Pearson");
        this.btnKendallsTaub = new Button((Composite)this.grpCorrelationCoefficients, 32);
        this.btnKendallsTaub.setLayoutData((Object)new GridData(16384, 4, true, true, 1, 1));
        this.btnKendallsTaub.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        this.btnKendallsTaub.setText("Kendall's tau-b");
        this.btnSpearman = new Button((Composite)this.grpCorrelationCoefficients, 32);
        this.btnSpearman.setLayoutData((Object)new GridData(16384, 4, true, true, 1, 1));
        this.btnSpearman.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        this.btnSpearman.setText("Spearman");
        TabItem tbtmOptions = new TabItem(tabFolder, 0);
        tbtmOptions.setText("Options");
        Composite composite_4 = new Composite((Composite)tabFolder, 0);
        tbtmOptions.setControl((Control)composite_4);
        composite_4.setLayout((Layout)new GridLayout(1, false));
        Group grpAlternativeHypothesis = new Group(composite_4, 0);
        grpAlternativeHypothesis.setLayout((Layout)new GridLayout(1, false));
        grpAlternativeHypothesis.setLayoutData((Object)new GridData(4, 4, true, false, 1, 1));
        grpAlternativeHypothesis.setText("Alternative Hypothesis:");
        this.btnLessThan = new Button((Composite)grpAlternativeHypothesis, 16);
        this.btnLessThan.setLayoutData((Object)new GridData(4, 4, true, true, 1, 1));
        this.btnLessThan.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        this.btnLessThan.setText("Less than");
        this.btnGreaterThan = new Button((Composite)grpAlternativeHypothesis, 16);
        this.btnGreaterThan.setLayoutData((Object)new GridData(4, 4, true, true, 1, 1));
        this.btnGreaterThan.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        this.btnGreaterThan.setText("Greater than");
        this.btnNotEqual = new Button((Composite)grpAlternativeHypothesis, 16);
        this.btnNotEqual.setSelection(true);
        this.btnNotEqual.setLayoutData((Object)new GridData(4, 4, true, true, 1, 1));
        this.btnNotEqual.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        this.btnNotEqual.setText("Not Equal");
        Group grpDisplay = new Group(composite_4, 0);
        grpDisplay.setText("Display:");
        grpDisplay.setLayout((Layout)new GridLayout(1, false));
        GridData gd_grpDisplay = new GridData(4, 4, true, false, 1, 1);
        gd_grpDisplay.heightHint = 35;
        grpDisplay.setLayoutData((Object)gd_grpDisplay);
        this.btnSummaryStatistics = new Button((Composite)grpDisplay, 32);
        this.btnSummaryStatistics.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
            }
        });
        GridData gd_btnSummaryStatistics = new GridData(4, 4, true, true, 1, 1);
        gd_btnSummaryStatistics.heightHint = 53;
        this.btnSummaryStatistics.setLayoutData((Object)gd_btnSummaryStatistics);
        this.btnSummaryStatistics.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        this.btnSummaryStatistics.setText("Summary Statistics");
        Group grpGraph = new Group(composite_4, 0);
        grpGraph.setLayout((Layout)new GridLayout(1, false));
        GridData gd_grpGraph = new GridData(4, 4, true, false, 1, 1);
        gd_grpGraph.heightHint = 37;
        grpGraph.setLayoutData((Object)gd_grpGraph);
        grpGraph.setText("Graph:");
        this.btnScatterplot = new Button((Composite)grpGraph, 32);
        this.btnScatterplot.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        GridData gd_btnScatterplot = new GridData(4, 4, true, true, 1, 1);
        gd_btnScatterplot.heightHint = 50;
        this.btnScatterplot.setLayoutData((Object)gd_btnScatterplot);
        this.btnScatterplot.setText("Scatterplot");
        this.initializeContent();
        return container;
    }

    private void initializeContent() {
        this.listManager.initializeNumericList(this.lstNumericVariables, this.filePath);
        this.listManager.initializeSingleButtonList(this.lstNumericVariables, this.lstTestVariables, this.btnAdd);
    }

    protected void createButtonsForButtonBar(Composite parent) {
        this.createButton(parent, 8, "Reset", false);
        this.btnOk = this.createButton(parent, 0, IDialogConstants.OK_LABEL, true);
        this.createButton(parent, 1, IDialogConstants.CANCEL_LABEL, false);
    }

    protected void okPressed() {
        if (this.lstTestVariables.getItemCount() <= 1 || !this.listManager.isCheckBoxesHasBoolean((Composite)this.grpCorrelationCoefficients)) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Form validation error", (String)"Validation Error: There should be atleast one variable in Test Variable(s) and one Correlation Coefficient box is checked");
            return;
        }
        this.btnOk.setEnabled(false);
        ProjectExplorerManager projectMan = new ProjectExplorerManager();
        String fileName = this.filePath.replace(File.separator, "/");
        Calendar.getInstance();
        File outputFolder = new File(StarAnalysisUtilities.createOutputFolder(this.filePath, "CorrelationAnalysis"));
        outputFolder.mkdir();
        OperationProgressDialog rInfo = new OperationProgressDialog(this.getShell(), "Performing Analysis");
        rInfo.open();
        String outputFile = (String.valueOf(outputFolder.getPath().toString()) + File.separator).toString().replace(File.separator, "/");
        String[] respvarVector = this.lstTestVariables.getItems();
        ArrayList<String> listMethod = new ArrayList<String>();
        if (this.btnPearson.getSelection()) {
            listMethod.add("pearson");
        }
        if (this.btnSpearman.getSelection()) {
            listMethod.add("spearman");
        }
        if (this.btnKendallsTaub.getSelection()) {
            listMethod.add("kendall");
        }
        String alternative = "NULL";
        if (this.btnLessThan.getSelection()) {
            alternative = "less";
        } else if (this.btnGreaterThan.getSelection()) {
            alternative = "greater";
        } else if (this.btnNotEqual.getSelection()) {
            alternative = "two.sided";
        }
        String[] method = listMethod.toArray(new String[listMethod.size()]);
        String statistics = this.convertToString(this.btnSummaryStatistics.getSelection());
        String outputPlot = this.convertToString(this.btnScatterplot.getSelection());
        ProjectExplorerView.rJavaManager.getSTARAnalysisManager().doBivariateCorr(fileName, outputFile, respvarVector, method, alternative, statistics, outputPlot);
        StarAnalysisUtilities.testVarArgs(fileName, outputFile, respvarVector, method, alternative, statistics, outputPlot);
        StarAnalysisUtilities.testVarArgs(fileName, outputFile, respvarVector, method, alternative, statistics, outputPlot);
        projectMan.refreshFolder((ProjectExplorerTreeNodeModel)projectMan.getOutputFolder(ProjectExplorerView.projectTree).getData());
        projectMan.getOutputFolder(ProjectExplorerView.projectTree).setExpanded(true);
        this.getShell().setMinimized(true);
        this.btnOk.setEnabled(true);
        rInfo.close();
        PartStackHandler.execute(projectMan.getTreeNodeModelbyFilePath(outputFolder.getAbsolutePath()));
    }

    protected void buttonPressed(int buttonId) {
        if (buttonId == 0) {
            this.okPressed();
        } else if (8 == buttonId) {
            this.btnOk.setEnabled(true);
            this.lstNumericVariables.removeAll();
            this.listManager.initializeNumericList(this.lstNumericVariables, this.filePath);
            this.lstTestVariables.removeAll();
            this.btnPearson.setSelection(true);
            this.btnKendallsTaub.setSelection(false);
            this.btnSpearman.setSelection(false);
            this.btnNotEqual.setSelection(true);
            this.btnGreaterThan.setSelection(false);
            this.btnLessThan.setSelection(false);
            this.btnSummaryStatistics.setSelection(false);
            this.btnScatterplot.setSelection(false);
            this.btnOk.setEnabled(true);
        } else if (1 == buttonId) {
            this.close();
        }
    }

    private String convertToString(boolean value) {
        if (value) {
            return "TRUE";
        }
        return "FALSE";
    }

    protected Point getInitialSize() {
        return new Point(562, 487);
    }
}

