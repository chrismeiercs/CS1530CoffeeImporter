package coffeeimport;/**
 * Created by Chris on 6/21/2015.
 */

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.validation.constraints.AssertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CoffeeImportApplication.class)
@WebAppConfiguration
public class WeightCalculatorTest {

    WeightCalculator weightCalculator;

    @Before
    public void setUp() throws Exception{
        weightCalculator = new WeightCalculator();
    }

    @Test
    public void calculatePricePerUnitTest(){
        double cost = weightCalculator.calcPricePerUnit(5, 5);
        Assert.assertEquals(1,cost,0);
    }

}