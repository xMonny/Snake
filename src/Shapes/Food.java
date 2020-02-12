package Shapes;

import AllSnake.Snake;
import AllVariables.Variables;
import Design.Barriers;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

import java.util.Random;

public class Food
{
    Random rand = new Random();

    int foodX;
    int foodY;

    public void setFoodX(int newFoodX)
    {
        this.foodX = newFoodX;
    }
    public int getFoodX()
    {
        return this.foodX;
    }

    public void setFoodY(int newFoodY)
    {
        this.foodY = newFoodY;
    }
    public int getFoodY()
    {
        return this.foodY;
    }

    public void setFood(Variables v, Snake snake, Magic magic, Barriers barrier)
    {
        if (v.getHelpRecord() != v.getRecordForNextLevel()-1 || v.getLevel() == v.getFinalLevel())
        {
            setFoodX(rand.nextInt(v.getWidth()));
            setFoodY(rand.nextInt(v.getHeight()-3));
        }
        else
        {
            setFoodX(-1);
            setFoodY(-1);
        }

        if (!snake.checkFoodBounds(getFoodX(), getFoodY()) && !barrier.checkFoodBounds(getFoodX(), getFoodY()))
        {
            v.setRecord(v.getRecord()+1);
            v.setHelpRecord(v.getHelpRecord()+1);
            if (v.getHelpRecord() == v.getRecordForNextLevel() && v.getLevel() != v.getFinalLevel()) //go to next level (exception for final level)
            {
                v.setGoNext(true);
            }

            if (v.getHelpRecord()%5 == 0 && v.getHelpRecord() > 10)
            {
                if (v.getSpeed() <= 13)
                {
                    v.setSpeed(v.getSpeed() + 1);
                }
            }

            if (v.getHelpRecord()%20 == 0 && v.getHelpRecord() != 0 && !magic.getCheckMagic() && v.getLevel() == 5)
            {
                magic.setMagic(v, snake, barrier);
                magic.setCheckMagic(true); //when is true, the blue box will be painted on every step and the snake can eat it
            }
            if (v.getHelpRecord()%13 == 0 && v.getHelpRecord() != 0 && !magic.getCheckMagic() && v.getLevel() != 5)
            {
                magic.setMagic(v, snake, barrier);
                magic.setCheckMagic(true); //when is true, the blue box will be painted on every step and the snake can eat it
            }
        }
        else
        {
            setFood(v, snake, magic, barrier);
        }
    }

    public void paintFood(Variables v, int width, int height, GraphicsContext gc, Paint paint)
    {
        gc.setFill(paint);
        gc.fillOval(getFoodX()*v.getCornerSize(), getFoodY()*v.getCornerSize(), width, height);
    }
}