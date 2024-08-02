package br.com.mariwheater.mariwheater.external;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public record CurrentData(
        @JsonAlias(value = "last_updated")
        String lastUpdated,
        @JsonAlias(value = "temp_c")
        Float temperatureInCelsius,

        ConditionData condition
) {
}
