package com.company;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public Server(int port) {

        try {
            ServerSocket sv = new ServerSocket(port);

            while (true) {

                Socket socket = sv.accept();

                DataInputStream inputStream = new DataInputStream(socket.getInputStream());

                String line = inputStream.readUTF();

                File file = new File("src/records.txt");



                FileWriter writer = new FileWriter(file, true);

                writer.write(line);


            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
