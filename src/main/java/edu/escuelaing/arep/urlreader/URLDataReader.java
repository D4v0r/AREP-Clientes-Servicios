package edu.escuelaing.arep.urlreader;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class URLDataReader {

    public static void main(String[] args) throws IOException {
        // EJERCICIO 2
        URL site = new URL(args[0]);
        URLConnection urlConnection = site.openConnection();
        BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
        BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/resultados.html"));
        String inputLine = null;
        while ((inputLine = reader.readLine()) != null) {
            writer.write(inputLine);
            System.out.println(inputLine);
        }
        writer.close();
        reader.close();
    }
}
