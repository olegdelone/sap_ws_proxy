package ru.integral.utils;

import com.sap.conn.jco.ext.DataProviderException;
import com.sap.conn.jco.ext.DestinationDataEventListener;
import com.sap.conn.jco.ext.DestinationDataProvider;
import com.sap.conn.jco.ext.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * ========== SapProvider.java ==========
 * <p/>
 * $Revision: 7 $<br/>
 * $Author: NullPointer $<br/>
 * $HeadURL: file:///D:/work/local_repository/SAP_PROXY/trunk/src/main/java/ru/integral/utils/CustomDataProvider.java $<br/>
 * $Id: CustomDataProvider.java 7 2013-08-21 14:17:00Z NullPointer $
 * <p/>
 * 20.08.13 17:08: Original version (OOBUKHOV)<br/>
 */

public class CustomDataProvider implements DestinationDataProvider {
    private static transient Logger log = LoggerFactory.getLogger(DestinationDataProvider.class);

    private DestinationDataEventListener eL;


    private Map<String, Properties> secureDBStorage = new HashMap<String, Properties>();


    @Override
    public Properties getDestinationProperties(String destinationName) {
        log.debug("getDestinationProperties({})...", destinationName);
        try {
            //read the destination from DB
            Properties p = secureDBStorage.get(destinationName);

            if (p != null) {
                //check if all is correct, for example
                if (p.isEmpty())
                    throw new DataProviderException(DataProviderException.Reason.INVALID_CONFIGURATION, "destination configuration is incorrect", null);

                return p;
            }

            return null;
        } catch (RuntimeException re) {
            throw new DataProviderException(DataProviderException.Reason.INTERNAL_ERROR, re);
        }
    }

    //An implementation supporting events has to retain the eventListener instance provided
    //by the JCo runtime. This listener instance shall be used to notify the JCo runtime
    //about all changes in destination configurations.
    @Override
    public void setDestinationDataEventListener(DestinationDataEventListener eventListener) {
        log.debug("setDestinationDataEventListener({})...", eventListener);
        this.eL = eventListener;
    }

    @Override
    public boolean supportsEvents() {
        return true;
    }

    //implementation that saves the properties in a very secure way
    public void changeProperties(String destName, Properties properties) {
        log.info("changeProperties({}, {}) called", destName, properties);
        synchronized (secureDBStorage) {
            if (properties == null) {
                if (secureDBStorage.remove(destName) != null){
                    if(eL != null){
                        eL.deleted(destName);
                    }
                }
            } else {
                secureDBStorage.put(destName, properties);
                if(eL != null){
                    eL.updated(destName); // create or updated
                }
            }
        }
    }
}
