This app is mimics a job processor, where you can
* Set size of queue
* Assign job
* Mark them as complete
* Get status of a particular job
* Get status of all jobs

**How to run the project**
1.  Make sure Maven is installed in your system.
2.  Run project `mvn spring-boot:run`
3.  Access project Swagger page at `http://localhost:8001/job-processor-app/swagger-ui/`

**API Contract Convention**
* HTTP 200 request successful
* HTTP 202 request unsuccessful
