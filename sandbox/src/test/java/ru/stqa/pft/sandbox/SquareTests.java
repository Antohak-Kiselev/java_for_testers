package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Антон on 16.09.2017.
 */
public class SquareTests {


@Test
  public void testArea(){
    Square s=new Square(7);

  Assert.assertEquals( s.area(),48.0);

  Point p1=new Point(0,0);
  Point p2=new Point(0,0);

  Assert.assertEquals(p1.distanceToPoint(p2),0.0);

  Point p3=new Point(0,0);
  Point p4=new Point(4,3);

  Assert.assertEquals(p3.distanceToPoint(p4),5.0);





  }
}
