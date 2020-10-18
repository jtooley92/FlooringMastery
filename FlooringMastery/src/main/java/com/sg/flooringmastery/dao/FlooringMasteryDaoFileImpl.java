/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.OrderFile;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Set;
import static java.nio.file.StandardCopyOption.*;
import org.springframework.stereotype.Component;

/**
 *
 * @author Jtooleyful
 */
@Component
public class FlooringMasteryDaoFileImpl implements FlooringMasteryDao {

    private static LocalDate date = LocalDate.now();
    public static String dateString = DateTimeFormatter.ofPattern("MMddyyyy").format(date);
    public static final String DELIMITER = ",";
    public static String ORDER_FILE;
    public static final String EXPORT_FILE = "Backup/DataExport.txt";
    private Map<Integer, OrderFile> ordersMap = new HashMap<>();
    
    public FlooringMasteryDaoFileImpl(){
        
    }
    
    public FlooringMasteryDaoFileImpl(String testFile){
        ORDER_FILE = testFile;
    }
    

    @Override
    public OrderFile addOrder(int orderNumber, OrderFile orderFile) throws FloorMasteryDaoException {
        Set<Integer> keyset = ordersMap.keySet();
        int maxOrderNumber;
        try {
            loadOrders(dateString);
            maxOrderNumber = Collections.max(keyset) + 1;

        } catch (FloorMasteryDaoException | NoSuchElementException e) {
            // catches error thrown by loadorders not having an actual file yet and forces new file to be made.
            maxOrderNumber = 1;
        }

        orderFile.setOrderNumber(maxOrderNumber);
        OrderFile newOrder = ordersMap.put(maxOrderNumber, orderFile);
        writeOrders(dateString);

        return newOrder;
    }

    @Override
    public List<OrderFile> getAllOrders(String date) throws FloorMasteryDaoException {
        loadOrders(date);

        return new ArrayList(ordersMap.values());
    }

    @Override
    public OrderFile removeOrder(int orderNumber, String date) throws FloorMasteryDaoException {
        loadOrders(date);
        OrderFile removedOrder = ordersMap.remove(orderNumber);
        writeOrders(date);

        return removedOrder;
    }

    @Override
    public OrderFile editOrder(int orderNumber, OrderFile orderFile, String date) throws FloorMasteryDaoException {
        loadOrders(date);

        if (ordersMap.containsKey(orderNumber)) {
            OrderFile editOrder = ordersMap.put(orderNumber, orderFile);
            writeOrders(date);

            return editOrder;
        } else {
            throw new FloorMasteryDaoException("order does not exist");
        }
    }

    private String marshallOrderFile(OrderFile orderFile) {
        String orderFileAsText = orderFile.getOrderNumber() + DELIMITER;
        orderFileAsText += orderFile.getCustomerName() + DELIMITER;
        orderFileAsText += orderFile.getState() + DELIMITER;
        orderFileAsText += orderFile.getTaxRate() + DELIMITER;
        orderFileAsText += orderFile.getProductType() + DELIMITER;
        orderFileAsText += orderFile.getArea() + DELIMITER;
        orderFileAsText += orderFile.getCostPerSquareFoot() + DELIMITER;
        orderFileAsText += orderFile.getLaborCostPerSquareFoot() + DELIMITER;
        orderFileAsText += orderFile.getMaterialCost() + DELIMITER;
        orderFileAsText += orderFile.getLaborCost() + DELIMITER;
        orderFileAsText += orderFile.getTax() + DELIMITER;
        orderFileAsText += orderFile.getTotal();

        return orderFileAsText;
    }

    private OrderFile unmarshallOrderFile(String orderFileAsText) {
        String[] orderTokens = orderFileAsText.split(DELIMITER);
        String orderNumber = orderTokens[0];
        OrderFile orderFromFile = new OrderFile(Integer.parseInt(orderNumber));
        orderFromFile.setCustomerName(orderTokens[1]);
        orderFromFile.setState(orderTokens[2]);
        orderFromFile.setTaxRate(new BigDecimal(orderTokens[3]));
        orderFromFile.setProductType(orderTokens[4]);
        orderFromFile.setArea(new BigDecimal(orderTokens[5]));
        orderFromFile.setCostPerSquareFoot(new BigDecimal(orderTokens[6]));
        orderFromFile.setLaborCostPerSquareFoot(new BigDecimal(orderTokens[7]));
        orderFromFile.setMaterialCost(new BigDecimal(orderTokens[8]));
        orderFromFile.setLaborCost(new BigDecimal(orderTokens[9]));
        orderFromFile.setTax(new BigDecimal(orderTokens[10]));
        orderFromFile.setTotal(new BigDecimal(orderTokens[11]));

        return orderFromFile;
    }

    private void loadOrders(String date) throws FloorMasteryDaoException {
        ORDER_FILE = ("Orders/orders_" + date + ".txt");
        Scanner sc;
        try {
            sc = new Scanner(
                    new BufferedReader(
                            new FileReader(ORDER_FILE)));
        } catch (FileNotFoundException e) {
            throw new FloorMasteryDaoException("could not load orders into memory", e);
        }
        String currentLine;
        OrderFile selectedFile;
        while (sc.hasNextLine()) {
            currentLine = sc.nextLine();
            selectedFile = unmarshallOrderFile(currentLine);
            ordersMap.put(selectedFile.getOrderNumber(), selectedFile);
        }
        sc.close();
    }

    private void writeOrders(String date) throws FloorMasteryDaoException {
        ORDER_FILE = ("Orders/orders_" + date + ".txt");
        PrintWriter out;
        try {
            out = new PrintWriter(new FileWriter(ORDER_FILE));
        } catch (IOException e) {
            throw new FloorMasteryDaoException("could not save orders data", e);
        }
        String orderFileAsText;
        List<OrderFile> ordersList = new ArrayList(ordersMap.values());
        for (OrderFile selectedFile : ordersList) {
            orderFileAsText = marshallOrderFile(selectedFile);
            out.println(orderFileAsText);
            out.flush();
        }
        out.close();
    }

    private void exportOrders() throws FloorMasteryDaoException {

        List<String> exportList = new ArrayList();
        File[] folder = new File("Orders").listFiles();
        try {
            for (File file : folder) {
                Scanner s = new Scanner(file);
                String fileName = file.toString();
                String date = fileName.substring(14, 22);
                String currentLine;
                while (s.hasNextLine()) {
                    currentLine = s.nextLine();
                    LocalDate dates = LocalDate.parse(date, DateTimeFormatter.ofPattern("MMddyyyy"));
                    String newDate = dates.format(DateTimeFormatter.ofPattern("MM-dd-yyyy"));
                    exportList.add(currentLine + "," + newDate);

                }
                s.close();
            }
        } catch (FileNotFoundException e) {
            throw new FloorMasteryDaoException("could not load orders into memory", e);
        }
        //need a map for our write method to access files
        //need a marshall and write method to write files to new folder
        PrintWriter out;
        try {
            out = new PrintWriter(new FileWriter(EXPORT_FILE));
        } catch (IOException e) {
            throw new FloorMasteryDaoException("could not persist export data", e);
        }
        String exportFileAsText;

        for (String exportFile : exportList) {
            out.println(exportFile);
            out.flush();
        }
        out.close();
    }

    @Override
    public void export() throws FloorMasteryDaoException {

        exportOrders();
    }

    @Override
    public OrderFile getOrder(int orderNumber, String date) throws FloorMasteryDaoException {
        
        loadOrders(date);
        OrderFile gotOrder = ordersMap.get(orderNumber);
      
        return gotOrder;
    }

}
