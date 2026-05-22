package seng201.team0.services;

import seng201.team0.GameEnvironment;
import seng201.team0.models.Adventurer;

/**
 * Service class for functionality for the guild hall area where the player can buy adventurers
 */
public class GuildHallService {
    private final GameEnvironment gameEnvironment;

    /**
     * Constructor to pass in game data
     * @param gameEnvironment reference to game data
     */
    public GuildHallService(GameEnvironment gameEnvironment) {this.gameEnvironment = gameEnvironment;}

    /**
     * Attempt to hire a new adventurer into the party prioritizing the reserve party, otherwise it will try to add the adventurer to the main party
     * @param adventurer the adventurer to be added
     * @return returns potential error string, with null dictating success
     */
    public String hireAdventurer(Adventurer adventurer)
    {
        if (adventurer == null)
        {
            return "No adventurer selected";
        }

        if (adventurer.getHiringCost() > gameEnvironment.getGold())
        {
            return "Not enough money";
        }

        if (addToReserveParty(adventurer))
        {
            gameEnvironment.setGold(
                    gameEnvironment.getGold() - adventurer.getHiringCost()
            );

            return null;
        }

        if (addToMainParty(adventurer))
        {
            gameEnvironment.setGold(
                    gameEnvironment.getGold() - adventurer.getHiringCost()
            );

            return null;
        }

        return "Main and reserve party full";
    }

    /**
     * Attempt to add adventurer to main party
     * @param adventurer the adventurer to be added
     * @return returns true if the adventurer was added, and false if there was no space in the main party
     */
    private boolean addToMainParty(Adventurer adventurer) {
        if (gameEnvironment.getMainParty().size() >= gameEnvironment.MAX_PARTY_SIZE)
        {
            System.out.println("Warning: Main party at maximum capacity");
            return false;
        }
        else
        {
            gameEnvironment.getMainParty().add(adventurer);
            return true;
        }
    }

    /**
     * Attempt to add adventurer to reserve party
     * @param adventurer the adventurer to be added
     * @return returns true if the adventurer was added, and false if there was no space in the reserve party
     */
    private boolean addToReserveParty(Adventurer adventurer)
    {
        if (gameEnvironment.getReserveParty().size() >= 5)
        {
            System.out.println("Warning: Reserve party at maximum capacity");
            return false;
        }
        gameEnvironment.getReserveParty().add(adventurer);
        return true;
    }

    /**
     * Retire an adventurer from the reserve party
     * @param adventurer The adventurer to retire
     * @return true if successful
     */
    public boolean retireAdventurer(Adventurer adventurer)
    {
        if (adventurer == null)
        {
            System.out.println("Warning: No adventurer selected!");
            return false;
        }

        if (!gameEnvironment.getReserveParty().contains(adventurer))
        {
            System.out.println("Warning: Adventurer not found in reserve party!");
            return false;
        }

        gameEnvironment.getReserveParty().remove(adventurer);

        return true;
    }

    public Adventurer getMainPartyAdventurer(int index)
    {
        if (index < 0 || index >= gameEnvironment.getMainParty().size())
        {
            return null;
        }

        return gameEnvironment.getMainParty().get(index);
    }

}
