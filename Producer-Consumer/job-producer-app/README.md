This app is mimics a job producer or assigner, where you can
* Set number of jobs to be executed parallel
* Try assigning a job, if available
* Poll for completed jobs and remove them
* Add new jobs when there is space
* Get status of all jobs

**How to run the project**
1.  Make sure Maven is installed in your system.
2.  Run project `mvn spring-boot:run`
3.  Access project Swagger page at `http://localhost:8001/job-processor-app/swagger-ui/`
