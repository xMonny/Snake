package Design;

import AllSnake.Snake;
import AllSnake.Square;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

import java.util.ArrayList;
import java.util.List;

public class Barriers
{
    List<Square> barrier = new ArrayList<>();

    public void addBarrier(int x, int y)
    {
        barrier.add(new Square(x, y));
    }

    public boolean checkSnakeBounds(Snake snake)
    {
        for (Square b: barrier)
        {
            if (snake.getSnakeX(0) == b.getX() && snake.getSnakeY(0) == b.getY())
            {
                return true;
            }
        }
        return false;
    }

    public boolean checkFoodBounds(int x, int y)
    {
        for (Square b: barrier)
        {
            if (x == b.getX() && y == b.getY())
            {
                return true;
            }
        }
        return false;
    }

    public boolean checkMagicBounds(int x, int y)
    {
        for (Square b: barrier)
        {
            if (x == b.getX() && y == b.getY())
            {
                return true;
            }
        }
        return false;
    }

    public void paintBarrier(GraphicsContext gc, Paint paint, String shape, int x, int y, int width, int height)
    {
        if (shape == "rectangle")
        {
            gc.setFill(paint);
            gc.fillRect(x, y, width, height);
        }
        if (shape == "circle")
        {
            gc.setFill(paint);
            gc.fillOval(x, y, width, height);
        }
    }
}