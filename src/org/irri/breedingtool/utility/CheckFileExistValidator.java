/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.jface.dialogs.IInputValidator
 */
package org.irri.breedingtool.utility;

import java.io.File;
import org.eclipse.jface.dialogs.IInputValidator;
import org.irri.breedingtool.utility.GeneralUtility;

public class CheckFileExistValidator
implements IInputValidator {
    private String folderPath = "";
    private String defaultFileName = "";
    private boolean canOverwrite = false;

    public CheckFileExistValidator(String fileName, String folderURL, boolean flag_CanOverwrite) {
        this.folderPath = folderURL;
        this.defaultFileName = fileName;
        this.canOverwrite = flag_CanOverwrite;
    }

    public String isValid(String fileName) {
        File newFile = new File(String.valueOf(this.folderPath) + File.separator + fileName);
        if (newFile.getName().toString().equals(this.defaultFileName)) {
            if (this.canOverwrite) {
                return null;
            }
            return "";
        }
        if (fileName.length() < 4) {
            return "";
        }
        if (!GeneralUtility.validateFileName(fileName)) {
            return "Not a valid File/Folder name.";
        }
        if (newFile.exists()) {
            if (newFile.isDirectory()) {
                return "Folder already exist";
            }
            return "File already exist!";
        }
        return null;
    }
}

