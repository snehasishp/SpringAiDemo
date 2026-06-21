package com.snehasish.ai.SpringAiDemo.service;

import com.snehasish.ai.SpringAiDemo.model.Answer;
import com.snehasish.ai.SpringAiDemo.model.Question;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.stereotype.Service;

/**
 * Implementation of AIService using OpenAI Chat API.
 */
@Service
public class OpenAIServiceImpl implements AIService {

  private final ChatModel chatModel;

  public OpenAIServiceImpl(ChatModel chatModel) {
    this.chatModel = chatModel;
  }

  /**
   * Use ChatModel to call OpenAI API and get the response.
   *
   * @param prompt String prompt input message
   * @return String response from OpenAI API
   */
  @Override
  public String chat(String prompt) {
    return chatModel.call(prompt);
  }

  /**
   * Use ChatModel to call OpenAI API and get the response.
   *
   * @param question Question object containing prompt
   * @return Answer object containing response
   */
  @Override
  public Answer chatWithPromtTemplate(Question question) {
    PromptTemplate promptTemplate = new PromptTemplate(question.prompt());
    Prompt prompt = promptTemplate.create();

    ChatResponse response = chatModel.call(prompt);
    return new Answer(response.getResult().getOutput().getText());
  }

  /**
   * Use ChatModel with options to call OpenAI API and get the response.
   *
   * @param promptInput String prompt input message
   * @return String response from OpenAI API
   */
  @Override
  public String chatWithOptions(String promptInput) {
    Prompt prompt = new Prompt(
        promptInput,
        OpenAiChatOptions.builder()
            .model("gpt-4o")
//            .maxTokens(150)  // Use maxTokens for non-reasoning models
            .temperature(0.4)
            .build()
    );

    ChatResponse response = chatModel.call(prompt);
    return response.getResult().getOutput().getText();
  }
}
