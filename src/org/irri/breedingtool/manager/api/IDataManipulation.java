/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.swt.widgets.List
 *  org.eclipse.swt.widgets.Table
 *  org.eclipse.swt.widgets.TableItem
 *  org.eclipse.swt.widgets.Text
 */
package org.irri.breedingtool.manager.api;

import java.io.File;
import java.util.ArrayList;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.irri.breedingtool.application.model.ProjectExplorerTreeNodeModel;

public interface IDataManipulation {
    public void addNewVariableNameToTmpVarInfo(String var1, String var2);

    public boolean checkColumnNameIfUsed(String var1, String[] var2);

    public void convertTxtToCsv(String var1, String var2, String var3);

    public void createRowNumberColumn(Table var1);

    public void deleteTableColumn(Table var1, int var2);

    public void deleteTableRow(Table var1, int var2);

    public void deleteVariableFromVarInfoTmp(String var1, String var2);

    public void editVariableType(String var1, ArrayList<String> var2, String var3, String var4);

    public String getDisplayFileName(String var1);

    public String[] getCsvFilesFromDir(File var1);

    public String[] getNumericVars(ArrayList<String> var1);

    public String[] getFactorVars(ArrayList<String> var1);

    public String[] getFileNamesFromDir(File var1);

    public String[] getFilesFromDir(ProjectExplorerTreeNodeModel var1);

    public String[] getListItems(ArrayList<String> var1);

    public String getManipulatedFileNameExtension(String var1, String var2);

    public String[] getTableItemText(TableItem[] var1);

    public String[] getTxtFilesFromDir(File var1);

    public ArrayList<String> getVarInfoFromFile(String var1);

    public void insertTableColumn(Table var1, int var2, String var3);

    public void insertTableRow(Table var1, int var2);

    public void insertTableRow(Table var1, int var2, ArrayList<String> var3);

    public boolean isMatchColumnHeaders(String[] var1, ArrayList<String> var2);

    public String isNumeric(String var1, String var2);

    public void keepOriginalFile(File var1);

    public void moveSelectedStringFromTo(List var1, List var2);

    public void removeItemOfThenAddTo(List var1, List var2);

    public void saveDataAs(String var1, Table var2, String var3);

    public void saveFileChanges(File var1, Table var2, String var3);

    public void saveOriginal(File var1, Table var2, String var3);

    public String[] updateColHeaderNames(Table var1, String[] var2);

    public void updateRowNumberColumn(Table var1);

    public void updateVarInfoTempFile(File var1);

    public String variableNameInputValidation(String var1);

    public void moveSelectedStringFromListToText(List var1, Text var2);

    public void moveSelectedStringFromTextToList(Text var1, List var2);
}

