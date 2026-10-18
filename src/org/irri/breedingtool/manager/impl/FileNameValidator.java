/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.jface.dialogs.IInputValidator
 */
package org.irri.breedingtool.manager.impl;

import java.io.File;
import org.eclipse.jface.dialogs.IInputValidator;
import org.irri.breedingtool.manager.impl.DataManipulationManager;

public class FileNameValidator
implements IInputValidator {
    private DataManipulationManager dataManipulationManager = new DataManipulationManager();
    private File currFile;

    public FileNameValidator(File currFile) {
        this.currFile = currFile;
    }

    public String isValid(String newText) {
        if (!newText.endsWith(".csv")) {
            return "Please enter a valid file name with the correct file extension(.csv)";
        }
        if (this.filenameExist(newText)) {
            return "This filename already exist!";
        }
        return null;
    }

    private boolean filenameExist(String newText) {
        String[] fileNames;
        String[] stringArray = fileNames = this.dataManipulationManager.getFileNamesFromDir(this.currFile);
        int n = fileNames.length;
        int n2 = 0;
        while (n2 < n) {
            String s = stringArray[n2];
            if (s.equals(newText)) {
                return true;
            }
            ++n2;
        }
        return false;
    }
}

