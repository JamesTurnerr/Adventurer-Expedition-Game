package seng201.team0.models;

public class Event {
    private String eventDescription;
    private Choice[] choices;

    /**
     * Event constructor
     * @param eventDescription What the event is
     * @param choices What choices can be made during the event
     */
    Event(String eventDescription, Choice[] choices)
    {
        this.eventDescription = eventDescription;
        this.choices = choices;
    }

    /**
     * Gets what's happening during the event e.g. "A rock is rolling towards you"
     * @return A String describing the event
     */
    public String getEventDescription()
    {
        return eventDescription;
    }

    /**
     * Gets an array of choices that can be made for the expedition event
     * @return The choices of the event
     */
    public Choice[] getChoices() {
        return choices;
    }
}
