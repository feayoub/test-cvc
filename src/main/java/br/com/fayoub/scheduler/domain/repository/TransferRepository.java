package br.com.fayoub.scheduler.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fayoub.scheduler.domain.model.Transfer;

/**
 * Repository of the Application
 */
@Repository
public interface TransferRepository extends JpaRepository<Transfer, Long>{

}