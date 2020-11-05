package com.company;

public class Stone {
    String color;
    int fieldLayer;
    int xPos;
    int yPos;
    String colorCode;
    boolean isProtected = false;

/*    Stone(String color, String fieldLayer){
        this.color = color;
        this.fieldLayer = fieldLayer;
    }*/
    Stone(String color, int fieldLayer, int xPos, int yPos) {
        this.color = color;
        this.fieldLayer = fieldLayer;
        this.xPos =xPos;
        this.yPos = yPos;
        if(color == "W"){
            colorCode = "\u001B[37m   \u001b[0m";
        }else {
            colorCode = "\u001B[31m   \u001b[0m";
        }
    }

    public String getStoneColor() {
        return this.color;
    }

    public void setStoneColor(String color) {
        this.color = color;
    }


}
