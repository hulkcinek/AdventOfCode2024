public class Robot {
    int x;
    int y;

    int velX;
    int velY;

    int mapHeight = 103;
    int mapWidth = 101;

    public Robot(int x, int y, int velX, int velY) {
        this.x = x;
        this.y = y;
        this.velX = velX;
        this.velY = velY;
    }

    public Quadrant getQuadrant(){
        if (x < mapWidth/2){
            if (y < mapHeight/2){
                return Quadrant.LEFTUP;
            }else if(y > mapHeight/2){
                return Quadrant.LEFTDOWN;
            }
        }else if(x > mapWidth/2){
            if (y < mapHeight/2){
                return Quadrant.RIGHTUP;
            }else if(y > mapHeight/2){
                return Quadrant.RIGHTDOWN;
            }
        }
        return Quadrant.MIDDLE;
    }

    enum Quadrant{
        LEFTUP,
        LEFTDOWN,
        RIGHTUP,
        RIGHTDOWN,
        MIDDLE
    }

    public void move(){
        moveX();
        moveY();
    }

    private void moveY() {
        int direction = velY/Math.abs(velY);
        for (int i = 0; i < Math.abs(velY); i++) {
            if (isValid(x, y+direction)) {
                y += direction;
            }else {
                if (direction == -1){
                    y = mapHeight-1;
                }else {
                    y = 0;
                }
            }
        }
    }

    private void moveX() {
        int direction = velX/Math.abs(velX);
        for (int i = 0; i < Math.abs(velX); i++) {
            if (isValid(x+direction, y)) {
                x += direction;
            }else {
                if (direction == -1){
                    x = mapWidth-1;
                }else {
                    x = 0;
                }
            }
        }
    }

    private boolean isValid(int nextX, int nextY){
        return (0 <= nextY && 0 <= nextX && mapHeight > nextY && mapWidth > nextX);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
