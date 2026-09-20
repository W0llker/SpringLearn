import org.example.test.Calculation;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestCalculation {
    private Calculation calculation;
    private int a;
    private int b;

    private final int maxRange = 10;
    private final int minRange = 5;

    @Before
    public void setup() {
        calculation = new Calculation();
        a = (int) (Math.random() * maxRange);
        b = (int) (Math.random() * minRange);
    }

    @Test
    public void TestSum() {
        int waitValue = a + b;
        int result = calculation.sum(a, b);
        Assert.assertEquals(waitValue, result);
    }

}
