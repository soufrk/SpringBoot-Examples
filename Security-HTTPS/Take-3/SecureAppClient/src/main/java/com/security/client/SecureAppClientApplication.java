package com.security.client;

import javax.net.ssl.HttpsURLConnection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class SecureAppClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecureAppClientApplication.class, args);
		System.out.println("Connecting to secure site");
		HttpsURLConnection.setDefaultHostnameVerifier ((hostname, session) -> true);
		RestTemplate restTemp = new RestTemplate();
		String greetings = restTemp.getForObject("https://192.168.3.10:8443/test/security/hello/aman123", String.class);
		System.out.println("Received greetings from secured server ---> " + greetings );
	}
}
