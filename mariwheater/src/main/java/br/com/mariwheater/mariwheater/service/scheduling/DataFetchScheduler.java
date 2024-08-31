package br.com.mariwheater.mariwheater.service.scheduling;

import br.com.mariwheater.mariwheater.DTO.CityData;
import br.com.mariwheater.mariwheater.external.WheaterAPIService;
import br.com.mariwheater.mariwheater.model.City;
import br.com.mariwheater.mariwheater.service.city.CityService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataFetchScheduler {

    private final WheaterAPIService wheaterAPIService;
    private final CityService cityService;

    public DataFetchScheduler(WheaterAPIService wheaterAPIService, CityService cityService) {
        this.wheaterAPIService = wheaterAPIService;
        this.cityService = cityService;
    }

    private void saveCities (List<CityData> cityDataList) {
        for (CityData data : cityDataList) {
            cityService.save(new City(data));
        }
    }
    public void fetchDataAndSaveCities () {
        var serializableCities = wheaterAPIService.requestCities();
        cityService.deleteLastHourRecords();
        cityService.resetAutoIncrement();
        saveCities(wheaterAPIService.cityDataUnmarshalling(serializableCities));
    }

}
