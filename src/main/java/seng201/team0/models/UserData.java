package seng201.team0.models;
import seng201.team0.Adventurer;
import seng201.team0.Item;
import java.util.List;
import java.util.ArrayList;

public class UserData {
    public List<Adventurer> mainParty = new ArrayList<Adventurer>();
    public List<Adventurer> reserveParty = new ArrayList<Adventurer>();
    public List<Item> items = new ArrayList<Item>();
}
