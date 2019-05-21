package ru.spbu.apmath.prog.battleship;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static ru.spbu.apmath.prog.battleship.Gui.doAIShot;
import static ru.spbu.apmath.prog.battleship.Gui.doShot;
import static ru.spbu.apmath.prog.battleship.Shots.hitTheShip;

public class GameModel {
    private boolean gameOver;

    public GameModel(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public void game(Ships shipsOfHuman, Ships shipsOfAI, ArrayList<JButton> buttonsOfAI, ArrayList<JButton> buttonsOfHuman, Shots humanShots, Shots AIShots) {
        for (int i = 0; i < buttonsOfAI.size(); i++) {
            int finalI = i;
            buttonsOfAI.get(i).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    if (!gameOver) {
                        doShot(buttonsOfAI, shipsOfAI, humanShots,finalI);
                        if (humanShots.isShot()&& !hitTheShip(shipsOfAI,humanShots.getLastShot())) {
                            doAIShot(buttonsOfHuman, shipsOfHuman, AIShots);
                            while (!AIShots.isShot() || hitTheShip(shipsOfHuman,AIShots.getLastShot())) {
                                doAIShot(buttonsOfHuman, shipsOfHuman, AIShots);
                            }
                            if (!shipsOfHuman.checkSurvivors()) {
                                gameOver = true;
                                System.out.println("Вы проиграли!");
                            }
                            if (!shipsOfAI.checkSurvivors()) {
                                gameOver = true;
                                System.out.println("Вы выиграли!");
                            }
                        }
                    } else {
                        System.out.println("Игра закончена!");
                    }

                }
            });
        }


    }


}
