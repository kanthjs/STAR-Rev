/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.inject.Named
 *  org.eclipse.e4.core.di.annotations.Execute
 *  org.eclipse.jface.dialogs.MessageDialog
 *  org.eclipse.swt.widgets.Display
 *  org.eclipse.swt.widgets.Shell
 */
package org.irri.breedingtool.star.analysis.anova.handler;

import javax.inject.Named;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.irri.breedingtool.application.handler.DialogHandler;
import org.irri.breedingtool.application.handler.PartStackHandler;
import org.irri.breedingtool.projectexplorer.view.ProjectTreeComponent;
import org.irri.breedingtool.projectexplorer.view.RJavaManagerInitializer;
import org.irri.breedingtool.star.analysis.anova.dialog.CompletelyRandomizedDesignDialog;
import org.irri.breedingtool.utility.GeneralUtility;

public class CompletelyRandomizationDesignHandler {
    ProjectTreeComponent projectTreeComponent;

    @Named(value="activeShell")
    @Execute
    public void launchDesignDialog(Shell parent) {
        if (!RJavaManagerInitializer.isProjectActive) {
            MessageDialog.openError((Shell)Display.getCurrent().getActiveShell(), (String)"Error", (String)"The Project Explorer should contain a project.");
            return;
        }
        if (!GeneralUtility.isFileValidData(PartStackHandler.getActiveElementID())) {
            MessageDialog.openError((Shell)Display.getCurrent().getActiveShell(), (String)"Error", (String)"The active tab in the Editor panel should contain a data file.");
            return;
        }
        CompletelyRandomizedDesignDialog analysisDialog = new CompletelyRandomizedDesignDialog(parent);
        if (DialogHandler.openDialog(String.valueOf(PartStackHandler.getActiveElementID()) + this.getClass().getName(), analysisDialog)) {
            analysisDialog.open();
        }
    }
}

