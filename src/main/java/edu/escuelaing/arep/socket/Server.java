package edu.escuelaing.arep.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket  socket = null;
        try{
            socket = new ServerSocket(35000);
        } catch (IOException e){
            e.printStackTrace();
        }

        Socket clientSocket = null;
        try{
            clientSocket = socket.accept();
        } catch (IOException e) {
            e.printStackTrace();
        }

        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                clientSocket.getInputStream()));

        String inputLine, outputLine;
        while ((inputLine = in.readLine()) != null) {
            System.out.println("Number: " + inputLine);
            outputLine = String.valueOf(Math.pow(Double.parseDouble(inputLine), 2));
            out.println(outputLine);
            if (outputLine.equals("0.0"))
                break;
            }
        out.close();
        in.close();
        clientSocket.close();
        socket.close();

    }
}
