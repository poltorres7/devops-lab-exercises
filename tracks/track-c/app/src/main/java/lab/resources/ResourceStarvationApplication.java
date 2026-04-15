package lab.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ResourceStarvationApplication {
  private static final List<byte[]> BLOCKS = new ArrayList<>();

  public static void main(String[] args) {
    SpringApplication.run(ResourceStarvationApplication.class, args);
  }

  @GetMapping("/healthz")
  Map<String, String> health() {
    return Map.of("status", "ok", "allocatedBlocks", Integer.toString(BLOCKS.size()));
  }

  @Bean
  CommandLineRunner allocateMemory() {
    return args -> {
      for (int i = 0; i < 32; i++) {
        BLOCKS.add(new byte[4 * 1024 * 1024]);
      }
      System.out.println("Allocated blocks: " + BLOCKS.size());
    };
  }
}
