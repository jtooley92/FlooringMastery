/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Products;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Jtooleyful
 */
public class FlooringMasteryProductsDaoImpl implements FlooringMasteryProductsDao {
    
    public static final String DELIMITER = ",";
    public static String PRODUCTS_FILE = ("Data/Products.txt");
    private Map<String, Products > productsMap = new HashMap<>();

    @Override
    public List<Products> readProductsFile() throws FlooringMasteryProductsDaoException {
          loadProducts();
        
        return new ArrayList(productsMap.values());
    }
    
    private Products unmarshallProductsFile(String ProductsFileAsText) {
        String[] productTokens = ProductsFileAsText.split(DELIMITER);
        Products productsFromFile = new Products();
        productsFromFile.setProductType(productTokens[0]);
        productsFromFile.setCostPerSquareFoot(new BigDecimal(productTokens[1]));
        productsFromFile.setLaborCostPerSquareFoot(new BigDecimal(productTokens[2]));
        
        return productsFromFile;
    }   
    
     private void loadProducts() throws FlooringMasteryProductsDaoException{
        Scanner sc;
         try {
        sc = new Scanner(
                new BufferedReader(
                        new FileReader(PRODUCTS_FILE)));
         } catch(FileNotFoundException e){
             throw new FlooringMasteryProductsDaoException("could not load product info into memory", e);
         }
         
         String currentLine;
         Products selectedProduct;
         while (sc.hasNextLine()){
             currentLine = sc.nextLine();
             selectedProduct = unmarshallProductsFile(currentLine);
             productsMap.put(selectedProduct.getProductType(), selectedProduct);
         }
}
}