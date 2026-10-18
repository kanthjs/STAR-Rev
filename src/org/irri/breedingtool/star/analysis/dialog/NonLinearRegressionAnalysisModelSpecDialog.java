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
 *  org.eclipse.swt.widgets.Composite
 *  org.eclipse.swt.widgets.Control
 *  org.eclipse.swt.widgets.Label
 *  org.eclipse.swt.widgets.Layout
 *  org.eclipse.swt.widgets.List
 *  org.eclipse.swt.widgets.Shell
 *  org.eclipse.swt.widgets.Text
 */
package org.irri.breedingtool.star.analysis.dialog;

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
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.ResourceManager;
import org.eclipse.wb.swt.SWTResourceManager;
import org.irri.breedingtool.application.handler.PartStackHandler;
import org.irri.breedingtool.manager.impl.DataManipulationManager;
import org.irri.breedingtool.star.analysis.dialog.NonLinearRegressionAnalysisParameters;
import org.irri.breedingtool.utility.DialogFormUtility;

public class NonLinearRegressionAnalysisModelSpecDialog
extends Dialog {
    private Button btnOk;
    private String filePath = PartStackHandler.getActiveElementID();
    private DialogFormUtility listManager = new DialogFormUtility();
    private DataManipulationManager dataManipulationManager = new DataManipulationManager();
    private ArrayList<String> tableData = new ArrayList();
    private Composite composite;
    private Label lblIndependentVariable;
    private Button btnParameter;
    private List lstParameter;
    private Label lblRighthandExpression;
    private Label lblOperators;
    private Composite composite_1;
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private Button btn5;
    private Button btn6;
    private Button btn7;
    private Button btn8;
    private Button btn9;
    private Button btn0;
    private Button btnDot;
    private Button btnAddition;
    private Button btnMinus;
    private Button btnMultiply;
    private Button btnDivide;
    private Label lblFunction;
    private List cmboFunctions;
    private Button btnDel;
    private Button btnCaret;
    private Button btnIndepVar;
    private Button btnParameters;
    private Button btnFunctions;
    private String returnVal;
    private Text txtRightHand;
    public ArrayList<NonLinearRegressionAnalysisParameters.ParameterModel> parameterList = new ArrayList();
    private ArrayList<String> arrExpression = new ArrayList();
    private String indepVar;
    private String[] paraNames;
    private String[] Functions = new String[]{"abs", "acos", "asin", "atan", "cos", "exp", "log", "log10", "sin", "sqrt", "tan"};
    private List lstIndependentVar;
    private Button btnOpen;
    private Button btnClose;
    private String regExpression;

    public String getReturnVal() {
        return this.returnVal;
    }

    public void setReturnVal(String returnVal) {
        this.returnVal = returnVal;
    }

    public NonLinearRegressionAnalysisModelSpecDialog(Shell parentShell, String indepVar, ArrayList<NonLinearRegressionAnalysisParameters.ParameterModel> parameterList, String regExpression) {
        super(parentShell);
        this.setShellStyle(133360);
        this.indepVar = indepVar;
        this.parameterList = parameterList;
        this.regExpression = regExpression;
    }

    protected void configureShell(Shell shell) {
        super.configureShell(shell);
        shell.setText("Nonlinear Regression: Expression Building");
    }

    protected Control createDialogArea(Composite parent) {
        Composite container = (Composite)super.createDialogArea(parent);
        FillLayout fl_container = new FillLayout(256);
        fl_container.marginHeight = 8;
        fl_container.marginWidth = 8;
        container.setLayout((Layout)fl_container);
        this.composite = new Composite(container, 0);
        this.composite.setLayout((Layout)new GridLayout(5, false));
        this.lblRighthandExpression = new Label(this.composite, 0);
        this.lblRighthandExpression.setLayoutData((Object)new GridData(16384, 0x1000000, true, false, 2, 1));
        this.lblRighthandExpression.setText("Right-hand Expression:");
        new Label(this.composite, 0);
        new Label(this.composite, 0);
        new Label(this.composite, 0);
        this.txtRightHand = new Text(this.composite, 2824);
        this.txtRightHand.setEditable(false);
        this.txtRightHand.setBackground(SWTResourceManager.getColor(1));
        GridData gd_txtRightHand = new GridData(4, 0x1000000, true, true, 5, 1);
        gd_txtRightHand.heightHint = 80;
        this.txtRightHand.setLayoutData((Object)gd_txtRightHand);
        this.lblIndependentVariable = new Label(this.composite, 0);
        this.lblIndependentVariable.setLayoutData((Object)new GridData(16384, 1024, true, false, 1, 1));
        this.lblIndependentVariable.setText("Independent Variable:");
        this.btnIndepVar = new Button(this.composite, 0);
        this.btnIndepVar.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (NonLinearRegressionAnalysisModelSpecDialog.this.lstIndependentVar.getSelectionCount() > 0) {
                    NonLinearRegressionAnalysisModelSpecDialog.this.arrExpression.add(NonLinearRegressionAnalysisModelSpecDialog.this.lstIndependentVar.getItem(0));
                    NonLinearRegressionAnalysisModelSpecDialog.this.rebuildExpressionBox();
                }
            }
        });
        this.btnIndepVar.setImage(ResourceManager.getPluginImage("Star", "icons/play1.PNG"));
        this.btnIndepVar.setFont(SWTResourceManager.getFont("Wingdings 3", 9, 0));
        new Label(this.composite, 0);
        this.lblOperators = new Label(this.composite, 0);
        this.lblOperators.setLayoutData((Object)new GridData(16384, 1024, true, false, 1, 1));
        this.lblOperators.setText("Operators/Constants:");
        new Label(this.composite, 0);
        this.lstIndependentVar = new List(this.composite, 2048);
        GridData gd_lstIndependentVar = new GridData(4, 4, false, false, 2, 1);
        gd_lstIndependentVar.heightHint = 15;
        this.lstIndependentVar.setLayoutData((Object)gd_lstIndependentVar);
        new Label(this.composite, 0);
        this.composite_1 = new Composite(this.composite, 2048);
        GridData gd_composite_1 = new GridData(4, 4, true, true, 2, 4);
        gd_composite_1.widthHint = 158;
        gd_composite_1.heightHint = 112;
        this.composite_1.setLayoutData((Object)gd_composite_1);
        this.composite_1.setLayout((Layout)new GridLayout(5, false));
        this.btn1 = new Button(this.composite_1, 0);
        this.btn1.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (NonLinearRegressionAnalysisModelSpecDialog.this.txtRightHand.getSelection() != null) {
                    NonLinearRegressionAnalysisModelSpecDialog.this.arrExpression.add("1");
                    NonLinearRegressionAnalysisModelSpecDialog.this.rebuildExpressionBox();
                }
            }
        });
        GridData gd_btn1 = new GridData(16384, 0x1000000, true, true, 1, 1);
        gd_btn1.widthHint = 25;
        gd_btn1.heightHint = 25;
        this.btn1.setLayoutData((Object)gd_btn1);
        this.btn1.setText("1");
        this.btn2 = new Button(this.composite_1, 0);
        this.btn2.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (NonLinearRegressionAnalysisModelSpecDialog.this.txtRightHand.getSelection() != null) {
                    NonLinearRegressionAnalysisModelSpecDialog.this.arrExpression.add("2");
                    NonLinearRegressionAnalysisModelSpecDialog.this.rebuildExpressionBox();
                }
            }
        });
        GridData gd_btn2 = new GridData(16384, 0x1000000, true, true, 1, 1);
        gd_btn2.widthHint = 25;
        gd_btn2.heightHint = 25;
        this.btn2.setLayoutData((Object)gd_btn2);
        this.btn2.setText("2");
        this.btn3 = new Button(this.composite_1, 0);
        this.btn3.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (NonLinearRegressionAnalysisModelSpecDialog.this.txtRightHand.getSelection() != null) {
                    NonLinearRegressionAnalysisModelSpecDialog.this.arrExpression.add("3");
                    NonLinearRegressionAnalysisModelSpecDialog.this.rebuildExpressionBox();
                }
            }
        });
        GridData gd_btn3 = new GridData(16384, 0x1000000, true, true, 1, 1);
        gd_btn3.widthHint = 25;
        gd_btn3.heightHint = 25;
        this.btn3.setLayoutData((Object)gd_btn3);
        this.btn3.setText("3");
        this.btn4 = new Button(this.composite_1, 0);
        this.btn4.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (NonLinearRegressionAnalysisModelSpecDialog.this.txtRightHand.getSelection() != null) {
                    NonLinearRegressionAnalysisModelSpecDialog.this.arrExpression.add("4");
                    NonLinearRegressionAnalysisModelSpecDialog.this.rebuildExpressionBox();
                }
            }
        });
        GridData gd_btn4 = new GridData(16384, 0x1000000, true, true, 1, 1);
        gd_btn4.widthHint = 25;
        gd_btn4.heightHint = 25;
        this.btn4.setLayoutData((Object)gd_btn4);
        this.btn4.setText("4");
        this.btnAddition = new Button(this.composite_1, 0);
        this.btnAddition.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (NonLinearRegressionAnalysisModelSpecDialog.this.txtRightHand.getSelection() != null) {
                    NonLinearRegressionAnalysisModelSpecDialog.this.arrExpression.add("+");
                    NonLinearRegressionAnalysisModelSpecDialog.this.rebuildExpressionBox();
                }
            }
        });
        GridData gd_btnAddition = new GridData(16384, 0x1000000, true, true, 1, 1);
        gd_btnAddition.heightHint = 25;
        gd_btnAddition.widthHint = 25;
        this.btnAddition.setLayoutData((Object)gd_btnAddition);
        this.btnAddition.setText("+");
        this.btn5 = new Button(this.composite_1, 0);
        this.btn5.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (NonLinearRegressionAnalysisModelSpecDialog.this.txtRightHand.getSelection() != null) {
                    NonLinearRegressionAnalysisModelSpecDialog.this.arrExpression.add("5");
                    NonLinearRegressionAnalysisModelSpecDialog.this.rebuildExpressionBox();
                }
            }
        });
        GridData gd_btn5 = new GridData(16384, 0x1000000, true, true, 1, 1);
        gd_btn5.widthHint = 25;
        gd_btn5.heightHint = 25;
        this.btn5.setLayoutData((Object)gd_btn5);
        this.btn5.setText("5");
        this.btn6 = new Button(this.composite_1, 0);
        this.btn6.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (NonLinearRegressionAnalysisModelSpecDialog.this.txtRightHand.getSelection() != null) {
                    NonLinearRegressionAnalysisModelSpecDialog.this.arrExpression.add("6");
                    NonLinearRegressionAnalysisModelSpecDialog.this.rebuildExpressionBox();
                }
            }
        });
        GridData gd_btn6 = new GridData(16384, 0x1000000, true, true, 1, 1);
        gd_btn6.heightHint = 25;
        gd_btn6.widthHint = 25;
        this.btn6.setLayoutData((Object)gd_btn6);
        this.btn6.setText("6");
        this.btn7 = new Button(this.composite_1, 0);
        this.btn7.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (NonLinearRegressionAnalysisModelSpecDialog.this.txtRightHand.getSelection() != null) {
                    NonLinearRegressionAnalysisModelSpecDialog.this.arrExpression.add("7");
                    NonLinearRegressionAnalysisModelSpecDialog.this.rebuildExpressionBox();
                }
            }
        });
        GridData gd_btn7 = new GridData(16384, 0x1000000, true, true, 1, 1);
        gd_btn7.heightHint = 25;
        gd_btn7.widthHint = 25;
        this.btn7.setLayoutData((Object)gd_btn7);
        this.btn7.setText("7");
        this.btn8 = new Button(this.composite_1, 0);
        this.btn8.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (NonLinearRegressionAnalysisModelSpecDialog.this.txtRightHand.getSelection() != null) {
                    NonLinearRegressionAnalysisModelSpecDialog.this.arrExpression.add("8");
                    NonLinearRegressionAnalysisModelSpecDialog.this.rebuildExpressionBox();
                }
            }
        });
        GridData gd_btn8 = new GridData(16384, 0x1000000, true, true, 1, 1);
        gd_btn8.heightHint = 25;
        gd_btn8.widthHint = 25;
        this.btn8.setLayoutData((Object)gd_btn8);
        this.btn8.setText("8");
        this.btnMultiply = new Button(this.composite_1, 0);
        this.btnMultiply.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (NonLinearRegressionAnalysisModelSpecDialog.this.txtRightHand.getSelection() != null) {
                    NonLinearRegressionAnalysisModelSpecDialog.this.arrExpression.add("*");
                    NonLinearRegressionAnalysisModelSpecDialog.this.rebuildExpressionBox();
                }
            }
        });
        GridData gd_btnMultiply = new GridData(16384, 0x1000000, true, true, 1, 1);
        gd_btnMultiply.heightHint = 25;
        gd_btnMultiply.widthHint = 25;
        this.btnMultiply.setLayoutData((Object)gd_btnMultiply);
        this.btnMultiply.setText("*");
        this.btn9 = new Button(this.composite_1, 0);
        this.btn9.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (NonLinearRegressionAnalysisModelSpecDialog.this.txtRightHand.getSelection() != null) {
                    NonLinearRegressionAnalysisModelSpecDialog.this.arrExpression.add("9");
                    NonLinearRegressionAnalysisModelSpecDialog.this.rebuildExpressionBox();
                }
            }
        });
        GridData gd_btn9 = new GridData(16384, 0x1000000, true, true, 1, 1);
        gd_btn9.heightHint = 25;
        gd_btn9.widthHint = 25;
        this.btn9.setLayoutData((Object)gd_btn9);
        this.btn9.setText("9");
        this.btn0 = new Button(this.composite_1, 0);
        this.btn0.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (NonLinearRegressionAnalysisModelSpecDialog.this.txtRightHand.getSelection() != null) {
                    NonLinearRegressionAnalysisModelSpecDialog.this.arrExpression.add("0");
                    NonLinearRegressionAnalysisModelSpecDialog.this.rebuildExpressionBox();
                }
            }
        });
        GridData gd_btn0 = new GridData(16384, 128, true, true, 1, 1);
        gd_btn0.heightHint = 25;
        gd_btn0.widthHint = 25;
        this.btn0.setLayoutData((Object)gd_btn0);
        this.btn0.setText("0");
        this.btnDot = new Button(this.composite_1, 0);
        this.btnDot.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (NonLinearRegressionAnalysisModelSpecDialog.this.txtRightHand.getSelection() != null) {
                    NonLinearRegressionAnalysisModelSpecDialog.this.arrExpression.add(".");
                    NonLinearRegressionAnalysisModelSpecDialog.this.rebuildExpressionBox();
                }
            }
        });
        GridData gd_btnDot = new GridData(16384, 0x1000000, true, true, 1, 1);
        gd_btnDot.heightHint = 25;
        gd_btnDot.widthHint = 25;
        this.btnDot.setLayoutData((Object)gd_btnDot);
        this.btnDot.setText(".");
        this.btnCaret = new Button(this.composite_1, 0);
        this.btnCaret.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (NonLinearRegressionAnalysisModelSpecDialog.this.txtRightHand.getSelection() != null) {
                    NonLinearRegressionAnalysisModelSpecDialog.this.arrExpression.add("^");
                    NonLinearRegressionAnalysisModelSpecDialog.this.rebuildExpressionBox();
                }
            }
        });
        GridData gd_btnCaret = new GridData(16384, 0x1000000, true, true, 1, 1);
        gd_btnCaret.heightHint = 25;
        gd_btnCaret.widthHint = 25;
        this.btnCaret.setLayoutData((Object)gd_btnCaret);
        this.btnCaret.setText("^");
        this.btnMinus = new Button(this.composite_1, 0);
        this.btnMinus.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (NonLinearRegressionAnalysisModelSpecDialog.this.txtRightHand.getSelection() != null) {
                    NonLinearRegressionAnalysisModelSpecDialog.this.arrExpression.add("-");
                    NonLinearRegressionAnalysisModelSpecDialog.this.rebuildExpressionBox();
                }
            }
        });
        GridData gd_btnMinus = new GridData(16384, 0x1000000, true, true, 1, 1);
        gd_btnMinus.widthHint = 25;
        this.btnMinus.setLayoutData((Object)gd_btnMinus);
        this.btnMinus.setText("-");
        this.btnOpen = new Button(this.composite_1, 0);
        this.btnOpen.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                NonLinearRegressionAnalysisModelSpecDialog.this.arrExpression.add("(");
                NonLinearRegressionAnalysisModelSpecDialog.this.rebuildExpressionBox();
            }
        });
        GridData gd_btnOpen = new GridData(16384, 0x1000000, true, true, 1, 1);
        gd_btnOpen.heightHint = 25;
        gd_btnOpen.widthHint = 25;
        this.btnOpen.setLayoutData((Object)gd_btnOpen);
        this.btnOpen.setText("(");
        this.btnClose = new Button(this.composite_1, 0);
        this.btnClose.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                NonLinearRegressionAnalysisModelSpecDialog.this.arrExpression.add(")");
                NonLinearRegressionAnalysisModelSpecDialog.this.rebuildExpressionBox();
            }
        });
        GridData gd_btnClose = new GridData(16384, 0x1000000, true, true, 1, 1);
        gd_btnClose.heightHint = 25;
        gd_btnClose.widthHint = 25;
        this.btnClose.setLayoutData((Object)gd_btnClose);
        this.btnClose.setText(")");
        this.btnDivide = new Button(this.composite_1, 0);
        this.btnDivide.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (NonLinearRegressionAnalysisModelSpecDialog.this.txtRightHand.getSelection() != null) {
                    NonLinearRegressionAnalysisModelSpecDialog.this.arrExpression.add("/");
                    NonLinearRegressionAnalysisModelSpecDialog.this.rebuildExpressionBox();
                }
            }
        });
        GridData gd_btnDivide = new GridData(16384, 0x1000000, true, true, 1, 1);
        gd_btnDivide.widthHint = 25;
        this.btnDivide.setLayoutData((Object)gd_btnDivide);
        this.btnDivide.setText("/");
        this.btnDel = new Button(this.composite_1, 0);
        this.btnDel.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (NonLinearRegressionAnalysisModelSpecDialog.this.arrExpression.isEmpty()) {
                    return;
                }
                NonLinearRegressionAnalysisModelSpecDialog.this.arrExpression.remove(NonLinearRegressionAnalysisModelSpecDialog.this.arrExpression.size() - 1);
                NonLinearRegressionAnalysisModelSpecDialog.this.rebuildExpressionBox();
            }
        });
        GridData gd_btnDel = new GridData(4, 0x1000000, true, true, 2, 1);
        gd_btnDel.widthHint = 27;
        this.btnDel.setLayoutData((Object)gd_btnDel);
        this.btnDel.setText("Del");
        new Label(this.composite, 0);
        new Label(this.composite, 0);
        new Label(this.composite, 0);
        new Label(this.composite, 0);
        new Label(this.composite, 0);
        new Label(this.composite, 0);
        this.btnParameter = new Button(this.composite, 0);
        this.btnParameter.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                NonLinearRegressionAnalysisParameters dlgParam = new NonLinearRegressionAnalysisParameters(NonLinearRegressionAnalysisModelSpecDialog.this.getShell(), NonLinearRegressionAnalysisModelSpecDialog.this.parameterList);
                if (dlgParam.open() == 0) {
                    NonLinearRegressionAnalysisModelSpecDialog.this.parameterList = dlgParam.getParameterList();
                    System.out.println("inn");
                    NonLinearRegressionAnalysisModelSpecDialog.this.lstParameter.removeAll();
                    for (NonLinearRegressionAnalysisParameters.ParameterModel paramModel : NonLinearRegressionAnalysisModelSpecDialog.this.parameterList) {
                        NonLinearRegressionAnalysisModelSpecDialog.this.lstParameter.add(paramModel.name);
                        System.out.println("i: ");
                    }
                }
            }
        });
        this.btnParameter.setLayoutData((Object)new GridData(16384, 1024, true, false, 1, 1));
        this.btnParameter.setText("Parameters:");
        this.btnParameters = new Button(this.composite, 0);
        this.btnParameters.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (NonLinearRegressionAnalysisModelSpecDialog.this.lstParameter.getSelectionCount() > 0) {
                    NonLinearRegressionAnalysisModelSpecDialog.this.arrExpression.add(NonLinearRegressionAnalysisModelSpecDialog.this.lstParameter.getItem(NonLinearRegressionAnalysisModelSpecDialog.this.lstParameter.getSelectionIndex()));
                    NonLinearRegressionAnalysisModelSpecDialog.this.rebuildExpressionBox();
                }
            }
        });
        this.btnParameters.setLayoutData((Object)new GridData(16384, 1024, false, false, 1, 1));
        this.btnParameters.setImage(ResourceManager.getPluginImage("Star", "icons/play1.PNG"));
        this.btnParameters.setFont(SWTResourceManager.getFont("Wingdings 3", 9, 0));
        new Label(this.composite, 0);
        this.lstParameter = new List(this.composite, 67584);
        GridData gd_lstParameter = new GridData(4, 4, true, false, 2, 2);
        gd_lstParameter.heightHint = 96;
        gd_lstParameter.widthHint = 199;
        this.lstParameter.setLayoutData((Object)gd_lstParameter);
        new Label(this.composite, 0);
        this.lblFunction = new Label(this.composite, 0);
        this.lblFunction.setLayoutData((Object)new GridData(16384, 1024, true, false, 1, 1));
        this.lblFunction.setText("Functions:");
        this.btnFunctions = new Button(this.composite, 0);
        this.btnFunctions.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (NonLinearRegressionAnalysisModelSpecDialog.this.cmboFunctions.getSelection() != null) {
                    NonLinearRegressionAnalysisModelSpecDialog.this.arrExpression.add(NonLinearRegressionAnalysisModelSpecDialog.this.Functions[NonLinearRegressionAnalysisModelSpecDialog.this.cmboFunctions.getSelectionIndex()]);
                    NonLinearRegressionAnalysisModelSpecDialog.this.rebuildExpressionBox();
                }
            }
        });
        this.btnFunctions.setImage(ResourceManager.getPluginImage("Star", "icons/play1.PNG"));
        this.btnFunctions.setFont(SWTResourceManager.getFont("Wingdings 3", 9, 0));
        new Label(this.composite, 0);
        this.cmboFunctions = new List(this.composite, 2560);
        this.cmboFunctions.setItems(new String[]{"abs", "acos", "asin", "atan", "cos", "exp", "log", "log10", "sin", "sqrt", "tan"});
        GridData gd_cmboFunctions = new GridData(4, 4, true, true, 2, 1);
        gd_cmboFunctions.heightHint = 48;
        gd_cmboFunctions.widthHint = 166;
        this.cmboFunctions.setLayoutData((Object)gd_cmboFunctions);
        this.cmboFunctions.select(0);
        if (this.indepVar != null) {
            this.lstIndependentVar.add(this.indepVar);
        }
        this.txtRightHand.setText(this.regExpression);
        for (NonLinearRegressionAnalysisParameters.ParameterModel param : this.parameterList) {
            this.lstParameter.add(param.name);
        }
        return container;
    }

    protected void buttonPressed(int buttonID) {
        if (buttonID == 0) {
            this.okPressed();
        } else if (buttonID == 8) {
            this.txtRightHand.setText("");
        } else {
            this.close();
        }
    }

    protected void okPressed() {
        this.btnOk.setEnabled(false);
        System.out.println("okpress");
        if (this.txtRightHand.getText().equals("")) {
            MessageDialog.openError((Shell)this.getParentShell(), (String)"Error", (String)"Please provide the Right-hand Expression");
            return;
        }
        this.setReturnVal(this.txtRightHand.getText());
        this.close();
    }

    private void rebuildExpressionBox() {
        String finalExp = "";
        for (String val : this.arrExpression) {
            finalExp = String.valueOf(finalExp) + val;
        }
        this.txtRightHand.setText(finalExp);
    }

    protected void createButtonsForButtonBar(Composite parent) {
        this.createButton(parent, 8, "Reset", false);
        this.btnOk = this.createButton(parent, 0, IDialogConstants.OK_LABEL, true);
        this.createButton(parent, 1, IDialogConstants.CANCEL_LABEL, false);
    }

    protected Point getInitialSize() {
        return new Point(404, 476);
    }
}

