/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.e4.ui.model.application.ui.basic.MPart
 *  org.eclipse.e4.ui.model.application.ui.basic.MStackElement
 *  org.eclipse.swt.widgets.TreeItem
 */
package org.irri.breedingtool.application.model;

import java.io.File;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.model.application.ui.basic.MStackElement;
import org.eclipse.swt.widgets.TreeItem;

public class ProjectExplorerTreeNodeModel {
    private String elementID;
    private TreeItem treeItem;
    private TreeItem parentTreeItem;
    private MPart elementInStack;
    private File projectFile;
    private boolean modifiedStatus;
    private String fileType;

    public ProjectExplorerTreeNodeModel(File file, TreeItem leaf) {
        this.treeItem = leaf;
        this.elementID = file.getPath();
        this.projectFile = file;
        this.parentTreeItem = leaf.getParentItem();
        this.modifiedStatus = false;
        this.fileType = file.isDirectory() ? "Folder" : file.getName().toString().substring(file.getName().toString().length() - 4);
    }

    public String getElementID() {
        return this.elementID;
    }

    public TreeItem getTreeItem() {
        return this.treeItem;
    }

    public TreeItem getParentTreeItem() {
        return this.parentTreeItem;
    }

    public MStackElement getElementInStack() {
        return this.elementInStack;
    }

    public void setElementInStack(MPart elementInStack) {
        this.elementInStack = elementInStack;
    }

    public File getProjectFile() {
        return this.projectFile;
    }

    public boolean isModified() {
        return this.modifiedStatus;
    }

    public void setModifiedStatus(boolean modifiedStatus) {
        this.modifiedStatus = modifiedStatus;
    }

    public String getFileType() {
        return this.fileType;
    }
}

