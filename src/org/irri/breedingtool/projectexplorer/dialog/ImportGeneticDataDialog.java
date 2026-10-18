/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.jface.dialogs.Dialog
 *  org.eclipse.jface.dialogs.IDialogConstants
 *  org.eclipse.jface.dialogs.MessageDialog
 *  org.eclipse.swt.graphics.Point
 *  org.eclipse.swt.layout.FormAttachment
 *  org.eclipse.swt.layout.FormData
 *  org.eclipse.swt.layout.FormLayout
 *  org.eclipse.swt.layout.GridData
 *  org.eclipse.swt.layout.GridLayout
 *  org.eclipse.swt.widgets.Button
 *  org.eclipse.swt.widgets.Combo
 *  org.eclipse.swt.widgets.Composite
 *  org.eclipse.swt.widgets.Control
 *  org.eclipse.swt.widgets.Group
 *  org.eclipse.swt.widgets.Label
 *  org.eclipse.swt.widgets.Layout
 *  org.eclipse.swt.widgets.Shell
 *  org.eclipse.swt.widgets.Spinner
 *  org.eclipse.swt.widgets.Text
 */
package org.irri.breedingtool.projectexplorer.dialog;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Text;
import org.irri.breedingtool.projectexplorer.view.RJavaManagerInitializer;

