package Screen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import GameFunction.Rolling;
import Player.Equipment;
import Player.Stats;

public class RollEquipment extends JFrame {
    Rolling equipment;
    Stats stats;
    Equipment accessory;
    private JButton Sword, Helmet, Chestplate, Leggings, Boots, exit;
    private JButton GetSword, GetHelmet, GetChestplate, GetLeggings, GetBoots;
    private JLabel RollEquipment, YougotSword, StatsSword, YougotHelmet, StatsHelmet, YougotChestplate, StatsChestplate,
            YougotLeggings, StatsLeggings, YougotBoots, StatsBoots, Money;
    private int Sword_Price, Helmet_Price, Chestplate_Price, Leggings_Price, Boots_Price;

    public RollEquipment(Stats stats, Equipment accessory) {
        this.stats = stats;
        this.accessory = accessory;
        this.equipment = new Rolling(accessory, stats);
        Sword_Price = 400;
        Helmet_Price = 200;
        Chestplate_Price = 300;
        Leggings_Price = 200;
        Boots_Price = 200;

        setButton();
        setText();
        setDisplay();
    }

    void setText() {
        RollEquipment = new JLabel("Roll Equipment");
        RollEquipment.setFont(new Font("Comic Sans MS", Font.BOLD, 32));
        RollEquipment.setBounds(220, 0, 300, 75);
        add(RollEquipment);

        Money = new JLabel("Money : " + stats.getMoney());
        Money.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
        Money.setBounds(10, 40, 300, 75);
        add(Money);

        YougotSword = new JLabel("You got : 1/" + accessory.getSword_Chance());
        YougotSword.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
        YougotSword.setBounds(10, 165, 200, 100);
        add(YougotSword);

        StatsSword = new JLabel("Stats : +" + accessory.getSword() + " ATK");
        StatsSword.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
        StatsSword.setBounds(10, 185, 200, 100);
        add(StatsSword);

        YougotHelmet = new JLabel("You got : 1/" + accessory.getHelmet_Chance());
        YougotHelmet.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
        YougotHelmet.setBounds(240, 165, 200, 100);
        add(YougotHelmet);

        StatsHelmet = new JLabel("Stats : +" + accessory.getHelmet() + " HP");
        StatsHelmet.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
        StatsHelmet.setBounds(240, 185, 200, 100);
        add(StatsHelmet);

        YougotChestplate = new JLabel("You got : 1/" + accessory.getChestplate_Chance());
        YougotChestplate.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
        YougotChestplate.setBounds(480, 165, 200, 100);
        add(YougotChestplate);

        StatsChestplate = new JLabel("Stats : +" + accessory.getChestplate() + " HP");
        StatsChestplate.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
        StatsChestplate.setBounds(480, 185, 200, 100);
        add(StatsChestplate);

        YougotLeggings = new JLabel("You got : 1/" + accessory.getLeggings_Chance());
        YougotLeggings.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
        YougotLeggings.setBounds(125, 355, 200, 100);
        add(YougotLeggings);

        StatsLeggings = new JLabel("Stats : +" + accessory.getLeggings() + " HP");
        StatsLeggings.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
        StatsLeggings.setBounds(125, 375, 200, 100);
        add(StatsLeggings);

        YougotBoots = new JLabel("You got : 1/" + accessory.getBoots_Chance());
        YougotBoots.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
        YougotBoots.setBounds(375, 355, 200, 100);
        add(YougotBoots);

        StatsBoots = new JLabel("Stats : +" + accessory.getBoots() + " HP");
        StatsBoots.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
        StatsBoots.setBounds(375, 375, 200, 100);
        add(StatsBoots);

    }

