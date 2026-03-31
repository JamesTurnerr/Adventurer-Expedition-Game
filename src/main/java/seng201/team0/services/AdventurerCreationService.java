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
    private static final List<String> namesList = new ArrayList<String>(Arrays.asList(names));

    public static Adventurer createRandomAdventurer()
    {
        Random random = new Random();
        String name = namesList.get(random.nextInt(namesList.size()));
        namesList.remove(name);
        return new Adventurer(name, 100, 100, 100, 10, 10, 3);
    }
}
