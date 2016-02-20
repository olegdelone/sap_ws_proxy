package ru.integral.model.second;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * ========== Cell.java ==========
 * <p/>
 * $Revision: 17 $<br/>
 * $Author: NullPointer $<br/>
 * $HeadURL: file:///D:/work/local_repository/SAP_PROXY/trunk/src/main/java/ru/integral/model/second/TypedEntry.java $<br/>
 * $Id: TypedEntry.java 17 2013-08-25 08:50:59Z NullPointer $
 * <p/>
 * 22.08.13 18:03: Original version (OOBUKHOV)<br/>
 */
public class TypedEntry {
    public enum ContentType {
        BYTES, STRING, INTEGER, DOUBLE, BIG_DECIMAL, DATE
    }

    private ContentType contentType;
    private String name;
    private String content;
    private DateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss"); // todo

    public TypedEntry(ContentType contentType, String name, String content) {
        this.contentType = contentType;
        this.name = name;
        this.content = content;
    }

    public TypedEntry(String name, Object inputValue) {
        TypedEntry.ContentType contentType;
        String content;
        if (inputValue instanceof Integer) {
            content = inputValue.toString();
            contentType = TypedEntry.ContentType.INTEGER;
        } else if (inputValue instanceof Date) {
            content = df.format((Date)inputValue);
            contentType = TypedEntry.ContentType.DATE;
        } else if (inputValue instanceof BigDecimal) {
            content = inputValue.toString();
            contentType = TypedEntry.ContentType.BIG_DECIMAL;
        } else if (inputValue instanceof Double) {
            content = inputValue.toString();
            contentType = TypedEntry.ContentType.DOUBLE;
        } else if (inputValue instanceof byte[]) {
            content = new String((byte[]) inputValue);
            contentType = TypedEntry.ContentType.BYTES;
        } else {
            content = inputValue.toString();
            contentType = TypedEntry.ContentType.STRING;
        }
        setContent(content);
        setContentType(contentType);
        setName(name);
    }

    public TypedEntry() {
    }

    public ContentType getContentType() {
        return contentType;
    }

    public void setContentType(ContentType contentType) {
        this.contentType = contentType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public Object getValue() {
        if (contentType == ContentType.BIG_DECIMAL) {
            return getBigDecimal();
        } else if (contentType == ContentType.BYTES) {
            return getBytes();
        } else if (contentType == ContentType.DATE) {
            return getDate();
        } else if (contentType == ContentType.DOUBLE) {
            return getDouble();
        } else if (contentType == ContentType.INTEGER) {
            return getInteger();
        } else if (contentType == ContentType.STRING) {
            return getString();
        }
        throw new RuntimeException("contentType: " + String.valueOf(contentType) + " is unsuitable");
    }

    public byte[] getBytes() {
        byte[] r = null;
        String c = getContent();
        if (c == null) {
            return null;
        }
        try {
            r = c.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        return r;
    }

    public String getString() {
        return getContent();
    }

    public Integer getInteger() {
        Integer r;
        String c = getContent();
        if (c == null) {
            return null;
        }
        try {
            r = Integer.valueOf(c);
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }
        return r;
    }

    public Double getDouble() {
        Double r;
        String c = getContent();
        if (c == null) {
            return null;
        }
        try {
            r = Double.valueOf(c);
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }
        return r;
    }

    public BigDecimal getBigDecimal() {
        BigDecimal r = null;
        String c = getContent();
        if (c == null) {
            return null;
        }
        try {
            r = BigDecimal.valueOf(Double.valueOf(c));
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }
        return r;
    }

    public Date getDate() {
        Date r = null;
        String c = getContent();
        if (c == null) {
            return null;
        }
        try {
            r = df.parse(c);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return r;
    }

    @Override
    public String toString() {
        return "TypedEntry{" +
                "contentType='" + contentType + '\'' +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' +
                ", value='" + getValue() + '\'' +
                '}';
    }
}
