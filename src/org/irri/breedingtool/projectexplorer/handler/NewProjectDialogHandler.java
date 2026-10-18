/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.inject.Named
 *  org.eclipse.e4.core.di.annotations.Execute
 *  org.eclipse.e4.ui.model.application.ui.basic.MWindow
 *  org.eclipse.e4.ui.workbench.modeling.EModelService
 *  org.eclipse.swt.widgets.Shell
 */
package org.irri.breedingtool.projectexplorer.handler;

import javax.inject.Named;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.ui.basic.MWindow;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.swt.widgets.Shell;
import org.irri.breedingtool.projectexplorer.dialog.NewProjectDialog;
import org.irri.breedingtool.projectexplorer.view.ProjectExplorerView;
import org.irri.breedingtool.projectexplorer.view.ProjectTreeComponent;

public class NewProjectDialogHandler {
    ProjectTreeComponent projectTreeComponent;

    @Named(value="activeShell")
    @Execute
    public void launchCreateNewProjectWizard(Shell parent, EModelService service, MWindow window) {
        NewProjectDialog newProjectDialog = new NewProjectDialog(parent);
        if (newProjectDialog.open() == 0) {
            this.projectTreeComponent = new ProjectTreeComponent(ProjectExplorerView.projectTree, newProjectDialog.getGetProjectName());
        }
    }
}

