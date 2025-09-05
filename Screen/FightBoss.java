package Screen;
import javax.swing.*;
import java.awt.*;
import Player.Stats;
import Player.Level;
import Player.Equipment;
import Enemy.BossStats;
import Enemy.MonsterStats;

public class FightBoss extends JFrame {
    Stats stats;
    Level level;
    Equipment equipment;
    BossStats bossStats;
    MonsterStats monsterStats;
    Bunker bunker; 
    Travel travel;
    JLabel PlayerHp, BossHp , TellSpeed;
    int Player_Health, Boss_Health, PlayerCurrent_Health, BossCurrent_Health;
    double speedMultiplier;
    volatile boolean fightEnd = false;

    public FightBoss(Stats stats, Level level, Equipment equipment, MonsterStats monsterStats, BossStats bossStats, Bunker bunker, Travel travel) {
        this.stats = stats;
        this.level = level;
        this.equipment = equipment;
        this.monsterStats = monsterStats;
        this.bossStats = bossStats;
        this.bunker = bunker;
        this.travel = travel;
        this.speedMultiplier = 1.0;

        Player_Health = stats.getHp();
        PlayerCurrent_Health = Player_Health;

        Boss_Health = bossStats.getHp();
        BossCurrent_Health = Boss_Health;

        setImage();
        setText();
        setButton();
        startFight();
        setDisplay();
    }

    Color setHealthPlayerColor(int currentHp , int Hp){
        double percent = (double) currentHp / Hp;
        if (percent >= 0.75) return Color.GREEN;
        else if (percent >= 0.50) return Color.YELLOW;
        else if (percent >= 0.25) return Color.ORANGE;
        else return Color.RED;
    }

    void startFight() {
        // Player thread
        new Thread(() -> {
            while (!fightEnd && PlayerCurrent_Health > 0 && BossCurrent_Health > 0) {
                BossCurrent_Health -= stats.getATK();
                if (BossCurrent_Health < 0) BossCurrent_Health = 0;

                SwingUtilities.invokeLater(() -> {
                    BossHp.setText("HP : " + BossCurrent_Health);
                    BossHp.setForeground(setHealthPlayerColor(BossCurrent_Health, Boss_Health));
                });

                if (BossCurrent_Health <= 0) {
                    fightEnd = true;
                    SwingUtilities.invokeLater(() -> playerWin());
                    break;
                }

                try { Thread.sleep((long)(stats.getATKSpeed() * 1000 / speedMultiplier)); }
                catch (InterruptedException e) { e.printStackTrace(); }
            }
        }).start();

        // Monster thread
        new Thread(() -> {
            while (!fightEnd && PlayerCurrent_Health > 0 && BossCurrent_Health > 0) {
                PlayerCurrent_Health -= bossStats.getATK();
                if (PlayerCurrent_Health < 0) PlayerCurrent_Health = 0;

                SwingUtilities.invokeLater(() -> {
                    PlayerHp.setText("HP : " + PlayerCurrent_Health);
                    PlayerHp.setForeground(setHealthPlayerColor(PlayerCurrent_Health, Player_Health));
                });

                if (PlayerCurrent_Health <= 0) {
                    fightEnd = true;
                    SwingUtilities.invokeLater(() -> playerLose());
                    break;
                }

                try { Thread.sleep((long)(monsterStats.getATKSpeed() * 1000 / speedMultiplier)); }
                catch (InterruptedException e) { e.printStackTrace();}
            }
        }).start();
    }

    void playerWin() {
        JOptionPane.showMessageDialog(this, "Monster Defeated!");
        stats.setExp(stats.getExp() + bossStats.getExp());
        stats.setMoney(stats.getMoney() + bossStats.getMoney());
        bossStats.BossIncreaseStats();
        level.levelUp();

        Player_Health = stats.getHp();
        PlayerCurrent_Health = Player_Health;

        Boss_Health = bossStats.getHp();
        BossCurrent_Health = Boss_Health;

        if (bunker != null) bunker.updateUI();
        if (travel != null) travel.updateUI();

        dispose();
        new Bunker(stats, level, equipment, monsterStats, bossStats);
    }

    void playerLose() {
        JOptionPane.showMessageDialog(this, "You Died!");
        bossStats.BossDecreaseStats();
        level.levelUp();
        if (bunker != null) bunker.updateUI();
        if (travel != null) travel.updateUI();

        Player_Health = stats.getHp();
        PlayerCurrent_Health = Player_Health;
        Boss_Health = bossStats.getHp();
        BossCurrent_Health = Boss_Health;

        dispose();
        new Bunker(stats, level, equipment, monsterStats, bossStats);
    }

    void setText(){
        PlayerHp = new JLabel("Hp : " + PlayerCurrent_Health);
        PlayerHp.setBounds(150, 50, 1000, 300);
        PlayerHp.setFont(new Font("Comic Sans MS", Font.BOLD, 48));
        PlayerHp.setForeground(setHealthPlayerColor(PlayerCurrent_Health, Player_Health));
        add(PlayerHp);

        BossHp = new JLabel("Monster HP : " + BossCurrent_Health);
        BossHp.setBounds(875, 200, 1000, 50);
        BossHp.setFont(new Font("Comic Sans MS", Font.BOLD, 32));
        BossHp.setForeground(setHealthPlayerColor(BossCurrent_Health, Boss_Health));
        add(BossHp);

        TellSpeed = new JLabel("Speed : " + speedMultiplier);
        TellSpeed.setBounds(10, 50, 100, 40);
        TellSpeed.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
        TellSpeed.setForeground(Color.white);
        add(TellSpeed);
    }

    void setButton(){
        JButton speed1x = new JButton("1x");
        speed1x.setBounds(10, 10, 60, 40);
        speed1x.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
        speed1x.setFocusPainted(false);
        speed1x.setBackground(Color.black);
        speed1x.setForeground(Color.white);
        speed1x.addActionListener(e -> {
            speedMultiplier = 1.0;
            TellSpeed.setText("Speed : " + speedMultiplier);
        });
        add(speed1x);
    
        JButton speed2x = new JButton("2x");
        speed2x.setBounds(80, 10, 60, 40);
        speed2x.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
        speed2x.setFocusPainted(false);
        speed2x.setBackground(Color.black);
        speed2x.setForeground(Color.white);
        speed2x.addActionListener(e -> {
            speedMultiplier = 2.0;
            TellSpeed.setText("Speed : " + speedMultiplier);
        });
        add(speed2x);
        
    }

    void setImage(){
        ImageIcon stickmanIcon = new ImageIcon(Bunker.class.getResource("/Image/Stickman.png"));
        Image scaleStickmanImage = stickmanIcon.getImage().getScaledInstance(500, 500, Image.SCALE_SMOOTH);
        JLabel stickmanLabel = new JLabel(new ImageIcon(scaleStickmanImage));
        stickmanLabel.setBounds(50, 275, 500, 500);
        add(stickmanLabel);

        ImageIcon BossIcon = new ImageIcon(Bunker.class.getResource("/Image/Boss.png"));
        Image scaleBossImage = BossIcon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        JLabel BossLabel = new JLabel(new ImageIcon(scaleBossImage));
        BossLabel.setBounds(800, 250, 300, 300);
        add(BossLabel);
    }

    void setDisplay(){
        setSize(1280, 768);
        setResizable(false);
        setLocationRelativeTo(null);
        setUndecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setVisible(true);
        getContentPane().setBackground(Color.gray);
    }
}
