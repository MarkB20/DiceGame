package com.example.dicegame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


public class NewGame extends AppCompatActivity  {
    //initializing the dice images for both player (P1) and computer(CPU)

    boolean[] reRoll = new boolean[5];

    DiceRoller diceRoller = new DiceRoller();

    // creating new objects for each player
    Player P1 = new Player("Player");
    Player CPU = new Player("CPU");

    Button throwButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);

        throwButton = findViewById(R.id.Throw);

        // assigning each image to the image view
        P1.DiceImages[0] = findViewById(R.id.P1Dice1);
        P1.DiceImages[1] = findViewById(R.id.P1Dice2);
        P1.DiceImages[2] = findViewById(R.id.P1Dice3);
        P1.DiceImages[3] = findViewById(R.id.P1Dice4);
        P1.DiceImages[4] = findViewById(R.id.P1Dice5);


        CPU.DiceImages[0]= findViewById(R.id.CPUDice1);
        CPU.DiceImages[1]= findViewById(R.id.CPUDice2);
        CPU.DiceImages[2]= findViewById(R.id.CPUDice3);
        CPU.DiceImages[3]= findViewById(R.id.CPUDice4);
        CPU.DiceImages[4]= findViewById(R.id.CPUDice5);


        diceRoller.diceInt(P1.DiceImages,CPU.DiceImages);

        throwButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                diceRoller.roll(P1.dice, P1.DiceImages);
                diceRoller.roll(CPU.dice, CPU.DiceImages);
            }
        });

    }


}