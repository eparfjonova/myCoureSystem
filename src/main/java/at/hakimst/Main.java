package at.hakimst;

import at.hakimst.dataaccess.MySqlCourseReposetory;
import at.hakimst.ui.Cli;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {

        try{
            Cli myCli = new Cli(new MySqlCourseReposetory());
            myCli.start();
        } catch (SQLException e) {
            System.out.println("Datenbankfehler: " + e.getMessage() + " SQL State: " + e.getSQLState());
        } catch (ClassNotFoundException e) {
            System.out.println("Datenbankfehler: " + e.getMessage());
        }

    }
}