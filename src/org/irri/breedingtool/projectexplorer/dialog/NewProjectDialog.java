/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.jface.dialogs.Dialog
 *  org.eclipse.jface.dialogs.IDialogConstants
 *  org.eclipse.swt.events.KeyEvent
 *  org.eclipse.swt.events.KeyListener
 *  org.eclipse.swt.graphics.Point
 *  org.eclipse.swt.layout.GridData
 *  org.eclipse.swt.layout.GridLayout
 *  org.eclipse.swt.widgets.Button
 *  org.eclipse.swt.widgets.Composite
 *  org.eclipse.swt.widgets.Control
 *  org.eclipse.swt.widgets.Label
 *  org.eclipse.swt.widgets.Shell
 *  org.eclipse.swt.widgets.Text
 */
package org.irri.breedingtool.projectexplorer.dialog;

import java.io.File;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;
import org.irri.breedingtool.application.handler.PartStackHandler;
import org.irri.breedingtool.manager.impl.ProjectExplorerManager;
import org.irri.breedingtool.projectexplorer.view.ProjectTreeComponent;
import org.irri.breedingtool.utility.GeneralUtility;

public class NewProjectDialog
extends Dialog {
    private String getProjectName = "";
    private Text txtProjectName;
    private Label lblProjectName;
    private Label lblError;
    private Button btnOk;

    public String getGetProjectName() {
        this.getProjectName = GeneralUtility.replaceLast(this.getProjectName, "\\s", "");
        return this.getProjectName;
    }

    protected Point getInitialSize() {
        return new Point(697, 212);
    }

    private void setGetProjectName(String getProjectName) {
        this.getProjectName = getProjectName;
    }

    public NewProjectDialog(Shell parentShell) {
        super(parentShell);
        this.setShellStyle(2160);
        this.setBlockOnOpen(true);
    }

    protected void createButtonsForButtonBar(Composite parent) {
        this.btnOk = this.createButton(parent, 0, IDialogConstants.OK_LABEL, true);
        this.createButton(parent, 1, IDialogConstants.CANCEL_LABEL, false);
    }

    protected void okPressed() {
        if (!this.txtProjectName.getText().toString().equals("") && !new File(String.valueOf(ProjectTreeComponent.getCurrentWorkspacePath()) + this.txtProjectName.getText().toString()).exists() && new File(ProjectTreeComponent.getCurrentWorkspacePath()).canWrite()) {
            ProjectExplorerManager projectExplorerManager = new ProjectExplorerManager();
            projectExplorerManager.createNewProject(this.txtProjectName.getText().toString(), "");
            this.setGetProjectName(this.txtProjectName.getText().toString());
            PartStackHandler.removeAll();
            this.close();
        } else if (this.txtProjectName.getText().toString().equals("")) {
            this.lblError.setText("Project name cannot be null!");
            this.lblError.setSize(190, 20);
        } else if (new File(String.valueOf(ProjectTreeComponent.getCurrentWorkspacePath()) + this.txtProjectName.getText().toString()).exists()) {
            this.lblError.setText("Project already exists. Please provide a different project name.");
            this.lblError.setSize(350, 100);
        } else if (new File(ProjectTreeComponent.getCurrentWorkspacePath()).canWrite()) {
            this.lblError.setText("Cannot create project. Access is Denied.");
            this.lblError.setSize(190, 20);
        }
    }

    protected Control createDialogArea(Composite parent) {
        parent.getShell().setText("Create New Project");
        Composite container = (Composite)super.createDialogArea(parent);
        GridLayout gridLayout = (GridLayout)container.getLayout();
        gridLayout.numColumns = 2;
        new Label(container, 0);
        new Label(container, 0);
        this.lblProjectName = new Label(container, 0);
        this.lblProjectName.setFont(SWTResourceManager.getFont("Tahoma", 10, 0));
        this.lblProjectName.setLayoutData((Object)new GridData(131072, 0x1000000, false, false, 1, 1));
        this.lblProjectName.setText("Project Name");
        this.txtProjectName = new Text(container, 2048);
        this.txtProjectName.setLayoutData((Object)new GridData(4, 0x1000000, true, false, 1, 1));
        this.txtProjectName.addKeyListener(new KeyListener(){

            public void keyPressed(KeyEvent e) {
            }

            public void keyReleased(KeyEvent e) {
                NewProjectDialog.this.lblError.setText("");
                NewProjectDialog.this.btnOk.setEnabled(true);
                if (NewProjectDialog.this.txtProjectName.getText().length() < 4) {
                    NewProjectDialog.this.btnOk.setEnabled(false);
                }
                if (!GeneralUtility.validateFileName(NewProjectDialog.this.txtProjectName.getText())) {
                    NewProjectDialog.this.lblError.setText("Invalid Project Name!");
                    NewProjectDialog.this.lblError.setSize(190, 20);
                    NewProjectDialog.this.btnOk.setEnabled(false);
                    return;
                }
                if (new File(String.valueOf(ProjectTreeComponent.getCurrentWorkspacePath()) + NewProjectDialog.this.txtProjectName.getText().toString()).exists() && !NewProjectDialog.this.txtProjectName.getText().equals("")) {
                    NewProjectDialog.this.lblError.setText("Project already exists. Please provide a different project name.");
                    NewProjectDialog.this.lblError.setSize(350, 100);
                    NewProjectDialog.this.btnOk.setEnabled(false);
                }
            }
        });
        new Label(container, 0);
        this.lblError = new Label(container, 0);
        this.lblError.setText("               ");
        return container;
    }
}

