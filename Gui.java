package ru.spbu.apmath.prog.project;

import com.sun.source.tree.IfTree;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static ru.spbu.apmath.prog.project.Logic.click;


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
       ArrayList<JButton> buttonsOfAI= new ArrayList<>();
       createField(panelOfAI,frame,0,buttonsOfAI);

        // создаем свое игровое поле
        JPanel panelOfHuman = new JPanel();
        ArrayList<JButton> buttonsOfHuman = new ArrayList<>();
        createField(panelOfHuman,frame,1,buttonsOfHuman);

        // панель для создания кораблей
        JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayout(4, 3));
        addOnFrame(frame,panel3,2,2);


        //подписи над полем
        JLabel label1 = new JLabel("Мой флот");
        addOnFrame(frame,label1,1,0);
        JLabel label2 = new JLabel("Флот противника");
        addOnFrame(frame,label2,0,0);
        label1.setHorizontalAlignment(JLabel.CENTER);
        label2.setHorizontalAlignment(JLabel.CENTER);


        //создаю кнопки меню
        JButton init = new JButton("Новая игра");
        addOnFrame(frame,init,1,3);
        JButton exit = new JButton("Выход");
        addOnFrame(frame,exit,1,4);
        JButton setRandom = new JButton("Расставить автоматически");
        addOnFrame(frame,setRandom,0,4);
        JButton set = new JButton("Расставить корабли");
        addOnFrame(frame,set,0,3);

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


        ArrayList<Cell> fourDecks = new ArrayList<>();
        ArrayList<Cell> threeDecks = new ArrayList<>();
        ArrayList<Cell> twoDecks = new ArrayList<>();
        ArrayList<Cell> oneDecks = new ArrayList<>();

        set.addActionListener(new ActionListener() {0
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
                    JButton ok1 = new JButton("ok");
                    JButton ok2 = new JButton("ok");
                    JButton ok3 = new JButton("ok");
                    JButton ok4 = new JButton("ok");

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
                            }
                        }
                    });

                    panel3.add(bfourDecks);
                    panel3.add(one);
                    panel3.add(ok1);
                    panel3.add(bthreeDecks);
                    panel3.add(two);
                    panel3.add(ok2);
                    panel3.add(btwoDecks);
                    panel3.add(three);
                    panel3.add(ok3);
                    panel3.add(boneDecks);
                    panel3.add(four);
                    panel3.add(ok4);

                    bfourDecks.addActionListener(new ActionListener() {
                        int amountOfClick = 0;

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (amountOfClick < 1) {
                                amountOfClick += 1;
                                one.setText(String.valueOf(1 - amountOfClick));
                                int numberOfDecks = 4;
                               click(buttonsOfHuman, fourDecks, numberOfDecks);
                                fourDecks.clear();
                            }
                        }
                    });

                    /*ok1.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            new Ships(fourDecks);
                            System.out.println(fourDecks);

                        }
                    });*/

                    bthreeDecks.addActionListener(new ActionListener() {
                        int amountOfClick = 0;


                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (amountOfClick < 2) {
                                amountOfClick += 1;
                                two.setText(String.valueOf(2 - amountOfClick));
                                int numberOfDecks = 3;
                                click(buttonsOfHuman, threeDecks, numberOfDecks);
                                threeDecks.clear();

                         /*       ok2.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        new Ships(threeDecks);
                                        System.out.println(threeDecks);
                                         }
                                });
                         */   }

                        }
                    });


                    btwoDecks.addActionListener(new ActionListener() {
                        int amountOfClick = 0;


                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (amountOfClick < 3) {
                                amountOfClick += 1;
                                three.setText(String.valueOf(3 - amountOfClick));
                                int numberOfDecks = 2;
                                click(buttonsOfHuman, twoDecks, numberOfDecks);
                                twoDecks.clear();
                            }
                        }

                    });

                    /*ok3.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            new Ships(twoDecks);
                            System.out.println(twoDecks);

                        }
                    });

*/                    boneDecks.addActionListener(new ActionListener() {
                        int amountOfClick = 0;


                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (amountOfClick < 4) {
                                amountOfClick += 1;
                                four.setText(String.valueOf(4 - amountOfClick));
                                int numberOfDecks = 1;
                                click(buttonsOfHuman, oneDecks, numberOfDecks);
                                oneDecks.clear();
                            }
                        }
                    });

                    /*ok4.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            new Ships(oneDecks);
                            System.out.println(oneDecks);

                        }
                    });
*/
                    frame.pack();
                    setRandom.setEnabled(true);
                }
            }
        });

        frame.pack();
        frame.setVisible(true);

    }

    public static void createField(JPanel panel, JFrame frame, int gridx, ArrayList<JButton> buttonsOfPanel ){
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

    public static void addOnFrame(JFrame frame, Component component, int gridx, int gridy){
        frame.add(component, new GridBagConstraints(gridx, gridy, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(1, 1, 1, 1), 0, 0));

    }
}

