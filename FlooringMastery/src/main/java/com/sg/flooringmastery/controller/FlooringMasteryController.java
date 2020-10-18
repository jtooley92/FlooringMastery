/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.controller;

import com.sg.flooringmastery.dao.FloorMasteryDaoException;
import com.sg.flooringmastery.dao.FlooringMasteryDao;
import com.sg.flooringmastery.dao.FlooringMasteryProductsDaoException;
import com.sg.flooringmastery.dao.FlooringMasteryTaxesDaoException;
import com.sg.flooringmastery.dto.OrderFile;
import com.sg.flooringmastery.service.FlooringMasteryService;
import com.sg.flooringmastery.service.FlooringMasteryServiceImpl;
import com.sg.flooringmastery.ui.FlooringMasteryView;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Jtooleyful
 */
@Component
public class FlooringMasteryController {

    private FlooringMasteryView view;
    private FlooringMasteryService service;
    
    @Autowired
    public FlooringMasteryController(FlooringMasteryService service, FlooringMasteryView view) {
        this.service = service;
        this.view = view;
    }

    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;

       
            while (keepGoing) {
                try{
                menuSelection = getMenuSelection();
                switch (menuSelection) {
                    case 1:
                        displayOrders();
                        break;
                    case 2:
                        addOrder();
                        break;
                    case 3:
                        editOrder();
                        break;
                    case 4:
                        removeOrder();
                        break;
                    case 5:
                        exportData();
                        break;
                    case 6:
                        keepGoing = false;
                        break;
                }
            }catch (FloorMasteryDaoException | FlooringMasteryTaxesDaoException | FlooringMasteryProductsDaoException e) {
            System.out.println(e.getMessage());
        } 
            
        }
    }

    private int getMenuSelection() throws FloorMasteryDaoException {
        return view.printMenuAndGetSelection();
    }

    private void displayOrders() throws FloorMasteryDaoException {
        String date = view.getDisplayOrdersDate();
        List<OrderFile> ordersList = service.getAllOrders(date);
        view.displayOrdersList(ordersList);

    }
    
    private void addOrder() throws FloorMasteryDaoException, FlooringMasteryTaxesDaoException, FlooringMasteryProductsDaoException {
        OrderFile newOrderFile = view.addOrderFileInfo();
        service.addOrder(newOrderFile.getOrderNumber(), newOrderFile);
    }
    
    private void removeOrder() throws FloorMasteryDaoException {
        int orderNumber = view.getOrderNumber();
        String date = view.getDisplayOrdersDate();
        OrderFile printOrder = service.getOrder(orderNumber, date);
        String yesOrNo = view.printOrderAndConfirm(printOrder);
        if (yesOrNo.equals("yes".toLowerCase()) || yesOrNo.equals("y".toLowerCase()))  {
        OrderFile removedOrder = service.removeOrder(orderNumber, date);
        view.displayRemoveResult(removedOrder);
        } else{
            throw new FloorMasteryDaoException("order not removed");
        }
    }
    
    private void editOrder() throws FloorMasteryDaoException, FlooringMasteryTaxesDaoException, FlooringMasteryProductsDaoException {
        String date = view.getDisplayOrdersDate();
        int orderNumber = view.getOrderNumber();
        OrderFile orderFile = view.editOrderFileInfo(orderNumber);
        OrderFile editedOrder = service.editOrder(orderNumber, orderFile, date);
        
    }
    
    private void exportData() throws FloorMasteryDaoException {
        service.export();
    }
}
