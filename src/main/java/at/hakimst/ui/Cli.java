package at.hakimst.ui;

import java.util.Scanner;

public class Cli {

    Scanner scan;

    public Cli(){
        this.scan = new Scanner(System.in);
    }


    public void start(){

        String input = "-";
        while(input.equals("x")){

            showMenue();
            input = scan.nextLine();

        }
    }
}
