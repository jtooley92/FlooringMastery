/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.controller;

import com.sg.flooringmastery.dao.FloorMasteryDaoException;
import com.sg.flooringmastery.dao.FlooringMasteryDao;
import com.sg.flooringmastery.dto.OrderFile;
import com.sg.flooringmastery.ui.FlooringMasteryView;
import java.util.List;

/**
 *
 * @author Jtooleyful
 */
public class FlooringMasteryController {

    private FlooringMasteryDao dao;
    private FlooringMasteryView view;

    public FlooringMasteryController(FlooringMasteryDao dao, FlooringMasteryView view) {
        this.dao = dao;
        this.view = view;
    }

    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;

        try {
            while (keepGoing) {
                menuSelection = getMenuSelection();
                switch (menuSelection) {
                    case 1:
                        displayOrders();
                        break;
                    case 2:
                        addOrder();
                        break;
                    case 3:
                        //editOrder
                        break;
                    case 4:
                        //removeorder
                        break;
                    case 5:
                        //exportData
                        break;
                    case 6:
                        keepGoing = false;
                        break;
                }
            }
        } catch (FloorMasteryDaoException e) {
            System.out.println(e.getMessage());
        }
    }

    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }

    private void displayOrders() throws FloorMasteryDaoException {
        String date = view.getDisplayOrdersDate();
        List<OrderFile> ordersList = dao.getAllOrders(date);
        view.displayOrdersList(ordersList);

    }
    
    private void addOrder() throws FloorMasteryDaoException{
        OrderFile newOrderFile = view.addOrderFileInfo();
        dao.addOrder(newOrderFile.getOrderNumber(), newOrderFile);
    }
}
