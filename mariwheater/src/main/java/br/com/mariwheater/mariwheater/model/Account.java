package br.com.mariwheater.mariwheater.model;

import br.com.mariwheater.mariwheater.dto.DataAccount;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
public class Account {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    @Column(name = "CITY_NAME")
    private String cityName;

    public Account (DataAccount dataAccount) {
        this.name = dataAccount.name();
        this.email = dataAccount.email();
        this.cityName = dataAccount.cityName();
    }
}
