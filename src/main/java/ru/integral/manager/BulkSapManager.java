package ru.integral.manager;

import com.sap.conn.jco.JCoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.integral.utils.SapConnector;

import java.util.List;
import java.util.Map;

/**
 * ========== ItCorp v. 1.0 class library ==========
 * <p/>
 * http://www.it.ru/
 * <p/>
 * &copy; Copyright 1990-2013, by ItCorp.
 * <p/>
 * ========== BulkSapManager.java ==========
 * <p/>
 * $Revision: 14 $<br/>
 * $Author: NullPointer $<br/>
 * $HeadURL: file:///D:/work/local_repository/SAP_PROXY/trunk/src/main/java/ru/integral/manager/BulkSapManager.java $<br/>
 * $Id: BulkSapManager.java 14 2013-08-25 08:10:19Z NullPointer $
 * <p/>
 * 25.08.13 12:06: Original version (OOBUKHOV)<br/>
 */
@Component
@Deprecated
public class BulkSapManager {
    private static transient Logger log = LoggerFactory.getLogger(BulkSapManager.class);

    @Autowired
    SapManager sapManager;

    public List<Map<String, ?>> bulkInsert() throws JCoException {
        log.error("under development");
        return null;
    }

    public List<Map<String, ?>> bulkRetrieve() throws JCoException {
        log.error("under development");
        return null;
    }

}
