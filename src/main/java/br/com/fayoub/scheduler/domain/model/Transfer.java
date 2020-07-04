package br.com.fayoub.scheduler.domain.model;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.fayoub.scheduler.domain.model.tax.Tax;
import br.com.fayoub.scheduler.domain.model.tax.TaxA;
import br.com.fayoub.scheduler.domain.model.tax.TaxB;
import br.com.fayoub.scheduler.domain.model.tax.TaxC;

@Entity
@Table(name = "transfer")
public class Transfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String sourceAccount;
    private String destinationAccount;
    @Column(name = "transfer_value")
    private BigDecimal value;
    private BigDecimal tax;
    @Column(name = "transfer_type")
    private TransferType type;
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
    
    public enum TransferType {
        A(new TaxA()),
        B(new TaxB()),
        C(new TaxC());
        
        private Tax tax;
        
        TransferType(Tax tax) {
            this.tax = tax;
        }
        
        public BigDecimal calculate(Transfer transfer) {
            return this.tax.calculate(
                    transfer.getValue(), 
                    Duration.between(transfer.getSchedulingDate().atStartOfDay(), transfer.getTransferDate().atStartOfDay()).toDays());
            
        }
    }
}
