package com.example.dicegame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;


public class NewGame extends AppCompatActivity  {
    //initializing the dice images for both player (P1) and computer(CPU)
    ImageView[] P1Dice = new ImageView[5];
    ImageView[] CPUDice = new ImageView[5];



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);

        // assigning each image to the image view
        P1Dice[0] = (ImageView) findViewById(R.id.P1Dice1);
        P1Dice[1] = (ImageView) findViewById(R.id.P1Dice2);
        P1Dice[2] = (ImageView) findViewById(R.id.P1Dice3);
        P1Dice[3] = (ImageView) findViewById(R.id.P1Dice4);
        P1Dice[4] = (ImageView) findViewById(R.id.P1Dice5);


        CPUDice[0]= ( ImageView) findViewById(R.id.CPUDice1);
        CPUDice[1]= ( ImageView) findViewById(R.id.CPUDice2);
        CPUDice[2]= ( ImageView) findViewById(R.id.CPUDice3);
        CPUDice[3]= ( ImageView) findViewById(R.id.CPUDice4);
        CPUDice[4]= ( ImageView) findViewById(R.id.CPUDice5);

        DiceInitializer diceInitializer = new DiceInitializer();
        diceInitializer.diceInt(P1Dice,CPUDice);


    }
}