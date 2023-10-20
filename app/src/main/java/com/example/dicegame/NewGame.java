package com.example.dicegame;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class NewGame extends AppCompatActivity  {
    //initializing the dice images for both player (P1) and computer(CPU)



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



    // Strings for each TextView display
    String roundTxt = "round: ";

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
        P1.ScoreTxt = (TextView) findViewById(R.id.playerScore);
        P1.RollScoreTxt = (TextView) findViewById(R.id.PlayerRollScore);
        P1.ReRollsTxt = (TextView) findViewById(R.id.P1ReRolls);

        // assigning each textview for CPU
        CPU.ScoreTxt = (TextView) findViewById(R.id.CPUScore);
        CPU.RollScoreTxt = (TextView) findViewById(R.id.CPURollScore);
        CPU.ReRollsTxt = (TextView) findViewById(R.id.CPUReRolls);

        // updates all the textViews so that they display the default at the start
        P1.updateScoreTxt();
        P1.updateRollScoreTxt();
        P1.updateReRollsTxt();

        // updates all the textViews so that they display the default at the start
        CPU.updateScoreTxt();
        CPU.updateRollScoreTxt();
        CPU.updateReRollsTxt();


        roundsTxt.setText(roundTxt + rounds);



//        P1.DiceImages[0].setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                P1.DiceImages[0].setColorFilter(Color.argb(50, 0, 0, 0)
//                );
//            }
//        });



        // when score is clicked  adds the roll score to the score and reset roll score
        scoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              score();
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
                diceRoller.roll(P1.dice, P1.DiceImages,P1.boolReRoll);
                diceRoller.roll(CPU.dice, CPU.DiceImages,P1.boolReRoll);


                 P1.rolls = P1.rolls - 1;
                 CPU.rolls = CPU.rolls - 1;



                  P1.sumScores();
                  P1.updateRollScoreTxt();
                  P1.updateReRollsTxt();


                  CPU.sumScores();
                  CPU.updateRollScoreTxt();
                  CPU.updateReRollsTxt();


              }else if(P1.rolls != 0){
                // re roll for player


                  P1.rolls = P1.rolls - 1;

                  diceRoller.roll(P1.dice, P1.DiceImages,P1.boolReRoll);

                  P1.sumScores();
                  P1.updateRollScoreTxt();

                  P1.selectReset();


              }else if(CPU.rolls!= 0){
                // re roll for CPU automatically with logic
                  CPU.rolls = CPU.rolls - 2;
                  diceRoller.roll(CPU.dice, CPU.DiceImages, CPU.boolReRoll);
              }else{
                  score();

              }
            }

        });





    }

    public void onClickRoll(final View view){

        if(view.getId() == R.id.P1Dice1){
            P1.selectDice(0);


        }else if(view.getId() == R.id.P1Dice2){
            P1.selectDice(1);

        }else if(view.getId() == R.id.P1Dice3){
            P1.selectDice(2);

        }else if(view.getId() == R.id.P1Dice4){
            P1.selectDice(3);

        }else if(view.getId() == R.id.P1Dice5){
            P1.selectDice(4);

        }



    }
    public  void score (){
        P1.boolReRollReset();
        CPU.boolReRollReset();

        P1.sumOverallScores();
        CPU.sumOverallScores();

        // displaying players overall score
        P1.updateScoreTxt();
        CPU.updateScoreTxt();

        // reset players roll score
        P1.RollScoreReset();
        CPU.RollScoreReset();

        // reset players roll score display


        // add 1 to the rounds counter and displays the counter again
        rounds =rounds + 1;
        roundsTxt.setText(roundTxt + rounds);

        // if(P1.ScoreTxt => 101 && Cpu.ScoreTxt => 101 ){
        // tie breaker
        // }else if(P1.ScoreTxt => 101 || Cpu.ScoreTxt => 101 ){
        // end screen showing who won.
        // }

    }


}