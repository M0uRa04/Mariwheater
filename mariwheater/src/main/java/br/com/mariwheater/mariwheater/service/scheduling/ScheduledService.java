package br.com.mariwheater.mariwheater.service.scheduling;

import br.com.mariwheater.mariwheater.DTO.CityData;
import br.com.mariwheater.mariwheater.external.WheaterAPIService;
import br.com.mariwheater.mariwheater.model.City;
import br.com.mariwheater.mariwheater.service.CityService;
import br.com.mariwheater.mariwheater.service.NotificationsService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduledService {

    private final WheaterAPIService wheaterAPIService;
    private final CityService cityService;
    private final NotificationsService notificationsService;

    public ScheduledService (WheaterAPIService wheaterAPIService, CityService cityService, NotificationsService notificationsService) {
        this.wheaterAPIService = wheaterAPIService;
        this.cityService = cityService;
        this.notificationsService = notificationsService;
    }
    //@Scheduled(fixedRate = 3600000) -> One hour request
    @Scheduled(fixedRate = 60000)
    public void fetchDataAndSaveCities () {
        cityService.deleteLastHourRecords();
        cityService.resetAutoIncrement();
        List<CityData> cities = wheaterAPIService.fetchWeatherData();
        for (CityData data : cities) {
            cityService.save(new City(data));
        }
    }

    @Scheduled(fixedRate = 61000)
    public void checkWeatherAndNotify() {
        List<City> dangerousCities = cityService.getAllCitiesWithTemperatureIsDangerous();
        notificationsService.createAllNotifications(dangerousCities);
    }
}
