package br.com.matheusferreira.picpaysimplificado.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.matheusferreira.picpaysimplificado.dtos.UserDTO;
import br.com.matheusferreira.picpaysimplificado.entities.User;
import br.com.matheusferreira.picpaysimplificado.entities.UserType;
import br.com.matheusferreira.picpaysimplificado.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void validateTransaction(User sender, BigDecimal amount) throws Exception {

        if(sender.getUserType() == UserType.MERCHANT) {
            throw new Exception("Merchant users cannot send transaction");
        }

        if(sender.getBalance().compareTo(amount) < 0) {
            throw new Exception("Insufficient balance");
        }

    }

    public User findUserById(Long id) throws Exception {
        return this.userRepository.findUserById(id).orElseThrow(() -> new Exception("User not found"));
    }

    public User createUser(UserDTO data) {
        User newUser = new User(data);
        this.saveUser(newUser);
        return newUser;
    }

    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    public void saveUser(User user) {
        this.userRepository.save(user);
    }
    
}
