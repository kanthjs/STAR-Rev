/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.jface.dialogs.MessageDialog
 *  org.eclipse.swt.events.MouseEvent
 *  org.eclipse.swt.events.MouseListener
 *  org.eclipse.swt.events.SelectionAdapter
 *  org.eclipse.swt.events.SelectionEvent
 *  org.eclipse.swt.events.SelectionListener
 *  org.eclipse.swt.widgets.Button
 *  org.eclipse.swt.widgets.Composite
 *  org.eclipse.swt.widgets.Display
 *  org.eclipse.swt.widgets.List
 *  org.eclipse.swt.widgets.Shell
 *  org.eclipse.swt.widgets.Text
 */
package org.irri.breedingtool.utility;

import java.io.File;
import java.util.ArrayList;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;
import org.irri.breedingtool.manager.impl.DataManipulationManager;
import org.irri.breedingtool.utility.DialogListValidator;
import org.irri.breedingtool.utility.StarAnalysisUtilities;

public class DialogFormUtility {
    private final String SET_TO_NUMERIC = "Set to Numeric";
    private final String SET_TO_FACTOR = "Set to Factor";

    public int getVariableLevels(String filePath, String variableName) {
        DataManipulationManager dataMan = new DataManipulationManager();
        return DataManipulationManager.getEnvtLevels(variableName, new File(filePath)).length;
    }

    public void setCheckBoxesToBoolean(boolean value, Composite ... composites) {
        Composite[] compositeArray = composites;
        int n = composites.length;
        int n2 = 0;
        while (n2 < n) {
            Composite sets = compositeArray[n2];
            int i = 0;
            while (i < sets.getChildren().length) {
                if (sets.getChildren()[i] instanceof Button) {
                    ((Button)sets.getChildren()[i]).setSelection(value);
                } else if (sets.getChildren()[i] instanceof Composite) {
                    this.setCheckBoxesToBoolean(value, (Composite)sets.getChildren()[i]);
                }
                ++i;
            }
            ++n2;
        }
    }

