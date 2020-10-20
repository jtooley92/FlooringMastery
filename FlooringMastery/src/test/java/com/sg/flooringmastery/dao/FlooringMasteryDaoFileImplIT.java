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
        String newTestFile = "TestDao/orders_10102020.txt";
        FileWriter fw = new FileWriter(newTestFile);
        fw.append("1,Name Tag,CA,2.25,Laminate,115,2.34,3.45,4.56,5.67,6.78,7.89");
        fw.flush();
        fw.close();
        testDao = new FlooringMasteryDaoMock(newTestFile);
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testAddOrder() throws FloorMasteryDaoException {

        OrderFile add = new OrderFile(2, "killer bee", "CA", new BigDecimal("2.25"), "Laminate", new BigDecimal("115"), new BigDecimal("2.34"), new BigDecimal("3.45"), new BigDecimal("4.56"), new BigDecimal("5.67"), new BigDecimal("6.78"), new BigDecimal("7.89"));

        OrderFile testAdd = testDao.addOrder(2, add);

        assertEquals(testAdd.getOrderNumber(), 2, "Expected order number to be 2");

    }

    @Test
    public void testGetAllOrder() throws FloorMasteryDaoException {
        OrderFile order2 = new OrderFile(2, "killer bee", "CA", new BigDecimal("2.25"), "Laminate", new BigDecimal("115"), new BigDecimal("2.34"), new BigDecimal("3.45"), new BigDecimal("4.56"), new BigDecimal("5.67"), new BigDecimal("6.78"), new BigDecimal("7.89"));
        OrderFile order1 = new OrderFile(1, "Name Tag", "CA", new BigDecimal("2.25"), "Laminate", new BigDecimal("115"), new BigDecimal("2.34"), new BigDecimal("3.45"), new BigDecimal("4.56"), new BigDecimal("5.67"), new BigDecimal("6.78"), new BigDecimal("7.89"));

        testDao.addOrder(2, order1);
        testDao.addOrder(3, order2);

        List orders = testDao.getAllOrders("10102020");

        assertTrue(orders.contains(order2), "Expected to return this specific order from this date");
        assertTrue(orders.contains(order1), "Expected to return this specific order from this date");
        assertEquals(orders.size(), 3, "Expected 2 items in this lis of orders for 10102020");

    }

    @Test
    public void testRemoveOrder() throws FloorMasteryDaoException {
        OrderFile add = new OrderFile(2, "name remove", "WA", new BigDecimal("3.15"), "Wood", new BigDecimal("200"), new BigDecimal("2.34"), new BigDecimal("3.45"), new BigDecimal("4.56"), new BigDecimal("5.67"), new BigDecimal("6.78"), new BigDecimal("7.89"));

        testDao.addOrder(2, add);

        OrderFile removed = testDao.removeOrder(2, "10102020");

        assertEquals(removed, add, "Expected the removed order to be the one i just added");
        assertFalse(testDao.getAllOrders("10102020").contains(removed), "Expected order to not exist in file any longer");
    }

    @Test
    public void testEditOrder() throws FloorMasteryDaoException {
        OrderFile add = new OrderFile(2, "name remove", "WA", new BigDecimal("3.15"), "Wood", new BigDecimal("200"), new BigDecimal("2.34"), new BigDecimal("3.45"), new BigDecimal("4.56"), new BigDecimal("5.67"), new BigDecimal("6.78"), new BigDecimal("7.89"));
        OrderFile edit = new OrderFile(2, "Jack harlow", "CA", new BigDecimal("3.15"), "Laminate", new BigDecimal("158"), new BigDecimal("2.34"), new BigDecimal("3.45"), new BigDecimal("4.56"), new BigDecimal("5.67"), new BigDecimal("6.78"), new BigDecimal("7.89"));
        testDao.addOrder(2, add);

        testDao.editOrder(2, edit, "10102020");

        assertEquals(testDao.getOrder(2, "10102020"), edit, "Expected order file number 2 to be changed with edit info");

    }

    @Test
    public void testGetOrder() throws FloorMasteryDaoException {
        OrderFile add = new OrderFile(2, "Jack harlow", "CA", new BigDecimal("3.15"), "Laminate", new BigDecimal("158"), new BigDecimal("2.34"), new BigDecimal("3.45"), new BigDecimal("4.56"), new BigDecimal("5.67"), new BigDecimal("6.78"), new BigDecimal("7.89"));
        testDao.addOrder(2, add);
        
        OrderFile got = testDao.getOrder(2, "10102020");
        
        assertEquals(got,add, "Expected retrieved order to be the order we added");
    }
}
