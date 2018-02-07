package io.net;

import io.attendant.Attendant;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private ServerSocket server;

    public Server(int port) throws IOException {
        this.server = new ServerSocket(port);
    }

    private void init(Attendant attendant) {
        while (true) {
            try {
                Socket client = this.server.accept();

                attendant.attend(client);
            } catch (IOException ioe) {
                System.out.println("Could not attend a request " + ioe.getMessage());
            }
        }
    }

}
