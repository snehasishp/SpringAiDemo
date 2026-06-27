package com.snehasish.ai.SpringAiDemo.controller;

import com.snehasish.ai.SpringAiDemo.model.Answer;
import com.snehasish.ai.SpringAiDemo.model.CapitalRequest;
import com.snehasish.ai.SpringAiDemo.model.CapitalResponse;
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

  @PostMapping("capital")
  public Answer getCapital(@RequestBody CapitalRequest capitalRequest) {
    return aiService.getCapital(capitalRequest);
  }

  @PostMapping("capital-with-info")
  public Answer getCapitalWithInfo(@RequestBody CapitalRequest capitalRequest) {
    return aiService.getCapitalWithInfo(capitalRequest);
  }

  @PostMapping("capital-with-info-json")
  public Answer getCapitalWithInfoJSON(@RequestBody CapitalRequest capitalRequest) {
    return aiService.getCapitalWithInfoJSON(capitalRequest);
  }

  @PostMapping("capital-json-schema")
  public CapitalResponse getCapitalJSONSchema(@RequestBody CapitalRequest capitalRequest) {
    return aiService.getCapitalJSONSchema(capitalRequest);
  }

  @GetMapping("chat-options")
  public String getChatWithOptions(@RequestParam String prompt) {
    return aiService.chatWithOptions(prompt);
  }
}
