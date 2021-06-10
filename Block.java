public class Block {

    private PieceType type;
    private Point[] points;
    private boolean initOrientation;


    public Block() {
      type = null;
    }

    public Block(PieceType type, boolean orientation) {
        this.type = type;
        points = type.getPoints();
        initOrientation = orientation;
    }

    public static Block getRandomBlock() {
        PieceType randType = PieceType.getRandomPiece();
        Block randBlock = new Block(randType, true);
        return randBlock;
    }

    public static Block getBlock(PieceType type) {
        return new Block(type, true);
    }

    public PieceType getType() {
        return type;
    }

    public Point[] getPoints() {
        return points;
    }

    public boolean getOrientation() {
        return initOrientation;
    }

    public void setOrientation(boolean result) {
        initOrientation = result;
    }

    public void setPoints(Point[] newPoints) {
        points = newPoints;
    }

    public Block rotate() {
        // square
        if (type.getMaxOrientations() == 0) {
            return this;
        }

        // 2 orientations
        else if (type.getMaxOrientations() == 2) {
            if (initOrientation) {
                // if it's at initial orientation, change it to 2nd orientation
                rotatePoints(points, "right");
                setOrientation(false);
                return this;
            }
            else {
                // if it's 2nd orientation, change it back to initial orientation
                rotatePoints(points, "left");
                setOrientation(true);
                return this;
            }
        }

        // 4 orientations
        else {
            rotatePoints(points, "right");
            return this;
        }

    }

    public void rotatePoints(Point[] points, String direction) {
        int x, y;
        if (direction.equals("left")) {
            x = 1;
            y = -1;
        }

        else {
            x = -1;
            y = 1;
        }

        for (int i = 0; i < 4; i++) {
            int temp = points[i].getX();
            points[i].setX(x*(points[i].getY()));
            points[i].setY(y*temp);
        }
    }

    // returns the array of Points as a string for debugging purposes
    public String toString() {
        String result = "";
        for (Point p : getPoints()) {
            result = result + "x: " + p.getX() + ", y: " + p.getY() + ";\t";
        }
        return result;
    }



}
