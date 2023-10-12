package com.example.dicegame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // on click take user to about page
    public void aboutPage(View view){
        startActivity(new Intent(MainActivity.this, About.class));
    }
    // on click start game
    public void newGame(View view){
        startActivity(new Intent(MainActivity.this, NewGame.class));
    }
}