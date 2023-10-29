package com.example.dicegame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class End extends AppCompatActivity {
    TextView roundsTxt;
    TextView P1ScoreTxt;
    TextView CPUScoreTxt;

    TextView winTxt;

    Button mainMenu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);

        // getting relevant data from the game
        int rounds = getIntent().getIntExtra("rounds", 0);
        int P1Score = getIntent().getIntExtra("P1.score", 0);
        int CPUScore = getIntent().getIntExtra("CPU.score", 0);

        roundsTxt = findViewById(R.id.endRound);
        P1ScoreTxt = findViewById(R.id.P1Score);
        CPUScoreTxt = findViewById(R.id.CPUScoreF);
        winTxt = findViewById(R.id.Winner);



        String roundString;

        // if there was a tie breaker have it display rather the amount of rounds
        if(rounds == -1){
            roundString = "round: Tie breaker";

        }else{
            roundString = "round: " + rounds;

        }


        String P1ScoreString = "player score: " + P1Score;
        String CPUScoreString = "CPU score: " + CPUScore;

        roundsTxt.setText(roundString);
        P1ScoreTxt.setText(P1ScoreString);
        CPUScoreTxt.setText(CPUScoreString);

        //point 7

        if(P1Score > CPUScore ){
            winTxt.setText("Player wins");
            winTxt.setTextColor(Color.argb(200, 0, 255, 0));
            ScoreHolder.getInstance().setP1Win();
        }else if(P1Score < CPUScore){
            winTxt.setText("CPU wins");
            winTxt.setTextColor(Color.argb(200, 255, 0, 0));
            ScoreHolder.getInstance().setCPUWin();
            System.out.println("CPU score: "+ScoreHolder.getInstance().getCPUWin());
        }else{
            // catch any errors
            winTxt.setText("Error");

        }


        mainMenu = findViewById(R.id.MainMenu);
        mainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
            }
        });


    }

    //point 8

    // on release of the back button the user is taken back to the main menu
    public boolean onKeyUp(int keyCode, KeyEvent keyEvent) {
        //check if the key is pressed and is released
        if ( keyEvent.isTracking() && keyCode == KeyEvent.KEYCODE_BACK && !keyEvent.isCanceled()) {

            // send the player back to the main menu
            Intent intent = new Intent(getBaseContext(), MainActivity.class);
            startActivity(intent);
        }
        return super.onKeyUp(keyCode, keyEvent);
    }

}