package br.com.fayoub.scheduler.domain.model.tax;

import java.math.BigDecimal;

public class TaxA implements Tax {

    @Override
    public BigDecimal calculate(BigDecimal value, long daysBetween) {
        return BigDecimal.valueOf(3).add(value.multiply(BigDecimal.valueOf(0.03)));
    }

}
