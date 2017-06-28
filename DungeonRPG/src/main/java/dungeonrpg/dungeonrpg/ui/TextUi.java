package dungeonrpg.dungeonrpg.ui;

import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Text based user interface.
 *
 * @author Jonne
 */
public class TextUi implements GameIF {

    private Scanner input;

    public TextUi() {
        this.input = new Scanner(System.in);

    }

    public boolean strt() {
        System.out.println("1. Start game");
        System.out.println("2. Exit");
        String in = this.input.nextLine();
        while (!in.equals("1") && !in.equals("2")) {
            in = this.input.nextLine();
        }
        if (in.equals("1")) {
            System.out.println("Darkness surrounds you.");
            return true;
        } else {
            return false;
        }
    }

    public int whereTo() {
        System.out.println("1. Move up");
        System.out.println("2. Move down");
        System.out.println("3. Move left");
        System.out.println("4. Move right.");
        System.out.println("5. Check inventory");
        System.out.println("6. Quit game.");
        String in = this.input.nextLine();
        while (!in.equals("1") && !in.equals("2") && !in.equals("3") && !in.equals("4") && !in.equals("5") && !in.equals("6")) {
            System.out.println("You can't do that right now.");
            in = this.input.nextLine();
        }
        return Integer.parseInt(in);
    }

    public String[] inventory(Set<String> inv) {
        int i = 0;
        for (String s : inv) {
            i++;
        }
        System.out.println("Choose an item from your inventory by typing its name.\nWrite empty if you don't want to equip anything.");
        String in1 = "";
        String in2 = "";
        System.out.println("Inventory:");
        inv.stream().forEach((name) -> {
            System.out.println(name);
        });
        while (!inv.contains(in1)) {
            System.out.print("Item slot 1: ");
            in1 = input.nextLine().toLowerCase();
            if (in1.equals("empty")) {
                break;
            }
        }
        if (i >= 2) {
            while (!inv.contains(in2)) {
                System.out.print("Item slot 2: ");
                in2 = input.nextLine().toLowerCase();
                if (in2.equalsIgnoreCase(in1)) {
                    System.out.println("That item is already equipped");
                    in2 = "";
                }
            }
        }
        String[] in = new String[2];
        in[0] = in1;
        in[1] = in2;
        return in;
    }

    public String battle() {
        String in = "";
        while (!in.equals("1") && !in.equals("2")) {
            System.out.println("\n1 Attack\n2 Defend");
            in = this.input.nextLine();
        }
        return in;
    }

    public void alert(int k, String name, int turnpoints) {
        //k: 1 = wrong direction,2 = attack unsuccesful,3 = defence unsuccesful,
        //4 = defeat enemy, 5 = attack succsesfull, 6 = confrontation,7 = defence succesful,
        //8 = game over, 9 = enemy attack success, 10 = enemy def success, 11 = enemy attacks
        //12 = enemy attack fails, 13 = enemy defends, 14 = item found, 15 = empty invntory,
        //16 = how many turnpoints left
        waitForABit(1000);
        if (k == 1) {
            System.out.println("Can't go that way.");
        }
        if (k == 2) {
            System.out.println("You missed!");
        }
        if (k == 3) {
            System.out.println("Your defence has no effect");
        }
        if (k == 4) {
            System.out.println(name + " has been vanguished!");
        }
        if (k == 5) {
            System.out.println(name + " takes damage!");
        }
        if (k == 6) {
            System.out.println("You are confronted by a" + name + ".");
        }
        if (k == 7) {
            System.out.println("You parry the enemy succesfully!");
        }
        if (k == 8) {
            System.out.println("You are too weak. The enemy strikes you down.\nGAME OVER");
        }
        if (k == 9) {
            System.out.println(name + " does damage, but you are still able to fight.");
        }
        if (k == 10) {
            System.out.println("You falter and lose one turnpoint.");
        }
        if (k == 11) {
            System.out.println(name + " attacks.");
        }
        if (k == 12) {
            System.out.println("It has no effect.");
        }
        if (k == 13) {
            System.out.println(name + " tries to parry you.");
        }
        if (k == 14) {
            System.out.println("You found a " + name);
        }
        if (k == 15) {
            System.out.println("Your inventory is empty.");
        }
        if (k == 16) {
            System.out.println("You have " + turnpoints + " turn points left.");
        }
    }

    public void waitForABit(int n) {
        try {
            TimeUnit.MILLISECONDS.sleep(n);
        } catch (InterruptedException e) {
        }
    }
}
