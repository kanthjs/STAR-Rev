/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.inject.Named
 *  org.eclipse.e4.core.di.annotations.Execute
 *  org.eclipse.swt.widgets.Shell
 */
package org.irri.breedingtool.projectexplorer.handler;

import javax.inject.Named;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.swt.widgets.Shell;
import org.irri.breedingtool.projectexplorer.dialog.OpenProjectDialog;
import org.irri.breedingtool.projectexplorer.view.ProjectExplorerView;
import org.irri.breedingtool.projectexplorer.view.ProjectTreeComponent;

public class OpenProjectDialogHandler {
    @Named(value="activeShell")
    @Execute
    public static void launchCreateNewProjectWizard(Shell parent) {
        OpenProjectDialog openProject = new OpenProjectDialog(parent);
        if (openProject.open() == 0) {
            new ProjectTreeComponent(ProjectExplorerView.projectTree, openProject.getGetProjectName());
        }
    }
}

