package com.my.project.sample.app.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("calculate")
public interface CalculateApi {

    @GetMapping(path = "add/{a}/{b}")
    public ResponseEntity<Integer> add(@PathVariable("a") Integer a, @PathVariable("b") Integer b);
	
}