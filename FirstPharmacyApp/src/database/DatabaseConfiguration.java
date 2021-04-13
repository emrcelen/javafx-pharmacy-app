package database;

public class DatabaseConfiguration {

    ///-------> Variable  <-------\\\
    private final String dbURL = "jdbc:sqlserver://localhost:1433;databaseName=pharmacy";
    private final String dbUserName = "root";
    private final String dbUserPass = "admin";

    ///-------> Getter Method <-------\\\
    public String getDbUserName() {
        return dbUserName;
    }

    public String getDbUserPass() {
        return dbUserPass;
    }

    public String getDbURL() {
        return dbURL;
    }
}
