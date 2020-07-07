package br.com.fayoub.scheduler.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Entity saved in the database
 */
@Entity
@Table(name = "transfer")
public class Transfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int sourceAccount;
    private int destinationAccount;
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
    public int getSourceAccount() {
        return sourceAccount;
    }
    public void setSourceAccount(int sourceAccount) {
        this.sourceAccount = sourceAccount;
    }
    public int getDestinationAccount() {
        return destinationAccount;
    }
    public void setDestinationAccount(int destinationAccount) {
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
    
    public enum TransferType {
        A,
        B,
        C;
    }
}
