package login;

import FXMLPage.FxmlLoader;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import database.DatabaseManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.GetFormManager;


import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    // Text and Password Field:
    @FXML
    private JFXTextField securityCode;
    @FXML
    private JFXTextField userName;
    @FXML
    private JFXPasswordField userPassword;
    // Register and Login Button:
    @FXML
    private Button loginButton;
    @FXML
    private Button registerButton;
    // Close Button:
    @FXML
    private Button closeButton;
    // Security Button:
    @FXML
    private Button firstButton;
    @FXML
    private Button secondButton;

    // Methods:
    public void setButtonText(){
        LoginSecurityCode loginSecurityCode = new LoginSecurityCode();
        firstButton.setText(String.valueOf(LoginSecurityCode.a));
        secondButton.setText(String.valueOf(String.valueOf(LoginSecurityCode.b)));
        //secondButton.setText(b);
    }
    // Button Action Code:
    public void closeButtonAction(ActionEvent event){ // İşlem Oldu Tamamdır.
       System.exit(0);
    }
    public void loginButtonAction(ActionEvent event){
        LoginSecurityCode loginSecurityCode = new LoginSecurityCode();
        int checkNumber = loginSecurityCode.securityCheck(mathResult,securityCode.getText(),userName.getText(),userPassword.getText());
        if(checkNumber==1){
        ((Node) event.getSource()).getScene().getWindow().hide(); // Bir önceki sayfayı gizler.
        DatabaseManager db = new DatabaseManager();
        String pName = db.getInfo("SELECT pname FROM account WHERE username ='"+userName.getText()+"'");
        String pSurname = db.getInfo("SELECT psurname FROM account WHERE username ='"+userName.getText()+"'");
        JOptionPane.showMessageDialog(null,"Sevgili "+pName+" "+pSurname+" Umarım İyi Vakit Geçirebilirsin.","Hoşgeldiniz.",JOptionPane.PLAIN_MESSAGE);
        }
    }
    public void registerButtonAction(ActionEvent event){
        GetFormManager formManager = new GetFormManager();
        ((Node) event.getSource()).getScene().getWindow().hide(); // Bir önceki sayfayı gizler.
        formManager.getForm("registerPage");

    }
    int mathResult,a,b;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DatabaseManager db = new DatabaseManager();
        db.login();
        setButtonText();
        a = Integer.valueOf(firstButton.getText());
        b = Integer.valueOf(secondButton.getText());
        mathResult = a+b;
        db.close();
    }
}
