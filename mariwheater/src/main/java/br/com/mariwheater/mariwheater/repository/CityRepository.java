package br.com.mariwheater.mariwheater.repository;


import br.com.mariwheater.mariwheater.model.City;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

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

//    REAL QUERY
//    @Query(
//            "SELECT from City c where c.temperature > 35.00 OR c.temperature < 9.00"
//    )

//        TEST QUERY
    @Query(
            "SELECT c from City c where c.temperature > 25.00 OR c.temperature < 13.00"
    )
    List<City> getAllCitiesWithTemperatureIsDangerous();
}
