/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

/**
 *
 * @author Jtooleyful
 */
public interface FlooringMasteryExportDao {
    public void writeExportEntry(String entry, String date) throws FloorMasteryDaoException;
        
    
}
