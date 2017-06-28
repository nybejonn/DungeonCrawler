package dungeonrpg.dungeonrpg.ui;

import dungeonrpg.dungeonrpg.entities.Loot;
import dungeonrpg.dungeonrpg.logic.GameWorld;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

/**
 * Graphical user interface class.
 *
 */
public class GUI extends JFrame implements GameIF {

    private JPanel mainPanel;
    private JPopupMenu invMenu;
    private JPopupMenu bttleMenu;
    private JLabel status;
    private JTextField statusb;
    private JPanel area;
    private JPanel menu;
    private JButton[][] menuButtons;
    private JButton[][] invButtons;
    private JButton[][] bttleButtons;
    private JButton[][] wrld;
    private final Integer size;
    private Integer x;
    private Integer y;
    private Integer lastMove;
    private String lastActn;
    private String invChoice;
    private String invChoice2;
    private boolean checkActn;
    private HashMap<String, Loot> loot;
    private final GameWorld gm;

    public GUI(int x, int y, GameWorld gm) {
        this.lastActn = "";
        this.checkActn = false;
        this.gm = gm;
        this.lastMove = 0;
        this.x = x - 1;
        this.y = y - 1;
        this.size = gm.getSize();
        this.loot = new HashMap<>();
        this.invChoice = "";
        this.invChoice2 = "";
    }

