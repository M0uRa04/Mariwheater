package br.com.mariwheater.mariwheater.repository;

import br.com.mariwheater.mariwheater.model.Notifications;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationsRepository extends JpaRepository<Notifications, Long> {
}
