package br.com.mariwheater.mariwheater.DTO;

import br.com.mariwheater.mariwheater.external.DataWheaterResponse;

public record CityData(

        String name,
        Float temperature,
        String lastUpdated
) {
    public CityData(DataWheaterResponse wheaterSerializableObject) {
        this(wheaterSerializableObject.location().name(),
                wheaterSerializableObject.currentData().temperatureInCelsius(),
                wheaterSerializableObject.currentData().lastUpdated());
    }
}
