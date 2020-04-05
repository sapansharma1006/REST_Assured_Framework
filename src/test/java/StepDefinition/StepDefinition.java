package StepDefinition;

import Pojo.AddPlace;
import Pojo.Location;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.ArrayList;
import java.util.List;

public class StepDefinition {

    @Given("Add Place Payload")
    public void add_Place_Payload() {
        // Write code here that turns the phrase above into concrete actions
        RestAssured.baseURI="https://rahulshettyacademy.com";
                AddPlace place = new AddPlace();
        List<String> myList = new ArrayList<String>();
        Location l = new Location();

        myList.add("shoe park");
        myList.add("shop");
        l.setLat(-38.383494);
        l.setLng(33.427362);
        place.setAccuracy(50);
        place.setAddress("29, side layout, cohen 09");
        place.setLanguage("French-IN");
        place.setName("Frontline house");
        place.setPhone_number("(+91) 983 893 3937");
//        place.setAddress("29, side layout, cohen 09");
        place.setWebsite("https://www.rahulshettyacademy.com/");
        place.setTypes(myList);
        place.setLocation(l);

        RequestSpecification requestSpecification = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").setContentType(ContentType.JSON).build();
        ResponseSpecBuilder responseSpecBuilder =  new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON);
        RequestSpecification request=given().spec(requestSpecification).body(place);

    }

    @When("user calls {string} with post request")
    public void user_calls_with_post_request(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Then("the API got success with status code {int}")
    public void the_API_got_success_with_status_code(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Then("{string} in response body is {string}")
    public void in_response_body_is(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }
}
