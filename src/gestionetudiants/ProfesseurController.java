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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import metier.Service;
import models.Classe;
import models.Professeur;

/**
 * FXML Controller class
 *
 * @author djiby
 */
public class ProfesseurController implements Initializable {
private Service metier;
ObservableList<Classe> obClasses;
ObservableList<Professeur> obProfesseurs;
    @FXML
    private TableView<Professeur> tblvModule;
    @FXML
    private ComboBox<Classe> cmbClasse;
    @FXML
    private TableView<Professeur> tblvProfesseurs;
    @FXML
    private TableColumn<Professeur, String> tblcId;
    @FXML
    private TableColumn<Professeur, String> tblcNomComplet;
    @FXML
    private TableColumn<Professeur, String> tblcGrade;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO metier=new Service();
        Professeur professeur=new Professeur();
         metier=new Service();
        obProfesseurs=FXCollections.observableArrayList(metier.listerProfesseur(professeur));
        loadTAble();
    }  
    private void loadTAble(){
        tblcId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tblcNomComplet.setCellValueFactory(new PropertyValueFactory<>("nomComplet"));
        tblcGrade.setCellValueFactory(new PropertyValueFactory<>("grade"));
        tblvProfesseurs.setItems(obProfesseurs);
    }

    @FXML
    private void showClasse(MouseEvent event) {
        obClasses=FXCollections.observableArrayList(metier.listerClasse());
          cmbClasse.setItems(obClasses);
    }
    }    
    

