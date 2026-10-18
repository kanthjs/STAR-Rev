/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.jface.dialogs.Dialog
 *  org.eclipse.jface.dialogs.IDialogConstants
 *  org.eclipse.jface.dialogs.MessageDialog
 *  org.eclipse.jface.viewers.ListViewer
 *  org.eclipse.swt.events.SelectionAdapter
 *  org.eclipse.swt.events.SelectionEvent
 *  org.eclipse.swt.events.SelectionListener
 *  org.eclipse.swt.graphics.Point
 *  org.eclipse.swt.layout.GridData
 *  org.eclipse.swt.layout.GridLayout
 *  org.eclipse.swt.widgets.Button
 *  org.eclipse.swt.widgets.Combo
 *  org.eclipse.swt.widgets.Composite
 *  org.eclipse.swt.widgets.Control
 *  org.eclipse.swt.widgets.Label
 *  org.eclipse.swt.widgets.Layout
 *  org.eclipse.swt.widgets.List
 *  org.eclipse.swt.widgets.Shell
 *  org.eclipse.swt.widgets.Text
 */
package org.irri.breedingtool.star.analysis.dialog;

import java.io.File;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;
import org.irri.breedingtool.application.handler.PartStackHandler;
import org.irri.breedingtool.datamanipulation.dialog.OperationProgressDialog;
import org.irri.breedingtool.projectexplorer.view.ProjectExplorerView;
import org.irri.breedingtool.utility.DialogFormUtility;
import org.irri.breedingtool.utility.StarAnalysisUtilities;

