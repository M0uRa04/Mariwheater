package br.com.mariwheater.mariwheater.infra.CustomExceptions;

public class ProcessamentoDeDadosJsonException extends RuntimeException {

    public ProcessamentoDeDadosJsonException (String message) {
        super(message);
    }

    public ProcessamentoDeDadosJsonException (String message, Throwable cause) {
        super(message, cause);
    }

    public ProcessamentoDeDadosJsonException (Throwable cause) {
        super(cause);
    }
}
