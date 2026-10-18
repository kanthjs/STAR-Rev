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
 *  org.eclipse.swt.widgets.Text
 */
package org.irri.breedingtool.star.analysis.dialog;

import java.io.File;
import java.util.ArrayList;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
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
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;
import org.irri.breedingtool.application.handler.PartStackHandler;
import org.irri.breedingtool.datamanipulation.dialog.OperationProgressDialog;
import org.irri.breedingtool.projectexplorer.view.ProjectExplorerView;
import org.irri.breedingtool.star.analysis.dialog.NonLinearRegressionAnalysisModelSpecDialog;
import org.irri.breedingtool.star.analysis.dialog.NonLinearRegressionAnalysisParameters;
import org.irri.breedingtool.utility.DialogFormUtility;
import org.irri.breedingtool.utility.GeneralUtility;
import org.irri.breedingtool.utility.StarAnalysisUtilities;

public class NonLinearRegressionDialog
extends Dialog {
    private Button btnOk;
    private Button btnAddIndependentVariable;
    private Button btnAddDependentVariable;
    private List lstAvailableData;
    private List lstDependentVariables;
    private String filePath = PartStackHandler.getActiveElementID();
    private DialogFormUtility listManager = new DialogFormUtility();
    private TabItem tbtmOptions_1;
    private Composite composite_1;
    private Button btnCoefficientInterval;
    private Button btnCovarianceMatrix;
    private Button btnShapirowilk;
    private Group grpDisplay;
    private Group grpRegressionCoefficients;
    private Spinner txtConfidenceInterval;
    private Label lblModel;
    private Combo cmboModel;
    private Text txtExpression;
    private Button btnDescriptiveStatistics;
    private Button btnSpecifyExpression;
    private String startValues;
    private Label lblNewLabel;
    private Text txtIndependentVariables;
    private String modelExp = null;
    private String[] modelItems = new String[]{"quadratic", "cubic", "logis", "fpl", "gompertz", "weibull", "custom"};
    private ArrayList<NonLinearRegressionAnalysisParameters.ParameterModel> lstParameter = new ArrayList();

    public NonLinearRegressionDialog(Shell parentShell) {
        super(parentShell);
        this.setShellStyle(2288);
    }

    protected void configureShell(Shell shell) {
        super.configureShell(shell);
        shell.setText("Nonlinear Regression Analysis : " + new File(this.filePath).getName());
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
        composite.setLayout((Layout)new GridLayout(5, false));
        Label lblAvailableData = new Label(composite, 0);
        lblAvailableData.setLayoutData((Object)new GridData(16384, 0x1000000, false, false, 2, 1));
        lblAvailableData.setText("Numeric Variables:");
        new Label(composite, 0);
        Label lblDependentVariables = new Label(composite, 0);
        lblDependentVariables.setLayoutData((Object)new GridData(16384, 0x1000000, true, false, 1, 1));
        lblDependentVariables.setText("Dependent Variable(s):");
        this.lblNewLabel = new Label(composite, 0);
        GridData gd_lblNewLabel = new GridData(16384, 0x1000000, false, false, 1, 1);
        gd_lblNewLabel.widthHint = 25;
        this.lblNewLabel.setLayoutData((Object)gd_lblNewLabel);
        this.lstAvailableData = new List(composite, 2818);
        this.lstAvailableData.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
            }
        });
        this.lstAvailableData.setLayoutData((Object)new GridData(4, 4, false, true, 2, 3));
        this.btnAddDependentVariable = new Button(composite, 0);
        GridData gd_btnAddDependentVariable = new GridData(0x1000000, 0x1000000, true, true, 1, 1);
        gd_btnAddDependentVariable.widthHint = 90;
        this.btnAddDependentVariable.setLayoutData((Object)gd_btnAddDependentVariable);
        this.btnAddDependentVariable.setText("Add");
        this.lstDependentVariables = new List(composite, 2818);
        this.lstDependentVariables.setLayoutData((Object)new GridData(4, 4, true, true, 2, 1));
        new Label(composite, 0);
        Label lblIndependentVariables = new Label(composite, 0);
        lblIndependentVariables.setLayoutData((Object)new GridData(16384, 0x1000000, true, false, 1, 1));
        lblIndependentVariables.setText("Independent Variable:");
        new Label(composite, 0);
        this.btnAddIndependentVariable = new Button(composite, 0);
        GridData gd_btnAddIndependentVariable = new GridData(0x1000000, 128, true, true, 1, 1);
        gd_btnAddIndependentVariable.widthHint = 90;
        this.btnAddIndependentVariable.setLayoutData((Object)gd_btnAddIndependentVariable);
        this.btnAddIndependentVariable.setText("Add");
        this.txtIndependentVariables = new Text(composite, 2056);
        this.txtIndependentVariables.setBackground(SWTResourceManager.getColor(1));
        this.txtIndependentVariables.setLayoutData((Object)new GridData(4, 128, true, false, 2, 1));
        this.lblModel = new Label(composite, 0);
        this.lblModel.setText("Model:");
        this.cmboModel = new Combo(composite, 8);
        this.cmboModel.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (NonLinearRegressionDialog.this.cmboModel.getSelectionIndex() == 6) {
                    NonLinearRegressionDialog.this.btnSpecifyExpression.setEnabled(true);
                } else {
                    NonLinearRegressionDialog.this.btnSpecifyExpression.setEnabled(false);
                }
            }
        });
        this.cmboModel.setItems(new String[]{"Quadratic Polynomial", "Cubic Polynomial", "Logistic", "Four-parameter Logistic", "Gompertz Growth", "Weibull Growth Curve", "[Custom]"});
        this.cmboModel.setLayoutData((Object)new GridData(4, 0x1000000, true, false, 1, 1));
        this.cmboModel.select(0);
        new Label(composite, 0);
        new Label(composite, 0);
        new Label(composite, 0);
        this.btnSpecifyExpression = new Button(composite, 0);
        this.btnSpecifyExpression.setEnabled(false);
        this.btnSpecifyExpression.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (NonLinearRegressionDialog.this.txtIndependentVariables.getText().equals("")) {
                    MessageDialog.openError((Shell)NonLinearRegressionDialog.this.getParentShell(), (String)"Error", (String)"Please add an Independent Variable.");
                    return;
                }
                String indepVar = NonLinearRegressionDialog.this.txtIndependentVariables.getText();
                NonLinearRegressionAnalysisModelSpecDialog dlgNonLiRegAnalysis = new NonLinearRegressionAnalysisModelSpecDialog(NonLinearRegressionDialog.this.getShell(), indepVar, NonLinearRegressionDialog.this.lstParameter, NonLinearRegressionDialog.this.txtExpression.getText());
                if (dlgNonLiRegAnalysis.open() == 0) {
                    System.out.println(dlgNonLiRegAnalysis.getReturnCode());
                    if (dlgNonLiRegAnalysis.getReturnVal() != null) {
                        NonLinearRegressionDialog.this.txtExpression.setText(dlgNonLiRegAnalysis.getReturnVal());
                        NonLinearRegressionDialog.this.lstParameter = dlgNonLiRegAnalysis.parameterList;
                    }
                }
            }
        });
        this.btnSpecifyExpression.setLayoutData((Object)new GridData(4, 0x1000000, false, true, 2, 1));
        this.btnSpecifyExpression.setText("Specify expression:");
        this.txtExpression = new Text(composite, 2048);
        this.txtExpression.setBackground(SWTResourceManager.getColor(1));
        this.txtExpression.setEditable(false);
        this.txtExpression.setLayoutData((Object)new GridData(4, 0x1000000, false, true, 3, 1));
        this.tbtmOptions_1 = new TabItem(tabFolder, 0);
        this.tbtmOptions_1.setText("Options");
        this.composite_1 = new Composite((Composite)tabFolder, 0);
        this.tbtmOptions_1.setControl((Control)this.composite_1);
        this.composite_1.setLayout((Layout)new GridLayout(1, false));
        this.grpDisplay = new Group(this.composite_1, 0);
        this.grpDisplay.setLayoutData((Object)new GridData(4, 4, true, false, 1, 1));
        this.grpDisplay.setText("Display");
        this.grpDisplay.setLayout((Layout)new GridLayout(1, false));
        this.btnDescriptiveStatistics = new Button((Composite)this.grpDisplay, 32);
        this.btnDescriptiveStatistics.setSelection(true);
        this.btnDescriptiveStatistics.setText("Descriptive Statistics");
        this.btnShapirowilk = new Button((Composite)this.grpDisplay, 32);
        this.btnShapirowilk.setText("Shapiro-Wilk Test for Normality");
        this.grpRegressionCoefficients = new Group(this.composite_1, 0);
        this.grpRegressionCoefficients.setLayoutData((Object)new GridData(4, 4, true, false, 1, 1));
        this.grpRegressionCoefficients.setText("Regression Coefficients");
        this.grpRegressionCoefficients.setLayout((Layout)new GridLayout(3, false));
        this.btnCoefficientInterval = new Button((Composite)this.grpRegressionCoefficients, 32);
        this.btnCoefficientInterval.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                NonLinearRegressionDialog.this.txtConfidenceInterval.setEnabled(NonLinearRegressionDialog.this.btnCoefficientInterval.getSelection());
            }
        });
        this.btnCoefficientInterval.setLayoutData((Object)new GridData(16384, 0x1000000, false, true, 1, 1));
        this.btnCoefficientInterval.setText("Coefficient Interval");
        this.txtConfidenceInterval = new Spinner((Composite)this.grpRegressionCoefficients, 2048);
        this.txtConfidenceInterval.setLayoutData((Object)new GridData(4, 0x1000000, false, false, 1, 1));
        this.txtConfidenceInterval.setMaximum(99);
        this.txtConfidenceInterval.setMinimum(95);
        this.txtConfidenceInterval.setEnabled(false);
        Label label_1 = new Label((Composite)this.grpRegressionCoefficients, 0);
        label_1.setLayoutData((Object)new GridData(4, 0x1000000, true, false, 1, 1));
        label_1.setText("%");
        this.btnCovarianceMatrix = new Button((Composite)this.grpRegressionCoefficients, 32);
        this.btnCovarianceMatrix.setLayoutData((Object)new GridData(4, 4, false, true, 1, 1));
        this.btnCovarianceMatrix.setText("Covariance Matrix");
        new Label((Composite)this.grpRegressionCoefficients, 0);
        new Label((Composite)this.grpRegressionCoefficients, 0);
        this.initializeContent();
        return container;
    }

    void initializeContent() {
        this.listManager.initializeNumericList(this.lstAvailableData, this.filePath);
        this.listManager.initializeSingleButtonList(this.lstAvailableData, this.lstDependentVariables, this.btnAddDependentVariable);
        this.listManager.initializeSingleSelectionList(this.lstAvailableData, this.txtIndependentVariables, this.btnAddIndependentVariable);
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
        this.lstAvailableData.removeAll();
        this.lstDependentVariables.removeAll();
        this.txtIndependentVariables.setText("");
        this.txtExpression.setText("");
        this.cmboModel.setText("Quadratic Polynomial");
        this.btnSpecifyExpression.setEnabled(false);
        this.btnAddDependentVariable.setText("Add");
        this.btnAddIndependentVariable.setText("Add");
        this.btnAddDependentVariable.setEnabled(false);
        this.btnAddIndependentVariable.setEnabled(false);
        this.btnDescriptiveStatistics.setSelection(true);
        this.btnShapirowilk.setSelection(false);
        this.btnCoefficientInterval.setSelection(false);
        this.btnCovarianceMatrix.setSelection(false);
        this.txtConfidenceInterval.setEnabled(false);
        this.txtConfidenceInterval.setSelection(95);
        this.lstParameter.clear();
        this.listManager.initializeNumericList(this.lstAvailableData, this.filePath);
    }

    protected void okPressed() {
        String outputFolder = String.valueOf(StarAnalysisUtilities.createOutputFolder(this.filePath, "NonLinearRegression")) + File.separator;
        OperationProgressDialog rInfo = new OperationProgressDialog(this.getShell(), "Performing Analysis");
        rInfo.open();
        this.btnOk.setEnabled(false);
        String dataFileName = this.filePath.replace(File.separator, "/");
        String outputPath = outputFolder;
        String[] depvar = this.lstDependentVariables.getItems();
        String indepvar = this.txtIndependentVariables.getText();
        boolean statistics = true;
        String model = this.modelItems[this.cmboModel.getSelectionIndex()];
        this.modelExp = !this.txtExpression.getText().isEmpty() ? this.txtExpression.getText() : null;
        boolean covMatrix = this.btnCovarianceMatrix.getSelection();
        boolean normality = this.btnShapirowilk.getSelection();
        boolean confInterval = this.btnCoefficientInterval.getSelection();
        double confLevel = this.txtConfidenceInterval.getSelection();
        String[] arrTmpParam = new String[this.lstParameter.size()];
        int i = 0;
        while (i < this.lstParameter.size()) {
            arrTmpParam[i] = String.valueOf(this.lstParameter.get((int)i).name) + " = " + this.lstParameter.get((int)i).value;
            ++i;
        }
        if (!this.lstParameter.isEmpty()) {
            this.startValues = "list(" + GeneralUtility.combineArrayOfString(arrTmpParam, ", ") + ")";
        }
        ProjectExplorerView.rJavaManager.getSTARAnalysisManager().doNonlinearRegression(dataFileName, outputPath.replace(File.separator, "/"), depvar, indepvar, statistics, model, this.modelExp, this.startValues, covMatrix, normality, confInterval, confLevel);
        StarAnalysisUtilities.openAndRefreshFolder(outputFolder);
        this.getShell().setMinimized(true);
        this.btnOk.setEnabled(true);
        rInfo.close();
    }

    protected void createButtonsForButtonBar(Composite parent) {
        this.createButton(parent, 8, "Reset", false);
        this.btnOk = this.createButton(parent, 0, IDialogConstants.OK_LABEL, true);
        this.createButton(parent, 1, IDialogConstants.CANCEL_LABEL, false);
    }

    protected Point getInitialSize() {
        return new Point(509, 505);
    }
}

