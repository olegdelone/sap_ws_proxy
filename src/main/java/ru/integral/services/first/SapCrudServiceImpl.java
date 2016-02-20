package ru.integral.services.first;

import com.sap.conn.jco.JCoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.integral.handler.ISapHandler;
import ru.integral.model.first.AbsEntry;
import ru.integral.model.first.Row;
import ru.integral.services.fault.WebServiceException;

import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

/**
 * ========== SapCrudServiceImpl.java ==========
 * <p/>
 * $Revision: 18 $<br/>
 * $Author: NullPointer $<br/>
 * $HeadURL: file:///D:/work/local_repository/SAP_PROXY/trunk/src/main/java/ru/integral/services/first/SapCrudServiceImpl.java $<br/>
 * $Id: SapCrudServiceImpl.java 18 2013-08-30 02:28:55Z NullPointer $
 * <p/>
 * 20.08.13 14:53: Original version (OOBUKHOV)<br/>
 */
@WebService(
        serviceName = "SapCrudServiceImpl",
        targetNamespace = "http://first.services.integral.ru",
        endpointInterface = "ru.integral.services.first.SapCrudService"
)
@Deprecated
public class SapCrudServiceImpl implements SapCrudService {

    private static transient Logger log = LoggerFactory.getLogger(SapCrudServiceImpl.class);

    @Autowired
    private ISapHandler<AbsEntry, Row> firstSapHandler;


    @Override
    public List<Row> retrieve(String functionalModule,
                              String tableName,
                              List<AbsEntry> importParams) throws WebServiceException {
        log.info("retrieve method called...");
        log.debug("functionalModule: {}", functionalModule);
        log.debug("tableName: {}", tableName);
        log.debug("importParams: {}", importParams);

        List<Row> result = null;
        try {
            result = firstSapHandler.retrieve(functionalModule, tableName, importParams);
        } catch (JCoException e) {
            log.error("JCoException has been caught: ", e);
            throw new WebServiceException(e.getMessage(), e);
        } catch (Throwable e) {
            log.error("Caught Throwable: ", e);
            throw new WebServiceException(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public List<Row> retrieveAll(String functionalModule, List<AbsEntry> importParams) throws WebServiceException {
        log.info("retrieve method called...");
        log.debug("functionalModule: {}", functionalModule);
        log.debug("importParams: {}", importParams);

        List<Row> result = null;
        try {
            result = firstSapHandler.retrieveAll(functionalModule, importParams);
        } catch (JCoException e) {
            log.error("JCoException has been caught: ", e);
            throw new WebServiceException(e.getMessage(), e);
        } catch (Throwable e) {
            log.error("Caught Throwable: ", e);
            throw new WebServiceException(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public List<AbsEntry> insert(String functionalModule,
                                 String tableName,
                                 List<AbsEntry> importParams,
                                 List<String> exportParamsFilter,
                                 List<Row> tableDataToBeInserted) throws WebServiceException {
        log.info("insert method called...");
        log.debug("functionalModule: {}", functionalModule);
        log.debug("tableName: {}", tableName);
        log.debug("importParams: {}", importParams);
        log.debug("tableDataToBeInserted: {}", tableDataToBeInserted);
        List<AbsEntry> result = null;
        try {
            result = firstSapHandler.insert(functionalModule, tableName, importParams, exportParamsFilter, tableDataToBeInserted);
        } catch (JCoException e) {
            log.error("JCoException has been caught: ", e);
            throw new WebServiceException(e.getMessage(), e);
        } catch (Throwable e) {
            log.error("Caught Throwable: ", e);
            throw new WebServiceException(e.getMessage(), e);
        }
        return result;
    }


    @Override
    public List<AbsEntry> getFunctionTableParametersList(String functionName) throws WebServiceException {
        List<AbsEntry> r;
        log.info("getFunctionsParametersList method called...");
        log.debug("functionName: {}", functionName);
        try {
            r = firstSapHandler.getFunctionTableParametersList(functionName);
        } catch (JCoException e) {
            log.error("Caught JCoException: ", e);
            throw new WebServiceException(e.getMessage(), e);
        } catch (Throwable e) {
            log.error("Caught Throwable: ", e);
            throw new WebServiceException(e.getMessage(), e);
        }
        return r;
    }

    @Override
    public List<AbsEntry> getFunctionImportTableParametersList(String functionName) throws WebServiceException {
        List<AbsEntry> r;
        log.info("getFunctionImportTableParametersList method called...");
        log.debug("functionName: {}", functionName);
        try {
            r = firstSapHandler.getFunctionImportParametersList(functionName);
        } catch (JCoException e) {
            log.error("Caught JCoException: ", e);
            throw new WebServiceException(e.getMessage(), e);
        } catch (Throwable e) {
            log.error("Caught Throwable: ", e);
            throw new WebServiceException(e.getMessage(), e);
        }
        return r;
    }

}
