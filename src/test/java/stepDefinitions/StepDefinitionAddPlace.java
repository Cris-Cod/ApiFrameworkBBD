package stepDefinitions;

import io.cucumber.java.en.*;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.Assert;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

import java.io.FileNotFoundException;
import java.io.IOException;

import static io.restassured.RestAssured.given;

public class StepDefinitionAddPlace  extends Utils {

    ResponseSpecification resspec;
    RequestSpecification res;
    Response response;
    TestDataBuild data = new TestDataBuild();
    static String placeId;

    @Given("Add Place Payload with {string} {string} {string}")
    public void add_place_payload_with(String name, String language, String address) throws IOException {


        res = given().spec(requestSpecification())
                .body(data.AddPlacePayload(name,language,address));
    }
    @When("user calls {string} with {string} http request")
    public void user_calls_with_http_request(String resource, String method) {

        APIResources resourceAPI = APIResources.valueOf(resource);
        System.out.println(resourceAPI.getResource());

        resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

        if(method.equalsIgnoreCase("POST")){
            response = res.when().post(resourceAPI.getResource());
        } else if (method.equalsIgnoreCase("GET")) {
            response = res.when().get(resourceAPI.getResource());
        }
        //then().spec(resspec).extract().response();
    }
    @Then("the API call is success with status {int}")
    public void the_api_call_is_success_with_status(Integer int1) {
        Assert.assertEquals(response.getStatusCode(), 200);

    }
    @Then("{string} in response body is {string}")
    public void in_response_body_is(String keyValue, String Expectedvalue) {
        Assert.assertEquals(getJsonPath(response,keyValue), Expectedvalue);

    }

    @Then("verify place_Id created maps to {string} using {string}")
    public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws IOException {
       //requestSpec
        placeId = getJsonPath(response, "place_id");
        res=given().spec(requestSpecification())
                .queryParam("place_id", placeId);
        user_calls_with_http_request(resource, "GET");
        String actualName = getJsonPath(response,"name");
        Assert.assertEquals(actualName,expectedName);
    }

    @Given("DeletePlace payload")
    public void delete_place_payload() throws IOException {
        res = given().spec(requestSpecification()).body(data.deletePlacePayload(placeId));
    }
}