    public void initializeList(List lstFrom, List lstTo, Button btnAdd, Button btnRemove) {
        btnAdd.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                DialogFormUtility.this.exchangeList(lstFrom, lstTo);
            }
        });
        btnRemove.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                DialogFormUtility.this.exchangeList(lstTo, lstFrom);
            }
        });
    }

    public void initializeVariableMoveList(List lstNumericVariables, List lstFactorVariables, Button btnMove, String filePath) {
        btnMove.setEnabled(false);
        lstNumericVariables.addSelectionListener(new SelectionListener(){

            public void widgetSelected(SelectionEvent e) {
                btnMove.setText("Set to Factor");
                btnMove.setEnabled(true);
                lstFactorVariables.setSelection(-1);
            }

            public void widgetDefaultSelected(SelectionEvent e) {
            }
        });
        lstFactorVariables.addSelectionListener(new SelectionListener(){
            private DataManipulationManager dataManipulationManager = new DataManipulationManager();

            public void widgetSelected(SelectionEvent e) {
                new DataManipulationManager();
                btnMove.setText("Set to Numeric");
                lstNumericVariables.setSelection(-1);
                int i = lstFactorVariables.getSelectionCount() - 1;
                while (i >= 0) {
                    String isNumeric = this.dataManipulationManager.isNumeric(filePath.replaceAll("\\\\", "/"), lstFactorVariables.getSelection()[i]);
                    if (isNumeric.equals("TRUE")) {
                        btnMove.setEnabled(true);
                    } else {
                        btnMove.setEnabled(false);
                    }
                    --i;
                }
            }

            public void widgetDefaultSelected(SelectionEvent e) {
            }
        });
        btnMove.addSelectionListener((SelectionListener)new SelectionAdapter(){
            private DataManipulationManager dataManipulationManager = new DataManipulationManager();

            public void widgetSelected(SelectionEvent e) {
                DataManipulationManager dataMan = new DataManipulationManager();
                if (btnMove.getText().equals("Set to Factor")) {
                    int i = 0;
                    while (i < lstNumericVariables.getSelectionCount()) {
                        dataMan.editVariableType(filePath, dataMan.getVarInfoFromFile(filePath), lstNumericVariables.getSelection()[i], "Factor");
                        ++i;
                    }
                    DialogFormUtility.this.exchangeList(lstNumericVariables, lstFactorVariables);
                } else if (btnMove.getText().equals("Set to Numeric")) {
                    new ArrayList();
                    int i = lstFactorVariables.getSelectionCount() - 1;
                    while (i >= 0) {
                        String isNumeric = this.dataManipulationManager.isNumeric(filePath.replaceAll("\\\\", "/"), lstFactorVariables.getSelection()[i]);
                        if (isNumeric.equals("TRUE")) {
                            dataMan.editVariableType(filePath, dataMan.getVarInfoFromFile(filePath), lstFactorVariables.getSelection()[i], "Numeric");
                        } else {
                            lstFactorVariables.deselect(lstFactorVariables.getSelectionIndices()[i]);
                        }
                        --i;
                    }
                    DialogFormUtility.this.exchangeList(lstFactorVariables, lstNumericVariables);
                }
                btnMove.setEnabled(false);
            }
        });
    }

    public void initializeVariableMoveList(List lstNumericVariables, List lstFactorVariables, Button btnMove, String filePath, int factorLevel) {
        btnMove.setEnabled(false);
        lstNumericVariables.addSelectionListener(new SelectionListener(){

            public void widgetSelected(SelectionEvent e) {
                btnMove.setText("Set to Factor");
                btnMove.setEnabled(true);
                lstFactorVariables.setSelection(-1);
            }

            public void widgetDefaultSelected(SelectionEvent e) {
                btnMove.setText("Set to Factor");
                btnMove.setEnabled(true);
                lstFactorVariables.setSelection(-1);
            }
        });
        lstFactorVariables.addSelectionListener(new SelectionListener(){

            public void widgetSelected(SelectionEvent e) {
                btnMove.setText("Set to Numeric");
                btnMove.setEnabled(true);
            }

            public void widgetDefaultSelected(SelectionEvent e) {
                btnMove.setText("Set to Numeric");
                btnMove.setEnabled(true);
            }
        });
        btnMove.addSelectionListener((SelectionListener)new SelectionAdapter(){
            private DataManipulationManager dataManipulationManager = new DataManipulationManager();

            public void widgetSelected(SelectionEvent e) {
                DataManipulationManager dataMan = new DataManipulationManager();
                if (btnMove.getText().equals("Set to Factor")) {
                    int i = 0;
                    while (i < lstNumericVariables.getSelectionCount()) {
                        if (StarAnalysisUtilities.getTotalVariableLevels(lstNumericVariables.getSelection()[i], filePath) != factorLevel) {
                            MessageDialog.openError((Shell)Display.getCurrent().getActiveShell(), (String)"Convering variable error", (String)("Could not move variable " + lstNumericVariables.getSelection()[i] + "  to factor. FactorLevel must be equal to (" + factorLevel + ")"));
                            return;
                        }
                        dataMan.editVariableType(filePath, dataMan.getVarInfoFromFile(filePath), lstNumericVariables.getSelection()[i], "Factor");
                        ++i;
                    }
                    DialogFormUtility.this.exchangeList(lstNumericVariables, lstFactorVariables);
                } else if (btnMove.getText().equals("Set to Numeric")) {
                    int i = 0;
                    while (i < lstFactorVariables.getSelectionCount()) {
                        String isNumeric = this.dataManipulationManager.isNumeric(filePath.replaceAll("\\\\", "/"), lstFactorVariables.getSelection()[i]);
                        if (isNumeric.equals("TRUE")) {
                            dataMan.editVariableType(filePath, dataMan.getVarInfoFromFile(filePath), lstFactorVariables.getSelection()[i], "Numeric");
                        } else {
                            lstFactorVariables.deselect(lstFactorVariables.getSelectionIndices()[i]);
                        }
                        ++i;
                    }
                    DialogFormUtility.this.exchangeList(lstFactorVariables, lstNumericVariables);
                }
                btnMove.setEnabled(false);
            }
        });
    }

    public void initializeSingleButtonList(List lstFrom, List lstTo, Button btnAddRemove) {
        btnAddRemove.setEnabled(false);
        lstFrom.addSelectionListener(new SelectionListener(){

            public void widgetSelected(SelectionEvent e) {
                if (lstFrom.getSelectionCount() > 0) {
                    lstTo.setSelection(-1);
                    btnAddRemove.setText("Add");
                    btnAddRemove.setEnabled(true);
                }
            }

            public void widgetDefaultSelected(SelectionEvent e) {
                if (lstFrom.getSelectionCount() > 0) {
                    lstTo.setSelection(-1);
                    btnAddRemove.setText("Add");
                    btnAddRemove.setEnabled(true);
                }
            }
        });
        lstTo.addSelectionListener(new SelectionListener(){

            public void widgetSelected(SelectionEvent e) {
                if (lstTo.getSelectionCount() > 0) {
                    lstFrom.setSelection(-1);
                    btnAddRemove.setText("Remove");
                    btnAddRemove.setEnabled(true);
                }
            }

            public void widgetDefaultSelected(SelectionEvent e) {
                if (lstTo.getSelectionCount() > 0) {
                    lstFrom.setSelection(-1);
                    btnAddRemove.setText("Remove");
                    btnAddRemove.setEnabled(true);
                }
            }
        });
        btnAddRemove.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (btnAddRemove.getText().equals("Add") && lstFrom.getSelectionCount() > 0) {
                    DialogFormUtility.this.exchangeList(lstFrom, lstTo);
                }
                if (btnAddRemove.getText().equals("Remove") && lstTo.getSelectionCount() > 0) {
                    DialogFormUtility.this.exchangeList(lstTo, lstFrom);
                }
                btnAddRemove.setEnabled(false);
            }
        });
        lstFrom.addMouseListener(new MouseListener(){

            public void mouseDoubleClick(MouseEvent e) {
                DialogFormUtility.this.exchangeList(lstFrom, lstTo);
                btnAddRemove.setEnabled(false);
            }

            public void mouseDown(MouseEvent e) {
            }

            public void mouseUp(MouseEvent e) {
            }
        });
        lstTo.addMouseListener(new MouseListener(){

            public void mouseDoubleClick(MouseEvent e) {
                DialogFormUtility.this.exchangeList(lstTo, lstFrom);
                btnAddRemove.setEnabled(false);
            }

            public void mouseDown(MouseEvent e) {
            }

            public void mouseUp(MouseEvent e) {
            }
        });
    }

    public void initializeSingleButtonList(List lstFrom, List lstTo, Button btnMove, Button btnAddRemove) {
        btnAddRemove.setEnabled(false);
        btnMove.setEnabled(false);
        lstFrom.addSelectionListener(new SelectionListener(){

            public void widgetSelected(SelectionEvent e) {
                if (lstFrom.getSelectionCount() > 0) {
                    lstTo.setSelection(-1);
                    btnAddRemove.setText("Add");
                    btnAddRemove.setEnabled(true);
                }
            }

            public void widgetDefaultSelected(SelectionEvent e) {
                if (lstFrom.getSelectionCount() > 0) {
                    lstTo.setSelection(-1);
                    btnAddRemove.setText("Add");
                    btnAddRemove.setEnabled(true);
                }
            }
        });
        lstTo.addSelectionListener(new SelectionListener(){

            public void widgetSelected(SelectionEvent e) {
                if (lstTo.getSelectionCount() > 0) {
                    lstFrom.setSelection(-1);
                    btnAddRemove.setText("Remove");
                    btnAddRemove.setEnabled(true);
                }
            }

            public void widgetDefaultSelected(SelectionEvent e) {
                if (lstTo.getSelectionCount() > 0) {
                    lstFrom.setSelection(-1);
                    btnAddRemove.setText("Remove");
                    btnAddRemove.setEnabled(true);
                }
            }
        });
        btnAddRemove.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (btnAddRemove.getText().equals("Add") && lstFrom.getSelectionCount() > 0) {
                    DialogFormUtility.this.exchangeList(lstFrom, lstTo);
                }
                if (btnAddRemove.getText().equals("Remove") && lstTo.getSelectionCount() > 0) {
                    DialogFormUtility.this.exchangeList(lstTo, lstFrom);
                }
                btnAddRemove.setEnabled(false);
                btnMove.setEnabled(false);
            }
        });
        lstFrom.addMouseListener(new MouseListener(){

            public void mouseDoubleClick(MouseEvent e) {
                System.out.println(btnMove.getText());
                btnMove.setEnabled(false);
                System.out.println(btnMove.isEnabled());
                DialogFormUtility.this.exchangeList(lstFrom, lstTo);
                btnAddRemove.setEnabled(false);
            }

            public void mouseDown(MouseEvent e) {
            }

            public void mouseUp(MouseEvent e) {
            }
        });
        lstTo.addMouseListener(new MouseListener(){

            public void mouseDoubleClick(MouseEvent e) {
                DialogFormUtility.this.exchangeList(lstTo, lstFrom);
                btnAddRemove.setEnabled(false);
                btnMove.setEnabled(false);
            }

            public void mouseDown(MouseEvent e) {
            }

            public void mouseUp(MouseEvent e) {
            }
        });
    }

    public void initializeSingleButtonList(List lstFrom, List lstTo, Button btnAddRemove, DialogListValidator lstValidator) {
        btnAddRemove.setEnabled(false);
        lstFrom.addSelectionListener(new SelectionListener(){

            public void widgetSelected(SelectionEvent e) {
                if (lstFrom.getSelectionCount() > 0 && lstValidator.validate()) {
                    lstTo.setSelection(-1);
                    btnAddRemove.setText("Add");
                    btnAddRemove.setEnabled(true);
                }
            }

            public void widgetDefaultSelected(SelectionEvent e) {
                if (lstFrom.getSelectionCount() > 0 && lstValidator.validate()) {
                    lstTo.setSelection(-1);
                    btnAddRemove.setText("Add");
                    btnAddRemove.setEnabled(true);
                }
            }
        });
        lstTo.addSelectionListener(new SelectionListener(){

            public void widgetSelected(SelectionEvent e) {
                if (lstTo.getSelectionCount() > 0) {
                    lstFrom.setSelection(-1);
                    btnAddRemove.setText("Remove");
                    btnAddRemove.setEnabled(true);
                }
            }

            public void widgetDefaultSelected(SelectionEvent e) {
                if (lstTo.getSelectionCount() > 0) {
                    lstFrom.setSelection(-1);
                    btnAddRemove.setText("Remove");
                    btnAddRemove.setEnabled(true);
                }
            }
        });
        btnAddRemove.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (btnAddRemove.getText().equals("Add") && lstFrom.getSelectionCount() > 0 && lstValidator.performAddProcess()) {
                    DialogFormUtility.this.exchangeList(lstFrom, lstTo);
                    lstValidator.postAddProcess();
                }
                if (btnAddRemove.getText().equals("Remove") && lstTo.getSelectionCount() > 0 && lstValidator.performRemoveProcess()) {
                    DialogFormUtility.this.exchangeList(lstTo, lstFrom);
                    lstValidator.postRemoveProcess();
                }
                btnAddRemove.setEnabled(false);
            }
        });
        lstFrom.addMouseListener(new MouseListener(){

            public void mouseDoubleClick(MouseEvent e) {
                if (lstValidator.performAddProcess()) {
                    DialogFormUtility.this.exchangeList(lstFrom, lstTo);
                    btnAddRemove.setEnabled(false);
                    lstValidator.postAddProcess();
                }
            }

            public void mouseDown(MouseEvent e) {
            }

            public void mouseUp(MouseEvent e) {
            }
        });
        lstTo.addMouseListener(new MouseListener(){

            public void mouseDoubleClick(MouseEvent e) {
                if (lstValidator.performRemoveProcess()) {
                    DialogFormUtility.this.exchangeList(lstTo, lstFrom);
                    btnAddRemove.setEnabled(false);
                    lstValidator.postRemoveProcess();
                }
            }

            public void mouseDown(MouseEvent e) {
            }

            public void mouseUp(MouseEvent e) {
            }
        });
    }

    public void initializeSingleSelectionList(List lstFrom, Text txtTo, Button btnAddRemove) {
        txtTo.setEditable(false);
        btnAddRemove.setEnabled(false);
        lstFrom.addSelectionListener(new SelectionListener(){

            public void widgetSelected(SelectionEvent e) {
                if (lstFrom.getSelectionCount() > 0 && txtTo.getText().isEmpty()) {
                    btnAddRemove.setText("Add");
                    btnAddRemove.setEnabled(true);
                }
            }

            public void widgetDefaultSelected(SelectionEvent e) {
                if (lstFrom.getSelectionCount() > 0 && txtTo.getText().isEmpty()) {
                    btnAddRemove.setText("Add");
                    btnAddRemove.setEnabled(true);
                }
            }
        });
        btnAddRemove.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (lstFrom.getSelectionCount() > 1) {
                    MessageDialog.openError((Shell)Display.getCurrent().getActiveShell(), (String)"Error", (String)"Please select one item only.");
                    return;
                }
                if (btnAddRemove.getText().equals("Add") && lstFrom.getSelectionCount() > 0) {
                    txtTo.setText(lstFrom.getSelection()[0]);
                    txtTo.setData((Object)lstFrom.getSelectionIndices()[0]);
                    lstFrom.remove(lstFrom.getSelectionIndex());
                    btnAddRemove.setText("Remove");
                    lstFrom.deselectAll();
                } else if (btnAddRemove.getText().equals("Remove") && !txtTo.getText().equals("")) {
                    try {
                        lstFrom.add(txtTo.getText(), ((Integer)txtTo.getData()).intValue());
                    }
                    catch (Exception exception) {
                        lstFrom.add(txtTo.getText());
                    }
                    txtTo.setText("");
                    btnAddRemove.setText("Add");
                }
                btnAddRemove.setEnabled(false);
            }
        });
        txtTo.addMouseListener(new MouseListener(){

            public void mouseDoubleClick(MouseEvent e) {
                if (!txtTo.getText().isEmpty()) {
                    lstFrom.add(txtTo.getText());
                    txtTo.setText("");
                    btnAddRemove.setEnabled(false);
                }
            }

            public void mouseDown(MouseEvent e) {
                if (!txtTo.getText().isEmpty()) {
                    lstFrom.setSelection(-1);
                    btnAddRemove.setText("Remove");
                    btnAddRemove.setEnabled(true);
                }
            }

            public void mouseUp(MouseEvent e) {
            }
        });
    }

    public void initializeSingleSelectionList(List lstFrom, Text txtTo, Button btnMove, Button btnAddRemove) {
        txtTo.setEditable(false);
        btnAddRemove.setEnabled(false);
        btnMove.setEnabled(false);
        lstFrom.addSelectionListener(new SelectionListener(){

            public void widgetSelected(SelectionEvent e) {
                if (lstFrom.getSelectionCount() > 0 && txtTo.getText().isEmpty()) {
                    btnAddRemove.setText("Add");
                    btnAddRemove.setEnabled(true);
                }
            }

            public void widgetDefaultSelected(SelectionEvent e) {
                if (lstFrom.getSelectionCount() > 0 && txtTo.getText().isEmpty()) {
                    btnAddRemove.setText("Add");
                    btnAddRemove.setEnabled(true);
                }
            }
        });
        btnAddRemove.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (lstFrom.getSelectionCount() > 1) {
                    MessageDialog.openError((Shell)Display.getCurrent().getActiveShell(), (String)"Error", (String)"Please select one item only.");
                    return;
                }
                if (btnAddRemove.getText().equals("Add") && lstFrom.getSelectionCount() > 0) {
                    txtTo.setText(lstFrom.getSelection()[0]);
                    txtTo.setData((Object)lstFrom.getSelectionIndices()[0]);
                    lstFrom.remove(lstFrom.getSelectionIndex());
                    btnAddRemove.setText("Remove");
                    lstFrom.deselectAll();
                } else if (btnAddRemove.getText().equals("Remove") && !txtTo.getText().equals("")) {
                    try {
                        lstFrom.add(txtTo.getText(), ((Integer)txtTo.getData()).intValue());
                    }
                    catch (Exception exception) {
                        lstFrom.add(txtTo.getText());
                    }
                    txtTo.setText("");
                    btnAddRemove.setText("Add");
                }
                btnAddRemove.setEnabled(false);
                btnMove.setEnabled(false);
            }
        });
        txtTo.addMouseListener(new MouseListener(){

            public void mouseDoubleClick(MouseEvent e) {
                if (!txtTo.getText().isEmpty()) {
                    lstFrom.add(txtTo.getText());
                    txtTo.setText("");
                    btnAddRemove.setEnabled(false);
                    btnMove.setEnabled(false);
                }
            }

            public void mouseDown(MouseEvent e) {
                if (!txtTo.getText().isEmpty()) {
                    lstFrom.setSelection(-1);
                    btnAddRemove.setText("Remove");
                    btnAddRemove.setEnabled(true);
                    btnMove.setEnabled(false);
                }
            }

            public void mouseUp(MouseEvent e) {
            }
        });
    }

    public void initializeSingleSelectionList(List lstFrom, Text txtTo, Button btnAddRemove, DialogListValidator lstValidator) {
        txtTo.setEditable(false);
        txtTo.setBackground(SWTResourceManager.getColor(1));
        btnAddRemove.setEnabled(false);
        lstFrom.addSelectionListener(new SelectionListener(){

            public void widgetSelected(SelectionEvent e) {
                if (lstFrom.getSelectionCount() > 0 && lstValidator.validate() && txtTo.getText().isEmpty()) {
                    btnAddRemove.setText("Add");
                    btnAddRemove.setEnabled(true);
                }
            }

            public void widgetDefaultSelected(SelectionEvent e) {
                if (lstFrom.getSelectionCount() > 0 && lstValidator.validate() && txtTo.getText().isEmpty()) {
                    btnAddRemove.setText("Add");
                    btnAddRemove.setEnabled(true);
                }
            }
        });
        btnAddRemove.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (lstFrom.getSelectionCount() > 1) {
                    MessageDialog.openError((Shell)Display.getCurrent().getActiveShell(), (String)"Error", (String)"Please select one item only.");
                    return;
                }
                if (btnAddRemove.getText().equals("Add") && lstFrom.getSelectionCount() > 0) {
                    if (lstValidator.performAddProcess()) {
                        txtTo.setText(lstFrom.getSelection()[0]);
                        txtTo.setData((Object)lstFrom.getSelectionIndices()[0]);
                        lstFrom.remove(lstFrom.getSelectionIndex());
                        btnAddRemove.setText("Remove");
                        lstFrom.deselectAll();
                    }
                } else if (btnAddRemove.getText().equals("Remove") && !txtTo.getText().equals("") && lstValidator.performRemoveProcess()) {
                    try {
                        lstFrom.add(txtTo.getText(), ((Integer)txtTo.getData()).intValue());
                    }
                    catch (Exception exception) {
                        lstFrom.add(txtTo.getText());
                    }
                    txtTo.setText("");
                    btnAddRemove.setText("Add");
                }
                btnAddRemove.setEnabled(false);
            }
        });
        txtTo.addMouseListener(new MouseListener(){

            public void mouseDoubleClick(MouseEvent e) {
                if (!txtTo.getText().isEmpty()) {
                    lstFrom.add(txtTo.getText());
                    txtTo.setText("");
                    btnAddRemove.setEnabled(false);
                }
            }

            public void mouseDown(MouseEvent e) {
                if (!txtTo.getText().isEmpty()) {
                    lstFrom.setSelection(-1);
                    btnAddRemove.setText("Remove");
                    btnAddRemove.setEnabled(true);
                }
            }

            public void mouseUp(MouseEvent e) {
            }
        });
    }

    public void initializeSingleSelectionList(List lstFrom, Text txtTo, Button btnMove, Button btnAddRemove, DialogListValidator lstValidator) {
        txtTo.setEditable(false);
        txtTo.setBackground(SWTResourceManager.getColor(1));
        btnAddRemove.setEnabled(false);
        btnMove.setEnabled(false);
        lstFrom.addSelectionListener(new SelectionListener(){

            public void widgetSelected(SelectionEvent e) {
                if (lstFrom.getSelectionCount() > 0 && lstValidator.validate() && txtTo.getText().isEmpty()) {
                    btnAddRemove.setText("Add");
                    btnAddRemove.setEnabled(true);
                }
            }

            public void widgetDefaultSelected(SelectionEvent e) {
                if (lstFrom.getSelectionCount() > 0 && lstValidator.validate() && txtTo.getText().isEmpty()) {
                    btnAddRemove.setText("Add");
                    btnAddRemove.setEnabled(true);
                }
            }
        });
        btnAddRemove.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                if (lstFrom.getSelectionCount() > 1) {
                    MessageDialog.openError((Shell)Display.getCurrent().getActiveShell(), (String)"Error", (String)"Please select one item only.");
                    return;
                }
                if (btnAddRemove.getText().equals("Add") && lstFrom.getSelectionCount() > 0) {
                    if (lstValidator.performAddProcess()) {
                        txtTo.setText(lstFrom.getSelection()[0]);
                        txtTo.setData((Object)lstFrom.getSelectionIndices()[0]);
                        lstFrom.remove(lstFrom.getSelectionIndex());
                        btnAddRemove.setText("Remove");
                        lstFrom.deselectAll();
                    }
                } else if (btnAddRemove.getText().equals("Remove") && !txtTo.getText().equals("") && lstValidator.performRemoveProcess()) {
                    try {
                        lstFrom.add(txtTo.getText(), ((Integer)txtTo.getData()).intValue());
                    }
                    catch (Exception exception) {
                        lstFrom.add(txtTo.getText());
                    }
                    txtTo.setText("");
                    btnAddRemove.setText("Add");
                }
                btnAddRemove.setEnabled(false);
                btnMove.setEnabled(false);
            }
        });
        txtTo.addMouseListener(new MouseListener(){

            public void mouseDoubleClick(MouseEvent e) {
                if (!txtTo.getText().isEmpty()) {
                    lstFrom.add(txtTo.getText());
                    txtTo.setText("");
                    btnAddRemove.setEnabled(false);
                    btnMove.setEnabled(false);
                }
            }

            public void mouseDown(MouseEvent e) {
                if (!txtTo.getText().isEmpty()) {
                    lstFrom.setSelection(-1);
                    btnAddRemove.setText("Remove");
                    btnAddRemove.setEnabled(true);
                    btnMove.setEnabled(false);
                }
            }

            public void mouseUp(MouseEvent e) {
            }
        });
    }

    public void initializeNumericList(List numericList, String filePath) {
        DataManipulationManager dataManipulationManager = new DataManipulationManager();
        ArrayList<String> s = dataManipulationManager.getVarInfoFromFile(filePath);
        int i = 0;
        while (i < s.size()) {
            if (s.get(i).toString().split(":")[1].equals("Numeric")) {
                numericList.add(s.get(i).toString().split(":")[0]);
            }
            ++i;
        }
    }

    public void initializeFactorList(List numericList, String filePath) {
        DataManipulationManager dataManipulationManager = new DataManipulationManager();
        ArrayList<String> s = dataManipulationManager.getVarInfoFromFile(filePath);
        int i = 0;
        while (i < s.size()) {
            if (s.get(i).toString().split(":")[1].equals("Factor")) {
                numericList.add(s.get(i).toString().split(":")[0]);
            }
            ++i;
        }
    }

    public void initializeVariableList(List numericList, String filePath) {
        DataManipulationManager dataManipulationManager = new DataManipulationManager();
        ArrayList<String> s = dataManipulationManager.getVarInfoFromFile(filePath);
        int i = 0;
        while (i < s.size()) {
            numericList.add(s.get(i).toString().split(":")[0]);
            ++i;
        }
    }

    public void initializeFactorList(List numericList, String filePath, int variableLevel) {
        DataManipulationManager dataManipulationManager = new DataManipulationManager();
        ArrayList<String> s = dataManipulationManager.getVarInfoFromFile(filePath);
        int i = 0;
        while (i < s.size()) {
            if (s.get(i).toString().split(":")[1].equals("Factor") && DataManipulationManager.getEnvtLevels(s.get(i).toString().split(":")[0], new File(filePath)).length == variableLevel) {
                numericList.add(s.get(i).toString().split(":")[0]);
            }
            ++i;
        }
    }

    public void exchangeList(List sourceList, List destinationList) {
        int i = 0;
        while (i < sourceList.getSelectionCount()) {
            destinationList.add(sourceList.getSelection()[i]);
            ++i;
        }
        sourceList.remove(sourceList.getSelectionIndices());
    }

    public void setCheckBoxesToBoolean(Composite[] composite, boolean value) {
        int x = 0;
        while (x < composite.length) {
            int i = 0;
            while (i < composite[x].getChildren().length) {
                ((Button)composite[x].getChildren()[i]).setSelection(value);
                ++i;
            }
            ++x;
        }
    }

    public void setEventCheckBoxes(Composite[] composite, SelectionAdapter eventListener) {
        int x = 0;
        while (x < composite.length) {
            int i = 0;
            while (i < composite[x].getChildren().length) {
                ((Button)composite[x].getChildren()[i]).addSelectionListener((SelectionListener)eventListener);
                ++i;
            }
            ++x;
        }
    }

    public boolean isCheckBoxesHasBoolean(Composite[] composite) {
        int x = 0;
        while (x < composite.length) {
            int i = 0;
            while (i < composite[x].getChildren().length) {
                if (((Button)composite[x].getChildren()[i]).getSelection()) {
                    return true;
                }
                ++i;
            }
            ++x;
        }
        return false;
    }

    public boolean isCheckBoxesHasBoolean(Composite composite) {
        int i = 0;
        while (i < composite.getChildren().length) {
            if (composite.getChildren()[i] instanceof Button) {
                if (((Button)composite.getChildren()[i]).getSelection()) {
                    return true;
                }
                if (composite.getChildren()[i] instanceof Composite && this.isCheckBoxesHasBoolean((Composite)composite.getChildren()[i])) {
                    return true;
                }
            }
            ++i;
        }
        return false;
    }

    public String[] getCheckBoxesValue(Button ... CheckBoxes) {
        ArrayList<String> returnValue = new ArrayList<String>();
        Button[] buttonArray = CheckBoxes;
        int n = CheckBoxes.length;
        int n2 = 0;
        while (n2 < n) {
            Button cBoxes = buttonArray[n2];
            if (cBoxes.getSelection()) {
                returnValue.add((String)cBoxes.getData());
            }
            ++n2;
        }
        return returnValue.toArray(new String[returnValue.size()]);
    }

    public String[] getCheckBoxesValue(Composite[] composite) {
        ArrayList<String> lstData = new ArrayList<String>();
        int x = 0;
        while (x < composite.length) {
            int i = 0;
            while (i < composite[x].getChildren().length) {
                if (((Button)composite[x].getChildren()[i]).getSelection()) {
                    String data = (String)((Button)composite[x].getChildren()[i]).getData();
                    lstData.add(data);
                }
                ++i;
            }
            ++x;
        }
        return lstData.toArray(new String[lstData.size()]);
    }

    public String[] getCheckBoxesValue(Composite composite) {
        ArrayList<String> lstData = new ArrayList<String>();
        int i = 0;
        while (i < composite.getChildren().length) {
            if (((Button)composite.getChildren()[i]).getSelection()) {
                String data = (String)((Button)composite.getChildren()[i]).getData();
                lstData.add(data);
            }
            ++i;
        }
        return lstData.toArray(new String[lstData.size()]);
    }
}

