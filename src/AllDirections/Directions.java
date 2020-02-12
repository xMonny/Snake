package AllDirections;

public class Directions
{
    public enum Direction
    {
        left,
        right,
        up,
        down,
        reset,
        pause,
        enter
    }

    //avoid pressing A while the snake is going on right for example
    boolean checkHorizontalDirection = true;
    boolean checkVerticalDirection = false;

    Direction direction = Direction.right;

    public void setDirection(Direction newDirection)
    {
        this.direction = newDirection;
    }
    public Direction getDirection()
    {
        return this.direction;
    }

    public boolean isLeft()
    {
        return this.direction==Direction.left;
    }
    public boolean isRight()
    {
        return this.direction==Direction.right;
    }
    public boolean isUp()
    {
        return this.direction==Direction.up;
    }
    public boolean isDown()
    {
        return this.direction==Direction.down;
    }
    public boolean isReset()
    {
        return this.direction==Direction.reset;
    }
    public boolean isPause()
    {
        return this.direction==Direction.pause;
    }
    public boolean isEnter()
    {
        return this.direction==Direction.enter;
    }

    public void setHorizontalDirection(boolean newHorizontalDirection)
    {
        this.checkHorizontalDirection = newHorizontalDirection;
    }
    public boolean isHorizontalDirection()
    {
        return this.checkHorizontalDirection;
    }

    public void setVerticalDirection(boolean newVerticalDirection)
    {
        this.checkVerticalDirection = newVerticalDirection;
    }
    public boolean isVerticalDirection()
    {
        return this.checkVerticalDirection;
    }
}
