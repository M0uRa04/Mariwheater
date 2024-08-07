package br.com.mariwheater.mariwheater.service;

import br.com.mariwheater.mariwheater.model.City;
import br.com.mariwheater.mariwheater.model.Notifications;
import br.com.mariwheater.mariwheater.repository.NotificationsRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationsService {

    @Autowired
    private NotificationsRepository notificationsRepository;

    @Autowired
    private CityService cityService;

    @Transactional
    public void createAllNotifications (List<City> cities) {
        List<Notifications> notifications = cities.stream()
                .map(this::createNotification)
                .collect(Collectors.toList());
        notifications.forEach(System.out::println);
        notificationsRepository.saveAll(notifications);
    }

    public void printAllCitiesIfTemperatureIsDangerous () {
        var cities = cityService.getAllCitiesWithTemperatureIsDangerous();
        cities.forEach(System.out::println);
    }

    public Notifications createNotification (City city) {
        String message;
        if (isTemperatureHigherThan35(city.getTemperature()) >= 0) {
            message = "A temperatura está elevada em " + city.getName() + ". Está " + city.getTemperature() + " graus. É recomendado o uso de protetor solar para sair de casa.";
        } else {
            message = "A temperatura está baixa em " + city.getName() + ". Está " + city.getTemperature() + " graus. Não esqueça o agasalho ao sair de casa.";
        }
        Notifications notification = new Notifications(message, city);
        city.addNotification(notification);
        return notification;
    }

    public Integer isTemperatureHigherThan35(BigDecimal temperature) {

//      Real case
//        return temperature.compareTo(BigDecimal.valueOf(35.00));

        //Test case
        return temperature.compareTo(BigDecimal.valueOf(25.00));
    }
}
