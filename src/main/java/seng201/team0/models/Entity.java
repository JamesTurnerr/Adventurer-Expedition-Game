package seng201.team0.models;

public class Entity {
    protected final String name;
    private int health;
    private int maxHealth;
    private final int damage;

    public Entity(String name, int maxHealth, int damage)
    {
        this.name = name;
        this.maxHealth = maxHealth;
        this.damage = damage;

        //set stats
        this.health = maxHealth;
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
    public int getDamage()
    {
        return damage;
    }

    public void attack(Entity target)
    {
        if (target.health <= 0)
        {
            target.health -= damage;
        }
    }
}
