package br.com.mariwheater.mariwheater.model;

import br.com.mariwheater.mariwheater.DTO.CityData;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(of = "id")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Float temperature;

    @Column(name = "last_Updated")
    private LocalDateTime lastUpdated;

    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Notifications> notificationsList;

    public City(CityData cityData) {
        this.name = cityData.name();
        this.temperature = cityData.temperature();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.lastUpdated = LocalDateTime.parse(cityData.lastUpdated(), formatter);
    }
}
