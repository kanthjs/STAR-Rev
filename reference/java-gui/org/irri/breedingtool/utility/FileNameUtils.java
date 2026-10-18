/*
 * Decompiled with CFR 0.152.
 */
package org.irri.breedingtool.utility;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Stack;

public class FileNameUtils {
    private static final char EXTENSION_SEPARATOR = '.';
    private static final char UNIX_SEPARATOR = '/';
    private static final char WINDOWS_SEPARATOR = '\\';
    private static final char SYSTEM_SEPARATOR = File.separatorChar;
    private static final char OTHER_SEPARATOR = SYSTEM_SEPARATOR == '\\' ? (char)47 : (char)92;

    private static boolean isSeparator(char ch) {
        return ch == '/' || ch == '\\';
    }

    public static String normalize(String filename) {
        return FileNameUtils.doNormalize(filename, true);
    }

    public static String normalizeNoEndSeparator(String filename) {
        return FileNameUtils.doNormalize(filename, false);
    }

    private static String doNormalize(String filename, boolean keepSeparator) {
        if (filename == null) {
            return null;
        }
        int size = filename.length();
        if (size == 0) {
            return filename;
        }
        int prefix = FileNameUtils.getPrefixLength(filename);
        if (prefix < 0) {
            return null;
        }
        char[] array = new char[size + 2];
        filename.getChars(0, filename.length(), array, 0);
        int i = 0;
        while (i < array.length) {
            if (array[i] == OTHER_SEPARATOR) {
                array[i] = SYSTEM_SEPARATOR;
            }
            ++i;
        }
        boolean lastIsDirectory = true;
        if (array[size - 1] != SYSTEM_SEPARATOR) {
            array[size++] = SYSTEM_SEPARATOR;
            lastIsDirectory = false;
        }
        int i2 = prefix + 1;
        while (i2 < size) {
            if (array[i2] == SYSTEM_SEPARATOR && array[i2 - 1] == SYSTEM_SEPARATOR) {
                System.arraycopy(array, i2, array, i2 - 1, size - i2);
                --size;
                --i2;
            }
            ++i2;
        }
        i2 = prefix + 1;
        while (i2 < size) {
            if (array[i2] == SYSTEM_SEPARATOR && array[i2 - 1] == '.' && (i2 == prefix + 1 || array[i2 - 2] == SYSTEM_SEPARATOR)) {
                if (i2 == size - 1) {
                    lastIsDirectory = true;
                }
                System.arraycopy(array, i2 + 1, array, i2 - 1, size - i2);
                size -= 2;
                --i2;
            }
            ++i2;
        }
        i2 = prefix + 2;
        while (i2 < size) {
            block20: {
                if (array[i2] == SYSTEM_SEPARATOR && array[i2 - 1] == '.' && array[i2 - 2] == '.' && (i2 == prefix + 2 || array[i2 - 3] == SYSTEM_SEPARATOR)) {
                    if (i2 == prefix + 2) {
                        return null;
                    }
                    if (i2 == size - 1) {
                        lastIsDirectory = true;
                    }
                    int j = i2 - 4;
                    while (j >= prefix) {
                        if (array[j] == SYSTEM_SEPARATOR) {
                            System.arraycopy(array, i2 + 1, array, j + 1, size - i2);
                            size -= i2 - j;
                            i2 = j + 1;
                            break block20;
                        }
                        --j;
                    }
                    System.arraycopy(array, i2 + 1, array, prefix, size - i2);
                    size -= i2 + 1 - prefix;
                    i2 = prefix + 1;
                }
            }
            ++i2;
        }
        if (size <= 0) {
            return "";
        }
        if (size <= prefix) {
            return new String(array, 0, size);
        }
        if (lastIsDirectory && keepSeparator) {
            return new String(array, 0, size);
        }
        return new String(array, 0, size - 1);
    }

