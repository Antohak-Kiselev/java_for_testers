package ru.stqa.pft.sandbox;

public class MyFirstProgramm {

  public static void main(String[] args) {

    Square s = new Square(345);

    System.out.println("Площадь квадрата со стороной= " + s.l + " равна " + s.area());


    Rectangle r=new Rectangle(234,434);

    System.out.println("Площадь прямоугольника со сторонами  " + r.a + " и " + r.b + " равна " + r.area());
  }


}