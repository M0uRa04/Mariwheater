package br.com.mariwheater.mariwheater.service.scheduling;

import br.com.mariwheater.mariwheater.model.Notifications;
import br.com.mariwheater.mariwheater.service.account.AccountService;
import br.com.mariwheater.mariwheater.service.mail.MailService;
import br.com.mariwheater.mariwheater.service.notifications.NotificationsService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class TemperatureAlertScheduler {

    private final NotificationsService notificationsService;

    private final MailService mailService;

    private final AccountService accountService;

    public TemperatureAlertScheduler(NotificationsService notificationsService, MailService mailService, AccountService accountService) {
        this.notificationsService = notificationsService;
        this.mailService = mailService;
        this.accountService = accountService;
    }

    //@Scheduled(cron = "0 20 7,19 * * *")//De 12 em 12 horas
    @Scheduled(fixedRate = 120000)
    public void sendNotificationMail() {
        var notifitionsList = notificationsService.getAllNotifications();
        for (Notifications n : notifitionsList) {
            mailService.sendSimpleEmail("robsonmoura970@gmail.com", "Alerta de temperatura", n.getMessage());
        }
    }
}
