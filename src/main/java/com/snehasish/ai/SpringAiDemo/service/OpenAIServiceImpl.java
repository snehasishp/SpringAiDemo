package com.snehasish.ai.SpringAiDemo.service;

import com.snehasish.ai.SpringAiDemo.model.Answer;
import com.snehasish.ai.SpringAiDemo.model.CapitalRequest;
import com.snehasish.ai.SpringAiDemo.model.CapitalResponse;
import com.snehasish.ai.SpringAiDemo.model.Question;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.converter.BeanOutputConverter;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

/**
 * Implementation of AIService using OpenAI Chat API.
 */
@Service
public class OpenAIServiceImpl implements AIService {

  @Value("classpath:templates/get-capital-prompt.st")
  Resource getCapitalPrompt;

  @Value("classpath:templates/get-capital-with-info-prompt.st")
  Resource getCapitalInfoPrompt;

  @Value("classpath:templates/get-capital-json-prompt.st")
  Resource getCapitalInfoJSONPrompt;

  @Value("classpath:templates/get-capital-json-format-prompt.st")
  Resource getCapitalJSONFormatPrompt;

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

    return chatWithCustomPromtTemplate(prompt);
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

  /**
   * Use ChatModel to call OpenAI API and get the capital of a state or country.
   *
   * @param capitalRequest CapitalRequest object containing state or country
   * @return Answer object containing response
   */
  @Override
  public Answer getCapital(CapitalRequest capitalRequest) {
    PromptTemplate promptTemplate = new PromptTemplate(getCapitalPrompt);
    Prompt prompt = promptTemplate.create(Map.of("stateOrCountry", capitalRequest.stateOrCountry()));

    return chatWithCustomPromtTemplate(prompt);
  }

  /**
   * Use ChatModel to call OpenAI API and get the capital of a state or country with additional information.
   *
   * @param capitalRequest CapitalRequest object containing state or country
   * @return Answer object containing response
   */
  @Override
  public Answer getCapitalWithInfo(CapitalRequest capitalRequest) {
    PromptTemplate promptTemplate = new PromptTemplate(getCapitalInfoPrompt);
    Prompt prompt = promptTemplate.create(Map.of("stateOrCountry", capitalRequest.stateOrCountry()));

    return chatWithCustomPromtTemplate(prompt);
  }

  /**
   * Use ChatModel to call OpenAI API to return JSON response and get the capital of a state or country with additional information.
   *
   * @param capitalRequest CapitalRequest object containing state or country
   * @return Answer object containing response
   */
  @Override
  public Answer getCapitalWithInfoJSON(CapitalRequest capitalRequest) {
    PromptTemplate promptTemplate = new PromptTemplate(getCapitalInfoJSONPrompt);
    Prompt prompt = promptTemplate.create(Map.of("stateOrCountry", capitalRequest.stateOrCountry()));
    return chatWithCustomPromtTemplate(prompt);
  }

  /**
   * Use ChatModel to call OpenAI API to return JSON response and get the capital of a state or country with additional information.
   *
   * @param capitalRequest CapitalRequest object containing state or country
   * @return CapitalResponse object containing response
   */
  @Override
  public CapitalResponse getCapitalJSONSchema(CapitalRequest capitalRequest) {
    BeanOutputConverter<CapitalResponse> beanOutputConverter = new BeanOutputConverter<>(CapitalResponse.class);
    String format = beanOutputConverter.getFormat();

    PromptTemplate promptTemplate = new PromptTemplate(getCapitalJSONFormatPrompt);
    Prompt prompt = promptTemplate.create(Map.of("stateOrCountry", capitalRequest.stateOrCountry(),
        "format", format));
    ChatResponse response = chatModel.call(prompt);
    return beanOutputConverter.convert(Objects.requireNonNull(response.getResult().getOutput().getText()));
  }

  private Answer chatWithCustomPromtTemplate(Prompt prompt) {
    ChatResponse response = chatModel.call(prompt);
    return new Answer(response.getResult().getOutput().getText());
  }

}
