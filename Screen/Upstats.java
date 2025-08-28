package Screen;

import Player.Stats;
import Player.Equipment;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Upstats extends JFrame {
    private Stats stats;
    private Equipment equipment;
    private JLabel pointsLabel, atkLabel, atkSpeedLabel, hpLabel;
    private JButton upATKButton, upATKSpeedButton, upHpButton;

    public Upstats(Stats stats, Equipment equipment) {
        this.stats = stats;
        this.equipment = equipment;
        setText();
        setButton();
        updateUI();
        setDisplay();
    }

    void setText() {
        JLabel title = new JLabel("UpStats");
        title.setFont(new Font("Comic Sans MS", Font.BOLD, 32));
        title.setBounds(250, 0, 200, 75);
        add(title);

        pointsLabel = new JLabel("Points: " + stats.getPoints());
        pointsLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        pointsLabel.setBounds(10, 50, 200, 75);
        add(pointsLabel);

        atkLabel = new JLabel("ATK: " + stats.getATK()); // ใช้ getATK() โดยตรง
        atkLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        atkLabel.setBounds(200, 50, 200, 75);
        add(atkLabel);

        atkSpeedLabel = new JLabel("ATKSpeed: " + String.format("%.1f", stats.getATKSpeed()));
        atkSpeedLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        atkSpeedLabel.setBounds(325, 50, 200, 75);
        add(atkSpeedLabel);

        hpLabel = new JLabel("HP: " + stats.getHp());
        hpLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        hpLabel.setBounds(500, 50, 200, 75);
        add(hpLabel);
    }

    void setButton() {
        JButton exit = new JButton("X");
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

        upATKButton = new JButton("<html>ATK +5 <br> Point: 1</html>");
        upATKButton.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
        upATKButton.setFocusPainted(false);
        upATKButton.setBackground(Color.black);
        upATKButton.setForeground(Color.white);
        upATKButton.setBounds(100, 125, 200, 150);
        upATKButton.addActionListener((ActionEvent e) -> {
            if (stats.getPoints() >= 1) {
                stats.setBaseATK(stats.getBaseATK() + 5); // เพิ่ม baseATK
                stats.setATK(stats.getBaseATK() + equipment.getSword()); // อัปเดต ATK รวม Sword
                stats.setPoints(stats.getPoints() - 1);
                updateUI();
            }
        });
        add(upATKButton);

        upATKSpeedButton = new JButton("<html>ATKSpeed -0.1 <br> Point: 1</html>");
        upATKSpeedButton.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
        upATKSpeedButton.setFocusPainted(false);
        upATKSpeedButton.setBackground(Color.black);
        upATKSpeedButton.setForeground(Color.white);
        upATKSpeedButton.setBounds(350, 125, 200, 150);
        upATKSpeedButton.addActionListener((ActionEvent e) -> {
            if (stats.getPoints() >= 1) {
                stats.setATKSpeed(stats.getATKSpeed() - 0.1);
                stats.setPoints(stats.getPoints() - 1);
                updateUI();
            }
        });
        add(upATKSpeedButton);

        upHpButton = new JButton("<html>HP +20 <br> Point: 1</html>");
        upHpButton.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
        upHpButton.setFocusPainted(false);
        upHpButton.setBackground(Color.black);
        upHpButton.setForeground(Color.white);
        upHpButton.setBounds(100, 300, 200, 150);
        upHpButton.addActionListener((ActionEvent e) -> {
            if (stats.getPoints() >= 1) {
                stats.setBaseHp(stats.getBaseHp() + 20); 
                updateTotalHp();
                stats.setPoints(stats.getPoints() - 1);
                updateUI();
            }
        });
        add(upHpButton);
    }

    private void updateTotalHp() {
        int totalHpBonus = equipment.getHelmet() + equipment.getChestplate() + equipment.getLeggings() + equipment.getBoots();
        stats.setHp(stats.getBaseHp() + totalHpBonus);
    }

    void updateUI() {
        pointsLabel.setText("Points: " + stats.getPoints());
        atkLabel.setText("ATK: " + stats.getATK()); // ใช้ getATK() โดยตรง
        atkSpeedLabel.setText("ATKSpeed: " + String.format("%.1f", stats.getATKSpeed()));
        hpLabel.setText("HP: " + stats.getHp());

        if (stats.getPoints() >= 1) {
            upATKButton.setEnabled(true);
            upATKButton.setText("<html>ATK +5 <br> Point: 1</html>");
            upATKSpeedButton.setEnabled(true);
            upATKSpeedButton.setText("<html>ATKSpeed -0.1 <br> Point: 1</html>");
            upHpButton.setEnabled(true);
            upHpButton.setText("<html>HP +20 <br> Point: 1</html>");
        } else {
            upATKButton.setEnabled(false);
            upATKButton.setText("You Need 1 Point");
            upATKSpeedButton.setEnabled(false);
            upATKSpeedButton.setText("You Need 1 Point");
            upHpButton.setEnabled(false);
            upHpButton.setText("You Need 1 Point");
        }

        revalidate();
        repaint();
    }

    void setDisplay() {
        setSize(640, 480);
        setResizable(false);
        setLocationRelativeTo(null);
        setUndecorated(true);
        setTitle("Game: Darkflame Shadow -> Upstats");
        setLayout(null);
        setVisible(true);
        getContentPane().setBackground(Color.white);
    }
}