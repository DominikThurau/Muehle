package com.company;

public class Player {
    String name;
    String color;
    int stonesLeft = 9;
    int anzahlSteineGesetzt = 0;

    Player(String name, String color) {
        if(color =="W"){
            this.name = "\u001b[35;1m" + name + "\u001b[0m";
        }else {
            this.name = "\u001b[34;1m" + name + "\u001b[0m";
        }

        this.color = color;
    }

    public void incrementStone() {
        anzahlSteineGesetzt++;
    }

    public void decrementStones() {
        stonesLeft = stonesLeft-1;
    }

    public String getName() {
        return this.name;
    }

    public String getStonesLeft() {
        return " (" + stonesLeft + " stones left)";
    }

    public String getAnzahlSteineGesetzt() {
        return " (" + anzahlSteineGesetzt + " stones set)";
    }
}
