package at.hakimst;

import at.hakimst.dataaccess.MySqlDatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        System.out.println("Test");


        try {
            Connection myConnection = MySqlDatabaseConnection.getConnection("jdbc:mysql://127.0.0.1:3306/kurssystem", "root","");
            System.out.println("Verbundung aufgebaut");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}