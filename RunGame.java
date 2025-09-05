import Screen.Bunker;
import Player.Stats;
import Player.Level;
import Player.Equipment;
import Enemy.MonsterStats;
import Enemy.BossStats;

public class RunGame{
    public static void main(String[] args) {
        Stats stats = new Stats();
        Level level = new Level(stats);
        Equipment equipment = new Equipment();
        MonsterStats monsterStats = new MonsterStats();
        BossStats bossStats = new BossStats();
        new Bunker(stats, level, equipment, monsterStats, bossStats);
    }
}
