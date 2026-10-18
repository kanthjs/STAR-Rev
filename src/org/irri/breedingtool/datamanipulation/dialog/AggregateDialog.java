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
 *  org.eclipse.swt.widgets.Group
 *  org.eclipse.swt.widgets.Label
 *  org.eclipse.swt.widgets.List
 *  org.eclipse.swt.widgets.Shell
 *  org.eclipse.swt.widgets.Table
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
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.irri.breedingtool.application.handler.PartStackHandler;
import org.irri.breedingtool.application.model.ProjectExplorerTreeNodeModel;
import org.irri.breedingtool.manager.impl.DataManipulationManager;
import org.irri.breedingtool.manager.impl.ProjectExplorerManager;
import org.irri.breedingtool.projectexplorer.view.ProjectExplorerView;
import org.irri.breedingtool.utility.WindowDialogControlUtil;

public class AggregateDialog
extends Dialog {
    private ArrayList<String> varInfo;
    private ArrayList<String> functionList = new ArrayList();
    private Composite container;
    private List numVarCombo;
    private List factorVarCombo;
    private List varsToAggregate;
    private List groupingFactors;
    private List functionCombo;
    private List functionsCombo;
    private boolean toAppend = true;
    private ProjectExplorerTreeNodeModel fileModel;
    private String[] inputNumVariables;
    private String[] inputFactorVariables;
    private String[] function;
    private String[] functionItems = new String[]{"minimum", "maximum", "mean", "median", "sum", "variance", "standard deviation"};
    private String[] functionEquiv = new String[]{"min", "max", "mean", "median", "sum", "variance", "standard deviation"};
    private DataManipulationManager dataManipulationManager = new DataManipulationManager();

    public AggregateDialog(Shell parentShell, ArrayList<String> varInfo, ProjectExplorerTreeNodeModel fileModel) {
        super(parentShell);
        this.fileModel = fileModel;
        this.varInfo = varInfo;
    }

    protected Control createDialogArea(Composite parent) {
        parent.getShell().setText("Aggregate Data: " + this.dataManipulationManager.getDisplayFileName(this.fileModel.getProjectFile().getAbsolutePath()));
        this.container = (Composite)super.createDialogArea(parent);
        GridLayout gridLayout = (GridLayout)this.container.getLayout();
        gridLayout.numColumns = 3;
        Label lblAvailableVariables = new Label(this.container, 0);
        lblAvailableVariables.setLayoutData((Object)new GridData(16384, 0x1000000, true, false, 1, 1));
        lblAvailableVariables.setText("Available Variable(s):");
        new Label(this.container, 0);
        Label lblVariablesToAggregate = new Label(this.container, 0);
        lblVariablesToAggregate.setText("Variable(s) to aggregate:");
        this.numVarCombo = new List(this.container, 2818);
        GridData gd_numVarCombo = new GridData(4, 4, true, true, 1, 1);
        gd_numVarCombo.widthHint = 200;
        gd_numVarCombo.heightHint = 49;
        this.numVarCombo.setLayoutData((Object)gd_numVarCombo);
        this.populateNumVarCombo();
        this.numVarCombo.addMouseListener(new MouseListener(){

            public void mouseDoubleClick(MouseEvent e) {
                AggregateDialog.this.numVarCombo.getSelection();
                AggregateDialog.this.dataManipulationManager.moveSelectedStringFromTo(AggregateDialog.this.numVarCombo, AggregateDialog.this.varsToAggregate);
            }

            public void mouseDown(MouseEvent e) {
            }

            public void mouseUp(MouseEvent e) {
            }
        });
        Button addVarBtn = new Button(this.container, 0);
        addVarBtn.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                String[] selectedNumVars = AggregateDialog.this.numVarCombo.getSelection();
                int i = 0;
                while (i < selectedNumVars.length) {
                    AggregateDialog.this.varsToAggregate.add(selectedNumVars[i]);
                    ++i;
                }
                AggregateDialog.this.numVarCombo.remove(AggregateDialog.this.numVarCombo.getSelectionIndices());
            }
        });
        GridData gd_addVarBtn = new GridData(16384, 0x1000000, true, false, 1, 1);
        gd_addVarBtn.widthHint = 72;
        addVarBtn.setLayoutData((Object)gd_addVarBtn);
        addVarBtn.setText("Add");
        this.varsToAggregate = new List(this.container, 2816);
        GridData gd_varsToAggregate = new GridData(4, 4, false, true, 1, 1);
        gd_varsToAggregate.heightHint = 48;
        gd_varsToAggregate.widthHint = 197;
        this.varsToAggregate.setLayoutData((Object)gd_varsToAggregate);
        this.varsToAggregate.addMouseListener(new MouseListener(){

            public void mouseDoubleClick(MouseEvent e) {
                AggregateDialog.this.varsToAggregate.getSelection();
                AggregateDialog.this.dataManipulationManager.moveSelectedStringFromTo(AggregateDialog.this.varsToAggregate, AggregateDialog.this.numVarCombo);
            }

            public void mouseDown(MouseEvent e) {
            }

            public void mouseUp(MouseEvent e) {
            }
        });
        Label lblAvailableFactors = new Label(this.container, 0);
        lblAvailableFactors.setLayoutData((Object)new GridData(16384, 0x1000000, true, false, 1, 1));
        lblAvailableFactors.setText("Available Factor(s):");
        new Label(this.container, 0);
        Label lblGroupingFactors = new Label(this.container, 0);
        lblGroupingFactors.setText("Grouping Factor(s):");
        this.factorVarCombo = new List(this.container, 2818);
        GridData gd_factorVarCombo = new GridData(4, 4, true, true, 1, 1);
        gd_factorVarCombo.heightHint = 52;
        gd_factorVarCombo.widthHint = 200;
        this.factorVarCombo.setLayoutData((Object)gd_factorVarCombo);
        this.populateFactorVarCombo();
        this.factorVarCombo.addMouseListener(new MouseListener(){

            public void mouseDoubleClick(MouseEvent e) {
                AggregateDialog.this.factorVarCombo.getSelection();
                AggregateDialog.this.dataManipulationManager.moveSelectedStringFromTo(AggregateDialog.this.factorVarCombo, AggregateDialog.this.groupingFactors);
            }

            public void mouseDown(MouseEvent e) {
            }

            public void mouseUp(MouseEvent e) {
            }
        });
        Button addFactorBtn = new Button(this.container, 0);
        addFactorBtn.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                String[] selectedFactors = AggregateDialog.this.factorVarCombo.getSelection();
                int i = 0;
                while (i < selectedFactors.length) {
                    AggregateDialog.this.groupingFactors.add(selectedFactors[i]);
                    ++i;
                }
                AggregateDialog.this.factorVarCombo.remove(AggregateDialog.this.factorVarCombo.getSelectionIndices());
            }
        });
        GridData gd_addFactorBtn = new GridData(16384, 0x1000000, true, false, 1, 1);
        gd_addFactorBtn.widthHint = 72;
        addFactorBtn.setLayoutData((Object)gd_addFactorBtn);
        addFactorBtn.setText("Add");
        this.groupingFactors = new List(this.container, 2816);
        GridData gd_groupingFactors = new GridData(4, 4, false, true, 1, 1);
        gd_groupingFactors.heightHint = 53;
        gd_groupingFactors.widthHint = 197;
        this.groupingFactors.setLayoutData((Object)gd_groupingFactors);
        this.groupingFactors.addMouseListener(new MouseListener(){

            public void mouseDoubleClick(MouseEvent e) {
                AggregateDialog.this.groupingFactors.getSelection();
                AggregateDialog.this.dataManipulationManager.moveSelectedStringFromTo(AggregateDialog.this.groupingFactors, AggregateDialog.this.factorVarCombo);
            }

            public void mouseDown(MouseEvent e) {
            }

            public void mouseUp(MouseEvent e) {
            }
        });
        Label lblAvailableFunctions = new Label(this.container, 0);
        lblAvailableFunctions.setLayoutData((Object)new GridData(16384, 0x1000000, true, false, 1, 1));
        lblAvailableFunctions.setText("Available Function(s):");
        new Label(this.container, 0);
        Label lblFunctions = new Label(this.container, 0);
        lblFunctions.setText("Functions:");
        this.functionCombo = new List(this.container, 2818);
        GridData gd_functionCombo = new GridData(4, 4, true, true, 1, 1);
        gd_functionCombo.heightHint = 49;
        gd_functionCombo.widthHint = 200;
        this.functionCombo.setLayoutData((Object)gd_functionCombo);
        this.functionCombo.addMouseListener(new MouseListener(){

            public void mouseDoubleClick(MouseEvent e) {
                String[] selectedFunction = AggregateDialog.this.functionCombo.getSelection();
                int i = 0;
                while (i < selectedFunction.length) {
                    AggregateDialog.this.functionsCombo.add(selectedFunction[i]);
                    AggregateDialog.this.functionList.add((String)AggregateDialog.this.functionCombo.getData(selectedFunction[i]));
                    ++i;
                }
                AggregateDialog.this.functionCombo.remove(AggregateDialog.this.functionCombo.getSelectionIndices());
            }

            public void mouseDown(MouseEvent e) {
            }

            public void mouseUp(MouseEvent e) {
            }
        });
        this.populateFunctionCombo();
        Button addFunctionBtn = new Button(this.container, 0);
        addFunctionBtn.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                String[] selectedFunction = AggregateDialog.this.functionCombo.getSelection();
                int i = 0;
                while (i < selectedFunction.length) {
                    AggregateDialog.this.functionsCombo.add(selectedFunction[i]);
                    AggregateDialog.this.functionList.add((String)AggregateDialog.this.functionCombo.getData(selectedFunction[i]));
                    ++i;
                }
                AggregateDialog.this.functionCombo.remove(AggregateDialog.this.functionCombo.getSelectionIndices());
            }
        });
        GridData gd_addFunctionBtn = new GridData(16384, 0x1000000, true, false, 1, 1);
        gd_addFunctionBtn.widthHint = 71;
        addFunctionBtn.setLayoutData((Object)gd_addFunctionBtn);
        addFunctionBtn.setText("Add");
        this.functionsCombo = new List(this.container, 2816);
        GridData gd_functionsCombo = new GridData(4, 4, true, true, 1, 1);
        gd_functionsCombo.heightHint = 51;
        gd_functionsCombo.widthHint = 197;
        this.functionsCombo.setLayoutData((Object)gd_functionsCombo);
        this.functionsCombo.addMouseListener(new MouseListener(){

            public void mouseDoubleClick(MouseEvent e) {
                String[] selectedFunction = AggregateDialog.this.functionsCombo.getSelection();
                int i = 0;
                while (i < selectedFunction.length) {
                    AggregateDialog.this.functionCombo.add(selectedFunction[i]);
                    System.out.println("removing: " + Integer.toString(AggregateDialog.this.functionCombo.getSelectionIndex()));
                    AggregateDialog.this.functionList.remove(AggregateDialog.this.functionsCombo.getSelectionIndex());
                    ++i;
                }
                AggregateDialog.this.functionsCombo.remove(AggregateDialog.this.functionsCombo.getSelectionIndices());
            }

            public void mouseDown(MouseEvent e) {
            }

            public void mouseUp(MouseEvent e) {
            }
        });
        Group grpSave = new Group(this.container, 0);
        grpSave.setText("Save");
        GridData gd_grpSave = new GridData(4, 4, true, true, 3, 1);
        gd_grpSave.heightHint = 50;
        gd_grpSave.widthHint = 416;
        grpSave.setLayoutData((Object)gd_grpSave);
        Button btnAddAggregatedVariables = new Button((Composite)grpSave, 16);
        btnAddAggregatedVariables.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                AggregateDialog.this.toAppend = true;
            }
        });
        btnAddAggregatedVariables.setSelection(true);
        btnAddAggregatedVariables.setText("Add aggregated variables to the active data");
        btnAddAggregatedVariables.setBounds(21, 20, 372, 16);
        Button btnCreateNewData = new Button((Composite)grpSave, 16);
        btnCreateNewData.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                AggregateDialog.this.toAppend = false;
            }
        });
        btnCreateNewData.setText("Create new data file containing the aggregated variables only");
        btnCreateNewData.setBounds(21, 42, 372, 16);
        return this.container;
    }

    private void populateNumVarCombo() {
        for (String s : this.varInfo) {
            String[] tmp = s.split(":");
            if (!tmp[1].toLowerCase().equals("numeric")) continue;
            this.numVarCombo.add(tmp[0]);
            this.numVarCombo.setData(tmp[0], (Object)tmp[1]);
        }
    }

    private void populateFactorVarCombo() {
        for (String s : this.varInfo) {
            String[] tmp = s.split(":");
            if (!tmp[1].toLowerCase().equals("factor")) continue;
            this.factorVarCombo.add(tmp[0]);
            this.factorVarCombo.setData(tmp[0], (Object)tmp[1]);
        }
    }

    private void populateFunctionCombo() {
        int ctr = 0;
        String[] stringArray = this.functionItems;
        int n = this.functionItems.length;
        int n2 = 0;
        while (n2 < n) {
            this.functionCombo.add(this.functionItems[ctr]);
            this.functionCombo.setData(this.functionItems[ctr], (Object)this.functionEquiv[ctr]);
            ++ctr;
            ++n2;
        }
    }

    protected void createButtonsForButtonBar(Composite parent) {
        this.createButton(parent, 19, "Reset", true);
        this.createButton(parent, 0, "OK", true);
        this.createButton(parent, 1, IDialogConstants.CANCEL_LABEL, false);
    }

    protected void buttonPressed(int buttonId) {
        if (buttonId == 19) {
            this.functionsCombo.removeAll();
            this.numVarCombo.removeAll();
            this.factorVarCombo.removeAll();
            this.functionCombo.removeAll();
            this.varsToAggregate.removeAll();
            this.groupingFactors.removeAll();
            this.functionList.clear();
            this.populateNumVarCombo();
            this.populateFactorVarCombo();
            this.populateFunctionCombo();
        }
        super.buttonPressed(buttonId);
    }

    protected void okPressed() {
        String fileName = this.fileModel.getProjectFile().getAbsolutePath().toString().replaceAll("\\\\+", "/");
        String newFileName = fileName.replaceAll(".csv", "_aggregate.csv");
        this.inputNumVariables = this.varsToAggregate.getItems();
        this.inputFactorVariables = this.groupingFactors.getItems();
        this.function = this.functionList.toArray(new String[this.functionList.size()]);
        if (this.function.length <= 0 || this.inputNumVariables.length <= 0 || this.inputFactorVariables.length <= 0) {
            MessageDialog.openWarning((Shell)this.getShell(), (String)"Invalid Input", (String)"Please make sure that you have entered atleast one for each: variable, factor and function. Thank you.");
        } else if (!this.toAppend) {
            String fileNameExtension = this.dataManipulationManager.getManipulatedFileNameExtension("_aggregate", this.fileModel.getProjectFile().getAbsolutePath());
            String newFile = this.fileModel.getProjectFile().getName().replaceAll(".csv", fileNameExtension);
            ProjectExplorerView.rJavaManager.getRJavaDataManipulationManager().aggregateData(fileName, newFileName, this.inputNumVariables, this.inputFactorVariables, this.function, this.toAppend);
            this.close();
            ProjectExplorerManager projExpMan = new ProjectExplorerManager();
            PartStackHandler.execute(projExpMan.createNewProjectExplorerModel((ProjectExplorerTreeNodeModel)this.fileModel.getParentTreeItem().getData(), newFile));
        } else {
            newFileName = fileName;
            PartStackHandler.setPartModified(this.fileModel.getProjectFile().getAbsolutePath());
            ProjectExplorerView.rJavaManager.getRJavaDataManipulationManager().aggregateData(fileName, newFileName, this.inputNumVariables, this.inputFactorVariables, this.function, this.toAppend);
            Table fileTable = DataManipulationManager.getActiveTable(this.fileModel.getProjectFile().getAbsolutePath());
            DataManipulationManager.removeTable(fileTable);
            this.close();
            WindowDialogControlUtil.closeAllWindowDialogOfFile(this.fileModel.getProjectFile().getAbsolutePath());
            System.out.println("overwrite and reopen");
            PartStackHandler.reOpenPart(this.fileModel);
        }
    }

    protected Point getInitialSize() {
        return new Point(679, 503);
    }

    protected boolean isResizable() {
        return true;
    }
}

