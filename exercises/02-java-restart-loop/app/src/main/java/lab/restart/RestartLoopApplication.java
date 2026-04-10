package lab.restart;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RestartLoopApplication {
  public static void main(String[] args) {
    SpringApplication.run(RestartLoopApplication.class, args);
  }

  @Bean
  CommandLineRunner crashAtStartup() {
    return args -> {
      throw new IllegalStateException("Intentional startup failure for the restart-loop exercise");
    };
  }
}
