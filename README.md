<img src="https://binmile.com/wp-content/uploads/2021/03/selenium-automation-testing.jpg" alt="Selenium UI Automation" style="height: 350px; width:700px;"/>

# Selenium UIAutomation Framework

UI Automation Hybrid Framework with ***Selenium WebDriver*** and ***TestNG*** integration, ***Extent Reports***, ***
Faker Library***, ***Docker Setup*** for ***Selenium Grid***, ***Elasticsearch & Kibana*** for Live Tests Reports, ***
Azure Devops CI/CD*** Pipeline and relevant dependencies.

## Tech Stack

1. Java
2. Selenium WebDriver
3. TestNG
4. Selenium Grid
5. Rest Assured
6. Elastisearch and Kibana
7. Docker Containerization
8. Azure Devops

## Included/Features Implemented

### Selenium UIAutomation Framework with TestNG

1. Selenium WebDriver with TestNG Framework to test and validate Web Application
2. Extent Report 5.x for generating Reports and Logging for TestNG Test Run
3. Thread safety for WebDriver and Extent Reports
4. Implementation classes to take Screenshot, read from properties/json, rest-assured methods, string encoding-decoding
5. Helper classes to create wrappers around diff WebElement methods, Wait, Dropdown etc.
6. TestNG Custom Annotations and RetryAnalyzer listeners
7. TestNG XML -> Sample XMLs for to run Tests based on Groups/Parallel
8. Selenium Grid with chrome and firefox nodes to run the tests in server via docker setup
9. Elastisearch and Kibana to check live tests outcome in server via docker setup
10. Dockerfile -> to create the Image of the project, copying relevant JARs and Files and Entrypoint to specify Test
    Execution
11. docker-compose files -> to create Selenium Grid setup and Elastisearch and Kibana setup
12. azure-pipelines.yml -> to build the Maven project, create and push the Docker image to repository
13. pom.xml -> maven pom.xml specifying all the dependencies and plugins for goals, reports and running the specified
    testNG xml using surefire plugin
14. Monte Screen Recording APIs -> to capture video recording for the Test Run
15. Log4j2 -> Logging for Tests executions

## Not Included/Yet to be Done

1. Excel Read/Write
2. Email APIs to send Reports
3. Data Parameterization examples/Parametrized Tests run using Excel
4. Jenkinsfile -> for creating and pushing Image to Docker repository

## Known Caveats/Limitations

1. **mvn test** command won't work due to surefire plugin which requires testNG xml suite to be selected, will throw
   this error ***Execution default-test of goal org.apache.maven.plugins:maven-surefire-plugin:2.22.2:test failed:
   testSuiteXmlFiles0 has null value***
2. Screen recording using Monte library won't work in parallel run

## Instructions

### Building Docker Image

1. Maven Command to Prepare Jars before running Dockerfile -> ***mvn clean package -DskipTests***
2. To Build Docker Image, run command: "***docker build -t <preferredImageName:latest> .***" example: docker build -t
   selenium_uiautomation:latest .

### Pushing Docker Images to Docker Repository

### Docker Setup For Selenium Hub For Tests Run on Local System

1. Run ***docker-compose -f docker-compose-localgrid.yaml*** to start the Selenium Grid Hub with Chrome and Firefox
2. The **runmode** in config.properties/config.json needs to be set as "remote"
3. The TestNG XML suites and Tests can be run from the IDE as it is(right click and Run)
4. The Tests can be run from Maven command line by specifying the XML suite and Browser(if Browser is not provided
   default Chrome is selected) - ***mvn clean package test -DsuiteXmlFile=${path/testNG XML name}
   -DBROWSER=${Browser}***
5. To bring down the Docker Setup ***docker-compose -f docker-compose-localgrid.yaml down***
6. Updating Grid and Node images to latest "***docker pull <image:tagName>***", example : docker pull
   selenium/node-firefox:latest
7. Scaling up Nodes for parallel execution, ***docker-compose -f docker-compose-localgrid.yaml up --scale chrome=3***

### Docker Setup For Elasticsearch and Kibana to track Real Time Tests result

1. Run ***docker-compose -f docker-compose-eklsetup.yaml*** to start the ElastiSearch and Kibana Setup
2. The **writetoekl** in config.properties/config.json needs to be set as "yes"
3. Once the docker setup runs, it requires approx 5 mins before the Kibana setup is ready, the Tests should be run post
   Kibana webapp is ready, the app can be accessed at ***"http://localhost:5601/"*** (the port is defined in
   docker-compose file)
4. The dashboard needs to be configured, to view the different statistics
    * Create Sample Collection and Document using POSTMAN app/Curl commands, in Postman do a POST request
      for "http://localhost:9200/regression/_doc" with JSON body having keys "testname" and "status" with dummy values(
      reference can be checked in EKLImpl.java class)
    * Go to Management > Stack Management
    * Go to Kibana > Data Views > Create Data View for regression collection
    * Go to Analytics > Dashboard > Create Dashboard for the above created Data View
5. To bring down the Docker Setup ***docker-compose -f docker-compose-eklsetup.yaml down***

**Sample EKS Dashboard**
![](test-img/EKSDashboard.png)

### Monte Screen Recorder Library for recording Test Run Videos

1. Set the **screenrecording** value to yes in properties file to record the video for tests(method added only for
   failed Tests)

### Log4J Logging

1. Set the **log4jloggingrequired** value to yes in properties file to write logs to file(per Test one respective file)
   and to display in the console

***Please check the config.properties file under test/resources for further info on various keys set in the framework***

## Common Troubleshoot

### Maven Tests

1. **mvn test** - will throw error since the surefire plugin in POM expect TestNG XML file to be specified

### Docker Setup

1. The Docker default Port Mapping is set to 4444,if localHost has already Application mapped with 4444 port; this needs
   to be changed in Docker compose and the same needs to be updated in **Huburl** in config.properties/config.json
2. Check changes to Selenium Grid image
   from [GitHub](https://github.com/SeleniumHQ/docker-selenium#node-configuration-options) for changes to Env variables
   and other settings

### Elastisearch and Kibana Setup

1. The webapp is not loading -> It takes almost 5 mins for the Kibana app to load, check the logs after running
   docker-compose if elastisearch is exiting at code 137, if Yes the Docker app needs to be updated with more resources
   from the local system
2. The Create Data View is not listing any Collection/Document -> Sample Collection and Document needs to be created
   using POSTMAN/Curl commands
