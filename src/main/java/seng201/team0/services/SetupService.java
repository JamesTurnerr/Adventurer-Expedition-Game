package seng201.team0.services;

import java.util.List;

import javafx.scene.control.ListView;
import seng201.team0.models.Adventurer;

/**
 * Service class for the games setup screen
 */
public class SetupService {
    /**
     * Checks all inputs to make sure they are valid before starting the game
     * @param guildInputTextField the chosen guild name
     * @param difficultyMenuButton the string of the selected difficulty
     * @param chosenAdventurersListView a list of the chosen adventurers
     */
    public boolean checkInputs(String guildInputTextField, String difficultyMenuButton, List<Adventurer> chosenAdventurersListView) {
        // need to add guild parameters
        if (guildInputTextField.isEmpty()) {
            System.out.println("Please enter a guild name");
            return false;
        }
        //check for special characters
        for (int i = 0; i < guildInputTextField.length(); i++)
        {
            char c = guildInputTextField.charAt(i);
            if (!Character.isLetterOrDigit(c) && !Character.isWhitespace(c))
            {
                return false;
            }
        }

        if (difficultyMenuButton.equals("Difficulty")) {
            System.out.println("Please select a difficulty");
            return false;
        }

        if (chosenAdventurersListView.size() != 3) {
            System.out.println("Please choose exactly 3 starter adventurers");
            return false;
        }

        return true;
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
