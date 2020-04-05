Feature: Validate Google Place API

Scenario: Verify if place is getting added  using Add Place API
     Given Add Place Payload
     When user calls "AddPlaceAPI" with post request
     Then the API got success with status code 200
     And "status" in response body is "OK"