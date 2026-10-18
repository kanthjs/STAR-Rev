/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.swt.widgets.Shell
 */
package org.irri.breedingtool.utility;

import java.util.ArrayList;
import org.eclipse.swt.widgets.Shell;
import org.irri.breedingtool.manager.api.IDataAnalysis;

public class WindowDialogControlUtil
implements IDataAnalysis {
    private static ArrayList<Shell> dataAnalysisShells = new ArrayList();

    public static void showWindowDialog(String analysis, String filePathID) {
        for (Shell s : dataAnalysisShells) {
            System.out.println(s.getData("filePathID").toString());
            if (!s.getData("filePathID").toString().equals(filePathID) || !s.getData("analysis").toString().equals(analysis)) continue;
            s.setMinimized(false);
            s.setActive();
        }
    }

    public static void hideAllWindowDialog() {
        for (Shell s : dataAnalysisShells) {
            s.setMinimized(true);
        }
    }

    public static void addWindowDialogToList(Shell shell) {
        dataAnalysisShells.add(shell);
    }

    public static void removeWindowDialogToList(Shell shell) {
        dataAnalysisShells.remove(shell);
    }

    public static boolean doesWindowDialogForFileExist(String analysis, String filePathID) {
        for (Shell s : dataAnalysisShells) {
            if (!s.getData("filePathID").toString().equals(filePathID) || !s.getData("analysis").toString().equals(analysis)) continue;
            return true;
        }
        return false;
    }

    public static void closeAllWindowDialogOfFile(String filePathID) {
        ArrayList<Shell> shellsToBeClosed = new ArrayList<Shell>();
        for (Shell s : dataAnalysisShells) {
            if (!s.getData("filePathID").toString().equals(filePathID)) continue;
            shellsToBeClosed.add(s);
        }
        for (Shell s : shellsToBeClosed) {
            dataAnalysisShells.remove(s);
            try {
                s.close();
            }
            catch (Exception exception) {
                System.out.println("caught exception at windowDialogControlUtil. :] ");
            }
        }
    }
}

