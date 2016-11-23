package pattern;

import java.util.*;

public class FlyWeightExample {
    public static void main(String[] args) {

        FactoryShape factoryShape = new FactoryShape();
        List<ShapeP> shapes = new ArrayList<>();

        shapes.add(factoryShape.getShape("квадрат"));
        shapes.add(factoryShape.getShape("круг"));
        shapes.add(factoryShape.getShape("круг"));
        shapes.add(factoryShape.getShape("круг"));
        shapes.add(factoryShape.getShape("точка"));
        shapes.add(factoryShape.getShape("круг"));

        Random r = new Random();

        for (ShapeP shape: shapes) {
            int x = r.nextInt(100);
            int y = r.nextInt(100);
            shape.draw(x,y);
        }
        System.out.println("______________________________");
        for (ShapeP shape: shapes) {
            int x = r.nextInt(100);
            int y = r.nextInt(100);
            shape.draw(x,y);
        }


    }
}


//-----------Реализация Приспособленцев ------------------------------------
interface ShapeP {
    void draw(int x, int y);
}
class Point implements ShapeP {
    public  void draw(int x, int y) {
        System.out.println(x+"  "+y+"  "+"  Point");
    }
}
class CircleP implements ShapeP {
    private int r=5;
    public  void draw(int x, int y) {
        System.out.println(x+"  "+y+"  "+"  CircleP" +" r="+r);
    }
}
class SquareP implements ShapeP {
    private int a=10;
    public  void draw(int x, int y) {
        System.out.println(x+"  "+y+"  "+"  SquareP" +" a="+a);
    }
}
//--------------------------------------------------------------------------
//-----------Реализация Фабрики для создания приспособленцев ---------------
class FactoryShape {
    private static final Map<String,ShapeP> shapes = new HashMap<>();
    public ShapeP getShape (String name) {
        ShapeP shape = shapes.get(name);
        if(shape==null) {
            switch (name) {
                case "круг"     : shape = new CircleP(); break;
                case "квадрат"  : shape = new SquareP(); break;
                case "точка"    : shape = new Point();   break;
            }
            shapes.put(name,shape);
        }
        return shape;
    }
}
//--------------------------------------------------------------------------
