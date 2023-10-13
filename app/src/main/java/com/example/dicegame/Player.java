package com.example.dicegame;

import android.widget.ImageView;

public class Player {
    private final String name;
    int score = 0;
    int rolls = 2;
    ImageView[] DiceImages = new ImageView[5];
    int[] dice = new int[5];
    public Player(String PName){
         name = PName;
    }

    // reset re rolls
    public void RollReset(){

        rolls = 2;

    }






}