    public static String concat(String basePath, String fullFilenameToAdd) {
        int prefix = FileNameUtils.getPrefixLength(fullFilenameToAdd);
        if (prefix < 0) {
            return null;
        }
        if (prefix > 0) {
            return FileNameUtils.normalize(fullFilenameToAdd);
        }
        if (basePath == null) {
            return null;
        }
        int len = basePath.length();
        if (len == 0) {
            return FileNameUtils.normalize(fullFilenameToAdd);
        }
        char ch = basePath.charAt(len - 1);
        if (FileNameUtils.isSeparator(ch)) {
            return FileNameUtils.normalize(String.valueOf(basePath) + fullFilenameToAdd);
        }
        return FileNameUtils.normalize(String.valueOf(basePath) + '/' + fullFilenameToAdd);
    }

    public static String separatorsToUnix(String path) {
        if (path == null || path.indexOf(92) == -1) {
            return path;
        }
        return path.replace('\\', '/');
    }

    public static String separatorsToWindows(String path) {
        if (path == null || path.indexOf(47) == -1) {
            return path;
        }
        return path.replace('/', '\\');
    }

    public static String separatorsToSystem(String path) {
        if (path == null) {
            return null;
        }
        if (SYSTEM_SEPARATOR == '\\') {
            return FileNameUtils.separatorsToWindows(path);
        }
        return FileNameUtils.separatorsToUnix(path);
    }

    public static int getPrefixLength(String filename) {
        if (filename == null) {
            return -1;
        }
        int len = filename.length();
        if (len == 0) {
            return 0;
        }
        char ch0 = filename.charAt(0);
        if (ch0 == ':') {
            return -1;
        }
        if (len == 1) {
            if (ch0 == '~') {
                return 2;
            }
            return FileNameUtils.isSeparator(ch0) ? 1 : 0;
        }
        if (ch0 == '~') {
            int posUnix = filename.indexOf(47, 1);
            int posWin = filename.indexOf(92, 1);
            if (posUnix == -1 && posWin == -1) {
                return len + 1;
            }
            posUnix = posUnix == -1 ? posWin : posUnix;
            posWin = posWin == -1 ? posUnix : posWin;
            return Math.min(posUnix, posWin) + 1;
        }
        char ch1 = filename.charAt(1);
        if (ch1 == ':') {
            if ((ch0 = Character.toUpperCase(ch0)) >= 'A' && ch0 <= 'Z') {
                if (len == 2 || !FileNameUtils.isSeparator(filename.charAt(2))) {
                    return 2;
                }
                return 3;
            }
            return -1;
        }
        if (FileNameUtils.isSeparator(ch0) && FileNameUtils.isSeparator(ch1)) {
            int posUnix = filename.indexOf(47, 2);
            int posWin = filename.indexOf(92, 2);
            if (posUnix == -1 && posWin == -1 || posUnix == 2 || posWin == 2) {
                return -1;
            }
            posUnix = posUnix == -1 ? posWin : posUnix;
            posWin = posWin == -1 ? posUnix : posWin;
            return Math.min(posUnix, posWin) + 1;
        }
        return FileNameUtils.isSeparator(ch0) ? 1 : 0;
    }

    public static int indexOfLastSeparator(String filename) {
        if (filename == null) {
            return -1;
        }
        int lastUnixPos = filename.lastIndexOf(47);
        int lastWindowsPos = filename.lastIndexOf(92);
        return Math.max(lastUnixPos, lastWindowsPos);
    }

    public static int indexOfExtension(String filename) {
        if (filename == null) {
            return -1;
        }
        int extensionPos = filename.lastIndexOf(46);
        int lastSeparator = FileNameUtils.indexOfLastSeparator(filename);
        return lastSeparator > extensionPos ? -1 : extensionPos;
    }

    public static String getPrefix(String filename) {
        if (filename == null) {
            return null;
        }
        int len = FileNameUtils.getPrefixLength(filename);
        if (len < 0) {
            return null;
        }
        if (len > filename.length()) {
            return String.valueOf(filename) + '/';
        }
        return filename.substring(0, len);
    }

