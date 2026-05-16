package seng201.team0.services;

import javafx.scene.control.Label;
import seng201.team0.models.Adventurer;
import seng201.team0.models.Item;

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

    /**
     * Update a label to the displayed stats of an item
     * @param item The item to have its stats displayed
     * @param nameLabel The name of the item
     * @param effectLabel The effect of the item
     * @param costLabel The cost of the item
     */
    public void updateItemStats(Item item, Label nameLabel, Label effectLabel, Label costLabel){
        nameLabel.setText(item.getName());
        effectLabel.setText(item.getModifier());
        costLabel.setText(String.valueOf(item.getCost()));
    }
}
