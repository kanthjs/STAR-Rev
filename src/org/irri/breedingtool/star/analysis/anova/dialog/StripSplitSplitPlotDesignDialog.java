/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.jface.dialogs.Dialog
 *  org.eclipse.jface.dialogs.IDialogConstants
 *  org.eclipse.jface.dialogs.MessageDialog
 *  org.eclipse.swt.events.ModifyEvent
 *  org.eclipse.swt.events.ModifyListener
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
 *  org.eclipse.swt.widgets.Text
 */
package org.irri.breedingtool.star.analysis.anova.dialog;

import java.io.File;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
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
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;
import org.irri.breedingtool.application.handler.PartStackHandler;
import org.irri.breedingtool.datamanipulation.dialog.OperationProgressDialog;
import org.irri.breedingtool.projectexplorer.view.ProjectExplorerView;
import org.irri.breedingtool.star.analysis.anova.dialog.ContrastDialog;
import org.irri.breedingtool.star.analysis.anova.dialog.PostHocDialog;
import org.irri.breedingtool.utility.DialogFormUtility;
import org.irri.breedingtool.utility.GeneralUtility;
import org.irri.breedingtool.utility.StarAnalysisUtilities;

public class StripSplitSplitPlotDesignDialog
extends Dialog {
    private Button btnDisplay1;
    private Spinner txtSignificantLevel;
    private String filePath = PartStackHandler.getActiveElementID();
    private List lstNumericVariables;
    private List lstFactorVariables;
    private List lstFactors;
    private List lstResponse;
    private DialogFormUtility listManager = new DialogFormUtility();
    private Composite cmpModel;
    private Button btnDisplay2;
    private Button btnDisplay3;
    private Text txtBlock;
    private List lstHorizontal;
    private List lstSubSubPlot;
    private List lstSubSubSubPlot;
    private Button btnOk;
    private Button btnContrast;
    private Button btnPostHoc;
    private ContrastDialog dlgContrast;
    private PostHocDialog dlgPostHoc;
    private boolean isContrastReady = false;
    private String[] preHocResult = null;
    private String[] postHocVar = null;
    private String[] postHocOpts = null;
    private String outputFolderPath;
    private String design = "Strip-Split2";
    private Text txtCombinedAnalysis;
    private Button btnPerformSet;
    private Button btnPerformCombined;
    private Button btnAddCombinedAnalysis;
    private Button btnResponseVariable;
    private Button btnMove;
    private Button btnVertical;
    private Button btnHorizontal;
    private Button btnSubSubPlot;
    private Button btnSubSubSubPlot;
    private Button btnBlock;

    public StripSplitSplitPlotDesignDialog(Shell parentShell) {
        super(parentShell);
        this.setShellStyle(2256);
    }

    protected void configureShell(Shell shell) {
        super.configureShell(shell);
        shell.setText("Strip- Split- Split Plot Design : " + new File(this.filePath).getName());
    }

    protected Control createDialogArea(Composite parent) {
        Composite container = (Composite)super.createDialogArea(parent);
        FillLayout fl_container = new FillLayout(256);
        fl_container.marginHeight = 8;
        fl_container.marginWidth = 8;
        container.setLayout((Layout)fl_container);
        TabFolder tabFolder = new TabFolder(container, 0);
        TabItem tbtmModel = new TabItem(tabFolder, 0);
        tbtmModel.setText("Model");
        this.cmpModel = new Composite((Composite)tabFolder, 0);
        tbtmModel.setControl((Control)this.cmpModel);
        this.cmpModel.setLayout((Layout)new GridLayout(4, false));
        Label lblNumericVariables = new Label(this.cmpModel, 256);
        lblNumericVariables.setLayoutData((Object)new GridData(4, 4, true, true, 2, 1));
        lblNumericVariables.setText("Numeric Variable(s)");
        new Label(this.cmpModel, 0);
        Label lblResponseVariables = new Label(this.cmpModel, 0);
        lblResponseVariables.setLayoutData((Object)new GridData(4, 4, true, true, 1, 1));
        lblResponseVariables.setText("Response Variable(s)");
        this.lstNumericVariables = new List(this.cmpModel, 2818);
        GridData gd_lstNumericVariables = new GridData(4, 4, true, true, 2, 2);
        gd_lstNumericVariables.heightHint = 43;
        gd_lstNumericVariables.widthHint = 86;
        this.lstNumericVariables.setLayoutData((Object)gd_lstNumericVariables);
        this.lstNumericVariables.setData((Object)"Disvalidate");
        this.btnResponseVariable = new Button(this.cmpModel, 0);
        GridData gd_btnResponseVariable = new GridData(0x1000000, 0x1000000, true, true, 1, 1);
        gd_btnResponseVariable.minimumWidth = 73;
        gd_btnResponseVariable.minimumHeight = 26;
        gd_btnResponseVariable.heightHint = 25;
        gd_btnResponseVariable.widthHint = 90;
        this.btnResponseVariable.setLayoutData((Object)gd_btnResponseVariable);
        this.btnResponseVariable.setText("Add");
        this.lstResponse = new List(this.cmpModel, 2818);
        GridData gd_lstResponse = new GridData(4, 4, true, true, 1, 2);
        gd_lstResponse.widthHint = 145;
        gd_lstResponse.heightHint = 18;
        this.lstResponse.setLayoutData((Object)gd_lstResponse);
        new Label(this.cmpModel, 0);
        new Label(this.cmpModel, 0);
        this.btnMove = new Button(this.cmpModel, 0);
        GridData gd_btnMove = new GridData(0x1000000, 0x1000000, false, false, 1, 1);
        gd_btnMove.minimumHeight = 26;
        gd_btnMove.minimumWidth = 66;
        gd_btnMove.widthHint = 110;
        this.btnMove.setLayoutData((Object)gd_btnMove);
        this.btnMove.setText("Set to Factor");
        new Label(this.cmpModel, 0);
        new Label(this.cmpModel, 0);
        Label lblFactors = new Label(this.cmpModel, 0);
        GridData gd_lblFactors = new GridData(4, 0x1000000, true, false, 1, 1);
        gd_lblFactors.widthHint = 56;
        lblFactors.setLayoutData((Object)gd_lblFactors);
        lblFactors.setText("Factor(s)");
        new Label(this.cmpModel, 0);
        new Label(this.cmpModel, 0);
        new Label(this.cmpModel, 0);
        this.lstFactorVariables = new List(this.cmpModel, 2818);
        GridData gd_lstFactorVariables = new GridData(4, 4, true, true, 2, 11);
        gd_lstFactorVariables.widthHint = 79;
        gd_lstFactorVariables.heightHint = 20;
        this.lstFactorVariables.setLayoutData((Object)gd_lstFactorVariables);
        this.lstFactorVariables.setData((Object)"Disvalidate");
        Group group = new Group(this.cmpModel, 0);
        group.setLayoutData((Object)new GridData(4, 4, true, true, 2, 1));
        group.setText("Set");
        group.setLayout((Layout)new GridLayout(2, false));
        this.btnPerformSet = new Button((Composite)group, 16);
        this.btnPerformSet.setLayoutData((Object)new GridData(4, 0x1000000, true, true, 2, 1));
        this.btnPerformSet.setText("Perform Analysis Per Set");
        this.btnPerformSet.setSelection(true);
        this.btnPerformCombined = new Button((Composite)group, 16);
        this.btnPerformCombined.setLayoutData((Object)new GridData(4, 0x1000000, true, false, 2, 1));
        this.btnPerformCombined.setText("Perform Combined Analysis");
        this.btnPerformCombined.addSelectionListener(new SelectionListener(){

            public void widgetSelected(SelectionEvent e) {
                StripSplitSplitPlotDesignDialog.this.btnDisplay3.setEnabled(!StripSplitSplitPlotDesignDialog.this.btnPerformCombined.getSelection());
                if (StripSplitSplitPlotDesignDialog.this.btnPerformCombined.getSelection()) {
                    StripSplitSplitPlotDesignDialog.this.btnDisplay3.setSelection(false);
                }
            }

            public void widgetDefaultSelected(SelectionEvent e) {
            }
        });
        this.btnAddCombinedAnalysis = new Button((Composite)group, 0);
        this.btnAddCombinedAnalysis.setEnabled(true);
        GridData gd_btnAddCombinedAnalysis = new GridData(0x1000000, 0x1000000, true, false, 1, 1);
        gd_btnAddCombinedAnalysis.heightHint = 26;
        gd_btnAddCombinedAnalysis.widthHint = 90;
        gd_btnAddCombinedAnalysis.horizontalIndent = 8;
        this.btnAddCombinedAnalysis.setLayoutData((Object)gd_btnAddCombinedAnalysis);
        this.btnAddCombinedAnalysis.setText("Add");
        this.txtCombinedAnalysis = new Text((Composite)group, 2048);
        this.txtCombinedAnalysis.setBackground(SWTResourceManager.getColor(1));
        this.txtCombinedAnalysis.setEditable(false);
        GridData gd_txtCombinedAnalysis = new GridData(4, 0x1000000, true, false, 1, 1);
        gd_txtCombinedAnalysis.heightHint = 15;
        gd_txtCombinedAnalysis.widthHint = 160;
        this.txtCombinedAnalysis.setLayoutData((Object)gd_txtCombinedAnalysis);
        new Label(this.cmpModel, 0);
        Label lblFactors_1 = new Label(this.cmpModel, 0);
        lblFactors_1.setLayoutData((Object)new GridData(4, 1024, true, false, 1, 1));
        lblFactors_1.setText("Vertical Factor(s)");
        System.out.println(this.filePath);
        this.btnVertical = new Button(this.cmpModel, 0);
        GridData gd_btnVertical = new GridData(0x1000000, 0x1000000, true, true, 1, 1);
        gd_btnVertical.minimumWidth = 73;
        gd_btnVertical.minimumHeight = 26;
        gd_btnVertical.heightHint = 26;
        gd_btnVertical.widthHint = 90;
        this.btnVertical.setLayoutData((Object)gd_btnVertical);
        this.btnVertical.setText("Add");
        this.lstFactors = new List(this.cmpModel, 2818);
        GridData gd_lstFactors = new GridData(4, 4, true, true, 1, 1);
        gd_lstFactors.heightHint = 25;
        this.lstFactors.setLayoutData((Object)gd_lstFactors);
        new Label(this.cmpModel, 0);
        Label lblSubPlotFactors = new Label(this.cmpModel, 0);
        lblSubPlotFactors.setLayoutData((Object)new GridData(4, 4, true, false, 1, 1));
        lblSubPlotFactors.setText("Horizontal Factor(s)");
        TabItem tbtmOption = new TabItem(tabFolder, 0);
        tbtmOption.setText("Options");
        Composite composite_1 = new Composite((Composite)tabFolder, 0);
        tbtmOption.setControl((Control)composite_1);
        composite_1.setLayout((Layout)new GridLayout(3, false));
        Group grpDisplay = new Group(composite_1, 0);
        grpDisplay.setText("Display");
        grpDisplay.setLayout((Layout)new GridLayout(1, false));
        GridData gd_grpDisplay = new GridData(4, 4, true, true, 3, 1);
        gd_grpDisplay.minimumHeight = 94;
        gd_grpDisplay.heightHint = 89;
        grpDisplay.setLayoutData((Object)gd_grpDisplay);
        this.btnDisplay1 = new Button((Composite)grpDisplay, 32);
        this.btnDisplay1.setLayoutData((Object)new GridData(4, 4, true, true, 1, 1));
        this.btnDisplay1.setText("Descriptive Statistics of the Response Variable");
        this.btnDisplay2 = new Button((Composite)grpDisplay, 32);
        this.btnDisplay2.setLayoutData((Object)new GridData(4, 4, true, true, 1, 1));
        this.btnDisplay2.setText("Shapiro-Wilk Test for Normality of Residuals");
        this.btnDisplay3 = new Button((Composite)grpDisplay, 32);
        this.btnDisplay3.setLayoutData((Object)new GridData(4, 4, true, true, 1, 1));
        this.btnDisplay3.setEnabled(true);
        this.btnDisplay3.setText("Bartlett's Test for Homogeneity of Variances");
        Label lblSignificantLevel = new Label(composite_1, 0);
        GridData gd_lblSignificantLevel = new GridData(16384, 0x1000000, true, true, 1, 1);
        gd_lblSignificantLevel.widthHint = 376;
        lblSignificantLevel.setLayoutData((Object)gd_lblSignificantLevel);
        lblSignificantLevel.setText("Significance Level:");
        this.txtSignificantLevel = new Spinner(composite_1, 2048);
        this.txtSignificantLevel.setMaximum(10);
        this.txtSignificantLevel.setMinimum(1);
        this.txtSignificantLevel.setSelection(5);
        this.txtSignificantLevel.setDigits(2);
        Label lblNewLabel_1 = new Label(composite_1, 0);
        GridData gd_lblNewLabel_1 = new GridData(4, 4, true, true, 1, 1);
        gd_lblNewLabel_1.widthHint = 477;
        lblNewLabel_1.setLayoutData((Object)gd_lblNewLabel_1);
        Label lblNewLabel = new Label(composite_1, 0);
        GridData gd_lblNewLabel = new GridData(4, 4, true, true, 1, 1);
        gd_lblNewLabel.heightHint = 299;
        lblNewLabel.setLayoutData((Object)gd_lblNewLabel);
        new Label(composite_1, 0);
        new Label(composite_1, 0);
        this.btnHorizontal = new Button(this.cmpModel, 0);
        GridData gd_btnHorizontal = new GridData(0x1000000, 0x1000000, true, true, 1, 1);
        gd_btnHorizontal.minimumWidth = 76;
        gd_btnHorizontal.minimumHeight = 26;
        gd_btnHorizontal.widthHint = 90;
        gd_btnHorizontal.heightHint = 26;
        this.btnHorizontal.setLayoutData((Object)gd_btnHorizontal);
        this.btnHorizontal.setText("Add");
        this.lstHorizontal = new List(this.cmpModel, 2818);
        GridData gd_lstHorizontal = new GridData(4, 4, true, true, 1, 1);
        gd_lstHorizontal.heightHint = 25;
        this.lstHorizontal.setLayoutData((Object)gd_lstHorizontal);
        new Label(this.cmpModel, 0);
        Label lblSubsubPlotFactors = new Label(this.cmpModel, 0);
        lblSubsubPlotFactors.setLayoutData((Object)new GridData(4, 4, true, false, 1, 1));
        lblSubsubPlotFactors.setText("Subplot Factor(s)");
        this.btnSubSubPlot = new Button(this.cmpModel, 0);
        GridData gd_btnSubSubPlot = new GridData(0x1000000, 0x1000000, true, true, 1, 1);
        gd_btnSubSubPlot.minimumWidth = 73;
        gd_btnSubSubPlot.minimumHeight = 26;
        gd_btnSubSubPlot.heightHint = 26;
        gd_btnSubSubPlot.widthHint = 90;
        this.btnSubSubPlot.setLayoutData((Object)gd_btnSubSubPlot);
        this.btnSubSubPlot.setText("Add");
        this.lstSubSubPlot = new List(this.cmpModel, 2818);
        GridData gd_lstSubSubPlot = new GridData(4, 4, true, true, 1, 1);
        gd_lstSubSubPlot.heightHint = 25;
        this.lstSubSubPlot.setLayoutData((Object)gd_lstSubSubPlot);
        new Label(this.cmpModel, 0);
        Label lblSubsubsubPlotFactors = new Label(this.cmpModel, 0);
        lblSubsubsubPlotFactors.setLayoutData((Object)new GridData(4, 4, true, false, 1, 1));
        lblSubsubsubPlotFactors.setText("Sub-subplot Factor(s)");
        this.btnSubSubSubPlot = new Button(this.cmpModel, 0);
        GridData gd_btnSubSubSubPlot = new GridData(0x1000000, 0x1000000, true, true, 1, 1);
        gd_btnSubSubSubPlot.minimumWidth = 76;
        gd_btnSubSubSubPlot.minimumHeight = 26;
        gd_btnSubSubSubPlot.heightHint = 26;
        gd_btnSubSubSubPlot.widthHint = 90;
        this.btnSubSubSubPlot.setLayoutData((Object)gd_btnSubSubSubPlot);
        this.btnSubSubSubPlot.setText("Add");
        this.lstSubSubSubPlot = new List(this.cmpModel, 2818);
        GridData gd_lstSubSubSubPlot = new GridData(4, 4, true, true, 1, 1);
        gd_lstSubSubSubPlot.heightHint = 28;
        this.lstSubSubSubPlot.setLayoutData((Object)gd_lstSubSubSubPlot);
        new Label(this.cmpModel, 0);
        Label lblColumnBlock = new Label(this.cmpModel, 0);
        lblColumnBlock.setLayoutData((Object)new GridData(4, 1024, true, false, 1, 1));
        lblColumnBlock.setText("Block");
        this.btnBlock = new Button(this.cmpModel, 0);
        GridData gd_btnBlock = new GridData(0x1000000, 0x1000000, true, true, 1, 1);
        gd_btnBlock.minimumWidth = 73;
        gd_btnBlock.minimumHeight = 26;
        gd_btnBlock.heightHint = 26;
        gd_btnBlock.widthHint = 90;
        this.btnBlock.setLayoutData((Object)gd_btnBlock);
        this.btnBlock.setText("Add");
        this.txtBlock = new Text(this.cmpModel, 2048);
        this.txtBlock.setEditable(false);
        this.txtBlock.setBackground(SWTResourceManager.getColor(1));
        GridData gd_txtBlock = new GridData(4, 0x1000000, true, true, 1, 1);
        gd_txtBlock.heightHint = 18;
        this.txtBlock.setLayoutData((Object)gd_txtBlock);
        this.initDialogContents();
        this.listManager.initializeSingleButtonList(this.lstNumericVariables, this.lstResponse, this.btnResponseVariable);
        SelectionListener sListener = new SelectionListener(){

            public void widgetSelected(SelectionEvent e) {
                StripSplitSplitPlotDesignDialog.this.btnDisplay3.setEnabled(!StripSplitSplitPlotDesignDialog.this.btnPerformCombined.getSelection());
                StripSplitSplitPlotDesignDialog.this.preHocResult = null;
                StripSplitSplitPlotDesignDialog.this.isContrastReady = false;
                StripSplitSplitPlotDesignDialog.this.postHocVar = null;
                StripSplitSplitPlotDesignDialog.this.postHocOpts = null;
                StripSplitSplitPlotDesignDialog.this.outputFolderPath = null;
                StripSplitSplitPlotDesignDialog.this.btnPostHoc.setEnabled(false);
                StripSplitSplitPlotDesignDialog.this.btnContrast.setEnabled(false);
                if (StripSplitSplitPlotDesignDialog.this.dlgPostHoc != null) {
                    StripSplitSplitPlotDesignDialog.this.dlgPostHoc.getShell().dispose();
                }
                if (StripSplitSplitPlotDesignDialog.this.dlgContrast != null) {
                    StripSplitSplitPlotDesignDialog.this.dlgContrast.getShell().dispose();
                }
            }

            public void widgetDefaultSelected(SelectionEvent e) {
            }
        };
        int i = 0;
        while (i < this.cmpModel.getChildren().length) {
            if (this.cmpModel.getChildren()[i] instanceof Button) {
                ((Button)this.cmpModel.getChildren()[i]).addSelectionListener(sListener);
            }
            ++i;
        }
        this.btnPerformSet.addSelectionListener(sListener);
        this.btnPerformCombined.addSelectionListener(sListener);
        this.btnAddCombinedAnalysis.addSelectionListener(sListener);
        this.btnDisplay1.addSelectionListener(sListener);
        this.btnDisplay2.addSelectionListener(sListener);
        this.btnDisplay3.addSelectionListener(sListener);
        this.txtSignificantLevel.addModifyListener(new ModifyListener(){

            public void modifyText(ModifyEvent e) {
                StripSplitSplitPlotDesignDialog.this.preHocResult = null;
                StripSplitSplitPlotDesignDialog.this.isContrastReady = false;
                StripSplitSplitPlotDesignDialog.this.postHocVar = null;
                StripSplitSplitPlotDesignDialog.this.postHocOpts = null;
                StripSplitSplitPlotDesignDialog.this.outputFolderPath = null;
                StripSplitSplitPlotDesignDialog.this.btnPostHoc.setEnabled(false);
                StripSplitSplitPlotDesignDialog.this.btnContrast.setEnabled(false);
                if (StripSplitSplitPlotDesignDialog.this.dlgPostHoc != null) {
                    StripSplitSplitPlotDesignDialog.this.dlgPostHoc.getShell().dispose();
                }
                if (StripSplitSplitPlotDesignDialog.this.dlgContrast != null) {
                    StripSplitSplitPlotDesignDialog.this.dlgContrast.getShell().dispose();
                }
            }
        });
        this.listManager.initializeVariableMoveList(this.lstNumericVariables, this.lstFactorVariables, this.btnMove, this.filePath);
        this.listManager.initializeSingleButtonList(this.lstFactorVariables, this.lstFactors, this.btnVertical);
        this.listManager.initializeSingleButtonList(this.lstFactorVariables, this.lstHorizontal, this.btnHorizontal);
        this.listManager.initializeSingleButtonList(this.lstFactorVariables, this.lstSubSubPlot, this.btnSubSubPlot);
        this.listManager.initializeSingleButtonList(this.lstFactorVariables, this.lstSubSubSubPlot, this.btnSubSubSubPlot);
        this.listManager.initializeSingleSelectionList(this.lstFactorVariables, this.txtBlock, this.btnBlock);
        this.listManager.initializeSingleSelectionList(this.lstFactorVariables, this.txtCombinedAnalysis, this.btnAddCombinedAnalysis);
        return container;
    }

    private boolean validateCmpModel() {
        if (this.btnPerformCombined.getSelection() && this.txtCombinedAnalysis.getText().isEmpty()) {
            return false;
        }
        int i = 0;
        while (i < this.cmpModel.getChildren().length) {
            if (this.cmpModel.getChildren()[i] instanceof List && this.cmpModel.getChildren()[i] != this.lstFactorVariables && this.cmpModel.getChildren()[i] != this.lstNumericVariables && ((List)this.cmpModel.getChildren()[i]).getItemCount() == 0) {
                return false;
            }
            if (this.cmpModel.getChildren()[i] instanceof Text && ((Text)this.cmpModel.getChildren()[i]).getText().equals("")) {
                return false;
            }
            ++i;
        }
        return true;
    }

    private void initDialogContents() {
        this.listManager.initializeNumericList(this.lstNumericVariables, this.filePath);
        this.listManager.initializeFactorList(this.lstFactorVariables, this.filePath);
    }

    protected void buttonPressed(int buttonId) {
        if (buttonId == 0) {
            this.okPressed();
        } else if (8 == buttonId) {
            int i = 0;
            while (i < this.cmpModel.getChildren().length) {
                if (this.cmpModel.getChildren()[i] instanceof List) {
                    ((List)this.cmpModel.getChildren()[i]).removeAll();
                } else if (this.cmpModel.getChildren()[i] instanceof Text) {
                    ((Text)this.cmpModel.getChildren()[i]).setText("");
                } else if (this.cmpModel.getChildren()[i] instanceof Button && !((Button)this.cmpModel.getChildren()[i]).getText().equals("To Factor") && !((Button)this.cmpModel.getChildren()[i]).getText().equals("To Numeric")) {
                    ((Button)this.cmpModel.getChildren()[i]).setText("Add");
                }
                ++i;
            }
            if (this.dlgPostHoc != null) {
                this.dlgPostHoc.getShell().dispose();
            }
            if (this.dlgContrast != null) {
                this.dlgContrast.getShell().dispose();
            }
            this.isContrastReady = false;
            this.btnOk.setEnabled(true);
            this.preHocResult = null;
            this.postHocVar = null;
            this.postHocOpts = null;
            this.outputFolderPath = null;
            this.btnPostHoc.setEnabled(false);
            this.btnContrast.setEnabled(false);
            this.btnDisplay1.setSelection(false);
            this.btnDisplay2.setSelection(false);
            this.btnDisplay3.setSelection(false);
            this.txtSignificantLevel.setSelection(5);
            this.initDialogContents();
            this.btnMove.setText("Set to Factor");
            this.btnMove.setEnabled(false);
            this.btnResponseVariable.setText("Add");
            this.btnResponseVariable.setEnabled(false);
            this.btnAddCombinedAnalysis.setText("Add");
            this.btnAddCombinedAnalysis.setEnabled(false);
            this.txtCombinedAnalysis.setText("");
            this.btnBlock.setEnabled(false);
            this.btnBlock.setText("Add");
            this.btnVertical.setEnabled(false);
            this.btnVertical.setText("Add");
            this.btnHorizontal.setEnabled(false);
            this.btnHorizontal.setText("Add");
            this.btnSubSubPlot.setText("Add");
            this.btnSubSubPlot.setEnabled(false);
            this.btnSubSubSubPlot.setText("Add");
            this.btnSubSubSubPlot.setEnabled(false);
        } else if (15 == buttonId) {
            if (this.dlgPostHoc == null || this.dlgPostHoc.getShell().isDisposed()) {
                this.dlgPostHoc = new PostHocDialog(this.getShell(), this.preHocResult, this.outputFolderPath, (double)this.txtSignificantLevel.getSelection() / 100.0, this.design, this.btnPerformCombined.getSelection());
                this.dlgPostHoc.open();
            } else {
                this.dlgPostHoc.getShell().setVisible(true);
            }
            this.postHocVar = PostHocDialog.getPostHocVar();
            this.postHocOpts = PostHocDialog.getPostHocOpt();
        } else if (10 == buttonId) {
            if (this.dlgContrast == null || this.dlgContrast.getShell().isDisposed()) {
                this.dlgContrast = new ContrastDialog(this.getShell(), GeneralUtility.arrayConcatAll(this.lstFactors.getItems(), this.lstSubSubPlot.getItems(), this.lstSubSubSubPlot.getItems(), this.lstHorizontal.getItems()), this.filePath, this.outputFolderPath, this.lstResponse.getItems(), this.btnPerformCombined.getSelection());
                this.dlgContrast.open();
                this.isContrastReady = this.dlgContrast.contrastReady();
            } else {
                this.dlgContrast.getShell().setVisible(true);
            }
            this.isContrastReady = true;
        } else if (1 == buttonId) {
            this.cancelPressed();
        }
    }

    protected void okPressed() {
        if (this.btnPostHoc.getEnabled() && this.dlgPostHoc != null && !this.dlgPostHoc.getShell().isDisposed()) {
            this.postHocVar = PostHocDialog.getPostHocVar();
            this.postHocOpts = PostHocDialog.getPostHocOpt();
        }
        if (this.validateCmpModel()) {
            OperationProgressDialog rInfo = new OperationProgressDialog(this.getShell(), "Performing Analysis of Variance");
            rInfo.open();
            String set = this.txtCombinedAnalysis.getText();
            if (set.equals("")) {
                set = null;
            }
            this.btnOk.setEnabled(false);
            if (this.outputFolderPath == null) {
                this.outputFolderPath = StarAnalysisUtilities.createOutputFolder(this.filePath, this.design);
            }
            try {
                this.preHocResult = this.btnPerformCombined.getSelection() ? ProjectExplorerView.rJavaManager.getSTARAnalysisManager().doANOVACombined(this.filePath.replace(File.separator, "/"), this.outputFolderPath.replace(File.separator, "/"), this.design, this.lstResponse.getItems(), this.lstFactors.getItems(), this.lstHorizontal.getItems(), this.lstSubSubPlot.getItems(), this.lstSubSubSubPlot.getItems(), this.txtBlock.getText(), null, set, this.btnDisplay1.getSelection(), this.btnDisplay2.getSelection(), this.btnDisplay3.getSelection(), this.postHocOpts, this.postHocVar, this.isContrastReady, (double)this.txtSignificantLevel.getSelection() / 100.0) : ProjectExplorerView.rJavaManager.getSTARAnalysisManager().doANOVA(this.filePath.replace(File.separator, "/"), this.outputFolderPath.replace(File.separator, "/"), this.design, this.lstResponse.getItems(), this.lstFactors.getItems(), this.lstHorizontal.getItems(), this.lstSubSubPlot.getItems(), this.lstSubSubSubPlot.getItems(), this.txtBlock.getText(), null, set, this.btnDisplay1.getSelection(), this.btnDisplay2.getSelection(), this.btnDisplay3.getSelection(), this.postHocOpts, this.postHocVar, this.isContrastReady, (double)this.txtSignificantLevel.getSelection() / 100.0);
            }
            catch (Exception exception) {
                this.preHocResult = null;
            }
            if (this.preHocResult != null) {
                this.btnPostHoc.setEnabled(true);
            }
            this.btnContrast.setEnabled(StarAnalysisUtilities.factorsHasLevels(GeneralUtility.arrayConcatAll(this.lstFactors.getItems(), this.lstSubSubPlot.getItems(), this.lstSubSubSubPlot.getItems(), this.lstHorizontal.getItems()), this.filePath));
            rInfo.close();
            this.getShell().setMinimized(true);
            StarAnalysisUtilities.openAndRefreshFolder(this.outputFolderPath);
            this.btnOk.setEnabled(true);
        } else {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"Could not perform the analysis. \n\n Reason: Incomplete requirements. Make sure to fill up all the required fields");
        }
    }

    protected void createButtonsForButtonBar(Composite parent) {
        this.btnContrast = this.createButton(parent, 10, "Contrast", false);
        this.btnContrast.setEnabled(false);
        this.btnPostHoc = this.createButton(parent, 15, "Post Hoc", false);
        this.btnPostHoc.setEnabled(false);
        this.createButton(parent, 8, "Reset", false);
        this.btnOk = this.createButton(parent, 0, IDialogConstants.OK_LABEL, true);
        this.createButton(parent, 1, IDialogConstants.CANCEL_LABEL, false);
    }

    protected Point getInitialSize() {
        return new Point(798, 708);
    }
}

