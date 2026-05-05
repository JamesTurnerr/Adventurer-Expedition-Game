package seng201.team0.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Expedition {
    Random rand = new Random();
    int numberOfAreas;

    public final String[] ExpeditionLocation = {"Plains", "Cave", "Mountain", "Jungle"};

    private final String[] PlainsAreas = {"Field", "Flower Patch", "Stream", "Bridge"};
    private final String[] CaveAreas = {"Dark Cave", "Waterfall", "Underground Lake", "Large Open Area"};
    private final String[] MountainAreas = {"Ledge", "Uphill", "Cliff", "Rocky Area"};
    private final String[] JungleAreas = {"Thin Jungle", "Thicc Jungle", "Opening", "Pond"};

    private final String[] EnemyTypes = {"Skeleton", "Goblin"};
    private final String[] EnemyPrefixes = {"Small", "Big", "Large", "Normal", "SUS"};

    public Expedition(int length)
    {
        this.numberOfAreas = length;
    }

    public String[] getAreas(String location)
    {
        return switch (location) {
            case "Plains" -> PlainsAreas;
            case "Cave" -> CaveAreas;
            case "Mountain" -> MountainAreas;
            case "Jungle" -> JungleAreas;
            default -> null;
        };
    }

    private ArrayList<String> getEnemyNameArray(int amount)
    {
        ArrayList<String> enemyNameArray = new ArrayList<String>();
        int randInt = rand.nextInt(EnemyTypes.length);
        String enemyType = EnemyTypes[randInt];
        List<String> enemyPrefixes = new ArrayList<String>(Arrays.asList(EnemyPrefixes));
        for (int i = 0; i < amount; i++)
        {
            enemyNameArray.add(enemyPrefixes.get(rand.nextInt(EnemyTypes.length)) + " " + enemyType);
            enemyPrefixes.remove(rand.nextInt(EnemyTypes.length));
        }
        return enemyNameArray;
    }

    public ArrayList<Enemy> generateEnemies(int amount)
    {
        ArrayList<String> enemyNameArray = getEnemyNameArray(amount);
        ArrayList<Enemy> enemyArrayList = new ArrayList<Enemy>();
        for (String enemyName : enemyNameArray)
        {
            enemyArrayList.add(new Enemy(enemyName, 10, 1));
        }
        return enemyArrayList;
    }
}
