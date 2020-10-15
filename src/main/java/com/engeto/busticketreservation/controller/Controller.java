package com.engeto.busticketreservation.controller;

import com.engeto.busticketreservation.gui.FormEvent;
import com.engeto.busticketreservation.model.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Controller {

    ReservationRepository reservationRepository = new ReservationRepository();
    TimeTableRepository timeTableRepository = new TimeTableRepository();

    /************* Reservations ***************/

    public List<Reservation> getReserevations() {
        return reservationRepository.getReservations();
    }

    public void addReservation(FormEvent ev) {
        String name = ev.getName();
        String date = ev.getDate();
        int fromIndex = ev.getFromIndex();
        int toIndex = ev.getToIndex();
        int timeIndex = ev.getTimeIndex();
        List<Integer> seats = ev.getReservedSeats();

        Places from;
        switch (fromIndex) {
            case 1:
                from = Places.Brno;
                break;
            case 2:
                from = Places.Jihlava;
                break;
            case 3:
                from = Places.Ostrava;
                break;
            default:
                from = null;
        }

        Places to;
        switch (toIndex) {
            case 1:
                to = Places.Brno;
                break;
            case 2:
                to = Places.Jihlava;
                break;
            case 3:
                to = Places.Ostrava;
                break;
            default:
                to = null;
        }

        String time;
        switch (timeIndex) {
            case 1:
                time = "7:00";
                break;
            case 2:
                time = "12:00";
                break;
            case 3:
                time = "19:00";
                break;
            default:
                time = null;
        }


        Reservation reservation = new Reservation(name, date, from, to, time, seats);
        reservationRepository.addReservation(reservation);
    }

    public void removeReservation(int index) {
        reservationRepository.removeReservation(index);
    }

    public void saveToFile(File file) throws IOException {

        reservationRepository.saveToCSVFile(file);
    }

    public void loadFromFile(File file) throws IOException {

        reservationRepository.loadFromCsvFile(file);

    }
    /************* TimeTable ************/

    public void loadTimeTableFromFile(File file) throws IOException {

        timeTableRepository.loadTimeTable(file);

    }
    public List<TimeTableLine> getTimeTable() {
        return timeTableRepository.getTimeTableLineList();
    }

    public String[] getFromCombo() {
        return timeTableRepository.getFromCombo();
    }
    public List<String> getToPlacesCombo() {
        return timeTableRepository.getToPlacesCombo();
    }


}
