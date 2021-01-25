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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Classe;

/**
 *
 * @author djiby
 */
public class DaoClasse implements IDao<Classe>{
    private final String SQL_INSERT="INSERT INTO `ge_etudiant_liage3`.`classe` (`libelle`, `nbre_etudiant`) VALUES (?,?);"; 
    private final String SQL_SELECT_ALL="select * from classe";
   
    private DaoMysql mysql;

    public DaoClasse() {
        mysql=new DaoMysql();
    }
    
    @Override
    public int insert(Classe classe){
        int nbreLigne=0;
         
      try {
            //insertion et retrn nbre de ligne
           mysql.ouvrirConnexionBd();
            //3 preparation de la requete
            mysql.preparerRequete(SQL_INSERT);
        
            //remplacer le var de la requete par les valeurs
            mysql.getPs().setString(1, classe.getLibelle());
       
           mysql.getPs().setInt(2, classe.getNbre());
            //executer la requete
             nbreLigne=mysql.executeMiseAJour();
        
         } catch (SQLException ex) {
            Logger.getLogger(DaoClasse.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
          mysql.CloseConnexion();
      }
        return nbreLigne;
        
    }
    
    public List<Classe> findAll(){
        List<Classe>lClasses=new ArrayList<>();
        
          //chargement du driver
               try {     
            mysql.ouvrirConnexionBd();
            //3 preparation de la requete
            mysql.preparerRequete(SQL_SELECT_ALL);
            ResultSet rs=mysql.executeSelect();
       
            while(rs.next()){
                Classe cl=new Classe(rs.getInt("id"), rs.getString("libelle"), rs.getInt("nbre_etudiant"));
                lClasses.add(cl);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoClasse.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
                   mysql.CloseConnexion();
               }
       
        //Remplir liste
        return lClasses;
    }
    
}
