public class Matrix {		
  private int width;
  private int height;


  public Matrix(){
    width = 5;
    height = 10;
  }

  public Matrix(int w, int h)
  {
    width = w;
    height = h;
  }

  public void setWidth(int w){
    width = w;
  }

  public void setHeight(int h){
    height = h;
  }

  public int getWidth(){
    return width;
  }

  public int getHeight()
  {
    return height;
  }

}