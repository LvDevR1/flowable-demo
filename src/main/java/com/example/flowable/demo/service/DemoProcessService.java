package com.example.flowable.demo.service;

import org.springframework.stereotype.Component;

@Component
public class DemoProcessService {

    public boolean isShortText(String message) {
        return message.length() < 5;
    }

    public void handleShortText() {
        System.out.println("Your text is too short , task failed");
    }

    public void handleLongText() {
        System.out.println("Your text is long enough, registration task successful");
    }
}
