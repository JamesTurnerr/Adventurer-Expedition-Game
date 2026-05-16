package seng201.team0.services;

import seng201.team0.models.Adventurer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Class used to create adventurers
 */
public class AdventurerCreationService {
    private static final String[] names = {"Olivia", "Emma", "Charlotte", "Amelia", "Sophia",
            "Isabella", "Ava", "Mia", "Evelyn", "Luna",
            "Noah", "Liam", "Oliver", "Elijah", "Mateo",
            "Lucas", "Levi", "Ezra", "Asher", "Leo",
            "James", "Benjamin", "Henry", "Alexander", "Jack",
            "William", "Michael", "Daniel", "Logan", "Jackson",
            "Sebastian", "Aiden", "Owen", "Samuel", "Matthew",
            "Joseph", "John", "David", "Wyatt", "Carter",
            "Julian", "Luke", "Grayson", "Isaac", "Jayden",
            "Theodore", "Gabriel", "Anthony", "Dylan", "Caleb"};

    private static List<String> namesList = new ArrayList<String>(Arrays.asList(names));
    private static int averageStatValue = 100;
    /**
     * Generate an adventurer with a random name and random stats
     * @return Returns randomly generated adventurer
     */
    public static Adventurer createRandomAdventurer()
    {
        String name = generateName();
        //int averageStatValue = 100;//Average value of Health, Stamina, Perception. Value bound to 33% greater or less than this number
        Random random = new Random();
        int health = random.nextInt(averageStatValue-averageStatValue/3, averageStatValue+averageStatValue/3);
        int stamina = random.nextInt(averageStatValue-averageStatValue/3, averageStatValue+averageStatValue/3);
        int perception = random.nextInt(averageStatValue-averageStatValue/3, averageStatValue+averageStatValue/3);
        return new Adventurer(name, stamina, health, perception, 10, 10, 3);
    }
    /**
     * Generate an adventurer with given stats
     * @return Returns randomly generated adventurer
     */
    public static Adventurer createAdventurer(int stamina, int health, int perception, int hiringCost, int pay, int damage)
    {
        String name = generateName();
        return new Adventurer(name, stamina, health, perception, hiringCost, pay, damage);
    }

    /**
     * Get a random name from the list of names then remove it so it cannot be used again
     * When the list of names is empty, repopulate it.
     * @return A random name
     */
    private static String generateName()
    {
        Random random = new Random();
        String name = namesList.get(random.nextInt(namesList.size()));
        namesList.remove(name);
        // check to see if the name list is empty - if so repopulate
        if (namesList.isEmpty()){
            namesList = new ArrayList<String>(Arrays.asList(names));
        }
        return name;
    }

    public static int getStatValue(){return averageStatValue;}
    // this can be used for increasing stats with difficulty
    public static void setAverageStatValue(int newVal){averageStatValue = newVal;}
}
