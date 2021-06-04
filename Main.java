
public class Main {
    public static void main(String[] args) {

        /*
        PieceType pieceTest = new PieceType("L");
        PieceType pieceRandom = PieceType.getRandomPiece();

        System.out.println(pieceRandom.getPieceType());
        System.out.println(pieceRandom.getMaxOrientations());

        for(Point p : pieceRandom.getPoints()) {
            System.out.println("x: " + p.getX() + "; y: " + p.getY());
        }

        Block blockTest = new Block(pieceTest, true);
        Block blockRandom = Block.getRandomBlock();

        System.out.println("initial points: " + blockTest.getPointsStr());
        blockTest.rotate();
        System.out.println("rotated points: " + blockTest.getPointsStr());
        */
        
        Tetris.main(args);
    }

}