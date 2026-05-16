package seng201.team0.models;

import seng201.team0.GameEnvironment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Expedition {
    Random rand = new Random();
    int expeditionIndex;

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
    public final Event[][] areaEvents = {
            {
            new Event("A section of the tunnel collapses ahead.", new Choice[]{
                    new Choice("Clear the rubble", "stamina"),
                    new Choice("Search for another route", "perception"),
                    new Choice("Retreat temporarily", "progress")}),
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
            },
            {
            new Event("An old bridge creaks over dark water.", new Choice[]{
                    new Choice("Cross quickly", "stamina"),
                    new Choice("Reinforce the bridge", "stamina"),
                    new Choice("Search for another crossing", "progress")}),
            new Event("Clouds of insects surround the party.", new Choice[]{
                    new Choice("Push through", "health"),
                    new Choice("Repel them", "stamina"),
                    new Choice("Search for cleaner ground", "perception")}),
            new Event("Something glitters underwater.", new Choice[]{
                    new Choice("Dive straight to it", "health"),
                    new Choice("Use tools to retrieve it", "stamina"),
                    new Choice("Inspect the water first", "perception")}),
            new Event("Small glowing lights drift through the fog.", new Choice[]{
                    new Choice("Follow them", "perception"),
                    new Choice("Avoid them", "none"),
                    new Choice("Observe them", "perception")}),
            new Event("Every step becomes exhausting.", new Choice[]{
                    new Choice("Force through", "stamina"),
                    new Choice("Move slowly together", "stamina"),
                    new Choice("Search for solid ground", "perception")}),
            new Event("The nearby water smells foul.", new Choice[]{
                    new Choice("Drink it anyway", "health"),
                    new Choice("Boil and filter it", "stamina"),
                    new Choice("Search for cleaner water", "perception")}),
            new Event("Something large moves beneath the water.", new Choice[]{//
                    new Choice("Attack first", "health"),
                    new Choice("Retreat quietly", "progress"),
                    new Choice("Observe and track its movement", "perception")}),
            new Event("The trail divides in heavy fog.", new Choice[]{
                    new Choice("Go left", "stamina"),
                    new Choice("Go right", "health"),
                    new Choice("Search for footprints", "perception")}),
            },
            {
            new Event("A click echoes beneath your feet.", new Choice[]{
                    new Choice("Jump away", "stamina"),
                    new Choice("Brace for impact", "health"),
                    new Choice("Search for the trigger", "perception")}),
            new Event("A valuable idol rests on a pedestal.", new Choice[]{
                    new Choice("Grab it immediately", "health"),
                    new Choice("Take smaller valuables", "none"),
                    new Choice("Inspect the room first", "perception")}),
            new Event("Loose stones crack beneath you.", new Choice[]{
                    new Choice("Climb quickly", "health"),
                    new Choice("Move carefully", "none"),
                    new Choice("Search for another route", "perception")}),
            new Event("A massive stone door blocks the path.", new Choice[]{
                    new Choice("Force it open", "stamina"),
                    new Choice("Wait for help/tools", "progress"),
                    new Choice("Search for a mechanism", "perception")}),
            new Event("Dusty shelves fill the chamber.", new Choice[]{
                    new Choice("Search thoroughly", "perception"),
                    new Choice("Take visible valuables only", "small_loot"),
                    new Choice("Look for hidden compartments", "perception")}),
            new Event("Faded symbols cover the walls.", new Choice[]{
                    new Choice("Ignore them", "none"),
                    new Choice("Trace the symbols", "random"),
                    new Choice("Study them carefully", "perception")}),
            new Event("The floor shakes violently.", new Choice[]{//
                    new Choice("Run through", "stamina"),
                    new Choice("Move carefully", "health"),
                    new Choice("Search for support beams", "perception")}),
            new Event("A hidden room filled with treasure appears.", new Choice[]{
                    new Choice("Loot everything", "stamina"),
                    new Choice("Take only essentials", "medium_loot"),
                    new Choice("Search for traps first", "perception")}),
            }
    };

    /**
     * Create a specific expedition
     * @param expeditionIndex the index for the expedition eg. 0 = Abandoned mine, 1 = Dense Fog Marsh...
     */
    public Expedition(int expeditionIndex)
    {
        this.expeditionIndex = expeditionIndex;
    }

    /**
     * Expedition constructor with no parameters for creating a reference to get expedition data
     */
    public Expedition(){}

    //can add location variety to this
    public Item getRandomLoot() {
        return lootPool[rand.nextInt(lootPool.length)];
    }


    /**
     * returns the name of the expedition based on its index in the areaNames array
     * @param areaIndex the index in {@link #areaNames} of the expedition name
     * @return the name of the expedition
     */
    public String getAreaName(int areaIndex)
    {
        return areaNames[areaIndex];
    }

    public String getAreaDescription(int areaIndex)
    {
        return areaDescriptions[areaIndex];
    }

    public String getAreaNames(int index)
    {
        return areaNames[index];
    }

    public String[] getAllAreaNames(int numAreas)
    {
        return Arrays.copyOfRange(areaNames, 0, numAreas);
    }
}
