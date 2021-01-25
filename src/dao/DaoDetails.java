/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Convert;
import models.Details;

/**
 *
 * @author djiby
 */
public class DaoDetails implements IDao<Details> {
    private final String SQL_SELECT_MODULES="select * from details where professeur_id=? and classe_id=?";
    private final String SQL_INSERT="INSERT INTO `gestion_etudiant_liage3`.`details` (`classe_id`, `professeur_id`, `annee`, `modules`) VALUES (?,?,?,?);";
     private DaoMysql mysql;

    public DaoDetails() {
        mysql=new DaoMysql();
    }
    @Override
    public int insert(Details detail){
        int nbreLigne=0;
          try {
         mysql.ouvrirConnexionBd();
            //3 preparation de la requete
            mysql.preparerRequete(SQL_INSERT);
      
            mysql.getPs().setInt(1, detail.getClasse().getId());
            mysql.getPs().setInt(2, detail.getProfesseur().getId());
            mysql.getPs().setString(3, detail.getAnnee());
            mysql.getPs().setString(4, Convert.listToString(detail.getModules()));
           nbreLigne=mysql.executeMiseAJour();
            
        } catch (SQLException ex) {
            Logger.getLogger(DaoDetails.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
              mysql.CloseConnexion();
          }
        //traitement insertion
        return nbreLigne; 
    }
    public List<Details> findModulesProfesseur(Details modules){
         List<Details> lModules=new ArrayList<>();
        
           

            
        //recupération
        return lModules;
    }
    public List<String> findModules(Details details){
        List<String> lModules=new ArrayList<>();
          try {
         mysql.ouvrirConnexionBd();
            //3 preparation de la requete
            mysql.preparerRequete(SQL_SELECT_MODULES);
      
            mysql.getPs().setInt(1,  details.getClasse().getId());
            mysql.getPs().setInt(2, details.getProfesseur().getId());
             ResultSet rs=mysql.executeSelect();
              
        } catch (SQLException ex) {
            Logger.getLogger(DaoDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lModules;
    }
}
