/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.jface.dialogs.Dialog
 *  org.eclipse.jface.dialogs.IDialogConstants
 *  org.eclipse.jface.dialogs.MessageDialog
 *  org.eclipse.swt.events.MouseEvent
 *  org.eclipse.swt.events.MouseListener
 *  org.eclipse.swt.events.SelectionAdapter
 *  org.eclipse.swt.events.SelectionEvent
 *  org.eclipse.swt.events.SelectionListener
 *  org.eclipse.swt.graphics.Point
 *  org.eclipse.swt.layout.GridData
 *  org.eclipse.swt.layout.GridLayout
 *  org.eclipse.swt.widgets.Button
 *  org.eclipse.swt.widgets.Composite
 *  org.eclipse.swt.widgets.Control
 *  org.eclipse.swt.widgets.Display
 *  org.eclipse.swt.widgets.Event
 *  org.eclipse.swt.widgets.Group
 *  org.eclipse.swt.widgets.Label
 *  org.eclipse.swt.widgets.Layout
 *  org.eclipse.swt.widgets.List
 *  org.eclipse.swt.widgets.Listener
 *  org.eclipse.swt.widgets.Shell
 *  org.eclipse.swt.widgets.Table
 *  org.eclipse.swt.widgets.TableColumn
 *  org.eclipse.swt.widgets.TableItem
 *  org.eclipse.swt.widgets.Text
 *  org.eclipse.swt.widgets.TreeItem
 */
package org.irri.breedingtool.datamanipulation.dialog;

import java.util.ArrayList;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.wb.swt.SWTResourceManager;
import org.irri.breedingtool.application.handler.PartStackHandler;
import org.irri.breedingtool.application.model.ProjectExplorerTreeNodeModel;
import org.irri.breedingtool.datamanipulation.dialog.DefineLevelsDialog;
import org.irri.breedingtool.manager.impl.DataManipulationManager;
import org.irri.breedingtool.manager.impl.ProjectExplorerManager;
import org.irri.breedingtool.projectexplorer.view.ProjectExplorerView;

