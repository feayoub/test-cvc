package br.com.fayoub.scheduler.domain.model.tax;

import java.math.BigDecimal;

public interface Tax {
    BigDecimal calculate(BigDecimal value, long daysBetween);
}
