package seng201.team0;

import java.util.ArrayList;
import java.util.List;

public class Party {

    private List<Adventurer> partyList;
    private final int maxSize = 5;

    private static Party party;

    public Party(){
        partyList = new ArrayList<>();
    }

    public List<Adventurer> getPartyList(){return partyList;}

    public boolean addToParty(Adventurer adventurer){
        if (!isFull() && !partyList.contains(adventurer)){
            partyList.add(adventurer);
            return true;
        }
        return false;
    }

    public boolean removeFromParty(Adventurer adventurer){
        if (partyList.size() != 0 && partyList.contains(adventurer)){
            partyList.remove(adventurer);
            return true;
        }
        return false;
    }

    public boolean isFull(){
        return partyList.size() >= maxSize;
    }

    //creates a single instance of party - kinda like global variable
    public static Party getInstance(){
        if (party == null){
            party = new Party();
        }
        return party;
    }
    //party.clear() ?
}
