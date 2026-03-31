package seng201.team0.services;

import javafx.scene.control.ListView;
import seng201.team0.models.Adventurer;


//potentially make subclass of a listview service - include items in other subclass
public class AdventurerListViewService {
    // will populate the list view with all characters not already in party
    public static void fill(ListView<Adventurer> listView){
        //Party party = Party.getInstance();
        //listView.getItems().setAll(notInPartyList(party));
        for (int i = 0; i < 5; i++)
        {
            listView.getItems().add(AdventurerCreationService.createRandomAdventurer());
        }


    }

    //returns all characters not in party
    /*private static List<Adventurer> notInPartyList(Party party){
        List<Adventurer> notInPartyList = new ArrayList<>();
        for (Adventurer adventurer : Adventurer.values()){
            if (!party.getPartyList().contains(adventurer)){
                notInPartyList.add(adventurer);
            }
        }
        return notInPartyList;
    }*/
}
