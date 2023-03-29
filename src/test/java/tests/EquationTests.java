package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.sandbox.Equation;

public class EquationTests {
  @Test (enabled = false)
  public void test0() {
    Equation e = new Equation(1, 1, 1);
    Assert.assertEquals(e.rootNumber(), 0);
  }


  @Test (enabled = false)
  public void test1() {
    Equation e = new Equation(1, 2, 1);
    Assert.assertEquals(e.rootNumber(), 1);
  }

  @Test (enabled = false)
  public void test2() {
    Equation e = new Equation(1, 5, 5);
    Assert.assertEquals(e.rootNumber(),2);
  }


}