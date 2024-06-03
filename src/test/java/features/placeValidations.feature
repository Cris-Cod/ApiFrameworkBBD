Feature: Validating place API'S

  Scenario: Verify if place is being Successfully added using AddPlaceAPI
    Given Add Place Payload
    When user calls "AddPlaceAPI" with Post http request
    Then the API call is success with status 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"