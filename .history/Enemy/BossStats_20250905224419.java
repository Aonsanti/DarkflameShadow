package Enemy;

public class BossStats {
    private int Level;
    private int ATK;
    private double ATKSpeed;
    private int Hp;
    private int Money;
    private int Exp;
    private int safeLevel;
    public BossStats(){
        this.Level = 1;
        this.ATK = 100;
        this.ATKSpeed = 2.0;
        this.Hp = 1000;
        this.Money = 1000;
        this.Exp = 500;
        this.safeLevel = 1;
    }

    public void BossIncreaseStats(){
        if(Level <= 0){
            Level = 0;
        }else{
            this.Level = Level + 1;
            ATK = Math.max(100, ATK * Level); 
            ATKSpeed = Math.max(2.0, 2.0 + (safeLevel - 1) / safeLevel);
            if (ATKSpeed < 0.2){ATKSpeed = 0.2;}
            Hp = Math.max(1000, 1000 * Level);
            Money = Level * 1000;
            Exp = Exp * Level;
        }
    }
    public void BossDecreaseStats(){
        if(Level <= 0){
            Level = 0;
        }else{
            safeLevel = Math.max(1, Level);
            this.Level = Level - 1;
            ATK = Math.max(100, ATK / (safeLevel * 2));
            ATKSpeed = Math.max(2.0, 2.0 + (safeLevel - 1) / safeLevel);
            if (ATKSpeed > 2.0){ATKSpeed = 2.0;}
            if (ATKSpeed < 0.2){ATKSpeed = 0.2;}
            Hp = Math.max(1000, 1000 / safeLevel);
            Money = Math.max(1000, Money - 1000);
            Exp = Math.max(500, Exp / safeLevel);
        }
    }   

    public void setLevel(int level){
        if (Level >= 0) {
            this.Level = level;
        }
    }

    public int getLevel(){
        return Level;
    }

    public void setATK(int ATK){
        if (ATK >= 0) {
            this.ATK = ATK;
        }
    }

    public int getATK(){
        return ATK;
    }

    public void setATKSpeed(double ATKSpeed){
        if (ATKSpeed >= 0) {
            this.ATKSpeed = ATKSpeed;
        }
    }

    public double getATKSpeed(){
        return ATKSpeed;
    }

    public void setHp(int Hp){
        if (Hp >= 0) {
            this.Hp = Hp;
        }
    }

    public int getHp(){
        return Hp;
    }

    public void setMoney(int Money){
        if (Money >= 0) {
            this.Money = Money;
        }
    }

    public int getMoney(){
        return Money;
    }
    
    public void setExp(int Exp){
        if (Exp >= 0) {
            this.Exp = Exp;
        }
    }

    public int getExp(){
        return Exp;
    }
}
