/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.jface.dialogs.Dialog
 *  org.eclipse.jface.dialogs.IDialogConstants
 *  org.eclipse.swt.graphics.Point
 *  org.eclipse.swt.layout.GridData
 *  org.eclipse.swt.layout.GridLayout
 *  org.eclipse.swt.widgets.Composite
 *  org.eclipse.swt.widgets.Control
 *  org.eclipse.swt.widgets.Group
 *  org.eclipse.swt.widgets.Label
 *  org.eclipse.swt.widgets.Layout
 *  org.eclipse.swt.widgets.Shell
 *  org.eclipse.swt.widgets.Spinner
 */
package org.irri.breedingtool.star.design.dialog;

import java.io.File;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.wb.swt.SWTResourceManager;
import org.irri.breedingtool.application.handler.PartStackHandler;

public class IncompleteBlockRectangularLatticeDesignDialog
extends Dialog {
    private static String filePath = PartStackHandler.getActivePart().getElementId().toString();

    public IncompleteBlockRectangularLatticeDesignDialog(Shell parentShell) {
        super(parentShell);
        this.setShellStyle(2288);
    }

    protected void configureShell(Shell shell) {
        super.configureShell(shell);
        shell.setText("Randomization");
    }

    protected Control createDialogArea(Composite parent) {
        Composite container = (Composite)super.createDialogArea(parent);
        Label lblFactorialDesign = new Label(container, 0);
        lblFactorialDesign.setLayoutData((Object)new GridData(16384, 0x1000000, true, false, 1, 1));
        lblFactorialDesign.setFont(SWTResourceManager.getFont("Tahoma", 10, 1));
        lblFactorialDesign.setText("Rectangular Lattice Design");
        Label lblLbldialogtitle = new Label(container, 0);
        lblLbldialogtitle.setFont(SWTResourceManager.getFont("Tahoma", 9, 0));
        lblLbldialogtitle.setLayoutData((Object)new GridData(16384, 0x1000000, true, false, 1, 1));
        lblLbldialogtitle.setText("Incomplete Block Design");
        Label label = new Label(container, 258);
        label.setLayoutData((Object)new GridData(4, 0x1000000, false, false, 1, 1));
        Group grpDesignParameters = new Group(container, 0);
        grpDesignParameters.setLayout((Layout)new GridLayout(1, false));
        GridData gd_grpDesignParameters = new GridData(4, 4, true, true, 1, 1);
        gd_grpDesignParameters.heightHint = 100;
        grpDesignParameters.setLayoutData((Object)gd_grpDesignParameters);
        grpDesignParameters.setText("Design Parameters");
        Composite composite_1 = new Composite((Composite)grpDesignParameters, 0);
        composite_1.setLayout((Layout)new GridLayout(2, false));
        GridData gd_composite_1 = new GridData(4, 4, true, true, 1, 1);
        gd_composite_1.heightHint = 49;
        composite_1.setLayoutData((Object)gd_composite_1);
        Label lblNumberOfReplicated = new Label(composite_1, 0);
        lblNumberOfReplicated.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        lblNumberOfReplicated.setLayoutData((Object)new GridData(16384, 0x1000000, true, true, 1, 1));
        lblNumberOfReplicated.setText("Number of Treatments");
        Spinner txtTotalTreatments = new Spinner(composite_1, 2048);
        txtTotalTreatments.setLayoutData((Object)new GridData(0x1000000, 0x1000000, true, true, 1, 1));
        txtTotalTreatments.setMinimum(2);
        Label lblNumberOfReplicates = new Label(composite_1, 0);
        lblNumberOfReplicates.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        lblNumberOfReplicates.setLayoutData((Object)new GridData(16384, 0x1000000, true, true, 1, 1));
        lblNumberOfReplicates.setText("Number of Replicates");
        Spinner txtTotalReplicates = new Spinner(composite_1, 2048);
        txtTotalReplicates.setLayoutData((Object)new GridData(0x1000000, 0x1000000, true, true, 1, 1));
        txtTotalReplicates.setMinimum(2);
        Label lblNumberOfTrials = new Label(composite_1, 0);
        lblNumberOfTrials.setFont(SWTResourceManager.getFont("Tahoma", 8, 0));
        lblNumberOfTrials.setLayoutData((Object)new GridData(16384, 0x1000000, true, true, 1, 1));
        lblNumberOfTrials.setText("Number of Trials");
        Spinner txtTotalTrials = new Spinner(composite_1, 2048);
        txtTotalTrials.setLayoutData((Object)new GridData(0x1000000, 0x1000000, true, true, 1, 1));
        txtTotalTrials.setMinimum(1);
        Group grpFieldBookFilename = new Group(container, 0);
        grpFieldBookFilename.setLayout((Layout)new GridLayout(1, false));
        GridData gd_grpFieldBookFilename = new GridData(4, 4, true, true, 1, 1);
        gd_grpFieldBookFilename.heightHint = 35;
        grpFieldBookFilename.setLayoutData((Object)gd_grpFieldBookFilename);
        grpFieldBookFilename.setText("Field Book Filename");
        Label lblFileName = new Label((Composite)grpFieldBookFilename, 0);
        lblFileName.setLayoutData((Object)new GridData(16384, 0x1000000, true, true, 1, 1));
        lblFileName.setFont(SWTResourceManager.getFont("Tahoma", 11, 0));
        lblFileName.setText(String.valueOf(new File(new File(filePath).getParent()).getName()) + "/" + new File(filePath).getName());
        return container;
    }

    protected void createButtonsForButtonBar(Composite parent) {
        this.createButton(parent, 0, IDialogConstants.OK_LABEL, true);
        this.createButton(parent, 1, IDialogConstants.CANCEL_LABEL, false);
    }

    protected Point getInitialSize() {
        return new Point(369, 448);
    }
}

