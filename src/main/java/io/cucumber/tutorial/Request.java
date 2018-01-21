package io.cucumber.tutorial;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Request {
    @JsonProperty("content")
    private String content;

    public Request() {

    }


    public Request(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String toString() {
        return content;
    }
}
