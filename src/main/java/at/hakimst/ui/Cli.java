package at.hakimst.ui;

import at.hakimst.dataaccess.DatabaseException;
import at.hakimst.dataaccess.MyCourseReposetory;
import at.hakimst.dataaccess.MySqlCourseReposetory;
import domain.Course;

import java.sql.SQLOutput;
import java.util.List;
import java.util.Scanner;

public class Cli {

    Scanner scan;
    MyCourseReposetory repo;

    public Cli(MyCourseReposetory repo){
        this.scan = new Scanner(System.in);
        this.repo = repo;
    }


    public void start(){

        String input = "-";
        while(!input.equals("x")){

            showMenue();
            input = scan.nextLine();
            switch(input) {
                case "1":
                    System.out.println("Kurseingabe");
                    break;
                case "2":
                    System.out.println("Alle Kurse anzeigen");
                    showAllCoureses();
                    break;
                case "3":
                    break;
                case "x":
                    System.out.println("Auf Wiedersehen");
                    break;
                default:
                    inputError();
                    break;
            }
        }
        scan.close();
    }

    private void showAllCoureses() {
        List<Course> list = null;
        try{
            list = repo.getAll();
            if(list.size() > 0){
                for(Course course : list){
                    System.out.println(course);
                }
            }else{
                System.out.println("Kursliste leer!");
            }

        }catch(DatabaseException databaseException){
            System.out.println("Datenbanfeher bei Anzeige aller Kurse: " + databaseException.getMessage());
        }catch(Exception exception){
            System.out.println("Unbekannter Feher bei Anzeige aller Kurse: " + exception.getMessage());
        }
    }

    private void showMenue(){
        System.out.println("--- KURSMANAGEMENT ---");
        System.out.println("(1) Kurs eingeben \t(2) Alle Kurse anzeigen \t(3)");
        System.out.println("(x) Ende für das Komandozeilenmenue");
    }

    private void inputError(){
        System.out.println("Bitte nur die Zalen der Menüauswahl eingeben");
    }
}
