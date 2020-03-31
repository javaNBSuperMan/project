package com.example.project.util.idutil;

public class IdGenerator extends AbstractIdGenerator {

    private static String sep = "";

    protected static String format(int intval) {
        String formatted = Integer.toHexString(intval);
        StringBuffer buf = new StringBuffer("00000000");
        buf.replace(8 - formatted.length(), 8, formatted);
        return buf.toString();
    }

    protected static String format(short shortval) {
        String formatted = Integer.toHexString(shortval);
        StringBuffer buf = new StringBuffer("0000");
        buf.replace(4 - formatted.length(), 4, formatted);
        return buf.toString();
    }

    public static String generate() {
        return format(getIP()) + sep + format(getJVM()) + sep + format(getHiTime()) + sep + format(getLoTime())
                + sep + format(getCount());
    }
}
