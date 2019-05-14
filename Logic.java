package ru.spbu.apmath.prog.project;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Logic {
    public static void click(ArrayList<JButton> bs, ArrayList<Cell> decks, int numberOfDecks) {

        for (int i = 0; i < bs.size(); i++) {
            int finalI = i;
            bs.get(i).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int x = finalI % 10;
                    int y = finalI / 10;

                }
                    /*if ((decks.size() < numberOfDecks) && (bs.get(finalI).getIcon() == null)) {
                        if (checkSetInRow(decks, finalI)) {
                            bs.get(finalI).setIcon(new ImageIcon("C:\\Users\\Кристина\\Desktop\\IdeaProjects\\hw1\\homework1\\src\\ru\\spbu\\apmath\\prog\\project\\картинка.png"));
                            int x = finalI % 10;
                            int y = finalI / 10;
                            Cell deck = new Cell(x, y);
                            decks.add(deck);
                        }
                    }
                    if(decks.size()==numberOfDecks){
                    System.out.println(decks);
                    }

                }*/
            });

        }

    }






    public static boolean checkSetInRow(ArrayList<Cell> decks, int finalI) {
        if (decks.isEmpty()) {
            return true;
        }
        if (decks.size() == 1) {
            int x = decks.get(0).getLetter();
            int y = decks.get(0).getNumber();
            if ((finalI == ((y + 1) * 10 + x)) || (finalI == ((y - 1) * 10 + x)) || (finalI == (y * 10 + x + 1)) || (finalI == (y * 10 + x - 1))) {
                return true;
            }

        }
        if (decks.size() == 2) {
            int x = decks.get(0).getLetter();
            int y = decks.get(0).getNumber();
            int x1 = decks.get(1).getLetter();
            int y1 = decks.get(1).getNumber();
            if (((x - x1) == 0) && ((y - y1) > 0)) {
                if ((finalI == ((y + 1) * 10 + x)) || (finalI == ((y1 - 1) * 10 + x))) {
                    return true;
                }
            }

            if (((x - x1) == 0) && ((y - y1) < 0)) {
                if ((finalI == ((y - 1) * 10 + x)) || (finalI == ((y1 + 1) * 10 + x))) {
                    return true;
                }
            }

            if (((y - y1) == 0) && ((x - x1) > 0)) {
                if ((finalI == (y * 10 + (x + 1))) || (finalI == (y * 10 + (x1 - 1)))) {
                    return true;
                }
            }

            if (((y - y1) == 0) && ((x - x1) < 0)) {
                if ((finalI == (y * 10 + (x - 1))) || (finalI == (y * 10 + (x1 + 1)))) {
                    return true;
                }
            }
        }
        if (decks.size() == 3) {
            int x = decks.get(0).getLetter();
            int y = decks.get(0).getNumber();
            int x1 = decks.get(1).getLetter();
            int y1 = decks.get(1).getNumber();
            int x2 = decks.get(2).getLetter();
            int y2 = decks.get(2).getNumber();
            if (((x - x1) == 0) && ((y - y2) == 2)) {
                if ((finalI == ((y + 1) * 10 + x)) || (finalI == ((y2 - 1) * 10 + x))) {
                    return true;
                }
            }

            if (((x - x1) == 0) && ((y - y2) == -2)) {
                if ((finalI == ((y - 1) * 10 + x)) || (finalI == ((y2 + 1) * 10 + x))) {
                    return true;
                }
            }

            if (((x - x1) == 0) && ((y1 - y2) == 2)) {
                if ((finalI == ((y1 + 1) * 10 + x)) || (finalI == ((y2 - 1) * 10 + x))) {
                    return true;
                }
            }

            if (((x - x1) == 0) && ((y1 - y2) == -2)) {
                if ((finalI == ((y1 - 1) * 10 + x)) || (finalI == ((y2 + 1) * 10 + x))) {
                    return true;
                }
            }

            if (((y - y1) == 0) && ((x - x2) == 2)) {
                if ((finalI == (y * 10 + (x + 1))) || (finalI == (y * 10 + (x2 - 1)))) {
                    return true;
                }
            }

            if (((y - y1) == 0) && ((x - x2) == -2)) {
                if ((finalI == (y * 10 + (x - 1))) || (finalI == (y * 10 + (x2 + 1)))) {
                    return true;
                }
            }

            if (((y - y1) == 0) && ((x1 - x2) == 2)) {
                if ((finalI == (y * 10 + (x1 + 1))) || (finalI == (y * 10 + (x2 - 1)))) {
                    return true;
                }
            }

            if (((y - y1) == 0) && ((x1 - x2) == -2)) {
                if ((finalI == (y * 10 + (x1 - 1))) || (finalI == (y * 10 + (x2 + 1)))) {
                    return true;
                }
            }
        }
        return false;
    }
}