    void setButton() {
        exit = new JButton("X");
        exit.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
        exit.setBounds(590, -10, 60, 60);
        exit.setFocusPainted(false);
        exit.setOpaque(false);
        exit.setContentAreaFilled(false);
        exit.setBorderPainted(false);
        exit.addActionListener((ActionEvent e) -> {
            dispose();
        });
        add(exit);

        Sword = new JButton(String.format("<html>Sword<br>Price: %d</html>", Sword_Price));
        Sword.setBounds(10, 100, 150, 100);
        Sword.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
        Sword.setFocusPainted(false);
        Sword.setBackground(Color.black);
        Sword.setForeground(Color.white);
        Sword.addActionListener((ActionEvent e) -> {
            if (stats.getMoney() >= Sword_Price) {
                stats.setMoney(stats.getMoney() - Sword_Price);
                equipment.RollSword();
                Money.setText("Money : " + stats.getMoney());
                YougotSword.setText("You got : 1/" + equipment.RollResult);
                StatsSword.setText("Stats : +" + equipment.SwordValue + " ATK");
            }
        });
        add(Sword);

        GetSword = new JButton("Get Sword");
        GetSword.setBounds(10, 250, 150, 30);
        GetSword.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
        GetSword.setFocusPainted(false);
        GetSword.setBackground(Color.white);
        GetSword.setForeground(Color.black);
        GetSword.addActionListener((ActionEvent e) -> {
            equipment.ConfirmSword();
        });
        add(GetSword);

        Helmet = new JButton(String.format("<html>Helmet<br>Price: %d</html>", Helmet_Price));
        Helmet.setBounds(240, 100, 150, 100);
        Helmet.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
        Helmet.setFocusPainted(false);
        Helmet.setBackground(Color.black);
        Helmet.setForeground(Color.white);
        Helmet.addActionListener((ActionEvent e) -> {
            if (stats.getMoney() >= Helmet_Price) {
                stats.setMoney(stats.getMoney() - Helmet_Price);
                equipment.RollHelmet();
                Money.setText("Money : " + stats.getMoney());
                YougotHelmet.setText("You got : 1/" + equipment.RollResult);
                StatsHelmet.setText("Stats : +" + equipment.HelmetValue + " Hp");
            }
        });
        add(Helmet);

        GetHelmet = new JButton("Get Helmet");
        GetHelmet.setBounds(240, 250, 150, 30);
        GetHelmet.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
        GetHelmet.setFocusPainted(false);
        GetHelmet.setBackground(Color.white);
        GetHelmet.setForeground(Color.black);
        GetHelmet.addActionListener((ActionEvent e) -> {
            equipment.ConfirmHelmet();
        });
        add(GetHelmet);

        Chestplate = new JButton(String.format("<html>Chestplate<br>Price: %d</html>", Chestplate_Price));
        Chestplate.setBounds(480, 100, 150, 100);
        Chestplate.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
        Chestplate.setFocusPainted(false);
        Chestplate.setBackground(Color.black);
        Chestplate.setForeground(Color.white);
        Chestplate.addActionListener((ActionEvent e) -> {
            if (stats.getMoney() >= Chestplate_Price) {
                stats.setMoney(stats.getMoney() - Chestplate_Price);
                equipment.RollChestplate();
                Money.setText("Money : " + stats.getMoney());
                YougotChestplate.setText("You got : 1/" + equipment.RollResult);
                StatsChestplate.setText("Stats : +" + equipment.ChestplateValue + " Hp");
            }
        });
        add(Chestplate);

        GetChestplate = new JButton("Get Chestplate");
        GetChestplate.setBounds(480, 250, 150, 30);
        GetChestplate.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
        GetChestplate.setFocusPainted(false);
        GetChestplate.setBackground(Color.white);
        GetChestplate.setForeground(Color.black);
        GetChestplate.addActionListener((ActionEvent e) -> {
            equipment.ConfirmChestplate();
        });
        add(GetChestplate);

        Leggings = new JButton(String.format("<html>Leggings<br>Price: %d</html>", Leggings_Price));
        Leggings.setBounds(125, 290, 150, 100);
        Leggings.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
        Leggings.setFocusPainted(false);
        Leggings.setBackground(Color.black);
        Leggings.setForeground(Color.white);
        Leggings.addActionListener((ActionEvent e) -> {
            if (stats.getMoney() >= Leggings_Price) {
                stats.setMoney(stats.getMoney() - Leggings_Price);
                equipment.RollLeggings();
                Money.setText("Money : " + stats.getMoney());
                YougotLeggings.setText("You got : 1/" + equipment.RollResult);
                StatsLeggings.setText("Stats : +" + equipment.LeggingsValue + " Hp");
            }
        });
        add(Leggings);

        GetLeggings = new JButton("Get Leggings");
        GetLeggings.setBounds(125, 440, 150, 30);
        GetLeggings.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
        GetLeggings.setFocusPainted(false);
        GetLeggings.setBackground(Color.white);
        GetLeggings.setForeground(Color.black);
        GetLeggings.addActionListener((ActionEvent e) -> {
            equipment.ConfirmLeggings();
        });
        add(GetLeggings);

        Boots = new JButton(String.format("<html>Boots<br>Price: %d</html>", Boots_Price));
        Boots.setBounds(375, 290, 150, 100);
        Boots.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
        Boots.setFocusPainted(false);
        Boots.setBackground(Color.black);
        Boots.setForeground(Color.white);
        Boots.addActionListener((ActionEvent e) -> {
            if (stats.getMoney() >= Boots_Price) {
                stats.setMoney(stats.getMoney() - Boots_Price);
                equipment.RollBoots();
                Money.setText("Money : " + stats.getMoney());
                YougotBoots.setText("You got : 1/" + equipment.RollResult);
                StatsBoots.setText("Stats : +" + equipment.BootsValue + " Hp");
            }
        });
        add(Boots);

        GetBoots = new JButton("Get Boots");
        GetBoots.setBounds(375, 440, 150, 30);
        GetBoots.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
        GetBoots.setFocusPainted(false);
        GetBoots.setBackground(Color.white);
        GetBoots.setForeground(Color.black);
        GetBoots.addActionListener((ActionEvent e) -> {
            equipment.ConfirmBoots();
        });
        add(GetBoots);
    }

    void setDisplay() {
        setSize(640, 480);
        setResizable(false);
        setLocationRelativeTo(null);
        setUndecorated(true);
        setLayout(null);
        setVisible(true);
        getContentPane().setBackground(Color.white);
    }
}
