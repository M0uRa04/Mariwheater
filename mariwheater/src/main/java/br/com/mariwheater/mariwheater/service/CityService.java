package br.com.mariwheater.mariwheater.service;

import br.com.mariwheater.mariwheater.DTO.CityData;
import br.com.mariwheater.mariwheater.external.WheaterAPIService;
import br.com.mariwheater.mariwheater.model.City;
import br.com.mariwheater.mariwheater.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {
    @Autowired
    private WheaterAPIService externalService;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void save (City city) {
        cityRepository.save(city);
    }

    //Substituir por saveAllFuturamente
    public void saveAllCities (List<City> cities) {
        for (City city : cities) {
            save(new City());
        }
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
        String sql = "ALTER TABLE City AUTO_INCREMENT = 1";
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
}
