package ru.spbu.apmath.prog.project;

import com.sun.source.tree.IfTree;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static ru.spbu.apmath.prog.project.Ships.*;


public class Gui {

    static final int FIELD_SIZE = 10;
    static final int PANEL_SIZE = 600;
    final int CELL_SIZE = PANEL_SIZE / FIELD_SIZE;

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setTitle("Battle Ship");
        frame.setSize(1500, 1500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new GridBagLayout());

        // создаем поле противника
        JPanel panelOfAI = new JPanel();
        ArrayList<JButton> buttonsOfAI = new ArrayList<>();
        createField(panelOfAI, frame, 0, buttonsOfAI);

        // создаем свое игровое поле
        JPanel panelOfHuman = new JPanel();
        ArrayList<JButton> buttonsOfHuman = new ArrayList<>();
        createField(panelOfHuman, frame, 1, buttonsOfHuman);

        // панель для создания кораблей
        JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayout(4, 3));
        addOnFrame(frame, panel3, 2, 2);


        //подписи над полем
        JLabel label1 = new JLabel("Мой флот");
        addOnFrame(frame, label1, 1, 0);
        JLabel label2 = new JLabel("Флот противника");
        addOnFrame(frame, label2, 0, 0);
        label1.setHorizontalAlignment(JLabel.CENTER);
        label2.setHorizontalAlignment(JLabel.CENTER);


        //создаю кнопки меню
        JButton init = new JButton("Новая игра");
        addOnFrame(frame, init, 1, 3);
        JButton exit = new JButton("Выход");
        addOnFrame(frame, exit, 1, 4);
        JButton setRandom = new JButton("Расставить автоматически");
        addOnFrame(frame, setRandom, 0, 4);
        JButton set = new JButton("Расставить корабли");
        addOnFrame(frame, set, 0, 3);

        //ставлю слушатель, чтобы на кнопку выход можно было выйти
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        //ставлю слушатель, чтобы на кнопку новая игра все обнулялось
        init.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (JButton b : buttonsOfHuman) {
                    b.setIcon(null);
                }
            }
        });

        //расставляю рандомно созданные корабли
        setRandom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (JButton b : buttonsOfHuman) {
                    b.setIcon(null);
                }
                Ships randomShips = new Ships(FIELD_SIZE - 1);
                for (Ship ship : randomShips.getShips()) {
                    for (Cell deck : ship.getDecks()) {
                        int x = deck.getLetter();
                        int y = deck.getNumber();
                        int numberOfButton = 10 * x + y;
                        buttonsOfHuman.get(numberOfButton).setIcon(new ImageIcon("C:\\Users\\Кристина\\Desktop\\IdeaProjects\\hw1\\homework1\\src\\ru\\spbu\\apmath\\prog\\project\\картинка.png"));
                    }
                }
            }
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

                    reset.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
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
                        }
                    });

                    panel3.add(bfourDecks);
                    panel3.add(one);
                    panel3.add(edit1);
                    panel3.add(bthreeDecks);
                    panel3.add(two);
                    panel3.add(edit2);
                    panel3.add(btwoDecks);
                    panel3.add(three);
                    panel3.add(edit3);
                    panel3.add(boneDecks);
                    panel3.add(four);
                    panel3.add(edit4);

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
                                doVisible(bthreeDecks,btwoDecks,boneDecks,edit2,edit3,edit4,two,three,four, true);
                            }
                        }
                    });

                    bthreeDecks.addActionListener(new ActionListener() {
                        int amountOfClick = 0;

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            doVisible(bfourDecks,btwoDecks,boneDecks,edit1,edit3,edit4,one,three,four, false);
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
                            doVisible(bfourDecks,bthreeDecks,boneDecks,edit1,edit2,edit4,one,two,four, false);
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
                            doVisible(bfourDecks,bthreeDecks,btwoDecks,edit1,edit2,edit3,one,two,three, false);
                            if (amountOfClick < 4) {
                                amountOfClick += 1;
                                four.setText(String.valueOf(4 - amountOfClick));
                                int numberOfDecks = 1;
                                click(buttonsOfHuman, amountOfClick, numberOfDecks);

                            }
                            if (thisTypeIsPlaced(4)) {
                                doVisible(bfourDecks, bthreeDecks, btwoDecks, edit1, edit2, edit2,one, two, three, true);
                            }
                        }
                    });


                    frame.pack();
                    setRandom.setEnabled(true);
                }
            }
        });

        frame.pack();
        frame.setVisible(true);

    }

    public static void createField(JPanel panel, JFrame frame, int gridx, ArrayList<JButton> buttonsOfPanel) {
        panel.setLayout((new GridLayout(10, 10)));
        panel.setPreferredSize(new Dimension(PANEL_SIZE, PANEL_SIZE));
        panel.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        frame.add(panel, new GridBagConstraints(gridx, 2, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(1, 1, 1, 1), 0, 0));
        panel.setBackground(Color.WHITE);
        for (int i = 0; i < 100; i++) {
            buttonsOfPanel.add(new JButton());
        }
        for (JButton button : buttonsOfPanel) {
            panel.add(button);
        }
    }

    public static void addOnFrame(JFrame frame, Component component, int gridx, int gridy) {
        frame.add(component, new GridBagConstraints(gridx, gridy, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(1, 1, 1, 1), 0, 0));

    }

    public static void click(ArrayList<JButton> bs, int amountOfClick, int numberOfDecks) {
        for (int i = 0; i < bs.size(); i++) {
            int finalI = i;
            bs.get(i).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int x = finalI % 10;
                    int y = finalI / 10;
                    if (check(x, y, numberOfDecks, amountOfClick) && checkSetInRow(amountOfClick, finalI, numberOfDecks)) {
                        bs.get(finalI).setIcon(new ImageIcon("C:\\Users\\Кристина\\Desktop\\IdeaProjects\\hw1\\homework1\\src\\ru\\spbu\\apmath\\prog\\project\\картинка.png"));
                        formShip(x, y, numberOfDecks, amountOfClick);
                    }
                }

            });

        }

    }

    public static void doVisible(JButton b1,JButton b2,JButton b3,JButton b4,JButton b5, JButton b6,JLabel l1,JLabel l2,JLabel l3, Boolean bol) {
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

