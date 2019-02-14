public class Spaceship extends Sprite2D{

    private boolean goingRight=false;
    private boolean goingLeft=false;
    private boolean shooting=false;

    Spaceship(){
        super("images/spaceship.png");

        xVector=0.9;
        yVector=0;

        x=gameWidth/2 - width/2;
        y=gameHeight-height-5;
    }

    public void move(){
        if(goingRight) {
            if ((x + width + xVector) > gameWidth)
                x = gameWidth - width;
            else
                x += xVector;
        }
        if(goingLeft){
            if( (x-xVector)<0)
                x=0;
            else
                x-=xVector;
        }
    }

    public void setGoingRight(boolean goingRight) {
        this.goingRight = goingRight;
    }

    public void setGoingLeft(boolean goingLeft) {
        this.goingLeft = goingLeft;
    }

    public boolean isShooting() {
        return shooting;
    }

    public void setShooting(boolean shooting) {
        this.shooting = shooting;
    }
}
