/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.jface.dialogs.Dialog
 *  org.eclipse.jface.dialogs.IDialogConstants
 *  org.eclipse.jface.dialogs.MessageDialog
 *  org.eclipse.swt.events.SelectionAdapter
 *  org.eclipse.swt.events.SelectionEvent
 *  org.eclipse.swt.events.SelectionListener
 *  org.eclipse.swt.graphics.Point
 *  org.eclipse.swt.layout.GridData
 *  org.eclipse.swt.layout.GridLayout
 *  org.eclipse.swt.widgets.Button
 *  org.eclipse.swt.widgets.Combo
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
 *  org.eclipse.swt.widgets.Text
 *  org.eclipse.swt.widgets.TreeItem
 */
package org.irri.breedingtool.datamanipulation.dialog;

import java.util.ArrayList;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
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
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.wb.swt.SWTResourceManager;
import org.irri.breedingtool.application.handler.PartStackHandler;
import org.irri.breedingtool.application.model.ProjectExplorerTreeNodeModel;
import org.irri.breedingtool.manager.impl.DataManipulationManager;
import org.irri.breedingtool.manager.impl.ProjectExplorerManager;
import org.irri.breedingtool.projectexplorer.view.ProjectExplorerView;

public class SubsetDialog
extends Dialog {
    private Text conditionValue;
    private String[] operatorList = new String[]{"equal (==)", "not equal (!=)", "at least (>=)", "at most (<=)", "greater than (>)", "less than (<)", "within range"};
    private String[] operation = new String[]{"==", "!=", ">=", "<=", ">", "<", "?"};
    private String[] operationWords = new String[]{"is equal to", "is not equal to", "is at least", "is at most", "is greater than", "is less than", "is within the range of"};
    private ArrayList<String> subsetConditions = new ArrayList();
    private ArrayList<String> conditionListContent = new ArrayList();
    private List conditionList;
    private Combo comboVariable;
    private Combo comboOperator;
    private Button btnCondition;
    private Button deleteBtn;
    private ArrayList<String> varInfo;
    private ProjectExplorerTreeNodeModel fileModel;
    private Shell shell;
    private DataManipulationManager dataManipulationManager = new DataManipulationManager();

    public SubsetDialog(Shell parentShell, ArrayList<String> varInfo, ProjectExplorerTreeNodeModel fileModel) {
        super(parentShell);
        this.varInfo = varInfo;
        this.fileModel = fileModel;
    }

    protected Control createDialogArea(Composite parent) {
        Composite container = (Composite)super.createDialogArea(parent);
        container.setFont(SWTResourceManager.getFont("Tahoma", 10, 0));
        container.setLayout((Layout)new GridLayout(1, false));
        Group grpSpecifyConditions = new Group(container, 0);
        grpSpecifyConditions.setLayoutData((Object)new GridData(4, 4, true, true, 1, 1));
        grpSpecifyConditions.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        grpSpecifyConditions.setText("Specify Condition:");
        grpSpecifyConditions.setLayout((Layout)new GridLayout(4, false));
        Label lblVariable = new Label((Composite)grpSpecifyConditions, 0);
        lblVariable.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        lblVariable.setText("Variable:");
        Label lblOperator = new Label((Composite)grpSpecifyConditions, 0);
        lblOperator.setLayoutData((Object)new GridData(16384, 0x1000000, true, false, 1, 1));
        lblOperator.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        lblOperator.setText("Operator:");
        Label lblValuelabel = new Label((Composite)grpSpecifyConditions, 0);
        lblValuelabel.setText("Value/Level:");
        lblValuelabel.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        new Label((Composite)grpSpecifyConditions, 0);
        this.comboVariable = new Combo((Composite)grpSpecifyConditions, 12);
        GridData gd_comboVariable = new GridData(4, 0x1000000, true, false, 1, 1);
        gd_comboVariable.widthHint = 123;
        this.comboVariable.setLayoutData((Object)gd_comboVariable);
        this.populateComboVariable();
        this.comboOperator = new Combo((Composite)grpSpecifyConditions, 12);
        GridData gd_comboOperator = new GridData(4, 0x1000000, false, false, 1, 1);
        gd_comboOperator.widthHint = 133;
        this.comboOperator.setLayoutData((Object)gd_comboOperator);
        this.comboOperator.setItems(this.operatorList);
        this.conditionValue = new Text((Composite)grpSpecifyConditions, 2048);
        GridData gd_conditionValue = new GridData(4, 0x1000000, true, false, 1, 1);
        gd_conditionValue.widthHint = 108;
        this.conditionValue.setLayoutData((Object)gd_conditionValue);
        this.conditionValue.setToolTipText("separate different values with a comma \",\"");
        this.btnCondition = new Button((Composite)grpSpecifyConditions, 0);
        this.btnCondition.setLayoutData((Object)new GridData(4, 0x1000000, false, false, 1, 1));
        this.btnCondition.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                String newCondition = "";
                String addedCondition = "";
                if (!(SubsetDialog.this.comboVariable.getText().isEmpty() || SubsetDialog.this.comboOperator.getText().isEmpty() || SubsetDialog.this.conditionValue.getText().isEmpty())) {
                    if (SubsetDialog.this.comboOperator.getSelectionIndex() == 6) {
                        String[] inputValues = SubsetDialog.this.conditionValue.getText().split(",");
                        if (inputValues.length == 2) {
                            try {
                                String min;
                                String max;
                                if (Double.parseDouble(inputValues[0]) < Double.parseDouble(inputValues[1])) {
                                    max = inputValues[1];
                                    min = inputValues[0];
                                } else {
                                    min = inputValues[1];
                                    max = inputValues[0];
                                }
                                addedCondition = String.valueOf(SubsetDialog.this.comboVariable.getText()) + ":>=:" + min + ":" + SubsetDialog.this.comboVariable.getData(SubsetDialog.this.comboVariable.getText());
                                newCondition = String.valueOf(SubsetDialog.this.comboVariable.getText()) + " is at least " + min + ".";
                                SubsetDialog.this.subsetConditions.add(addedCondition);
                                SubsetDialog.this.conditionListContent.add(newCondition);
                                addedCondition = String.valueOf(SubsetDialog.this.comboVariable.getText()) + ":<=:" + max + ":" + SubsetDialog.this.comboVariable.getData(SubsetDialog.this.comboVariable.getText());
                                newCondition = String.valueOf(SubsetDialog.this.comboVariable.getText()) + " is at most " + max + ".";
                                SubsetDialog.this.subsetConditions.add(addedCondition);
                                SubsetDialog.this.conditionListContent.add(newCondition);
                                SubsetDialog.this.conditionList.setItems(SubsetDialog.this.conditionListContent.toArray(new String[SubsetDialog.this.conditionListContent.size()]));
                                SubsetDialog.this.comboVariable.deselectAll();
                                SubsetDialog.this.comboOperator.deselectAll();
                                SubsetDialog.this.conditionValue.setText("");
                                SubsetDialog.this.deleteBtn.setEnabled(false);
                            }
                            catch (NumberFormatException numberFormatException) {
                                MessageDialog.openWarning((Shell)Display.getCurrent().getActiveShell(), (String)"Invalid Input", (String)"The 'within range' condition requires numeric input.\n\nValid input values: lowerLimit,upperLimit\n\nExample value: 0,1");
                            }
                        } else {
                            MessageDialog.openWarning((Shell)Display.getCurrent().getActiveShell(), (String)"Invalid Input for 'within range'", (String)"Valid input values: lowerLimit,upperLimit\n\nExample value: 0,1");
                        }
                    } else {
                        boolean addCondition = true;
                        if (SubsetDialog.this.comboVariable.getData(SubsetDialog.this.comboVariable.getText()).equals("Numeric")) {
                            String[] values;
                            String[] stringArray = values = SubsetDialog.this.conditionValue.getText().split(",");
                            int n = values.length;
                            int n2 = 0;
                            while (n2 < n) {
                                String s = stringArray[n2];
                                try {
                                    Double.parseDouble(s);
                                }
                                catch (NumberFormatException numberFormatException) {
                                    MessageDialog.openWarning((Shell)Display.getCurrent().getActiveShell(), (String)"Invalid Input", (String)("'" + SubsetDialog.this.comboVariable.getText() + "' is a numeric variable."));
                                    addCondition = false;
                                }
                                ++n2;
                            }
                        } else if (SubsetDialog.this.comboOperator.getSelectionIndex() > 1) {
                            String[] values;
                            String[] stringArray = values = SubsetDialog.this.conditionValue.getText().split(",");
                            int n = values.length;
                            int n3 = 0;
                            while (n3 < n) {
                                String s = stringArray[n3];
                                try {
                                    Double.parseDouble(s);
                                }
                                catch (NumberFormatException numberFormatException) {
                                    MessageDialog.openWarning((Shell)Display.getCurrent().getActiveShell(), (String)"Invalid operator input", (String)("The operator '" + SubsetDialog.this.operation[SubsetDialog.this.comboOperator.getSelectionIndex()] + "' can only be used for numeric input conditions."));
                                    addCondition = false;
                                }
                                ++n3;
                            }
                        }
                        if (addCondition) {
                            addedCondition = String.valueOf(SubsetDialog.this.comboVariable.getText()) + ":" + SubsetDialog.this.operation[SubsetDialog.this.comboOperator.getSelectionIndex()] + ":" + SubsetDialog.this.conditionValue.getText() + ":" + SubsetDialog.this.comboVariable.getData(SubsetDialog.this.comboVariable.getText());
                            newCondition = String.valueOf(SubsetDialog.this.comboVariable.getText()) + " " + SubsetDialog.this.operationWords[SubsetDialog.this.comboOperator.getSelectionIndex()] + " " + SubsetDialog.this.conditionValue.getText() + ".";
                            SubsetDialog.this.subsetConditions.add(addedCondition);
                            SubsetDialog.this.conditionListContent.add(newCondition);
                            SubsetDialog.this.conditionList.setItems(SubsetDialog.this.conditionListContent.toArray(new String[SubsetDialog.this.conditionListContent.size()]));
                            SubsetDialog.this.comboVariable.deselectAll();
                            SubsetDialog.this.comboOperator.deselectAll();
                            SubsetDialog.this.conditionValue.setText("");
                            SubsetDialog.this.deleteBtn.setEnabled(false);
                        }
                    }
                } else {
                    MessageDialog.openWarning((Shell)Display.getCurrent().getActiveShell(), (String)"Missing Input", (String)"Please don't leave the variable, operator, and value/level fields empty.");
                }
            }
        });
        this.btnCondition.setText("Add Condition");
        Label lblConditions = new Label((Composite)grpSpecifyConditions, 0);
        lblConditions.setText("Conditions:");
        lblConditions.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        new Label((Composite)grpSpecifyConditions, 0);
        new Label((Composite)grpSpecifyConditions, 0);
        new Label((Composite)grpSpecifyConditions, 0);
        this.conditionList = new List((Composite)grpSpecifyConditions, 2816);
        GridData gd_conditionList = new GridData(4, 4, false, true, 4, 1);
        gd_conditionList.heightHint = 110;
        gd_conditionList.widthHint = 371;
        this.conditionList.setLayoutData((Object)gd_conditionList);
        new Label((Composite)grpSpecifyConditions, 0);
        new Label((Composite)grpSpecifyConditions, 0);
        new Label((Composite)grpSpecifyConditions, 0);
        this.deleteBtn = new Button((Composite)grpSpecifyConditions, 0);
        this.deleteBtn.setLayoutData((Object)new GridData(4, 0x1000000, false, false, 1, 1));
        this.deleteBtn.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                SubsetDialog.this.subsetConditions.remove(SubsetDialog.this.conditionList.getSelectionIndex());
                SubsetDialog.this.conditionListContent.remove(SubsetDialog.this.conditionList.getSelectionIndex());
                SubsetDialog.this.conditionList.removeAll();
                SubsetDialog.this.conditionList.setItems(SubsetDialog.this.conditionListContent.toArray(new String[SubsetDialog.this.conditionListContent.size()]));
                SubsetDialog.this.deleteBtn.setEnabled(false);
            }
        });
        this.deleteBtn.setEnabled(false);
        this.deleteBtn.setText("Delete");
        this.conditionList.addListener(3, new Listener(){

            public void handleEvent(Event event) {
                if (SubsetDialog.this.conditionList.getSelectionCount() > 0) {
                    SubsetDialog.this.deleteBtn.setEnabled(true);
                }
            }
        });
        parent.getShell().setText("Create Data Subset: " + this.dataManipulationManager.getDisplayFileName(this.fileModel.getProjectFile().getAbsolutePath()));
        return container;
    }

    private void populateComboVariable() {
        for (String s : this.varInfo) {
            String[] tmp = s.split(":");
            System.out.println(tmp[0]);
            this.comboVariable.add(tmp[0]);
            this.comboVariable.setData(tmp[0], (Object)tmp[1]);
        }
    }

    protected void okPressed() {
        if (this.subsetConditions.isEmpty()) {
            MessageDialog.openWarning((Shell)this.getShell(), (String)"Empty Field", (String)"Please enter your subset conditions.");
        } else {
            String fileName = this.fileModel.getProjectFile().getAbsolutePath().toString().replaceAll("\\\\+", "/");
            String fileNameExtension = this.dataManipulationManager.getManipulatedFileNameExtension("_subset", this.fileModel.getProjectFile().getAbsolutePath());
            String newFileName = fileName.replaceAll(".csv", fileNameExtension);
            String newFile = this.fileModel.getProjectFile().getName().replaceAll(".csv", fileNameExtension);
            for (String s : this.subsetConditions) {
                System.out.println("subsetConditions:");
                System.out.println(s);
            }
            ProjectExplorerView.rJavaManager.getRJavaDataManipulationManager().subSet(fileName, newFileName, this.subsetConditions);
            this.close();
            ProjectExplorerManager projExpMan = new ProjectExplorerManager();
            TreeItem sortedTree = projExpMan.getTreeNodeByName(this.fileModel.getParentTreeItem(), newFile);
            try {
                if (((ProjectExplorerTreeNodeModel)sortedTree.getData()).getElementInStack() == null) {
                    PartStackHandler.execute(projExpMan.createNewProjectExplorerModel((ProjectExplorerTreeNodeModel)this.fileModel.getParentTreeItem().getData(), newFile));
                } else {
                    PartStackHandler.reOpenPart((ProjectExplorerTreeNodeModel)sortedTree.getData());
                }
            }
            catch (NullPointerException nullPointerException) {
                MessageDialog.openWarning((Shell)Display.getCurrent().getActiveShell(), (String)"Create Subset was Unsuccessful", (String)"Failed to create a subset from file.\n\nAre you sure you specified valid conditions?");
            }
        }
    }

    protected void buttonPressed(int buttonId) {
        if (buttonId == 19) {
            this.subsetConditions.removeAll(this.subsetConditions);
            this.conditionListContent.removeAll(this.conditionListContent);
            this.conditionList.setItems(this.conditionListContent.toArray(new String[this.conditionListContent.size()]));
        }
        super.buttonPressed(buttonId);
    }

    protected void createButtonsForButtonBar(Composite parent) {
        this.createButton(parent, 19, "Reset", true);
        this.createButton(parent, 0, IDialogConstants.OK_LABEL, true);
        this.createButton(parent, 1, IDialogConstants.CANCEL_LABEL, false);
    }

    protected Point getInitialSize() {
        return new Point(611, 419);
    }

    protected boolean isResizable() {
        return true;
    }
}