public class ReshapeWideToLongDialog
extends Dialog {
    private int targetVarCount = 0;
    private int selectedRowIndex;
    private int targetVarItemsCount;
    private Text targetVarText;
    private Text indexFactorText;
    private List activeVarCombo;
    private List targetVarList;
    private List varRetainList;
    private ProjectExplorerTreeNodeModel fileModel;
    private ArrayList<String> varInfo;
    private String[] columnHeaders;
    private Button btnAddTargetVar;
    private Button btnAddVarRetain;
    private Button btnAddTargetVariable;
    private ArrayList<String[]> reshapeVariables = new ArrayList();
    private ArrayList<String> targetVariableList = new ArrayList();
    private Table table;
    private TableColumn tblclmnTargetVariable;
    private TableColumn tblclmnNewColumn;
    private Button deleteTargetBtn;
    private Group group;
    private DataManipulationManager dataManipulationManager = new DataManipulationManager();

    public ReshapeWideToLongDialog(Shell parentShell, ArrayList<String> varInfo, ProjectExplorerTreeNodeModel fileModel) {
        super(parentShell);
        this.fileModel = fileModel;
        this.varInfo = varInfo;
    }

    protected Control createDialogArea(Composite parent) {
        parent.getShell().setText("Reshape Data (Wide to Long): " + this.dataManipulationManager.getDisplayFileName(this.fileModel.getProjectFile().getAbsolutePath()));
        Composite container = (Composite)super.createDialogArea(parent);
        GridLayout gridLayout = (GridLayout)container.getLayout();
        gridLayout.numColumns = 5;
        Label lblNewLabel = new Label(container, 0);
        GridData gd_lblNewLabel = new GridData(4, 0x1000000, true, false, 2, 1);
        gd_lblNewLabel.widthHint = 157;
        lblNewLabel.setLayoutData((Object)gd_lblNewLabel);
        lblNewLabel.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        lblNewLabel.setText("Variables in the active data:");
        Group grpVariablesToBe = new Group(container, 0);
        grpVariablesToBe.setForeground(SWTResourceManager.getColor(24));
        grpVariablesToBe.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        grpVariablesToBe.setText("Variable to Reshape:");
        grpVariablesToBe.setLayout((Layout)new GridLayout(4, false));
        GridData gd_grpVariablesToBe = new GridData(4, 4, true, false, 3, 7);
        gd_grpVariablesToBe.heightHint = 277;
        gd_grpVariablesToBe.widthHint = 253;
        grpVariablesToBe.setLayoutData((Object)gd_grpVariablesToBe);
        Label lblTargetVariable = new Label((Composite)grpVariablesToBe, 0);
        lblTargetVariable.setLayoutData((Object)new GridData(4, 0x1000000, false, false, 1, 1));
        lblTargetVariable.setText("Target Variable:");
        this.targetVarText = new Text((Composite)grpVariablesToBe, 2048);
        this.targetVarText.setText("targetvar1");
        this.targetVarText.setLayoutData((Object)new GridData(4, 4, true, false, 3, 1));
        this.targetVarText.addListener(25, new Listener(){

            public void handleEvent(Event event) {
                String string = event.text;
                char[] chars = new char[string.length()];
                string.getChars(0, chars.length, chars, 0);
                if (ReshapeWideToLongDialog.this.targetVarText.getText().isEmpty() && Character.isDigit(chars[0])) {
                    event.doit = false;
                    MessageDialog.openWarning((Shell)ReshapeWideToLongDialog.this.getShell(), (String)"Invalid Input", (String)"Sorry, you can't have an integer as the first character of your variable name.");
                    return;
                }
            }
        });
        this.btnAddTargetVar = new Button((Composite)grpVariablesToBe, 0);
        this.btnAddTargetVar.setEnabled(false);
        this.btnAddTargetVar.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (ReshapeWideToLongDialog.this.activeVarCombo.getItemCount() > 0) {
                    String[] selectedNumVars = ReshapeWideToLongDialog.this.activeVarCombo.getSelection();
                    int i = 0;
                    while (i < selectedNumVars.length) {
                        ReshapeWideToLongDialog.this.targetVarList.add(selectedNumVars[i]);
                        ++i;
                    }
                    ReshapeWideToLongDialog.this.activeVarCombo.remove(ReshapeWideToLongDialog.this.activeVarCombo.getSelectionIndices());
                    ReshapeWideToLongDialog.this.btnAddTargetVar.setEnabled(false);
                    ReshapeWideToLongDialog.this.btnAddVarRetain.setEnabled(false);
                    ReshapeWideToLongDialog.this.btnAddTargetVariable.setEnabled(true);
                }
            }
        });
        GridData gd_btnAddTargetVar = new GridData(4, 0x1000000, false, false, 1, 1);
        gd_btnAddTargetVar.widthHint = 95;
        this.btnAddTargetVar.setLayoutData((Object)gd_btnAddTargetVar);
        this.btnAddTargetVar.setText("Add");
        this.targetVarList = new List((Composite)grpVariablesToBe, 2816);
        GridData gd_targetVarList = new GridData(4, 4, true, true, 3, 1);
        gd_targetVarList.heightHint = 87;
        this.targetVarList.setLayoutData((Object)gd_targetVarList);
        new Label((Composite)grpVariablesToBe, 0);
        new Label((Composite)grpVariablesToBe, 0);
        this.btnAddTargetVariable = new Button((Composite)grpVariablesToBe, 0);
        this.btnAddTargetVariable.setEnabled(false);
        this.btnAddTargetVariable.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (ReshapeWideToLongDialog.this.dataManipulationManager.checkColumnNameIfUsed(ReshapeWideToLongDialog.this.targetVarText.getText(), ReshapeWideToLongDialog.this.columnHeaders)) {
                    MessageDialog.openWarning((Shell)ReshapeWideToLongDialog.this.getShell(), (String)"Invalid Input", (String)("Sorry, the variable name \"" + ReshapeWideToLongDialog.this.targetVarText.getText() + "\" already exist.\n Please rename your target variable!"));
                } else if (ReshapeWideToLongDialog.this.targetVarText.getText().isEmpty()) {
                    MessageDialog.openWarning((Shell)ReshapeWideToLongDialog.this.getShell(), (String)"Invalid Input", (String)"Please enter a target variable name.");
                }
                if (!ReshapeWideToLongDialog.this.dataManipulationManager.variableNameInputValidation(ReshapeWideToLongDialog.this.targetVarText.getText()).equals("true")) {
                    MessageDialog.openWarning((Shell)ReshapeWideToLongDialog.this.getShell(), (String)"Invalid Input", (String)("'" + ReshapeWideToLongDialog.this.targetVarText.getText() + "' is an invalid variable name."));
                } else if (ReshapeWideToLongDialog.this.targetVarList.getItems().length < 1) {
                    MessageDialog.openWarning((Shell)ReshapeWideToLongDialog.this.getShell(), (String)"Invalid Input", (String)"Please add some reshape variables from the active variables table.");
                } else if (ReshapeWideToLongDialog.this.targetVarText.getText().equals(ReshapeWideToLongDialog.this.indexFactorText.getText())) {
                    MessageDialog.openWarning((Shell)ReshapeWideToLongDialog.this.getShell(), (String)"Invalid Input", (String)("Sorry, you already used \"" + ReshapeWideToLongDialog.this.targetVarText.getText() + "\" as your index factor. Please change your target variable name."));
                } else if (ReshapeWideToLongDialog.this.alreadyUsedasTargetVariable(ReshapeWideToLongDialog.this.targetVarText.getText())) {
                    MessageDialog.openWarning((Shell)ReshapeWideToLongDialog.this.getShell(), (String)"Invalid Input", (String)("Sorry, you already used \"" + ReshapeWideToLongDialog.this.targetVarText.getText() + "\" as a targetVariableName. Please change your target variable name."));
                } else if (ReshapeWideToLongDialog.this.targetVarList.getItemCount() < 2) {
                    MessageDialog.openWarning((Shell)ReshapeWideToLongDialog.this.getShell(), (String)"Invalid Input", (String)"Sorry, you should have 2 or more reshape variables for each target variable.");
                } else if (ReshapeWideToLongDialog.this.targetVarList.getItemCount() != ReshapeWideToLongDialog.this.targetVarItemsCount && ReshapeWideToLongDialog.this.targetVarItemsCount > 0) {
                    MessageDialog.openWarning((Shell)ReshapeWideToLongDialog.this.getShell(), (String)"Invalid Input", (String)("Sorry, your targetVariables should have the same number of reshape variables. The current number of your reshape variables for each is " + Integer.toString(ReshapeWideToLongDialog.this.targetVarItemsCount)));
                } else {
                    String[] targetVariableInput = ReshapeWideToLongDialog.this.targetVarList.getItems();
                    String reshapeVariableString = "";
                    if (ReshapeWideToLongDialog.this.table.getItemCount() < 1) {
                        ReshapeWideToLongDialog.this.targetVarItemsCount = targetVariableInput.length;
                    }
                    ReshapeWideToLongDialog.this.reshapeVariables.add(targetVariableInput);
                    ReshapeWideToLongDialog.this.targetVariableList.add(ReshapeWideToLongDialog.this.targetVarText.getText());
                    TableItem tableItem = new TableItem(ReshapeWideToLongDialog.this.table, 0x1000000);
                    tableItem.setText(0, ReshapeWideToLongDialog.this.targetVarText.getText());
                    String[] stringArray = targetVariableInput;
                    int n = targetVariableInput.length;
                    int n2 = 0;
                    while (n2 < n) {
                        String s = stringArray[n2];
                        reshapeVariableString = String.valueOf(reshapeVariableString) + " " + s + ",";
                        ++n2;
                    }
                    tableItem.setText(1, reshapeVariableString);
                    ReshapeWideToLongDialog.this.targetVarText.setText("targetvar1");
                    ReshapeWideToLongDialog.this.targetVarList.removeAll();
                    ReshapeWideToLongDialog reshapeWideToLongDialog = ReshapeWideToLongDialog.this;
                    reshapeWideToLongDialog.targetVarCount = reshapeWideToLongDialog.targetVarCount + 1;
                }
            }
        });
        GridData gd_btnAddTargetVariable = new GridData(4, 4, false, false, 1, 1);
        gd_btnAddTargetVariable.heightHint = 37;
        this.btnAddTargetVariable.setLayoutData((Object)gd_btnAddTargetVariable);
        this.btnAddTargetVariable.setText("Add Target Variable");
        new Label((Composite)grpVariablesToBe, 0);
        this.table = new Table((Composite)grpVariablesToBe, 67584);
        GridData gd_table = new GridData(4, 4, true, true, 4, 3);
        gd_table.heightHint = 87;
        this.table.setLayoutData((Object)gd_table);
        this.table.setHeaderVisible(true);
        this.table.setLinesVisible(true);
        this.table.addSelectionListener(new SelectionListener(){

            public void widgetSelected(SelectionEvent e) {
                if (ReshapeWideToLongDialog.this.table.getSelectionIndex() != -1) {
                    ReshapeWideToLongDialog.this.deleteTargetBtn.setEnabled(true);
                    ReshapeWideToLongDialog.this.selectedRowIndex = ReshapeWideToLongDialog.this.table.getSelectionIndex();
                }
            }

            public void widgetDefaultSelected(SelectionEvent e) {
            }
        });
        this.tblclmnTargetVariable = new TableColumn(this.table, 0);
        this.tblclmnTargetVariable.setWidth(135);
        this.tblclmnTargetVariable.setText("Target Variable");
        this.tblclmnNewColumn = new TableColumn(this.table, 0);
        this.tblclmnNewColumn.setWidth(162);
        this.tblclmnNewColumn.setText("Reshape Variables");
        new Label((Composite)grpVariablesToBe, 0);
        new Label((Composite)grpVariablesToBe, 0);
        this.deleteTargetBtn = new Button((Composite)grpVariablesToBe, 0);
        GridData gd_deleteTargetBtn = new GridData(16384, 0x1000000, false, false, 1, 1);
        gd_deleteTargetBtn.heightHint = 38;
        this.deleteTargetBtn.setLayoutData((Object)gd_deleteTargetBtn);
        this.deleteTargetBtn.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                String[] selectedRowItems;
                System.out.println(Integer.toString(ReshapeWideToLongDialog.this.selectedRowIndex));
                System.out.println("removing " + ReshapeWideToLongDialog.this.selectedRowIndex + " from reshapeVariables = " + Integer.toString(ReshapeWideToLongDialog.this.reshapeVariables.size()));
                ReshapeWideToLongDialog.this.targetVariableList.remove(ReshapeWideToLongDialog.this.selectedRowIndex);
                String[] stringArray = selectedRowItems = (String[])ReshapeWideToLongDialog.this.reshapeVariables.get(ReshapeWideToLongDialog.this.selectedRowIndex);
                int n = selectedRowItems.length;
                int n2 = 0;
                while (n2 < n) {
                    String s = stringArray[n2];
                    ReshapeWideToLongDialog.this.activeVarCombo.add(s);
                    System.out.println(s);
                    ++n2;
                }
                ReshapeWideToLongDialog.this.reshapeVariables.remove(ReshapeWideToLongDialog.this.selectedRowIndex);
                ReshapeWideToLongDialog.this.table.remove(ReshapeWideToLongDialog.this.selectedRowIndex);
                ReshapeWideToLongDialog.this.deleteTargetBtn.setEnabled(false);
                if (ReshapeWideToLongDialog.this.table.getItemCount() < 1) {
                    ReshapeWideToLongDialog.this.targetVarItemsCount = 0;
                }
            }
        });
        this.deleteTargetBtn.setEnabled(false);
        this.deleteTargetBtn.setText("Delete Target Variable");
        new Label((Composite)grpVariablesToBe, 0);
        this.activeVarCombo = new List(container, 2818);
        this.activeVarCombo.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        this.activeVarCombo.setLayoutData((Object)new GridData(4, 4, false, true, 2, 11));
        this.activeVarCombo.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (ReshapeWideToLongDialog.this.activeVarCombo.getSelectionCount() > 0) {
                    ReshapeWideToLongDialog.this.btnAddVarRetain.setText("Add");
                    ReshapeWideToLongDialog.this.btnAddVarRetain.setEnabled(true);
                    ReshapeWideToLongDialog.this.btnAddTargetVar.setEnabled(true);
                }
            }
        });
        this.populateActiveVarCombo();
        this.group = new Group(container, 0);
        this.group.setLayout((Layout)new GridLayout(2, false));
        GridData gd_group = new GridData(4, 4, false, false, 3, 5);
        gd_group.heightHint = 120;
        gd_group.widthHint = 253;
        this.group.setLayoutData((Object)gd_group);
        Label lblVariablesToRetain = new Label((Composite)this.group, 0);
        lblVariablesToRetain.setLayoutData((Object)new GridData(16384, 0x1000000, false, false, 2, 1));
        lblVariablesToRetain.setText("Variables to retain:");
        this.btnAddVarRetain = new Button((Composite)this.group, 0);
        GridData gd_btnAddVarRetain = new GridData(4, 0x1000000, false, false, 1, 1);
        gd_btnAddVarRetain.widthHint = 60;
        this.btnAddVarRetain.setLayoutData((Object)gd_btnAddVarRetain);
        this.btnAddVarRetain.setEnabled(false);
        this.btnAddVarRetain.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (ReshapeWideToLongDialog.this.btnAddVarRetain.getText().equals("Add")) {
                    if (ReshapeWideToLongDialog.this.activeVarCombo.getSelectionCount() > 0) {
                        ReshapeWideToLongDialog.this.dataManipulationManager.moveSelectedStringFromTo(ReshapeWideToLongDialog.this.activeVarCombo, ReshapeWideToLongDialog.this.varRetainList);
                        ReshapeWideToLongDialog.this.btnAddVarRetain.setEnabled(false);
                        ReshapeWideToLongDialog.this.activeVarCombo.remove(ReshapeWideToLongDialog.this.activeVarCombo.getSelectionIndices());
                        ReshapeWideToLongDialog.this.btnAddVarRetain.setEnabled(false);
                        ReshapeWideToLongDialog.this.btnAddTargetVar.setEnabled(false);
                    }
                } else {
                    ReshapeWideToLongDialog.this.dataManipulationManager.moveSelectedStringFromTo(ReshapeWideToLongDialog.this.varRetainList, ReshapeWideToLongDialog.this.activeVarCombo);
                }
                ReshapeWideToLongDialog.this.btnAddVarRetain.setEnabled(false);
            }
        });
        this.btnAddVarRetain.setText("Add");
        this.varRetainList = new List((Composite)this.group, 2816);
        GridData gd_varRetainList = new GridData(4, 0x1000000, true, true, 1, 1);
        gd_varRetainList.heightHint = 35;
        this.varRetainList.setLayoutData((Object)gd_varRetainList);
        this.varRetainList.addMouseListener(new MouseListener(){

            public void mouseDoubleClick(MouseEvent e) {
                ReshapeWideToLongDialog.this.dataManipulationManager.moveSelectedStringFromTo(ReshapeWideToLongDialog.this.varRetainList, ReshapeWideToLongDialog.this.activeVarCombo);
                ReshapeWideToLongDialog.this.btnAddVarRetain.setEnabled(false);
            }

            public void mouseDown(MouseEvent e) {
                if (ReshapeWideToLongDialog.this.varRetainList.getSelectionIndex() > -1) {
                    ReshapeWideToLongDialog.this.btnAddVarRetain.setEnabled(true);
                    ReshapeWideToLongDialog.this.activeVarCombo.setSelection(-1);
                    ReshapeWideToLongDialog.this.varRetainList.getSelection();
                    ReshapeWideToLongDialog.this.btnAddVarRetain.setText("Remove");
                    ReshapeWideToLongDialog.this.btnAddVarRetain.setEnabled(true);
                }
            }

            public void mouseUp(MouseEvent e) {
            }
        });
        Label lblNewLabel_1 = new Label((Composite)this.group, 0);
        lblNewLabel_1.setText("Index Factor:");
        this.indexFactorText = new Text((Composite)this.group, 2048);
        GridData gd_indexFactorText = new GridData(4, 128, true, false, 1, 1);
        gd_indexFactorText.heightHint = 30;
        this.indexFactorText.setLayoutData((Object)gd_indexFactorText);
        this.indexFactorText.addListener(25, new Listener(){

            public void handleEvent(Event event) {
                String string = event.text;
                char[] chars = new char[string.length()];
                string.getChars(0, chars.length, chars, 0);
                if (ReshapeWideToLongDialog.this.indexFactorText.getText().isEmpty()) {
                    try {
                        if (Character.isDigit(chars[0])) {
                            event.doit = false;
                            MessageDialog.openWarning((Shell)ReshapeWideToLongDialog.this.getShell(), (String)"Invalid Input", (String)"Sorry, you can't have an integer as the first character of your variable name.");
                            return;
                        }
                    }
                    catch (Exception exception) {}
                }
            }
        });
        return container;
    }

    protected boolean alreadyUsedasTargetVariable(String string) {
        int i = 0;
        while (i < this.targetVariableList.size()) {
            if (string.equals(this.targetVariableList.get(i))) {
                return true;
            }
            ++i;
        }
        return false;
    }

    protected void okPressed() {
        if (!this.dataManipulationManager.variableNameInputValidation(this.indexFactorText.getText()).equals("true")) {
            MessageDialog.openWarning((Shell)this.getShell(), (String)"Invalid Input", (String)("'" + this.indexFactorText.getText() + "' is an invalid variable name."));
        } else if (this.dataManipulationManager.checkColumnNameIfUsed(this.indexFactorText.getText(), this.columnHeaders)) {
            MessageDialog.openWarning((Shell)this.getShell(), (String)"Invalid Input", (String)("Sorry, the variable name \"" + this.indexFactorText.getText() + "\" already exist."));
        } else if (this.alreadyUsedasTargetVariable(this.indexFactorText.getText())) {
            MessageDialog.openWarning((Shell)this.getShell(), (String)"Invalid Input", (String)("Sorry, you already used \"" + this.indexFactorText.getText() + "\" as your target variable name.\n\n Please change your  index factor name."));
        } else if (this.indexFactorText.getText().isEmpty()) {
            MessageDialog.openWarning((Shell)this.getShell(), (String)"Invalid Input", (String)"Please enter an index factor.");
        } else if (this.varRetainList.getItemCount() < 1) {
            MessageDialog.openWarning((Shell)this.getShell(), (String)"Invalid Input", (String)"Please choose your retain variables from the active variable list.");
        } else if (this.targetVariableList.size() < 1) {
            MessageDialog.openWarning((Shell)this.getShell(), (String)"Invalid Input", (String)"Please enter the variables you want to reshape.");
        } else {
            String dataFileName = this.fileModel.getProjectFile().getAbsolutePath().toString().replaceAll("\\\\+", "/");
            String fileNameExtension = this.dataManipulationManager.getManipulatedFileNameExtension("_ReshapeToLong", this.fileModel.getProjectFile().getAbsolutePath());
            String newFileName = dataFileName.replaceAll(".csv", fileNameExtension);
            String[] retainVariables = this.varRetainList.getItems();
            String[] indexFactor = new String[]{this.indexFactorText.getText()};
            String[] targetVariables = this.targetVariableList.toArray(new String[this.targetVariableList.size()]);
            DefineLevelsDialog defineLevelsDlg = new DefineLevelsDialog(this.getShell(), indexFactor[0], targetVariables[0], this.reshapeVariables.get(0));
            defineLevelsDlg.open();
            String[] newVarCategory = defineLevelsDlg.getLevels();
            if (defineLevelsDlg.getReturnCode() == 0) {
                ProjectExplorerView.rJavaManager.getRJavaDataManipulationManager().toLong(dataFileName, newFileName, this.reshapeVariables, retainVariables, indexFactor, targetVariables, newVarCategory);
                this.close();
                ProjectExplorerManager projExpMan = new ProjectExplorerManager();
                String newFile = this.fileModel.getProjectFile().getName().replaceAll(".csv", fileNameExtension);
                try {
                    TreeItem sortedTree = projExpMan.getTreeNodeByName(this.fileModel.getParentTreeItem(), newFile);
                    if (((ProjectExplorerTreeNodeModel)sortedTree.getData()).getElementInStack() == null) {
                        PartStackHandler.execute(projExpMan.createNewProjectExplorerModel((ProjectExplorerTreeNodeModel)this.fileModel.getParentTreeItem().getData(), newFile));
                    } else {
                        PartStackHandler.reOpenPart((ProjectExplorerTreeNodeModel)sortedTree.getData());
                    }
                }
                catch (NullPointerException nullPointerException) {
                    MessageDialog.openWarning((Shell)Display.getCurrent().getActiveShell(), (String)"Reshape Wide to Long was Unsuccessful", (String)"Failed to reshape variables from data.\n\n");
                }
            }
        }
    }

    protected void createButtonsForButtonBar(Composite parent) {
        this.createButton(parent, 19, "Reset", true);
        this.createButton(parent, 0, "Next", true);
        this.createButton(parent, 1, IDialogConstants.CANCEL_LABEL, false);
    }

    private void populateActiveVarCombo() {
        for (String s : this.varInfo) {
            String[] tmp = s.split(":");
            this.activeVarCombo.add(tmp[0]);
            this.activeVarCombo.setData(tmp[0], (Object)tmp[1]);
        }
        this.columnHeaders = this.activeVarCombo.getItems();
    }

    protected void buttonPressed(int buttonId) {
        if (buttonId == 19) {
            this.activeVarCombo.removeAll();
            this.populateActiveVarCombo();
            this.table.removeAll();
            this.varRetainList.removeAll();
            this.targetVarList.removeAll();
            this.indexFactorText.setText("");
            this.targetVarItemsCount = 0;
            this.reshapeVariables.clear();
            this.targetVariableList.clear();
        }
        super.buttonPressed(buttonId);
    }

    protected Point getInitialSize() {
        return new Point(678, 605);
    }

    protected boolean isResizable() {
        return true;
    }
}

