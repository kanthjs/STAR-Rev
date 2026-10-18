/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.jface.dialogs.Dialog
 *  org.eclipse.jface.dialogs.IDialogConstants
 *  org.eclipse.swt.graphics.Point
 *  org.eclipse.swt.layout.GridData
 *  org.eclipse.swt.widgets.Composite
 *  org.eclipse.swt.widgets.Control
 *  org.eclipse.swt.widgets.Event
 *  org.eclipse.swt.widgets.Label
 *  org.eclipse.swt.widgets.List
 *  org.eclipse.swt.widgets.Listener
 *  org.eclipse.swt.widgets.Shell
 */
package org.irri.breedingtool.projectexplorer.dialog;

import java.io.File;
import java.util.Arrays;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.irri.breedingtool.application.handler.PartStackHandler;
import org.irri.breedingtool.manager.impl.ProjectExplorerManager;
import org.irri.breedingtool.projectexplorer.view.ProjectTreeComponent;

public class OpenProjectDialog
extends Dialog {
    private String getProjectName = "";
    private List projectList;
    private Label lblDateCreated;

    public String getGetProjectName() {
        return this.getProjectName;
    }

    private void setGetProjectName(String getProjectName) {
        this.getProjectName = getProjectName;
    }

    protected Point getInitialSize() {
        return new Point(429, 330);
    }

    public OpenProjectDialog(Shell parentShell) {
        super(parentShell);
        this.setBlockOnOpen(true);
    }

    protected void createButtonsForButtonBar(Composite parent) {
        this.createButton(parent, 0, IDialogConstants.OK_LABEL, true);
        this.createButton(parent, 1, IDialogConstants.CANCEL_LABEL, false);
    }

    protected void okPressed() {
        PartStackHandler.removeAll();
        this.setGetProjectName(this.projectList.getItem(this.projectList.getSelectionIndex()).toString());
        this.close();
    }

    protected Control createDialogArea(Composite parent) {
        parent.getShell().setText("Open Existing Project from the Workspace");
        Composite container = (Composite)super.createDialogArea(parent);
        new Label(container, 0);
        Listener listener = new Listener(){

            public void handleEvent(Event event) {
                switch (event.type) {
                    case 8: {
                        OpenProjectDialog.this.okPressed();
                        break;
                    }
                    case 3: {
                        String projectNameSelected = OpenProjectDialog.this.projectList.getItem(OpenProjectDialog.this.projectList.getSelectionIndex()).toString();
                        ProjectExplorerManager projectExplorerManager = new ProjectExplorerManager();
                        System.out.println(projectExplorerManager.getProjectDescription(projectNameSelected));
                        OpenProjectDialog.this.lblDateCreated.setText("Date Created: " + projectExplorerManager.getProjectDateCreated(projectNameSelected));
                        OpenProjectDialog.this.lblDateCreated.setVisible(true);
                    }
                }
            }
        };
        this.projectList = new List(container, 2816);
        this.projectList.addListener(8, listener);
        this.projectList.addListener(3, listener);
        File folder = new File(ProjectTreeComponent.getCurrentWorkspacePath());
        Object[] getDir = folder.listFiles();
        Arrays.sort(getDir);
        Object[] objectArray = getDir;
        int n = getDir.length;
        int n2 = 0;
        while (n2 < n) {
            Object fileEntry = objectArray[n2];
            if (((File)fileEntry).isDirectory()) {
                this.projectList.add(((File)fileEntry).getName());
            }
            ++n2;
        }
        GridData gd_list = new GridData(4, 4, true, true, 1, 1);
        gd_list.widthHint = 420;
        gd_list.heightHint = 381;
        this.projectList.setLayoutData((Object)gd_list);
        this.lblDateCreated = new Label(container, 0);
        this.lblDateCreated.setLayoutData((Object)new GridData(4, 4, true, true, 1, 1));
        this.lblDateCreated.setVisible(false);
        this.lblDateCreated.setText("Date created:");
        return container;
    }
}

