/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.jface.dialogs.Dialog
 *  org.eclipse.jface.dialogs.IDialogConstants
 *  org.eclipse.swt.events.SelectionAdapter
 *  org.eclipse.swt.events.SelectionEvent
 *  org.eclipse.swt.events.SelectionListener
 *  org.eclipse.swt.graphics.Color
 *  org.eclipse.swt.graphics.Device
 *  org.eclipse.swt.graphics.Point
 *  org.eclipse.swt.layout.GridData
 *  org.eclipse.swt.layout.GridLayout
 *  org.eclipse.swt.widgets.Button
 *  org.eclipse.swt.widgets.Composite
 *  org.eclipse.swt.widgets.Control
 *  org.eclipse.swt.widgets.Display
 *  org.eclipse.swt.widgets.Layout
 *  org.eclipse.swt.widgets.Shell
 *  org.eclipse.swt.widgets.Table
 *  org.eclipse.swt.widgets.TableColumn
 */
package org.irri.breedingtool.graphs.managers;

import java.util.ArrayList;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.irri.breedingtool.graphs.managers.GraphTableManager;
import org.irri.breedingtool.graphs.managers.RowEntityModel;

public class GraphTableSample
extends Dialog {
    private Table table;
    private GraphTableManager tableManager;

    public GraphTableSample(Shell parentShell) {
        super(parentShell);
    }

    protected Control createDialogArea(Composite parent) {
        Composite container = (Composite)super.createDialogArea(parent);
        container.setLayout((Layout)new GridLayout(3, false));
        this.table = new Table(container, 67584);
        GridData gd_table = new GridData(4, 4, true, true, 3, 1);
        gd_table.widthHint = 432;
        this.table.setLayoutData((Object)gd_table);
        this.table.setHeaderVisible(true);
        this.table.setLinesVisible(true);
        this.table.setHeaderVisible(true);
        this.table.setLinesVisible(true);
        int i = 0;
        while (i < 6) {
            new TableColumn(this.table, 0);
            ++i;
        }
        this.table.getColumn(0).setText("Task");
        this.table.getColumn(1).setText("Progress");
        this.table.getColumn(2).setText("Color");
        this.table.getColumn(4).setText("Fill");
        this.table.getColumn(5).setText("Combo");
        ArrayList<Integer> header = new ArrayList<Integer>();
        header.add(0);
        header.add(1);
        header.add(2);
        header.add(3);
        header.add(5);
        header.add(6);
        this.tableManager = new GraphTableManager(this.table, header);
        Button btnNewButton = new Button(container, 0);
        btnNewButton.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                RowEntityModel newModel = new RowEntityModel(5, 5, 2, 10);
                RowEntityModel comboModel = new RowEntityModel(6, new String[]{"Hello", "World", "Combo Test!"});
                Object[] dataSample = new Object[]{"Test", "Test", Display.getCurrent().getSystemColor(9), "solid", newModel, comboModel};
                GraphTableSample.this.tableManager.addItem(dataSample);
            }
        });
        btnNewButton.setText("Add Fields");
        Button btnNewButton_1 = new Button(container, 0);
        btnNewButton_1.setLayoutData((Object)new GridData(0x1000000, 0x1000000, true, false, 1, 1));
        btnNewButton_1.setText("Flush Data");
        Button btnNewButton_2 = new Button(container, 0);
        btnNewButton_2.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                GraphTableSample.this.tableManager.deleteRow(GraphTableSample.this.tableManager.getDataToString().size() - 1);
            }
        });
        btnNewButton_2.setText("Remove Fields");
        Display device = Display.getCurrent();
        new Color((Device)device, 255, 0, 0);
        Display display = Display.getCurrent();
        RowEntityModel comboModel = new RowEntityModel(6, new String[]{"Hello", "World", "Combo Test!"});
        Object[] dataSample = new Object[]{"Test", "Test", display.getSystemColor(13), "solid", 1, comboModel};
        this.tableManager.addItem(dataSample);
        dataSample = new Object[]{"Test", "Test", display.getSystemColor(9), "solid", 0, comboModel};
        this.tableManager.addItem(dataSample);
        dataSample = new Object[]{"Test", "Test", display.getSystemColor(12), "solid", 2, comboModel};
        this.tableManager.addItem(dataSample);
        dataSample = new Object[]{"Test", "Test", display.getSystemColor(5), "solid", 5, comboModel};
        this.tableManager.addItem(dataSample);
        this.table.getColumn(0).pack();
        this.table.getColumn(1).setWidth(128);
        this.table.getColumn(2).setWidth(128);
        this.table.getColumn(3).setWidth(128);
        this.table.getColumn(4).setWidth(128);
        this.table.getColumn(5).setWidth(128);
        return container;
    }

    protected void okPressed() {
        ArrayList<String[]> data = this.tableManager.getDataToString();
        int i = 0;
        while (i < data.size()) {
            System.out.println(this.arrToString(data.get(i)));
            ++i;
        }
    }

    public String arrToString(String[] a) {
        StringBuffer result = new StringBuffer();
        int i = 0;
        while (i < a.length) {
            result.append(a[i]);
            ++i;
        }
        String mynewstring = result.toString();
        return mynewstring;
    }

    protected void createButtonsForButtonBar(Composite parent) {
        this.createButton(parent, 0, IDialogConstants.OK_LABEL, true);
        this.createButton(parent, 1, IDialogConstants.CANCEL_LABEL, false);
    }

    protected Point getInitialSize() {
        return new Point(450, 300);
    }
}

