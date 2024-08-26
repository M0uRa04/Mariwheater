package br.com.mariwheater.mariwheater.service.scheduling;

import br.com.mariwheater.mariwheater.DTO.CityData;
import br.com.mariwheater.mariwheater.external.WheaterAPIService;
import br.com.mariwheater.mariwheater.model.City;
import br.com.mariwheater.mariwheater.model.Notifications;
import br.com.mariwheater.mariwheater.service.account.AccountService;
import br.com.mariwheater.mariwheater.service.city.CityService;
import br.com.mariwheater.mariwheater.service.mail.MailService;
import br.com.mariwheater.mariwheater.service.notifications.NotificationsService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduledService {

    private final WheaterAPIService wheaterAPIService;
    private final CityService cityService;
    private final NotificationsService notificationsService;

    private final MailService mailService;

    private final AccountService accountService;

    public ScheduledService (MailService mailService, WheaterAPIService wheaterAPIService, CityService cityService, NotificationsService notificationsService, AccountService accountService) {
        this.wheaterAPIService = wheaterAPIService;
        this.cityService = cityService;
        this.notificationsService = notificationsService;
        this.mailService = mailService;
        this.accountService = accountService;
    }
    //@Scheduled(fixedRate = 3600000) // -> One hour request
    //@Scheduled(cron = "0 0 6,18 * * *") //De 12 em 12 horas
    @Scheduled(fixedRate = 60000) //1 Minuto
    public void fetchDataAndSaveCities () {
        var serializableCities = wheaterAPIService.requestCities();
        cityService.deleteLastHourRecords();
        cityService.resetAutoIncrement();
        List<CityData> cities = wheaterAPIService.cityDataUnmarshalling(serializableCities);
        for (CityData data : cities) {
            cityService.save(new City(data));
        }
    }


    //@Scheduled(cron = "0 0 7,19 * * *") //De 12 em 12 horas
    @Scheduled(fixedRate = 65000) //1 Minuto
    //@Scheduled(fixedRate = 3600000) // -> One hour request
    public void checkWeatherAndNotify() {
        List<City> dangerousCities = cityService.getAllCitiesWithTemperatureIsDangerous();
        notificationsService.createAllNotifications(dangerousCities);
    }

//    //@Scheduled(cron = "0 20 7,19 * * *")//De 12 em 12 horas
//    @Scheduled(fixedRate = 120000)
//    public void sendNotificationMail() {
//        var notifitionsList = notificationsService.getAllNotifications();
//        for (Notifications n : notifitionsList) {
//            mailService.sendSimpleEmail("robsonmoura970@gmail.com", "Alerta de temperatura", n.getMessage());
//        }
//    }
}
