/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.swt.events.SelectionAdapter
 *  org.eclipse.swt.events.SelectionEvent
 *  org.eclipse.swt.events.SelectionListener
 *  org.eclipse.swt.widgets.Button
 *  org.eclipse.swt.widgets.Combo
 *  org.eclipse.swt.widgets.Composite
 *  org.eclipse.swt.widgets.Control
 *  org.eclipse.swt.widgets.Dialog
 *  org.eclipse.swt.widgets.Display
 *  org.eclipse.swt.widgets.Label
 *  org.eclipse.swt.widgets.List
 *  org.eclipse.swt.widgets.Shell
 *  org.eclipse.swt.widgets.Spinner
 *  org.eclipse.swt.widgets.TabFolder
 *  org.eclipse.swt.widgets.TabItem
 *  org.eclipse.swt.widgets.Table
 *  org.eclipse.swt.widgets.TableColumn
 *  org.eclipse.swt.widgets.TableItem
 *  org.eclipse.swt.widgets.Text
 */
package org.irri.breedingtool.graphs.managers;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

public class BarGraph
extends Dialog {
    protected Object result;
    protected Shell shlBarChart;
    private Text text;
    private Text text_1;
    private Text text_2;
    private Text text_18;
    private Text text_19;
    private Text text_20;
    private Table table;
    private Table table_1;
    private Table table_2;
    private Table table_4;
    private Table table_3;
    private Text text_6;
    private Text text_9;
    private Text text_11;
    private Text text_12;
    private Text text_13;
    private Text text_14;

    public BarGraph(Shell parent, int style) {
        super(parent, style);
        this.setText("SWT Dialog");
    }

    public Object open() {
        this.createContents();
        this.shlBarChart.open();
        this.shlBarChart.layout();
        Display display = this.getParent().getDisplay();
        while (!this.shlBarChart.isDisposed()) {
            if (display.readAndDispatch()) continue;
            display.sleep();
        }
        return this.result;
    }

    private void createContents() {
        this.shlBarChart = new Shell(this.getParent(), this.getStyle());
        this.shlBarChart.setSize(483, 490);
        this.shlBarChart.setText("Bar Graph: [File name]");
        TabFolder tabFolder = new TabFolder((Composite)this.shlBarChart, 0);
        tabFolder.setBounds(10, 10, 454, 407);
        TabItem tbtmNewItem = new TabItem(tabFolder, 0);
        tbtmNewItem.setText("Variable Definition");
        Composite composite = new Composite((Composite)tabFolder, 0);
        tbtmNewItem.setControl((Control)composite);
        Label lblNumericData = new Label(composite, 0);
        lblNumericData.setBounds(33, 81, 125, 17);
        lblNumericData.setText("Numeric Variable(s):");
        List list = new List(composite, 2048);
        list.setBounds(33, 104, 150, 80);
        Label lblAvailableFactors = new Label(composite, 0);
        lblAvailableFactors.setBounds(33, 230, 57, 17);
        lblAvailableFactors.setText("Factor(s):");
        List list_1 = new List(composite, 2048);
        list_1.setBounds(33, 251, 150, 80);
        Label lblCategoryAxis = new Label(composite, 0);
        lblCategoryAxis.setBounds(263, 230, 102, 17);
        lblCategoryAxis.setText("Categorize by:");
        Button btnAdd_1 = new Button(composite, 0);
        btnAdd_1.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
            }
        });
        btnAdd_1.setBounds(189, 251, 68, 23);
        btnAdd_1.setText("Add");
        this.text_1 = new Text(composite, 2048);
        this.text_1.setBounds(263, 251, 150, 23);
        Label lblDefineGroupBy = new Label(composite, 0);
        lblDefineGroupBy.setText("Group by:");
        lblDefineGroupBy.setBounds(263, 286, 113, 17);
        Button btnAdd_2 = new Button(composite, 0);
        btnAdd_2.setText("Add");
        btnAdd_2.setBounds(189, 308, 68, 23);
        this.text_2 = new Text(composite, 2048);
        this.text_2.setBounds(263, 308, 150, 23);
        Label lblInputFileContains = new Label(composite, 0);
        lblInputFileContains.setBounds(33, 10, 113, 17);
        lblInputFileContains.setText("Input File Contains:");
        Button btnRawData = new Button(composite, 16);
        btnRawData.setSelection(true);
        btnRawData.setBounds(155, 10, 83, 16);
        btnRawData.setText("Raw Data");
        Button btnSummaryStatistics = new Button(composite, 16);
        btnSummaryStatistics.setText("Summary Statistics");
        btnSummaryStatistics.setBounds(243, 10, 135, 16);
        Label lblVariable = new Label(composite, 0);
        lblVariable.setBounds(263, 81, 68, 17);
        lblVariable.setText("Variable(s):");
        Button btnAdd = new Button(composite, 0);
        btnAdd.setBounds(189, 104, 68, 23);
        btnAdd.setText("Add");
        this.text = new Text(composite, 2048);
        this.text.setBounds(263, 104, 150, 80);
        Label lblBarRepresent = new Label(composite, 0);
        lblBarRepresent.setBounds(33, 45, 95, 17);
        lblBarRepresent.setText("Bars Represent:");
        Combo combo = new Combo(composite, 0);
        combo.setBounds(155, 40, 143, 24);
        combo.setItems(new String[]{"Frequency", "Mean", "Median", "Sum"});
        combo.select(1);
        Button btnToFactor = new Button(composite, 0);
        btnToFactor.setBounds(93, 190, 90, 26);
        btnToFactor.setText("Set To Factor");
        TabItem tbtmDisplay = new TabItem(tabFolder, 0);
        tbtmDisplay.setText("Display Options");
        Composite composite_2 = new Composite((Composite)tabFolder, 0);
        tbtmDisplay.setControl((Control)composite_2);
        Label label_2 = new Label(composite_2, 0);
        label_2.setText("Main Title:");
        label_2.setBounds(10, 12, 76, 17);
        this.text_6 = new Text(composite_2, 2048);
        this.text_6.setBounds(140, 10, 277, 23);
        Label label_7 = new Label(composite_2, 0);
        label_7.setText("Category Axis Label:");
        label_7.setBounds(10, 44, 120, 17);
        Label lblLabel = new Label(composite_2, 0);
        lblLabel.setText("Label:");
        lblLabel.setBounds(87, 122, 34, 17);
        this.text_9 = new Text(composite_2, 2048);
        this.text_9.setBounds(140, 42, 277, 23);
        this.text_11 = new Text(composite_2, 2048);
        this.text_11.setBounds(140, 119, 277, 23);
        Label label_9 = new Label(composite_2, 0);
        label_9.setText("Lower Limit:");
        label_9.setBounds(140, 151, 76, 17);
        this.text_12 = new Text(composite_2, 2048);
        this.text_12.setText("Auto");
        this.text_12.setBounds(222, 148, 50, 23);
        Label label_10 = new Label(composite_2, 0);
        label_10.setText("Upper Limit:");
        label_10.setBounds(285, 151, 76, 17);
        this.text_13 = new Text(composite_2, 2048);
        this.text_13.setText("Auto");
        this.text_13.setBounds(367, 148, 50, 23);
        Button button_7 = new Button(composite_2, 32);
        button_7.setText("Show legend");
        button_7.setBounds(10, 202, 118, 19);
        Label label_11 = new Label(composite_2, 0);
        label_11.setText("Position:");
        label_11.setBounds(32, 230, 50, 19);
        Combo combo_1 = new Combo(composite_2, 0);
        combo_1.setItems(new String[]{"bottom", "bottom-left", "bottom-right", "center", "left", "right", "top", "top-left", "top-right"});
        combo_1.setBounds(87, 227, 105, 23);
        combo_1.select(2);
        this.text_14 = new Text(composite_2, 2048);
        this.text_14.setBounds(87, 257, 190, 22);
        Label label_12 = new Label(composite_2, 0);
        label_12.setText("Title:");
        label_12.setBounds(32, 258, 34, 19);
        Button button_12 = new Button(composite_2, 32);
        button_12.setText("Display multiple graphs in a page");
        button_12.setBounds(10, 279, 210, 19);
        Label label_13 = new Label(composite_2, 0);
        label_13.setText("Number of rows:");
        label_13.setBounds(31, 306, 104, 19);
        Label label_14 = new Label(composite_2, 0);
        label_14.setText("Number of columns:");
        label_14.setBounds(217, 306, 115, 19);
        Spinner spinner = new Spinner(composite_2, 2048);
        spinner.setMaximum(5);
        spinner.setMinimum(1);
        spinner.setSelection(1);
        spinner.setBounds(138, 304, 52, 23);
        Spinner spinner_1 = new Spinner(composite_2, 2048);
        spinner_1.setMaximum(5);
        spinner_1.setMinimum(1);
        spinner_1.setSelection(1);
        spinner_1.setBounds(338, 304, 52, 23);
        Label label_15 = new Label(composite_2, 0);
        label_15.setText("Orientation:");
        label_15.setBounds(30, 338, 75, 19);
        Combo combo_3 = new Combo(composite_2, 0);
        combo_3.setItems(new String[]{"Left-to-right", "Top-to-bottom"});
        combo_3.setBounds(137, 335, 115, 23);
        combo_3.setText("Top-to-bottom");
        combo_3.select(1);
        Button button_13 = new Button(composite_2, 32);
        button_13.setText("Draw box around plot");
        button_13.setBounds(10, 177, 155, 19);
        Button button_15 = new Button(composite_2, 32);
        button_15.setText("Display variable names");
        button_15.setBounds(140, 70, 155, 19);
        Label lblRange = new Label(composite_2, 0);
        lblRange.setText("Range:");
        lblRange.setBounds(87, 151, 43, 17);
        Label lblValueAxis = new Label(composite_2, 0);
        lblValueAxis.setText("Value Axis:");
        lblValueAxis.setBounds(10, 94, 120, 17);
        TabItem tbtmBars = new TabItem(tabFolder, 0);
        tbtmBars.setText("Other Options");
        Composite composite_5 = new Composite((Composite)tabFolder, 0);
        tbtmBars.setControl((Control)composite_5);
        Button btnDisplayErrorBars = new Button(composite_5, 32);
        btnDisplayErrorBars.setText("Display Error Bars");
        btnDisplayErrorBars.setBounds(254, 44, 155, 19);
        Composite group_3 = new Composite(composite_5, 0);
        group_3.setBounds(277, 91, 145, 191);
        Button btnConfidenceInterval = new Button(group_3, 16);
        btnConfidenceInterval.setText("Confidence Interval");
        btnConfidenceInterval.setSelection(true);
        btnConfidenceInterval.setGrayed(true);
        btnConfidenceInterval.setBounds(10, 10, 155, 19);
        this.text_18 = new Text(group_3, 2048);
        this.text_18.setText("95");
        this.text_18.setEditable(false);
        this.text_18.setBounds(104, 33, 30, 23);
        Button button_8 = new Button(group_3, 16);
        button_8.setText("Standard Error");
        button_8.setBounds(10, 65, 124, 19);
        Button button_9 = new Button(group_3, 16);
        button_9.setText("Standard Deviation");
        button_9.setBounds(10, 122, 135, 19);
        Label lblLevel = new Label(group_3, 0);
        lblLevel.setText("Level (%):");
        lblLevel.setBounds(35, 35, 61, 17);
        Label lblMultiplier_1 = new Label(group_3, 0);
        lblMultiplier_1.setText("Multiplier:");
        lblMultiplier_1.setBounds(35, 90, 61, 17);
        this.text_19 = new Text(group_3, 2048);
        this.text_19.setText("1");
        this.text_19.setEditable(false);
        this.text_19.setBounds(104, 85, 30, 23);
        Label lblMultiplier = new Label(group_3, 0);
        lblMultiplier.setText("Multiplier:");
        lblMultiplier.setBounds(35, 145, 61, 17);
        this.text_20 = new Text(group_3, 2048);
        this.text_20.setText("1");
        this.text_20.setEditable(false);
        this.text_20.setBounds(104, 144, 30, 23);
        Composite composite_4 = new Composite(composite_5, 0);
        composite_4.setBounds(10, 35, 238, 264);
        Composite group = new Composite(composite_4, 0);
        group.setBounds(10, 10, 228, 43);
        Button button_1 = new Button(group, 16);
        button_1.setText("Vertical");
        button_1.setSelection(true);
        button_1.setBounds(10, 21, 82, 19);
        Button button_2 = new Button(group, 16);
        button_2.setText("Horizontal");
        button_2.setBounds(109, 21, 104, 19);
        Label lblOrientation = new Label(group, 0);
        lblOrientation.setBounds(0, -1, 92, 16);
        lblOrientation.setText("Orientation:");
        Composite group_1 = new Composite(composite_4, 0);
        group_1.setBounds(10, 69, 228, 43);
        Button button_3 = new Button(group_1, 16);
        button_3.setText("Clustered");
        button_3.setSelection(true);
        button_3.setBounds(10, 21, 91, 19);
        Button btnStacked = new Button(group_1, 16);
        btnStacked.setText("Stacked");
        btnStacked.setBounds(109, 21, 72, 19);
        Label lblStyle = new Label(group_1, 0);
        lblStyle.setBounds(0, 0, 59, 16);
        lblStyle.setText("Style:");
        Composite group_2 = new Composite(composite_4, 0);
        group_2.setBounds(10, 130, 228, 120);
        Label lblColor = new Label(group_2, 0);
        lblColor.setBounds(0, 0, 82, 16);
        lblColor.setText("Color and Fill:");
        this.table = new Table(group_2, 67584);
        this.table.setBounds(10, 22, 66, 88);
        this.table.setHeaderVisible(true);
        this.table.setLinesVisible(true);
        TableColumn tblclmnVariable = new TableColumn(this.table, 0);
        tblclmnVariable.setWidth(62);
        tblclmnVariable.setText("Variable");
        TableItem tableItem = new TableItem(this.table, 0);
        tableItem.setText(new String[]{"GrnYld"});
        TableItem tableItem_1 = new TableItem(this.table, 0);
        tableItem_1.setBackground(SWTResourceManager.getColor(1));
        tableItem_1.setText(new String[]{"PlHt"});
        this.table_1 = new Table(group_2, 67584);
        this.table_1.setLinesVisible(true);
        this.table_1.setHeaderVisible(true);
        this.table_1.setBounds(73, 22, 52, 88);
        TableColumn tblclmnColor = new TableColumn(this.table_1, 0);
        tblclmnColor.setText("Color");
        tblclmnColor.setWidth(47);
        TableItem tableItem_2 = new TableItem(this.table_1, 0);
        tableItem_2.setText(new String[0]);
        tableItem_2.setBackground(SWTResourceManager.getColor(16));
        TableItem tableItem_3 = new TableItem(this.table_1, 0);
        tableItem_3.setText(new String[0]);
        tableItem_3.setBackground(SWTResourceManager.getColor(15));
        Button btnChange = new Button(group_2, 0);
        btnChange.setBounds(125, 48, 20, 18);
        btnChange.setText("...");
        Button button = new Button(group_2, 0);
        button.setText("...");
        button.setBounds(125, 67, 20, 18);
        this.table_2 = new Table(group_2, 67584);
        this.table_2.setBounds(125, 22, 20, 88);
        this.table_2.setHeaderVisible(true);
        this.table_2.setLinesVisible(true);
        Button button_5 = new Button(group_2, 0);
        button_5.setText("...");
        button_5.setBounds(187, 48, 20, 18);
        Button button_11 = new Button(group_2, 0);
        button_11.setText("...");
        button_11.setBounds(187, 67, 20, 18);
        this.table_4 = new Table(group_2, 67584);
        this.table_4.setLinesVisible(true);
        this.table_4.setHeaderVisible(true);
        this.table_4.setBounds(187, 22, 20, 88);
        this.table_3 = new Table(group_2, 67584);
        this.table_3.setLinesVisible(true);
        this.table_3.setHeaderVisible(true);
        this.table_3.setBounds(140, 22, 52, 88);
        TableColumn tblclmnFill = new TableColumn(this.table_3, 0x1000000);
        tblclmnFill.setWidth(47);
        tblclmnFill.setText("Fill");
        TableItem tableItem_5 = new TableItem(this.table_3, 0);
        tableItem_5.setText(new String[0]);
        tableItem_5.setBackground(SWTResourceManager.getColor(1));
        TableItem tableItem_4 = new TableItem(this.table_3, 0);
        tableItem_4.setText("/ / / /");
        tableItem_4.setText(new String[0]);
        tableItem_4.setBackground(SWTResourceManager.getColor(1));
        Label lblConfigureBars = new Label(composite_5, 0);
        lblConfigureBars.setBounds(10, 11, 116, 16);
        lblConfigureBars.setText("CONFIGURE BARS:");
        Label lblErrorBarsRepresent = new Label(composite_5, 0);
        lblErrorBarsRepresent.setBounds(277, 74, 88, 16);
        lblErrorBarsRepresent.setText("ERROR BARS:");
        Composite composite_3 = new Composite((Composite)this.shlBarChart, 0);
        composite_3.setBounds(200, 425, 264, 27);
        Button btnReset = new Button(composite_3, 0);
        btnReset.setBounds(90, 0, 84, 27);
        btnReset.setText("Reset");
        Button btnOk = new Button(composite_3, 0);
        btnOk.setBounds(0, 0, 84, 27);
        btnOk.setText("Ok");
        Button btnCancel = new Button(composite_3, 0);
        btnCancel.setBounds(180, 0, 84, 27);
        btnCancel.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
            }
        });
        btnCancel.setText("Cancel");
    }
}

