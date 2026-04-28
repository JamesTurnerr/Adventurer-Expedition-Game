package seng201.team0.services;

import seng201.team0.models.Adventurer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

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

    public static Adventurer createRandomAdventurer()
    {
        Random random = new Random();
        String name = namesList.get(random.nextInt(namesList.size()));
        namesList.remove(name);
        // check to see if the name list is empty - if so repopulate
        if (namesList.size() == 0){
            namesList = new ArrayList<String>(Arrays.asList(names));
        }
        return new Adventurer(name, 100, 100, 100, 10, 10, 3);
    }
}
