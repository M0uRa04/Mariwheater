package br.com.mariwheater.mariwheater.service;

import br.com.mariwheater.mariwheater.model.City;
import br.com.mariwheater.mariwheater.repository.NotificationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationsService {

    @Autowired
    private NotificationsRepository notificationsRepository;

    @Autowired
    private CityService cityService;

    public void printAllCitiesIfTemperatureIsDangerous () {
        var cities = cityService.getAllCitiesWithTemperatureIsDangerous();
        cities.forEach(System.out::println);
    }
}
