package register;

import database.DatabaseManager;

import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;

public class RegisterAccountManager {
    Statement statement = null;
    ResultSet resultSet = null;
    Connection connect = null;
    int checkNumber;
    public void addAccount(String pName, String pSurName, String date, String userName, String userPass){
        DatabaseManager db = new DatabaseManager();
        String datem;
        DateTimeFormatter dt1=DateTimeFormatter.ofPattern("yyyy-MM-dd");
        datem = date;
        try{
            connect = db.getConnect();
            statement = connect.createStatement();
            resultSet = statement.executeQuery("SELECT COUNT(username) FROM account WHERE  username='"+userName+"'");
            resultSet.next();
            if(resultSet.getString(1).equals("0")){
                db.addUser(pName,pSurName,datem,userName,userPass);
                JOptionPane.showMessageDialog(null,"Kaydınız Başarılı Bir Şekilde Gerçekleştirildi.","Bilgi Penceresi",JOptionPane.INFORMATION_MESSAGE);
            }
            else{
                JOptionPane.showMessageDialog(null,"Sistem Üzerinde "+userName+" Kullanıcı Adı Bulunmaktadır.","Hata Penceresi",JOptionPane.ERROR_MESSAGE);
            }
            connect.close();
            statement.close();
            resultSet.close();
            db.close();
        }
        catch (Exception e){
            System.out.println("Oops, there's an error: ");
            e.printStackTrace();
        }

    }

}
