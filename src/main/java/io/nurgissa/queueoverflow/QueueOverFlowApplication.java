package io.nurgissa.queueoverflow;

import io.nurgissa.queueoverflow.auth.AuthenticationService;
import io.nurgissa.queueoverflow.auth.RegisterRequest;
import io.nurgissa.queueoverflow.models.enums.Role;
import io.nurgissa.queueoverflow.service.impl.TagServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static io.nurgissa.queueoverflow.models.enums.Role.ADMIN;
import static io.nurgissa.queueoverflow.models.enums.Role.MODERATOR;

@SpringBootApplication
public class QueueOverFlowApplication {

    public static void main(String[] args) {
        SpringApplication.run(QueueOverFlowApplication.class, args);
    }
//    @Bean
//    public CommandLineRunner commandLineRunner(
//            AuthenticationService service
//    ) {
//        return args -> {
//            var admin = RegisterRequest.builder()
//                    .username("Admin")
//                    .email("admin@mail.com")
//                    .password("password")
//                    .role(ADMIN)
//                    .build();
//            System.out.println("Admin token: " + service.register(admin).getAccessToken());
//
//            var manager = RegisterRequest.builder()
//                    .username("Moderator")
//                    .email("manager@mail.com")
//                    .password("password")
//                    .role(MODERATOR)
//                    .build();
//            System.out.println("Manager token: " + service.register(manager).getAccessToken());
//        };
//    }


}
