package ru.spbu.apmath.prog.battleship;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import static ru.spbu.apmath.prog.battleship.Ships.*;
import static ru.spbu.apmath.prog.battleship.Shots.checkShot;


public class Gui {

    private static final int FIELD_SIZE = 10;
    private static final int PANEL_SIZE = 600;
    private static ImageIcon shipIcon = new ImageIcon("resources/ship.png");
    private static ImageIcon bangIcon = new ImageIcon("resources/bang.png");
    private static ImageIcon crossIcon = new ImageIcon("resources/cross.png");
    private static Ships randomShips;
    private static Cells fieldOfAI = new Cells();
    private static Cells fieldOfHuman = new Cells();

    public static void main(String[] args) {

        JFrame frame = new JFrame();
        frame.setTitle("Battle Ship");
        frame.setSize(1500, 1500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        //подписи над полем
        JPanel panelOfLabel = new JPanel();
        frame.add(panelOfLabel);
        panelOfLabel.setLayout(new GridLayout(1, 2));
        JLabel label2 = new JLabel("Флот противника");
        panelOfLabel.add(label2);
        JLabel label1 = new JLabel("Мой флот");
        panelOfLabel.add(label1);
        label1.setFont(new Font(null, Font.BOLD, 20));
        label2.setFont(new Font(null, Font.BOLD, 20));
        label1.setHorizontalAlignment(JLabel.CENTER);
        label2.setHorizontalAlignment(JLabel.CENTER);

        //создание игровых полей
        JPanel panelOfField = new JPanel();
        frame.add(panelOfField);
        ArrayList<JButton> buttonsOfAI = new ArrayList<>();
        createField(panelOfField, buttonsOfAI, fieldOfAI);
        ArrayList<JButton> buttonsOfHuman = new ArrayList<>();
        createField(panelOfField, buttonsOfHuman, fieldOfHuman);

        // панель для создания кораблей
        JPanel panelOfSetShips = new JPanel();
        panelOfSetShips.setLayout(new GridLayout(4, 3));
        frame.add(panelOfSetShips);


        //создаю кнопки меню
        JPanel panelOfButton = new JPanel();
        panelOfButton.setLayout(new GridLayout(2, 2));
        frame.add(panelOfButton);
        JButton init = new JButton("Начать игру");
        panelOfButton.add(init);
        JButton setRandom = new JButton("Расставить автоматически");
        panelOfButton.add(setRandom);
        JButton exit = new JButton("Выход");
        panelOfButton.add(exit);
        JButton set = new JButton("Расставить корабли");
        panelOfButton.add(set);

        //ставлю слушатель, чтобы на кнопку выход можно было выйти
        exit.addActionListener(e -> System.exit(0));


        //расставляю рандомно созданные корабли
        setRandom.addActionListener(e -> {
            for (JButton b : buttonsOfHuman) {
                b.setIcon(null);
            }
            randomShips = new Ships(FIELD_SIZE - 1);
            for (Ship ship : randomShips.getShips()) {
                for (Cell deck : ship.getDecks()) {
                    int x = deck.getLetter();
                    int y = deck.getNumber();
                    int numberOfButton = 10 * y + x;
                    buttonsOfHuman.get(numberOfButton).setIcon(shipIcon);
                }
            }
        });


        //ставлю слушатель, чтобы началась игра
        init.addActionListener(e -> {
            DialogWindow.infoBox("Игра началась!", "Information");
            Shots humanShots = new Shots();
            Shots AIShots = new Shots();
            for (JButton b : buttonsOfAI) {
                b.setIcon(null);
            }
            Ships ShipsOfAI = new Ships(FIELD_SIZE - 1);
            panelOfSetShips.setVisible(false);
            GameModel gameModel = new GameModel(false);
            gameModel.game(randomShips, ShipsOfAI, buttonsOfAI, buttonsOfHuman, humanShots, AIShots);

        });

        set.addActionListener(new ActionListener() {
            int amount = 0;

            @Override
            public void actionPerformed(ActionEvent e) {


                if (amount == 0) {
                    JButton bfourDecks = new JButton("Четырехпалубники");
                    JButton bthreeDecks = new JButton("Трехпалубники");
                    JButton btwoDecks = new JButton("Двухпалубники");
                    JButton boneDecks = new JButton("Однопалубники");
                    amount += 1;
                    JLabel one = new JLabel("1");
                    JLabel two = new JLabel("2");
                    JLabel three = new JLabel("3");
                    JLabel four = new JLabel("4");
                    one.setHorizontalAlignment(JLabel.CENTER);
                    two.setHorizontalAlignment(JLabel.CENTER);
                    three.setHorizontalAlignment(JLabel.CENTER);
                    four.setHorizontalAlignment(JLabel.CENTER);
                    JButton edit1 = new JButton("редактировать");
                    JButton edit2 = new JButton("редактировать");
                    JButton edit3 = new JButton("редактировать");
                    JButton edit4 = new JButton("редактировать");

                    JPanel panel4 = new JPanel();
                    frame.add(panel4, new GridBagConstraints(2, 3, 2, 1, 1, 1,
                            GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(1, 1, 1, 1), 0, 0));
                    JButton save = new JButton("Сохранить");
                    JButton reset = new JButton("Сбросить");
                    panel4.add(save);
                    panel4.add(reset);

                    reset.addActionListener(e15 -> {
                        for (JButton b : buttonsOfHuman) {
                            b.setIcon(null);
                            one.setText("1");
                            two.setText("2");
                            three.setText("3");
                            four.setText("4");
                            doVisible(bthreeDecks, btwoDecks, boneDecks, edit2, edit3, edit4, two, three, four, true);
                            bfourDecks.setVisible(true);
                            one.setVisible(true);
                            edit1.setVisible(true);
                        }
                    });

                    panelOfSetShips.add(bfourDecks);
                    panelOfSetShips.add(one);
                    panelOfSetShips.add(edit1);
                    panelOfSetShips.add(bthreeDecks);
                    panelOfSetShips.add(two);
                    panelOfSetShips.add(edit2);
                    panelOfSetShips.add(btwoDecks);
                    panelOfSetShips.add(three);
                    panelOfSetShips.add(edit3);
                    panelOfSetShips.add(boneDecks);
                    panelOfSetShips.add(four);
                    panelOfSetShips.add(edit4);

                    bfourDecks.addActionListener(new ActionListener() {
                        int amountOfClick = 0;

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            doVisible(bthreeDecks, btwoDecks, boneDecks, edit2, edit3, edit4, two, three, four, false);
                            if (amountOfClick < 1) {
                                amountOfClick += 1;
                                one.setText(String.valueOf(1 - amountOfClick));
                                int numberOfDecks = 4;
                                click(buttonsOfHuman, amountOfClick, numberOfDecks);
                            }
                            if (thisTypeIsPlaced(1)) {
                                doVisible(bthreeDecks, btwoDecks, boneDecks, edit2, edit3, edit4, two, three, four, true);
                            }
                        }
                    });

                    bthreeDecks.addActionListener(new ActionListener() {
                        int amountOfClick = 0;

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            doVisible(bfourDecks, btwoDecks, boneDecks, edit1, edit3, edit4, one, three, four, false);
                            if (amountOfClick < 2) {
                                amountOfClick += 1;
                                two.setText(String.valueOf(2 - amountOfClick));
                                int numberOfDecks = 3;
                                click(buttonsOfHuman, amountOfClick, numberOfDecks);

                            }
                            if (thisTypeIsPlaced(2)) {
                                doVisible(bfourDecks, btwoDecks, boneDecks, edit1, edit3, edit4, one, three, four, true);
                            }
                        }
                    });


                    btwoDecks.addActionListener(new ActionListener() {
                        int amountOfClick = 0;

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            doVisible(bfourDecks, bthreeDecks, boneDecks, edit1, edit2, edit4, one, two, four, false);
                            if (amountOfClick < 3) {
                                amountOfClick += 1;
                                three.setText(String.valueOf(3 - amountOfClick));
                                int numberOfDecks = 2;
                                click(buttonsOfHuman, amountOfClick, numberOfDecks);

                            }
                            if (thisTypeIsPlaced(3)) {
                                doVisible(bfourDecks, bthreeDecks, boneDecks, edit1, edit2, edit4, one, two, four, true);
                            }
                        }

                    });

                    boneDecks.addActionListener(new ActionListener() {
                        int amountOfClick = 0;


                        @Override
                        public void actionPerformed(ActionEvent e) {
                            doVisible(bfourDecks, bthreeDecks, btwoDecks, edit1, edit2, edit3, one, two, three, false);
                            if (amountOfClick < 4) {
                                amountOfClick += 1;
                                four.setText(String.valueOf(4 - amountOfClick));
                                int numberOfDecks = 1;
                                click(buttonsOfHuman, amountOfClick, numberOfDecks);

                            }
                            if (thisTypeIsPlaced(4)) {
                                doVisible(bfourDecks, bthreeDecks, btwoDecks, edit1, edit2, edit2, one, two, three, true);
                            }
                        }
                    });

                    edit1.addActionListener(e1 -> click2(buttonsOfHuman));

                    edit2.addActionListener(e12 -> click2(buttonsOfHuman));

                    edit3.addActionListener(e13 -> click2(buttonsOfHuman));

                    edit4.addActionListener(e14 -> click2(buttonsOfHuman));

                    frame.pack();
                    setRandom.setEnabled(true);
                }
            }
        });


        frame.pack();
        frame.setVisible(true);

    }

    private static void createField(JPanel panel1, ArrayList<JButton> buttonsOfPanel, Cells fieldOfGamer) {

        JPanel panel = new JPanel();
        panel.setLayout((new GridLayout(10, 10)));
        panel.setPreferredSize(new Dimension(PANEL_SIZE, PANEL_SIZE));
        panel.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        panel1.add(panel);
        panel.setBackground(Color.WHITE);
        for (int i = 0; i < 100; i++) {
            buttonsOfPanel.add(new JButton());
            fieldOfGamer.addCell(i % 10, i / 10);

        }
        for (JButton button : buttonsOfPanel) panel.add(button);
    }


    private static void click(ArrayList<JButton> bs, int amountOfClick, int numberOfDecks) {
        for (int i = 0; i < bs.size(); i++) {
            int finalI = i;
            bs.get(i).addActionListener(e -> {
                int x = finalI % 10;
                int y = finalI / 10;
                if (check(x, y, numberOfDecks, amountOfClick) && checkSetInRow(amountOfClick, finalI, numberOfDecks)) {
                    bs.get(finalI).setIcon(shipIcon);
                    formShip(x, y, numberOfDecks, amountOfClick);
                }

            });

        }

    }

    static void doAIShot(ArrayList<JButton> bs, Ships shipsOfHuman, Shots shots) {
        Cell shot = shots.randomShots();
        int i = 10 * shot.getNumber() + shot.getLetter();
        if (!shots.hitSamePlace(shot.getLetter(), shot.getNumber())) {
            shots.setLastShot(shot);
            String returnCheck = checkShot(shot, shipsOfHuman, shots, fieldOfHuman);
            if (returnCheck.equals("bangIcon")) {
                bs.get(i).setIcon(bangIcon);
                fieldOfHuman.setStateCell(shot.getLetter(), shot.getNumber());
                shots.setShot(true);
            } else if (returnCheck.equals("bangIconAround")) {
                for (Integer b : shots.getIndexOfButtonOfCurrentShip(fieldOfHuman, shots)) {
                    bs.get(i).setIcon(bangIcon);
                    shots.setShot(true);
                    bs.get(b).setIcon(crossIcon);
                }
            } else {
                bs.get(i).setIcon(crossIcon);
                shots.setShot(true);
            }
        } else {
            System.out.println("уже стреляли");
            shots.setShot(false);
        }
    }


    static void doShot(ArrayList<JButton> bs, Ships ShipsOfAI, Shots shots, int finalI) {
        int x = finalI % 10;
        int y = finalI / 10;
        Cell shot = new Cell(x, y);
        if (!shots.hitSamePlace(x, y)) {
            shots.setLastShot(shot);
            String returnCheck = checkShot(shot, ShipsOfAI, shots, fieldOfAI);
            if (returnCheck.equals("bangIcon")) {
                bs.get(finalI).setIcon(bangIcon);
                shots.setShot(true);
            } else if (returnCheck.equals("bangIconAround")) {
                for (Integer b : shots.getIndexOfButtonOfCurrentShip(fieldOfAI, shots)) {
                    bs.get(finalI).setIcon(bangIcon);
                    shots.setShot(true);
                    bs.get(b).setIcon(crossIcon);
                }
            } else {
                bs.get(finalI).setIcon(crossIcon);
                shot.setState("busy");
                shots.setShot(true);
            }
        } else {
            System.out.println("уже стреляли");
            shots.setShot(false);
        }

    }


    private static void click2(ArrayList<JButton> bs) {
        for (int i = 0; i < bs.size(); i++) {
            int finalI = i;
            bs.get(i).addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent event) {
                    if (event.getButton() == MouseEvent.BUTTON3) {
                        if (bs.get(finalI).getIcon() != null) {
                            bs.get(finalI).setIcon(null);
                        }
                    }
                }

                @Override
                public void mousePressed(MouseEvent e) {

                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {

                }

                @Override
                public void mouseExited(MouseEvent e) {

                }

            });

        }

    }

    private static void doVisible(JButton b1, JButton b2, JButton b3, JButton b4, JButton b5, JButton b6, JLabel l1, JLabel l2, JLabel l3, Boolean bol) {
        b1.setVisible(bol);
        b2.setVisible(bol);
        b3.setVisible(bol);
        b4.setVisible(bol);
        b5.setVisible(bol);
        b6.setVisible(bol);
        l1.setVisible(bol);
        l2.setVisible(bol);
        l3.setVisible(bol);
    }


}


