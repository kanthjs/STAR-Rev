/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.jface.dialogs.MessageDialog
 *  org.eclipse.swt.events.SelectionAdapter
 *  org.eclipse.swt.events.SelectionEvent
 *  org.eclipse.swt.events.SelectionListener
 *  org.eclipse.swt.layout.GridData
 *  org.eclipse.swt.layout.GridLayout
 *  org.eclipse.swt.widgets.Button
 *  org.eclipse.swt.widgets.Composite
 *  org.eclipse.swt.widgets.Dialog
 *  org.eclipse.swt.widgets.Display
 *  org.eclipse.swt.widgets.Layout
 *  org.eclipse.swt.widgets.Shell
 *  org.eclipse.swt.widgets.Tree
 *  org.eclipse.swt.widgets.TreeItem
 */
package org.irri.breedingtool.projectexplorer.dialog;

import java.io.File;
import java.util.ArrayList;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.wb.swt.ResourceManager;
import org.irri.breedingtool.application.model.ProjectExplorerTreeNodeModel;
import org.irri.breedingtool.manager.impl.ProjectExplorerManager;
import org.irri.breedingtool.projectexplorer.view.ProjectExplorerView;
import org.irri.breedingtool.projectexplorer.view.ProjectTreeComponent;

public class ImportFromOtherProjectsDialog
extends Dialog {
    protected Object result;
    protected Shell shlMoveTo;
    protected ArrayList<File> returnData = new ArrayList();

    public ImportFromOtherProjectsDialog(Shell parent) {
        super(parent, 16);
        this.setText("SWT Dialog");
    }

    public ArrayList<File> open() {
        this.createContents();
        this.shlMoveTo.open();
        this.shlMoveTo.layout();
        Display display = this.getParent().getDisplay();
        while (!this.shlMoveTo.isDisposed()) {
            if (display.readAndDispatch()) continue;
            display.sleep();
        }
        return this.returnData;
    }

    private void createContents() {
        this.shlMoveTo = new Shell(this.getParent(), 208);
        this.shlMoveTo.setSize(404, 517);
        this.shlMoveTo.setText("Import from Other Project");
        this.shlMoveTo.setLayout((Layout)new GridLayout(1, false));
        ProjectExplorerManager projectMan = new ProjectExplorerManager();
        Tree tree = new Tree((Composite)this.shlMoveTo, 2050);
        tree.setLayoutData((Object)new GridData(4, 4, true, true, 1, 1));
        TreeItem trtmProject = new TreeItem(tree, 0);
        trtmProject.setImage(ResourceManager.getPluginImage("Star", "icons/package.png"));
        trtmProject.setText("Projects");
        Composite composite = new Composite((Composite)this.shlMoveTo, 0);
        composite.setLayout((Layout)new GridLayout(2, false));
        composite.setLayoutData((Object)new GridData(131072, 4, true, false, 1, 1));
        Button btnChooseFolder = new Button(composite, 0);
        btnChooseFolder.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                int i = 0;
                while (i < tree.getSelectionCount()) {
                    if (tree.getSelection()[i].getData() != null) {
                        if (tree.getSelection()[i].getParentItem().getData() != null) {
                            if (!ImportFromOtherProjectsDialog.this.returnData.contains(((ProjectExplorerTreeNodeModel)tree.getSelection()[i].getParentItem().getData()).getProjectFile())) {
                                ImportFromOtherProjectsDialog.this.returnData.add(((ProjectExplorerTreeNodeModel)tree.getSelection()[i].getData()).getProjectFile());
                            }
                        } else {
                            ImportFromOtherProjectsDialog.this.returnData.add(((ProjectExplorerTreeNodeModel)tree.getSelection()[i].getData()).getProjectFile());
                        }
                    }
                    ++i;
                }
                ImportFromOtherProjectsDialog.this.shlMoveTo.close();
            }
        });
        btnChooseFolder.setLayoutData((Object)new GridData(131072, 0x1000000, true, false, 1, 1));
        btnChooseFolder.setText("Import Data");
        Button btnCancel = new Button(composite, 0);
        btnCancel.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                ImportFromOtherProjectsDialog.this.returnData = null;
                ImportFromOtherProjectsDialog.this.shlMoveTo.close();
            }
        });
        btnCancel.setLayoutData((Object)new GridData(131072, 0x1000000, true, false, 1, 1));
        btnCancel.setText("Cancel");
        File projectFile = new File(ProjectTreeComponent.getCurrentWorkspacePath());
        projectMan.listFolder(projectFile, trtmProject);
        projectMan.listFiles(projectFile, trtmProject);
        trtmProject.setExpanded(true);
        int i = 0;
        while (i < trtmProject.getItemCount()) {
            if (trtmProject.getItem(i).getText().equals(ProjectExplorerView.projectTree.getText())) {
                trtmProject.getItem(i).dispose();
            }
            ++i;
        }
        if (trtmProject.getItemCount() == 0) {
            MessageDialog.openWarning((Shell)Display.getCurrent().getActiveShell(), (String)"", (String)"There is no other project to import");
            return;
        }
    }
}

