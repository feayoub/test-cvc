package br.com.fayoub.scheduler.domain.strategy.tax;

import java.math.BigDecimal;

import br.com.fayoub.scheduler.domain.model.Transfer;
import br.com.fayoub.scheduler.domain.model.Transfer.TransferType;

/**
 * Responsible for calculating the value of the tax applied to a transfer 
 * following the strategy design-pattern
 */
public interface Tax {
    /**
     * 
     * @param transfer
     * @return value of the tax
     */
    BigDecimal calculate(Transfer transfer);
    
    /**
     * 
     * @return the type of the transfer based on its tax applied
     */
    TransferType getTransferType();
}
