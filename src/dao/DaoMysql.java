/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author djiby
 */
public class DaoMysql {
    private Connection conn=null;

    public PreparedStatement getPs() {
        return ps;
    }
     PreparedStatement ps=null;
    public void ouvrirConnexionBd(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
               //ouvrir la connexion
            conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/ge_etudiant_liage3", "root", "");
         } catch (ClassNotFoundException ex) {
               System.out.println("Erreur de chargement");
        } catch (SQLException ex) {
            System.out.println("Erreur d'ouverture"); 
        }
    }
    public void preparerRequete(String sql) {
        try {
        if(sql.toLowerCase().startsWith("INSERT")){
            
                PreparedStatement ps=conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            
            
        }else{
            ps=conn.prepareStatement(sql);  
        }
        } catch (SQLException ex) {
                Logger.getLogger(DaoMysql.class.getName()).log(Level.SEVERE, null, ex);
            }
       
        
    }
    public int executeMiseAJour() {
       int nbreLigne=0;
        try {
           nbreLigne= ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DaoMysql.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nbreLigne;
    }
    public ResultSet executeSelect(){
        ResultSet rs=null;
        try {
            rs=ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(DaoMysql.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    public void CloseConnexion(){
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DaoMysql.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    
}
