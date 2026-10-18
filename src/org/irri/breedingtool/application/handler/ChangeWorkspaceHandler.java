/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.e4.core.di.annotations.Execute
 *  org.eclipse.e4.ui.model.application.ui.basic.MWindow
 *  org.eclipse.e4.ui.workbench.modeling.EModelService
 *  org.eclipse.swt.widgets.Shell
 */
package org.irri.breedingtool.application.handler;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.ui.basic.MWindow;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.swt.widgets.Shell;
import org.irri.breedingtool.application.dialog.SelectWorkspaceDialog;
import org.irri.breedingtool.application.handler.PartStackHandler;
import org.irri.breedingtool.projectexplorer.view.ProjectExplorerView;
import org.irri.breedingtool.projectexplorer.view.ProjectTreeComponent;
import org.irri.breedingtool.projectexplorer.view.RJavaManagerInitializer;

public class ChangeWorkspaceHandler {
    @Execute
    public void launchDesignDialog(Shell parent, EModelService service, MWindow window) {
        SelectWorkspaceDialog dlg = new SelectWorkspaceDialog(parent);
        dlg.open();
        if (!dlg.isDialogCancelled()) {
            ProjectExplorerView.projectTree.removeAll();
            ProjectExplorerView.projectTree.setText("No active project selected.");
            RJavaManagerInitializer.isProjectActive = false;
            if (dlg.isSampleProjectImported()) {
                new ProjectTreeComponent(ProjectExplorerView.projectTree, "SampleProject");
            }
            ProjectExplorerView.refreshApplicationTitle();
            PartStackHandler.removeAll();
        }
    }
}

