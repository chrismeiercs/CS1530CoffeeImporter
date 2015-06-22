package coffeeimport;

import ch.qos.logback.core.net.ObjectWriter;
import com.fasterxml.jackson.databind.util.JSONPObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.Net.HttpMethods;
//import com.badlogic.gdx.Net.HttpRequest;
//import com.badlogic.gdx.Net.HttpResponse;
//import com.badlogic.gdx.Net.HttpResponseListener;

/**
 * Created by Tron on 6/21/2015.
 *
 *
 * This will not be part of the web app
 *
 * I only need it for reference
 *
 */
public class ParseTemporary {

    private URL url = null;
    private URLConnection conn = null;
    private String app_id;
    private String app_key;


    //This Method should initialize Parse

    public void Parse(){
        try {
            url = new URL("https://api.parse.com/1/classes/score/");
            app_id = "NyVAmF4GHENom2dWu7mKBGUWk8HAhPpxPSbFcSMP";
            app_key = "cZQI9V32nbWCj29nbH68gknoCtr9hNJnS4HJRUxp";
        } catch (MalformedURLException e) {
            System.out.println(e.getMessage());
        }
    }
}
