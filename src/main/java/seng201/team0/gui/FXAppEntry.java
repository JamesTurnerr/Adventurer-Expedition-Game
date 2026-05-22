package seng201.team0.gui;

import javafx.application.Application;
import javafx.stage.Stage;
import seng201.team0.GameEnvironment;

import javafx.scene.image.Image;

/**
 * JavaFX entry class
 */
public class FXAppEntry extends Application {

    /**
     * Creates the {@link GameEnvironment} with a {@link ScreenNavigator} for the given {@link Stage}
     * @param primaryStage The current fxml stage, handled by this JavaFX Application class
     */
    @Override
    public void start(Stage primaryStage) {
        var stream = getClass().getResourceAsStream("/images/test.png");

        if (stream == null) {
            System.out.println("Warning: Icon not found");
        } else {
            primaryStage.getIcons().add(new Image(stream));
        }
        ScreenNavigator navigator = new ScreenNavigator(primaryStage);
        new GameEnvironment(navigator);
    }
}
