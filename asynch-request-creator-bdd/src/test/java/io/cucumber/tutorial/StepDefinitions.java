package io.cucumber.tutorial;

import cucumber.api.java8.En;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import static org.junit.Assert.assertEquals;


public class StepDefinitions implements En {

    private String REQUEST;
    private String URI;
    private Request request;
    private HttpResponse response;


    public StepDefinitions() {
        Given("^a JSON request$", () -> {
            REQUEST = "{ \"content\": \"My JSON request\"}";
        });

        Given("^a malformed request$", () -> {
            REQUEST = "Malformed Request";
        });

        Given("^a request with incorrect field", () -> {
            REQUEST = "{ \"incorrect\": \"this is something\"}";
        });


        When("^it is received by the endpoint$", () -> {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpPost postRequest = new HttpPost("http://localhost:8010/create-request");
            StringEntity entity = new StringEntity(REQUEST);
            postRequest.addHeader("content-type", "application/json");
            postRequest.setEntity(entity);
            response = httpClient.execute(postRequest);

        });

        Then("^an HTTP OK status code 200 will be returned$", () -> {
            assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());
        });


        Then("^it will be rejected with HTTP status code 400 Bad Request$", () -> {
            assertEquals(HttpStatus.SC_BAD_REQUEST, response.getStatusLine().getStatusCode());

        });


        Then("^it will be rejected with HTTP status code 500 Internal Server Error$", () -> {
            assertEquals(HttpStatus.SC_INTERNAL_SERVER_ERROR, response.getStatusLine().getStatusCode());

        });

    }



}
