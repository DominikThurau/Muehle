package com.company;

import java.util.Scanner;

public class Logic {
    Player playerOne;
    Player playerTwo;
    Player currentPlayer;
    Player notCurrentplayer;
    Field gameField = new Field();

    private void roundManagement() {
        //wechsele Spieler
        if (currentPlayer == playerOne) {
            currentPlayer = playerTwo;
            notCurrentplayer = playerOne;

        } else {
            currentPlayer = playerOne;
            notCurrentplayer = playerTwo;
        }
        System.out.println(currentPlayer.getName() + " ist am zug");

        //Phase 1
        if (currentPlayer.anzahlSteineGesetzt < 9) {
            setStone();
        }
        //phase 2
        else if (currentPlayer.stonesLeft > 3) {
            System.out.println("Phase 2 startet:");
            moveStone();
        } /*phase 3*/ else if (currentPlayer.stonesLeft == 3) {
            System.out.println("Phase 3 startet:");
            moveFreeStone();
        } else if (currentPlayer.stonesLeft < 3) {
            System.out.println("\u001b[32;1m Das Spiel ist vorbei, " + notCurrentplayer.name + " hat gewonnen\u001b[0m");
            System.out.println(playerOne.name + " hat " + playerOne.stonesLeft + " Steine, ");
            System.out.println(playerTwo.name + " hat " + playerTwo.stonesLeft + " Steine.");
            endGame();
        }
    }

    public void starteSpiel() {
        printRules();
        System.out.println("Name von Spieler Eins: ");
        Scanner scannerNameOne = new Scanner(System.in);
        String nameOne = scannerNameOne.nextLine();
        System.out.println("Name von Spieler Zwei: ");
        Scanner scannerNameTwo = new Scanner(System.in);
        String nameTwo = scannerNameTwo.nextLine();
        setRandomColor(nameOne, nameTwo);
        //System.out.println("Der Spieler mit den weißen Steinen beginnt.");
        gameField.drawField();
        roundManagement();
    }

    private void printRules() {
        System.out.println("\u001b[32;1m  Spielregeln:");
        System.out.println("-Der \u001b[35;1m Pinke \u001b[32;1m Spieler beginnt");
        System.out.println("-Jeder Spieler hat in Phase 1, 9 Steine zum setzen");
        System.out.println("-Wenn eine Mühle gebildet wird, dann darfst dem Gegner ein Stein nehmen");
        System.out.println("-Phase 2 Du darfst deine Steine nur auf benachbarte Felder bewegen");
        System.out.println("-Wenn ein Spieler keine legalen Züge machen kann \u001b[31;1m ->Spielende \u001b[32;1m");
        System.out.println("-Wenn ein Spieler weniger als 3 Steine hat \u001b[31;1m -> Spielende \u001b[32;1m");
        System.out.println("-Wenn ein Spieler genau 3 Steine hat darf er auf alle Felder springen");
        System.out.println("-Eingabe der Spielfelder und Steinnummer immer 2 stellig. Z.Bsp. -01-\u001b[0m");
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
        System.out.print(currentPlayer.getName() + currentPlayer.getStonesLeft() + " setze Spielstein auf Feld: ");
        Scanner scannerFeld = new Scanner(System.in);
        String scannerEingabe = scannerFeld.next();
        if (gameField.checkIfPositionIsAvailable(scannerEingabe, currentPlayer.color)) {
            gameField.setStoneAtPosition(scannerEingabe, currentPlayer.color);
            currentPlayer.incrementStone();
            System.out.println("hat funktioniert");
            gameField.drawField();
            if (gameField.checkMuehle(scannerEingabe, currentPlayer.color)) {
                removeOpponentStone();
            }

            roundManagement();

        } else {
            System.out.println("Feld ist nicht verfügbar, probier es nochmal weil das Feld schon besetzt ist");
            //System.out.println("hat nicht funktioniert");
            setStone();
        }
    }

    private void moveStone() {
        System.out.print(currentPlayer.getName() + currentPlayer.getStonesLeft() + ", welchen Stein willst du verschieben ?: ");
        Scanner scannerFeld1 = new Scanner(System.in);
        String scannerEingabe2 = scannerFeld1.next();
        if (!gameField.checkIfItIsTheOwnStone(scannerEingabe2, currentPlayer.color)) {
            moveStone();
        }
        System.out.print(currentPlayer.getName() + " und auf welches Feld ?: ");
        Scanner scannerFeld2 = new Scanner(System.in);
        String scannerEingabe3 = scannerFeld2.next();

        if (gameField.checkIfPositionIsAvailable(scannerEingabe3, currentPlayer.color) && gameField.checkIfLegit(scannerEingabe2, scannerEingabe3)) {
            gameField.moveStoneToPosition(scannerEingabe2, scannerEingabe3, currentPlayer.color);
            System.out.println("Stein setzen hat funktioniert");
            gameField.drawField();
            if (gameField.checkMuehle(scannerEingabe3, currentPlayer.color)) {
                removeOpponentStone();
            }
            roundManagement();
        } else {
            System.out.println("Stein setzen hat nicht funktioniert");

            moveStone();
        }
    }

    private void moveFreeStone() {
        System.out.print(currentPlayer.getName() + currentPlayer.getStonesLeft() + ", welchen Stein willst du frei verschieben ?: ");
        Scanner scannerFeld1 = new Scanner(System.in);
        String scannerEingabe2 = scannerFeld1.next();
        if (gameField.checkIfItIsTheOwnStone(scannerEingabe2, currentPlayer.color)) {
            System.out.print(currentPlayer.getName() + " und auf welches Feld ?: ");
            Scanner scannerFeld2 = new Scanner(System.in);
            String scannerEingabe3 = scannerFeld2.next();

            if (gameField.checkIfPositionIsAvailable(scannerEingabe3, currentPlayer.color)) {
                gameField.moveStoneToPosition(scannerEingabe2, scannerEingabe3, currentPlayer.color);
                System.out.println("Stein setzen hat funktioniert");
                gameField.drawField();
                if (gameField.checkMuehle(scannerEingabe3, currentPlayer.color)) {
                    removeOpponentStone();
                }
                roundManagement();

            } else {
                System.out.println("Stein setzen hat nicht funktioniert");

                moveFreeStone();
            }
        } else {
            System.out.println("Nana, das ist nicht dein Stein!");
            moveFreeStone();
        }
    }

    private void removeOpponentStone() {
        System.out.println(currentPlayer.getName() + " hat eine Mühle");
        System.out.print(currentPlayer.getName() + " welchen Stein vom Gegner willst du entfernen ?: ");
        Scanner scannerFeld4 = new Scanner(System.in);
        String scannerEingabe5 = scannerFeld4.next();
        if (gameField.checkIfRemoveIsLegit(scannerEingabe5, currentPlayer.color)) {
            if (gameField.removeStone(scannerEingabe5, notCurrentplayer)) {
                System.out.println("Stein entfernen hat funktioniert");
                gameField.drawField();
                roundManagement();
            } else {
                System.out.println("Stein entfernen hat nicht funktioniert");
                removeOpponentStone();
            }
        } else {
            System.out.println("Stein entfernen hat nicht funktioniert");
            removeOpponentStone();
        }
    }

    private void endGame() {
        System.exit(1);
    }
}

