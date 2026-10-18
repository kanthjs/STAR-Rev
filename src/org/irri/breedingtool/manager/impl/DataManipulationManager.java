/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.jface.dialogs.IInputValidator
 *  org.eclipse.jface.dialogs.InputDialog
 *  org.eclipse.jface.dialogs.MessageDialog
 *  org.eclipse.swt.widgets.Button
 *  org.eclipse.swt.widgets.Display
 *  org.eclipse.swt.widgets.Label
 *  org.eclipse.swt.widgets.List
 *  org.eclipse.swt.widgets.Shell
 *  org.eclipse.swt.widgets.Table
 *  org.eclipse.swt.widgets.TableColumn
 *  org.eclipse.swt.widgets.TableItem
 *  org.eclipse.swt.widgets.Text
 */
package org.irri.breedingtool.manager.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.irri.breedingtool.application.model.ProjectExplorerTreeNodeModel;
import org.irri.breedingtool.datamanipulation.data.view.FileTableViewer;
import org.irri.breedingtool.manager.api.IDataManipulation;
import org.irri.breedingtool.manager.impl.FileNameValidator;
import org.irri.breedingtool.manager.impl.ProjectExplorerManager;
import org.irri.breedingtool.projectexplorer.view.ProjectExplorerView;
import org.irri.breedingtool.projectexplorer.view.RJavaManagerInitializer;

