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
    /*WILSON("Wilson", 100, 100, 70, 40, 20, 40),
    PUNJAB("Pubjab", 40, 40, 60, 10, 10, 30),
    MIKE_TYSON("Tyson", 80, 60, 60, 60, 40, 90),
    FREDDY_FAZBEAR("Freddy", 20, 70, 20, 50, 20, 70),
    DIDDY("Colms", 100, 40, 30, 70, 60, 80),
    EPSTEIN("Jeff", 70, 30, 60, 70, 50, 70),
    PETER_GRIFFIN("Peter", 90, 90, 10, 40, 40, 50)
    ;*/
    private static List<String> namesList = new ArrayList<String>(Arrays.asList(names));

    /**
     * Generate an adventurer with a random name and random stats
     * @return Returns randomly generated adventurer
     */
    public static Adventurer createRandomAdventurer()
    {
        String name = generateName();
        int averageStatValue = 100;//Average value of Health, Stamina, Perception. Value bound to 33% greater or less than this number
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
}
