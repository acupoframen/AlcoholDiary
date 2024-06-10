package hwanchoe.spbootdiary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SpbootdiaryApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpbootdiaryApplication.class, args);
    }

}
