package seng201.team0.models;

public class Choice {
    private final String choiceDescription;
    private final EventOutcome[] eventOutcomes;
    Choice(String choiceDescription, EventOutcome[] eventOutcomes)
    {
        this.choiceDescription = choiceDescription;
        this.eventOutcomes = eventOutcomes;
    }
    public String getChoice()
    {
        return choiceDescription;
    }

    public String getChoiceOutcome()
    {
        StringBuilder totalOutcome = new StringBuilder();
        for (EventOutcome eventOutcome : eventOutcomes)
        {
            totalOutcome.append(" ").append(eventOutcome.getOutcome());
        }
        return totalOutcome.toString();
    }

    public EventOutcome[] getEventOutcomes()
    {
        return eventOutcomes;
    }
}
