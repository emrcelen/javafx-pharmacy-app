package database;

public interface IDatabase {
    // Database İşlemlerimiz:
    void login();
    void close();
    void addUser(String name, String surName, String birthDay, String userName, String userPass);
    int getUser(String userName, String passWord);
}

