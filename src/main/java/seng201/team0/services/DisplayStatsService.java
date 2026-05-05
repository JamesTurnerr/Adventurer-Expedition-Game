package seng201.team0.services;

import javafx.scene.control.Label;
import seng201.team0.models.Adventurer;

/**
 * Service class to update the UI labels containing adventurer stats
 */
public class DisplayStatsService {
    /**
     * Update UI labels of adventurer stats
     * @param adventurer the adventurer object that the labels will change to the stats of
     * @param nameLabel the label that will be changed to the adventurers name
     * @param healthLabel the label that will be changed to the adventurers health
     * @param staminaLabel the label that will be changed to the adventurers stamina
     * @param perceptionLabel the label that will be changed to the adventurers perception
     * @param costLabel the label that will be changed to the adventurers cost
     * @param payLabel the label that will be changed to the adventurers pay
     * @param damageLabel the label that will be changed to the adventurers damage
     */
    public void updateStats(
            Adventurer adventurer,
            Label nameLabel,
            Label healthLabel,
            Label staminaLabel,
            Label perceptionLabel,
            Label costLabel,
            Label payLabel,
            Label damageLabel)
    {

        nameLabel.setText(adventurer.getName());
        healthLabel.setText(String.valueOf(adventurer.getHealth()));
        staminaLabel.setText(String.valueOf(adventurer.getStamina()));
        perceptionLabel.setText(String.valueOf(adventurer.getPerception()));
        costLabel.setText(String.valueOf(adventurer.getHiringCost()));
        payLabel.setText(String.valueOf(adventurer.getPay()));
        damageLabel.setText(String.valueOf(adventurer.getDamage()));
    }
}
