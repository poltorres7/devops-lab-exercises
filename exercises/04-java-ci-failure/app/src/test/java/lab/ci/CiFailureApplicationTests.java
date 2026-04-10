package lab.ci;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CiFailureApplicationTests {
  @Test
  void intentionallyFails() {
    assertEquals(200, 500, "Intentional failure for the CI troubleshooting exercise");
  }
}
