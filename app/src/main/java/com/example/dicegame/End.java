package com.example.dicegame;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

public class End extends AppCompatActivity {
    TextView roundsTxt;
    TextView P1ScoreTxt;
    TextView CPUScoreTxt;

    TextView winTxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);

        int rounds = getIntent().getIntExtra("rounds", 0);
        int P1Score = getIntent().getIntExtra("P1.score", 0);
        int CPUScore = getIntent().getIntExtra("CPU.score", 0);

        System.out.println("P1.score" + P1Score);
        System.out.println("P1.score" + P1Score);

        roundsTxt = (TextView) findViewById(R.id.endRound);
        P1ScoreTxt = (TextView) findViewById(R.id.P1Score);
        CPUScoreTxt = (TextView) findViewById(R.id.CPUScoreF);
        winTxt = (TextView) findViewById(R.id.Winner);



        String roundString;

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

        if(P1Score > CPUScore ){
            winTxt.setText("Player wins");
            winTxt.setTextColor(Color.argb(200, 0, 255, 0));
        }else if(P1Score < CPUScore){
            winTxt.setText("CPU wins");
            winTxt.setTextColor(Color.argb(200, 255, 0, 0));

        }else{

            winTxt.setText("Tie");

        }

    }
}