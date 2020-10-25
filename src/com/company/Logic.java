package com.company;

import java.util.Scanner;

public class Logic {
    Player playerOne;
    Player playerTwo;
    Player currentPlayer;
    Field gameField = new Field();

    Logic() {

    }

    private void roundManagement() {

        //wechsele Spieler
        if (currentPlayer == playerOne) {
            currentPlayer = playerTwo;
        } else {
            currentPlayer = playerOne;
        }
        System.out.println(currentPlayer.getName() + " ist am zug");
        CheckMuehle();
        //Phase 1
        if (playerOne.anzahlSteineGesetzt < 9 || playerTwo.anzahlSteineGesetzt < 9) {
            setStone();
        }
        //phase 2
        else if (currentPlayer.stonesLeft > 3) {
            System.out.println("Phase 2 startet");
            moveStone();

        }

    }

    public void starteSpiel() {
        System.out.println("Spielerklärung: ...");
        System.out.println("Name von Spieler Eins: ");
        Scanner scannerNameOne = new Scanner(System.in);
        String nameOne = scannerNameOne.next();
        System.out.println("Name von Spieler Zwei: ");
        Scanner scannerNameTwo = new Scanner(System.in);
        String nameTwo = scannerNameTwo.next();
        setRandomColor(nameOne, nameTwo);
        //System.out.println("Der Spieler mit den weißen Steinen beginnt.");
        gameField.drawField();
        roundManagement();

    }

    private void setRandomColor(String nameOne, String nameTwo) {
        int randomNumber;
        //Überprüfen ob es tatsächlich Zufall ist
        randomNumber = (int) (Math.random() * 2);
        if (randomNumber == 0) {
            playerOne = new Player(nameOne, "S");
            playerTwo = new Player(nameTwo, "W");
            currentPlayer = playerOne;
        } else {
            playerOne = new Player(nameOne, "W");
            playerTwo = new Player(nameTwo, "S");
            currentPlayer = playerTwo;
        }
    }

    private void setStone() {
        System.out.print(currentPlayer.getName() + ", setze Spielstein auf Feld: ");
        Scanner scannerFeld = new Scanner(System.in);
        String scannerEingabe = scannerFeld.next();
        if (gameField.checkIfPositionIsAvailable(scannerEingabe, currentPlayer.color)) {
            gameField.setStoneAtPosition(scannerEingabe, currentPlayer.color);
            System.out.println("hat funktioniert");
            gameField.drawField();
            currentPlayer.incrementStone();
            System.out.println("" + currentPlayer.anzahlSteineGesetzt);
            roundManagement();

        } else {
            System.out.println("Feld ist nicht verfügbar, probier es nochmal weil das Feld schon besetzt ist");
            //System.out.println("hat nicht funktioniert");
            setStone();


        }
    }

