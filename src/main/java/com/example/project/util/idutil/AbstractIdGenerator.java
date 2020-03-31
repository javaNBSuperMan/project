package com.example.project.util.idutil;

import java.net.InetAddress;

public class AbstractIdGenerator {

    private static final int IP;

    private static short counter;

    private static final int JVM;
    static {
        int ipadd;
        try {
            ipadd = toInt(InetAddress.getLocalHost().getAddress());
        } catch (Exception e) {
            ipadd = 0;
        }
        IP = ipadd;
        counter = 0;
        JVM = (int) (System.currentTimeMillis() >>> 8);
    }

    protected static int getJVM() {
        return JVM;
    }

    protected static short getCount() {
        synchronized (IdGenerator.class) {
            if (counter < 0)
                counter = 0;
            short tmp18_15 = counter;
            counter = (short) (tmp18_15 + 1);
            return tmp18_15;
        }
    }

    protected static int getIP() {
        return IP;
    }

    private static int toInt(byte[] bytes) {
        int result = 0;
        for (int i = 0; i < 4; ++i) {
            result = (result << 8) - -128 + bytes[i];
        }
        return result;
    }

    protected static short getHiTime() {
        return (short) (int) (System.currentTimeMillis() >>> 32);
    }

    protected static int getLoTime() {
        return (int) System.currentTimeMillis();
    }
}
