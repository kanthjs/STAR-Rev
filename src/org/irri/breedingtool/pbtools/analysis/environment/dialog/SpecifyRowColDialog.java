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
 *  org.eclipse.swt.widgets.Label
 *  org.eclipse.swt.widgets.Layout
 *  org.eclipse.swt.widgets.List
 *  org.eclipse.swt.widgets.Shell
 */
package org.irri.breedingtool.pbtools.analysis.environment.dialog;

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
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.irri.breedingtool.manager.impl.DataManipulationManager;

public class SpecifyRowColDialog
extends Dialog {
    private String[] levelItems;
    private String row;
    private String column;
    private List colVar;
    private List rowVar;
    private Button addRowBtn;
    private DataManipulationManager dataManipulationManager = new DataManipulationManager();
    private Button addColBtn;

    public SpecifyRowColDialog(Shell parentShell, String[] levelItems) {
        super(parentShell);
        this.levelItems = levelItems;
    }

    protected Control createDialogArea(Composite parent) {
        Composite container = (Composite)super.createDialogArea(parent);
        this.getShell().setText("Specify Row/Column Variables");
        Composite composite = new Composite(container, 0);
        composite.setLayout((Layout)new GridLayout(4, false));
        composite.setLayoutData((Object)new GridData(4, 4, true, true, 1, 1));
        Label lblVariables = new Label(composite, 0);
        lblVariables.setText("Variables:");
        new Label(composite, 0);
        new Label(composite, 0);
        new Label(composite, 0);
        List varList = new List(composite, 2048);
        GridData gd_varList = new GridData(4, 4, true, true, 1, 3);
        gd_varList.widthHint = 103;
        varList.setItems(this.levelItems);
        varList.setLayoutData((Object)gd_varList);
        varList.addMouseListener(new MouseListener(){

            public void mouseDoubleClick(MouseEvent e) {
            }

            public void mouseDown(MouseEvent e) {
                if (varList.getSelectionCount() > 0) {
                    SpecifyRowColDialog.this.rowVar.setSelection(-1);
                    SpecifyRowColDialog.this.colVar.setSelection(-1);
                    SpecifyRowColDialog.this.addRowBtn.setText("Add");
                    SpecifyRowColDialog.this.addColBtn.setText("Add");
                    if (SpecifyRowColDialog.this.rowVar.getItemCount() < 1) {
                        SpecifyRowColDialog.this.addRowBtn.setEnabled(true);
                    } else {
                        SpecifyRowColDialog.this.addRowBtn.setEnabled(false);
                    }
                    if (SpecifyRowColDialog.this.colVar.getItemCount() < 1) {
                        SpecifyRowColDialog.this.addColBtn.setEnabled(true);
                    } else {
                        SpecifyRowColDialog.this.addColBtn.setEnabled(false);
                    }
                }
            }

            public void mouseUp(MouseEvent e) {
            }
        });
        new Label(composite, 0);
        new Label(composite, 0);
        new Label(composite, 0);
        this.addRowBtn = new Button(composite, 0);
        GridData gd_addRowBtn = new GridData(4, 0x1000000, false, false, 1, 1);
        gd_addRowBtn.widthHint = 55;
        this.addRowBtn.setLayoutData((Object)gd_addRowBtn);
        this.addRowBtn.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (varList.getSelectionCount() > 0) {
                    String[] selectedNumVars = varList.getSelection();
                    int i = 0;
                    while (i < selectedNumVars.length) {
                        SpecifyRowColDialog.this.rowVar.add(selectedNumVars[i]);
                        ++i;
                    }
                    varList.remove(varList.getSelectionIndices());
                } else {
                    String[] selectedNumVars = SpecifyRowColDialog.this.rowVar.getSelection();
                    int i = 0;
                    while (i < selectedNumVars.length) {
                        varList.add(selectedNumVars[i]);
                        ++i;
                    }
                    SpecifyRowColDialog.this.rowVar.remove(SpecifyRowColDialog.this.rowVar.getSelectionIndices());
                }
                SpecifyRowColDialog.this.addRowBtn.setEnabled(false);
            }
        });
        this.addRowBtn.setText("Add");
        Label lblRowVariable = new Label(composite, 0);
        lblRowVariable.setText("Field Row Variable:");
        this.rowVar = new List(composite, 2048);
        GridData gd_rowVar = new GridData(4, 0x1000000, true, false, 1, 1);
        gd_rowVar.heightHint = 26;
        this.rowVar.setLayoutData((Object)gd_rowVar);
        this.rowVar.addMouseListener(new MouseListener(){

            public void mouseDoubleClick(MouseEvent e) {
                SpecifyRowColDialog.this.dataManipulationManager.moveSelectedStringFromTo(SpecifyRowColDialog.this.rowVar, varList);
                SpecifyRowColDialog.this.addRowBtn.setEnabled(false);
            }

            public void mouseDown(MouseEvent e) {
                if (SpecifyRowColDialog.this.rowVar.getSelectionIndex() > -1) {
                    SpecifyRowColDialog.this.addRowBtn.setEnabled(true);
                    varList.setSelection(-1);
                    SpecifyRowColDialog.this.rowVar.getSelection();
                    SpecifyRowColDialog.this.addRowBtn.setText("Remove");
                    SpecifyRowColDialog.this.addRowBtn.setEnabled(true);
                }
            }

            public void mouseUp(MouseEvent e) {
            }
        });
        this.addColBtn = new Button(composite, 0);
        this.addColBtn.setLayoutData((Object)new GridData(4, 0x1000000, false, false, 1, 1));
        this.addColBtn.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (varList.getSelectionCount() > 0) {
                    String[] selectedNumVars = varList.getSelection();
                    int i = 0;
                    while (i < selectedNumVars.length) {
                        SpecifyRowColDialog.this.colVar.add(selectedNumVars[i]);
                        ++i;
                    }
                    varList.remove(varList.getSelectionIndices());
                } else {
                    String[] selectedNumVars = SpecifyRowColDialog.this.colVar.getSelection();
                    int i = 0;
                    while (i < selectedNumVars.length) {
                        varList.add(selectedNumVars[i]);
                        ++i;
                    }
                    SpecifyRowColDialog.this.colVar.remove(SpecifyRowColDialog.this.colVar.getSelectionIndices());
                }
                SpecifyRowColDialog.this.addColBtn.setEnabled(false);
            }
        });
        this.addColBtn.setText("Add");
        Label lblColumnVariable = new Label(composite, 0);
        lblColumnVariable.setText("Field Column Variable:");
        this.colVar = new List(composite, 2048);
        GridData gd_colVar = new GridData(4, 0x1000000, true, false, 1, 1);
        gd_colVar.heightHint = 26;
        this.colVar.setLayoutData((Object)gd_colVar);
        this.colVar.addMouseListener(new MouseListener(){

            public void mouseDoubleClick(MouseEvent e) {
                SpecifyRowColDialog.this.dataManipulationManager.moveSelectedStringFromTo(SpecifyRowColDialog.this.colVar, varList);
                SpecifyRowColDialog.this.addColBtn.setEnabled(false);
            }

            public void mouseDown(MouseEvent e) {
                if (SpecifyRowColDialog.this.colVar.getSelectionIndex() > -1) {
                    SpecifyRowColDialog.this.addColBtn.setEnabled(true);
                    varList.setSelection(-1);
                    SpecifyRowColDialog.this.colVar.getSelection();
                    SpecifyRowColDialog.this.addColBtn.setText("Remove");
                    SpecifyRowColDialog.this.addColBtn.setEnabled(true);
                }
            }

            public void mouseUp(MouseEvent e) {
            }
        });
        return container;
    }

    public String getHeatRow() {
        return this.row;
    }

    public String getHeatColumn() {
        return this.column;
    }

    protected void okPressed() {
        if (this.rowVar.getItemCount() < 1) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"Please add a Row variable.");
        } else if (this.colVar.getItemCount() < 1) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)"Please add a Column variable.");
        } else {
            this.row = this.rowVar.getItem(0);
            this.column = this.colVar.getItem(0);
            this.close();
        }
    }

    protected void createButtonsForButtonBar(Composite parent) {
        this.createButton(parent, 0, IDialogConstants.OK_LABEL, true);
        this.createButton(parent, 1, IDialogConstants.CANCEL_LABEL, false);
    }

    protected Point getInitialSize() {
        return new Point(495, 314);
    }
}

