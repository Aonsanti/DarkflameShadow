package Screen;
import Enemy.MonsterStats;
import Player.Equipment;
import Player.Stats;
import Player.Level;
import Enemy.BossStats;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Travel extends JFrame{
    private MonsterStats monsterStats;
    private BossStats bossStats;
    private Stats stats;
    private Level level;
    private Equipment equipment;
    private Bunker bunker;
    private JLabel Travel , MonsterATK , MonsterATKSpeed , MonsterHp , Monster_Level , Boss_Level , Money , BossATK , BossATKSpeed , BossHp;
    private JButton Monster , Boss;
    public Travel(Stats stats, Level level, Equipment equipment, MonsterStats monsterStats, BossStats bossStats) {
            this.stats = stats;
            this.level = level;
            this.equipment = equipment;
            this.monsterStats = monsterStats;
            this.bossStats = bossStats;
            this.bunker = bunker;   

            setText();
            setButton();
            setDisplay();
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

        Monster = new JButton("Monster");
        Monster.setFont(new Font("Comic Sans MS", Font.BOLD, 36));
        Monster.setFocusPainted(false);
        Monster.setBackground(Color.black);
        Monster.setForeground(Color.white);
        Monster.setBounds(10, 125, 275, 150);
        Monster.addActionListener((ActionEvent e) -> { 
            if (bunker != null) bunker.dispose();
            dispose();
            SwingUtilities.invokeLater(() -> 
            new FightMonster(stats, level, equipment, monsterStats, bossStats, bunker, this));
        });
        add(Monster);

        Boss = new JButton("Boss");
        Boss.setFont(new Font("Comic Sans MS", Font.BOLD, 36));
        Boss.setFocusPainted(false);
        Boss.setBackground(Color.black);
        Boss.setForeground(Color.white);
        Boss.setBounds(350 , 125, 275, 150);
        Boss.addActionListener((ActionEvent e) -> {
            if (bunker != null) bunker.dispose();
            dispose();
            SwingUtilities.invokeLater(() -> 
            new FightBoss(stats, level, equipment, monsterStats, bossStats, bunker, this));
        });
        add(Boss);
    }
    void setText(){
        Travel = new JLabel("Travel");
        Travel.setFont(new Font("Comic Sans MS", Font.BOLD, 32));
        Travel.setBounds(275, 0, 200, 75);
        add(Travel);


        Monster_Level = new JLabel("Monster Level : " + monsterStats.getLevel());
        Monster_Level.setBounds(10, 250, 300, 100);
        Monster_Level.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
        add(Monster_Level);

        MonsterATK = new JLabel("ATK : " + monsterStats.getATK());
        MonsterATK.setBounds(10, 275, 200, 100);
        MonsterATK.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
        add(MonsterATK);

        MonsterATKSpeed = new JLabel("ATK Speed : " + monsterStats.getATKSpeed());
        MonsterATKSpeed.setBounds(10, 300, 200, 100);
        MonsterATKSpeed.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
        add(MonsterATKSpeed);

        MonsterHp = new JLabel("Hp : " + monsterStats.getHp());
        MonsterHp.setBounds(10, 325, 200, 100);
        MonsterHp.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
        add(MonsterHp);

        Money = new JLabel("Money : " + monsterStats.getMoney());
        Money.setBounds(150, 275, 200, 100);    
        Money.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
        add(Money);

        Boss_Level = new JLabel("Boss Level : " + bossStats.getLevel());
        Boss_Level.setBounds(350, 250, 300, 100);
        Boss_Level.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
        add(Boss_Level);

        BossATK = new JLabel("ATK : " + bossStats.getATK());
        BossATK.setBounds(350, 275, 200, 100);
        BossATK.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
        add(BossATK);

        BossATKSpeed = new JLabel("ATK Speed : " + bossStats.getATKSpeed());
        BossATKSpeed.setBounds(350, 300, 200, 100);
        BossATKSpeed.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
        add(BossATKSpeed);

        BossHp = new JLabel("Hp : " + bossStats.getHp());
        BossHp.setBounds(350, 325, 200, 100);
        BossHp.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
        add(BossHp);

        Money = new JLabel("Money : " + bossStats.getMoney());
        Money.setBounds(500, 275, 200, 100);
        Money.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
        add(Money);
    }
    void updateUI(){
        Boss_Level.setText("Boss: " + bossStats.getLevel());
        BossATK.setText("ATK: " + bossStats.getATK());
        BossATKSpeed.setText("ATKSpeed: " + bossStats.getATKSpeed());
        BossHp.setText("Hp: " + bossStats.getHp());

        Monster_Level.setText("Monster: " + monsterStats.getLevel());
        MonsterATK.setText("ATK: " + monsterStats.getATK());
        MonsterATKSpeed.setText("ATKSpeed: " + String.format("%.1f", monsterStats.getATKSpeed()));
        MonsterHp.setText("Hp: " + monsterStats.getHp());
        
        revalidate();
        repaint();
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
