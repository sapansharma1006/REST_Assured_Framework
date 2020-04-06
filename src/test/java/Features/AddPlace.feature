Feature: Validate Google Place API

     @All @AddPlace
     Scenario Outline: Verify if place is getting added  using Add Place API
          Given Add Place Payload with "<name>" "<language>" "<address>"
          When user calls "AddPlaceAPI" with "POST" request
          Then the API got success with status code 200
          And "status" in response body is "OK"
          And "scope" in response body is "APP"
          And verify place_id created maps to "<name>" using "GetPlaceAPI"
     Examples:
          |name|language|address|
          |sapan|hindi  |seoni  |
 #         |mahima|english|bangalore|

   @All  @DeletePlace
     Scenario: Verify whether place id is getting deleted using delete place API
          Given Delete Place Payload
          When user calls "DeletePlaceAPI" with "POST" request
          Then the API got success with status code 200
          And "status" in response body is "OK"

