package edu.escuelaing.arep.urlreader;

import java.net.MalformedURLException;
import java.net.URL;

/**
 *  Class URLComponents to show URL information.
 * @author Davor Cortes
 * @version 1.0
 */
public class URLComponents {
    /**
     * Create an URL object and shows its information.
     * @param args
     */
    public static void main(String[]args){
        //EJERCICIO 1
        URL site = null;
        try {
            site = new URL("https://www.google.com/search?q=java&sxsrf=ALeKk01z6H4PkvWAcMMxA--D6rt9NtqXlw:1598030917643&source=lnms&tbm=isch&sa=X&ved=2ahUKEwjYpr6J6azrAhWn1FkKHRhYAl0Q_AUoAXoECBYQAw&biw=1536&bih=792#imgrc=oltCrsrrNHsfZM");

            String output =
                    "Protocol : " + site.getProtocol() + "\n" +
                    "Authority : "+ site.getAuthority() + "\n" +
                    "Host : " + site.getHost() + "\n" +
                    "Port : " + site.getPort() + "\n" +
                    "Path :  " + site.getPath() + "\n" +
                    "Query :  " + site.getQuery() + "\n" +
                    "File : " + site.getFile() + "\n" +
                    "Ref : " + site.getRef() + "\n";
            System.out.println(output);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
