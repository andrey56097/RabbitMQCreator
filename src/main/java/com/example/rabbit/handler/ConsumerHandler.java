package com.example.rabbit.handler;

public class ConsumerHandler {

    public void handleMessage(String text) {
        System.out.println("Received--------------------------: " + text);
    }
}
