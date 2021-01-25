/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionetudiants;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import metier.Service;
import models.Personne;

/**
 *
 * @author Fatima
 */
public class ConnexionController implements Initializable {
    private Service metier;
     
    @FXML
    private TextField textLogin;
    @FXML
    private PasswordField textPwd;
    @FXML
    private Text lblError;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        lblError.setVisible(false);
        metier=new Service();
    }    

    @FXML
    private void handleConnexion(ActionEvent event) throws IOException {
        String login=textLogin.getText();
        String pwd=textPwd.getText();
       Personne pers=metier.seConnecter(login, pwd);
       if(pers==null){
           lblError.setVisible(true);
       }else{
            //Ferme la fenetre de connexxion
           textLogin.getScene().getWindow().hide();
       //ouvrir fen Menu
           AnchorPane view = FXMLLoader.load(getClass().getResource("vmenu.fxml"));
           Scene scene=new Scene(view);
       Stage stage=new Stage();
       stage.setScene(scene);
       stage.showAndWait();
       
       }
    }
    
}
