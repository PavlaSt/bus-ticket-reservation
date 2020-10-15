package com.engeto.busticketreservation.gui;

import com.engeto.busticketreservation.model.Reservation;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class ReservationTableModel extends AbstractTableModel {

    private List<Reservation> reservationList;
    private String[] colNames = { "ID", "Name", "Date", "From", "To", "Time", "Seats"};

    public ReservationTableModel() {

    }

    @Override
    public String getColumnName(int column) {

        return colNames[column];
    }
    public void setData(List<Reservation> reservationList) {
        this.reservationList = reservationList;
    }

    @Override
    public int getRowCount() {
        return reservationList.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public Object getValueAt(int row, int col) {
        Reservation reservation = reservationList.get(row);

        switch (col) {
            case 0:
                return reservation.getName();
            case 1:
                return reservation.getDate();
            case 2:
                return reservation.getFrom();
            case 3:
                return reservation.getTo();
            case 4:
                return reservation.getTime();
            case 5:
                return  reservation.getReservedSeats();
        }
        return null;
    }


}
