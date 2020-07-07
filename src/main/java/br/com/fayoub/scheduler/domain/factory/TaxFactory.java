package br.com.fayoub.scheduler.domain.factory;

import java.math.BigDecimal;
import java.time.Duration;

import br.com.fayoub.scheduler.domain.exception.TaxNotFoundException;
import br.com.fayoub.scheduler.domain.model.Transfer;
import br.com.fayoub.scheduler.domain.strategy.tax.Tax;
import br.com.fayoub.scheduler.domain.strategy.tax.TaxA;
import br.com.fayoub.scheduler.domain.strategy.tax.TaxB;
import br.com.fayoub.scheduler.domain.strategy.tax.TaxC;

/***
 * Factory responsible for instantiate the right Tax class given a Transfer
 */
public class TaxFactory {

    /***
     * 
     * @param transfer
     * @return possible taxes given the rules
     */
    public Tax create(Transfer transfer) {
        long daysThreshold = 10;
        long maxDaysThreshold = 40;
        BigDecimal valueThreshold = BigDecimal.valueOf(100000.0);
        
        long daysBetween = Duration.between(transfer.getSchedulingDate().atStartOfDay(), transfer.getTransferDate().atStartOfDay()).toDays();
        BigDecimal value = transfer.getValue();
        
        if (daysBetween == 0)
            return new TaxA();
        if (daysBetween <= daysThreshold)
            return new TaxB();
        if (daysBetween <= maxDaysThreshold || 
                (daysBetween > maxDaysThreshold && value.compareTo(valueThreshold) == 1))
            return new TaxC();
        
        throw new TaxNotFoundException("Tax not found for input values");
    }
}