    private void moveStone() {
        System.out.print(currentPlayer.getName() + " Welchen Stein willst du verschieben ?: ");
        Scanner scannerFeld1 = new Scanner(System.in);
        String scannerEingabe2 = scannerFeld1.next();

        System.out.print(currentPlayer.getName() + " und auf welches Feld ?: ");
        Scanner scannerFeld2 = new Scanner(System.in);
        String scannerEingabe3 = scannerFeld2.next();

        if (gameField.checkIfPositionIsAvailable(scannerEingabe3, currentPlayer.color) && gameField.checkIfLegit(scannerEingabe2, scannerEingabe3)) {
            gameField.moveStoneToPosition(scannerEingabe2, scannerEingabe3, currentPlayer.color);
            System.out.println("Stein setzen hat funktioniert");
            gameField.drawField();
            roundManagement();

        } else {
            System.out.println("Stein setzen hat nicht funktioniert");

            moveStone();
        }
    }
    public void CheckMuehle() {
        if (
                gameField.outerField[0][0].getStoneColor().equals("S") && gameField.outerField[1][0].getStoneColor().equals("S") && gameField.outerField[2][0].getStoneColor().equals("S") ||
                        gameField.outerField[0][2].getStoneColor().equals("S") && gameField.outerField[1][2].getStoneColor().equals("S") && gameField.outerField[2][2].getStoneColor().equals("S") ||
                        gameField.outerField[0][0].getStoneColor().equals("S") && gameField.outerField[0][1].getStoneColor().equals("S") && gameField.outerField[0][2].getStoneColor().equals("S") ||
                        gameField.outerField[2][0].getStoneColor().equals("S") && gameField.outerField[2][1].getStoneColor().equals("S") && gameField.outerField[2][2].getStoneColor().equals("S") ||

                        gameField.middleField[0][0].getStoneColor().equals("S") && gameField.middleField[1][0].getStoneColor().equals("S") && gameField.middleField[2][0].getStoneColor().equals("S") ||
                        gameField.middleField[0][2].getStoneColor().equals("S") && gameField.middleField[1][2].getStoneColor().equals("S") && gameField.middleField[2][2].getStoneColor().equals("S") ||
                        gameField.middleField[0][0].getStoneColor().equals("S") && gameField.middleField[0][1].getStoneColor().equals("S") && gameField.middleField[0][2].getStoneColor().equals("S") ||
                        gameField.middleField[2][0].getStoneColor().equals("S") && gameField.middleField[2][1].getStoneColor().equals("S") && gameField.middleField[2][2].getStoneColor().equals("S") ||

                        gameField.innerField[0][0].getStoneColor().equals("S") && gameField.innerField[1][0].getStoneColor().equals("S") && gameField.innerField[2][0].getStoneColor().equals("S") ||
                        gameField.innerField[0][2].getStoneColor().equals("S") && gameField.innerField[1][2].getStoneColor().equals("S") && gameField.innerField[2][2].getStoneColor().equals("S") ||
                        gameField.innerField[0][0].getStoneColor().equals("S") && gameField.innerField[0][1].getStoneColor().equals("S") && gameField.innerField[0][2].getStoneColor().equals("S") ||
                        gameField.innerField[2][0].getStoneColor().equals("S") && gameField.innerField[2][1].getStoneColor().equals("S") && gameField.innerField[2][2].getStoneColor().equals("S") ||

                        gameField.outerField[0][1].getStoneColor().equals("S") && gameField.middleField[0][1].getStoneColor().equals("S") && gameField.innerField[0][1].getStoneColor().equals("S") ||
                        gameField.outerField[2][1].getStoneColor().equals("S") && gameField.middleField[2][1].getStoneColor().equals("S") && gameField.innerField[2][1].getStoneColor().equals("S") ||
                        gameField.outerField[1][2].getStoneColor().equals("S") && gameField.middleField[1][2].getStoneColor().equals("S") && gameField.innerField[1][2].getStoneColor().equals("S") ||
                        gameField.outerField[1][0].getStoneColor().equals("S") && gameField.middleField[1][0].getStoneColor().equals("S") && gameField.innerField[1][0].getStoneColor().equals("S") ||

                        gameField.outerField[0][0].getStoneColor().equals("W") && gameField.outerField[1][0].getStoneColor().equals("W") && gameField.outerField[2][0].getStoneColor().equals("W") ||
                        gameField.outerField[0][2].getStoneColor().equals("W") && gameField.outerField[1][2].getStoneColor().equals("W") && gameField.outerField[2][2].getStoneColor().equals("W") ||
                        gameField.outerField[0][0].getStoneColor().equals("W") && gameField.outerField[0][1].getStoneColor().equals("W") && gameField.outerField[0][2].getStoneColor().equals("W") ||
                        gameField.outerField[2][0].getStoneColor().equals("W") && gameField.outerField[2][1].getStoneColor().equals("W") && gameField.outerField[2][2].getStoneColor().equals("W") ||

                        gameField.middleField[0][0].getStoneColor().equals("W") && gameField.middleField[1][0].getStoneColor().equals("W") && gameField.middleField[2][0].getStoneColor().equals("W") ||
                        gameField.middleField[0][2].getStoneColor().equals("W") && gameField.middleField[1][2].getStoneColor().equals("W") && gameField.middleField[2][2].getStoneColor().equals("W") ||
                        gameField.middleField[0][0].getStoneColor().equals("W") && gameField.middleField[0][1].getStoneColor().equals("W") && gameField.middleField[0][2].getStoneColor().equals("W") ||
                        gameField.middleField[2][0].getStoneColor().equals("W") && gameField.middleField[2][1].getStoneColor().equals("W") && gameField.middleField[2][2].getStoneColor().equals("W") ||

                        gameField.innerField[0][0].getStoneColor().equals("W") && gameField.innerField[1][0].getStoneColor().equals("W") && gameField.innerField[2][0].getStoneColor().equals("W") ||
                        gameField.innerField[0][2].getStoneColor().equals("W") && gameField.innerField[1][2].getStoneColor().equals("W") && gameField.innerField[2][2].getStoneColor().equals("W") ||
                        gameField.innerField[0][0].getStoneColor().equals("W") && gameField.innerField[0][1].getStoneColor().equals("W") && gameField.innerField[0][2].getStoneColor().equals("W") ||
                        gameField.innerField[2][0].getStoneColor().equals("W") && gameField.innerField[2][1].getStoneColor().equals("W") && gameField.innerField[2][2].getStoneColor().equals("W") ||

                        gameField.outerField[0][1].getStoneColor().equals("W") && gameField.middleField[0][1].getStoneColor().equals("W") && gameField.innerField[0][1].getStoneColor().equals("W") ||
                        gameField.outerField[2][1].getStoneColor().equals("W") && gameField.middleField[2][1].getStoneColor().equals("W") && gameField.innerField[2][1].getStoneColor().equals("W") ||
                        gameField.outerField[1][2].getStoneColor().equals("W") && gameField.middleField[1][2].getStoneColor().equals("W") && gameField.innerField[1][2].getStoneColor().equals("W") ||
                        gameField.outerField[1][0].getStoneColor().equals("W") && gameField.middleField[1][0].getStoneColor().equals("W") && gameField.innerField[1][0].getStoneColor().equals("W")


        ) {
            System.out.println(currentPlayer.getName() + "hat eine Mühle");
            System.out.print(currentPlayer.getName() + " welchen Stein vom Gegner willst du entfernen ?: ");
            Scanner scannerFeld4 = new Scanner(System.in);
            String scannerEingabe5 = scannerFeld4.next();
            gameField.removeStone(scannerEingabe5);
            System.out.println("Stein entfernen hat funktioniert");
            gameField.drawField();
            roundManagement();
        }
    }
}

