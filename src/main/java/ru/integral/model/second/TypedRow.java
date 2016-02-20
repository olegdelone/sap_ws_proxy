package ru.integral.model.second;

import ru.integral.helper.ArgsConverter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * ========== Row.java ==========
 * <p/>
 * $Revision: 15 $<br/>
 * $Author: NullPointer $<br/>
 * $HeadURL: file:///D:/work/local_repository/SAP_PROXY/trunk/src/main/java/ru/integral/model/second/TypedRow.java $<br/>
 * $Id: TypedRow.java 15 2013-08-25 08:37:23Z NullPointer $
 * <p/>
 * 21.08.13 16:15: Original version (OOBUKHOV)<br/>
 */
public class TypedRow {
    private List<TypedEntry> cells;

    public TypedRow() {
        cells = new ArrayList<TypedEntry>();
    }

    public List<TypedEntry> getCells() {
        return cells;
    }

    public void setCells(List<TypedEntry> cells) {
        this.cells = cells;
    }

    public void addCell(TypedEntry cell) {
        cells.add(cell);
    }

    @Override
    public String toString() {
        return "TypedRow{" +
                "cells=" + cells +
                '}';
    }
}
