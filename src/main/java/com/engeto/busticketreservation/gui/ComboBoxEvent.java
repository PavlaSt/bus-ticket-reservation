package com.engeto.busticketreservation.gui;

import java.util.EventObject;

public class ComboBoxEvent extends EventObject {

    private int fromIndex;
    private int toIndex;
    private int timeIndex;

    public ComboBoxEvent(Object source) {
        super(source);
    }

    public ComboBoxEvent(Object source, int fromIndex, int toIndex, int timeIndex) {
        super(source);
        this.fromIndex = fromIndex;
        this.toIndex = toIndex;
        this.timeIndex = timeIndex;
    }

    public int getFromIndex() {
        return fromIndex;
    }

    public void setFromIndex(int fromIndex) {
        this.fromIndex = fromIndex;
    }

    public int getToIndex() {
        return toIndex;
    }

    public void setToIndex(int toIndex) {
        this.toIndex = toIndex;
    }

    public int getTimeIndex() {
        return timeIndex;
    }

    public void setTimeIndex(int timeIndex) {
        this.timeIndex = timeIndex;
    }
}
