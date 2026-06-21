package com.snehasish.ai.SpringAiDemo.controller;

import com.snehasish.ai.SpringAiDemo.model.Answer;
import com.snehasish.ai.SpringAiDemo.model.Question;
import com.snehasish.ai.SpringAiDemo.service.AIService;
import org.springframework.web.bind.annotation.*;

@RestController
public class ChatController {

  private final AIService aiService;

  public ChatController(AIService aiService) {
    this.aiService = aiService;
  }

  @GetMapping("chat")
  public String getChat(@RequestParam String prompt) {
    return aiService.chat(prompt);
  }

  @PostMapping("chat")
  public Answer postChat(@RequestBody Question question) {
    return aiService.chatWithPromtTemplate(question);
  }

  @GetMapping("chat-options")
  public String getChatWithOptions(@RequestParam String prompt) {
    return aiService.chatWithOptions(prompt);
  }
}
