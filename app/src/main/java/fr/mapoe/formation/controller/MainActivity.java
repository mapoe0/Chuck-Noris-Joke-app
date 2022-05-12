package fr.mapoe.formation.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import fr.mapoe.formation.R;
import fr.mapoe.formation.Utils.BackgroundTask;
import fr.mapoe.formation.adaptaters.GridAdapter;
import fr.mapoe.formation.models.Categorie;
import fr.mapoe.formation.models.CategoriesResponse;
import fr.mapoe.formation.services.API;


public class MainActivity extends AppCompatActivity {



// DATA
ArrayList<Categorie> categories = new ArrayList<>();

// VARIABLE
GridView gridView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = (GridView) findViewById(R.id.grid_cat);


        loadData();
    }

    private void loadData(){
        Log.d("CONSOLE","loadData");
        new BackgroundTask(MainActivity.this){

            @Override
            public void doInBackground() {
                Log.d("CONSOLE","START LOADING DATA");
                //CALL API
                categories = CategoriesResponse.getListEntity(API.getCategories());
            }

            @Override
            public void onPostExecute() {
                // UPDATE UI
                updateUI();
            }
        }.execute();


    }
    private void updateUI(){

        if(categories != null){
            gridView.setAdapter(new GridAdapter(this,categories));
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent jokeIntent = new Intent(getApplicationContext(), JokeActivity.class);
                    jokeIntent.putExtra("categorie",categories.get(i).title);
                    startActivity(jokeIntent);
                    finish();
                }
            });
        }
        else{
            Log.d("ERRREUR","ArrayList<Categorie> null");
        }
    }

}