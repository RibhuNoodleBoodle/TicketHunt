package com.project.authentication.rabbitmq;

import com.project.authentication.domain.User;
import com.project.authentication.service.UserService;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    @Autowired
    UserService userService;

    @RabbitListener(queuesToDeclare = @Queue("user_queue"))
    public void getDTOFromQueueAndAddToDb(UserDTO userDTO) {
        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setRole(userDTO.getRole());

        User user1 = userService.addUser(user);
        System.out.println("Result = " + user1);
    }
}
