package seng201.team0.models;

public class Adventurer extends Entity {
    private int maxStamina;
    private int stamina;
    private int perception;
    private final int hiringCost;
    private final int pay;

    public Adventurer(String name, int maxStamina, int maxHealth, int perception, int hiringCost, int pay, int damage)
    {
        super(name, maxHealth, damage);
        this.maxStamina = maxStamina;
        this.perception = perception;
        this.hiringCost = hiringCost;
        this.pay = pay;

        //set stats
        this.stamina = maxStamina;
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
    public int getHiringCost()
    {
        return hiringCost;
    }
    public int getPay()
    {
        return pay;
    }

    // setter
    public void setStamina(int stamina) {this.stamina = Math.max(0, Math.min(stamina, maxStamina));}

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

    @Override
    public String toString()
    {
        //return String.format("Name: %s, Stm: %d, Per: %d, HC: %d, Pay: %d", name, maxStamina, perception, hiringCost, pay);
        return String.format(name);
    }
}
