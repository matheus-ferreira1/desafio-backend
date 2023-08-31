package br.com.matheusferreira.picpaysimplificado.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.matheusferreira.picpaysimplificado.entities.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    
}
