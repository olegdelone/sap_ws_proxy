package ru.integral.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.integral.model.second.TypedEntry;
import ru.integral.model.second.TypedRow;
import ru.integral.model.first.AbsEntry;
import ru.integral.model.first.BigDecimalEntry;
import ru.integral.model.first.BytesEntry;
import ru.integral.model.first.DateEntry;
import ru.integral.model.first.DoubleEntry;
import ru.integral.model.first.IntegerEntry;
import ru.integral.model.first.Row;
import ru.integral.model.first.StringEntry;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ========== ItCorp v. 1.0 class library ==========
 * <p/>
 * http://www.it.ru/
 * <p/>
 * &copy; Copyright 1990-2013, by ItCorp.
 * <p/>
 * ========== ArgsConverter.java ==========
 * <p/>
 * $Revision: 18 $<br/>
 * $Author: NullPointer $<br/>
 * $HeadURL: file:///D:/work/local_repository/SAP_PROXY/trunk/src/main/java/ru/integral/helper/ArgsConverter.java $<br/>
 * $Id: ArgsConverter.java 18 2013-08-30 02:28:55Z NullPointer $
 * <p/>
 * 23.08.13 7:23: Original version (OOBUKHOV)<br/>
 */
public class ArgsConverter {
    private static transient Logger log = LoggerFactory.getLogger(ArgsConverter.class);

    public static Map<String, Object> convertListOfEntries2Map(List<AbsEntry> list){
//        log.debug("ArgsConverter.convertListOfEntries2Map called...");
        Map<String, Object> r = new HashMap<String, Object>(list.size());
        for (AbsEntry absEntry : list) {
            r.put(absEntry.getName(), absEntry.getValue());
        }
        return r;
    }

    public static List<AbsEntry> convertMap2ListOfAbsEntries(Map<String, ?> map){
//        log.debug("ArgsConverter.convertMap2ListOfAbsEntries called...");

        List<AbsEntry> absEntries = new ArrayList<AbsEntry>(map.size());
        for (Map.Entry<String, ?> entry : map.entrySet()) {
            Object inputValue = entry.getValue();
            String inputName = entry.getKey();
            absEntries.add(AbsEntry.createInstance(inputName, inputValue));
        }
        return absEntries;
    }

    public static List<Row> convertListOfMaps2ListOfRows(List<Map<String, Object>> maps){
        log.debug("ArgsConverter.convertListOfMaps2ListOfRows called...");

        List<Row> r = new ArrayList<Row>(maps.size());
        for (Map<String, ?> map : maps) {
            Row row = new Row();
            row.setCells(convertMap2ListOfAbsEntries(map));
            r.add(row);
        }
        return r;
    }

    public static List<Map<String, ?>> convertListOfRows2ListOfMaps(List<Row> list) {
        log.debug("ArgsConverter.convertListOfRows2ListOfMaps called...");

        List<Map<String, ?>> r = new ArrayList<Map<String, ?>>(list.size());
        for (Row row : list) {
            r.add(row.getAsMap());
        }
        return r;
    }

    //=========================


    public static Map<String, ?> convertListOfTypedEntries2Map(List<TypedEntry> list) {
//        log.debug("ArgsConverter.convertListOfTypedEntries2Map called...");

        Map<String, Object> r = new HashMap<String, Object>(list.size());
        for (TypedEntry typedEntry : list) {
            r.put(typedEntry.getName(), typedEntry.getValue());
        }
        return r;
    }

    public static List<TypedEntry> convertMap2ListOfTypedEntries(Map<String, ?> map){
//        log.debug("ArgsConverter.convertMap2ListOfTypedEntries called...");

        List<TypedEntry> typedEntries = new ArrayList<TypedEntry>(map.size());
        for (Map.Entry<String, ?> entry : map.entrySet()) {
            Object inputValue = entry.getValue();
            String name = entry.getKey();
            typedEntries.add(new TypedEntry(name, inputValue));
        }
        return typedEntries;
    }

    public static List<TypedRow> convertListOfMaps2ListOfTypedRows(List<Map<String, Object>> maps){
        log.debug("ArgsConverter.convertListOfMaps2ListOfTypedRows called...");

        List<TypedRow> r = new ArrayList<TypedRow>(maps.size());
        for (Map<String, ?> map : maps) {
            TypedRow row = new TypedRow();
            row.setCells(convertMap2ListOfTypedEntries(map));
            r.add(row);
        }
        return r;
    }

    public static List<Map<String, ?>> convertListOfTypedRows2ListOfMaps(List<TypedRow> list) {
        log.debug("ArgsConverter.convertListOfTypedRows2ListOfMaps called...");

        List<Map<String, ?>> r = new ArrayList<Map<String, ?>>(list.size());
        for (TypedRow row : list) {
            r.add(convertListOfTypedEntries2Map(row.getCells()));
        }
        return r;
    }
}
