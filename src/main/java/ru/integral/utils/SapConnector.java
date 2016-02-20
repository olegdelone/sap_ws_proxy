package ru.integral.utils;

import com.sap.conn.jco.ext.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * ========== SapConnector.java ==========
 * <p/>
 * $Revision: 7 $<br/>
 * $Author: NullPointer $<br/>
 * $HeadURL: file:///D:/work/local_repository/SAP_PROXY/trunk/src/main/java/ru/integral/utils/SapConnector.java $<br/>
 * $Id: SapConnector.java 7 2013-08-21 14:17:00Z NullPointer $
 * <p/>
 * 20.08.13 16:16: Original version (OOBUKHOV)<br/>
 */
@Component
public class SapConnector {
    private static transient Logger log = LoggerFactory.getLogger(SapConnector.class);


    @Resource(name = "connectionConfigMap")
    private Map<String, Map<String, String>> connectionConfigMap = new HashMap<String, Map<String, String>>();

    public final static String DEFAULT_CONNECTION = "DEFAULT_CONNECTION";

    private CustomDataProvider sapProvider;

    public void init(){
        log.debug("init called...");
        obtainConnections();
    }

    synchronized public void obtainConnections(){
        log.info("obtainConnections called...");
        if(sapProvider == null){
            sapProvider = new CustomDataProvider();
            log.info("init new sapProvider....");
            log.info("java.library.path: {}", System.getProperty("java.library.path"));
            // todo: is not so good, but happens only once
            for (Map.Entry<String, Map<String, String>> entry : connectionConfigMap.entrySet()) {
                Map<String, String> subMap = entry.getValue();
                if(subMap != null){
                    Properties properties = new Properties();
                    for (Map.Entry<String, String> subEntry : subMap.entrySet()) {
                        properties.setProperty(subEntry.getKey(), subEntry.getValue());
                    }
                    sapProvider.changeProperties(entry.getKey(), properties);
                }
            }
            log.info("registering provider...");
            Environment.registerDestinationDataProvider(sapProvider);
        }
    }
}
