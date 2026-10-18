/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.jface.dialogs.Dialog
 *  org.eclipse.jface.dialogs.MessageDialog
 *  org.eclipse.swt.events.SelectionAdapter
 *  org.eclipse.swt.events.SelectionEvent
 *  org.eclipse.swt.events.SelectionListener
 *  org.eclipse.swt.graphics.Point
 *  org.eclipse.swt.graphics.Rectangle
 *  org.eclipse.swt.layout.GridData
 *  org.eclipse.swt.widgets.Button
 *  org.eclipse.swt.widgets.Composite
 *  org.eclipse.swt.widgets.Control
 *  org.eclipse.swt.widgets.Display
 *  org.eclipse.swt.widgets.Label
 *  org.eclipse.swt.widgets.List
 *  org.eclipse.swt.widgets.Shell
 */
package org.irri.breedingtool.datamanipulation.dialog;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;

public class DeleteColumnsDialog
extends Dialog {
    int[] selectionIndices;
    private List list;
    private String inputText = "";
    private String[] columnNames;
    private Label lblAreYouSure;
    private Button checkBoxDeleteMulti;
    protected int ctr;
    private GridData gd_list;
    private Rectangle rect;

    public DeleteColumnsDialog(Shell parentShell, String selectedString, String[] columnNames) {
        super(parentShell);
        this.columnNames = columnNames;
        this.inputText = selectedString;
    }

    protected Control createDialogArea(Composite parent) {
        Composite container = (Composite)super.createDialogArea(parent);
        this.lblAreYouSure = new Label(container, 0);
        this.lblAreYouSure.setLayoutData((Object)new GridData(4, 4, true, false, 1, 1));
        this.lblAreYouSure.setText("Are you sure you want to delete column " + this.inputText + "?");
        this.checkBoxDeleteMulti = new Button(container, 32);
        this.checkBoxDeleteMulti.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                DeleteColumnsDialog.this.lblAreYouSure.setText("Please select the columns you want to delete.");
                if (DeleteColumnsDialog.this.checkBoxDeleteMulti.getSelection()) {
                    DeleteColumnsDialog.this.rect = DeleteColumnsDialog.this.getShell().getBounds();
                    ((DeleteColumnsDialog)DeleteColumnsDialog.this).gd_list.grabExcessVerticalSpace = true;
                    DeleteColumnsDialog.this.list.setLayoutData((Object)DeleteColumnsDialog.this.gd_list);
                    DeleteColumnsDialog.this.getShell().setBounds(((DeleteColumnsDialog)DeleteColumnsDialog.this).rect.x, ((DeleteColumnsDialog)DeleteColumnsDialog.this).rect.y, ((DeleteColumnsDialog)DeleteColumnsDialog.this).rect.width, ((DeleteColumnsDialog)DeleteColumnsDialog.this).rect.height + 100);
                    DeleteColumnsDialog.this.list.setVisible(true);
                    DeleteColumnsDialog.this.list.setItems(DeleteColumnsDialog.this.columnNames);
                    DeleteColumnsDialog.this.ctr = 0;
                    String[] stringArray = DeleteColumnsDialog.this.columnNames;
                    int n = stringArray.length;
                    int n2 = 0;
                    while (n2 < n) {
                        String s = stringArray[n2];
                        if (s.equals(DeleteColumnsDialog.this.inputText)) {
                            int cfr_ignored_0 = DeleteColumnsDialog.this.ctr;
                            break;
                        }
                        ++DeleteColumnsDialog.this.ctr;
                        ++n2;
                    }
                    DeleteColumnsDialog.this.list.select(DeleteColumnsDialog.this.ctr);
                    System.out.println(Integer.toString(DeleteColumnsDialog.this.ctr));
                } else {
                    DeleteColumnsDialog.this.list.removeAll();
                    DeleteColumnsDialog.this.list.setVisible(false);
                    ((DeleteColumnsDialog)DeleteColumnsDialog.this).list.getBounds().height = 0;
                    DeleteColumnsDialog.this.lblAreYouSure.setText("Are you sure you want to delete column " + DeleteColumnsDialog.this.inputText + "?");
                    DeleteColumnsDialog.this.getShell().setBounds(DeleteColumnsDialog.this.rect);
                }
                parent.layout();
            }
        });
        this.checkBoxDeleteMulti.setText("I want to delete multiple columns.");
        if (this.columnNames.length == 1) {
            this.checkBoxDeleteMulti.setVisible(false);
        }
        this.list = new List(container, 2818);
        this.gd_list = new GridData(4, 4, true, true, 1, 3);
        this.gd_list.heightHint = 10;
        this.list.setLayoutData((Object)this.gd_list);
        this.list.setVisible(false);
        this.list.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                DeleteColumnsDialog.this.list.select(DeleteColumnsDialog.this.ctr);
            }
        });
        return container;
    }

    public int[] getSelectedColumnIndices() {
        return this.selectionIndices;
    }

    protected void createButtonsForButtonBar(Composite parent) {
        this.createButton(parent, 0, "Yes", true);
        this.createButton(parent, 1, "No", false);
    }

    protected void okPressed() {
        this.selectionIndices = this.list.getSelectionIndices();
        if (this.selectionIndices.length > 0 && this.list.isVisible()) {
            if (MessageDialog.openQuestion((Shell)Display.getCurrent().getActiveShell(), (String)"Delete Columns", (String)"Are you sure you want to delete the selected column(s)?")) {
                this.setReturnCode(0);
                this.close();
            }
        } else {
            this.list.removeAll();
            this.selectionIndices = this.list.getSelectionIndices();
            this.setReturnCode(0);
            this.close();
        }
    }

    protected Point getInitialSize() {
        return new Point(414, 204);
    }
}

