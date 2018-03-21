Feature: Accept request

    Requests in JSON format should be accepted
    by a REST endpoint and processed asynchronously.

    Scenario: JSON request is accepted
        Given a JSON request
        When it is received by the endpoint
        Then an HTTP OK status code 200 will be returned

    Scenario: Malformed request is rejected
        Given a malformed request
        When it is received by the endpoint
        Then it will be rejected with HTTP status code 400 Bad Request

    Scenario: Request with incorrect field is rejected
            Given a request with incorrect field
            When it is received by the endpoint
            Then it will be rejected with HTTP status code 500 Internal Server Error