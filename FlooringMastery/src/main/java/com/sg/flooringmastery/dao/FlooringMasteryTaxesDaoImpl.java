/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import static com.sg.flooringmastery.dao.FlooringMasteryDaoFileImpl.ORDER_FILE;
import com.sg.flooringmastery.dto.Taxes;
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
public class FlooringMasteryTaxesDaoImpl implements FlooringMasteryTaxesDao {

    public static final String DELIMITER = ",";
    public static String TAXES_FILE = ("Data/Taxes.txt");
    private Map<String, Taxes> taxRateMap = new HashMap<>();

    @Override
    public List<Taxes> readTaxFile() throws FlooringMasteryTaxesDaoException{ 
        loadTaxes();
        
        return new ArrayList(taxRateMap.values());

    }

    private Taxes unmarshallTaxFile(String taxFileAsText) {
        String[] taxTokens = taxFileAsText.split(DELIMITER);
        Taxes taxesFromFile = new Taxes();
        taxesFromFile.setState(taxTokens[0]);
        taxesFromFile.setStateName(taxTokens[1]);
        taxesFromFile.setTaxRate(new BigDecimal(taxTokens[2]));
        
        return taxesFromFile;

    }
    
    private void loadTaxes() throws FlooringMasteryTaxesDaoException{
        Scanner sc;
         try {
        sc = new Scanner(
                new BufferedReader(
                        new FileReader(TAXES_FILE)));
         } catch(FileNotFoundException e){
             throw new FlooringMasteryTaxesDaoException("could not load tax info into memory", e);
         }
         
         String currentLine;
         Taxes selectedTaxes;
         sc.nextLine();
         while (sc.hasNextLine()){
             currentLine = sc.nextLine();
             selectedTaxes = unmarshallTaxFile(currentLine);
             taxRateMap.put(selectedTaxes.getState(), selectedTaxes);
         }
    }
}
