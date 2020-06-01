package com.company;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketAddress;

public class Client implements Runnable {

    private final static String HOST = "atlas.dsv.su.se";
    private final static int PORT = 9494;

    private final BufferedReader socketReader;

    public Client(BufferedReader socketReader) {
        this.socketReader = socketReader;
    }

    // Receive messages.
    public void run() {
        try {
            String threadInfo = " (" + Thread.currentThread().getName() + ").";
            while (true) {
                if (socketReader.ready()) {
                    String line = socketReader.readLine();
                    System.out.println(line);
                    //System.out.println("Received: \"" + line + "\"" + threadInfo);
                }
                Thread.sleep(100);
            }
        } catch (InterruptedException exception) {
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }







}
