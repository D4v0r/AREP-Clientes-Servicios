package edu.escuelaing.arep.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class Client {

    static int[] numbers = {1, 2, 3, 45, 78, 5, 6, 12, 7, 10, 11, 12, 13, 14 ,15 ,16 ,17 ,18, 19 , 20};

    public static void main(String[] args) throws IOException, InterruptedException {
        Socket socket = null;
        PrintWriter out = null;
        BufferedReader in = null;
        try {
            socket = new Socket("127.0.0.1", 35000);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Don’t know about host!.");
            System.exit(1);
            } catch (IOException e) {
            System.err.println("Couldn’t get I/O for "
                    + "the connection to: localhost.");
            System.exit(1);
            }
        BufferedReader stdIn = new BufferedReader(
                new InputStreamReader(System.in));
        String userInput;
        /*
        while ((userInput = stdIn.readLine()) != null) {
            out.println(userInput);
            System.out.println(in.readLine());
        }

         */

        for (int number: numbers
             ) {
            out.println(number);
            String square = in.readLine();
            System.out.println("Square of  " + number + " is : " + square);
            Thread.sleep(500);

        }
        out.close();
        in.close();
        stdIn.close();
        socket.close();
    }
}

