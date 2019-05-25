package ru.spbu.apmath.prog.battleship;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import static ru.spbu.apmath.prog.battleship.Shots.checkShot;


public class Gui {

    public static final int FIELD_SIZE = 10;
    public static final int PANEL_SIZE = 600;
    static ImageIcon shipIcon = new ImageIcon("resources/ship.png");
    private static ImageIcon bangIcon = new ImageIcon("resources/bang.png");
    private static ImageIcon crossIcon = new ImageIcon("resources/cross.png");
    static Ships randomShips;
    static Ships shipsOfHuman = new Ships() ;
    private static Cells fieldOfAI = new Cells();
    private static Cells fieldOfHuman = new Cells();
    private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public static int sizeWidth = 1000;
    public static int sizeHeight = 1000;
    public static int locationX = (screenSize.width - sizeWidth) / 2;
    public static int locationY = (screenSize.height - sizeHeight) / 2;
    public static ArrayList<JButton> buttonsOfHuman;

    public static void main(String[] args) {

        JFrame frame = new JFrame();
        frame.setTitle("Battle Ship");
        frame.setBounds(locationX, locationY, sizeWidth, sizeHeight);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));

        JLabel background = new JLabel();
        background.setIcon(new ImageIcon("resources/PlacementPanelBackgroundIcon.jpg"));
        mainPanel.add(background);

        PlacementPanel placementPanel = new PlacementPanel(fieldOfHuman,frame,mainPanel);
        StartPanel startPanel = new StartPanel(placementPanel,frame);
        frame.add(startPanel);


        JLabel battleShipLabel = new JLabel("Battle Ship");
        battleShipLabel.setForeground(Color.WHITE);
        battleShipLabel.setFont(new Font("Courier New", Font.ITALIC, 100));


        //подписи над полем

        JLabel label2 = new JLabel("Флот противника");
        JLabel label1 = new JLabel("Мой флот");
        label1.setForeground(Color.WHITE);
        label2.setForeground(Color.WHITE);
        label1.setFont(new Font(null, Font.BOLD, 20));
        label2.setFont(new Font(null, Font.BOLD, 20));
        label1.setHorizontalAlignment(JLabel.CENTER);
        label2.setHorizontalAlignment(JLabel.CENTER);

        //создание игровых полей
        JPanel panelOfAI = new JPanel();
        panelOfAI.setOpaque(false);
        JPanel panelOfHuman = new JPanel();
        panelOfHuman.setOpaque(false);
        panelOfAI.add(label2);
        panelOfHuman.add(label1);
        ArrayList<JButton> buttonsOfAI = new ArrayList<>();
        createField(panelOfAI, buttonsOfAI, fieldOfAI,500);
        ArrayList<JButton> buttonsOfHuman = new ArrayList<>();
        createField(panelOfHuman, buttonsOfHuman, fieldOfHuman,500);

        background.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.NORTH;

        JPanel panelOfField = new JPanel();
        panelOfField.setLayout(new GridLayout(1,2));
        panelOfField.add(panelOfAI);
        panelOfField.add(panelOfHuman);
        panelOfField.setOpaque(false);
        background.add(battleShipLabel,gbc);
        gbc.anchor = GridBagConstraints.CENTER;
        background.add(panelOfField,gbc);



        /*//ставлю слушатель, чтобы на кнопку выход можно было выйти
        exit.addActionListener(e -> System.exit(0));



*/

        /*//ставлю слушатель, чтобы началась игра
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
            if (!shipsOfHuman.getShips().isEmpty()) {
                gameModel.game(shipsOfHuman, ShipsOfAI, buttonsOfAI, buttonsOfHuman, humanShots, AIShots);
            } else {
                gameModel.game(randomShips, ShipsOfAI, buttonsOfAI, buttonsOfHuman, humanShots, AIShots);
            }

        });


*/

        frame.pack();
        frame.setVisible(true);

    }

    static void createField(JPanel panel1, ArrayList<JButton> buttonsOfPanel, Cells fieldOfGamer, int size) {

        JPanel panel = new JPanel();
        panel.setLayout((new GridLayout(10, 10)));
        panel.setPreferredSize(new Dimension(size, size));
        panel.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        panel1.add(panel);
        panel.setBackground(Color.WHITE);
        for (int i = 0; i < 100; i++) {
            buttonsOfPanel.add(new JButton());
            fieldOfGamer.addCell(i % 10, i / 10);

        }
        for (JButton button : buttonsOfPanel) panel.add(button);
    }


    static void click(ArrayList<JButton> bs, int amountOfClick, int numberOfDecks) {
        for (int i = 0; i < bs.size(); i++) {
            int finalI = i;
            bs.get(i).addActionListener(e -> {
                int x = finalI % 10;
                int y = finalI / 10;
                if (shipsOfHuman.check(x, y, numberOfDecks, amountOfClick) && shipsOfHuman.checkSetInRow(amountOfClick, finalI, numberOfDecks)) {
                    bs.get(finalI).setIcon(shipIcon);
                    shipsOfHuman.formShip(x, y, numberOfDecks, amountOfClick);
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
                shots.addInInjuredShip(shot);
                fieldOfHuman.setStateCell(shot.getLetter(), shot.getNumber());
                shots.setShot(false);
            } else if (returnCheck.equals("bangIconAround")) {
                for (Integer b : shots.getIndexOfButtonOfCurrentShip(fieldOfHuman, shots)) {
                    bs.get(i).setIcon(bangIcon);
                    shots.setShot(false);
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

    static void doAIShot2(ArrayList<JButton> bs, Ships shipsOfHuman, Shots shots) {
        Cell shot;
        if (shots.getInjuredShip().isEmpty()) {
            shot = shots.getLastShot();
        } else {
            shot = shots.getInjuredShip().get(shots.getInjuredShip().size() - 1);
        }

        String state = "";
        for (Ship ship : shipsOfHuman.getShips()) {
            if (ship.getDecks().indexOf(shot) != -1) {
                ship.checkState(fieldOfHuman);
                state = ship.getState();
            }
        }
        if (state.equals("injured")) {
            Cell nextShot = null;
            if (shots.getInjuredShip().indexOf(shot) == -1) {
                shots.addInInjuredShip(shot);
            }
            if (shots.getInjuredShip().size() == 1) {
                if (!fieldOfHuman.getStateCell(shot.getLetter() + 1, shot.getNumber()).equals("busy") && fieldOfHuman.cellInField(shot.getLetter(), shot.getNumber())) {
                    nextShot = new Cell(shot.getLetter() + 1, shot.getNumber());
                } else if (!fieldOfHuman.getStateCell(shot.getLetter() - 1, shot.getNumber()).equals("busy") && fieldOfHuman.cellInField(shot.getLetter(), shot.getNumber())) {
                    nextShot = new Cell(shot.getLetter() - 1, shot.getNumber());
                } else if (!fieldOfHuman.getStateCell(shot.getLetter(), shot.getNumber() + 1).equals("busy") && fieldOfHuman.cellInField(shot.getLetter(), shot.getNumber())) {
                    nextShot = new Cell(shot.getLetter(), shot.getNumber() + 1);
                } else if (!fieldOfHuman.getStateCell(shot.getLetter(), shot.getNumber() - 1).equals("busy") && fieldOfHuman.cellInField(shot.getLetter(), shot.getNumber())) {
                    nextShot = new Cell(shot.getLetter(), shot.getNumber() - 1);
                }
            } else if (shots.getInjuredShip().size() == 2) {
                int deltax = shots.getInjuredShip().get(1).getLetter() - shots.getInjuredShip().get(0).getLetter();
                int deltay = shots.getInjuredShip().get(1).getNumber() - shots.getInjuredShip().get(0).getNumber();
                if (deltax == 1) {
                    if (!fieldOfHuman.getStateCell(shots.getInjuredShip().get(1).getLetter() + 1, shots.getInjuredShip().get(1).getNumber()).equals("busy") && fieldOfHuman.cellInField(shot.getLetter(), shot.getNumber())) {
                        nextShot = new Cell(shots.getInjuredShip().get(1).getLetter() + 1, shots.getInjuredShip().get(1).getNumber());
                    } else if (!fieldOfHuman.getStateCell(shots.getInjuredShip().get(0).getLetter() - 1, shots.getInjuredShip().get(0).getNumber()).equals("busy") && fieldOfHuman.cellInField(shot.getLetter(), shot.getNumber())) {
                        nextShot = new Cell(shots.getInjuredShip().get(0).getLetter() - 1, shots.getInjuredShip().get(0).getNumber());
                    }
                }
                if (deltax == -1) {
                    if (!fieldOfHuman.getStateCell(shots.getInjuredShip().get(0).getLetter() + 1, shots.getInjuredShip().get(0).getNumber()).equals("busy") && fieldOfHuman.cellInField(shot.getLetter(), shot.getNumber())) {
                        nextShot = new Cell(shots.getInjuredShip().get(0).getLetter() + 1, shots.getInjuredShip().get(0).getNumber());
                    } else if (!fieldOfHuman.getStateCell(shots.getInjuredShip().get(1).getLetter() - 1, shots.getInjuredShip().get(1).getNumber()).equals("busy") && fieldOfHuman.cellInField(shot.getLetter(), shot.getNumber())) {
                        nextShot = new Cell(shots.getInjuredShip().get(1).getLetter() - 1, shots.getInjuredShip().get(1).getNumber());
                    }
                }
                if (deltay == 1) {
                    if (!fieldOfHuman.getStateCell(shots.getInjuredShip().get(1).getLetter(), shots.getInjuredShip().get(1).getNumber() + 1).equals("busy") && fieldOfHuman.cellInField(shot.getLetter(), shot.getNumber())) {
                        nextShot = new Cell(shots.getInjuredShip().get(1).getLetter(), shots.getInjuredShip().get(1).getNumber() + 1);
                    } else if (!fieldOfHuman.getStateCell(shots.getInjuredShip().get(0).getLetter(), shots.getInjuredShip().get(0).getNumber() - 1).equals("busy") && fieldOfHuman.cellInField(shot.getLetter(), shot.getNumber())) {
                        nextShot = new Cell(shots.getInjuredShip().get(0).getLetter(), shots.getInjuredShip().get(0).getNumber() - 1);
                    }
                }
                if (deltay == -1) {
                    if (!fieldOfHuman.getStateCell(shots.getInjuredShip().get(0).getLetter(), shots.getInjuredShip().get(0).getNumber() + 1).equals("busy") && fieldOfHuman.cellInField(shot.getLetter(), shot.getNumber())) {
                        nextShot = new Cell(shots.getInjuredShip().get(0).getLetter(), shots.getInjuredShip().get(0).getNumber() + 1);
                    } else if (!fieldOfHuman.getStateCell(shots.getInjuredShip().get(1).getLetter(), shots.getInjuredShip().get(1).getNumber() - 1).equals("busy") && fieldOfHuman.cellInField(shot.getLetter(), shot.getNumber())) {
                        nextShot = new Cell(shots.getInjuredShip().get(1).getLetter(), shots.getInjuredShip().get(1).getNumber() - 1);
                    }
                }
            } else if (shots.getInjuredShip().size() == 3) {
                int deltax1 = shots.getInjuredShip().get(2).getLetter() - shots.getInjuredShip().get(0).getLetter();
                int deltax2 = shots.getInjuredShip().get(2).getLetter() - shots.getInjuredShip().get(1).getLetter();
                int deltay1 = shots.getInjuredShip().get(2).getNumber() - shots.getInjuredShip().get(0).getNumber();
                int deltay2 = shots.getInjuredShip().get(2).getNumber() - shots.getInjuredShip().get(1).getNumber();
                if (deltax1 == 2) {
                    if (!fieldOfHuman.getStateCell(shots.getInjuredShip().get(2).getLetter() + 1, shots.getInjuredShip().get(2).getNumber()).equals("busy") && fieldOfHuman.cellInField(shot.getLetter(), shot.getNumber())) {
                        nextShot = new Cell(shots.getInjuredShip().get(2).getLetter() + 1, shots.getInjuredShip().get(2).getNumber());
                    } else if (!fieldOfHuman.getStateCell(shots.getInjuredShip().get(0).getLetter() - 1, shots.getInjuredShip().get(0).getNumber()).equals("busy") && fieldOfHuman.cellInField(shot.getLetter(), shot.getNumber())) {
                        nextShot = new Cell(shots.getInjuredShip().get(0).getLetter() - 1, shots.getInjuredShip().get(0).getNumber());
                    }
                }
                if (deltax1 == -2) {
                    if (!fieldOfHuman.getStateCell(shots.getInjuredShip().get(0).getLetter() + 1, shots.getInjuredShip().get(0).getNumber()).equals("busy") && fieldOfHuman.cellInField(shot.getLetter(), shot.getNumber())) {
                        nextShot = new Cell(shots.getInjuredShip().get(0).getLetter() + 1, shots.getInjuredShip().get(0).getNumber());
                    } else if (!fieldOfHuman.getStateCell(shots.getInjuredShip().get(2).getLetter() - 1, shots.getInjuredShip().get(2).getNumber()).equals("busy") && fieldOfHuman.cellInField(shot.getLetter(), shot.getNumber())) {
                        nextShot = new Cell(shots.getInjuredShip().get(2).getLetter() - 1, shots.getInjuredShip().get(2).getNumber());
                    }
                }
                if (deltay1 == 2) {
                    if (!fieldOfHuman.getStateCell(shots.getInjuredShip().get(2).getLetter(), shots.getInjuredShip().get(2).getNumber() + 1).equals("busy") && fieldOfHuman.cellInField(shot.getLetter(), shot.getNumber())) {
                        nextShot = new Cell(shots.getInjuredShip().get(2).getLetter(), shots.getInjuredShip().get(2).getNumber() + 1);
                    } else if (!fieldOfHuman.getStateCell(shots.getInjuredShip().get(0).getLetter(), shots.getInjuredShip().get(0).getNumber() - 1).equals("busy") && fieldOfHuman.cellInField(shot.getLetter(), shot.getNumber())) {
                        nextShot = new Cell(shots.getInjuredShip().get(0).getLetter(), shots.getInjuredShip().get(0).getNumber() - 1);
                    }
                }
                if (deltay1 == -2) {
                    if (!fieldOfHuman.getStateCell(shots.getInjuredShip().get(0).getLetter(), shots.getInjuredShip().get(0).getNumber() + 1).equals("busy") && fieldOfHuman.cellInField(shot.getLetter(), shot.getNumber())) {
                        nextShot = new Cell(shots.getInjuredShip().get(0).getLetter(), shots.getInjuredShip().get(0).getNumber() + 1);
                    } else if (!fieldOfHuman.getStateCell(shots.getInjuredShip().get(2).getLetter(), shots.getInjuredShip().get(2).getNumber() - 1).equals("busy") && fieldOfHuman.cellInField(shot.getLetter(), shot.getNumber())) {
                        nextShot = new Cell(shots.getInjuredShip().get(2).getLetter(), shots.getInjuredShip().get(2).getNumber() - 1);
                    }
                }
                if (deltax2 == 2) {
                    if (!fieldOfHuman.getStateCell(shots.getInjuredShip().get(2).getLetter() + 1, shots.getInjuredShip().get(2).getNumber()).equals("busy") && fieldOfHuman.cellInField(shot.getLetter(), shot.getNumber())) {
                        nextShot = new Cell(shots.getInjuredShip().get(2).getLetter() + 1, shots.getInjuredShip().get(2).getNumber());
                    } else if (!fieldOfHuman.getStateCell(shots.getInjuredShip().get(1).getLetter() - 1, shots.getInjuredShip().get(1).getNumber()).equals("busy") && fieldOfHuman.cellInField(shot.getLetter(), shot.getNumber())) {
                        nextShot = new Cell(shots.getInjuredShip().get(1).getLetter() - 1, shots.getInjuredShip().get(1).getNumber());
                    }
                }
                if (deltax2 == -2) {
                    if (!fieldOfHuman.getStateCell(shots.getInjuredShip().get(1).getLetter() + 1, shots.getInjuredShip().get(1).getNumber()).equals("busy") && fieldOfHuman.cellInField(shot.getLetter(), shot.getNumber())) {
                        nextShot = new Cell(shots.getInjuredShip().get(1).getLetter() + 1, shots.getInjuredShip().get(1).getNumber());
                    } else if (!fieldOfHuman.getStateCell(shots.getInjuredShip().get(2).getLetter() - 1, shots.getInjuredShip().get(2).getNumber()).equals("busy") && fieldOfHuman.cellInField(shot.getLetter(), shot.getNumber())) {
                        nextShot = new Cell(shots.getInjuredShip().get(2).getLetter() - 1, shots.getInjuredShip().get(2).getNumber());
                    }
                }
                if (deltay2 == 2) {
                    if (!fieldOfHuman.getStateCell(shots.getInjuredShip().get(2).getLetter(), shots.getInjuredShip().get(2).getNumber() + 1).equals("busy") && fieldOfHuman.cellInField(shot.getLetter(), shot.getNumber())) {
                        nextShot = new Cell(shots.getInjuredShip().get(2).getLetter(), shots.getInjuredShip().get(2).getNumber() + 1);
                    } else if (!fieldOfHuman.getStateCell(shots.getInjuredShip().get(1).getLetter(), shots.getInjuredShip().get(1).getNumber() - 1).equals("busy") && fieldOfHuman.cellInField(shot.getLetter(), shot.getNumber())) {
                        nextShot = new Cell(shots.getInjuredShip().get(1).getLetter(), shots.getInjuredShip().get(1).getNumber() - 1);
                    }
                }
                if (deltay1 == -2) {
                    if (!fieldOfHuman.getStateCell(shots.getInjuredShip().get(1).getLetter(), shots.getInjuredShip().get(1).getNumber() + 1).equals("busy") && fieldOfHuman.cellInField(shot.getLetter(), shot.getNumber())) {
                        nextShot = new Cell(shots.getInjuredShip().get(1).getLetter(), shots.getInjuredShip().get(1).getNumber() + 1);
                    } else if (!fieldOfHuman.getStateCell(shots.getInjuredShip().get(2).getLetter(), shots.getInjuredShip().get(2).getNumber() - 1).equals("busy") && fieldOfHuman.cellInField(shot.getLetter(), shot.getNumber())) {
                        nextShot = new Cell(shots.getInjuredShip().get(2).getLetter(), shots.getInjuredShip().get(2).getNumber() - 1);
                    }
                }
            }
            if (nextShot == null) {
                System.out.println(shots.getInjuredShip().size());
            }
            int i = nextShot.getNumber() * 10 + nextShot.getLetter();
            shots.setLastShot(nextShot);
            String returnCheck = checkShot(nextShot, shipsOfHuman, shots, fieldOfHuman);
            if (returnCheck.equals("bangIcon")) {
                bs.get(i).setIcon(bangIcon);
                shots.addInInjuredShip(nextShot);
                fieldOfHuman.setStateCell(nextShot.getLetter(), nextShot.getNumber());
                shots.setShot(false);
            } else if (returnCheck.equals("bangIconAround")) {
                shots.clearInjuredShip();
                for (Integer b : shots.getIndexOfButtonOfCurrentShip(fieldOfHuman, shots)) {
                    bs.get(i).setIcon(bangIcon);
                    shots.setShot(false);
                    bs.get(b).setIcon(crossIcon);
                }
            } else {
                bs.get(i).setIcon(crossIcon);
                shots.setShot(true);
            }
        } else if (state.equals("killed")) {
            shots.clearInjuredShip();
            doAIShot(bs, shipsOfHuman, shots);
            shots.clearInjuredShip();
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


    static void click2(ArrayList<JButton> bs) {
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

    static void doVisible(JButton b1, JButton b2, JButton b3, JButton b4, JButton b5, JButton b6, JLabel
            l1, JLabel l2, JLabel l3, Boolean bol) {
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


