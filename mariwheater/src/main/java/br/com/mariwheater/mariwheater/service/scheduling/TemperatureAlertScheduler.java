package br.com.mariwheater.mariwheater.service.scheduling;

import br.com.mariwheater.mariwheater.model.Account;
import br.com.mariwheater.mariwheater.model.City;
import br.com.mariwheater.mariwheater.model.Notifications;
import br.com.mariwheater.mariwheater.service.account.AccountService;
import br.com.mariwheater.mariwheater.service.city.CityService;
import br.com.mariwheater.mariwheater.service.mail.MailService;
import br.com.mariwheater.mariwheater.service.notifications.NotificationsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    public void sendNotificationMail() {
        Map<String, String> accountMap = new HashMap<>();
        //Consultar todas as notificações criadas
        var notificationsList = notificationsService.getAllNotifications();

        //Consultar todas as cidades das notificações pelo ID
        var citiesList = cityService.getAllCitiesWithTemperatureIsDangerous();

        //Consultar todos os usuários pertencentes aquela cidade
        for (City c : citiesList) {
            var account = accountService.getAccountByCityName(c.getName());
            if (!account.isEmpty()) {
                account.forEach(a -> accountMap.put(a.getEmail(), a.getCityName()));
            }
        }
        System.out.println(accountMap);
        //Notificar os usuários
        accountMap.forEach((email, cityName) -> {
            for (Notifications n : notificationsList) {
                if (n.getCity().getName().equalsIgnoreCase(cityName)) {
                    mailService.sendSimpleEmail(email, "Alerta de Temperatura", n.getMessage());
                }
            }
        });

    }


}
