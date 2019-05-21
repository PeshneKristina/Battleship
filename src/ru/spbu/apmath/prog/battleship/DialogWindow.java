package ru.spbu.apmath.prog.battleship;

import javax.swing.JOptionPane;


public class DialogWindow {

    public static void infoBox(String infoMessage, String titleBar)
    {
        JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
    }
}

