package com.example.jobprocessor.api;

import com.example.jobprocessor.model.ApiMessage;
import com.example.jobprocessor.model.Job;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static org.springframework.http.ResponseEntity.*;

import java.util.*;

@RestController
@RequestMapping("job")
public class JobProcessController {
    private static int MAX_CAPACITY;
    private static Map<String, Job> MAP_OF_JOBS;
    private static Map<String, Job> MAP_OF_COMPLETED_JOBS;
    static {
        MAX_CAPACITY = -1;
        MAP_OF_JOBS = new HashMap<>();
        MAP_OF_COMPLETED_JOBS = new HashMap<>();
    }
    //1. setSize
        // set MAX_CAPACITY
    //2. addJob
        // check if status MAX_CAPACITY
    //3. getStatusOfJob
    //4. stopJob
        // If job exists in running pool
            // Move to stop Map

    @PostMapping("setsize/{size}")
    public ResponseEntity<?> setMaxCapacity(@PathVariable("size") Integer size) {
        ResponseEntity<?> response = ResponseEntity.accepted().build();
        if(size>0){
            MAX_CAPACITY = size;
            response = ResponseEntity.ok().build();
        }
        return response;
    }

    @PostMapping("add/{jobName}")
    public ResponseEntity<?> addJob(@PathVariable("jobName") String job) {
        ResponseEntity<?> response = ResponseEntity.accepted().build();
        if(MAX_CAPACITY == -1){
            ApiMessage m = new ApiMessage("Set Capacity !!!");
            response = new ResponseEntity(m, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(MAP_OF_JOBS.size() < MAX_CAPACITY){
            Job j = new Job(job, "RUNNING");
            j.setStartedAt(new Date());
            MAP_OF_JOBS.put(job, j);
            response = ResponseEntity.ok().build();
        }
        return response;
    }

    @GetMapping("status/{jobName}")
    public ResponseEntity<?> getStatus(@PathVariable("jobName") String jobName) {
        ResponseEntity<?> response = ResponseEntity.accepted().build();
        if(MAP_OF_JOBS.containsKey(jobName)){
            Job j = MAP_OF_JOBS.get(jobName);
            response = ResponseEntity.ok(j);
        }
        if(MAP_OF_COMPLETED_JOBS.containsKey(jobName)){
            Job j = MAP_OF_COMPLETED_JOBS.get(jobName);
            response = ResponseEntity.ok(j);
        }
        return response;
    }

    @GetMapping("allJobs")
    public ResponseEntity<?> getAllJobs() {
        ResponseEntity<?> response = ResponseEntity.accepted().build();
        Set<Job> setOfJobs = new HashSet<>();
        setOfJobs.addAll(MAP_OF_JOBS.values());
        setOfJobs.addAll(MAP_OF_COMPLETED_JOBS.values());
        if(!setOfJobs.isEmpty()){
            response = ResponseEntity.ok(setOfJobs);
        }
        return response;
    }

    @DeleteMapping("stop/{jobName}")
    public ResponseEntity<?> stopJob(@PathVariable("jobName") String jobName) {
        ResponseEntity<?> response = ResponseEntity.accepted().build();
        if(MAP_OF_JOBS.containsKey(jobName)){
            Job j = MAP_OF_JOBS.get(jobName);
            j.setStatus("COMPLETE");
            j.setCompletedAt(new Date());
            MAP_OF_JOBS.remove(jobName);
            MAP_OF_COMPLETED_JOBS.put(jobName, j);
            response = ResponseEntity.ok().build();
        }
        return response;
    }
}
