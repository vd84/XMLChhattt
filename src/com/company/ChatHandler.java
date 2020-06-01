package com.company;

import java.io.*;
import java.net.Socket;

public class ChatHandler {

    private final DataOutputStream dataOutputStream;
    private final BufferedReader socketReader;
    private XMLHandler xmlHandler;

    public ChatHandler() throws IOException {
        Socket socket = new Socket("atlas.dsv.su.se", 9494);
        OutputStream outputStream = socket.getOutputStream();
        this.dataOutputStream = new DataOutputStream(outputStream);
        this.socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.startRecieve();
    }


    public void sendMessage(String message) throws IOException {
        System.out.println("Sending message");
        dataOutputStream.writeUTF(message);
        dataOutputStream.flush();

    }

    private void startRecieve(){
        new Thread(() -> {
            try {
                while (true) {
                    if (socketReader.ready()) {
                        String line = socketReader.readLine();
                        System.out.println("HÄR ÄR SVAR");
                        System.out.println(line);
                        System.out.println(new XMLHandler().convertStringToXML(line));
                    }
                    Thread.sleep(100);
                }
            } catch (InterruptedException exception) {
                System.out.println("InterruptedException");
            } catch (Exception exception) {
                System.out.println(exception);
            }
        }).start();

    }


}
