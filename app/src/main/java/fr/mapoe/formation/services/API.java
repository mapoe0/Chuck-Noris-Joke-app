package fr.mapoe.formation.services;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;

import java.net.URL;

import fr.mapoe.formation.models.CategoriesResponse;
import fr.mapoe.formation.models.JokeResponse;


public class API {
    // base url
    static final String JOKE_URL = "https://matchilling-chuck-norris-jokes-v1.p.rapidapi.com/jokes/random";
    static final String CATEGORIES_URL = "https://matchilling-chuck-norris-jokes-v1.p.rapidapi.com/jokes/categories";

    // HOST and KEY
    static final String HOST_ADDRESS = "matchilling-chuck-norris-jokes-v1.p.rapidapi.com";
    static final String API_KEY = "a0228bcac8msh759e880b2efc663p141273jsn890294865af8";

    public static JokeResponse getJoke(String categories){

        String line = null;
        try {
           URL url = new URL(JOKE_URL+"?category="+categories);

           HttpURLConnection con = (HttpURLConnection) url.openConnection();

           con.setRequestProperty("accept","application/json");
           con.setRequestProperty("X-RapidAPI-Host",HOST_ADDRESS);
           con.setRequestProperty("X-RapidAPI-Key",API_KEY);

            InputStream inputStream = new BufferedInputStream(con.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuffer stringBuffer = new StringBuffer();

            if(bufferedReader != null){
                while((line = bufferedReader.readLine()) != null){
                    stringBuffer.append(line).append("\n");
                }
                String data = stringBuffer.toString();
                Log.d("CONSOLE: ", data);
                con.disconnect();
                return JokeResponse.parseResponse(data);
            }
            else{
                return null;
            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static CategoriesResponse getCategories(){
        String line = null;
        Log.d("CONSOLE", "API METHODE START");
        try {
            URL url = new URL(CATEGORIES_URL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestProperty("accept","application/json");
            con.setRequestProperty("X-RapidAPI-Host",HOST_ADDRESS);
            con.setRequestProperty("X-RapidAPI-Key",API_KEY);

            InputStream inputStream = new BufferedInputStream(con.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuffer stringBuffer = new StringBuffer();


            if(bufferedReader != null){
                while((line = bufferedReader.readLine()) != null){
                    stringBuffer.append(line).append("\n");
                }
                String data = stringBuffer.toString();
                Log.d("CONSOLE: ", data);
                return CategoriesResponse.parseResponse(data);
            }
            else{
                return null;
            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
