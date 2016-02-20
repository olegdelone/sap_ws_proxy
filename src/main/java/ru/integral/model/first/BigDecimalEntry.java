package ru.integral.model.first;

import javax.xml.bind.annotation.XmlElement;
import java.math.BigDecimal;

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
 * $HeadURL: file:///D:/work/local_repository/SAP_PROXY/trunk/src/main/java/ru/integral/model/first/BigDecimalEntry.java $<br/>
 * $Id: BigDecimalEntry.java 9 2013-08-24 06:26:05Z NullPointer $
 * <p/>
 * 22.08.13 18:15: Original version (OOBUKHOV)<br/>
 */
public class BigDecimalEntry extends AbsEntry {
    @XmlElement(name = "value")
    protected BigDecimal value;

    public BigDecimalEntry() {
    }

    public BigDecimalEntry(BigDecimal value) {
        this.value = value;
    }
    public BigDecimalEntry(String name, BigDecimal value) {
        super.setName(name);
        this.value = value;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }
}
