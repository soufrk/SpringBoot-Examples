package com.my.project.sample.app.controller;

import com.my.project.sample.app.api.CalculateApi;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class CalculateController implements CalculateApi {

    @Override
    public ResponseEntity<Integer> add(Integer a, Integer b) {
        ResponseEntity<Integer> response = ResponseEntity.badRequest().build();
        if(a != null && b != null){
            Integer sum = a + b;
            response = ResponseEntity.ok(sum);
        }
        return response;
    }
}