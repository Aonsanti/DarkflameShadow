package Enemy;

public class MonsterStats {
    private int Level;
    private int ATK;
    private double ATKSpeed;
    private int Hp;
    private int Money;
    private int Exp;
    private int safeLevel;
    
    public MonsterStats(){
        this.Level = 1;
        this.ATK = 10;
        this.ATKSpeed = 3.0;
        this.Hp = 100;
        this.Money = 100;
        this.Exp = 200;
        this.safeLevel = 1;
    }
    public void MonsterIncreaseStats(){
        if(Level <= 1){
            Level = 1;
        }else{
            this.Level = Level + 1;
            ATK = Math.max(10, ATK * Level); 
            ATKSpeed = Math.max(3.0, 3.0 + (safeLevel - 1) / safeLevel);
            if (ATKSpeed < 0.5){ATKSpeed = 0.5;}
            Hp = Math.max(100, 100 * Level);
            Money = Level * 100;
            Exp = Exp * Level;
        }
    }
    public void MonsterDecreaseStats(){
        safeLevel = Math.max(1, Level);
        this.Level = Level - 1;
        ATK = Math.max(10, ATK / (safeLevel * 2));
        ATKSpeed = Math.max(3.0, 3.0 + (safeLevel - 1) / safeLevel);
        if (ATKSpeed > 3.0){ATKSpeed = 3.0;}
        if (ATKSpeed < 0.5){ATKSpeed = 0.5;}
        Hp = Math.max(100, 100 / safeLevel);
        Money = Math.max(100, Money - 100);
        Exp = Math.max(50, Exp / safeLevel);
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
