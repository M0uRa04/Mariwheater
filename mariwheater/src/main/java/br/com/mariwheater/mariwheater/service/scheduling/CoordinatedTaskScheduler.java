package br.com.mariwheater.mariwheater.service.scheduling;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class CoordinatedTaskScheduler {

    @Autowired
    DataFetchScheduler dataFetchScheduler;

    @Autowired
    NotificationScheduler notificationScheduler;

    @Autowired
    TemperatureAlertScheduler temperatureAlertScheduler;

    //@Scheduled(fixedRate = 3600000) // -> One hour request
    //@Scheduled(cron = "0 0 6,18 * * *") //De 12 em 12 horas
    @Scheduled(fixedRate = 60000) //1 Minuto
    public void fetchData () {
        dataFetchScheduler.fetchDataAndSaveCities();
    }

    //@Scheduled(cron = "0 0 7,19 * * *") //De 12 em 12 horas
    @Scheduled(fixedRate = 80000) //1 Minuto
    //@Scheduled(fixedRate = 3600000) // -> One hour request
    public void createNotifications () {
        notificationScheduler.constructNotifications();
    }

    //@Scheduled(fixedRate = 100000)
    public void alertTemperature () {
        temperatureAlertScheduler.sendNotificationMail();
    }
}
