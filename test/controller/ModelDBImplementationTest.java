/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.HashMap;
import model.Account;
import model.Customer;
import model.Movement;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author 2dam
 */
public class ModelDBImplementationTest {
    
    public ModelDBImplementationTest() {
    }

    /**
     * Test of createCustomer method, of class ModelDBImplementation.
     */
    @Test
    public void testCreateCustomer() throws Exception {
        System.out.println("createCustomer");
        Customer c = null;
        ModelDBImplementation instance = new ModelDBImplementation();
        instance.createCustomer(c);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkCustomer method, of class ModelDBImplementation.
     */
    @Test
    public void testCheckCustomer() throws Exception {
        System.out.println("checkCustomer");
        Integer idc = null;
        ModelDBImplementation instance = new ModelDBImplementation();
        Customer expResult = null;
        Customer result = instance.checkCustomer(idc);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createAccount method, of class ModelDBImplementation.
     */
    @Test
    public void testCreateAccount() throws Exception {
        System.out.println("createAccount");
        Account a = null;
        Integer idc = null;
        ModelDBImplementation instance = new ModelDBImplementation();
        instance.createAccount(a, idc);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addClient method, of class ModelDBImplementation.
     */
    @Test
    public void testAddClient() throws Exception {
        System.out.println("addClient");
        Integer idc = null;
        Integer ida = null;
        ModelDBImplementation instance = new ModelDBImplementation();
        instance.addClient(idc, ida);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkAccounts method, of class ModelDBImplementation.
     */
    @Test
    public void testCheckAccounts() throws Exception {
        System.out.println("checkAccounts");
        Integer idc = null;
        ModelDBImplementation instance = new ModelDBImplementation();
        HashMap<Integer, Account> expResult = null;
        HashMap<Integer, Account> result = instance.checkAccounts(idc);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of accountMovement method, of class ModelDBImplementation.
     */
    @Test
    public void testAccountMovement() throws Exception {
        System.out.println("accountMovement");
        Movement m = null;
        ModelDBImplementation instance = new ModelDBImplementation();
        instance.accountMovement(m);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkAccount_Movement method, of class ModelDBImplementation.
     */
    @Test
    public void testCheckAccount_Movement() throws Exception {
        System.out.println("checkAccount_Movement");
        Integer ida = null;
        ModelDBImplementation instance = new ModelDBImplementation();
        HashMap<Integer, Movement> expResult = null;
        HashMap<Integer, Movement> result = instance.checkAccount_Movement(ida);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkAccount method, of class ModelDBImplementation.
     */
    @Test
    public void testCheckAccount() throws Exception {
        System.out.println("checkAccount");
        Integer ida = null;
        ModelDBImplementation instance = new ModelDBImplementation();
        Account expResult = null;
        Account result = instance.checkAccount(ida);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
