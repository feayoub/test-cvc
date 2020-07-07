package br.com.fayoub.scheduler.domain.exception;

/***
 * Exception when cannot find a transfer
 */
public class TransferNotFoundException extends RuntimeException {

    public TransferNotFoundException(String message) {
        super(message);
    }
}