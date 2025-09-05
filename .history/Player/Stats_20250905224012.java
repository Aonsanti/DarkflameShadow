package Player;

public class Stats {
    private int Level;
    private int ATK;
    private double ATKSpeed;
    private int Hp;
    private int Exp;
    private int maxExp;  
    private int Money;
    private int Points;
    private int baseATK;
    private int baseHp;

    public Stats() {
        this.Level = 1;
        this.ATK = 10;
        this.ATKSpeed = 2.5;
        this.Hp = 100;
        this.Exp = 0;
        this.maxExp = 100;
        this.Money = 1000000;
        this.Points = 0;
        this.baseATK = 10;
        this.baseHp = 100;
    }

    public void setLevel(int Level) {
        this.Level = Level;
    }

    public int getLevel() {
        return Level;
    }

    public void setATK(int ATK) {
        this.ATK = ATK;
    }

    public int getATK() {
        return ATK;
    }

    public void setATKSpeed(double ATKSpeed) {
        this.ATKSpeed = ATKSpeed;
    }

    public double getATKSpeed() {
        return ATKSpeed;
    }

    public void setHp(int Hp) {
        if (Hp >= 0) {
            this.Hp = Hp;
        }
    }

    public int getHp() {
        return Hp;
    }

    public void setExp(int Exp) {
        this.Exp = Math.min(Exp, maxExp);
    }

    public int getExp() {
        return Exp;
    }

    public int getMaxExp() {
        return maxExp;
    }
    
    public void setMaxExp(int maxExp) {
        this.maxExp = maxExp;
    }

    public void setMoney(int Money) {
        this.Money = Money;
    }

    public int getMoney() {
        return Money;
    }

    public void setPoints(int Points) {
        this.Points = Points;
    }

    public int getPoints() {
        return Points;
    }

    public int getBaseATK() {
        return baseATK;
    }

    public void setBaseATK(int baseATK) {
        this.baseATK = baseATK;
    }

    public int getBaseHp() {
        return baseHp;
    }

    public void setBaseHp(int baseHp) {
        this.baseHp = baseHp;
    }
}