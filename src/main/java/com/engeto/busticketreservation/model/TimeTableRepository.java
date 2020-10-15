package com.engeto.busticketreservation.model;


import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class TimeTableRepository {
    private static final String DELIMITER = ";";
    private List<TimeTableLine> timeTableLineList;



    public TimeTableRepository() {
        this.timeTableLineList = new ArrayList<>();
    }

    public List<TimeTableLine> getTimeTableLineList() {
        return timeTableLineList;
    }



    /***********************************************************************/

    public void loadTimeTable(File inputFile) throws FileNotFoundException {
        List<TimeTableLine> timeTable = new ArrayList<>();
        Scanner scanner = new Scanner(inputFile);
        scanner.nextLine();
        while (scanner.hasNextLine()) {
            timeTable.add(getTimeTableLineFromLine(scanner.nextLine()));
        }

        timeTableLineList.clear();
        timeTableLineList.addAll(timeTable);
    }


    private TimeTableLine getTimeTableLineFromLine(String line) {

        DateTimeFormatter parser = DateTimeFormatter.ofPattern("H:mm");

        TimeTableLine timeTable = new TimeTableLine();
        try (Scanner rowScanner = new Scanner(line)) {
            rowScanner.useDelimiter(DELIMITER);

            timeTable.setLocalTime(LocalTime.parse(rowScanner.next(), parser));
            timeTable.setFrom(rowScanner.next());
            timeTable.setTo(rowScanner.next());
            timeTable.setCapacity(rowScanner.nextInt());

        }
        return timeTable;
    }

    /*****************************************************************************/
    //získání hodnot pro rozevírací seznamy v GUI
    public String[] getFromCombo() {
        String[] fromCSV = new String[timeTableLineList.size()];
        int i = 0;
        for (TimeTableLine item : timeTableLineList) {
            fromCSV[i] = item.from;
            i++;
        }
        Set<String> targetSet = new HashSet<>(Arrays.asList(fromCSV));
        String[] fromCombo = new String[targetSet.size()];
        targetSet.toArray(fromCombo);
        return fromCombo;
    }
    public List<String> getToPlacesCombo() {
        List<String> places = new ArrayList<>();

        for (TimeTableLine item : timeTableLineList) {
            places.add(item.from);
        }
        Set<String> targetSet = new HashSet<>(places);
        places.clear();
        places.addAll(targetSet);
        return places;
    }


}
