package ru.spbu.apmath.prog.battleship;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static ru.spbu.apmath.prog.battleship.Gui.doAIShot;
import static ru.spbu.apmath.prog.battleship.Gui.doShot;

public class GameModel {
    private boolean gameOver;

    public GameModel(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public void game(Ships shipsOfHuman, Ships shipsOfAI, ArrayList<JButton> buttonsOfAI, ArrayList<JButton> buttonsOfHuman, Shots humanShots, Shots AIShots) {
        doShot(buttonsOfAI, shipsOfAI, humanShots);
        for (JButton bs:buttonsOfAI) {
            bs.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!gameOver) {
                        doAIShot(buttonsOfHuman, shipsOfHuman, AIShots);
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
                }
            });
        }



        }


}
