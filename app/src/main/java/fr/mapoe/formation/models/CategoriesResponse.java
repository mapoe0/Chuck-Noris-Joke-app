package fr.mapoe.formation.models;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class CategoriesResponse {
    public CategoriesResponse(String[] allCategories) {
        this.allCategories = allCategories;
    }

    public String[] allCategories;



    public static CategoriesResponse parseResponse(String data){

        String[] responseParse;

        if(data != null){
            // HAND PARSING
            data = data.replaceAll("\"","").replace("[","").replace("]","");
            Log.d("PARSING: ", data);
            responseParse = data.split(",");
            return new CategoriesResponse(responseParse);
        }
        return null;
    }

    // transform to Entity of categories object
    public static ArrayList<Categorie> getListEntity(CategoriesResponse allCategories){
        ArrayList<Categorie> categoriesLst = new ArrayList<>();
        for (String cat: allCategories.allCategories
             ) {
            categoriesLst.add(new Categorie(cat,false));
        }
        return  categoriesLst;
    }
}
