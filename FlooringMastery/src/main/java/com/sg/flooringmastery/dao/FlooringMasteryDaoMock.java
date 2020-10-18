/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.OrderFile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Jtooleyful
 */
public class FlooringMasteryDaoMock implements FlooringMasteryDao {

    @Override
    public OrderFile addOrder(int orderNumber, OrderFile orderFile) throws FloorMasteryDaoException {
        return orderFile;
    }

    @Override
    public List<OrderFile> getAllOrders(String date) throws FloorMasteryDaoException {
        Map<Integer, OrderFile> files = new HashMap<>();
        return new ArrayList(files.values());
    }

    @Override
    public OrderFile removeOrder(int orderNumber, String date) throws FloorMasteryDaoException {
        OrderFile orderFile = new OrderFile();

        return orderFile;
    }

    @Override
    public OrderFile editOrder(int orderNumber, OrderFile orderFile, String date) throws FloorMasteryDaoException {

        return orderFile;
    }

    @Override
    public void export() throws FloorMasteryDaoException {
    }

    @Override
    public OrderFile getOrder(int orderNumber, String date) throws FloorMasteryDaoException {
        OrderFile orderFile = new OrderFile();
        
        return orderFile;
    }

}
