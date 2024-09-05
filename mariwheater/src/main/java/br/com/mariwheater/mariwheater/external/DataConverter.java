package br.com.mariwheater.mariwheater.external;

import br.com.mariwheater.mariwheater.infra.CustomExceptions.ProcessamentoDeDadosJsonException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataConverter implements IDataConverter{

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public <T> T obterDados(String json, Class<T> tClass) {
        try {
            return mapper.readValue(json, tClass);
        } catch (JsonProcessingException ex) {
            throw new ProcessamentoDeDadosJsonException("Erro ao processar o JSON.", ex);
        }
    }
}
