package ru.integral.model.first;

import ru.integral.helper.ArgsConverter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * ========== ItCorp v. 1.0 class library ==========
 * <p/>
 * http://www.it.ru/
 * <p/>
 * &copy; Copyright 1990-2013, by ItCorp.
 * <p/>
 * ========== Row.java ==========
 * <p/>
 * $Revision: 9 $<br/>
 * $Author: NullPointer $<br/>
 * $HeadURL: file:///D:/work/local_repository/SAP_PROXY/trunk/src/main/java/ru/integral/model/first/Row.java $<br/>
 * $Id: Row.java 9 2013-08-24 06:26:05Z NullPointer $
 * <p/>
 * 21.08.13 16:15: Original version (OOBUKHOV)<br/>
 */
public class Row {
    private List<AbsEntry> cells;

    public Row() {
        cells = new ArrayList<AbsEntry>();
    }

    public List<AbsEntry> getCells() {
        return cells;
    }

    public void setCells(List<AbsEntry> cells) {
        this.cells = cells;
    }

    public void addCell(AbsEntry cell) {
        cells.add(cell);
    }

    public Map<String, Object> getAsMap(){
        return ArgsConverter.convertListOfEntries2Map(cells);
    }

    @Override
    public String toString() {
        return "Row{" +
                "cells=" + cells +
                '}';
    }
}
