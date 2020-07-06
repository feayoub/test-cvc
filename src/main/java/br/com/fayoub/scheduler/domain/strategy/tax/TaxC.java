package br.com.fayoub.scheduler.domain.strategy.tax;

import java.math.BigDecimal;
import java.time.Duration;

import br.com.fayoub.scheduler.domain.model.Transfer;
import br.com.fayoub.scheduler.domain.model.Transfer.TransferType;

public class TaxC extends AbstractTax {
    
    public TaxC() {
        this.transferType = TransferType.C;
    }
    
    @Override
    public BigDecimal calculate(Transfer transfer) {
        BigDecimal value = transfer.getValue();
        long daysBetween = Duration.between(transfer.getSchedulingDate().atStartOfDay(), transfer.getTransferDate().atStartOfDay()).toDays();
        
        if (daysBetween > 10 && daysBetween <= 20)
            return value.multiply(BigDecimal.valueOf(0.08));
        if (daysBetween > 20 && daysBetween <= 30)
            return value.multiply(BigDecimal.valueOf(0.06));
        if (daysBetween > 30 && daysBetween <= 40)
            return value.multiply(BigDecimal.valueOf(0.04));
        if (daysBetween > 40 && value.compareTo(BigDecimal.valueOf(100000.0)) == 1)
            return value.multiply(BigDecimal.valueOf(0.02));
        
        return BigDecimal.ZERO;
    }

}
