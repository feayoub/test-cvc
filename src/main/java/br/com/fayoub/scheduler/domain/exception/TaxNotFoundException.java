package br.com.fayoub.scheduler.domain.exception;

public class TaxNotFoundException extends RuntimeException {

    public TaxNotFoundException(String message) {
        super(message);
    }
}
