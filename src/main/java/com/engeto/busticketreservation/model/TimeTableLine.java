package com.engeto.busticketreservation.model;

import java.time.LocalTime;

public class TimeTableLine {


    LocalTime localTime;
    String from;
    String to;
    int capacity;


    public TimeTableLine() {

    }

    public TimeTableLine(LocalTime localTime, String from, String to, int capacity) {

        this.localTime = localTime;
        this.from = from;
        this.to = to;
        this.capacity = capacity;
    }


    public TimeTableLine(int timeTableId, LocalTime localTime, String from, String to, int capacity) {

        this.localTime = localTime;
        this.from = from;
        this.to = to;
        this.capacity = capacity;
    }


    public LocalTime getLocalTime() {
        return localTime;
    }

    public void setLocalTime(LocalTime localTime) {
        this.localTime = localTime;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return localTime + ";" + from + ";" + to + ";" + capacity;

    }
}
