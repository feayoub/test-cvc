package br.com.fayoub.scheduler.domain.strategy.tax;

import java.math.BigDecimal;

import br.com.fayoub.scheduler.domain.model.Transfer;
import br.com.fayoub.scheduler.domain.model.Transfer.TransferType;

public class TaxA extends AbstractTax {
    
    public TaxA() {
        this.transferType = TransferType.A;
    }

    @Override
    public BigDecimal calculate(Transfer transfer) {
        BigDecimal value = transfer.getValue();
        return BigDecimal.valueOf(3).add(value.multiply(BigDecimal.valueOf(0.03)));
    }

}
