/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.swt.custom.CCombo
 *  org.eclipse.swt.custom.TableEditor
 *  org.eclipse.swt.events.SelectionEvent
 *  org.eclipse.swt.events.SelectionListener
 *  org.eclipse.swt.graphics.Color
 *  org.eclipse.swt.graphics.Device
 *  org.eclipse.swt.graphics.Drawable
 *  org.eclipse.swt.graphics.GC
 *  org.eclipse.swt.graphics.Image
 *  org.eclipse.swt.graphics.RGB
 *  org.eclipse.swt.graphics.Rectangle
 *  org.eclipse.swt.widgets.Button
 *  org.eclipse.swt.widgets.ColorDialog
 *  org.eclipse.swt.widgets.Composite
 *  org.eclipse.swt.widgets.Control
 *  org.eclipse.swt.widgets.Display
 *  org.eclipse.swt.widgets.Event
 *  org.eclipse.swt.widgets.Listener
 *  org.eclipse.swt.widgets.Spinner
 *  org.eclipse.swt.widgets.Table
 *  org.eclipse.swt.widgets.TableItem
 *  org.eclipse.swt.widgets.Text
 */
package org.irri.breedingtool.graphs.managers;

import java.util.ArrayList;
import java.util.HashMap;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Drawable;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.ColorDialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.ResourceManager;
import org.irri.breedingtool.graphs.dialog.ChooseFillPatternDialog;
import org.irri.breedingtool.graphs.dialog.ChoosePlotSymbolsDialog;
import org.irri.breedingtool.graphs.managers.RowEntityModel;

public class GraphTableManager {
    public static final int ROW_STATIC_TEXT = 0;
    public static final int ROW_TEXT = 1;
    public static final int ROW_COLOR_PICKER = 2;
    public static final int ROW_FILL_TYPE = 3;
    public static final int ROW_NUMERIC = 4;
    public static final int ROW_SPINNER = 5;
    public static final int ROW_COMBO = 6;
    public static final int ROW_SYMBOL = 7;
    public int ROW_HEIGHT = 18;
    public int SYMBOL_HEIGHT = 0;
    public HashMap<String, Integer> tableMap = new HashMap();
    private ArrayList<TableEditor[]> tableEditors = new ArrayList();
    private int TABLE_INDEX = -1;
    private Table mainTable;
    private ArrayList<Integer> ROW_TYPES = new ArrayList();
    private Object[] rowData = new Object[1];
    private ArrayList<Object[]> tableData = new ArrayList();

    public GraphTableManager(Table table, ArrayList<Integer> rowType) {
        this.mainTable = table;
        this.ROW_TYPES = rowType;
        this.rowData = new Object[this.mainTable.getColumnCount()];
        this.mainTable.addListener(41, new Listener(){

            public void handleEvent(Event event) {
                event.height = GraphTableManager.this.ROW_HEIGHT;
            }
        });
    }

    public void setAllEnabled(boolean flag) {
        int i = 0;
        while (i < this.tableData.size()) {
            Object[] objectRow;
            Object[] objectArray = objectRow = this.tableData.get(i);
            int n = objectRow.length;
            int n2 = 0;
            while (n2 < n) {
                Object obj = objectArray[n2];
                if (obj instanceof Text) {
                    ((Text)obj).setEnabled(flag);
                } else if (obj instanceof Spinner) {
                    ((Spinner)obj).setEnabled(flag);
                } else if (obj instanceof Button) {
                    ((Button)obj).setEnabled(flag);
                }
                ++n2;
            }
            ++i;
        }
    }

    public void setColumnsDisabled(boolean flag, int ... columns) {
        int i = 0;
        while (i < this.tableData.size()) {
            Object[] objectRow = this.tableData.get(i);
            int[] nArray = columns;
            int n = columns.length;
            int n2 = 0;
            while (n2 < n) {
                int x = nArray[n2];
                if (objectRow[x] instanceof Text) {
                    ((Text)objectRow[x]).setEnabled(flag);
                } else if (objectRow[x] instanceof Spinner) {
                    ((Spinner)objectRow[x]).setEnabled(flag);
                } else if (objectRow[x] instanceof Button) {
                    ((Button)objectRow[x]).setEnabled(flag);
                }
                ++n2;
            }
            ++i;
        }
    }

    public void removeAll() {
        int i = this.mainTable.getItemCount() - 1;
        while (i >= 0) {
            this.deleteRow(i);
            --i;
        }
    }

    public void setTableIndex(int index) {
        this.TABLE_INDEX = index;
    }

    public int getTableIndex() {
        return this.TABLE_INDEX;
    }

    public void setColumnWidths(int width) {
        int i = 0;
        while (i < this.mainTable.getColumnCount()) {
            this.mainTable.getColumn(i).setWidth(width);
            ++i;
        }
    }

