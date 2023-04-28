package org.fresno;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.net.URL;
@Component
public class GetVerbForms {
    private static final XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
    private static final XmlRpcClient client = new XmlRpcClient();

    public String getVerbForms(String verb) {
        try {

            config.setServerURL(new URL("http://fdn_py_container:8000/"));
            client.setConfig(config);
            Object[] params = new Object[]{verb};
            String result = (String) client.execute("get_verb_forms", params);
            System.out.println("Result: " + result);
            return result;
        } catch (MalformedURLException | XmlRpcException e) {
            throw new RuntimeException(e);
        }
    }
    public String getAllVerbsStartWith(String start) {
        try {

            config.setServerURL(new URL("http://fdn_py_container:8000/"));
            client.setConfig(config);
            Object[] params = new Object[]{start};
            String result = (String) client.execute("get_all_verbs_start_with", params);
            System.out.println("Result: " + result);
            return result;
        } catch (MalformedURLException | XmlRpcException e) {
            throw new RuntimeException(e);
        }
    }

}
