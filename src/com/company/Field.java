package com.company;

import java.util.HashMap;

import java.lang.Math;

public class Field {
    Stone[][] outerField = new Stone[3][3];
    Stone[][] middleField = new Stone[3][3];
    Stone[][] innerField = new Stone[3][3];

    HashMap<String, Stone> koordinatenFeld = new HashMap<>();

    Field() {
        outerField[0][0] = new Stone("01", 0, 0, 0);
        outerField[0][1] = new Stone("10", 0, 0, 1);
        outerField[0][2] = new Stone("22", 0, 0, 2);
        outerField[1][0] = new Stone("02", 0, 1, 0);
        outerField[1][1] = new Stone("N/N", 0,1, 1);
        outerField[1][2] = new Stone("23", 0, 1, 2);
        outerField[2][0] = new Stone("03", 0, 2, 0);
        outerField[2][1] = new Stone("15", 0, 2, 1);
        outerField[2][2] = new Stone("24", 0, 2, 2);

        middleField[0][0] = new Stone("04", 1, 0, 0);
        middleField[0][1] = new Stone("11", 1, 0, 1);
        middleField[0][2] = new Stone("19", 1, 0, 2);
        middleField[1][0] = new Stone("05", 1, 1, 0);
        middleField[1][1] = new Stone("N/N", 1,1, 1);
        middleField[1][2] = new Stone("20", 1, 1, 2);
        middleField[2][0] = new Stone("06", 1, 2, 0);
        middleField[2][1] = new Stone("14", 1, 2, 1);
        middleField[2][2] = new Stone("21", 1, 2, 2);

        innerField[0][0] = new Stone("07", 2, 0, 0);
        innerField[0][1] = new Stone("12", 2, 0, 1);
        innerField[0][2] = new Stone("16", 2, 0, 2);
        innerField[1][0] = new Stone("08", 2, 1, 0);
        innerField[1][1] = new Stone("N/N", 2,1, 1);
        innerField[1][2] = new Stone("17", 2, 1, 2);
        innerField[2][0] = new Stone("09", 2, 2, 0);
        innerField[2][1] = new Stone("13", 2, 2, 1);
        innerField[2][2] = new Stone("18", 2, 2, 2);


        koordinatenFeld.put("01", outerField[0][0]);
        koordinatenFeld.put("10", outerField[0][1]);
        koordinatenFeld.put("22", outerField[0][2]);
        koordinatenFeld.put("02", outerField[1][0]);
        koordinatenFeld.put("23", outerField[1][2]);
        koordinatenFeld.put("03", outerField[2][0]);
        koordinatenFeld.put("15", outerField[2][1]);
        koordinatenFeld.put("24", outerField[2][2]);

        koordinatenFeld.put("04", middleField[0][0]);
        koordinatenFeld.put("11", middleField[0][1]);
        koordinatenFeld.put("19", middleField[0][2]);
        koordinatenFeld.put("05", middleField[1][0]);
        koordinatenFeld.put("20", middleField[1][2]);
        koordinatenFeld.put("06", middleField[2][0]);
        koordinatenFeld.put("14", middleField[2][1]);
        koordinatenFeld.put("21", middleField[2][2]);

        koordinatenFeld.put("07", innerField[0][0]);
        koordinatenFeld.put("12", innerField[0][1]);
        koordinatenFeld.put("16", innerField[0][2]);
        koordinatenFeld.put("08", innerField[1][0]);
        koordinatenFeld.put("17", innerField[1][2]);
        koordinatenFeld.put("09", innerField[2][0]);
        koordinatenFeld.put("13", innerField[2][1]);
        koordinatenFeld.put("18", innerField[2][2]);

    }

    public boolean checkIfPositionIsAvailable(String ID, String color) {
        if (koordinatenFeld.containsKey(ID)) {
            System.out.println("" + koordinatenFeld.get(ID).color);
            System.out.println("Feld existiert");

            if (koordinatenFeld.get(ID).color == "W" || koordinatenFeld.get(ID).color == "S") {
                return false;
            } else {

                return true;
            }
        } else {
            return false;
        }
    }

    public boolean checkIfRemoveIsLegit(String ID, String currentColor) {
        if (koordinatenFeld.get(ID).color == currentColor || !koordinatenFeld.containsKey(ID)) {
            return false;
        } else {
            return true;
        }
    }

    public boolean checkIfItIsTheOwnStone(String ID, String currentColor) {
        if (koordinatenFeld.get(ID).color != currentColor || !koordinatenFeld.containsKey(ID)) {
            System.out.println("Wähle einen eigenen Stein");
            return false;
        } else {
            return true;
        }
    }

    public void setStoneAtPosition(String ID, String color) {
        koordinatenFeld.get(ID).setStoneColor(color);
    }

    public void moveStoneToPosition(String IDOld, String IDNew, String color) {
        removeOwnStone(IDOld);
        setStoneAtPosition(IDNew, color);
    }

    public boolean checkIfLegit(String IDOld, String IDNew) {
        if (koordinatenFeld.get(IDOld).fieldLayer == koordinatenFeld.get(IDNew).fieldLayer) {
            System.out.println("Differenz: " + Math.abs(koordinatenFeld.get(IDOld).yPos - koordinatenFeld.get(IDNew).yPos));
            if (koordinatenFeld.get(IDOld).xPos == koordinatenFeld.get(IDNew).xPos) {
                if (Math.abs(koordinatenFeld.get(IDOld).yPos - koordinatenFeld.get(IDNew).yPos) == 1) {
                    System.out.println("Der zug ist legitim");
                    return true;
                }
            } else if (koordinatenFeld.get(IDOld).yPos == koordinatenFeld.get(IDNew).yPos) {
                if (Math.abs(koordinatenFeld.get(IDOld).xPos - koordinatenFeld.get(IDNew).xPos) == 1) {
                    System.out.println("Der zug ist legitim");
                    return true;
                }
            } else {
                System.out.println("Der zug ist nicht legitim");
                return false;
            }
        } else if (koordinatenFeld.get(IDOld).xPos == koordinatenFeld.get(IDNew).xPos || koordinatenFeld.get(IDOld).yPos == koordinatenFeld.get(IDNew).yPos) {
            if (Math.abs(koordinatenFeld.get(IDOld).fieldLayer - koordinatenFeld.get(IDNew).fieldLayer) == 1) {
                System.out.println("Der zug ist legitim");
                return true;
            }
        }
        return false;
    }

