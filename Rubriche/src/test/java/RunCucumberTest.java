import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources",
        glue = "cucumber.steps",
        plugin = {"html:testreportdir","json:testreportdir/testreport.json"}
)
public class RunCucumberTest {
}
