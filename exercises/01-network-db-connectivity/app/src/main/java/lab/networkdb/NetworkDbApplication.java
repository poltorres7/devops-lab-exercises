package lab.networkdb;

import java.net.InetAddress;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Map;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class NetworkDbApplication {
  public static void main(String[] args) {
    SpringApplication.run(NetworkDbApplication.class, args);
  }

  @GetMapping("/healthz")
  Map<String, String> health() {
    return Map.of("status", "ok");
  }

  @GetMapping("/db-check")
  Map<String, String> dbCheck() throws Exception {
    String host = required("DB_HOST");
    String port = System.getenv().getOrDefault("DB_PORT", "25060");
    String db = System.getenv().getOrDefault("DB_NAME", "lab");
    String user = required("DB_USER");
    String password = required("DB_PASSWORD");

    String dns = InetAddress.getByName(host).getHostAddress();
    String jdbcUrl = "jdbc:mysql://" + host + ":" + port + "/" + db + "?useSSL=true&requireSSL=true";

    try (Connection ignored = DriverManager.getConnection(jdbcUrl, user, password)) {
      return Map.of("status", "connected", "host", host, "resolvedIp", dns);
    }
  }

  private static String required(String key) {
    String value = System.getenv(key);
    if (value == null || value.isBlank()) {
      throw new IllegalStateException("Missing required env var: " + key);
    }
    return value;
  }
}
