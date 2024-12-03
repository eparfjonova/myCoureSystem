package at.hakimst.ui;

import java.sql.SQLOutput;
import java.util.Scanner;

public class Cli {

    Scanner scan;

    public Cli(){
        this.scan = new Scanner(System.in);
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

    private void showMenue(){
        System.out.println("--- KURSMANAGEMENT ---");
        System.out.println("(1) Kurs eingeben \t(2) Alle Kurse anzeigen \t(3)");
        System.out.println("(x) Ende für das Komandozeilenmenue");
    }

    private void inputError(){
        System.out.println("Bitte nur die Zalen der Menüauswahl eingeben");
    }
}
