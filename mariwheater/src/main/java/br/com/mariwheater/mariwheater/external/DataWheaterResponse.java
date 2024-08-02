package br.com.mariwheater.mariwheater.external;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DataWheaterResponse(

        LocationData location,

        @JsonAlias(value = "current")
        CurrentData currentData
) {
}
