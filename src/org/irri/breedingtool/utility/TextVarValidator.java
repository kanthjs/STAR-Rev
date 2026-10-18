/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.swt.widgets.Button
 *  org.eclipse.swt.widgets.Label
 *  org.eclipse.swt.widgets.List
 *  org.eclipse.swt.widgets.Text
 */
package org.irri.breedingtool.utility;

import java.io.File;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Text;
import org.irri.breedingtool.utility.DialogListValidator;

public class TextVarValidator
extends DialogListValidator {
    List factorVarList;
    Button performMultiRadio;
    File file;
    Text txtVar;
    Label lblVar;

    public TextVarValidator(Text txtVar, Label lblVar) {
        this.txtVar = txtVar;
        this.lblVar = lblVar;
    }

    @Override
    public boolean validate() {
        if (!this.lblVar.getEnabled()) {
            return false;
        }
        return this.txtVar.getText().isEmpty();
    }
}

