package br.com.mariwheater.mariwheater.external;

import br.com.mariwheater.mariwheater.dto.CityData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WheaterAPIService {

    @Autowired
    private RestTemplate restTemplate;
    private static final String API_URL = "http://api.weatherapi.com/v1/current.json?q=";
    @Value("${API_KEY_WeaterAPI}")
    private String API_KEY;

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

    public List<String> requestCities () {
        var listOfResponses = new ArrayList<String>();
        for (String city : CITIES) {
            try {
                String url = constructURL(city);
                ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

                if (response.getStatusCode().is2xxSuccessful()) {
                    String json = response.getBody();
                    listOfResponses.add(json);
                } else  {
                    throw new RuntimeException("Não foi possível obter os dados da cidade");
                }
            } catch (HttpClientErrorException e) {
                throw new RuntimeException("Erro do cliente ao solicitar dados para a cidade: " + city + ". Código de status: " + e.getStatusCode(), e);
            } catch (HttpServerErrorException e) {
                throw new RuntimeException("Erro do servidor ao solicitar dados para a cidade: " + city + ". Código de status: " + e.getStatusCode(), e);
            } catch (ResourceAccessException e) {
                throw new RuntimeException("Erro ao acessar o recurso para a cidade: " + city + ". Causa: " + e.getMessage(), e);
            } catch (RestClientException e) {

                throw new RuntimeException("Ocorreu um erro ao recuperar dados para a cidade: " + city, e);
            }

        }
        return listOfResponses;
    }

    public List<CityData> cityDataUnmarshalling (List<String> listOfResponses) {
        return listOfResponses.stream()
                .map(response -> converter.obterDados(response, DataWheaterResponse.class))
                .map(CityData::new)
                .collect(Collectors.toList());
    }
}
