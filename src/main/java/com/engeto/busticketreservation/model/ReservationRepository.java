package com.engeto.busticketreservation.model;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class ReservationRepository {

    private static final String DELIMITER = ";";
    private List<Reservation> reservations;

    public ReservationRepository() {
        this.reservations = new LinkedList<>();
    }

    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
    }

    public void removeReservation(int index) {
        reservations.remove(index);
    }

    public List<Reservation> getReservations() {
        return Collections.unmodifiableList(reservations);
    }


    public void saveToCSVFile(File file) throws IOException {
            List<String> rows = new ArrayList<>(reservations.size());
            for (Reservation oneReservation : reservations) {
                String row =
                                oneReservation.getName() + ";"
                                + oneReservation.getDate() + ";"
                                + oneReservation.getFrom() + ";"
                                + oneReservation.getTo() + ";"
                                + oneReservation.getTime() + ";"
                                + oneReservation.getReservedSeats() + ";";

                rows.add(row);
            }
            Files.write(Paths.get(String.valueOf(file)), rows, StandardCharsets.UTF_8); // StandardOpenOption.APPEND
    }


    public void loadFromCsvFile(File file) throws FileNotFoundException {
        List<Reservation> reservationList = new ArrayList<>();

        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            reservationList.add(getReservationFromLine(scanner.nextLine()));
            }
        reservations.clear();
        reservations.addAll(reservationList);

    }

    private Reservation getReservationFromLine(String line) {

        Reservation reservation = new Reservation();
        List<Integer> list = new ArrayList<>();

        try (Scanner rowScanner = new Scanner(line)) {
            rowScanner.useDelimiter(DELIMITER);

            reservation.setName(rowScanner.next());
            reservation.setDate(rowScanner.next());
            reservation.setFrom(com.engeto.busticketreservation.model.Places.valueOf(rowScanner.next()));
            reservation.setTo(com.engeto.busticketreservation.model.Places.valueOf(rowScanner.next()));
            reservation.setTime(rowScanner.next());

            String seatsString = rowScanner.next();
            String seatsSubstring = seatsString.substring(1, seatsString.length()-1);
            for (String s : seatsSubstring.split(", ")) {
                list.add(Integer.parseInt(s));
            }
            reservation.setReservedSeats(list);
        }
        return reservation;
    }
}
