package com.yi.server;

import java.util.HashMap;
import java.util.Map;

public class SimpleResponse {
    private Map<String, String> responses;

    public SimpleResponse() {
        this.responses = new HashMap<>();
        this.responses.put("Hello", "Hello.");
        this.responses.put("What's your name?", "My name is SimpleServer!");
    }
}
