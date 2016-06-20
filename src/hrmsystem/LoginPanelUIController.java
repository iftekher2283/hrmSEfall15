/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hrmsystem;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author iftekher
 */
public class LoginPanelUIController implements Initializable {
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Text messageText;
    
    private String DB_URL = "jdbc:mysql://127.0.0.1/hrmsystemdb";
    private String DB_USER = "root";
    private String DB_PASS = "123";
    
    private String message;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String messageCheck = messageText.getText();
        if(!messageCheck.isEmpty()){
            message = "";
            messageText.setText("");
        }
    }    

    @FXML
    private void handleLoginAction(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();
        int pass_enc = password.hashCode();
        int count = 0;
        
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            Statement statement = connection.createStatement();
            
            String query1 = "select * from tbl_users;";
            ResultSet users = statement.executeQuery(query1);
            
            while(users.next()){
                String user_get = users.getString("username");
                int pass_get = Integer.parseInt(users.getString("password"));
                
                if(user_get.equals(username) && pass_get == pass_enc){
                    count = count + 1;
                }
            }
            if(count == 1){
                Parent root = FXMLLoader.load(getClass().getResource("MenuPanelUI.fxml"));
            
                Scene scene = new Scene(root);
            
                HRMSystem.getMainStage().setScene(scene);
                HRMSystem.getMainStage().show();
            }
            else{
                message = "Sorry! Username or Password didn't match";
                messageText.setText(message);
            }
        } catch (SQLException ex) {
            Logger.getLogger(NewEmployeeUIController.class.getName()).log(Level.SEVERE, null, ex);
            message = "Sorry! Database couldn't be connected";
            messageText.setText(message);
        } catch (IOException ex) {
            Logger.getLogger(LoginPanelUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handleLoginEnterAction(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();
        int pass_enc = password.hashCode();
        int count = 0;
        
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            Statement statement = connection.createStatement();
            
            String query1 = "select * from tbl_users;";
            ResultSet users = statement.executeQuery(query1);
            
            while(users.next()){
                String user_get = users.getString("username");
                int pass_get = Integer.parseInt(users.getString("password"));
                
                if(user_get.equals(username) && pass_get == pass_enc){
                    count = count + 1;
                }
            }
            if(count == 1){
                Parent root = FXMLLoader.load(getClass().getResource("MenuPanelUI.fxml"));
            
                Scene scene = new Scene(root);
            
                HRMSystem.getMainStage().setScene(scene);
                HRMSystem.getMainStage().show();
            }
            else{
                message = "Sorry! Username or Password didn't match";
                messageText.setText(message);
            }
        } catch (SQLException ex) {
            Logger.getLogger(NewEmployeeUIController.class.getName()).log(Level.SEVERE, null, ex);
            message = "Sorry! Database couldn't be connected";
            messageText.setText(message);
        } catch (IOException ex) {
            Logger.getLogger(LoginPanelUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
