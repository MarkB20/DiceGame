package com.example.dicegame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class NewGame extends AppCompatActivity  {
    //initializing the dice images for both player (P1) and computer(CPU)

    // Todo add color contrast using a color contrast picker using the red and white from dice:
    //#D51D1B
    //#AF0C0D
    //#DADADA
    //#72A6A9
    //#2E3B2C
    // Todo find out how to fix errors in the activity images

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


        P1.updateTxt();


        CPU.updateTxt();


        roundsTxt.setText(roundTxt + rounds);



//        P1.DiceImages[0].setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                P1.DiceImages[0].setColorFilter(Color.argb(50, 0, 0, 0)
//                );
//            }
//        });


        P1.selectReset();
        CPU.selectReset();
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

                if(rounds == -1){
                    P1.throwDice();
                    CPU.throwDice();
                    score();
                }


                // roll the dice for both players
                    P1.throwDice();
                    CPU.throwDice();


                // for any number 3 and above keep unless losing by 20 then 4 and above

                if (P1.score - CPU.score >= 20 ){
                    CPU.autoSelect(3);

                }else{
                    CPU.autoSelect(2);

                }






                 if(P1.rolls == 0){



//                     if CPU re rolls separately
//                     while(CPU.rolls != 0){
//
//
//                         CPU.updateTxt();
//
//                      CPU.updateTxt();
//                     }






                     score();



                 }
              }


        });





    }


    // checks to see what image gets
    public void onClickRoll(final View view){


        if(P1.rolls == 3){
            Toast.makeText(getApplicationContext(), "must throw first",Toast.LENGTH_SHORT).show();

        }else if(rounds == -1){
            Toast.makeText(getApplicationContext(), "no re-rolls on tie breaker",Toast.LENGTH_SHORT).show();

        }else if(view.getId() == R.id.P1Dice1){
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
        // reset the selection dice function
        P1.boolReRollReset();
        CPU.boolReRollReset();

        // sum up the current roll score with the actual score
        P1.sumOverallScores();
        CPU.sumOverallScores();


        // reset players roll score
        P1.RollScoreReset();
        CPU.RollScoreReset();

        System.out.println(P1.score);
        System.out.println(CPU.score);

        if(rounds == -1){
            Intent intent = new Intent(getBaseContext(), End.class);
            intent.putExtra("P1.score", P1.score);
            intent.putExtra("CPU.score", CPU.score);
            intent.putExtra("rounds",rounds);
            startActivity(intent);

        }

         if(P1.score >= 101 && CPU.score >= 101 ){

             //TODO implement tie breaker
             // tie breaker
             rounds = -1;
             roundsTxt.setText(roundTxt + "Tie breaker");

             // resets everything
             P1.tieBreaker();
             CPU.tieBreaker();


         }else if(P1.score >= 101 || CPU.score >= 101 ){
         // end screen showing who won.


             Intent intent = new Intent(getBaseContext(), End.class);
             intent.putExtra("P1.score", P1.score);
             intent.putExtra("CPU.score", CPU.score);
             intent.putExtra("rounds",rounds);
             startActivity(intent);
         }else{
             rounds =rounds + 1;
             roundsTxt.setText(roundTxt + rounds);


             // reset how many rolls the user gets
             P1.RollReset();
             CPU.RollReset();

             // updates all the text displayed to show new results
             P1.updateTxt();
             CPU.updateTxt();

             // reset the current dice roll score
             P1.RollScoreReset();
             CPU.RollScoreReset();

             // reset what dice are selected
             P1.selectReset();
             CPU.selectReset();

             // Todo add winning condition
             //      - add tie breaker
             // Todo add ai using there re rolls if user just went to score and didn't re-roll

             // throws the dice automatically
             P1.throwDice();
             CPU.throwDice();



         }




        // add 1 to the rounds counter and displays the counter again



    }


}