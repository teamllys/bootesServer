package com.llys.bootes.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.InetAddress;
import java.util.Random;

public class CommonUtils {
    public static String genUniqueStr() {
        Random rand = new Random();
        return System.currentTimeMillis() + "." + rand.nextInt(10000);
    }
    public static String exception2Str(Exception e) {
        StringWriter out = new StringWriter();
        e.printStackTrace(new PrintWriter(out));
        return out.toString();
     } 
    public static String getHostName() throws Exception {
        InetAddress addr = InetAddress.getLocalHost();
        String hostname = addr.getHostName();
        return hostname;
    }
}
