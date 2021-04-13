package otherpage;

import FXMLPage.FxmlLoader;
import com.jfoenix.controls.JFXComboBox;
import database.DatabaseManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.GetFormManager;

import javax.swing.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class SearchMedicineController implements Initializable {

    @FXML
    private TextField medicineName;
    @FXML
    private TextField medicineMoney;
    @FXML
    private TextField medicineCode;
    @FXML
    private TextField medicineType;
    @FXML
    private JFXComboBox<String> selectSearchTypeComboBox;
    @FXML
    private TextField searchTypeText;
    @FXML
    private TextField companyName;
    @FXML
    private TextField totalMedicineCount;
    @FXML
    private TextArea medicineAbout;
    @FXML
    private TextField companyCode;


    // Get Database Info Methods:
    DatabaseManager db = null;
    Statement statement = null;
    ResultSet resultSet = null;
    ResultSet resultSet2 = null;
    ResultSet resultSet3 = null;
    Connection connect = null;
    public int getDatabaseMedicineInfo(String sqlCode){
        int maxLength = 15;

        try{
            db = new DatabaseManager();
            connect = db.getConnect();
            statement = connect.createStatement();
            resultSet = statement.executeQuery(sqlCode);
            resultSet.next();
            if(resultSet.getString("medicineName").length()>maxLength)
                medicineName.setText(resultSet.getString("medicineName").substring(0,maxLength));
            else
            medicineName.setText(resultSet.getString("medicineName"));
            medicineMoney.setText(resultSet.getString("medicineMoney")+" TL");
            medicineCode.setText(resultSet.getString("medicineNo"));
            medicineType.setText(resultSet.getString("medicineType"));
            companyName.setText(resultSet.getString("producerName"));
            medicineAbout.setText(resultSet.getString("medicineAbout"));
            resultSet2 = statement.executeQuery("SELECT COUNT(*) FROM medicine WHERE producerName='"+companyName.getText()+"'");
            resultSet2.next();
            totalMedicineCount.setText(resultSet2.getString(1));
            resultSet3 = statement.executeQuery("SELECT * FROM producer WHERE producerName ='"+companyName.getText()+"'");
            resultSet3.next();
            companyCode.setText(resultSet3.getString("producerNo"));
            connect.close();
            statement.close();
            resultSet.close();
            resultSet2.close();
            resultSet3.close();
            db.close();
            return 1;
        }
        catch (Exception e){
            System.out.println("Oops, there's an error: ");
            e.printStackTrace();
            return 0;
        }
    }
    //Action Event Methods:
    public void searchButtonAction(ActionEvent event){
        int length = 0;
        int value = 0;
        if(selectSearchTypeComboBox.getValue() == null){
            JOptionPane.showMessageDialog(null,"Arama İşlemini Gerçekleştirmek İçin Lütfen Bir Arama Tipi Seçin.","Hata Mesajı",JOptionPane.ERROR_MESSAGE);
        }
        else if(selectSearchTypeComboBox.getValue().equalsIgnoreCase("Barkod Numarası")){
            if(searchTypeText.getText().length() == 19){
                value = getDatabaseMedicineInfo("SELECT * FROM medicine WHERE medicineNo='"+searchTypeText.getText()+"'");
                if(value == 0)
                    JOptionPane.showMessageDialog(null,"Aradığınız İlaç Sistem İçerisinde Bulunamadı","Hata Penceresi",JOptionPane.ERROR_MESSAGE);
            }
            else
                JOptionPane.showMessageDialog(null,"Barkod Numaraları Genelde 19 Bölümden Oluşur.\n\nYeterli Uzunlukta Barkod Numarası Girmediniz.","Hata Penceresi",JOptionPane.ERROR_MESSAGE);

        }
        else if(selectSearchTypeComboBox.getValue().equalsIgnoreCase("İlaç İsmi")){
            if(searchTypeText.getText().length()<5){
                JOptionPane.showMessageDialog(null,"Yeterli Uzunlukta İlaç İsmi Girmediniz.","Hata Penceresi",JOptionPane.ERROR_MESSAGE);
            }
            else{
                value = getDatabaseMedicineInfo("SELECT * FROM medicine WHERE medicineName='"+searchTypeText.getText()+"'");
                if(value == 0)
                    JOptionPane.showMessageDialog(null,"Aradığınız İlaç Sistem İçerisinde Bulunamadı","Hata Penceresi",JOptionPane.ERROR_MESSAGE);

            }
        }


    }
    public void clearSearchButtonAction(ActionEvent event){
        medicineName.setText(null);
        medicineMoney.setText(null);
        medicineCode.setText(null);
        medicineType.setText(null);
        companyName.setText(null);
        totalMedicineCount.setText(null);
        medicineAbout.setText(null);
        companyCode.setText(null);
        searchTypeText.setText(null);
        selectSearchTypeComboBox.setValue(null);
        JOptionPane.showMessageDialog(null,"İşlem başarılı bir şekilde gerçekleştirildi.","Bilgi Penceresi",JOptionPane.INFORMATION_MESSAGE);

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
    public void addMedicineButtonAction(ActionEvent event){
        ((Node) event.getSource()).getScene().getWindow().hide(); // Bir önceki sayfayı gizler.
        formManager.getForm("addMedicinePage");
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        medicineName.setEditable(false);
        medicineMoney.setEditable(false);
        medicineCode.setEditable(false);
        medicineType.setEditable(false);
        companyName.setEditable(false);
        totalMedicineCount.setEditable(false);
        medicineAbout.setEditable(false);
        companyCode.setEditable(false);
        ObservableList<String> searchList = FXCollections.observableArrayList("Barkod Numarası","İlaç İsmi");
        selectSearchTypeComboBox.setItems(searchList);
    }
}