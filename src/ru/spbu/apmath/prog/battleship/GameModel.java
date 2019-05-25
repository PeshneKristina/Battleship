package ru.spbu.apmath.prog.battleship;

import javax.swing.*;
import java.util.ArrayList;

import static ru.spbu.apmath.prog.battleship.Gui.*;
import static ru.spbu.apmath.prog.battleship.Shots.hitTheShip;

class GameModel {
    private boolean gameOver;

    GameModel(boolean gameOver) {
        this.gameOver = gameOver;
    }

    void game(Ships shipsOfHuman, Ships shipsOfAI, ArrayList<JButton> buttonsOfAI, ArrayList<JButton> buttonsOfHuman, Shots humanShots, Shots AIShots) {
        for (int i = 0; i < buttonsOfAI.size(); i++) {
            int finalI = i;
            buttonsOfAI.get(i).addActionListener(event -> {
                if (!gameOver) {
                    doShot(buttonsOfAI, shipsOfAI, humanShots, finalI);
                    System.out.println(humanShots.isShot());
                    System.out.println(!hitTheShip(shipsOfAI, humanShots.getLastShot()));
                    if (humanShots.isShot() && !hitTheShip(shipsOfAI, humanShots.getLastShot())) {
                        if (AIShots.getInjuredShip().isEmpty()) {
                            doAIShot(buttonsOfHuman, shipsOfHuman, AIShots);
                            while (!AIShots.isShot()&&AIShots.getInjuredShip().isEmpty()) {
                                doAIShot(buttonsOfHuman, shipsOfHuman, AIShots);
                            }
                        }
                        if (!AIShots.getInjuredShip().isEmpty()) {
                            doAIShot2(buttonsOfHuman, shipsOfHuman, AIShots);
                            while (!AIShots.getInjuredShip().isEmpty() && !AIShots.isShot()) {
                                doAIShot2(buttonsOfHuman, shipsOfHuman, AIShots);
                            }
                        }
                    }
                    if (!shipsOfHuman.checkSurvivors()) {
                        gameOver = true;
                        System.out.println("Вы проиграли!");
                    }
                    if (!shipsOfAI.checkSurvivors()) {
                        gameOver = true;
                        System.out.println("Вы выиграли!");
                    }

                } else {
                    System.out.println("Игра закончена!");
                }

            });
        }


    }


}
