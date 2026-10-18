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
import org.irri.breedingtool.datamanipulation.dialog.OperationProgressDialog;
import org.irri.breedingtool.projectexplorer.view.ProjectExplorerView;
import org.irri.breedingtool.utility.DialogFormUtility;
import org.irri.breedingtool.utility.StarAnalysisUtilities;

public class DescriptiveStatisticsCrossTabulationDialog
extends Dialog {
    private String filePath = PartStackHandler.getActivePart().getElementId().toString();
    private List lstAvailableData;
    private List lstRowVariables;
    private List lstColumnVariables;
    private Button btnExpected;
    private Button btnRow;
    private Button btnColumn;
    private Button btnTotal;
    private Button btnStandardize;
    private DialogFormUtility listManager = new DialogFormUtility();
    private Button btnOk;
    private TabItem tbtmCellDisplay;
    private Composite cmpOptions;
    private Group grpCount;
    private Group grpPercentages;
    private Group grpResidual;
    private Button btnAddRowVariables;
    private Button btnAddColumnVariables;

    public DescriptiveStatisticsCrossTabulationDialog(Shell parentShell) {
        super(parentShell);
        this.setShellStyle(2288);
    }

    protected void configureShell(Shell shell) {
        super.configureShell(shell);
        shell.setText("Cross Tabulation: " + new File(this.filePath).getName());
    }

    protected Control createDialogArea(Composite parent) {
        Composite container = (Composite)super.createDialogArea(parent);
        GridLayout gl_container = new GridLayout(1, false);
        gl_container.marginHeight = 8;
        gl_container.marginWidth = 8;
        container.setLayout((Layout)gl_container);
        TabFolder tabFolder = new TabFolder(container, 0);
        tabFolder.setLayoutData((Object)new GridData(4, 4, true, true, 1, 1));
        TabItem tbtmDescription = new TabItem(tabFolder, 0);
        tbtmDescription.setText("Variable Description");
        Composite composite = new Composite((Composite)tabFolder, 0);
        tbtmDescription.setControl((Control)composite);
        composite.setLayout((Layout)new GridLayout(3, false));
        Label lblAvailableData = new Label(composite, 0);
        lblAvailableData.setText("Available Data");
        new Label(composite, 0);
        Label lblRowVariabless = new Label(composite, 0);
        lblRowVariabless.setLayoutData((Object)new GridData(16384, 0x1000000, true, false, 1, 1));
        lblRowVariabless.setText("Row Variables(s)");
        this.lstAvailableData = new List(composite, 2818);
        GridData gd_lstAvailableData = new GridData(4, 4, true, true, 1, 11);
        gd_lstAvailableData.heightHint = 200;
        gd_lstAvailableData.widthHint = 80;
        this.lstAvailableData.setLayoutData((Object)gd_lstAvailableData);
        new Label(composite, 0);
        this.lstRowVariables = new List(composite, 2818);
        GridData gd_lstRowVariables = new GridData(4, 4, true, true, 1, 5);
        gd_lstRowVariables.widthHint = 80;
        gd_lstRowVariables.heightHint = 58;
        this.lstRowVariables.setLayoutData((Object)gd_lstRowVariables);
        new Label(composite, 0);
        new Label(composite, 0);
        this.btnAddRowVariables = new Button(composite, 0);
        this.btnAddRowVariables.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                DescriptiveStatisticsCrossTabulationDialog.this.btnAddRowVariables.setEnabled(true);
                DescriptiveStatisticsCrossTabulationDialog.this.listManager.initializeSingleButtonList(DescriptiveStatisticsCrossTabulationDialog.this.lstAvailableData, DescriptiveStatisticsCrossTabulationDialog.this.lstRowVariables, DescriptiveStatisticsCrossTabulationDialog.this.btnAddRowVariables);
                DescriptiveStatisticsCrossTabulationDialog.this.btnAddColumnVariables.setEnabled(false);
            }
        });
        GridData gd_btnAddRowVariables = new GridData(4, 0x1000000, false, false, 1, 1);
        gd_btnAddRowVariables.widthHint = 90;
        this.btnAddRowVariables.setLayoutData((Object)gd_btnAddRowVariables);
        this.btnAddRowVariables.setText("Add");
        this.listManager.initializeSingleButtonList(this.lstAvailableData, this.lstRowVariables, this.btnAddRowVariables);
        new Label(composite, 0);
        new Label(composite, 0);
        Label lblColumnVariables = new Label(composite, 0);
        lblColumnVariables.setText("Column Variable(s)");
        new Label(composite, 0);
        this.lstColumnVariables = new List(composite, 2818);
        GridData gd_lstColumnVariables = new GridData(4, 4, true, true, 1, 5);
        gd_lstColumnVariables.widthHint = 80;
        gd_lstColumnVariables.heightHint = 58;
        this.lstColumnVariables.setLayoutData((Object)gd_lstColumnVariables);
        new Label(composite, 0);
        new Label(composite, 0);
        this.btnAddColumnVariables = new Button(composite, 0);
        this.btnAddColumnVariables.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                DescriptiveStatisticsCrossTabulationDialog.this.btnAddColumnVariables.setEnabled(true);
                DescriptiveStatisticsCrossTabulationDialog.this.listManager.initializeSingleButtonList(DescriptiveStatisticsCrossTabulationDialog.this.lstAvailableData, DescriptiveStatisticsCrossTabulationDialog.this.lstColumnVariables, DescriptiveStatisticsCrossTabulationDialog.this.btnAddColumnVariables);
                DescriptiveStatisticsCrossTabulationDialog.this.btnAddRowVariables.setEnabled(false);
            }
        });
        GridData gd_btnAddColumnVariables = new GridData(4, 0x1000000, false, false, 1, 1);
        gd_btnAddColumnVariables.widthHint = 90;
        this.btnAddColumnVariables.setLayoutData((Object)gd_btnAddColumnVariables);
        this.btnAddColumnVariables.setText("Add");
        this.listManager.initializeSingleButtonList(this.lstAvailableData, this.lstColumnVariables, this.btnAddColumnVariables);
        new Label(composite, 0);
        this.tbtmCellDisplay = new TabItem(tabFolder, 0);
        this.tbtmCellDisplay.setText("Options");
        this.cmpOptions = new Composite((Composite)tabFolder, 0);
        this.tbtmCellDisplay.setControl((Control)this.cmpOptions);
        this.cmpOptions.setLayout((Layout)new GridLayout(1, false));
        Group grpCellDisplay = new Group(this.cmpOptions, 0);
        grpCellDisplay.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        grpCellDisplay.setLayout((Layout)new GridLayout(1, false));
        GridData gd_grpCellDisplay = new GridData(4, 4, true, true, 1, 1);
        gd_grpCellDisplay.widthHint = 256;
        grpCellDisplay.setLayoutData((Object)gd_grpCellDisplay);
        grpCellDisplay.setText("Cell Display");
        this.grpCount = new Group((Composite)grpCellDisplay, 0);
        this.grpCount.setLayout((Layout)new GridLayout(1, false));
        this.grpCount.setLayoutData((Object)new GridData(4, 4, true, true, 1, 1));
        this.grpCount.setText("Count:");
        this.btnExpected = new Button((Composite)this.grpCount, 32);
        this.btnExpected.setLayoutData((Object)new GridData(16384, 0x1000000, true, true, 1, 1));
        this.btnExpected.setText("Expected");
        this.grpPercentages = new Group((Composite)grpCellDisplay, 0);
        this.grpPercentages.setLayout((Layout)new GridLayout(1, false));
        this.grpPercentages.setLayoutData((Object)new GridData(4, 4, true, true, 1, 1));
        this.grpPercentages.setText("Percentages:");
        this.btnRow = new Button((Composite)this.grpPercentages, 32);
        this.btnRow.setLayoutData((Object)new GridData(16384, 0x1000000, true, true, 1, 1));
        this.btnRow.setText("Row");
        this.btnColumn = new Button((Composite)this.grpPercentages, 32);
        this.btnColumn.setLayoutData((Object)new GridData(16384, 0x1000000, true, true, 1, 1));
        this.btnColumn.setText("Column");
        this.btnTotal = new Button((Composite)this.grpPercentages, 32);
        this.btnTotal.setLayoutData((Object)new GridData(16384, 0x1000000, true, true, 1, 1));
        this.btnTotal.setText("Total");
        this.grpResidual = new Group((Composite)grpCellDisplay, 0);
        this.grpResidual.setLayout((Layout)new GridLayout(1, false));
        this.grpResidual.setLayoutData((Object)new GridData(4, 4, true, true, 1, 1));
        this.grpResidual.setText("Residual:");
        this.btnStandardize = new Button((Composite)this.grpResidual, 32);
        this.btnStandardize.setText("Standardize");
        this.listManager.initializeVariableList(this.lstAvailableData, this.filePath);
        return container;
    }

    protected void buttonPressed(int buttonId) {
        if (buttonId == 0) {
            this.okPressed();
        } else if (buttonId == 1) {
            this.cancelPressed();
        } else if (buttonId == 8) {
            this.lstAvailableData.removeAll();
            this.lstColumnVariables.removeAll();
            this.lstRowVariables.removeAll();
            this.btnAddRowVariables.setText("Add");
            this.btnAddRowVariables.setEnabled(false);
            this.btnAddColumnVariables.setText("Add");
            this.btnAddColumnVariables.setEnabled(false);
            this.listManager.setCheckBoxesToBoolean(false, new Composite[]{this.grpResidual, this.grpCount, this.grpPercentages});
            this.listManager.initializeVariableList(this.lstAvailableData, this.filePath);
        }
    }

    protected void okPressed() {
        if (this.lstRowVariables.getItemCount() <= 0 || this.lstColumnVariables.getItemCount() <= 0) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"Row Variable(s) and Column Variable(s) dialog box of the Variable Description tab should both contain at least one item.");
            return;
        }
        this.btnOk.setEnabled(false);
        String dataFileName = this.filePath.replace(File.separator, "/");
        String outFolder = StarAnalysisUtilities.createOutputFolder(this.filePath, "CrossTabulation");
        OperationProgressDialog rInfo = new OperationProgressDialog(this.getShell(), "Performing Analysis");
        rInfo.open();
        String outFileName = outFolder;
        ProjectExplorerView.rJavaManager.getSTARAnalysisManager().doCrossTab(dataFileName, outFileName.replace(File.separator, "/"), this.lstRowVariables.getItems(), this.lstColumnVariables.getItems(), this.btnExpected.getSelection(), this.btnStandardize.getSelection(), this.btnTotal.getSelection(), this.btnRow.getSelection(), this.btnColumn.getSelection());
        this.getShell().setMinimized(true);
        StarAnalysisUtilities.openAndRefreshFolder(outFolder);
        rInfo.close();
        this.btnOk.setEnabled(true);
    }

    protected void createButtonsForButtonBar(Composite parent) {
        this.createButton(parent, 8, "Reset", false);
        this.btnOk = this.createButton(parent, 0, IDialogConstants.OK_LABEL, true);
        this.createButton(parent, 1, IDialogConstants.CANCEL_LABEL, false);
    }

    protected Point getInitialSize() {
        return new Point(483, 410);
    }
}