public class ImportGeneticDataDialog
extends Dialog {
    private String pkgName = RJavaManagerInitializer.PACKAGE_NAME;
    private String outputFolderPath;
    private Text txtGeneticfile;

    public ImportGeneticDataDialog(Shell parentShell, String outputFolderPath) {
        super(parentShell);
        this.outputFolderPath = outputFolderPath;
        System.out.println("final folder path: " + outputFolderPath);
    }

    protected void configureShell(Shell shell) {
        super.configureShell(shell);
        shell.setText("Import Data");
    }

    protected Control createDialogArea(Composite parent) {
        Composite container = (Composite)super.createDialogArea(parent);
        container.setLayout((Layout)new FormLayout());
        Group grpSelectFromThe = new Group(container, 0);
        grpSelectFromThe.setLayout((Layout)new GridLayout(3, false));
        FormData fd_grpSelectFromThe = new FormData();
        fd_grpSelectFromThe.top = new FormAttachment(0, 10);
        fd_grpSelectFromThe.left = new FormAttachment(0, 10);
        fd_grpSelectFromThe.right = new FormAttachment(100, -10);
        grpSelectFromThe.setLayoutData((Object)fd_grpSelectFromThe);
        grpSelectFromThe.setText("Select from the following file formats:");
        Button btngtxgenetix = new Button((Composite)grpSelectFromThe, 16);
        GridData gd_btngtxgenetix = new GridData(16384, 0x1000000, false, false, 3, 1);
        gd_btngtxgenetix.heightHint = 25;
        btngtxgenetix.setLayoutData((Object)gd_btngtxgenetix);
        btngtxgenetix.setText(".gtx (GENETIX)");
        Button btnstrstrustructure = new Button((Composite)grpSelectFromThe, 16);
        GridData gd_btnstrstrustructure = new GridData(16384, 0x1000000, false, false, 3, 1);
        gd_btnstrstrustructure.heightHint = 25;
        btnstrstrustructure.setLayoutData((Object)gd_btnstrstrustructure);
        btnstrstrustructure.setText(".str/.stru (STRUCTURE)");
        Button btndatfstat = new Button((Composite)grpSelectFromThe, 16);
        GridData gd_btndatfstat = new GridData(16384, 0x1000000, false, false, 3, 1);
        gd_btndatfstat.heightHint = 25;
        btndatfstat.setLayoutData((Object)gd_btndatfstat);
        btndatfstat.setText(".dat (FSTAT)");
        Button btngengenepop = new Button((Composite)grpSelectFromThe, 16);
        GridData gd_btngengenepop = new GridData(16384, 0x1000000, false, false, 3, 1);
        gd_btngengenepop.heightHint = 25;
        btngengenepop.setLayoutData((Object)gd_btngengenepop);
        btngengenepop.setText(".gen (Genepop)");
        Button btncsv = new Button((Composite)grpSelectFromThe, 16);
        GridData gd_btncsv = new GridData(16384, 0x1000000, false, false, 3, 1);
        gd_btncsv.heightHint = 25;
        btncsv.setLayoutData((Object)gd_btncsv);
        btncsv.setText(".csv");
        Label lblNewLabel = new Label((Composite)grpSelectFromThe, 0);
        GridData gd_lblNewLabel = new GridData(16384, 0x1000000, false, false, 1, 1);
        gd_lblNewLabel.widthHint = 20;
        lblNewLabel.setLayoutData((Object)gd_lblNewLabel);
        Label lblIndividual = new Label((Composite)grpSelectFromThe, 0);
        lblIndividual.setText("Individual:");
        Group grpSaveFileAs = new Group(container, 0);
        fd_grpSelectFromThe.bottom = new FormAttachment((Control)grpSaveFileAs, -6);
        grpSaveFileAs.setText("Save file as:");
        FormData fd_grpSaveFileAs = new FormData();
        fd_grpSaveFileAs.left = new FormAttachment(0, 10);
        fd_grpSaveFileAs.right = new FormAttachment(100, -10);
        fd_grpSaveFileAs.bottom = new FormAttachment(100, -10);
        fd_grpSaveFileAs.top = new FormAttachment(0, 336);
        Combo combo = new Combo((Composite)grpSelectFromThe, 0);
        combo.setItems(new String[]{"<none>"});
        combo.setLayoutData((Object)new GridData(4, 0x1000000, false, false, 1, 1));
        combo.select(0);
        new Label((Composite)grpSelectFromThe, 0);
        Label lblPopulation = new Label((Composite)grpSelectFromThe, 0);
        lblPopulation.setText("Population:");
        Combo combo_1 = new Combo((Composite)grpSelectFromThe, 0);
        combo_1.setItems(new String[]{"<none>"});
        combo_1.setLayoutData((Object)new GridData(4, 0x1000000, false, false, 1, 1));
        combo_1.select(0);
        new Label((Composite)grpSelectFromThe, 0);
        Label lblPloidy = new Label((Composite)grpSelectFromThe, 0);
        lblPloidy.setText("Ploidy:");
        Spinner spinner = new Spinner((Composite)grpSelectFromThe, 2048);
        spinner.setMinimum(2);
        spinner.setSelection(2);
        new Label((Composite)grpSelectFromThe, 0);
        Label lblType = new Label((Composite)grpSelectFromThe, 0);
        lblType.setText("Type:");
        Combo combo_2 = new Combo((Composite)grpSelectFromThe, 0);
        combo_2.setItems(new String[]{"codominant"});
        combo_2.setLayoutData((Object)new GridData(4, 0x1000000, false, false, 1, 1));
        combo_2.select(0);
        new Label((Composite)grpSelectFromThe, 0);
        Label lblSeparator = new Label((Composite)grpSelectFromThe, 0);
        lblSeparator.setText("Separator:");
        Combo combo_3 = new Combo((Composite)grpSelectFromThe, 0);
        combo_3.setLayoutData((Object)new GridData(4, 0x1000000, false, false, 1, 1));
        combo_3.setItems(new String[]{"none"});
        combo_3.select(0);
        grpSaveFileAs.setLayout((Layout)new GridLayout(3, false));
        grpSaveFileAs.setLayoutData((Object)fd_grpSaveFileAs);
        Label lblFilename = new Label((Composite)grpSaveFileAs, 0);
        lblFilename.setText("Filename:");
        this.txtGeneticfile = new Text((Composite)grpSaveFileAs, 2048);
        this.txtGeneticfile.setLayoutData((Object)new GridData(4, 0x1000000, true, false, 1, 1));
        this.txtGeneticfile.setText("GeneticFile");
        Label lblrdata = new Label((Composite)grpSaveFileAs, 0);
        lblrdata.setText(".RData");
        return container;
    }

    protected void okPressed() {
        MessageDialog.openInformation((Shell)this.getShell(), (String)"Imported data from package.", (String)("Successfully imported data from " + this.pkgName + "."));
        this.close();
    }

    protected void createButtonsForButtonBar(Composite parent) {
        this.createButton(parent, 0, IDialogConstants.OK_LABEL, true);
        this.createButton(parent, 1, IDialogConstants.CANCEL_LABEL, false);
    }

    protected Point getInitialSize() {
        return new Point(323, 483);
    }
}

