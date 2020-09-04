package edu.escuelaing.arep.httpserver;

import org.apache.tika.Tika;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import  java.io.File;

public class WebServer {

    private int port;

    public WebServer(int port){
        this.port = port;
    }
    public void start() throws IOException {
        ServerSocket listenSocket = new ServerSocket(port);
        boolean running = true;
        while(running){
            Socket connectionSocket = listenSocket.accept();
            attendRequest(connectionSocket);
            connectionSocket.close();
        }
        listenSocket.close();
    }

    private void attendRequest(Socket connectionSocket) throws IOException {
        BufferedReader inFromClient = new BufferedReader( new InputStreamReader(connectionSocket.getInputStream()));
        DataOutputStream outToClient = new DataOutputStream( connectionSocket.getOutputStream());
        String inputLine = null;
        String requestLine = inFromClient.readLine();
        System.out.println(requestLine);
        Request request = new Request(requestLine);
        System.out.println(request.getUri());
        generateResponse(request, outToClient);
        outToClient.close();
        inFromClient.close();
    }

    private void generateResponse(Request req, DataOutputStream out) throws IOException{
        URI uri = req.getUri();
        String path = uri.getPath();
        Path file = Paths.get("src/main/resources" + path);
        Tika tika = new Tika();
        try {
            String fileName = String.valueOf(file.getFileName());
            File arch = new File("src/main/resources/" + fileName);
            int numOfBytes = (int ) arch.length();
            FileInputStream inFile = new FileInputStream("src/main/resources/"+ fileName);
            byte[] fileBytes = new byte[numOfBytes];
            inFile.read(fileBytes);
            String mimeType = tika.detect(file);
            String header = "HTTP/1.1 200 OK\r\n"
                    + "Content-Type: "+mimeType+"\r\n"
                    + "\r\n";
            out.writeBytes(header);
            out.write(fileBytes, 0, numOfBytes);
        } catch (FileNotFoundException e ){
            out.writeBytes("HTTP/1.0 404 Not Found \r\n" + "Content-type: text/html" + "\r\n\r\n");
            out.writeBytes("<h1> 404 File not found </h1>");
        }
    }
    

}