    public static String getPath(String filename) {
        return FileNameUtils.doGetPath(filename, 1);
    }

    public static String getPathNoEndSeparator(String filename) {
        return FileNameUtils.doGetPath(filename, 0);
    }

    private static String doGetPath(String filename, int separatorAdd) {
        if (filename == null) {
            return null;
        }
        int prefix = FileNameUtils.getPrefixLength(filename);
        if (prefix < 0) {
            return null;
        }
        int index = FileNameUtils.indexOfLastSeparator(filename);
        if (prefix >= filename.length() || index < 0) {
            return "";
        }
        return filename.substring(prefix, index + separatorAdd);
    }

    public static String getFullPath(String filename) {
        return FileNameUtils.doGetFullPath(filename, true);
    }

    public static String getFullPathNoEndSeparator(String filename) {
        return FileNameUtils.doGetFullPath(filename, false);
    }

    private static String doGetFullPath(String filename, boolean includeSeparator) {
        if (filename == null) {
            return null;
        }
        int prefix = FileNameUtils.getPrefixLength(filename);
        if (prefix < 0) {
            return null;
        }
        if (prefix >= filename.length()) {
            if (includeSeparator) {
                return FileNameUtils.getPrefix(filename);
            }
            return filename;
        }
        int index = FileNameUtils.indexOfLastSeparator(filename);
        if (index < 0) {
            return filename.substring(0, prefix);
        }
        int end = index + (includeSeparator ? 1 : 0);
        return filename.substring(0, end);
    }

    public static String getName(String filename) {
        if (filename == null) {
            return null;
        }
        int index = FileNameUtils.indexOfLastSeparator(filename);
        return filename.substring(index + 1);
    }

    public static String getBaseName(String filename) {
        return FileNameUtils.removeExtension(FileNameUtils.getName(filename));
    }

    public static String getExtension(String filename) {
        if (filename == null) {
            return null;
        }
        int index = FileNameUtils.indexOfExtension(filename);
        if (index == -1) {
            return "";
        }
        return filename.substring(index + 1);
    }

    public static String removeExtension(String filename) {
        if (filename == null) {
            return null;
        }
        int index = FileNameUtils.indexOfExtension(filename);
        if (index == -1) {
            return filename;
        }
        return filename.substring(0, index);
    }

    public static boolean equals(String filename1, String filename2) {
        return FileNameUtils.equals(filename1, filename2, false, false);
    }

    public static boolean equalsOnSystem(String filename1, String filename2) {
        return FileNameUtils.equals(filename1, filename2, true, false);
    }

    public static boolean equalsNormalized(String filename1, String filename2) {
        return FileNameUtils.equals(filename1, filename2, false, true);
    }

    public static boolean equalsNormalizedOnSystem(String filename1, String filename2) {
        return FileNameUtils.equals(filename1, filename2, true, true);
    }

    private static boolean equals(String filename1, String filename2, boolean system, boolean normalized) {
        if (filename1 == filename2) {
            return true;
        }
        if (filename1 == null || filename2 == null) {
            return false;
        }
        if (normalized) {
            filename1 = FileNameUtils.normalize(filename1);
            filename2 = FileNameUtils.normalize(filename2);
        }
        if (system && SYSTEM_SEPARATOR == '\\') {
            return filename1.equalsIgnoreCase(filename2);
        }
        return filename1.equals(filename2);
    }

    public static boolean isExtension(String filename, String extension) {
        if (filename == null) {
            return false;
        }
        if (extension == null || extension.length() == 0) {
            return FileNameUtils.indexOfExtension(filename) == -1;
        }
        String fileExt = FileNameUtils.getExtension(filename);
        return fileExt.equals(extension);
    }

