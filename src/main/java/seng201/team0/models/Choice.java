package seng201.team0.models;

/**
 * A class used as each choice in an expedition event.
 */
public class Choice {
    private final String choiceDescription;
    private final EventOutcome[] eventOutcomes;

    /**
     * Constructor for choice class, holds the name of the choice and the choice outcome(s).
     * @param choiceDescription The description of the choice e.g. "Search chest", "Attack enemy"
     * @param eventOutcomes The outcome(s) of the choice, all effects from the outcome will be applied to an adventurer
     */
    Choice(String choiceDescription, EventOutcome[] eventOutcomes)
    {
        this.choiceDescription = choiceDescription;
        this.eventOutcomes = eventOutcomes;
    }
    public String getChoice()
    {
        return choiceDescription;
    }

    /**
     * Concatenates all the effects that the players choice has made.
     * @return A String of effects such as "lose a little bit of health and find a little bit of gold"
     */
    public String getChoiceOutcome()
    {
        StringBuilder totalOutcome = new StringBuilder();
        for (EventOutcome eventOutcome : eventOutcomes)
        {
            if (totalOutcome.toString().isEmpty())
            {
                totalOutcome.append(eventOutcome.getOutcome());
            }
            else
            {
                totalOutcome.append(" and\n").append(eventOutcome.getOutcome());
            }

        }
        return totalOutcome.toString();
    }

    /**
     * Gets all the outcomes of an event
     * @return An array of the event outcome enums
     */
    public EventOutcome[] getEventOutcomes()
    {
        return eventOutcomes;
    }
}
