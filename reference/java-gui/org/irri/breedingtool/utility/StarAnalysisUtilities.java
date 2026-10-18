/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.swt.widgets.Text
 */
package org.irri.breedingtool.utility;

import java.io.File;
import java.util.Arrays;
import java.util.Calendar;
import org.eclipse.swt.widgets.Text;
import org.irri.breedingtool.application.handler.DialogHandler;
import org.irri.breedingtool.application.handler.PartStackHandler;
import org.irri.breedingtool.application.model.ProjectExplorerTreeNodeModel;
import org.irri.breedingtool.manager.impl.DataManipulationManager;
import org.irri.breedingtool.manager.impl.ProjectExplorerManager;
import org.irri.breedingtool.projectexplorer.view.ProjectExplorerView;

public class StarAnalysisUtilities {
    public static String createOutputFolder(String activeFilePath, String OutputFolder) {
        ProjectExplorerManager projectMan = new ProjectExplorerManager();
        File activeFile = new File(activeFilePath);
        Calendar cldTime = Calendar.getInstance();
        File fileFolder = new File(String.valueOf(((ProjectExplorerTreeNodeModel)projectMan.getOutputFolder(ProjectExplorerView.projectTree).getData()).getProjectFile().getPath()) + File.separator + activeFile.getName().replaceAll(".csv", "") + "(" + OutputFolder + "_" + cldTime.getTimeInMillis() + ")");
        if (!fileFolder.exists()) {
            fileFolder.mkdir();
        }
        return String.valueOf(fileFolder.getPath()) + File.separator;
    }

    public static int getTotalVariableLevels(String varName, String filePath) {
        return DataManipulationManager.getEnvtLevels(varName, new File(filePath)).length;
    }

    public static String createOutputFile(String OutputFile) {
        ProjectExplorerManager projectMan = new ProjectExplorerManager();
        Calendar cldTime = Calendar.getInstance();
        File fileFolder = new File(String.valueOf(((ProjectExplorerTreeNodeModel)projectMan.getOutputFolder(ProjectExplorerView.projectTree).getData()).getProjectFile().getPath()) + File.separator + OutputFile + "_" + cldTime.getTimeInMillis() + ".txt");
        return fileFolder.getPath();
    }

    public static void openAndRefreshFolder(String outputFile) {
        ProjectExplorerManager projectMan = new ProjectExplorerManager();
        projectMan.refreshFolder((ProjectExplorerTreeNodeModel)projectMan.getOutputFolder(ProjectExplorerView.projectTree).getData());
        if (!PartStackHandler.isOpen(outputFile)) {
            PartStackHandler.execute(projectMan.getTreeNodeModelbyFilePath(outputFile));
        } else {
            PartStackHandler.reOpenPart(projectMan.getTreeNodeModelbyFilePath(outputFile));
            System.out.println(String.valueOf(outputFile) + " Part Re-Opened");
        }
        DialogHandler.setPartDialogMaximized("None");
    }

    public static boolean factorsHasLevels(String[] factorList, String filePath) {
        int i = 0;
        while (i < factorList.length) {
            if (DataManipulationManager.getEnvtLevels(factorList[i], new File(filePath)).length > 2) {
                return true;
            }
            ++i;
        }
        return false;
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

    public static boolean validateTextFields(Text ... fieldTexts) {
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

