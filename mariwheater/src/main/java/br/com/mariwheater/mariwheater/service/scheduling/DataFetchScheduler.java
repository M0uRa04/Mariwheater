package br.com.mariwheater.mariwheater.service.scheduling;

import br.com.mariwheater.mariwheater.dto.CityData;
import br.com.mariwheater.mariwheater.external.WheaterAPIService;
import br.com.mariwheater.mariwheater.model.City;
import br.com.mariwheater.mariwheater.service.city.CityService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DataFetchScheduler {

    private final WheaterAPIService wheaterAPIService;
    private final CityService cityService;

    public DataFetchScheduler(WheaterAPIService wheaterAPIService, CityService cityService) {
        this.wheaterAPIService = wheaterAPIService;
        this.cityService = cityService;
    }

    private void saveCities (List<CityData> cityDataList) {
        cityService.saveAllCities(cityDataList);
    }
    public void fetchDataAndSaveCities () {
        var serializableCities = wheaterAPIService.requestCities();
        cityService.deleteLastHourRecords();
        cityService.resetAutoIncrement();
        saveCities(wheaterAPIService.cityDataUnmarshalling(serializableCities));
    }

}
