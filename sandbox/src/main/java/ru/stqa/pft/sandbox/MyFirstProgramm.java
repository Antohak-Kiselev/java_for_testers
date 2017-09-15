package ru.stqa.pft.sandbox;

public class MyFirstProgramm {

  public static void main(String[] args) {

    Square s = new Square(345);

    System.out.println("Площадь квадрата со стороной= " + s.l + " равна " + s.area());


    Rectangle r=new Rectangle(234,434);

    System.out.println("Площадь прямоугольника со сторонами  " + r.a + " и " + r.b + " равна " + r.area());

    Point p1=new Point(0,0);
    Point p2=new Point(2,2);
    System.out.println("Distance from p1 to p2 equals  " +distanse(p1,p2));


  }


  public static double distanse(Point p1, Point p2){

    return  Math.sqrt( (p2.a-p1.a)*(p2.a-p1.a) +  (p2.b-p1.b)*(p2.b-p1.b));

  }

}