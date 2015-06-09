package coffeeimport;

import org.junit.Test;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CoffeeImportApplication.class)
@WebAppConfiguration
public class CoffeeImportApplicationTests {

	@Test
	public void contextLoads() {
		Assert.assertEquals(true, true);
	}

}
