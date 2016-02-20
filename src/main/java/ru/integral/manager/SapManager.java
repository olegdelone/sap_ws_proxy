package ru.integral.manager;

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
import org.springframework.stereotype.Component;
import ru.integral.model.JCoBean;
import ru.integral.utils.SapConnector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ========== SapManager.java ==========
 * <p/>
 * $Revision: 18 $<br/>
 * $Author: NullPointer $<br/>
 * $HeadURL: file:///D:/work/local_repository/SAP_PROXY/trunk/src/main/java/ru/integral/manager/SapManager.java $<br/>
 * $Id: SapManager.java 18 2013-08-30 02:28:55Z NullPointer $
 * <p/>
 * 20.08.13 16:47: Original version (OOBUKHOV)<br/>
 */
@Component
public class SapManager {
    private static transient Logger log = LoggerFactory.getLogger(SapManager.class);


    /**
     * @param functionalModule
     * @param tableName
     * @param importParams
     * @return
     * @throws JCoException
     */
    public List<Map<String, Object>> retrieve(String functionalModule,
                                         String tableName,
                                         Map<String, ?> importParams) throws JCoException {
        return retrieve(SapConnector.DEFAULT_CONNECTION, functionalModule, tableName, importParams);
    }

    /**
     * @param destination
     * @param functionalModule
     * @param tableName
     * @param importParams
     * @return
     * @throws JCoException
     */
    public List<Map<String, Object>> retrieve(String destination,
                                         String functionalModule,
                                         String tableName,
                                         Map<String, ?> importParams) throws JCoException {

        JCoBean jCoBean = new JCoBean(destination, functionalModule);

        jCoBean.pushImportParams(importParams);

        jCoBean.execute();

        List<Map<String, Object>> result = jCoBean.retrieveTableData(tableName);
        return result;
    }

    /**
     * @param functionalModule
     * @param importParams
     * @return
     * @throws JCoException
     */
    public List<Map<String, Object>> retrieveAll(String functionalModule,
                                         Map<String, ?> importParams) throws JCoException {
        return retrieveAll(SapConnector.DEFAULT_CONNECTION, functionalModule, importParams);
    }

    /**
     * @param destination
     * @param functionalModule
     * @param importParams
     * @return
     * @throws JCoException
     */
    public List<Map<String, Object>> retrieveAll(String destination,
                                         String functionalModule,
                                         Map<String, ?> importParams) throws JCoException {

        JCoBean jCoBean = new JCoBean(destination, functionalModule);

        jCoBean.pushImportParams(importParams);

        jCoBean.execute();

        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        Map<String, String> map = jCoBean.getTableParametersListOfFM();
        for (String tableName : map.keySet()) {
            List<Map<String, Object>> subResult = jCoBean.retrieveTableData(tableName);
            for (Map<String, Object> stringMap : subResult) {
                stringMap.put("TABLE_NAME", tableName);
            }
            result.addAll(subResult);
        }
        return result;
    }

    /**
     * @param functionalModule
     * @param tableName
     * @param importParams
     * @param tableDataToBeInserted
     * @return
     * @throws JCoException
     */
    public Map<String, ?> insert(String functionalModule,
                                 String tableName,
                                 Map<String, ?> importParams,
                                 List<String> exportFilterParams,
                                 List<Map<String, ?>> tableDataToBeInserted) throws JCoException {
        return insert(SapConnector.DEFAULT_CONNECTION, functionalModule, tableName, importParams, exportFilterParams, tableDataToBeInserted);
    }

    /**
     * @param destination
     * @param functionalModule
     * @param tableName
     * @param importParams
     * @param tableDataToBeInserted
     * @return
     * @throws JCoException
     */
    public Map<String, ?> insert(String destination, String functionalModule, String tableName,
                                 Map<String, ?> importParams,
                                 List<String> exportFilterParams,
                                 List<Map<String, ?>> tableDataToBeInserted) throws JCoException {

        if(tableDataToBeInserted == null){
            throw new IllegalArgumentException("argument tableDataToBeInserted must not be null!");
        }
        JCoBean jCoBean = new JCoBean(destination, functionalModule);

        jCoBean.pushImportParams(importParams);

        // execute?

        jCoBean.pushDataIntoTheTable(tableName, tableDataToBeInserted);

        jCoBean.execute();

        Map<String, Object> result = jCoBean.popExportParams(exportFilterParams);

        return result;
    }

    /**
     * @param destination
     * @param functionalModule
     * @return
     * @throws JCoException
     */
    public Map<String, String> getFunctionTableParametersList(String destination, String functionalModule) throws JCoException {
        JCoBean jCoBean = new JCoBean(destination, functionalModule);
        Map<String, String> result = jCoBean.getTableParametersListOfFM();
        return result;
    }

    public Map<String, String> getFunctionTableParametersList(String function) throws JCoException {
        return getFunctionTableParametersList(SapConnector.DEFAULT_CONNECTION, function);
    }

    /**
     * @param destination
     * @param functionalModule
     * @return
     * @throws JCoException
     */
    public Map<String, String> getFunctionImportParametersList(String destination, String functionalModule) throws JCoException {
        JCoBean jCoBean = new JCoBean(destination, functionalModule);
        Map<String, String> result = jCoBean.getImportParametersListOfFM();
        return result;
    }

    public Map<String, String> getFunctionImportParametersList(String function) throws JCoException {
        return getFunctionImportParametersList(SapConnector.DEFAULT_CONNECTION, function);
    }


    /**
     * @param destination
     * @param functionalModule
     * @return
     * @throws JCoException
     */
    public Map<String, String> getTableFieldsDescription(String destination, String functionalModule, String tableName) throws JCoException {
        JCoBean jCoBean = new JCoBean(destination, functionalModule);
        Map<String, String> result = jCoBean.getTableFieldsDescription(tableName);
        return result;
    }

    // todo
    public Map<String, String> getTableFieldsDescription(String function, String tableName) throws JCoException {
        return getTableFieldsDescription(SapConnector.DEFAULT_CONNECTION, function, tableName);
    }

}
