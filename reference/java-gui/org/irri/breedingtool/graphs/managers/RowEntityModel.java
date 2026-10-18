/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.swt.events.KeyListener
 *  org.eclipse.swt.events.ModifyListener
 *  org.eclipse.swt.events.SelectionListener
 */
package org.irri.breedingtool.graphs.managers;

import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionListener;

public class RowEntityModel {
    public int type;
    public Object value;
    public int max_value;
    public int min_value;
    public SelectionListener selectionListener;
    public ModifyListener modifyListener;
    public KeyListener keyListener;
    public int decimal = 0;

    public RowEntityModel(int type, Object value, int min, int max) {
        this.type = type;
        this.value = value;
        this.max_value = max;
        this.min_value = min;
    }

    public RowEntityModel(int type, Object value, int min, int max, int dec) {
        this.type = type;
        this.value = value;
        this.max_value = max;
        this.min_value = min;
        this.decimal = dec;
    }

    public RowEntityModel(int type, Object value) {
        this.type = type;
        this.value = value;
    }

    public RowEntityModel(int type, Object value, int min, int max, SelectionListener selectionListener, ModifyListener modifyListener, KeyListener keyListener) {
        this.type = type;
        this.value = value;
        this.max_value = max;
        this.min_value = min;
        this.selectionListener = selectionListener;
        this.modifyListener = modifyListener;
        this.keyListener = keyListener;
    }

    public RowEntityModel(int type, Object value, int min, int max, KeyListener keyListener) {
        this.type = type;
        this.value = value;
        this.max_value = max;
        this.min_value = min;
        this.keyListener = keyListener;
    }

    public RowEntityModel(int type, Object value, int min, int max, SelectionListener selectionListener) {
        this.type = type;
        this.value = value;
        this.max_value = max;
        this.min_value = min;
        this.selectionListener = selectionListener;
    }

    public RowEntityModel(int type, Object value, int min, int max, ModifyListener modifyListener) {
        this.type = type;
        this.value = value;
        this.max_value = max;
        this.min_value = min;
        this.modifyListener = modifyListener;
    }
}

