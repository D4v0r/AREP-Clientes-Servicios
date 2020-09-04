package edu.escuelaing.arep.httpserver;

import java.io.IOException;

public class ServerRunner {
    //RETO1
    public static void main(String[] args) throws IOException {
      int port = getPort();
      WebServer server = new WebServer(port);
      server.start();
    }

    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 36000; //returns default port if heroku-port isn't set (i.e. on localhost)
    }

}
