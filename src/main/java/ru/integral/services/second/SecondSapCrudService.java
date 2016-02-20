package ru.integral.services.second;

import ru.integral.model.first.AbsEntry;
import ru.integral.model.first.Row;
import ru.integral.model.second.TypedEntry;
import ru.integral.model.second.TypedRow;
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
 * $HeadURL: file:///D:/work/local_repository/SAP_PROXY/trunk/src/main/java/ru/integral/services/second/SecondSapCrudService.java $<br/>
 * $Id: SecondSapCrudService.java 18 2013-08-30 02:28:55Z NullPointer $
 * <p/>
 * 20.08.13 14:50: Original version (OOBUKHOV)<br/>
 */
@WebService(targetNamespace = "http://second.services.integral.ru")
public interface SecondSapCrudService {

    @WebMethod
    @WebResult(name = "r")
    List<TypedEntry> getTableFieldsDescription(@WebParam(name = "functionalModule") String functionalModule,
                                        @WebParam(name = "tableName") String tableName
    ) throws WebServiceException;

    @WebMethod
    @WebResult(name = "r")
    List<TypedEntry> getFunctionTableParametersList(@WebParam(name = "functionName") String functionName) throws WebServiceException;

    @WebMethod
    @WebResult(name = "r")
    List<TypedRow> retrieve(@WebParam(name = "functionalModule") String functionalModule,
                       @WebParam(name = "tableName") String tableName,
                       @WebParam(name = "importParams") List<TypedEntry> importParams
    ) throws WebServiceException;

    @WebMethod
    @WebResult(name = "r")
    List<TypedEntry> insert(@WebParam(name = "functionalModule") String functionalModule,
                  @WebParam(name = "tableName") String tableName,
                  @WebParam(name = "importParams") List<TypedEntry> importParams,
                  @WebParam(name = "exportParamsFilter") List<String> exportParamsFilter,
                  @WebParam(name = "tableDataToBeInserted") List<TypedRow> tableDataToBeInserted
    ) throws WebServiceException;

    @WebMethod
    @WebResult(name = "r")
    public List<TypedEntry> getFunctionImportTableParametersList(@WebParam(name = "functionName") String functionName) throws WebServiceException;

    @WebMethod
    @WebResult(name = "r")
    List<TypedRow> retrieveAll(@WebParam(name = "functionalModule") String functionalModule,
                          @WebParam(name = "importParams") List<TypedEntry> importParams
    ) throws WebServiceException;

    @WebMethod
    @WebResult(name = "r")
    boolean test(@WebParam(name = "testParam") String param) throws WebServiceException;
}
