package seng201.team0.services;

import seng201.team0.GameEnvironment;
import seng201.team0.models.Expedition;

/**
 * A service class to handle logic during an expedition.
 */
public class ExpeditionSelectService {

    GameEnvironment gameEnvironment;

    public ExpeditionSelectService(GameEnvironment gameEnvironment)
    {
        this.gameEnvironment = gameEnvironment;
    }

    /**
     * Lock in an expedition given its index so long as player has adventurers in their main party
     * @param expedition A reference to get the list of expedition names
     * @param index The index of the chosen expedition
     * @return If the player can go on an expedition
     */
    public boolean selectLocation(Expedition expedition, int index) {
        if (gameEnvironment.getMainParty().isEmpty()) {
            System.out.println("You need a party to continue");
            return false;
        }

        int expeditionIndex = gameEnvironment.getExpeditionLocations().get(index);
        String location = expedition.getAreaName(expeditionIndex);
        System.out.println("Selected location: " + location);
        gameEnvironment.setSelectedExpeditionLocation(location, expeditionIndex);
        gameEnvironment.goToExpeditionScreen();
        return true;
    }

}
