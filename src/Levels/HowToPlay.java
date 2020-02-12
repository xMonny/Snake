package Levels;

import AllVariables.Variables;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import sample.Main;

public class HowToPlay
{
    public void start(Stage primaryStage)
    {
        //coordinates controls image
        final int x = 170;
        final int y = 130;

        //coordinates description
        final int x_text = 10;
        final int y_text = 315;

        Variables v = new Variables();
        RepeatingCode repeat = new RepeatingCode();

        Pane root = new Pane();
        Canvas canvas = new Canvas(v.getWidth()*v.getCornerSize(), v.getHeight()*v.getCornerSize());
        GraphicsContext gc = canvas.getGraphicsContext2D();

        Scene scene = new Scene(root, v.getWidth()*v.getCornerSize()-1, v.getHeight()*v.getCornerSize()-2);

        repeat.fillBackground(v, gc, Color.BLACK);

        final ImageView imageView = new ImageView();
        final Image image = new Image(Main.class.getResourceAsStream("controls.png"));
        imageView.setImage(image);

        final ImageView imageViewIcon = new ImageView();
        final Image imageIcon = new Image(Main.class.getResourceAsStream("snake_icon.png"));
        imageViewIcon.setImage(imageIcon);

        final ImageView imageViewSnake = new ImageView();
        final Image imageSnake = new Image(Main.class.getResourceAsStream("snakeSecondPage.png"));
        imageViewSnake.setImage(imageSnake);

        final ImageView imageViewP = new ImageView();
        final Image imageP = new Image(Main.class.getResourceAsStream("P_control.png"));
        imageViewP.setImage(imageP);

        final ImageView imageViewR = new ImageView();
        final Image imageR = new Image(Main.class.getResourceAsStream("R_control.png"));
        imageViewR.setImage(imageR);

        final ImageView imageViewEnter = new ImageView();
        final Image imageEnter = new Image(Main.class.getResourceAsStream("Enter_control.png"));
        imageViewEnter.setImage(imageEnter);

        Button buttonStart = new Button();
        Button buttonBack = new Button();

        Label how = new Label("How to play");
        Label up = new Label("Up");
        Label down = new Label("Down");
        Label right = new Label("Right");
        Label left = new Label("Left");
        Label pause = new Label("Pause");
        Label restart = new Label("Restart");
        Label nextLevel = new Label("Next level");

        Label controls = new Label("Controls:");
        Label description = new Label("Description:");
        Label text = new Label("The game has 5 levels. The main character is a snake and it has to eat the \nfood (the circle). There is a blue square which appears in a certain time. \nIts power is to decrease the snake's speed for a period of time. Be careful \nabout everything in RED. If the snake touches anything in RED color or \nif the snake bites itself, then it is a Game Over. The snake can move from \nthe right side of the screen to the left side of the screen if there is nothing \nin RED (for example). On the last level you can play until the snake bites \nitself.");

        buttonStart.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent e)
            {
                LevelOne levelOne = new LevelOne();
                levelOne.start(primaryStage);
            }
        });

        buttonBack.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent e)
            {
                FirstWindow first = new FirstWindow();
                first.start(primaryStage);
            }
        });

        buttonStart.setFont(new Font("Times new roman", 30));
        buttonStart.setTextFill(Color.BLACK);
        buttonStart.setLayoutX(640);
        buttonStart.setLayoutY(660);
        buttonStart.setStyle("-fx-focus-color: transparent;" + "-fx-background-radius: 0.5em;");

        buttonBack.setFont(new Font("Times new roman", 30));
        buttonBack.setTextFill(Color.BLACK);
        buttonBack.setLayoutX(10);
        buttonBack.setLayoutY(660);
        buttonBack.setStyle("-fx-focus-color: transparent;" + "-fx-background-radius: 0.5em;");

        buttonStart.setText("Start");
        buttonBack.setText("Back");

        //image controls
        imageView.setX(x); //280
        imageView.setY(y); //130
        imageView.setFitWidth(150);
        imageView.setFitHeight(100);

        //image down snake
        imageViewSnake.setX(230); //280
        imageViewSnake.setY(500); //130
        imageViewSnake.setFitWidth(350);
        imageViewSnake.setFitHeight(250);

        //image P control
        imageViewP.setX(x+280); //450
        imageViewP.setY(y); //130
        imageViewP.setFitWidth(50);
        imageViewP.setFitHeight(50);

        //image R control
        imageViewR.setX(x+280); //450
        imageViewR.setY(y+60); //190
        imageViewR.setFitWidth(50);
        imageViewR.setFitHeight(45);

        //image Enter control
        imageViewEnter.setX(x+275); //445
        imageViewEnter.setY(y+120); //250
        imageViewEnter.setFitWidth(100);
        imageViewEnter.setFitHeight(50);

        how.setTextFill(Color.WHITE);
        how.setFont(new Font("Monotype Corsiva", 50));
        how.setLayoutX(280);
        how.setLayoutY(0);

        up.setTextFill(Color.WHITE);
        up.setFont(new Font("Times New Roman", 20));
        up.setLayoutX(x+60); //340
        up.setLayoutY(y-30); //100

        down.setTextFill(Color.WHITE);
        down.setFont(new Font("Times New Roman", 20));
        down.setLayoutX(x+50); //330
        down.setLayoutY(y+105); //235

        right.setTextFill(Color.WHITE);
        right.setFont(new Font("Times New Roman", 20));
        right.setLayoutX(x+155); //435
        right.setLayoutY(y+65); //195

        left.setTextFill(Color.WHITE);
        left.setFont(new Font("Times New Roman", 20));
        left.setLayoutX(x-40); //240
        left.setLayoutY(y+65); //195

        pause.setTextFill(Color.WHITE);
        pause.setFont(new Font("Times New Roman", 20));
        pause.setLayoutX(x+335); //505
        pause.setLayoutY(y+15); //145

        restart.setTextFill(Color.WHITE);
        restart.setFont(new Font("Times New Roman", 20));
        restart.setLayoutX(x+335); //505
        restart.setLayoutY(y+75); //205

        nextLevel.setTextFill(Color.WHITE);
        nextLevel.setFont(new Font("Times New Roman", 20));
        nextLevel.setLayoutX(x+380); //550
        nextLevel.setLayoutY(y+135); //265

        controls.setTextFill(Color.WHITE);
        controls.setFont(new Font("Times New Roman", 25));
        controls.setStyle("-fx-font-style: italic");
        controls.setLayoutX(10); //240
        controls.setLayoutY(188); //195

        description.setTextFill(Color.WHITE);
        description.setFont(new Font("Times New Roman", 25));
        description.setStyle("-fx-font-style: italic");
        description.setLayoutX(x_text); //10
        description.setLayoutY(y_text); //315

        text.setTextFill(Color.WHITE);
        text.setFont(new Font("Times New Roman", 20));
        text.setLayoutX(x_text+140); //150
        text.setLayoutY(y_text+5); //320

        //background and buttons
        root.getChildren().add(canvas);
        root.getChildren().add(buttonStart);
        root.getChildren().add(buttonBack);

        //images
        root.getChildren().add(imageView);
        root.getChildren().add(imageViewSnake);
        root.getChildren().add(imageViewP);
        root.getChildren().add(imageViewR);
        root.getChildren().add(imageViewEnter);

        //labels
        root.getChildren().add(how);
        root.getChildren().add(up);
        root.getChildren().add(down);
        root.getChildren().add(right);
        root.getChildren().add(left);
        root.getChildren().add(pause);
        root.getChildren().add(restart);
        root.getChildren().add(nextLevel);
        root.getChildren().add(controls);

        //text
        root.getChildren().add(description);
        root.getChildren().add(text);

        primaryStage.setTitle("Snake");
        primaryStage.setScene(scene);
        primaryStage.setMaximized(false);
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(imageIcon);
        primaryStage.show();
    }
}