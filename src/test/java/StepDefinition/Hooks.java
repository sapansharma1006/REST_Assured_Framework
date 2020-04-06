package StepDefinition;

import io.cucumber.java.Before;

import java.io.IOException;

import static StepDefinition.StepDefinition.place_id;

public class Hooks {
    StepDefinition stepDefinition = new StepDefinition();

    @Before("@DeletePlace")
    public void beforeDeleteScenario() throws IOException {


        if (place_id==null) {

            stepDefinition.add_Place_Payload_with("sapan", "Hindi", "Bangalore");
            stepDefinition.user_calls_with_request("AddPlaceAPI", "POST");
            stepDefinition.verify_place_id_created_maps_to_using("sapan", "GetPlaceAPI");
        }

    }

}
