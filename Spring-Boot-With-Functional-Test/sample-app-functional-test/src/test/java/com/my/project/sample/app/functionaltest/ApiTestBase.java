package com.my.project.sample.app.functionaltest;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.my.project.sample.app.functionaltest.steps.CalculateApiSteps;
import org.junit.Rule;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import com.my.project.sample.app.SampleApp;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

@RunWith(SpringRunner.class)
@SpringBootTest(classes ={SampleApp.class},
	webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public abstract class ApiTestBase {

	@Rule
	public WireMockRule apiMock = new WireMockRule(options().port(8777));

	@Autowired
    protected CalculateApiSteps appSteps;

	@TestConfiguration
	@ComponentScan("com.my.project.sample.app.functionaltest")
	public static class StepsConfiguration {
		
	}
	
}