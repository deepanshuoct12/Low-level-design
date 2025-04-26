import org.upiSystem.demo.Driver;
import org.testng.annotations.Test;

public class DriverTest {

    private Driver driver = new Driver();

    @Test
    public void driverTest() {
        driver.startExecution();
    }
}
