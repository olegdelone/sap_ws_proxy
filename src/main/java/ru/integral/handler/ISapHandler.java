package ru.integral.handler;

import com.sap.conn.jco.JCoException;
import ru.integral.model.first.AbsEntry;
import ru.integral.model.first.Row;
import ru.integral.model.second.TypedEntry;

import java.util.List;

/**
 * ========== ItCorp v. 1.0 class library ==========
 * <p/>
 * http://www.it.ru/
 * <p/>
 * &copy; Copyright 1990-2013, by ItCorp.
 * <p/>
 * ========== SapHandleable.java ==========
 * <p/>
 * $Revision: 18 $<br/>
 * $Author: NullPointer $<br/>
 * $HeadURL: file:///D:/work/local_repository/SAP_PROXY/trunk/src/main/java/ru/integral/handler/ISapHandler.java $<br/>
 * $Id: ISapHandler.java 18 2013-08-30 02:28:55Z NullPointer $
 * <p/>
 * 24.08.13 8:52: Original version (OOBUKHOV)<br/>
 */
public interface ISapHandler<E, R> {
    public List<R> retrieve(String functionalModule, String tableName, List<E> importParams) throws JCoException;
    public List<E> insert(String functionalModule, String tableName,
                         List<E> importParams,
                         List<String> exportParamsFilter,
                         List<R> tableDataToBeInserted) throws JCoException;


    public List<E> getFunctionTableParametersList(String functionalModule) throws JCoException;
    public List<E> getFunctionImportParametersList(String functionalModule) throws JCoException;
    public List<R> retrieveAll(String functionalModule, List<E> importParams) throws JCoException;
    public List<E> getTableFieldsDescription(String functionalModule, String tableName) throws JCoException;
}
