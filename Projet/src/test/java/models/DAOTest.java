/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author fbilhac
 */
public class DAOTest {
    
    private DAO myDAO; // L'objet à tester
    private DataSource myDataSource; // La source de données à utiliser
    
    public DAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws SQLException{
        myDataSource = DataSourceFactory.getDataSource();
        myDAO = new DAO(myDataSource);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of checkLogin method, of class DAO.
     */
    @Test
    public void testCheckLogin() throws Exception {
        System.out.println("checkLogin");
        String login = "";
        int password = 0;
        DAO instance = null;
        boolean expResult = false;
        boolean result = instance.checkLogin(login, password);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of listeProduits method, of class DAO.
     */
    @Test
    public void testListeProduits() throws Exception {
        System.out.println("listeProduits");
        DAO instance = null;
        List<ProductEntity> expResult = null;
        List<ProductEntity> result = instance.listeProduits();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of listeCommandes method, of class DAO.
     */
    @Test
    public void testListeCommandes() throws Exception {
        System.out.println("listeCommandes");
        String userName = "";
        DAO instance = null;
        List<PurchaseOrderEntity> expResult = null;
        List<PurchaseOrderEntity> result = instance.listeCommandes(userName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of orderNum method, of class DAO.
     */
    @Test
    public void testOrderNum() throws Exception {
        System.out.println("orderNum");
        DAO instance = null;
        int expResult = 0;
        int result = instance.orderNum();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ajoutCommande method, of class DAO.
     */
    @Test
    public void testAjoutCommande() throws Exception {
        System.out.println("ajoutCommande");
        int order_num = 0;
        int customer_id = 0;
        int product_id = 0;
        int quantity = 0;
        float shipping_cost = 0.0F;
        Date sales_date = null;
        Date shipping_date = null;
        String freight_company = "";
        DAO instance = null;
        int expResult = 0;
        int result = instance.ajoutCommande(order_num, customer_id, product_id, quantity, shipping_cost, sales_date, shipping_date, freight_company);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateQuantity method, of class DAO.
     */
    @Test
    public void testUpdateQuantity() throws Exception {
        System.out.println("updateQuantity");
        int quantity = 0;
        int product_id = 0;
        DAO instance = null;
        int expResult = 0;
        int result = instance.updateQuantity(quantity, product_id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteCommande method, of class DAO.
     */
    @Test
    public void testDeleteCommande() throws Exception {
        System.out.println("deleteCommande");
        int order_num = 0;
        DAO instance = null;
        int expResult = 0;
        int result = instance.deleteCommande(order_num);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateCommande method, of class DAO.
     */
    @Test
    public void testUpdateCommande() throws Exception {
        System.out.println("updateCommande");
        int quantity = 0;
        String freight_company = "";
        float shipping_cost = 0.0F;
        int order_num = 0;
        DAO instance = null;
        int expResult = 0;
        int result = instance.updateCommande(quantity, freight_company, shipping_cost, order_num);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of salesByProduct method, of class DAO.
     */
    @Test
    public void testSalesByProduct() throws Exception {
        System.out.println("salesByProduct");
        Date debut = null;
        Date fin = null;
        DAO instance = null;
        Map<String, Double> expResult = null;
        Map<String, Double> result = instance.salesByProduct(debut, fin);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of listeCommandesEnvoyees method, of class DAO.
     */
    @Test
    public void testListeCommandesEnvoyees() throws Exception {
        System.out.println("listeCommandesEnvoyees");
        String userName = "";
        Date dateNow = null;
        DAO instance = null;
        List expResult = null;
        List result = instance.listeCommandesEnvoyees(userName, dateNow);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
