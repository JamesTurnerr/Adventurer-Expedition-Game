package seng201.team0.models;

public class Adventurer {
    private final String name;
    private int stamina;
    private int maxStamina;
    private int health;
    private int maxHealth;
    private int perception;
    private final int hiringCost;
    private final int pay;
    private int damage;

    public Adventurer(String name, int maxStamina, int maxHealth, int perception, int hiringCost, int pay, int damage)
    {
        this.name = name;
        this. maxStamina = maxStamina;
        this.maxHealth = maxHealth;
        this.perception = perception;
        this.hiringCost = hiringCost;
        this.pay = pay;
        this.damage = damage;
    }
    //Getters
    public int getHealth()
    {
        return health;
    }
    public void setHealth(int health)
    {
        this.health = health;
    }
    public String getName()
    {
        return name;
    }
    public int getStamina()
    {
        return stamina;
    }
    public void setStamina(int stamina)
    {
        this.stamina = stamina;
    }
    public int getPerception()
    {
        return perception;
    }
    public int getHiringCost()
    {
        return hiringCost;
    }
    public int getPay()
    {
        return pay;
    }
    public int getDamage()
    {
        return damage;
    }

    @Override
    public String toString()
    {
        return String.format("Name: %s, Stm: %d, Per: %d, HC: %d, Pay: %d", name, maxStamina, perception, hiringCost, pay);
    }
}
