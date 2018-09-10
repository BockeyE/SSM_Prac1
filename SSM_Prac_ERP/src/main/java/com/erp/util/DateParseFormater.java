package com.erp.util;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author bockey
 */
public class DateParseFormater implements Converter<String,Date> {
    @Override
    public Date convert(String s) {
        SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        try {
            Date d=sd.parse(s);
            return d;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
