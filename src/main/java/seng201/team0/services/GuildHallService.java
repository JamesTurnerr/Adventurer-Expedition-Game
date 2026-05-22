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
     * @return returns true if the adventurer was added, and false if there was no space in the reserve or main party
     */
    public boolean hireAdventurer(Adventurer adventurer)
    {
        if (adventurer != null)
        {
            if (adventurer.getHiringCost() <= gameEnvironment.getGold())
            {
                if (addToReserveParty(adventurer))//Try to add new adventurer to reserves
                {
                    gameEnvironment.setGold(gameEnvironment.getGold() - adventurer.getHiringCost());
                    return true;
                }
                else//Try to add new adventurer to main party
                {
                    System.out.println("Warning: Reserve party full, attempting to add to main party");
                    if (addToMainParty(adventurer))
                    {
                        gameEnvironment.setGold(gameEnvironment.getGold() - adventurer.getHiringCost());
                        return true;
                    }
                    else
                    {
                        System.out.println("Warning: Main party full, could not hire new adventurer");
                        return false;
                    }

                }
            }
            else {
                System.out.println("Warning: Not enough gold, could not hire new adventurer");
                return false;
            }
        }
        else {
            System.out.println("Warning: No adventurer selected!");
            return false;
        }
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

}
