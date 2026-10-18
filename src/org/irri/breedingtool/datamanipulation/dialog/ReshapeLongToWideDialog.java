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
 *  org.eclipse.swt.widgets.Label
 *  org.eclipse.swt.widgets.List
 *  org.eclipse.swt.widgets.Shell
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
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TreeItem;
import org.irri.breedingtool.application.handler.PartStackHandler;
import org.irri.breedingtool.application.model.ProjectExplorerTreeNodeModel;
import org.irri.breedingtool.manager.impl.DataManipulationManager;
import org.irri.breedingtool.manager.impl.ProjectExplorerManager;
import org.irri.breedingtool.projectexplorer.view.ProjectExplorerView;

public class ReshapeLongToWideDialog
extends Dialog {
    private ProjectExplorerTreeNodeModel fileModel;
    private ArrayList<String> varInfo;
    private List activeVarCombo;
    private List reshapeCombo;
    private List retainCombo;
    private List indexCombo;
    private Button addReshapeBtn;
    private Button addRetainBtn;
    private Button addIndexBtn;
    private DataManipulationManager dataManipulationManager = new DataManipulationManager();

    public ReshapeLongToWideDialog(Shell parentShell, ArrayList<String> varInfo, ProjectExplorerTreeNodeModel fileModel) {
        super(parentShell);
        this.fileModel = fileModel;
        this.varInfo = varInfo;
    }

    protected Control createDialogArea(Composite parent) {
        parent.getShell().setText("Reshape Data (Long to Wide): " + this.dataManipulationManager.getDisplayFileName(this.fileModel.getProjectFile().getAbsolutePath()));
        Composite container = (Composite)super.createDialogArea(parent);
        GridLayout gridLayout = (GridLayout)container.getLayout();
        gridLayout.numColumns = 3;
        Label lblVariablesInThe = new Label(container, 0);
        lblVariablesInThe.setLayoutData((Object)new GridData(4, 4, false, false, 1, 1));
        lblVariablesInThe.setText("Variables in the active data");
        new Label(container, 0);
        Label lblVariablesToReshape = new Label(container, 0);
        lblVariablesToReshape.setLayoutData((Object)new GridData(4, 4, false, false, 1, 1));
        lblVariablesToReshape.setText("Variable(s) to Reshape");
        this.activeVarCombo = new List(container, 2818);
        GridData gd_list_1 = new GridData(4, 4, true, true, 1, 5);
        gd_list_1.widthHint = 166;
        this.activeVarCombo.setLayoutData((Object)gd_list_1);
        this.populateActiveVarCombo();
        this.activeVarCombo.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                ReshapeLongToWideDialog.this.enableButtons(true);
            }
        });
        this.addReshapeBtn = new Button(container, 0);
        this.addReshapeBtn.setEnabled(false);
        this.addReshapeBtn.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (ReshapeLongToWideDialog.this.addReshapeBtn.getText().equals("Add")) {
                    if (ReshapeLongToWideDialog.this.activeVarCombo.getSelectionCount() > 0) {
                        ReshapeLongToWideDialog.this.dataManipulationManager.moveSelectedStringFromTo(ReshapeLongToWideDialog.this.activeVarCombo, ReshapeLongToWideDialog.this.reshapeCombo);
                        if (ReshapeLongToWideDialog.this.activeVarCombo.getItemCount() < 1) {
                            ReshapeLongToWideDialog.this.enableButtons(false);
                        }
                    }
                } else {
                    ReshapeLongToWideDialog.this.dataManipulationManager.moveSelectedStringFromTo(ReshapeLongToWideDialog.this.reshapeCombo, ReshapeLongToWideDialog.this.activeVarCombo);
                }
                ReshapeLongToWideDialog.this.addReshapeBtn.setEnabled(false);
            }
        });
        GridData gd_addReshapeBtn = new GridData(16384, 0x1000000, false, false, 1, 1);
        gd_addReshapeBtn.widthHint = 62;
        this.addReshapeBtn.setLayoutData((Object)gd_addReshapeBtn);
        this.addReshapeBtn.setText("Add");
        this.reshapeCombo = new List(container, 2816);
        GridData gd_reshapeCombo = new GridData(4, 4, true, true, 1, 1);
        gd_reshapeCombo.heightHint = 55;
        gd_reshapeCombo.widthHint = 180;
        this.reshapeCombo.setLayoutData((Object)gd_reshapeCombo);
        this.reshapeCombo.addMouseListener(new MouseListener(){

            public void mouseDoubleClick(MouseEvent e) {
                ReshapeLongToWideDialog.this.dataManipulationManager.moveSelectedStringFromTo(ReshapeLongToWideDialog.this.reshapeCombo, ReshapeLongToWideDialog.this.activeVarCombo);
                ReshapeLongToWideDialog.this.addReshapeBtn.setEnabled(false);
            }

            public void mouseDown(MouseEvent e) {
                if (ReshapeLongToWideDialog.this.reshapeCombo.getSelectionIndex() > -1) {
                    ReshapeLongToWideDialog.this.addReshapeBtn.setEnabled(true);
                    ReshapeLongToWideDialog.this.activeVarCombo.setSelection(-1);
                    ReshapeLongToWideDialog.this.reshapeCombo.getSelection();
                    ReshapeLongToWideDialog.this.addReshapeBtn.setText("Remove");
                    ReshapeLongToWideDialog.this.addReshapeBtn.setEnabled(true);
                }
            }

            public void mouseUp(MouseEvent e) {
            }
        });
        new Label(container, 0);
        Label lblVariablesToRetain = new Label(container, 0);
        lblVariablesToRetain.setLayoutData((Object)new GridData(4, 4, false, false, 1, 1));
        lblVariablesToRetain.setText("Variable(s) to Retain");
        this.addRetainBtn = new Button(container, 0);
        this.addRetainBtn.setEnabled(false);
        this.addRetainBtn.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (ReshapeLongToWideDialog.this.addRetainBtn.getText().equals("Add")) {
                    if (ReshapeLongToWideDialog.this.activeVarCombo.getSelectionCount() > 0) {
                        ReshapeLongToWideDialog.this.dataManipulationManager.moveSelectedStringFromTo(ReshapeLongToWideDialog.this.activeVarCombo, ReshapeLongToWideDialog.this.retainCombo);
                        if (ReshapeLongToWideDialog.this.activeVarCombo.getItemCount() < 1) {
                            ReshapeLongToWideDialog.this.enableButtons(false);
                        }
                    }
                } else {
                    ReshapeLongToWideDialog.this.dataManipulationManager.moveSelectedStringFromTo(ReshapeLongToWideDialog.this.retainCombo, ReshapeLongToWideDialog.this.activeVarCombo);
                }
                ReshapeLongToWideDialog.this.addRetainBtn.setEnabled(false);
            }
        });
        this.addRetainBtn.setLayoutData((Object)new GridData(4, 0x1000000, false, false, 1, 1));
        this.addRetainBtn.setText("Add");
        this.retainCombo = new List(container, 2816);
        GridData gd_retainCombo = new GridData(4, 4, true, true, 1, 1);
        gd_retainCombo.heightHint = 49;
        gd_retainCombo.widthHint = 180;
        this.retainCombo.setLayoutData((Object)gd_retainCombo);
        this.retainCombo.addMouseListener(new MouseListener(){

            public void mouseDoubleClick(MouseEvent e) {
                ReshapeLongToWideDialog.this.dataManipulationManager.moveSelectedStringFromTo(ReshapeLongToWideDialog.this.retainCombo, ReshapeLongToWideDialog.this.activeVarCombo);
                ReshapeLongToWideDialog.this.addRetainBtn.setEnabled(false);
            }

            public void mouseDown(MouseEvent e) {
                if (ReshapeLongToWideDialog.this.retainCombo.getSelectionIndex() > -1) {
                    ReshapeLongToWideDialog.this.addRetainBtn.setEnabled(true);
                    ReshapeLongToWideDialog.this.activeVarCombo.setSelection(-1);
                    ReshapeLongToWideDialog.this.retainCombo.getSelection();
                    ReshapeLongToWideDialog.this.addRetainBtn.setText("Remove");
                    ReshapeLongToWideDialog.this.addRetainBtn.setEnabled(true);
                }
            }

            public void mouseUp(MouseEvent e) {
            }
        });
        new Label(container, 0);
        Label lblIndexFactor = new Label(container, 0);
        lblIndexFactor.setLayoutData((Object)new GridData(4, 4, false, false, 1, 1));
        lblIndexFactor.setText("Index Factor:");
        this.addIndexBtn = new Button(container, 0);
        this.addIndexBtn.setEnabled(false);
        this.addIndexBtn.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (ReshapeLongToWideDialog.this.addIndexBtn.getText().equals("Add")) {
                    if (ReshapeLongToWideDialog.this.activeVarCombo.getSelectionCount() > 0) {
                        ReshapeLongToWideDialog.this.dataManipulationManager.moveSelectedStringFromTo(ReshapeLongToWideDialog.this.activeVarCombo, ReshapeLongToWideDialog.this.indexCombo);
                        if (ReshapeLongToWideDialog.this.activeVarCombo.getItemCount() < 1) {
                            ReshapeLongToWideDialog.this.enableButtons(false);
                        }
                    }
                } else {
                    ReshapeLongToWideDialog.this.dataManipulationManager.moveSelectedStringFromTo(ReshapeLongToWideDialog.this.indexCombo, ReshapeLongToWideDialog.this.activeVarCombo);
                }
                ReshapeLongToWideDialog.this.addIndexBtn.setEnabled(false);
            }
        });
        this.addIndexBtn.setLayoutData((Object)new GridData(4, 0x1000000, false, false, 1, 1));
        this.addIndexBtn.setText("Add");
        this.indexCombo = new List(container, 2816);
        GridData gd_indexCombo = new GridData(4, 4, true, true, 1, 1);
        gd_indexCombo.heightHint = 55;
        gd_indexCombo.widthHint = 181;
        this.indexCombo.setLayoutData((Object)gd_indexCombo);
        this.indexCombo.addMouseListener(new MouseListener(){

            public void mouseDoubleClick(MouseEvent e) {
                ReshapeLongToWideDialog.this.dataManipulationManager.moveSelectedStringFromTo(ReshapeLongToWideDialog.this.indexCombo, ReshapeLongToWideDialog.this.activeVarCombo);
                ReshapeLongToWideDialog.this.addIndexBtn.setEnabled(false);
            }

            public void mouseDown(MouseEvent e) {
                if (ReshapeLongToWideDialog.this.indexCombo.getSelectionIndex() > -1) {
                    ReshapeLongToWideDialog.this.addIndexBtn.setEnabled(true);
                    ReshapeLongToWideDialog.this.activeVarCombo.setSelection(-1);
                    ReshapeLongToWideDialog.this.indexCombo.getSelection();
                    ReshapeLongToWideDialog.this.addIndexBtn.setText("Remove");
                    ReshapeLongToWideDialog.this.addIndexBtn.setEnabled(true);
                }
            }

            public void mouseUp(MouseEvent e) {
            }
        });
        return container;
    }

    protected void enableButtons(boolean state) {
        this.addReshapeBtn.setEnabled(state);
        this.addRetainBtn.setEnabled(state);
        this.addIndexBtn.setEnabled(state);
        this.addReshapeBtn.setText("Add");
        this.addRetainBtn.setText("Add");
        this.addIndexBtn.setText("Add");
        this.reshapeCombo.setSelection(-1);
        this.retainCombo.setSelection(-1);
        this.indexCombo.setSelection(-1);
    }

    private void populateActiveVarCombo() {
        for (String s : this.varInfo) {
            String[] tmp = s.split(":");
            this.activeVarCombo.add(tmp[0]);
            this.activeVarCombo.setData(tmp[0], (Object)tmp[1]);
        }
    }

    protected void okPressed() {
        block5: {
            String dataFileName = this.fileModel.getProjectFile().getAbsolutePath().toString().replaceAll("\\\\+", "/");
            String fileNameExtension = this.dataManipulationManager.getManipulatedFileNameExtension("_ReshapeToWide", this.fileModel.getProjectFile().getAbsolutePath());
            String newFileName = dataFileName.replaceAll(".csv", fileNameExtension);
            String[] reshapeVariables = this.reshapeCombo.getItems();
            String[] retainVariables = this.retainCombo.getItems();
            String[] indexFactor = this.indexCombo.getItems();
            if (retainVariables.length > 0 && indexFactor.length > 0) {
                ProjectExplorerView.rJavaManager.getRJavaDataManipulationManager().toWide(dataFileName, newFileName, reshapeVariables, retainVariables, indexFactor);
                this.close();
                ProjectExplorerManager projExpMan = new ProjectExplorerManager();
                String newFile = this.fileModel.getProjectFile().getName().replaceAll(".csv", fileNameExtension);
                try {
                    TreeItem sortedTree = projExpMan.getTreeNodeByName(this.fileModel.getParentTreeItem(), newFile);
                    if (((ProjectExplorerTreeNodeModel)sortedTree.getData()).getElementInStack() == null) {
                        PartStackHandler.execute(projExpMan.createNewProjectExplorerModel((ProjectExplorerTreeNodeModel)this.fileModel.getParentTreeItem().getData(), newFile));
                        break block5;
                    }
                    PartStackHandler.reOpenPart((ProjectExplorerTreeNodeModel)sortedTree.getData());
                }
                catch (NullPointerException nullPointerException) {
                    MessageDialog.openWarning((Shell)Display.getCurrent().getActiveShell(), (String)"Reshape Long to Wide was Unsuccessful", (String)"Failed to reshape variables from data.\n\n");
                }
            } else {
                MessageDialog.openWarning((Shell)this.getShell(), (String)"Incomplete Input", (String)"Please don't leave the \"VARIABLES TO RETAIN\" and \"INDEX FACTORS\" fields empty.");
            }
        }
    }

    protected void buttonPressed(int buttonId) {
        if (buttonId == 19) {
            this.addReshapeBtn.setEnabled(false);
            this.addRetainBtn.setEnabled(false);
            this.addIndexBtn.setEnabled(false);
            this.reshapeCombo.removeAll();
            this.retainCombo.removeAll();
            this.indexCombo.removeAll();
            this.activeVarCombo.removeAll();
            this.populateActiveVarCombo();
        }
        super.buttonPressed(buttonId);
    }

    protected void createButtonsForButtonBar(Composite parent) {
        Button button = this.createButton(parent, 19, "Reset", true);
        button.setText("Reset");
        this.createButton(parent, 0, IDialogConstants.OK_LABEL, true);
        this.createButton(parent, 1, IDialogConstants.CANCEL_LABEL, false);
    }

    protected Point getInitialSize() {
        return new Point(583, 443);
    }

    protected boolean isResizable() {
        return true;
    }
}

