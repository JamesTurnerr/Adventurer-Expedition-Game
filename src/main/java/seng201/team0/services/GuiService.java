package seng201.team0.services;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class GuiService {
    public <T> void populateListView(ListView<T> listView, List<T> data)//Must be same type
    {
        listView.getItems().clear();
        for (T t : data)
        {
            listView.getItems().add(t);
        }
    }
    public void switchWindow(Stage stage, String fxmlWindowPath) throws IOException {
        FXMLLoader baseLoader = new FXMLLoader(getClass().getResource(fxmlWindowPath));
        Parent root = baseLoader.load();
        Scene scene = new Scene(root, 600, 400);
        stage.setScene(scene);
    }
}
