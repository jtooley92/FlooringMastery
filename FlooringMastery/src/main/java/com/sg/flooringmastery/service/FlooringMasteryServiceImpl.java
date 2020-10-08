/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.service;

import com.sg.flooringmastery.dao.FloorMasteryDaoException;
import com.sg.flooringmastery.dao.FlooringMasteryDao;
import com.sg.flooringmastery.dao.FlooringMasteryProductsDao;
import com.sg.flooringmastery.dao.FlooringMasteryProductsDaoException;
import com.sg.flooringmastery.dao.FlooringMasteryTaxesDao;
import com.sg.flooringmastery.dao.FlooringMasteryTaxesDaoException;
import com.sg.flooringmastery.dto.OrderFile;
import com.sg.flooringmastery.dto.Products;
import com.sg.flooringmastery.dto.Taxes;
import java.util.List;

/**
 *
 * @author Jtooleyful
 */
public class FlooringMasteryServiceImpl implements FlooringMasteryService {

    FlooringMasteryDao dao;
    FlooringMasteryTaxesDao taxDao;
    FlooringMasteryProductsDao productsDao;
    
     public FlooringMasteryServiceImpl() {
     }
     
    public FlooringMasteryServiceImpl(FlooringMasteryDao dao, FlooringMasteryTaxesDao taxDao, FlooringMasteryProductsDao ProductsDao) {
        this.dao = dao;
        this.taxDao = taxDao;
        this.productsDao = productsDao;
    }

    @Override
    public OrderFile addOrder(int orderNumber, OrderFile orderFile) throws FloorMasteryDaoException, FlooringMasteryTaxesDaoException, FlooringMasteryProductsDaoException {
        OrderFile addedOrder = dao.addOrder(orderNumber, orderFile);
        OrderFile newTaxInfo = getTaxRate(orderNumber, orderFile);
        OrderFile newProductInfo = getCost(orderNumber, orderFile);
        
        
        addedOrder.setTaxRate(newTaxInfo.getTaxRate());
        addedOrder.setCostPerSquareFoot(newProductInfo.getCostPerSquareFoot());
        addedOrder.setLaborCostPerSquareFoot(newProductInfo.getLaborCostPerSquareFoot());
        
        return addedOrder;

    }

    @Override
    public List<OrderFile> getAllOrders(String date) throws FloorMasteryDaoException {
        dao.getAllOrders(date);
        
        return dao.getAllOrders(date);
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
    public OrderFile getTaxRate(int orderNumber, OrderFile orderFile) throws FloorMasteryDaoException, FlooringMasteryTaxesDaoException {
        readTaxFile();
        OrderFile addOrder = dao.addOrder(orderNumber, orderFile);
        if (readTaxFile().get(0).getState().equals(addOrder.getState())) {
            addOrder.setTaxRate(readTaxFile().get(0).getTaxRate());
        } else if (readTaxFile().get(1).getState().equals(addOrder.getState())) {
            addOrder.setTaxRate(readTaxFile().get(1).getTaxRate());
        } else if (readTaxFile().get(2).getState().equals(addOrder.getState())) {
            addOrder.setTaxRate(readTaxFile().get(2).getTaxRate());
        } else if (readTaxFile().get(3).getState().equals(addOrder.getState())) {
            addOrder.setTaxRate(readTaxFile().get(3).getTaxRate());
        } else {
            throw new FlooringMasteryTaxesDaoException("State does not exist");

        }

        return addOrder;
    }

    @Override
    public List<Taxes> readTaxFile() throws FlooringMasteryTaxesDaoException {
        List<Taxes> newTaxList = taxDao.readTaxFile();

        return newTaxList;
    }

    @Override
    public List<Products> readProductsFile() throws FlooringMasteryProductsDaoException {
        List<Products> newProductsList = productsDao.readProductsFile();

        return newProductsList;
    }

    @Override
    public OrderFile getCost(int orderNumber, OrderFile orderFile) throws FloorMasteryDaoException, FlooringMasteryProductsDaoException {
        readProductsFile();
        OrderFile addOrder = dao.addOrder(orderNumber, orderFile);
        if (readProductsFile().get(0).getProductType().equals(addOrder.getProductType())) {
            addOrder.setLaborCostPerSquareFoot(readProductsFile().get(0).getLaborCostPerSquareFoot());
            addOrder.setCostPerSquareFoot(readProductsFile().get(0).getCostPerSquareFoot());
        } else if (readProductsFile().get(1).getProductType().equals(addOrder.getProductType())) {
            addOrder.setLaborCostPerSquareFoot(readProductsFile().get(1).getLaborCostPerSquareFoot());
            addOrder.setCostPerSquareFoot(readProductsFile().get(1).getCostPerSquareFoot());
        } else if (readProductsFile().get(2).getProductType().equals(addOrder.getProductType())) {
            addOrder.setLaborCostPerSquareFoot(readProductsFile().get(2).getLaborCostPerSquareFoot());
            addOrder.setCostPerSquareFoot(readProductsFile().get(2).getCostPerSquareFoot());
        } else if (readProductsFile().get(3).getProductType().equals(addOrder.getProductType())) {
            addOrder.setLaborCostPerSquareFoot(readProductsFile().get(3).getLaborCostPerSquareFoot());
            addOrder.setCostPerSquareFoot(readProductsFile().get(3).getCostPerSquareFoot());
        } else {
            throw new FlooringMasteryProductsDaoException("product does not exist");

        }

        return addOrder;
    }

}
