package com.example.dicegame;

//point 11

// stores how many times the user or CPU wins
public class ScoreHolder {
    private int P1Win = 0;
    private int CPUWin= 0;

    // get the score for display
    public int getP1Win() {
        return P1Win;
    }

    public int getCPUWin() {
        return CPUWin;
    }

    // set the score to +1 to the respective player
    public void setP1Win() {
        P1Win = P1Win + 1;
    }

    public void setCPUWin() {
        CPUWin = CPUWin + 1;
    }

    private static final ScoreHolder holder = new ScoreHolder();
    public static ScoreHolder getInstance() {return holder;}
}
