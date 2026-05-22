package seng201.team0.services;

import java.util.List;

import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import seng201.team0.GameEnvironment;
import seng201.team0.models.Adventurer;

/**
 * Service class for the games setup screen
 */
public class SetupService {
    GameEnvironment gameEnvironment;
    public SetupService(GameEnvironment gameEnvironment) { this.gameEnvironment = gameEnvironment; }
    /**
     * Checks all inputs to make sure they are valid before starting the game
     *
     * @param guildName         the chosen guild name
     * @param difficulty        the string of the selected difficulty
     * @param chosenAdventurers a list of the chosen adventurers
     * @param expeditionCount   the number of expeditions to go on
     * @return If setup was successful or not
     */
    public String checkInputs(String guildName, String difficulty, List<Adventurer> chosenAdventurers, int expeditionCount) {
        // need to add guild parameters
        if (guildName.isEmpty()) {
            System.out.println("Please enter a guild name");
            return "Please enter a guild name";
        }
        //check for special characters
        for (int i = 0; i < guildName.length(); i++)
        {
            char c = guildName.charAt(i);
            if (!Character.isLetter(c) && !Character.isWhitespace(c))
            {
                System.out.println("Please only use letters for guild name");
                return "Please only use letters for guild name";
            }
        }

        if (guildName.length() > 15 || guildName.length() < 3)
        {
            System.out.println("Name size must be between 3 and 15");
            return "Name size must be between 3 and 15";
        }

        if (difficulty.equals("Difficulty")) {
            System.out.println("Please select a difficulty");
            return "Please select a difficulty";
        }

        for (Adventurer adventurer : chosenAdventurers)
        {
            if (adventurer == null)
            {
                System.out.println("Please choose 3 starter adventurers");
                return "Please choose 3 starter adventurers";
            }
        }
        return "";
    }


    /**
     * Fills a ListView of type Adventurer with x amount of randomly generated adventurers
     * @param listView the ListView to be filled
     * @param numberOfAdventurers the amount of adventurers to be added
     */
    public void fillStarterAdventurerList(ListView<Adventurer> listView, int numberOfAdventurers)
    {
        listView.getItems().clear();
        for (int i = 0; i < numberOfAdventurers; i++)
        {
            listView.getItems().add(AdventurerCreationService.createRandomAdventurer());
        }
    }
}