    public Object[] getRowObjects(int row) {
        return this.tableData.get(row);
    }

    public void addItem(Object[] items) {
        TableItem newItem = new TableItem(this.mainTable, 0);
        Object[] newRowItem = new Object[this.ROW_TYPES.size()];
        TableEditor[] newEditors = new TableEditor[this.ROW_TYPES.size()];
        if (this.TABLE_INDEX != -1) {
            this.tableMap.put(String.valueOf(items[this.TABLE_INDEX]), this.tableData.size());
        }
        int i = 0;
        while (i < this.ROW_TYPES.size()) {
            int TYPE = this.ROW_TYPES.get(i);
            switch (TYPE) {
                case 0: {
                    newItem.setText(i, (String)items[i]);
                    newRowItem[i] = items[i];
                    newEditors[i] = null;
                    break;
                }
                case 1: {
                    RowEntityModel rowModel;
                    Text textEditor = new Text((Composite)this.mainTable, 0);
                    if (items[i] instanceof RowEntityModel) {
                        rowModel = (RowEntityModel)items[i];
                        textEditor.setText((String)rowModel.value);
                        if (rowModel.modifyListener != null) {
                            textEditor.addModifyListener(rowModel.modifyListener);
                        }
                        if (rowModel.keyListener != null) {
                            textEditor.addKeyListener(rowModel.keyListener);
                        }
                    } else {
                        textEditor.setText((String)items[i]);
                    }
                    TableEditor editor = new TableEditor(this.mainTable);
                    editor.grabVertical = true;
                    editor.grabHorizontal = true;
                    editor.setEditor((Control)textEditor, newItem, i);
                    newRowItem[i] = textEditor;
                    newEditors[i] = editor;
                    break;
                }
                case 7: {
                    String defaultSymbolSelected;
                    RowEntityModel rowModel;
                    Object defaultSelectionListener = null;
                    if (items[i] instanceof RowEntityModel) {
                        rowModel = (RowEntityModel)items[i];
                        defaultSymbolSelected = (String)rowModel.value;
                        if (rowModel.selectionListener != null) {
                            defaultSelectionListener = rowModel.selectionListener;
                        }
                    } else {
                        defaultSymbolSelected = (String)items[i];
                        int rowCol = i;
                        defaultSelectionListener = new SelectionListener(){

                            public void widgetSelected(SelectionEvent e) {
                                System.out.println(Integer.getInteger((String)((Button)e.widget).getData("SELECTED")));
                                ChoosePlotSymbolsDialog fillDlg = new ChoosePlotSymbolsDialog(Display.getCurrent().getActiveShell(), Integer.parseInt((String)((Button)e.widget).getData("SELECTED")));
                                fillDlg.open();
                                if (fillDlg.getReturnCode() == 0) {
                                    ((Button)e.widget).setImage(ResourceManager.getPluginImage("Star", "icons/plotsymbol" + fillDlg.getChosenSymbol() + ".png"));
                                    ((Button)e.widget).setImage(GraphTableManager.this.createImage(Display.getCurrent(), new Color((Device)Display.getCurrent(), new RGB(228, 237, 216)), ResourceManager.getPluginImage("Star", "icons/plotsymbol" + fillDlg.getChosenSymbol() + ".png"), GraphTableManager.this.mainTable.getColumn(rowCol).getWidth(), GraphTableManager.this.mainTable.getColumn(rowCol).getWidth()));
                                    ((Button)e.widget).setData("SELECTED", (Object)fillDlg.getChosenSymbol());
                                }
                            }

                            public void widgetDefaultSelected(SelectionEvent e) {
                            }
                        };
                    }
                    Button button = new Button((Composite)this.mainTable, 0);
                    TableEditor editor = new TableEditor(this.mainTable);
                    editor.grabVertical = true;
                    editor.grabHorizontal = true;
                    editor.setEditor((Control)button, newItem, i);
                    newEditors[i] = editor;
                    button.setImage(this.createImage(Display.getCurrent(), new Color((Device)Display.getCurrent(), new RGB(228, 237, 216)), ResourceManager.getPluginImage("Star", "icons/plotsymbol" + defaultSymbolSelected + ".png"), this.mainTable.getColumn(i).getWidth(), this.mainTable.getColumn(i).getWidth()));
                    button.setData("SELECTED", (Object)defaultSymbolSelected);
                    button.addSelectionListener(defaultSelectionListener);
                    newRowItem[i] = button;
                    break;
                }
                case 3: {
                    String defaultImageSelected;
                    RowEntityModel rowModel;
                    Object defaultSelectionListener = null;
                    if (items[i] instanceof RowEntityModel) {
                        rowModel = (RowEntityModel)items[i];
                        defaultImageSelected = (String)rowModel.value;
                        if (rowModel.selectionListener != null) {
                            defaultSelectionListener = rowModel.selectionListener;
                        }
                    } else {
                        defaultImageSelected = (String)items[i];
                        defaultSelectionListener = new SelectionListener(){

                            public void widgetSelected(SelectionEvent e) {
                                ChooseFillPatternDialog fillDlg = new ChooseFillPatternDialog(Display.getCurrent().getActiveShell(), "");
                                fillDlg.open();
                                if (fillDlg.getReturnCode() == 0) {
                                    ((Button)e.widget).setImage(ResourceManager.getPluginImage("Star", "icons/" + fillDlg.getChosenPattern() + ".png"));
                                    ((Button)e.widget).setData("SELECTED", (Object)fillDlg.getChosenPattern());
                                }
                            }

                            public void widgetDefaultSelected(SelectionEvent e) {
                            }
                        };
                    }
                    Button button = new Button((Composite)this.mainTable, 0);
                    TableEditor editor = new TableEditor(this.mainTable);
                    editor.grabVertical = true;
                    editor.grabHorizontal = true;
                    editor.setEditor((Control)button, newItem, i);
                    newEditors[i] = editor;
                    button.setImage(ResourceManager.getPluginImage("Star", "icons/" + defaultImageSelected + ".png"));
                    button.setData("SELECTED", (Object)defaultImageSelected);
                    button.addSelectionListener(defaultSelectionListener);
                    newRowItem[i] = button;
                    break;
                }
                case 2: {
                    Color defaultColorSelected;
                    RowEntityModel rowModel;
                    Object defaultSelectionListener = null;
                    if (items[i] instanceof RowEntityModel) {
                        rowModel = (RowEntityModel)items[i];
                        defaultColorSelected = (Color)rowModel.value;
                        if (rowModel.selectionListener != null) {
                            defaultSelectionListener = rowModel.selectionListener;
                        }
                    } else {
                        defaultColorSelected = (Color)items[i];
                        defaultSelectionListener = new SelectionListener(){

                            public void widgetSelected(SelectionEvent e) {
                                ColorDialog dlg = new ColorDialog(Display.getCurrent().getActiveShell());
                                dlg.setText("Choose a Color");
                                dlg.setRGB((RGB)e.widget.getData("SELECTED"));
                                RGB rgb = dlg.open();
                                if (rgb != null) {
                                    Color color = new Color((Device)Display.getCurrent(), rgb);
                                    ((Button)e.widget).setImage(GraphTableManager.this.createImage(Display.getCurrent(), color, 100));
                                    ((Button)e.widget).setData("SELECTED", (Object)rgb);
                                }
                            }

                            public void widgetDefaultSelected(SelectionEvent e) {
                            }
                        };
                    }
                    Button button = new Button((Composite)this.mainTable, 0);
                    TableEditor editor = new TableEditor(this.mainTable);
                    editor.grabVertical = true;
                    editor.grabHorizontal = true;
                    editor.setEditor((Control)button, newItem, i);
                    newEditors[i] = editor;
                    button.setImage(this.createImage(Display.getCurrent(), defaultColorSelected, 100));
                    button.setData("SELECTED", (Object)defaultColorSelected.getRGB());
                    button.addSelectionListener(defaultSelectionListener);
                    newRowItem[i] = button;
                    break;
                }
                case 5: {
                    RowEntityModel rowModel;
                    Spinner spinner = new Spinner((Composite)this.mainTable, 0);
                    if (items[i] instanceof RowEntityModel) {
                        rowModel = (RowEntityModel)items[i];
                        spinner.setSelection(((Integer)rowModel.value).intValue());
                        spinner.setMaximum(rowModel.max_value);
                        spinner.setMinimum(rowModel.min_value);
                        spinner.setDigits(rowModel.decimal);
                        if (rowModel.modifyListener != null) {
                            spinner.addModifyListener(rowModel.modifyListener);
                        }
                        if (rowModel.keyListener != null) {
                            spinner.addKeyListener(rowModel.keyListener);
                        }
                    } else {
                        spinner.setSelection(((Integer)items[i]).intValue());
                    }
                    TableEditor editor = new TableEditor(this.mainTable);
                    editor.grabVertical = true;
                    editor.grabHorizontal = true;
                    editor.setEditor((Control)spinner, newItem, i);
                    newRowItem[i] = spinner;
                    newEditors[i] = editor;
                    break;
                }
                case 6: {
                    RowEntityModel rowModel;
                    CCombo combo = new CCombo((Composite)this.mainTable, 0);
                    if (items[i] instanceof RowEntityModel) {
                        rowModel = (RowEntityModel)items[i];
                        combo.setItems((String[])rowModel.value);
                        if (rowModel.modifyListener != null) {
                            combo.addModifyListener(rowModel.modifyListener);
                        }
                        if (rowModel.selectionListener != null) {
                            combo.addSelectionListener(rowModel.selectionListener);
                        }
                    } else {
                        combo.setText((String)items[i]);
                    }
                    TableEditor editor = new TableEditor(this.mainTable);
                    editor.grabVertical = true;
                    editor.grabHorizontal = true;
                    editor.setEditor((Control)combo, newItem, i);
                    newRowItem[i] = combo;
                    newEditors[i] = editor;
                }
            }
            ++i;
        }
        this.tableData.add(newRowItem);
        this.tableEditors.add(newEditors);
    }

