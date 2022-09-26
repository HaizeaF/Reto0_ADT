/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankadt;

import utilidades.Utilidades;

/**
 *
 * @author haize
 */
public class BankADT {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String opcion = null;
        
        System.out.println("1.Create customer \n2.Check the data \n3.Check the accounts of a customer "
                + "\n4.Create a customer’s account \n5.Add a client to an account \n6.Check the data of an account "
                + "\n7.Make movements in an account \n8.Check movements in an account");
        Utilidades.leerInt(1, 9, "Choose a option: ");
        switch(opcion){
            case "1":
               break;
            case "2":
               break;   
            case "3":
               break;
            case "4":
               break;
            case "5":
               break;
            case "6":
               break;
            case "7":
               break;
            case "8":
               break;
            default:
                System.out.println("FATAL ERROR");
                break;
        }
    }
    
}
