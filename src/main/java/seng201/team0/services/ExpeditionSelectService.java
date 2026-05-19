package seng201.team0.services;

import seng201.team0.GameEnvironment;
import seng201.team0.models.Expedition;

public class ExpeditionSelectService {

    GameEnvironment gameEnvironment;

    public ExpeditionSelectService(GameEnvironment gameEnvironment)
    {
        this.gameEnvironment = gameEnvironment;
    }

    /**
     * Lock in an expedition given its index
     * @param expedition A reference to get the list of expedition names
     * @param index The index of the chosen expedition
     */
    public void selectLocation(Expedition expedition, int index) {
        if (!hasPartyToContinue()) {
            System.out.println("You need a party to continue");
            return;
        }

        int expeditionIndex = gameEnvironment.getExpeditionLocations().get(index);
        String location = expedition.getAreaName(expeditionIndex);
        System.out.println("Selected location: " + location);
        gameEnvironment.setSelectedExpeditionLocation(location, expeditionIndex);
        gameEnvironment.goToExpeditionScreen();

    }

    /**
     * Check if the player has at least one adventurer in either party
     */
    private boolean hasPartyToContinue() {
        return (gameEnvironment.getMainParty().size()
                + gameEnvironment.getReserveParty().size()) > 0;
    }
}
