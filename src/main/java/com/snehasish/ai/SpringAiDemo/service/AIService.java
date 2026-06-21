package com.snehasish.ai.SpringAiDemo.service;

import com.snehasish.ai.SpringAiDemo.model.Answer;
import com.snehasish.ai.SpringAiDemo.model.CapitalRequest;
import com.snehasish.ai.SpringAiDemo.model.Question;

/**
 * Interface for AI services.
 */
public interface AIService {

  /**
   * Use ChatModel to call OpenAI API and get the response.
   *
   * @param prompt String prompt input message
   * @return String response from OpenAI API
   */
  String chat(String prompt);

  /**
   * Use ChatModel to call OpenAI API and get the response.
   *
   * @param question Question object containing prompt
   * @return Answer object containing response
   */
  Answer chatWithPromtTemplate(Question question);

  /**
   * Use ChatModel with options to call OpenAI API and get the response.
   *
   * @param prompt String prompt input message
   * @return String response from OpenAI API
   */
  String chatWithOptions(String prompt);

  /**
   * Use ChatModel to call OpenAI API and get the capital of a state or country.
   *
   * @param capitalRequest CapitalRequest object containing state or country
   * @return Answer object containing response
   */
  Answer getCapital(CapitalRequest capitalRequest);

  /**
   * Use ChatModel to call OpenAI API and get the capital of a state or country with additional information.
   *
   * @param capitalRequest CapitalRequest object containing state or country
   * @return Answer object containing response
   */
  Answer getCapitalWithInfo(CapitalRequest capitalRequest);
}
