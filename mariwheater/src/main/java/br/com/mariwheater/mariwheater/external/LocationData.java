package br.com.mariwheater.mariwheater.external;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public record LocationData(

        String name,
        String region,
        String country,
        @JsonAlias(value = "localtime")
        String localTime
) {
}
