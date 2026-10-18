/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.swt.dnd.Clipboard
 *  org.eclipse.swt.dnd.FileTransfer
 *  org.eclipse.swt.dnd.Transfer
 *  org.eclipse.swt.widgets.Display
 *  org.mozilla.universalchardet.UniversalDetector
 */
package org.irri.breedingtool.utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.FileTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Display;
import org.mozilla.universalchardet.UniversalDetector;

public class GeneralUtility {
    public static String replaceLast(String string, String toReplace, String replacement) {
        int pos = string.lastIndexOf(toReplace);
        if (pos > -1) {
            return String.valueOf(string.substring(0, pos)) + replacement + string.substring(pos + toReplace.length(), string.length());
        }
        return string;
    }

    public static boolean isFileValidData(String filePath) {
        if (filePath == null) {
            return false;
        }
        return filePath.endsWith(".csv");
    }

    public static boolean validateFileName(String value) {
        char[] charSet = value.toCharArray();
        if (!Character.isLetter(charSet[0])) {
            return false;
        }
        int i = 1;
        while (i < charSet.length) {
            if (!Character.isLetterOrDigit(charSet[i]) && charSet[i] != '.' && charSet[i] != '_' && charSet[i] != ' ' && charSet[i] != '-') {
                return false;
            }
            if (i == charSet.length - 1 && !Character.isLetterOrDigit(charSet[i])) {
                return false;
            }
            ++i;
        }
        return true;
    }

    public static String getFileExtension(String fullPath) {
        int dot = fullPath.lastIndexOf(46);
        return fullPath.substring(dot + 1);
    }

    public static boolean isClipboardEmpty() {
        Clipboard clipboard = new Clipboard(Display.getCurrent());
        String[] data = (String[])clipboard.getContents((Transfer)FileTransfer.getInstance());
        return data == null;
    }

    public static String combineArrayOfString(String[] s, String glue) {
        int k = s.length;
        if (k == 0) {
            return null;
        }
        StringBuilder out = new StringBuilder();
        out.append(s[0]);
        int x = 1;
        while (x < k) {
            out.append(glue).append(s[x]);
            ++x;
        }
        return out.toString();
    }

    public static boolean isDirectoryWritable(String folderPath) {
        return true;
    }

    public static <T> T[] arrayConcatAll(T[] first, T[] ... rest) {
        int totalLength = first.length;
        T[][] TArray = rest;
        int n = rest.length;
        int n2 = 0;
        while (n2 < n) {
            T[] array = TArray[n2];
            totalLength += array.length;
            ++n2;
        }
        T[] result = Arrays.copyOf(first, totalLength);
        int offset = first.length;
        T[][] TArray2 = rest;
        int n3 = rest.length;
        int n4 = 0;
        while (n4 < n3) {
            T[] array = TArray2[n4];
            System.arraycopy(array, 0, result, offset, array.length);
            offset += array.length;
            ++n4;
        }
        return result;
    }

    public static String getFileEncoding(String filePath) {
        byte[] buf = new byte[4096];
        String fileName = filePath;
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(fileName);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        UniversalDetector detector = new UniversalDetector(null);
        try {
            int nread;
            while ((nread = fis.read(buf)) > 0 && !detector.isDone()) {
                detector.handleData(buf, 0, nread);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        detector.dataEnd();
        String encoding = detector.getDetectedCharset();
        if (encoding != null) {
            System.out.println("Detected encoding = " + encoding);
            return encoding;
        }
        System.out.println("No encoding detected.");
        return null;
    }
}

