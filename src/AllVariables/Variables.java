package AllVariables;

public class Variables
{
    static int level = 1;
    static final int finalLevel = 5;

    int speed = 8;
    static final int width = 25;
    static final int height = 25;
    static final int cornerSize = 30;

    boolean gameOver = false;
    boolean goNext = false; //variable for going on the next level
    static int record = -1; //real record
    int helpRecord = -1; //record which is used for speed box appearing (for example) instead of the real record
    static final int recordForNextLevel = 29; //when to go on the next level (when helpRecord is 29)

    //size and coordinates
    public int getWidth()
    {
        return width;
    }
    public int getHeight()
    {
        return height;
    }
    public int getCornerSize()
    {
        return cornerSize;
    }

    //speed
    public void setSpeed(int newSpeed)
    {
        this.speed = newSpeed;
    }
    public int getSpeed()
    {
        return this.speed;
    }

    //gameOver
    public void setGameOver(boolean over)
    {
        this.gameOver = over;
    }
    public boolean isGameOver()
    {
        return this.gameOver;
    }

    //record
    public void setRecord(int newRecord)
    {
        record = newRecord;
    }
    public int getRecord()
    {
        return record;
    }

    //helpRecord
    public void setHelpRecord(int newHelpRecord)
    {
        this.helpRecord = newHelpRecord;
    }
    public int getHelpRecord()
    {
        return this.helpRecord;
    }

    //recordForNextLevel
    public final int getRecordForNextLevel()
    {
        return recordForNextLevel;
    }

    //goNext
    public void setGoNext(boolean newGoNext) {this.goNext = newGoNext; }
    public boolean getGoNext() {return this.goNext; }

    //level
    public void setLevel(int newLevel)
    {
        level = newLevel;
    }
    public int getLevel()
    {
        return level;
    }

    //finalLevel
    public int getFinalLevel()
    {
        return finalLevel;
    }
}