/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.jface.dialogs.Dialog
 *  org.eclipse.jface.dialogs.IDialogConstants
 *  org.eclipse.jface.dialogs.MessageDialog
 *  org.eclipse.swt.events.SelectionEvent
 *  org.eclipse.swt.events.SelectionListener
 *  org.eclipse.swt.graphics.Point
 *  org.eclipse.swt.layout.GridData
 *  org.eclipse.swt.widgets.Button
 *  org.eclipse.swt.widgets.Combo
 *  org.eclipse.swt.widgets.Composite
 *  org.eclipse.swt.widgets.Control
 *  org.eclipse.swt.widgets.Display
 *  org.eclipse.swt.widgets.Group
 *  org.eclipse.swt.widgets.Shell
 *  org.eclipse.swt.widgets.TreeItem
 */
package org.irri.breedingtool.datamanipulation.dialog;

import java.io.File;
import java.util.ArrayList;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TreeItem;
import org.irri.breedingtool.application.handler.PartStackHandler;
import org.irri.breedingtool.application.model.ProjectExplorerTreeNodeModel;
import org.irri.breedingtool.manager.impl.DataManipulationManager;
import org.irri.breedingtool.manager.impl.ProjectExplorerManager;
import org.irri.breedingtool.projectexplorer.view.ProjectExplorerView;

