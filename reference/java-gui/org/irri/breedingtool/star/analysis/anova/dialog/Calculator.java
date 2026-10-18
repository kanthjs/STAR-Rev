/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.jface.dialogs.Dialog
 *  org.eclipse.jface.dialogs.IDialogConstants
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
 *  org.eclipse.swt.widgets.Layout
 *  org.eclipse.swt.widgets.Shell
 *  org.eclipse.swt.widgets.Text
 */
package org.irri.breedingtool.star.analysis.anova.dialog;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
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
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class Calculator
extends Dialog {
    private Text displayText;
    private String displayString = "0.";
    private String memoryString = new String();
    private String operatorString = new String();
    private char calcChar = (char)32;
    private final String ERROR_STRING = "Error: ";
    private final String NAN_STRING = "Not a Number";
    private final String LONG_STRING = "Number too long";
    private final String INFINITY_STRING = "Infinity";
    private boolean clearDisplay = true;
    private static final int CLIPBOARD_ID = 22;

    public static void main(String[] a) {
        new Calculator(new Shell(new Display()));
    }

    public Calculator(Shell parentShell) {
        super(parentShell);
    }

    protected Control createDialogArea(Composite parent) {
        Composite container = (Composite)super.createDialogArea(parent);
        GridLayout calculatorGridLayout = new GridLayout();
        calculatorGridLayout.marginRight = 5;
        calculatorGridLayout.marginLeft = 5;
        calculatorGridLayout.marginBottom = 5;
        calculatorGridLayout.marginTop = 5;
        calculatorGridLayout.marginWidth = 10;
        calculatorGridLayout.marginHeight = 2;
        calculatorGridLayout.numColumns = 4;
        calculatorGridLayout.verticalSpacing = 2;
        calculatorGridLayout.makeColumnsEqualWidth = true;
        calculatorGridLayout.horizontalSpacing = 2;
        container.setLayout((Layout)calculatorGridLayout);
        this.displayText = new Text(container, 133120);
        this.displayText.setBackground(Display.getCurrent().getSystemColor(1));
        this.displayText.setEditable(false);
        this.displayText.setDoubleClickEnabled(false);
        this.displayText.setTextLimit(30);
        this.displayText.setText(this.displayString);
        this.displayText.setLayoutData((Object)new GridData(4, 128, true, false, 4, 1));
        Button msButton = new Button(container, 0);
        msButton.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                Calculator.this.updateMemory('S');
            }
        });
        msButton.setToolTipText("Save value to Memory");
        msButton.setLayoutData((Object)new GridData(4, 4, false, false));
        msButton.setText("MS");
        Button mcButton = new Button(container, 0);
        mcButton.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                Calculator.this.updateDisplay('D');
            }
        });
        mcButton.setLayoutData((Object)new GridData(4, 4, false, false));
        mcButton.setToolTipText("Clear Memory");
        mcButton.setText("MC");
        Button clearButton = new Button(container, 0);
        clearButton.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                Calculator.this.updateDisplay('C');
            }
        });
        clearButton.setLayoutData((Object)new GridData(4, 4, false, false));
        clearButton.setToolTipText("Clear all Calculator Registers");
        clearButton.setText("C");
        Button ceButton = new Button(container, 0);
        ceButton.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                Calculator.this.updateDisplay('E');
            }
        });
        ceButton.setLayoutData((Object)new GridData(4, 4, false, false));
        ceButton.setToolTipText("Clear Entry");
        ceButton.setText("CE");
        Button memAddButton = new Button(container, 0);
        memAddButton.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                Calculator.this.updateMemory('+');
            }
        });
        memAddButton.setLayoutData((Object)new GridData(4, 4, false, false));
        memAddButton.setToolTipText("Add value to Memory");
        memAddButton.setText("M+");
        Button mrButton = new Button(container, 0);
        mrButton.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                Calculator.this.updateDisplay('R');
            }
        });
        mrButton.setToolTipText("Recall value in Memory");
        mrButton.setLayoutData((Object)new GridData(4, 4, false, false));
        mrButton.setText("MR");
        Button backButton = new Button(container, 0);
        backButton.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                Calculator.this.updateDisplay('B');
            }
        });
        backButton.setLayoutData((Object)new GridData(4, 4, false, false));
        backButton.setToolTipText("Backspace");
        backButton.setText("BACK");
        Button divideButton = new Button(container, 0);
        divideButton.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                Calculator.this.updateCalc('/');
            }
        });
        divideButton.setLayoutData((Object)new GridData(4, 4, false, false));
        divideButton.setToolTipText("Divide");
        divideButton.setText("/");
        Button memSubtractButton = new Button(container, 0);
        memSubtractButton.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                Calculator.this.updateMemory('-');
            }
        });
        memSubtractButton.setLayoutData((Object)new GridData(4, 4, false, false));
        memSubtractButton.setToolTipText("Subtract value from Memory");
        memSubtractButton.setText("M-");
        Button inverseButton = new Button(container, 0);
        inverseButton.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                Calculator.this.updateDisplay('I');
            }
        });
        inverseButton.setLayoutData((Object)new GridData(4, 4, false, false));
        inverseButton.setToolTipText("Inverse of value");
        inverseButton.setText("1/X");
        Button sqrtButton = new Button(container, 0);
        sqrtButton.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                Calculator.this.updateDisplay('Q');
            }
        });
        sqrtButton.setLayoutData((Object)new GridData(4, 4, false, false));
        sqrtButton.setToolTipText("Square Root of value");
        sqrtButton.setText("SQRT");
        Button multiplyButton = new Button(container, 0);
        multiplyButton.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                Calculator.this.updateCalc('*');
            }
        });
        multiplyButton.setLayoutData((Object)new GridData(4, 4, false, false));
        multiplyButton.setToolTipText("Multiply");
        multiplyButton.setText("*");
        Button num7Button = new Button(container, 0);
        num7Button.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                Calculator.this.updateDisplay('7');
            }
        });
        num7Button.setToolTipText("Numeric Pad");
        num7Button.setLayoutData((Object)new GridData(4, 4, false, false));
        num7Button.setText("7");
        Button num8Button = new Button(container, 0);
        num8Button.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                Calculator.this.updateDisplay('8');
            }
        });
        num8Button.setToolTipText("Numeric Pad");
        num8Button.setLayoutData((Object)new GridData(4, 4, false, false));
        num8Button.setText("8");
        Button num9Button = new Button(container, 0);
        num9Button.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                Calculator.this.updateDisplay('9');
            }
        });
        num9Button.setToolTipText("Numeric Pad");
        num9Button.setLayoutData((Object)new GridData(4, 4, false, false));
        num9Button.setText("9");
        Button SubtractButton = new Button(container, 0);
        SubtractButton.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                Calculator.this.updateCalc('-');
            }
        });
        SubtractButton.setLayoutData((Object)new GridData(4, 4, false, false));
        SubtractButton.setToolTipText("Subtract");
        SubtractButton.setText("-");
        Button num4Button = new Button(container, 0);
        num4Button.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                Calculator.this.updateDisplay('4');
            }
        });
        num4Button.setToolTipText("Numeric Pad");
        num4Button.setLayoutData((Object)new GridData(4, 4, false, false));
        num4Button.setText("4");
        Button num5Button = new Button(container, 0);
        num5Button.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                Calculator.this.updateDisplay('5');
            }
        });
        num5Button.setToolTipText("Numeric Pad");
        num5Button.setLayoutData((Object)new GridData(4, 4, false, false));
        num5Button.setText("5");
        Button num6Button = new Button(container, 0);
        num6Button.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                Calculator.this.updateDisplay('6');
            }
        });
        num6Button.setToolTipText("Numeric Pad");
        num6Button.setLayoutData((Object)new GridData(4, 4, false, false));
        num6Button.setText("6");
        Button AdditionButton = new Button(container, 0);
        AdditionButton.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                Calculator.this.updateCalc('+');
            }
        });
        AdditionButton.setLayoutData((Object)new GridData(4, 4, false, false));
        AdditionButton.setToolTipText("Add");
        AdditionButton.setText("+");
        Button num1Button = new Button(container, 0);
        num1Button.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                Calculator.this.updateDisplay('1');
            }
        });
        num1Button.setCapture(true);
        num1Button.setToolTipText("Numeric Pad");
        num1Button.setLayoutData((Object)new GridData(4, 4, false, false));
        num1Button.setText("1");
        Button num2Button = new Button(container, 0);
        num2Button.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                Calculator.this.updateDisplay('2');
            }
        });
        num2Button.setToolTipText("Numeric Pad");
        num2Button.setLayoutData((Object)new GridData(4, 4, false, false));
        num2Button.setText("2");
        Button num3Button = new Button(container, 0);
        num3Button.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                Calculator.this.updateDisplay('3');
            }
        });
        num3Button.setToolTipText("Numeric Pad");
        num3Button.setLayoutData((Object)new GridData(4, 4, false, false));
        num3Button.setText("3");
        Button equalsButton = new Button(container, 0);
        equalsButton.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                Calculator.this.updateCalc('=');
            }
        });
        equalsButton.setToolTipText("Equals (get result)");
        equalsButton.setLayoutData((Object)new GridData(4, 4, false, false, 1, 2));
        equalsButton.setText("=");
        Button num0Button = new Button(container, 0);
        num0Button.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                Calculator.this.updateDisplay('0');
            }
        });
        num0Button.setLayoutData((Object)new GridData(4, 4, false, false));
        num0Button.setToolTipText("Numeric Pad");
        num0Button.setText("0");
        Button decimalButton = new Button(container, 0);
        decimalButton.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                Calculator.this.updateDisplay('.');
            }
        });
        decimalButton.setToolTipText("Numeric Pad");
        decimalButton.setLayoutData((Object)new GridData(4, 4, false, false));
        decimalButton.setText(".");
        Button signButton = new Button(container, 0);
        signButton.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                Calculator.this.updateDisplay('-');
            }
        });
        signButton.setToolTipText("Change sign of value");
        signButton.setLayoutData((Object)new GridData(4, 4, false, false));
        signButton.setText("+/-");
        new Label(container, 0);
        return container;
    }

    protected void createButtonsForButtonBar(Composite parent) {
        this.createButton(parent, 22, "Copy to Clipboard", false);
        this.createButton(parent, 1, IDialogConstants.CLOSE_LABEL, true);
    }

    protected Point getInitialSize() {
        return new Point(260, 350);
    }

    protected void configureShell(Shell newShell) {
        super.configureShell(newShell);
        newShell.setText("Calculator");
    }

    protected void buttonPressed(int buttonID) {
        if (buttonID == 22) {
            this.displayText.selectAll();
            this.displayText.copy();
            this.displayText.clearSelection();
        } else {
            super.buttonPressed(buttonID);
        }
    }

    private void updateDisplay(char keyPressed) {
        char keyVal = keyPressed;
        String tempString = new String();
        boolean doClear = false;
        if (!this.clearDisplay) {
            tempString = this.displayString;
        }
        switch (keyVal) {
            case 'B': {
                if (tempString.length() < 2) {
                    tempString = "";
                    break;
                }
                tempString = tempString.substring(0, tempString.length() - 1);
                break;
            }
            case 'C': {
                tempString = "0.";
                this.operatorString = "";
                this.calcChar = (char)32;
                doClear = true;
                break;
            }
            case 'D': {
                this.memoryString = "";
                break;
            }
            case 'E': {
                tempString = "0.";
                doClear = true;
                break;
            }
            case 'I': {
                tempString = this.doCalc(this.displayString, "", keyVal);
                doClear = true;
                break;
            }
            case 'Q': {
                tempString = this.doCalc(this.displayString, "", keyVal);
                doClear = true;
                break;
            }
            case 'R': {
                tempString = this.memoryString;
                doClear = true;
                break;
            }
            case '-': {
                if (tempString.startsWith("-")) {
                    tempString = tempString.substring(1, tempString.length());
                    break;
                }
                tempString = String.valueOf(keyVal) + tempString;
                break;
            }
            case '.': {
                if (tempString.indexOf(".") != -1 || tempString.length() >= 29) break;
                tempString = String.valueOf(tempString) + keyVal;
                break;
            }
            case '0': {
                if (tempString.equals("0") || tempString.length() >= 29) break;
                tempString = String.valueOf(tempString) + keyVal;
                break;
            }
            default: {
                if (tempString.length() >= 29) break;
                tempString = String.valueOf(tempString) + keyVal;
            }
        }
        this.clearDisplay = doClear;
        if (!this.displayString.equals(tempString)) {
            this.displayString = tempString;
            this.displayText.setText(this.displayString);
        }
    }

    private void updateMemory(char keyPressed) {
        char keyVal = keyPressed;
        String tempString = new String();
        switch (keyVal) {
            case 'S': {
                tempString = this.trimString(this.displayString);
                break;
            }
            case '+': {
                if (this.memoryString.length() == 0) {
                    tempString = this.trimString(this.displayString);
                    break;
                }
                tempString = this.doCalc(this.memoryString, this.displayString, '+');
                break;
            }
            case '-': {
                if (this.memoryString.length() == 0) {
                    if (this.displayString.startsWith("-")) {
                        tempString = this.displayString.substring(1, this.displayString.length());
                        tempString = this.trimString(tempString);
                        break;
                    }
                    if (this.displayString.equals("0.0") || this.displayString.equals("0") || this.displayString.equals("0.") || this.displayString.equals("-0.0")) {
                        tempString = "0";
                        break;
                    }
                    tempString = String.valueOf(keyVal) + this.displayString;
                    tempString = this.trimString(this.displayString);
                    break;
                }
                tempString = this.doCalc(this.memoryString, this.displayString, '-');
                break;
            }
        }
        if (tempString.startsWith("Error: ")) {
            if (!this.displayString.equals(tempString)) {
                this.displayString = tempString;
                this.displayText.setText(this.displayString);
            }
        } else {
            this.memoryString = tempString;
        }
        this.clearDisplay = true;
    }

    private String doCalc(String valAString, String valBString, char opChar) {
        String resultString = "Error: Not a Number";
        Double valA = 0.0;
        Double valB = 0.0;
        Double valAnswer = 0.0;
        if (valAString.length() > 0) {
            try {
                valA = Double.parseDouble(valAString);
            }
            catch (NumberFormatException numberFormatException) {
                return resultString;
            }
        } else {
            return resultString;
        }
        if (opChar != 'Q' && opChar != 'I') {
            if (valBString.length() > 0) {
                try {
                    valB = Double.parseDouble(valBString);
                }
                catch (NumberFormatException numberFormatException) {
                    return resultString;
                }
            } else {
                return resultString;
            }
        }
        switch (opChar) {
            case 'Q': {
                valAnswer = Math.sqrt(valA);
                break;
            }
            case 'I': {
                valB = 1.0;
                valAnswer = valB / valA;
                break;
            }
            case '+': {
                valAnswer = valA + valB;
                break;
            }
            case '-': {
                valAnswer = valA - valB;
                break;
            }
            case '/': {
                valAnswer = valA / valB;
                break;
            }
            case '*': {
                valAnswer = valA * valB;
                break;
            }
        }
        resultString = valAnswer.toString();
        resultString = this.trimString(resultString);
        return resultString;
    }

    private void updateCalc(char keyPressed) {
        char keyVal = keyPressed;
        String tempString = this.displayString;
        if (tempString.length() == 0) {
            return;
        }
        if (this.operatorString.length() == 0) {
            if (keyVal != '=') {
                if ((tempString = this.trimString(tempString)).startsWith("Error: ")) {
                    this.clearDisplay = true;
                    this.operatorString = "";
                    this.calcChar = (char)32;
                } else {
                    this.operatorString = tempString;
                    this.calcChar = keyVal;
                    this.clearDisplay = true;
                }
            }
            return;
        }
        this.displayString = this.doCalc(this.operatorString, tempString, this.calcChar);
        if (keyVal == '=' || this.displayString.startsWith("Error: ")) {
            this.calcChar = (char)32;
            this.operatorString = "";
        } else {
            this.calcChar = keyVal;
            this.operatorString = this.displayString;
        }
        this.clearDisplay = true;
        this.displayText.setText(this.displayString);
    }

    private String trimString(String newString) {
        String tempString = newString;
        if (tempString.equals("NaN")) {
            tempString = "Error: Not a Number";
            return tempString;
        }
        if (tempString.equals("Infinity") || tempString.equals("-Infinity")) {
            tempString = "Error: Infinity";
            return tempString;
        }
        if (tempString.equals(-0.0)) {
            tempString = "0";
            return tempString;
        }
        if (tempString.endsWith(".0")) {
            tempString = tempString.substring(0, tempString.length() - 2);
        }
        if (tempString.length() > 28) {
            tempString = "Error: Number too long";
        }
        return tempString;
    }
}

