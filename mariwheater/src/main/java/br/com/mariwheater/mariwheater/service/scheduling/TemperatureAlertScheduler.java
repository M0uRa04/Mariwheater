package br.com.mariwheater.mariwheater.service.scheduling;

import br.com.mariwheater.mariwheater.model.City;
import br.com.mariwheater.mariwheater.model.Notifications;
import br.com.mariwheater.mariwheater.service.account.AccountService;
import br.com.mariwheater.mariwheater.service.city.CityService;
import br.com.mariwheater.mariwheater.service.mail.MailService;
import br.com.mariwheater.mariwheater.service.notifications.NotificationsService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TemperatureAlertScheduler {

    private final NotificationsService notificationsService;

    private final MailService mailService;

    private final CityService cityService;

    private final AccountService accountService;

    public TemperatureAlertScheduler(NotificationsService notificationsService, MailService mailService, CityService cityService, AccountService accountService) {
        this.notificationsService = notificationsService;
        this.mailService = mailService;
        this.cityService = cityService;
        this.accountService = accountService;
    }

    public void sendNotificationMail () {
        var notificationsList = notificationsService.getAllNotifications();
        var citiesList = getCitiesFromNotifications(notificationsList);
        var accountMap = getAccountMapByCities(citiesList);

        sendEmails(accountMap, notificationsList);
    }

    private List<City> getCitiesFromNotifications (List<Notifications> notificationsList){
        return notificationsList.stream()
                .map(Notifications::getCity)
                .distinct().toList();
    }

    private Map<String, String> getAccountMapByCities(List<City> citiesList) {
        Map<String, String> accountMap = new HashMap<>();
        for (City city : citiesList) {
            var accounts = accountService.getAccountsByCityName(city.getName());
            if (!accounts.isEmpty()) {
                accounts.forEach(account -> accountMap.put(account.getEmail(), account.getCityName()));
            }
        }
        return accountMap;
    }

    private void sendEmails (Map<String, String> accountMap, List<Notifications> notificationsList) {
        accountMap.forEach((email, cityName) -> {
            notificationsList.stream()
                    .filter(notification -> notification.getCity().getName().equalsIgnoreCase(cityName))
                    .findFirst()
                    .ifPresent(notification -> mailService.sendSimpleEmail(email, "Mariwheater API: Alerta de temperatura", notification.getMessage()));
        });
    }

}
