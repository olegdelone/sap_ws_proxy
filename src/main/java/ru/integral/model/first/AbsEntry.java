package ru.integral.model.first;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.math.BigDecimal;
import java.util.Date;

/**
 * ========== ItCorp v. 1.0 class library ==========
 * <p/>
 * http://www.it.ru/
 * <p/>
 * &copy; Copyright 1990-2013, by ItCorp.
 * <p/>
 * ========== AbsEntry.java ==========
 * <p/>
 * $Revision: 16 $<br/>
 * $Author: NullPointer $<br/>
 * $HeadURL: file:///D:/work/local_repository/SAP_PROXY/trunk/src/main/java/ru/integral/model/first/AbsEntry.java $<br/>
 * $Id: AbsEntry.java 16 2013-08-25 08:43:01Z NullPointer $
 * <p/>
 * 22.08.13 18:10: Original version (OOBUKHOV)<br/>
 */
@XmlSeeAlso({BigDecimalEntry.class,
        BytesEntry.class,
        DateEntry.class,
        DoubleEntry.class,
        IntegerEntry.class,
        StringEntry.class
})
@XmlRootElement(name="entry")
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class AbsEntry {
    @XmlElement(name = "name")
    protected String name;


    public static AbsEntry createInstance(String name, Object inputValue) {
        AbsEntry absEntry;
        if(inputValue instanceof Integer){
            absEntry = new IntegerEntry(name, (Integer)inputValue);
        } else if(inputValue instanceof Date){
            absEntry = new DateEntry(name, (Date)inputValue);
        } else if(inputValue instanceof BigDecimal){
            absEntry = new BigDecimalEntry(name, (BigDecimal)inputValue);
        } else if(inputValue instanceof Double){
            absEntry = new DoubleEntry(name, (Double)inputValue);
        } else if(inputValue instanceof byte[]){
            absEntry = new BytesEntry(name, (byte[])inputValue);
        } else {
            absEntry = new StringEntry(name, String.valueOf(inputValue));
        }
        return absEntry;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract Object getValue();


    @Override
    public String toString() {
        return "AbsEntry{" +
                "name='" + name + "'," +
                "value='" + getValue() + '\'' +
                '}';
    }
}
