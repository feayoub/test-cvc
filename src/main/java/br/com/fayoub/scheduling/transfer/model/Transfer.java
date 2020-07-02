package br.com.fayoub.scheduling.transfer.model;

import java.time.LocalDate;

import br.com.fayoub.scheduling.transfer.model.enumerator.TransferType;

public class Transfer {

    private final String sourceAccount;
    private final String destinationAccount;
    private final Double value;
    private final Double tax;
    private final TransferType type;
    private final LocalDate transferDate;
    private final LocalDate schedulingDate;

    private Transfer(Builder builder) {
        this.sourceAccount = builder.sourceAccount;
        this.destinationAccount = builder.destinationAccount;
        this.value = builder.value;
        this.tax = builder.tax;
        this.type = builder.type;
        this.transferDate = builder.transferDate;
        this.schedulingDate = builder.schedulingDate;
    }

    public String getSourceAccount() {
        return sourceAccount;
    }

    public String getDestinationAccount() {
        return destinationAccount;
    }

    public Double getValue() {
        return value;
    }

    public Double getTax() {
        return tax;
    }

    public TransferType getType() {
        return type;
    }

    public LocalDate getTransferDate() {
        return transferDate;
    }

    public LocalDate getSchedulingDate() {
        return schedulingDate;
    }

    public static class Builder {
        private String sourceAccount;
        private String destinationAccount;
        private Double value;
        private Double tax;
        private TransferType type;
        private LocalDate transferDate;
        private LocalDate schedulingDate;

        private Builder() {
        }

        public static Builder create() {
            return new Builder();
        }

        public Builder withSourceAccount(String sourceAccount) {
            this.sourceAccount = sourceAccount;
            return this;
        }

        public Builder withDestinationAccount(String destinationAccount) {
            this.destinationAccount = destinationAccount;
            return this;
        }

        public Builder withValue(Double value) {
            this.value = value;
            return this;
        }

        public Builder withTax(Double tax) {
            this.tax = tax;
            return this;
        }

        public Builder withType(TransferType type) {
            this.type = type;
            return this;
        }

        public Builder withTransferDate(LocalDate transferDate) {
            this.transferDate = transferDate;
            return this;
        }

        public Builder withSchedulingDate(LocalDate schedulingDate) {
            this.schedulingDate = schedulingDate;
            return this;
        }
        
        public Transfer build() {
            return new Transfer(this);
        }
    }
}