package seng201.team0.services;

import javafx.scene.control.ListView;
import seng201.team0.models.Adventurer;

/**
 * Service class for functionality for the guild hall area where the player can buy adventurers
 */
public class GuildHallService {
    /**
     * Creates a new expedition with a given amount of areas
     * @param listView reference to the ListView that will contain buyable adventurers
     * @param numberOfAdventurers the number adventurers to be added to the ListView
     */
    public void fillAdventurerList(ListView<Adventurer> listView, int numberOfAdventurers)
    {
        listView.getItems().clear();
        for (int i = 0; i < numberOfAdventurers; i++)
        {
            listView.getItems().add(AdventurerCreationService.createRandomAdventurer());
        }
    }
}
