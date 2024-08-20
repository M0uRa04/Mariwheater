package br.com.mariwheater.mariwheater.external;

import br.com.mariwheater.mariwheater.DTO.CityData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class WheaterAPIService {

    @Autowired
    private RestTemplate restTemplate;

    /*
    Exemplo de requisicão com a cidade de São Paulo
    http://api.weatherapi.com/v1/current.json?key=03ffc5e3218f40948a9140740243007&q=São Paulo&aqi=no
    */
    private static final String API_URL = "http://api.weatherapi.com/v1/current.json?q=";

    private static final String API_KEY = "03ffc5e3218f40948a9140740243007";

    private static final List<String> CITIES = Arrays.asList(
            "Brasilia", "Rio de Janeiro", "Sao Paulo", "Belo Horizonte", "Salvador",
            "Fortaleza", "Recife", "Manaus", "Belem", "Vitória", "Curitiba", "Porto Alegre",
            "Campo Grande", "Cuiaba", "Maceio", "Joao Pessoa", "Aracaju", "Natal",
            "Teresina", "Palmas", "Boa Vista", "Macapa"
    );


    private final DataConverter converter = new DataConverter();
    public String constructURL (String city) {
        var url = API_URL + city.replace(" ", "%20")
                + "&" + "lang=pt" + "&key=" + API_KEY;
        return url;
    }

    public List<CityData> fetchWeatherData() {
        var listOfCityData = new ArrayList<CityData>();
        for (String city : CITIES) {
            try {
                String url = constructURL(city);
                ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

                if (response.getStatusCode().is2xxSuccessful()) {
                    String json = response.getBody();
                    DataWheaterResponse wheaterSerializableObject = converter.obterDados(json, DataWheaterResponse.class);
                    //System.out.println(wheaterSerializableObject.toString());
                    listOfCityData.add(new CityData(wheaterSerializableObject));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //listOfCityData.forEach(System.out::println);
        return listOfCityData;
    }
}
