package seng201.team0.services;

import javafx.scene.control.ListView;
import seng201.team0.GameEnvironment;
import seng201.team0.models.Adventurer;
import seng201.team0.models.Item;

import java.util.List;

/**
 * Service class for functionality for the guild hall area where the player can buy adventurers
 */
public class GuildHallService {
    private final GameEnvironment gameEnvironment;
    public GuildHallService(GameEnvironment gameEnvironment) {this.gameEnvironment = gameEnvironment;}
    private int size = 5;

    // fills the list with a new 5 adventurers
    public List<Adventurer> getAdventurerList(){
        if (gameEnvironment.getDoUpdateHall()){
            gameEnvironment.setDoUpdateHall(false);
            return getNewList(size);

        }
        else{
            return gameEnvironment.getHireableAdventurers();
        }
    }
    public List<Adventurer> getNewList(int size){
        gameEnvironment.getHireableAdventurers().clear();
        for (int i = 0; i < size; i++) {
            Adventurer adventurer = AdventurerCreationService.createRandomAdventurer();
            gameEnvironment.getHireableAdventurers().add(adventurer);
        }
        return gameEnvironment.getHireableAdventurers();
    }

}
