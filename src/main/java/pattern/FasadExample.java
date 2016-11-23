package pattern;


public class FasadExample {
    public static void main(String[] args) {
        ShapeMaker shapeMaker = new ShapeMaker();

        shapeMaker.drawCircle();
        shapeMaker.drawRectangle();
        shapeMaker.drawSquare();
    }
}

interface ShapeMy {    void draw();  }

class Rectangle implements ShapeMy {
    @Override
    public void draw() {  System.out.println("Rectangle::draw()");  }
}

class Square implements ShapeMy {
    @Override
    public void draw() {  System.out.println("Square::draw()");     }
}

class CircleMy implements ShapeMy {
    @Override
    public void draw() {  System.out.println("Circle::draw()");     }
}

class ShapeMaker {
    private ShapeMy circle;
    private ShapeMy rectangle;
    private ShapeMy square;

    public ShapeMaker() {
        circle = new CircleMy();
        rectangle = new Rectangle();
        square = new Square();
    }
    public void drawCircle()    {       circle.draw();      }
    public void drawRectangle() {       rectangle.draw();   }
    public void drawSquare()    {       square.draw();      }
}