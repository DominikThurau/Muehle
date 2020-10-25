package com.company;

public class Stone {
    String color;
    int fieldLayer;
    int xPos;
    int yPos;

/*    Stone(String color, String fieldLayer){
        this.color = color;
        this.fieldLayer = fieldLayer;
    }*/
    Stone(String color, int fieldLayer, int xPos, int yPos) {
        this.color = color;
        this.fieldLayer = fieldLayer;
        this.xPos =xPos;
        this.yPos = yPos;
    }

    public String getStoneColor() {
        return this.color;
    }

    public void setStoneColor(String color) {
        this.color = color;
    }


}
