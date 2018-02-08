package concurrency;

import io.attendant.SameThreadAttendant;
import io.net.Server;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Server server = new Server(9000);

        server.init(new SameThreadAttendant());
    }

}