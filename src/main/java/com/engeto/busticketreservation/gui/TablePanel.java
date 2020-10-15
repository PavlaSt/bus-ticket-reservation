package com.engeto.busticketreservation.gui;

import com.engeto.busticketreservation.model.Reservation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class TablePanel extends JPanel {

    private JTable table;
    private ReservationTableModel tableModel;
    private JPopupMenu popupMenu;
    private ReservationTableListener reservationTableListener;

    public TablePanel() {
        tableModel = new ReservationTableModel();
        table = new JTable(tableModel);
        popupMenu = new JPopupMenu();

        JMenuItem removeItem = new JMenuItem("Smazat řádek");
        popupMenu.add(removeItem);

        table.addMouseListener(new MouseAdapter() {


            @Override
            public void mousePressed(MouseEvent e) {
                int row = table.rowAtPoint(e.getPoint());
                table.getSelectionModel().setSelectionInterval(row, row);
                if(e.getButton() == MouseEvent.BUTTON3) {
                    popupMenu.show(table, e.getX(), e.getY());
                }
            }
        });

        removeItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int row = table.getSelectedRow();

                if (reservationTableListener != null) {
                    reservationTableListener.rowDeleted(row);
                    tableModel.fireTableRowsDeleted(row, row);
                }
                System.out.println(row);
            }
        });

        setLayout(new BorderLayout());
        add(new JScrollPane(table) , BorderLayout.CENTER);

    }
    public void setData(List<Reservation> reservationList) {
        tableModel.setData(reservationList);

    }

    public void refresh() {
        tableModel.fireTableDataChanged();
    }

    public void setReservationTableListener(ReservationTableListener listener) {
        this.reservationTableListener = listener;
    }
}
