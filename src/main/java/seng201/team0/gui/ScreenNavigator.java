package seng201.team0.gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import seng201.team0.GameEnvironment;

import java.io.IOException;

/**
 * Class that handles navigation between various {@link ScreenController}s. This navigator
 * uses a {@link BorderPane} layout for the root pane. A launched screen is placed in the
 * center area of the border pane, replacing the previous screen if any.
 *
 * @author seng201 teaching team
 */
public class ScreenNavigator {

    private final Stage stage;

    private final BorderPane rootPane;

    /**
     * Creates a ScreenNavigator with the given stage.
     *
     * @param stage The JavaFX stage
     */
    public ScreenNavigator(Stage stage) {
        this.stage = stage;

        // Use a border pane as the root component to allow children to be resizable.
        rootPane = new BorderPane();
        rootPane.setPrefHeight(500);
        rootPane.setPrefWidth(600);
        stage.setScene(new Scene(rootPane));
        stage.show();
    }

    /**
     * Launches the setup screen.
     *
     * @param gameEnvironment The game environment used by the setup screen controller
     */
    public void launchSetupScreen(GameEnvironment gameEnvironment) {
        ScreenController controller = new SetupScreenController(gameEnvironment);
        launchScreen(controller);
    }

    /**
     * Launches the main screen.
     *
     * @param gameEnvironment The game environment used by the main screen controller
     */
    public void launchMainScreen(GameEnvironment gameEnvironment) {
        ScreenController controller = new MainScreenController(gameEnvironment);
        launchScreen(controller);
    }

    /**
     * Launches the game over screen.
     *
     * @param gameEnvironment The game environment used by the main screen controller
     */
    public void launchGameOverScreen(GameEnvironment gameEnvironment) {
        ScreenController controller = new GameOverScreenController(gameEnvironment);
        launchScreen(controller);
    }

    /**
     * Launches the expedition screen.
     *
     * @param gameEnvironment The game environment used by the expedition screen controller
     */
    public void launchExpeditionScreen(GameEnvironment gameEnvironment) {
        ScreenController controller = new ExpeditionScreenController(gameEnvironment);
        launchScreen(controller);
    }

    /**
     * Launches the guild hall screen.
     *
     * @param gameEnvironment The game environment used by the guild hall screen controller
     */
    public void launchGuildHallScreen(GameEnvironment gameEnvironment) {
        ScreenController controller = new GuildHallScreenController(gameEnvironment);
        launchScreen(controller);
    }

    /**
     * Launches the guild overview screen.
     *
     * @param gameEnvironment The game environment used by the guild overview screen controller
     */
    public void launchGuildOverviewScreen(GameEnvironment gameEnvironment) {
        ScreenController controller = new GuildOverviewScreenController(gameEnvironment);
        launchScreen(controller);
    }

    /**
     * Launches the market screen.
     *
     * @param gameEnvironment The game environment used by the market screen controller
     */
    public void launchMarketScreen(GameEnvironment gameEnvironment) {
        ScreenController controller = new MarketScreenController(gameEnvironment);
        launchScreen(controller);
    }

    /**
     * Launches the expedition selection screen.
     *
     * @param gameEnvironment The game environment used by the market screen controller
     */
    public void launchExpeditionLocationScreen(GameEnvironment gameEnvironment){
        ScreenController controller = new ExpeditionSelectScreenController(gameEnvironment);
        launchScreen(controller);
    }

    /**
     * Launches the random event screen at the end of an expedition.
     *
     * @param gameEnvironment The game environment used by the market screen controller
     */
    public void launchRandomEventScreen(GameEnvironment gameEnvironment){
        ScreenController controller = new RandomEventScreenController(gameEnvironment);
        launchScreen(controller);
    }

    /**
     * Replaces the root border pane's center component with the screen defined by the given
     * {@link ScreenController}.
     *
     * @param controller The JavaFX screen controller for the screen to be launched
     */
    private void launchScreen(ScreenController controller) {
        try {
            FXMLLoader setupLoader = new FXMLLoader(getClass().getResource(controller.getFxmlFile()));
            // Set a controller factory that returns the given ScreenController.
            // This allows us to have screen controllers that take argument(s) in their constructor.
            setupLoader.setControllerFactory(param -> controller);
            Parent setupParent  = setupLoader.load();
            rootPane.setCenter(setupParent);
            stage.setTitle(controller.getTitle());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
