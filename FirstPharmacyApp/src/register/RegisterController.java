package register;

import FXMLPage.FxmlLoader;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.GetFormManager;

import javax.swing.*;
import java.net.URL;


import java.util.ResourceBundle;

public class RegisterController implements Initializable {

    // Datepicker:
    @FXML
    private DatePicker personalBirthDay;
    // TextField and PasswordField:
    @FXML
    private JFXTextField userName;
    @FXML
    private JFXTextField personalName;
    @FXML
    private JFXTextField personalSurName;
    @FXML
    private JFXPasswordField userPassword;
    // Button and Checkbox:
    @FXML
    private JFXCheckBox confirmCheck;
    // Veriable:
    int i = 0;
    // Button Action Code:
    public void registerButtonAction(ActionEvent event) {
        RegisterAccountManager registerAccountManager = new RegisterAccountManager();
        if(i==1){
            registerAccountManager.addAccount(personalName.getText(),personalSurName.getText(),personalBirthDay.getValue().toString(),userName.getText(),userPassword.getText());
        }
        else
            JOptionPane.showMessageDialog(null,"Lütfen Onay Kutusunu İşaretleyin","Hata",JOptionPane.ERROR_MESSAGE);
    }
    public void checkBoxAction(ActionEvent event){
        if(confirmCheck.isSelected()){
           i = 1;
        }
        else{
            i = 0;
        }
    }
    public void closeButtonAction(ActionEvent event){ // İşlem Oldu Tamamdır.
        System.exit(0);
    }

    public void backButtonAction(ActionEvent event){
        GetFormManager formManager = new GetFormManager();
        ((Node) event.getSource()).getScene().getWindow().hide(); // Bir önceki sayfayı gizler.
        formManager.getForm("loginPage");
    }

        @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
