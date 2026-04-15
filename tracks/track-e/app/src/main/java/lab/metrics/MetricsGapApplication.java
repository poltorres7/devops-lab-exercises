package lab.metrics;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import io.prometheus.client.Counter;
import io.prometheus.client.exporter.HTTPServer;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.Executors;

public class MetricsGapApplication {
  private static final Counter STATUS_REQUESTS =
      Counter.build()
          .name("lab_status_requests_total")
          .help("Total /status requests.")
          .register();

  public static void main(String[] args) throws Exception {
    HTTPServer metricsServer = new HTTPServer(9404);

    HttpServer appServer = HttpServer.create(new InetSocketAddress(8080), 0);
    appServer.createContext("/status", new StatusHandler());
    appServer.setExecutor(Executors.newFixedThreadPool(4));
    appServer.start();

    Runtime.getRuntime().addShutdownHook(new Thread(() -> {
      appServer.stop(0);
      metricsServer.stop();
    }));
  }

  private static final class StatusHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
      STATUS_REQUESTS.inc();
      byte[] body = "{\"status\":\"ok\"}".getBytes(StandardCharsets.UTF_8);
      exchange.getResponseHeaders().add("Content-Type", "application/json");
      exchange.sendResponseHeaders(200, body.length);
      try (OutputStream outputStream = exchange.getResponseBody()) {
        outputStream.write(body);
      }
    }
  }
}
