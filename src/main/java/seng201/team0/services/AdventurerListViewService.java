package seng201.team0.services;

import javafx.scene.control.ListView;
import seng201.team0.Adventurer;
import seng201.team0.Party;

import java.util.ArrayList;
import java.util.List;

public class AdventurerListViewService {
    public static void fill(ListView<Adventurer> listView){
        //List.of(Adventurer.values())
        Party party = Party.getInstance();
        listView.getItems().setAll(notInPartyList(party));

    }

    private static List<Adventurer> notInPartyList(Party party){
        List<Adventurer> notInPartyList = new ArrayList<>();
        for (Adventurer adventurer : Adventurer.values()){
            if (!party.getPartyList().contains(adventurer)){
                notInPartyList.add(adventurer);
            }
        }
        return notInPartyList;
    }
}
