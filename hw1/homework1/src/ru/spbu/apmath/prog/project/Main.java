package ru.spbu.apmath.prog.project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        JFrame frame = new JFrame();

        frame.setSize(500, 500);
        frame.setTitle("Title");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout((new GridBagLayout()));
       /* JButton myButton = new JButton();
        JButton myButton2 = new JButton();
        myButton.setText("Press me");
        myButton2.setText("You are beautiful");
        myButton.setBackground(Color.YELLOW);
        myButton2.setBackground(Color.GRAY);
        myButton2.setForeground(Color.BLUE);
        myButton2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        JTextField textField = new JTextField(20);
        JButton butt = new JButton("Сохранить");
        System.out.println(textField.getText());

        //frame.add(myButton);
        //frame.add(myButton2);
        frame.add(textField);
        frame.add(butt);
*/
       TableModel btm = new TableModel() ;
       JTable bookTable = new JTable(btm);
       JScrollPane bookTableScroolPage = new JScrollPane();
       bookTableScroolPage.setPreferredSize(new Dimension(400,400));
       frame.add(bookTableScroolPage,new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(1, 1, 1, 1), 0, 0));
       frame.setVisible(true);

        /*JProgressBar progressBar = new JProgressBar();
        frame.add(progressBar);
        progressBar.setStringPainted(true);

        progressBar.setMinimum(0);
        progressBar.setMaximum(100);

        JLabel label = new JLabel();
        frame.add(label);
        label.setText("Загрузка...");
        for (int i = progressBar.getMinimum(); i <= progressBar.getMaximum(); i++) {
            Thread.sleep(50);
            progressBar.setValue(i);
        }
        label.setText("Загружено");*/

        /*JPanel panel = new JPanel();
        frame.add(panel);
        panel.setBackground(Color.BLUE);
        JButton button = new JButton("First");
        panel.add(button);*/

        /*frame.setLayout((new BorderLayout()));
        JButton button = new JButton("Button");
        frame.add(button,BorderLayout.PAGE_START);
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();

        panel1.setBackground(Color.BLUE);
        panel2.setBackground(Color.RED);
        panel3.setBackground(Color.GREEN);

        frame.add(panel1, BorderLayout.PAGE_START);
        frame.add(panel2, BorderLayout.PAGE_END);
        frame.add(panel3, BorderLayout.LINE_START);
        panel3.setPreferredSize(new Dimension(600,100));
*/



    }

}

