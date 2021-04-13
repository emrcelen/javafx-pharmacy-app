package database;

import javax.swing.*;
import java.sql.*;

public class DatabaseManager extends DatabaseConfiguration implements IDatabase {
    static Connection connect = null;
    PreparedStatement preparedStatement = null;
    Statement statement = null;
    ResultSet resultSet = null;

    public void login() {
        try {
            connect = DriverManager.getConnection(getDbURL(), getDbUserName(), getDbUserPass());
            //System.out.println("Connected to Microsoft SQL Server");
        } catch (SQLException e) {
            System.out.println("Oops, there's an error: ");
            e.printStackTrace();
        }
    }

    public void addUser(String name, String surName, String birthDay, String userName, String userPass) {
        login();
        try {
            String sqlCode = "insert into account (pname,psurname,pbirthDay,username,userpassword) " + "values (?,?,?,?,?)";
            preparedStatement = connect.prepareStatement(sqlCode);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, surName);
            preparedStatement.setString(3, birthDay);
            preparedStatement.setString(4, userName);
            preparedStatement.setString(5, userPass);
            int result = preparedStatement.executeUpdate();
            connect.close();
            preparedStatement.close();
            close();

        } catch (SQLException e) {
            System.out.println("Ooop, there's an error: ");
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            connect.close();
        } catch (SQLException e) {
            System.out.println("Ooop, there's an error: ");
            e.printStackTrace();
        }
    }

    public int getUser(String nameCheck, String passCheck) {
        String sqlCode = "SELECT username, userpassword from account where username='" + nameCheck + "' and userpassword='" + passCheck + "'";
        int veriable;
        try {
            connect = getConnect();
            statement = connect.createStatement();
            resultSet = statement.executeQuery(sqlCode);
            resultSet.next();
            if (resultSet.getString("username").equals(nameCheck) && resultSet.getString("userpassword").equals(passCheck))
                veriable = 1;

            else
                veriable = 0;
            connect.close();
            statement.close();
            resultSet.close();
            close();
            return veriable;
        } catch (Exception e) {
            System.out.println("Ooop, there's an error: ");
            e.printStackTrace();
            return 0;
        }
    }

    public Connection getConnect() throws SQLException {
        connect = DriverManager.getConnection(getDbURL(), getDbUserName(), getDbUserPass());
        return connect;
    }

    public int get(String sqlCode) {
        int count = 0;
        try {
            statement = connect.createStatement();
            resultSet = statement.executeQuery(sqlCode);
            while (resultSet.next()) {
                count = (resultSet.getInt(1));
            }
            statement.close();
            resultSet.close();
            close();
            return count;

        } catch (Exception e) {
            System.out.println("Ooop, there's an error: ");
            e.printStackTrace();
            return 0;
        }

    }

    public String getInfo(String sqlCode) {
        String veriable = null;
        try {
            connect = getConnect();
            statement = connect.createStatement();
            resultSet = statement.executeQuery(sqlCode);
            while (resultSet.next()) {
                veriable = (resultSet.getString(1));
            }
            connect.close();
            statement.close();
            resultSet.close();
            close();
            return veriable;
        } catch (Exception e) {
            System.out.println("Ooop, there's an error: ");
            e.printStackTrace();
            return null;
        }
    }

    public void addMedicine(String medicineNo, String medicineName, String producerName, String medicineType, String medicineMoney, String medicineAbout) {
        login();
        try {
            String sqlCode = "insert into medicine (medicineNo,medicineName,producerName,medicineType,medicineMoney,medicineAbout) " + "values (?,?,?,?,?,?)";
            preparedStatement = connect.prepareStatement(sqlCode);
            preparedStatement.setString(1, medicineNo);
            preparedStatement.setString(2, medicineName);
            preparedStatement.setString(3, producerName);
            preparedStatement.setString(4, medicineType);
            preparedStatement.setString(5, medicineMoney);
            preparedStatement.setString(6, medicineAbout);
            int result = preparedStatement.executeUpdate();
            connect.close();
            preparedStatement.close();
            close();
            JOptionPane.showMessageDialog(null, "İlaç Başarılı Bir Şekilde Sisteme Eklendi.", "İşlem Başarılı", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            System.out.println("Ooop, there's an error: ");
            e.printStackTrace();
        }
    }


}

