package ru.integral.services.first;

import ru.integral.model.first.AbsEntry;
import ru.integral.model.first.Row;
import ru.integral.model.second.TypedEntry;
import ru.integral.services.fault.WebServiceException;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import java.util.List;

/**
 * ========== SapCrudService.java ==========
 * <p/>
 * $Revision: 18 $<br/>
 * $Author: NullPointer $<br/>
 * $HeadURL: file:///D:/work/local_repository/SAP_PROXY/trunk/src/main/java/ru/integral/services/first/SapCrudService.java $<br/>
 * $Id: SapCrudService.java 18 2013-08-30 02:28:55Z NullPointer $
 * <p/>
 * 20.08.13 14:50: Original version (OOBUKHOV)<br/>
 */
@WebService(targetNamespace = "http://first.services.integral.ru")
public interface SapCrudService {




    @WebMethod
    @WebResult(name = "r")
    List<Row> retrieve(@WebParam(name = "functionalModule") String functionalModule,
                       @WebParam(name = "tableName") String tableName,
                       @WebParam(name = "importParams") List<AbsEntry> importParams
    ) throws WebServiceException;

    @WebMethod
    @WebResult(name = "r")
    List<Row> retrieveAll(@WebParam(name = "functionalModule") String functionalModule,
                       @WebParam(name = "importParams") List<AbsEntry> importParams
    ) throws WebServiceException;

    @WebMethod
    @WebResult(name = "r")
    List<AbsEntry> insert(@WebParam(name = "functionalModule") String functionalModule,
               @WebParam(name = "tableName") String tableName,
               @WebParam(name = "importParams") List<AbsEntry> importParams,
               @WebParam(name = "exportParamsFilter") List<String> exportParamsFilter,
               @WebParam(name = "tableDataToBeInserted") List<Row> tableDataToBeInserted
    ) throws WebServiceException;

    @WebMethod
    @WebResult(name = "r")
    public List<AbsEntry> getFunctionTableParametersList(@WebParam(name = "functionName") String functionName) throws WebServiceException;

    @WebMethod
    @WebResult(name = "r")
    public List<AbsEntry> getFunctionImportTableParametersList(@WebParam(name = "functionName") String functionName) throws WebServiceException;

}
