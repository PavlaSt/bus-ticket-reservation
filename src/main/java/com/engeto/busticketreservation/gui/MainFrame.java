package com.engeto.busticketreservation.gui;

import com.engeto.busticketreservation.controller.Controller;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;


public class MainFrame extends JFrame {


    private ToolBar toolBar;
    private FormPanel formPanel;
    private JFileChooser fileChooser;
    private Controller controller;
    private TablePanel tablePanel;

    public MainFrame() {
        super("Bus Reservation System");

        setLayout(new BorderLayout());

        toolBar = new ToolBar();
        formPanel = new FormPanel();
        tablePanel = new TablePanel();

        controller = new Controller();

        tablePanel.setData(controller.getReserevations());


        tablePanel.setReservationTableListener(new ReservationTableListener(){
            public void rowDeleted(int row) {
                controller.removeReservation(row);
            }
        });

        fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("./data/"));
        fileChooser.setFileFilter(new FileNameExtensionFilter("csv soubory", "csv"));

        setJMenuBar(createMenuBar());

        //TODO  setFormPanelListener

        toolBar.setToolBarListener(new ToolBarListener() {
            @Override
            public void saveEventOccured() {
                saveToFile();
            }
            @Override
            public void loadEventOccured() {
               loadFromFile();
            }
            @Override
            public void loadTimeTblEventOccured() {
                loadTimetableFromFile();
            }
        });

        formPanel.setFormListener(new FormListener() {
            public void formEventOccured(FormEvent e) {
                controller.addReservation(e);
                tablePanel.refresh();
            }
        });

        formPanel.setComboListener(new ComboListener() {
            @Override
            public void formEventOccured(ComboBoxEvent e) {
                System.out.println(e.getSource());
            }
        });

        add(formPanel, BorderLayout.WEST);
        add(toolBar, BorderLayout.NORTH);
        add(tablePanel, BorderLayout.CENTER);

        setMinimumSize(new Dimension(750, 650));
        setSize(800, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("Soubory");
        JMenuItem exportDataItem = new JMenuItem("Uložit data...");
        JMenuItem importDataItem = new JMenuItem("Načíst data...");
        JMenuItem exitItem = new JMenuItem("Konec");

        fileMenu.add(exportDataItem);
        fileMenu.add(importDataItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        JMenu windowMenu = new JMenu("Okno");
        JMenu showMenu = new JMenu("Zobraz");

        JCheckBoxMenuItem showFormItem = new JCheckBoxMenuItem("Rezervace");
        showFormItem.setSelected(true);

        showMenu.add(showFormItem);
        windowMenu.add(showMenu);

        menuBar.add(fileMenu);
        menuBar.add(windowMenu);

        showFormItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                JCheckBoxMenuItem menuItem = (JCheckBoxMenuItem) ev.getSource();

                formPanel.setVisible(menuItem.isSelected());
            }
        });

        importDataItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                loadFromFile();
            }
        });

        exportDataItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                saveToFile();
            }
        });

        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                int action = JOptionPane.showConfirmDialog(MainFrame.this, "Opravdu chcete ukončit aplikaci?",
                        "Potvrzení ukončení", JOptionPane.OK_CANCEL_OPTION);
                if (action == JOptionPane.OK_OPTION) {
                    System.exit(0);
                }
            }
        });
        return menuBar;
    }


    public void saveToFile() {
        if(fileChooser.showSaveDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION) {
            try {
                controller.saveToFile(fileChooser.getSelectedFile());
            } catch (IOException e) {
                JOptionPane.showMessageDialog(MainFrame.this,
                        "Nepodařilo se uložit data do souboru.", "Chyba",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void loadFromFile() {
        if(fileChooser.showOpenDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION) {
            try {
                controller.loadFromFile(fileChooser.getSelectedFile());
                tablePanel.refresh();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(MainFrame.this,
                        "Nepodařilo se načíst data ze souboru.", "Chyba",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void loadTimetableFromFile() {
        if(fileChooser.showOpenDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION) {
            try {
                controller.loadTimeTableFromFile(fileChooser.getSelectedFile());
                //System.out.println(controller.getTimeTable());
            } catch (IOException e) {
                JOptionPane.showMessageDialog(MainFrame.this,
                        "Nepodařilo se načíst data ze souboru.", "Chyba",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
