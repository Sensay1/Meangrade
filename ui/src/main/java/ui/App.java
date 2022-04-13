package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import json.CourseHandler;

import java.io.IOException;

public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Parent parent = FXMLLoader.load(this.getClass().getResource("App.fxml"));
        stage.setScene(new Scene(parent));
        stage.setTitle("Vault");
        stage.show();
    }

    public static void main(String[] args) {
        launch(App.class, args);
    }

}