public class SortDataDialog
extends Dialog {
    private Combo combo;
    private Combo combo_1;
    private Combo combo_2;
    private Button btnAscending;
    private Button btnAscending1;
    private Button btnAscending2;
    private Button btnDescending;
    private Button btnDescending1;
    private Button btnDescending2;
    private Group grpSortBy1;
    private Group grpSortBy2;
    private Group grpSortBy3;
    private String[] sortChoices;
    private String[] sortOrder;
    private String[] columnHeaders;
    private File newFile;
    private int ctr = 0;
    private ProjectExplorerTreeNodeModel newFileModel;
    DataManipulationManager dataManipulationManager;
    private ArrayList<String> varInfo = new ArrayList();
    private ProjectExplorerTreeNodeModel fileModel;

    public SortDataDialog(Shell parentShell, ArrayList<String> varInfo, ProjectExplorerTreeNodeModel fileModel) {
        super(parentShell);
        this.varInfo = varInfo;
        this.fileModel = fileModel;
        this.dataManipulationManager = new DataManipulationManager();
    }

    protected Control createDialogArea(Composite parent) {
        Composite container = (Composite)super.createDialogArea(parent);
        this.setShellStyle(2128);
        this.setBlockOnOpen(false);
        this.getShell().setText("Sort: " + this.dataManipulationManager.getDisplayFileName(this.fileModel.getProjectFile().getAbsolutePath()));
        this.columnHeaders = this.dataManipulationManager.getListItems(this.varInfo);
        System.out.println(this.fileModel.getProjectFile().getName());
        this.newFileModel = this.fileModel;
        this.newFile = this.fileModel.getProjectFile();
        this.grpSortBy1 = new Group(container, 0);
        GridData gd_grpSortBy1 = new GridData(4, 4, true, true, 4, 1);
        gd_grpSortBy1.widthHint = 280;
        this.grpSortBy1.setLayoutData((Object)gd_grpSortBy1);
        this.grpSortBy1.setText("Sort by");
        this.combo = new Combo((Composite)this.grpSortBy1, 12);
        this.combo.setBounds(10, 21, 137, 28);
        this.combo.setItems(this.columnHeaders);
        this.btnAscending = new Button((Composite)this.grpSortBy1, 16);
        this.btnAscending.setBounds(159, 23, 102, 23);
        this.btnAscending.setText("ascending");
        this.btnAscending.setSelection(true);
        this.btnDescending = new Button((Composite)this.grpSortBy1, 16);
        this.btnDescending.setBounds(286, 26, 102, 23);
        this.btnDescending.setText("descending");
        this.grpSortBy2 = new Group(container, 0);
        this.grpSortBy2.setEnabled(false);
        this.grpSortBy2.setLayoutData((Object)new GridData(4, 4, true, true, 4, 1));
        this.grpSortBy2.setText("Then by");
        this.combo.addSelectionListener(new SelectionListener(){

            public void widgetSelected(SelectionEvent e) {
                SortDataDialog.this.grpSortBy2.setEnabled(true);
                SortDataDialog sortDataDialog = SortDataDialog.this;
                sortDataDialog.ctr = sortDataDialog.ctr + 1;
            }

            public void widgetDefaultSelected(SelectionEvent e) {
            }
        });
        this.combo_1 = new Combo((Composite)this.grpSortBy2, 12);
        this.combo_1.setBounds(10, 21, 133, 28);
        this.combo_1.setItems(this.columnHeaders);
        this.btnAscending1 = new Button((Composite)this.grpSortBy2, 16);
        this.btnAscending1.setSelection(true);
        this.btnAscending1.setText("ascending");
        this.btnAscending1.setBounds(159, 21, 95, 27);
        this.btnDescending1 = new Button((Composite)this.grpSortBy2, 16);
        this.btnDescending1.setText("descending");
        this.btnDescending1.setBounds(286, 26, 109, 23);
        this.grpSortBy3 = new Group(container, 0);
        this.grpSortBy3.setEnabled(false);
        this.grpSortBy3.setLayoutData((Object)new GridData(4, 4, true, true, 4, 1));
        this.grpSortBy3.setText("Then by");
        this.combo_1.addSelectionListener(new SelectionListener(){

            public void widgetSelected(SelectionEvent e) {
                SortDataDialog.this.grpSortBy3.setEnabled(true);
                SortDataDialog sortDataDialog = SortDataDialog.this;
                sortDataDialog.ctr = sortDataDialog.ctr + 1;
            }

            public void widgetDefaultSelected(SelectionEvent e) {
            }
        });
        this.combo_2 = new Combo((Composite)this.grpSortBy3, 12);
        this.combo_2.setBounds(10, 21, 128, 28);
        this.combo_2.setItems(this.columnHeaders);
        this.combo_2.addSelectionListener(new SelectionListener(){

            public void widgetSelected(SelectionEvent e) {
                SortDataDialog sortDataDialog = SortDataDialog.this;
                sortDataDialog.ctr = sortDataDialog.ctr + 1;
            }

            public void widgetDefaultSelected(SelectionEvent e) {
            }
        });
        this.btnAscending2 = new Button((Composite)this.grpSortBy3, 16);
        this.btnAscending2.setText("ascending");
        this.btnAscending2.setBounds(160, 21, 98, 27);
        this.btnAscending2.setSelection(true);
        this.btnDescending2 = new Button((Composite)this.grpSortBy3, 16);
        this.btnDescending2.setText("descending");
        this.btnDescending2.setBounds(288, 23, 115, 23);
        return container;
    }

    protected void buttonPressed(int buttonId) {
        if (buttonId == 19) {
            this.combo.setItems(this.columnHeaders);
            this.combo_1.setItems(this.columnHeaders);
            this.grpSortBy2.setEnabled(true);
            this.combo_2.setItems(this.columnHeaders);
            this.grpSortBy3.setEnabled(true);
            this.ctr = 0;
        }
        super.buttonPressed(buttonId);
    }

    protected void okPressed() {
        if (this.combo.getText().isEmpty() && this.combo_1.getText().isEmpty() && this.combo_2.getText().isEmpty()) {
            MessageDialog.openWarning((Shell)this.getShell(), (String)"Data Sorting", (String)"Please choose a variable to be sorted.");
        } else if (this.checkVariableMatch()) {
            MessageDialog.openWarning((Shell)this.getShell(), (String)"Data Sorting", (String)"Sorry, You can only use a variable once.");
        } else {
            String dataFileName = this.newFile.getAbsolutePath().toString().replaceAll("\\\\+", "/");
            String fileNameExtension = this.dataManipulationManager.getManipulatedFileNameExtension("_sorted", this.newFile.getAbsolutePath());
            String newFile = dataFileName.replaceAll(".csv", fileNameExtension);
            String newFileName = this.newFileModel.getProjectFile().getName().replaceAll(".csv", fileNameExtension);
            this.sortChoices = new String[this.ctr];
            this.sortOrder = new String[this.ctr];
            this.getComboStrings();
            ProjectExplorerView.rJavaManager.getRJavaDataManipulationManager().sortCases(dataFileName, newFile, this.sortChoices, this.sortOrder);
            this.close();
            ProjectExplorerManager projExpMan = new ProjectExplorerManager();
            TreeItem sortedTree = projExpMan.getTreeNodeByName(this.newFileModel.getParentTreeItem(), newFileName);
            try {
                if (((ProjectExplorerTreeNodeModel)sortedTree.getData()).getElementInStack() == null) {
                    PartStackHandler.execute(projExpMan.createNewProjectExplorerModel((ProjectExplorerTreeNodeModel)this.newFileModel.getParentTreeItem().getData(), newFileName));
                } else {
                    PartStackHandler.reOpenPart((ProjectExplorerTreeNodeModel)sortedTree.getData());
                }
            }
            catch (NullPointerException nullPointerException) {
                MessageDialog.openWarning((Shell)Display.getCurrent().getActiveShell(), (String)"Sort was Unsuccessful", (String)"Failed to sort data from file.");
            }
        }
    }

    public void getComboStrings() {
        int ctr = 0;
        if (!this.combo.getText().isEmpty()) {
            this.sortChoices[ctr] = this.combo.getText();
            this.sortOrder[ctr] = this.btnAscending.getSelection() ? "ascending" : "descending";
            ++ctr;
        }
        if (!this.combo_1.getText().isEmpty()) {
            this.sortChoices[ctr] = this.combo_1.getText();
            this.sortOrder[ctr] = this.btnAscending1.getSelection() ? "ascending" : "descending";
            ++ctr;
        }
        if (!this.combo_2.getText().isEmpty()) {
            this.sortChoices[ctr] = this.combo_2.getText();
            this.sortOrder[ctr] = this.btnAscending2.getSelection() ? "ascending" : "descending";
            ++ctr;
        }
    }

    public boolean checkVariableMatch() {
        if (!this.combo_1.getText().isEmpty() && this.combo_1.getText().equals(this.combo.getText())) {
            return true;
        }
        if (!this.combo_2.getText().isEmpty()) {
            if (this.combo_2.getText().equals(this.combo.getText())) {
                return true;
            }
            if (this.combo_2.getText().equals(this.combo_1.getText())) {
                return true;
            }
        }
        return false;
    }

    protected void createButtonsForButtonBar(Composite parent) {
        this.createButton(parent, 19, "Reset", true);
        this.createButton(parent, 0, IDialogConstants.OK_LABEL, true);
        this.createButton(parent, 1, IDialogConstants.CANCEL_LABEL, false);
    }

    protected Point getInitialSize() {
        return new Point(450, 300);
    }
}

