package br.com.fayoub.scheduler.exceptionhandler;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import br.com.fayoub.scheduler.domain.exception.TaxNotFoundException;
import br.com.fayoub.scheduler.domain.exception.TransferNotFoundException;

/**
 * Exception handler of the application.
 * <p>
 * Captures the exception and shows it to the user through the error view
 */
@ControllerAdvice
public class TransferExceptionHandler {
    
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @ExceptionHandler(TaxNotFoundException.class)
    public ModelAndView handleTaxNotFound(TaxNotFoundException ex, HttpServletRequest req) {
        return generateBadRequest(ex, req);
    }
    
    @ExceptionHandler(TransferNotFoundException.class)
    public ModelAndView handleTransferNotFound(TransferNotFoundException ex, HttpServletRequest req) {
        return generateBadRequest(ex, req);
    }
    
    private ModelAndView generateBadRequest(RuntimeException ex, HttpServletRequest req) {
        ModelAndView mav = new ModelAndView("error");
        mav.addObject("path", req.getRequestURI());
        mav.addObject("timestamp", formatter.format(OffsetDateTime.now()));
        mav.addObject("error", "Bad Request");
        mav.addObject("status", HttpStatus.BAD_REQUEST.value());
        mav.addObject("message", ex.getMessage());
        mav.addObject("exception", "");
        return mav;
    }
}
