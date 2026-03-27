package seng201.team0.services;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class WindowSwitchService {
    public void switchWindow(Stage stage, String fxmlWindowPath) throws IOException {
        FXMLLoader baseLoader = new FXMLLoader(getClass().getResource(fxmlWindowPath));
        Parent root = baseLoader.load();
        Scene scene = new Scene(root, 600, 400);
        stage.setScene(scene);
    }
}
