package ru.spbu.apmath.prog.battleship;

import javax.swing.*;
import java.awt.*;

public class ConstructionPanel extends JPanel{
    public ConstructionPanel(){
        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        setOpaque(false);
        JButton fourDecksBtn = new JButton("Четырехпалубники");
        JButton threeDecksBtn = new JButton("    Трехпалубники   ");
        JButton twoDecksBtn = new JButton("    Двухпалубники   ");
        JButton oneDecksBtn = new JButton("    Однопалубники   ");
        JLabel one = new JLabel("1");
        JLabel two = new JLabel("2");
        JLabel three = new JLabel("3");
        JLabel four = new JLabel("4");
        one.setForeground(Color.WHITE);
        two.setForeground(Color.WHITE);
        three.setForeground(Color.WHITE);
        four.setForeground(Color.WHITE);
        JButton edit1 = new JButton("редактировать");
        JButton edit2 = new JButton("редактировать");
        JButton edit3 = new JButton("редактировать");
        JButton edit4 = new JButton("редактировать");
        JButton resetBtn = new JButton("сбросить");
        JButton saveBtn = new JButton("сохранить");
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.setHorizontalGroup(layout.createParallelGroup(
                GroupLayout.Alignment.CENTER)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(
                                        GroupLayout.Alignment.LEADING, true)
                                        .addComponent(fourDecksBtn)
                                        .addComponent(threeDecksBtn)
                                        .addComponent(twoDecksBtn)
                                        .addComponent(oneDecksBtn)
                                )
                                .addGroup(layout.createParallelGroup()
                                        .addComponent(four)
                                        .addComponent(three)
                                        .addComponent(two)
                                        .addComponent(one)
                                )
                                .addGroup(layout.createParallelGroup()
                                        .addComponent(edit4)
                                        .addComponent(edit3)
                                        .addComponent(edit2)
                                        .addComponent(edit1)
                                )
                        )
                        .addGroup(layout.createSequentialGroup()
                        //.addComponent(saveBtn)
                        //.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(resetBtn)
                        )
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup()
                                .addComponent(fourDecksBtn)
                                .addComponent(four)
                                .addComponent(edit4)
                        )
                        .addGroup(layout.createParallelGroup()
                                .addComponent(threeDecksBtn)
                                .addComponent(three)
                                .addComponent(edit3)
                        )
                        .addGroup(layout.createParallelGroup()
                                .addComponent(twoDecksBtn)
                                .addComponent(two)
                                .addComponent(edit2)
                        )
                        .addGroup(layout.createParallelGroup()
                                .addComponent(oneDecksBtn)
                                .addComponent(one)
                                .addComponent(edit1)
                        )
                        .addGroup(layout.createParallelGroup()
                                        //.addComponent(saveBtn)
                                        .addComponent(resetBtn)
                        )
        );


    }
}
