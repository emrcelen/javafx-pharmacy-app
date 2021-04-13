package otherpage;

import database.DatabaseManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import main.GetFormManager;

import javax.swing.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class PharmacologyController implements Initializable {

    @FXML
    private TableView<PharmacologyTableManager> table;
    @FXML
    private TableColumn<PharmacologyTableManager,String> medicineNoTable;
    @FXML
    private TableColumn<PharmacologyTableManager,String> medicineNameTable;
    @FXML
    private TableColumn<PharmacologyTableManager,String> companyNameTable;
    @FXML
    private TableColumn<PharmacologyTableManager,String> medicineMoneyTable;



    // Methods:
    ObservableList<PharmacologyTableManager> observableList = FXCollections.observableArrayList();
    public void getTable(String sqlCode){
        DatabaseManager db = new DatabaseManager();
        Statement statement =null;
        ResultSet resultSet = null;
        Connection connect = null;

        try{
            connect = db.getConnect();
            statement = connect.createStatement();
            resultSet = statement.executeQuery(sqlCode);
            while(resultSet.next()){
                observableList.add(new PharmacologyTableManager(resultSet.getString("medicineNo"),resultSet.getString("medicineName"),resultSet.getString("producerName"),resultSet.getString("medicineMoney")));
            }
            connect.close();
            statement.close();
            resultSet.close();
            db.close();
        }
        catch (Exception e){
            System.out.println("Ooop, there's an error: ");
            e.printStackTrace();
        }

    }


    // Action Event --> Table Methods:
    public void painKillerButtonAction(ActionEvent event){
        table.getItems().clear();
        getTable("SELECT medicineNo,medicineName,producerName,medicineMoney FROM medicine WHERE medicineType='Ağrı Kesici'");
        table.setItems(observableList);
        JOptionPane.showMessageDialog(null,"İsteğiniz Doğrultusunda Ağrı Kesici Türündeki İlaçlar Listelendi.","Bilgi Penceresi",JOptionPane.INFORMATION_MESSAGE);
    }
    public void heartAndVeinButtonAction(ActionEvent event){
        table.getItems().clear();
        getTable("SELECT medicineNo,medicineName,producerName,medicineMoney FROM medicine WHERE medicineType='Kalp ve Damar Sistemi'");
        table.setItems(observableList);
        JOptionPane.showMessageDialog(null,"İsteğiniz Doğrultusunda Kalp ve Damar Sistemi Türündeki İlaçlar Listelendi.","Bilgi Penceresi",JOptionPane.INFORMATION_MESSAGE);
    }
    public void digestiveSystemButtonAction(ActionEvent event){
        table.getItems().clear();
        getTable("SELECT medicineNo,medicineName,producerName,medicineMoney FROM medicine WHERE medicineType='Sindirim Sistemi'");
        table.setItems(observableList);
        JOptionPane.showMessageDialog(null,"İsteğiniz Doğrultusunda Sindirim Sistemi Türündeki İlaçlar Listelendi.","Bilgi Penceresi",JOptionPane.INFORMATION_MESSAGE);
    }
    public void specialButtonAction(ActionEvent event){
        table.getItems().clear();
        getTable("SELECT medicineNo,medicineName,producerName,medicineMoney FROM medicine WHERE medicineType='Özel Kullanım'");
        table.setItems(observableList);
        JOptionPane.showMessageDialog(null,"İsteğiniz Doğrultusunda Özel Kullanım Kategorisine Ait İlaçlar Listelendi.","Bilgi Penceresi",JOptionPane.INFORMATION_MESSAGE);
    }
    public void getAllMedicineButtonAction(ActionEvent event){
        table.getItems().clear();
        getTable("SELECT medicineNo,medicineName,producerName,medicineMoney FROM medicine");
        table.setItems(observableList);
        JOptionPane.showMessageDialog(null,"İsteğiniz Doğrultusunda Sisteme Kayıtlı Tüm İlaçlar Listelendi.","Bilgi Penceresi",JOptionPane.INFORMATION_MESSAGE);
    }
    public void clearTableViewButtonAction(ActionEvent event){
        table.getItems().clear();
        JOptionPane.showMessageDialog(null,"İsteğiniz Doğrultusunda Tablo Temizlendi.","Bilgi Penceresi",JOptionPane.INFORMATION_MESSAGE);

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
    public void addMedicineButtonAction(ActionEvent event){
        ((Node) event.getSource()).getScene().getWindow().hide(); // Bir önceki sayfayı gizler.
        formManager.getForm("addMedicinePage");
    }
    public void searchMedicineButtonAction(ActionEvent event){
        ((Node) event.getSource()).getScene().getWindow().hide(); // Bir önceki sayfayı gizler.
        formManager.getForm("searchMedicinePage");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        medicineNoTable.setCellValueFactory(new PropertyValueFactory<>("medicineNo"));
        medicineNameTable.setCellValueFactory(new PropertyValueFactory<>("medicineName"));
        companyNameTable.setCellValueFactory(new PropertyValueFactory<>("companyName"));
        medicineMoneyTable.setCellValueFactory(new PropertyValueFactory<>("medicineMoney"));
        getTable("SELECT medicineNo,medicineName,producerName,medicineMoney FROM medicine");
        table.setItems(observableList);
    }
}
