package com.example.dicegame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.stream.IntStream;


public class NewGame extends AppCompatActivity  {
    //initializing the dice images for both player (P1) and computer(CPU)

    boolean[] reRoll = new boolean[5];

    // classes
    DiceRoller diceRoller = new DiceRoller();

    // creating new objects for each player
    Player P1 = new Player("Player");
    Player CPU = new Player("CPU");


    // buttons
    Button throwButton;
    Button scoreButton;


    TextView roundsTxt;
    int rounds = 0;

    // player text
    TextView playerScore;
    TextView playerRollScore;
    TextView P1ReRolls;

    // CPU text
    TextView CPUScore;
    TextView CPURollScore;
    TextView CPUReRolls;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);

        throwButton = findViewById(R.id.Throw);
        scoreButton = findViewById(R.id.Score);

        // assigning each image to the image view for P1
        P1.DiceImages[0] = findViewById(R.id.P1Dice1);
        P1.DiceImages[1] = findViewById(R.id.P1Dice2);
        P1.DiceImages[2] = findViewById(R.id.P1Dice3);
        P1.DiceImages[3] = findViewById(R.id.P1Dice4);
        P1.DiceImages[4] = findViewById(R.id.P1Dice5);

        // assigning each image to the image view for CPU
        CPU.DiceImages[0]= findViewById(R.id.CPUDice1);
        CPU.DiceImages[1]= findViewById(R.id.CPUDice2);
        CPU.DiceImages[2]= findViewById(R.id.CPUDice3);
        CPU.DiceImages[3]= findViewById(R.id.CPUDice4);
        CPU.DiceImages[4]= findViewById(R.id.CPUDice5);

        //displays all ones before the game starts
        diceRoller.diceInt(P1.DiceImages,CPU.DiceImages);

       // assigning textview for the round score
        roundsTxt = (TextView) findViewById(R.id.rounds);

        // assigning each textview for P1
        playerScore = (TextView) findViewById(R.id.playerScore);
        playerRollScore = (TextView) findViewById(R.id.PlayerRollScore);
        P1ReRolls = (TextView) findViewById(R.id.P1ReRolls);

        // assigning each textview for CPU
        CPUScore = (TextView) findViewById(R.id.CPUScore);
        CPURollScore = (TextView) findViewById(R.id.CPURollScore);
        CPUReRolls = (TextView) findViewById(R.id.CPUReRolls);


        playerScore.setText(P1.name + ": " + P1.score);
        playerRollScore.setText(P1.name + " roll score: " + P1.rollScore);
        P1ReRolls.setText(P1.name + " Rolls: " + P1.rolls);

        CPUScore.setText(CPU.name + ": " + CPU.score);
        CPURollScore.setText(CPU.name + " roll score: " + CPU.rollScore);
        CPUReRolls.setText(CPU.name + " Rolls: " + CPU.rolls);


        roundsTxt.setText("round: " + rounds);


        scoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                P1.sumOverallScores();
                CPU.sumOverallScores();

                playerScore.setText(P1.name + ": " + P1.score);
                CPUScore.setText(CPU.name + ": " + CPU.score);
            }
        });


        /*
        *  when the throw button is clicked it will
        *  randomize the dice for both P1 and CPU at the start
        */
        throwButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                P1.RollScoreReset();
                CPU.RollScoreReset();
              if(P1.rolls == 3 && CPU.rolls == 3){
                // roll the dice for both players
                diceRoller.roll(P1.dice, P1.DiceImages);
                diceRoller.roll(CPU.dice, CPU.DiceImages);
                // add 1 to the rounds counter and displays the counter again
                rounds =rounds + 1;
                roundsTxt.setText("round: " + rounds);
                /*
                * for later
                *
                * P1.rolls = P1.rolls - 1
                * CPU.rolls = CPU.rolls - 1
                *
                * */

                  P1.sumScores();
                  playerRollScore.setText(P1.name + " roll score: " + P1.rollScore);


                  CPU.sumScores();
                  CPURollScore.setText(CPU.name + " roll score: " + CPU.rollScore);


              }else if(P1.rolls != 0){
                // re roll for player
              }else if(CPU.rolls!= 0){
                // re roll for CPU automatically with logic
              }
            }

        });





    }


}