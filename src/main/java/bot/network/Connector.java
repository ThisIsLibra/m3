/*
 * Copyright (C) 2020 Max 'Libra' Kersten [@Libranalysis, https://maxkersten.nl]
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package bot.network;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;

/**
 * This class abstracts the HTTP connections away from the user. Upon its
 * creation, the proxy details are provided. If these are null, then no proxy
 * will be used. If these are not null, they will be used as a proxy server for
 * each request that is made with that specific instance of the connector.
 *
 * @author Max 'Libra' Kersten [@Libranalysis, https://maxkersten.nl]
 */
public class Connector {

    /**
     * The address of the proxy server. This value should be null if no proxy
     * server should be used
     */
    private String proxyAddress;

    /**
     * The port of the proxy server. This value should be null if no proxy
     * server should be used
     */
    private Integer proxyPort;

    /**
     * The user-agent to use in the requests
     */
    private String userAgent;

    /**
     * Creates a HTTP connector to easily send HTTP requests. Every request will
     * be sent via the proxy server if the provided values are not null. Both
     * values can also be null when creating this object, after which no proxy
     * server is used. The argumentless constructor sets these values to null
     * automatically.
     *
     * @param proxyAddress the address of the proxy server
     * @param proxyPort the port of the proxy server
     * @param userAgent the user-agent to use in the requests
     */
    public Connector(String proxyAddress, Integer proxyPort, String userAgent) {
        this.proxyAddress = proxyAddress;
        this.proxyPort = proxyPort;
    }

    /**
     * Sends a HTTP POST request to the given URL, with the given parameters,
     * and the given user-agent. If a proxy server was provided during the
     * initialisation of this object, this request is sent via the proxy server.
     *
     * @param url the URL to connect to
     * @param parameter the POST parameter(s)
     * @return the result of the request in the form of a string
     * @throws Exception if the connection fails
     */
    public String post(String url, String parameter) throws Exception {
        String result = "";
        byte[] data = null;
        InputStream is = null;
        URL urlObject = new URL(url);
        HttpURLConnection connection;
        if (proxyAddress == null && proxyPort == null) {
            connection = (HttpURLConnection) urlObject.openConnection();
        } else {
            Proxy webProxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyAddress, proxyPort));
            connection = (HttpURLConnection) urlObject.openConnection(webProxy);
        }
        connection.setRequestProperty("User-Agent", userAgent);
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setRequestProperty("Content-Length", "" + Integer.toString(parameter.getBytes().length));
        OutputStream outputStream = connection.getOutputStream();
        data = parameter.getBytes("UTF-8");
        outputStream.write(data);
        int parameterLength = parameter.length();
        data = null;
        connection.connect();
        int responseCode = connection.getResponseCode();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        if (responseCode == 200) {
            is = connection.getInputStream();

            byte[] buffer = new byte[parameterLength + 3000];
            int bytesRead;
            while ((bytesRead = is.read(buffer)) != -1) {
                byteArrayOutputStream.write(buffer, 0, bytesRead);
            }
            data = byteArrayOutputStream.toByteArray();
            result = new String(data, "UTF-8");
        } else {
            //Result is empty by default, meaning an empty string is returned if the response code is not HTTP OK (status 200)
        }
        return result;
    }

    /**
     * Sends a HTTP GET request to the given URL, with the given parameters, and
     * the given user-agent. If a proxy server was provided during the
     * initialisation of this object, this request is sent via tha proxy server.
     *
     * @param url the URL to connect to
     * @param parameter the GET parameter(s)
     * @return the result of the request in the form of a string
     * @throws Exception if the connection fails
     */
    public String get(String url, String parameter) throws Exception {
        URL urlObject = new URL(url);
        HttpURLConnection connection;
        if (proxyAddress == null && proxyPort == null) {
            connection = (HttpURLConnection) urlObject.openConnection();
        } else {
            Proxy webProxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyAddress, proxyPort));
            connection = (HttpURLConnection) urlObject.openConnection(webProxy);
        }
        connection.setRequestProperty("User-Agent", userAgent);
        connection.setRequestMethod("GET");

        StringBuilder content = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            String line;
            while ((line = in.readLine()) != null) {
                content.append(line);
                content.append(System.lineSeparator());
            }
        }
        return content.toString();
    }
}
