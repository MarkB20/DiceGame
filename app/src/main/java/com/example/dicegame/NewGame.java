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

import java.util.Objects;

public class NewGame extends AppCompatActivity  {
    // Todo add color contrast using a color contrast picker using the red and white from dice:

    //#D51D1B
    //#AF0C0D
    //#DADADA
    //#72A6A9
    //#2E3B2C


    // creating new objects for each player
    Player P1 = new Player("Player");
    Player CPU = new Player("CPU");


    // buttons
    Button throwButton;
    Button scoreButton;


    TextView roundsTxt;
    TextView winText;
    int rounds = 0;



    // Strings for each TextView display
    String roundTxt = "round: ";

    String mode;
    int maxScore = 101;

    int P1Win = ScoreHolder.getInstance().getP1Win();
    int CPUWin= ScoreHolder.getInstance().getCPUWin();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);

        throwButton = findViewById(R.id.Throw);
        scoreButton = findViewById(R.id.Score);


         mode = getIntent().getStringExtra("mode");
         maxScore = getIntent().getIntExtra("max", 101);



         System.out.println(maxScore);




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

       // assigning textview for the round score
        roundsTxt = findViewById(R.id.rounds);
        winText = findViewById(R.id.WinTxt);

        // assigning each textview for P1
        P1.ScoreTxt = findViewById(R.id.playerScore);
        P1.RollScoreTxt = findViewById(R.id.PlayerRollScore);
        P1.ReRollsTxt = findViewById(R.id.P1ReRolls);

        // assigning each textview for CPU
        CPU.ScoreTxt = findViewById(R.id.CPUScore);
        CPU.RollScoreTxt = findViewById(R.id.CPURollScore);
        CPU.ReRollsTxt = findViewById(R.id.CPUReRolls);

        // update text before game starts
        P1.updateTxt();
        CPU.updateTxt();

        roundsTxt.setText(roundTxt + rounds);
        winText.setText("P1: " + P1Win + " | CPU: " + CPUWin);

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
                AIMode();

                 if(P1.rolls == 0){
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
        while(CPU.rolls != 0){
            CPU.updateTxt();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            AIMode();

            CPU.rolls = CPU.rolls -1;
            CPU.updateTxt();
        }



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

        // when the user has a tie breaker and there is a winner send to end screen but if its a tie again go again
        if(rounds == -1 && CPU.score == P1.score){

            // resets everything
            P1.tieBreaker();
            CPU.tieBreaker();

        }else if(rounds == -1 && CPU.score != P1.score ){

            P1.updateTxt();
            CPU.updateTxt();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            Intent intent = new Intent(getBaseContext(), End.class);
            intent.putExtra("P1.score", P1.score);
            intent.putExtra("CPU.score", CPU.score);
            intent.putExtra("rounds",rounds);
            startActivity(intent);

        }

         if(P1.score >= maxScore && CPU.score == P1.score ){
             rounds = -1;
             roundsTxt.setText(roundTxt + "Tie breaker");

             // resets everything
             P1.tieBreaker();
             CPU.tieBreaker();

         }else if(P1.score >= maxScore || CPU.score >= maxScore ){
         // end screen showing who won.
            CPU.updateTxt();
             try {
                 Thread.sleep(500);
             } catch (InterruptedException e) {
                 throw new RuntimeException(e);
             }

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

             // throws the dice automatically
             P1.throwDice();
             CPU.throwDice();


             AIMode();


         }






        // add 1 to the rounds counter and displays the counter again



    }

    public void AIMode(){

        if(Objects.equals(mode, "Easy")){

            int min = 1;
            int max = 6;
            int range = max - min + 1;
// generate random numbers within 1 to 10
            for (int i = 0; i < CPU.boolReRoll.length; i++ ){
                int rand = (int)(Math.random() * range) + min;
                if(rand == 1){
                    CPU.selectDice(i);
                }

            }

        }else if(Objects.equals(mode, "Hard")){
            if (P1.score - CPU.score >= 20 ){
                CPU.autoSelect(3);

            }else{
                CPU.autoSelect(2);

            }






        }

    }


}