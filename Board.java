public class Board extends Matrix{

  private Point spawn = new Point(getWidth()/2, getHeight()-1);
  private Block currPiece;

  private Block[][] board = new Block[getWidth()][getHeight()];

  private int linesFull = 0;


  public Board(){
    super(10,20);
    Block[][] newBoard = new Block[getWidth()][getHeight()];
    for (int i = 0; i < getWidth(); i++) {

        Block[] blocks = new Block[getHeight()];

        for(int n = 0; n < getHeight(); n++){
          blocks[n] = new Block();
        }

        newBoard[i] = blocks;
    }

    board = newBoard;

  }

  public Block[][] getBoard(){
    return board;
  }

  public int getLinesFull(){
    return linesFull;
  }

  public Block getBoardPosition(int x, int y){
    return board[x][y];
  }

  public boolean isFilled(int x, int y){
    return board[x][y] != null;
  }

  public boolean checkFull(int l) {
    for(int i = 0; i < getWidth(); i++) {
        if (getBoardPosition(i, l).getType() == null) {
            return false;
        }
    }
    return true;
  }


  private void saveRow(Block[][] b, int n, int in) {
       for (int i = 0; i < getWidth(); i++) {
           b[i][in] = board[i][n];
       }
   }


   public void rotate() {
        Block rot = currPiece.rotate();
        if (fitPiece(rot.getPoints(), 0, 0)) {

            currPiece = rot;
        }
    }

  public void clearRows() {

    Block[][] newBoard = new Block[getWidth()][getHeight()];
    for (int i = 0; i < getWidth(); i++) {

        Block[] blocks = new Block[getHeight()];

        for(int n = 0; n < getHeight(); n++){
          blocks[n] = new Block();
        }

        newBoard[i] = blocks;
    }

     Block[][] baseBoard = newBoard;

     int full = 0;
     for (int i = 0; i < getHeight(); i++) {
         if (checkFull(i)) {
             full++;
         } else {
             saveRow(baseBoard, i, i-full);
         }
     }

     linesFull += full;
     board = baseBoard;
 }


   public boolean fitPiece(Point[] points, int moveX, int moveY) {
       for (Point p : points) {
           int x = spawn.getX() + p.getX() + moveX;
           int y = spawn.getY() + p.getY() + moveY;

           if (x < 0 || y >= getHeight() || x >= getWidth() || y < 0) {
               return false;
           }

           if (board[x][y].getType() != null) {
               return false;
           }
       }

       return true;
   }

    public void moveLeft() {
        if (fitPiece(currPiece.getPoints(), -1, 0)) {
            move( -1, 0);
        }
    }

    public void moveRight() {
        if (fitPiece(currPiece.getPoints(), 1, 0)) {
            move(1, 0);
        }
    }

    public boolean canMoveDown() {
        return fitPiece(currPiece.getPoints(), 0, -1);
    }

    public void moveDown() {
        if (canMoveDown()) {
            move(0, -1);
            clearRows();
        }
    }

    public void move(int mx, int my) {
        spawn = new Point(spawn.getX() + mx, spawn.getY() + my);
    }

    public void setCurrentBlock(Block piece) {
        if (currPiece != null) {
            addPiece();
        }
        currPiece = piece;
        resetSpawn();
    }

    public Block getCurrentPiece() {
        return currPiece;
    }


    public void resetSpawn() {
        spawn.setX(getWidth()/2);
        spawn.setY(getHeight()-1);
    }

    public Block[][] getBoardWithPiece() {
        Block[][] b = new Block[getWidth()][getHeight()];

        for (int i = 0; i < getWidth(); i++) {
            System.arraycopy(board[i], 0, b[i], 0, board[0].length);
        }

        for (Point point : currPiece.getPoints()) {
            int x = point.getX() + spawn.getX();
            int y = point.getY() + spawn.getY();
            b[x][y] = new Block(currPiece.getType(), true);
        }

        return b;
    }

    public void addPiece() {
        for (Point point : currPiece.getPoints()) {
            int x = spawn.getX() + point.getX();
            int y = spawn.getY() + point.getY();
            board[x][y] = new Block(currPiece.getType(), true);
        }
    }



}
