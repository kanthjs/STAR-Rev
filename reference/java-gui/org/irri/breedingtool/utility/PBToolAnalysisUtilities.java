/*
 * Decompiled with CFR 0.152.
 */
package org.irri.breedingtool.utility;

import java.io.File;
import org.irri.breedingtool.application.handler.PartStackHandler;
import org.irri.breedingtool.application.model.ProjectExplorerTreeNodeModel;
import org.irri.breedingtool.manager.impl.DataManipulationManager;
import org.irri.breedingtool.manager.impl.ProjectExplorerManager;
import org.irri.breedingtool.projectexplorer.view.ProjectExplorerView;

public class PBToolAnalysisUtilities {
    public static String getOutputFolderName(String analysisType) {
        String outputFolderName = analysisType.equals("NCI") ? "NorthCarolinaI" : (analysisType.equals("NCII") ? "NorthCarolinaII" : (analysisType.equals("NCIII") ? "NorthCarolinaIII" : (analysisType.equals("TripleTestCross") ? "TripleTestCross" : (analysisType.equals("GriffingI") ? "DiallelGMI" : (analysisType.equals("GriffingII") ? "DiallelGMII" : (analysisType.equals("GriffingIII") ? "DiallelGMIII" : (analysisType.equals("GriffingIV") ? "DiallelGMIV" : (analysisType.equals("SingleEnv") ? "SingleSite" : (analysisType.equals("twoStageMultiEnv") ? "TwoStageMultiSite" : (analysisType.equals("oneStageMultiEnv") ? "OneStageMultiSite" : (analysisType.equals("GMASummaryStat") ? "GenerationMean" : (analysisType.equals("GMARawData") ? "GenerationMeanRawData" : (analysisType.equals("QTL") ? "QTL" : (analysisType.equals("selectionIndex") ? "SelectionIndex" : "unknown analysis"))))))))))))));
        return "(" + outputFolderName + "_" + DataManipulationManager.getTimeStamp() + ")\\";
    }

    public static File createOutputFolder(String fileName, String analysisType) {
        ProjectExplorerManager projExpMan = new ProjectExplorerManager();
        String dataFileName = fileName.replaceAll(".csv", "");
        String outputFolderPath = ((ProjectExplorerTreeNodeModel)projExpMan.getOutputFolder(ProjectExplorerView.projectTree).getData()).getProjectFile().getAbsolutePath();
        String newFolderName = PBToolAnalysisUtilities.getOutputFolderName(analysisType);
        File outputFolder = new File(String.valueOf(outputFolderPath) + "//" + dataFileName + newFolderName);
        if (!outputFolder.exists()) {
            outputFolder.mkdir();
        }
        return outputFolder;
    }

    public static void openFolder(File outputFolder) {
        ProjectExplorerManager projExpMan = new ProjectExplorerManager();
        projExpMan.refreshFolder(projExpMan.getTreeNodeModelbyFilePath(outputFolder.getAbsolutePath()));
        ProjectExplorerTreeNodeModel newFileModel = projExpMan.getTreeNodeModelbyFilePath(outputFolder.getAbsolutePath());
        if (newFileModel.getElementInStack() == null) {
            PartStackHandler.execute(newFileModel);
        } else {
            PartStackHandler.reOpenPart(newFileModel);
        }
    }
}

