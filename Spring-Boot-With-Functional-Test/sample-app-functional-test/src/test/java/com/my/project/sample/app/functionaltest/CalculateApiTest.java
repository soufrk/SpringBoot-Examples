package com.my.project.sample.app.functionaltest;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CalculateApiTest extends ApiTestBase {

	@Test
	public void addTest() {
	    int a = 4, b = 5;
	    Integer sum = a + b;
		// when
		Integer response = appSteps.add(a, b);
		// then
		assertEquals("Sum not equal !", response, sum);
	}
	
}