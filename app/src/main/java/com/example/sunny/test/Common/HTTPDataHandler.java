package com.example.sunny.test.Common;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Sunny on 12/03/2018.
 */

public class HTTPDataHandler {

    static String stream = null;
    private HttpsURLConnection urlConnection = null;
    private BufferedReader r = null;

    public HTTPDataHandler() {

    }

    public String GetHTTPData(String urlString){
        try {
            // create URL object and put the urlString
            URL url = new URL(urlString);
            // open the connection to url
            urlConnection = (HttpsURLConnection) url.openConnection();

            // check if there is no problem with the connection
            if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK){

                // get the all info from the url
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                r = new BufferedReader(new InputStreamReader(in));
                StringBuilder sb = new StringBuilder();

                String line = r.readLine();
                while (line != null) {
                    sb.append(line);
                    line = r.readLine();
                }

                stream = sb.toString();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            // at finally close the reader and the connection
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (r != null){
                try {
                    r.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return stream;
    }
}
