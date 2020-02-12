package Levels;

import AllDirections.Directions;
import AllSnake.Snake;
import AllVariables.Variables;
import Design.Barriers;
import Shapes.Food;
import Shapes.Magic;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

public class RepeatingCode {
    boolean paused = false;
    boolean secondPause = false;

    public void createStart(VBox root, Canvas canvas, Label score) {
        score.setFont(new Font("Monotype Corsiva", 30));
        score.setTextFill(Color.BLACK);
        root.getChildren().add(score);
        root.getChildren().add(canvas);
    }

    public void defineControls(Variables v, Directions direction, Directions rememberDirection, Scene scene) {
        scene.addEventFilter(KeyEvent.KEY_PRESSED, key ->
        {
            if (key.getCode() == KeyCode.W) {
                if (!paused && !secondPause) {
                    if (!direction.isVerticalDirection()) {
                        direction.setDirection(Directions.Direction.up);
                    }
                    direction.setVerticalDirection(true);
                    direction.setHorizontalDirection(false);
                }
            }
            if (key.getCode() == KeyCode.A) {
                if (!paused && !secondPause) {
                    if (!direction.isHorizontalDirection()) {
                        direction.setDirection(Directions.Direction.left);
                    }
                    direction.setHorizontalDirection(true);
                    direction.setVerticalDirection(false);
                }
            }
            if (key.getCode() == KeyCode.D) {
                if (!paused && !secondPause) {
                    if (!direction.isHorizontalDirection()) {
                        direction.setDirection(Directions.Direction.right);
                    }
                    direction.setHorizontalDirection(true);
                    direction.setVerticalDirection(false);
                }
            }
            if (key.getCode() == KeyCode.S) {
                if (!paused && !secondPause) {
                    if (!direction.isVerticalDirection()) {
                        direction.setDirection(Directions.Direction.down);
                    }
                    direction.setVerticalDirection(true);
                    direction.setHorizontalDirection(false);
                }
            }
            if (v.isGameOver() && key.getCode() == KeyCode.R) {
                direction.setDirection(Directions.Direction.reset);
            }
            if (!v.isGameOver() && key.getCode() == KeyCode.P) {
                if (paused) {
                    secondPause = true;
                } else {
                    rememberDirection.setDirection(direction.getDirection());
                    direction.setDirection(Directions.Direction.pause);
                    paused = true;
                }
            }
            if (!v.isGameOver() && v.getGoNext() && key.getCode() == KeyCode.ENTER)
            {
                direction.setDirection(Directions.Direction.enter);
            }
        });
    }

    public void restartOtherLevels(Variables v, Magic magic, Directions direction) {
        v.setGameOver(false);
        v.setGoNext(false);
        v.setHelpRecord(-1);
        direction.setHorizontalDirection(true);
        direction.setVerticalDirection(false);
        v.setSpeed(8);
        direction.setDirection(Directions.Direction.right);
        magic.setSlowTime(0);
        magic.setCheckTime(0);
        magic.setCheckMagic(false);
        magic.setAfterMagic(false);
        magic.setCheckIn(false);
        paused = false;
        secondPause = false;
    }

    public void restartLevelOne(Variables v, Snake snake, Food food, Magic magic, Barriers barrier, Directions direction)
    {
        restartOtherLevels(v, magic, direction);
        v.setRecord(-1);
        snake.clearSnake();
        food.setFood(v, snake, magic, barrier);
    }

    public void restartLevelTwo(Variables v, Magic magic, Directions direction)
    {
        restartOtherLevels(v, magic, direction);
    }

    public void restartLevelThree(Variables v, Magic magic, Directions direction)
    {
        restartOtherLevels(v, magic, direction);
    }

    public void restartLevelFour(Variables v, Magic magic, Directions direction)
    {
        restartOtherLevels(v, magic, direction);
    }

