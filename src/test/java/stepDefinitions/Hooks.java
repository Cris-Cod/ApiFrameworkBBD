package stepDefinitions;

import io.cucumber.java.Before;

import java.io.IOException;

public class Hooks {

    @Before("@DeletePlace")
    public void beforeScenario() throws IOException {
        //Write a code that will give you place id
        // executethis code only when  place id is null
        StepDefinitionAddPlace m = new StepDefinitionAddPlace();

        if(StepDefinitionAddPlace.placeId ==null){
            m.add_place_payload_with("Mary", "Indi", "Asia");
            m.user_calls_with_http_request("AddPlaceAPI", "POST");
            m.verify_place_id_created_maps_to_using("Mary","GetPlaceApi");
        }

    }
}
