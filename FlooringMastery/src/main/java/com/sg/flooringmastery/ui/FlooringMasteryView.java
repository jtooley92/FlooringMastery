/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.ui;

import com.sg.flooringmastery.dao.FloorMasteryDaoException;
import com.sg.flooringmastery.dao.FlooringMasteryTaxesDaoException;
import com.sg.flooringmastery.dto.OrderFile;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Jtooleyful
 */
@Component
public class FlooringMasteryView {

    private UserIO io;
    
    @Autowired
    public FlooringMasteryView(UserIO io) {
        this.io = io;
    }

    public int printMenuAndGetSelection()throws FloorMasteryDaoException {
        try{
        io.print("<<Flooring Program>>");
        io.print("1. Display Orders");
        io.print("2. Add an Order");
        io.print("3. Edit an Order");
        io.print("4. Remove an Order");
        io.print("5. Export All Data");
        io.print("6. Quit");
        
        return io.readInt("Please select from the above choices", 1, 6);
        }catch (NumberFormatException e){
            throw new FloorMasteryDaoException("please enter a number between 1 and 6");
        }
    }
    
    public String getDisplayOrdersDate() {
    String date = io.readString("Please enter date in MMDDYYYY format");
    
    return date;
}
    
    public int getOrderNumber() {
        int orderNumber = io.readInt("Please enter order number");
        
        return orderNumber;
    }
    
    public OrderFile addOrderFileInfo() throws FloorMasteryDaoException {
        try{
        String name = io.readString("Please enter name");
        String state = io.readString("Please enter state abbreviation");
        String product = io.readString("Please enter product type starting with a capital letter");
        BigDecimal area = io.readBigDecimal("Please enter area in sqFt");
        if(area.compareTo(new BigDecimal("100")) == -1){
        throw new  FloorMasteryDaoException("order must be at least 100 sq ft");
    }else if (name.equals("")){
            throw new FloorMasteryDaoException("name cannot be empty");
        } else{
        OrderFile currentOrder = new OrderFile(1);
        currentOrder.setCustomerName(name);
        currentOrder.setState(state.toUpperCase());
        currentOrder.setProductType(product.substring(0, 1).toUpperCase() + product.substring(1).toLowerCase());
        currentOrder.setArea(area);
        return currentOrder;
        
        }
        }catch(NumberFormatException e){
            throw new FloorMasteryDaoException("please enter a numerical digit");
        }
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
     
     public OrderFile editOrderFileInfo(int orderNumber) throws FloorMasteryDaoException{
         try{
        String name = io.readString("Please enter name");
        String state = io.readString("Please enter state abbreviation");
        String product = io.readString("Please enter product type starting with a capital letter");
        BigDecimal area = io.readBigDecimal("Please enter area in sqFt");
        
        if(area.compareTo(new BigDecimal("100")) == -1){
            throw new FloorMasteryDaoException("order must be at least 100 sq feet");
        }else if (name.equals("")){
            throw new FloorMasteryDaoException("name cannot be empty");
        }else{
         System.out.println(name);
         System.out.println(state);
         System.out.println(product);
         System.out.println(area);
        }
        
         String answer = io.readString("would you like to commit these changes? (y/n)").toLowerCase();
        if (answer.equals("yes".toLowerCase()) || answer.equals("y".toLowerCase())){
        OrderFile currentOrder = new OrderFile(orderNumber);
        currentOrder.setCustomerName(name);
        currentOrder.setState(state.toUpperCase());
        currentOrder.setProductType(product);
        currentOrder.setArea(area);
        
        return currentOrder;
        
        } else {
            
            throw new FloorMasteryDaoException("changes not commited");
        }
        }catch(NumberFormatException e){
            throw new FloorMasteryDaoException("please enter a numerical digit");
}
       }

     
      public String printOrderAndConfirm(OrderFile orderFile) throws FloorMasteryDaoException{
          try{
          System.out.println(orderFile.toString());
          String yesOrNo = io.readString("are you sure you want to delete this file? (y/n)" ).toLowerCase();
          
          return yesOrNo;
          }catch(NullPointerException e){
              throw new FloorMasteryDaoException("order number does not exist");
          }
      }
     }
