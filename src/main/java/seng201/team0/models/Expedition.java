package seng201.team0.models;

/**
 * Expedition class holds data for all expeditions
 */
public class Expedition {
    int expeditionIndex;

    private final String[] areaNames = {  "Abandoned Mine", "Dense Fog Marsh", "Ancient Ruins", "Frozen Pass", "Bandit Territory",
            "Crystal Caverns", "Burning Wasteland", "Overgrown Jungle", "Haunted Battlefield", "Sunken Temple",
            "Volcanic Crater", "Deserted Fortress", "Crystal Canyon", "Desert Tombs", "Storm Coast"};
    private final String[] areaDescriptions = {   "Collapsing underground tunnels filled with hidden ore, unstable structures,\nand dangerous cave systems.",
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
                        new Choice("Clear the rubble", new EventOutcome[]{EventOutcome.MEDIUM_STAMINA_LOSS}),
                        new Choice("Search for another route", new EventOutcome[]{EventOutcome.SMALL_PERCEPTION}),
                        new Choice("Retreat temporarily", new EventOutcome[]{EventOutcome.LOSE_PROGRESS})}),
                new Event("A green cloud spreads through the tunnel.", new Choice[]{
                        new Choice("Run through quickly", new EventOutcome[]{EventOutcome.MEDIUM_STAMINA_LOSS}),
                        new Choice("Move slowly with caution", new EventOutcome[]{EventOutcome.SMALL_HEALTH_LOSS}),
                        new Choice("Search for ventilation shafts", new EventOutcome[]{EventOutcome.SMALL_PERCEPTION}),}),
                new Event("Rare minerals glitter in the rock.", new Choice[]{
                        new Choice("Mine aggressively", new EventOutcome[]{EventOutcome.LARGE_STAMINA_LOSS, EventOutcome.LARGE_GOLD}),
                        new Choice("Take only surface ore", new EventOutcome[]{EventOutcome.SMALL_GOLD}),
                        new Choice("Inspect for hidden deposits", new EventOutcome[]{EventOutcome.MEDIUM_PERCEPTION, EventOutcome.MEDIUM_STAMINA_LOSS}),}),
                new Event("A dark lake blocks the path.", new Choice[]{
                        new Choice("Swim across", new EventOutcome[]{EventOutcome.SMALL_STAMINA_LOSS,  EventOutcome.SMALL_HEALTH_LOSS}),
                        new Choice("Walk around it", new EventOutcome[]{EventOutcome.MEDIUM_STAMINA_LOSS}),
                        new Choice("Search for a hidden crossing", new EventOutcome[]{EventOutcome.SMALL_PERCEPTION}),}),
                new Event("Something moves behind the walls.", new Choice[]{
                        new Choice("Investigate", new EventOutcome[]{EventOutcome.MEDIUM_HEALTH_LOSS}),
                        new Choice("Stay quiet and continue", new EventOutcome[]{EventOutcome.NOTHING}),
                        new Choice("Run away", new EventOutcome[]{EventOutcome.MEDIUM_STAMINA_LOSS})}),
                new Event("Rusty rails disappear into darkness.", new Choice[]{
                        new Choice("Repair the tracks", new EventOutcome[]{EventOutcome.LARGE_STAMINA_LOSS}),
                        new Choice("Continue on foot", new EventOutcome[]{EventOutcome.MEDIUM_STAMINA_LOSS}),
                        new Choice("Search for alternate tunnels", new EventOutcome[]{EventOutcome.SMALL_PERCEPTION}),}),
                new Event("A dusty crate sits untouched.", new Choice[]{
                        new Choice("Open it immediately", new EventOutcome[]{EventOutcome.LARGE_PERCEPTION}),
                        new Choice("Inspect carefully", new EventOutcome[]{EventOutcome.MEDIUM_PERCEPTION}),
                        new Choice("Ignore it", new EventOutcome[]{EventOutcome.NOTHING}),}),
                new Event("Dust falls from cracks overhead.", new Choice[]{
                        new Choice("Sprint through", new EventOutcome[]{EventOutcome.MEDIUM_STAMINA_LOSS}),
                        new Choice("Move carefully", new EventOutcome[]{EventOutcome.SMALL_HEALTH_LOSS}),
                        new Choice("Search for supports", new EventOutcome[]{EventOutcome.SMALL_PERCEPTION}),}),
            },
            {
                new Event("An old bridge creaks over dark water.", new Choice[]{
                        new Choice("Cross quickly", new EventOutcome[]{EventOutcome.MEDIUM_STAMINA_LOSS}),
                        new Choice("Reinforce the bridge", new EventOutcome[]{EventOutcome.MEDIUM_STAMINA_LOSS}),
                        new Choice("Search for another crossing", new EventOutcome[]{EventOutcome.SMALL_PERCEPTION})}),
                new Event("Clouds of insects surround the party.", new Choice[]{
                        new Choice("Push through", new EventOutcome[]{EventOutcome.MEDIUM_HEALTH_LOSS}),
                        new Choice("Repel them", new EventOutcome[]{EventOutcome.MEDIUM_STAMINA_LOSS}),
                        new Choice("Search for cleaner ground", new EventOutcome[]{EventOutcome.MEDIUM_PERCEPTION}),}),
                new Event("Something glitters underwater.", new Choice[]{
                        new Choice("Dive straight to it", new EventOutcome[]{EventOutcome.SMALL_HEALTH_LOSS}),
                        new Choice("Use tools to retrieve it", new EventOutcome[]{EventOutcome.SMALL_STAMINA_LOSS}),
                        new Choice("Inspect the water first", new EventOutcome[]{EventOutcome.SMALL_PERCEPTION}),}),
                new Event("Small glowing lights drift through the fog.", new Choice[]{
                        new Choice("Follow them", new EventOutcome[]{EventOutcome.MEDIUM_PERCEPTION}),
                        new Choice("Avoid them", new EventOutcome[]{EventOutcome.NOTHING}),
                        new Choice("Observe them", new EventOutcome[]{EventOutcome.SMALL_PERCEPTION}),}),
                new Event("Every step becomes exhausting.", new Choice[]{
                        new Choice("Force through", new EventOutcome[]{EventOutcome.LARGE_STAMINA_LOSS}),
                        new Choice("Move slowly together", new EventOutcome[]{EventOutcome.SMALL_STAMINA_LOSS}),
                        new Choice("Search for solid ground", new EventOutcome[]{EventOutcome.SMALL_PERCEPTION}),}),
                new Event("The nearby water smells foul.", new Choice[]{
                        new Choice("Drink it anyway", new EventOutcome[]{EventOutcome.LARGE_HEALTH_LOSS}),
                        new Choice("Boil and filter it", new EventOutcome[]{EventOutcome.SMALL_STAMINA_LOSS}),
                        new Choice("Search for cleaner water", new EventOutcome[]{EventOutcome.SMALL_PERCEPTION}),}),
                new Event("Something large moves beneath the water.", new Choice[]{//
                        new Choice("Attack first", new EventOutcome[]{EventOutcome.SMALL_HEALTH_LOSS}),
                        new Choice("Retreat quietly", new EventOutcome[]{EventOutcome.LOSE_PROGRESS}),
                        new Choice("Observe and track its movement", new EventOutcome[]{EventOutcome.MEDIUM_PERCEPTION}),}),
                new Event("The trail divides in heavy fog.", new Choice[]{
                        new Choice("Go left", new EventOutcome[]{EventOutcome.SMALL_STAMINA_LOSS}),
                        new Choice("Go right", new EventOutcome[]{EventOutcome.SMALL_HEALTH_LOSS}),
                        new Choice("Search for footprints", new EventOutcome[]{EventOutcome.SMALL_PERCEPTION}),}),
            },
            {
                new Event("A click echoes beneath your feet.", new Choice[]{
                        new Choice("Jump away", new EventOutcome[]{EventOutcome.SMALL_STAMINA_LOSS}),
                        new Choice("Brace for impact", new EventOutcome[]{EventOutcome.MEDIUM_HEALTH_LOSS}),
                        new Choice("Search for the trigger", new EventOutcome[]{EventOutcome.MEDIUM_PERCEPTION}),}),
                new Event("A valuable idol rests on a pedestal.", new Choice[]{
                        new Choice("Grab it immediately", new EventOutcome[]{EventOutcome.MEDIUM_HEALTH_LOSS, EventOutcome.MEDIUM_GOLD}),
                        new Choice("Take smaller valuables", new EventOutcome[]{EventOutcome.SMALL_GOLD}),
                        new Choice("Inspect the room first", new EventOutcome[]{EventOutcome.LARGE_PERCEPTION}),}),
                new Event("Loose stones crack beneath you.", new Choice[]{
                        new Choice("Climb quickly", new EventOutcome[]{EventOutcome.MEDIUM_STAMINA_LOSS}),
                        new Choice("Move carefully", new EventOutcome[]{EventOutcome.SMALL_STAMINA_LOSS}),
                        new Choice("Search for another route", new EventOutcome[]{EventOutcome.SMALL_PERCEPTION}),}),
                new Event("A massive stone door blocks the path.", new Choice[]{
                        new Choice("Force it open", new EventOutcome[]{EventOutcome.LARGE_STAMINA_LOSS}),
                        new Choice("Wait for help/tools", new EventOutcome[]{EventOutcome.SMALL_STAMINA_LOSS}),
                        new Choice("Search for a mechanism", new EventOutcome[]{EventOutcome.SMALL_PERCEPTION}),}),
                new Event("Dusty shelves fill the chamber.", new Choice[]{
                        new Choice("Search thoroughly", new EventOutcome[]{EventOutcome.LARGE_PERCEPTION}),
                        new Choice("Take visible valuables only", new EventOutcome[]{EventOutcome.SMALL_GOLD}),
                        new Choice("Look for hidden compartments", new EventOutcome[]{EventOutcome.MEDIUM_PERCEPTION}),}),
                new Event("Faded symbols cover the walls.", new Choice[]{
                        new Choice("Ignore them", new EventOutcome[]{EventOutcome.NOTHING}),
                        new Choice("Trace the symbols", new EventOutcome[]{EventOutcome.MEDIUM_GOLD}),
                        new Choice("Study them carefully", new EventOutcome[]{EventOutcome.MEDIUM_PERCEPTION}),}),
                new Event("The floor shakes violently.", new Choice[]{
                        new Choice("Run through", new EventOutcome[]{EventOutcome.MEDIUM_STAMINA_LOSS}),
                        new Choice("Move carefully", new EventOutcome[]{EventOutcome.MEDIUM_HEALTH_LOSS}),
                        new Choice("Search for support beams", new EventOutcome[]{EventOutcome.SMALL_PERCEPTION})}),
                new Event("A hidden room filled with treasure appears.", new Choice[]{
                        new Choice("Loot everything", new EventOutcome[]{EventOutcome.MEDIUM_STAMINA_LOSS, EventOutcome.MEDIUM_GOLD}),
                        new Choice("Take only essentials", new EventOutcome[]{EventOutcome.SMALL_GOLD}),
                        new Choice("Search for traps first", new EventOutcome[]{EventOutcome.LARGE_PERCEPTION}),}),
            },
            {
                new Event("A snowstorm races toward the party.", new Choice[]{
                        new Choice("Push through it", new EventOutcome[]{EventOutcome.LARGE_STAMINA_LOSS}),
                        new Choice("Build shelter", new EventOutcome[]{EventOutcome.MEDIUM_STAMINA_LOSS}),
                        new Choice("Search for a cave", new EventOutcome[]{EventOutcome.SMALL_PERCEPTION}),}),
                new Event("Cracks spread beneath your feet.", new Choice[]{
                        new Choice("Run across", new EventOutcome[]{EventOutcome.SMALL_STAMINA_LOSS}),
                        new Choice("Move slowly", new EventOutcome[]{EventOutcome.SMALL_STAMINA_LOSS}),
                        new Choice("Search for another path", new EventOutcome[]{EventOutcome.SMALL_PERCEPTION}),}),
                new Event("A pack of wolves surrounds the group.", new Choice[]{
                        new Choice("Fight them", new EventOutcome[]{EventOutcome.MEDIUM_HEALTH_LOSS}),
                        new Choice("Scare them away", new EventOutcome[]{EventOutcome.SMALL_STAMINA_LOSS}),
                        new Choice("Sneak around them", new EventOutcome[]{EventOutcome.SMALL_PERCEPTION}),}),
                new Event("Your food has frozen solid.", new Choice[]{
                        new Choice("Eat it frozen", new EventOutcome[]{EventOutcome.SMALL_HEALTH_LOSS}),
                        new Choice("Warm it by fire", new EventOutcome[]{EventOutcome.SMALL_HEALTH_LOSS}),
                        new Choice("Search for fresh food", new EventOutcome[]{EventOutcome.SMALL_PERCEPTION}),}),
                new Event("A dark cave opens in the glacier.", new Choice[]{
                        new Choice("Explore it", new EventOutcome[]{EventOutcome.LARGE_PERCEPTION}),
                        new Choice("Ignore It", new EventOutcome[]{EventOutcome.NOTHING}),
                        new Choice("Search around the entrance", new EventOutcome[]{EventOutcome.SMALL_PERCEPTION}),}),
                new Event("Snow shifts loudly above.", new Choice[]{
                        new Choice("Sprint for cover", new EventOutcome[]{EventOutcome.MEDIUM_STAMINA_LOSS}),
                        new Choice("Stay completely still", new EventOutcome[]{EventOutcome.LARGE_HEALTH_LOSS}),
                        new Choice("Search for protected terrain", new EventOutcome[]{EventOutcome.MEDIUM_PERCEPTION}),}),
                new Event("Freezing wind tears through the pass.", new Choice[]{
                        new Choice("Push onward", new EventOutcome[]{EventOutcome.MEDIUM_HEALTH_LOSS}),
                        new Choice("Wait it out", new EventOutcome[]{EventOutcome.MEDIUM_HEALTH_LOSS}),
                        new Choice("Search for cover", new EventOutcome[]{EventOutcome.SMALL_PERCEPTION})}),
                new Event("A body lies trapped in ice.", new Choice[]{
                        new Choice("Search the corpse", new EventOutcome[]{EventOutcome.MEDIUM_GOLD}),
                        new Choice("Leave it alone", new EventOutcome[]{EventOutcome.NOTHING}),
                        new Choice("Inspect surroundings first", new EventOutcome[]{EventOutcome.SMALL_PERCEPTION}),}),
            },
            {
                new Event("Bandits attack from the trees.", new Choice[]{
                        new Choice("Fight", new EventOutcome[]{EventOutcome.MEDIUM_HEALTH_LOSS, EventOutcome.MEDIUM_STAMINA_LOSS, EventOutcome.MEDIUM_GOLD}),
                        new Choice("Flee", new EventOutcome[]{EventOutcome.MEDIUM_STAMINA_LOSS}),
                        new Choice("Try to reason with them", new EventOutcome[]{EventOutcome.SMALL_PERCEPTION}),}),
                new Event("A suspicious trader offers supplies.", new Choice[]{
                        new Choice("Buy from him", new EventOutcome[]{EventOutcome.SMALL_PERCEPTION}),
                        new Choice("Threaten him", new EventOutcome[]{EventOutcome.MEDIUM_HEALTH_LOSS, EventOutcome.MEDIUM_GOLD}),
                        new Choice("Decline his offer", new EventOutcome[]{EventOutcome.NOTHING}),}),
                new Event("An injured traveler begs for help.", new Choice[]{
                        new Choice("Help them", new EventOutcome[]{EventOutcome.SMALL_STAMINA_LOSS, EventOutcome.SMALL_GOLD}),
                        new Choice("Ignore them", new EventOutcome[]{EventOutcome.NOTHING}),
                        new Choice("Question them", new EventOutcome[]{EventOutcome.SMALL_PERCEPTION}),}),
                new Event("Smoke rises nearby.", new Choice[]{
                        new Choice("Approach openly", new EventOutcome[]{EventOutcome.SMALL_PERCEPTION}),
                        new Choice("Avoid the area", new EventOutcome[]{EventOutcome.NOTHING}),
                        new Choice("Sneak closer quietly", new EventOutcome[]{EventOutcome.SMALL_PERCEPTION}),}),
                new Event("An arrow lands nearby.", new Choice[]{
                        new Choice("Charge the attacker", new EventOutcome[]{EventOutcome.MEDIUM_HEALTH_LOSS}),
                        new Choice("Take cover", new EventOutcome[]{EventOutcome.MEDIUM_STAMINA_LOSS}),
                        new Choice("Locate the shooter", new EventOutcome[]{EventOutcome.MEDIUM_PERCEPTION}),}),
                new Event("An overturned caravan blocks the road.", new Choice[]{
                        new Choice("Loot the remains", new EventOutcome[]{EventOutcome.MEDIUM_GOLD, EventOutcome.MEDIUM_HEALTH_LOSS}),
                        new Choice("Inspect for traps", new EventOutcome[]{EventOutcome.MEDIUM_PERCEPTION}),
                        new Choice("Take a different route", new EventOutcome[]{EventOutcome.NOTHING}),}),
                new Event("Bandits patrol the area ahead.", new Choice[]{
                        new Choice("Fight through", new EventOutcome[]{EventOutcome.MEDIUM_HEALTH_LOSS, EventOutcome.MEDIUM_STAMINA_LOSS}),
                        new Choice("Wait for them to leave", new EventOutcome[]{EventOutcome.SMALL_STAMINA_LOSS}),
                        new Choice("Sneak around them", new EventOutcome[]{EventOutcome.MEDIUM_PERCEPTION})}),
                new Event("A barricade seals the canyon.", new Choice[]{
                        new Choice("Break through", new EventOutcome[]{EventOutcome.LARGE_STAMINA_LOSS}),
                        new Choice("Search for hidden paths", new EventOutcome[]{EventOutcome.MEDIUM_PERCEPTION}),
                        new Choice("Take the long way", new EventOutcome[]{EventOutcome.LARGE_STAMINA_LOSS}),}),
            }
    };

    /**
     * Create a specific expedition
     * @param expeditionIndex the index for the expedition e.g. 0 = Abandoned mine, 1 = Dense Fog Marsh...
     */
    public Expedition(int expeditionIndex)
    {
        this.expeditionIndex = expeditionIndex;
    }

    /**
     * Expedition constructor with no parameters for creating a reference to get expedition data
     */
    public Expedition(){}


    /**
     * returns the name of the expedition based on its index in the areaNames array
     * @param areaIndex the index in {@link #areaNames} of the expedition name
     * @return the name of the expedition
     */
    public String getAreaName(int areaIndex)
    {
        return areaNames[areaIndex];
    }

    /**
     * Gets the description of the area for immersion
     * @param areaIndex the array index of the area
     * @return Readable text describing the area
     */
    public String getAreaDescription(int areaIndex)
    {
        return areaDescriptions[areaIndex];
    }
}
