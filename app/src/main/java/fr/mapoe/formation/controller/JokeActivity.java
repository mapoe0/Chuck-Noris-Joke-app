package fr.mapoe.formation.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import fr.mapoe.formation.R;
import fr.mapoe.formation.Utils.BackgroundTask;
import fr.mapoe.formation.models.Joke;
import fr.mapoe.formation.models.JokeResponse;
import fr.mapoe.formation.services.API;

public class JokeActivity extends AppCompatActivity {
    // DATA
    Joke currentJoke;
    // VARIABLE
    Button b;
    TextView textDisplay;
    String cat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke_acitivty);

        // GET PARAMS
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            cat = extras.getString("categorie");
        }
        TextView catText = (TextView) findViewById(R.id.categories_text);
        catText.setText(cat.substring(0, 1).toUpperCase() + cat.substring(1));
        b = findViewById(R.id.start_task_btn);
        textDisplay = (TextView) findViewById(R.id.joke_display_text);

        b.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                new BackgroundTask(JokeActivity.this) {

                    @Override
                    public void doInBackground() {

                        //put you background code
                        //same like doingBackground
                        //Background Thread


                        loadData();

                    }

                    @Override
                    public void onPostExecute() {

                        //hear is result part same
                        //same like post execute
                        //UI Thread(update your UI widget)
                        Log.d("ONPOSTEXECUTE","tâches executé");
                        changeUI();

                    }
                }.execute();
            }
        });
    }
    private void changeUI(){
        b.setText("Restart");
        if(currentJoke != null){
            textDisplay.setText(currentJoke.getValue());
        }
        else{
            textDisplay.setText("Erreur");
        }


    }
    private void loadData(){
        currentJoke = JokeResponse.toEntity(API.getJoke(cat));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent mainIntent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(mainIntent);
        finish();
    }
}