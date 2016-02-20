package ru.integral.model;

import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoDestinationManager;
import com.sap.conn.jco.JCoException;
import com.sap.conn.jco.JCoField;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoParameterList;
import com.sap.conn.jco.JCoRepository;
import com.sap.conn.jco.JCoTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
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
 * ========== JCoBean.java ==========
 * <p/>
 * $Revision: 18 $<br/>
 * $Author: NullPointer $<br/>
 * $HeadURL: file:///D:/work/local_repository/SAP_PROXY/trunk/src/main/java/ru/integral/model/JCoBean.java $<br/>
 * $Id: JCoBean.java 18 2013-08-30 02:28:55Z NullPointer $
 * <p/>
 * 25.08.13 11:21: Original version (OOBUKHOV)<br/>
 */

// todo: encapsulates too much logic

public class JCoBean {
    private static transient Logger log = LoggerFactory.getLogger(JCoBean.class);

    private JCoDestination jCoDestination;
    private JCoRepository jCoRepository;
    private JCoFunction jCoFunction;

    public JCoBean(String destination, String function) throws JCoException {
        log.debug("constructing JCoBean...");
        JCoDestination jCoDestination = JCoDestinationManager.getDestination(destination);
        log.debug("jCoDestination: {}", jCoDestination);
        log.debug("jCoDestination.hashCode: {}", jCoDestination.hashCode());

        JCoRepository jCoRepository = jCoDestination.getRepository();
        log.debug("jCoRepository: {}", jCoRepository);
        if (jCoRepository == null) {
            throw new IllegalArgumentException("jCoRepository for destination " + String.valueOf(destination) + " is not found!");
        }

        JCoFunction jCoFunction = jCoRepository.getFunction(function);
        log.debug("JCoFunction: {}", jCoFunction);
        if (jCoFunction == null) {
            throw new IllegalArgumentException("JCoFunction: " + String.valueOf(function) + " is not found!");
        }

        this.jCoDestination = jCoDestination;
        this.jCoRepository = jCoRepository;
        this.jCoFunction = jCoFunction;
    }


    public void pushDataIntoTheTable(String tableName, List<Map<String, ?>> tableDataToBeInserted) {
        log.debug("JCoBean.pushDataIntoTheTable called...");
        JCoTable jCoTable = getTable(tableName);
        for (Map<String, ?> row : tableDataToBeInserted) {
            for (JCoField jCoField : jCoTable) {
                String name = jCoField.getName();
                Object val = row.get(name);
                if (val != null) {
                    jCoField.setValue(val);
                }
            }
        }
    }

    public Map<String, String> getImportParametersListOfFM() {
        log.debug("JCoBean.getImportParametersListOfFM called...");

        JCoParameterList jCoParameterList = jCoFunction.getImportParameterList();
        if(jCoParameterList == null){
            return Collections.emptyMap();
        }
        Map<String, String> result = new HashMap<String, String>();
        for (JCoField jCoField : jCoParameterList) {
            result.put(jCoField.getName(), jCoField.getTypeAsString());
        }
        return result;
    }

    public Map<String, String> getTableParametersListOfFM() {
        log.debug("JCoBean.getTableParametersListOfFM called...");

        JCoParameterList jCoParameterList = jCoFunction.getTableParameterList();
        if(jCoParameterList == null){
            return Collections.emptyMap();
        }
        Map<String, String> result = new HashMap<String, String>();
        for (JCoField jCoField : jCoParameterList) {
            result.put(jCoField.getName(), jCoField.getTypeAsString());
        }
        return result;
    }

    public void pushImportParams(Map<String, ?> importParams) {
        log.debug("JCoBean.pushImportParams called...");

        JCoParameterList jCoImportParameterList = jCoFunction.getImportParameterList();
        log.debug("jCoImportParameterList: {}", jCoImportParameterList);
        if (jCoImportParameterList != null && importParams != null) {
            for (Map.Entry<String, ?> importParam : importParams.entrySet()) {
                jCoImportParameterList.setValue(importParam.getKey(), importParam.getValue());
            }
        }
    }

    public Map<String, Object> popExportParams() {
        return popExportParams(null);
    }

    public Map<String, Object> popExportParams(List<String> exportFilterParams) {
        log.debug("JCoBean.popExportParams called...");

        JCoParameterList jCoExportParameterList = jCoFunction.getExportParameterList();
        Map<String, Object> result = new HashMap<String, Object>();
        for (JCoField jCoField : jCoExportParameterList) {
            String name = jCoField.getName();
            if (exportFilterParams == null || exportFilterParams.contains(name)) {
                result.put(name, jCoField.getValue());
            }
        }
        return result;
    }

    public void execute() throws JCoException {
        log.debug("executing of function: {}", jCoFunction);
        jCoFunction.execute(jCoDestination);
    }

    public List<Map<String, Object>> retrieveTableData(String tableName) {
        return retrieveTableData(tableName, null);
    }

    public List<Map<String, Object>> retrieveTableData(String tableName, List<String> filteringRowParams) {
        log.debug("JCoBean.retrieveTableData called...");

        JCoTable jCoTable = getTable(tableName);
        int rowSize = jCoTable.getNumRows();
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>(rowSize);
        log.debug("jCoTable.getNumRows(): {}", rowSize);
        for (int i = 0; i < rowSize; i++) {
            Map<String, Object> row = new HashMap<String, Object>();
            jCoTable.setRow(i);
            for (JCoField jCoField : jCoTable) {
                String name = jCoField.getName();
                if (filteringRowParams == null || filteringRowParams.contains(name)) {
                    Object value = jCoTable.getValue(name);
                    row.put(name, value);
                }
            }
            result.add(row);
        }
        return result;
    }

    public Map<String, String> getTableFieldsDescription(String tableName) {
        Map<String, String> r = new HashMap<String, String>();
        JCoTable jCoTable = getTable(tableName);
        for (JCoField jCoField : jCoTable) {
            r.put(jCoField.getName(),jCoField.getTypeAsString());
        }
        return r;
    }

    public JCoTable getTable(String tableName) {
        log.debug("JCoBean.getTable({}) called...", tableName);

        JCoParameterList jCoTableParameterList = jCoFunction.getTableParameterList();
        log.debug("jCoTableParameterList: {}", jCoTableParameterList);
        if (jCoTableParameterList == null) {
            throw new IllegalArgumentException("jCoTableParameterList is null for the given FM: " + jCoFunction);
        }

        JCoTable jCoTable = jCoTableParameterList.getTable(tableName);
        log.debug("jCoTable: {}", jCoTable);
        if (jCoTable == null) {
            throw new IllegalArgumentException("jCoTable: " + String.valueOf(tableName) + " does not exist in the given FM: " + jCoFunction);
        }
        return jCoTable;
    }

    public JCoDestination getjCoDestination() {
        return jCoDestination;
    }

    public JCoRepository getjCoRepository() {
        return jCoRepository;
    }

    public JCoFunction getjCoFunction() {
        return jCoFunction;
    }
}