    public boolean checkMuehle(String position, String color) {
        System.out.println("Mühle wird gechekt");
        if(outerField[koordinatenFeld.get(position).xPos][koordinatenFeld.get(position).yPos].color.equals(middleField[koordinatenFeld.get(position).xPos][koordinatenFeld.get(position).yPos].color)&&middleField[koordinatenFeld.get(position).xPos][koordinatenFeld.get(position).yPos].color.equals(innerField[koordinatenFeld.get(position).xPos][koordinatenFeld.get(position).yPos].color)){
            return true;
        }
        else if (koordinatenFeld.get(position).fieldLayer == 0) {
            if(outerField[koordinatenFeld.get(position).xPos][0].color.equals(outerField[koordinatenFeld.get(position).xPos][1].color) && outerField[koordinatenFeld.get(position).xPos][1].color.equals(outerField[koordinatenFeld.get(position).xPos][2].color)){
                //System.out.println("Eine Mühle wurde von spieler " + color + "gebilded");
                return true;
            }
            if(outerField[0][koordinatenFeld.get(position).yPos].color.equals(outerField[1][koordinatenFeld.get(position).yPos].color) && outerField[1][koordinatenFeld.get(position).yPos].color.equals(outerField[2][koordinatenFeld.get(position).yPos].color)){
                //System.out.println("Eine Mühle wurde von spieler " + color + "gebilded");
                return true;
            }
        }
        else if (koordinatenFeld.get(position).fieldLayer == 1) {
            if(middleField[koordinatenFeld.get(position).xPos][0].color.equals(middleField[koordinatenFeld.get(position).xPos][1].color) && middleField[koordinatenFeld.get(position).xPos][1].color.equals(middleField[koordinatenFeld.get(position).xPos][2].color)){
                //System.out.println("Eine Mühle wurde von spieler " + color + "gebilded");
                return true;
            }
            if(middleField[0][koordinatenFeld.get(position).yPos].color.equals(middleField[1][koordinatenFeld.get(position).yPos].color) && middleField[1][koordinatenFeld.get(position).yPos].color.equals(middleField[2][koordinatenFeld.get(position).yPos].color)){
                //System.out.println("Eine Mühle wurde von spieler " + color + "gebilded");
                return true;
            }
        }
        else if (koordinatenFeld.get(position).fieldLayer == 2) {
            if(innerField[koordinatenFeld.get(position).xPos][0].color.equals(innerField[koordinatenFeld.get(position).xPos][1].color) && innerField[koordinatenFeld.get(position).xPos][1].color.equals(innerField[koordinatenFeld.get(position).xPos][2].color)){
                //System.out.println("Eine Mühle wurde von spieler " + color + "gebilded");
                return true;
            }
            if(innerField[0][koordinatenFeld.get(position).yPos].color.equals(innerField[1][koordinatenFeld.get(position).yPos].color) && innerField[1][koordinatenFeld.get(position).yPos].color.equals(innerField[2][koordinatenFeld.get(position).yPos].color)){
                System.out.println("Eine Mühle wurde von spieler " + color + "gebilded");
                return true;
            }
        }

        return false;
    }

    private boolean removeOwnStone(String ID) {
        if (koordinatenFeld.get(ID).isProtected == false) {
            koordinatenFeld.get(ID).setStoneColor("00");
            return true;
        } else {
            return false;
        }
    }

    public boolean removeStone(String ID, Player notCurrentPlayer) {
        if (koordinatenFeld.get(ID).isProtected == false) {
            koordinatenFeld.get(ID).setStoneColor("00");
            notCurrentPlayer.decrementStones();
            return true;
        } else {
            return false;
        }
    }

    public void drawField() {
        System.out.println(outerField[0][0].color + "-----------" + outerField[1][0].color + "-----------" + outerField[2][0].color);
        System.out.println("|           |           |");
        System.out.println("|   " + middleField[0][0].color + "-------" + middleField[1][0].color + "-------" + middleField[2][0].color + "   |");
        System.out.println("|   |       |       |   |");
        System.out.println("|   |   " + innerField[0][0].color + "---" + innerField[1][0].color + "---" + innerField[2][0].color + "   |   |");
        System.out.println("|   |   |       |   |   |");
        System.out.println(outerField[0][1].color + "--" + middleField[0][1].color + "---" + innerField[0][1].color + "       " + innerField[2][1].color + "---" + middleField[2][1].color + "---" + outerField[2][1].color);
        System.out.println("|   |   |       |   |   |");
        System.out.println("|   |   " + innerField[0][2].color + "---" + innerField[1][2].color + "---" + innerField[2][2].color + "   |   |");
        System.out.println("|   |       |       |   |");
        System.out.println("|   " + middleField[0][2].color + "-------" + middleField[1][2].color + "-------" + middleField[2][2].color + "   |");
        System.out.println("|           |           |");
        System.out.println(outerField[0][2].color + "-----------" + outerField[1][2].color + "-----------" + outerField[2][2].color);
    }
}
