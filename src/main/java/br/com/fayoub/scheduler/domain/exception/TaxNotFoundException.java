package br.com.fayoub.scheduler.domain.exception;

/***
 * Exception when there is no applicable tax.
 */
public class TaxNotFoundException extends RuntimeException {

    public TaxNotFoundException(String message) {
        super(message);
    }
}
