package br.com.fayoub.scheduler.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import br.com.fayoub.scheduler.domain.model.Transfer.TransferType;

public class TransferDTO {

    private long id;
    
    @Size(min = 6, max = 6, message = "The source account '${validatedValue}' must be {max} characters long")
    @Pattern(regexp = "\\d+", message = "The source account must be numeric")
    private String sourceAccount;
    
    @Size(min = 6, max = 6, message = "The destination account '${validatedValue}' must be {max} characters long")
    @Pattern(regexp = "\\d+", message = "The destination account must be numeric")
    private String destinationAccount;
    
    @DecimalMin(value = "0.01", message = "Value '${validatedValue}' must be at least {value}")
    private BigDecimal value;
    
    private BigDecimal tax;
    
    private TransferType type;
    
    @FutureOrPresent(message = "Transfer date '${validatedValue}' must be present or future")
    private LocalDate transferDate;
    
    private LocalDate schedulingDate;
    
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getSourceAccount() {
        return sourceAccount;
    }
    public void setSourceAccount(String sourceAccount) {
        this.sourceAccount = sourceAccount;
    }
    public String getDestinationAccount() {
        return destinationAccount;
    }
    public void setDestinationAccount(String destinationAccount) {
        this.destinationAccount = destinationAccount;
    }
    public BigDecimal getValue() {
        return value;
    }
    public void setValue(BigDecimal value) {
        this.value = value;
    }
    public BigDecimal getTax() {
        return tax;
    }
    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }
    public TransferType getType() {
        return type;
    }
    public void setType(TransferType type) {
        this.type = type;
    }
    public LocalDate getTransferDate() {
        return transferDate;
    }
    public void setTransferDate(LocalDate transferDate) {
        this.transferDate = transferDate;
    }
    public LocalDate getSchedulingDate() {
        return schedulingDate;
    }
    public void setSchedulingDate(LocalDate schedulingDate) {
        this.schedulingDate = schedulingDate;
    }
    
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
    
    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }
}
