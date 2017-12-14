package com.eziosoft.wltoysrobot;

/**
 * Written by Bartosz Szczygiel <eziosoft@gmail.com>
 * Created on 14/12/2017.
 */
public class Protocol {

    static String IP = "192.168.0.1";
    static int Port = 40000;

    static byte[] startFrame = {0x63, 0x63, 0x01, 0x00, 0x00, 0x00, 0x00};
    static int[] controlFrame = {0x63, 0x63, 0x0a, 0x00, 0x00, 0x08, 0x00, 0x66, 0x80, 0x80, 0x80, 0x80, 0x00, 0x00, 0x99};




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
