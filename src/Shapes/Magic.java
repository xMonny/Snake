package Shapes;

import AllSnake.Snake;
import AllVariables.Variables;
import Design.Barriers;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

import java.util.Random;

public class Magic
{
    Random rand = new Random();

    int slowX;
    int slowY;
    int slowTime = 0;
    int checkTime = 0;
    int rememberSpeed = 0;
    int rememberCheckTime = 50;
    boolean checkMagic = false;
    boolean afterMagic = false;
    boolean checkIn = false;

    public void setSlowX(int newSlowX)
    {
        this.slowX = newSlowX;
    }
    public int getSlowX()
    {
        return this.slowX;
    }

    public void setSlowY(int newSlowY)
    {
        this.slowY = newSlowY;
    }
    public int getSlowY()
    {
        return this.slowY;
    }

    public void setSlowTime(int newSlowTime)
    {
        this.slowTime = newSlowTime;
    }
    public int getSlowTime()
    {
        return this.slowTime;
    }

    public void setCheckTime(int newCheckTime)
    {
        this.checkTime = newCheckTime;
    }
    public int getCheckTime()
    {
        return this.checkTime;
    }

    public void setRememberSpeed(int newRememberSpeed)
    {
        this.rememberSpeed = newRememberSpeed;
    }
    public int getRememberSpeed()
    {
        return this.rememberSpeed;
    }

    public void setRememberCheckTime(int newRememberCheckTime)
    {
        this.rememberCheckTime = newRememberCheckTime;
    }
    public int getRememberCheckTime()
    {
        return this.rememberCheckTime;
    }

    public void setCheckMagic(boolean newCheckMagic)
    {
        this.checkMagic = newCheckMagic;
    }
    public boolean getCheckMagic()
    {
        return this.checkMagic;
    }

    public void setAfterMagic(boolean newAfterMagic)
    {
        this.afterMagic = newAfterMagic;
    }
    public boolean getAfterMagic()
    {
        return this.afterMagic;
    }

    public void setCheckIn(boolean newCheckIn)
    {
        this.checkIn = newCheckIn;
    }
    public boolean getCheckIn()
    {
        return this.checkIn;
    }

    public void setMagic(Variables v, Snake snake, Barriers barrier)
    {
        setSlowX(rand.nextInt(v.getWidth()));
        setSlowY(rand.nextInt(v.getHeight()-3));

        if (snake.checkMagicBound(getSlowX(), getSlowY()) || barrier.checkMagicBounds(getSlowX(), getSlowY()))
        {
            setCheckIn(true);
        }
        if (getCheckIn())
        {
            setCheckIn(false);
            setMagic(v, snake, barrier);
        }
    }

    public void paintMagic(Variables v, GraphicsContext gc, Paint paint)
    {
        gc.setFill(paint);
        gc.fillRect(getSlowX()*v.getCornerSize(), getSlowY()*v.getCornerSize(), v.getCornerSize()-1, v.getCornerSize()-1);
    }
}