    public void deleteRow(int rowNum) {
        TableEditor[] tableEditorArray = this.tableEditors.get(rowNum);
        int n = tableEditorArray.length;
        int n2 = 0;
        while (n2 < n) {
            TableEditor editor = tableEditorArray[n2];
            if (editor != null) {
                editor.getEditor().dispose();
            }
            ++n2;
        }
        this.tableEditors.remove(rowNum);
        this.mainTable.remove(rowNum);
        this.tableData.remove(rowNum);
    }

    public ArrayList<String[]> getDataToString() {
        ArrayList<String[]> returnVal = new ArrayList<String[]>();
        int i = 0;
        while (i < this.tableData.size()) {
            Object[] objectRow = this.tableData.get(i);
            String[] rowData = new String[this.ROW_TYPES.size()];
            int j = 0;
            while (j < this.ROW_TYPES.size()) {
                Object currObjectRow = objectRow[j];
                int TYPE = this.ROW_TYPES.get(j);
                switch (TYPE) {
                    case 0: {
                        rowData[j] = (String)currObjectRow;
                        break;
                    }
                    case 1: {
                        rowData[j] = ((Text)currObjectRow).getText();
                        break;
                    }
                    case 3: {
                        rowData[j] = ((Button)currObjectRow).getData("SELECTED").toString();
                        break;
                    }
                    case 2: {
                        rowData[j] = ((RGB)((Button)currObjectRow).getData("SELECTED")).toString();
                        break;
                    }
                    case 5: {
                        if (((Spinner)currObjectRow).getDigits() <= 0) {
                            rowData[j] = String.valueOf(((Spinner)currObjectRow).getSelection());
                            break;
                        }
                        Double sprVal = ((Spinner)currObjectRow).getSelection();
                        Double digits = (double)((Spinner)currObjectRow).getDigits() / sprVal;
                        rowData[j] = String.valueOf(sprVal * digits);
                        break;
                    }
                    case 6: {
                        rowData[j] = String.valueOf(((CCombo)currObjectRow).getText());
                        break;
                    }
                    case 7: {
                        rowData[j] = ((Button)currObjectRow).getData("SELECTED").toString();
                    }
                }
                ++j;
            }
            returnVal.add(rowData);
            ++i;
        }
        return returnVal;
    }

