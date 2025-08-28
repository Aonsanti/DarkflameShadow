package Player;

public class Level {
    private Stats stats;

    public Level(Stats stats) {
        this.stats = stats;
    }

    // เรียกตอนเพิ่ม exp
    public void levelUp() {
        int requiredExp;
        while ((requiredExp = getLevelUpRequirement()) > 0 && stats.getExp() >= requiredExp) {
            stats.setLevel(stats.getLevel() + 1);
            stats.setPoints(stats.getPoints() + 1);
            stats.setExp(stats.getExp() - requiredExp);
            stats.setMaxExp(stats.getMaxExp() * stats.getLevel());
        }
    }

    // สูตรคำนวณ exp ที่ต้องใช้ต่อ level
    public int getLevelUpRequirement() {
        return 100 * stats.getLevel(); // หรือสูตรอื่นตามต้องการ
    }

    public int getLevel() {
        return stats.getLevel(); // คืนค่า level ของผู้เล่น
    }
}
