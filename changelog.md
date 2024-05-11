# USEFUL

    1. hub http://localhost:4444/ui#/sessions
    2. kibana reports http://localhost:5601/app/management/insightsAndAlerting/reporting
    3. the website https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index

# NEED TODO:

    1. fix selectors ✔
    2. add waits ✔
    3. test on single mode remote ✔
    4. test on parallel remote ✔
    5. test on single mode local ✔
    6. test on parallel mode local ✔

# TO RUN

    1. run the docker file
    2. run the docker compose of grid
    3. run the eklsetup of kibana reports

# SOLUTIONS

## parallel on local:

    prob 1. webdrivermanager didn't use chromedriver newer than 114, when chrome is v124
    - when running got: "Session not created: This version of ChromeDriver only supports Chrome version 114"
    --> deleted the selenium webdriver cache: located at %USERPROFILE%\.cache\selenium\chromedriver
        resources: 'https://github.com/bonigarcia/webdrivermanager/issues/1282',
                   'https://github.com/Auties00/WhatsappWebRequestAnalyzer/pull/21'.

## prob 2. couldn't run parallel

    - when running got: "Unable to establish websocket connection"
    --> added `options.addArguments("--remote-allow-origins=*");`
        at- driver/DriverFactory.java:65 | (in condition hierarchy: chrome -> local)
        resource: 'https://stackoverflow.com/questions/75680149/unable-to-establish-websocket-connection',
                  'https://stackoverflow.com/questions/57533827/how-to-set-chrome-options-when-using-webdrivermanager'.

