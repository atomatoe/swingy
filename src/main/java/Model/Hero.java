package Model;

public class Hero {
    private String photo;
    private String name;
    private String HeroClass;
    private int lvl;
    private int exp;
    private int attack;
    private int defence;
    private int hitPoints;

    private Hero() { }
    public Hero(String name, String HeroClass, int attack, int defence, int hp, String photo) {
        this.name = name;
        this.HeroClass = HeroClass;
        this.attack = attack;
        this.defence = defence;
        this.hitPoints = hp;
        this.lvl = 0;
        this.exp = 0;
        this.photo = photo;
    }

    public String getPhoto() { return photo; }
    public String getName() { return name; }
    public String getHeroClass() { return HeroClass; }
    public int getLvl() { return lvl; }
    public int getExp() { return exp; }
    public int getAttack() { return attack; }
    public int getDefence() { return defence; }
    public int getHitPoints() { return hitPoints; }
}
