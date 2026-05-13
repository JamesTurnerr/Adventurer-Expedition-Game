package seng201.team0.models;

public class Event {
    private String eventDescription;
    private Choice[] choices;
    Event(String eventDescription, Choice[] choices)
    {
        this.eventDescription = eventDescription;
        this.choices = choices;
    }
    public String getEventDescription()
    {
        return eventDescription;
    }
    public Choice[] getChoices() {
        return choices;
    }
}
