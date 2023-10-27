package com.example.dicegame;

public class ScoreHolder {
    private int P1Win = 0;
    private int CPUWin= 0;

    public int getP1Win() {
        return P1Win;
    }

    public int getCPUWin() {
        return CPUWin;
    }

    public void setP1Win() {
        P1Win = P1Win + 1;
    }

    public void setCPUWin() {
        this.CPUWin = CPUWin + 1;
    }

    private static final ScoreHolder holder = new ScoreHolder();
    public static ScoreHolder getInstance() {return holder;}
}
