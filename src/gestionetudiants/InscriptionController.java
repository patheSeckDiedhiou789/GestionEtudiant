/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionetudiants;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import metier.Service;
import models.Classe;
import models.Etudiant;
import models.Personne;


/**
 * FXML Controller class
 *
 * @author djiby
 */
public class InscriptionController implements Initializable {
private Service metier;
ObservableList<Classe> obClasses;
ObservableList<Etudiant> obEtudiants;
    @FXML
    private TextField textNom;
    @FXML
    private TextField textPrenom;
    @FXML
    private TextField textTuteur;
    @FXML
    private ComboBox<Classe> cmbClasse;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
         metier=new Service();
     
          
   
    }    

   

    @FXML
    private void handleCreerEtudiant(ActionEvent event) {
      
        
         String nomComplet=textNom.getText()+ textPrenom.getText();
         String tuteur=textTuteur.getText();
         int classe_id=Integer.parseInt(cmbClasse.getId());
        Etudiant etu=new Etudiant(nomComplet,classe_id,tuteur);
       if (metier.creerPersonne(etu)){
           Alert alert=new Alert(Alert.AlertType.INFORMATION);
           alert.setContentText("Etudiant ajoutée avec succès");
           alert.setTitle("Information");
           alert.show();
           obEtudiants.add(etu);
       }else{
            Alert alert=new Alert(Alert.AlertType.ERROR);
           alert.setContentText("Erreur d'insertion");
           alert.setTitle("Erreur");
           alert.show();
           
       }
    }

    @FXML
    private void showClasse(MouseEvent event) {
         obClasses=FXCollections.observableArrayList(metier.listerClasse());
          cmbClasse.setItems(obClasses);
    }
    
}