    public void restartLevelFinal(Variables v, Magic magic, Directions direction)
    {
        restartOtherLevels(v, magic, direction);
        v.setRecord(-1);
    }

    public void pause(Directions direction, Directions rememberDirection) {
        direction.setDirection(rememberDirection.getDirection());
        paused = false;
        secondPause = false;
    }

    public boolean getSecondPause() {
        return secondPause;
    }

    public void caseControls(Variables v, Directions direction, Snake snake) {
        if (direction.isUp()) {
            snake.setSnakeY(0, snake.getSnakeY(0) - 1);
            if (snake.getSnakeY(0) < 0) {
                snake.setSnakeY(0, v.getHeight() - 2);
            }
        }
        if (direction.isLeft()) {
            snake.setSnakeX(0, snake.getSnakeX(0) - 1);
            if (snake.getSnakeX(0) < 0) {
                snake.setSnakeX(0, v.getWidth() - 1);
            }
        }
        if (direction.isRight()) {
            snake.setSnakeX(0, snake.getSnakeX(0) + 1);
            if (snake.getSnakeX(0) > v.getWidth() - 1) {
                snake.setSnakeX(0, 0);
            }
        }
        if (direction.isDown()) {
            snake.setSnakeY(0, snake.getSnakeY(0) + 1);
            if (snake.getSnakeY(0) > v.getHeight() - 2) {
                snake.setSnakeY(0, 0);
            }
        }
    }

    public void fillBackground(Variables v, GraphicsContext gc, Paint paint) {
        gc.setFill(paint);
        gc.fillRect(0, 0, v.getWidth() * v.getCornerSize(), v.getWidth() * v.getCornerSize() - 2);
    }

    public void updateMagic(Variables v, Magic magic, Snake snake, GraphicsContext gc, Paint paint) {
        if (magic.getCheckMagic()) {
            magic.paintMagic(v, gc, paint);
            magic.setCheckTime(magic.getCheckTime() + 1);
            if (magic.getCheckTime() == magic.getRememberCheckTime()) {
                magic.setCheckMagic(false);
                magic.setCheckTime(0); //increase time of the box after the first time
                magic.setRememberCheckTime(magic.getRememberCheckTime()+20);
            }
        }
        if (magic.getSlowX() == snake.getSnakeX(0) && magic.getSlowY() == snake.getSnakeY(0) && magic.getCheckMagic()) {
            magic.setAfterMagic(true); //activate the effect of the blue box - the snake will be slow for a period of time
            magic.setCheckMagic(false); //the blue box won't be painted anymore
            magic.setRememberSpeed(v.getSpeed()); //we remember this speed
            v.setSpeed(v.getSpeed() - 3);
        }
        if (magic.getAfterMagic()) //after eating the blue box
        {
            magic.setSlowTime(magic.getSlowTime() + 1);
            if (magic.getSlowTime() == 100) //end of the effect
            {
                magic.setSlowTime(0);
                v.setSpeed(magic.getRememberSpeed());
                magic.setAfterMagic(false);
            }
        }
    }

    public void buildBarriersLevelOne(Variables v, Barriers barriers, GraphicsContext gc, Paint paint) {
        int positionX = 0;
        int positionY = 15;
        for (int i = 0; i < 20; i++) {
            barriers.paintBarrier(gc, paint, "rectangle", positionX * v.getCornerSize(), positionY * v.getCornerSize(), v.getCornerSize(), v.getCornerSize());
            barriers.addBarrier(positionX, positionY);
            if (i == 9) {
                positionX = 15;
            } else {
                positionX++;
            }
        }

        positionX = 5;
        positionY = 3;
        for (int i = 0; i < 20; i++) {
            if (i < 4) {
                positionY++;
            } else {
                if (i <= 16) {
                    positionX++;
                } else {
                    positionY--;
                }
            }
            barriers.paintBarrier(gc, paint, "rectangle",positionX * v.getCornerSize(), positionY * v.getCornerSize(), v.getCornerSize(), v.getCornerSize());
            barriers.addBarrier(positionX, positionY);
        }
    }

