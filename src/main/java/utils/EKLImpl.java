package utils;

import enums.ConfigProperties;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

/**
 * EKLImp class to implement RestAssured Methods for Elastic Search queries
 */
public final class EKLImpl {

    private EKLImpl() {
    }

    /**
     * Method to create the Request Specification
     *
     * @return Request Specification - Request specification for the Rest APIs
     */
    public static RequestSpecification setRequestSpec() {
        RequestSpecification specs = new RequestSpecBuilder()
                .setBaseUri("http://localhost:9200/")
                .setBasePath("regression")
                .build();

        return specs;
    }

    /**
     * Method to delete the Regression Collection
     *
     */
    public static void clearPreviousELKResults() {
        if(PropertiesFileImpl.getDataFromPropertyFile(ConfigProperties.WRITETOEKL).equalsIgnoreCase("yes")){
            RequestSpecification specs = setRequestSpec();
            given(specs).delete();
        }
    }

    /**
     * Method to set test run data to Regression Collection
     *
     * @param testName - Test/Method name
     * @param status - Test Status
     */
    public static void sendResultsToELK(String testName, String status) {
        if(PropertiesFileImpl.getDataFromPropertyFile(ConfigProperties.WRITETOEKL).equalsIgnoreCase("yes")){
            Map<String, String> map = new HashMap<>();
            map.put("testName", testName);
            map.put("status", status);

            RequestSpecification specs = setRequestSpec();
            Response response = given(specs).contentType(ContentType.JSON)
                    .log().all()
                    .body(map)
                    .post("/_doc");

            response.then().statusCode(201);
            response.prettyPrint();
        }
    }

}
