package br.com.fayoub.scheduler.domain.strategy.tax;

import br.com.fayoub.scheduler.domain.model.Transfer.TransferType;

public abstract class AbstractTax implements Tax {
    
    protected TransferType transferType;
    
    @Override
    public TransferType getTransferType() {
        return this.transferType;
    }
}
