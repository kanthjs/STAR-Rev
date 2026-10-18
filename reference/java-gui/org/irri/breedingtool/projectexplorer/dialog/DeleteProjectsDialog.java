/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.jface.dialogs.Dialog
 *  org.eclipse.jface.dialogs.IDialogConstants
 *  org.eclipse.jface.viewers.CheckboxTreeViewer
 *  org.eclipse.swt.events.SelectionAdapter
 *  org.eclipse.swt.events.SelectionEvent
 *  org.eclipse.swt.events.SelectionListener
 *  org.eclipse.swt.graphics.Point
 *  org.eclipse.swt.layout.GridData
 *  org.eclipse.swt.layout.GridLayout
 *  org.eclipse.swt.widgets.Button
 *  org.eclipse.swt.widgets.Composite
 *  org.eclipse.swt.widgets.Control
 *  org.eclipse.swt.widgets.Label
 *  org.eclipse.swt.widgets.Layout
 *  org.eclipse.swt.widgets.Shell
 *  org.eclipse.swt.widgets.Tree
 *  org.eclipse.swt.widgets.TreeItem
 */
package org.irri.breedingtool.projectexplorer.dialog;

import java.io.File;
import java.util.ArrayList;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.wb.swt.SWTResourceManager;
import org.irri.breedingtool.manager.impl.ProjectExplorerManager;
import org.irri.breedingtool.projectexplorer.view.ProjectTreeComponent;

public class DeleteProjectsDialog
extends Dialog {
    private Tree dialogTree;
    private ArrayList<File> selectedProjects = new ArrayList();
    private Label lblSelectProjectsTo;
    private Button btnDeleteMultiProjects;

    public DeleteProjectsDialog(Shell parentShell) {
        super(parentShell);
    }

    protected Control createDialogArea(Composite parent) {
        Composite container = (Composite)super.createDialogArea(parent);
        container.setLayout((Layout)new GridLayout(1, false));
        ProjectExplorerManager projMan = new ProjectExplorerManager();
        Label lblDeleteProject = new Label(container, 0);
        lblDeleteProject.setLayoutData((Object)new GridData(4, 0x1000000, true, false, 1, 1));
        lblDeleteProject.setFont(SWTResourceManager.getFont("Tahoma", 10, 0));
        lblDeleteProject.setText("Delete Project: " + projMan.getLastOpenedProject());
        this.btnDeleteMultiProjects = new Button(container, 32);
        this.btnDeleteMultiProjects.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                DeleteProjectsDialog.this.lblSelectProjectsTo.setEnabled(DeleteProjectsDialog.this.btnDeleteMultiProjects.getSelection());
                DeleteProjectsDialog.this.dialogTree.setEnabled(DeleteProjectsDialog.this.btnDeleteMultiProjects.getSelection());
            }
        });
        this.btnDeleteMultiProjects.setText("I want to delete multiple projects");
        this.lblSelectProjectsTo = new Label(container, 0);
        this.lblSelectProjectsTo.setEnabled(false);
        this.lblSelectProjectsTo.setLayoutData((Object)new GridData(4, 0x1000000, true, false, 1, 1));
        this.lblSelectProjectsTo.setText("Select Projects to be deleted:");
        CheckboxTreeViewer checkboxTreeViewer = new CheckboxTreeViewer(container, 2048);
        this.dialogTree = checkboxTreeViewer.getTree();
        this.dialogTree.setEnabled(false);
        this.dialogTree.setLayoutData((Object)new GridData(4, 4, true, true, 1, 1));
        File projectsDir = new File(ProjectTreeComponent.getCurrentWorkspacePath());
        File[] fileArray = projectsDir.listFiles();
        int n = fileArray.length;
        int n2 = 0;
        while (n2 < n) {
            File project = fileArray[n2];
            if (project.isDirectory()) {
                TreeItem trtmNewTreeitem = new TreeItem(this.dialogTree, 0);
                trtmNewTreeitem.setText(project.getName());
                trtmNewTreeitem.setData((Object)project);
            }
            ++n2;
        }
        return container;
    }

    protected void createButtonsForButtonBar(Composite parent) {
        this.createButton(parent, 0, IDialogConstants.OK_LABEL, true);
        this.createButton(parent, 1, IDialogConstants.CANCEL_LABEL, false);
    }

    protected void okPressed() {
        this.selectedProjects.clear();
        int i = 0;
        while (i < this.dialogTree.getItemCount()) {
            if (this.dialogTree.getItem(i).getChecked()) {
                this.selectedProjects.add((File)this.dialogTree.getItem(i).getData());
            }
            ++i;
        }
        if (this.selectedProjects.isEmpty()) {
            if (!this.btnDeleteMultiProjects.getSelection()) {
                ProjectExplorerManager projMan = new ProjectExplorerManager();
                File currProject = new File(String.valueOf(ProjectTreeComponent.getCurrentWorkspacePath()) + File.separator + projMan.getLastOpenedProject());
                this.selectedProjects.add(currProject);
                this.close();
            }
            return;
        }
        this.close();
    }

    public ArrayList<File> getProjectsSelected() {
        return this.selectedProjects;
    }

    protected Point getInitialSize() {
        return new Point(450, 300);
    }
}

