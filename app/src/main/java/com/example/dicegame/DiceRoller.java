package com.example.dicegame;

import android.widget.ImageView;

public class DiceRoller {

    // roll all the dice

   public void roll(int[] dice, ImageView[] diceImages, boolean[] reRoll){

       int min = 1;

       int max = 6;
       int range = max - min + 1;
// generate random numbers within 1 to 10


       for (int i = 0; i < 5; i++) {
           if(!reRoll[i]){
               int rand = (int)(Math.random() * range) + min;
               dice[i] = rand;
           }

       }

       for (int i = 0; i < 5; i++) {
           if(!reRoll[i]){
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
}
