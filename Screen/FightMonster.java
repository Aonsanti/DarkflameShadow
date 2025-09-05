package Screen;
import javax.swing.*;
import java.awt.*;
import Player.Stats;
import Player.Level;
import Player.Equipment;
import Enemy.BossStats;
import Enemy.MonsterStats;

public class FightMonster extends JFrame {
    Stats stats;
    Level level;
    Equipment equipment;
    BossStats bossStats;
    MonsterStats monsterStats;
    Bunker bunker; 
    Travel travel;
    JLabel PlayerHp, MonsterHp , TellSpeed;
    int Player_Health, Monster_Health, PlayerCurrent_Health, MonsterCurrent_Health;
    double speedMultiplier;
    volatile boolean fightEnd = false;

    public FightMonster(Stats stats, Level level, Equipment equipment, MonsterStats monsterStats, BossStats bossStats, Bunker bunker, Travel travel) {
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

        Monster_Health = monsterStats.getHp();
        MonsterCurrent_Health = Monster_Health;

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
            while (!fightEnd && PlayerCurrent_Health > 0 && MonsterCurrent_Health > 0) {
                MonsterCurrent_Health -= stats.getATK();
                if (MonsterCurrent_Health < 0) MonsterCurrent_Health = 0;

                SwingUtilities.invokeLater(() -> {
                    MonsterHp.setText("HP : " + MonsterCurrent_Health);
                    MonsterHp.setForeground(setHealthPlayerColor(MonsterCurrent_Health, Monster_Health));
                });

                if (MonsterCurrent_Health <= 0) {
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
            while (!fightEnd && PlayerCurrent_Health > 0 && MonsterCurrent_Health > 0) {
                PlayerCurrent_Health -= monsterStats.getATK();
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
        stats.setExp(stats.getExp() + monsterStats.getExp());
        stats.setMoney(stats.getMoney() + monsterStats.getMoney());
        monsterStats.MonsterIncreaseStats();
        level.levelUp();

        Player_Health = stats.getHp();
        PlayerCurrent_Health = Player_Health;

        Monster_Health = monsterStats.getHp();
        MonsterCurrent_Health = Monster_Health;

        if (bunker != null) bunker.updateUI();
        if (travel != null) travel.updateUI();

        dispose();
        new Bunker(stats, level, equipment, monsterStats, bossStats);
    }

    void playerLose() {
        JOptionPane.showMessageDialog(this, "You Died!");
        monsterStats.MonsterDecreaseStats();
        level.levelUp();
        if (bunker != null) bunker.updateUI();
        if (travel != null) travel.updateUI();

        Player_Health = stats.getHp();
        PlayerCurrent_Health = Player_Health;
        Monster_Health = monsterStats.getHp();
        MonsterCurrent_Health = Monster_Health;

        dispose();
        new Bunker(stats, level, equipment, monsterStats, bossStats);
    }

    void setText(){
        PlayerHp = new JLabel("Hp : " + PlayerCurrent_Health);
        PlayerHp.setBounds(150, 50, 1000, 300);
        PlayerHp.setFont(new Font("Comic Sans MS", Font.BOLD, 48));
        PlayerHp.setForeground(setHealthPlayerColor(PlayerCurrent_Health, Player_Health));
        add(PlayerHp);

        MonsterHp = new JLabel("Monster HP : " + MonsterCurrent_Health);
        MonsterHp.setBounds(875, 200, 1000, 50);
        MonsterHp.setFont(new Font("Comic Sans MS", Font.BOLD, 32));
        MonsterHp.setForeground(setHealthPlayerColor(MonsterCurrent_Health, Monster_Health));
        add(MonsterHp);

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

        ImageIcon MonsterIcon = new ImageIcon(Bunker.class.getResource("/Image/Monster.png"));
        Image scaleMonsterImage = MonsterIcon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        JLabel MonsterLabel = new JLabel(new ImageIcon(scaleMonsterImage));
        MonsterLabel.setBounds(800, 250, 300, 300);
        add(MonsterLabel);
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
