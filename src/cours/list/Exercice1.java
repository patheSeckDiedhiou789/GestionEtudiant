/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cours.list;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Fatima
 */
public class Exercice1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //1-creer une liste String
        List<String> lString=new ArrayList();
     //2-ajouter un element
        lString.add("Bonjour");
        lString.add("Au revoir");
        lString.add("les etudiants de la liage 3");
                
        //3- affichage de la liste
        //methode1
        lString.forEach((elt) -> {
            System.out.println(elt);
        });
            lString.add(0, "Debut");
            lString.forEach((elt) -> {
                System.out.println(elt);
        });
            
            //4-supprimer element
                lString.remove("Debut");
                lString.remove(2);
                System.out.println("affichage apres supression");
                lString.forEach((elt) -> {
                System.out.println(elt);
                });
                
              //5- modifier element de  la liste
              lString.set(0, "Bonsoir");
              
                System.out.println("affichage apres modification");
                lString.forEach((elt) -> {
                System.out.println(elt);
                });
                
                //6- rechercher un element de la liste
                if (lString.contains("Bonsoir")==true){
                    System.out.println("Element existe");
                }
                else{
                    System.out.println("EVement pas existe");
                }
                
                }
    
    
    
   
    
      
        
}
