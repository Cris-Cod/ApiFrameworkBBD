Feature: Validating place API'S

  @AddPlace
  Scenario Outline: Verify if place is being Successfully added using AddPlaceAPI
    Given Add Place Payload with "<name>" "<language>" "<address>"
    When user calls "AddPlaceAPI" with "Post" http request
    Then the API call is success with status 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And verify place_Id created maps to "<name>" using "GetPlaceApi"

    Examples:
    |name   | language |address           |
    |AAhouse|English   |World Cross Center|

  @DeletePlace
  Scenario: Verify if Delete Place functionality is working
    Given DeletePlace payload
    When  user calls "DeletePlaceApi" with "Post" http request
    Then  the API call is success with status 200
    And "status" in response body is "OK"



