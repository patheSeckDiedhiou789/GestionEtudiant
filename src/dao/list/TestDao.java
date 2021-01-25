/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.list;

import dao.DaoClasse;
import dao.DaoPersonne;
import java.util.Iterator;
import java.util.List;
import models.Classe;

/**
 *
 * @author djiby
 */
public class TestDao {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        // DaoClasse daoClasse=new DaoClasse();
         //Classe classe=new Classe("LIAGE3", 32);
         //daoClasse.insert(classe);
        //daoClasse.findAll().forEach((classe)->{
        //System.out.println(classe);
        //});
        // List<Classe> classes=daoClasse.findAll();
        //for (Classe cl : classes) { 
         //System.out.println(cl);
         
       DaoPersonne daoPersonne = new DaoPersonne();{
          daoPersonne.findByClasse(new Classe(2)).forEach(System.out::println);
          System.out.println(daoPersonne.findProfesseurByMatricule("MAT20212"));
    }
    }
}  
    //}
    
