package br.com.fayoub.scheduler.domain.strategy.tax;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import br.com.fayoub.scheduler.domain.model.Transfer;

public class TaxTest {

    private Tax tax;
    private Transfer transfer;

    @Test
    public void testTaxA() {
        BigDecimal value = BigDecimal.valueOf(100.0);

        tax = new TaxA();
        transfer = new Transfer();
        transfer.setValue(value);

        assertEquals(value.multiply(BigDecimal.valueOf(0.03)).add(BigDecimal.valueOf(3.0)), tax.calculate(transfer));
    }

    @Test
    public void testTaxB() {
        LocalDate schedulingDate = LocalDate.now();
        LocalDate transferDate = LocalDate.now().plusDays(5);
        long daysBetween = Duration.between(schedulingDate.atStartOfDay(), transferDate.atStartOfDay()).toDays();

        tax = new TaxB();
        transfer = new Transfer();
        transfer.setSchedulingDate(schedulingDate);
        transfer.setTransferDate(transferDate);

        assertEquals(BigDecimal.valueOf(12).multiply(BigDecimal.valueOf(daysBetween)), tax.calculate(transfer));
    }

    @Test
    public void testTaxC() {
        LocalDate schedulingDate = LocalDate.now();
        LocalDate transferDate = LocalDate.now().plusDays(11);
        BigDecimal value = BigDecimal.valueOf(100.0);
        BigDecimal taxMultiplier = BigDecimal.valueOf(0.08);

        tax = new TaxC();
        transfer = new Transfer();
        transfer.setValue(value);

        for (int i = 0; i < 4; i++) {
            transfer.setSchedulingDate(schedulingDate);
            transfer.setTransferDate(transferDate);

            assertEquals(value.multiply(taxMultiplier), tax.calculate(transfer));
            
            transferDate.plusDays(10);
            taxMultiplier.multiply(BigDecimal.valueOf(0.75));
        }
    }
}
