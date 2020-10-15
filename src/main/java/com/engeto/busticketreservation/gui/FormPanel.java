package com.engeto.busticketreservation.gui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class FormPanel extends JPanel {

    private JLabel fromLabel;
    private JLabel toLabel;
    private JLabel timeLabel;
    private JLabel dateLabel;
    private JLabel nameLabel;
    private JLabel seatsLabel;


    private JComboBox fromComboBox;
    private JComboBox toComboBox;
    private JComboBox timeComboBox;
    private JTextField dateTextfield;
    private JTextField nameTextfield;
    private JPanel seatsArea;

    private JButton okBtn;
    private JButton saveBtn;


    private JCheckBox[] seats;
    private DefaultComboBoxModel toModel;
    private ComboBoxEvent ev;

    private FormListener formListener;
    private ComboListener comboListener;

    public FormPanel() {
        Dimension dim = getPreferredSize();
        dim.width = 300;
        setPreferredSize(dim);

        fromLabel = new JLabel("Odkud: ");
        toLabel = new JLabel("Kam: ");
        timeLabel = new JLabel("Čas :");
        dateLabel = new JLabel("Datum: ");
        nameLabel = new JLabel("Jméno: ");
        seatsLabel = new JLabel("Sedadla: ");


        fromComboBox = new JComboBox();
        toComboBox = new JComboBox();
        timeComboBox = new JComboBox();
        dateTextfield = new JTextField(12);
        nameTextfield = new JTextField(12);
        seatsArea = new JPanel();

        Dimension dimText = getPreferredSize();
        dimText.setSize(100, 25);
        dateTextfield.setPreferredSize(dimText);
        nameTextfield.setPreferredSize(dimText);



        DefaultComboBoxModel fromModel = new DefaultComboBoxModel();
        fromModel.addElement(new From(0, " "));
        fromModel.addElement(new From(1, "Brno"));
        fromModel.addElement(new From(2, "Jihlava"));
        fromModel.addElement(new From(3, "Ostrava"));
        fromComboBox.setModel(fromModel);
        fromComboBox.setSelectedIndex(0);

        toModel = new DefaultComboBoxModel();
        toModel.addElement(new To(0, " "));


        DefaultComboBoxModel timeModel = new DefaultComboBoxModel();
        timeModel.addElement(new Time(0, " "));
        timeModel.addElement(new Time(1, " 7:00"));
        timeModel.addElement(new Time(2, "12:00"));
        timeModel.addElement(new Time(3, "19:00"));
        timeComboBox.setModel(timeModel);
        timeComboBox.setSelectedIndex(0);




        okBtn = new JButton("Potvrdit");
        seats = new JCheckBox[50];
        saveBtn = new JButton("Uložit");

        fromComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int from =  fromComboBox.getSelectedIndex(); //getselectedIndex
                ComboBoxEvent ev = new ComboBoxEvent(from);
                if (comboListener != null) {
                    comboListener.formEventOccured(ev);

                }
            }
        });

        toComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int to =  toComboBox.getSelectedIndex(); //getselectedIndex
                ev = new ComboBoxEvent(to);
                if (comboListener != null) {
                    comboListener.formEventOccured(ev);
                }




            }
        });

        timeComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int time =  timeComboBox.getSelectedIndex();
                ComboBoxEvent ev = new ComboBoxEvent(time);
                if (comboListener != null) {
                    comboListener.formEventOccured(ev);
                }
            }
        });

        okBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setSeats();
            }
        });
        saveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int from =  fromComboBox.getSelectedIndex();
                int to = toComboBox.getSelectedIndex();
                int time = timeComboBox.getSelectedIndex();

                String date = dateTextfield.getText();
                List<Integer> reservedSeats = new ArrayList<>();
                for (int i = 0; i < seats.length; i++) {
                    if(seats[i].isSelected()){
                        reservedSeats.add(i);
                    }
                }
                String name = nameTextfield.getText();

                FormEvent ev = new FormEvent(this, name, date, from, to, time, reservedSeats);

                if (formListener != null) {
                    formListener.formEventOccured(ev);
                }

            }
        });


        Border innerBorder = BorderFactory.createTitledBorder("Udělat rezervaci");
        Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));

        layoutComponents();
        createSeats();
        setComboBoxModels();

    }

    private void setComboBoxModels() {
        toModel.addElement(new To(1, "Brno"));
        toModel.addElement(new To(2, "Jihlava"));
        toModel.addElement(new To(3, "Ostrava"));
        toComboBox.setModel(toModel);
        toComboBox.setSelectedIndex(0);
    }


    public void layoutComponents() {

        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        /////////////////First row////////////////////

        gc.weightx = 1;
        gc.weighty = 0.1;

        gc.gridx = 0;
        gc.gridy = 0;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(0, 0, 0, 5);
        add(fromLabel, gc);

        gc.gridx = 1;
        gc.gridy = 0;
        gc.insets = new Insets(0,0,0,0);
        fromComboBox.setPrototypeDisplayValue("default text  here");
        gc.anchor = GridBagConstraints.LINE_START;
        add(fromComboBox, gc);

        //////////////////Next row////////////////////

        gc.weightx = 1;
        gc.weighty = 0.1;

        gc.gridx = 0;
        gc.gridy++;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(0, 0, 0, 5);
        add(toLabel, gc);

        gc.gridx = 1;
        gc.insets = new Insets(0,0,0,0);
        toComboBox.setPrototypeDisplayValue("default text  here");
        gc.anchor = GridBagConstraints.LINE_START;
        add(toComboBox, gc);

        //////////////////Next row////////////////////

        gc.weightx = 1;
        gc.weighty = 0.1;

        gc.gridx = 0;
        gc.gridy++;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(0, 0, 0, 5);
        add(timeLabel, gc);

        gc.gridx = 1;
        gc.insets = new Insets(0,0,0,0);
        timeComboBox.setPrototypeDisplayValue("default text  here");
        gc.anchor = GridBagConstraints.LINE_START;
        add(timeComboBox, gc);

        //////////////////Next row////////////////////

        gc.weightx = 1;
        gc.weighty = 0.1;

        gc.gridx = 0;
        gc.gridy++;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(0, 0, 0, 5);
        add(dateLabel, gc);

        gc.gridx = 1;
        gc.insets = new Insets(0,0,0,0);
        gc.anchor = GridBagConstraints.LINE_START;
        add(dateTextfield, gc);


        ///////////////////////Button row///////////////////////////

        gc.weightx = 1;
        gc.weighty = 0.1;

        gc.gridx = 1;
        gc.gridy++;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(0,0,0,0);
        add(okBtn, gc);



        //////////////////Seats row////////////////////

        gc.weightx = 1;
        gc.weighty = 2;

        gc.gridx = 0;
        gc.gridy++;
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        gc.insets = new Insets(0, 0, 0, 5);
        add(seatsLabel, gc);

        gc.gridx = 1;

        gc.fill = GridBagConstraints.BOTH;
        gc.insets = new Insets(0,0,0,0);
        gc.anchor = GridBagConstraints.LINE_START;

        seatsArea.setBorder(BorderFactory.createEtchedBorder());
        seatsArea.setLayout(new GridLayout(13,4));

        add(seatsArea, gc);


        //////////////////Next row////////////////////

        gc.weightx = 1;
        gc.weighty = 0.1;

        gc.gridx = 0;
        gc.gridy++;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(0, 0, 0, 5);
        add(nameLabel, gc);

        gc.gridx = 1;
        gc.insets = new Insets(0,0,0,0);
        gc.anchor = GridBagConstraints.LINE_START;
        add(nameTextfield, gc);



        ///////////////////////Last row///////////////////////////

        gc.weightx = 1;
        gc.weighty = 0.1;

        gc.gridx = 1;
        gc.gridy++;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(0,0,0,0);
        add(saveBtn, gc);

    }

    public void setFormListener(FormListener listener) {
        this.formListener = listener;
    }

    public void createSeats() {
        for (int i = 0; i < 50; i++) {
            seats[i] = new JCheckBox();
            seats[i].setText(String.valueOf(i + 1));
            seats[i].setEnabled(false);
            seatsArea.add(seats[i]);
        }
        seatsArea.updateUI();
    }
    public void setSeats() {
        for (int i = 0; i < 50; i++) {
            seats[i].setEnabled(true);
        }
        seatsArea.updateUI();
    }

    public void setComboListener(ComboListener comboListener) {
        this.comboListener = comboListener;

    }

}


class From {
    private int id;
    private String text;

    public From(int id, String text) {
        this.id = id;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return text;
    }
}

class To {
    private int id;
    private String text;

    public To(int id, String text) {
        this.id = id;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return text;
    }
}

class Time {
    private int id;
    private String text;

    public Time(int id, String text) {
        this.id = id;
        this.text = text;
    }

    public int getId() {
        return id;
    }
    @Override
    public String toString() {
        return text;
    }

}

