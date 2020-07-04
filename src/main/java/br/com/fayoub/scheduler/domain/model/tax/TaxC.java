package br.com.fayoub.scheduler.domain.model.tax;

import java.math.BigDecimal;

public class TaxC implements Tax {

    @Override
    public BigDecimal calculate(BigDecimal value, long daysBetween) {
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
