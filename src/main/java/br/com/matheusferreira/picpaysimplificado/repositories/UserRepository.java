package br.com.matheusferreira.picpaysimplificado.repositories;

import br.com.matheusferreira.picpaysimplificado.entities.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByDocument(String document);

    Optional<User> findUserById(Long id);
    
}
