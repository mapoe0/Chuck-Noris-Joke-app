package fr.mapoe.formation.models;

import org.json.JSONException;
import org.json.JSONObject;

public class JokeResponse {
    public JokeResponse(String value, String id, String date) {
        this.value = value;
        this.id = id;
        this.date = date;
    }

    public String value;
    public String id;

    public String getValue() {
        return value;
    }

    public String getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String date;

    public static JokeResponse parseResponse(String data){
        try{
            JSONObject jsonObject = new JSONObject(data);
            String id = jsonObject.getString("id");
            String value = jsonObject.getString("value");
            String date = jsonObject.getString("created_at");
            return new JokeResponse(value,id,date);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static Joke toEntity(JokeResponse jokeResponse){
        return new Joke(jokeResponse.value,jokeResponse.id,jokeResponse.date);
    }


}
