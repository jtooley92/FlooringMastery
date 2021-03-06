/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery;

import com.sg.flooringmastery.controller.FlooringMasteryController;
import com.sg.flooringmastery.dao.FlooringMasteryDao;
import com.sg.flooringmastery.dao.FlooringMasteryDaoFileImpl;
import com.sg.flooringmastery.dao.FlooringMasteryProductsDao;
import com.sg.flooringmastery.dao.FlooringMasteryProductsDaoImpl;
import com.sg.flooringmastery.dao.FlooringMasteryTaxesDao;
import com.sg.flooringmastery.dao.FlooringMasteryTaxesDaoImpl;
import com.sg.flooringmastery.service.FlooringMasteryService;
import com.sg.flooringmastery.service.FlooringMasteryServiceImpl;
import com.sg.flooringmastery.ui.FlooringMasteryView;
import com.sg.flooringmastery.ui.UserIO;
import com.sg.flooringmastery.ui.UserIOConsoleImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 * @author Jtooleyful
 */
public class FlooringMasteryApp {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext appContext
                = new AnnotationConfigApplicationContext();
        appContext.scan("com.sg.flooringmastery");
        appContext.refresh();

        FlooringMasteryController controller
                = appContext.getBean("flooringMasteryController", FlooringMasteryController.class);
        controller.run();
    }
}
