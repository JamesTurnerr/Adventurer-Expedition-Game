package seng201.team0.models;

public class Adventurer extends Entity {
    private int maxStamina;
    private int stamina;
    private int perception;
    private final int hiringCost;
    private final int pay;
    private String skill;
    private int expeditionsInARow;

    /**
     * Constructor for adventurer class, sets base stats.
     * @param name the name of the adventurer
     * @param maxStamina the maximum stamina stat of the adventurer
     * @param maxHealth the maximum health stat of the adventurer
     * @param perception the perception stat of the adventurer
     * @param hiringCost the cost to hire the adventurer
     * @param pay the amount that must be paid to the adventurer after an expedition
     * @param damage the amount of damage the adventurer does
     */
    public Adventurer(String name, int maxStamina, int maxHealth, int perception, int hiringCost, int pay, int damage)
    {
        super(name, maxHealth, damage);
        this.maxStamina = maxStamina;
        this.perception = perception;
        this.hiringCost = hiringCost;
        this.pay = pay;

        //set stats
        this.stamina = maxStamina;

        this.expeditionsInARow = 0;
        this.skill = "None";
    }
    //Getters
    public int getStamina()
    {
        return stamina;
    }
    public int getPerception()
    {
        return perception;
    }
    public int getHiringCost() {return hiringCost;}
    public int getPay()
    {
        return pay;
    }
    public int getExpeditionsInARow() {return expeditionsInARow;}

    // setter
    public void setStamina(int stamina) {this.stamina = Math.max(0, Math.min(stamina, maxStamina));}

    public void incrementExpeditionsInARow() {
        expeditionsInARow++;
    }

    public void resetExpeditionsInARow() {
        expeditionsInARow = 0;
    }

    /**
     * Causes the adventurer to lose some health.
     * @param healthDamageToTake the amount of health the adventurer will lose
     */
    public void takeHealthDamage(int healthDamageToTake)
    {
        if (healthDamageToTake > getHealth())
        {
            setHealth(0);
        }
        else
        {
            setHealth(getHealth() - healthDamageToTake);
        }
    }

    /**
     * Causes the adventurer to lose some stamina.
     * @param staminaDamageToTake the amount of stamina the adventurer will lose
     */
    public void takeStaminaDamage(int staminaDamageToTake)
    {
        if (staminaDamageToTake > getStamina())
        {
            setStamina(0);
        }
        else
        {
            setStamina(getStamina() - staminaDamageToTake);
        }
    }

    /**
     * Simply gets the adventurers name
     * @return the name value of the adventurer
     */
    @Override
    public String toString()
    {
        //return String.format("Name: %s, Stm: %d, Per: %d, HC: %d, Pay: %d", name, maxStamina, perception, hiringCost, pay);
        return String.format(name);
    }
}
