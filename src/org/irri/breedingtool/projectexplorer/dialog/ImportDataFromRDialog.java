/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.jface.dialogs.Dialog
 *  org.eclipse.jface.dialogs.IDialogConstants
 *  org.eclipse.jface.dialogs.MessageDialog
 *  org.eclipse.swt.graphics.Point
 *  org.eclipse.swt.widgets.Composite
 *  org.eclipse.swt.widgets.Control
 *  org.eclipse.swt.widgets.Label
 *  org.eclipse.swt.widgets.List
 *  org.eclipse.swt.widgets.Shell
 */
package org.irri.breedingtool.projectexplorer.dialog;

import java.io.File;
import java.util.regex.Pattern;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.irri.breedingtool.projectexplorer.view.ProjectExplorerView;
import org.irri.breedingtool.projectexplorer.view.RJavaManagerInitializer;

public class ImportDataFromRDialog
extends Dialog {
    private String pkgName = RJavaManagerInitializer.PACKAGE_NAME;
    private List list;
    private String outputFolderPath;

    public ImportDataFromRDialog(Shell parentShell, String outputFolderPath) {
        super(parentShell);
        this.outputFolderPath = outputFolderPath;
        System.out.println("final folder path: " + outputFolderPath);
    }

    protected void configureShell(Shell shell) {
        super.configureShell(shell);
        shell.setText("Import Data from R");
    }

    protected Control createDialogArea(Composite parent) {
        Composite container = (Composite)super.createDialogArea(parent);
        container.setLayout(null);
        Label lblDatasetInStar = new Label(container, 0);
        lblDatasetInStar.setBounds(10, 10, 164, 17);
        lblDatasetInStar.setText("Dataset in " + this.pkgName + " Package");
        this.list = new List(container, 2818);
        this.list.setBounds(10, 29, 201, 144);
        try {
            this.list.setItems(ProjectExplorerView.rJavaManager.getRJavaDataManipulationManager().getDataInPackage(this.pkgName));
        }
        catch (Exception exception) {
            MessageDialog.openWarning((Shell)this.getShell(), (String)("Failed to load data from " + this.pkgName), (String)("The package " + this.pkgName + " is empty."));
            this.getShell().close();
        }
        return container;
    }

    protected void okPressed() {
        if (this.list.getSelectionCount() > 0) {
            int i = 0;
            while (i < this.list.getSelectionCount()) {
                String dataset = this.list.getSelection()[i];
                String outputPath = this.outputFolderPath.replaceAll(Pattern.quote(File.separator), "/");
                System.out.println("final folder path: " + outputPath);
                ProjectExplorerView.rJavaManager.getRJavaDataManipulationManager().loadDataInPackage(this.pkgName, dataset, String.valueOf(outputPath) + "/");
                ++i;
            }
            MessageDialog.openInformation((Shell)this.getShell(), (String)"Imported data from package.", (String)("Successfully imported data from " + this.pkgName + "."));
            this.close();
        } else {
            MessageDialog.openWarning((Shell)this.getShell(), (String)"Invalid Input.", (String)"Please select the file you want to import.");
        }
    }

    protected void createButtonsForButtonBar(Composite parent) {
        this.createButton(parent, 0, IDialogConstants.OK_LABEL, true);
        this.createButton(parent, 1, IDialogConstants.CANCEL_LABEL, false);
    }

    protected Point getInitialSize() {
        return new Point(227, 284);
    }
}

