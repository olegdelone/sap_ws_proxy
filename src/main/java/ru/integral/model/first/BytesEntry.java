package ru.integral.model.first;

import javax.xml.bind.annotation.XmlElement;

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
 * $HeadURL: file:///D:/work/local_repository/SAP_PROXY/trunk/src/main/java/ru/integral/model/first/BytesEntry.java $<br/>
 * $Id: BytesEntry.java 9 2013-08-24 06:26:05Z NullPointer $
 * <p/>
 * 22.08.13 18:15: Original version (OOBUKHOV)<br/>
 */
public class BytesEntry extends AbsEntry {
    @XmlElement(name = "value")
    protected byte[] value;

    public BytesEntry() {
    }

    public BytesEntry(byte[] value) {
        this.value = value;
    }
    public BytesEntry(String name, byte[] value) {
        super.setName(name);
        this.value = value;
    }

    public byte[] getValue() {
        return value;
    }

    public void setValue(byte[] value) {
        this.value = value;
    }
}
