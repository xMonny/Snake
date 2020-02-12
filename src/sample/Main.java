package sample;

import Levels.*;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    FirstWindow first = new FirstWindow();

    public void start(Stage primaryStage)
    {
        first.start(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
