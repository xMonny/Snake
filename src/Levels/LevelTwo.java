package Levels;

import AllDirections.Directions;
import AllSnake.Snake;
import AllVariables.Variables;
import Design.Barriers;
import Shapes.Food;
import Shapes.Magic;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class LevelTwo
{
    Variables v = new Variables();
    Snake snake = new Snake();
    Food food = new Food();
    Magic magic = new Magic();
    Barriers barriers = new Barriers();
    Directions direction = new Directions();
    RepeatingCode repeat = new RepeatingCode();
    Label score = new Label();

    Directions rememberDirection = new Directions();

    VBox root = new VBox();
    Canvas canvas = new Canvas(v.getWidth()*v.getCornerSize(), v.getHeight()*v.getCornerSize());
    GraphicsContext gc = canvas.getGraphicsContext2D();

    int radiusCircle = v.getCornerSize()-1;

    public void start(Stage primaryStage)
    {
        LevelOne levelOne = new LevelOne();
        v.setRecord(levelOne.getRecord()-1);
        repeat.createStart(root, canvas, score);

        //build barriers
        repeat.buildBarriersLevelTwo(v, barriers, gc, Color.RED);

        food.setFood(v, snake, magic, barriers);

        new AnimationTimer()
        {
            long lastTick = 0;

            public void handle(long now)
            {
                if (lastTick == 0)
                {
                    lastTick = now;
                    tick(v, gc, score, snake, food, magic, barriers, direction, repeat);
                    return;
                }
                if (now-lastTick > 1000000000/v.getSpeed())
                {
                    lastTick = now;
                    tick(v, gc, score, snake, food, magic, barriers, direction, repeat);
                }
                if (v.isGameOver())
                {
                    if (direction.isReset())
                    {
                        LevelOne levelOne = new LevelOne();
                        repeat.restartLevelTwo(v, magic, direction);
                        v.setRecord(-1);
                        v.setLevel(1);
                        levelOne.start(primaryStage);
                    }
                }
                else
                {
                    if (v.getGoNext())
                    {
                        gc.setFill(Color.BLACK);
                        gc.setFont(new Font("Times New Roman", 30));
                        gc.fillText("Congratulations!", 280, 350);
                        gc.fillText("Press ENTER for level 3", 230, 380);
                        if (direction.isEnter())
                        {
                            v.setLevel(3);
                            LevelThree levelThree = new LevelThree();
                            repeat.restartLevelTwo(v, magic, direction);
                            levelThree.start(primaryStage);
                        }
                    }
                    else //pause the game
                    {
                        if (direction.isPause() && repeat.getSecondPause())
                        {
                            repeat.pause(direction, rememberDirection);
                            start();
                        }
                    }
                }
            }
        }.start();

        Scene scene = new Scene(root,v.getWidth()*v.getCornerSize()-1, v.getHeight()*v.getCornerSize()+8);

        //controls
        repeat.defineControls(v, direction, rememberDirection, scene);

        //create snake
        snake.createSnake(3, v.getWidth(), v.getHeight()/2-1);

        primaryStage.setTitle("Snake");
        primaryStage.setScene(scene);
        primaryStage.setMaximized(false);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public void tick(Variables v, GraphicsContext gc, Label score, Snake snake, Food food, Magic magic, Barriers barriers, Directions direction, RepeatingCode repeat)
    {
        if (v.isGameOver())
        {
            gc.setFill(Color.RED);
            gc.setFont(new Font("Times New Roman", 50));
            gc.fillText("Game Over", 270, 350);
            return;
        }
        else
        {
            if (v.getGoNext())
            {
                return;
            }
            if (direction.isPause())
            {
                gc.setFill(Color.RED);
                gc.setFont(new Font("Times New Roman", 50));
                gc.fillText("Paused", 300, 350);
                return;
            }
        }

        snake.moveSnake();

        repeat.caseControls(v, direction, snake);

        //eating food
        snake.eatFood(v, magic, barriers, food);

        //self-eating
        snake.selfDestroy(v);

        //hit barrier
        if (barriers.checkSnakeBounds(snake))
        {
            v.setGameOver(true);
        }

        //fill background
        repeat.fillBackground(v, gc, Color.LIGHTSEAGREEN);

        //update score and level
        score.setText("Score: "+v.getRecord() + "                                                                                  Level: "+v.getLevel());

        //food paint
        if (v.getHelpRecord() == v.getRecordForNextLevel()-1)
        {
            if (radiusCircle >= v.getCornerSize()-1)
            {
                radiusCircle++;
            }
            if (radiusCircle == v.getCornerSize()+3)
            {
                radiusCircle = v.getCornerSize()-1;
            }
            food.paintFood(v, radiusCircle, radiusCircle, gc, Color.DARKSLATEGRAY);
        }
        else
        {
            food.paintFood(v, v.getCornerSize()-1, v.getCornerSize()-1, gc, Color.DARKSLATEGRAY);
        }

        //magic-blue rectangle
        repeat.updateMagic(v, magic, snake, gc, Color.BLUE);

        //snake
        snake.paintSnake(v, gc);

        //build barriers
        repeat.buildBarriersLevelTwo(v, barriers, gc, Color.RED);
    }

    public int getRecord()
    {
        return this.v.getRecord();
    }
}
