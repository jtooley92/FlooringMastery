/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.OrderFile;
import java.io.FileWriter;
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
public class FlooringMasteryDaoFileImplIT {
    FlooringMasteryDao testDao; 
    public FlooringMasteryDaoFileImplIT() {
    }
    
    @BeforeAll
    public static void setUpClass() {
        
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() throws Exception {
        String newTestFile = "flooringMasteryUnitTest.txt";
        FileWriter fw = new FileWriter(newTestFile);
        fw.append("1,Name Tag,CA,2.25,Laminate,115,2.34,3.45,4.56,5.67,6.78,7.89");
        fw.flush();
        fw.close();
        testDao = new FlooringMasteryDaoFileImpl(newTestFile);
    }
    
    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testAddOrder() throws FloorMasteryDaoException {
       
        OrderFile add = new OrderFile(1, "Name Tag", "CA", new BigDecimal("2.25"), "Laminate", new BigDecimal("115"), new BigDecimal("2.34"), new BigDecimal("3.45"), new BigDecimal("4.56"), new BigDecimal("5.67"), new BigDecimal("6.78"), new BigDecimal("7.89"));
        
        OrderFile testAdd = testDao.addOrder(1, add);
        
        assertEquals(testDao.getOrder(1, "10182020").getCustomerName(),"Name Tag", "Expected order number to be 1");

    }
    
    @Test
    public void testGetAllOrder() throws FloorMasteryDaoException{
    OrderFile order2 = new OrderFile(2, "name", "TX", new BigDecimal("4.45"), "Tile", new BigDecimal("248"), new BigDecimal("3.50"), new BigDecimal("4.15"), new BigDecimal("868.00"), new BigDecimal("1029.20"), new BigDecimal("84.43"), new BigDecimal("1981.63"));
    OrderFile order3 = new OrderFile(3, "Jim bob", "WA", new BigDecimal("9.25"), "Wood", new BigDecimal("240"), new BigDecimal("5.15"), new BigDecimal("4.75"), new BigDecimal("1236.00"), new BigDecimal("1140.00"), new BigDecimal("219.78"), new BigDecimal("2595.78"));
    
    List orders = testDao.getAllOrders("10142020");
    
    assertTrue(orders.contains(order2), "Expected to return this specific order from this date");
    assertTrue(orders.contains(order3), "Expected to return this specific order from this date");
    assertEquals(orders.size(), 2, "Expected 2 items in this lis of orders for 10142020");

    }
    
    @Test
    public void testRemoveOrder() throws FloorMasteryDaoException{
            OrderFile add = new OrderFile(2, "name remove", "WA", new BigDecimal("3.15"), "Wood", new BigDecimal("200"), new BigDecimal("2.34"), new BigDecimal("3.45"), new BigDecimal("4.56"), new BigDecimal("5.67"), new BigDecimal("6.78"), new BigDecimal("7.89"));

            OrderFile removed = testDao.removeOrder(2, "10182020");
            
            assertEquals(removed, add, "Expected the removed order to be the one i just added");
            assertFalse(testDao.getAllOrders("10182020").contains(removed), "Expected order to not exist in file any longer");
    }
}
