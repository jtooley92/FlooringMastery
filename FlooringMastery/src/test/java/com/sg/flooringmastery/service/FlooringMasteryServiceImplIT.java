/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.service;

import com.sg.flooringmastery.dao.FloorMasteryDaoException;
import com.sg.flooringmastery.dao.FlooringMasteryDao;
import com.sg.flooringmastery.dao.FlooringMasteryDaoMock;
import com.sg.flooringmastery.dao.FlooringMasteryProductsDao;
import com.sg.flooringmastery.dao.FlooringMasteryProductsDaoException;
import com.sg.flooringmastery.dao.FlooringMasteryProductsDaoImpl;
import com.sg.flooringmastery.dao.FlooringMasteryTaxesDao;
import com.sg.flooringmastery.dao.FlooringMasteryTaxesDaoException;
import com.sg.flooringmastery.dao.FlooringMasteryTaxesDaoImpl;
import com.sg.flooringmastery.dto.OrderFile;
import java.io.FileWriter;
import java.math.BigDecimal;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Jtooleyful
 */
public class FlooringMasteryServiceImplIT {
    String newTestFile = "TestDao/orders_10102020.txt";
    FlooringMasteryDao mockDao = new FlooringMasteryDaoMock(newTestFile);
    FlooringMasteryTaxesDao taxDao = new FlooringMasteryTaxesDaoImpl();
    FlooringMasteryProductsDao productsDao = new FlooringMasteryProductsDaoImpl();
    FlooringMasteryService testService = new FlooringMasteryServiceImpl(mockDao, taxDao, productsDao);
    public FlooringMasteryServiceImplIT() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() throws Exception{
        
        FileWriter fw = new FileWriter(newTestFile);
        fw.append("1,Name Tag,CA,2.25,Laminate,115,2.34,3.45,4.56,5.67,6.78,7.89");
        fw.flush();
        fw.close();
         
//        3,Jim bob,WA,9.25,Wood,240,5.15,4.75,1236.00,1140.00,219.78,2595.78
    }
    
    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testAddCalcs() throws FloorMasteryDaoException, FlooringMasteryTaxesDaoException, FlooringMasteryProductsDaoException {
        OrderFile add = new OrderFile(2,"Jim bob", "WA","Wood", new BigDecimal("240"));
        
        OrderFile serviceAdd = testService.addOrder(2, add);
        
        assertEquals(serviceAdd.getTaxRate(),new BigDecimal("9.25"), "Expected tax rate to be 9.25");
        assertEquals(serviceAdd.getCostPerSquareFoot(), new BigDecimal("5.15"), "Expected cost per square foot to be 5.15");
        assertEquals(serviceAdd.getLaborCostPerSquareFoot(),new BigDecimal("4.75"), "Expected labor cost per square foot to be 4.75");
        assertEquals(serviceAdd.getMaterialCost(),new BigDecimal("1236.00"), "Expected Material cost to be 1236.00");
        assertEquals(serviceAdd.getLaborCost(),new BigDecimal("1140.00"), "Expected labor cost to be 1140.00");
        assertEquals(serviceAdd.getTax(),new BigDecimal("219.78"), "Expected tax to be 219.78");
        assertEquals(serviceAdd.getTotal(),new BigDecimal("2595.78"), "Expected tax rate to be 2595.78");
    }
    
    
}
