package com.udacity.pricing;

import com.udacity.pricing.service.PriceException;
import com.udacity.pricing.service.PricingService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PricingServiceApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Test
	public void testGetPrice() throws PriceException {
		// Test Price Service returns price of a given Vehicle
		Assert.assertNotNull(PricingService.getPrice(Long.valueOf(1)));
	}

}
