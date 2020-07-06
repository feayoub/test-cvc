package br.com.fayoub.scheduler.domain.factory;

import java.math.BigDecimal;
import java.time.Duration;

import br.com.fayoub.scheduler.domain.exception.TaxNotFoundException;
import br.com.fayoub.scheduler.domain.model.Transfer;
import br.com.fayoub.scheduler.domain.model.Transfer.TransferType;

public class TransferTypeFactory {

    public static TransferType resolveType(Transfer transfer) {
        long daysThreshold = 10;
        long maxDaysThreshold = 40;
        BigDecimal valueThreshold = BigDecimal.valueOf(100000.0);
        
        long daysBetween = Duration.between(transfer.getSchedulingDate().atStartOfDay(), transfer.getTransferDate().atStartOfDay()).toDays();
        BigDecimal value = transfer.getValue();
        
        if (daysBetween == 0)
            return TransferType.A;
        if (daysBetween <= daysThreshold)
            return TransferType.B;
        if (daysBetween <= maxDaysThreshold || 
                (daysBetween > maxDaysThreshold && value.compareTo(valueThreshold) == 1))
            return TransferType.C;
        
        throw new TaxNotFoundException("Tax not found for input values");
    }
}
