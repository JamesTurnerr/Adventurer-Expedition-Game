package seng201.team0.services;

import javafx.scene.control.TextArea;
import seng201.team0.models.Expedition;

import java.util.Random;

public class ExpeditionService {
    Random rand = new Random();
    Expedition expedition;
    String location;
    String[] areas;
    TextArea expeditionTextArea;
    public ExpeditionService(TextArea expeditionTextArea, int numberOfAreas)
    {
        expedition = new Expedition(numberOfAreas);
        int randInt = rand.nextInt(expedition.ExpeditionLocation.length);
        this.expeditionTextArea = expeditionTextArea;
        location = expedition.ExpeditionLocation[randInt];
        areas = expedition.getAreas(location);
        writeLine(String.format("You start your expedition in the %s at a %s", location, areas[randInt]));
    }
    public void writeLine(String string)
    {
        if (expeditionTextArea.getText().isEmpty())
        {
            expeditionTextArea.setText(string);
        }
        else
        {
            expeditionTextArea.setText(expeditionTextArea.getText() + "\n" + string);
        }
    }
}
