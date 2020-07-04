package br.com.fayoub.scheduler.domain.model.tax;

import java.math.BigDecimal;

public class TaxB implements Tax {

    @Override
    public BigDecimal calculate(BigDecimal value, long daysBetween) {
        return BigDecimal.valueOf(daysBetween).multiply(BigDecimal.valueOf(12));
    }

}
