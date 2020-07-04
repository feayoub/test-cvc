package br.com.fayoub.scheduler.exceptionhandler;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import br.com.fayoub.scheduler.domain.exception.TaxNotFoundException;
import br.com.fayoub.scheduler.domain.exception.TransferNotFoundException;

@ControllerAdvice
public class TransferExceptionHandler {
    
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @ExceptionHandler(TaxNotFoundException.class)
    public ModelAndView handleTaxNotFound(TaxNotFoundException ex, WebRequest web) {
        ModelAndView mav = new ModelAndView("error");
        mav.addObject("path", "/transfers/schedule");
        return generateBadRequest(mav, ex);
    }
    
    @ExceptionHandler(TransferNotFoundException.class)
    public ModelAndView handleTaxNotFound(TransferNotFoundException ex, WebRequest web) {
        ModelAndView mav = new ModelAndView("error");
        mav.addObject("path", "/transfers/edit/{id}");
        return generateBadRequest(mav, ex);
        
    }
    
    private ModelAndView generateBadRequest(ModelAndView mav, RuntimeException ex) {
        mav.addObject("timestamp", formatter.format(OffsetDateTime.now()));
        mav.addObject("error", "Bad Request");
        mav.addObject("status", HttpStatus.BAD_REQUEST.value());
        mav.addObject("message", ex.getMessage());
        mav.addObject("exception", "");
        return mav;
    }
}
