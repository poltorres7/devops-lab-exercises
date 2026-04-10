package lab.ci;

import java.util.Map;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class CiFailureApplication {
  public static void main(String[] args) {
    SpringApplication.run(CiFailureApplication.class, args);
  }

  @GetMapping("/healthz")
  Map<String, String> health() {
    return Map.of("status", "ok");
  }
}
