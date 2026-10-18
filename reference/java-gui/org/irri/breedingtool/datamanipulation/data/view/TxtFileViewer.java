/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.inject.Inject
 *  org.eclipse.e4.ui.di.Focus
 *  org.eclipse.e4.ui.model.application.ui.basic.MPart
 *  org.eclipse.swt.custom.StyledText
 *  org.eclipse.swt.dnd.Clipboard
 *  org.eclipse.swt.dnd.TextTransfer
 *  org.eclipse.swt.dnd.Transfer
 *  org.eclipse.swt.events.SelectionAdapter
 *  org.eclipse.swt.events.SelectionEvent
 *  org.eclipse.swt.events.SelectionListener
 *  org.eclipse.swt.widgets.Composite
 *  org.eclipse.swt.widgets.Control
 *  org.eclipse.swt.widgets.Layout
 *  org.eclipse.swt.widgets.Menu
 *  org.eclipse.swt.widgets.MenuItem
 *  org.eclipse.swt.widgets.ToolBar
 *  swing2swt.layout.BorderLayout
 */
package org.irri.breedingtool.datamanipulation.data.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;
import javax.inject.Inject;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.wb.swt.SWTResourceManager;
import org.irri.breedingtool.application.handler.DialogHandler;
import org.irri.breedingtool.application.handler.PartStackHandler;
import org.irri.breedingtool.application.model.ProjectExplorerTreeNodeModel;
import org.irri.breedingtool.utility.GeneralUtility;
import swing2swt.layout.BorderLayout;

public class TxtFileViewer {
    static File txtFile;
    static Scanner opnScanner;
    static StyledText styledText;
    private String outputFile;

    @Inject
    public TxtFileViewer(Composite parent) {
        Composite composite = new Composite(parent, 0);
        composite.setLayout((Layout)new BorderLayout(0, 0));
        new ArrayList();
        StyledText stOutput = new StyledText(composite, 2824);
        stOutput.setIndent(5);
        stOutput.setFont(SWTResourceManager.getFont("Courier New", 10, 0));
        try {
            stOutput.setText(TxtFileViewer.readFileAsString(txtFile.getAbsolutePath(), "utf-8"));
        }
        catch (IOException e1) {
            e1.printStackTrace();
        }
        Menu menu = new Menu((Control)stOutput);
        stOutput.setMenu(menu);
        MenuItem mntmCopy = new MenuItem(menu, 0);
        mntmCopy.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                Clipboard clipboard = new Clipboard(parent.getDisplay());
                String plainText = stOutput.getSelectionText();
                TextTransfer textTransfer = TextTransfer.getInstance();
                clipboard.setContents((Object[])new String[]{plainText}, new Transfer[]{textTransfer});
                clipboard.dispose();
            }
        });
        mntmCopy.setText("Copy");
        ToolBar toolBar = new ToolBar(composite, 0x820000);
        toolBar.setLayoutData((Object)"North");
        try {
            opnScanner = new Scanner(txtFile);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("file not found.");
        }
        while (opnScanner.hasNext()) {
            String newLine = opnScanner.nextLine();
            newLine = newLine.replaceAll(" ", "\t");
            try {
                styledText.setText(String.valueOf(styledText.getText()) + newLine + "\n");
            }
            catch (Exception exception) {}
        }
        opnScanner.close();
    }

    public static void openTxtFile(ProjectExplorerTreeNodeModel fileM) {
        txtFile = new File(fileM.getProjectFile().getAbsolutePath());
    }

    public static boolean saveTxtFileChanges(File file) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(file);
            String newLine = styledText.getText();
            newLine = newLine.replaceAll("\t", " ");
            writer.write(styledText.getText());
            if (writer != null) {
                writer.close();
            }
            return true;
        }
        catch (Exception e) {
            System.out.print(e + "\n");
            return false;
        }
    }

    public static String readFileAsString(String fileName, String charsetName) throws IOException {
        charsetName = GeneralUtility.getFileEncoding(fileName);
        FileInputStream is = new FileInputStream(fileName);
        try {
            int available = ((InputStream)is).available();
            byte[] data = new byte[available < 4096 ? 4096 : available];
            int used = 0;
            while (true) {
                int got;
                if (data.length - used < 4096) {
                    byte[] newData = new byte[data.length << 1];
                    System.arraycopy(data, 0, newData, 0, used);
                    data = newData;
                }
                if ((got = ((InputStream)is).read(data, used, data.length - used)) <= 0) break;
                used += got;
            }
            String string = charsetName != null ? new String(data, 0, used, charsetName) : new String(data, 0, used);
            return string;
        }
        finally {
            ((InputStream)is).close();
        }
    }

    @Focus
    public void onFocus(MPart part) {
        DialogHandler.setPartDialogMaximized(PartStackHandler.getActiveElementID());
    }
}

