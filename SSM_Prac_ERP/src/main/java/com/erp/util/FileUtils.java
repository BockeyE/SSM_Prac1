package com.erp.util;

/**
 * @author bockey
 */
public class FileUtils {

    public String getFileExt(String s){
        String s1 = new String();
        int i = 0;
        int j = 0;
        if(s == null)
            return null;
        i = s.lastIndexOf(46) + 1;
        j = s.length();
        s1 = s.substring(i, j);
        if(s.lastIndexOf(46) > 0)
            return s1.toLowerCase();
        else
            return "";
    }
}
