package com.snehasish.ai.SpringAiDemo.model;

import com.fasterxml.jackson.annotation.JsonPropertyDescription;

public record CapitalResponse(@JsonPropertyDescription("The country or state name") String country,
                              @JsonPropertyDescription("The capital city name") String capital,
                              @JsonPropertyDescription("The population of the city") Integer population,
                              @JsonPropertyDescription("The region the city is located in") String region,
                              @JsonPropertyDescription("The primary language spoken in the city") String language,
                              @JsonPropertyDescription("The currency used in the city") String currency) {
}
