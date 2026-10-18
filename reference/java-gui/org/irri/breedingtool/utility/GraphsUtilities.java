/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.jface.dialogs.MessageDialog
 *  org.eclipse.swt.events.ModifyEvent
 *  org.eclipse.swt.events.ModifyListener
 *  org.eclipse.swt.graphics.Color
 *  org.eclipse.swt.graphics.Device
 *  org.eclipse.swt.graphics.RGB
 *  org.eclipse.swt.widgets.ColorDialog
 *  org.eclipse.swt.widgets.Display
 *  org.eclipse.swt.widgets.Event
 *  org.eclipse.swt.widgets.Listener
 *  org.eclipse.swt.widgets.Shell
 *  org.eclipse.swt.widgets.Table
 *  org.eclipse.swt.widgets.TableItem
 *  org.eclipse.swt.widgets.Text
 */
package org.irri.breedingtool.utility;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.ColorDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.irri.breedingtool.application.handler.PartStackHandler;
import org.irri.breedingtool.application.model.ProjectExplorerTreeNodeModel;
import org.irri.breedingtool.manager.impl.DataManipulationManager;
import org.irri.breedingtool.manager.impl.ProjectExplorerManager;
import org.irri.breedingtool.projectexplorer.view.ProjectExplorerView;

public class GraphsUtilities {
    public static RGB chooseColor() {
        ColorDialog dlg = new ColorDialog(Display.getCurrent().getActiveShell());
        dlg.setText("Choose a Color");
        RGB rgb = dlg.open();
        System.out.println("RGB: returned format " + rgb);
        return rgb;
    }

    private static RGB[] generateRGBColors(int size) {
        RGB[] shades = new RGB[size];
        shades[0] = new RGB(255, 0, 0);
        shades[1] = new RGB(0, 255, 0);
        shades[2] = new RGB(0, 0, 255);
        shades[3] = new RGB(255, 140, 0);
        shades[4] = new RGB(148, 0, 211);
        shades[5] = new RGB(255, 0, 255);
        shades[6] = new RGB(139, 69, 19);
        shades[7] = new RGB(255, 255, 0);
        shades[8] = new RGB(230, 230, 250);
        shades[9] = new RGB(0, 100, 0);
        shades[10] = new RGB(221, 160, 221);
        shades[11] = new RGB(255, 215, 0);
        shades[12] = new RGB(176, 48, 96);
        shades[13] = new RGB(152, 251, 152);
        shades[14] = new RGB(70, 130, 180);
        shades[15] = new RGB(205, 92, 92);
        shades[16] = new RGB(255, 127, 80);
        shades[17] = new RGB(210, 180, 140);
        shades[18] = new RGB(147, 112, 219);
        shades[19] = new RGB(0, 255, 255);
        return shades;
    }

    public static boolean inputLimitValidation(String inputName) {
        System.out.println("validating variable Name: " + inputName);
        if (!inputName.equals("Auto")) {
            try {
                Integer.parseInt(inputName);
            }
            catch (NumberFormatException numberFormatException) {
                return false;
            }
        }
        return true;
    }

    public static String convertToRrgbFormat(String inputRGB) {
        String color = inputRGB.replace("}", ", max=255)");
        color = color.replaceAll("RGB \\{", "rgb(");
        System.out.println("converted " + inputRGB + "to" + color);
        return color;
    }

    public static String convertToTransparentRrgbFormat(String inputRGB) {
        String color = inputRGB.replace("}", ", max=255, alpha=190)");
        color = color.replaceAll("RGB \\{", "rgb(");
        System.out.println("converted " + inputRGB + "to" + color);
        return color;
    }

    public static int[] convertIntListToIntAray(ArrayList<Integer> IntegerArrayList) {
        int[] intArray = new int[IntegerArrayList.size()];
        int i = 0;
        while (i < IntegerArrayList.size()) {
            intArray[i] = IntegerArrayList.get(i);
            ++i;
        }
        return intArray;
    }

    public static String getOutputFolderName(String analysisType) {
        String outputFolderName = analysisType.equals("BarGraph") ? "BarGraph" : (analysisType.equals("LineGraph") ? "LineGraph" : (analysisType.equals("ScatterPlotGraph") ? "ScatterPlotGraph" : (analysisType.equals("BoxPlot") ? "BoxPlotGraph" : (analysisType.equals("PieChart") ? "PieChart" : (analysisType.equals("Histogram") ? "Histogram" : "unknown analysis")))));
        return "(" + outputFolderName + "_" + DataManipulationManager.getTimeStamp() + ")\\";
    }

