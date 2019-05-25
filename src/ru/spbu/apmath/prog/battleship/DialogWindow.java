package ru.spbu.apmath.prog.battleship;

import javax.swing.JOptionPane;


class DialogWindow {

    static void infoBox(String infoMessage, String titleBar)
    {
        JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
    }
}

