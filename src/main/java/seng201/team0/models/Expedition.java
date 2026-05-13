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

    private final Item[] lootPool = {
            Item.RUSTY_SWORD,
            Item.HEALTH_POTION,
            Item.STAMINA_POTION,
    };

    private final String[] areaNames = {  "Abandoned Mine", "Dense Fog Marsh", "Ancient Ruins", "Frozen Pass", "Bandit Territory",
            "Crystal Caverns", "Burning Wasteland", "Overgrown Jungle", "Haunted Battlefield", "Sunken Temple",
            "Volcanic Crater", "Deserted Fortress", "Crystal Canyon", "Desert Tombs", "Storm Coast"};
    private final String[] areaDescriptions = {   "Collapsing underground tunnels filled with hidden ore, unstable structures, and dangerous cave systems.",
            "A swamp covered in thick fog where visibility is poor and danger hides beneath the water.",
            "Forgotten stone ruins packed with traps, puzzles, buried treasure, and remnants of a lost civilization.",
            "A freezing mountain route battered by snowstorms, ice caves, and deadly cold.",
            "Lawless roads controlled by raiders, ambushes, stolen caravans, and hidden camps.",
            "Glowing underground caverns filled with rare crystals, reflections, and unstable formations.",
            "A scorching desert of ash, heatwaves, volcanic vents, and endless exhaustion.",
            "Dense jungle terrain filled with predators, poisonous plants, hidden ruins, and dangerous rivers.",
            "An ancient war zone haunted by ghosts, undead soldiers, and cursed relics.",
            "Flooded temple ruins containing ancient mechanisms, underwater passages, and trapped treasure chambers.",
            "A dangerous volcanic region filled with lava flows, ash storms, eruptions, and unstable terrain.",
            "A ruined military stronghold hiding armories, prisons, traps, and abandoned defenses.",
            "A massive canyon lined with glowing crystal formations, narrow cliffs, and hidden gem deposits.",
            "Buried tombs beneath shifting sands containing ancient curses, traps, and hidden chambers.",
            "A violent coastline battered by storms, shipwrecks, cliffs, tidal caves, and smuggler hideouts."};
    public final Event[] areaEvents = {
            new Event("A section of the tunnel collapses ahead.", new Choice[]{
                    new Choice("Clear the rubble", "stamina"),
                    new Choice("Search for another route", "perception"),
                    new Choice("Search for another route", "progress")}),
            new Event("A green cloud spreads through the tunnel.", new Choice[]{
                    new Choice("Run through quickly", "stamina"),
                    new Choice("Move slowly with caution", "health"),
                    new Choice("Search for ventilation shafts", "perception")}),
            new Event("Rare minerals glitter in the rock.", new Choice[]{
                    new Choice("Mine aggressively", "stamina"),
                    new Choice("Take only surface ore", "none"),
                    new Choice("Inspect for hidden deposits", "perception")}),
            new Event("A dark lake blocks the path.", new Choice[]{
                    new Choice("Swim across", "health"),
                    new Choice("Walk around it", "stamina"),
                    new Choice("Search for a hidden crossing", "perception")}),
            new Event("Something moves behind the walls.", new Choice[]{
                    new Choice("Investigate", "health"),
                    new Choice("Stay quiet and continue", "none"),
                    new Choice("Run away", "stamina")}),
            new Event("Rusty rails disappear into darkness.", new Choice[]{
                    new Choice("Repair the tracks", "stamina"),
                    new Choice("Continue on foot", "none"),
                    new Choice("Search for alternate tunnels", "perception")}),
            new Event("A dusty crate sits untouched.", new Choice[]{
                    new Choice("Open it immediately", "perception"),
                    new Choice("Inspect carefully", "perception"),
                    new Choice("Ignore it", "none")}),
            new Event("Dust falls from cracks overhead.", new Choice[]{
                    new Choice("Sprint through", "stamina"),
                    new Choice("Move carefully", "health"),
                    new Choice("Search for supports", "perception")}),
    };

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
    //can add location variety to this
    public Item getRandomLoot() {
        return lootPool[rand.nextInt(lootPool.length)];
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

    public String getAreaName(int areaIndex)
    {
        return areaNames[areaIndex];
    }

    public String getAreaDescription(int areaIndex)
    {
        return areaDescriptions[areaIndex];
    }
}
