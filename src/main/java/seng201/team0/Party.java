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

    public boolean isFull(){
        return partyList.size() >= maxSize;
    }

    public static Party getInstance(){
        if (party == null){
            party = new Party();
        }
        return party;
    }
}
