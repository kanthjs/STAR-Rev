/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
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
import org.irri.breedingtool.projectexplorer.view.ProjectTreeComponent;

public class MoveToDialog
extends Dialog {
    protected Object result;
    protected Shell shlMoveTo;
    protected File returnFolder;

    public MoveToDialog(Shell parent) {
        super(parent, 16);
        this.setText("SWT Dialog");
    }

    public File open() {
        this.createContents();
        this.shlMoveTo.open();
        this.shlMoveTo.layout();
        Display display = this.getParent().getDisplay();
        while (!this.shlMoveTo.isDisposed()) {
            if (display.readAndDispatch()) continue;
            display.sleep();
        }
        return this.returnFolder;
    }

    private void createContents() {
        this.shlMoveTo = new Shell(this.getParent(), 208);
        this.shlMoveTo.setSize(427, 537);
        this.shlMoveTo.setText("Move to");
        this.shlMoveTo.setLayout((Layout)new GridLayout(1, false));
        ProjectExplorerManager projectMan = new ProjectExplorerManager();
        Tree tree = new Tree((Composite)this.shlMoveTo, 2048);
        tree.setLayoutData((Object)new GridData(4, 4, true, true, 1, 1));
        TreeItem leafData = new TreeItem(tree, 0);
        leafData.setImage(ResourceManager.getPluginImage("Star", "icons/folder.png"));
        leafData.setText("Data");
        leafData.setExpanded(true);
        Composite composite = new Composite((Composite)this.shlMoveTo, 0);
        composite.setLayout((Layout)new GridLayout(2, false));
        composite.setLayoutData((Object)new GridData(131072, 4, true, false, 1, 1));
        Button btnChooseFolder = new Button(composite, 0);
        btnChooseFolder.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                TreeItem cfr_ignored_0 = tree.getSelection()[0];
                MoveToDialog.this.returnFolder = ((ProjectExplorerTreeNodeModel)tree.getSelection()[0].getData()).getProjectFile();
                MoveToDialog.this.shlMoveTo.close();
            }
        });
        btnChooseFolder.setLayoutData((Object)new GridData(131072, 0x1000000, true, false, 1, 1));
        btnChooseFolder.setText("Choose Folder");
        Button btnCancel = new Button(composite, 0);
        btnCancel.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                MoveToDialog.this.returnFolder = null;
                MoveToDialog.this.shlMoveTo.close();
            }
        });
        btnCancel.setLayoutData((Object)new GridData(131072, 0x1000000, true, false, 1, 1));
        btnCancel.setText("Cancel");
        File projectFile = new File(String.valueOf(ProjectTreeComponent.getCurrentWorkspacePath()) + projectMan.getLastOpenedProject() + File.separator + "Data");
        leafData.setData((Object)new ProjectExplorerTreeNodeModel(projectFile, leafData));
        projectMan.listFolder(projectFile, leafData);
    }
}