    public ArrayList<Object[]> getTableRowsObject() {
        ArrayList<Object[]> returnVal = new ArrayList<Object[]>();
        int i = 0;
        while (i < this.tableData.size()) {
            Object[] objectRow = this.tableData.get(i);
            Object[] rowData = new Object[this.ROW_TYPES.size()];
            int j = 0;
            while (j < this.ROW_TYPES.size()) {
                Object currObjectRow;
                rowData[j] = currObjectRow = objectRow[j];
                ++j;
            }
            returnVal.add(rowData);
            ++i;
        }
        return returnVal;
    }

    private Image createImage(Display display, Color color, int x) {
        Image image = new Image((Device)display, x, x);
        GC gc = new GC((Drawable)image);
        gc.setBackground(color);
        Rectangle rect = image.getBounds();
        gc.fillRectangle(rect);
        if (color.equals((Object)display.getSystemColor(2))) {
            gc.setForeground(display.getSystemColor(1));
        }
        gc.drawRectangle(rect.x, rect.y, rect.width - 1, rect.height - 1);
        gc.dispose();
        return image;
    }

    private Image createImage(Display display, Color color, Image overlayImage, int x, int columnWidth) {
        Image image = new Image((Device)display, x, x);
        GC gc = new GC((Drawable)image);
        gc.setBackground(color);
        Rectangle rect = image.getBounds();
        gc.fillRectangle(rect);
        if (color.equals((Object)display.getSystemColor(2))) {
            gc.setForeground(display.getSystemColor(1));
        }
        gc.drawRectangle(rect.x, rect.y, rect.width - 1, rect.height - 1);
        int heightAuto = this.SYMBOL_HEIGHT != 0 ? this.SYMBOL_HEIGHT : this.ROW_HEIGHT / 2;
        System.out.println(columnWidth);
        gc.drawImage(overlayImage, x / 2 / 2, heightAuto);
        gc.dispose();
        return image;
    }
}

