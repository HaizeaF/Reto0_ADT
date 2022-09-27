/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankadt;

import controller.ModelFileImplementation;
import controller.ModelInterface;
import java.time.LocalDate;
import model.Account;
import model.AccountType;
import model.Customer;
import utilidades.Utilidades;

/**
 *
 * @author Julen
 */
public class BankADT{
    
    private static ModelInterface mi = new ModelFileImplementation();
    
    public static void main(String[] args) {
        Integer opcion = 0;

        System.out.println("1.Create customer \n2.Check the data \n3.Check the accounts of a customer "
                + "\n4.Create a customer’s account \n5.Add a client to an account \n6.Check the data of an account "
                + "\n7.Make movements in an account \n8.Check movements in an account");
        System.out.println("Choose an option: ");
        opcion = Utilidades.leerInt(1, 8);
        switch (opcion) {
            case 1:
                createCustomer(mi);
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                createAccount(mi);
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                break;
            case 8:
                break;
            default:
                break;
        }
    }
    
    private static void createCustomer(ModelInterface mi) {
        
        Customer customer;
        
        System.out.println("Introduce the id of the new Customer: ");
        Integer account_id = Utilidades.leerInt();
        System.out.println("Introduce the first name of the Customer: ");
        String firstName = Utilidades.introducirCadena();
        System.out.println("Introduce the last name of the Customer: ");
        String lastName = Utilidades.introducirCadena();
        System.out.println("Introduce the middle initial of the Customer: ");
        String middleInitial = Utilidades.introducirCadena();
        System.out.println("Introduce the street of the Customer: ");
        String street = Utilidades.introducirCadena();
        System.out.println("Introduce the city of the Customer: ");
        String city = Utilidades.introducirCadena();
        System.out.println("Introduce the state of the Customer: ");
        String state = Utilidades.introducirCadena();
        System.out.println("Introduce the zip of the Customer: ");
        String zip = Utilidades.introducirCadena();
        System.out.println("Introduce the phone of the Customer: ");
        Integer phone = Utilidades.leerInt();
        System.out.println("Introduce the email of the Customer: ");
        String email = Utilidades.introducirCadena();
        
        customer = new Customer(account_id, firstName, lastName, middleInitial, street, city, state, account_id, account_id, email);
        mi.CreateCustomer(customer);
        
    }

    private static void createAccount(ModelInterface mi1) {
        
        
        Account account;
        Customer customer;
        Character standarOrCredit;
        AccountType type = null;
        
        System.out.println("Introduce the id of the Customer: ");
        Integer customer_id = Utilidades.leerInt();
        customer = mi.CheckCustomer(customer_id);

        System.out.println("Introduce the id of the new Account: ");
        Integer account_id = Utilidades.leerInt();
        System.out.println("Introduce a sort description of the Account: ");
        String description = Utilidades.introducirCadena();
        System.out.println("Introduce the current balance of the Account: ");
        Float balance = Utilidades.leerFloat();
        System.out.println("Introduce the current amount of creditLine of the Account: ");
        Float creditLine = Utilidades.leerFloat();
        System.out.println("Introduce the beginning balance of the Account: ");
        Float beginBalance = Utilidades.leerFloat();
        System.out.println("Introduce the date of the beginning balance of the Account: ");
        LocalDate beginBalanceTimestamp = Utilidades.leerFechaDMA();
        do {
            System.out.println("Introduce the type of the Account: (S STANDARD / C CREDIT)");
            standarOrCredit = Utilidades.leerChar();
            if (standarOrCredit == 's' || standarOrCredit == 'S') {
                type = AccountType.STANDARD;
            } else if (standarOrCredit == 'c' || standarOrCredit == 'C') {
                type = AccountType.CREDIT;
            }else{
                System.out.println("That option is not valid");
            }
        } while (!(standarOrCredit == 's' || standarOrCredit == 'S' || standarOrCredit == 'c' || standarOrCredit == 'C'));

        account = new Account(account_id, description, balance, creditLine, beginBalance, beginBalanceTimestamp, type);
        
        mi.CreateAccount(account, customer.getCustomer_id());
    }

    

}
