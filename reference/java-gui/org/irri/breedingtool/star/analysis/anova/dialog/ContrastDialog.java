/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.jface.dialogs.Dialog
 *  org.eclipse.jface.dialogs.IDialogConstants
 *  org.eclipse.jface.dialogs.MessageDialog
 *  org.eclipse.swt.custom.CTabFolder
 *  org.eclipse.swt.custom.CTabItem
 *  org.eclipse.swt.custom.TableEditor
 *  org.eclipse.swt.events.KeyEvent
 *  org.eclipse.swt.events.KeyListener
 *  org.eclipse.swt.events.ModifyEvent
 *  org.eclipse.swt.events.ModifyListener
 *  org.eclipse.swt.graphics.Point
 *  org.eclipse.swt.layout.FillLayout
 *  org.eclipse.swt.layout.GridData
 *  org.eclipse.swt.layout.GridLayout
 *  org.eclipse.swt.widgets.Combo
 *  org.eclipse.swt.widgets.Composite
 *  org.eclipse.swt.widgets.Control
 *  org.eclipse.swt.widgets.Display
 *  org.eclipse.swt.widgets.Event
 *  org.eclipse.swt.widgets.Layout
 *  org.eclipse.swt.widgets.Listener
 *  org.eclipse.swt.widgets.Shell
 *  org.eclipse.swt.widgets.Spinner
 *  org.eclipse.swt.widgets.Table
 *  org.eclipse.swt.widgets.TableColumn
 *  org.eclipse.swt.widgets.TableItem
 *  org.eclipse.swt.widgets.Text
 */
package org.irri.breedingtool.star.analysis.anova.dialog;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.irri.breedingtool.application.handler.PartStackHandler;
import org.irri.breedingtool.manager.impl.DataManipulationManager;
import org.irri.breedingtool.manager.impl.ProjectExplorerManager;
import org.irri.breedingtool.projectexplorer.view.ProjectExplorerView;
import org.irri.breedingtool.utility.StarAnalysisUtilities;
import org.irri.breedingtool.utility.TableUtility;

