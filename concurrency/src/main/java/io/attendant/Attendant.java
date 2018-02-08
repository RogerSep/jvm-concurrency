package io.attendant;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.Executor;

public abstract class Attendant {

    private Executor executor;

    public Attendant(Executor executor) {
        this.executor = executor;
    }

    public final void attend(Socket client) {
        this.executor.execute(() -> {
            BufferedReader in = null;
            PrintWriter out = null;
            try {
                in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                out = new PrintWriter(client.getOutputStream(), true);
                String clientMessage;

                loop: while ((clientMessage = in.readLine()) != null) {
                    switch(clientMessage) {
                        case "ping":
                            out.write("pong\n");
                            out.flush();
                            break;

                        case "pong":
                            out.write("ping\n");
                            out.flush();
                            break;

                        default:
                            out.write("G'bye\n");
                            out.flush();
                            closeStuff(in, out, client);
                            break loop;
                    }
                }

            } catch (IOException ioe) {
                System.out.println("Failed to attend a client: " + ioe.getMessage());
                ioe.printStackTrace();

                closeStuff(in, out, client);
            }
        });
    }

    private void closeStuff(BufferedReader in, PrintWriter out, Socket client) {
        try {
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        out.close();

        try {
            client.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
