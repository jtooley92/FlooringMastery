/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.service;

import com.sg.flooringmastery.dao.FloorMasteryDaoException;
import com.sg.flooringmastery.dao.FlooringMasteryDao;
import com.sg.flooringmastery.dao.FlooringMasteryProductsDao;
import com.sg.flooringmastery.dao.FlooringMasteryTaxesDao;
import com.sg.flooringmastery.dto.OrderFile;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Jtooleyful
 */
public class FlooringMasteryServiceImpl implements FlooringMasteryService {
    

    FlooringMasteryDao dao;
    FlooringMasteryTaxesDao taxDao;
    FlooringMasteryProductsDao productsDao;
    public FlooringMasteryServiceImpl(FlooringMasteryDao dao, FlooringMasteryTaxesDao taxDao, FlooringMasteryProductsDao ProductsDao){
    this.dao = dao;
    this.taxDao = taxDao;
    this.productsDao = productsDao;
}
    @Override
    public OrderFile addOrder(int orderNumber, OrderFile orderFile) throws FloorMasteryDaoException {
        
        
        return dao.addOrder(orderNumber, orderFile);
    }

    @Override
    public List<OrderFile> getAllOrders(String date) throws FloorMasteryDaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OrderFile removeOrder(int orderNumber, String date) throws FloorMasteryDaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OrderFile editOrder(int orderNumber, OrderFile orderFile) throws FloorMasteryDaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BigDecimal getTaxRate() throws FloorMasteryDaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    }   
