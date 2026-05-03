package seng201.team0.services;

import javafx.scene.control.Label;
import seng201.team0.models.Adventurer;

public class DisplayStatsService {
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
