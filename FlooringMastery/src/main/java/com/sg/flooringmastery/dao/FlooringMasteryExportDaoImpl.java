/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author Jtooleyful
 */
public class FlooringMasteryExportDaoImpl implements FlooringMasteryExportDao{

public static final String EXPORT_FILE = "Backup/DataExport.txt";
    public void writeExportEntry(String entry, String date) throws FloorMasteryDaoException {
        PrintWriter out;
       
        try {
            out = new PrintWriter(new FileWriter(EXPORT_FILE, true));
        } catch (IOException e) {
            throw new FloorMasteryDaoException("Could not persist export information.", e);
        }
 
        out.println(entry + "," + date);
        out.flush();
    }
    
}
