package Screen;

import Player.Stats;
import Player.Level;
import Player.Equipment;
import Enemy.MonsterStats;
import Enemy.BossStats;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Bunker extends JFrame {
    private Stats stats;
    private Level level;
    private Equipment equipment;
    private MonsterStats monsterStats;
    private BossStats bossStats;
    private JLabel levelLabel, atkLabel, atkSpeedLabel, hpLabel, expLabel, moneyLabel;
    private JLabel weaponSlotLabel, helmetLabel, chestplateLabel, leggingsLabel, bootsLabel;

    public Bunker(Stats stats, Level level, Equipment equipment, MonsterStats monsterStats, BossStats bossStats) {
        this.stats = stats;
        this.level = level;
        this.equipment = equipment;
        this.monsterStats = monsterStats;
        this.bossStats = bossStats;
        setText();
        setImage();
        setButton();
        setDisplay();
    }

    void setText() {
        JLabel title = new JLabel("Bunker");
        title.setBounds(540, 0, 300, 100);
        title.setFont(new Font("Comic Sans MS", Font.BOLD, 72));
        title.setForeground(Color.white);
        add(title);

        levelLabel = new JLabel("Level: " + stats.getLevel());
        levelLabel.setBounds(300, 100, 600, 100);
        levelLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 32));
        levelLabel.setForeground(Color.white);
        add(levelLabel);

        atkLabel = new JLabel("ATK: " + stats.getATK()); // ใช้ getATK() โดยตรง
        atkLabel.setBounds(300, 150, 600, 100);
        atkLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 32));
        atkLabel.setForeground(Color.white);
        add(atkLabel);

        atkSpeedLabel = new JLabel("ATK Speed: " + String.format("%.1f", stats.getATKSpeed()));
        atkSpeedLabel.setBounds(300, 200, 300, 100);
        atkSpeedLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 32));
        atkSpeedLabel.setForeground(Color.white);
        add(atkSpeedLabel);

        hpLabel = new JLabel("HP: " + stats.getHp());
        hpLabel.setBounds(300, 250, 600, 100);
        hpLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 32));
        hpLabel.setForeground(Color.white);
        add(hpLabel);

        expLabel = new JLabel("EXP: " + stats.getExp() + " / " + stats.getMaxExp());
        expLabel.setBounds(300, 300, 600, 100);
        expLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 32));
        expLabel.setForeground(Color.white);
        add(expLabel);

        moneyLabel = new JLabel("Money: " + stats.getMoney());
        moneyLabel.setBounds(300, 350, 1000, 100);
        moneyLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 32));
        moneyLabel.setForeground(Color.white);
        add(moneyLabel);

        weaponSlotLabel = new JLabel("<html>Weapon<br>1/" + equipment.getSword_Chance() + "<br>+" + equipment.getSword() + " ATK</html>");
        weaponSlotLabel.setBounds(50, 450, 150, 150);
        weaponSlotLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
        weaponSlotLabel.setForeground(Color.BLACK);
        weaponSlotLabel.setBackground(Color.WHITE);
        weaponSlotLabel.setOpaque(true);
        weaponSlotLabel.setHorizontalAlignment(SwingConstants.CENTER);
        weaponSlotLabel.setVerticalAlignment(SwingConstants.CENTER);
        add(weaponSlotLabel);

        helmetLabel = new JLabel("<html>Helmet<br>1/" + equipment.getHelmet_Chance() + "<br>+" + equipment.getHelmet() + " HP</html>");
        helmetLabel.setBounds(250, 450, 150, 150);
        helmetLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
        helmetLabel.setForeground(Color.BLACK);
        helmetLabel.setBackground(Color.WHITE);
        helmetLabel.setOpaque(true);
        helmetLabel.setHorizontalAlignment(SwingConstants.CENTER);
        helmetLabel.setVerticalAlignment(SwingConstants.CENTER);
        add(helmetLabel);
        
        chestplateLabel = new JLabel("<html>Chestplate<br>1/" + equipment.getChestplate_Chance() + "<br>+" + equipment.getChestplate() + " HP</html>");
        chestplateLabel.setBounds(450, 450, 150, 150);
        chestplateLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
        chestplateLabel.setForeground(Color.BLACK);
        chestplateLabel.setBackground(Color.WHITE);
        chestplateLabel.setOpaque(true);
        chestplateLabel.setHorizontalAlignment(SwingConstants.CENTER);
        chestplateLabel.setVerticalAlignment(SwingConstants.CENTER);
        add(chestplateLabel);

        leggingsLabel = new JLabel("<html>Leggings<br>1/" + equipment.getLeggings_Chance() + "<br>+" + equipment.getLeggings() + " HP</html>");
        leggingsLabel.setBounds(650, 450, 150, 150);
        leggingsLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
        leggingsLabel.setForeground(Color.BLACK);
        leggingsLabel.setBackground(Color.WHITE);
        leggingsLabel.setOpaque(true);
        leggingsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        leggingsLabel.setVerticalAlignment(SwingConstants.CENTER);
        add(leggingsLabel);

        bootsLabel = new JLabel("<html>Boots<br>1/" + equipment.getBoots_Chance() + "<br>+" + equipment.getBoots() + " HP</html>");
        bootsLabel.setBounds(850, 450, 150, 150);
        bootsLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
        bootsLabel.setForeground(Color.BLACK);
        bootsLabel.setBackground(Color.WHITE);
        bootsLabel.setOpaque(true);
        bootsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        bootsLabel.setVerticalAlignment(SwingConstants.CENTER);
        add(bootsLabel);
    }

    void setButton() {
        JButton upstats = new JButton("Upstats");
        upstats.setFont(new Font("Comic Sans MS", Font.BOLD, 36));
        upstats.setBounds(900, 125, 300, 75);
        upstats.setFocusPainted(false);
        upstats.setBackground(Color.white);
        upstats.addActionListener((ActionEvent e) -> {
            Upstats upstatsWindow = new Upstats(stats, equipment);
            upstatsWindow.addWindowListener(new WindowAdapter() {
                public void windowClosed(WindowEvent e) {
                    updateUI();
                    System.out.println("Bunker updateUI called: ATK=" + stats.getATK() + ", HP=" + stats.getHp());
                }
            });
        });
        add(upstats);

        JButton travel = new JButton("Travel");
        travel.setFont(new Font("Comic Sans MS", Font.BOLD, 36));
        travel.setBounds(900, 225, 300, 75);
        travel.setFocusPainted(false);
        travel.setBackground(Color.white);
        travel.addActionListener((ActionEvent e) -> {
            SwingUtilities.invokeLater(() -> new Travel(stats, level, equipment, monsterStats, bossStats));
        });
        add(travel);

        JButton rollEquipment = new JButton("Roll Equipment");
        rollEquipment.setFont(new Font("Comic Sans MS", Font.BOLD, 36));
        rollEquipment.setBounds(900, 325, 300, 75);
        rollEquipment.setFocusPainted(false);
        rollEquipment.setBackground(Color.white);
        rollEquipment.addActionListener((ActionEvent e)->{
            System.out.println("Opening RollEquipment, Money: " + stats.getMoney() + ", Sword: " + equipment.getSword() + ", Sword_Chance: " + equipment.getSword_Chance());
            RollEquipment rollWindow = new RollEquipment(stats, equipment);
            rollWindow.addWindowListener(new WindowAdapter() {
                public void windowClosed(WindowEvent e) {
                    updateUI();
                    System.out.println("RollEquipment closed, Money: " + stats.getMoney() + ", Sword: " + equipment.getSword() + ", Sword_Chance: " + equipment.getSword_Chance());
                }
            });
        });
        add(rollEquipment);

        JButton exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Comic Sans MS", Font.BOLD, 36));
        exitButton.setBounds(900, 650, 300, 75);
        exitButton.setFocusPainted(false);
        exitButton.setBackground(Color.white);
        exitButton.addActionListener((ActionEvent e) -> {
            int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to Exit?", "Confirm Exit", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                System.exit(0);
            }
        });
        add(exitButton);
    }

    void setImage() {
        ImageIcon stickmanIcon = new ImageIcon(Bunker.class.getResource("/Image/Stickman.png"));
        Image scaledImage = stickmanIcon.getImage().getScaledInstance(400, 400, Image.SCALE_SMOOTH);

        JLabel stickmanLabel = new JLabel(new ImageIcon(scaledImage));
        stickmanLabel.setBounds(0, 20, 400, 400);
        add(stickmanLabel);
    }

    void updateUI() {
        levelLabel.setText("Level: " + stats.getLevel());
        atkLabel.setText("ATK: " + stats.getATK());
        atkSpeedLabel.setText("ATK Speed: " + String.format("%.1f", stats.getATKSpeed()));
        hpLabel.setText("HP: " + stats.getHp());
        expLabel.setText("EXP: " + stats.getExp() + " / " + stats.getMaxExp());
        moneyLabel.setText("Money: " + stats.getMoney());
        weaponSlotLabel.setText("<html>Weapon<br>1/" + equipment.getSword_Chance() + "<br>+" + equipment.getSword() + " ATK</html>");
        helmetLabel.setText("<html>Helmet<br>1/" + equipment.getHelmet_Chance() + "<br>+" + equipment.getHelmet() + " HP</html>");
        chestplateLabel.setText("<html>Chestplate<br>1/" + equipment.getChestplate_Chance() + "<br>+" + equipment.getChestplate() + " HP</html>");
        leggingsLabel.setText("<html>Leggings<br>1/" + equipment.getLeggings_Chance() + "<br>+" + equipment.getLeggings() + " HP</html>");
        bootsLabel.setText("<html>Boots<br>1/" + equipment.getBoots_Chance() + "<br>+" + equipment.getBoots() + " HP</html>");
        revalidate();
        repaint();
    }

    void setDisplay() {
        setSize(1280, 768);
        setResizable(false);
        setLocationRelativeTo(null);
        setUndecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Game: Darkflame Shadow");
        setLayout(null);
        setVisible(true);
        getContentPane().setBackground(Color.gray);
    }
}