/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.service;

import com.sg.flooringmastery.dao.FloorMasteryDaoException;
import com.sg.flooringmastery.dao.FlooringMasteryProductsDaoException;
import com.sg.flooringmastery.dao.FlooringMasteryTaxesDaoException;
import com.sg.flooringmastery.dto.OrderFile;
import com.sg.flooringmastery.dto.Products;
import com.sg.flooringmastery.dto.Taxes;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Jtooleyful
 */
public interface FlooringMasteryService {
    OrderFile addOrder(int orderNumber, OrderFile orderFile)throws FloorMasteryDaoException, FlooringMasteryTaxesDaoException, FlooringMasteryProductsDaoException;
    List<OrderFile> getAllOrders(String date) throws FloorMasteryDaoException;
    OrderFile removeOrder(int orderNumber, String date)throws FloorMasteryDaoException;
    OrderFile editOrder(int orderNumber, OrderFile orderFile, String date)throws FloorMasteryDaoException, FlooringMasteryTaxesDaoException, FlooringMasteryProductsDaoException;
    OrderFile getTaxRate(int orderNumber, OrderFile orderFile) throws FloorMasteryDaoException, FlooringMasteryTaxesDaoException;
    OrderFile getCost(int orderNumber, OrderFile orderFile) throws FloorMasteryDaoException, FlooringMasteryProductsDaoException;
    List<Taxes> readTaxFile()throws FlooringMasteryTaxesDaoException;
    List<Products> readProductsFile()throws FlooringMasteryProductsDaoException;
    OrderFile getMaterialCost(int orderNumber, OrderFile orderFile) throws FloorMasteryDaoException;
    OrderFile getLaborCost(int orderNumber, OrderFile orderFile) throws FloorMasteryDaoException;
    OrderFile getTax(int orderNumber, OrderFile orderFile) throws FloorMasteryDaoException;
    OrderFile getTotal(int orderNumber, OrderFile orderFile) throws FloorMasteryDaoException;
}
