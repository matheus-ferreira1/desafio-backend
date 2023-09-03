package br.com.matheusferreira.picpaysimplificado.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.matheusferreira.picpaysimplificado.dtos.NotificationDTO;
import br.com.matheusferreira.picpaysimplificado.entities.User;

@Service
public class NotificationService {

    @Autowired
    private RestTemplate restTemplate;

    public void sendNotification(User user, String message) throws Exception {

        String email = user.getEmail();

        NotificationDTO notificationRequest = new NotificationDTO(email, message);

        // ResponseEntity<String> notificationResponse = restTemplate.postForEntity("o4d9z.mocklab.io/notify", notificationRequest, String.class);

        // if (!(notificationResponse.getStatusCode() == HttpStatus.OK)) {
        //     System.out.println("Error sending notification");
        //     throw new Exception("Error sending notification");
        // }

        System.out.println("Notification sent");
        
    }
    
}
