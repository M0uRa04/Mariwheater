package br.com.mariwheater.mariwheater.service.scheduling;

import br.com.mariwheater.mariwheater.model.City;
import br.com.mariwheater.mariwheater.service.account.AccountService;
import br.com.mariwheater.mariwheater.service.city.CityService;
import br.com.mariwheater.mariwheater.service.mail.MailService;
import br.com.mariwheater.mariwheater.service.notifications.NotificationsService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationScheduler {

    private final NotificationsService notificationsService;

    private final MailService mailService;

    private final AccountService accountService;

    private final CityService cityService;

    public NotificationScheduler(NotificationsService notificationsService, MailService mailService, AccountService accountService, CityService cityService) {
        this.notificationsService = notificationsService;
        this.mailService = mailService;
        this.accountService = accountService;
        this.cityService = cityService;
    }

    public void constructNotifications() {
        List<City> dangerousCities = cityService.getAllCitiesWithTemperatureIsDangerous();
        notificationsService.createAllNotifications(dangerousCities);
    }

}
