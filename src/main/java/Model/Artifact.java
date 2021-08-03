package Model;

public class Artifact {
    private String photo;
    private String name;
    private int inventory;
    private int attack;
    private int defence;
    private int hitPoints;

    private Artifact() {}
    public Artifact(String photo, String name, int inventory, int attack, int defence, int hitPoints) {
        this.photo = photo;
        this.name = name;
        this.inventory = inventory;
        this.attack = attack;
        this.defence = defence;
        this.hitPoints = hitPoints;
    }

    public String getPhoto() {
        return photo;
    }

    public String getName() {
        return name;
    }

    public int getInventory() {
        return inventory;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefence() {
        return defence;
    }

    public int getHitPoints() {
        return hitPoints;
    }


}
