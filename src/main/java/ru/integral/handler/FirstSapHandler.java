package ru.integral.handler;

import com.sap.conn.jco.JCoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.integral.helper.ArgsConverter;
import ru.integral.manager.SapManager;
import ru.integral.model.first.AbsEntry;
import ru.integral.model.first.Row;

import java.util.List;
import java.util.Map;

/**
 * ========== ItCorp v. 1.0 class library ==========
 * <p/>
 * http://www.it.ru/
 * <p/>
 * &copy; Copyright 1990-2013, by ItCorp.
 * <p/>
 * ========== SapHandler.java ==========
 * <p/>
 * $Revision: 18 $<br/>
 * $Author: NullPointer $<br/>
 * $HeadURL: file:///D:/work/local_repository/SAP_PROXY/trunk/src/main/java/ru/integral/handler/FirstSapHandler.java $<br/>
 * $Id: FirstSapHandler.java 18 2013-08-30 02:28:55Z NullPointer $
 * <p/>
 * 23.08.13 7:15: Original version (OOBUKHOV)<br/>
 */
@Service
public class FirstSapHandler implements ISapHandler<AbsEntry, Row>{
    private static transient Logger log = LoggerFactory.getLogger(FirstSapHandler.class);

    @Autowired
    private SapManager sapManager;


    public List<Row> retrieve(String functionalModule, String tableName, List<AbsEntry> importParams) throws JCoException {
        Map<String, Object> iParams = ArgsConverter.convertListOfEntries2Map(importParams);
        log.debug("converted importParams: {}", iParams);
        List<Map<String, Object>> r = sapManager.retrieve(functionalModule, tableName, iParams);

        return ArgsConverter.convertListOfMaps2ListOfRows(r);
    }



    public List<AbsEntry> insert(String functionalModule, String tableName,
                                             List<AbsEntry> importParams,
                                             List<String> exportParamsFilter,
                                             List<Row> tableDataToBeInserted) throws JCoException {

        List<Map<String, ?>> data = ArgsConverter.convertListOfRows2ListOfMaps(tableDataToBeInserted);
        Map<String, Object> iParams = ArgsConverter.convertListOfEntries2Map(importParams);

        log.debug("converted importParams: {}", iParams);
        log.debug("converted tableDataToBeInserted: {}", data);

        Map<String, ?> r_ =  sapManager.insert(functionalModule, tableName, iParams, exportParamsFilter, data);
        return ArgsConverter.convertMap2ListOfAbsEntries(r_);
    }

    @Override
    public List<AbsEntry> getFunctionTableParametersList(String functionName) throws JCoException {
        Map<String, String> r_ = sapManager.getFunctionTableParametersList(functionName);
        return ArgsConverter.convertMap2ListOfAbsEntries(r_);
    }

    @Override
    public List<AbsEntry> getFunctionImportParametersList(String functionName) throws JCoException {
        Map<String, String> r_ = sapManager.getFunctionImportParametersList(functionName);
        return ArgsConverter.convertMap2ListOfAbsEntries(r_);
    }

    @Override
    public List<Row> retrieveAll(String functionalModule, List<AbsEntry> importParams) throws JCoException {
        Map<String, Object> iParams = ArgsConverter.convertListOfEntries2Map(importParams);
        log.debug("converted importParams: {}", iParams);
        List<Map<String, Object>> r = sapManager.retrieveAll(functionalModule, iParams);

        return ArgsConverter.convertListOfMaps2ListOfRows(r);
    }

    @Override
    public List<AbsEntry> getTableFieldsDescription(String functionalModule, String tableName) throws JCoException {
        Map<String, String> r_ = sapManager.getTableFieldsDescription(functionalModule, tableName);
        return ArgsConverter.convertMap2ListOfAbsEntries(r_);
    }
}
