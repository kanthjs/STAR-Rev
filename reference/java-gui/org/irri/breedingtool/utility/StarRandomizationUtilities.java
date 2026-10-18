/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.jface.dialogs.MessageDialog
 *  org.eclipse.swt.widgets.Composite
 *  org.eclipse.swt.widgets.Display
 *  org.eclipse.swt.widgets.Shell
 *  org.eclipse.swt.widgets.Spinner
 *  org.eclipse.swt.widgets.Text
 */
package org.irri.breedingtool.utility;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Text;
import org.irri.breedingtool.application.handler.PartStackHandler;
import org.irri.breedingtool.application.model.ProjectExplorerTreeNodeModel;
import org.irri.breedingtool.manager.impl.ProjectExplorerManager;
import org.irri.breedingtool.projectexplorer.view.ProjectExplorerView;

public class StarRandomizationUtilities {
    public static String createOutputFile(String OutputFolder, String newFileName) {
        ProjectExplorerManager projectMan = new ProjectExplorerManager();
        File fileFolder = new File(String.valueOf(((ProjectExplorerTreeNodeModel)projectMan.getOutputFolder(ProjectExplorerView.projectTree).getData()).getProjectFile().getPath()) + File.separator + "Randomization");
        if (!fileFolder.exists()) {
            fileFolder.mkdir();
        }
        Calendar cldTime = Calendar.getInstance();
        fileFolder = new File(String.valueOf(((ProjectExplorerTreeNodeModel)projectMan.getOutputFolder(ProjectExplorerView.projectTree).getData()).getProjectFile().getPath()) + File.separator + "Randomization" + File.separator + OutputFolder + "_" + cldTime.getTimeInMillis());
        if (!fileFolder.exists()) {
            fileFolder.mkdir();
        }
        return String.valueOf(fileFolder.getPath()) + File.separator + newFileName;
    }

    public static String createOutputFolder(String OutputFolder) {
        ProjectExplorerManager projectMan = new ProjectExplorerManager();
        File fileFolder = new File(String.valueOf(((ProjectExplorerTreeNodeModel)projectMan.getOutputFolder(ProjectExplorerView.projectTree).getData()).getProjectFile().getPath()) + File.separator + "Randomization");
        if (!fileFolder.exists()) {
            fileFolder.mkdir();
        }
        Calendar cldTime = Calendar.getInstance();
        fileFolder = new File(String.valueOf(((ProjectExplorerTreeNodeModel)projectMan.getOutputFolder(ProjectExplorerView.projectTree).getData()).getProjectFile().getPath()) + File.separator + "Randomization" + File.separator + OutputFolder + "_" + cldTime.getTimeInMillis());
        if (!fileFolder.exists()) {
            fileFolder.mkdir();
        }
        return String.valueOf(fileFolder.getPath()) + File.separator;
    }

    public static void openAndRefreshFileFolder(String outputFile) {
        ProjectExplorerManager projectMan = new ProjectExplorerManager();
        projectMan.refreshFolder(projectMan.getTreeNodeModelbyFilePath(String.valueOf(outputFile) + ".csv"));
        if (new File(String.valueOf(outputFile) + ".csv").exists() || new File(outputFile).exists()) {
            if (!outputFile.endsWith("csv")) {
                PartStackHandler.execute(projectMan.getTreeNodeModelbyFilePath(String.valueOf(outputFile) + ".csv"));
                PartStackHandler.execute(projectMan.getTreeNodeModelbyFilePath(String.valueOf(outputFile) + ".txt"));
            } else {
                PartStackHandler.execute(projectMan.getTreeNodeModelbyFilePath(outputFile));
                PartStackHandler.execute(projectMan.getTreeNodeModelbyFilePath(outputFile.replace(".csv", ".txt")));
            }
            System.out.println("This File2: " + outputFile + ".txt");
        } else {
            PartStackHandler.execute(projectMan.getTreeNodeModelbyFilePath(String.valueOf(outputFile) + ".txt"));
        }
    }

