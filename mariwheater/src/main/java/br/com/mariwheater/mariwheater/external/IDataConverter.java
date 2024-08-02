package br.com.mariwheater.mariwheater.external;

public interface IDataConverter {

    <T> T obterDados (String json, Class<T> tClass);
}
