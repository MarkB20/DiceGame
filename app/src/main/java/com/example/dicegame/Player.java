package com.example.dicegame;

import android.graphics.Color;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;



public class Player {

    DiceRoller diceRoller = new DiceRoller();
   final int color = Color.argb(50, 0, 0, 0);
    final int colorRest = Color.argb(0, 0, 0, 0);

    boolean[] boolReRoll = new boolean[5];
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

        rolls = 3;
        // updateReRollsTxt();

        updateTxt();

    }

    // reset score that will be used every roll and re roll to be counted up
    public  void  RollScoreReset(){
        rollScore = 0;
        // updateRollScoreTxt();
        updateTxt();
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
        RollScoreReset();

    }

    public void updateTxt(){
        ScoreTxt.setText(name + ": " + score);
        RollScoreTxt.setText(name + " roll score: " + rollScore);
        ReRollsTxt.setText(name + " Rolls: " + rolls);
    }

    public void boolReRollReset(){
        Arrays.fill(boolReRoll, false);
    }
    public void selectDice(int arrayNo) {


        if(!boolReRoll[arrayNo]){
            DiceImages[arrayNo].setColorFilter(color);
            boolReRoll[arrayNo] = true;
        }else{
            DiceImages[arrayNo].setColorFilter(colorRest);
            boolReRoll[arrayNo] = false;

        }

        }

        public void selectReset(){
            for (ImageView dice: DiceImages){
            dice.setColorFilter(colorRest);


            }

            boolReRollReset();
            for (boolean bool : boolReRoll){


            }
        }


    public  void throwDice (){


        // take away 1 from number of rolls for re-roll
        rolls = rolls - 1;

        //roll dice
        diceRoller.roll(dice, DiceImages,boolReRoll);

        RollScoreReset();

        // adds up the dice stores
        sumScores();
        // updates the text to display the score
        updateTxt();

    }

    public void autoSelect(int riskLv){

        for (int i = 0; i < dice.length; i++ ){
            if(dice[i] >riskLv && !boolReRoll[i] ){
                selectDice(i);

            }
        }


    }

    public void tieBreaker(){
        score = 0;

        rolls = 0;

        // updates all the text displayed to show new results


        // reset the current dice roll score

        RollScoreReset();

        // reset what dice are selected

        selectReset();

        updateTxt();



    }












}
