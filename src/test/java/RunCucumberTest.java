import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "html:target/cucumber-report.html"},
        features = {"src/test/resources"},
        glue = "org.cheuni02.stepdefs"
)
public class RunCucumberTest {
}
