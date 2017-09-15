package ru.stqa.pft.sandbox;

/**
 * Created by Антон on 15.09.2017.
 */
public class Point {
  public double a;
  public double b;

  public Point(double a, double b){
    this.a=a;
    this.b=b;
  }

  public double  distanceToPoint(Point p2){

    return Math.sqrt( (p2.a-this.a)*(p2.a-this.a) +  (p2.b-this.b)*(p2.b-this.b));
  }
}

