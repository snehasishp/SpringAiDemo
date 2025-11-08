package com.snehasish.ai.SpringAiDemo.controller;

import com.snehasish.ai.SpringAiDemo.service.ChatService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {
    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping("chat")
    public String chat(@RequestParam String prompt) {
        return chatService.chat(prompt);
    }

    @GetMapping("chat-options")
    public String chatWithOptions(@RequestParam String prompt) {
        return chatService.chatWithOptions(prompt);
    }
}
