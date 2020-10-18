/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Taxes;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Jtooleyful
 */
public class FlooringMasteryTaxesDaoImplIT {
    FlooringMasteryTaxesDao taxDao = new FlooringMasteryTaxesDaoImpl();
    public FlooringMasteryTaxesDaoImplIT() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testReadTaxes() throws FlooringMasteryTaxesDaoException {
        Taxes tx = new Taxes("TX", "Texas", new BigDecimal("4.45"));
        
        List allTaxes = taxDao.readTaxFile();
        
        assertEquals(allTaxes.size(),4,  "Expected there to be 4 items in list");
        assertEquals(allTaxes.contains(tx), true, "expected list to contain tax item");
        
    }
    
}
