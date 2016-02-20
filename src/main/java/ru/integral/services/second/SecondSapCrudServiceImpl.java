package ru.integral.services.second;

import com.sap.conn.jco.JCoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.integral.handler.ISapHandler;
import ru.integral.model.second.TypedEntry;
import ru.integral.model.second.TypedRow;
import ru.integral.services.fault.WebServiceException;
import ru.integral.utils.Timer;

import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

/**
 * ========== SapCrudServiceImpl.java ==========
 * <p/>
 * $Revision: 19 $<br/>
 * $Author: NullPointer $<br/>
 * $HeadURL: file:///D:/work/local_repository/SAP_PROXY/trunk/src/main/java/ru/integral/services/second/SecondSapCrudServiceImpl.java $<br/>
 * $Id: SecondSapCrudServiceImpl.java 19 2013-08-30 02:34:47Z NullPointer $
 * <p/>
 * 20.08.13 14:53: Original version (OOBUKHOV)<br/>
 */
@WebService(
        serviceName = "SecondSapCrudServiceImpl",
        targetNamespace = "http://second.services.integral.ru",
        endpointInterface = "ru.integral.services.second.SecondSapCrudService"
)
public class SecondSapCrudServiceImpl implements SecondSapCrudService {

    private static transient Logger log = LoggerFactory.getLogger(SecondSapCrudServiceImpl.class);

    @Autowired
    private ISapHandler<TypedEntry, TypedRow> secondSapHandler;

    @Override
    public List<TypedRow> retrieveAll(String functionalModule, List<TypedEntry> importParams) throws WebServiceException {
        log.info("retrieveAll method called...");
        Timer timer = Timer.registerStart();
        log.debug("functionalModule: {}", functionalModule);
        log.debug("importParams: {}", importParams);

        List<TypedRow> result;
        try {
            result = secondSapHandler.retrieveAll(functionalModule, importParams);
        } catch (JCoException e) {
            log.error("JCoException has been caught: ", e);
            throw new WebServiceException(e.getMessage(), e);
        } catch (Throwable e) {
            log.error("Caught Throwable: ", e);
            throw new WebServiceException(e.getMessage(), e);
        } finally {
            log.info("retrieveAll method finished in {} seconds.", timer.registerStop());
        }
        return result;
    }

    @Override
    public List<TypedEntry> getFunctionTableParametersList(String functionName) throws WebServiceException {
        List<TypedEntry> r;
        log.info("getFunctionsParametersList method called...");
        Timer timer = Timer.registerStart();
        log.debug("functionName: {}", functionName);
        try {
            r = secondSapHandler.getFunctionTableParametersList(functionName);
        } catch (JCoException e) {
            log.error("Caught JCoException: ", e);
            throw new WebServiceException(e.getMessage(), e);
        } catch (Throwable e) {
            log.error("Caught Throwable: ", e);
            throw new WebServiceException(e.getMessage(), e);
        } finally {
            log.info("getFunctionTableParametersList method finished in {} seconds.", timer.registerStop());
        }
        return r;
    }


    @Override
    public List<TypedRow> retrieve(String functionalModule, String tableName, List<TypedEntry> importParams) throws WebServiceException {
        log.info("retrieve method called...");
        Timer timer = Timer.registerStart();
        log.debug("functionalModule: {}", functionalModule);
        log.debug("tableName: {}", tableName);
        log.debug("importParams: {}", importParams);

        List<TypedRow> result;
        try {
            result = secondSapHandler.retrieve(functionalModule, tableName, importParams);
        } catch (JCoException e) {
            log.error("JCoException has been caught: ", e);
            throw new WebServiceException(e.getMessage(), e);
        } catch (Throwable e) {
            log.error("Caught Throwable: ", e);
            throw new WebServiceException(e.getMessage(), e);
        } finally {
            log.info("retrieve method finished in {} seconds.", timer.registerStop());
        }
        return result;
    }

    @Override
    public List<TypedEntry> insert(String functionalModule,
                                                   String tableName,
                                                   List<TypedEntry> importParams,
                                                   List<String> exportParamsFilter,
                                                   List<TypedRow> tableDataToBeInserted) throws WebServiceException {
        log.info("insert method called...");
        Timer timer = Timer.registerStart();
        log.debug("functionalModule: {}", functionalModule);
        log.debug("tableName: {}", tableName);
        log.debug("importParams: {}", importParams);
        log.debug("tableDataToBeInserted: {}", tableDataToBeInserted);
        List<TypedEntry> result;
        try {
            result = secondSapHandler.insert(functionalModule, tableName, importParams,exportParamsFilter, tableDataToBeInserted);
        } catch (JCoException e) {
            log.error("JCoException has been caught: ", e);
            throw new WebServiceException(e.getMessage(), e);
        } catch (Throwable e) {
            log.error("Caught Throwable: ", e);
            throw new WebServiceException(e.getMessage(), e);
        } finally {
            log.info("insert method finished in {} seconds.", timer.registerStop());
        }
        return result;
    }

    @Override
    public List<TypedEntry> getFunctionImportTableParametersList(String functionName) throws WebServiceException {
        List<TypedEntry> r;
        log.info("getFunctionImportTableParametersList method called...");
        Timer timer = Timer.registerStart();
        log.debug("functionName: {}", functionName);
        try {
            r = secondSapHandler.getFunctionImportParametersList(functionName);
        } catch (JCoException e) {
            log.error("Caught JCoException: ", e);
            throw new WebServiceException(e.getMessage(), e);
        } catch (Throwable e) {
            log.error("Caught Throwable: ", e);
            throw new WebServiceException(e.getMessage(), e);
        } finally {
            log.info("getFunctionImportTableParametersList method finished in {} seconds.", timer.registerStop());
        }
        return r;
    }

    @Override
    public List<TypedEntry> getTableFieldsDescription(String functionalModule, String tableName) throws WebServiceException {
        List<TypedEntry> r;
        log.info("getTableFieldsDescription method called...");
        Timer timer = Timer.registerStart();
        log.debug("functionalModule: {}", functionalModule);
        log.debug("tableName: {}", tableName);
        try {
            r = secondSapHandler.getTableFieldsDescription(functionalModule, tableName);
        } catch (JCoException e) {
            log.error("Caught JCoException: ", e);
            throw new WebServiceException(e.getMessage(), e);
        } catch (Throwable e) {
            log.error("Caught Throwable: ", e);
            throw new WebServiceException(e.getMessage(), e);
        } finally {
            log.info("getTableFieldsDescription method finished in {} seconds.", timer.registerStop());
        }
        return r;
    }

    @Override
    public boolean test(String param) throws WebServiceException {
        log.info("test method called...");
        log.info("param: ", param);

        return true;
    }
}
