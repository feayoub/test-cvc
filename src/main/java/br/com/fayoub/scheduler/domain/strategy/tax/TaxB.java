package br.com.fayoub.scheduler.domain.strategy.tax;

import java.math.BigDecimal;
import java.time.Duration;

import br.com.fayoub.scheduler.domain.model.Transfer;
import br.com.fayoub.scheduler.domain.model.Transfer.TransferType;

/**
 * Implementation B of {@link Tax} interface
 */
public class TaxB extends AbstractTax {
    
    public TaxB() {
        this.transferType = TransferType.B;
    }
    
    @Override
    public BigDecimal calculate(Transfer transfer) {
        long daysBetween = Duration.between(transfer.getSchedulingDate().atStartOfDay(), transfer.getTransferDate().atStartOfDay()).toDays();
        return BigDecimal.valueOf(daysBetween).multiply(BigDecimal.valueOf(12));
    }

}
