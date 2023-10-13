package com.example.dicegame;

import android.widget.ImageView;

public class DiceRoller {

    public void diceInt(ImageView[] P1Dice, ImageView[] CPUDice){
            for (ImageView Dice : P1Dice){
                Dice.setImageResource(R.drawable.die_face_1);
            }

        for (ImageView Dice : CPUDice){
            Dice.setImageResource(R.drawable.die_face_1);
        }
    }
}
