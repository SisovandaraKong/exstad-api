package istad.co.exstadbackendapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ExstadApi {

    public static void main(String[] args) {
        SpringApplication.run(ExstadApi.class, args);
    }

}
