package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("myappserver")
public class SecureAppServerController {

	@GetMapping(value = "/hello/{name}")
	public String testHello(@PathVariable String name) {
		return "Hello " + name + " !";
	}

}
