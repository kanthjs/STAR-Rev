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
 *  org.eclipse.swt.widgets.DirectoryDialog
 *  org.eclipse.swt.widgets.Label
 *  org.eclipse.swt.widgets.Layout
 *  org.eclipse.swt.widgets.Shell
 *  org.eclipse.swt.widgets.Text
 */
package org.irri.breedingtool.application.dialog;

import java.io.File;
import java.io.IOException;
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
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;
import org.irri.breedingtool.manager.impl.ProjectExplorerManager;

public class SelectWorkspaceDialogNew
extends Dialog {
    public String workspacePath = "E:\\New Folder 4\\";
    private Text txtWorkspacePath;
    private Button btnCopySampleProject;
    private boolean isSampleProjectImported;
    public boolean isCancelled = true;
    private ProjectExplorerManager projMan = new ProjectExplorerManager();

    public SelectWorkspaceDialogNew(Shell parentShell) {
        super(parentShell);
    }

    protected Control createDialogArea(Composite parent) {
        Composite area = (Composite)super.createDialogArea(parent);
        Label lblSelectWorkSpace = new Label(area, 0);
        lblSelectWorkSpace.setFont(SWTResourceManager.getFont("Tahoma", 10, 1));
        lblSelectWorkSpace.setText("Select WorkSpace");
        Label lblChooseAWorkspace = new Label(area, 0);
        lblChooseAWorkspace.setText("Choose a workspace folder to use for this session.");
        Composite container = new Composite(area, 0);
        container.setLayout((Layout)new GridLayout(3, false));
        container.setLayoutData((Object)new GridData(1808));
        Label lblWorkspace = new Label(container, 0);
        lblWorkspace.setLayoutData((Object)new GridData(131072, 0x1000000, false, false, 1, 1));
        lblWorkspace.setText("Workspace:");
        this.txtWorkspacePath = new Text(container, 2048);
        this.txtWorkspacePath.setLayoutData((Object)new GridData(4, 0x1000000, true, false, 1, 1));
        if (this.projMan.getWorkspacePath().equals("NULL")) {
            this.txtWorkspacePath.setText(System.getProperty("user.home"));
        } else {
            this.txtWorkspacePath.setText(this.projMan.getWorkspacePath());
        }
        Button btnBrowseFolder = new Button(container, 0);
        btnBrowseFolder.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                DirectoryDialog dlg = new DirectoryDialog(SelectWorkspaceDialogNew.this.getShell());
                dlg.setFilterPath(SelectWorkspaceDialogNew.this.txtWorkspacePath.getText());
                dlg.setText("Workspace");
                dlg.setMessage("Select a workspace directory to use");
                String dir = dlg.open();
                if (dir != null) {
                    SelectWorkspaceDialogNew.this.txtWorkspacePath.setText(dir);
                }
            }
        });
        btnBrowseFolder.setLayoutData((Object)new GridData(131072, 0x1000000, false, false, 1, 1));
        btnBrowseFolder.setText("Browse");
        new Label(container, 0);
        this.btnCopySampleProject = new Button(container, 32);
        this.btnCopySampleProject.setSelection(true);
        this.btnCopySampleProject.setText("Import Sample Project");
        new Label(container, 0);
        try {
            File applicationPath = new File(String.valueOf(new File(".").getCanonicalPath()) + File.separator + "Projects" + File.separator + "SampleProject");
            if (!applicationPath.exists()) {
                this.btnCopySampleProject.setEnabled(false);
                this.btnCopySampleProject.setSelection(false);
            }
        }
        catch (IOException iOException) {
            this.btnCopySampleProject.setEnabled(false);
            this.btnCopySampleProject.setSelection(false);
        }
        return area;
    }

    protected void okPressed() {
        File workspace = new File(this.txtWorkspacePath.getText());
        if (!workspace.exists()) {
            workspace.mkdirs();
            if (!workspace.exists()) {
                MessageDialog.openError((Shell)this.getShell(), (String)"Workspace Error", (String)("The workspace path: '" + this.txtWorkspacePath.getText() + "' is not a valid filepath.\n \nThe path does not exist OR permission denied"));
                return;
            }
        }
        if (!this.txtWorkspacePath.getText().startsWith(workspace.getAbsolutePath())) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Workspace Error", (String)("The workspace path: '" + this.txtWorkspacePath.getText() + "' is not a valid filepath."));
            return;
        }
        System.out.println("DEBUG: " + this.txtWorkspacePath.getText() + " : " + workspace.getAbsolutePath());
        this.projMan.saveWorkspacePath(this.txtWorkspacePath.getText());
        if (this.btnCopySampleProject.getSelection()) {
            this.projMan.saveLastOpenedProject("SampleProject");
        }
        this.isSampleProjectImported = this.btnCopySampleProject.getSelection();
        this.workspacePath = this.txtWorkspacePath.getText();
        this.isCancelled = false;
        this.close();
    }

    public boolean isDialogCancelled() {
        return this.isCancelled;
    }

    protected void createButtonsForButtonBar(Composite parent) {
        this.createButton(parent, 0, IDialogConstants.OK_LABEL, true);
        this.createButton(parent, 1, IDialogConstants.CANCEL_LABEL, false);
    }

    protected Point getInitialSize() {
        return new Point(628, 245);
    }
}

