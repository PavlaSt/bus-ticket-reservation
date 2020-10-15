package com.engeto.busticketreservation.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ToolBar extends JPanel implements ActionListener {

    private JButton loadTimetblBtn;
    private JButton loadResBtn;
    private JButton saveResBtn;

    private ToolBarListener toolBarListener;


    public ToolBar() {
        setBorder(BorderFactory.createEtchedBorder());
        loadTimetblBtn = new JButton("Načíst jízdní řád");
        loadResBtn = new JButton("Načíst rezervace");
        saveResBtn = new JButton("Uložit rezervace");

        loadTimetblBtn.addActionListener(this);
        loadResBtn.addActionListener(this);
        saveResBtn.addActionListener(this);

        setLayout(new FlowLayout(FlowLayout.LEFT));

        add(loadTimetblBtn);
        add(loadResBtn);
        add(saveResBtn);


    }


    public void setToolBarListener(ToolBarListener listener) {
        this.toolBarListener = listener;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clicked = (JButton) e.getSource();

        if (clicked == loadTimetblBtn) {
            if (toolBarListener != null) {
                toolBarListener.loadTimeTblEventOccured();
            }

        } else if (clicked == loadResBtn) {
            if (toolBarListener != null) {
                toolBarListener.loadEventOccured();
            }
        } else {
            if (toolBarListener != null) {
                toolBarListener.saveEventOccured();
            }
        }
    }
}
