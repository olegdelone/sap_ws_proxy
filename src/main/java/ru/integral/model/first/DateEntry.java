package ru.integral.model.first;

import javax.xml.bind.annotation.XmlElement;
import java.util.Date;

/**
 * ========== ItCorp v. 1.0 class library ==========
 * <p/>
 * http://www.it.ru/
 * <p/>
 * &copy; Copyright 1990-2013, by ItCorp.
 * <p/>
 * ========== IntegerEntry.java ==========
 * <p/>
 * $Revision: 9 $<br/>
 * $Author: NullPointer $<br/>
 * $HeadURL: file:///D:/work/local_repository/SAP_PROXY/trunk/src/main/java/ru/integral/model/first/DateEntry.java $<br/>
 * $Id: DateEntry.java 9 2013-08-24 06:26:05Z NullPointer $
 * <p/>
 * 22.08.13 18:15: Original version (OOBUKHOV)<br/>
 */
public class DateEntry extends AbsEntry {
    @XmlElement(name = "value")
    protected Date value;

    public DateEntry() {
    }

    public DateEntry(Date value) {
        this.value = value;
    }

    public DateEntry(String name, Date value) {
        super.setName(name);
        this.value = value;
    }

    public Date getValue() {
        return value;
    }

    public void setValue(Date value) {
        this.value = value;
    }
}