public class DataManipulationManager
implements IDataManipulation {
    String lineSeparator = System.getProperty("line.separator");
    String fileSeparator = System.getProperty("file.separator");
    private ProjectExplorerManager projExpManager = new ProjectExplorerManager();
    public static ArrayList<Table> tableList = new ArrayList();

    @Override
    public void deleteTableRow(Table table, int rowIndex) {
        table.remove(rowIndex);
    }

    @Override
    public void deleteTableColumn(Table table, int columnIndex) {
        TableColumn column = table.getColumn(columnIndex);
        column.dispose();
        if (table.getColumnCount() == 1) {
            table.removeAll();
        }
    }

    @Override
    public void insertTableRow(Table table, int newRowIndex, ArrayList<String> tableData) {
        tableData.add(newRowIndex, tableData.get(0).replaceAll("[^" + table.getData("delimiter") + "]", ""));
        TableItem newItem = newRowIndex >= table.getItemCount() ? new TableItem(table, 0x1000000) : new TableItem(table, 0, newRowIndex);
        newItem.setText(0, String.valueOf(newRowIndex));
        newItem.setText(tableData.get(0).replaceAll("[^" + table.getData("delimiter") + "]", "").split((String)table.getData("delimiter")));
    }

    @Override
    public void insertTableColumn(Table table, int newColumnIndex, String newColumnHeaderName) {
        TableColumn column = newColumnIndex >= table.getColumnCount() ? new TableColumn(table, 0x1000000) : new TableColumn(table, 0x1000000, newColumnIndex);
        column.setText(newColumnHeaderName);
        column.setWidth(100);
    }

    @Override
    public void createRowNumberColumn(Table table) {
        TableColumn column = new TableColumn(table, 0x1000000, 0);
        column.setText("");
        column.pack();
        column.setWidth(50);
        int i = 0;
        while (i < table.getItemCount()) {
            TableItem item = table.getItem(i);
            item.setText(0, Integer.toString(i + 1));
            item.setBackground(0, table.getShell().getBackground());
            ++i;
        }
    }

    @Override
    public void updateRowNumberColumn(Table table) {
        int i = 0;
        while (i < table.getItemCount()) {
            TableItem item = table.getItem(i);
            item.setText(0, Integer.toString(i + 1));
            item.setBackground(0, table.getShell().getBackground());
            ++i;
        }
        ((Label)table.getData("tableInfoLabel")).setText("Column(s): " + Integer.toString(table.getColumnCount() - 1) + "   Row(s): " + Integer.toString(table.getItemCount()));
    }

    @Override
    public void saveDataAs(String currDir, Table table, String delimiter) {
        block15: {
            File currFile = new File(currDir);
            String filename = null;
            FileWriter writer = null;
            InputDialog fd = new InputDialog(Display.getCurrent().getActiveShell(), "Save File As", "Enter file name", "copyOf" + currFile.getName(), (IInputValidator)new FileNameValidator(currFile));
            try {
                fd.open();
                if (fd.getReturnCode() != 0) break block15;
                String selected = String.valueOf(currFile.getParentFile().getAbsolutePath()) + this.fileSeparator + fd.getValue();
                String bSlash = "\\";
                String fSlash = "/";
                if (selected.lastIndexOf(bSlash) != -1) {
                    filename = selected.replace(bSlash, fSlash);
                }
                StringBuilder sb = new StringBuilder();
                int numCols = table.getColumnCount();
                delimiter = ",";
                int colCount = 1;
                while (colCount < numCols) {
                    sb.append(table.getColumn(colCount).getText());
                    if (colCount != numCols - 1) {
                        sb.append(delimiter);
                    } else {
                        sb.append(this.lineSeparator);
                    }
                    ++colCount;
                }
                int numRows = table.getItemCount();
                int rowCount = 0;
                while (rowCount < numRows) {
                    int colCount2 = 1;
                    while (colCount2 < numCols) {
                        String itemText = table.getItem(rowCount).getText(colCount2).replaceAll("\"", "");
                        if (itemText.contains(",")) {
                            sb.append("\"" + itemText + "\"");
                        } else {
                            sb.append(itemText);
                        }
                        if (colCount2 != numCols - 1) {
                            sb.append(delimiter);
                        }
                        ++colCount2;
                    }
                    if (rowCount != numRows - 1) {
                        sb.append(this.lineSeparator);
                    }
                    ++rowCount;
                }
                try {
                    writer = new FileWriter(filename);
                    writer.write(sb.toString());
                    if (writer != null) {
                        FileTableViewer.refreshExplorer();
                        MessageDialog.openInformation((Shell)Display.getCurrent().getActiveShell(), (String)"Save As", (String)("Successfully created '" + new File(filename).getName() + "'!"));
                        writer.close();
                    }
                }
                catch (Exception e) {
                    System.out.println(e);
                }
            }
            catch (Exception exception) {}
        }
    }

    @Override
    public void saveFileChanges(File file, Table table, String delimiter) {
        System.out.println("Saving..." + file.getName() + "...");
        FileWriter writer = null;
        StringBuilder sb = new StringBuilder();
        int colCount = 1;
        while (colCount < table.getColumnCount()) {
            sb.append(table.getColumn(colCount).getText());
            if (colCount != table.getColumnCount() - 1) {
                sb.append(delimiter);
            } else {
                sb.append(this.lineSeparator);
            }
            ++colCount;
        }
        int numRows = table.getItemCount();
        int rowCount = 0;
        while (rowCount < numRows) {
            int colCount2 = 1;
            while (colCount2 < table.getColumnCount()) {
                String itemText = table.getItem(rowCount).getText(colCount2).replaceAll("\"", "");
                if (itemText.contains(",")) {
                    sb.append("\"" + itemText + "\"");
                } else {
                    sb.append(itemText);
                }
                if (colCount2 != table.getColumnCount() - 1) {
                    sb.append(delimiter);
                }
                ++colCount2;
            }
            if (rowCount != numRows - 1) {
                sb.append(this.lineSeparator);
            }
            ++rowCount;
        }
        try {
            writer = new FileWriter(file);
            writer.write(sb.toString());
            if (writer != null) {
                writer.close();
            }
        }
        catch (Exception exception) {}
    }

    @Override
    public void saveOriginal(File file, Table table, String delimiter) {
        String originalFile = file.getAbsolutePath().replaceAll(".csv", RJavaManagerInitializer.VARINFO_ORIGINALFILE_EXTENSION);
        System.out.println("Saving original " + originalFile + "...");
        FileWriter writer = null;
        StringBuilder sb = new StringBuilder();
        int colCount = 1;
        while (colCount < table.getColumnCount()) {
            sb.append(table.getColumn(colCount).getText());
            if (colCount != table.getColumnCount() - 1) {
                sb.append(delimiter);
            } else {
                sb.append(this.lineSeparator);
            }
            ++colCount;
        }
        int numRows = table.getItemCount();
        int rowCount = 0;
        while (rowCount < numRows) {
            int colCount2 = 1;
            while (colCount2 < table.getColumnCount()) {
                String itemText = table.getItem(rowCount).getText(colCount2).replaceAll("\"", "");
                if (itemText.contains(",")) {
                    sb.append("\"" + itemText + "\"");
                } else {
                    sb.append(itemText);
                }
                if (colCount2 != table.getColumnCount() - 1) {
                    sb.append(delimiter);
                }
                ++colCount2;
            }
            if (rowCount != numRows - 1) {
                sb.append(this.lineSeparator);
            }
            ++rowCount;
        }
        try {
            writer = new FileWriter(originalFile);
            writer.write(sb.toString());
            if (writer != null) {
                writer.close();
            }
        }
        catch (Exception exception) {}
        File original = new File(originalFile);
        original.deleteOnExit();
    }

    @Override
    public boolean checkColumnNameIfUsed(String value, String[] columnHeaders) {
        int i = 0;
        while (i < columnHeaders.length) {
            if (value.equals(columnHeaders[i])) {
                return true;
            }
            ++i;
        }
        return false;
    }

    @Override
    public String[] getListItems(ArrayList<String> varInfo) {
        ArrayList<String> variableNames = new ArrayList<String>();
        for (String s : varInfo) {
            String[] tmp = s.split(":");
            variableNames.add(tmp[0]);
        }
        return variableNames.toArray(new String[variableNames.size()]);
    }

    @Override
    public String[] getFilesFromDir(ProjectExplorerTreeNodeModel fileModel) {
        File dir = new File(fileModel.getProjectFile().getParentFile().toString());
        String[] children = dir.list();
        ArrayList<String> transactionFiles = new ArrayList<String>();
        int i = 0;
        while (i < children.length) {
            if ((children[i].endsWith(".txt") || children[i].endsWith(".csv")) && !children[i].equals(fileModel.getProjectFile().getName())) {
                transactionFiles.add(children[i]);
            }
            ++i;
        }
        return transactionFiles.toArray(new String[transactionFiles.size()]);
    }

    @Override
    public String[] getFileNamesFromDir(File file) {
        File dir = new File(file.getParentFile().toString());
        String[] children = dir.list();
        ArrayList<String> transactionFiles = new ArrayList<String>();
        int i = 0;
        while (i < children.length) {
            if ((children[i].endsWith(".txt") || children[i].endsWith(".csv")) && !children[i].equals(file.getName())) {
                transactionFiles.add(children[i]);
            }
            ++i;
        }
        return transactionFiles.toArray(new String[transactionFiles.size()]);
    }

    @Override
    public ArrayList<String> getVarInfoFromFile(String fileName) {
        ArrayList<String> varInfo = new ArrayList<String>();
        String tmpFile = null;
        tmpFile = fileName.endsWith(".csv") ? fileName.replaceAll(".csv", RJavaManagerInitializer.VARINFO_TMPFILE_EXTENSION) : tmpFile.replaceAll(".txt", RJavaManagerInitializer.VARINFO_TMPFILE_EXTENSION);
        try {
            Scanner newScanner = new Scanner(new File(tmpFile));
            newScanner.hasNext();
            while (newScanner.hasNext()) {
                String readLine = newScanner.nextLine();
                if (!(readLine = readLine.replaceAll("\"", "")).toLowerCase().endsWith("numeric") && !readLine.toLowerCase().endsWith("factor")) continue;
                varInfo.add(readLine);
            }
            newScanner.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return varInfo;
    }

    @Override
    public void updateVarInfoTempFile(File file) {
        String fileName = file.getAbsolutePath();
        String tmpFile = null;
        tmpFile = fileName.endsWith(".csv") ? fileName.replaceAll(".csv", RJavaManagerInitializer.VARINFO_TMPFILE_EXTENSION) : tmpFile.replaceAll(".txt", RJavaManagerInitializer.VARINFO_TMPFILE_EXTENSION);
        fileName = fileName.replace("\\", "/");
        String rJavaTmpFile = tmpFile.replace("\\", "/");
        try {
            ProjectExplorerView.rJavaManager.getRJavaDataManipulationManager().getVariableInfo(fileName, rJavaTmpFile, DataManipulationManager.getActiveDataFileFormat(file.getAbsolutePath()), DataManipulationManager.getActiveDataDelimiter(file.getAbsolutePath()));
        }
        catch (NullPointerException nullPointerException) {
            this.projExpManager.deleteFile(tmpFile);
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    public boolean isMatchColumnHeaders(String[] fileColumnHeaders, ArrayList<String> tmpVarInfo) {
        if (fileColumnHeaders.length != tmpVarInfo.size()) {
            return false;
        }
        try {
            int i = 0;
            while (true) {
                if (i >= fileColumnHeaders.length) {
                    return true;
                }
                String[] lineRead = tmpVarInfo.get(i).split(":");
                System.out.println(String.valueOf(fileColumnHeaders[i]) + " vs " + lineRead[0]);
                String columnHeaderName = fileColumnHeaders[i].replaceAll("\"", "");
                if (!columnHeaderName.equals(lineRead[0])) {
                    return false;
                }
                ++i;
            }
        }
        catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            return false;
        }
    }

    @Override
    public String[] updateColHeaderNames(Table table, String[] columnHeaderNames) {
        TableColumn[] columnNames = table.getColumns();
        columnHeaderNames = new String[table.getColumnCount() - 1];
        int i = 0;
        while (i < table.getColumnCount() - 1) {
            columnHeaderNames[i] = columnNames[i + 1].getText();
            table.getColumn(i).setData("index", (Object)i);
            ++i;
        }
        table.setData("columnHeaderNames", (Object)columnHeaderNames);
        ((Label)table.getData("tableInfoLabel")).setText("Column(s): " + Integer.toString(table.getColumnCount() - 1) + "   Row(s): " + Integer.toString(table.getItemCount()));
        return columnHeaderNames;
    }

    @Override
    public String[] getTableItemText(TableItem[] items) {
        String[] tableItemText = new String[items.length];
        int i = 0;
        while (i < items.length) {
            tableItemText[i] = items[i].getText();
            System.out.println(tableItemText[i]);
            ++i;
        }
        return tableItemText;
    }

    @Override
    public String[] getNumericVars(ArrayList<String> varInfo) {
        ArrayList<String> numVars = new ArrayList<String>();
        for (String s : varInfo) {
            String[] tmp = s.split(":");
            if (!tmp[1].toLowerCase().equals("numeric")) continue;
            numVars.add(tmp[0]);
        }
        return numVars.toArray(new String[numVars.size()]);
    }

    @Override
    public String[] getFactorVars(ArrayList<String> varInfo) {
        ArrayList<String> factors = new ArrayList<String>();
        for (String s : varInfo) {
            String[] tmp = s.split(":");
            if (!tmp[1].toLowerCase().equals("factor")) continue;
            factors.add(tmp[0]);
        }
        return factors.toArray(new String[factors.size()]);
    }

    public static Table getActiveTable(String elementId) {
        System.out.println("Element ID:" + elementId);
        for (Table table : tableList) {
            if (!table.getData("ID").toString().equals(elementId)) continue;
            return table;
        }
        return null;
    }

    public static int getActiveDataFileFormat(String elementId) {
        for (Table table : tableList) {
            System.out.println(table.getData("fileFormat").toString());
            if (!table.getData("ID").toString().equals(elementId)) continue;
            return Integer.parseInt(table.getData("fileFormat").toString());
        }
        return 0;
    }

    public static String getActiveDataDelimiter(String elementId) {
        for (Table table : tableList) {
            if (!table.getData("ID").toString().equals(elementId)) continue;
            return table.getData("delimiter").toString();
        }
        return null;
    }

    public static void addTable(Table newTable) {
        tableList.add(newTable);
        System.out.println("adding Table .. " + newTable.getData("ID"));
    }

    public static void removeTable(Table table) {
        System.out.println("removing Table .. " + table.getData("ID"));
        tableList.remove(table);
    }

    public static String getTimeStamp() {
        Calendar now = Calendar.getInstance();
        return Long.toString(now.getTimeInMillis());
    }

    public static String[] getEnvtLevels(String facEnvt, File file) {
        int envtColumn = 0;
        Table table = DataManipulationManager.getActiveTable(file.getAbsolutePath());
        int i = 0;
        while (i < table.getColumnCount()) {
            if (table.getColumn(i).getText().equals(facEnvt)) {
                envtColumn = i;
            }
            ++i;
        }
        ArrayList<String> envts = new ArrayList<String>();
        int j = 0;
        while (j < table.getItemCount()) {
            String level = table.getItem(j).getText(envtColumn);
            if (!envts.contains(level) && !level.isEmpty()) {
                envts.add(level);
            }
            ++j;
        }
        String[] envtLevels = new String[envts.size()];
        int k = 0;
        while (k < envts.size()) {
            envtLevels[k] = (String)envts.get(k);
            ++k;
        }
        return envtLevels;
    }

    public static void saveVarTempFile(String absolutePath, ArrayList<String> varInfo) {
        String fileName = absolutePath;
        String tmpFile = null;
        tmpFile = fileName.endsWith(".csv") ? fileName.replaceAll(".csv", RJavaManagerInitializer.VARINFO_TMPFILE_EXTENSION) : fileName.replaceAll(".txt", RJavaManagerInitializer.VARINFO_TMPFILE_EXTENSION);
        FileWriter writer = null;
        StringBuilder sb = new StringBuilder();
        System.out.println("saving variables");
        for (String s : varInfo) {
            System.out.println(s);
            sb.append(s);
            sb.append(System.getProperty("line.separator"));
        }
        try {
            writer = new FileWriter(tmpFile);
            writer.write(sb.toString());
            if (writer != null) {
                writer.close();
            }
        }
        catch (Exception exception) {
            System.out.println("can't write file to:" + tmpFile);
        }
    }

    @Override
    public void editVariableType(String filePath, ArrayList<String> varInfo, String varName, String newType) {
        int ctr = 0;
        for (String s : varInfo) {
            String[] tmp = s.split(":");
            if (varName.equals(tmp[0])) {
                s = String.valueOf(tmp[0]) + ":" + newType;
                varInfo.set(ctr, s);
            }
            ++ctr;
        }
        DataManipulationManager.saveVarTempFile(filePath, varInfo);
    }

    @Override
    public void deleteVariableFromVarInfoTmp(String filePath, String varName) {
        int ctr = 0;
        int deleteVar = 0;
        ArrayList<String> varInfo = this.getVarInfoFromFile(filePath);
        String dottedVarName = varName.replaceAll(" ", ".");
        for (String s : varInfo) {
            String[] tmp = s.split(":");
            if (varName.equals(tmp[0]) || dottedVarName.equals(tmp[0])) {
                deleteVar = ctr;
            }
            ++ctr;
        }
        varInfo.remove(deleteVar);
        DataManipulationManager.saveVarTempFile(filePath, varInfo);
    }

    @Override
    public void convertTxtToCsv(String selectedTxtFilePath, String newCsvFilePath, String selectedFiledelimiter) {
        StringBuilder sb = new StringBuilder();
        try {
            Scanner newScanner = new Scanner(new File(selectedTxtFilePath));
            while (newScanner.hasNext()) {
                String readLine = newScanner.nextLine();
                System.out.println(readLine);
                readLine = readLine.replaceAll(selectedFiledelimiter, ",");
                sb.append(readLine);
                sb.append(System.getProperty("line.separator"));
            }
            newScanner.close();
        }
        catch (FileNotFoundException fileNotFoundException) {}
        try {
            FileWriter writer = new FileWriter(newCsvFilePath);
            writer.write(sb.toString());
            if (writer != null) {
                writer.close();
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    public double convertInttoDouble(int selection, int digits) {
        Double decimal = (double)selection / Math.pow(10.0, digits);
        return decimal;
    }

    @Override
    public void insertTableRow(Table table, int newRowIndex) {
    }

    @Override
    public void keepOriginalFile(File projectFile) {
        String fileName = projectFile.getName();
        String originalFilePath = projectFile.getAbsolutePath().replaceAll(".csv", RJavaManagerInitializer.VARINFO_ORIGINALFILE_EXTENSION);
        this.projExpManager.deleteFile(projectFile.getAbsolutePath());
        this.projExpManager.renameFile(originalFilePath, fileName);
    }

    @Override
    public void addNewVariableNameToTmpVarInfo(String absolutePath, String varName) {
        block14: {
            BufferedWriter bw = null;
            String tmpFile = absolutePath.replaceAll(".csv", RJavaManagerInitializer.VARINFO_TMPFILE_EXTENSION);
            try {
                try {
                    bw = new BufferedWriter(new FileWriter(tmpFile, true));
                    bw.write(String.valueOf(varName) + ":" + "Factor");
                    bw.newLine();
                    bw.flush();
                }
                catch (IOException ioe) {
                    ioe.printStackTrace();
                    if (bw != null) {
                        try {
                            bw.close();
                        }
                        catch (IOException iOException) {}
                    }
                    break block14;
                }
            }
            catch (Throwable throwable) {
                if (bw != null) {
                    try {
                        bw.close();
                    }
                    catch (IOException iOException) {}
                }
                throw throwable;
            }
            if (bw != null) {
                try {
                    bw.close();
                }
                catch (IOException iOException) {}
            }
        }
    }

    @Override
    public String[] getTxtFilesFromDir(File file) {
        File dir = new File(file.getParentFile().toString());
        String[] children = dir.list();
        ArrayList<String> transactionFiles = new ArrayList<String>();
        int i = 0;
        while (i < children.length) {
            if (children[i].endsWith(".txt") && !children[i].equals(file.getName())) {
                transactionFiles.add(children[i]);
            }
            ++i;
        }
        return transactionFiles.toArray(new String[transactionFiles.size()]);
    }

    @Override
    public String[] getCsvFilesFromDir(File file) {
        File dir = new File(file.getParentFile().toString());
        String[] children = dir.list();
        ArrayList<String> transactionFiles = new ArrayList<String>();
        int i = 0;
        while (i < children.length) {
            if (children[i].endsWith(".csv") && !children[i].equals(file.getName())) {
                transactionFiles.add(children[i]);
            }
            ++i;
        }
        return transactionFiles.toArray(new String[transactionFiles.size()]);
    }

    @Override
    public String isNumeric(String absolutePath, String columnName) {
        System.out.println("called isNumeric");
        String isNumeric = "FALSE";
        try {
            isNumeric = ProjectExplorerView.rJavaManager.getRJavaDataManipulationManager().isColumnNumeric(absolutePath.replaceAll("\\\\", "/"), columnName);
        }
        catch (NullPointerException nullPointerException) {
            isNumeric = ProjectExplorerView.rJavaManager.getRJavaDataManipulationManager().isColumnNumeric(absolutePath.replaceAll("\\\\", "/"), columnName.replaceAll(" ", "."));
            System.out.println("caught Exception from isNumericable");
        }
        return isNumeric;
    }

    @Override
    public void removeItemOfThenAddTo(List varList, List returnList) {
        if (varList.getItemCount() > 0) {
            returnList.add(varList.getItem(0));
            varList.setToolTipText("");
            varList.removeAll();
        }
    }

    @Override
    public void moveSelectedStringFromTo(List fromList, List toList) {
        String[] selectedStrings = fromList.getSelection();
        if (selectedStrings.length > 0) {
            int i = 0;
            while (i < selectedStrings.length) {
                toList.add(selectedStrings[i]);
                ++i;
            }
            fromList.remove(fromList.getSelectionIndices());
            if (toList.getItemCount() == 1) {
                toList.setToolTipText(selectedStrings[0]);
            } else {
                toList.setToolTipText("");
            }
        }
    }

    public void moveSelectedStringFromTo(List fromList, List toList, Button moveBtn) {
        String[] selectedStrings = fromList.getSelection();
        if (selectedStrings.length > 0) {
            int i = 0;
            while (i < selectedStrings.length) {
                toList.add(selectedStrings[i]);
                ++i;
            }
            fromList.remove(fromList.getSelectionIndices());
            if (toList.getItemCount() == 1) {
                toList.setToolTipText(selectedStrings[0]);
            } else {
                toList.setToolTipText("");
            }
            moveBtn.setEnabled(false);
        }
    }

    @Override
    public void moveSelectedStringFromListToText(List fromList, Text toText) {
        String[] selectedStrings = fromList.getSelection();
        if (selectedStrings.length > 0) {
            toText.setText(fromList.getItem(0));
            fromList.remove(fromList.getSelectionIndices());
        }
    }

    @Override
    public void moveSelectedStringFromTextToList(Text fromText, List toList) {
        toList.add(fromText.getText());
        fromText.setText("");
    }

    @Override
    public String getManipulatedFileNameExtension(String originalExtension, String dataFilePath) {
        String newExtension = String.valueOf(originalExtension) + ".csv";
        String createdFilePath = "";
        int ctr = 1;
        createdFilePath = dataFilePath.replaceAll(".csv", newExtension);
        File newFile = new File(createdFilePath);
        System.out.println("created path: " + createdFilePath);
        while (newFile.exists()) {
            newExtension = String.valueOf(originalExtension) + "(" + Integer.toString(ctr) + ").csv";
            System.out.println(String.valueOf(Integer.toString(ctr)) + " : " + newExtension);
            createdFilePath = dataFilePath.replaceAll(".csv", newExtension);
            newFile = new File(createdFilePath);
            ++ctr;
        }
        return newExtension;
    }

    @Override
    public String variableNameInputValidation(String inputName) {
        System.out.println("validating variable Name: " + inputName);
        int i = 0;
        while (i < inputName.length()) {
            if (i == 0 && !Character.isLetter(inputName.charAt(i))) {
                return "Invalid Variable Name.\n";
            }
            if (!Character.isLetterOrDigit(inputName.charAt(i)) && inputName.charAt(i) != '.' && inputName.charAt(i) != '_') {
                return "Invalid Variable Name.\n";
            }
            ++i;
        }
        return "true";
    }

    @Override
    public String getDisplayFileName(String absolutePath) {
        String newFileName = "";
        String dataPath = ((ProjectExplorerTreeNodeModel)this.projExpManager.getDataFolder(ProjectExplorerView.projectTree).getData()).getProjectFile().getAbsolutePath();
        String outputFolderPath = ((ProjectExplorerTreeNodeModel)this.projExpManager.getOutputFolder(ProjectExplorerView.projectTree).getData()).getProjectFile().getAbsolutePath();
        newFileName = new File(absolutePath).isFile() ? (!absolutePath.contains("Output") ? absolutePath.replace(String.valueOf(dataPath) + this.fileSeparator, "") : absolutePath.replace(String.valueOf(outputFolderPath) + this.fileSeparator, "")) : absolutePath.replace(String.valueOf(outputFolderPath) + this.fileSeparator, "");
        return newFileName;
    }

    public void removeItemOfThenAddTo(Text txtFrom, List lstTo) {
        lstTo.add(txtFrom.getText());
        txtFrom.setText("");
    }
}

