package ru.spbu.apmath.prog.project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {

    private static final long serialVersionUID = 2269971701250845501L;

    private String title;
    private Dimension d;

    private JTextField text1Field = new JTextField(10);
    private JTextField text2Field = new JTextField(10);
    private JButton button = new JButton("Button");
    private JLabel label = new JLabel("label");

    public MainFrame(String title, Dimension d) {
        this.title = title;
        this.d = d;
    }

    public void init() {
        setTitle(title);
        setSize(d);
        setLayout(new GridBagLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        button.addActionListener(new ButtonActionListener());

        add(text1Field, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(1, 1, 1, 1), 0, 0));
        add(text2Field, new GridBagConstraints(1, 0, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(1, 1, 1, 1), 0, 0));
        add(button, new GridBagConstraints(0, 1, 2, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(1, 1, 1, 1), 0, 0));

        setVisible(true);
        pack();
    }


    public class ButtonActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            System.out.println("hello world!");
        }


    }
}
