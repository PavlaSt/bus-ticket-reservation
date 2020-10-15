package com.engeto.busticketreservation.gui;

import javax.swing.filechooser.FileFilter;
import java.io.File;

public class CsvFileFilter extends FileFilter {
    @Override
    public boolean accept(File file) {
        String name = file.getName();

        return false;
    }

    @Override
    public String getDescription() {
        return "CSV files (*.csv)";
    }
}
