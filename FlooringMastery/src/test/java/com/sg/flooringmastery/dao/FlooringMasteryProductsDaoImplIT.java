/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Products;
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
public class FlooringMasteryProductsDaoImplIT {
    FlooringMasteryProductsDao productsDao = new FlooringMasteryProductsDaoImpl();
    public FlooringMasteryProductsDaoImplIT() {
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
    public void testreadProductFile() throws FlooringMasteryProductsDaoException {
        Products prdct = new Products("Tile", new BigDecimal("3.50"), new BigDecimal("4.15"));
        
        List products = productsDao.readProductsFile();
        
         assertEquals(products.size(),4,  "Expected there to be 4 items in list");
         assertEquals(products.contains(prdct), true, "expected list to contain tax item");
    }
    
}
