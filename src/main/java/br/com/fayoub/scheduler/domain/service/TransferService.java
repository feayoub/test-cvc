package br.com.fayoub.scheduler.domain.service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fayoub.scheduler.domain.factory.TransferTypeFactory;
import br.com.fayoub.scheduler.domain.model.Transfer;
import br.com.fayoub.scheduler.domain.model.Transfer.TransferType;
import br.com.fayoub.scheduler.domain.repository.TransferRepository;

@Service
public class TransferService{
    
    @Autowired
    private TransferRepository repository;

    public List<Transfer> getAll() {
        return repository.findAll();
    }

    public Transfer create(Transfer transfer) {
        transfer.setSchedulingDate(OffsetDateTime.now().toLocalDate());
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
    
    private void resolveTaxAndType(Transfer transfer) {
        TransferType type = TransferTypeFactory.resolveType(transfer);
        
        transfer.setTax(type.calculate(transfer));
        transfer.setType(type);
    }

}
