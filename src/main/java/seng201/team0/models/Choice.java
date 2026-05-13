package seng201.team0.models;

public class Choice {
    private final String choiceDescription;
    private final String statChecked;
    Choice(String choiceDescription, String statChecked)
    {
        this.choiceDescription = choiceDescription;
        this.statChecked = statChecked;
    }
    public String  getChoice()
    {
        return choiceDescription;
    }
}
