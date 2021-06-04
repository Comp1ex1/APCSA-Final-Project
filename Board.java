public class Board {

  private int width = 10;
  private int height = 20;

  private Point spawn = new Point(width/2, height-1);
  private Block currPiece;

  private Block[][] board = new Block[width][height];

  private int linesFull = 0;


  public Board(){
    Block[][] newBoard = new Block[width][height];
    for (int i = 0; i < width; i++) {

        Block[] blocks = new Block[height];

        for(int n = 0; n < height; n++){
          blocks[n] = new Block();
        }

        newBoard[i] = blocks;
    }

    board = newBoard;

  }

  public Block[][] getBoard(){
    return board;
  }

  public int getWidth(){
    return width;
  }

  public int getHeight(){
    return height;
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
    for(int i = 0; i < width; i++) {
        if (getBoardPosition(i, l).getType() == null) {
            return false;
        }
    }
    return true;
  }


  private void saveRow(Block[][] b, int n, int in) {
       for (int i = 0; i < width; i++) {
           b[i][in] = board[i][n];
       }
   }


  public void clearRows() {

    Block[][] newBoard = new Block[width][height];
    for (int i = 0; i < width; i++) {

        Block[] blocks = new Block[height];

        for(int n = 0; n < height; n++){
          blocks[n] = new Block();
        }

        newBoard[i] = blocks;
    }

     Block[][] baseBoard = newBoard;

     int full = 0;
     for (int i = 0; i < height; i++) {
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

    public void setCurrentPiece(Block piece) {
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
        spawn.setX(width/2);
        spawn.setY(height-1);
    }

    public Block[][] getBoardWithPiece() {
        Block[][] b = new Block[width][height];

        for (int i = 0; i < width; i++) {
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
