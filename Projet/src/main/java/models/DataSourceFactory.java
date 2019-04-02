/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author ssylvest
 */
import javax.sql.DataSource;

public class DataSourceFactory {

    public static DataSource getDataSource() {
        // Driver
        org.apache.derby.jdbc.ClientDataSource ds = new org.apache.derby.jdbc.ClientDataSource();
        // DatabaseName
        ds.setDatabaseName("sample");
        // UserName
        ds.setUser("app");
        // Password
        ds.setPassword("app");
        // The host on which Network Server is running
        ds.setServerName("localhost");
        // The port on which Network Server is listening
        ds.setPortNumber(1527);
        return ds;
    }
}