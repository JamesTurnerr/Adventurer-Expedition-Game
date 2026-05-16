package seng201.team0.services;

import seng201.team0.GameEnvironment;
import seng201.team0.models.Adventurer;
import seng201.team0.models.Item;

import java.util.List;
import java.util.Random;

public class RandomEventService {
    GameEnvironment gameEnvironment;
    public RandomEventService(GameEnvironment gameEnvironment) { this.gameEnvironment = gameEnvironment; }

    private final Random rand = new Random();

    /**
     * Trigger a random event after an expedition is finished
     * @return the text for the random event
     */
    public String generateRandomEvent() {
        List<Adventurer> party = gameEnvironment.getMainParty();
        Adventurer adventurer = party.get(rand.nextInt(party.size()));

        int eventRoll = rand.nextInt(3);

        return switch (eventRoll) {
            case 0 -> statChangeEvent(adventurer);
            case 1 -> rewardEvent(adventurer);
            case 2 -> retirementEvent(adventurer);
            default -> "";
        };
    }

    /**
     * Random event: chance to increase or decrease a single adventurers stats
     * @param adventurer that will have their stat modified
     * @return the text of what happened in the random event
     */
    private String statChangeEvent(Adventurer adventurer){
        int amount = rand.nextInt(20) + 1;
        double baseChance = 50.0;
        boolean positiveChange = rand.nextInt(100) < baseChance / gameEnvironment.getDifficultyModifier();

        if (positiveChange) {
            adventurer.setStamina(adventurer.getStamina()+amount);
            adventurer.setHealth(adventurer.getHealth()+amount);
            return adventurer.getName() + " has been strengthened by that expedition..\n\n" + "Stamina and Health both increased by " + amount + "!";
        }
        else{
            //adventurer.setHealth(Math.max(1, adventurer.getHealth() - amount));
            adventurer.setStamina(Math.max(1, adventurer.getStamina() - amount));
            return adventurer.getName() + " felt especially fatigued during that expedition.\n\n" + "Stamina decreased by " + amount + ".";
        }
    }

    /**
     * Random event: chance for an adventurer to find a random item or an unexpected enemy causing a slight amount of damage
     * @param adventurer that will find the item/take the damage
     * @return the text of what happened in the random event
     */
    private String rewardEvent(Adventurer adventurer) {
        int roll = rand.nextInt(100);
        String adv = adventurer.getName();

        if (roll < 70 / gameEnvironment.getDifficultyModifier()) {
            Item item = Item.getRandomItem();
            gameEnvironment.getPlayerInventory().addItem(item);

            return adv + " found a " + item.getName().toLowerCase() + " as you were leaving! What luck!";
        }
        else {
            adventurer.setHealth(Math.max(1, adventurer.getHealth() - 20));
            return adv + " thought they found a rare item in the bushes, but it was a snake!\n\n" + "In a panic, " + adv + " stubbed their toe on a rock.\n\n" + "Also, the snake bit them.";
        }
    }

    /**
     * Random event: chance for an adventurer to retire if their stats are too low
     * @param adventurer the adventurer that will potentially retire
     * @return the text of what happened in the random event
     */
    private String retirementEvent(Adventurer adventurer) {
        int health = adventurer.getHealth();
        int stamina = adventurer.getStamina();
        int value = (health + stamina) / 2;

        if (value < 20 * gameEnvironment.getDifficultyModifier()){
            gameEnvironment.getMainParty().remove(adventurer);
            return adventurer.getName() + " has retired from adventuring.\n\n" + "After suffering too many injuries and exhaustion, they leave the party.";
        }
        else{
            int amount = 10;
            adventurer.setStamina(adventurer.getStamina() - amount);
            return adventurer.getName() + " considers retirement...\n" + "But the pay is too good of an incentive.\n\n" + "Stamina decreases by " + amount;
        }
    }
}
