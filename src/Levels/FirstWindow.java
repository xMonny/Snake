package Levels;

import AllVariables.Variables;

import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import javafx.scene.image.*;
import sample.Main;

public class FirstWindow
{
    public void start(Stage primaryStage)
    {
        //coordinates buttonStart
        int x = 325;
        int y = 350;

        Variables v = new Variables();
        RepeatingCode repeat = new RepeatingCode();

        Pane root = new Pane();
        Canvas canvas = new Canvas(v.getWidth()*v.getCornerSize(), v.getHeight()*v.getCornerSize());
        GraphicsContext gc = canvas.getGraphicsContext2D();

        Scene scene = new Scene(root, v.getWidth()*v.getCornerSize()-1, v.getHeight()*v.getCornerSize()-2);

        repeat.fillBackground(v, gc, Color.BLACK);

        final ImageView imageView = new ImageView();
        final Image image = new Image(Main.class.getResourceAsStream("snake.png"));
        imageView.setImage(image);

        final ImageView imageViewIcon = new ImageView();
        final Image imageIcon = new Image(Main.class.getResourceAsStream("snake_icon.png"));
        imageViewIcon.setImage(imageIcon);

        Label labelSnake = new Label("Snake");

        Button buttonStart = new Button();
        Button buttonHowToPlay = new Button();
        Button buttonExit = new Button();

        buttonStart.setOnAction(new EventHandler<ActionEvent>()
        {
            LevelOne levelOne = new LevelOne();
            public void handle(ActionEvent e)
            {
                levelOne.start(primaryStage);
            }
        });

        buttonHowToPlay.setOnAction(new EventHandler<ActionEvent>()
        {
            HowToPlay howToPlay = new HowToPlay();
            public void handle(ActionEvent e)
            {
                howToPlay.start(primaryStage);
            }
        });

        buttonExit.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent e)
            {
                primaryStage.close();
            }
        });

        buttonStart.setFont(new Font("Times new roman", 30));
        buttonStart.setTextFill(Color.BLACK);
        buttonStart.setLayoutX(x); //325
        buttonStart.setLayoutY(y); //350
        buttonStart.setStyle("-fx-focus-color: transparent;" + "-fx-background-radius: 0.5em;");

        buttonHowToPlay.setFont(new Font("Times new roman", 30));
        buttonHowToPlay.setTextFill(Color.BLACK);
        buttonHowToPlay.setLayoutX(x-45); //280
        buttonHowToPlay.setLayoutY(y+100); //450
        buttonHowToPlay.setStyle("-fx-focus-color: transparent;" + "-fx-background-radius: 0.5em;");

        buttonExit.setFont(new Font("Times new roman", 30));
        buttonExit.setTextFill(Color.BLACK);
        buttonExit.setLayoutX(x); //325
        buttonExit.setLayoutY(y+200); //550
        buttonExit.setStyle("-fx-focus-color: transparent;" + "-fx-background-radius: 0.5em;");

        buttonStart.setText("Start");
        buttonHowToPlay.setText("How to play");
        buttonExit.setText("Exit");

        imageView.setX(x-45); //280
        imageView.setY(y-310); //40
        imageView.setFitWidth(200);
        imageView.setFitHeight(200);

        labelSnake.setTextFill(Color.WHITE);
        labelSnake.setFont(new Font("Monotype Corsiva", 50));
        labelSnake.setLayoutX(x-5); //320
        labelSnake.setLayoutY(y-100); //250

        root.getChildren().add(canvas);
        root.getChildren().add(buttonStart);
        root.getChildren().add(buttonHowToPlay);
        root.getChildren().add(buttonExit);
        root.getChildren().add(imageView);
        root.getChildren().add(labelSnake);

        primaryStage.setTitle("Snake");
        primaryStage.setScene(scene);
        primaryStage.setMaximized(false);
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(imageIcon);
        primaryStage.show();
    }
}

