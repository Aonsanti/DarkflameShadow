package GameFunction;
import java.util.Random;
import Player.Equipment;
import Player.Stats;

public class Rolling {
    Random random;
    Equipment equipment;
    Stats stats;
    public static int RollValue, SwordValue, HelmetValue, ChestplateValue, LeggingsValue, BootsValue, RollResult;

    public Rolling(Equipment equipment, Stats stats) {
        random = new Random();
        this.stats = stats;
        this.equipment = equipment;
        RollValue = 0;
        SwordValue = 0;
        HelmetValue = 0;
        ChestplateValue = 0;
        LeggingsValue = 0;
        BootsValue = 0;
    }

    private int Roll() {
        RollValue = random.nextInt(1000000);
        if (RollValue < 10000) return 10000;    // 0 to 9,999: 1%
        else if (RollValue < 30000) return 5000; // 10,000 to 29,999: 2%
        else if (RollValue < 60000) return 2500; // 30,000 to 59,999: 3%
        else if (RollValue < 110000) return 1500; // 60,000 to 109,999: 5%
        else if (RollValue < 180000) return 1000; // 110,000 to 179,999: 7%
        else if (RollValue < 280000) return 750;  // 180,000 to 279,999: 10%
        else if (RollValue < 400000) return 500;  // 280,000 to 399,999: 12%
        else if (RollValue < 550000) return 250;  // 400,000 to 549,999: 15%
        else if (RollValue < 750000) return 100;  // 550,000 to 749,999: 20%
        else return 50;                          // 750,000 to 999,999: 25%
    }

    public void RollSword() {
        RollResult = Roll();
        System.out.println("Roll value: " + RollValue);
        switch (RollResult) {
            case 10000: SwordValue = 1000; break;
            case 5000: SwordValue = 750; break;
            case 2500: SwordValue = 500; break;
            case 1500: SwordValue = 400; break;
            case 1000: SwordValue = 300; break;
            case 750: SwordValue = 200; break;
            case 500: SwordValue = 150; break;
            case 250: SwordValue = 100; break;
            case 100: SwordValue = 75; break;
            default: SwordValue = 50; break;
        }
    }

    public void ConfirmSword() {
        stats.setATK(stats.getBaseATK() + SwordValue); 
        equipment.setSword(SwordValue);
        equipment.setSword_Chance(RollResult);
        updateTotalHp();
    }

    public void RollHelmet() {
        RollResult = Roll();
        switch (RollResult) {
            case 10000: HelmetValue = 1000; break;
            case 5000: HelmetValue = 750; break;
            case 2500: HelmetValue = 500; break;
            case 1500: HelmetValue = 400; break;
            case 1000: HelmetValue = 300; break;
            case 750: HelmetValue = 200; break;
            case 500: HelmetValue = 150; break;
            case 250: HelmetValue = 100; break;
            case 100: HelmetValue = 75; break;
            default: HelmetValue = 50; break;
        }
    }

    public void ConfirmHelmet() {
        equipment.setHelmet(HelmetValue);
        equipment.setHelmet_Chance(RollResult);
        updateTotalHp();
    }

    public void RollChestplate() {
        RollResult = Roll();
        switch (RollResult) {
            case 10000: ChestplateValue = 1000; break;
            case 5000: ChestplateValue = 750; break;
            case 2500: ChestplateValue = 500; break;
            case 1500: ChestplateValue = 400; break;
            case 1000: ChestplateValue = 300; break;
            case 750: ChestplateValue = 200; break;
            case 500: ChestplateValue = 150; break;
            case 250: ChestplateValue = 100; break;
            case 100: ChestplateValue = 75; break;
            default: ChestplateValue = 50; break;
        }
    }

    public void ConfirmChestplate() {
        equipment.setChestplate(ChestplateValue);
        equipment.setChestplate_Chance(RollResult);
        updateTotalHp();
    }

    public void RollLeggings() {
        RollResult = Roll();
        switch (RollResult) {
            case 10000: LeggingsValue = 1000; break;
            case 5000: LeggingsValue = 750; break;
            case 2500: LeggingsValue = 500; break;
            case 1500: LeggingsValue = 400; break;
            case 1000: LeggingsValue = 300; break;
            case 750: LeggingsValue = 200; break;
            case 500: LeggingsValue = 150; break;
            case 250: LeggingsValue = 100; break;
            case 100: LeggingsValue = 75; break;
            default: LeggingsValue = 50; break; 
        }
    }

    public void ConfirmLeggings() {
        equipment.setLeggings(LeggingsValue);
        equipment.setLeggings_Chance(RollResult);
        updateTotalHp();
    }

    public void RollBoots() {
        RollResult = Roll();
        switch (RollResult) {
            case 10000: BootsValue = 1000; break;
            case 5000: BootsValue = 750; break;
            case 2500: BootsValue = 500; break;
            case 1500: BootsValue = 400; break;
            case 1000: BootsValue = 300; break;
            case 750: BootsValue = 200; break;
            case 500: BootsValue = 150; break;
            case 250: BootsValue = 100; break;
            case 100: BootsValue = 75; break;
            default: BootsValue = 50; break;
        }
    }

    public void ConfirmBoots() {
        equipment.setBoots(BootsValue);
        equipment.setBoots_Chance(RollResult);
        updateTotalHp();
    }

    private void updateTotalHp() {
        int totalHpBonus = equipment.getHelmet() + equipment.getChestplate() + equipment.getLeggings() + equipment.getBoots();
        stats.setHp(stats.getBaseHp() + totalHpBonus);
    }
}