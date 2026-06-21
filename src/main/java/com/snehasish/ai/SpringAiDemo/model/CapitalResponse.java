package com.snehasish.ai.SpringAiDemo.model;

import com.fasterxml.jackson.annotation.JsonPropertyDescription;

public record CapitalResponse(@JsonPropertyDescription("This is the country name") String country,
                              @JsonPropertyDescription("This is the city name") String capital,
                              @JsonPropertyDescription("This is the population in the city") String population,
                              @JsonPropertyDescription("This is the region") String region,
                              @JsonPropertyDescription("This is the language spoken in the city") String language,
                              @JsonPropertyDescription("This is the currency used in the city") String currency) {
}
