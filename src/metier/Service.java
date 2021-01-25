/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier;

import dao.DaoClasse;
import dao.DaoDetails;
import dao.DaoPersonne;
import java.util.List;
import models.Classe;
import models.Details;
import models.Etudiant;
import models.Personne;
import models.Professeur;

/**
 *
 * @author djiby
 */
public class Service {
    private DaoClasse daoClasse;
    private DaoPersonne daoPersonne;
    private DaoDetails daoDetails;

    public Service() {
        daoClasse=new DaoClasse();
        daoPersonne=new DaoPersonne();
        daoDetails=new DaoDetails();
    }
    
    public boolean creerClasse(Classe classe){
        int nbreLigne= daoClasse.insert(classe);
        return nbreLigne != 0;
    }
    public List<Classe> listerClasse(){
        return daoClasse.findAll();
        
    }
    public List<Etudiant> listerEtudiantParClasse(Classe classe){
         return daoPersonne.findByClasse(classe);
}
    //on combine tout dans personne
   // public boolean creerEtudiant(Etudiant etu){
       // int nbreLigne= daoPersonne.insert(etu);
        //return nbreLigne != 0;
        //meme chose:
     //   return daoPersonne.insert(etu)!=0;
    //}
    // public boolean creerProfesseur(Professeur prof){
       // int nbreLigne= daoPersonne.insert(etu);
        //return nbreLigne != 0;
        //meme chose:
      //  return daoPersonne.insert(prof)!=0;
    //}
    public boolean creerPersonne(Personne pers){
         int nbreLigne=daoPersonne.insert(pers);
         return nbreLigne !=0;
    }
    public Professeur chercherProfesseur(String matricule){
        return daoPersonne.findProfesseurByMatricule(matricule);
    }
    public boolean attribuerClasse(Classe classe, Professeur prof, List<String> modules, String annee){
        if(prof.getId()==0){
           //creerPersonne(prof);
           int id = daoPersonne.insert(prof);
           prof.setId(id);
        }
        Details detail =new Details(annee, modules, classe, prof);
        return daoDetails.insert(detail)!=0;
    }
    public List<Professeur> listerProfesseur(Professeur professeur){
         
        return daoPersonne.findProfesseur(professeur);
    }
    public List<String> listerModulesProfesseurParClasse(Classe classe, Professeur professeur){
    Details details=new Details(classe, professeur);
    return daoDetails.findModules(details);
}
    public Personne seConnecter(String login, String mdp){
        return daoPersonne.findUserConnect(login, mdp);
    }
}