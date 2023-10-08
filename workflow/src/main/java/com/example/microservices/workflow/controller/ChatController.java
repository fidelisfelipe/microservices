package com.example.microservices.workflow.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChatController {
    @GetMapping("api/v1/chat")
    public String chatPage() {
        return "chat.html";
    }
}
