package dungeonrpg.dungeonrpg.ui;

import java.util.Scanner;

public class Interface {

    private Scanner input;


    public Interface() {
        this.input = new Scanner(System.in);
       
    }

    public void strt() {
        System.out.println("You have entered the Dungeon. Darkness surrounds you.");

    }

    public int whereTo() {
        String in = "";
        while (!in.equals("1") && !in.equals("2") && !in.equals("3") && !in.equals("4")) {
            System.out.println("1. Move up");
            System.out.println("2. Move down");
            System.out.println("3. Move left");
            System.out.println("4. Move right.");
            in = this.input.nextLine();
        }
        return Integer.parseInt(in);
    }

    public String battle(String enmyName) {
        
        String in = "";
        while (!in.equals("1") && !in.equals("2") && !in.equals("3")) {
            System.out.println("\n1 Attack\n2 Defend\n3 Quit game");
            in = this.input.nextLine();
        }
        return in;
    }
    
    public void alert(int k,String name){
        //1 = wrong direction,2 = attack unsuccesful,3 = defence unsuccesful,
        //4 = defeat enemy, 5 = attack succsesfull, 6 = confrontation,7 = defence succesful,
        //8 = game over
        if (k == 1) {
            System.out.println("Can't go that way.");
        }
        if (k == 2) {
            System.out.println("You missed!");
        }
        if (k == 3) {
            System.out.println("Your defence has no effect");
        }
        if(k==4){
            System.out.println(name + " has been vanguished!");
        }
        if(k==5){
            System.out.println(name + " takes damage!");
        }
        if(k==6){
            System.out.println("You are confronted by an " + name + ".");
        }
        if(k==7){
            System.out.println("You parry the enemy succesfully!");
        }
        if(k==8){
            System.out.println(name + " strikes you down.\n GAME OVER");
        }
    }
}
