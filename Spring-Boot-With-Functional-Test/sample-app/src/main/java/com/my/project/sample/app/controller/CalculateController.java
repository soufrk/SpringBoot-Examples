package com.my.project.sample.app.controller;

import com.my.project.sample.app.api.CalculateApi;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class CalculateController implements CalculateApi {

    @Override
    public ResponseEntity<Integer> add(Integer a, Integer b) {
        ResponseEntity<Integer> response = ResponseEntity.badRequest().build();
        if(a != null && b != null) {
            Integer sum = a + b;
            response = ResponseEntity.ok(sum);
        }
        return response;
    }

    @Override
    public ResponseEntity<Integer> subtract(Integer a, Integer b) {
        ResponseEntity<Integer> response = ResponseEntity.badRequest().build();
        if(a != null && b != null) {
            Integer difference = a - b;
            response = ResponseEntity.ok(difference);
        }
        return response;
    }

    @Override
    public ResponseEntity<Integer> multiply(Integer a, Integer b) {
        ResponseEntity<Integer> response = ResponseEntity.badRequest().build();
        if(a != null && b != null) {
            Integer product = 0;
            if(a != 0 && b != 0){
                product = a * b;
            }
            response = ResponseEntity.ok(product);
        }
        return response;
    }

    @Override
    public ResponseEntity<Integer> divide(Integer a, Integer b) {
        ResponseEntity<Integer> response = ResponseEntity.badRequest().build();
        if(a != null && b != null && b != 0) {
            Integer product = 0;
            if(a != 0){
                product = a / b;
            }
            response = ResponseEntity.ok(product);
        }
        return response;
    }
}