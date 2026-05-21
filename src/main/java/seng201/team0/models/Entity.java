package seng201.team0.models;

public class Entity {
    protected final String name;
    private int health;
    private int maxHealth;
    private final int damage;

    /**
     * Constructor for the entity class, sets base stats
     * @param name The name of the entity
     * @param maxHealth The maximum health of the entity
     * @param damage The damage that the entity can do
     */
    public Entity(String name, int maxHealth, int damage)
    {
        this.name = name;
        this.maxHealth = maxHealth;
        this.damage = damage;
        this.health = maxHealth;
    }

    /**
     * Get the health of the entity
     * @return The remaining health of the entity
     */
    public int getHealth()
    {
        return health;
    }

    /**
     * Set the health of the entity
     * @param health The amount of health to set the entity to
     */
    public void setHealth(int health)
    {
        this.health = health;
    }

    /**
     * Increases the health of an entity but will not increase it over its max health
     * @param amount The amount to have entities health increased by
     */
    public void increaseHealth(int amount)
    {
        setHealth(Math.min(health + amount, maxHealth));
    }

    /**
     * Gets the name of the entity
     * @return Entity name
     */
    public String getName()
    {
        return name;
    }

    /**
     * Gets the amount of damage the entity can do
     * @return Entity damage amount
     */
    public int getDamage()
    {
        return damage;
    }
}
