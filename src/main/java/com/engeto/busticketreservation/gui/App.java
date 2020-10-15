package com.engeto.busticketreservation.gui;

import javax.swing.*;

public class App {

    public static void main(String[] args) {

       /* try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | UnsupportedLookAndFeelException | IllegalAccessException e) {
            e.printStackTrace();
        }*/

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
               new MainFrame();
            }
        });



    }
}
