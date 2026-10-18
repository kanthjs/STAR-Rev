/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.e4.core.contexts.IEclipseContext
 *  org.eclipse.e4.ui.workbench.lifecycle.PostContextCreate
 *  org.eclipse.jface.dialogs.MessageDialog
 *  org.eclipse.swt.widgets.Shell
 */
package org.irri.breedingtool.application.handler;

import java.io.File;
import java.io.IOException;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.ui.workbench.lifecycle.PostContextCreate;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;
import org.irri.breedingtool.application.dialog.SelectWorkspaceDialogNew;
import org.irri.breedingtool.manager.impl.ProjectExplorerManager;
import org.irri.breedingtool.utility.GeneralUtility;

public class StartupLifeCycleHandler {
    public boolean isWorkspaceNew = false;
    public String newWorkspacePath = "";

    @PostContextCreate
    public void startup(IEclipseContext context) {
        ProjectExplorerManager projMan = new ProjectExplorerManager();
        Shell shell = new Shell(0);
        if (!projMan.getWorkspacePath().equals("") && new File(projMan.getWorkspacePath()).exists() && GeneralUtility.isDirectoryWritable(projMan.getWorkspacePath())) {
            return;
        }
        System.out.println("Startup LifeCycle Triggered");
        if (!GeneralUtility.isDirectoryWritable(projMan.getWorkspacePath())) {
            MessageDialog.openError((Shell)shell, (String)"Workspace Error", (String)("The workspace path: '" + projMan.getWorkspacePath() + "' is not a valid filepath.\n \nThe path does not exist OR permission denied"));
        }
        SelectWorkspaceDialogNew dlg = new SelectWorkspaceDialogNew(shell);
        dlg.open();
        this.isWorkspaceNew = true;
        this.newWorkspacePath = dlg.workspacePath;
        if (dlg.isCancelled) {
            return;
        }
        if (this.isWorkspaceNew) {
            File sampleProject = new File(String.valueOf(dlg.workspacePath) + File.separator + "SampleProject");
            sampleProject.mkdirs();
            try {
                projMan.copySampleProjectTo(sampleProject.getAbsolutePath());
            }
            catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}

