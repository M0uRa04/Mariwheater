package br.com.mariwheater.mariwheater.service.city;

import br.com.mariwheater.mariwheater.dto.CityData;
import br.com.mariwheater.mariwheater.external.WheaterAPIService;
import br.com.mariwheater.mariwheater.model.City;
import br.com.mariwheater.mariwheater.repository.CityRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityService {
    @Autowired
    private WheaterAPIService externalService;

    @Autowired
    private CityRepository cityRepository;

    @PersistenceContext
    private EntityManager em;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Deprecated
    public void save (City city) {
        cityRepository.save(city);
    }

    @Transactional
    public void saveAllCities (List<CityData> cities) {
        cityRepository.saveAll(cities.stream().map(City::new).collect(Collectors.toList()));
        em.flush();
        em.clear();
    }

    public void deleteLastHourRecords () {
        if(areThereAnyCities()) {
            cityRepository.deleteAll();
        }
    }

    public boolean areThereAnyCities() {
        return cityRepository.countAllCities() > 0;
    }

    public void resetAutoIncrement() {
        String sql = "ALTER TABLE City AUTO_INCREMENT = 1"; //MySQL
        //String sql = "ALTER TABLE City ALTER COLUMN id RESTART WITH 1"; //H2
        jdbcTemplate.execute(sql);
    }

    public List<City> getAllCitiesWithTemperatureIsDangerous () {
        return cityRepository.getAllCitiesWithTemperatureIsDangerous();
    }

    public List<City> convertCityData (List<CityData> cityData) {
        return cityData.stream()
                .map(City::new)
                .toList();
    }

    public City getCityById (Long cityId) {
        try {
            return cityRepository.getReferenceById(cityId);
        } catch (EntityNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }
}
