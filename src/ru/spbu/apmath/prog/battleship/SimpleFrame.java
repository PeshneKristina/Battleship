package ru.spbu.apmath.prog.battleship;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimpleFrame extends JFrame {

    public SimpleFrame() {
        setPreferredSize(new Dimension(500, 500));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(300, 310));
        layeredPane.setBackground(Color.BLUE);
        //layeredPane.setLayout(new BorderLayout());
        add(layeredPane);

        //create green panel
        JPanel greenPanel = new JPanel();
        greenPanel.setBackground(Color.GREEN);
        greenPanel.setLocation(0, 0);
        greenPanel.setSize(getPreferredSize());
        JButton moveToYellowPanelBtn = new JButton("Move to yellow panel");
        greenPanel.add(moveToYellowPanelBtn);

        //create yellow panel
        JPanel yellowPanel = new JPanel();
        yellowPanel.setBackground(Color.YELLOW);
        yellowPanel.setLocation(0, 0);
        yellowPanel.setSize(getPreferredSize());
        JButton moveToGreenPanelBtn = new JButton("Move to green panel");
        yellowPanel.add(moveToGreenPanelBtn);

        layeredPane.add(yellowPanel);
        layeredPane.add(greenPanel);
        System.out.println(layeredPane.getLayer(yellowPanel));
        System.out.println(layeredPane.getLayer(greenPanel));
        System.out.println(layeredPane.getPosition(yellowPanel));
        System.out.println(layeredPane.getPosition(greenPanel));

        moveToYellowPanelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                layeredPane.moveToFront(yellowPanel);
                //layeredPane.setLayer(yellowPanel,1);
                //layeredPane.setLayer(greenPanel, 1, 0);
                //layeredPane.setLayer(yellowPanel, 1, -1);
                System.out.println(layeredPane.getLayer(yellowPanel));
                System.out.println(layeredPane.getLayer(greenPanel));
                System.out.println(layeredPane.getPosition(yellowPanel));
                System.out.println(layeredPane.getPosition(greenPanel));

            }
        });

        moveToGreenPanelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                layeredPane.moveToFront(greenPanel);
                System.out.println(layeredPane.getLayer(yellowPanel));
                System.out.println(layeredPane.getLayer(greenPanel));
                System.out.println(layeredPane.getPosition(yellowPanel));
                System.out.println(layeredPane.getPosition(greenPanel));
                //layeredPane.moveToBack(greenPanel);
                //layeredPane.setLayer(greenPanel, 1, -1);
                //layeredPane.setLayer(yellowPanel, 1, 0);

            }
        });
        pack();
    }

    public static void main(String[] args) {
        SimpleFrame frame = new SimpleFrame();
        frame.setVisible(true);
    }
}
