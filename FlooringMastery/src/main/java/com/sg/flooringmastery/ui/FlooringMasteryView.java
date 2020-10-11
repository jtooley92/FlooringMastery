/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.ui;

import com.sg.flooringmastery.dto.OrderFile;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 *
 * @author Jtooleyful
 */
public class FlooringMasteryView {

    private UserIO io;

    public FlooringMasteryView(UserIO io) {
        this.io = io;
    }

    public int printMenuAndGetSelection(){
        io.print("<<Flooring Program>>");
        io.print("1. Display Orders");
        io.print("2. Add an Order");
        io.print("3. Edit an Order");
        io.print("4. Remove an Order");
        io.print("5. Export All Data");
        io.print("6. Quit");
        
        return io.readInt("Please select from the above choices", 1, 6);
    }
    
    public String getDisplayOrdersDate() {
    String date = io.readString("Please enter date in MMDDYYYY format");
    
    return date;
}
    
    public int getOrderNumber() {
        int orderNumber = io.readInt("Please enter order number");
        
        return orderNumber;
    }
    
    public OrderFile addOrderFileInfo() {
        //String orderNumber = io.readString("Please enter order number");
        String name = io.readString("Please enter name");
        String state = io.readString("Please enter state abbreviation");
        String product = io.readString("Please enter product type");
        BigDecimal area = io.readBigDecimal("Please enter area in sqFt");
        
        OrderFile currentOrder = new OrderFile(1);
        currentOrder.setCustomerName(name);
        currentOrder.setState(state);
        currentOrder.setProductType(product);
        currentOrder.setArea(area);
        return currentOrder;
    }
    
    public void displayOrdersList(List<OrderFile> orderList) {
       for (OrderFile currentOrder : orderList){
           String orderInfo = String.format("%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s", 
                   currentOrder.getOrderNumber(),
                   currentOrder.getCustomerName(),
                   currentOrder.getState(),
                   currentOrder.getTaxRate(),
                   currentOrder.getProductType(),
                   currentOrder.getArea(),
                   currentOrder.getCostPerSquareFoot(),
                   currentOrder.getLaborCostPerSquareFoot(),
                   currentOrder.getMaterialCost(),
                   currentOrder.getLaborCost(),
                   currentOrder.getTax(),
                   currentOrder.getTotal());
           io.print(orderInfo);
       }
       io.readString("Please hit enter to continue.");
       
    }
    
     public void displayRemoveResult(OrderFile orderFileRecord) {
        if (orderFileRecord != null) {
            io.print("Order successfully removed.");
        } else {
            io.print("No such order.");
        }
        io.readString("Please hit enter to continue.");
    }
     
     public OrderFile editOrderFileInfo(int orderNumber) {
        String name = io.readString("Please enter name");
        String state = io.readString("Please enter state abbreviation");
        String product = io.readString("Please enter product type");
        BigDecimal area = io.readBigDecimal("Please enter area in sqFt");
        
        OrderFile currentOrder = new OrderFile(orderNumber);
        currentOrder.setCustomerName(name);
        currentOrder.setState(state);
        currentOrder.setProductType(product);
        currentOrder.setArea(area);
        return currentOrder;
     }
}
