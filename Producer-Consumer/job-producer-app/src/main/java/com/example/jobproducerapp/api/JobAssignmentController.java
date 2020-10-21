package com.example.jobproducerapp.api;

import com.example.jobproducerapp.models.ApiMessage;
import com.example.jobproducerapp.models.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

@RestController
public class JobAssignmentController {
    private static Integer MAX_CAPACITY;
    private static Queue<Job> QUEUE_OF_JOBS;
    private static Map<String, Job> ACTIVE_JOBS;
    private static List<Job> COMPLETED_JOBS;

    static {
        MAX_CAPACITY = -1;
        ACTIVE_JOBS = new HashMap<>();
        COMPLETED_JOBS = new LinkedList<>();
        QUEUE_OF_JOBS = new LinkedList<>();
    }

//    @Autowired
//    private JobProcessorClient jobProcessorClient;
    // 0. Set queue size
    // 1. Add a job to the queue
    // 2. Try Assigning a job
    // 2. Check queue for completed job
            // If job is completed, remove from queue
    // 3. Get list of all jobs, and status
    // 4. Persist state(Optional)

    @PostMapping("/setsize/{size}")
    public ResponseEntity<?> setSize(@PathVariable("size") Integer size) {
        MAX_CAPACITY = size;
        return ResponseEntity.ok().build();
    }

    @PostMapping("addJob/{jobName}")
    public ResponseEntity<?> acceptJob(@PathVariable("jobName") String jobName) {
        ResponseEntity<?> response = ResponseEntity.accepted().build();
        if (MAX_CAPACITY == -1) {
            ApiMessage m = new ApiMessage("Set Capacity !!!");
            response = new ResponseEntity(m, HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            Job j = new Job(jobName, "WAITING");
            if (QUEUE_OF_JOBS.offer(j)) {
                response = ResponseEntity.ok().build();
            }
        }
        return response;
    }

    @GetMapping("assignJob")
    public ResponseEntity<?> tryShitingJob() throws MalformedURLException, URISyntaxException {
        ResponseEntity<?> response = ResponseEntity.accepted().build();
        if(ACTIVE_JOBS.size()<MAX_CAPACITY && !QUEUE_OF_JOBS.isEmpty()) {
            RestTemplate apiClient = new RestTemplate();
            while (ACTIVE_JOBS.size()<MAX_CAPACITY || QUEUE_OF_JOBS.isEmpty()) {
                Job j = QUEUE_OF_JOBS.peek();
                // Build URL
                // Call API to add job
                String protocol = "http";
                String host = "localhost";
                int port = 8001;
                String path = "/job-processor-app/job/add/" + j.getName();
                String auth = null;
                String fragment = null;
                URI uri = new URI(protocol, auth, host, port, path, null, fragment);
                String url = uri.toURL().toString();
                ResponseEntity<?> jobSubmitResult = apiClient.postForEntity(uri, "", String.class);
                if(jobSubmitResult.getStatusCodeValue() == HttpStatus.OK.value()){
                    // Remove job from QUEUE
                    QUEUE_OF_JOBS.poll();
                    // Add to currentMap
                    j.setStatus("ASSIGNED");
                    ACTIVE_JOBS.put(j.getName(), j);
                    response = ResponseEntity.ok().build();
                } else if(jobSubmitResult.getStatusCodeValue() == HttpStatus.ACCEPTED.value()){
                    break;
                }
            }
        }
        return response;
    }

    @GetMapping("poll")
    public ResponseEntity<?> pollActiveJobs() throws URISyntaxException, MalformedURLException {
        ResponseEntity<?> response = ResponseEntity.accepted().build();
        for(String jobName: ACTIVE_JOBS.keySet()){
            RestTemplate apiClient = new RestTemplate();
            // Call API to check job status
            String protocol = "http";
            String host = "localhost";
            int port = 8001;
            String path = "/job-processor-app/job/status/" + jobName;
            String auth = null;
            String fragment = null;
            URI uri = new URI(protocol, auth, host, port, path, null, fragment);
            String url = uri.toURL().toString();
            ResponseEntity<Job> jobSubmitResult = apiClient.getForEntity(uri, Job.class);
            if(jobSubmitResult.getStatusCodeValue() == HttpStatus.OK.value()){
                Job j = jobSubmitResult.getBody();
                if(j != null && j.getCompletedAt() != null){
                    ACTIVE_JOBS.remove(jobName);
                    COMPLETED_JOBS.add(j);
                    response = ResponseEntity.ok().build();
                }
            }
        }
        return response;
    }

    @GetMapping("allJobs")
    public ResponseEntity<?> getAllJobs(){
        ResponseEntity<?> response = ResponseEntity.accepted().build();
        Set<Job> setOfJobs = new HashSet<>();
        if (MAX_CAPACITY == -1) {
            ApiMessage m = new ApiMessage("Set Capacity !!!");
            response = new ResponseEntity(m, HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            setOfJobs.addAll(COMPLETED_JOBS);
            setOfJobs.addAll(ACTIVE_JOBS.values());
            setOfJobs.addAll(QUEUE_OF_JOBS);
            response = ResponseEntity.ok(setOfJobs);
        }
        return response;
    }
}
