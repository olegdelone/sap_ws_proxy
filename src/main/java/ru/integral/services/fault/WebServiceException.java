package ru.integral.services.fault;

import javax.xml.ws.WebFault;

/**
 * ========== WebServiceException.java ==========
 * <p/>
 * $Revision: 7 $<br/>
 * $Author: NullPointer $<br/>
 * $HeadURL: file:///D:/work/local_repository/SAP_PROXY/trunk/src/main/java/ru/integral/services/fault/WebServiceException.java $<br/>
 * $Id: WebServiceException.java 7 2013-08-21 14:17:00Z NullPointer $
 * <p/>
 * 21.08.13 7:12: Original version (OOBUKHOV)<br/>
 */
@WebFault(
        name = "WebServiceException",
        targetNamespace = "http://fault.services.integral.ru"
)
public class WebServiceException extends Exception {

    public WebServiceException(String message) {
        super(message);
    }

    public WebServiceException(String message, Throwable cause) {
        super(message, cause);
    }

}