public class DistributionFittingDialog
extends Dialog {
    private String filePath = PartStackHandler.getActiveElementID();
    private DialogFormUtility listManager = new DialogFormUtility();
    private Button btnOkay;
    private List lstNumericVars;
    private List lstFactors;
    private Button btnAdd1;
    private Button btnAdd2;
    private Button btnSetToFactor;
    private Text txtVariable;
    private Text txtGroupBy;
    private String[] FittingMethods = new String[]{"mle", "mme", "mge"};
    private Label lblAvailableDist;
    private List lstAvailableDist;
    private ListViewer listViewer;
    private Label lblSelectedDist;
    private List lstSelectedDist;
    private ListViewer listViewer_1;
    private Button btnAdd;
    private Label lblFittingMethod;
    private Combo cmboFittingMethod;
    private Label lblNewLabel;
    private Label label;
    private Label label_4;

    public DistributionFittingDialog(Shell parentShell) {
        super(parentShell);
        this.setShellStyle(2256);
    }

    protected void configureShell(Shell shell) {
        super.configureShell(shell);
        shell.setText("Fit Distributions : " + new File(this.filePath).getName());
    }

    protected Control createDialogArea(Composite parent) {
        Composite container = (Composite)super.createDialogArea(parent);
        GridLayout gl_container = new GridLayout(6, false);
        gl_container.horizontalSpacing = 0;
        container.setLayout((Layout)gl_container);
        Label lblNumericVariables = new Label(container, 0);
        GridData gd_lblNumericVariables = new GridData(16384, 0x1000000, true, false, 1, 1);
        gd_lblNumericVariables.horizontalIndent = 6;
        lblNumericVariables.setLayoutData((Object)gd_lblNumericVariables);
        lblNumericVariables.setText("Numeric Variable(s):");
        lblNumericVariables.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        new Label(container, 0);
        new Label(container, 0);
        new Label(container, 0);
        Label lblVariable = new Label(container, 0);
        lblVariable.setLayoutData((Object)new GridData(16384, 0x1000000, true, false, 1, 1));
        lblVariable.setText("Variable:");
        lblVariable.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        new Label(container, 0);
        ListViewer listViewer_2 = new ListViewer(container, 2818);
        this.lstNumericVars = listViewer_2.getList();
        GridData gd_lstNumericVars = new GridData(4, 4, true, true, 1, 1);
        gd_lstNumericVars.widthHint = 150;
        gd_lstNumericVars.horizontalIndent = 6;
        gd_lstNumericVars.heightHint = 100;
        this.lstNumericVars.setLayoutData((Object)gd_lstNumericVars);
        this.lstNumericVars.setItems(new String[0]);
        this.label_4 = new Label(container, 0);
        GridData gd_label_4 = new GridData(16384, 0x1000000, false, true, 1, 1);
        gd_label_4.widthHint = 14;
        this.label_4.setLayoutData((Object)gd_label_4);
        this.btnAdd1 = new Button(container, 0);
        GridData gd_btnAdd1 = new GridData(4, 128, false, true, 1, 1);
        gd_btnAdd1.widthHint = 90;
        this.btnAdd1.setLayoutData((Object)gd_btnAdd1);
        this.btnAdd1.setText("Add");
        this.label = new Label(container, 0);
        GridData gd_label = new GridData(131072, 0x1000000, false, true, 1, 1);
        gd_label.widthHint = 13;
        this.label.setLayoutData((Object)gd_label);
        this.txtVariable = new Text(container, 2048);
        this.txtVariable.setForeground(SWTResourceManager.getColor(2));
        this.txtVariable.setBackground(SWTResourceManager.getColor(1));
        this.txtVariable.setEditable(false);
        GridData gd_txtVariable = new GridData(4, 128, true, true, 1, 1);
        gd_txtVariable.widthHint = 150;
        this.txtVariable.setLayoutData((Object)gd_txtVariable);
        this.lblNewLabel = new Label(container, 0);
        GridData gd_lblNewLabel = new GridData(16384, 0x1000000, false, true, 1, 1);
        gd_lblNewLabel.widthHint = 8;
        this.lblNewLabel.setLayoutData((Object)gd_lblNewLabel);
        this.btnSetToFactor = new Button(container, 0);
        GridData gd_btnSetToFactor = new GridData(131072, 0x1000000, true, false, 1, 1);
        gd_btnSetToFactor.heightHint = 24;
        gd_btnSetToFactor.widthHint = 110;
        this.btnSetToFactor.setLayoutData((Object)gd_btnSetToFactor);
        this.btnSetToFactor.setText("Set to Factor");
        new Label(container, 0);
        new Label(container, 0);
        new Label(container, 0);
        new Label(container, 0);
        new Label(container, 0);
        Label lblFactors = new Label(container, 0);
        GridData gd_lblFactors = new GridData(16384, 0x1000000, true, false, 1, 1);
        gd_lblFactors.horizontalIndent = 6;
        lblFactors.setLayoutData((Object)gd_lblFactors);
        lblFactors.setText("Factor(s):");
        lblFactors.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        new Label(container, 0);
        new Label(container, 0);
        new Label(container, 0);
        Label lblGroupBy = new Label(container, 0);
        lblGroupBy.setLayoutData((Object)new GridData(16384, 0x1000000, true, false, 1, 1));
        lblGroupBy.setText("Group by:");
        lblGroupBy.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        new Label(container, 0);
        ListViewer listViewer_3 = new ListViewer(container, 2818);
        this.lstFactors = listViewer_3.getList();
        GridData gd_lstFactors = new GridData(4, 4, true, true, 1, 1);
        gd_lstFactors.widthHint = 150;
        gd_lstFactors.horizontalIndent = 6;
        gd_lstFactors.heightHint = 100;
        this.lstFactors.setLayoutData((Object)gd_lstFactors);
        this.lstFactors.setItems(new String[0]);
        new Label(container, 0);
        this.btnAdd2 = new Button(container, 0);
        this.btnAdd2.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
            }
        });
        GridData gd_btnAdd2 = new GridData(4, 128, false, true, 1, 1);
        gd_btnAdd2.widthHint = 90;
        this.btnAdd2.setLayoutData((Object)gd_btnAdd2);
        this.btnAdd2.setText("Add");
        new Label(container, 0);
        this.txtGroupBy = new Text(container, 2048);
        this.txtGroupBy.setBackground(SWTResourceManager.getColor(1));
        this.txtGroupBy.setEditable(false);
        GridData gd_txtGroupBy = new GridData(4, 128, true, true, 1, 1);
        gd_txtGroupBy.widthHint = 150;
        this.txtGroupBy.setLayoutData((Object)gd_txtGroupBy);
        new Label(container, 0);
        this.lblAvailableDist = new Label(container, 0);
        GridData gd_lblAvailableDist = new GridData(16384, 0x1000000, true, false, 1, 1);
        gd_lblAvailableDist.horizontalIndent = 6;
        this.lblAvailableDist.setLayoutData((Object)gd_lblAvailableDist);
        this.lblAvailableDist.setText("Available Distributions:");
        this.lblAvailableDist.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        new Label(container, 0);
        new Label(container, 0);
        new Label(container, 0);
        this.lblSelectedDist = new Label(container, 0);
        this.lblSelectedDist.setLayoutData((Object)new GridData(16384, 0x1000000, true, false, 1, 1));
        this.lblSelectedDist.setText("Selected Distribution(s):");
        this.lblSelectedDist.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        new Label(container, 0);
        this.listViewer = new ListViewer(container, 2818);
        this.lstAvailableDist = this.listViewer.getList();
        GridData gd_lstAvailableDist = new GridData(4, 4, true, true, 1, 1);
        gd_lstAvailableDist.widthHint = 150;
        gd_lstAvailableDist.horizontalIndent = 6;
        gd_lstAvailableDist.heightHint = 70;
        this.lstAvailableDist.setLayoutData((Object)gd_lstAvailableDist);
        this.lstAvailableDist.setItems(new String[]{"Beta", "Exponential", "Gamma", "Geometric", "Log-Normal", "Logistic", "Negative Binomial", "Normal", "Poisson"});
        new Label(container, 0);
        this.btnAdd = new Button(container, 0);
        GridData gd_btnAdd = new GridData(4, 0x1000000, false, true, 1, 1);
        gd_btnAdd.widthHint = 90;
        this.btnAdd.setLayoutData((Object)gd_btnAdd);
        this.btnAdd.setText("Add");
        new Label(container, 0);
        this.listViewer_1 = new ListViewer(container, 2818);
        this.lstSelectedDist = this.listViewer_1.getList();
        GridData gd_lstSelectedDist = new GridData(4, 4, true, true, 1, 1);
        gd_lstSelectedDist.heightHint = 70;
        gd_lstSelectedDist.widthHint = 150;
        this.lstSelectedDist.setLayoutData((Object)gd_lstSelectedDist);
        this.lstSelectedDist.setItems(new String[0]);
        new Label(container, 0);
        this.lblFittingMethod = new Label(container, 0);
        GridData gd_lblFittingMethod = new GridData(16384, 0x1000000, true, false, 1, 1);
        gd_lblFittingMethod.horizontalIndent = 6;
        this.lblFittingMethod.setLayoutData((Object)gd_lblFittingMethod);
        this.lblFittingMethod.setText("Fitting Method:");
        this.lblFittingMethod.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        new Label(container, 0);
        new Label(container, 0);
        new Label(container, 0);
        this.cmboFittingMethod = new Combo(container, 0);
        this.cmboFittingMethod.setItems(new String[]{"maximum likelihood", "moment matching", "maximum goodness-of-fit"});
        this.cmboFittingMethod.setLayoutData((Object)new GridData(4, 0x1000000, true, false, 1, 1));
        this.cmboFittingMethod.select(0);
        new Label(container, 0);
        this.initializeContent();
        return container;
    }

    void initializeContent() {
        this.listManager.initializeSingleButtonList(this.lstAvailableDist, this.lstSelectedDist, this.btnSetToFactor, this.btnAdd);
        this.listManager.initializeSingleSelectionList(this.lstNumericVars, this.txtVariable, this.btnAdd1);
        this.listManager.initializeSingleSelectionList(this.lstFactors, this.txtGroupBy, this.btnAdd2);
        this.listManager.initializeVariableMoveList(this.lstNumericVars, this.lstFactors, this.btnSetToFactor, this.filePath);
        this.listManager.initializeNumericList(this.lstNumericVars, this.filePath);
        this.listManager.initializeFactorList(this.lstFactors, this.filePath);
    }

    protected void buttonPressed(int ID) {
        if (ID == 0) {
            this.okPressed();
        } else if (ID == 1) {
            this.cancelPressed();
        } else if (ID == 8) {
            this.resetDialog();
        }
    }

    void resetDialog() {
        this.lstSelectedDist.removeAll();
        this.lstNumericVars.removeAll();
        this.lstFactors.removeAll();
        this.txtVariable.setText("");
        this.txtGroupBy.setText("");
        this.listManager.initializeNumericList(this.lstNumericVars, this.filePath);
        this.listManager.initializeFactorList(this.lstFactors, this.filePath);
        this.btnAdd.setText("Add");
        this.btnAdd1.setText("Add");
        this.btnAdd2.setText("Add");
        this.btnSetToFactor.setText("Set to Factor");
        this.btnAdd.setEnabled(false);
        this.btnAdd1.setEnabled(false);
        this.btnAdd2.setEnabled(false);
        this.btnSetToFactor.setEnabled(false);
        this.lstAvailableDist.setItems(new String[]{"Beta", "Exponential", "Gamma", "Geometric", "Log-Normal", "Logistic", "Negative Binomial", "Normal", "Poisson"});
        this.cmboFittingMethod.setText("maximum likelihood");
    }

    protected void okPressed() {
        if (this.lstSelectedDist.getItemCount() < 1) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"Could not perform the analysis. \n\n Reason: Incomplete requirements. Make sure to fill up all the required fields");
            return;
        }
        if (this.txtVariable.getText().isEmpty()) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"Could not perform the analysis. \n\n Reason: Incomplete requirements. Make sure to fill up all the required fields");
            return;
        }
        OperationProgressDialog rInfo = new OperationProgressDialog(this.getShell(), "Performing Analysis");
        rInfo.open();
        this.btnOkay.setEnabled(false);
        String dataFileName = this.filePath.replace(File.separator, "/");
        String outputPath = StarAnalysisUtilities.createOutputFolder(this.filePath, "FittingDistributions");
        String yVar = this.txtVariable.getText();
        String byVar = this.txtGroupBy.getText();
        String[] distrib = this.getDistributions(this.lstSelectedDist.getItems());
        String fitMethod = this.FittingMethods[this.cmboFittingMethod.getSelectionIndex()];
        if (byVar.equals("")) {
            byVar = null;
        }
        StarAnalysisUtilities.testVarArgs(dataFileName, outputPath.replace(File.separator, "/"), yVar, byVar, distrib, fitMethod);
        ProjectExplorerView.rJavaManager.getSTARAnalysisManager().doFitDistribution(dataFileName, outputPath.replace(File.separator, "/"), yVar, byVar, distrib, fitMethod);
        this.getShell().setMinimized(true);
        StarAnalysisUtilities.openAndRefreshFolder(outputPath);
        this.btnOkay.setEnabled(true);
        rInfo.close();
    }

    private String[] getDistributions(String[] items) {
        String[] distributions = new String[items.length];
        int i = 0;
        while (i < items.length) {
            String s = items[i];
            distributions[i] = s.equals("Beta") ? "beta" : (s.equals("Exponential") ? "exp" : (s.equals("Gamma") ? "gamma" : (s.equals("Geometric") ? "geom" : (s.equals("Log-Normal") ? "lnorm" : (s.equals("Logistic") ? "logis" : (s.equals("Negative Binomial") ? "nbinom" : (s.equals("Normal") ? "norm" : "pois")))))));
            ++i;
        }
        return distributions;
    }

    protected void createButtonsForButtonBar(Composite parent) {
        this.createButton(parent, 8, "Reset", false);
        this.btnOkay = this.createButton(parent, 0, IDialogConstants.OK_LABEL, true);
        this.createButton(parent, 1, IDialogConstants.CANCEL_LABEL, false);
    }

    protected Point getInitialSize() {
        return new Point(445, 551);
    }
}

