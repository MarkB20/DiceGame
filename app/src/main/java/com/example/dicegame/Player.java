package com.example.dicegame;

import android.widget.ImageView;
import android.widget.TextView;

public class Player {

    // initializing scoring elements
    String name;
    int score = 0;
    int rollScore = 0;
    int rolls = 3;


    // initializing dice elements
    ImageView[] DiceImages = new ImageView[5];
    int[] dice = new int[5];


    // initializing textView elements
    TextView ScoreTxt;
    TextView RollScoreTxt;
    TextView ReRollsTxt;




    public Player(String PName){
         name = PName;
    }

    // reset re rolls
    public void RollReset(){

        rolls = 2;

    }

    // reset score that will be used every roll and re roll to be counted up
    public  void  RollScoreReset(){
        rollScore = 0;

    }
    //sum of scores
    public void sumScores(){
        for(int die: dice){
            rollScore = rollScore + die;
        }
    }
    // add current roll scores to the overall score
    public void  sumOverallScores(){
        score = score + rollScore;

    }

    public void updateScoreTxt(){

        ScoreTxt.setText(name + ": " + score);
    }

    public void updateRollScoreTxt(){
        RollScoreTxt.setText(name + " roll score: " + rollScore);
    }

    public void updateReRollsTxt(){

        ReRollsTxt.setText(name + " Rolls: " + rolls);
    }












}
