package seng201.team0.services;

import javafx.scene.control.ListView;
import seng201.team0.models.Adventurer;

public class GuildHallService {
    public void fillAdventurerList(ListView<Adventurer> listView, int numberOfAdventurers)
    {
        listView.getItems().clear();
        for (int i = 0; i < numberOfAdventurers; i++)
        {
            listView.getItems().add(AdventurerCreationService.createRandomAdventurer());
        }
    }
}
