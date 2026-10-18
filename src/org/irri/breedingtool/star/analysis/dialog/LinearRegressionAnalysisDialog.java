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
import java.util.ArrayList;
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
import org.irri.breedingtool.utility.StarAnalysisUtilities;

public class LinearRegressionAnalysisDialog
extends Dialog {
    private Button btnOk;
    private Button btnAddIndependentVariable;
    private Button btnAddDependentVariable;
    private List lstAvailableData;
    private List lstDependentVariables;
    private List lstIndependentVariables;
    private Button btnAndersondarling;
    private Button btnCramervonMises;
    private Button btnLilliefors;
    private Button btnShapirofrancia;
    private Button btnShapirowilk;
    private Composite cmpOptions;
    private Button btnVarianceInflationFactors;
    private Button btnCovarienceMatrix;
    private Button btnCoefficientInterval;
    private Spinner txtConfidenceInterval;
    private Button btnBreuschpagar;
    private Button btnGoldfeldquandt;
    private Group grpRegressionCoefficients;
    private Group grpTestForHeteroskedasticity;
    private Group grpNormalityTest;
    private String filePath = PartStackHandler.getActiveElementID();
    private Button btnIncludeConstantEquation;
    private Button btnCooksDistance;
    private Button btnLeverageValues;
    private TabItem tbtmOptions;
    private Button btnDurbinwatson;
    private DialogFormUtility listManager = new DialogFormUtility();
    private ArrayList<String> tableData = new ArrayList();
    private Group grpDisplay;
    private Group grpSave;

    public LinearRegressionAnalysisDialog(Shell parentShell) {
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
        new Label(composite, 0);
        new Label(composite, 0);
        new Label(composite, 0);
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
        this.listManager.initializeSingleButtonList(this.lstAvailableData, this.lstDependentVariables, this.btnAddDependentVariable);
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
        this.listManager.initializeSingleButtonList(this.lstAvailableData, this.lstIndependentVariables, this.btnAddIndependentVariable);
        this.tbtmOptions = new TabItem(tabFolder, 0);
        this.tbtmOptions.setText("Options");
        this.cmpOptions = new Composite((Composite)tabFolder, 0);
        this.tbtmOptions.setControl((Control)this.cmpOptions);
        this.cmpOptions.setLayout((Layout)new GridLayout(2, false));
        this.grpRegressionCoefficients = new Group(this.cmpOptions, 0);
        this.grpRegressionCoefficients.setLayout((Layout)new GridLayout(3, false));
        GridData gd_grpRegressionCoefficients = new GridData(4, 4, true, true, 1, 1);
        gd_grpRegressionCoefficients.heightHint = 74;
        this.grpRegressionCoefficients.setLayoutData((Object)gd_grpRegressionCoefficients);
        this.grpRegressionCoefficients.setText("Regression Coefficients");
        this.btnCoefficientInterval = new Button((Composite)this.grpRegressionCoefficients, 32);
        this.btnCoefficientInterval.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                LinearRegressionAnalysisDialog.this.txtConfidenceInterval.setEnabled(LinearRegressionAnalysisDialog.this.btnCoefficientInterval.getSelection());
            }
        });
        this.btnCoefficientInterval.setLayoutData((Object)new GridData(16384, 0x1000000, true, true, 1, 1));
        this.btnCoefficientInterval.setText("Coefficient Interval");
        this.txtConfidenceInterval = new Spinner((Composite)this.grpRegressionCoefficients, 2048);
        this.txtConfidenceInterval.setEnabled(false);
        this.txtConfidenceInterval.setLayoutData((Object)new GridData(4, 0x1000000, true, true, 1, 1));
        this.txtConfidenceInterval.setMaximum(99);
        this.txtConfidenceInterval.setMinimum(95);
        Label label = new Label((Composite)this.grpRegressionCoefficients, 0);
        label.setLayoutData((Object)new GridData(4, 0x1000000, true, false, 1, 1));
        label.setText("%");
        this.btnCovarienceMatrix = new Button((Composite)this.grpRegressionCoefficients, 32);
        this.btnCovarienceMatrix.setLayoutData((Object)new GridData(4, 4, true, true, 1, 1));
        this.btnCovarienceMatrix.setText("Covarience Matrix");
        new Label((Composite)this.grpRegressionCoefficients, 0);
        new Label((Composite)this.grpRegressionCoefficients, 0);
        this.grpTestForHeteroskedasticity = new Group(this.cmpOptions, 0);
        this.grpTestForHeteroskedasticity.setLayout((Layout)new GridLayout(1, false));
        GridData gd_grpTestForHeteroskedasticity = new GridData(4, 4, true, true, 1, 1);
        gd_grpTestForHeteroskedasticity.heightHint = 78;
        this.grpTestForHeteroskedasticity.setLayoutData((Object)gd_grpTestForHeteroskedasticity);
        this.grpTestForHeteroskedasticity.setText("Test for Heteroscedasticity");
        this.btnBreuschpagar = new Button((Composite)this.grpTestForHeteroskedasticity, 32);
        this.btnBreuschpagar.setLayoutData((Object)new GridData(4, 4, true, true, 1, 1));
        this.btnBreuschpagar.setText("Breusch-Pagan");
        this.btnBreuschpagar.setData((Object)"bpagan");
        this.btnGoldfeldquandt = new Button((Composite)this.grpTestForHeteroskedasticity, 32);
        this.btnGoldfeldquandt.setLayoutData((Object)new GridData(4, 4, true, true, 1, 1));
        this.btnGoldfeldquandt.setText("Goldfeld-Quandt");
        this.btnGoldfeldquandt.setData((Object)"gqtest");
        this.grpDisplay = new Group(this.cmpOptions, 0);
        this.grpDisplay.setText("Display");
        this.grpDisplay.setLayout((Layout)new GridLayout(1, false));
        GridData gd_grpDisplay = new GridData(4, 4, true, true, 1, 7);
        gd_grpDisplay.heightHint = 99;
        this.grpDisplay.setLayoutData((Object)gd_grpDisplay);
        this.btnVarianceInflationFactors = new Button((Composite)this.grpDisplay, 32);
        this.btnVarianceInflationFactors.setLayoutData((Object)new GridData(4, 4, true, true, 1, 1));
        this.btnVarianceInflationFactors.setText("Variance Inflation Factors");
        this.btnDurbinwatson = new Button((Composite)this.grpDisplay, 32);
        this.btnDurbinwatson.setLayoutData((Object)new GridData(4, 4, true, true, 1, 1));
        this.btnDurbinwatson.setText("Durbin-Watson");
        this.btnIncludeConstantEquation = new Button((Composite)this.grpDisplay, 32);
        this.btnIncludeConstantEquation.setSelection(true);
        this.btnIncludeConstantEquation.setLayoutData((Object)new GridData(4, 4, true, true, 1, 1));
        this.btnIncludeConstantEquation.setText("Include constant in the equation");
        this.grpNormalityTest = new Group(this.cmpOptions, 0);
        this.grpNormalityTest.setLayout((Layout)new GridLayout(1, false));
        GridData gd_grpNormalityTest = new GridData(4, 4, true, true, 1, 8);
        gd_grpNormalityTest.heightHint = 128;
        this.grpNormalityTest.setLayoutData((Object)gd_grpNormalityTest);
        this.grpNormalityTest.setText("Normality Test for Residuals");
        this.btnShapirowilk = new Button((Composite)this.grpNormalityTest, 32);
        this.btnShapirowilk.setLayoutData((Object)new GridData(16384, 4, true, true, 1, 1));
        this.btnShapirowilk.setText("Shapiro-Wilk");
        this.btnShapirowilk.setData((Object)"swilk");
        this.btnShapirofrancia = new Button((Composite)this.grpNormalityTest, 32);
        this.btnShapirofrancia.setLayoutData((Object)new GridData(16384, 4, true, true, 1, 1));
        this.btnShapirofrancia.setText("Shapiro-Francia");
        this.btnShapirofrancia.setData((Object)"sfrancia");
        this.btnLilliefors = new Button((Composite)this.grpNormalityTest, 32);
        this.btnLilliefors.setLayoutData((Object)new GridData(16384, 4, true, true, 1, 1));
        this.btnLilliefors.setText("Lilliefors (Kolmogorov-Smirnov)");
        this.btnLilliefors.setData((Object)"ks");
        this.btnCramervonMises = new Button((Composite)this.grpNormalityTest, 32);
        this.btnCramervonMises.setLayoutData((Object)new GridData(16384, 4, true, true, 1, 1));
        this.btnCramervonMises.setText("Cramer-Von Mises");
        this.btnCramervonMises.setData((Object)"cramer");
        this.btnAndersondarling = new Button((Composite)this.grpNormalityTest, 32);
        this.btnAndersondarling.setLayoutData((Object)new GridData(16384, 4, true, true, 1, 1));
        this.btnAndersondarling.setText("Anderson-Darling");
        this.btnAndersondarling.setData((Object)"anderson");
        this.grpSave = new Group(this.cmpOptions, 0);
        this.grpSave.setLayout((Layout)new GridLayout(1, false));
        GridData gd_grpSave = new GridData(4, 4, true, true, 1, 2);
        gd_grpSave.heightHint = 81;
        this.grpSave.setLayoutData((Object)gd_grpSave);
        this.grpSave.setText("Save");
        this.btnLeverageValues = new Button((Composite)this.grpSave, 32);
        this.btnLeverageValues.setLayoutData((Object)new GridData(4, 4, true, true, 1, 1));
        this.btnLeverageValues.setText("Leverage Values");
        this.btnCooksDistance = new Button((Composite)this.grpSave, 32);
        this.btnCooksDistance.setLayoutData((Object)new GridData(4, 4, true, true, 1, 1));
        this.btnCooksDistance.setText("Cook's Distance");
        Label lblNewLabel_3 = new Label(this.cmpOptions, 0);
        GridData gd_lblNewLabel_3 = new GridData(4, 4, true, false, 1, 1);
        gd_lblNewLabel_3.heightHint = 16;
        lblNewLabel_3.setLayoutData((Object)gd_lblNewLabel_3);
        this.listManager.initializeNumericList(this.lstAvailableData, this.filePath);
        return container;
    }

    protected void buttonPressed(int buttonID) {
        if (buttonID == 0) {
            this.okPressed();
        } else if (buttonID == 8) {
            this.listManager.setCheckBoxesToBoolean(new Composite[]{this.grpDisplay, this.grpSave, this.grpNormalityTest, this.grpTestForHeteroskedasticity}, false);
            this.btnAddDependentVariable.setText("Add");
            this.btnAddDependentVariable.setEnabled(false);
            this.btnAddIndependentVariable.setText("Add");
            this.btnAddIndependentVariable.setEnabled(false);
            this.btnIncludeConstantEquation.setSelection(true);
            this.txtConfidenceInterval.setSelection(95);
            this.lstAvailableData.removeAll();
            this.lstDependentVariables.removeAll();
            this.lstIndependentVariables.removeAll();
            this.listManager.initializeNumericList(this.lstAvailableData, this.filePath);
            this.txtConfidenceInterval.setEnabled(false);
        } else {
            this.close();
        }
    }

    protected void okPressed() {
        this.btnOk.setEnabled(false);
        String outputFolder = String.valueOf(StarAnalysisUtilities.createOutputFolder(this.filePath, "LinearRegression")) + File.separator;
        OperationProgressDialog rInfo = new OperationProgressDialog(this.getShell(), "Performing Analysis");
        rInfo.open();
        ProjectExplorerView.rJavaManager.getSTARAnalysisManager().doLinearReg(this.filePath.replace(File.separator, "/"), outputFolder.replace(File.separator, "/"), this.lstDependentVariables.getItems(), this.lstIndependentVariables.getItems(), this.btnIncludeConstantEquation.getSelection(), this.btnCoefficientInterval.getSelection(), (double)this.txtConfidenceInterval.getSelection(), this.btnCovarienceMatrix.getSelection(), this.btnVarianceInflationFactors.getSelection(), this.btnDurbinwatson.getSelection(), this.listManager.getCheckBoxesValue(this.btnBreuschpagar, this.btnGoldfeldquandt), this.listManager.getCheckBoxesValue((Composite)this.grpNormalityTest), this.btnCooksDistance.getSelection(), this.btnLeverageValues.getSelection());
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
        return new Point(524, 456);
    }
}

