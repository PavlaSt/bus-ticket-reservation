package com.engeto.busticketreservation.gui;

import java.util.EventObject;
import java.util.List;

public class FormEvent extends EventObject {

    private String name;
    private String date;
    private int fromIndex;
    private int toIndex;
    private int timeIndex;
    private List<Integer> reservedSeats;


    public FormEvent(Object source) {
        super(source);
    }

    public FormEvent(Object source, String name, String date, int fromIndex, int toIndex,
                     int timeIndex, List<Integer> reservedSeats) {
        super(source);
        this.name = name;
        this.date = date;
        this.fromIndex = fromIndex;
        this.toIndex = toIndex;
        this.timeIndex = timeIndex;
        this.reservedSeats = reservedSeats;
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

    public int getFromIndex() {
        return fromIndex;
    }

    public int getToIndex() {
        return toIndex;
    }

    public int getTimeIndex() {
        return timeIndex;
    }

    public List<Integer> getReservedSeats() {
        return reservedSeats;
    }
}
