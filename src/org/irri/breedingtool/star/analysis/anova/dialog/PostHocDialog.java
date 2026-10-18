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
 *  org.eclipse.swt.widgets.Label
 *  org.eclipse.swt.widgets.Layout
 *  org.eclipse.swt.widgets.List
 *  org.eclipse.swt.widgets.Shell
 */
package org.irri.breedingtool.star.analysis.anova.dialog;

import java.io.File;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.irri.breedingtool.application.handler.PartStackHandler;
import org.irri.breedingtool.manager.impl.ProjectExplorerManager;
import org.irri.breedingtool.projectexplorer.view.ProjectExplorerView;
import org.irri.breedingtool.utility.DialogFormUtility;
import org.irri.breedingtool.utility.StarAnalysisUtilities;

public class PostHocDialog
extends Dialog {
    protected Object result;
    private String[] availableVars;
    private static String[] postHocVar;
    private static String[] postHocOpt;
    private String folderPath;
    private double sig;
    private String design;
    private DialogFormUtility lstManager = new DialogFormUtility();
    private List lstPostHocTest;
    private Composite cmpOptions;
    private Control btnOk;
    private boolean isSet = false;
    private boolean isScheffesTestEnabled = true;

    public PostHocDialog(Shell parentShell, String[] vars, String folderP, double numSig, String designType, boolean set) {
        super(parentShell);
        this.setBlockOnOpen(false);
        this.isSet = set;
        this.setShellStyle(34960);
        this.availableVars = vars;
        this.folderPath = folderP;
        this.sig = numSig;
        this.design = designType;
    }

    public PostHocDialog(Shell parentShell, String[] vars, String folderP, double numSig, String designType, boolean set, boolean isScheffesTestEnabled) {
        super(parentShell);
        this.setBlockOnOpen(false);
        this.isSet = set;
        this.setShellStyle(34960);
        this.availableVars = vars;
        this.folderPath = folderP;
        this.sig = numSig;
        this.design = designType;
        this.isScheffesTestEnabled = isScheffesTestEnabled;
    }

    public boolean close() {
        this.getShell().setVisible(false);
        return false;
    }

    protected Control createDialogArea(Composite parent) {
        Composite container = (Composite)super.createDialogArea(parent);
        GridLayout gl_container = new GridLayout(3, false);
        gl_container.marginHeight = 8;
        gl_container.marginWidth = 8;
        container.setLayout((Layout)gl_container);
        Label label = new Label(container, 0);
        label.setText("Response Variable(s) \r\nwith significant effect(s)");
        new Label(container, 0);
        Label lblPostHocTests = new Label(container, 0);
        lblPostHocTests.setText("Post Hoc Tests:");
        List lstAvailableVariables = new List(container, 2818);
        GridData gd_lstAvailableVariables = new GridData(4, 4, true, true, 1, 1);
        gd_lstAvailableVariables.heightHint = 174;
        gd_lstAvailableVariables.widthHint = 200;
        lstAvailableVariables.setLayoutData((Object)gd_lstAvailableVariables);
        int i = 0;
        while (i < this.availableVars.length) {
            lstAvailableVariables.add(this.availableVars[i]);
            ++i;
        }
        Button btnAddRemove = new Button(container, 0);
        GridData gd_btnAddRemove = new GridData(16384, 0x1000000, false, false, 1, 1);
        gd_btnAddRemove.widthHint = 82;
        btnAddRemove.setLayoutData((Object)gd_btnAddRemove);
        btnAddRemove.setText("Add");
        this.lstPostHocTest = new List(container, 2818);
        GridData gd_lstPostHocTest_1 = new GridData(4, 4, true, true, 1, 1);
        gd_lstPostHocTest_1.heightHint = 190;
        gd_lstPostHocTest_1.widthHint = 200;
        this.lstPostHocTest.setLayoutData((Object)gd_lstPostHocTest_1);
        new Label(container, 0);
        new Label(container, 0);
        new Label(container, 0);
        this.cmpOptions = new Composite(container, 0);
        this.cmpOptions.setLayoutData((Object)new GridData(4, 4, true, false, 4, 1));
        this.cmpOptions.setLayout((Layout)new GridLayout(1, false));
        Button btnLeastSignificantDifference = new Button(this.cmpOptions, 32);
        btnLeastSignificantDifference.setLayoutData((Object)new GridData(4, 4, true, false, 1, 1));
        btnLeastSignificantDifference.setText("Least Significant Difference (LSD) Test");
        btnLeastSignificantDifference.setData((Object)"LSD");
        Button btnDuncanMultipleRange = new Button(this.cmpOptions, 32);
        btnDuncanMultipleRange.setLayoutData((Object)new GridData(4, 4, true, false, 1, 1));
        btnDuncanMultipleRange.setText("Duncan Multiple Range test (DMRT)");
        btnDuncanMultipleRange.setData((Object)"duncan");
        Button btnTukeysHonestSignificant = new Button(this.cmpOptions, 32);
        btnTukeysHonestSignificant.setLayoutData((Object)new GridData(4, 4, true, false, 1, 1));
        btnTukeysHonestSignificant.setText("Tukey's Honest Significant Difference (HSD) Test");
        btnTukeysHonestSignificant.setData((Object)"HSD");
        Button btnStudentnewmannkeulssnkTest = new Button(this.cmpOptions, 32);
        btnStudentnewmannkeulssnkTest.setText("Student-Newmann-Keul's (SNK) Test");
        btnStudentnewmannkeulssnkTest.setData((Object)"SNK");
        Button btnScheffesTest = new Button(this.cmpOptions, 32);
        btnScheffesTest.setText("Scheffe's Test");
        btnScheffesTest.setData((Object)"scheffe");
        btnScheffesTest.setEnabled(this.isScheffesTestEnabled);
        new Label(container, 0);
        new Label(container, 0);
        this.lstManager.initializeSingleButtonList(lstAvailableVariables, this.lstPostHocTest, btnAddRemove);
        new Label(container, 0);
        return container;
    }

    protected void createButtonsForButtonBar(Composite parent) {
        this.btnOk = this.createButton(parent, 0, IDialogConstants.OK_LABEL, true);
        this.createButton(parent, 1, IDialogConstants.CANCEL_LABEL, false);
    }

    protected void okPressed() {
        if (this.lstPostHocTest.getItemCount() > 0 && this.lstManager.isCheckBoxesHasBoolean(this.cmpOptions)) {
            this.btnOk.setEnabled(false);
            postHocVar = this.lstPostHocTest.getItems();
            postHocOpt = this.lstManager.getCheckBoxesValue(this.cmpOptions);
            if (this.isScheffesTestEnabled) {
                ProjectExplorerView.rJavaManager.getSTARAnalysisManager().doPairwiseANOVA(this.folderPath.replace(File.separator, "/"), postHocVar, postHocOpt, this.design, this.sig, this.isSet);
                System.out.println("DoPairwiseAnova called.");
            } else {
                StarAnalysisUtilities.testVarArgs(this.folderPath.replace(File.separator, "/"), postHocVar, postHocOpt);
                ProjectExplorerView.rJavaManager.getSTARAnalysisManager().doPairwiseBIBD(this.folderPath.replace(File.separator, "/"), postHocVar, postHocOpt);
            }
            ProjectExplorerManager projectMan = new ProjectExplorerManager();
            projectMan.refreshFolder(projectMan.getTreeNodeModelbyFilePath(this.folderPath));
            if (PartStackHandler.isOpen(String.valueOf(this.folderPath) + "PairwiseOutput.txt")) {
                PartStackHandler.reOpenPart(projectMan.getTreeNodeModelbyFilePath(String.valueOf(this.folderPath) + "PairwiseOutput.txt"));
                System.out.println("IsOpen");
            } else {
                PartStackHandler.execute(projectMan.getTreeNodeModelbyFilePath(String.valueOf(this.folderPath) + "PairwiseOutput.txt"));
            }
            this.btnOk.setEnabled(true);
            this.getShell().setVisible(false);
        } else {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"Must be aleast one variable selected and one option selected");
        }
    }

    static String[] getPostHocVar() {
        return postHocVar;
    }

    static String[] getPostHocOpt() {
        return postHocOpt;
    }

    protected Point getInitialSize() {
        return new Point(605, 555);
    }
}

