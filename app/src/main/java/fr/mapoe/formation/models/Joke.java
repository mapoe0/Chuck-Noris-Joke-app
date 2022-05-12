package fr.mapoe.formation.models;

public class Joke {
    public String getValue() {
        return value;
    }

    String value;
    String id;
    String date;

    Joke(String value, String id, String date) {
        this.value = value;
        this.id = id;
        this.date = date;
    }
}
