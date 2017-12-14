package com.eziosoft.wltoysrobot;

/**
 * Written by Bartosz Szczygiel <eziosoft@gmail.com>
 * Created on 14/12/2017.
 */
public class Protocol {
    public static String IP = "192.168.0.1";
    public static int Port = 40000;

    public static byte[] startFrame = {0x63, 0x63, 0x01, 0x00, 0x00, 0x00, 0x00};
    public static int[] controlFrame = {0x63, 0x63, 0x0a, 0x00, 0x00, 0x08, 0x00, 0x66, 0x80, 0x80, 0x80, 0x80, 0x00, 0x00, 0x99};

    public static int MODE_RED = 1;
    public static int MODE_PINK = 2;
    public static int MODE_GREEN = 4;
    public static int MODE_BLUE = 8;

    public static int MIN = 0x01;
    public static int MID = 0x80;
    public static int MAX = 0xFF;


    public static byte[] getFrame(int FR, int LR, int mode) {
        controlFrame[9] = FR;
        controlFrame[11] = LR;
        controlFrame[12] = mode;
        controlFrame[13] = getCRC(controlFrame);

        byte[] frame = new byte[15];
        for (int i = 0; i < controlFrame.length; i++) {
            frame[i] = (byte) (controlFrame[i] & 0xFF);
        }
        return frame;
    }


    private static int getCRC(int[] controlFrame) {
        int crc = 0;
        for (int i = 8; i < 13; i++) {
            crc = (crc ^ controlFrame[i]);
        }
        return crc;
    }
}
