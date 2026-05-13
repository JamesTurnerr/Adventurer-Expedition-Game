package seng201.team0.services;

import javafx.scene.control.ListView;
import seng201.team0.GameEnvironment;
import seng201.team0.models.Adventurer;

/**
 * Service class for functionality for the guild hall area where the player can buy adventurers
 */
public class GuildHallService {
    private final GameEnvironment gameEnvironment;
    public GuildHallService(GameEnvironment gameEnvironment) {this.gameEnvironment = gameEnvironment;}
    /**
     * Creates a new expedition with a given amount of areas
     * @param listView reference to the ListView that will contain buyable adventurers
     * @param numberOfAdventurers the number adventurers to be added to the ListView
     */
    // fills the list with a new 5 adventurers
    public void fillNewAdventurerList(ListView<Adventurer> listView, int numberOfAdventurers)
    {
        gameEnvironment.getHireableAdventurers().clear();
        listView.getItems().clear();
        for (int i = 0; i < numberOfAdventurers; i++)
        {
            Adventurer adventurer = AdventurerCreationService.createRandomAdventurer();
            listView.getItems().add(adventurer);
            gameEnvironment.getHireableAdventurers().add(adventurer);
        }
    }

    //puts in existing list
    public void fillOldAdventurerList(ListView<Adventurer> listView){
        listView.getItems().clear();
        for (Adventurer adventurer : gameEnvironment.getHireableAdventurers()){
            listView.getItems().add(adventurer);
        }
    }

}