public class ContrastDialog
extends Dialog {
    private String[] factorList;
    private String filePath;
    final String opt0 = "NONE";
    final String opt1 = "Compare with control";
    final String opt2 = "Orthogonal Polynomial Contrast";
    final String opt3 = "User Specified Contrast";
    int EDITABLECOLUMN = 0;
    private Map<String, Table> tableMap = new HashMap<String, Table>();
    private Table mainTable;
    private CTabFolder tabFolder;
    private String folderPath;
    private String[] respvar;
    private boolean isContrastReady = false;
    private boolean performCombined = false;

    public ContrastDialog(Shell parentShell, String[] factors, String fileP, String outputP, String[] respV, boolean combined) {
        super(parentShell);
        this.factorList = factors;
        this.filePath = fileP;
        this.respvar = respV;
        this.folderPath = outputP;
        this.performCombined = false;
        this.setShellStyle(33808);
    }

    public boolean close() {
        this.getShell().setVisible(false);
        return false;
    }

    boolean contrastReady() {
        return this.isContrastReady;
    }

    void contrastnotReady() {
        this.isContrastReady = false;
    }

    protected Control createDialogArea(Composite parent) {
        Composite container = (Composite)super.createDialogArea(parent);
        GridLayout gridLayout = (GridLayout)container.getLayout();
        gridLayout.marginHeight = 8;
        gridLayout.marginWidth = 8;
        this.mainTable = new Table(container, 67584);
        this.mainTable.setLinesVisible(true);
        this.mainTable.setHeaderVisible(true);
        this.mainTable.setLayoutData((Object)new GridData(4, 4, true, true, 1, 1));
        TableColumn tblclmnFactorName = new TableColumn(this.mainTable, 0);
        tblclmnFactorName.setText("Factor Name");
        tblclmnFactorName.setWidth(86);
        TableColumn tblclmnPartitionType = new TableColumn(this.mainTable, 0);
        tblclmnPartitionType.setWidth(204);
        tblclmnPartitionType.setText("Partition Type");
        TableColumn tblclmnLabel = new TableColumn(this.mainTable, 0);
        tblclmnLabel.setWidth(110);
        tblclmnLabel.setText("Label");
        TableEditor editor = new TableEditor(this.mainTable);
        TableColumn tblclmnValue = new TableColumn(this.mainTable, 0);
        tblclmnValue.setWidth(129);
        tblclmnValue.setText("Value");
        this.tabFolder = new CTabFolder(container, 2048);
        this.tabFolder.setLayoutData((Object)new GridData(4, 4, true, true, 1, 1));
        this.tabFolder.setSelectionBackground(Display.getCurrent().getSystemColor(35));
        int i = 0;
        while (i < this.factorList.length) {
            if (DataManipulationManager.getEnvtLevels(this.factorList[i], new File(this.filePath)).length > 2) {
                TableItem item = new TableItem(this.mainTable, 0);
                item.setText(new String[]{this.factorList[i], "NONE"});
            }
            ++i;
        }
        this.mainTable.addListener(3, new Listener(){
            private DataManipulationManager dataManipulationManager = new DataManipulationManager();

            public void handleEvent(Event e) {
                Combo newEditorCombo;
                TableItem item;
                Control oldEditor = editor.getEditor();
                if (oldEditor != null) {
                    oldEditor.dispose();
                }
                if ((item = ContrastDialog.this.mainTable.getSelection()[0]) == null) {
                    return;
                }
                Point mPoint = new Point(e.x, e.y);
                int i = 0;
                while (i < item.getParent().getColumnCount()) {
                    if (item.getBounds(i).contains(mPoint)) {
                        ContrastDialog.this.EDITABLECOLUMN = i;
                    }
                    ++i;
                }
                System.out.println(ContrastDialog.this.EDITABLECOLUMN);
                if (ContrastDialog.this.EDITABLECOLUMN == 0) {
                    ContrastDialog.this.EDITABLECOLUMN = 1;
                }
                final String factorName = item.getText(0);
                if (item.getText(1).equals("NONE")) {
                    ContrastDialog.this.EDITABLECOLUMN = 1;
                }
                if (item.getText(1).equals("User Specified Contrast")) {
                    ContrastDialog.this.checkTableExist(factorName);
                }
                if (ContrastDialog.this.EDITABLECOLUMN == 1) {
                    newEditorCombo = new Combo((Composite)ContrastDialog.this.mainTable, 8);
                    if (this.dataManipulationManager.isNumeric(ContrastDialog.this.filePath.replace(File.separator, "/"), factorName).equals("TRUE")) {
                        newEditorCombo.setItems(new String[]{"NONE", "Compare with control", "Orthogonal Polynomial Contrast", "User Specified Contrast"});
                    } else {
                        newEditorCombo.setItems(new String[]{"NONE", "Compare with control", "User Specified Contrast"});
                    }
                    newEditorCombo.setText(item.getText(ContrastDialog.this.EDITABLECOLUMN));
                    newEditorCombo.addModifyListener(new ModifyListener(){

                        public void modifyText(ModifyEvent me) {
                            Combo text = (Combo)editor.getEditor();
                            editor.getItem().setText((this).ContrastDialog.this.EDITABLECOLUMN, text.getText());
                            if (text.getText().equals("Compare with control")) {
                                editor.getItem().setText(2, "Control Level:");
                                editor.getItem().setText(3, DataManipulationManager.getEnvtLevels(factorName, new File(ContrastDialog.this.filePath))[0]);
                                ContrastDialog.this.deleteTable(factorName);
                            } else if (text.getText().equals("Orthogonal Polynomial Contrast")) {
                                editor.getItem().setText(2, "Degree:");
                                editor.getItem().setText(3, "1");
                                ContrastDialog.this.deleteTable(factorName);
                            } else if (text.getText().equals("User Specified Contrast")) {
                                editor.getItem().setText(2, "No. of Contrast:");
                                editor.getItem().setText(3, "1");
                                ContrastDialog.this.addNewTable(factorName);
                            } else {
                                editor.getItem().setText(2, "");
                                editor.getItem().setText(3, "");
                                ContrastDialog.this.deleteTable(factorName);
                            }
                        }
                    });
                    newEditorCombo.setFocus();
                    editor.setEditor((Control)newEditorCombo, item, ContrastDialog.this.EDITABLECOLUMN);
                }
                if (ContrastDialog.this.EDITABLECOLUMN == 3) {
                    if (item.getText(1).equals("Orthogonal Polynomial Contrast") || item.getText(1).equals("User Specified Contrast")) {
                        Spinner newEditor = new Spinner((Composite)ContrastDialog.this.mainTable, 0);
                        newEditor.setIncrement(1);
                        newEditor.setMaximum(DataManipulationManager.getEnvtLevels(factorName, new File(ContrastDialog.this.filePath)).length - 1);
                        newEditor.setMinimum(1);
                        newEditor.setSelection(Integer.valueOf(item.getText(ContrastDialog.this.EDITABLECOLUMN)).intValue());
                        newEditor.addModifyListener(new ModifyListener(){

                            public void modifyText(ModifyEvent me) {
                                Spinner text = (Spinner)editor.getEditor();
                                editor.getItem().setText((this).ContrastDialog.this.EDITABLECOLUMN, text.getText());
                                if (editor.getItem().getText(1).equals("User Specified Contrast")) {
                                    ContrastDialog.this.setTableRows((Table)ContrastDialog.this.tableMap.get(factorName), text.getSelection());
                                }
                            }
                        });
                        newEditor.setFocus();
                        editor.setEditor((Control)newEditor, item, ContrastDialog.this.EDITABLECOLUMN);
                    }
                    if (item.getText(1).equals("Compare with control")) {
                        newEditorCombo = new Combo((Composite)ContrastDialog.this.mainTable, 8);
                        newEditorCombo.setItems(DataManipulationManager.getEnvtLevels(factorName, new File(ContrastDialog.this.filePath)));
                        newEditorCombo.setText(item.getText(ContrastDialog.this.EDITABLECOLUMN));
                        newEditorCombo.addModifyListener(new ModifyListener(){

                            public void modifyText(ModifyEvent me) {
                                Combo text = (Combo)editor.getEditor();
                                editor.getItem().setText((this).ContrastDialog.this.EDITABLECOLUMN, text.getText());
                            }
                        });
                        newEditorCombo.setFocus();
                        editor.setEditor((Control)newEditorCombo, item, ContrastDialog.this.EDITABLECOLUMN);
                    }
                }
            }
        });
        editor.horizontalAlignment = 16384;
        editor.grabHorizontal = true;
        editor.minimumWidth = 70;
        new ArrayList();
        return container;
    }

    private void createContentForUserSpecified(Composite factorContainer, String factorName) {
        String[] factorLevels = DataManipulationManager.getEnvtLevels(factorName, new File(this.filePath));
        this.tableMap.put(factorName, new Table(factorContainer, 67584));
        GridData gd_table = new GridData(4, 4, true, true, 3, 1);
        gd_table.heightHint = 135;
        this.tableMap.get(factorName).setLayoutData((Object)gd_table);
        this.tableMap.get(factorName).setHeaderVisible(true);
        this.tableMap.get(factorName).setLinesVisible(true);
        TableColumn column1 = new TableColumn(this.tableMap.get(factorName), 0);
        column1.setText("Label");
        column1.setWidth(86);
        String[] stringArray = factorLevels;
        int n = factorLevels.length;
        int n2 = 0;
        while (n2 < n) {
            String factorLevelName = stringArray[n2];
            TableColumn column2 = new TableColumn(this.tableMap.get(factorName), 0);
            column2.setWidth(50);
            column2.setText(factorLevelName);
            ++n2;
        }
        this.setTableRows(this.tableMap.get(factorName), 1);
        TableEditor editor = new TableEditor(this.tableMap.get(factorName));
        Spinner sprLevel = new Spinner((Composite)this.tableMap.get(factorName), 0);
        sprLevel.setIncrement(1);
        this.tableMap.get(factorName).addListener(3, new Listener(){
            private int EDITABLECOLUMN;

            public void handleEvent(Event e) {
                TableItem item;
                Control oldEditor = editor.getEditor();
                if (oldEditor != null) {
                    oldEditor.dispose();
                }
                if ((item = ((Table)ContrastDialog.this.tableMap.get(factorName)).getSelection()[0]) == null) {
                    return;
                }
                Point mPoint = new Point(e.x, e.y);
                int i = 0;
                while (i < item.getParent().getColumnCount()) {
                    if (item.getBounds(i).contains(mPoint)) {
                        this.EDITABLECOLUMN = i;
                    }
                    ++i;
                }
                System.out.println(this.EDITABLECOLUMN);
                if (this.EDITABLECOLUMN == 0) {
                    this.EDITABLECOLUMN = 1;
                }
                Text newEditor = new Text((Composite)ContrastDialog.this.tableMap.get(factorName), 0);
                newEditor.setText(item.getText(this.EDITABLECOLUMN));
                newEditor.addModifyListener(new ModifyListener(){

                    public void modifyText(ModifyEvent me) {
                        Text text = (Text)editor.getEditor();
                        editor.getItem().setText(EDITABLECOLUMN, text.getText());
                    }
                });
                newEditor.addKeyListener(new KeyListener(){

                    public void keyPressed(KeyEvent e) {
                        System.out.println(String.valueOf(e.keyCode) + " this");
                        String[] stringArray = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", ".", "-"};
                        ArrayList<String> charsAllowed = new ArrayList<String>();
                        charsAllowed.addAll(Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", ".", "-"));
                        if (!charsAllowed.contains(String.valueOf(e.character)) && e.keyCode != 8) {
                            e.doit = false;
                            return;
                        }
                        Text text = (Text)editor.getEditor();
                        editor.getItem().setText(EDITABLECOLUMN, text.getText());
                        System.out.println("textModified");
                    }

                    public void keyReleased(KeyEvent e) {
                    }
                });
                newEditor.selectAll();
                newEditor.setFocus();
                editor.setEditor((Control)newEditor, item, this.EDITABLECOLUMN);
            }
        });
        editor.horizontalAlignment = 16384;
        editor.grabHorizontal = true;
        editor.minimumWidth = 40;
    }

    private void deleteTable(String factorName) {
        CTabItem[] cTabItemArray = this.tabFolder.getItems();
        int n = cTabItemArray.length;
        int n2 = 0;
        while (n2 < n) {
            CTabItem tItem = cTabItemArray[n2];
            if (tItem.getText().equals(factorName)) {
                tItem.dispose();
                this.tableMap.remove(factorName);
            }
            ++n2;
        }
    }

    private void addNewTable(String factorName) {
        if (this.checkTableExist(factorName)) {
            return;
        }
        CTabItem tabItem = new CTabItem(this.tabFolder, 0);
        tabItem.setText(factorName);
        Composite composite = new Composite((Composite)this.tabFolder, 0);
        tabItem.setControl((Control)composite);
        composite.setLayout((Layout)new FillLayout(256));
        this.createContentForUserSpecified(composite, factorName);
        this.tabFolder.setSelection(tabItem);
    }

    private boolean checkTableExist(String factorName) {
        CTabItem[] cTabItemArray = this.tabFolder.getItems();
        int n = cTabItemArray.length;
        int n2 = 0;
        while (n2 < n) {
            CTabItem tItem = cTabItemArray[n2];
            if (tItem.getText().equals(factorName)) {
                this.tabFolder.setSelection(tItem);
                return true;
            }
            ++n2;
        }
        return false;
    }

    private void setTableRows(Table dialogTable, int levels) {
        if (dialogTable.getItemCount() < levels) {
            int i = dialogTable.getItemCount();
            while (i < levels) {
                TableItem item = new TableItem(dialogTable, 0);
                ArrayList<String> row = this.rowSetter(dialogTable.getColumnCount());
                row.set(0, "C" + i);
                item.setText(row.toArray(new String[row.size()]));
                ++i;
            }
        } else if (dialogTable.getItemCount() > levels) {
            dialogTable.remove(levels, dialogTable.getItemCount() - 1);
        }
    }

    boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        }
        catch (NumberFormatException numberFormatException) {
            return false;
        }
    }

    boolean validateTable(Table table) {
        double total = 0.0;
        TableItem[] tableItemArray = table.getItems();
        int n = tableItemArray.length;
        int n2 = 0;
        while (n2 < n) {
            TableItem item = tableItemArray[n2];
            int i = 1;
            while (i < table.getColumnCount()) {
                double x = Double.valueOf(item.getText(i));
                System.out.println(Double.valueOf(item.getText(i)) + ":" + item.getText(i));
                total += x;
                ++i;
            }
            if (total != 0.0) {
                return false;
            }
            total = 0.0;
            ++n2;
        }
        if (total == 0.0) {
            return true;
        }
        System.out.println("TOTAL: " + total);
        return false;
    }

    ArrayList<String> rowSetter(int total) {
        ArrayList<String> returnVal = new ArrayList<String>();
        returnVal.add("");
        int i = 1;
        while (i < total) {
            returnVal.add("0");
            ++i;
        }
        return returnVal;
    }

    protected void createButtonsForButtonBar(Composite parent) {
        this.createButton(parent, 0, IDialogConstants.OK_LABEL, true);
        this.createButton(parent, 1, IDialogConstants.CANCEL_LABEL, false);
    }

    protected void okPressed() {
        String factorLevel;
        String partitionType;
        String factorName;
        TableItem item;
        File tempFolder = null;
        ArrayList<String> factor = new ArrayList<String>();
        ArrayList<String> type = new ArrayList<String>();
        ArrayList<String> level = new ArrayList<String>();
        String[] coeff = null;
        TableItem[] tableItemArray = this.mainTable.getItems();
        int n = tableItemArray.length;
        int n2 = 0;
        while (n2 < n) {
            item = tableItemArray[n2];
            factorName = item.getText(0);
            partitionType = item.getText(1);
            factorLevel = item.getText(3);
            if (partitionType.equals("User Specified Contrast")) {
                factor.add(factorName);
                type.add("user");
                level.add(factorLevel);
            }
            ++n2;
        }
        if (!factor.isEmpty()) {
            ArrayList<Table> tables = new ArrayList<Table>();
            int i = 0;
            while (i < factor.size()) {
                if (!this.validateTable(this.tableMap.get(factor.get(i)))) {
                    MessageDialog.openError((Shell)this.getShell(), (String)"Table Error", (String)("Table " + (String)factor.get(i) + " is invalid. All input should be integer and table sum must be equal to zero."));
                    return;
                }
                tables.add(this.tableMap.get(factor.get(i)));
                ++i;
            }
            tempFolder = new File(String.valueOf(this.folderPath) + File.separator + "temp");
            tempFolder.mkdir();
            coeff = TableUtility.saveTableToCsv(tables.toArray(new Table[tables.size()]), tempFolder.getAbsolutePath());
            int x = 0;
            while (x < coeff.length) {
                coeff[x] = coeff[x].replace(File.separator, "/");
                ++x;
            }
        }
        tableItemArray = this.mainTable.getItems();
        n = tableItemArray.length;
        n2 = 0;
        while (n2 < n) {
            item = tableItemArray[n2];
            factorName = item.getText(0);
            partitionType = item.getText(1);
            factorLevel = item.getText(3);
            if (partitionType.equals("Compare with control")) {
                factor.add(factorName);
                type.add("control");
                level.add(factorLevel);
            } else if (partitionType.equals("Orthogonal Polynomial Contrast")) {
                factor.add(factorName);
                type.add("orthoPoly");
                level.add(factorLevel);
            }
            ++n2;
        }
        if (coeff == null) {
            coeff = factor.toArray(new String[factor.size()]);
        }
        StarAnalysisUtilities.testVarArgs(this.folderPath.replace(File.separator, "/"), this.respvar, factor.toArray(new String[factor.size()]), type.toArray(new String[factor.size()]), level.toArray(new String[factor.size()]), coeff, this.performCombined);
        ProjectExplorerView.rJavaManager.getSTARAnalysisManager().doContrast(this.folderPath.replace(File.separator, "/"), this.respvar, factor.toArray(new String[factor.size()]), type.toArray(new String[factor.size()]), level.toArray(new String[factor.size()]), coeff, this.performCombined);
        if (tempFolder != null) {
            tempFolder.delete();
        }
        MessageDialog.openInformation((Shell)this.getShell(), (String)"Star Analysis", (String)"Contrast has been set");
        this.isContrastReady = true;
        this.getShell().setVisible(false);
        ProjectExplorerManager projMan = new ProjectExplorerManager();
        projMan.refreshFolder(projMan.getTreeNodeModelbyFilePath(this.folderPath));
        if (PartStackHandler.isOpen(String.valueOf(this.folderPath) + File.separator + "ContrastAnalysisOutput.txt")) {
            PartStackHandler.reOpenPart(projMan.getTreeNodeModelbyFilePath(String.valueOf(this.folderPath) + File.separator + "ContrastAnalysisOutput.txt"));
        } else {
            PartStackHandler.execute(projMan.getTreeNodeModelbyFilePath(String.valueOf(this.folderPath) + File.separator + "ContrastAnalysisOutput.txt"));
        }
    }

    protected Point getInitialSize() {
        return new Point(571, 549);
    }
}

