package com.eziosoft.wltoysrobot;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * Written by Bartosz Szczygiel <eziosoft@gmail.com>
 * Created on 14/12/2017.
 */
public class RobotControl {
    private boolean running = true;
    private int FR = Protocol.MID, LR = Protocol.MID, mode = 0;

    private void StartSendingControlFrames() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                DatagramSocket s = null;
                try {
                    s = new DatagramSocket();

                    InetAddress address = InetAddress.getByName(Protocol.IP);

                    while (running) {
                        DatagramPacket p = new DatagramPacket(Protocol.getFrame(FR, LR, mode), Protocol.controlFrame.length, address, Protocol.Port);
                        s.send(p);
                        mode = 0;
                        Thread.sleep(50);

                    }
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
    }


    private void startSendingStartFrames() {
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {

                DatagramSocket s = null;
                try {
                    s = new DatagramSocket();

                    InetAddress address = InetAddress.getByName(Protocol.IP);
                    DatagramPacket p = new DatagramPacket(Protocol.startFrame, Protocol.startFrame.length, address, Protocol.Port);

                    while (running) {
                        s.send(p);
                        Thread.sleep(1000);
                    }
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
    }


    public synchronized void Start() {
        running = true;
        startSendingStartFrames();
        StartSendingControlFrames();
    }

    public synchronized void Stop() {
        running = false;
    }

    public synchronized void setFR(int FR) {
        this.FR = FR;
    }

    public synchronized void setLR(int LR) {
        this.LR = LR;
    }

    public synchronized void setMode(int mode) {
        this.mode = mode;
    }
}
