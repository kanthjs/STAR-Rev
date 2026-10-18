/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.jface.dialogs.ProgressMonitorDialog
 *  org.eclipse.swt.graphics.Point
 *  org.eclipse.swt.layout.GridData
 *  org.eclipse.swt.layout.GridLayout
 *  org.eclipse.swt.widgets.Composite
 *  org.eclipse.swt.widgets.Control
 *  org.eclipse.swt.widgets.Layout
 *  org.eclipse.swt.widgets.ProgressBar
 *  org.eclipse.swt.widgets.Shell
 */
package org.irri.breedingtool.datamanipulation.dialog;

import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.widgets.Shell;

public class OperationProgressDialog
extends ProgressMonitorDialog {
    private String title;

    public OperationProgressDialog(Shell parentShell, String title) {
        super(parentShell);
        this.title = title;
    }

    protected Control createDialogArea(Composite parent) {
        this.getShell().setText(this.title);
        Composite container = (Composite)super.createDialogArea(parent);
        container.setLayout((Layout)new GridLayout(2, false));
        ProgressBar bar = new ProgressBar((Composite)this.getShell(), 65538);
        bar.setLayoutData((Object)new GridData(4, 128, true, false, 2, 1));
        this.getShell().getDisplay().timerExec(50, new Runnable(){
            int i = 0;

            @Override
            public void run() {
                if (bar.isDisposed()) {
                    return;
                }
                bar.setSelection(this.i++);
                if (this.i < bar.getMaximum()) {
                    OperationProgressDialog.this.getShell().getDisplay().timerExec(50, (Runnable)this);
                } else if (this.i == bar.getMaximum()) {
                    this.i = 0;
                    bar.setSelection(this.i++);
                    OperationProgressDialog.this.getShell().getDisplay().timerExec(50, (Runnable)this);
                }
            }
        });
        return container;
    }

    protected void createButtonsForButtonBar(Composite parent) {
    }

    protected Point getInitialSize() {
        return new Point(350, 175);
    }
}

