/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.jface.dialogs.Dialog
 *  org.eclipse.jface.window.Window
 */
package org.irri.breedingtool.application.handler;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.window.Window;

public class DialogHandler {
    public static HashMap<String, Dialog> dialogMap = new HashMap();

    public static boolean openDialog(String dialogID, Dialog newShell) {
        try {
            dialogMap.get(dialogID).getShell().setMinimized(false);
            return false;
        }
        catch (Exception exception) {
            dialogMap.remove(dialogID);
            dialogMap.put(dialogID, newShell);
            return true;
        }
    }

    public static void closeAllPartDialogs(String partID) {
        Iterator<Map.Entry<String, Dialog>> it = dialogMap.entrySet().iterator();
        System.out.println("Closing all dialogs for: " + partID);
        while (it.hasNext()) {
            Map.Entry<String, Dialog> pairs = it.next();
            if (pairs.getKey().toString().contains(partID)) {
                pairs.getValue().close();
                it.remove();
                dialogMap.remove(pairs.getKey());
            }
            System.out.println(pairs.getKey() + " = " + pairs.getValue());
        }
    }

    public static void setPartDialogMaximized(String partID) {
        for (Map.Entry<String, Dialog> pairs : dialogMap.entrySet()) {
            if (pairs.getValue() == null || ((Window)pairs.getValue()).getShell() == null || ((Window)pairs.getValue()).getShell().isDisposed()) continue;
            if (pairs.getKey().toString().contains(partID)) {
                pairs.getValue().getShell().setMinimized(false);
                continue;
            }
            pairs.getValue().getShell().setMinimized(true);
        }
    }
}

