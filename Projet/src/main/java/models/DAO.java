/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;


public class DAO {
        protected final DataSource myDataSource;
        
        
        /**
     * @param dataSource la source de données à utiliser
     */
        public DAO(DataSource dataSource) {
        this.myDataSource = dataSource;
    }
        
        /**
     * @param login le nom d'utilisateur
     * @param password le mot de passe de l'utilisateur
     * @return la vérification de la connexion de l'utilisateur ('true' ou 'false' si login et/ou password est incorrect) (table : CUSTOMER)
     * @throws DAOException
     */
    
}
