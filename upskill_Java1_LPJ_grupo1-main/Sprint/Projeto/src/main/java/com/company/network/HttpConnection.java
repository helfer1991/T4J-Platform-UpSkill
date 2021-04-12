package com.company.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Classe com métodos para estabelecer uma conecção e configurar um pedido ao webservice.
 * 
 * @author pbs
 */
public class HttpConnection {

    /**
     * Leitura de dados de entrada do tipo InputStream.
     * Devolve uma cópia dos dados de entrada no formato de String.
     * 
     * @param in dados de entrada do tipo InputStream
     * @return cópia dos dados de entrada no formato de String
     */
    private static String readBody(InputStream in) {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        try {
            String read = br.readLine();
            while (read != null) {
                sb.append(read);
                read = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
    
    /**
     * Configuração da conecção e do pedido ao webservice.
     * 
     * @param httpRequest pedido ao webservice
     * @return resposta como HttpResponse
     */
    public static HttpResponse makeRequest(HttpRequest httpRequest) {
        HttpURLConnection httpConn = null;
        int resCode = -1;
        String body = "";
        try {
            URL url = new URL(httpRequest.getUrl());
            URLConnection urlConn = url.openConnection();
            if (!(urlConn instanceof HttpURLConnection)) {
                throw new IOException("URL is not an Http URL");
            }
            httpConn = (HttpURLConnection) urlConn;
            httpConn.setConnectTimeout(3000);
            httpConn.setRequestProperty("Content-Type", "application/xml");
            switch (httpRequest.getType()) {
                case GET:
                    httpConn.setRequestMethod("GET");
                    httpConn.setDoInput(true);
                    break;
                case POST:
                    httpConn.setRequestMethod("POST");
                    httpConn.setDoOutput(true);
                    break;
                case PUT:
                    httpConn.setRequestMethod("PUT");
                    httpConn.setDoOutput(true);
                    break;
                case DELETE:
                    httpConn.setRequestMethod("DELETE");
                    break;
            }
            httpConn.connect();
            resCode = httpConn.getResponseCode();
            body = readBody(httpConn.getInputStream());
        } catch (MalformedURLException e) {
            //this is for normalize the error events according to the way is handled by the WS
            resCode = HttpStatusCode.Conflict;
        } catch (IOException e) {
            //this is for normalize the error events according to the way is handled by the WS
            resCode = HttpStatusCode.Conflict;
        }
        HttpResponse httpResponse = new HttpResponse(resCode, body);
        return httpResponse;
    }
}
