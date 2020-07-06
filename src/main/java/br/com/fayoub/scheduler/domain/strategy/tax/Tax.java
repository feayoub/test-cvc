package br.com.fayoub.scheduler.domain.strategy.tax;

import java.math.BigDecimal;

import br.com.fayoub.scheduler.domain.model.Transfer;
import br.com.fayoub.scheduler.domain.model.Transfer.TransferType;

public interface Tax {
    BigDecimal calculate(Transfer transfer);
    TransferType getTransferType();
}
