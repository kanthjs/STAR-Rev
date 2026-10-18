/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.jface.dialogs.MessageDialog
 *  org.eclipse.swt.widgets.Label
 *  org.eclipse.swt.widgets.List
 *  org.eclipse.swt.widgets.Shell
 *  org.eclipse.swt.widgets.Text
 */
package org.irri.breedingtool.utility;

import java.io.File;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.irri.breedingtool.manager.impl.DataManipulationManager;
import org.irri.breedingtool.utility.DialogListValidator;

public class EnvVarValidator
extends DialogListValidator {
    DataManipulationManager dataManipulationManager = new DataManipulationManager();
    List factorVarList;
    Boolean performMultiEnv;
    File file;
    Text txtEnvVar;
    Label lblEnvironment;

    public EnvVarValidator(List factorVarList, Boolean performMultiEnv, File file, Text envVarText, Label lblEnvironment) {
        this.factorVarList = factorVarList;
        this.performMultiEnv = performMultiEnv;
        this.file = file;
        this.txtEnvVar = envVarText;
        this.lblEnvironment = lblEnvironment;
    }

    @Override
    public boolean validate() {
        return this.txtEnvVar.getText().isEmpty();
    }

    @Override
    public boolean performAddProcess() {
        String[] selectedNumVars = this.factorVarList.getSelection();
        String[] levelItems = DataManipulationManager.getEnvtLevels(selectedNumVars[0], this.file);
        if (levelItems.length < 2 && this.performMultiEnv.booleanValue()) {
            MessageDialog.openWarning((Shell)this.factorVarList.getShell(), (String)"Invalid factor", (String)("The environment factor for a Multi-Environment Analysis should have atleast 2(two) levels.\n\n" + selectedNumVars[0] + " has 1 level only. \n\n" + "Please choose a different environment factor."));
            return false;
        }
        return true;
    }
}

