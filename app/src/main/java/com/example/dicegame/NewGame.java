package com.example.dicegame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class NewGame extends AppCompatActivity  {

    // creating new objects for each player
    Player P1 = new Player("Player");
    Player CPU = new Player("CPU");


    // buttons
    Button throwButton;
    Button scoreButton;


    //text view for displaying non player info
    TextView roundsTxt;
    TextView winText;
    int rounds = 1;



    // Strings for each TextView display
    String roundTxt = "round: ";

    // variables fro the main menu passed through
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

        //getting the mode selected so that the AI will be random or follow logic
         mode = getIntent().getStringExtra("mode");
         // getting the max score that the players must hit to win

        maxScore = getIntent().getIntExtra("max", 101);

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

        // update for non user text
        roundsTxt.setText(roundTxt + rounds);
        winText.setText("Player: " + P1Win + " | CPU: " + CPUWin);


        // point 3.5

        // when score is clicked  adds the roll score to the score and reset roll score
        scoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // if tie breaker (-1) is triggered then disable score function and tey user that they can use it
                // otherwise trigger the score function
                if (rounds == -1){
                    Toast.makeText(getApplicationContext(), "only throw in tie breaker",Toast.LENGTH_SHORT).show();
                }else{
                    score();
                }

            }
        });


        // point 3
        /*
        *  when the throw button is clicked it will
        *  randomize the dice for both P1 and CPU at the start
        */
        throwButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // roll the dice for both players
                P1.throwDice();
                CPU.throwDice();

                // when tie breaker (-1) happens then it just throws the dice and scores
                if(rounds == -1){
                    score();
                }





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

// point 5

        // if the player hasn't stated or is in a tie breaker: disallow this feature
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


    // function for when the user scores with by button press or automatically when out of re-rolls
    public  void score (){
        // point 4

        // if the CPU still has re-rolls it will do so using the provided difficulty using AI Mode
        while(CPU.rolls > 0){

            CPU.throwDice();

            AIMode();

            CPU.updateTxt();

        }


        // reset the selected dice
        P1.boolReRollReset();
        CPU.boolReRollReset();

        // sum up the current roll score with the actual score
        P1.sumOverallScores();
        CPU.sumOverallScores();

        // reset players roll score
        P1.RollScoreReset();
        CPU.RollScoreReset();


        // when the user has a tie breaker and there is a winner send to end screen but if its a tie again go again
        if(rounds == -1 && CPU.score == P1.score){

            // resets everything
            P1.tieBreaker();
            CPU.tieBreaker();

            // if someone wins the tie breaker
        }else if(rounds == -1 && CPU.score != P1.score ){

            P1.updateTxt();
            CPU.updateTxt();


            Intent intent = new Intent(getBaseContext(), End.class);
            intent.putExtra("P1.score", P1.score);
            intent.putExtra("CPU.score", CPU.score);
            intent.putExtra("rounds",rounds);
            startActivity(intent);

        }


        //  point 9

        // if there is a tie then activate tie breaker
         if((P1.score >= maxScore && CPU.score == P1.score) || rounds == -1 ){
             rounds = -1;
             roundsTxt.setText(roundTxt + "Tie breaker");

             // resets everything
             P1.tieBreaker();
             CPU.tieBreaker();

             // if some one has won
         }else if(P1.score >= maxScore || CPU.score >= maxScore ){
         // end screen showing who won.
            CPU.updateTxt();

             Intent intent = new Intent(getBaseContext(), End.class);
             intent.putExtra("P1.score", P1.score);
             intent.putExtra("CPU.score", CPU.score);
             intent.putExtra("rounds",rounds);
             startActivity(intent);
         }else{
             // if no tie breakers or wins happens
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

            // have the CPU select its dice since the dice are thrown
             AIMode();


         }

    }

    // dictates what the CPU does depending on if the user chosen easy or hard mode
    public void AIMode(){

        // if the player chooses easy mode then the AI is random
        if(Objects.equals(mode, "Easy")){

            // point 6

            int min = 1;
            int max = 5;
            int range = max - min + 1;
// generate random numbers within 1 to 5
            for (int i = 0; i < CPU.boolReRoll.length; i++ ){
                int rand = (int)(Math.random() * range) + min;
                if(rand == 1){
                    CPU.selectDice(i);
                }

            }

            // if the player chooses hard mode then the AI follows logic described bellow
        }else if(Objects.equals(mode, "Hard")){

            // point 12

            //if the CPU is losing by 10 have the CPU keep numbers above 3 otherwise above 2
            if (P1.score - CPU.score >= 10 ){
                CPU.autoSelect(3);

            }else{
                CPU.autoSelect(2);

            }

        }

    }



}