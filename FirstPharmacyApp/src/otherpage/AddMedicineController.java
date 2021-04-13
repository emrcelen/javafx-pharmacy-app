package otherpage;

import com.jfoenix.controls.JFXComboBox;
import database.DatabaseManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import main.GetFormManager;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

public class AddMedicineController implements Initializable {


    @FXML
    private TextField medicineName;
    @FXML
    private TextField medicineMoney;
    @FXML
    private TextField medicineCode;
    @FXML
    private JFXComboBox<String> selectCompanyComboBox;
    @FXML
    private TextField companyName;
    @FXML
    private TextField totalMedicineCount;
    @FXML
    private TextField companyCode;
    @FXML
    private TextArea medicineAbout;
    @FXML
    private JFXComboBox<String> selectMedicineComboBox;

    // Action Event -- > Methods:
    AddMedicineManager addMedicineManager = new AddMedicineManager();
    public void createCodeButtonAction(ActionEvent event){

        medicineCode.setText(addMedicineManager.createMedicineCode());
    }
    public void selectCompanyButtonAction(ActionEvent event){
        if(selectCompanyComboBox.getValue()==null){
            JOptionPane.showMessageDialog(null,"Herhangi bir ilaç üreticisi seçmedin.","Hata",JOptionPane.ERROR_MESSAGE);
        }
        else{
            companyName.setText(selectCompanyComboBox.getValue());
            totalMedicineCount.setText(String.valueOf(addMedicineManager.countMedicine(selectCompanyComboBox.getValue())));
            companyCode.setText(addMedicineManager.companyCode(selectCompanyComboBox.getValue()));
        }

    }
    public void closeButtonAction(ActionEvent event){
        System.exit(0);
    }
    // Action Event --> Page Methods:
    GetFormManager formManager = new GetFormManager();
    public void homePageButtonAction(ActionEvent event){
        ((Node) event.getSource()).getScene().getWindow().hide(); // Bir önceki sayfayı gizler.
        formManager.getForm("homePage");
    }
    public void pharmacologyButtonAction(ActionEvent event){
        ((Node) event.getSource()).getScene().getWindow().hide(); // Bir önceki sayfayı gizler.
        formManager.getForm("pharmacologyPage");
    }
    public void searchMedicineButtonAction(ActionEvent event){
        ((Node) event.getSource()).getScene().getWindow().hide(); // Bir önceki sayfayı gizler.
        formManager.getForm("searchMedicinePage");
    }

    // Methods:
    public void insertButtonAction(ActionEvent event){
        boolean result = true;
      if(medicineName.getText().length()>0 && medicineMoney.getText().length()>0 && medicineAbout.getText().length()>0){
          if(medicineCode.getText().length()<=0) {
              JOptionPane.showMessageDialog(null, "Lütfen İlaç Kodunu Oluşturun.", "Hata", JOptionPane.ERROR_MESSAGE);
              result = false;
          }
          if(companyCode.getText().length()<=0){
              JOptionPane.showMessageDialog(null,"Lütfen İlaç Şirketini Seçin.","Hata",JOptionPane.ERROR_MESSAGE);
              result = false;
          }
          if(selectMedicineComboBox.getValue().length()<=0){
              JOptionPane.showMessageDialog(null,"Lütfen İlaç Türünü Seçin.","Hata",JOptionPane.ERROR_MESSAGE);
              result = false;
          }
          else if(medicineAbout.getText().length()<300 && result != false){
              DatabaseManager db = new DatabaseManager();
              db.addMedicine(medicineCode.getText(),medicineName.getText(),companyName.getText(),selectMedicineComboBox.getValue(),medicineMoney.getText(),medicineAbout.getText());
              medicineName.setText("");
              medicineCode.setText("");
              medicineAbout.setText("");
              medicineMoney.setText("");
              companyName.setText("");
              companyCode.setText("");
              totalMedicineCount.setText("");
          }
      }
      else
          JOptionPane.showMessageDialog(null,"Lütfen Boş Yerleri Doldurunuz.","Hata",JOptionPane.ERROR_MESSAGE);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        medicineCode.setEditable(false);
        companyName.setEditable(false);
        companyCode.setEditable(false);
        totalMedicineCount.setEditable(false);
        ObservableList<String> companyList = FXCollections.observableArrayList("Abdi İbrahim","Novartis","Pfizer","Sanofi");
        ObservableList<String> medicineList = FXCollections.observableArrayList("Ağrı Kesici","Kalp ve Damar Sistemi","Sindirim Sistemi","Özel Kullanım");
        selectCompanyComboBox.setItems(companyList);
        selectMedicineComboBox.setItems(medicineList);
    }
}
