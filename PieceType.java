import java.util.Random;

public class PieceType extends Object {

    private static final Random random = new Random();
    private final int maxOrientations;
    private final Point[] points;
    private final String pieceType;

    public PieceType() {
        points = new Point[4];
        this.maxOrientations = -1;
        this.pieceType = "";
    }

    public PieceType(String pieceType) {
        this.pieceType = pieceType;
        if(pieceType.equals("O")) {
            points = new Point[4];
            points[0] = new Point(-1,0);
            points[1] = new Point(0,0);
            points[2] = new Point(-1,-1);
            points[3] = new Point(0,-1);
            this.maxOrientations = 0;
        }
        else if(pieceType.equals("I")) {
            points = new Point[4];
            points[0] = new Point(-2, 0);
            points[1] = new Point(-1, 0);
            points[2] = new Point(0,0);
            points[3] = new Point(1,0);
            this.maxOrientations = 2;
        }
        else if(pieceType.equals("S")) {
            points = new Point[4];
            points[0] = new Point(0, 0);
            points[1] = new Point(1, 0);
            points[2] = new Point(-1, -1);
            points[3] = new Point(0, -1);
            this.maxOrientations = 2;
        }
        else if(pieceType.equals("Z")) {
            points = new Point[4];
            points[0] = new Point(-1, 0);
            points[1] = new Point(0, 0);
            points[2] = new Point(0, -1);
            points[3] = new Point(1, -1);
            this.maxOrientations = 2;
        }
        else if(pieceType.equals("L")) {
            points = new Point[4];
            points[0] = new Point(-1, 0);
            points[1] = new Point(0, 0);
            points[2] = new Point(1, 0);
            points[3] = new Point(-1, -1);
            this.maxOrientations = 4;
        }
        else if(pieceType.equals("J")) {
            points = new Point[4];
            points[0] = new Point(-1, 0);
            points[1] = new Point(0, 0);
            points[2] = new Point(1, 0);
            points[3] = new Point(1, -1);
            this.maxOrientations = 4;
        }
        else if(pieceType.equals("T")) {
            points = new Point[4];
            points[0] = new Point(-1, 0);
            points[1] = new Point(0, 0);
            points[2] = new Point(1, 0);
            points[3] = new Point(0, -1);
            this.maxOrientations = 4;
        }
        else {
            points = new Point[4];
            this.maxOrientations = -1;
        }
    }

    public static PieceType getRandomPiece() {
        PieceType randomPiece = new PieceType();
        int randomNum = (int) (random.nextInt(6));
        if(randomNum == 0) {
            randomPiece = new PieceType("O");
        }
        else if(randomNum == 1) {
            randomPiece = new PieceType("I");
        }
        else if(randomNum == 2) {
            randomPiece = new PieceType("S");
        }
        else if(randomNum == 3) {
            randomPiece = new PieceType("Z");
        }
        else if(randomNum == 4) {
            randomPiece = new PieceType("L");
        }
        else if(randomNum == 5) {
            randomPiece = new PieceType("J");
        }
        else if(randomNum == 6) {
            randomPiece = new PieceType("T");
        }
        return randomPiece;
    }

    public Point[] getPoints() {
        return points;
    }

    public int getMaxOrientations() {
        return maxOrientations;
    }

    public String getPieceType() {
        return pieceType;
    }
    
    @Override
    public String toString() {
        System.out.println(“Piece Type: ” + pieceType + “Max Orientations: ” + maxOrientations);
    }
}

