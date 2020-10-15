package com.engeto.busticketreservation.model;

import java.io.Serializable;
import java.util.List;

public class Reservation implements Serializable {


    private String name;
    private String date;
    private Places from;
    private Places to;
    private String time;
    private List<Integer> reservedSeats;


    public Reservation(String name, String date, Places from, Places to,
                       String time, List<Integer> reservedSeats) {
        this.name = name;
        this.date = date;
        this.from = from;
        this.to = to;
        this.time = time;
        this.reservedSeats = reservedSeats;

    }

    public Reservation() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Places getFrom() {
        return from;
    }

    public void setFrom(Places from) {
        this.from = from;
    }

    public Places getTo() {
        return to;
    }

    public void setTo(Places to) {
        this.to = to;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<Integer> getReservedSeats() {
        return reservedSeats;
    }

    public void setReservedSeats(List<Integer> reservedSeats) {
        this.reservedSeats = reservedSeats;
    }
    void addReservedSeats(int numberOfseat) {
        reservedSeats.add(numberOfseat);

    }

    @Override
    public String toString() {
        return "Reservation{" +
                ", name='" + name + '\'' +
                ", date='" + date + '\'' +
                ", from=" + from +
                ", to=" + to +
                ", time='" + time + '\'' +
                ", reservedSeats=" + reservedSeats +
                '}';
    }
}
