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
import models.Admin;
import models.Classe;
import models.Convert;
import models.Details;
import models.Etudiant;
import models.Personne;
import models.Professeur;

/**
 *
 * @author djiby
 */
public class DaoPersonne  implements IDao<Personne>{
    private final String SQL_SELECT_BY_CLASSE="select p.id,nom_complet,tuteur,libelle,classe_id,nbre_etudiant from personne p,classe cl where type='Etudiant' and classe_id=? and cl.id=p.classe_id";
    public final String SQL_INSERT="INSERT INTO `gestion_etudiant_liage3`.`personne` (`nom_complet`, `type`, `tuteur`, `modules`, `grade`, `classe_id`) VALUES (?,?,?,?,?,?);";
    private final String SQL_SELECT_PROFESSEUR="select * from personne where matricule=?";
    private final String SQL_SELECT_ALL_PROFESSEUR="select * from personne where type='Professeur'";
    private final String SQL_SELECT_CONNECT="select * from personne where login=? and pwd=?";
    private DaoMysql mysql;

    public DaoPersonne() {
        mysql=new DaoMysql();
    }
    
    public List<Etudiant> findByClasse(Classe classe){
        List<Etudiant> lEtudiants=new ArrayList<>();
        try {
        mysql.ouvrirConnexionBd();
            //3 preparation de la requete
            mysql.preparerRequete(SQL_SELECT_BY_CLASSE);
           

            mysql.getPs().setInt(1, classe.getId());
             ResultSet rs=mysql.executeSelect();
             while (rs.next()){
                 Etudiant etu=new Etudiant();
                 etu.setNomComplet(rs.getString("nom_complet"));
                 etu.setTuteur(rs.getString("tuteur"));
                 etu.setId(rs.getInt("id"));
             
             Classe cl=new Classe();
             cl.setId(rs.getInt("classe_id"));
             cl.setLibelle(rs.getString("libelle"));
             cl.setNbre(rs.getInt("nbre_etudiant"));
                etu.setCl(cl);
                lEtudiants.add(etu);
             }
        } catch (SQLException ex) {
            Logger.getLogger(DaoPersonne.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            mysql.CloseConnexion();
        }
        
        //recupération
        return lEtudiants;
    }
    
    public Professeur findProfesseurByMatricule(String matricule){
        Professeur professeur = null;
                //recherche
               
        try {
              mysql.ouvrirConnexionBd();
            //3 preparation de la requete
            mysql.preparerRequete(SQL_SELECT_PROFESSEUR);
            mysql.getPs().setString(1, matricule);
            ResultSet rs=mysql.executeSelect();
            if(rs.next()){
               professeur=new Professeur();
                professeur.setId(rs.getInt("id"));
                professeur.setNomComplet(rs.getString("nom_complet"));
                professeur.setGrade(rs.getString("grade"));
                professeur.setMatricule(rs.getString("matricule"));
              
                professeur.setModules(Convert.stringToList(rs.getString("modules")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoPersonne.class.getName()).log(Level.SEVERE, null, ex);
        }
           
                return professeur;
    }
    public List<Professeur> findProfesseur(Professeur professeur){
        List<Professeur> lProfesseurs= new ArrayList();
         
        try {
             mysql.ouvrirConnexionBd();
            //3 preparation de la requete
            mysql.preparerRequete(SQL_SELECT_ALL_PROFESSEUR);
            //mysql.getPs().setString(1,professeur.getType());
                ResultSet rs=mysql.executeSelect();
            while (rs.next()) {
            Professeur prof= new Professeur();
                 prof.setId(rs.getInt("id"));
                prof.setNomComplet(rs.getString("nom_complet"));
                prof.setGrade(rs.getString("grade"));
            lProfesseurs.add(prof);
        }
        
        } catch (SQLException ex) {
            Logger.getLogger(DaoPersonne.class.getName()).log(Level.SEVERE, null, ex);
        }
   
        //récupération
        return lProfesseurs;
    }
    public Personne findUserConnect(String login, String pwd){
        Personne pers=null;
        //traitement connexion
        try {
        mysql.ouvrirConnexionBd();
        mysql.preparerRequete(SQL_SELECT_CONNECT);
        
            mysql.getPs().setString(1, login);
            mysql.getPs().setString(2, pwd);
            ResultSet rs=mysql.executeSelect();
            if(rs.next()){
                if(rs.getString("type").trim().compareTo("Etudiant")==0){
                    pers=new Etudiant(rs.getString("tuteur"));
                }else{
                    if(rs.getString("type").trim().compareTo("Professeur")==0){
                    pers=new Professeur();
                     ((Professeur)pers).setGrade(rs.getString("grade"));
                ((Professeur)pers).setMatricule(rs.getString("matricule"));
              
               ((Professeur)pers).setModules(Convert.stringToList(rs.getString("modules")));
                }else{
                        pers=new Admin();
                }
            }
                 pers.setId(rs.getInt("id"));
                pers.setNomComplet(rs.getString("nom_complet"));
                pers.setLogin(rs.getString("login"));
                pers.setPwd(rs.getString("pwd"));
            }       
        } catch (SQLException ex) {
            Logger.getLogger(DaoPersonne.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pers;
    }
    @Override
    public int insert(Personne pers){
       int nbreLigne=0;
       Etudiant etu=new Etudiant();
       Professeur prof=new Professeur();
       Details classe=new Details();
       //Traitement d'insertion
        try {
         mysql.ouvrirConnexionBd();
            //3 preparation de la requete
            mysql.preparerRequete(SQL_INSERT);
       
            mysql.getPs().setString(1, pers.getNomComplet());
            mysql.getPs().setString(2, pers.getType());
            mysql.getPs().setString(3, etu.getTuteur());
            mysql.getPs().setString(4, Convert.listToString(prof.getModules()));
            mysql.getPs().setString(5, prof.getGrade());
            mysql.getPs().setString(6,Convert.listToString((List<String>) classe.getClasse()) );
            nbreLigne=mysql.executeMiseAJour();
            
        } catch (SQLException ex) {
            Logger.getLogger(DaoPersonne.class.getName()).log(Level.SEVERE, null, ex);
        }
       return nbreLigne;
    }
    
}
