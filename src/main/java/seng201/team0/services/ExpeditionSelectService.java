package seng201.team0.services;

import seng201.team0.GameEnvironment;
import seng201.team0.models.Expedition;

public class ExpeditionSelectService {

    GameEnvironment gameEnvironment;

    public ExpeditionSelectService(GameEnvironment gameEnvironment)
    {
        this.gameEnvironment = gameEnvironment;
    }

    public void selectLocation(Expedition expedition, int index) {
        int expeditionIndex = gameEnvironment.getExpeditionLocations().get(index);
        String location = expedition.getAreaNames(expeditionIndex);
        System.out.println("Selected location: " + location);
        gameEnvironment.setSelectedExpeditionLocation(location, expeditionIndex);
        gameEnvironment.goToExpeditionScreen();

    }
}
