package ru.spbu.apmath.prog.battleship;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class StartPanel extends JPanel {

    public StartPanel(JPanel nextPanel, JFrame frame) {
        setBorder(new EmptyBorder(10, 10, 10, 10));
        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(1000, 1000));
        JLabel background = new JLabel(new ImageIcon("resources/startMenu.png"));
        background.setLayout(new GridBagLayout());
        add(background);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.NORTH;

        JLabel battleShipLabel = new JLabel("Battle Ship");
        battleShipLabel.setForeground(Color.WHITE);
        battleShipLabel.setFont(new Font("Courier New", Font.ITALIC, 100));
        background.add(battleShipLabel, gbc);

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JPanel buttons = new JPanel(new GridBagLayout());
        buttons.setOpaque(false);
        JButton startBtn = new JButton("Новая игра");
        JButton exitBtn = new JButton("Выxод");
        startBtn.setPreferredSize(new Dimension(150, 35));
        exitBtn.setPreferredSize(new Dimension(150, 35));
        startBtn.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));
        exitBtn.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));
        gbc.weighty = 1;
        buttons.add(startBtn, gbc);
        buttons.add(exitBtn, gbc);
        background.add(buttons, gbc);
        exitBtn.addActionListener(e -> System.exit(0));
        startBtn.addActionListener(e -> {
            setVisible(false);
            frame.add(nextPanel);
            frame.pack();
        });

    }


}
