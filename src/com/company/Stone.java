package com.company;

public class Stone {
    String color;
    int fieldLayer;
    int xPos;
    int yPos;
    String colorCode;

/*    Stone(String color, String fieldLayer){
        this.color = color;
        this.fieldLayer = fieldLayer;
    }*/
    Stone(String color, int fieldLayer, int xPos, int yPos) {
        this.color = color;
        this.fieldLayer = fieldLayer;
        this.xPos =xPos;
        this.yPos = yPos;
        colorCode = "   ";
    }

    public String getStoneColor() {
        return this.color;
    }

    public void setStoneColor(String color) {
        this.color = color;
        if(color == "W"){
            colorCode = "\u001b[45m   \u001b[0m";

        }else if(color == "S") {
            colorCode = "\u001b[44m   \u001b[0m";
        }else {
            colorCode = "   ";
        }
    }


}
