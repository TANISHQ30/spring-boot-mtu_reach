package ie.tanishq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        System.out.println("Starting MTU Reach application....");
        SpringApplication.run(Application.class, args);
    }

}
