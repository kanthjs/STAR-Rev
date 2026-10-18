/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.swt.widgets.Table
 *  org.eclipse.swt.widgets.TableItem
 */
package org.irri.breedingtool.utility;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

public class TableUtility {
    public static String[] saveTableToCsv(Table[] table, String folderPath) {
        ArrayList<String> returnPaths = new ArrayList<String>();
        Table[] tableArray = table;
        int n = table.length;
        int n2 = 0;
        while (n2 < n) {
            Table data = tableArray[n2];
            String filePath = String.valueOf(folderPath) + File.separator + Math.random() + "tmp.csv";
            TableUtility.saveTableToCsv(data, filePath);
            returnPaths.add(filePath);
            ++n2;
        }
        return returnPaths.toArray(new String[returnPaths.size()]);
    }

    public static void saveTableToCsv(Table table, String filePath) {
        ArrayList<String> data = new ArrayList<String>();
        String[] columns = new String[table.getColumnCount()];
        int i = 0;
        while (i < table.getColumnCount()) {
            columns[i] = table.getColumn(i).getText();
            ++i;
        }
        data.add(TableUtility.implode(columns, ","));
        i = 0;
        while (i < table.getItemCount()) {
            data.add(TableUtility.implode(table.getItem(i), ",", table.getColumnCount()));
            ++i;
        }
        FileWriter writer = null;
        try {
            writer = new FileWriter(filePath);
        }
        catch (IOException e1) {
            e1.printStackTrace();
        }
        try {
            TableUtility.write(data, writer);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void write(ArrayList<String> records, Writer writer) throws IOException {
        long start = System.currentTimeMillis();
        for (String record : records) {
            writer.write(String.valueOf(record) + System.getProperty("line.separator"));
        }
        writer.flush();
        writer.close();
        long end = System.currentTimeMillis();
        System.out.println(String.valueOf((float)(end - start) / 1000.0f) + " seconds");
    }

    public static String implode(String[] array, String separator) {
        StringBuffer out = new StringBuffer();
        boolean first = true;
        String[] stringArray = array;
        int n = array.length;
        int n2 = 0;
        while (n2 < n) {
            String v = stringArray[n2];
            if (first) {
                first = false;
            } else {
                out.append(separator);
            }
            out.append(v);
            ++n2;
        }
        return out.toString();
    }

    public static String implode(TableItem tableItem, String separator, int columnCount) {
        String[] array = new String[columnCount];
        int i = 0;
        while (i < columnCount) {
            array[i] = tableItem.getText(i);
            ++i;
        }
        StringBuffer out = new StringBuffer();
        boolean first = true;
        String[] stringArray = array;
        int n = array.length;
        int n2 = 0;
        while (n2 < n) {
            String v = stringArray[n2];
            if (first) {
                first = false;
            } else {
                out.append(separator);
            }
            out.append(v);
            ++n2;
        }
        return out.toString();
    }
}