    public static File createOutputFolder(String fileName, String graphType) {
        ProjectExplorerManager projExpMan = new ProjectExplorerManager();
        String dataFileName = fileName.replaceAll(".csv", "");
        String outputFolderPath = ((ProjectExplorerTreeNodeModel)projExpMan.getOutputFolder(ProjectExplorerView.projectTree).getData()).getProjectFile().getAbsolutePath();
        String newFolderName = GraphsUtilities.getOutputFolderName(graphType);
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

    public static String checkIfAutoOrNumeric(String text) {
        try {
            Double.parseDouble(text);
            return text;
        }
        catch (NumberFormatException numberFormatException) {
            return "NA";
        }
    }

    public static void addTextModifyListener(Text newEditor) {
        String originalText = newEditor.getText();
        newEditor.addModifyListener(new ModifyListener(){

            public void modifyText(ModifyEvent e) {
                final Text text = newEditor;
                newEditor.addListener(16, new Listener(){

                    public void handleEvent(Event e) {
                        block2: {
                            try {
                                Double.parseDouble(text.getText());
                            }
                            catch (Exception exception) {
                                if (newEditor.getText().toLowerCase().equals("auto")) break block2;
                                MessageDialog.openInformation((Shell)Display.getCurrent().getActiveShell(), (String)"Invalid Input", (String)"The limits should either be 'Auto' or numeric.");
                                newEditor.setText(originalText);
                            }
                        }
                    }
                });
            }
        });
    }

    public static RGB[] getGrayShades(int numberOfVarLevels) {
        RGB[] getAllShades = GraphsUtilities.generateRGBColors(20);
        RGB[] grayShadesRGB = new RGB[numberOfVarLevels];
        int ctr = 0;
        int i = 0;
        while (i < numberOfVarLevels) {
            grayShadesRGB[i] = getAllShades[ctr++];
            if (ctr == 20) {
                ctr = 0;
            }
            ++i;
        }
        return grayShadesRGB;
    }

    public static boolean checkIfErrorBarsIsAllowed(String absolutePath, String cVar, String[] nVar, String byVar) {
        String[] stringArray = nVar;
        int n = nVar.length;
        int n2 = 0;
        while (n2 < n) {
            int[] levels;
            String s = stringArray[n2];
            int[] nArray = levels = ProjectExplorerView.rJavaManager.getRJavaDataManipulationManager().howManyLevelsHaveOneDatum(absolutePath, cVar, s, byVar);
            int n3 = levels.length;
            int n4 = 0;
            while (n4 < n3) {
                int i = nArray[n4];
                if (i == 1) {
                    return false;
                }
                ++n4;
            }
            ++n2;
        }
        return true;
    }

    public static boolean checkIfWeighVarIsAllowed(String absolutePath, String cVar, String wVar, String byVar) {
        int[] levels;
        int[] nArray = levels = ProjectExplorerView.rJavaManager.getRJavaDataManipulationManager().howManyLevelsHaveOneDatum(absolutePath.replaceAll("\\\\", "/"), cVar, wVar, byVar);
        int n = levels.length;
        int n2 = 0;
        while (n2 < n) {
            int i = nArray[n2];
            if (i > 1) {
                return false;
            }
            ++n2;
        }
        return true;
    }

    public static void refreshTableColors(Table table, int columnNumber, RGB[] colors) {
        int i = 0;
        while (i < table.getItemCount()) {
            TableItem tableItem = table.getItem(i);
            tableItem.setBackground(columnNumber, new Color((Device)Display.getCurrent(), colors[i]));
            ++i;
        }
    }

    public static int randInt(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt(max - min + 1) + min;
        return randomNum;
    }

    public static Color getColorFromRGB(RGB[] colors) {
        return new Color((Device)Display.getCurrent(), colors[GraphsUtilities.randInt(0, colors.length - 1)]);
    }

    public static Color getRandomColor() {
        return new Color((Device)Display.getCurrent(), new RGB(GraphsUtilities.randInt(0, 255), GraphsUtilities.randInt(0, 255), GraphsUtilities.randInt(0, 255)));
    }
}

