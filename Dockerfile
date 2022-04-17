FROM openjdk:19-jdk-alpine3.15

#Adding Bash to the Alpine image
RUN apk update && apk add bash

#WORKSPACE
WORKDIR /usr/share/tag

#ADD .jars under Target from Host
#into this image
ADD target/selenium-uiautomation.jar            selenium-uiautomation.jar
ADD target/selenium-uiautomation-tests.jar      selenium-uiautomation-tests.jar
ADD target/libs                                 libs

#ADD XML SUITE files
ADD xml-suites/*                                /usr/share/tag/

#copy if any other dependencies are there like test-data files, properties files
ADD src/test/resources/config/*                 src/test/resources/properties/config/

#Overriding JShell to Bash
CMD ["bash"]