    public static boolean isExtension(String filename, String[] extensions) {
        if (filename == null) {
            return false;
        }
        if (extensions == null || extensions.length == 0) {
            return FileNameUtils.indexOfExtension(filename) == -1;
        }
        String fileExt = FileNameUtils.getExtension(filename);
        int i = 0;
        while (i < extensions.length) {
            if (fileExt.equals(extensions[i])) {
                return true;
            }
            ++i;
        }
        return false;
    }

    public static boolean isExtension(String filename, Collection extensions) {
        if (filename == null) {
            return false;
        }
        if (extensions == null || extensions.isEmpty()) {
            return FileNameUtils.indexOfExtension(filename) == -1;
        }
        String fileExt = FileNameUtils.getExtension(filename);
        Iterator it = extensions.iterator();
        while (it.hasNext()) {
            if (!fileExt.equals(it.next())) continue;
            return true;
        }
        return false;
    }

    public static boolean wildcardMatch(String filename, String wildcardMatcher) {
        return FileNameUtils.wildcardMatch(filename, wildcardMatcher, false);
    }

    public static boolean wildcardMatchOnSystem(String filename, String wildcardMatcher) {
        return FileNameUtils.wildcardMatch(filename, wildcardMatcher, true);
    }

    private static boolean wildcardMatch(String filename, String wildcardMatcher, boolean system) {
        if (filename == null && wildcardMatcher == null) {
            return true;
        }
        if (filename == null || wildcardMatcher == null) {
            return false;
        }
        if (system && SYSTEM_SEPARATOR == '\\') {
            filename = filename.toLowerCase();
            wildcardMatcher = wildcardMatcher.toLowerCase();
        }
        String[] wcs = FileNameUtils.splitOnTokens(wildcardMatcher);
        boolean anyChars = false;
        int textIdx = 0;
        int wcsIdx = 0;
        Stack<int[]> backtrack = new Stack<int[]>();
        do {
            if (backtrack.size() > 0) {
                int[] array = (int[])backtrack.pop();
                wcsIdx = array[0];
                textIdx = array[1];
                anyChars = true;
            }
            while (wcsIdx < wcs.length) {
                if (wcs[wcsIdx].equals("?")) {
                    ++textIdx;
                    anyChars = false;
                } else if (wcs[wcsIdx].equals("*")) {
                    anyChars = true;
                    if (wcsIdx == wcs.length - 1) {
                        textIdx = filename.length();
                    }
                } else {
                    if (anyChars) {
                        if ((textIdx = filename.indexOf(wcs[wcsIdx], textIdx)) == -1) break;
                        int repeat = filename.indexOf(wcs[wcsIdx], textIdx + 1);
                        if (repeat >= 0) {
                            backtrack.push(new int[]{wcsIdx, repeat});
                        }
                    } else if (!filename.startsWith(wcs[wcsIdx], textIdx)) break;
                    textIdx += wcs[wcsIdx].length();
                    anyChars = false;
                }
                ++wcsIdx;
            }
            if (wcsIdx != wcs.length || textIdx != filename.length()) continue;
            return true;
        } while (backtrack.size() > 0);
        return false;
    }

    static String[] splitOnTokens(String text) {
        if (text.indexOf("?") == -1 && text.indexOf("*") == -1) {
            return new String[]{text};
        }
        char[] array = text.toCharArray();
        ArrayList<String> list = new ArrayList<String>();
        StringBuffer buffer = new StringBuffer();
        int i = 0;
        while (i < array.length) {
            if (array[i] == '?' || array[i] == '*') {
                if (buffer.length() != 0) {
                    list.add(buffer.toString());
                    buffer.setLength(0);
                }
                if (array[i] == '?') {
                    list.add("?");
                } else if (list.size() == 0 || i > 0 && !list.get(list.size() - 1).equals("*")) {
                    list.add("*");
                }
            } else {
                buffer.append(array[i]);
            }
            ++i;
        }
        if (buffer.length() != 0) {
            list.add(buffer.toString());
        }
        return list.toArray(new String[0]);
    }
}

