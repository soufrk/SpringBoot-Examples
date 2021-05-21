package com.my.project.sample.app.functionaltest.steps;

import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

import static io.restassured.RestAssured.given;

@Component
public class CalculateApiSteps implements ApplicationListener<WebServerInitializedEvent> {
	
	private int servicePort;

	public Integer add(int value1, int value2) {
		return given().port(servicePort)
				.pathParam("a", value1)
				.pathParam("b", value2)
		        .when().get("calculate/add/{a}/{b}")
		        .then().statusCode(200)
		        .extract().as(Integer.class);
	} 

	@Override
	public void onApplicationEvent(@NotNull WebServerInitializedEvent webServerInitializedEvent) {
		this.servicePort = webServerInitializedEvent.getWebServer().getPort();
	}
}