    public void buildBarriersLevelTwo(Variables v, Barriers barriers, GraphicsContext gc, Paint paint)
    {
        int positionX = 0;
        int positionY = 0;
        for (int i = 0; i < 60; i++)
        {
            barriers.paintBarrier(gc, paint, "rectangle", positionX * v.getCornerSize(), positionY * v.getCornerSize(), v.getCornerSize()-1, v.getCornerSize()-1);
            barriers.addBarrier(positionX, positionY);
            if (i <= 6) //top left
            {
                positionX++;
            }
            if (i >= 7 && i <= 14) //top right
            {
                if (i == 7)
                {
                    positionX = 24;
                }
                else
                {
                    positionX--;
                }
            }
            if (i >= 15 && i <= 21) //left side
            {
                if (i == 15)
                {
                    positionX = 0;
                    positionY = 0;
                }
                positionY++;
            }
            if (i >= 22 && i <= 28) //right side
            {
                if (i == 22)
                {
                    positionY = 0;
                    positionX = 24;
                }
                positionY++;
            }
            if (i >= 29 && i <= 36) //bottom left
            {
                if (i == 29)
                {
                    positionX = 0;
                    positionY = 23;
                }
                else
                {
                    positionX++;
                }
            }
            if (i >= 37 && i <= 44) //bottom right
            {
                if (i == 37)
                {
                    positionX = 24;
                    positionY = 23;
                }
                else
                {
                    positionX--;
                }
            }
            if (i >= 45 && i <= 51) //left side
            {
                if (i == 45)
                {
                    positionX = 0;
                    positionY = 23;
                }
                positionY--;
            }
            if (i >= 52 && i <= 58) //right side
            {
                if (i == 52)
                {
                    positionX = 24;
                    positionY = 23;
                }
                positionY--;
            }
        }

        positionX = 4;
        positionY = 8;

        for (int i = 0; i < 30; i++)
        {
            if (i == 0)
            {
                positionY++;
            }
            if (i == 15)
            {
                positionX = 4;
                positionY = 13;
            }
            positionX++;

            barriers.paintBarrier(gc, paint, "rectangle",positionX * v.getCornerSize(), positionY * v.getCornerSize(), v.getCornerSize(), v.getCornerSize());
            barriers.addBarrier(positionX, positionY);
        }
    }

    public void buildBarriersLevelThree(Variables v, Barriers barriers, GraphicsContext gc, Paint paint)
    {
        int positionX = 1;
        int positionY = 8;

        for (int i = 0; i < 23; i++)
        {
            barriers.paintBarrier(gc, paint, "rectangle", positionX * v.getCornerSize(), positionY * v.getCornerSize(), v.getCornerSize(), v.getCornerSize());
            barriers.addBarrier(positionX, positionY);

            positionX++;
        }

        positionX = 0;
        positionY = 8;

        while (positionY >= 0)
        {
            barriers.paintBarrier(gc, paint, "circle", positionX * v.getCornerSize(), positionY * v.getCornerSize(), v.getCornerSize(), v.getCornerSize());
            barriers.addBarrier(positionX, positionY);

            positionY--;
        }

        positionX = 24;
        positionY = 8;

        while (positionY >= 0)
        {
            barriers.paintBarrier(gc, paint, "circle", positionX * v.getCornerSize(), positionY * v.getCornerSize(), v.getCornerSize(), v.getCornerSize());
            barriers.addBarrier(positionX, positionY);

            positionY--;
        }

        positionX = 0;
        positionY = 17;

        for (int i = 0; i < 9; i++)
        {
            barriers.paintBarrier(gc, paint, "rectangle", positionX * v.getCornerSize(), positionY * v.getCornerSize(), v.getCornerSize(), v.getCornerSize());
            barriers.addBarrier(positionX, positionY);

            positionX++;
        }

        while (positionY <= 23)
        {
            barriers.paintBarrier(gc, paint, "rectangle", positionX * v.getCornerSize(), positionY * v.getCornerSize(), v.getCornerSize(), v.getCornerSize());
            barriers.addBarrier(positionX, positionY);

            positionY++;
        }

        positionX = 15;
        positionY = 17;

        while (positionX <= 24)
        {
            barriers.paintBarrier(gc, paint, "rectangle", positionX * v.getCornerSize(), positionY * v.getCornerSize(), v.getCornerSize(), v.getCornerSize());
            barriers.addBarrier(positionX, positionY);

            positionX++;
        }

        positionX = 15;
        positionY = 17;

        while (positionY <= 23)
        {
            barriers.paintBarrier(gc, paint, "rectangle", positionX * v.getCornerSize(), positionY * v.getCornerSize(), v.getCornerSize(), v.getCornerSize());
            barriers.addBarrier(positionX, positionY);

            positionY++;
        }
    }

