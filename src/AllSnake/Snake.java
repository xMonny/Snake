package AllSnake;

import java.util.ArrayList;
import java.util.List;
import AllVariables.Variables;
import Design.Barriers;
import Shapes.Food;
import Shapes.Magic;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Snake
{
    List<Square> snake = new ArrayList<>();

    public void createSnake(int number, int x, int y)
    {
        for (int i = 0; i < number; i++)
        {
            snake.add(new Square(x, y));
        }
    }

    public void setSnakeX(int position, int newX)
    {
        snake.get(position).setX(newX);
    }
    public int getSnakeX(int position)
    {
        return snake.get(position).getX();
    }

    public void setSnakeY(int position, int newY)
    {
        snake.get(position).setY(newY);
    }
    public int getSnakeY(int position)
    {
        return snake.get(position).getY();
    }

    public int getSize()
    {
        return snake.size();
    }

    public void addNew(Square square)
    {
        snake.add(square);
    }

    public boolean checkFoodBounds(int x, int y)
    {
        for (Square s: snake)
        {
            if (s.getX() == x && s.getY() == y)
            {
                return true;
            }
        }
        return false;
    }

    public boolean checkMagicBound(int x, int y)
    {
        for (Square s: snake)
        {
            if (s.getX() == x && s.getY() == y)
            {
                return true;
            }
        }
        return false;
    }

    public void moveSnake()
    {
        for (int i = getSize()-1; i >= 1; i--)
        {
            setSnakeX(i, getSnakeX(i-1));
            setSnakeY(i, getSnakeY(i-1));
        }
    }

    public void eatFood(Variables v, Magic magic, Barriers barrier, Food food)
    {
        if (food.getFoodX() == getSnakeX(0) && food.getFoodY() == getSnakeY(0))
        {
            snake.add(new Square(100, 100)); //out of the form (if x = width/2 and y = height/2 it will apprear in the center)
            food.setFood(v, this, magic, barrier);
        }
    }

    public void selfDestroy(Variables v)
    {
        for (int i = 1; i < getSize(); i++)
        {
            if (getSnakeX(0) == getSnakeX(i) && getSnakeY(0) == getSnakeY(i))
            {
                v.setGameOver(true);
            }
        }
    }

    public void clearSnake()
    {
        snake.clear();
    }

    public void paintSnake(Variables v, GraphicsContext gc)
    {
        boolean first = false;
        for (Square s: snake)
        {
            if (!first)
            {
                first = true;
                gc.setFill(Color.DARKOLIVEGREEN);
            }
            else
            {
                gc.setFill(Color.GREEN);
            }
            gc.fillRect(s.getX()*v.getCornerSize(), s.getY()*v.getCornerSize(), v.getCornerSize()-1, v.getCornerSize()-1);
        }
    }
}
