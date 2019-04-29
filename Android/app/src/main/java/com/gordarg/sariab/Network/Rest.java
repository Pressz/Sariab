package com.gordarg.sariab.Network;

import android.os.Build;
import android.support.annotation.RequiresApi;

import com.gordarg.sariab.Configuration.App;
import com.gordarg.sariab.Configuration.Network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

public class Rest implements IRest {

    private URL apiEndPoint;
    private HttpURLConnection urlConnection;

    public Rest(String controller) throws IOException {

            // Endpoint configuration
            this.apiEndPoint = new URL(Network.api_end_point.toString() + controller);
            this.urlConnection = (HttpURLConnection) apiEndPoint.openConnection();
            this.urlConnection.setConnectTimeout(3000);

            // Connection headers
            // this.urlConnection.setRequestProperty("User-Agent", Network.agent + "-" + App.version.toString());
            // this.urlConnection.setRequestProperty("Accept", "application/vnd.github.v3+json");
            // this.urlConnection.setRequestProperty("Contact-Me","hathibelagal@example.com");

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public String ConvertInputStreamToString(InputStream inputStream, Charset charset) throws IOException {

        StringBuilder stringBuilder = new StringBuilder();
        String line = null;

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, charset))) {
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
        }

        return stringBuilder.toString();
    }


    @Override
    public short testConnection() throws IOException {
        // return's url status code
        return (short) this.urlConnection.getResponseCode();
    }

    @Override
    public String get() throws IOException {

        InputStream responseBody = this.urlConnection.getInputStream();
        InputStreamReader responseBodyReader =
           new InputStreamReader(responseBody, "UTF-8");
        java.util.Scanner s = new java.util.Scanner(responseBody).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";

    }

    @Override
    public void post() throws IOException {

            this.urlConnection.setRequestMethod("POST");
            // Create the data
            String myData = "message=Hello";

            // Enable writing
            this.urlConnection.setDoOutput(true);

            // Write the data
            this.urlConnection.getOutputStream().write(myData.getBytes());

    }

    @Override
    public void put() {

    }

    @Override
    public void delete() {

    }


}
