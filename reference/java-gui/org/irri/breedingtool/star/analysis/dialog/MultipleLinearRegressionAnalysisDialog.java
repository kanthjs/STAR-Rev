/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.jface.dialogs.Dialog
 *  org.eclipse.jface.dialogs.IDialogConstants
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
 */
package org.irri.breedingtool.star.analysis.dialog;

import java.io.File;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
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
import org.irri.breedingtool.application.handler.PartStackHandler;
import org.irri.breedingtool.datamanipulation.dialog.OperationProgressDialog;
import org.irri.breedingtool.projectexplorer.view.ProjectExplorerView;
import org.irri.breedingtool.utility.DialogFormUtility;
import org.irri.breedingtool.utility.DialogListValidator;
import org.irri.breedingtool.utility.StarAnalysisUtilities;

public class MultipleLinearRegressionAnalysisDialog
extends Dialog {
    private Button btnOk;
    private Button btnAddIndependentVariable;
    private Button btnAddDependentVariable;
    private List lstAvailableData;
    private List lstDependentVariables;
    private List lstIndependentVariables;
    private String filePath = PartStackHandler.getActiveElementID();
    private DialogFormUtility listManager = new DialogFormUtility();
    private TabItem tbtmOptions_1;
    private Composite composite_1;
    private Group grpSelection;
    private Combo cmboMethod;
    private Combo cmboStatistic;
    private Button btnVarianceInflationFactors;
    private Button btnDurbinwatson;
    private Button btnIncludeConstantEquation;
    private Button btnCoefficientInterval;
    private Button btnCovarianceMatrix;
    private Button btnBreuschpagan;
    private Button btnGoldfeldquandt;
    private Button btnShapirowilk;
    private Button btnShapirofrancia;
    private Button btnLilliefors;
    private Button btnCramervonMises;
    private Button btnAndersondarling;
    private Group grpDisplay;
    private Group grpSave;
    private Group grpRegressionCoefficients;
    private Group grpTestForHeteroskedasticity;
    private Group grpNormalityTest;
    private Spinner txtConfidenceInterval;
    private Button btnLeverageValues;
    private Button btnCooksDistance;
    private Label lblMethod;
    private Label lblStatistic;
    private Button btnCorrelationMatrix;
    private Button btnScatterplotMatrix;
    private String[] method = new String[]{"none", "allposs", "forward", "backward", "stepwise"};
    private String[] stat = new String[]{"adjrsq", "rsquare", "mallow"};

    public MultipleLinearRegressionAnalysisDialog(Shell parentShell) {
        super(parentShell);
        this.setShellStyle(2288);
    }

    protected void configureShell(Shell shell) {
        super.configureShell(shell);
        shell.setText("Regression Analysis : " + new File(this.filePath).getName());
    }

    protected Control createDialogArea(Composite parent) {
        Composite container = (Composite)super.createDialogArea(parent);
        FillLayout fl_container = new FillLayout(256);
        fl_container.marginHeight = 8;
        fl_container.marginWidth = 8;
        container.setLayout((Layout)fl_container);
        TabFolder tabFolder = new TabFolder(container, 0);
        TabItem tbtmTitleCase = new TabItem(tabFolder, 0);
        tbtmTitleCase.setText("Variable Description");
        Composite composite = new Composite((Composite)tabFolder, 0);
        tbtmTitleCase.setControl((Control)composite);
        composite.setLayout((Layout)new GridLayout(3, false));
        Label lblAvailableData = new Label(composite, 0);
        lblAvailableData.setLayoutData((Object)new GridData(4, 0x1000000, true, false, 1, 1));
        lblAvailableData.setText("Numeric Variables");
        new Label(composite, 0);
        Label lblDependentVariables = new Label(composite, 0);
        lblDependentVariables.setText("Dependent Variable(s)");
        this.lstAvailableData = new List(composite, 2818);
        GridData gd_lstAvailableData = new GridData(4, 4, true, true, 1, 9);
        gd_lstAvailableData.heightHint = 130;
        gd_lstAvailableData.widthHint = 80;
        this.lstAvailableData.setLayoutData((Object)gd_lstAvailableData);
        new Label(composite, 0);
        this.lstDependentVariables = new List(composite, 2818);
        GridData gd_lstDependentVariables = new GridData(4, 4, true, true, 1, 4);
        gd_lstDependentVariables.heightHint = 80;
        gd_lstDependentVariables.widthHint = 80;
        this.lstDependentVariables.setLayoutData((Object)gd_lstDependentVariables);
        new Label(composite, 0);
        new Label(composite, 0);
        this.btnAddDependentVariable = new Button(composite, 0);
        GridData gd_btnAddDependentVariable = new GridData(4, 128, false, false, 1, 1);
        gd_btnAddDependentVariable.widthHint = 90;
        this.btnAddDependentVariable.setLayoutData((Object)gd_btnAddDependentVariable);
        this.btnAddDependentVariable.setText("Add");
        new Label(composite, 0);
        Label lblIndependentVariables = new Label(composite, 0);
        lblIndependentVariables.setText("Independent Variable(s)");
        new Label(composite, 0);
        this.lstIndependentVariables = new List(composite, 2818);
        GridData gd_lstIndependentVariables = new GridData(4, 4, true, true, 1, 4);
        gd_lstIndependentVariables.heightHint = 80;
        gd_lstIndependentVariables.widthHint = 80;
        this.lstIndependentVariables.setLayoutData((Object)gd_lstIndependentVariables);
        new Label(composite, 0);
        new Label(composite, 0);
        this.btnAddIndependentVariable = new Button(composite, 0);
        GridData gd_btnAddIndependentVariable = new GridData(4, 128, false, false, 1, 1);
        gd_btnAddIndependentVariable.widthHint = 90;
        this.btnAddIndependentVariable.setLayoutData((Object)gd_btnAddIndependentVariable);
        this.btnAddIndependentVariable.setText("Add");
        this.tbtmOptions_1 = new TabItem(tabFolder, 0);
        this.tbtmOptions_1.setText("Options");
        this.composite_1 = new Composite((Composite)tabFolder, 0);
        this.tbtmOptions_1.setControl((Control)this.composite_1);
        this.composite_1.setLayout((Layout)new GridLayout(2, false));
        this.grpSelection = new Group(this.composite_1, 0);
        this.grpSelection.setLayoutData((Object)new GridData(4, 4, true, true, 1, 1));
        this.grpSelection.setLayout((Layout)new GridLayout(4, false));
        this.grpSelection.setText("Selection");
        this.lblMethod = new Label((Composite)this.grpSelection, 0);
        this.lblMethod.setEnabled(false);
        this.lblMethod.setText("Method:");
        new Label((Composite)this.grpSelection, 0);
        this.cmboMethod = new Combo((Composite)this.grpSelection, 8);
        this.cmboMethod.setEnabled(false);
        this.cmboMethod.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (MultipleLinearRegressionAnalysisDialog.this.cmboMethod.getSelectionIndex() == 1) {
                    MultipleLinearRegressionAnalysisDialog.this.lblStatistic.setEnabled(true);
                    MultipleLinearRegressionAnalysisDialog.this.cmboStatistic.setEnabled(true);
                    MultipleLinearRegressionAnalysisDialog.this.btnScatterplotMatrix.setEnabled(false);
                    MultipleLinearRegressionAnalysisDialog.this.btnVarianceInflationFactors.setEnabled(false);
                    MultipleLinearRegressionAnalysisDialog.this.btnDurbinwatson.setEnabled(false);
                    MultipleLinearRegressionAnalysisDialog.this.btnLeverageValues.setEnabled(false);
                    MultipleLinearRegressionAnalysisDialog.this.btnCooksDistance.setEnabled(false);
                    MultipleLinearRegressionAnalysisDialog.this.btnCoefficientInterval.setEnabled(false);
                    MultipleLinearRegressionAnalysisDialog.this.btnCovarianceMatrix.setEnabled(false);
                    MultipleLinearRegressionAnalysisDialog.this.btnBreuschpagan.setEnabled(false);
                    MultipleLinearRegressionAnalysisDialog.this.btnGoldfeldquandt.setEnabled(false);
                    MultipleLinearRegressionAnalysisDialog.this.btnShapirowilk.setEnabled(false);
                    MultipleLinearRegressionAnalysisDialog.this.btnShapirofrancia.setEnabled(false);
                    MultipleLinearRegressionAnalysisDialog.this.btnLilliefors.setEnabled(false);
                    MultipleLinearRegressionAnalysisDialog.this.btnCramervonMises.setEnabled(false);
                    MultipleLinearRegressionAnalysisDialog.this.btnAndersondarling.setEnabled(false);
                } else {
                    MultipleLinearRegressionAnalysisDialog.this.lblStatistic.setEnabled(false);
                    MultipleLinearRegressionAnalysisDialog.this.cmboStatistic.setEnabled(false);
                    MultipleLinearRegressionAnalysisDialog.this.btnScatterplotMatrix.setEnabled(true);
                    MultipleLinearRegressionAnalysisDialog.this.btnVarianceInflationFactors.setEnabled(true);
                    MultipleLinearRegressionAnalysisDialog.this.btnDurbinwatson.setEnabled(true);
                    MultipleLinearRegressionAnalysisDialog.this.btnLeverageValues.setEnabled(true);
                    MultipleLinearRegressionAnalysisDialog.this.btnCooksDistance.setEnabled(true);
                    MultipleLinearRegressionAnalysisDialog.this.btnCoefficientInterval.setEnabled(true);
                    MultipleLinearRegressionAnalysisDialog.this.btnCovarianceMatrix.setEnabled(true);
                    MultipleLinearRegressionAnalysisDialog.this.btnBreuschpagan.setEnabled(true);
                    MultipleLinearRegressionAnalysisDialog.this.btnGoldfeldquandt.setEnabled(true);
                    MultipleLinearRegressionAnalysisDialog.this.btnShapirowilk.setEnabled(true);
                    MultipleLinearRegressionAnalysisDialog.this.btnShapirofrancia.setEnabled(true);
                    MultipleLinearRegressionAnalysisDialog.this.btnLilliefors.setEnabled(true);
                    MultipleLinearRegressionAnalysisDialog.this.btnCramervonMises.setEnabled(true);
                    MultipleLinearRegressionAnalysisDialog.this.btnAndersondarling.setEnabled(true);
                }
            }
        });
        this.cmboMethod.setLayoutData((Object)new GridData(16384, 0x1000000, false, false, 2, 1));
        this.cmboMethod.setItems(new String[]{"[None]", "All possible regression", "Forward selection", "Backward elimination", "Stepwise regression"});
        this.cmboMethod.select(0);
        this.lblStatistic = new Label((Composite)this.grpSelection, 0);
        this.lblStatistic.setEnabled(false);
        this.lblStatistic.setLayoutData((Object)new GridData(16384, 0x1000000, false, false, 2, 1));
        this.lblStatistic.setText("Statistic:");
        this.cmboStatistic = new Combo((Composite)this.grpSelection, 8);
        this.cmboStatistic.setEnabled(false);
        this.cmboStatistic.setLayoutData((Object)new GridData(4, 0x1000000, false, false, 2, 1));
        this.cmboStatistic.setItems(new String[]{"Adjusted R-squared", "R-squared", "Mallow's C(p)"});
        this.cmboStatistic.select(0);
        this.grpRegressionCoefficients = new Group(this.composite_1, 0);
        this.grpRegressionCoefficients.setLayoutData((Object)new GridData(4, 4, true, true, 1, 1));
        this.grpRegressionCoefficients.setText("Regression Coefficients");
        this.grpRegressionCoefficients.setLayout((Layout)new GridLayout(3, false));
        this.btnCoefficientInterval = new Button((Composite)this.grpRegressionCoefficients, 32);
        this.btnCoefficientInterval.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                MultipleLinearRegressionAnalysisDialog.this.txtConfidenceInterval.setEnabled(MultipleLinearRegressionAnalysisDialog.this.btnCoefficientInterval.getSelection());
            }
        });
        this.btnCoefficientInterval.setLayoutData((Object)new GridData(16384, 0x1000000, true, true, 1, 1));
        this.btnCoefficientInterval.setText("Coefficient Interval");
        this.txtConfidenceInterval = new Spinner((Composite)this.grpRegressionCoefficients, 2048);
        this.txtConfidenceInterval.setLayoutData((Object)new GridData(4, 0x1000000, true, false, 1, 1));
        this.txtConfidenceInterval.setMaximum(99);
        this.txtConfidenceInterval.setMinimum(95);
        this.txtConfidenceInterval.setEnabled(false);
        Label label_1 = new Label((Composite)this.grpRegressionCoefficients, 0);
        label_1.setLayoutData((Object)new GridData(4, 0x1000000, true, false, 1, 1));
        label_1.setText("%");
        this.btnCovarianceMatrix = new Button((Composite)this.grpRegressionCoefficients, 32);
        this.btnCovarianceMatrix.setLayoutData((Object)new GridData(4, 4, true, true, 1, 1));
        this.btnCovarianceMatrix.setText("Covariance Matrix");
        new Label((Composite)this.grpRegressionCoefficients, 0);
        new Label((Composite)this.grpRegressionCoefficients, 0);
        this.grpDisplay = new Group(this.composite_1, 0);
        this.grpDisplay.setLayoutData((Object)new GridData(4, 4, true, false, 1, 2));
        this.grpDisplay.setText("Display");
        this.grpDisplay.setLayout((Layout)new GridLayout(1, false));
        this.btnIncludeConstantEquation = new Button((Composite)this.grpDisplay, 32);
        this.btnIncludeConstantEquation.setLayoutData((Object)new GridData(4, 4, true, true, 1, 1));
        this.btnIncludeConstantEquation.setText("Include constant in the equation");
        this.btnIncludeConstantEquation.setSelection(true);
        this.btnCorrelationMatrix = new Button((Composite)this.grpDisplay, 32);
        this.btnCorrelationMatrix.setLayoutData((Object)new GridData(16384, 4, true, true, 1, 1));
        this.btnCorrelationMatrix.setText("Correlation Matrix");
        this.btnScatterplotMatrix = new Button((Composite)this.grpDisplay, 32);
        this.btnScatterplotMatrix.setLayoutData((Object)new GridData(16384, 4, true, true, 1, 1));
        this.btnScatterplotMatrix.setText("Scatterplot Matrix");
        this.btnVarianceInflationFactors = new Button((Composite)this.grpDisplay, 32);
        this.btnVarianceInflationFactors.setLayoutData((Object)new GridData(4, 4, true, true, 1, 1));
        this.btnVarianceInflationFactors.setText("Variance Inflation Factors");
        this.btnDurbinwatson = new Button((Composite)this.grpDisplay, 32);
        this.btnDurbinwatson.setLayoutData((Object)new GridData(4, 4, true, true, 1, 1));
        this.btnDurbinwatson.setText("Durbin-Watson");
        this.grpTestForHeteroskedasticity = new Group(this.composite_1, 0);
        this.grpTestForHeteroskedasticity.setLayoutData((Object)new GridData(4, 4, true, false, 1, 1));
        this.grpTestForHeteroskedasticity.setText("Test for Heteroscedasticity");
        this.grpTestForHeteroskedasticity.setLayout((Layout)new GridLayout(1, false));
        this.btnBreuschpagan = new Button((Composite)this.grpTestForHeteroskedasticity, 32);
        this.btnBreuschpagan.setLayoutData((Object)new GridData(4, 4, true, true, 1, 1));
        this.btnBreuschpagan.setText("Breusch-Pagan");
        this.btnBreuschpagan.setData((Object)"bpagan");
        this.btnGoldfeldquandt = new Button((Composite)this.grpTestForHeteroskedasticity, 32);
        this.btnGoldfeldquandt.setLayoutData((Object)new GridData(4, 4, true, true, 1, 1));
        this.btnGoldfeldquandt.setText("Goldfeld-Quandt");
        this.btnGoldfeldquandt.setData((Object)"gquandt");
        this.grpNormalityTest = new Group(this.composite_1, 0);
        this.grpNormalityTest.setLayoutData((Object)new GridData(4, 4, true, true, 1, 2));
        this.grpNormalityTest.setText("Normality Test for Residuals");
        this.grpNormalityTest.setLayout((Layout)new GridLayout(1, false));
        this.btnShapirowilk = new Button((Composite)this.grpNormalityTest, 32);
        this.btnShapirowilk.setLayoutData((Object)new GridData(16384, 4, true, true, 1, 1));
        this.btnShapirowilk.setText("Shapiro-Wilk");
        this.btnShapirofrancia = new Button((Composite)this.grpNormalityTest, 32);
        this.btnShapirofrancia.setLayoutData((Object)new GridData(16384, 4, true, true, 1, 1));
        this.btnShapirofrancia.setText("Shapiro-Francia");
        this.btnLilliefors = new Button((Composite)this.grpNormalityTest, 32);
        this.btnLilliefors.setLayoutData((Object)new GridData(16384, 4, true, true, 1, 1));
        this.btnLilliefors.setText("Lilliefors (Kolmogorov-Smirnov)");
        this.btnCramervonMises = new Button((Composite)this.grpNormalityTest, 32);
        this.btnCramervonMises.setLayoutData((Object)new GridData(16384, 4, true, true, 1, 1));
        this.btnCramervonMises.setText("Cramer-Von Mises");
        this.btnAndersondarling = new Button((Composite)this.grpNormalityTest, 32);
        this.btnAndersondarling.setLayoutData((Object)new GridData(16384, 4, true, true, 1, 1));
        this.btnAndersondarling.setText("Anderson-Darling");
        this.grpSave = new Group(this.composite_1, 0);
        this.grpSave.setLayoutData((Object)new GridData(4, 4, true, true, 1, 1));
        this.grpSave.setText("Save");
        this.grpSave.setLayout((Layout)new GridLayout(1, false));
        this.btnLeverageValues = new Button((Composite)this.grpSave, 32);
        this.btnLeverageValues.setLayoutData((Object)new GridData(4, 4, true, true, 1, 1));
        this.btnLeverageValues.setText("Leverage Values");
        this.btnCooksDistance = new Button((Composite)this.grpSave, 32);
        this.btnCooksDistance.setLayoutData((Object)new GridData(4, 4, true, true, 1, 1));
        this.btnCooksDistance.setText("Cook's Distance");
        this.initializeContent();
        return container;
    }

    void initializeContent() {
        this.listManager.initializeSingleButtonList(this.lstAvailableData, this.lstIndependentVariables, this.btnAddIndependentVariable, new varValidate(this.cmboMethod, this.btnAddIndependentVariable, this.lblMethod));
        this.listManager.initializeSingleButtonList(this.lstAvailableData, this.lstDependentVariables, this.btnAddIndependentVariable, this.btnAddDependentVariable);
        this.listManager.initializeNumericList(this.lstAvailableData, this.filePath);
    }

    protected void buttonPressed(int buttonID) {
        if (buttonID == 0) {
            this.okPressed();
        } else if (buttonID == 8) {
            this.listManager.setCheckBoxesToBoolean(new Composite[]{this.grpDisplay, this.grpSave, this.grpNormalityTest, this.grpTestForHeteroskedasticity}, false);
            this.btnCoefficientInterval.setSelection(false);
            this.btnCovarianceMatrix.setSelection(false);
            this.cmboMethod.setText("[None]");
            this.cmboStatistic.setText("Adjusted R-squared");
            this.cmboMethod.setEnabled(false);
            this.lblMethod.setEnabled(false);
            this.btnIncludeConstantEquation.setSelection(true);
            this.txtConfidenceInterval.setSelection(95);
            this.btnAddDependentVariable.setText("Add");
            this.btnAddIndependentVariable.setText("Add");
            this.btnAddDependentVariable.setEnabled(false);
            this.btnAddIndependentVariable.setEnabled(false);
            this.lstAvailableData.removeAll();
            this.lstDependentVariables.removeAll();
            this.lstIndependentVariables.removeAll();
            this.listManager.initializeNumericList(this.lstAvailableData, this.filePath);
            this.txtConfidenceInterval.setEnabled(false);
            this.lblStatistic.setEnabled(false);
            this.cmboStatistic.setEnabled(false);
            this.btnScatterplotMatrix.setEnabled(true);
            this.btnVarianceInflationFactors.setEnabled(true);
            this.btnDurbinwatson.setEnabled(true);
            this.btnLeverageValues.setEnabled(true);
            this.btnCooksDistance.setEnabled(true);
            this.btnCoefficientInterval.setEnabled(true);
            this.btnCovarianceMatrix.setEnabled(true);
            this.btnBreuschpagan.setEnabled(true);
            this.btnGoldfeldquandt.setEnabled(true);
            this.btnShapirowilk.setEnabled(true);
            this.btnShapirofrancia.setEnabled(true);
            this.btnLilliefors.setEnabled(true);
            this.btnCramervonMises.setEnabled(true);
            this.btnAndersondarling.setEnabled(true);
        } else {
            this.close();
        }
    }

    protected void okPressed() {
        String outputFolder = String.valueOf(StarAnalysisUtilities.createOutputFolder(this.filePath, "MultipleLinearRegression")) + File.separator;
        String varSelection = this.method[this.cmboMethod.getSelectionIndex()];
        String statistic = this.stat[this.cmboStatistic.getSelectionIndex()];
        OperationProgressDialog rInfo = new OperationProgressDialog(this.getShell(), "Performing Analysis");
        rInfo.open();
        this.btnOk.setEnabled(false);
        boolean scatterplot = this.btnScatterplotMatrix.getSelection();
        StarAnalysisUtilities.testVarArgs(this.filePath.replace("\\", "/"), outputFolder.replace("\\", "/"), this.lstDependentVariables.getItems(), this.lstIndependentVariables.getItems(), this.btnIncludeConstantEquation.getSelection(), this.btnCorrelationMatrix.getSelection(), varSelection, statistic, this.btnCoefficientInterval.getSelection(), this.txtConfidenceInterval.getSelection(), this.btnCovarianceMatrix.getSelection(), this.listManager.getCheckBoxesValue((Composite)this.grpNormalityTest), this.listManager.getCheckBoxesValue(this.btnBreuschpagan, this.btnGoldfeldquandt), this.btnDurbinwatson.getSelection(), this.btnVarianceInflationFactors.getSelection(), this.btnCooksDistance.getSelection(), this.btnLeverageValues.getSelection(), scatterplot);
        ProjectExplorerView.rJavaManager.getSTARAnalysisManager().doLinearRegSelection(this.filePath.replace("\\", "/"), outputFolder.replace("\\", "/"), this.lstDependentVariables.getItems(), this.lstIndependentVariables.getItems(), this.btnIncludeConstantEquation.getSelection(), this.btnCorrelationMatrix.getSelection(), varSelection, statistic, this.btnCoefficientInterval.getSelection(), (double)this.txtConfidenceInterval.getSelection(), this.btnCovarianceMatrix.getSelection(), this.listManager.getCheckBoxesValue((Composite)this.grpNormalityTest), this.listManager.getCheckBoxesValue(this.btnBreuschpagan, this.btnGoldfeldquandt), this.btnDurbinwatson.getSelection(), this.btnVarianceInflationFactors.getSelection(), this.btnCooksDistance.getSelection(), this.btnLeverageValues.getSelection(), scatterplot);
        this.getShell().setMinimized(true);
        StarAnalysisUtilities.openAndRefreshFolder(outputFolder);
        this.btnOk.setEnabled(true);
        rInfo.close();
    }

    protected void createButtonsForButtonBar(Composite parent) {
        this.createButton(parent, 8, "Reset", false);
        this.btnOk = this.createButton(parent, 0, IDialogConstants.OK_LABEL, true);
        this.createButton(parent, 1, IDialogConstants.CANCEL_LABEL, false);
    }

    protected Point getInitialSize() {
        return new Point(475, 428);
    }

    public class varValidate
    extends DialogListValidator {
        Combo cmboMethod;
        Button btnAddIndependentVariable;
        Label lblMethod;

        public varValidate(Combo cmboMethod, Button btnAddIndependentVariable, Label lblMethod) {
            this.btnAddIndependentVariable = btnAddIndependentVariable;
            this.cmboMethod = cmboMethod;
            this.lblMethod = lblMethod;
        }

        @Override
        public void postAddProcess() {
            this.cmboMethod.setEnabled(MultipleLinearRegressionAnalysisDialog.this.lstIndependentVariables.getItemCount() > 1);
            this.lblMethod.setEnabled(MultipleLinearRegressionAnalysisDialog.this.lstIndependentVariables.getItemCount() > 1);
        }

        @Override
        public void postRemoveProcess() {
            this.cmboMethod.setEnabled(MultipleLinearRegressionAnalysisDialog.this.lstIndependentVariables.getItemCount() > 1);
            this.lblMethod.setEnabled(MultipleLinearRegressionAnalysisDialog.this.lstIndependentVariables.getItemCount() > 1);
        }
    }
}

