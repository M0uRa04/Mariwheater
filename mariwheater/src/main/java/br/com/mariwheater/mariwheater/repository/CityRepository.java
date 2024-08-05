package br.com.mariwheater.mariwheater.repository;


import br.com.mariwheater.mariwheater.model.City;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface CityRepository extends JpaRepository <City, Long> {

    @Transactional
    @Modifying
    @Query(
            "DELETE FROM City c WHERE " +
                    "c.lastUpdated <= c.lastUpdated"
    )
    void deleteAllByLastUpdatedBefore();

    @Query(
            "SELECT COUNT(c) FROM City c"
    )
    Long countAllCities();
}
