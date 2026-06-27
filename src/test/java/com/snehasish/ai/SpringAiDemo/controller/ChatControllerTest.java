package com.snehasish.ai.SpringAiDemo.controller;

import com.snehasish.ai.SpringAiDemo.model.Answer;
import com.snehasish.ai.SpringAiDemo.model.Question;
import com.snehasish.ai.SpringAiDemo.service.OpenAIServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
class ChatControllerTest {

  @Mock
  OpenAIServiceImpl aiService;

  @InjectMocks
  ChatController chatController;

  @Test
  void testGetChat() {
    Mockito.when(aiService.chat("test"))
        .thenReturn("test response");

    Assertions.assertEquals("test response", chatController.getChat("test"), "Expected test response");
  }

  @Test
  void testPostChat() {
    Mockito.when(aiService.chatWithPromtTemplate(any(Question.class)))
        .thenReturn(new Answer("test response 3"));

    Assertions.assertEquals("test response 3", chatController.postChat(new Question("test 3")).response(), "Expected test response 3");
  }

  @Test
  void testGetChatWithOptions() {
    Mockito.when(aiService.chatWithOptions("test 2"))
        .thenReturn("test response 2");

    Assertions.assertEquals("test response 2", chatController.getChatWithOptions("test 2"), "Expected test response 2");
  }
}