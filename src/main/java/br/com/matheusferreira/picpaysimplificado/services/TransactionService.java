package br.com.matheusferreira.picpaysimplificado.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import br.com.matheusferreira.picpaysimplificado.dtos.TransactionDTO;
import br.com.matheusferreira.picpaysimplificado.entities.Transaction;
import br.com.matheusferreira.picpaysimplificado.entities.User;
import br.com.matheusferreira.picpaysimplificado.repositories.TransactionRepository;

@Service
public class TransactionService {

    @Autowired
    private UserService userService;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private RestTemplate restTemplate;

    public void createTransaction(TransactionDTO transaction) throws Exception {

        User sender = this.userService.findUserById(transaction.senderId());

        User receiver = this.userService.findUserById(transaction.receiverId());

        userService.validateTransaction(sender, transaction.value());

        boolean isAuthorized = this.authorizeTransaction(sender, transaction.value());

        if (!isAuthorized) {
            throw new Exception("Transaction not authorized");            
        }

        Transaction newTransaction = new Transaction();
        newTransaction.setAmount(transaction.value());
        newTransaction.setSender(sender);
        newTransaction.setReceiver(receiver);
        newTransaction.setTimeStamp(LocalDateTime.now());

        sender.setBalance(sender.getBalance().subtract(transaction.value()));
        receiver.setBalance(receiver.getBalance().add(transaction.value()));

        this.transactionRepository.save(newTransaction);
        this.userService.saveUser(sender);
        this.userService.saveUser(receiver);
        
    }

    public boolean authorizeTransaction(User sender, BigDecimal value) {

        ResponseEntity<Map> authorizationResponse = restTemplate.getForEntity("https://run.mocky.io/v3/8fafdd68-a090-496f-8c9a-3442cf30dae6", Map.class);

        if (authorizationResponse.getStatusCode() == HttpStatus.OK && authorizationResponse.getBody().get("message").equals("Autorizado")) {
            return true;
        } else return false;

    }
    
}
