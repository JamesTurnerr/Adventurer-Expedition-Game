package seng201.team0.services;

import javafx.scene.control.TextArea;
import seng201.team0.models.Expedition;

public class ExpeditionService {
    TextArea expeditionTextArea;
    public ExpeditionService(TextArea expeditionTextArea)
    {
        Expedition expedition = new Expedition(2);
        this.expeditionTextArea = expeditionTextArea;
    }
    public void writeLine(String string)
    {
        if (expeditionTextArea.getText() == "")
        {
            expeditionTextArea.setText(string);
        }
        else
        {
            expeditionTextArea.setText(expeditionTextArea.getText() + "\n" + string);
        }
    }
}
