package com.company;

public class Player {
    String name;
    String color;
    int stonesLeft = 9;
    int anzahlSteineGesetzt = 8;

    Player(String name, String color){
        this.name = name;
        this.color = color;
    }
    public void incrementStone(){
        anzahlSteineGesetzt ++ ;
    }
    public String getName (){
        return this.name;
    }
}
