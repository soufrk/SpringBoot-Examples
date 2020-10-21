package com.security.client;

import javax.net.ssl.HttpsURLConnection;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/client/security")
public class ClientController {

	@GetMapping(value = "/hellotest")
	public String testHello() {
		System.out.println("Connecting to secure site");
		HttpsURLConnection.setDefaultHostnameVerifier((hostname, session) -> true);
		RestTemplate restTemp = new RestTemplate();
		return restTemp.getForObject("https://192.168.3.10:8443/test/security/hello/aman123", String.class);
	}

}