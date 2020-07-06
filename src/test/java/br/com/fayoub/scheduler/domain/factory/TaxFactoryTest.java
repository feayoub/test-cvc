package br.com.fayoub.scheduler.domain.factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.fayoub.scheduler.domain.exception.TaxNotFoundException;
import br.com.fayoub.scheduler.domain.model.Transfer;
import br.com.fayoub.scheduler.domain.model.Transfer.TransferType;
import br.com.fayoub.scheduler.domain.strategy.tax.Tax;
import br.com.fayoub.scheduler.domain.strategy.tax.TaxA;
import br.com.fayoub.scheduler.domain.strategy.tax.TaxB;
import br.com.fayoub.scheduler.domain.strategy.tax.TaxC;

public class TaxFactoryTest {

    private TaxFactory factory;
    
    private Transfer transfer;
    
    @BeforeEach
    public void setup() {
        transfer = new Transfer();
        factory = new TaxFactory();
    }
    
    @Test
    public void testCreateTaxA() throws Exception {
        transfer.setSchedulingDate(LocalDate.now());
        transfer.setTransferDate(LocalDate.now());
        
        Tax tax = factory.create(transfer);

        assertEquals(tax.getClass(), TaxA.class);
        assertEquals(tax.getTransferType(), TransferType.A);
    }
    
    @Test
    public void testCreateTaxB() throws Exception {
        transfer.setSchedulingDate(LocalDate.now());
        transfer.setTransferDate(LocalDate.now().plusDays(5));
        
        Tax tax = factory.create(transfer);

        assertEquals(tax.getClass(), TaxB.class);
        assertEquals(tax.getTransferType(), TransferType.B);
    }
    
    @Test
    public void testCreateTaxC() throws Exception {
        transfer.setSchedulingDate(LocalDate.now());
        transfer.setTransferDate(LocalDate.now().plusDays(20));
        
        Tax tax = factory.create(transfer);

        assertEquals(tax.getClass(), TaxC.class);
        assertEquals(tax.getTransferType(), TransferType.C);
    }
    
    @Test
    public void testNoTaxFound() throws Exception {
        transfer.setSchedulingDate(LocalDate.now());
        transfer.setTransferDate(LocalDate.now().plusDays(50));
        transfer.setValue(BigDecimal.valueOf(100));
        
        assertThrows(TaxNotFoundException.class, () -> {factory.create(transfer);}, "Tax not found for input values");
    }
}
