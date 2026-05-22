package seng201.team0.models;

import java.util.Random;

/**
 * The possible outcomes that can happen during any event
 */
public enum EventOutcome {
    SMALL_HEALTH_LOSS("lose a small amount of health"),
    MEDIUM_HEALTH_LOSS("lose a moderate amount of health"),
    LARGE_HEALTH_LOSS("lose a lot of health"),
    SMALL_STAMINA_LOSS("lose a small amount of stamina"),
    MEDIUM_STAMINA_LOSS("lose a moderate amount of stamina"),
    LARGE_STAMINA_LOSS("lose a lot of stamina"),
    SMALL_PERCEPTION("SMALL_PERCEPTION_CHECK"),
    MEDIUM_PERCEPTION("MEDIUM_PERCEPTION_CHECK"),
    LARGE_PERCEPTION("LARGE_PERCEPTION_CHECK"),
    SMALL_GOLD("find a little bit of gold"),
    MEDIUM_GOLD("find a substantial amount of gold"),
    LARGE_GOLD("find almost 1 million dollar"),
    LOSE_PROGRESS("lose progress"),
    NOTHING("have nothing happen to you");

    private final String outcome;

    /**
     * EventOutcome constructor, maps enumerators to readable text
     * @param outcome The readable text
     */
    EventOutcome(String outcome)
    {
        this.outcome = outcome;
    }

    /**
     * Get a readable String of the outcome from its enumerator
     * @return A readable string of the outcome
     */
    public String getOutcome()
    {
        return outcome;
    }
}
