package ru.spbu.apmath.prog.battleship;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static ru.spbu.apmath.prog.battleship.Gui.*;


public class PlacementPanel extends JPanel {

    public PlacementPanel(Cells fieldOfHuman, JFrame frame, JPanel nextPanel) {
        setLayout(new GridBagLayout());
        //ставлю картинку на фон
        JLabel background = new JLabel(new ImageIcon("resources/PlacementPanelBackgroundIcon.jpg"));
        background.setLayout(new GridBagLayout());
        add(background);


        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.NORTH;



        //надпись над полем
        JLabel placementLabel = new JLabel("Расставьте свои корабли");
        placementLabel.setForeground(Color.WHITE);
        placementLabel.setFont(new Font("Helvetica Cyrillic Oblique", Font.ITALIC, 58));
        background.add(placementLabel, gbc);


        //ставлю промежуток между надписью и полем
        //background.add(Box.createRigidArea(new Dimension(0, 50)), gbc);



        GridBagConstraints gbc2 = new GridBagConstraints();
        //gbc2.anchor = GridBagConstraints.CENTER;
        gbc2.gridx = 0;
        gbc2.gridwidth = 2;
        gbc2.gridy = 1;

        //формирую поле из кнопок
        ArrayList<JButton> buttonsOfHuman = new ArrayList<>();
        JPanel panelOfField = new JPanel();
        panelOfField.setLayout((new GridLayout(10, 10)));
        panelOfField.setPreferredSize(new Dimension(500, 500));
        panelOfField.setMinimumSize(new Dimension(500, 500));
        panelOfField.setBorder(BorderFactory.createLineBorder(Color.BLUE, 3));
        panelOfField.setBackground(Color.WHITE);
        for (int i = 0; i < 100; i++) {
            buttonsOfHuman.add(new JButton());
            fieldOfHuman.addCell(i % 10, i / 10);

        }
        for (JButton button : buttonsOfHuman) {
            panelOfField.add(button);
        }
        background.add(panelOfField,gbc2);


        //gbc.anchor = GridBagConstraints.SOUTH;

        //делаю промежуток между полем и кнопками
        //background.add(Box.createRigidArea(new Dimension(0, 50)), gbc);

        // панель для создания кораблей

        ConstructionPanel mainPanelOfSet = new ConstructionPanel();
        GridBagConstraints gbc3 = new GridBagConstraints();
        gbc3.gridx = 2 ;
        gbc3.gridy = 1;
        background.add(mainPanelOfSet,gbc3);
        //mainPanelOfSet.setVisible(false);


        //создаю кнопки меню
        JPanel panelOfButton = new JPanel();
        panelOfButton.setLayout(new GridLayout(2, 4));
        panelOfButton.setOpaque(false);

        JButton initBtn = new JButton("Начать игру");
        panelOfButton.add(initBtn);

        JButton setRandomBtn = new JButton("Расставить автоматически");
        panelOfButton.add(setRandomBtn);


        JButton exitBtn = new JButton("Выход");
        panelOfButton.add(exitBtn);

        JButton setBtn = new JButton("Расставить корабли");
        panelOfButton.add(setBtn);

        GridBagConstraints gbc4 = new GridBagConstraints();
        gbc4.anchor = GridBagConstraints.PAGE_END;
        gbc4.gridwidth = 2;
        gbc4.gridx = 1;
        gbc4.gridy = 2;
        background.add(panelOfButton, gbc4);






        //ставлю слушатели на кнопки

        exitBtn.addActionListener(e -> System.exit(0)); //осуществляю выход на кнопку выход

        setRandomBtn.addActionListener(e -> {          //расставляю рандомно созданные корабли
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

        initBtn.addActionListener(e -> {            //ставлю слушатель, чтобы началась игра(переход на следующую панель)
            DialogWindow.infoBox("Игра начинаeтся...", "Information");
            setVisible(false);
            frame.add(nextPanel);

        });

       /* setBtn.addActionListener(new ActionListener() {
            int amount = 0;

            @Override
            public void actionPerformed(ActionEvent e) {

                if (amount == 0) {
                    amount += 1;


                    reset.addActionListener(e15 -> {
                        for (JButton b : buttonsOfHuman) {
                            b.setIcon(null);
                            one.setText("1");
                            two.setText("2");
                            three.setText("3");
                            four.setText("4");
                            doVisible(threeDecksBtn, twoDecksBtn, oneDecksBtn, edit2, edit3, edit4, two, three, four, true);
                            fourDecksBtn.setVisible(true);
                            one.setVisible(true);
                            edit1.setVisible(true);
                        }
                    });


                    fourDecksBtn.addActionListener(new ActionListener() {
                        int amountOfClick = 0;

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            doVisible(threeDecksBtn, twoDecksBtn, oneDecksBtn, edit2, edit3, edit4, two, three, four, false);
                            if (amountOfClick < 1) {
                                amountOfClick += 1;
                                one.setText(String.valueOf(1 - amountOfClick));
                                int numberOfDecks = 4;
                                click(buttonsOfHuman, amountOfClick, numberOfDecks);
                            }
                            if (shipsOfHuman.thisTypeIsPlaced(1)) {
                                doVisible(threeDecksBtn, twoDecksBtn, oneDecksBtn, edit2, edit3, edit4, two, three, four, true);
                            }
                        }
                    });

                    threeDecksBtn.addActionListener(new ActionListener() {
                        int amountOfClick = 0;

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            doVisible(fourDecksBtn, twoDecksBtn, oneDecksBtn, edit1, edit3, edit4, one, three, four, false);
                            if (amountOfClick < 2) {
                                amountOfClick += 1;
                                two.setText(String.valueOf(2 - amountOfClick));
                                int numberOfDecks = 3;
                                click(buttonsOfHuman, amountOfClick, numberOfDecks);

                            }
                            if (shipsOfHuman.thisTypeIsPlaced(2)) {
                                doVisible(fourDecksBtn, twoDecksBtn, oneDecksBtn, edit1, edit3, edit4, one, three, four, true);
                            }
                        }
                    });


                    twoDecksBtn.addActionListener(new ActionListener() {
                        int amountOfClick = 0;

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            doVisible(fourDecksBtn, threeDecksBtn, oneDecksBtn, edit1, edit2, edit4, one, two, four, false);
                            if (amountOfClick < 3) {
                                amountOfClick += 1;
                                three.setText(String.valueOf(3 - amountOfClick));
                                int numberOfDecks = 2;
                                click(buttonsOfHuman, amountOfClick, numberOfDecks);

                            }
                            if (shipsOfHuman.thisTypeIsPlaced(3)) {
                                doVisible(fourDecksBtn, threeDecksBtn, oneDecksBtn, edit1, edit2, edit4, one, two, four, true);
                            }
                        }

                    });

                    oneDecksBtn.addActionListener(new ActionListener() {
                        int amountOfClick = 0;


                        @Override
                        public void actionPerformed(ActionEvent e) {
                            doVisible(fourDecksBtn, threeDecksBtn, twoDecksBtn, edit1, edit2, edit3, one, two, three, false);
                            if (amountOfClick < 4) {
                                amountOfClick += 1;
                                four.setText(String.valueOf(4 - amountOfClick));
                                int numberOfDecks = 1;
                                click(buttonsOfHuman, amountOfClick, numberOfDecks);

                            }
                            if (shipsOfHuman.thisTypeIsPlaced(4)) {
                                doVisible(fourDecksBtn, threeDecksBtn, twoDecksBtn, edit1, edit2, edit2, one, two, three, true);
                            }
                        }
                    });

                    edit1.addActionListener(e1 -> click2(buttonsOfHuman));

                    edit2.addActionListener(e12 -> click2(buttonsOfHuman));

                    edit3.addActionListener(e13 -> click2(buttonsOfHuman));

                    edit4.addActionListener(e14 -> click2(buttonsOfHuman));

                    frame.pack();
                    //setRandom.setEnabled(true);
                }
            }
        });*/
    }


}
