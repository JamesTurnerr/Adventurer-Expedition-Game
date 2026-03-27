package seng201.team0;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GuildHallController {
    @FXML
    private Button backButton;
    @FXML
    private Button hireAdventurerButton;

    @FXML
    private void backButtonClicked() throws IOException {
        FXMLLoader baseLoader = new FXMLLoader(getClass().getResource("/fxml/main_screen.fxml"));
        Parent root = baseLoader.load();
        Stage window = (Stage) backButton.getScene().getWindow();
        Scene scene = new Scene(root, 600, 400);
        window.setScene(scene);
    }
    @FXML
    private void hireAdventurerButtonClicked()
    {
        System.out.println("goToGuildOverview");
    }
}
