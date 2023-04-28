package org.fresno;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.net.URL;

@Component
public class ExtractKeyWords {
    private static final XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
    private static final XmlRpcClient client = new XmlRpcClient();

    public String extractKeyWords(String t) {
        try {

            config.setServerURL(new URL("http://fdn_py_container:8000/"));
            client.setConfig(config);
            Object[] params = new Object[]{t};
            String result = (String) client.execute("extract_keywords", params);
            System.out.println("Result: " + result);
            return result;
        } catch (MalformedURLException | XmlRpcException e) {
            throw new RuntimeException(e);
        }
    }
}

