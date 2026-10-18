/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.e4.core.di.annotations.Execute
 */
package org.irri.breedingtool.application.handler;

import org.eclipse.e4.core.di.annotations.Execute;
import org.irri.breedingtool.application.handler.PartStackHandler;

public class HelpHandler {
    @Execute
    public void execute() {
        PartStackHandler.launchHelp();
    }
}