    public static void openAndRefreshFileFolder(String outputFile, boolean openOutputTxt) {
        ProjectExplorerManager projectMan = new ProjectExplorerManager();
        projectMan.refreshFolder(projectMan.getTreeNodeModelbyFilePath(String.valueOf(outputFile) + ".csv"));
        if (new File(String.valueOf(outputFile) + ".csv").exists() || new File(outputFile).exists()) {
            if (!outputFile.endsWith("csv")) {
                PartStackHandler.execute(projectMan.getTreeNodeModelbyFilePath(String.valueOf(outputFile) + ".csv"));
                if (openOutputTxt) {
                    PartStackHandler.execute(projectMan.getTreeNodeModelbyFilePath(String.valueOf(outputFile) + ".txt"));
                }
            } else {
                PartStackHandler.execute(projectMan.getTreeNodeModelbyFilePath(outputFile));
            }
        } else {
            PartStackHandler.execute(projectMan.getTreeNodeModelbyFilePath(String.valueOf(outputFile) + ".txt"));
        }
    }

    public static void testVarArgs(Object ... parameters) {
        System.out.println("---------------------- testVarArgs---------------");
        Object[] objectArray = parameters;
        int n = parameters.length;
        int n2 = 0;
        while (n2 < n) {
            Object u = objectArray[n2];
            if (u == null) {
                System.out.println("Null");
            } else {
                System.out.println(u.getClass().isArray() ? Arrays.deepToString((Object[])u) : u);
            }
            ++n2;
        }
        System.out.println("---------------------- EndtestVarArgs---------------");
    }

    public static ArrayList<Text> getAllText(Composite parent) {
        ArrayList<Text> returnText = new ArrayList<Text>();
        int i = 0;
        while (i < parent.getChildren().length) {
            if (parent.getChildren()[i] instanceof Text) {
                returnText.add((Text)parent.getChildren()[i]);
            } else if (parent.getChildren()[i] instanceof Composite) {
                ArrayList<Text> childParent = StarRandomizationUtilities.getAllText((Composite)parent.getChildren()[i]);
                returnText.addAll(childParent);
            }
            ++i;
        }
        return returnText;
    }

    public static boolean isTextUnique(ArrayList<Text> lstText) {
        int x = 0;
        while (x < lstText.size()) {
            Text compareText = lstText.get(x);
            int i = 0;
            while (i < lstText.size()) {
                if (lstText.get(i).getText().equals(compareText.getText()) && lstText.get(i) != compareText) {
                    System.out.println(String.valueOf(compareText.getText()) + " = " + lstText.get(i).getText());
                    return false;
                }
                ++i;
            }
            ++x;
        }
        return true;
    }

    public static boolean validateVariableText(String value) {
        char[] charSet = value.toCharArray();
        int i = 1;
        while (i < charSet.length) {
            if (!Character.isLetter(charSet[i])) {
                return false;
            }
            ++i;
        }
        return true;
    }

    public static void validateSpinner(Spinner spinner) {
        String string = spinner.getText();
        String message = null;
        try {
            int value = Integer.parseInt(string);
            int maximum = spinner.getMaximum();
            int minimum = spinner.getMinimum();
            if (value > maximum) {
                message = "Current input is greater than the maximum limit (" + maximum + ")";
            } else if (value < minimum) {
                message = "Current input is less than the minimum limit (" + minimum + ")";
            }
        }
        catch (Exception exception) {
            message = "Current input is not numeric";
        }
        if (message != null) {
            MessageDialog.open((int)1, (Shell)Display.getCurrent().getActiveShell(), (String)"Error", (String)message, (int)0);
            spinner.setSelection(spinner.getMinimum());
        }
    }

    public static boolean validateTextFiles(Text ... fieldTexts) {
        Text[] textArray = fieldTexts;
        int n = fieldTexts.length;
        int n2 = 0;
        while (n2 < n) {
            Text u = textArray[n2];
            if (u.getText().equals("")) {
                return false;
            }
            ++n2;
        }
        return true;
    }
}

