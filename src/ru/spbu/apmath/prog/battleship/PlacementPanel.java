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
        background.add(Box.createRigidArea(new Dimension(0, 80)), gbc);

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        //формирую поле из кнопок
        ArrayList<JButton> buttonsOfHuman = new ArrayList<>();
        JPanel panelOfField = new JPanel();
        panelOfField.setLayout((new GridLayout(10, 10)));
        panelOfField.setPreferredSize(new Dimension(500, 500));
        panelOfField.setBorder(BorderFactory.createLineBorder(Color.BLUE, 3));
        panelOfField.setBackground(Color.WHITE);
        for (int i = 0; i < 100; i++) {
            buttonsOfHuman.add(new JButton());
            fieldOfHuman.addCell(i % 10, i / 10);

        }
        for (JButton button : buttonsOfHuman) {
            panelOfField.add(button);
        }
        background.add(panelOfField,gbc);

        //JPanel middlePanel = new JPanel();
        //middlePanel.setLayout(new GridLayout(1, 2));
        //middlePanel.setOpaque(false);
        //middlePanel.add(panelOfField);
        //middlePanel.add(mainPanelOfSet);
        //background.add(middlePanel, gbc);
        //mainPanelOfSet.setVisible(false);

        gbc.anchor = GridBagConstraints.SOUTH;

        //делаю промежуток между полем и кнопками
        background.add(Box.createRigidArea(new Dimension(0, 80)), gbc);


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

        background.add(panelOfButton, gbc);

        // панель для создания кораблей

        JPanel panelOfSetShips = new JPanel();
        panelOfSetShips.setLayout(new GridLayout(8, 3));
        panelOfSetShips.setOpaque(false);
        JButton fourDecksBtn = new JButton("Четырехпалубники");
        JButton threeDecksBtn = new JButton("Трехпалубники");
        JButton twoDecksBtn = new JButton("Двухпалубники");
        JButton oneDecksBtn = new JButton("Однопалубники");
        JLabel one = new JLabel("1");
        JLabel two = new JLabel("2");
        JLabel three = new JLabel("3");
        JLabel four = new JLabel("4");
        one.setForeground(Color.WHITE);
        two.setForeground(Color.WHITE);
        three.setForeground(Color.WHITE);
        four.setForeground(Color.WHITE);
        one.setHorizontalAlignment(JLabel.CENTER);
        two.setHorizontalAlignment(JLabel.CENTER);
        three.setHorizontalAlignment(JLabel.CENTER);
        four.setHorizontalAlignment(JLabel.CENTER);
        JButton edit1 = new JButton("редактировать");
        JButton edit2 = new JButton("редактировать");
        JButton edit3 = new JButton("редактировать");
        JButton edit4 = new JButton("редактировать");
        panelOfSetShips.add(fourDecksBtn);
        panelOfSetShips.add(one);
        panelOfSetShips.add(edit1);
        panelOfSetShips.add(Box.createRigidArea(new Dimension(0, 3)));
        panelOfSetShips.add(Box.createRigidArea(new Dimension(0, 3)));
        panelOfSetShips.add(Box.createRigidArea(new Dimension(0, 3)));
        panelOfSetShips.add(threeDecksBtn);
        panelOfSetShips.add(two);
        panelOfSetShips.add(edit2);
        panelOfSetShips.add(Box.createRigidArea(new Dimension(0, 3)));
        panelOfSetShips.add(Box.createRigidArea(new Dimension(0, 3)));
        panelOfSetShips.add(Box.createRigidArea(new Dimension(0, 3)));
        panelOfSetShips.add(twoDecksBtn);
        panelOfSetShips.add(three);
        panelOfSetShips.add(edit3);
        panelOfSetShips.add(Box.createRigidArea(new Dimension(0, 3)));
        panelOfSetShips.add(Box.createRigidArea(new Dimension(0, 3)));
        panelOfSetShips.add(Box.createRigidArea(new Dimension(0, 3)));
        panelOfSetShips.add(oneDecksBtn);
        panelOfSetShips.add(four);
        panelOfSetShips.add(edit4);

        JPanel miniPanelOfButton = new JPanel();
        miniPanelOfButton.setOpaque(false);
        JButton save = new JButton("Сохранить");
        JButton reset = new JButton("Сбросить");
        miniPanelOfButton.add(save);
        miniPanelOfButton.add(reset);

        JPanel mainPanelOfSet = new JPanel();
        mainPanelOfSet.setLayout(new GridLayout(2,1));
        mainPanelOfSet.setOpaque(false);
        mainPanelOfSet.add(panelOfSetShips);
        mainPanelOfSet.add(Box.createRigidArea(new Dimension(0,10)));
        mainPanelOfSet.add(miniPanelOfButton);

        gbc.gridwidth = 2;
        background.add(mainPanelOfSet,gbc);
        //mainPanelOfSet.setVisible(false);
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
            DialogWindow.infoBox("Игра началась!", "Information");
            setVisible(false);
            frame.add(nextPanel);

        });

        setBtn.addActionListener(new ActionListener() {
            int amount = 0;

            @Override
            public void actionPerformed(ActionEvent e) {

                if (amount == 0) {
                    amount += 1;


  /*                  reset.addActionListener(e15 -> {
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
*/

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
        });
    }


}
