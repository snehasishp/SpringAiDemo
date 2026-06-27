package com.snehasish.ai.SpringAiDemo.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.model.Generation;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
class OpenAIServiceImplTest {

  @Mock
  ChatModel chatModel;

  @Mock
  ChatResponse chatResponse;

  @Mock
  Generation generation;

  @Mock
  AssistantMessage assistantMessage;

  @InjectMocks
  OpenAIServiceImpl openAIService;

  @BeforeEach
  void setUp() {
  }

  @AfterEach
  void tearDown() {
  }

  @Test
  void testChat() {
    Mockito.when(chatModel.call("test"))
        .thenReturn("test response");

    Assertions.assertEquals("test response", openAIService.chat("test"), "Expected test response");
  }

  @Test
  void testChatWithOptions() {
    Mockito.when(assistantMessage.getText())
        .thenReturn("test response 2");
    Mockito.when(generation.getOutput())
        .thenReturn(assistantMessage);
    Mockito.when(chatResponse.getResult())
        .thenReturn(generation);
    Mockito.when(chatModel.call(any(Prompt.class)))
        .thenReturn(chatResponse);

    Assertions.assertEquals("test response 2", openAIService.chatWithOptions("test 2"), "Expected test response 2");
  }

}
