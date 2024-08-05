package br.com.mariwheater.mariwheater.service.scheduling;

import br.com.mariwheater.mariwheater.DTO.CityData;
import br.com.mariwheater.mariwheater.external.WheaterAPIService;
import br.com.mariwheater.mariwheater.model.City;
import br.com.mariwheater.mariwheater.service.CityService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduledService {

    private final WheaterAPIService wheaterAPIService;
    private final CityService cityService;

    public ScheduledService (WheaterAPIService wheaterAPIService, CityService cityService) {
        this.wheaterAPIService = wheaterAPIService;
        this.cityService = cityService;
    }
    //@Scheduled(fixedRate = 3600000) -> One hour request
    @Scheduled(fixedRate = 15000)
    public void fetchDataAndSaveCities () {
        cityService.deleteLastHourRecords();
        cityService.resetAutoIncrement();
        List<CityData> cities = wheaterAPIService.fetchWeatherData();
        for (CityData data : cities) {
            cityService.save(new City(data));
        }
    }
}