    public void buildBarriersLevelFour(Variables v, Barriers barriers, GraphicsContext gc, Paint paint)
    {
        int positionX = 0;
        int positionY = 12;

        for (int i = 0; i < 8; i++)
        {
            barriers.paintBarrier(gc, paint, "rectangle", positionX * v.getCornerSize(), positionY * v.getCornerSize(), v.getCornerSize()-1, v.getCornerSize()-1);
            barriers.addBarrier(positionX, positionY);

            positionX++;
        }

        positionX = 17;

        while (positionX <= 24)
        {
            barriers.paintBarrier(gc, paint, "rectangle", positionX * v.getCornerSize(), positionY * v.getCornerSize(), v.getCornerSize()-1, v.getCornerSize()-1);
            barriers.addBarrier(positionX, positionY);

            positionX++;
        }

        positionX = 7;
        positionY = 11;

        for (int i = 0; i < 8; i++)
        {
            barriers.paintBarrier(gc, paint, "circle", positionX * v.getCornerSize(), positionY * v.getCornerSize(), v.getCornerSize(), v.getCornerSize());
            barriers.addBarrier(positionX, positionY);

            positionY--;
        }

        for (int i = 0; i < 4; i++)
        {
            barriers.paintBarrier(gc, paint, "circle", positionX * v.getCornerSize(), positionY * v.getCornerSize(), v.getCornerSize(), v.getCornerSize());
            barriers.addBarrier(positionX, positionY);

            positionX++;
        }

        positionX = 17;
        positionY = 11;

        for (int i = 0; i < 8; i++)
        {
            barriers.paintBarrier(gc, paint, "circle", positionX * v.getCornerSize(), positionY * v.getCornerSize(), v.getCornerSize(), v.getCornerSize());
            barriers.addBarrier(positionX, positionY);

            positionY--;
        }

        for (int i = 0; i < 4; i++)
        {
            barriers.paintBarrier(gc, paint, "circle", positionX * v.getCornerSize(), positionY * v.getCornerSize(), v.getCornerSize(), v.getCornerSize());
            barriers.addBarrier(positionX, positionY);

            positionX--;
        }

        positionX = 6;
        positionY = 18;

        for (int i = 0; i < 13; i++)
        {
            barriers.paintBarrier(gc, paint, "rectangle", positionX * v.getCornerSize(), positionY * v.getCornerSize(), v.getCornerSize()-1, v.getCornerSize()-1);
            barriers.addBarrier(positionX, positionY);

            positionX++;
        }

        positionX = 8;
        positionY = 21;

        for (int i = 0; i < 9; i++)
        {
            barriers.paintBarrier(gc, paint, "rectangle", positionX * v.getCornerSize(), positionY * v.getCornerSize(), v.getCornerSize()-1, v.getCornerSize()-1);
            barriers.addBarrier(positionX, positionY);

            positionX++;
        }
    }
}
