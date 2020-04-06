package StepDefinition;

import Resources.APIResource;
import Resources.TestDataBuild;
import Resources.Utils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class StepDefinition extends Utils {

    ResponseSpecification responseSpecBuilder;
    RequestSpecification request;
    Response response;
    static String place_id;
    TestDataBuild data = new TestDataBuild();

    @Given("Add Place Payload with {string} {string} {string}")
    public void add_Place_Payload_with(String name, String language, String address) throws IOException {


        request=given().spec(getRequestSpecification()).body(data.getAddPlacePayload(name,language,address));


    }

    @When("user calls {string} with {string} request")
    public void user_calls_with_request(String endpoint, String method) {

         APIResource apiResource=APIResource.valueOf(endpoint);
        System.out.println(apiResource.getResource());

         responseSpecBuilder =  new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

        if (method.equalsIgnoreCase("POST"))
         response = request.when().post(apiResource.getResource());

        else if (method.equalsIgnoreCase("GET"))
            response = request.when().get(apiResource.getResource());


    }

    @Then("the API got success with status code {int}")
    public void the_API_got_success_with_status_code(Integer statuscode) {


        assertEquals(response.statusCode(),200);
    }

    @Then("{string} in response body is {string}")
    public void in_response_body_is(String Key, String Value) {

        assertEquals(getJsonPathValue(response,Key),Value);

        System.out.println("Successfully Ran");
    }

    @Then("verify place_id created maps to {string} using {string}")
    public void verify_place_id_created_maps_to_using(String expectedname, String endpoint) throws IOException {


        place_id = getJsonPathValue(response,"place_id");

       request= given().spec(getRequestSpecification()).queryParam("place_id",place_id);
        user_calls_with_request(endpoint,"GET");
        String actualname=getJsonPathValue(response,"name");
        assertEquals(actualname,expectedname);

    }

    @Given("Delete Place Payload")
    public void delete_Place_Payload() throws IOException {

        request=given().spec(getRequestSpecification()).body(data.getDeletePayload(place_id));

    }

}
