package br.com.mariwheater.mariwheater.model;

import br.com.mariwheater.mariwheater.DTO.CityData;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(precision = 4, scale = 2)
    private BigDecimal temperature;

    @Column(name = "last_Updated")
    private LocalDateTime lastUpdated;

    @OneToMany(mappedBy = "city", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.EAGER)
    private List<Notifications> notificationsList;

    public City(CityData cityData) {
        this.name = cityData.name();
        this.temperature = BigDecimal.valueOf(cityData.temperature());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.lastUpdated = LocalDateTime.parse(cityData.lastUpdated(), formatter);
    }

    public void addNotification(Notifications notification) {
        this.notificationsList.add(notification);
        notification.setCity(this);
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", temperature=" + temperature +
                ", lastUpdated=" + lastUpdated +
                ", notificationsList=" + notificationsList +
                '}';
    }
}
