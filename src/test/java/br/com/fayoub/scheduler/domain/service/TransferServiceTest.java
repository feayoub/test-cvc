package br.com.fayoub.scheduler.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.fayoub.scheduler.domain.factory.TaxFactory;
import br.com.fayoub.scheduler.domain.model.Transfer;
import br.com.fayoub.scheduler.domain.model.Transfer.TransferType;
import br.com.fayoub.scheduler.domain.repository.TransferRepository;

@ExtendWith(MockitoExtension.class)
public class TransferServiceTest {

    @InjectMocks
    TransferService service;

    @Mock
    TransferRepository repository;

    TaxFactory factory;

    Transfer expectedTransfer;

    @BeforeEach
    public void setup() {
        expectedTransfer = createTransfer();
        expectedTransfer.setSchedulingDate(LocalDate.now());
        expectedTransfer.setType(TransferType.A);
        expectedTransfer.setTax(BigDecimal.valueOf(3.03));
    }

    @Test
    public void testGetAll() {
        when(repository.findAll()).thenReturn(List.of(expectedTransfer));

        List<Transfer> transfers = service.getAll();

        assertEquals(List.of(expectedTransfer), transfers);
    }

    @Test
    public void testCreate() {
        when(repository.save(any())).thenReturn(expectedTransfer);

        Transfer transfer = service.create(expectedTransfer);

        assertEquals(transfer, expectedTransfer);
    }

    @Test
    public void testSaveOrUpdate() {
        when(repository.save(any())).thenReturn(expectedTransfer);

        Transfer transfer = service.saveOrUpdate(expectedTransfer);

        assertEquals(transfer, expectedTransfer);
    }

    @Test
    public void testGet() {
        when(repository.findById(any())).thenReturn(Optional.of(expectedTransfer));

        Optional<Transfer> optionalTransfer = service.get(1L);

        assertTrue(optionalTransfer.isPresent());
        assertEquals(optionalTransfer.get(), expectedTransfer);
        verify(repository, times(1)).findById(any());
    }

    @Test
    public void testDelete() {
        doNothing().when(repository).deleteById(any());
        service.delete(expectedTransfer);
        verify(repository, times(1)).deleteById(any());
    }

    private Transfer createTransfer() {
        Transfer transfer = new Transfer();
        transfer.setId(1L);
        transfer.setSourceAccount(123456);
        transfer.setDestinationAccount(123456);
        transfer.setValue(BigDecimal.valueOf(100));
        transfer.setTransferDate(LocalDate.now());
        transfer.setSchedulingDate(LocalDate.now());

        return transfer;
    }

}
