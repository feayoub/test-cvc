package br.com.fayoub.scheduler.domain.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fayoub.scheduler.domain.factory.TaxFactory;
import br.com.fayoub.scheduler.domain.model.Transfer;
import br.com.fayoub.scheduler.domain.repository.TransferRepository;
import br.com.fayoub.scheduler.domain.strategy.tax.Tax;

/**
 * Service layer for the application
 */
@Service
public class TransferService{
    
    @Autowired
    private TransferRepository repository;

    public List<Transfer> getAll() {
        return repository.findAll();
    }

    public Transfer create(Transfer transfer) {
        transfer.setSchedulingDate(LocalDate.now());
        return saveOrUpdate(transfer);
    }
    
    public Transfer saveOrUpdate(Transfer transfer) {
        resolveTaxAndType(transfer);
        return repository.save(transfer);
    }

    public Optional<Transfer> get(long id) {
        return repository.findById(id);
    }

    public void delete(Transfer transfer) {
        repository.deleteById(transfer.getId());
    }
    
    /***
     * Method responsible to set the right type and tax for the transfer
     * @param transfer
     */
    private void resolveTaxAndType(Transfer transfer) {
        Tax tax = new TaxFactory().create(transfer);
        
        transfer.setTax(tax.calculate(transfer));
        transfer.setType(tax.getTransferType());
    }

}