    @Override
    public boolean strt() {
        this.mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        this.area = new JPanel(new GridLayout(size, size));
        this.wrld = new JButton[size][size];
        this.menu = new JPanel(new GridLayout(6, 1));
        this.menuButtons = new JButton[6][1];
        this.invMenu = new JPopupMenu("");
        this.bttleMenu = new JPopupMenu("");
        this.invButtons = new JButton[gm.getLoot().size()][1];
        this.bttleButtons = new JButton[3][1];
        statusBar();
        buildGrid();
        makeMenu();
        makeInvMenu();
        makeBttleMenu();
        mainPanel.add(this.area);
        mainPanel.add(this.statusb);
        mainPanel.add(this.menu);

        add(mainPanel);
        wrld[x][y].setText("@");
        putSprites();
        pack();
        setVisible(true);
        this.menu.setVisible(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.updateStatus("Darkness surrounds you.");
        waitForABit(1000);
        alert(0, "", 0);
        this.menu.setVisible(true);
        return true;
    }

    public void setMove(int k) {
        synchronized (this) {
            this.lastMove = k;
            notify();
        }
    }

    public void buildGrid() {
        for (int r = size - 1; r >= 0; r--) {

            for (int c = 0; c < size; c++) {

                JButton button = new JButton("");
                button.setForeground(Color.GRAY);
                button.setBackground(Color.BLACK);
                Border line = new LineBorder(Color.BLACK);
                Border margin = new EmptyBorder(5, 15, 5, 15);
                Border compound = new CompoundBorder(line, margin);
                button.setBorder(compound);
                wrld[r][c] = button;

                this.area.add(wrld[r][c]);

            }
        }
    }

    private void statusBar() {
        this.statusb = new JTextField();
        this.status = new JLabel();
        statusb.setLayout(new BorderLayout());
        statusb.add(status, BorderLayout.CENTER);
    }

    private void makeMenu() {
        for (int i = 0; i < 6; i++) {
            JButton button = new JButton("");
            button.setForeground(Color.BLACK);
            button.setBackground(Color.GRAY);
            Border line = new LineBorder(Color.BLACK);
            Border margin = new EmptyBorder(5, 15, 5, 15);
            Border compound = new CompoundBorder(line, margin);
            button.setBorder(compound);
            this.menuButtons[i][0] = button;
            this.menu.add(this.menuButtons[i][0]);
        }
        this.menuButtons[0][0].setText("Up");
        this.menuButtons[0][0].addActionListener((ActionEvent e) -> {
            change("1");
            setMove(1);
        });

        this.menuButtons[2][0].setText("Right");
        this.menuButtons[2][0].addActionListener((ActionEvent e) -> {
            change("4");
            setMove(4);
        });

        this.menuButtons[1][0].setText("Down");
        this.menuButtons[1][0].addActionListener((ActionEvent e) -> {
            change("2");
            setMove(2);
        });

        this.menuButtons[3][0].setText("Left");
        this.menuButtons[3][0].addActionListener((ActionEvent e) -> {
            change("3");
            setMove(3);
        });

        this.menuButtons[4][0].setText("Inventory");
        this.menuButtons[4][0].addActionListener((ActionEvent e) -> {
            setMove(5);
            if (!loot.isEmpty()) {
                invMenu.show(mainPanel, 9, 100);
                this.menu.setVisible(false);
            }
        });

        this.menuButtons[5][0].setText("Quit");
        this.menuButtons[5][0].addActionListener((ActionEvent e) -> {
            setMove(6);
            close();
        });
    }

    private void makeInvMenu() {
        for (int i = 0; i < gm.getLoot().size(); i++) {
            JButton button = new JButton("");
            button.setForeground(Color.BLACK);
            button.setBackground(Color.GRAY);
            Border line = new LineBorder(Color.BLACK);
            Border margin = new EmptyBorder(5, 15, 5, 15);
            Border compound = new CompoundBorder(line, margin);
            button.setBorder(compound);
            this.invButtons[i][0] = button;
            this.invMenu.add(this.invButtons[i][0]);
        }
    }

    private void makeBttleMenu() {
        for (int i = 0; i < 3; i++) {
            JButton button = new JButton("");
            button.setForeground(Color.BLACK);
            button.setBackground(Color.WHITE);
            this.bttleButtons[i][0] = button;
            this.bttleMenu.add(this.bttleButtons[i][0]);
        }
        this.bttleButtons[0][0].setText("Attack");
        this.bttleButtons[0][0].addActionListener((ActionEvent e) -> {
            setLastActn("1");
            this.checkActn = true;
        });
        this.bttleButtons[1][0].setText("Defend");
        this.bttleButtons[1][0].addActionListener((ActionEvent e) -> {
            setLastActn("2");
            this.checkActn = true;
        });
        this.bttleButtons[2][0].setText("Escape");
        this.bttleButtons[2][0].addActionListener((ActionEvent e) -> {
            this.status.setText("Coward!");
        });
    }

    public void change(String d) {
        synchronized (this) {
            if (d.equals("1") && x < size - 1) {
                wrld[x][y].setText("");
                wrld[x + 1][y].setText("@");
                x++;
            }
            if (d.equals("2") && x > 0) {
                wrld[x][y].setText("");
                wrld[x - 1][y].setText("@");
                x--;
            }
            if (d.equals("4") && y < size - 1) {
                wrld[x][y].setText("");
                wrld[x][y + 1].setText("@");
                y++;
            }
            if (d.equals("3") && y > 0) {
                wrld[x][y].setText("");
                wrld[x][y - 1].setText("@");
                y--;
            }
            notify();
        }
    }

    public void updateStatus(String stat) {
        this.status.setText(stat);
    }

    public void close() {
        dispose();
    }

    public void putSprites() {
        this.gm.getEnmies().stream().forEach((e) -> {
            wrld[e.getPosY() - 1][e.getPosX() - 1].setText(e.getSprite());
        });
        Loot rD = this.gm.getLoot().get("rubber duck");
        wrld[rD.getPosY() - 1][rD.getPosX() - 1].setText(rD.getSprite());
        Loot wS = this.gm.getLoot().get("wooden shield");
        wrld[wS.getPosY() - 1][wS.getPosX() - 1].setText(wS.getSprite());
    }

    public void menuInventory(String name) {
        loot.put(name, gm.getLoot().get(name));
        this.invButtons[loot.size() - 1][0].setText(name);
        this.invButtons[loot.size() - 1][0].addActionListener((ActionEvent e) -> {
            setInvChoice(name);
            this.checkActn = true;
        });
    }

    @Override
    public int whereTo() {
        synchronized (this) {
            try {
                this.wait();
            } catch (InterruptedException e) {
            }
        }
        return this.lastMove;
    }

    @Override
    public String battle() {
        this.checkActn = false;
        bttleMenu.show(mainPanel, 9, 100);
        synchronized (this) {
            try {
                this.wait(4000);
            } catch (InterruptedException ex) {
            }
        }
        if (!bttleMenu.isShowing() || this.checkActn == false) {
            return battle();
        }
        return this.lastActn;
    }

    @Override
    public void alert(int k, String name, int turnpoints) {
        //k: 1 = wrong direction,2 = attack unsuccesful,3 = defence unsuccesful,
        //4 = defeat enemy, 5 = attack succsesfull, 6 = confrontation,7 = defence succesful,
        //8 = game over, 9 = enemy attack success, 10 = enemy def success, 11 = enemy attacks
        //12 = enemy attack fails, 13 = enemy defends, 14 = item found, 15 = empty invntory,
        //16 = how many turnpoints left
        if (k == 1) {
            updateStatus("Can't go that way!");
        }
        if (k == 2) {
            updateStatus("You missed!");
        }
        if (k == 3) {
            updateStatus("Your defence has no effect");
        }
        if (k == 4) {
            updateStatus("Victory!");
            waitForABit(500);
            this.bttleMenu.setVisible(false);
            this.menu.setVisible(true);
        }
        if (k == 5) {
            updateStatus(name + " takes damage!");
        }
        if (k == 6) {
            updateStatus("You are confronted by: " + name + ".");
            this.menu.setVisible(false);
            waitForABit(1000);
        }
        if (k == 7) {
            updateStatus("You parry the enemy succesfully!");
        }
        if (k == 8) {
            updateStatus("You are too weak. The enemy strikes you down.");
            waitForABit(1500);
            updateStatus("GAME OVER");
            waitForABit(1500);
            close();
        }
        if (k == 9) {
            updateStatus(name + " does damage, but you are still able to fight.");
        }
        if (k == 10) {
            updateStatus("You falter and lose one turnpoint.");
        }
        if (k == 11) {
            updateStatus(name + " attacks.");
        }
        if (k == 12) {
            updateStatus("It has no effect.");
        }
        if (k == 13) {
            updateStatus(name + " tries to parry you.");
        }
        if (k == 14) {
            updateStatus("You found: " + name);
            menuInventory(name);
        }
        if (k == 15) {
            updateStatus("Your inventory is empty.");
        }
        if (k == 16) {
            updateStatus("You have " + turnpoints + " turn points left.");
        }
        if (k != 16 && k != 14) {
            waitForABit(1200);
            updateStatus("Item 1: " + invChoice + " Item 2: " + invChoice2);
        }
    }

    @Override
    public String[] inventory(Set<String> inv) {
        this.checkActn = false;

        this.updateStatus("Choose an item for item slot 1");
        String[] s = new String[2];

        while (this.checkActn == false) {
            synchronized (this) {
                try {
                    this.wait(1000);
                } catch (InterruptedException e) {
                }
            }
            if (!this.invMenu.isShowing()) {
                invMenu.setVisible(true);
            }
        }
        this.checkActn = false;
        s[0] = invChoice;
        if (loot.size() == 1) {
            this.invMenu.setVisible(false);
            this.menu.setVisible(true);
            this.updateStatus("Item 1: " + invChoice + ", Item 2: " + invChoice2);
            return s;
        }
        this.updateStatus("Choose an item for item slot 2");
        while (this.checkActn == false) {
            synchronized (this) {
                try {
                    this.wait(1000);
                } catch (InterruptedException e) {
                }
            }
            if (!this.invMenu.isShowing()) {
                invMenu.setVisible(true);
            }
        }
        s[1] = invChoice;
        setInvChoice(s[0]);
        setInvChoice2(s[1]);
        this.invMenu.setVisible(false);
        this.menu.setVisible(true);
        this.updateStatus("Item 1: " + invChoice + ", Item 2: " + invChoice2);
        return s;
    }

    private void setLastActn(String s) {
        synchronized (this) {
            lastActn = s;
            notify();
        }
    }

    public void waitForABit(int n) {
        try {
            TimeUnit.MILLISECONDS.sleep(n);
        } catch (InterruptedException e) {
        }
    }

    public void setInvChoice(String s) {
        synchronized (this) {
            invChoice = s;
            notify();
        }
    }

    public void setInvChoice2(String s) {
        invChoice2 = s;
    }
}
