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


    // roll all the dice
   public void roll(int[] dice, ImageView[] diceImages){

       int min = 1;
       int max = 5;
       int range = max - min + 1;
// generate random numbers within 1 to 10
       for (int i = 0; i < 5; i++) {
           int rand = (int)(Math.random() * range) + min;
           dice[i] = rand;
       }

       for (int i = 0; i < 5; i++) {
          switch (dice[i]){
              case 1:
                  diceImages[i].setImageResource(R.drawable.die_face_1);
                  break;
              case 2:
                  diceImages[i].setImageResource(R.drawable.die_face_2);
                  break;
              case 3:
                  diceImages[i].setImageResource(R.drawable.die_face_3);
                  break;
              case 4:
                  diceImages[i].setImageResource(R.drawable.die_face_4);
                  break;
              case 5:
                  diceImages[i].setImageResource(R.drawable.die_face_5);
                  break;
              case 6:
                  diceImages[i].setImageResource(R.drawable.die_face_6);
                  break;
          }
       }



   }
}