/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.OrderFile;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Jtooleyful
 */
public interface FlooringMasteryDao {
    OrderFile addOrder(int orderNumber, OrderFile orderFile)throws FloorMasteryDaoException;
    List<OrderFile> getAllOrders(String date) throws FloorMasteryDaoException;
    OrderFile removeOrder(int orderNumber, String date)throws FloorMasteryDaoException;
    OrderFile editOrder(int orderNumber, OrderFile orderFile)throws FloorMasteryDaoException;
}
