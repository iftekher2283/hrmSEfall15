/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hrmsystem;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 * FXML Controller class
 *
 * @author iftekher
 */
public class MenuPanelUIController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleEmployeeDetailsAction(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("NewEmployeeUI.fxml"));
            
            Scene scene = new Scene(root);
            
            HRMSystem.getMainStage().setScene(scene);
            HRMSystem.getMainStage().show();
        } catch (IOException ex) {
            Logger.getLogger(MenuPanelUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handleLeaveDetailsAction(ActionEvent event) {
    }

    @FXML
    private void handleSalaryDetailsAction(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("SalaryDetailsUI.fxml"));
            
            Scene scene = new Scene(root);
            
            HRMSystem.getMainStage().setScene(scene);
            HRMSystem.getMainStage().show();
        } catch (IOException ex) {
            Logger.getLogger(MenuPanelUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handleSignOutAction(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("LoginPanelUI.fxml"));
            
            Scene scene = new Scene(root);
            
            HRMSystem.getMainStage().setScene(scene);
            HRMSystem.getMainStage().show();
        } catch (IOException ex) {
            Logger.getLogger(MenuPanelUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
