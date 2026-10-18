/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.inject.Named
 *  org.eclipse.e4.core.contexts.IEclipseContext
 *  org.eclipse.e4.core.di.annotations.Execute
 *  org.eclipse.e4.ui.workbench.IWorkbench
 *  org.eclipse.jface.dialogs.MessageDialog
 *  org.eclipse.swt.widgets.Shell
 */
package org.irri.breedingtool.application.handler;

import java.lang.reflect.InvocationTargetException;
import javax.inject.Named;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.workbench.IWorkbench;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;

public class QuitHandler {
    @Execute
    public void execute(IWorkbench workbench, IEclipseContext context, @Named(value="activeShell") Shell shell) throws InvocationTargetException, InterruptedException {
        if (MessageDialog.openConfirm((Shell)shell, (String)"Confirmation", (String)"Do you want to exit?")) {
            workbench.close();
        }
    }
}

