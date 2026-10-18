/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.e4.ui.model.application.ui.basic.MPart
 *  org.eclipse.jface.dialogs.Dialog
 *  org.eclipse.jface.viewers.ArrayContentProvider
 *  org.eclipse.jface.viewers.CheckboxTableViewer
 *  org.eclipse.jface.viewers.IBaseLabelProvider
 *  org.eclipse.jface.viewers.IContentProvider
 *  org.eclipse.jface.viewers.LabelProvider
 *  org.eclipse.swt.layout.GridData
 *  org.eclipse.swt.widgets.Composite
 *  org.eclipse.swt.widgets.Control
 *  org.eclipse.swt.widgets.Label
 *  org.eclipse.swt.widgets.Shell
 *  org.eclipse.swt.widgets.Table
 */
package org.irri.breedingtool.datamanipulation.dialog;

import java.util.Collection;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;

public class SaveablePartPromptDialog
extends Dialog {
    private Collection<MPart> collection;
    private CheckboxTableViewer tableViewer;
    private Object[] checkedElements = new Object[0];

    public SaveablePartPromptDialog(Shell shell, Collection<MPart> collection) {
        super(shell);
        this.collection = collection;
    }

    protected Control createDialogArea(Composite parent) {
        parent = (Composite)super.createDialogArea(parent);
        Label lblSelectTheData = new Label(parent, 16384);
        lblSelectTheData.setLayoutData((Object)new GridData(4, 0x1000000, true, false));
        lblSelectTheData.setText("Select the data to save:");
        this.tableViewer = CheckboxTableViewer.newCheckList((Composite)parent, (int)2052);
        Table table = this.tableViewer.getTable();
        table.setLayoutData((Object)new GridData(4, 4, true, true, 1, 1));
        GridData data = new GridData(4, 4, true, true);
        data.heightHint = 250;
        data.widthHint = 300;
        this.tableViewer.getControl().setLayoutData((Object)data);
        this.tableViewer.setLabelProvider((IBaseLabelProvider)new LabelProvider(){

            public String getText(Object element) {
                return ((MPart)element).getLocalizedLabel();
            }
        });
        this.tableViewer.setContentProvider((IContentProvider)ArrayContentProvider.getInstance());
        this.tableViewer.setInput(this.collection);
        this.tableViewer.setAllChecked(true);
        return parent;
    }

    protected void okPressed() {
        this.checkedElements = this.tableViewer.getCheckedElements();
        super.okPressed();
    }

    public Object[] getCheckedElements() {
        return this.checkedElements;
    }
}

