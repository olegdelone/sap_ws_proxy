package ru.integral.handler;

import com.sap.conn.jco.JCoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.integral.helper.ArgsConverter;
import ru.integral.manager.SapManager;
import ru.integral.model.second.TypedEntry;
import ru.integral.model.second.TypedRow;

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
 * $HeadURL: file:///D:/work/local_repository/SAP_PROXY/trunk/src/main/java/ru/integral/handler/SecondSapHandler.java $<br/>
 * $Id: SecondSapHandler.java 18 2013-08-30 02:28:55Z NullPointer $
 * <p/>
 * 23.08.13 7:15: Original version (OOBUKHOV)<br/>
 */
@Service
public class SecondSapHandler implements ISapHandler<TypedEntry, TypedRow> {
    private static transient Logger log = LoggerFactory.getLogger(SecondSapHandler.class);

    @Autowired
    private SapManager sapManager;


    public List<TypedRow> retrieve(String functionalModule, String tableName, List<TypedEntry> importParams) throws JCoException {

        Map<String, ?> iParams = ArgsConverter.convertListOfTypedEntries2Map(importParams);
        log.info("converted importParams: {}", iParams);

        List<Map<String, Object>> r = sapManager.retrieve(functionalModule, tableName, iParams);

        return ArgsConverter.convertListOfMaps2ListOfTypedRows(r);
    }


    public List<TypedEntry> insert(String functionalModule, String tableName,
                                             List<TypedEntry> importParams,
                                             List<String> exportParamsFilter,
                                             List<TypedRow> tableDataToBeInserted) throws JCoException {
        List<Map<String, ?>> data = ArgsConverter.convertListOfTypedRows2ListOfMaps(tableDataToBeInserted);
        log.info("converted tableDataToBeInserted: {}", data);

        Map<String, ?> iParams = ArgsConverter.convertListOfTypedEntries2Map(importParams);
        log.info("converted importParams: {}", iParams);

        Map<String, ?> r_ = sapManager.insert(functionalModule, tableName, iParams, exportParamsFilter, data);
        return ArgsConverter.convertMap2ListOfTypedEntries(r_);
    }

    @Override
    public List<TypedEntry> getFunctionTableParametersList(String functionName) throws JCoException {
        Map<String, String> r_ = sapManager.getFunctionTableParametersList(functionName);
        return ArgsConverter.convertMap2ListOfTypedEntries(r_);
    }


    @Override
    public List<TypedEntry> getFunctionImportParametersList(String functionName) throws JCoException {
        Map<String, String> r_ = sapManager.getFunctionImportParametersList(functionName);
        return ArgsConverter.convertMap2ListOfTypedEntries(r_);
    }

    public List<TypedRow> retrieveAll(String functionalModule, List<TypedEntry> importParams) throws JCoException{
        Map<String, ?> iParams = ArgsConverter.convertListOfTypedEntries2Map(importParams);
        log.info("converted importParams: {}", iParams);

        List<Map<String, Object>> r = sapManager.retrieveAll(functionalModule, iParams);

        return ArgsConverter.convertListOfMaps2ListOfTypedRows(r);
    }

    @Override
    public List<TypedEntry> getTableFieldsDescription(String functionalModule, String tableName) throws JCoException {
        Map<String, String> r_ = sapManager.getTableFieldsDescription(functionalModule, tableName);
        return ArgsConverter.convertMap2ListOfTypedEntries(r_);
    }
}
